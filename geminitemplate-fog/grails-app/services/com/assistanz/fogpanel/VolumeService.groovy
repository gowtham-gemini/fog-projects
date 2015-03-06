package com.assistanz.fogpanel

import com.assistanz.cloud.config.ConfigLoader
import com.assistanz.openstack.OpenStackServer
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.storage.block.Volume as OpenStackVolume;
import org.openstack4j.model.compute.VolumeAttachment;
import grails.transaction.Transactional
import grails.converters.deep.JSON;
import java.text.DateFormat;
import org.openstack4j.model.compute.ActionResponse;

@Transactional
class VolumeService {
    def springSecurityService;
    InvoiceService invoiceService
    OpenStackAuthService openStackAuthService

    def getList(referenceId) {
        
        //def list = Volume.findAllWhere(deleted: false)
        try{
            def user = springSecurityService.currentUser;      
            ArrayList<ArrayList<String>> volumeListMinimal = new ArrayList<ArrayList<String>>();
                       
            if(!referenceId) {
   
                OSClient os = openStackAuthService.authenticate(); 

                List<? extends OpenStackVolume> openStackvolumes = os.blockStorage().volumes().list();
                
                    for (OpenStackVolume openStackVolume : openStackvolumes) {

                    def volume = Volume.findWhere(referenceId: openStackVolume.getId(), deleted: false, user: user)

                    if(volume) {

                        volume.status = openStackVolume.getStatus();
                        volume.save(flush: true);
                    }
                }
            }
            
            def volumeList = Volume.withCriteria { 
                eq('deleted', false)
                eq('user', user)
                if(referenceId) {
                    eq('referenceId', referenceId) 
                }
            }
            
            for(def IteratedVolume : volumeList) {
                HashMap<String,String> item = new HashMap<String,String>();
                item.put("name", IteratedVolume.name);
                item.put("id", IteratedVolume.id);
                item.put("referenceId", IteratedVolume.referenceId);
                item.put("projectName", IteratedVolume.account.accountIdentifier);
                item.put("user", user);
                item.put("description", IteratedVolume.description);
                item.put("size", IteratedVolume.size);
                item.put("status", IteratedVolume.status);
                item.put("volumeType", IteratedVolume.volumeType?.name);
                item.put("availabilityZone", IteratedVolume.zone?.name);
                item.put("instanceReferenceId", IteratedVolume.virtualMachine?.referenceId);
                item.put("instanceName", IteratedVolume.virtualMachine?.name);
                item.put("createdOn", DateFormat.getDateTimeInstance().format(IteratedVolume.createdAt) )
                volumeListMinimal.add(item)
            }
            return volumeListMinimal

        } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    
    def volumeCount() {
        try {
            
            ArrayList<ArrayList<String>> volumeCount = new ArrayList<ArrayList<String>>();
                                   
            def totalVolumes = Volume.withCriteria { 
                eq('deleted', false)  
            }.size();
            
            def attachedVolumes = Volume.withCriteria { 
                eq('deleted', false)
                isNotNull("virtualMachine")      
            }.size();
            
            def detachedVolumes = totalVolumes - attachedVolumes;
            
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("totalVolumes",totalVolumes);
            item.put("attachedVolumes",attachedVolumes);
            item.put("detachedVolumes",detachedVolumes);
            volumeCount.add(item)
            
            return volumeCount;
             
        } catch (Exception ex) {
            throw ex;
        }
        
    }
    
    def getVolumeListForAdmin(referenceId) {
        
        try{
                 
            ArrayList<ArrayList<String>> volumeListMinimal = new ArrayList<ArrayList<String>>();
                                   
            def volumeList = Volume.withCriteria { 
                eq('deleted', false)
                if(referenceId) {
                   eq('referenceId', referenceId)  
                }
                
            }
                        
            for(def IteratedVolume : volumeList) {
                HashMap<String,String> item = new HashMap<String,String>();
                item.put("name", IteratedVolume.name);
                item.put("id", IteratedVolume.id);
                item.put("referenceId", IteratedVolume.referenceId);
                item.put("projectName", IteratedVolume.account.accountIdentifier);
                item.put("user", IteratedVolume.user);
                item.put("description", IteratedVolume.description);
                item.put("size", IteratedVolume.size);
                item.put("status", IteratedVolume.status);
                item.put("volumeType", IteratedVolume.volumeType?.name);
                item.put("availabilityZone", IteratedVolume.zone?.name);
                item.put("instanceReferenceId", IteratedVolume.virtualMachine?.referenceId);
                item.put("instanceName", IteratedVolume.virtualMachine?.name );
                item.put("createdOn", DateFormat.getDateTimeInstance().format(IteratedVolume.createdAt) );
                volumeListMinimal.add(item)
            }
            return volumeListMinimal

        } catch (Exception ex) {
            throw ex;
        }
        
    }
    def create(requestData) {
        
        try { 
            
            def user = springSecurityService.currentUser;  

            OSClient os = openStackAuthService.authenticate(); 
            
            OpenStackVolume openStackVolume = null;
            def openStackVT = ""
            
            if(requestData.volumeType == "default" || requestData.volumeType == "" || requestData.volumeType == " " || requestData.volumeType == null || requestData.volumeType == "null") {
                openStackVT = ""
            } else {
                openStackVT = requestData.volumeType
            }
            
            // from existing volume 
            if(requestData.volumeId != null && !requestData.volumeId.empty) {                
                openStackVolume = os.blockStorage().volumes()
                .create(Builders.volume()
                    .name(requestData.name)
                    .description(requestData.description)
                    .size(requestData.size)
                    .volumeType(openStackVT)
                    .source_volid(requestData.volumeId)
                    .bootable(true)
                    .build()
                );
                // from existing image 
            } else if(requestData.imageId != null && !requestData.imageId.empty) {                
                openStackVolume = os.blockStorage().volumes()
                .create(Builders.volume()
                    .name(requestData.name)
                    .description(requestData.description)
                    .size(requestData.size)
                    .volumeType(openStackVT)
                    .imageRef(requestData.imageId)
                    .bootable(true)
                    .build()
                );    
            } else if(requestData.volumeSnapshotId != null && !requestData.volumeSnapshotId.empty) {                  
                openStackVolume = os.blockStorage().volumes()
                .create(Builders.volume()
                    .name(requestData.name)
                    .description(requestData.description)
                    .size(requestData.size)
                    .volumeType(openStackVT)
                    .snapshot(requestData.volumeSnapshotId)
                    .bootable(true)
                    .build()
                );    
            } else {                
                // for empty volume creation     
                openStackVolume = os.blockStorage().volumes()
                .create(Builders.volume()
                    .name(requestData.name)
                    .description(requestData.description)   
                    .size(requestData.size)
                    .volumeType(openStackVT)
                    .bootable(true)
                    .build()
                );
            }
            
            def volumeType = ""
            if(requestData.volumeType == "default" || requestData.volumeType == "" || requestData.volumeType == " " || requestData.volumeType == "null" || requestData.volumeType == null) {
                volumeType = VolumeType.findByReferenceId("default");
            } else {
                volumeType = VolumeType.findByName(openStackVolume.getVolumeType());
            }

            def osvJson = openStackVolume as JSON;
            
            def volume = new Volume()  
            volume.referenceId = openStackVolume.getId()
            volume.name = openStackVolume.getName();
            volume.description = openStackVolume.getDescription();
            volume.status = openStackVolume.getStatus();
            volume.size = openStackVolume.getSize();
            volume.sourceVolumeId = openStackVolume.getSourceVolid();
            volume.image = Images.findById(openStackVolume.getImageRef());
            volume.volumeSnapshot = VolumeSnapshot.findByReferenceId(openStackVolume.getSnapshotId());
            volume.volumeType = volumeType
            volume.zone = Zone.findByName(openStackVolume.getZone());
            volume.billingType = requestData.billingType
            
            volume.createdAt = new Date()   
            volume.account = user.account
            volume.user = user
            volume.save(flush: true)
            
            def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6])
            
            if(!invoice) {
                def price = VolumeTypeZoneCost.findWhere(zone:volume.zone, volumeType:volumeType)?.cost
                invoice = invoiceService.createDraftInvoice(user.account) 
                invoiceService.addInvoiceItem(invoice, BillableItem.get(2), volume.billingType, volume.name, volume.referenceId, volumeType.referenceId, price, volume.zone, null)                
            } else { 
                def cost = VolumeTypeZoneCost.findWhere(zone:volume.zone, volumeType:volumeType)?.cost 
                def price = ""
                if(volume.billingType == "monthly") {
                    price = cost * volume.size * 720
                } else {
                    price = cost
                }
                def invoiceItem = invoiceService.addInvoiceItem(invoice, BillableItem.get(2), volume.billingType, volume.name, volume.referenceId, volumeType.referenceId, price, volume.zone, null)
            }
            
            ArrayList<ArrayList<String>> createResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createResponse.add(item)
            
            return createResponse
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    } 
    def update(requestData) {
        
        try{ 
            def user = springSecurityService.currentUser;  

            OSClient os = openStackAuthService.authenticate(); 
            
            os.blockStorage().volumes().update(requestData.id, requestData.name, requestData.description);
            
            Volume volume = Volume.findByReferenceId(requestData.id);
            volume.name=requestData.name;
            volume.description=requestData.description;
            
            volume.account = user.account
            volume.user = user
            volume.save(flush: true)
            
            ArrayList<ArrayList<String>> createResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            createResponse.add(item)
            
            return createResponse
            
        } catch (Exception ex) {
            throw ex;
        }
    }
    def delete(id) {
        try {
            
            def user = springSecurityService.currentUser;  
            
            OSClient os = openStackAuthService.authenticate(); 
                        
            ActionResponse actionResponse = os.blockStorage().volumes().delete(id);
            
            if(actionResponse.isSuccess() == true) {
            
                def volume = Volume.findByReferenceId(id)
                volume.deleted = true;
                volume.deletedAt = new Date();
                volume.save(flush: true)
                ["OK"]
            } else {
                
                return actionResponse.getFault();
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def deleteByAdminUser(requestData) {
        
        try {
            
            OSClient os = openStackAuthService.authenticate(); 
                        
            os.blockStorage().volumes().delete(requestData.volumeId);
            
            def volume = Volume.findByReferenceId(requestData.volumeId)
            volume.deleted = true;
            volume.deletedAt = new Date();
            volume.save(flush: true)
            
            ArrayList<ArrayList<String>> deleteResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            deleteResponse.add(item)
            
            return deleteResponse;
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def getAvailableResources () {
        
        ArrayList<ArrayList<String>> resourceList = new ArrayList<ArrayList<String>>();
        
        HashMap defaultItem = new HashMap();
        defaultItem.put("value",GeneralConstants.RESOURCE_SELECT_OPTION_VALUE_NO)
        defaultItem.put("name" , GeneralConstants.RESOURCE_VOLUME_EMPTY);
        resourceList.add(defaultItem);
        
        def imageList = Images.findAllWhere(deleted: false);
        
        if(imageList) {
            HashMap imageItem = new HashMap();
            imageItem.put("value", GeneralConstants.RESOURCE_SELECT_OPTION_VALUE_IMAGE)
            imageItem.put("name" , GeneralConstants.RESOURCE_IMAGE);
            resourceList.add(imageItem);
        }
        
        def volumeList = Volume.findAllWhere(deleted: false);
        
        if(volumeList) {

            HashMap volumeItem = new HashMap();
            volumeItem.put("value",GeneralConstants.RESOURCE_SELECT_OPTION_VALUE_VOLUME)
            volumeItem.put("name" , GeneralConstants.RESOURCE_VOLUME);
            resourceList.add(volumeItem);
        }
        
        def volumeSnapshotList = VolumeSnapshot.findAllWhere(deleted: false);
        
        if(volumeSnapshotList) {
            HashMap snpshotItem = new HashMap();
            snpshotItem.put("value",GeneralConstants.RESOURCE_SELECT_OPTION_VALUE_SNAPSHOT)
            snpshotItem.put("name" , GeneralConstants.RESOURCE_SNAPSHOT);
            resourceList.add(snpshotItem);
        }
        
        return resourceList;
        
    }
    
    def attachToInstance(requestData) {
        
        try { 
                         
            def user = springSecurityService.currentUser;  
            
            OSClient os = openStackAuthService.authenticate(); 
            
            println("instanceId"+requestData.instanceId)
            println("volumeId"+requestData.volumeId)
            
            VolumeAttachment volumeAttachment = os.compute().servers().attachVolume(requestData.instanceId , requestData.volumeId);
            
            def virtualMachine = VirtualMachine.findByReferenceId(requestData.instanceId);
            
            def volume = Volume.findByReferenceId(requestData.volumeId);
            volume.virtualMachine = virtualMachine;
            volume.save(flush: true);
            
            ArrayList<ArrayList<String>> attachResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            attachResponse.add(item)
            
            return attachResponse
            
        }  catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }    
        
    def detachVolume(requestData) {
        
        try { 
                         
            def user = springSecurityService.currentUser;  
            
            OSClient os = openStackAuthService.authenticate(); 
            
            os.compute().servers().detachVolume(requestData.instanceId, requestData.volumeId);
            
            def volume = Volume.findByReferenceId(requestData.volumeId);
            volume.virtualMachine = null; 
            volume.save(flush: true);
            
            ArrayList<ArrayList<String>> detachResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            detachResponse.add(item)
            
            return detachResponse
            
        }  catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }  
        
    }
    
    def detachedVolumeList() {
        
        def user = springSecurityService.currentUser;    
        ArrayList<ArrayList<String>> detachedVolumeList = new ArrayList<ArrayList<String>>();
        
        def volumeList = Volume.findAllWhere(deleted: false, user: user, virtualMachine: null)

        for(def IteratedVolume : volumeList) {
            HashMap<String,String> item = new HashMap<String,String>();
            item.put("name", IteratedVolume.name);
            item.put("referenceId", IteratedVolume.referenceId);
            item.put("vm", IteratedVolume.virtualMachine);
            detachedVolumeList.add(item)
        }
        
        return detachedVolumeList
  
    }
}
