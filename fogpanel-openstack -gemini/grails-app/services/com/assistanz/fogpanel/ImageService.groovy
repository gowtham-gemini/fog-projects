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
import org.openstack4j.api.exceptions.AuthenticationException;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.image.Image;
import org.openstack4j.model.image.ContainerFormat;
import org.openstack4j.model.image.DiskFormat;
import org.openstack4j.model.image.StoreType;
import com.assistanz.cloud.config.ConfigLoader;
import org.openstack4j.model.compute.ActionResponse;
import com.assistanz.fogpanel.Config;
import java.text.DateFormat;
import com.assistanz.fogpanel.PullImageJob

@Transactional
class ImageService {
    
    def springSecurityService;
    OpenStackAuthService openStackAuthService;

    def list(referenceId, userImage) {
        
        ArrayList<ArrayList<String>> imagesList = new ArrayList<ArrayList<String>>();
        def user = springSecurityService.currentUser; 
        
        def images = Images.withCriteria {
            eq('deleted', false)
//            eq('isVMSnapshot', false)
            if(referenceId) {
               eq('referenceId', referenceId) 
            }
            if(userImage) {
               eq('account', user.account) 
                }
            }
        for (def image : images) {
            
            HashMap item = new HashMap();
            item.put("name", image.name);
            item.put("referenceId", image.referenceId);
            item.put("description", image.description);
            item.put("size", ((image.size)/1073741824));
            item.put("sizeInMB", ((image.size)/1048576));
            item.put("status", image.status);
            item.put("isPublic", image.isPublic);
            item.put("minRam", image.minRam);
            item.put("minDisk", image.minDisk);
            item.put("isProtected", image.isProtected);
            item.put("diskFormat", image.diskFormat);
            item.put("containerFormat", image.containerFormat);
            item.put("createdOn", DateFormat.getDateTimeInstance().format(image.createdAt) )
            item.put("checksum", image.checksum);
            item.put("cost", image.cost);
            item.put("isVMSnapshot", image.isVMSnapshot); 
            item.put("oneTimeChargeable", image.oneTimeChargeable);            
            imagesList.add(item)             
        }
        
        return imagesList
        
    } 
    
    def userImageList (referenceId) {
        try {
            
            ArrayList<ArrayList<String>> imagesList = new ArrayList<ArrayList<String>>();
            def user = springSecurityService.currentUser; 

            def images = Images.withCriteria {
                    eq('deleted', false)
//                    eq('isVMSnapshot', false)
                    if(referenceId) {
                       eq('referenceId', referenceId) 
                    }
                    
                }
            for (def image : images) {
                def action = true;
                //image list for current account or public images
                if(image.account == user.account || image.isPublic == true) {
                    
                    if(image.isPublic == true) {
                        action = false;
                    }
                    
                    HashMap item = new HashMap();
                    item.put("name", image.name);
                    item.put("referenceId", image.referenceId);
                    item.put("description", image.description);
                    item.put("size", ((image.size)/1073741824));
                    item.put("sizeInMB", ((image.size)/1048576));
                    item.put("status", image.status);
                    item.put("isPublic", image.isPublic);
                    item.put("minRam", image.minRam);
                    item.put("minDisk", image.minDisk);
                    item.put("isProtected", image.isProtected);
                    item.put("diskFormat", image.diskFormat);
                    item.put("containerFormat", image.containerFormat);
                    item.put("createdOn", DateFormat.getDateTimeInstance().format(image.createdAt) )
                    item.put("checksum", image.checksum);
                    item.put("cost", image.cost);
                    item.put("oneTimeChargeable", image.oneTimeChargeable);            
                    item.put("isVMSnapshot", image.isVMSnapshot);            
                    item.put("action", action);            
                    imagesList.add(item)
                    
                } 

                             
            }

            return imagesList
            
        } catch(Exception e) {
            return e;
        }
        
    }
    
