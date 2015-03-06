package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.snapshot.CSSnapshotService
import com.assistanz.cloud.cloudstack.snapshot.SnapShotResponse
import com.assistanz.cloud.cloudstack.snapshot.SnapShotPolicyResponse
import com.assistanz.fogpanel.GeneralConstants;
import grails.converters.deep.JSON
import org.codehaus.groovy.grails.commons.ApplicationHolder

class SnapShotPolicyService {
    
    def springSecurityService;
    LicenseValidationService licenseValidationService

    def snapShotServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
        CSSnapshotService csSnapshotService = new CSSnapshotService(server);
        
        return csSnapshotService;
        
    }
    def count(zoneReferenceId) {
        def totalJobs = 0;
        def snapshotPoilicies;
        snapshotPoilicies =  SnapshotPolicy.findAllWhere(deleted: false);
        
        for(int i=0; i < snapshotPoilicies.size(); i++) { 
            def items = snapshotPoilicies[i];  

            if(items.volume.zone.referenceZoneId == zoneReferenceId) {                
                totalJobs = totalJobs + 1;
            } 
             ArrayList countList = new ArrayList(); 
             HashMap countItem = new HashMap();  
             countItem.put("totalJobs", totalJobs);  
             countList.add(countItem);

             return countList;
        }
        
    }
    def getItem(String id) {
        
        try{
            SnapshotPolicy snapshotPolicy = SnapshotPolicy.findBySnapshotPolicyReferenceId(id);
            ArrayList<ArrayList<String>> snapshotPolicyList = new ArrayList<ArrayList<String>>();            
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("snapshotPolicyId", snapshotPolicy.snapshotPolicyReferenceId);
            item.put("maxSnaps", snapshotPolicy.maxSnaps);
            item.put("dayValue", snapshotPolicy.dayValue);
            item.put("name", snapshotPolicy.snapshotName);
            item.put("monthValue", snapshotPolicy.monthValue);
            item.put("timeZoneValue", snapshotPolicy.timeZoneValue);
            item.put("snapshotPolicyIntervalType", snapshotPolicy.intervalType);
            item.put("dayName", snapshotPolicy.dayName);
            item.put("snapshotSchedule", snapshotPolicy.scheduleTime);
            item.put("snapshotPolicyTimeZone", snapshotPolicy.timeZone);
            item.put("volumeName", snapshotPolicy.volume.name);
            item.put("volumeReferenceId", snapshotPolicy.volume.volumeReferenceId);
            snapshotPolicyList.add(item);
            return snapshotPolicyList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }   
        
    }

    def list(String volumeReferenceId) {
        try{
            def user = springSecurityService.currentUser
            def role = springSecurityService.getPrincipal().getAuthorities()
            def result;
            HashMap<String,String> cloudStackSnapJobList = new HashMap<String,String>(); 
            ArrayList<ArrayList<String>> snapShotPoliciesList = new ArrayList<ArrayList<String>>(); 
            def volumeList =  Volume.findAllWhere(deleted: false);
            for(def volume: volumeList) {
                def response = snapShotServer().listSnapshotPolicies(volume.volumeReferenceId, null)
                for(Iterator<SnapShotPolicyResponse> iter = response.snapShotPolicies.iterator(); iter.hasNext();) {
                    def data = iter.next();
                    cloudStackSnapJobList.put(data.snapshotPolicyId ,"referenceId");
                                        
                    SnapshotPolicy snapshotPolicy = SnapshotPolicy.findBySnapshotPolicyReferenceId(data.snapshotPolicyId);
                    if (!snapshotPolicy) {   
                        Volume snapVolume = Volume.findByVolumeReferenceId(data.diskVolumeId);
                        snapshotPolicy  = new SnapshotPolicy();
                        snapshotPolicy.snapshotPolicyReferenceId = data.snapshotPolicyId
                        snapshotPolicy.deleted = false
                        snapshotPolicy.user = snapVolume.user
                        snapshotPolicy.maxSnaps = data.maxSnaps
                        if (data.snapshotPolicyIntervalType== "2") {
                           snapshotPolicy.intervalType = GeneralConstants.INTERVAL_TYPE_WEEKLY
                        } else if (data.snapshotPolicyIntervalType== "1") {
                            snapshotPolicy.intervalType = GeneralConstants.INTERVAL_TYPE_DAILY
                        } else if (data.snapshotPolicyIntervalType== "3") {
                            snapshotPolicy.intervalType = GeneralConstants.INTERVAL_TYPE_MONTHL
                        }
                        snapshotPolicy.scheduleTime = data.snapshotSchedule
                        snapshotPolicy.timeZone = data.snapshotPolicyTimeZone
                        snapshotPolicy.volume = snapVolume
                        snapshotPolicy.save(flush: true);   
                    } 
                }
                def oldSnapjob = SnapshotPolicy.findAllWhere(deleted: false, user: user, volume: volume); 
                for(int vm=0; vm < oldSnapjob.size(); vm++) { 
                    def oldSnapJobtem = oldSnapjob[vm]; 
                    boolean blnExists = cloudStackSnapJobList.containsKey(oldSnapJobtem.snapshotPolicyReferenceId);
                    if(blnExists.toString() == "false" || blnExists == false) {
                       oldSnapJobtem.deleted = true;
                       oldSnapJobtem.save(flush: true); 
                    }
                }
            }
            if(volumeReferenceId == "null" || volumeReferenceId == null) {
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = SnapshotPolicy.findAllWhere(deleted: false, volume: volume); 
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    result = SnapshotPolicy.findAllWhere(deleted: false, user: user); 
                } 
            } else if(volumeReferenceId != "null" || volumeReferenceId != null) {
                Volume volume = Volume.findByVolumeReferenceId(volumeReferenceId);
//                def response = snapShotServer().listSnapshotPolicies(volumeReferenceId, null)
//                for(Iterator<SnapShotPolicyResponse> iter = response.snapShotPolicies.iterator(); iter.hasNext();) {
//                    def data = iter.next();
//                    cloudStackSnapJobList.put(data.snapshotPolicyId ,"referenceId");
//                                        
//                    SnapshotPolicy snapshotPolicy = SnapshotPolicy.findBySnapshotPolicyReferenceId(data.snapshotPolicyId);
//                        if (!snapshotPolicy) {   
//                            Volume snapVolume = Volume.findByVolumeReferenceId(data.diskVolumeId);
//                            snapshotPolicy  = new SnapshotPolicy();
//                            snapshotPolicy.snapshotPolicyReferenceId = data.snapshotPolicyId
//                            snapshotPolicy.deleted = false
//                            snapshotPolicy.user = snapVolume.user
//                            snapshotPolicy.maxSnaps = data.maxSnaps
//                            if (data.snapshotPolicyIntervalType== "2") {
//                               snapshotPolicy.intervalType = GeneralConstants.INTERVAL_TYPE_WEEKLY
//                            } else if (data.snapshotPolicyIntervalType== "1") {
//                                snapshotPolicy.intervalType = GeneralConstants.INTERVAL_TYPE_DAILY
//                            } else if (data.snapshotPolicyIntervalType== "3") {
//                                snapshotPolicy.intervalType = GeneralConstants.INTERVAL_TYPE_MONTHL
//                            }
//                            snapshotPolicy.scheduleTime = data.snapshotSchedule
//                            snapshotPolicy.timeZone = data.snapshotPolicyTimeZone
//                            snapshotPolicy.volume = snapVolume
//                            snapshotPolicy.save(flush: true);   
//                        } 
//                }
                
                
                if(role.iterator().next() == "ROLE_ADMIN" ) {
                    result = SnapshotPolicy.findAllWhere(deleted: false, volume: volume); 
                } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
                    result = SnapshotPolicy.findAllWhere(deleted: false, user: user, volume: volume); 
                }
            }
            for(int i=0; i < result.size(); i++) { 
                def item = result[i]; 
                HashMap<String,String> snapShotPolicyItem = new HashMap<String,String>(); 
                snapShotPolicyItem.put("snapshotPolicyId", item.snapshotPolicyReferenceId);
                snapShotPolicyItem.put("maxSnaps", item.maxSnaps);
                snapShotPolicyItem.put("snapshotPolicyIntervalType", item.intervalType);
                snapShotPolicyItem.put("snapshotSchedule", item.scheduleTime);
                snapShotPolicyItem.put("snapshotPolicyTimeZone", item.timeZone);
                snapShotPolicyItem.put("volumeName", item.volume.name);
                snapShotPolicyItem.put("snapshotName", item.snapshotName);
                snapShotPolicyItem.put("volumeReferenceId", item.volume.volumeReferenceId);
                snapShotPoliciesList.add(snapShotPolicyItem);
            }
            return snapShotPoliciesList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }    
    }
    
    
    def create(requestBody) {
         try {
            
            licenseValidationService.authorizeAction(FogPanelService.SNAPSHOT_POLICY_CREATE)
            
            def user = springSecurityService.currentUser
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
            def snapshotPolicySchedule;
            
            if (requestData.intervaltype == GeneralConstants.INTERVAL_TYPE_DAILY) {
                snapshotPolicySchedule = requestData.scheduleMin + ":" + requestData.scheduleHour
                
            } else if(requestData.intervaltype == GeneralConstants.INTERVAL_TYPE_WEEKLY) {
                snapshotPolicySchedule = requestData.scheduleMin + ":" + requestData.scheduleHour + ":" + requestData.dayValue 
               
            } else if (requestData.intervaltype == GeneralConstants.INTERVAL_TYPE_MONTHLY) {
                snapshotPolicySchedule = requestData.scheduleMin + ":" + requestData.scheduleHour + ":" + requestData.month 
                
            }                       
            SnapshotPolicy.withTransaction {
                def response = snapShotServer().createSnapshotPolicy(requestData.intervaltype ,requestData.maxsnaps, 
                    snapshotPolicySchedule, requestData.timeZone, requestData.volumeId)
                SnapshotPolicy newSnapshotPolicy  = new SnapshotPolicy();
                newSnapshotPolicy.snapshotPolicyReferenceId = response.snapshotPolicyId
                newSnapshotPolicy.deleted = false
                newSnapshotPolicy.user = user
                newSnapshotPolicy.dayValue = requestData.dayValue 
                newSnapshotPolicy.monthValue = requestData.month 
                newSnapshotPolicy.maxSnaps = response.maxsnaps
                newSnapshotPolicy.snapshotName = requestData.snapshotName
                
                newSnapshotPolicy.intervalType = requestData.intervaltype
                newSnapshotPolicy.scheduleTime = requestData.scheduleHour + ":" + requestData.scheduleMin
                newSnapshotPolicy.dayName = requestData.dayName
                newSnapshotPolicy.timeZone = requestData.timeZoneName 
                newSnapshotPolicy.timeZoneValue = requestData.timeZone 
                newSnapshotPolicy.volume = Volume.findByVolumeReferenceId(response.diskVolumeId);
                newSnapshotPolicy.save(flush: true, failOnError:true)
                if (newSnapshotPolicy.hasErrors()) {
                    throw new ValidationException(newSnapshotPolicy.errors.allErrors);
                }
                ArrayList<ArrayList<String>> snapshotPolicy = new ArrayList<ArrayList<String>>();            
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("snapshotPolicyId", newSnapshotPolicy.snapshotPolicyReferenceId);
                item.put("maxSnaps", newSnapshotPolicy.maxSnaps);
                item.put("snapshotName", newSnapshotPolicy.snapshotName);                
                
                item.put("snapshotPolicyIntervalType", newSnapshotPolicy.intervalType);
                item.put("dayName", newSnapshotPolicy.intervalType);
                item.put("snapshotSchedule", newSnapshotPolicy.scheduleTime);
                item.put("snapshotPolicyTimeZone", newSnapshotPolicy.timeZone);
                item.put("volumeName", newSnapshotPolicy.volume.name);
                item.put("volumeReferenceId", newSnapshotPolicy.volume.volumeReferenceId);
                snapshotPolicy.add(item);
                return snapshotPolicy; 
            }
        
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
    def update(requestBody) {
         try {
            
            licenseValidationService.authorizeAction(FogPanelService.SNAPSHOT_POLICY_UPDATE)
            
            def user = springSecurityService.currentUser
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
            def snapshotPolicySchedule;
            SnapshotPolicy oldSnapshotPolicy = SnapshotPolicy.findBySnapshotPolicyReferenceId(requestData.id);
            
            SnapshotPolicy.withTransaction {
                
                if (requestData.intervaltype == GeneralConstants.INTERVAL_TYPE_DAILY) {
                    snapshotPolicySchedule = requestData.scheduleMin + ":" + requestData.scheduleHour

                } else if(requestData.intervaltype == GeneralConstants.INTERVAL_TYPE_WEEKLY) {
                    snapshotPolicySchedule = requestData.scheduleMin + ":" + requestData.scheduleHour + ":" + requestData.dayValue 

                } else if (requestData.intervaltype == GeneralConstants.INTERVAL_TYPE_MONTHLY) {
                    snapshotPolicySchedule = requestData.scheduleMin + ":" + requestData.scheduleHour + ":" + requestData.month 

                }     

                def response = snapShotServer().createSnapshotPolicy(requestData.intervaltype ,requestData.maxsnaps, 
                snapshotPolicySchedule, requestData.timeZone, oldSnapshotPolicy.volume.volumeReferenceId)
                oldSnapshotPolicy.snapshotPolicyReferenceId = response.snapshotPolicyId
                oldSnapshotPolicy.deleted = false
                oldSnapshotPolicy.user = user
                oldSnapshotPolicy.maxSnaps = response.maxsnaps
                oldSnapshotPolicy.dayValue = requestData.dayValue 
                oldSnapshotPolicy.monthValue = requestData.month 
                oldSnapshotPolicy.timeZoneValue = requestData.timeZone 
                oldSnapshotPolicy.intervalType = requestData.intervaltype
                oldSnapshotPolicy.scheduleTime = requestData.scheduleHour + ":" + requestData.scheduleMin
                oldSnapshotPolicy.dayName = requestData.dayName
                oldSnapshotPolicy.timeZone = requestData.timeZoneName 
                oldSnapshotPolicy.save(flush: true, failOnError:true)
                if (oldSnapshotPolicy.hasErrors()) {
                    throw new ValidationException(oldSnapshotPolicy.errors.allErrors);
                }
                ArrayList<ArrayList<String>> snapshotPolicy = new ArrayList<ArrayList<String>>();            
                HashMap<String,String> item = new HashMap<String,String>(); 
                item.put("result", GeneralConstants.RESULT_SUCCESS);
                item.put("snapshotPolicyId", oldSnapshotPolicy.snapshotPolicyReferenceId);
                item.put("maxSnaps", oldSnapshotPolicy.maxSnaps);
                item.put("snapshotPolicyIntervalType", oldSnapshotPolicy.intervalType);
                item.put("dayName", oldSnapshotPolicy.intervalType);
                item.put("snapshotSchedule", oldSnapshotPolicy.scheduleTime);
                item.put("snapshotPolicyTimeZone", oldSnapshotPolicy.timeZone);
                item.put("volumeName", oldSnapshotPolicy.volume.name);
                item.put("volumeReferenceId", oldSnapshotPolicy.volume.volumeReferenceId);
                snapshotPolicy.add(item);
                return snapshotPolicy;                   
            }             
         } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
    }
    
    def delete(String snapshotPolicyId) {
        try {
            
            licenseValidationService.authorizeAction(FogPanelService.SNAPSHOT_POLICY_DELETE)
            
            SnapshotPolicy snapshotPolicy = SnapshotPolicy.findBySnapshotPolicyReferenceId(snapshotPolicyId);
            SnapshotPolicy.withTransaction {
                HashMap<String,String> optional = new HashMap<String,String>(); 
                optional.put("id", snapshotPolicyId);
                def response = snapShotServer().deleteSnapshotPolicies(optional);  
                if (response.success == "true") {
//                    snapshotPolicy.deleted = true                 
                    snapshotPolicy.delete(flush: true, failOnError:true);   
                     return GeneralConstants.RESULT_SUCCESS
                } else {
                    return GeneralConstants.RESULT_FAILURE
                }
            }  
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        } 
    } 
    
    
}
