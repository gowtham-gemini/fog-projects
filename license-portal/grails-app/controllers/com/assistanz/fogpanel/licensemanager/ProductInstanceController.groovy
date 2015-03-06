package com.assistanz.fogpanel.licensemanager



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN', 'ROLE_SUPPORT'])
class ProductInstanceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def validationService;
    
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProductInstance.list(params), model:[productInstanceCount: ProductInstance.count()]
    }

    def show(ProductInstance productInstanceInstance) {
        def productInstance = ProductInstance.get(params.id)
        def validationLogList = ValidationLog.withCriteria {
            eq("productInstance", productInstance)  
            order("createdDate", "desc")
            maxResults(10)
        }
                
        [productInstanceInstance: productInstance, validationLogList: validationLogList, updateMessage: params.message]
    }

    def create() {
        def productsList = Product.activeProducts.list()
        respond new ProductInstance(params), model: [productsList: productsList]
    }

    @Transactional
    def save(ProductInstance productInstanceInstance) {
        if (productInstanceInstance == null) {
            notFound()
            return
        }
        
        productInstanceInstance.secretKey = "XXXYYY"
        productInstanceInstance.validate()

        if (productInstanceInstance.hasErrors()) {
            def productsList = Product.activeProducts.list()
            respond productInstanceInstance.errors, view:'create', model: [productsList: productsList]
            return
        }

        productInstanceInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'productInstance.label', default: 'ProductInstance'), productInstanceInstance.id])
                redirect productInstanceInstance
            }
            '*' { respond productInstanceInstance, [status: CREATED] }
        }
    }

    def edit(ProductInstance productInstanceInstance) {
     
        [productInstanceInstance: productInstanceInstance, productList: Product.findAllWhere(baseProduct: null)]
    }

    @Transactional
    def update(ProductInstance productInstanceInstance) {
                
        if (productInstanceInstance == null) {
            notFound()
            return
        }

        if (productInstanceInstance.hasErrors()) {
            respond productInstanceInstance.errors, view:'edit'
            return
        }
        productInstanceInstance.baseLicense = productInstanceInstance.product.licenseQuantity
        productInstanceInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ProductInstance.label', default: 'ProductInstance'), productInstanceInstance.id])
                redirect productInstanceInstance
            }
            '*'{ respond productInstanceInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ProductInstance productInstanceInstance) {

        if (productInstanceInstance == null) {
            notFound()
            return
        }

        productInstanceInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ProductInstance.label', default: 'ProductInstance'), productInstanceInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    
    
    def generateChecksum(ProductInstance productInstanceInstance) {
        
        [checksum: validationService.generateChecksum(productInstanceInstance) ]
        
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'productInstance.label', default: 'ProductInstance'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
