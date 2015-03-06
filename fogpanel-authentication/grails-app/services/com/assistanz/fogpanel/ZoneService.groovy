package com.assistanz.fogpanel

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2AsyncClient;
import com.amazonaws.services.ec2.model.AvailabilityZone;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesRequest;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import com.amazonaws.services.ec2.model.Region;

import grails.transaction.Transactional



@Transactional
class ZoneService {
    
    AwsAuthService awsAuthService;
    
    def getZoneList() {
        
        def list = Zone.findAllWhere(deleted: false)
        
        return list
    }
    
    def pullZoneJobStart() {     
        
        PullZoneJob.triggerNow([id:"zone"])
            
        return "OK"
    }

    def pullZoneFromAws(jobid) {

        def job = AsynchronousJobs.get(jobid)
        
        try { 
          
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)
            
            AmazonEC2 ec2 = awsAuthService.authenticateEC2();
            HashMap<String,String> awsZoneList = new HashMap<String,String>();
            DescribeRegionsResult describeRegionResult = ec2.describeRegions();        
            List<Region> regions = describeRegionResult.getRegions();                
            for(Region region  : regions){
                
                def endPoint = "https://" +region.getEndpoint();                 
                ec2.setEndpoint(endPoint);
                
                DescribeAvailabilityZonesResult availabilityZonesResult = ec2.describeAvailabilityZones();
                List<AvailabilityZone> awsAvailabilityZones = availabilityZonesResult.getAvailabilityZones();
                for(AvailabilityZone awsZone : awsAvailabilityZones) {
                    if(awsZone != null) {
                        awsZoneList.put(awsZone.getZoneName(), "zoneName");

                        def zone = Zone.findByName(awsZone.getZoneName())
                        if(!zone) {
                            zone = new Zone();
                            zone.name = awsZone.getZoneName()
                            zone.regionName = awsZone.getRegionName()
                            if(awsZone.getState() == "available") {
                                zone.available = true;
                            }                            

                            zone.createdAt = new Date()
                            zone.save(flush: true)
                            if (!zone.save()) {
                                zone.errors.allErrors.each { println it }
                            }
                        }                                              
                    }            
                }
                                
            }               
            
            def dbZones = Zone.findAllWhere(deleted: false); 
            for(def zone :dbZones) { 

                boolean blnExists = awsZoneList.containsKey(zone.name);

                if(blnExists.toString() == "false" || blnExists == false) {
                   zone.deleted = true; 
                   zone.deletedAt =  new Date();
                   zone.save(flush: true); 
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
}
