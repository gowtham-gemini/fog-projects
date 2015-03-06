package com.assistanz.fogpanel

import grails.converters.deep.JSON
import java.text.SimpleDateFormat
import java.sql.Timestamp
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory;
import org.codehaus.groovy.grails.commons.ApplicationHolder

@Transactional
class SupportService {

    def springSecurityService; 
    NotificationService notificationService
    private static final log = LogFactory.getLog(this)
    
    def addDepartment(requestBody) {
      
        try {  
        def user = springSecurityService.currentUser
        // convert string to json object
            def requestData = JSON.parse(requestBody) 


            def department = new SupportDepartment()
            department.name = requestData.name
            department.deleted = false
            department.save(flush: true);
            log.info("Department added with name: ${requestData.name} by user ${user.id}")
        
        } catch (Exception ex){  
               throw ex; 
        }
        
    } 
    
    def ticketStatusCount(departmentId, account) {
        try {  
            
            def department
            if(departmentId != "ALL") {
                department = SupportDepartment.get(departmentId)
            }
            ArrayList<ArrayList<String>> departmentListArray = new ArrayList<ArrayList<String>>();
            
            def open = Ticket.withCriteria {
                if(departmentId != "ALL") {
                    eq("department", department) 
                }
                if(account != "ALL") {
                    eq("account", Account.get(account))  
                }
                and {
                    eq("state", "OPEN")
                }
                
            } 
            
            def closed = Ticket.withCriteria {
                if(departmentId != "ALL") {
                    eq("department", department) 
                }
                if(account != "ALL") {
                    eq("account", Account.get(account))  
                }
                and {
                    eq("state", "CLOSE")
                }
                
            } 
            
            def onHold = Ticket.withCriteria {
                if(departmentId != "ALL") {
                    eq("department", department) 
                }
                if(account != "ALL") {
                    eq("account", Account.get(account))  
                }
                and {
                    eq("state", "ON_HOLD")
                }
                
            } 
            
            def inprogress = Ticket.withCriteria {
                if(departmentId != "ALL") {
                    eq("department", department) 
                }
                if(account != "ALL") {
                    eq("account", Account.get(account))  
                }
                and {
                    eq("state", "IN_PROGRSS")
                }
                
            } 
            
            def active = Ticket.withCriteria {
                if(departmentId != "ALL") {
                    eq("department", department)  
                }
                if(account != "ALL") {
                    eq("account", Account.get(account))  
                }
                and {
                    ne("state", "CLOSE")
                }
                
            } 
            
            HashMap item = new HashMap();
            item.put("active", active.size());
            item.put("open", open.size());
            item.put("closed", closed.size());
            item.put("onHold", onHold.size());
            item.put("inProgress", inprogress.size());
            departmentListArray.add(item)
            
            return departmentListArray
//            Console.print(ticketList); 
            
        } catch (Exception ex){  
               throw ex; 
        }
    }
    
    def ticketFilter(departmentId, status, userId, subject) {
        
        
        def department
        if(departmentId != "ALL") {
            department = SupportDepartment.get(departmentId)
        }
        def account
        if(userId != "ALL") {
            account = Account.get(userId)
        } 
        
//        def ticketCriteria = Ticket.createCriteria()
//        def ticketList = ticketCriteria.list {
//             if(departmentId != "ALL") {
//                eq("department", department) 
//            }
//            if(userId != "ALL") {
//                eq("account", account) 
//            }
//            if(subject != "") {
//                Console.print("subject" + subject)
//              ilike("subject", subject+"%")  
//            }
//            if(status == "ACTIVE") {  
//                Console.print("status" + status)
//                 ne("state", "CLOSE") 
//            } else {
//                Console.print("status" + status)
//                eq("state", status)  
//            }
//            order("id", "desc")
//        }
        
        def ticketList = Ticket.withCriteria {  
            if(departmentId != "ALL") {
                eq("department", department) 
            }
            if(userId != "ALL") {
                eq("account", account) 
            }
            if(subject != "") {
              ilike("subject", subject+"%")  
            }
            if(status == "ACTIVE") {   
                ne("state", "CLOSE") 
            } else if(status == "ALL") {   
                ne("state", "") 
            } else {
                eq("state", status) 
            }
            order("id", "desc")
        } 
        ArrayList<ArrayList<String>> ticListArray = new ArrayList<ArrayList<String>>();
        for(def ticket: ticketList) {
            
            HashMap<String,String> item = new HashMap<String,String>();  
            item.put("id",  ticket.id);
            item.put("subject",  ticket.subject);
            item.put("priority",  ticket.priority);
            item.put("state",  ticket.state);
            item.put("account",  ticket.account.firstName);
            item.put("user",  ticket.user.username);
            item.put("department",  ticket.department.name);
            item.put("departmentId",  ticket.department.id);
            item.put("date",  ticket.date.toString());
            item.put("content",  ticket.content);
            ticListArray.add(item);
        }
        
        return ticListArray;
        
        
    }
    
