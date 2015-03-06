package com.assistanz.fogpanel

import grails.transaction.Transactional

import org.zenoss.client.ZenossFacade;
import org.zenoss.client.common.RestServiceFactory;
import org.zenoss.client.common.RestServiceHandler;
import org.zenoss.client.modal.entities.device.Device;
import static org.zenoss.client.modal.entities.device.Device.Builder;
import org.zenoss.client.modal.entities.device.GraphDefType;
import org.zenoss.client.ZenosServicesRegistry;
import org.zenoss.client.api.DeviceService;
import org.zenoss.client.modal.entities.device.DeviceResponse;
import com.assistanz.cloud.config.ConfigLoader;
import org.springframework.context.MessageSource
import com.assistanz.fogpanel.EnableDeviceJob

@Transactional
class MonitoringService {

    def springSecurityService;
    MessageSource messageSource;
    AsyncJobService asyncJobService
    def getZenossFacade() {
        
        ConfigLoader configLoader = ConfigLoader.getInstance();
  
        Properties configProps = configLoader.getProperties();                
        //instantiate static ZenossFacade
        def zenossUrl = configProps.get(Config.ZENOSS_ENDPOINT_URL);
        def zenossUsername = configProps.get(Config.ZENOSS_USERNAME);
        def zenossPassword = configProps.get(Config.ZENOSS_PASSWORD);
        if( zenossUrl != null &&  !zenossUrl.isEmpty() 
            && zenossUsername != null &&  !zenossUsername.isEmpty()
            && zenossPassword != null &&  !zenossPassword.isEmpty()
        ) {
            ZenossClient.getZenossFacadeInstance(zenossUrl, zenossUsername, zenossPassword);
            return ZenossClient.zenossFacade;
        }
    } 
    
    def getIPServiceList(deviceId) {   
        ArrayList ipArrayList = new ArrayList(); 
        Device  device = new Device.Builder(deviceId).build(); 
        def ipResultList =  getZenossFacade().getPortList(device);
         
        for(def ipService : ipResultList) {   
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("name", ipService.getName());  
            item.put("protocol", ipService.getProtocol()); 
            item.put("ipAddresses", ipService.getIpAddresses()); 
            item.put("serviceClassUid", ipService.getServiceClassUid()); 
            item.put("status", ipService.getStatus()); 
            item.put("uid", ipService.getUid()); 
            item.put("usesMonitorAttribute", ipService.getUsesMonitorAttribute()); 
            item.put("uuid", ipService.getUuid());                         
            item.put("description", ipService.getDescription());                         
            item.put("monitor", ipService.getMonitor());                   
            item.put("monitored", ipService.getMonitored());                         
            item.put("port", ipService.getPort());                         
            item.put("severity", ipService.getSeverity());                         
            
            
            ipArrayList.add(item)
        }        
        return ipArrayList;
    }
    def getGraph(deviceId, graphType, size, dRange) {                     
        def url = ""
        ArrayList<ArrayList<String>> graphList = new ArrayList<ArrayList<String>>(); 
        def defType = GraphDefType.MEMORY_UTILIZATION;
        if(graphType == "memory") {
            defType = GraphDefType.MEMORY_UTILIZATION
            url = deviceId
            
        } else if(graphType == "cpu") {
            defType = GraphDefType.CPU_UTILIZATION
            url = deviceId
            
        } else if(graphType == "io") {
            defType = GraphDefType.IO
            url = deviceId
            
        } else if(graphType == "availablity") {
            defType = GraphDefType.SYSTEM_AVAILABLITY
            url = deviceId
            
        } else if(graphType == "throughput") {
            defType = GraphDefType.THROUGHPUT
            url = deviceId + GeneralConstants.GRAPH_OS_INTERFACE_ETH0;
        }        
        Device  device = new Device.Builder(url).build(); 
        def graphRes =  getZenossFacade().getGraph(device, defType , new Long(dRange), new Long(size));
        HashMap<String,String> item = new HashMap<String,String>(); 
        item.put("id", url);
        item.put("imageURL", graphRes);
        graphList.add(item);         
        return graphList;  
        
    }
    
    def getCollectorList() {
        
        try{
            
            ArrayList<ArrayList<String>> collectorList = new ArrayList<ArrayList<String>>(); 

            HashMap map = getZenossFacade().getCollectors();

            collectorList.add(map);
            
            return collectorList;
            
        } catch(Exception e) {
            e.printStackTrace()
            throw e;
            
        }
    }
    
