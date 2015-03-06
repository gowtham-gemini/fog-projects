package com.assistanz.fogpanel

import java.text.SimpleDateFormat
import java.text.DateFormat
import org.apache.commons.logging.LogFactory;
import grails.transaction.Transactional
import org.codehaus.groovy.grails.commons.ApplicationHolder
@Transactional
class CloudMaintenanceService {
    NotificationService notificationService
    ConfigService configService;
    private static final log = LogFactory.getLog(this)
    def springSecurityService;
    
    def add(cloudMaintenanceData) {
        try {
            def user = springSecurityService.currentUser
            
            DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");  
            Date date = formater.parse(cloudMaintenanceData.date);
 
            def cloudMaintenance = new CloudMaintenance();
            cloudMaintenance.date = date
            cloudMaintenance.description = cloudMaintenanceData.description  
            cloudMaintenance.mailSend = false            
            cloudMaintenance.deleted = false
            
            String[] zones;
            zones = cloudMaintenanceData.zones.split(",");
            for(def j=0; j < zones.length; j++) {
                def zone = Zone.get(Integer.parseInt(zones[j]))
                cloudMaintenance.addToZones(zone);  
            }
            cloudMaintenance.save(flush: true);
            if (cloudMaintenance.hasErrors()) {
                throw new ValidationException(cloudMaintenance.errors.allErrors);
            }
            ArrayList<ArrayList<String>> cloudMaintenanceResultList = new ArrayList<ArrayList<String>>();       
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("result",  GeneralConstants.RESULT_SUCCESS);
            cloudMaintenanceResultList.add(item);   
            log.info("Cloud maintence added Id: ${cloudMaintenance.id} by user ${user.id}")
             
            return cloudMaintenanceResultList;
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def sendCloudMaintainMail() {
        try {   
            def account =  Account.findAll();
            def cloudMaintainance = CloudMaintenance.findAllWhere(mailSend: false, deleted: false);           
            if(cloudMaintainance) {
                for(int count = 0; count < cloudMaintainance.size(); count++ ) {
                    def currentShedule = cloudMaintainance[count]; 
                    for(int i=0; i < account.size(); i++) { 
                        def accountItem = account[i];                         
                        def vm = VirtualMachine.findAllWhere(owner: accountItem)                        
                        if(vm) { 
                            def currentUser = User.findByAccount(accountItem);
                            Map tempalteMap = notificationService.getDefaultMailTemplateMap()
                            tempalteMap.put("maintainDate", currentShedule.date.toString())
                            tempalteMap.put("description", currentShedule.description)    
                            tempalteMap.put("user", currentUser)                                       
                            notificationService.send(accountItem.email.toString(), "scheduleMaintaince.subject.ftl", tempalteMap, "scheduleMaintaince.ftl")                                 

                        }                                    
                    }
                    currentShedule.mailSend = true;
                    currentShedule.save(flush: true)
                }
            }                                    
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def list() {
        return CloudMaintenance.findAll()
    }
    
    
    def validateDate(cloudMaintenanceData) {
                
        def cloudMaintenanceCriteria = CloudMaintenance.createCriteria()
        def dateFormatValue = configService.getDateFormat();  
        DateFormat formater = new SimpleDateFormat(dateFormatValue); 
        Date date = formater.parse(cloudMaintenanceData.date);
        
    }
    
    def update(cloudMaintenanceData) {
        try {
            def user = springSecurityService.currentUser
            
            def newCloudMaintenance = CloudMaintenance.get(cloudMaintenanceData.id)
            newCloudMaintenance.description = cloudMaintenanceData.description;
            newCloudMaintenance.save(flush: true)
            log.info("Cloud maintence updated for Id: ${newCloudMaintenance.id} by user ${user.id}")
            if (newCloudMaintenance.hasErrors()) {
                throw new ValidationException(newCloudMaintenance.errors.allErrors);
            }  
            ["OK"]
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    def delete(cloudMaintenanceData) {
        try {   
            
            def user = springSecurityService.currentUser
            
            def newCloudMaintenance = CloudMaintenance.get(cloudMaintenanceData.itemId)
            newCloudMaintenance.deleted = true
            newCloudMaintenance.save(flush: true)
            log.info("Cloud maintence deleted Id: ${newCloudMaintenance.id} by user ${user.id}")
            if (newCloudMaintenance.hasErrors()) {
                throw new ValidationException(newCloudMaintenance.errors.allErrors);
            }  
            ["OK"]
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
}