    def ticketCount() {
        
        try {  

            def newTicket = Ticket.findAll();

            def newCount = 0;
            def staffReply = 0;
            def clientReply = 0;

            for(def tic : newTicket) {
                def rep = TicketDetails.findAllWhere(ticket: tic)

                if(rep.size() == 1) {
                    newCount++
                } else {
                    
                def reply = TicketDetails.withCriteria {
                    eq("ticket", tic)
                    order("id", "desc")
                } 
                
                if(reply[0].userType == "Admin") {
                     staffReply++
                } else if(reply[0].userType == "Client") {
                    clientReply++
                }
                    
                }
            }
            
            ArrayList<ArrayList<String>> ticListArray = new ArrayList<ArrayList<String>>();
            HashMap<String,String> item = new HashMap<String,String>();  
            item.put("newTic",  newCount);
            item.put("staff",  staffReply);
            item.put("client",  clientReply);
            ticListArray.add(item);
            
            return ticListArray;
        
        } catch (Exception ex){  
               throw ex; 
        }
    }
    
    def addDefinedReply(requestBody) {
      
        try {  
            
             def user = springSecurityService.currentUser
            // convert string to json object
            def requestData = JSON.parse(requestBody) 


            def definedReply = new PreDefinedReply()
            definedReply.content = requestData.content
            definedReply.subject = requestData.subject
            definedReply.deleted = false
            definedReply.department = SupportDepartment.get(requestData.department) 
            definedReply.save(flush: true);
            
            log.info("Pre Defined Reply added with subject: ${requestData.subject} by user ${user.id}")
        
        } catch (Exception ex){  
               throw ex; 
        }
        
    }
    
    def updateDefaultReply(requestBody) {
         try {  
        
            def user = springSecurityService.currentUser
            
            // convert string to json object
            def requestData = JSON.parse(requestBody) 


            def definedReply = PreDefinedReply.get(requestData.id)
            definedReply.content = requestData.content
            definedReply.subject = requestData.subject
            definedReply.department = SupportDepartment.get(requestData.department) 
            definedReply.save(flush: true);
            
            log.info("Pre Defined Reply updated to: ${requestBody} by user ${user.id}")
        
        } catch (Exception ex){  
               throw ex; 
        }
    }
    
    def updateDepartment(requestBody) {
         try {  
             
            def user = springSecurityService.currentUser
            
            // convert string to json object
            def requestData = JSON.parse(requestBody) 


            def dep = SupportDepartment.get(requestData.id)
            dep.name = requestData.name
            dep.save(flush: true);
            
            log.info("Department updated to: ${requestBody} by user ${user.id}")
        
        } catch (Exception ex){  
               throw ex; 
        }
    }
    
    def deleteDefaultReply(id) {
         try {  
             
            def user = springSecurityService.currentUser
                    
            def definedReply = PreDefinedReply.get(id)
            definedReply.deleted = true
            definedReply.save(flush: true);
            
            log.info("DefaultReply :${definedReply.id} is deleted by user ${user.id}")
        
        } catch (Exception ex){  
               throw ex; 
        }
    }
    