    def pullImageFromOpenstack() {
        
        PullImageJob.triggerNow([id:"image"])
            
        return "OK"
    }
    
def createImages(requestData) {        
    try {             

        OSClient os = openStackAuthService.authenticate();
        def user = springSecurityService.currentUser; 
        
        Payload<URL> payload = null;
        if(requestData.imageSource == "url") {
            payload = Payloads.create(new URL(requestData.location));
        } else if(requestData.imageSource == "file") {
            payload = Payloads.create(new File(requestData.imageFile));
        }            
        Image openstackImage = os.images().create(Builders.image()
            .name(requestData.name)
            .isPublic(requestData.isPublic)
            .diskFormat(DiskFormat.value(requestData.diskFormat))
            .minDisk(requestData.minDisk)
            .minRam(requestData.minRam)
            .build(), payload);        
        def image = Images.findByReferenceId(openstackImage.getId())
        if(!image) {
            def newImage = new Images();
            newImage.name = openstackImage.getName()
            newImage.referenceId = openstackImage.getId()
            newImage.description = requestData.description
            newImage.size = openstackImage.getSize()
            newImage.status = openstackImage.getStatus()
            newImage.minRam = openstackImage.getMinDisk()
            newImage.minDisk = openstackImage.getMinRam()
            newImage.createdAt = openstackImage.getCreatedAt()
            newImage.containerFormat = openstackImage.getContainerFormat()
            newImage.diskFormat = openstackImage.getDiskFormat()
            newImage.isProtected = openstackImage.isProtected()
            newImage.isPublic = openstackImage.isPublic()
            newImage.checksum = openstackImage.getChecksum();
            //condtion cheched , If user creating image 
            if(requestData.userImage) {
                newImage.account = user.account;
            }
            newImage.billingType = "monthly"
            if(requestData.cost == "" || requestData.cost == null || requestData.cost == "null") {
                newImage.cost = 0;  
            } else {
                newImage.cost = requestData.cost;   
            }
            newImage.oneTimeChargeable = requestData.isOneTimeChargable;
            newImage.checksum = openstackImage.getChecksum()
            newImage.save(flush: true);
            if (!newImage.save()) {
                newImage.errors.allErrors.each { println it }
            }
        } else if(image) {
            image.name = openstackImage.getName()
            image.referenceId = openstackImage.getId()
            image.description = openstackImage.getName()
            image.size = openstackImage.getSize()
            image.status = openstackImage.getStatus()
            image.minRam = openstackImage.getMinDisk()
            image.minDisk = openstackImage.getMinRam()
            image.createdAt = openstackImage.getCreatedAt()
            image.containerFormat = openstackImage.getContainerFormat()
            image.diskFormat = openstackImage.getDiskFormat()
            image.isProtected = openstackImage.isProtected()
            image.isPublic = openstackImage.isPublic()
            image.checksum = openstackImage.getChecksum()
            if(requestData.cost == "" || requestData.cost == null || requestData.cost == "null") {
                image.cost = 0;  
            } else {
                image.cost = requestData.cost;   
            }
            image.oneTimeChargeable = requestData.isOneTimeChargable;
            image.save(flush: true)
        }                                                              
        ArrayList<ArrayList<String>> createImageResponse = new ArrayList<ArrayList<String>>();        
        HashMap<String,String> item = new HashMap<String,String>(); 
        item.put("result", GeneralConstants.RESULT_SUCCESS);
        createImageResponse.add(item)
        return createImageResponse
    } catch (Exception ex) {
        throw ex;
    }
}
    
    def updateImage(requestData) {
        
        try { 
            
            def user = springSecurityService.currentUser; 
            OSClient os = openStackAuthService.authenticate();
                     
            os.images().update(Builders.image().id(requestData.id).name(requestData.name).isPublic(requestData.isPublic)
                .isProtected(requestData.isProtected)
                .build());
            
            def image = Images.findByReferenceId(requestData.id)
            image.name = requestData.name;  
            image.description = requestData.description;
            image.isPublic = requestData.isPublic;
            image.isProtected = requestData.isProtected;
            
            if(requestData.userImage) {
               image.account = user.account; 
            }
            
            image.billingType = "monthly"
            Double cost = Math.round(new Double(requestData.cost)*100000)/100000;               
            
            if(cost == 0 || requestData.cost == "" || requestData.cost == null || requestData.cost == "null") {
                image.cost = 0;                 
            } else {
                image.cost = cost;                  
            }
            image.oneTimeChargeable = requestData.isOneTimeChargable;            
            image.save(flush: true);
            if (image.hasErrors()) {
                throw new ValidationException(image.errors.allErrors);
            } 
            ArrayList<ArrayList<String>> updateImageResponse = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>(); 
            item.put("result", GeneralConstants.RESULT_SUCCESS);
            updateImageResponse.add(item)

            return updateImageResponse
            
        } catch (Exception ex) {
               throw ex;
        }
    }
    
