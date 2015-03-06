package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.openstack.OpenStackServer;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.net.URL;
import java.util.List;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import com.assistanz.fogpanel.MonitoringDeviceJob;
import java.util.UUID;
import org.zenoss.client.modal.entities.device.Threshold;
import org.zenoss.client.modal.entities.device.Device;
import static org.zenoss.client.modal.entities.device.Device.Builder;
import org.zenoss.client.ZenossFacade;
import org.zenoss.client.common.RestServiceFactory;
import org.zenoss.client.common.RestServiceHandler;
import com.assistanz.cloud.config.ConfigLoader;
import org.zenoss.client.modal.entities.device.Dictionary;
import org.zenoss.client.api.DeviceService;
import org.zenoss.client.modal.entities.device.IPService;

@Transactional
class AlarmService {
    
    NotificationTopicService notificationTopicService;
    NotificationService notificationService;
    def springSecurityService;
    OpenStackAuthService openStackAuthService;
    
    
    def getZenossFacade() {

        ConfigLoader configLoader = ConfigLoader.getInstance();
  
        Properties props = configLoader.getProperties();                
        
        RestServiceHandler restServiceHandler = RestServiceFactory.getHttpClient(props.get(Config.ZENOSS_ENDPOINT_URL), props.get(Config.ZENOSS_USERNAME), props.get(Config.ZENOSS_PASSWORD));

        ZenossFacade zenossFacade = new ZenossFacade(restServiceHandler);    
        return zenossFacade;       

    } 
    
    def getList(instanceId, alarmId) {
       
        ArrayList<ArrayList<String>> alarmList = new ArrayList<ArrayList<String>>();
        def user = springSecurityService.currentUser; 
        
        def instance =  VirtualMachine.findByReferenceId(instanceId);
        def monitoringDevice = MonitoringDevice.findWhere(virtualMachine: instance, deleted: false);
        
        println("instance"+instance)
        def alarms = Alarm.withCriteria {
            eq('deleted', false)
            eq('virtualMachine', instance)
            eq('monitoringDevice', monitoringDevice)
            
            if(alarmId) {
                eq('refrenceId', alarmId)
            }
        }
        
        for(def alarm: alarms) {
            HashMap item = new HashMap();
            item.put("referenceId", alarm.refrenceId)
            item.put("name", alarm.name)
            item.put("topic", alarm.topic)
            item.put("virtualMachine", alarm.virtualMachine)
            item.put("type", alarm.type)
            item.put("subType", alarm.subType)
            item.put("minValue", alarm.minValue)
            item.put("maxValue", alarm.maxValue)
            item.put("threshold", alarm.minValue + " - " +alarm.maxValue)  
            item.put("portNumber", alarm.portNumber) 
            item.put("portEnabled", alarm.portEnabled) 
            item.put("partition", alarm.partition) 
            alarmList.add(item);
        }
        return alarmList;
    }
    
    def getFileSystemPartitionTemplates (osType, deviceId) {
        try {
               
            ArrayList<ArrayList<String>> partitionList = new ArrayList<ArrayList<String>>();
            HashMap item = new HashMap();
            item.put("uid", deviceId);
            if(osType == "LINUX") {
                item.put("meta_type", "FileSystem");
            } else if(osType == "WINDOWS") {
                item.put("meta_type", "HardDisk");
            } 
              
            partitionList = getZenossFacade().getPartitionTemplates(item);
            println("partitionList"+partitionList)
            return partitionList;
              
        } catch(Exception e) {
            throw e;
        }
    }
    
