package com.assistanz.fogpanel

import com.assistanz.cloud.config.ConfigLoader;
import com.assistanz.openstack.OpenStackServer;
import com.assistanz.fogpanel.PullRegionJob
import grails.transaction.Transactional
import com.amazonaws.regions.RegionUtils
import com.amazonaws.regions.Regions
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeRegionsRequest
import com.amazonaws.services.ec2.model.DescribeRegionsResult

@Transactional
class RegionService {
    
    def awsAuthService

    def getRegionList() {
        
        def regionList = Region.findAllWhere(deleted: false)
        
        return regionList
    }
    
    def pullRegionJobStart() {
        
        PullRegionJob.triggerNow([id:"region"])
            
        return "OK"
        
    }
    
    def pullRegions(jobId) {
        
        def job = AsynchronousJobs.get(jobId);
        
        try {
            
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();
            DescribeRegionsResult describeResult = ec2.describeRegions()
            
            HashMap awsRegionList = new HashMap()
            
            for(def list in describeResult.getRegions()) {
                def regionName = list.getRegionName();
                awsRegionList.put(regionName,"regionName")
                def existRegion = Region.findByNameAndDeleted(list.getRegionName(),false)
                if(!existRegion) {
                    def region = new Region()
                    region.name = regionName
                    region.endPoint = list.getEndpoint()
                    
                    def aliasName = "";
                    if(regionName.equals("eu-central-1")) {
                        aliasName = "Frankfurt" 
                    } else if (regionName.equals("sa-east-1")) {
                        aliasName = "São Paulo"   
                    } else if (regionName.equals("ap-northeast-1")) {
                        aliasName = "Tokyo"   
                    } else if (regionName.equals("eu-west-1")) {
                        aliasName = "Ireland"   
                    } else if (regionName.equals("us-east-1")) {
                        aliasName = "N. Virginia"   
                    } else if (regionName.equals("us-west-1")) {
                        aliasName = "N. California"  
                    } else if (regionName.equals("us-west-2")) {
                        aliasName = "Oregon"  
                    } else if (regionName.equals("ap-southeast-2")) {
                        aliasName = "Sydney"   
                    } else if (regionName.equals("ap-southeast-1")) {
                        aliasName = "Singapore" 
                    } 
                    
                    region.aliasName = aliasName
                    region.save(flush:true)
                    if (!region.save()) {
                        region.errors.allErrors.each { println it }
                    }
                }
            }
            
            def oldRegion = Region.findAllWhere(deleted: false); 
            for(def region :oldRegion ) { 

                boolean blnExists = awsRegionList.containsKey(region.name);
                if(blnExists.toString() == "false" || blnExists == false) {
                    region.deleted = true; 
                    region.save(flush: true); 
                }
            }
            
            
            job.status = JobStatus.valueOf("COMPLETED")
            job.completedAt = new Date()
            job.save(flush: true)
            
        } catch( Exception ex) {
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
    
 
}