    def deleteCurrentImage(id) {
        try {
            
            OSClient os = openStackAuthService.authenticate();
                        
            ActionResponse actionResponse = os.images().delete(id);
            
            if(actionResponse.isSuccess() == true) {
            
                def image = Images.findByReferenceId(id)
                image.deleted = true;
                image.deletedAt = new Date();
                image.save(flush: true)
                if (!image.save()) {
                    image.errors.allErrors.each { println it }
                }
                ["OK"]
            } else {
                return actionResponse.getFault();
            }    
            
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def pullImage(jobid) {
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)

            ConfigLoader configLoader = ConfigLoader.getInstance();

            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();

            List<? extends Image> images = os.images().list();
            
            HashMap<String,String> openStackImageList = new HashMap<String,String>();

            for (Image openstackImage : images) {

    //            image.getProperties().get("image_location")
    
                openStackImageList.put(openstackImage.getId(),"referenceId");
                def image = Images.findByReferenceId(openstackImage.getId())

                if(!image && openstackImage.getProperties().get("image_location") != "snapshot") {
                    image = new Images()  
                    image.name = openstackImage.getName()
                    image.referenceId = openstackImage.getId()
                    image.description = openstackImage.getName()
                    image.size = openstackImage.getSize()
                    image.status = openstackImage.getStatus()
                    image.minRam = openstackImage.getMinDisk()
                    image.minDisk = openstackImage.getMinRam()
                    image.createdAt = openstackImage.getCreatedAt()
                    image.containerFormat = openstackImage.getContainerFormat()
                    image.diskFormat = openstackImage.getDiskFormat()
                    image.isProtected = openstackImage.isProtected()
                    image.isPublic = openstackImage.isPublic()
                    image.checksum = openstackImage.getChecksum()
                    image.billingType = "monthly"
                    image.oneTimeChargeable = false;
                    image.save(flush: true)
                    if (!image.save()) {
                      image.errors.allErrors.each { println it }
                    }
                } else if(image && openstackImage.getProperties().get("image_location") != "snapshot") {
                    image.name = openstackImage.getName()
                    image.referenceId = openstackImage.getId()
                    image.description = openstackImage.getName()
                    image.size = openstackImage.getSize()
                    image.status = openstackImage.getStatus()
                    image.minRam = openstackImage.getMinDisk()
                    image.minDisk = openstackImage.getMinRam()
                    image.createdAt = openstackImage.getCreatedAt()
                    image.containerFormat = openstackImage.getContainerFormat()
                    image.diskFormat = openstackImage.getDiskFormat()
                    image.isProtected = openstackImage.isProtected()
                    image.isPublic = openstackImage.isPublic()
                    image.checksum = openstackImage.getChecksum()
                    image.oneTimeChargeable = false;
                    image.billingType = "monthly"
                    image.save(flush: true)
                    if (!image.save()) {
                      image.errors.allErrors.each { println it }
                    }
                }
            }
            
            def oldImage = Images.findAllWhere(deleted: false, isVMSnapshot:false); 
            for(def image :oldImage ) { 

                boolean blnExists = openStackImageList.containsKey(image.referenceId);

                println("blnExists"+blnExists)

                if(blnExists.toString() == "false" || blnExists == false) {
                   image.deleted = true; 
                   image.deletedAt = new Date(); 
                   image.save(flush: true); 
                   if (!image.save()) {
                      image.errors.allErrors.each { println it }
                    }
                }
            }
            
            job.status = JobStatus.valueOf("COMPLETED")
            job.completedAt = new Date()
            job.save(flush: true)
            
        } catch (Exception ex) {
            
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
    def imageCount() {   
        try {
            
            ArrayList<ArrayList<String>> imageCount = new ArrayList<ArrayList<String>>();        
            HashMap<String,String> item = new HashMap<String,String>();
            
            def totalImages = Images.findAllWhere(deleted: false);
            def publicImages = Images.findAllWhere(deleted: false, isPublic: true, isProtected: false);
            def protectedImages = Images.findAllWhere(deleted: false, isPublic:  false, isProtected: true);
            
            item.put("totalImages",totalImages.size());
            item.put("publicImages",publicImages.size());
            item.put("protectedImages",protectedImages.size());
            
            imageCount.add(item);
            return imageCount;
            
        }  catch (Exception ex) {
             throw ex;
        }
    }
    
        
}
