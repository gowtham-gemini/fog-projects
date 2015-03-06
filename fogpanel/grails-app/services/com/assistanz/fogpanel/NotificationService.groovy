package com.assistanz.fogpanel

import com.assistanz.fogpanel.MailManager
import grails.converters.deep.JSON
import java.text.SimpleDateFormat
import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.events.CSEventService
import javax.xml.bind.DatatypeConverter;
import com.assistanz.cloud.cloudstack.events.EventsResponse
import java.text.DateFormat
import java.util.Date;
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.cloud.cloudstack.alerts.CSAlertService
import com.assistanz.cloud.cloudstack.alerts.AlertResponse
import grails.transaction.Transactional
import org.apache.commons.lang.StringEscapeUtils;

@Transactional
class NotificationService {
    
    def springSecurityService;
    def mailService 
    def grailsResourceLocator
    ConfigService configService;
   def eventList() {
        def dateFormatValue = configService.getDateFormat(); 
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        DateFormat formater2 = new SimpleDateFormat("yyyy-MM-dd H:mm:ss.SS");
        def user = springSecurityService.currentUser
                                                    
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        CSEventService csEventService = new CSEventService(server);
         
        
          
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Calendar startDate = Calendar.getInstance(); 
        startDate.add(Calendar.DATE, -1);
        Calendar toDate = Calendar.getInstance();
                
        def response;
        def role = springSecurityService.getPrincipal().getAuthorities()    
        if(role.iterator().next() == "ROLE_ADMIN") {
            HashMap<String,String> optional = new HashMap<String,String>();  
            optional.put("listall", "true");
            optional.put("startdate", sdf.format(startDate.getTime()).toString()); 
            optional.put("enddate", sdf.format(toDate.getTime()).toString()); 
            response = csEventService.listEvents(optional)
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            HashMap<String,String> optional = new HashMap<String,String>();  
            optional.put("account", new String(user.account.accountIdentifier));
            optional.put("domainid", new String(user.account.domain.referenceId));
            optional.put("startdate", sdf.format(startDate.getTime()).toString()); 
            optional.put("enddate", sdf.format(toDate.getTime()).toString()); 
            response = csEventService.listEvents(optional)
        } 
        
        ArrayList<ArrayList<String>> eventList = new ArrayList<ArrayList<String>>();        
        for(Iterator<EventsResponse> iter = response.events.iterator(); iter.hasNext();) {
            def data = iter.next();

            def account = Account.findByAccountIdentifier(data.accountName)

            if(account) {
                Date date = sdf.parse(data.created);
                def cal = Calendar.getInstance()
                cal.setTime(date);
                Date eventDate = cal.getTime();   
                HashMap<String,String> eventItem = new HashMap<String,String>();  
                eventItem.put("id", data.id);
                eventItem.put("description", data.description);
                eventItem.put("level", data.level);
                eventItem.put("type", data.type);
                eventItem.put("state", data.state);
                eventItem.put("date", dateFormat.format(eventDate.getTime()).toString());
                eventItem.put("account", account.accountIdentifier);
                eventList.add(eventItem)
            }
                
        }
        return eventList;
        
   } 
    
    
    
   def csAlertsList() {
       
            
            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
        
            CloudStackServer server =
                     new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

            CSAlertService csAlertService = new CSAlertService(server);
            
            def dateFormatValue = configService.getDateFormat(); 
            SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
            
            HashMap<String,String> optional = new HashMap<String,String>(); 
            def response = csAlertService.listAlerts();            
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ArrayList<ArrayList<String>> alertList = new ArrayList<ArrayList<String>>();     
            for(Iterator<AlertResponse> iter = response.alerts.iterator(); iter.hasNext();) {
                def data = iter.next();

                Date date = sdf.parse(data.sent);
                def cal = Calendar.getInstance()
                cal.setTime(date);
                Date eventDate = cal.getTime();  
                
                HashMap<String,String> eventItem = new HashMap<String,String>();  
                eventItem.put("id", data.id);
                eventItem.put("description", data.description);
                eventItem.put("date", dateFormat.format(eventDate.getTime()).toString());
                alertList.add(eventItem)

            }
            
            return alertList;
   }
   
    def cloudUsageAlerts() {
        def alerts;
        def dateFormatValue = configService.getDateFormat(); 
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        def role = springSecurityService.getPrincipal().getAuthorities()   
        if(role.iterator().next() == "ROLE_ADMIN") {
            
            def logCriteria = Log.createCriteria()
            alerts = logCriteria.list { 
                eq("type", LogType.values()[4])
                order("date", "desc")
            }
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
           return "Authendication Failed";
        } 
        ArrayList<ArrayList<String>> usageAlertList = new ArrayList<ArrayList<String>>();
        
        for(def alert: alerts) {
            HashMap<String,String> event = new HashMap<String,String>();  
            event.put("id", alert.id);
            event.put("description", alert.description);
            event.put("viewed", alert.viewed);
            event.put("date", dateFormat.format(alert.date).toString()); 
            event.put("icon", "icon-warning-sign");
            event.put("link", "#/admin/activity/cloudUsage");
            usageAlertList.add(event)
        }
        
        return usageAlertList;
    }
    
