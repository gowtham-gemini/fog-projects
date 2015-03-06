package com.assistanz.fogpanel

import com.assistanz.cloud.config.ConfigLoader;
import com.assistanz.openstack.OpenStackServer;
import com.assistanz.fogpanel.PullRegionJob
import grails.transaction.Transactional
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
                awsRegionList.put(list.getRegionName(),"regionName")
                def existRegion = Region.findByNameAndDeleted(list.getRegionName(),false)
                if(!existRegion) {
                    def region = new Region()
                    region.name = list.getRegionName()
                    region.endPoint = list.getEndpoint()
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
            
        } catch( Exception Ex) {
            job.status = JobStatus.valueOf("ERROR")
            job.completedAt = new Date()
            job.save(flush: true)
            
            ex.printStackTrace(System.err);
            throw ex;
        }
        
        }
    
    def pullRegionFromOpenStack(jobId) {
        
//        def job = AsynchronousJobs.get(jobId);
//        
//        try { 
//          
//            job.status = JobStatus.valueOf("RUNNING")
//            job.startedAt = new Date()
//            job.save(flush: true)
//
//            ConfigLoader configLoader = ConfigLoader.getInstance();
//
//            Properties props = configLoader.getProperties();
//
//            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);
//
//            OSClient os = server.authenticate();
//            
//            KeystoneTokenV3 token = (KeystoneTokenV3) os.getAccess().getToken();
//            
//            HashMap<String, Boolean> regions = new HashMap<>();
//            for (Catalog catalog : token.getCatalog()) {
//                for (EndpointV3 endpoint : catalog.getEndpoints()) {
//
//                    regions.put(endpoint.getRegion(), Boolean.TRUE);
//                }
//            }
//            
//            HashMap<String,String> openStackRegionList = new HashMap<String,String>();
//            for(String regionName: regions.keySet()) {
//                
//                openStackRegionList.put(regionName,"regionName");
//                
//                def region = Region.findByName(regionName);
//                
//                if(!region) {
//                    def newRegion = new Region();
//                    newRegion.name = regionName;
//                    newRegion.save(flush:true);
//                    if (!newRegion.save()) {
//                        newRegion.errors.allErrors.each { println it }
//                    }
//                }
//                
//            }
//            
//            def oldRegion = Region.findAllWhere(deleted: false); 
//            for(def region :oldRegion ) { 
//
//                boolean blnExists = openStackRegionList.containsKey(region.name);
//
//                println("blnExists"+blnExists)
//
//                if(blnExists.toString() == "false" || blnExists == false) {
//                   region.deleted = true; 
//                   region.save(flush: true); 
//                }
//            }
//       
//            job.status = JobStatus.valueOf("COMPLETED")
//            job.completedAt = new Date()
//            job.save(flush: true)
//            
//        } catch (Exception ex) {
//            
//            job.status = JobStatus.valueOf("ERROR")
//            job.completedAt = new Date()
//            job.save(flush: true)
//            
//            ex.printStackTrace(System.err);
//            throw ex;
//        }
//        
//    }
//    
//    def addRegion(requestData) {
//        
//        try {
//            
//            
//            ArrayList<ArrayList<String>> regionResult = new ArrayList<ArrayList<String>>();       
//            
//            Region region = Region.findWhere(name:requestData.regionName, deleted: false)
//            if(region) {
//                HashMap<String,String> item = new HashMap<String,String>();
//                item.put("result",  "Exists");
//                regionResult.add(item);   
//                 
//                return regionResult;
//                
//            } else {
//                region = new Region();
//                region.name = requestData.regionName;
//                region.save(flush:true);
//            }
//            
//            HashMap<String,String> item = new HashMap<String,String>();
//            item.put("result",  GeneralConstants.RESULT_SUCCESS);
//            regionResult.add(item);   
//                         
//            return regionResult;
//            
//        } catch (Exception ex) {
//            throw ex;   
//        } 
    }
//    
//    def deleteRegion(id) {
//        
//        try {
//            
//            def region = Region.findById(id)
//            region.deleted = true;
//            region.save(flush: true)
//            ["OK"]
//            
//        } catch (Exception ex) {
//            ex.printStackTrace(System.err);
//            throw ex;
//        }
//    }
//    
//    def getRegionName (id) {
//        
//        long regionId = Long.parseLong(id)
//        def region = Region.findWhere(id: regionId, deleted:false);
//        ArrayList<ArrayList<String>> regionArray = new ArrayList<ArrayList<String>>();              
//        HashMap<String,String> item = new HashMap<String,String>();  
//        item.put("regionName",  region.name);
//        regionArray.add(item);
//        
//        return regionArray;
//    }
//    
//    def updateRegion(requestData) {      
//        try{ 
//            
//            ArrayList<ArrayList<String>> regionResult = new ArrayList<ArrayList<String>>();  
//            
//                long regionId = Long.parseLong(requestData.id);
//                def region = Region.findWhere(id: regionId, deleted:false);
//                region.name = requestData.regionName;
//                region.save(flush:true);
//                
//                HashMap<String,String> item = new HashMap<String,String>();
//                item.put("result",  GeneralConstants.RESULT_SUCCESS);
//                regionResult.add(item);   
//
//                return regionResult;
//            
//        }  catch (Exception ex){
//            throw ex;   
//        }
//    }
//    
}
