package com.assistanz.fogpanel

import com.assistanz.cloud.config.ConfigLoader
import com.assistanz.openstack.OpenStackServer
import org.openstack4j.api.OSClient;
import grails.transaction.Transactional
import com.assistanz.fogpanel.PullZoneJob
import org.openstack4j.model.compute.ext.AvailabilityZone;

@Transactional
class ZoneService {
    
    def getZoneList() {
        
        def list = Zone.findAllWhere(deleted: false)
        
        return list
    }
    
    def pullZoneJobStart() {
        
        PullZoneJob.triggerNow([id:"zone"])
            
        return "OK"
    }

    def pullZoneFromOpenStack(jobid) {

        def job = AsynchronousJobs.get(jobid)
        
        try { 
          
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)

            ConfigLoader configLoader = ConfigLoader.getInstance();

            Properties props = configLoader.getProperties();

            OpenStackServer server = new OpenStackServer(props.get(Config.OPENSTACK_ENDPOINT_URL), props.get(Config.OPENSTACK_ADMIN_UUID), props.get(Config.OPENSTACK_ADMIN_PASSWORD), null);

            OSClient os = server.authenticate();

            List<? extends AvailabilityZone> availabilityzones =  os.compute().zones().list(true);

            HashMap<String,String> openStackZoneList = new HashMap<String,String>();       
            for (AvailabilityZone availabilityzone : availabilityzones) {
                
                if(availabilityzone.getZoneName() == "internal") {
                    
                } else {

                    openStackZoneList.put(availabilityzone.getZoneName(), "zoneName");
                    
                    def zone = Zone.findByName(availabilityzone.getZoneName())
                    if(!zone) {
                        zone = new Zone();
                        zone.name = availabilityzone.getZoneName()
                        zone.available = availabilityzone.getZoneState().getAvailable()

                        zone.createdAt = new Date()
                        zone.save(flush: true)
                        if (!zone.save()) {
                            zone.errors.allErrors.each { println it }
                        }
                    }
                }

            }
            
            def oldZone = Zone.findAllWhere(deleted: false); 
            for(def zone :oldZone ) { 

                boolean blnExists = openStackZoneList.containsKey(zone.name);

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