    def healthByZone(zoneReferenceId) {
        def dateFormatValue = configService.getDateFormat();
        SimpleDateFormat calendarDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        ArrayList<ArrayList<String>> cloudMaintenanceList = new ArrayList<ArrayList<String>>();    
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        if(zoneReferenceId != null || zoneReferenceId != "null") {
            def zoneList = Zone.findByReferenceZoneId(zoneReferenceId);
            for(Iterator k = zoneList.cloudMaintenances.iterator();k.hasNext();) {  
                def item = k.next();  
                if(item.deleted == false) {
                    HashMap<String,String> notification = new HashMap<String,String>();
                    notification.put("id", item.id);
                    notification.put("description", item.description);
                    notification.put("date", dateFormat.format(item.date).toString());    
                    notification.put("calendarDate", calendarDateFormat.format(item.date).toString());
                    cloudMaintenanceList.add(notification);
                }
            }
        }
        return cloudMaintenanceList;
    }
    
    def billingAlerts() {                
        def dateFormatValue = configService.getDateFormat(); 
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatValue);
        SimpleDateFormat calendarDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()    
        def alerts 
        def nonViewedAlerts 
        
        if(role.iterator().next() == "ROLE_ADMIN") {
            alerts = Log.findAll();
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
            alerts = Log.findAllWhere(user: user, viewed: true);
            nonViewedAlerts = Log.findAllWhere(user: user, viewed: false); 
        } 
        
        ArrayList alertList = new ArrayList(); 
        ArrayList viewedAlertList = new ArrayList(); 
        ArrayList nonViewedAlertList = new ArrayList(); 
        ArrayList notificationList = new ArrayList(); 
        
        for(def item: alerts) { 
                
            HashMap<String,String> event = new HashMap<String,String>();                
            event.put("id", item.id);
            event.put("description", item.description);
            event.put("viewed", item.viewed);
            event.put("date", dateFormat.format(item.date).toString());  
            event.put("icon", "icon-warning-sign");
            event.put("link", "#/user/home/billingAlert");            
            viewedAlertList.add(event);
        }
        for(def item: nonViewedAlerts) { 
                
            HashMap<String,String> event = new HashMap<String,String>();                
            event.put("id", item.id);
            event.put("description", item.description);
            event.put("viewed", item.viewed);
            event.put("date", dateFormat.format(item.date).toString()); 
            event.put("icon", "icon-warning-sign");
            event.put("link", "#/user/home/billingAlert");
            nonViewedAlertList.add(event);
        }
        
        HashMap alertType = new HashMap();                
        alertType.put("viewed", viewedAlertList);
        alertType.put("nonViewed", nonViewedAlertList);
        if(nonViewedAlerts == "null" || nonViewedAlerts == null) {
            alertType.put("count", 0);
        } else {
            alertType.put("count", nonViewedAlerts.size());
        }
//        def notificationQuery = CloudMaintenance.createCriteria()
       
        
         def query = "from CloudMaintenance as b where b.deleted=false order by b.date desc";

         def cloudMaintenance = CloudMaintenance.findAll(query, [max: 100])

        
        for(def item: cloudMaintenance) { 
                
            HashMap<String,String> notification = new HashMap<String,String>();                
            notification.put("id", item.id);
            notification.put("description", StringEscapeUtils.escapeHtml(item.description));
            notification.put("date", dateFormat.format(item.date).toString()); 
            notification.put("calendarDate", calendarDateFormat.format(item.date).toString());
            notification.put("icon", "icon-warning-sign");
            notification.put("link", "#/user/home/notification");
            notificationList.add(notification);
        }
        
