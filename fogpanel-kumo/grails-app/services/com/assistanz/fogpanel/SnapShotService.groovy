package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.snapshot.CSSnapshotService
import com.assistanz.cloud.cloudstack.snapshot.SnapShotResponse
import com.assistanz.cloud.cloudstack.snapshot.VMSnapShotResponse
import com.assistanz.cloud.cloudstack.snapshot.SnapShotPolicyResponse
import grails.converters.deep.JSON
import java.text.SimpleDateFormat
import com.assistanz.cloud.cloudstack.volume.CSVolumeService
import com.assistanz.cloud.cloudstack.volume.VolumeResponse
import com.assistanz.fogpanel.GeneralConstants;
import com.assistanz.fogpanel.Volume;
import com.assistanz.cloud.cloudstack.template.CSTemplateService
import com.assistanz.cloud.cloudstack.template.TemplateResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.transaction.Transactional
import org.apache.commons.logging.LogFactory;

@Transactional
class SnapShotService {
    
    def springSecurityService;
    NotificationService notificationService
    LicenseValidationService licenseValidationService
    private static final log = LogFactory.getLog(this)
    
    def snapShotServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSSnapshotService csSnapshotService = new CSSnapshotService(server);
        
        return csSnapshotService;
        
    }
    
    def volumeServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        CSVolumeService csVolumeService = new CSVolumeService(server);
      
        return csVolumeService;
    }
    
    def templateServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSTemplateService cSTemplateService = new CSTemplateService(server);
        
        return cSTemplateService;
        
    }
    def count(zoneReferenceId, status) {
        def role = springSecurityService.getPrincipal().getAuthorities()
        def user = springSecurityService.currentUser
        def snapShotCriteria = Snapshot.createCriteria();
        def totalManual = 0;
        def totalAuto = 0;
        def totalJobs = 0;
        def totalCount = 0;
        def name;
        def type;
        def createdDate;
        def referenceId;
        def snapShots;
        
        ArrayList countList = new ArrayList(); 
        HashMap countItem = new HashMap(); 
        if((zoneReferenceId == null || zoneReferenceId == "null") && (status == null || status == "null")) {
            if(role.iterator().next() == "ROLE_ADMIN" ) {                
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {                
                totalManual = Snapshot.findAllWhere(deleted: false, snapshotType:"MANUAL", user: user).size(); 
                totalJobs = SnapshotPolicy.findAllWhere(deleted: false, user: user).size();
                totalAuto = snapShotCriteria.list{
                        eq("deleted", false)  
                        eq("user", user)
                        and {
                            ne("snapshotType", "MANUAL")                             
                        }
                        
                }.size(); 
                totalCount = Snapshot.findAllWhere(deleted: false, user: user).size();     
            }
        } else if((zoneReferenceId == null || zoneReferenceId == "null") && (status != null || status != "null")) {            
            if(role.iterator().next() == "ROLE_ADMIN" ) {                
                 if(status == "Manual") {                    
                     snapShots = Snapshot.findAllWhere(deleted: false, snapshotType:"MANUAL");   
                     
                     totalManual = Snapshot.findAllWhere(deleted: false, snapshotType:"MANUAL").size();         
                     totalJobs = SnapshotPolicy.findAllWhere(deleted: false).size(); 
                     totalAuto = snapShotCriteria.list{
                        eq("deleted", false)                     
                        and {
                            ne("snapshotType", "MANUAL")  
                        }
                }.size(); 
                 } else if(status == "Job") {                     
                     snapShots = SnapshotPolicy.findAllWhere(deleted: false);   
                     
                    totalManual = Snapshot.findAllWhere(deleted: false, snapshotType:"MANUAL").size();                
                     totalJobs = snapShots.size(); 
                     totalAuto = snapShotCriteria.list{
                        eq("deleted", false)                     
                        and {
                            ne("snapshotType", "MANUAL")  
                        }
                }.size(); 
                 } else if(status == "auto") {                     
                    snapShots =  snapShotCriteria.list{
                    ne("snapshotType", "MANUAL") 
                    and {
                        eq("deleted", false)  
                    }
                    };
                    
                    totalManual = Snapshot.findAllWhere(deleted: false, snapshotType:"MANUAL").size();                
                     totalJobs = SnapshotPolicy.findAllWhere(deleted: false).size();
                     totalAuto = snapShots.size();
//                    Console.print("snapShots" + snapShots.size())
                }                    
                              
                ArrayList<ArrayList<String>> snapShotList = new ArrayList<ArrayList<String>>();            
                for(int i=0; i < snapShots.size(); i++) {
                    
                    def item = snapShots[i]; 
                   
                    if(status == "Job") {
                        name = item.snapshotName;
                        type = item.intervalType;
                        createdDate = item.timeZoneValue;
                        referenceId = item.snapshotPolicyReferenceId;
                    }  else if(status == "auto" || status == "Manual") {                        
                        name = item.name;
                        type = item.snapshotType;
                        createdDate = item.created
                        referenceId = item.snapshotReferenceId     
                    }

                    HashMap<String,String> snapShotItem = new HashMap<String,String>();                                
                        snapShotItem.put("name", name);
                        snapShotItem.put("volumeId", item.volume.volumeReferenceId);
                        snapShotItem.put("volumeName", item.volume.name);                            
                        snapShotItem.put("zoneName", item.volume.zone.aliasName);
                        snapShotItem.put("zoneReferenceId", item.volume.zone.referenceZoneId);
                        snapShotItem.put("createdDate", createdDate);
                        snapShotItem.put("user", item.user.username);
                        snapShotItem.put("referenceId",referenceId);
                        snapShotItem.put("type", type);                            
                        snapShotList.add(snapShotItem);
                }
                countItem.put("snapshotInfo", snapShotList);  
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                
            }
        
        } else if((zoneReferenceId != null || zoneReferenceId != "null") && (status == null || status == "null")) {            
            if(role.iterator().next() == "ROLE_ADMIN" ) {                                                             
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {  
                snapShots = Snapshot.findAllWhere(deleted: false, snapshotType:"MANUAL", user: user);                                                           
                ArrayList<ArrayList<String>> snapShotList = new ArrayList<ArrayList<String>>();            
                for(int i=0; i < snapShots.size(); i++) { 
                     def item = snapShots[i];                                
                     if(item.volume.zone.referenceZoneId == zoneReferenceId) {                                    
                         HashMap<String,String> snapShotItem = new HashMap<String,String>();                                                       
                        snapShotItem.put("volumeId", item.volume.volumeReferenceId);
                        snapShotItem.put("volumeName", item.volume.name);      
                        snapShotItem.put("diskVolumeType", item.volume.type);                                                      
                        snapShotItem.put("zoneName", item.volume.zone.aliasName);
                        snapShotItem.put("zoneReferenceId", item.volume.zone.referenceZoneId);
                        snapShotItem.put("created", item.created);
                        snapShotItem.put("snapShotType", item.snapshotType);
                        snapShotItem.put("snapshotState", item.state);
                        snapShotItem.put("name", item.name);                        
                        snapShotItem.put("user", item.user.username);
                        snapShotItem.put("referenceId",item.snapshotReferenceId);
                        snapShotItem.put("type", type);          
                        if(item.volume.virtualMachine == null || item.volume.virtualMachine == "null") {
                            snapShotItem.put("virtualMachineName", "");
                        } else {
                            snapShotItem.put("virtualMachineName", item.volume.virtualMachine.displayName);
                        }
                        snapShotList.add(snapShotItem);                                                
                     }    
                     totalCount = snapShotList.size();
                 }
                 countItem.put("snapshotInfo", snapShotList);
            }
    } else if((zoneReferenceId != null || zoneReferenceId != "null") && (status != null || status != "null")) {            
            if(role.iterator().next() == "ROLE_ADMIN" ) {        
                if(status == "Manual") {                
                    snapShots = Snapshot.findAllWhere(deleted: false, snapshotType:"MANUAL");                                    

                } else if(status == "auto") {
                    snapShots = snapShotCriteria.list {
                        ne("snapshotType", "MANUAL") 
                        and {
                            eq("deleted", false)  
                        }
                    } 
                } else if(status == "Job") {                
                    snapShots = SnapshotPolicy.findAllWhere(deleted: false);
                } 
                def snapshotCount =  Snapshot.findAllWhere(deleted: false);
                totalJobs = snapshotCount;
                for(int i=0; i < snapshotCount.size(); i++) { 
                    def items = snapshotCount[i];  

                    if((items.snapshotType == "MANUAL")&& (items.volume.zone.referenceZoneId == zoneReferenceId)) {
                        totalManual = totalManual+1;
                        totalJobs = 0;
                    } else if((items.snapshotType != "MANUAL") && (items.volume.zone.referenceZoneId == zoneReferenceId)) {
                        totalAuto = totalAuto+1;
                        totalJobs = 0;
                    }

                }
                ArrayList<ArrayList<String>> snapShotList = new ArrayList<ArrayList<String>>();            
                for(int i=0; i < snapShots.size(); i++) { 
                def item = snapShots[i];           
                    if(status == "Job") {                   
                        name = item.snapshotName;
                        type = item.intervalType;
                        createdDate = item.timeZoneValue;
                        referenceId = item.snapshotPolicyReferenceId;
                    }  else if(status == "auto" || status == "Manual") {
                        name = item.name;
                        type = item.snapshotType;
                        createdDate = item.created
                        referenceId = item.snapshotReferenceId     
                    }
                    if(item.volume.zone.referenceZoneId == zoneReferenceId) {            
                        HashMap<String,String> snapShotItem = new HashMap<String,String>();                                
                        snapShotItem.put("name", name);
                        snapShotItem.put("volumeId", item.volume.volumeReferenceId);
                        snapShotItem.put("volumeName", item.volume.name);                            
                        snapShotItem.put("zoneName", item.volume.zone.aliasName);
                        snapShotItem.put("zoneReferenceId", item.volume.zone.referenceZoneId);
                        snapShotItem.put("createdDate", createdDate);
                        snapShotItem.put("user", item.user.username);
                        snapShotItem.put("referenceId",referenceId);
                        snapShotItem.put("type", type);                            
                        snapShotList.add(snapShotItem);                            
                    }                   
                }
                countItem.put("snapshotInfo", snapShotList);                         
               
            }
        }
        countItem.put("totalManual", totalManual);
        countItem.put("totalAuto", totalAuto);
        countItem.put("totalJobs", totalJobs); 
        countItem.put("totalCount", totalCount); 
        countList.add(countItem);
        return countList;
    }

    def list(String volumeReferenceId, String name) {
        try {        
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()
            def result;
            def volume = Volume.findByVolumeReferenceId(volumeReferenceId);
            if((volumeReferenceId == "null" || volumeReferenceId == null) && (name == "null" || name == null)) {
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = Snapshot.findAll("from Snapshot as snapshot where snapshot.deleted=?", [false]);
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    result = Snapshot.findAllWhere(deleted: false, user: user); 
                }   
            } else if((volumeReferenceId != "null" || volumeReferenceId != null) && (name == "null" || name == null)) {
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = Snapshot.findAllWhere(deleted: false, volume: volume); 
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    result = Snapshot.findAllWhere(deleted: false, user: user, volume: volume); 
                }   
            } else if((name != "null" || name != null) && (volumeReferenceId == "null" || volumeReferenceId == null)) {
                def snapShotCriteria = Snapshot.createCriteria()
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = snapShotCriteria.list {
                        like("name", "%" + name + "%")
                        and {
                            eq("deleted", false)
                        }
                    }
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    result = snapShotCriteria.list {
                        like("name", "%" + name + "%")
                        and {
                            eq("deleted", false)
                            and {
                                eq("user", user)
                            } 
                        }
                    }
                }   
            }          
            ArrayList<ArrayList<String>> snapshotList = new ArrayList<ArrayList<String>>();            
            for(int i=0; i < result.size(); i++) { 
                def item = result[i]; 
                HashMap<String,String> snapShotItem = new HashMap<String,String>();                
                    snapShotItem.put("snapShotId", item.snapshotReferenceId);
                    snapShotItem.put("snapShotName", item.name);
                    snapShotItem.put("snapShotType", item.snapshotType);
                    snapShotItem.put("snapshotState", item.state);
                    snapShotItem.put("diskVolumeId", item.volume.volumeReferenceId);
                    snapShotItem.put("diskVolumeName", item.volume.name);
                    snapShotItem.put("diskVolumeType", item.volume.type);
                    if(item.volume.virtualMachine == null || item.volume.virtualMachine == "null") {
                        snapShotItem.put("virtualMachineName", "");
                    } else {
                        snapShotItem.put("virtualMachineName", item.volume.virtualMachine.displayName);
                    }
                    snapShotItem.put("created", item.created);                 
                    snapshotList.add(snapShotItem);
            }
            return snapshotList;
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }      
    }
     
    def create(String requestBody) {
         try {
            
            licenseValidationService.authorizeAction(FogPanelService.SNAPSHOT_CREATE)
            
            def user = springSecurityService.currentUser
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
      
            Snapshot.withTransaction {
                def response = snapShotServer().createSnapshot(requestData.volumeId, null)
                def jobResponse = snapShotServer().snapshotJobResult(response.jobid);   
                Calendar calendar = Calendar.getInstance(); 
                Snapshot newSnapshot  = new Snapshot();
                newSnapshot.snapshotReferenceId = jobResponse.asychronousJobInstanceId
                newSnapshot.deleted = false
                newSnapshot.snapshotType = "MANUAL"
                newSnapshot.name = requestData.snapshotName
                newSnapshot.billingType = "hourly" //requestData.billingType
                newSnapshot.user = user
                newSnapshot.state = GeneralConstants.BACKING_UP
                newSnapshot.created = calendar.getTime().toString()
                newSnapshot.job = response.jobid
                newSnapshot.volume = Volume.findByVolumeReferenceId(requestData.volumeId);
                newSnapshot.zone = Volume.findByVolumeReferenceId(requestData.volumeId)?.zone;
                newSnapshot.size = Double.parseDouble(newSnapshot.volume.customDiskSize) 
                newSnapshot.save(flush: true, failOnError:true)
                if (newSnapshot.hasErrors()) {
                    throw new ValidationException(newSnapshot.errors.allErrors);
                }
                ArrayList<ArrayList<String>> snapshot = new ArrayList<ArrayList<String>>();            
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("jobId", response.jobid);
                snapshot.add(item);
                log.info("Snapshot created for volume: ${requestData.volumeId} and returned job id ") 
                return snapshot;
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
     def deleteJob(String requestBody) {
        try {
            def jobResult = "";
            def requestData = JSON.parse(requestBody)
            ArrayList<ArrayList<String>> snapshotResult = new ArrayList<ArrayList<String>>();  
            def snapshot = Snapshot.findBySnapshotReferenceId(requestData.snapId);
            def jobResponse = snapShotServer().snapshotJobResult(requestData.jobId);    
            if(jobResponse.asychronousJobStatus == "0") {
                jobResult = "Pending";
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", jobResponse.asychronousJobInstanceId);
                snapshotResult.add(item);
            }  else if(jobResponse.asychronousJobStatus == "1") {
                jobResult = "Success";
                snapshot.deleted = true
                snapshot.save(flush: true);    
                HashMap<String,String> snapShotItem = new HashMap<String,String>();                
                snapShotItem.put("jobResult", GeneralConstants.RESULT_SUCCESS); 
                snapshotResult.add(snapShotItem);   
            } else if(jobResponse.asychronousJobStatus == "2") {
                jobResult = "Failed";
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                snapshotResult.add(item);          
            }
            log.info("Job, snapshot deleted ${jobResult} for snapshot: ${requestData.snapId}")   
            return snapshotResult;             
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }     
    }
    
    def job(String requestBody) {
        try {
            def jobResult = "";
             // convert string to json object
            def account = springSecurityService.currentUser.account;
            def requestData = JSON.parse(requestBody)
            ArrayList<ArrayList<String>> snapshotResult = new ArrayList<ArrayList<String>>();  
            def snapshot = Snapshot.findByJob(requestData.jobId);
            // def jobResponse = snapShotServer().snapshotJobResult(requestData.jobId);    
            if(snapshot.state == "BackingUp" || snapshot.state == "Creating") {
                jobResult = "Pending";
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", snapshot.job);
                snapshotResult.add(item);
            } else if(snapshot.state == "BackedUp") {
                    jobResult = "Success";
                    HashMap<String,String> snapShotItem = new HashMap<String,String>();                
                    snapShotItem.put("snapShotId", snapshot.snapshotReferenceId);
                    snapShotItem.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                    snapShotItem.put("snapShotName", snapshot.name);
                    snapShotItem.put("snapShotType", snapshot.snapshotType);
                    snapShotItem.put("snapshotState", snapshot.state);
                    snapShotItem.put("diskVolumeId", snapshot.volume.volumeReferenceId);
                    snapShotItem.put("diskVolumeType", snapshot.volume.type);
                    snapShotItem.put("diskVolumeName", snapshot.volume.name);
                    if(snapshot.volume.virtualMachine == null || snapshot.volume.virtualMachine == "null") {
                        snapShotItem.put("virtualMachineName", "");
                    } else {
                        snapShotItem.put("virtualMachineName", snapshot.volume.virtualMachine.displayName);
                    }
                    snapShotItem.put("created", snapshot.created);                 
                    snapshotResult.add(snapShotItem);                                    
                } else if(snapshot.state == "Error") {
                    jobResult = "Failed";
                    HashMap<String,String> item = new HashMap<String,String>()
                    item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                    item.put("message", jobResponse.errorText);
                    snapshotResult.add(item); 
                }
                log.info("Job for Snapsot:${snapshot.snapshotReferenceId} created ${jobResult}") 
                return snapshotResult;             
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
                throw ex;
            }             
        } 
        
    def createVolume(String requestBody) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.VOLUME_CREATE)
            
            def user = springSecurityService.currentUser
        
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("account", new String(user.account.userName));
            optional.put("domainid", new String(user.account.domain.referenceId));
            optional.put("snapshotid",  requestData.snapshotId);
            Snapshot snapshot = Snapshot.findBySnapshotReferenceId(requestData.snapshotId);
            if(snapshot.volume.type == "ROOT") {
                def customDisk = DiskOffer.findWhere(cluster: snapshot.volume.virtualMachine.cluster, customized:true, deleted: false)
                if(!customDisk) {
                    throw new Exception("Cannot Create Volume!please contact admin");
                }
                
                Volume.withTransaction {
                    def response = volumeServer().createVolume(requestData.name ,optional); 

                    def diskOffer = snapshot.volume.diskOffer
                    def vm = snapshot.volume.virtualMachine
                    Volume newVolume = new Volume()
                    if(diskOffer == null || diskOffer == "null") {
                       newVolume.customDiskSize = snapshot.volume.customDiskSize;
                       newVolume.diskOffer = null
                    } else {
                        newVolume.diskOffer = diskOffer
                    }
                    if(vm) {
                        newVolume.cluster = vm.cluster
                    }
                    newVolume.source = "SNAPSHOT";
                    newVolume.name = requestData.name;
                    newVolume.zone = snapshot.volume.zone
                    newVolume.owner = Account.findByUserName(user.account.userName);
                    newVolume.user = user
                    newVolume.deleted = false; 
                    newVolume.billingType = requestData.billingType 
                    
//                    newVolume.createdDate = Calendar.getInstance().getTime()
                    newVolume.job = response.jobId;
                    def jobResponse = snapShotServer().snapshotJobResult(response.jobId);  
                    newVolume.volumeReferenceId = jobResponse.asychronousJobInstanceId;
                    newVolume.save(flush: true, failOnError:true);
                    ArrayList<ArrayList<String>> volume = new ArrayList<ArrayList<String>>();            
                    HashMap<String,String> item = new HashMap<String,String>(); 
                    item.put("result", GeneralConstants.RESULT_SUCCESS);
                    item.put("jobId", response.jobId);
                    volume.add(item);
                    log.info("Volume:${newVolume.id} created Successfully from snapshot: ${requestData.snapshotId} and returned job id ") 
                    return volume;
                } 
            } else {
                Volume.withTransaction {
                def response = volumeServer().createVolume(requestData.name ,optional); 
            
                def diskOffer = snapshot.volume.diskOffer
                def vm = snapshot.volume.virtualMachine
                    Volume newVolume = new Volume()
                    newVolume.diskOffer = diskOffer
                    newVolume.cluster = diskOffer.cluster
                    newVolume.name = requestData.name;
                    newVolume.zone = snapshot.volume.zone
                    newVolume.owner = Account.findByUserName(user.account.userName);
                    newVolume.user = user
                    newVolume.deleted = false; 
                    newVolume.billingType = requestData.billingType 
//                    newVolume.createdDate = Calendar.getInstance().getTime()
                    newVolume.job = response.jobId;
                    def jobResponse = snapShotServer().snapshotJobResult(response.jobId);  
                    newVolume.volumeReferenceId = jobResponse.asychronousJobInstanceId;
                    newVolume.save(flush: true, failOnError:true);
                    ArrayList<ArrayList<String>> volume = new ArrayList<ArrayList<String>>();            
                    HashMap<String,String> item = new HashMap<String,String>(); 
                    item.put("result", GeneralConstants.RESULT_SUCCESS);
                    item.put("jobId", response.jobId);
                    volume.add(item);
                    log.info("Volume:${newVolume.id} created Successfully from snapshot: ${requestData.snapshotId} and returned job id ") 
                    return volume;
                }  
            }
            
             
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def createTemplate(String requestBody) {
        try {    
            
            def user = springSecurityService.currentUser
        
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            Snapshot snapshot = Snapshot.findBySnapshotReferenceId(requestData.snapshotId);
            def template = snapshot.volume.virtualMachine.template
            HashMap<String,String> optional = new HashMap<String,String>();     
            optional.put("snapshotid", requestData.snapshotId);
            optional.put("passwordenabled", new Boolean(requestData.passwordEnabled).toString());
            
            def response = templateServer().createTemplate(requestData.description, 
            requestData.tempName, template.osType.referenceOsTypeId, optional)
            Template newTemplate  = new Template();  
            def jobResponse = templateServer().templateJobResult(response.jobId);   
            if(jobResponse.asychronousJobProgressStatus == "0") {
                newTemplate.templateReferenceId = jobResponse.asychronousJobInstanceId; 
            }   
            newTemplate.job = response.jobId;
//            newTemplate.templateReferenceId = response.templateId;
            newTemplate.deleted = false;  
            newTemplate.name = requestData.tempName;
            newTemplate.account = user.account;  
            newTemplate.baseOs = template.baseOs;  
            newTemplate.user = user;
            newTemplate.description = requestData.description;
            newTemplate.url = "FROM SNAPSHOT";
            newTemplate.format = template.format;
            newTemplate.hypervisor = template.hypervisor
            newTemplate.extractable = template.extractable;                    
            newTemplate.featured = false
            newTemplate.isPublic = false
            newTemplate.myTemplate = true; 
            newTemplate.appTemplate = false;
            newTemplate.cost = template.cost;
            newTemplate.addToZones(snapshot.volume.virtualMachine.zone);
            newTemplate.osCategory = template.osType.osCategory;    
            newTemplate.osType = template.osType
            newTemplate.passwordEnabled = requestData.passwordEnabled;
            //save new template
            newTemplate.save(flush: true)            
            if (newTemplate.hasErrors()) {
                throw new ValidationException(newTemplate.errors.allErrors);
            }
            ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId);
            temp.add(item);
            log.info("Template:${newTemplate.id} created Successfully from snapshot: ${requestData.snapshotId} and returned job id ") 
            return temp;
        }catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }        
    }
    
    
    def delete(String requestBody) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.SNAPSHOT_DELETE)
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            Snapshot snapshot = Snapshot.findBySnapshotReferenceId(requestData.snapshotId);
            Snapshot.withTransaction {
                def response = snapShotServer().deleteSnapshot(requestData.snapshotId);  
//                snapshot.job = response.jobid
//                snapshot.save(flush: true, failOnError:true)
//                if (snapshot.hasErrors()) {
//                    throw new ValidationException(snapshot.errors.allErrors);
//                }
                ArrayList<ArrayList<String>> snapshotResult = new ArrayList<ArrayList<String>>();            
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("jobId", response.jobid);
                item.put("snapId", requestData.snapshotId);
                snapshotResult.add(item);
                log.info("Ssnapshot deleted for Snapshot: ${requestData.snapshotId} and returned job")   
                return snapshotResult;
            }  
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    def sendSnapMail(snapshot) {
         try {            
            def account = snapshot.user.account            
                     
            //send Mail Menthod
            def mailtemplate = MailTemplate.findByName("createSnapshot")
            def headerTemplate = MailTemplate.findByName("header");
            def footerTemplate = MailTemplate.findByName("footer");
            def applicationUrl = ApplicationHolder.getApplication().config.cloudstack.applicationUrl
            def logoConfig = Config.findByName("organisation.billing.logo")
            def signature = Config.findByName("organisation.billing.signature");

            def hasHeader = mailtemplate.hasHeader
            def hasFooter = mailtemplate.hasFooter                    

            // Header Content
            def logoUrl = ""
            if(logoConfig.value == "") {                    
                logoUrl = applicationUrl.toString() + "/images/fog_logo.png"
            } else {
                logoUrl =  logoConfig.value;
            }
            def logoContent = '<img style="height: 30px; width: 100px"  src = '+logoUrl+' alt="progress" height="9" width="100">'
            def headerContent = logoContent + headerTemplate.content;

            // Footer content
            def footerContent = footerTemplate.content;

            // Content Area
            def userName;
            def snapName;
            def attachedVolume;
            def status;
            def type; 
            def message;
            def finalMessage;   
            
            userName = mailtemplate.content.replaceAll("\\[userName\\]", account.firstName)
            snapName = userName.replaceAll("\\[snapshotName\\]", snapshot.name)
            attachedVolume = snapName.replaceAll("\\[attachedVolume\\]", snapshot.volume.name)
            status = attachedVolume.replaceAll("\\[status\\]", snapshot.state)        
            type = status.repla.replaceAll("\\[snapshotType\\]", snapshot.snapshotType)    
            message = type.replaceAll("\\[signature\\]", signature.value);                            
            
            if((hasHeader == true || hasHeader == 'true') && (hasFooter == true || hasFooter == 'true')) {
                finalMessage = "<html><body>" + headerContent.toString() + message.toString() + footerContent.toString() + "</body></html>";
            } else if((hasHeader == true || hasHeader == 'true') && (hasFooter == false || hasFooter == 'false')) {
                finalMessage = "<html><body>" + headerContent.toString() + message.toString() + "</body></html>";
            } else if((hasHeader == false || hasHeader == 'false') && (hasFooter == true || hasFooter == 'true')) {                       
                finalMessage = "<html><body>" + message.toString() + footerContent.toString() + "</body></html>";
            } else if((hasHeader == true || hasHeader == 'true') && (hasFooter == false || hasFooter == 'false')) {                       
                finalMessage = "<html><body>" + headerContent.toString() + message.toString() + "</body></html>";
            } else {
                finalMessage = "<html><body>" + message.toString() + "</body></html>";
            }
            notificationService.send(account.email.toString(), mailtemplate.subject, finalMessage)
         } catch(Exception ex) {
             ex.printStackTrace(System.err);
            throw ex;
         }
    }
    
    
    def listVMSnapshot(vmId, zoneReferenceId) {
        try {
            
            Console.print(zoneReferenceId)
            
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()
            def result;
            HashMap<String,String> optional = new HashMap<String,String>(); 
            optional.put("listall", "true");
            def response = snapShotServer().listVMSnapshot(optional)
            for(Iterator<VMSnapShotResponse> iter = response.vmsnapShots.iterator(); iter.hasNext();) {
                def data = iter.next();
                VMSnapshot vmSnapshot = VMSnapshot.findByReferenceId(data.VMSnapshotId);
                if (!vmSnapshot) {   
                   
                } else if(vmSnapshot) {
                    vmSnapshot.state = data.VMSnapshotState
                    vmSnapshot.current = Boolean.parseBoolean(data.VMSnapshotCurrent); 
                    if(data.VMSnapshotParentName) {
                       vmSnapshot.parentName = data.VMSnapshotParentName 
                    }
                    vmSnapshot.type = data.VMSnapshotType
                    vmSnapshot.save(flush: true);   
                }
            }            
            if(role.iterator().next() == "ROLE_ADMIN" ) {
                result = VMSnapshot.findAllWhere(deleted: false);
            } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                if(vmId) {
                    def vm = VirtualMachine.findByReferenceId(vmId);
                    
                    if(zoneReferenceId && zoneReferenceId != "All") {
                        def zone = Zone.findByReferenceZoneId(zoneReferenceId)
                        result = VMSnapshot.findAllWhere(deleted: false, user: user, virtualMachine: vm, zone: zone); 
                                                
                    } else {
                        result = VMSnapshot.findAllWhere(deleted: false, user: user, virtualMachine: vm); 
                    }
                    
                } else {
                    if(zoneReferenceId && zoneReferenceId != "All") {
                        def zone = Zone.findByReferenceZoneId(zoneReferenceId)
                        result = VMSnapshot.findAllWhere(deleted: false, user: user, zone: zone); 
                    } else {
                         result = VMSnapshot.findAllWhere(deleted: false, user: user); 
                    }
                }    
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm"); 
            ArrayList<ArrayList<String>> vmSnapshotList = new ArrayList<ArrayList<String>>();            
            for(int i=0; i < result.size(); i++) { 
                def item = result[i]; 
                HashMap<String,String> vmSnapShotItem = new HashMap<String,String>();                
                    vmSnapShotItem.put("referenceId", item.referenceId);
                    vmSnapShotItem.put("id", item.id);
                    vmSnapShotItem.put("name", item.name);
                    vmSnapShotItem.put("type", item.type);
                    vmSnapShotItem.put("state", item.state);
                    vmSnapShotItem.put("parentName", item.parentName);
                    if(item.current == true) {
                        vmSnapShotItem.put("current", "yes");
                    } else {
                        vmSnapShotItem.put("current", "no");
                    }
                    vmSnapShotItem.put("snapshotMemory", item.snapshotMemory);
                    vmSnapShotItem.put("virtualMachineName", item.virtualMachine.displayName);
                    vmSnapShotItem.put("created", dateFormat.format(item.createdDate).toString());   
                    vmSnapShotItem.put("zone", item.zone.aliasName); 
                    vmSnapShotItem.put("account", item.account.userName); 
                    vmSnapShotItem.put("user", item.user.username); 
                    vmSnapshotList.add(vmSnapShotItem);
            }
            return vmSnapshotList;
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    }
    
    
    def createVMSnapshot(String requestBody) {
         try {
             
            licenseValidationService.authorizeAction(FogPanelService.VM_SNAPSHOT_CREATE)
            
            def user = springSecurityService.currentUser
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
            HashMap<String,String> optional = new HashMap<String,String>();   
            optional.put("description", requestData.description);     
            optional.put("name", requestData.name);   
            optional.put("snapshotmemory", new Boolean(requestData.snapshotMemory).toString());              
            def response = snapShotServer().createVMSnapshot(requestData.vmId, optional)
            def jobResponse = snapShotServer().vmSnapshotJobResult(response.jobid);   
            Calendar calendar = Calendar.getInstance(); 
            VMSnapshot newVMSnapshot  = new VMSnapshot();
            newVMSnapshot.referenceId = jobResponse.asychronousJobInstanceId
            newVMSnapshot.deleted = false
            newVMSnapshot.name = requestData.name
            newVMSnapshot.description = requestData.description
            newVMSnapshot.billingType =  "hourly" //requestData.billingType
            newVMSnapshot.user = user
            newVMSnapshot.account = user.account
            newVMSnapshot.snapshotMemory = requestData.snapshotMemory
            newVMSnapshot.parentName = "-"
            newVMSnapshot.createdDate = calendar.getTime()
            newVMSnapshot.jobId = response.jobid
            newVMSnapshot.virtualMachine = VirtualMachine.findByReferenceId(requestData.vmId)
            newVMSnapshot.zone = newVMSnapshot.virtualMachine.zone
            newVMSnapshot.save(flush: true, failOnError:true)
            if (newVMSnapshot.hasErrors()) {
                throw new ValidationException(newVMSnapshot.errors.allErrors);
            }
            ArrayList<ArrayList<String>> VMSnapshot = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobid);
            VMSnapshot.add(item);
            log.info("VM snapshot created vm: ${requestData.vmId} and returned job")   
            return VMSnapshot;
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
    def createVMSnapshotJob(String requestBody) {
        try { 
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            def jobResult = "";
            def account = springSecurityService.currentUser.account;
            
            ArrayList<ArrayList<String>> vmSnapshotResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = snapShotServer().vmSnapshotJobResult(requestData.jobId.toString());    
            def vmSnapshot = VMSnapshot.findByJobId(requestData.jobId);
            if(jobResponse.asychronousJobStatus == "0") {
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", requestData.jobId);
                jobResult = "Pending"
                vmSnapshotResult.add(item);
            } else if(jobResponse.asychronousJobStatus == "1") {
                vmSnapshot.referenceId = jobResponse.VMSnapshotId;
                vmSnapshot.state = jobResponse.VMSnapshotState;        
                vmSnapshot.save(flush: true)
                HashMap<String,String> vmSnapItemItem = new HashMap<String,String>(); 
                vmSnapItemItem.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                jobResult = "Success"
                // add volume invoice item
                def vmSnapshotBillableItem
                if(vmSnapshot.billingType == "monthly") { 
                    vmSnapshotBillableItem = BillableItem.get(17) 
                } else {
                    vmSnapshotBillableItem= BillableItem.get(17) 
                }
                if(vmSnapshotBillableItem.enabled == true || vmSnapshotBillableItem.enabled == "true") {
                    def invoice = Invoice.findWhere(account: vmSnapshot.account, status: InvoiceStatus.values()[6])
                    
                    def oldInvoiceitem = InvoiceItem.findWhere(invoice : invoice, referenceItemId: vmSnapshot.virtualMachine.referenceId, billableItem:vmSnapshotBillableItem)
                    
                    if(!oldInvoiceitem) {
                        def invoiceItem = new InvoiceItem()
                        invoiceItem.billableItem = vmSnapshotBillableItem
                        invoiceItem.taxPercent = vmSnapshotBillableItem.tax.percentage
                        invoiceItem.zone = vmSnapshot.zone


                        if(vmSnapshot.billingType == "monthly") {

//                            double monthlyAmount =  MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(4), zone: vmSnapshot.zone).cost * 720.00
//
//                            invoiceItem.usageUnitPrice = Math.ceil(monthlyAmount * 100d) / 100d;   
//                            invoiceItem.usageUnits = 1.0
//                            invoiceItem.usageAmount = Math.ceil(monthlyAmount * 100d) / 100d;   
//                            double taxAmount = (invoiceItem.usageAmount/100)* invoiceItem.taxPercent
//                            invoiceItem.taxAmount = Math.ceil(taxAmount * 100d) / 100d;   
//
//                            double totalAmt =  (invoiceItem.usageAmount + invoiceItem.taxAmount)
//
//                            invoiceItem.totalAmount = Math.ceil(totalAmt * 100d) / 100d;   
                            
                            invoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(4), zone: vmSnapshot.zone).cost
                            
                        } else {
                             invoiceItem.usageUnitPrice = MiscellaneousOfferZoneCost.findWhere(miscellaneousOffer: MiscellaneousOffer.get(4), zone: vmSnapshot.zone).cost
                        }

                        invoiceItem.invoice = invoice
                        invoiceItem.referenceItemName = "VM Snapshot"
                        invoiceItem.referenceItemId = vmSnapshot.virtualMachine.referenceId
                        invoiceItem.save(flush: true); 
                        if (!invoiceItem.save()) {
                            invoiceItem.errors.allErrors.each { Console.print(it) }
                        }
                    }
                }
                vmSnapshotResult.add(vmSnapItemItem);                              
            } else if(jobResponse.asychronousJobStatus == "2") {
                jobResult = "Failed"
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                vmSnapshotResult.add(item); 
            }
            log.info("Job,  VM snapshot created vm: ${jobResult} for vm: ${vmSnapshot.virtualMachine.referenceId}")   
            return vmSnapshotResult; 
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
    def deleteVMSnapshot(String requestBody) {
        try {  
            
            licenseValidationService.authorizeAction(FogPanelService.VM_SNAPSHOT_DELETE)
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            def vmSnapshot = VMSnapshot.get(requestData.vmSnapId)

            def response = snapShotServer().deleteVMSnapshot(vmSnapshot.referenceId)
//            def jobResponse = snapShotServer().vmSnapshotJobResult(response.jobid);   
            vmSnapshot.jobId = response.jobid
            vmSnapshot.save(flush: true)
            
            
            ArrayList<ArrayList<String>> VMSnapshot = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobid);
            VMSnapshot.add(item);
            log.info("VM snapshot deleted vm: ${vmSnapshot.virtualMachine.referenceId} and returned job")   
            return VMSnapshot;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
    def deleteVMSnapshotJob(String requestBody) {
        
        try { 
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            def jobResult = ""        
            ArrayList<ArrayList<String>> vmSnapshotResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = snapShotServer().vmSnapshotJobResult(requestData.jobId.toString());    
            def vmSnapshot = VMSnapshot.findByJobId(requestData.jobId);
            if(jobResponse.asychronousJobStatus == "0") {
                jobResult = "Pending";
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", requestData.jobId);
                vmSnapshotResult.add(item);
            } else if(jobResponse.asychronousJobStatus == "1") {
                jobResult = "Success";
                vmSnapshot.state = jobResponse.VMSnapshotState;   
                vmSnapshot.deleted = true;   
                vmSnapshot.save(flush: true)
                HashMap<String,String> vmSnapItemItem = new HashMap<String,String>(); 
                vmSnapItemItem.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                vmSnapshotResult.add(vmSnapItemItem);                              
            } else if(jobResponse.asychronousJobStatus == "2") {
                jobResult = "Failed";
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                vmSnapshotResult.add(item); 
            }
            log.info("Job,  VM snapshot deleted ${jobResult} for vm: ${vmSnapshot.virtualMachine.referenceId}")   
            return vmSnapshotResult; 
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def revertVMSnapshot(String requestBody) {
        try {  
            
            licenseValidationService.authorizeAction(FogPanelService.VM_SNAPSHOT_REVERT)
            
            // convert string to json object 
            def requestData = JSON.parse(requestBody)
                        
            def vmSnapshot = VMSnapshot.get(requestData.vmSnapId)

            def response = snapShotServer().revertToVMSnapshot(vmSnapshot.referenceId) 

            ArrayList<ArrayList<String>> VMSnapshot = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            item.put("jobId", response.jobId); 
            VMSnapshot.add(item);
            log.info("VM snapshot revert vm: ${vmSnapshot.virtualMachine.referenceId} and returned job")   
            return VMSnapshot;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
    def revertVMSnapshotJob(String requestBody) {
        
        try { 
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            def jobResult = ""     
            ArrayList<ArrayList<String>> vmSnapshotResult = new ArrayList<ArrayList<String>>();  
            def jobResponse = snapShotServer().vmSnapshotJobResult(requestData.jobId.toString());    
        
            if(jobResponse.asychronousJobStatus == "0") {
                jobResult = "Pending"
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.JOB_RESULT_PENDING);
                item.put("jobId", requestData.jobId);
                vmSnapshotResult.add(item);
            } else if(jobResponse.asychronousJobStatus == "1") {
                jobResult = "Success"
                HashMap<String,String> vmSnapItemItem = new HashMap<String,String>(); 
                vmSnapItemItem.put("jobResult", GeneralConstants.RESULT_SUCCESS);
                vmSnapshotResult.add(vmSnapItemItem);                              
            } else if(jobResponse.asychronousJobStatus == "2") {
                jobResult = "Failed"
                HashMap<String,String> item = new HashMap<String,String>()
                item.put("jobResult", GeneralConstants.RESULT_FAILURE);
                item.put("message", jobResponse.errorText);
                vmSnapshotResult.add(item); 
            }
            log.info("Job,  VM snapshot revert ${jobResult} for vm: ${requestData.jobId.toString()}")   
            return vmSnapshotResult; 
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
}