    def deleteDep(id) {
         try {  
            
            def user = springSecurityService.currentUser
            
            def dep = SupportDepartment.get(id)
            dep.deleted = true
            dep.save(flush: true);
            
            log.info("Department :${dep.id} is deleted by user ${user.id}")
        
        } catch (Exception ex){  
               throw ex; 
        }
    }
    
    
    def addTicket(requestBody) {
      
        try {  
             def user = springSecurityService.currentUser
            // convert string to json object
            def requestData = JSON.parse(requestBody) 
            
            Calendar cal = Calendar.getInstance();

            def ticket = new Ticket()
            ticket.content = requestData.content
            ticket.subject = requestData.subject
            ticket.department = SupportDepartment.get(requestData.department) 
            ticket.priority = requestData.priority
            ticket.state = requestData.state
            ticket.account = user.account
            ticket.user = user
            ticket.date = cal.getTime()
            ticket.save(flush: true);
            
            def ticketDetails = new TicketDetails()
            ticketDetails.content = requestData.content
            ticketDetails.subject = requestData.subject
            ticketDetails.state = ticket.state
            ticketDetails.userType = "Client"
            ticketDetails.deleted = false
            ticketDetails.ticket = ticket
            ticketDetails.user = user
            ticketDetails.account = user.account
            ticketDetails.date = cal.getTime()
            ticketDetails.save(flush: true);
            
            log.info("Ticket added Id: ${ticket.id}, subject: ${requestData.subject}")
            
            sendMailForTicket(ticketDetails, "Ticket Opened")
        } catch (Exception ex){  
            
            log.info("Ticket added failed due to ${ex}")
            throw ex; 
        }
        
    } 
    def sendMailForTicket(ticketDetails, ticketStatus) {
        try {                      
            def user = ticketDetails.ticket.user
            def applicationUrl = ApplicationHolder.getApplication().config.openstack.applicationUrl
            ArrayList<ArrayList<String>> sendMailResultList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            if(user) {   
                Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                if(ticketStatus == "Ticket Replied" || ticketStatus == "Ticket Closed") {
                    
                    tempalteMap.put("ticketStatus", ticketStatus)
                    tempalteMap.put("ticketId", ticketDetails.id.toString())
                    tempalteMap.put("department", ticketDetails.ticket.department.name)
                    tempalteMap.put("status", ticketDetails.state)
                    tempalteMap.put("subject", ticketDetails.subject)
                    tempalteMap.put("content", ticketDetails.content)
                    tempalteMap.put("postedDate", ticketDetails.date.toString())
                    tempalteMap.put("priority", ticketDetails.ticket.priority)
                    tempalteMap.put("user", user)
                   
                } else {
                    tempalteMap.put("ticketStatus", ticketStatus)
                    tempalteMap.put("ticketId", ticketDetails.id.toString())
                    tempalteMap.put("department", ticketDetails.ticket.department.name)
                    tempalteMap.put("status", ticketDetails.state)
                    tempalteMap.put("subject", ticketDetails.subject)
                    tempalteMap.put("content", ticketDetails.content)
                    tempalteMap.put("postedDate", ticketDetails.date.toString())
                    tempalteMap.put("priority", ticketDetails.ticket.priority) 
                    tempalteMap.put("user", user)
                }                                                                
                               
                notificationService.send(user.username.toString(), "ticket.subject.ftl", tempalteMap, "ticket.ftl")   
                
                item.put("Result",  GeneralConstants.RESULT_SUCCESS);
                sendMailResultList.add(item);                              
            } else {
                item.put("Result",  "No user found");
                sendMailResultList.add(item);
            }
            return sendMailResultList;            
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def replyTicket(requestBody) {
      
        try {  
             def user = springSecurityService.currentUser
            // convert string to json object
            def requestData = JSON.parse(requestBody) 
            
            Calendar cal = Calendar.getInstance();
            def ticketStatus;
            if(requestData.state == "OPEN") {
                ticketStatus = "Ticket Replied";
            } else if(requestData.state == "CLOSE"){
                ticketStatus = "Ticket Closed";
            } else {
                ticketStatus = "Ticket Opened";
            }
            def ticket = Ticket.get(Long.parseLong(requestData.ticketId));
            ticket.department = SupportDepartment.get(requestData.department) 
            if(requestData.priority) {
                if(requestData.userType == "Client") {
                    ticket.priority = requestData.priority
                } 
            }
            ticket.state = requestData.state
            ticket.save(flush: true);
            
            log.info("Reply for Ticket: ${ticket.id} , subject : ${requestData.subject} by user ${user.id}")
            
            def ticketDetails = new TicketDetails()
            ticketDetails.content = requestData.content
            ticketDetails.subject = requestData.subject
            ticketDetails.state = ticket.state
            ticketDetails.userType = requestData.userType
            ticketDetails.deleted = false
            ticketDetails.ticket = ticket
            ticketDetails.user = user
            ticketDetails.account = user.account
            ticketDetails.date = cal.getTime()
            ticketDetails.save(flush: true);
            sendMailForTicket(ticketDetails, ticketStatus)
        } catch (Exception ex){  
            log.info("Ticket Reply failed due to ${ex}")
            throw ex; 
        }
        
    } 
    
    def getTicket(id) {
        
        try {  
            def ticket = Ticket.get(id)
            
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            
            def ticketDetCriteria = TicketDetails.createCriteria()
            def ticketDetails = ticketDetCriteria.list {
                    eq("ticket", ticket)
                    and {
                        eq("deleted", false)
                    }
                    order("id", "desc")
                }
            
            ArrayList<ArrayList<String>> ticListArray = new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> ticDetailsArray = new ArrayList<ArrayList<String>>();
         
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
            
            HashMap item = new HashMap();  
            item.put("id",  ticket.id);
            item.put("subject",  ticket.subject);
            item.put("state",  ticket.state);
            item.put("content",  ticket.content);
            item.put("department",  ticket.department.name);
            item.put("departmentId",  ticket.department.id); 
            item.put("account",  ticket.account.firstName);
            item.put("priority",  ticket.priority);
            item.put("accountStatus",  ticket.account.status.name());
            item.put("user",  ticket.user.username);
            item.put("date",  dateFormat.format(ticket.date).toString());
                
        
            for(def ticketDetail: ticketDetails) { 
                
                if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
                    
                    if(ticketDetail.userType == "Client") { 
                        ticketDetail.viewed =true;
                        ticketDetail.save(flush: true); 
                    }
                    
                    
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    
                     if(ticketDetail.userType == "Admin") {
                        ticketDetail.viewed =true;
                        ticketDetail.save(flush: true); 
                    }
                }
                
                HashMap titem = new HashMap();  
                titem.put("ticketDetailId",  ticketDetail.id);
                titem.put("ticketDetailSubject",  ticketDetail.subject);
                titem.put("ticketDetailState",  ticketDetail.state);
                titem.put("ticketDetailContent",  ticketDetail.content);
                titem.put("ticketDetailAccount",  ticketDetail.account.firstName);
                titem.put("ticketDetailUser",  ticketDetail.user.username);
                titem.put("ticketDetailUserType",  ticketDetail.userType);
                titem.put("ticketDetailDate",  dateFormat.format(ticketDetail.date).toString());  
                ticDetailsArray.add(titem)
                
            }
            item.put("ticketDetails",  ticDetailsArray);
            ticListArray.add(item)
            
            return ticListArray
            
        } catch (Exception ex){  
               throw ex; 
        }
    }
    
    
    def ticketNotification() {
        try {  
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities() 
            
            
            ArrayList<ArrayList<String>> ticNotificationArray = new ArrayList<ArrayList<String>>();
            
            def ticketList;
            
            if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
                def ticketCriteria = Ticket.createCriteria()
                ticketList = ticketCriteria.list {
                   order("id", "desc")
                }
                
                for(def tic: ticketList) {

                    def ticketRepCriteria = TicketDetails.createCriteria() 
                    def ticReply = ticketRepCriteria.list {
                        eq("ticket", tic)
                        and {
                            eq("userType", "Client")  
                            and {
                                eq("viewed", false)
                            }
                        }
                        order("id", "desc")
                    }

                    if(ticReply.size() !=0) {
                        HashMap item = new HashMap();  
                        item.put("ticId",  tic.id);
                        item.put("id",  ticReply[0].id);
                        item.put("content",  ticReply[0].content);
                        item.put("subject",  ticReply[0].subject);
                        item.put("icon", "icon-bell-alt");
                        item.put("link", "#/admin/support/viewTicket/"+tic.id); 
                        ticNotificationArray.add(item)
                    }
                }
                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                                
                def ticketCriteria = Ticket.createCriteria()
                ticketList = ticketCriteria.list {
                   eq("account", user.account) 
                   order("id", "desc")
                }
                
                for(def tic: ticketList) {

                    def ticketRepCriteria = TicketDetails.createCriteria() 
                    def ticReply = ticketRepCriteria.list {
                        eq("ticket", tic)
                        and{
                          eq("userType", "Admin") 
                          and {
                                eq("viewed", false)
                            }
                        }
                        order("id", "desc")
                    }
                    if(ticReply.size() !=0) {
                        HashMap item = new HashMap();  
                        item.put("ticId",  tic.id);
                        item.put("id",  ticReply[0].id);
                        item.put("content",  ticReply[0].content);
                        item.put("subject",  ticReply[0].subject);
                        item.put("icon", "icon-bell-alt");
                        item.put("link", "#/user/support/viewTicket/"+tic.id); 
                        ticNotificationArray.add(item)
                    }
                }
                
            }
                        
            return ticNotificationArray;
            
        } catch (Exception ex){  
               throw ex; 
        }
    }
    