    def create(requestData) {
        try {
            
            def instance =  VirtualMachine.findByReferenceId(requestData.instanceId);
              
            //create topic and subscriber 
            def newTopic = topicAndSubscriberCreation(requestData);
              
            def subType;
            def thresholdAlarmType = requestData.type;
            if(requestData.type == "CPU_LINUX" && requestData.osType == "LINUX") {
                subType = requestData.subCpuType;
                thresholdAlarmType = subType;
            } else if(requestData.type == "disk") {
                subType = requestData.subDiskType;
                thresholdAlarmType = subType;
            } else if(requestData.type == "fileSystem") {
                println("file system----")
                subType = requestData.subFileType;
                println("subType"+subType)
                thresholdAlarmType = subType;
            }
              
            println("thresholdAlarmType :"+thresholdAlarmType)
            println("requestData.partitionId : "+requestData.partitionId)
              
            def thresholdType = "MinMaxThreshold"; 
            def monitoringDevice = MonitoringDevice.findWhere(virtualMachine: instance, deleted: false);
                        
            Device addedDevice = new Device.Builder(monitoringDevice.refrenceId).build();
            //            Device addedDevice = new Device.Builder("/zport/dmd/Devices/Server/Linux/devices/192.168.1.154").build();
            IPService iPServiceByPort;
            def alarm = new Alarm();
            alarm.type = requestData.type; 

            // only for IP service
            if(requestData.type == "ipService") {
                
                IPService iPService = new IPService.Builder("").port(requestData.port.toString()).build();
                
                def ipServiceAdded = zenossFacade.addIpService(addedDevice, iPService);
                
                println("ipServiceAdded"+ipServiceAdded)
                
                if(ipServiceAdded == true) {
                    
                    thresholdType = "port";
                    
                    addedDevice.setPort(requestData.port.toString());
                    iPServiceByPort = zenossFacade.getIpServiceByPort(addedDevice);
                    iPServiceByPort.setMonitor(Boolean.TRUE);
                    
                    def setMonitor = zenossFacade.setMonitoringFlag(iPServiceByPort);
                    
                    println("iPServiceByPort"+setMonitor)
                }
                
                // for db persist
                alarm.refrenceId = iPServiceByPort.getUid();
                alarm.name = "IPSERVICE" + "_" + iPServiceByPort.getUid();
                alarm.portNumber = requestData.port;
                alarm.minValue = new Long(0); 
                alarm.maxValue = new Long(0);
                alarm.portEnabled = true;
                
            } else {
               
                addedDevice.setOsType(monitoringDevice.osType)
                //            
                //generate random UUID 
                UUID refrenceId = UUID.randomUUID();
                String uid = String.valueOf(refrenceId);
                
                def thresholdId = requestData.type.toUpperCase();
                
//                //Append subType 
//                if(subType) {
//                    thresholdId += "_" + subType;
//                }
                
                thresholdId += "_" + uid;
                
                println("uuid :" +uid);

                def minValue = requestData.thresholdMinValue;
                def maxValue = requestData.thresholdMaxValue;
                def severity = "5";

                Threshold threshold = new Threshold.Builder(thresholdId, thresholdType)
                    .uid(thresholdId)
                    .maxval(maxValue.toString())
                    .minval(minValue.toString())
                    .serverity(5L)
                    .build();

                threshold.setAlarmType(thresholdAlarmType);

                println("threshold"+threshold)

                if(requestData.type == "fileSystem") {
                    threshold.setPartitionName(requestData.partitionId);
                    println("file system")
                }

                Dictionary dictionary = getZenossFacade().addAlarm(addedDevice, threshold);

                println("dictionary.getUid()::"+dictionary.getUid())
                
                // for db persist
                alarm.refrenceId = dictionary.getUid();
                alarm.name = thresholdId;
                alarm.minValue = new Long(requestData.thresholdMinValue); 
                alarm.maxValue = new Long(requestData.thresholdMaxValue);
                alarm.severity = severity;
                
            }
                         
            alarm.topic = newTopic; 
            alarm.virtualMachine = instance; 
            alarm.subType = subType;
                        
            if(requestData.type == "fileSystem") {
                
                alarm.partition = requestData.partitionId;
                alarm.mountPoint = requestData.mountPoint;
            }
   
            alarm.monitoringDevice = monitoringDevice;
            alarm.thresholdType = thresholdType;
            alarm.createdAt = new Date();
            alarm.save(flush: true);
            
            if (!alarm.save()) {
                alarm.errors.allErrors.each { println it }
            }
            
            // response results           
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            response.add(item)
            
            return response
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    def enable(requestData) {
        try {
 
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def alarm = Alarm.findByRefrenceId(requestData.alarmId);
            
            Device addedDevice = new Device.Builder(alarm.monitoringDevice.refrenceId).build();
            //            Device addedDevice = new Device.Builder("zport/dmd/Devices/Server/Linux/devices/192.168.1.154").build();
            addedDevice.setPort(alarm.portNumber.toString());
            
            IPService iPService = zenossFacade.getIpServiceByPort(addedDevice);
            iPService.setMonitor(Boolean.TRUE);
            
            if(iPService) {
                
                alarm.portEnabled = true;
                alarm.save(flush: true);
                
                item.put("result", GeneralConstants.RESULT_SUCCESS);
            } else {
                item.put("result", GeneralConstants.RESULT_FAILURE)
            }

            response.add(item)
            
            return response
        } catch(Exception e) {
            throw e;
        }
    }
    
    def disable(requestData) {
        try {
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            
            def alarm = Alarm.findByRefrenceId(requestData.alarmId);
            
            println("alarm.monitoringDevice.refrenceId: "+alarm.monitoringDevice.refrenceId)
            
            //            Device addedDevice = new Device.Builder(alarm.monitoringDevice.refrenceId).build();
            Device addedDevice = new Device.Builder("zport/dmd/Devices/Server/Linux/devices/192.168.1.154").build();
            println("addedDevice"+addedDevice)
            addedDevice.setPort(alarm.portNumber.toString());
            IPService iPService = zenossFacade.getIpServiceByPort(addedDevice);
            println("iPService"+iPService)
            iPService.setMonitor(Boolean.FALSE);
            
            if(iPService) {
                
                alarm.portEnabled = false;
                alarm.save(flush: true);
                
                item.put("result", GeneralConstants.RESULT_SUCCESS);
            } else {
                item.put("result", GeneralConstants.RESULT_FAILURE)
            }

            response.add(item)
            
            return response
        } catch(Exception e) {
            throw e;
        }
    }
    
    def topicAndSubscriberCreation(requestData) {
        try {
             
            def topic;
            def user = springSecurityService.currentUser;
            
            println("topic listname"+requestData.topicListName == "null" || requestData.topicListName == null  ? "true" : "false")
            println("topic listname"+requestData.name == "null" || requestData.name == null  ? "true" : "false")
            
            def subscribers = requestData.reciepient;
            
            println("requestData.name"+requestData.name)
            println("requestData.topicListName"+requestData.topicListName)
            
            //Get old topic            
            if(requestData.name == "null" || requestData.name == null) {
                topic =  NotificationTopic.findById(requestData.topicListName);
                println("topic list"+topic)
            }
            
            //Create new topic
            if(requestData.topicListName == "null" || requestData.topicListName == null ) {
                topic = notificationTopicService.createTopic(requestData.name, user.account)
                println("topic"+topic)
                
                for (String subscriber: subscribers.split(",")) {
                    System.out.println(subscriber.trim());
                    def email = subscriber.trim();
                    notificationTopicService.createSubscriber(email, topic);
                }
            }
            
            
            
            return topic;
        } catch(Exception e) {
            throw e;
        }
    }
    
    def update(requestData) {
        try {

            def instance =  VirtualMachine.findByReferenceId(requestData.instanceId);
            
            //create topic and subscriber 
            def updatedTopic = topicAndSubscriberCreation(requestData);
            
            def monitoringDevice = MonitoringDevice.findWhere(virtualMachine: instance, deleted: false);
                        
            Device addedDevice = new Device.Builder(monitoringDevice.refrenceId).build();
            addedDevice.setOsType(monitoringDevice.osType)

            def thresholdType = "MinMaxThreshold";

            def minValue = requestData.thresholdMinValue;
            def maxValue = requestData.thresholdMaxValue;
            def severity = "5";
            
            println("minValue"+minValue)
            println("maxValue"+maxValue)
            
            def alarm = Alarm.findByRefrenceId(requestData.alarmId);
            
            Threshold threshold = new Threshold.Builder(alarm.name, thresholdType)
            .uid(alarm.name)
            .maxval(maxValue.toString())
            .minval(minValue.toString())
            .serverity(5L)
            .build();
                
            def thresholdAlarmType;
            
            println("alarm.type"+alarm.type)
            
            if(alarm.type == "MEMORY_LINUX" || alarm.type == "MEMORY_WINDOWS") {
                thresholdAlarmType = alarm.type;
            } else if(alarm.type == "CPU_WINDOWS" || alarm.type == "CPU_LINUX_USER" || alarm.type == "CPU_LINUX_SYSTEM") {
                thresholdAlarmType = alarm.type;
            } else if(alarm.type == "CPU_LINUX" || alarm.type == "disk") {
                thresholdAlarmType = alarm.subType;
            } 
                                
            threshold.setAlarmType(thresholdAlarmType);
            
            Dictionary dictionary = getZenossFacade().addAlarm(addedDevice, threshold);

            alarm.type = thresholdAlarmType; 
            alarm.refrenceId = dictionary.getUid(); 
            alarm.topic = updatedTopic; 
            alarm.virtualMachine = instance; 
            if(requestData.thresholdMinValue != null && requestData.thresholdMaxValue != null) {
                
                alarm.minValue = new Long(minValue); 
                alarm.maxValue = new Long(maxValue);
            }
   
            alarm.severity = severity;
            alarm.monitoringDevice = monitoringDevice;
            alarm.thresholdType = thresholdType;
            alarm.updatedAt = new Date();
            alarm.save(flush: true);
            
            if (!alarm.save()) {
                alarm.errors.allErrors.each { println it }
            }
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            response.add(item)
            
            return response
            
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    def delete(requestData) {
        try {
            
            ArrayList<ArrayList<String>> deleteResponse = new ArrayList<ArrayList<String>>(); 
            
            Dictionary dictionary = new Dictionary.Builder(requestData.thresholdId, requestData.thresholdId).build();
            
            String response = getZenossFacade().deleteAlarm(dictionary);
            
            println("alarm delete response: "+response)
            
            if(response == "success") {
                println("alarm delete inside: ") 
                def alarm = Alarm.findByRefrenceId(requestData.thresholdId);
                alarm.deleted = true;
                alarm.deletedAt =  new Date();
                alarm.save(flush: true);
                
                       
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                deleteResponse.add(item)

               
            } 
            
            return deleteResponse
            
        } catch(Exception e) {
            throw e;
        }
    }
    
}
