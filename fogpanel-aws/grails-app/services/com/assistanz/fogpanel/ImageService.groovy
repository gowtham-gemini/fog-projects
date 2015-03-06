package com.assistanz.fogpanel

import grails.transaction.Transactional
import com.assistanz.openstack.OpenStackServer;
import grails.converters.JSON
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.net.URL;
import java.util.List;
import com.assistanz.cloud.config.ConfigLoader;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.AmazonEC2;
import com.assistanz.fogpanel.Config;
import java.text.DateFormat;
import com.assistanz.fogpanel.PullImageJob
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Transactional
class ImageService {    
    
    def springSecurityService;
    AwsAuthService awsAuthService;
    
    
    def pullImageFromAws() {
        PullImageJob.triggerNow([id:"image"])  
        return "OK"
    }    
   
    def pullImage(jobid) {
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            //Authentication Process
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();
            //Get list of Images   
            DescribeImagesRequest request = new DescribeImagesRequest();                 
            List<Filter> filters=new LinkedList<Filter>();
            def imageIds = ["ami-dfc39aef", "ami-99bef1a9", "ami-d7450be7"]
            request.withImageIds(imageIds);
          
            DescribeImagesResult imageResult = ec2.describeImages(request);
            HashMap awsImageList = new HashMap()
            if(imageResult != null){                                       
                for(Image awsImage  : imageResult.getImages() ) {
                    if(awsImage != null) {
                        awsImageList.put(awsImage.getImageId(),"referenceId")
                        def existImage = Images.findByReferenceId(awsImage.getImageId());
                        if(existImage) {
                            existImage.name = awsImage.getName()
                            existImage.referenceId = awsImage.getImageId()
                            existImage.architecture = awsImage.getArchitecture()
                            existImage.description = awsImage.getDescription()
                            existImage.rootDeviceType = awsImage.getRootDeviceType()
                            existImage.rootDeviceName = awsImage.getRootDeviceName()
                            existImage.virtualizationType = awsImage.getVirtualizationType()
                            existImage.hypervisor = awsImage.getHypervisor()
                            existImage.state = awsImage.getState()
                            existImage.ownerId = awsImage.getOwnerId()
                            existImage.imageType = awsImage.getImageType()
                            existImage.kernelId = awsImage.getKernelId()
                            existImage.ramdiskId = awsImage.getRamdiskId()
                            existImage.platform = awsImage.getPlatform()
                            existImage.imageOwnerAlias = awsImage.getImageOwnerAlias()
                            existImage.billingType = "monthly"
                            existImage.oneTimeChargeable = false;
                            existImage.save(flush:true)
                            if (!existImage.save()) {
                                existImage.errors.allErrors.each { println it }
                            }
                            
                       } else if(!existImage){
                            def image = new Images()
                            image.name = awsImage.getName()
                            image.referenceId = awsImage.getImageId()
                            image.architecture = awsImage.getArchitecture()
                            image.description = awsImage.getDescription()
                            image.rootDeviceType = awsImage.getRootDeviceType()
                            image.rootDeviceName = awsImage.getRootDeviceName()
                            image.virtualizationType = awsImage.getVirtualizationType()
                            image.hypervisor = awsImage.getHypervisor()
                            image.state = awsImage.getState()
                            image.ownerId = awsImage.getOwnerId()
                            image.imageType = awsImage.getImageType()
                            image.kernelId = awsImage.getKernelId()
                            image.ramdiskId = awsImage.getRamdiskId()
                            image.platform = awsImage.getPlatform()
                            image.imageOwnerAlias = awsImage.getImageOwnerAlias()

                            image.billingType = "monthly"
                            image.oneTimeChargeable = false;
                            image.save(flush: true)
                            if (!image.save()) {
                                image.errors.allErrors.each { println it }
                            }
                        }
                        
                    }
                }
            }
            
            def oldImage = Images.findAllWhere(deleted: false);
            for(def image :oldImage ) { 
                boolean blnExists = awsImageList.containsKey(image.referenceId);
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
            def publicImages = Images.findAllWhere(deleted: false, isPublic: true,);
            def protectedImages = Images.findAllWhere(deleted: false, isPublic:  false);
            
            item.put("totalImages",totalImages.size());
            item.put("publicImages",publicImages.size());
            item.put("protectedImages",protectedImages.size());
            
            imageCount.add(item);
            return imageCount;
            
        }  catch (Exception ex) {
             throw ex;
        }
    }
     
    def view(id) {
        
        try {
            def image = Images.findByReferenceId(id)
            HashMap map = new HashMap()
            map.put("name",image.name)
            map.put("referenceId",image.referenceId)
            map.put("description",image.description)
            map.put("rootDeviceType",image.rootDeviceType)
            map.put("rootDeviceName",image.rootDeviceName)
            map.put("virtualizationType",image.virtualizationType)
            map.put("hypervisor",image.hypervisor)
            map.put("state",image.state)
            map.put("architecture",image.architecture)
            map.put("imageType",image.imageType)
            map.put("platform",image.platform)
            map.put("isPublic",image.isPublic == true ? "Yes" : "No")
            map.put("createdAt",image.createdAt)
            return map as JSON
        } catch(Exception ex) {
            throw ex;
        }
        
    }
    
    def listImages() {
        
        try {
            
            def image = Images.withCriteria{
                eq('deleted',false)
            }.asList() 
        } catch (Exception Ex) {
            throw Ex
        }
        
        
    }
        
}
