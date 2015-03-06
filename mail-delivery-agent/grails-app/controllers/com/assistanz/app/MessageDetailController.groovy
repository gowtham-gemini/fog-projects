package com.assistanz.app



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import com.assistanz.app.MailSenderService
import com.assistanz.app.ListenerService
import grails.plugins.springsecurity.Secured
import grails.converters.deep.JSON

@Transactional(readOnly = true)
class MessageDetailController {
    
    MailSenderService mailSenderService;
    ListenerService listenerService;

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    @Secured(['ROLE_USER','ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MessageDetail.list(params), model:[messageDetailInstanceCount: MessageDetail.count()]
    }
    
    @Secured(['ROLE_ADMIN'])
    def show(MessageDetail messageDetailInstance) {
        respond messageDetailInstance
    }
    
    @Secured(['ROLE_ADMIN'])
    def create() {
        respond new MessageDetail(params)
    }

    @Transactional
    @Secured(['ROLE_ADMIN'])
    def save(MessageDetail messageDetailInstance) {
        
        println("params"+params)
        
        if (messageDetailInstance == null) {
            notFound()
            return
        }
        //custom saving of mails to db
        mailSenderService.sendEmails(params);
        
        // explicit redirect
        redirect action: "index", method: "GET"
        return
    }
    
    @Secured(['ROLE_ADMIN'])
    @Transactional
    def resendAllUndeliveredMails() {
        def c = MessageDetail.createCriteria()
        def results = c.list {
            eq("isDelivered", false)
            order("date", "desc")
        }
        mailSenderService.resendEmails(results)
        
        // explicit redirect
        redirect action: "index", method: "GET"
        return 
    }
    
    @Secured(['ROLE_ADMIN'])
    @Transactional
    def resendHijax(MessageDetail messageDetailInstance) {
        
        def results = new ArrayList()
        results.add(messageDetailInstance)
        mailSenderService.resendEmails(results)
        // explicit redirect
        redirect action: "index", method: "GET"
        return 
    }
    
    @Secured(['ROLE_ADMIN'])
    def edit(MessageDetail messageDetailInstance) {
        respond messageDetailInstance
    }
    
    @Transactional
    def update(MessageDetail messageDetailInstance) {
        if (messageDetailInstance == null) {
            notFound()
            return
        }

        if (messageDetailInstance.hasErrors()) {
            respond messageDetailInstance.errors, view:'edit'
            return
        }

        messageDetailInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MessageDetail.label', default: 'MessageDetail'), messageDetailInstance.id])
                redirect messageDetailInstance
            }
            '*'{ respond messageDetailInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(MessageDetail messageDetailInstance) {

        if (messageDetailInstance == null) {
            notFound()
            return
        }

        messageDetailInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MessageDetail.label', default: 'MessageDetail'), messageDetailInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    @Transactional
    @Secured(['ROLE_ADMIN'])
    def deleteParent(MessageDetail messageDetailInstance) {

        if (messageDetailInstance == null) {
            notFound()
            return
        }
        // finding parent and delete it
        def message = messageDetailInstance.message
        def json = message as JSON
        message.delete flush:true
        request.withFormat {
            form multipartForm {
                //flash.message = message(code: 'default.deleted.message', args: [message(code: 'MessageDetail.label', default: 'MessageDetail'), messageDetailInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }
    @Transactional
    @Secured(['ROLE_ADMIN'])
    def deleteall() {
        
        // clearing the whole mail list
        def messages = Message.list()
        for (messageInstance in messages) {
            messageInstance.delete flush:true
        }
        // explicit redirect
        redirect action: "index", method: "GET"
        return
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'messageDetail.label', default: 'MessageDetail'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