        if(cloudMaintenance == "null" || cloudMaintenance == null) {
            alertType.put("notificationCount", 0);
        } else {
            alertType.put("notificationCount", cloudMaintenance.size());
        }
        alertType.put("notification", notificationList);
        alertList.add(alertType);
        return alertList; 
        
    }
    
    def viewBillingNotification(id) {
        def alert = Log.get(id)
        alert.viewed = true;
        alert.save(flush:true)
        return ["OK"]
    }
    
    def viewCloudUsageNotification(id) {
        def cloudUsage = Log.get(id)
        cloudUsage.viewed = true;
        cloudUsage.save(flush:true)
        return ["OK"]
    }
    
    def list() {
         
        def result;
        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()        
        
        if(role.iterator().next() == "ROLE_ADMIN") {
            result = EventLogIpAddress.findAll();
        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
           result = EventLogIpAddress.findAllWhere(user: user); 
        }               
        ArrayList<ArrayList<String>> eventList = new ArrayList<ArrayList<String>>();            
        for(int i=0; i < result.size(); i++) { 
            def item = result[i]; 
            HashMap<String,String> event = new HashMap<String,String>();                
//                event.put("eventType", item.eventType);
                event.put("ipAddress", item.ipAddress);
                event.put("date", item.lockTime);
                event.put("username", item.user.username);
                event.put("failureCount", item.overAllFailureCount);
                event.put("currentFailureCount", item.failureCount);
                event.put("successCount", item.successCount);
                event.put("overAllCount", item.overAllCount);
                event.put("isLocked", item.isLocked);
                eventList.add(event);
        }
        return eventList; 
    }
    
    def getDefaultMailTemplateMap() {
        
        def user = springSecurityService.currentUser
        
        Map root = new HashMap();
        root.put("applicationUrl", ApplicationHolder.getApplication().config.cloudstack.applicationUrl);
        root.put("user", user);
        root.put("organizationName", Config.findByName(Config.ORGANISATION_NAME).value);
        root.put("organizationEmail", Config.findByName(Config.ORGANISATION_EMAIL).value);
        root.put("organizationAddress", Config.findByName(Config.ORGANISATION_ADDRESS).value);
        root.put("organizationExtensionAddress", Config.findByName(Config.ORGANISATION_ADDRESS_EXTENSION).value);
        root.put("organizationCity", Config.findByName(Config.ORGANISATION_ADDRESS_CITY).value);
        root.put("organizationCountry", Config.findByName(Config.ORGANISATION_ADDRESS_COUNTRY).value);
        root.put("organizationState", Config.findByName(Config.ORGANISATION_ADDRESS_STATE).value);        
        root.put("organizationPhone1", Config.findByName(Config.ORGANISATION_BILLING_PHONE_NUMBER1).value);
        root.put("signature", Config.findByName(Config.ORGANISATION_BILLING_SINGNATURE).value);
        def logoConfig = Config.findByName(Config.ORGANISATION_BILLING_LOGO).value
        def logoUrl =  "" 
        if(logoConfig == "") {                                  
            logoUrl =  ApplicationHolder.getApplication().config.cloudstack.applicationUrl.toString() + "/images/fog_logo.png"
        } else {
            logoUrl =  logoConfig.value;
        }        
        root.put("organizationLogoUrl", logoUrl.toString());                        
        return root
        
    }
            
    def send(String toMail, String mailSubject, Map root, String templateName) {
        try {    
                        
            MailTemplateManager mailTemplateManager = new MailTemplateManager()
            
            def user = springSecurityService.currentUser
            
            def defaultLanguage = Config.findByName(Config.DEFAULT_LANGUAGE)
            def useExternalTemplate = ApplicationHolder.getApplication().config.mailTemplate.useExternalTemplate
            def mailTemplateURL =  ""
            def language = defaultLanguage.value 
            if(useExternalTemplate == true || useExternalTemplate == "true") {
                useExternalTemplate = true;
                mailTemplateURL = ApplicationHolder.getApplication().config.mailTemplate.externalTemplateURL.toString() +"/"+ defaultLanguage.value 
            }                       
            if(ApplicationHolder.getApplication().config.mailTemplate.useUserlanguage == true && user) {
                language = user.account.preferredLanguage
                if(useExternalTemplate == true || useExternalTemplate == "true") {
                    mailTemplateURL = ApplicationHolder.getApplication().config.mailTemplate.externalTemplateURL.toString() +"/"+ user.account.preferredLanguage
                } else {
                    mailTemplateURL =  "" 
                }
            }   
            def templateString = mailTemplateManager.getTemplate(root, mailTemplateURL, templateName, language, useExternalTemplate)
            def templateSubject = mailTemplateManager.getTemplate(root, mailTemplateURL+"/subject", mailSubject, language, useExternalTemplate)
            
            def senderName = ApplicationHolder.getApplication().config.fogPanelMail.senderName.toString();   
            def fromAddress = ApplicationHolder.getApplication().config.grails.mail.username.toString();                
            def fromName = senderName +"<"+fromAddress+">"
            mailService.sendMail {                    
                to toMail
                from fromName
                subject templateSubject
                html templateString
            }
            return ["OK"]   
                   
        } catch (Exception ex) {
           Console.print(ex);
        }    
    }
    
    
    
    def addEmail(String requestBody) {
        try {
            def user = springSecurityService.currentUser 
            
            def requestData = JSON.parse(requestBody)
            
            NotificationEmail notificationEmail = new NotificationEmail()
            notificationEmail.user = user
            notificationEmail.email = requestData.email
            notificationEmail.save(flush: true)            
            if (notificationEmail.hasErrors()) {
                throw new ValidationException(notificationEmail.errors.allErrors);
            }
            return ["OK"]
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }    
    
    def listEmail() {
        try {
            def user = springSecurityService.currentUser 
            def result = NotificationEmail.findAllWhere(user: user); 
            ArrayList<ArrayList<String>> notificationEmailList = new ArrayList<ArrayList<String>>();            
            for(int i=0; i < result.size(); i++) { 
                def item = result[i]; 
                HashMap<String,String> notificationEmail = new HashMap<String,String>();                
                    notificationEmail.put("email", item.email);
                    notificationEmailList.add(notificationEmail);
            }
            return notificationEmailList; 
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }

    def deleteEmail(String requestBody) {
        try {
            def user = springSecurityService.currentUser 
            def requestData = JSON.parse(requestBody)
            def email = NotificationEmail.findWhere(user: user, email:requestData.email); 
            email.delete(flush: true)
            return ["OK"] 
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    
    
}
