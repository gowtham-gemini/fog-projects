package com.assistanz.app

import com.assistanz.app.Message
import com.assistanz.app.MessageDetail
import com.assistanz.app.MailExchange
import grails.transaction.Transactional
import grails.converters.deep.JSON
import com.assistanz.app.AppConstant
import com.assistanz.app.Config
import com.assistanz.app.ConfigLoader
import com.assistanz.app.MailManager


@Transactional
class ListenerService {
    
    def mailService
    /* Consuming the Message By its Queue Name*/
    //static rabbitQueue = 'msgs'
    
    /* Consuming the Message By its Exchange Name*/
    static rabbitSubscribe = [ name: 'fogPanelMailExchge', routingKey: 'fogPanelMailExchge.#' ]
    
    void  handleMessage(String body) { 
        def obj = JSON.parse(body)
        def _from = '"Balaji" <balaji.assistanz@gmail.com>'
        //def _from = obj.jFrom
        
        def saveAllFlag = false
        
        // checking save config before saving
        Config configInstance = Config.findByName(AppConstant.SAVE_CONFIG)
        def props = ConfigLoader.getProperties();
        println("props all"+props)
        if(props?.get("SAVE_ALL_CONFIG_FLAG")?.equals("true")) {
            saveAllFlag = true
        }
        println("saveAllFlag"+saveAllFlag)
        
        try{
//            sendMail {
//                from _from
//                to obj.jTo
//                print(obj.jCc)
//                if(!(obj.jCc==""  || obj.jCc.equals("") || obj.jCc==null)){
//                    // Assigning the Cc value when there is some Cc Address Given in the UI
//                    cc obj.jCc
//                }
//                if(!(obj.jBcc==""  || obj.jBcc.equals("") || obj.jBcc==null)){
//                    // Assigning the Bcc value when there is some Cc Address Given in the UI
//                    bcc obj.jBcc
//                }
//                subject obj.jSubject
//                html obj.jMessageBody
//            }
            println("**********try block********")
            MailManager mailManager = MailManager.getInstance(); 
            mailManager.sendMail(obj.jTo, obj.jSubject, obj.jMessageBody)
            
            if(saveAllFlag) {
                savingLog(obj, _from, true, "")
            }
        }
        catch(Exception e){
            println("**********catch block********8")

            println("mail failed")
            savingLog(obj, _from, false, e.getMessage())

            println("Exception"+e.getMessage())
        }
    }
    def savingLog(def obj,def from, def status, def exceptionMsg) {
        def s = new Message 
        (subject: obj.jSubject, 
            body: obj.jMessageBody).addToMessageDetails(new MessageDetail(
                qFrom: from, 
                qTo: obj.jTo,
                date: new Date(),
                failureReason: exceptionMsg , 
                isDelivered:status)).save flush:true 
        println("status"+status)
            
    }
    
}