    def getDefaultReply(id) {
        
        try {  
            def rep = PreDefinedReply.get(id)
            
            
            ArrayList<ArrayList<String>> ticListArray = new ArrayList<ArrayList<String>>();
                       
            HashMap item = new HashMap();  
            item.put("id",  rep.id);
            item.put("subject",  rep.subject);
            item.put("content",  rep.content);
            ticListArray.add(item)
            
            return ticListArray
            
        } catch (Exception ex){  
               throw ex; 
        }
        
       
    }
    
    def listTicket(departmentId, state) {
        
        
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities() 
        
        
        def department;
        if(departmentId != "ALL") {
          department = SupportDepartment.get(departmentId)  
        }
        def ticketList;
        
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        ArrayList<ArrayList<String>> ticListArray = new ArrayList<ArrayList<String>>();
        if((departmentId == "null" || departmentId == null) && (state == "null" || state == null)) {
            def ticketCriteria = Ticket.createCriteria()
            ticketList = ticketCriteria.list {
                if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    eq("account", user.account)
                }
                order("id", "desc")
            }
        } else {
            ticketList = Ticket.withCriteria {
                if(state != "ALL") {
                   eq("state", state)
                } 
                and {
                    if(departmentId != "ALL") {
                        eq("department", department)
                    }                 
                }
                if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    eq("account", user.account)
                }
                order("id", "desc")
            }
        } 
