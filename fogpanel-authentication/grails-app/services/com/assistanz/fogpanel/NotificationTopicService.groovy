package com.assistanz.fogpanel

import grails.transaction.Transactional
import java.security.MessageDigest
import java.sql.Timestamp
import java.text.SimpleDateFormat
import org.springframework.context.MessageSource

@Transactional
class NotificationTopicService {

    def springSecurityService;
    ConfigService configService;
    MessageSource messageSource;
        
    def list() {
    
        def user = springSecurityService.currentUser;  
        def dateFormatValue = configService.getDateFormat();    
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        
        def topicList = NotificationTopic.findAllWhere(account:user.account, deleted:false)
        ArrayList<ArrayList<String>> topics = new ArrayList<ArrayList<String>>();
           
        for (def topic : topicList) {

            HashMap item = new HashMap();
            item.put("name", topic.name);
            item.put("id", topic.id);
            item.put("status", topic.status);
            item.put("date", dateFormat.format(topic.createdAt).toString());
            
            ArrayList<ArrayList<String>> subscribers = new ArrayList<ArrayList<String>>();
            
            def subscribersList = TopicSubscriber.findAllWhere(topic:topic, deleted:false)
                        
            for (def subscriber : subscribersList) {
                HashMap subscriberItem = new HashMap();
                subscriberItem.put("name", subscriber.email);
                subscriberItem.put("id", subscriber.id);
                subscriberItem.put("token", subscriber.token);
                subscriberItem.put("subscribedDate", dateFormat.format(subscriber.subscribedDate).toString());
                subscribers.add(subscriberItem)
            }
            item.put("subscribers", subscribers)
            topics.add(item)             
        }

        return topics
    }
    
    
    def getNotificationTopic(id) {
        
        def topic = NotificationTopic.get(id);
        ArrayList<ArrayList<String>> topics = new ArrayList<ArrayList<String>>();
        def dateFormatValue = configService.getDateFormat();    
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);  
        
        HashMap item = new HashMap();
        item.put("name", topic.name);
        item.put("id", topic.id);
        item.put("status", topic.status);
        item.put("date", dateFormat.format(topic.createdAt).toString());

        ArrayList<ArrayList<String>> subscribers = new ArrayList<ArrayList<String>>();

        def subscribersList = TopicSubscriber.findAllWhere(topic:topic, deleted:false)

        for (def subscriber : subscribersList) {
            HashMap subscriberItem = new HashMap();
            subscriberItem.put("name", subscriber.email);
            subscriberItem.put("id", subscriber.id);
            subscriberItem.put("token", subscriber.token);
            subscriberItem.put("subscribedDate", dateFormat.format(subscriber.subscribedDate).toString());
            subscribers.add(subscriberItem)
        }
        item.put("subscribers", subscribers)
        topics.add(item)             

        return topics
    }
    
    def editTopic(requestData) {
        
        try {  
            def user = springSecurityService.currentUser; 
            def topic = NotificationTopic.get(Integer.parseInt(requestData.id))
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();      
            
            def oldTopics = NotificationTopic.withCriteria {
                eq("deleted", false)
                ne("id", topic.id) 
                eq("account", user.account)
                eq("name",requestData.name)
            }
           
            if(!oldTopics) {
                topic?.name = requestData.name
                topic?.save(flush: true)
            } else {
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", "EXISTS");               
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                item.put("message", messageSource.getMessage('user.notificationTopicName.alreadyExists', null, new Locale(defaultLanguage.value)));
                response.add(item)                 
                return response;
            }                        
           
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            response.add(item)
            
            return response;
            
        } catch(Exception ex) {
            throw ex;
        }
        
    }
    
    
    def addTopic(requestData) {
               
        def user = springSecurityService.currentUser;  
        try {                        
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();          
            def topic = NotificationTopic.findWhere(name:requestData.name, account:user.account, deleted:false)
            if(!topic) {
                 createTopic(requestData.name, user.account)
            } else {
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", "EXISTS");               
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                item.put("message", messageSource.getMessage('user.notificationTopicName.alreadyExists', null, new Locale(defaultLanguage.value)));
                response.add(item)                 
                return response;
            }
            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            response.add(item)            
            
            return response;
            
        } catch(Exception ex) {
            throw ex;
        }
    }
    
    def addSubscriber(requestData) {
        
        try {
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();          
            def subscriber = TopicSubscriber.findWhere(email:requestData.email, topic:NotificationTopic.get(Integer.parseInt(requestData.topicId)), deleted:false)
            if(!subscriber) {
                 createSubscriber(requestData.email,  NotificationTopic.get(Integer.parseInt(requestData.topicId)))
            } else {
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", "EXISTS");
                Config defaultLanguage =  Config.findByName(Config.DEFAULT_LANGUAGE);
                item.put("message", messageSource.getMessage('user.notificationTopic.subscriberAlreadyExists', null, new Locale(defaultLanguage.value)));
                response.add(item) 
                
                return response;
            }
            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            response.add(item)
            
            return response;
            
        } catch(Exception ex) {
            throw ex;
        }        
    }
    
    def unsubscribe(token) {
        
        try {
            def subscriber = TopicSubscriber.findByToken(token)
            if(subscriber) {
                subscriber.delete(flush: true)
//                subscriber.unsubscribedDate = new Date()
//                subscriber.save(flush: true)
                return "SUCCESS"
            } else {
               return "NO_SUBSCRIBER"
            }                        
        } catch(Exception ex) {
            throw ex;
        }
        
    }
    
    def removeSubscriber(id) {
        try {
            def subscriber = TopicSubscriber.get(Integer.parseInt(id))
            if(subscriber) {
                subscriber.delete(flush: true)
//                subscriber.unsubscribedDate = new Date()
//                subscriber.save(flush: true)
                return "SUCCESS"
            } else {
               return "NO_SUBSCRIBER"
            }                        
        } catch(Exception ex) {
            throw ex;
        }
    }
    
    def removeTopic(id) {
                
        try {
            def topic = NotificationTopic.get(Integer.parseInt(id))
            def alarms = Alarm.findAllWhere(topic:topic, deleted: false)
            if(topic) {
                if(alarms) {
                    return "ALARM_EXISTS"
                }
                topic.deleted = true
                topic.deletedAt = new Date()
                topic.save(flush: true)
                return "SUCCESS"
            } else {
               return "NO_TOPIC"
            }                        
        } catch(Exception ex) {
            throw ex;
        }
        
    }
    
        
    def createSubscriber(email, topic) {
        
        Date date = new Date();
        def subscriber = new TopicSubscriber()
        subscriber.topic = topic
        subscriber.email = email
        subscriber.subscribedDate = date

        def time = new Timestamp(date.getTime())
        def token = email + date.toString()
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(token.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        subscriber.token = hashtext;
        subscriber.save(flush: true)
        
        if (!subscriber.save()) {
            subscriber.errors.allErrors.each { println it }
        }
        
        return subscriber
    }
    
    def createTopic(name, account) {
        
        def topic = new NotificationTopic()
        topic.account = account
//            topic.status = "ACTIVE"
        topic.name = name
        topic.createdAt = new Date()
        topic.save(flush: true)
        
        return topic
        
    }
    
}
