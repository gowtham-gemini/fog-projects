package com.assistanz.fogpanel

import grails.transaction.Transactional
import grails.converters.deep.JSON

@Transactional
class ZenossMailQueueService {
    
    def notificationService;
    static rabbitQueue = [queues: 'zenoss-mail-queue']

    void handleMessage(msg) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
        // Do something with the message headers
        //        println "Received message with content type ${msg.contentType};${msg.encoding}"
        //            println "-- MESSAGE BEGINS --"
        //            println msg
        //            println "-- MESSAGE ENDS --"

        //        def msgData = JSON.parse(msg)   
        try {
        
            Console.print("==========================Message Body=======================================")
            Console.print("msg" + msg)
            Console.print("=========================End Message Log========================================")
            def msgData = JSON.parse(msg)              
            Console.print("==========================msgData=======================================")
            Console.print(msgData)
            Console.print("=========================End msgData========================================")
        
            def alarmId;
            Console.print("==========================Event Key / Alarm Name =======================================")
            Console.print("msgData" + msgData.'eventKey'.split("\\|"))
            Console.print("msgData" + msgData.'eventKey'.split("\\|").size())
            Console.print("=========================End Alarm Log ========================================")
            if(msgData.'eventKey'.split("\\|").size() >= 2) {
                alarmId = msgData.'eventKey'.split("\\|")[1]
            } else {
                alarmId = ""
            }
            Console.print("alarmId" + alarmId) 
            def  alarm = Alarm.findWhere(name:alarmId, deleted:false)
            Console.print("alarm" + alarm)
            if(alarm) {
                def topic = alarm.topic
                def subscribers = TopicSubscriber.findAllWhere(topic:topic)
                Console.print("subscribers" + subscribers)
                for(def subscriber: subscribers) {
                    Console.print("subscriber.email" + subscriber.email)
                    Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                    tempalteMap.put("subscriberEmail" , subscriber.email)                                   
                    tempalteMap.put("device" , msgData.'device')                                   
                    tempalteMap.put("ipAddress" , msgData.'ipAddress')                                   
                    tempalteMap.put("alarmMessage" , msgData.'message' ? msgData.'message' : "" ) 
                    tempalteMap.put("time" , msgData.'time' ? msgData.'time' : "" ) 
                    
                    notificationService.send(subscriber.email.toString(), "monitoring.subject.ftl", tempalteMap, "monitoring.ftl") 
                }
              
            }
            Console.print("=========================End OF Message Process ========================================")
        } catch (Exception ex){
            Console.print("ex" + ex)
        }
    }
}
