package com.assistanz.fogpanel


import com.assistanz.cloud.config.ConfigLoader
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.fogpanel.PullDomainJob
import com.assistanz.openstack.OpenStackServer


class DomainService {
//    
//    
//    def list() {
//        
//        try{
////            def response = domainServer().listDomains();
////            
////            for(Iterator<DomainResponse> iter = response.domains.iterator(); iter.hasNext();) {
////                def data = iter.next();
////                
////                Domain domain = Domain.findByReferenceId(data.domainId);
////                if(!domain) {
////                    domain = new Domain();
////                    domain.referenceId = data.domainId;
////                    domain.hasChild = Boolean.parseBoolean(data.domainHasChild);;
////                    domain.level = Integer.parseInt(data.domainLevel);
////                    domain.name = data.domainName;
////                    domain.networkDomain = data.networkDomain;
////                    domain.parentDomainName = data.parentDomainName;
////                    domain.parentDomainId = data.parentDomainId;
////                    domain.path = data.domainPath;
////                    domain.save(flush: true); 
////                }
////            }
//            
//            def domainList = Domain.withCriteria {
//                ne("referenceId", "dummy")
//                eq("enabled", true)
//                eq("deleted", false)
//                //order("isDefault", "desc")
//            }
//            
//             return domainList;
//        } catch (Exception ex) {
//            ex.printStackTrace(System.err);
//            throw ex;
//        }
//        
//    }
//        
//    def pullFromOpenstackJobStart() {
//        
//        PullDomainJob.triggerNow([id:"domain"])
//            
//        return "OK"
//    }
//    
//    
//    def pullFromOpenstack(jobid) {
//        
//        def job = AsynchronousJobs.get(jobid)
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
////            OSClient os = server.authenticate();
//
////            List<? extends OSDomain> domains = os.identity().domains().list();
////            HashMap<String,String> openStackDomainList = new HashMap<String,String>();
//            
////            for (OSDomain openstackDomain : domains) {
////                
////                openStackDomainList.put(openstackDomain.id,"referenceId");
////                
////                Domain domain = Domain.findByReferenceId(openstackDomain.id);
////                
////                if(!domain) {
////                    
////                    domain = new Domain();
////                    domain.referenceId = openstackDomain.id;
////                    domain.name = openstackDomain.name;
////                    domain.description = openstackDomain.description;
////                    domain.enabled = openstackDomain.enabled
////                    
////                    if (!domain.save(flush: true)) {
////                        domain.errors.allErrors.each { println it }
////                    }
////                } else {
////                   
////                    domain.enabled = openstackDomain.enabled
////                    domain.name = openstackDomain.name;
////                    domain.description = openstackDomain.description;
////                    
////                    if (!domain.save(flush: true)) {
////                        domain.errors.allErrors.each { println it }
////                    }
////                }
////            }
//            
////            def oldDomains = Domain.findAllWhere(deleted: false); 
////            for(def domain : oldDomains ) { 
////
////                boolean blnExists = openStackDomainList.containsKey(domain.referenceId);
////
////
////                if(blnExists.toString() == "false" || blnExists == false) {
////                   domain.deleted = true; 
////                   domain.deletedAt = new Date(); 
////                   domain.save(flush: true); 
////                }
////            }
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
//    def updateDefaultDomain(referenceId) {
//        
//        def defaultDomain = Domain.findByIsDefault(true)
//        defaultDomain?.isDefault = false;
//        defaultDomain?.save(flush:true)
//        
//        def domain = Domain.findByReferenceId(referenceId)
//        domain.isDefault = true;
//        domain.save(flush:true)
//        
//        return ["OK"]
//        
//    }
//    
    
}
