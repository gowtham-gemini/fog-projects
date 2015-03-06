package com.assistanz.app

import grails.transaction.Transactional
import com.assistanz.app.MailExchange
import grails.converters.deep.JSON

@Transactional
public class MailSenderService {

    def serviceMethod() {

    }
    // persisting mail to db
    def sendEmails(def params) {
        
        // null and empty check 
        if(params != null && params.qTo != null && !params.qTo.empty) {
            
            // iterate params.qTo for more users
            def qTos = params.qTo.split(',')
            for(def to: qTos) {
                
                // passing through queue
                sendMailViaQueue(params, to)
//                
//                                def s = new Message (subject: params.subject, body: params.body)
//                                .addToMessageDetails(new MessageDetail(qFrom: params.qFrom, qTo: to))
//                                .save flush:true 
            }
        }
    }
    
    def sendMailViaQueue(def params,def to){
        
        MailExchange msg = new MailExchange()
        msg.jFrom = params.qFrom
        msg.jTo = to
        msg.jCc = ""
        msg.jBcc = ""
        msg.jSubject = params.subject
        msg.jMessageBody = params.body
        
        // Converting the Data to JSON Object
        String mailDataAsJSON = msg as JSON
        
        //println("mailDataAsJSON"+mailDataAsJSON)
        
        /* Sending the Message By its Exchange Name*/
        rabbitSend "fogPanelMailExchge","fogPanelMailExchge.#", mailDataAsJSON
        
        // after resending we have to clear the existing entry in db
        if(params != null && params.id != null) {
            
            MessageDetail messageDetail = MessageDetail.findById(params.id)
            Message message = Message.findById(messageDetail?.message?.id)
            message.delete flush:true
            println("existing entry cleared");
        }
        
    }
    //resending the undelivered emails
    def resendEmails(def messageDetails) {
        
        // null and empty check 
        if(messageDetails != null && !messageDetails.empty) {
            
            // iterate messagedetails
            for(def messageDetail: messageDetails) {
                
                MailExchange msg = new MailExchange()
                msg.jFrom = messageDetail?.qFrom?:""
                msg.jTo = messageDetail?.qTo?:""
                msg.jCc = messageDetail?.qCc?:""
                msg.jBcc = messageDetail?.qBcc?:""
                
                msg.jSubject = messageDetail?.message?.subject
                
                msg.jMessageBody = messageDetail?.message?.body
                
                // Converting the Data to JSON Object
                String mailDataAsJSON = msg as JSON
                
                //println("mailDataAsJSON : ["+messageDetail?.id +"]"+mailDataAsJSON)
                
                /* Sending the Message By its Exchange Name*/
                rabbitSend "fogPanelMailExchge","fogPanelMailExchge.#", mailDataAsJSON
                
                // after resending we have to clear the existing list in db
                //messageDetails*.delete()
                Message message = Message.findById(messageDetail?.message?.id)
                message.delete flush:true
                
            }
            println("existing list cleared");
        }
    }
}
