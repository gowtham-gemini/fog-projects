package com.assistanz.fogpanel.licensemanager

import org.codehaus.groovy.grails.validation.Validateable
import org.springframework.security.access.annotation.Secured
import com.kayako.api.ticket.Ticket;
import com.assistanz.fogpanel.licensemanager.*;

@Secured(['ROLE_USER'])
class ApplicationsController {
    
    def springSecurityService
    def applicationService
    def ticketService 
    static listOfPriority
    
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def currentUser = springSecurityService.currentUser
        def appsQuery = ProductInstance.where {
            user == currentUser
        }
        respond currentUser, model:[productInstanceInstanceList: appsQuery.list(params), productInstanceCount: appsQuery.count()]
    }
    
    def show() {
        def productInstance = ProductInstance.get(params.id)
        def validationLogList = ValidationLog.withCriteria {
            eq("productInstance", productInstance)  
            order("createdDate", "desc")
            maxResults(10)
        }
              
        [productInstance: productInstance, validationLogList: validationLogList, updateMessage: params.message]
    }
    
    def newApp() { 
        def productsList = Product.activeBaseProducts.list()
        respond productsList, model: [productsList: productsList]
    }
    
    def releases() {
        def releaseList = Release.listOrderByProductVersion(order: "desc")
        respond releaseList, model: [releaseList: releaseList]
    }
    
    def changeName() {
        
        def productInstance = ProductInstance.get(params.id)
        productInstance.name = params.customName
        productInstance.save(flush: true)     
               
        def validationLogList = ValidationLog.withCriteria {
            eq("productInstance", productInstance) 
            order("createdDate", "desc")
            maxResults(10)
        }
        
        flash.message = "Name updated successfully"
        render(view: "show", model: [productInstance: productInstance, validationLogList: validationLogList, messageSuccess: flash.message])
        
    }
    
    def showAddAdditionalLicense() {
        
        def productInstance = ProductInstance.get(params.id)
          
        def additionalProductList = Product.findAllByBaseProduct(productInstance.product)
        
        [productInstance: productInstance, additionalProductList:additionalProductList]
        
    }
    
    def selectedProduct() { 
        if (!params.productId) {
            render "Select a product"
        }
        
        def baseProduct = Product.get(params.productId)
        
        def productsQuery = Product.where {
            id == params.productId || baseProduct == baseProduct
        }
        
        respond productsQuery.list(), model: [productsList: productsQuery.list(), baseProduct: baseProduct]
    }

    def freeTrial() { 
        
        ProductInstance.countByUserAndStatus(springSecurityService.currentUser, ProductInstance.Status.FREE_TRIAL)
        
        if (!params.agree && params.agree != 1 ) {
            def product = Product.get(params.id)
            return render(view: "licenseAgreement", model: [productId: product.id,productName : product.name,page: "freeTrial"])
        }
        
        def productInstance = new ProductInstance()
        productInstance.product = Product.get(params.productId)
        productInstance.user = springSecurityService.currentUser
        productInstance.baseLicense = 5
        productInstance.additionalLicense = 0
        productInstance.inceptionDate = new Date()
        
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DATE, 45);  // number of days to add
        productInstance.expirationDate = calender.getTime()
        productInstance.secretKey = "XXXYYY"
        productInstance.save flush:true
        
        flash.message = "New license created successfully"
        
        redirect action:"show", id: productInstance.id
    }
        
    def buyAdditionalLicenseForInstanse() {
        
        def productInstance = ProductInstance.get(params.id)
        
        for(def i = 0; i<params.quantity.length ; i++) {
            
            def quantity = params.quantity[i]
            def host =  params.additionalHostQuantity[i]
            
            if(quantity != 0) {
                
                double totalAdditionalLicense = Long.parseLong(quantity) * Long.parseLong(host)

                productInstance.additionalLicense += totalAdditionalLicense
            }

        }
        productInstance.save flush:true
        redirect action:"index"
    }
    
    def buyLicense() {
        
        try {
            
            applicationService.buyLicense(params)
            redirect action:"index"
            
        } catch (Exception ex) {
//           
            Console.print(ex)
            
            flash.message = "Failed Due to:" + ex.getMessage()
            render(view: "newApp", model: [message: flash.message])
        }
        
    }
    
    def renewLicense() {
       
        def productInstance = ProductInstance.get(params.id)
        
        Calendar calender = Calendar.getInstance();
        calender.setTime(productInstance.expirationDate)
        calender.add(Calendar.MONTH, params.licenseExpiryMonth.toInteger());  
        
        productInstance.expirationDate = calender.getTime()
        productInstance.save flush:true
        
        redirect action:"index"
    }
    
    def createTicket() {
        if(params.id == null) {
            def currentUser = springSecurityService.currentUser
            def appsQuery = ProductInstance.where {
                user == currentUser
            }
            def priorityList = getPriority()
            ArrayList departmentList = new ArrayList();
            def departments = ticketService.getTicketDepartments()    
            for (def department : departments) {
                def departmentTitle = department.getComponents("title");
                departmentList.add(departmentTitle.get(0).getContent())
                render(view: "createTicket", model:[productInstanceList: appsQuery.list(), priorityTitle : priorityList, departmentTitle : departmentList])
            }
        } else if(params.id != null) {
            def currentUser = springSecurityService.currentUser
            def appsQuery = ProductInstance.where {
                user == currentUser
            }
            def priorityList = getPriority()
            ArrayList departmentList = new ArrayList();
            def departments = ticketService.getTicketDepartments()    
            for (def department : departments) {
                def departmentTitle = department.getComponents("title");
                departmentList.add(departmentTitle.get(0).getContent())
                render(view: "createTicket", model:[licenseId : params.id,productInstanceList: appsQuery.list(), priorityTitle : priorityList, departmentTitle : departmentList])
            }
        }
    }
    
    def getPriority() {
        
        if(listOfPriority == null) {
            ArrayList priorityList = new ArrayList();
            def priorities = ticketService.getTicketPriority()
            for (def priority : priorities) {
                def priorityTitle = priority.getComponents("title");
                priorityList.add(priorityTitle.get(0).getContent())
                listOfPriority = priorityList
            }  
        }
        return listOfPriority
    }
    
    def saveTicket() {
        
        def values = params;
        values.remove("action")
        values.remove("controller")
        TicketCommand ticket = new TicketCommand(values)
        if(!ticket.validate()) {
            render(view: "createTicket", model: [ticketInstance: ticket])
        }
        else {
            def currentUserName = springSecurityService.currentUser.firstName
            currentUserName = currentUserName.concat(springSecurityService.currentUser.lastName)
            def currentUserEmail = springSecurityService.currentUser.username
            def ticketId = ticketService.createTicket(ticket, currentUserName, currentUserEmail) 
            redirect(action: "showTicket", params: [id: ticketId])
            flash.message = "Ticket created Successfully";
        }
    }
    
    def showTicket() {
        
        def priorities = getPriority()
        def ticketId = params.id;
        Ticket ticket = ticketService.findTicket(ticketId)
        def name = springSecurityService.currentUser
        render(view: "showTicket", model:[ticket : ticket, ticketId:ticketId, priorities : priorities])
    }
    
    def support() {
    
        def currentUserEmail = springSecurityService.currentUser.username
        def tickets = ticketService.listTickets(currentUserEmail)
        render(view: "support", model:[tickets : tickets])
    }
    
    def addPosts() {
        
        def ticketId = params.ticketId
        def posts = params.posts
        def currentUserEmail = springSecurityService.currentUser.username
        ticketService.addPosts(ticketId, posts, currentUserEmail)
        redirect(action: "showTicket", params: [id: ticketId])
        flash.message = "Post added Successfully";
    }
    
    def editPriority() {
        
        def priority = params.listBox;
        def ticketId = params.ticketId;
        ticketService.editPriority(ticketId, priority)
        redirect(action: "showTicket", params: [id: ticketId])
        flash.message = "Priority Updated Successfully";
    }
}

@grails.validation.Validateable

class TicketCommand {
    String department
    String priority
    String instanceId
    String subject
    String description

    static constraints = { 
        subject blank: false, nullable: false 
        description blank: false, nullable: false 
    } 
}