//        else if((departmentId == "null" || departmentId == null) && (state != "null" || state != null)) {
//            ticketList = Ticket.findAllWhere(state: state)
//        } else if((departmentId != "null" || departmentId != null) && (state != "null" || state != null)) {
//            
//            def ticketCriteria = Ticket.createCriteria()
//            ticketList = ticketCriteria.list {
//                    eq("state", state)
//                    and {
//                        eq("department", department)
//                    }
//                    order("id", "desc")
//                }
//        }
        
        
        for(def ticket: ticketList) {
            
            HashMap<String,String> item = new HashMap<String,String>();  
            item.put("id",  ticket.id);
            item.put("subject",  ticket.subject);
            item.put("priority",  ticket.priority);
            item.put("state",  ticket.state);
            item.put("account",  ticket.account.firstName);
            item.put("user",  ticket.user.username);
            item.put("department",  ticket.department.name);
            item.put("departmentId",  ticket.department.id);
            item.put("date",  ticket.date.toString());
            item.put("content",  ticket.content);
            ticListArray.add(item);
        }
        
        return ticListArray;
    }
    
    def listDepartment(requestBody) {
      
        ArrayList<ArrayList<String>> depListArray = new ArrayList<ArrayList<String>>();     
        
        try {  
        
            def depList = SupportDepartment.findAllWhere(deleted: false)
            
        
            for(def dep: depList) {
                
                HashMap<String,String> item = new HashMap<String,String>();  
                item.put("id",  dep.id);
                item.put("name",  dep.name);
                depListArray.add(item);
                
            }
            
            return depListArray
            
        } catch (Exception ex){  
               throw ex; 
        }
        
    } 
    
    
    def listDefaultReply(departmentId) {
      
        ArrayList<ArrayList<String>> replyListArray = new ArrayList<ArrayList<String>>();     
        
        try {
            def replyList;
            if(departmentId == "null" || departmentId == null) {
                replyList = PreDefinedReply.findAllWhere(deleted: false)
            } else if(departmentId != "null" || departmentId != null) {
                def department = SupportDepartment.get(Long.parseLong(departmentId))
                replyList = PreDefinedReply.findAllWhere(department: department, deleted: false)
            }
        
            for(def reply: replyList) {
                
                HashMap<String,String> item = new HashMap<String,String>();  
                item.put("id",  reply.id);
                item.put("content",  reply.content);
                item.put("subject",  reply.subject);
                item.put("department",  reply.department.name);
                item.put("departmentId",  reply.department.id);
                replyListArray.add(item);
            }
            return replyListArray
            
        } catch (Exception ex){  
               throw ex; 
        }
        
    }
    
    
    def supportGraph() {
        
        try {
            
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities() 
        
        Calendar currMonDateCalendar = Calendar.getInstance();
        currMonDateCalendar.set(Calendar.DATE, 1) 
        currMonDateCalendar.set(Calendar.HOUR_OF_DAY, 00);
        currMonDateCalendar.set(Calendar.MINUTE, 00);
        currMonDateCalendar.set(Calendar.SECOND, 00);
        currMonDateCalendar.set(Calendar.MILLISECOND, 00);  
        Date curMonStartDate = currMonDateCalendar.getTime()

        Calendar currDateCalendar = Calendar.getInstance();
        currDateCalendar.set(Calendar.HOUR_OF_DAY, 23);
        currDateCalendar.set(Calendar.MINUTE, 59);
        currDateCalendar.set(Calendar.SECOND, 59);
        currDateCalendar.set(Calendar.MILLISECOND, 999);  
        
        Date curMonToDate = currDateCalendar.getTime()
        
        def ticketCount;
        
        
        if(role.iterator().next().toString().equals("ROLE_ADMIN") ) {
            def ticket = Ticket.createCriteria()
            ticketCount = ticket.list {
                ge("date", curMonStartDate) and { le("date", curMonToDate) } 
            }
            
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            def ticket = Ticket.createCriteria()
            ticketCount = ticket.list {
                ge("date", curMonStartDate) and { le("date", curMonToDate) } 
                and {
                   eq("account", user.account)
                }
            }      
        }
        
        def close = 0;
        def inProgress = 0;
        def onHold = 0;
        def open = 0;
            
            
        for(def tic : ticketCount) {
            if(tic.state == "OPEN") {
               open++; 
            } else if(tic.state == "ON_HOLD") {
                onHold++;  
            } else if(tic.state == "CLOSE") {
                close++;  
            } else if(tic.state == "IN_PROGRSS") {
                inProgress++;  
            }
        }     
        ArrayList<ArrayList<String>> countArray = new ArrayList<ArrayList<String>>();       
        HashMap<String,String> item = new HashMap<String,String>();  
        item.put("close",  close);
        item.put("inProgress",  inProgress);
        item.put("onHold",  onHold);
        item.put("open",  open);
        countArray.add(item)
        
        return countArray
        
        } catch (Exception ex){  
               throw ex; 
        }
        
    }
    
} 