    def deviceList(instanceId) {
        try {
            ArrayList<ArrayList<String>> deviceList = new ArrayList<ArrayList<String>>();
            def user = springSecurityService.currentUser; 

            def instance =  VirtualMachine.findByReferenceId(instanceId);
            
            def devices = MonitoringDevice.withCriteria {
                eq('deleted', false)
                eq('virtualMachine', instance)
            }
            
            for(def device: devices) {
                HashMap item = new HashMap();
                item.put("deviceId", device.id)
                item.put("refrenceId", device.refrenceId)
                item.put("deviceName", device.deviceName)
                item.put("osType", device.osType)
                item.put("snmpCommunity", device.snmpCommunity)
                item.put("snmpPort", device.snmpPort)
                item.put("collector", device.collector)
                item.put("jobId", device.jobId)
                item.put("jobStatus", device.jobStatus)
                deviceList.add(item);
                
            }
            
            return deviceList;
            
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    //Enable device for monitoring
    def enableMonitorJob(requestData) {
        try {
            
             def instance =  VirtualMachine.findByReferenceId(requestData.instanceId);
            
//            MonitoringDeviceJob.triggerNow([collector: requestData.collector, snmpPort: requestData.snmpPort,
//                    snmpCommunity: requestData.snmpCommunity, monitorOsType: requestData.monitorOsType])
            
            ArrayList<ArrayList<String>> enableResponse = new ArrayList<ArrayList<String>>(); 
                        
            Device device = new Device.Builder(requestData.monitoringIp, requestData.monitoringIp, requestData.snmpCommunity)
                            .productionState(1000)
                            .priority(3)
                            .model(Boolean.TRUE)
                            .collector(requestData.collector)
                            .snmpPort(requestData.snmpPort).build();
                            
                            
            device.setOsType(requestData.monitorOsType);
                            
            DeviceResponse deviceResponse = getZenossFacade().enable(device);

            MonitoringDevice monitoringDevice = new MonitoringDevice();
            monitoringDevice.refrenceId = deviceResponse.getDeviceId();
            monitoringDevice.deviceName = requestData.monitoringIp;
            monitoringDevice.snmpCommunity = requestData.snmpCommunity;
            monitoringDevice.snmpPort = requestData.snmpPort;
            monitoringDevice.collector = requestData.collector;
            monitoringDevice.virtualMachine = instance;
            monitoringDevice.jobId = deviceResponse.getJobId();
            monitoringDevice.jobStatus = deviceResponse.getJobStatus();
            monitoringDevice.createdAt = new Date();

            monitoringDevice.osType = requestData.monitorOsType;
            monitoringDevice.save(flush: true);
            
            // Add job to job table
            def jobid = asyncJobService.addJob("ZENOSS_ADD_DEVICE")
           
            // Add job properties
            asyncJobService.addDeviceJobProperties("VM_REFERENCE_ID", instance.referenceId, jobid)   
            asyncJobService.addDeviceJobProperties("DEVICE_ID", monitoringDevice.refrenceId , jobid)   
            asyncJobService.addDeviceJobProperties("DEVICE_JOB_ID",deviceResponse.getJobId() , jobid)   
            
            // Pass device id for monitoring enable job
            EnableDeviceJob.triggerNow([id: deviceResponse.getDeviceId()])                       
            
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("uuid", deviceResponse.getJobId());
            item.put("deviceId", deviceResponse.getDeviceId());
            item.put("status", deviceResponse.getJobStatus());   
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            enableResponse.add(item);
            
            return enableResponse;
        } catch(Exception e) {
            throw e;
        }
    }       
      
     def getMonitoringDeviceStatus(deviceId) {  
         def jobPropsStatus = "";
        ArrayList monitoringArrayList = new ArrayList();                        
        def deviceIdJobPropsCriteria = JobProperties.createCriteria()
        def deviceIdProperty  = deviceIdJobPropsCriteria.list {
//            eq("name", "DEVICE_ID")   
//            and {
//                eq("value", deviceId)
//            }
            and {
                ne("jobStatus", "COMPLETED")
            }                                        
        }          
        if(!deviceIdProperty) {
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("deviceStatus", "No Job"); 
            monitoringArrayList.add(item)    
            return  monitoringArrayList                                 
        }
        
        for (def currentDeviceId : deviceIdProperty) {               
            if(currentDeviceId.value == deviceId) {
                def deviceJob = JobProperties.findWhere(asynchronousJobs: currentDeviceId.asynchronousJobs, name: "DEVICE_JOB_ID"); 
                def virtualMachine = JobProperties.findWhere(asynchronousJobs: currentDeviceId.asynchronousJobs, name: "VM_REFERENCE_ID");                 
                String deviceStatus = getZenossFacade().jobStatus(deviceJob.value);                
                def monitoringDevice = MonitoringDevice.findWhere(jobId: deviceJob.value, deleted: false);                
                currentDeviceId.jobStatus = "RUNNING"
                deviceJob.jobStatus = "RUNNING"
                virtualMachine.jobStatus = "RUNNING"
                
                if(jobPropsStatus == "RUNNING") {
                    jobPropsStatus = "RUNNING"
                }
                
                
                currentDeviceId.save(flush: true)
                deviceJob.save(flush: true)
                virtualMachine.save(flush: true)

                if(monitoringDevice) {
                    monitoringDevice.jobStatus = deviceStatus;
                    monitoringDevice.save(flush: true);

                    if (!monitoringDevice.save()) {
                        monitoringDevice.errors.allErrors.each { println it }
                    }
                }  
                if((deviceStatus == "SUCCESS") || (deviceStatus == "STARTED")) {                                                     

                    currentDeviceId.asynchronousJobs.status = JobStatus.valueOf("COMPLETED")
                    currentDeviceId.asynchronousJobs.completedAt = new Date()
                    currentDeviceId.asynchronousJobs.save(flush: true)                    

                    currentDeviceId.jobStatus = "COMPLETED"
                    deviceJob.jobStatus = "COMPLETED"
                    virtualMachine.jobStatus = "COMPLETED"

                    currentDeviceId.save(flush: true)
                    deviceJob.save(flush: true)
                    virtualMachine.save(flush: true)
                    jobPropsStatus = "COMPLETED"
                    if (!currentDeviceId.asynchronousJobs.save()) {
                        currentDeviceId.asynchronousJobs.errors.allErrors.each { println it }
                    }                                                           
                }    
            }
        }     
        
        if(deviceIdProperty) {                      
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("deviceStatus", jobPropsStatus); 
            monitoringArrayList.add(item)
        }
        return monitoringArrayList;
    }
    
    def checkForDeviceJob(deviceId) {           
        
        try {                     
            def jobProperty = ""
            def asynchronousJobList = AsynchronousJobs.findWhere(jobType : JobType.values()[8], status: JobStatus.values()[0])
            println("*****Calling*****" + asynchronousJobList)
            ArrayList monitoringArrayList = new ArrayList(); 
            for (def asynchronousJob : asynchronousJobList) {                                                 
                println("++++asynchronousJob++++" + asynchronousJob)
                asynchronousJob.status = JobStatus.valueOf("RUNNING")
                asynchronousJob.startedAt = new Date()
                asynchronousJob.save(flush: true)
                println("++++asynchronousJob stat++++" + asynchronousJob.status)
                def deviceJob = JobProperties.findWhere(asynchronousJobs: asynchronousJob, name: "DEVICE_JOB_ID");
                def device = JobProperties.findWhere(asynchronousJobs: asynchronousJob, name: "DEVICE_ID");
                def virtualMachine = JobProperties.findWhere(asynchronousJobs: asynchronousJob, name: "VM_REFERENCE_ID");
                
                deviceJob.jobStatus = "RUNNING"
                device.jobStatus = "RUNNING"
                virtualMachine.jobStatus = "RUNNING"

                deviceJob.save(flush: true)
                device.save(flush: true)
                virtualMachine.save(flush: true)
                
                jobProperty = JobProperties.findWhere(value: deviceId, name: "DEVICE_ID" , jobStatus: "COMPLETED");       

                if(jobProperty) {      

                    HashMap<String,String> item = new HashMap<String,String>(); 
                    item.put("deviceStatus", jobProperty.jobStatus); 
                    monitoringArrayList.add(item)
                }
                
                String deviceStatus = getZenossFacade().jobStatus(deviceJob.value);
                  
                def monitoringDevice = MonitoringDevice.findWhere(jobId: deviceJob.value, deleted: false);
                
                monitoringDevice.jobStatus = deviceStatus;
                monitoringDevice.save(flush: true);

                if (!monitoringDevice.save()) {
                    monitoringDevice.errors.allErrors.each { println it }
                }                            
                
                println("*****deviceStatus*****" + deviceStatus)
                
                if((deviceStatus == "SUCCESS") || (deviceStatus == "STARTED")) {                                        
                    
                    asynchronousJob.status = JobStatus.valueOf("COMPLETED")
                    asynchronousJob.completedAt = new Date()
                    asynchronousJob.save(flush: true)                    
                    println("*****after success asynchronousJob stat*****" + asynchronousJob.status)
                    deviceJob.jobStatus = "COMPLETED"
                    device.jobStatus = "COMPLETED"
                    virtualMachine.jobStatus = "COMPLETED"
                    
                    deviceJob.save(flush: true)
                    device.save(flush: true)
                    virtualMachine.save(flush: true)
                    
                    if (!asynchronousJob.save()) {
                        asynchronousJob.errors.allErrors.each { println it }
                    }                                                           
                }
            }
            
            
                   

            return monitoringArrayList;
        } catch(Exception e) {
            throw e;
        }        
        
    }
    def deviceEnabledStatus(requestData) {
        try {
            def instance =  VirtualMachine.findByReferenceId(requestData.instanceId);
            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();
            
            ZenossFacade zenossFacade = getZenossFacade();

            String deviceStatus = zenossFacade.jobStatus(requestData.jobId);
                        
            def monitoringDevice = MonitoringDevice.findWhere(jobId: requestData.jobId, deleted: false);
            
            monitoringDevice.jobStatus = deviceStatus;
            monitoringDevice.save(flush: true);
                       
            if (!monitoringDevice.save()) {
                    monitoringDevice.errors.allErrors.each { println it }
                } else {
                     
                }
            
            if((deviceStatus == "SUCCESS") || (deviceStatus == "STARTED")) {

                Device device = new Device.Builder(requestData.deviceId).build()
                
                if(zenossFacade.checkComponentsAvailable(device)) {
                    
//                    String status = zenossFacade.disable(device);
//                   
//
//                    def oldMonitoringDevice = MonitoringDevice.findWhere(virtualMachine: instance, deleted: false);
//                    
//                    if(status == "SUCCESS") {
//                
//                        oldMonitoringDevice.deleted = true;
//                        oldMonitoringDevice.deletedAt = new Date();
//                        oldMonitoringDevice.save(flush: true);
//
//                    }
                }
                zenossFacade.overWriteDefaultTemplates(device);
                
                
            }
            
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("status", deviceStatus);
            response.add(item);
            
            return response;
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    def enableMonitor(collector, snmpPort, snmpCommunity, monitorOsType) {
        try {
            
            
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    def disableMonitoringDevice(requestData) {       
        try {
            def instance =  VirtualMachine.findByReferenceId(requestData.instanceId);
            
            def monitoringDevice = MonitoringDevice.findWhere(virtualMachine: instance, deleted: false);
            
            Device device = new Device.Builder(monitoringDevice.refrenceId).build();
//            
            ArrayList<ArrayList<String>> response = new ArrayList<ArrayList<String>>();
//            
            String status = getZenossFacade().disable(device);
            
            if(status == "SUCCESS") {
                
                //All alarms removed to this device
                
                def alarms = Alarm.withCriteria {
                    eq('monitoringDevice', monitoringDevice)
                } 
                
                println("alarms"+alarms)
                
                for(def alarm: alarms) {
                    
                    alarm.deleted = true;
                    alarm.deletedAt = new Date();
                    alarm.save(flush: true);
                }
                
                monitoringDevice.deleted = true;
                monitoringDevice.deletedAt = new Date();
                monitoringDevice.save(flush: true);
                
                HashMap<String,String> item = new HashMap<String,String>();
                item.put("status", status);
                response.add(item);
            }
            
            return response;
            
        } catch(Exception e) {
            println("msg "+e.getMessage())
            if(e.getMessage() == "Moved Temporarily") {
                ArrayList<ArrayList<String>> errorResponse = new ArrayList<ArrayList<String>>();
                HashMap<String,String> item = new HashMap<String,String>();                
                item.put("result", GeneralConstants.RESULT_FALSE);
                errorResponse.add(item);
                return errorResponse;
            }
            throw e;
        }
    }
    
}   
