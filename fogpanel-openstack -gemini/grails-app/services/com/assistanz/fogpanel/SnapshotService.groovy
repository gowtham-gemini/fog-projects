package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.cloud.config.ConfigLoader;
import org.openstack4j.model.image.Image;
import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import com.assistanz.openstack.OpenStackServer;
import org.openstack4j.model.storage.block.VolumeSnapshot as OpenstackSnapshot;

@Transactional
class SnapshotService {
    
    OpenStackAuthService openStackAuthService
    
    def springSecurityService;
    InvoiceService invoiceService

    def list(referenceId) {
        
        if(!referenceId) {
            
            OSClient os = openStackAuthService.authenticate();

            List<? extends Image> openstackImages = os.images().list();

            for (Image openstackImage : openstackImages) {

                //            image.getProperties().get("image_location")
                def image = Images.findByReferenceId(openstackImage.getId())

                if(image) {
                    image.size = openstackImage.getSize()
                    image.status = openstackImage.getStatus()

                    image.save(flush: true)
                    if (!image.save()) {
                        image.errors.allErrors.each { println it }
                    }
                }
            }
        }
        
        def user = springSecurityService.currentUser;      
        
        ArrayList<ArrayList<String>> imagesList = new ArrayList<ArrayList<String>>();
          
        def images = Images.withCriteria { 
            eq('deleted', false)
            eq('user', user)
            eq('isVMSnapshot', true)
            if(referenceId) {
                eq('referenceId', referenceId) 
            }
        }
        for (def image : images) {
            
            HashMap item = new HashMap();
            item.put("name", image.name);
            item.put("referenceId", image.referenceId);
            item.put("description", image.description);
            item.put("size", ((image.size)/1073741824));
            item.put("status", image.status);
            item.put("isPublic", image.isPublic);
            item.put("minRam", image.minRam);
            item.put("minDisk", image.minDisk);
            item.put("isProtected", image.isProtected);
            item.put("diskFormat", image.diskFormat);
            item.put("containerFormat", image.containerFormat);
            item.put("checksum", image.checksum);
            item.put("cost", image.cost);
            item.put("user", image.user?.username);
            item.put("account", image.account?.accountIdentifier);
            item.put("vm", image.virtualMachine?.name);
            item.put("vmReferenceId", image.virtualMachine?.referenceId);
            item.put("oneTimeChargeable", image.oneTimeChargeable);                        
            imagesList.add(item)             
        }
        
        return imagesList
        
    }
    
    def deleteVMSnapshot(referenceId) {
        
        OSClient os = openStackAuthService.authenticate();  
        
        os.images().delete(referenceId);
        
        
        def image = Images.findByReferenceId(referenceId)
        image.deletedAt = new Date()
        image.deleted = true
        image.save(flush:true)
        
        ArrayList<ArrayList<String>> deleteVMSnapResponse = new ArrayList<ArrayList<String>>();        
        HashMap<String,String> item = new HashMap<String,String>(); 
        item.put("result", GeneralConstants.RESULT_SUCCESS);
        deleteVMSnapResponse.add(item)

        return deleteVMSnapResponse
    }
    
    def getVolumeSnapshots(referenceId) {
        
        try {
            ArrayList<ArrayList<String>> volumeSnapShotsList = new ArrayList<ArrayList<String>>();        
                        
            def user = springSecurityService.currentUser;
            
            if(!referenceId) {
                
                OSClient os = openStackAuthService.authenticate();  
                
                List<? extends VolumeSnapshot> snapshots = os.blockStorage().snapshots().list();
                
                for(def snapshot : snapshots) {
                    
                    def volumeSnapshot = VolumeSnapshot.findWhere(referenceId: snapshot.getId(), deleted: false, user: user)
                    
                    if(volumeSnapshot) {
                        
                        volumeSnapshot.status = snapshot.getStatus();
                        volumeSnapshot.save(flush: true);
                    }
                } 
            }
            
            def snapList = VolumeSnapshot.withCriteria { 
                eq('deleted', false)
                eq('user', user)
                if(referenceId) {
                    eq('referenceId', referenceId) 
                }
            }
            
            for(def snap: snapList) {
                HashMap<String,String> item = new HashMap<String,String>();
                item.put("referenceId",snap.referenceId);
                item.put("name",snap.name);
                item.put("description",snap.description);
                item.put("status",snap.status);
                item.put("size",snap.size);
                item.put("volumeName",snap.volume.name);
                item.put("volumeType",snap.volume.volumeType);
                volumeSnapShotsList.add(item);
            }
   
            return volumeSnapShotsList;
        } catch(Exception ex) {
            throw ex;
        }
    }
    
    
    def createVolumeSnapshot(requestData) {
        
        def user = springSecurityService.currentUser;
        
        OSClient os = openStackAuthService.authenticate(); 
                
        OpenstackSnapshot snapshot = os.blockStorage().snapshots()
        .create(Builders.volumeSnapshot()
            .name(requestData.name)
            .description(requestData.description)
            .volume(requestData.volumeId)
            .build()
        );
                               
        VolumeSnapshot volumeSnapshot = new VolumeSnapshot()
        volumeSnapshot.referenceId = snapshot.getId()
        volumeSnapshot.description = requestData.description
        volumeSnapshot.name = requestData.name
        volumeSnapshot.createdAt = new Date()
        volumeSnapshot.status = snapshot.getStatus()
        volumeSnapshot.size = snapshot.getSize()
        volumeSnapshot.volume = Volume.findByReferenceId(requestData.volumeId)
        volumeSnapshot.user = user
        volumeSnapshot.zone = volumeSnapshot.volume?.zone
        volumeSnapshot.account = user.account
        volumeSnapshot.save(flush: true)   
        
        def invoice = Invoice.findWhere(account: user.account, status: InvoiceStatus.values()[6]) 
        def price = 0 //MiscellaneousOfferZoneCost.findWhere(zone:volume.zone, miscellaneousOffer:MiscellaneousOffer.get(2))?.cost
        if(!invoice) {
            invoice = invoiceService.createDraftInvoice(user.account) 
        } 
        invoiceService.addInvoiceItem(invoice, BillableItem.get(3), "hourly", volumeSnapshot.name, volumeSnapshot.referenceId, volumeSnapshot.volume.referenceId, price, volumeSnapshot.volume.zone, null)
        
        ArrayList<ArrayList<String>> volumeSnapResponse = new ArrayList<ArrayList<String>>();        
        HashMap<String,String> item = new HashMap<String,String>(); 
        item.put("result", GeneralConstants.RESULT_SUCCESS);
        volumeSnapResponse.add(item)

        return volumeSnapResponse
        
    }
    
    def deleteVolumeSnapshot (referenceId) {
        
        OSClient os = openStackAuthService.authenticate();  
        
        os.blockStorage().snapshots().delete(referenceId);
        
        def volumeSnapshot = VolumeSnapshot.findByReferenceId(referenceId)
        volumeSnapshot.deletedAt = new Date()
        volumeSnapshot.deleted = true
        volumeSnapshot.save(flush:true)
        
        
    }
    
}
