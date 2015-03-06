package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.amazonaws.services.ec2.AmazonEC2;

@Transactional
class VolumeService {

    def springSecurityService    
    AwsAuthService awsAuthService;
    
    def getVolumeList(currentRegionId) {
        try {
            
            def user = springSecurityService.currentUser;                        
            def currentRegion = Region.findById(currentRegionId)
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();     
            
            ArrayList list = new ArrayList()
            if(currentRegion != null) {
                String endPoint = "https://" + currentRegion.getEndPoint();                 
                ec2.setEndpoint(endPoint);
                def volumesResult = ec2.describeVolumes();
                def volumes = volumesResult.getVolumes();     
                for(def awsVolume : volumes) {
                    def volume = Volume.findByReferenceId(awsVolume.getVolumeId());
                    if(volume){
                        volume.status = awsVolume.getState()
                        volume.save(flush:true)
                    } else {
                        //create new
                    }
                }
                
                def volumesList = Volume.findAllByRegionAndDeleted(currentRegion, false)  
                for(def volume : volumesList) {
                    HashMap vmDetails = new HashMap()
                    vmDetails.put("id",volume.id)
                    vmDetails.put("name",volume.name)
                    vmDetails.put("referenceId",volume.referenceId)                    
                    vmDetails.put("description",volume.description)                    
                    vmDetails.put("zone",volume.zone.name)                    
                    vmDetails.put("volumeType",volume.volumeType.name)                    
                    vmDetails.put("status",volume.status)                    
                    vmDetails.put("virtualMachineId",volume.virtualMachine.referenceId)                    
                    vmDetails.put("size",volume.size)                    
                    vmDetails.put("iops",volume.iops)                    
                    list.add(vmDetails)
                }                
            }
            
            return list
        } catch(Exception ex){
            throw ex
        }
        
    }
    
    def view(referenceId) {
        try {
            
            def user = springSecurityService.currentUser;      
            ArrayList<ArrayList<String>> volumeResponse = new ArrayList<ArrayList<String>>();
            
            def volume = Volume.findWhere(referenceId: referenceId, deleted: false);
            
            if(volume != null) {
                HashMap<String,String> item = new HashMap<String,String>();
                item.put("name", volume.name);
                item.put("id", volume.id);
                item.put("referenceId", volume.referenceId);
                item.put("projectName", volume.account.accountIdentifier);
                item.put("description", volume.description);
                item.put("size", volume.size);
                item.put("status", volume.status);
                item.put("volumeType", volume.volumeType?.name);
                item.put("iops", volume.iops);
                item.put("availabilityZone", volume.zone?.name);
                item.put("instanceReferenceId", volume.virtualMachine?.referenceId);
                item.put("instanceName", volume.virtualMachine?.name);
//                item.put("createdOn", DateFormat.getDateTimeInstance().format(volume.createdAt))
                volumeResponse.add(item)
            }                
                
            return volumeResponse;
                
        } catch (Exception ex) {
            throw ex;
        }
    }
}
