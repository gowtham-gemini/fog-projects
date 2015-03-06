package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.domain.CSDomainService
import com.assistanz.cloud.cloudstack.domain.DomainResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder
import com.assistanz.fogpanel.PullDomainJob

class DomainService {
    
    def domainServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);
    
        CSDomainService cSDomainService = new CSDomainService(server);
        
        return cSDomainService;
    }
    
    
    
    def list() {
        
        try{
//            def response = domainServer().listDomains();
//            
//            for(Iterator<DomainResponse> iter = response.domains.iterator(); iter.hasNext();) {
//                def data = iter.next();
//                
//                Domain domain = Domain.findByReferenceId(data.domainId);
//                if(!domain) {
//                    domain = new Domain();
//                    domain.referenceId = data.domainId;
//                    domain.hasChild = Boolean.parseBoolean(data.domainHasChild);;
//                    domain.level = Integer.parseInt(data.domainLevel);
//                    domain.name = data.domainName;
//                    domain.networkDomain = data.networkDomain;
//                    domain.parentDomainName = data.parentDomainName;
//                    domain.parentDomainId = data.parentDomainId;
//                    domain.path = data.domainPath;
//                    domain.save(flush: true); 
//                }
//            }
            
            def domainList = Domain.withCriteria {
                ne("referenceId", "dummy")
                order("isDefault", "desc")
            }
            
             return domainList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
        
    def pullDomainFromCloudStackJobStart() {
        
        PullDomainJob.triggerNow([id:"domain"])
            
        return "OK"
    }
    
    
    def pullDomainFromCloudStack(jobid) {
        
        def job = AsynchronousJobs.get(jobid)
        try { 
                        
            job.status = JobStatus.valueOf("RUNNING")
            job.startedAt = new Date()
            job.save(flush: true)

            def response = domainServer().listDomains();
            
            for(Iterator<DomainResponse> iter = response.domains.iterator(); iter.hasNext();) {
                def data = iter.next(); 
                
                Domain domain = Domain.findByReferenceId(data.id);
                if(!domain) {
                    domain = new Domain();
                    domain.referenceId = data.id;
                    domain.hasChild = Boolean.parseBoolean(data.hasChild);;
                    domain.level = Integer.parseInt(data.level);
                    domain.name = data.name;
                    domain.networkDomain = data.networkDomain;
                    domain.parentDomainName = data.parentDomainName;
                    domain.parentDomainId = data.parentDomainId;
                    domain.path = data.path;
                    domain.save(flush: true); 
                } else if(domain) {
                    domain.referenceId = data.id;
                    domain.hasChild = Boolean.parseBoolean(data.hasChild);;
                    domain.level = Integer.parseInt(data.level);
                    domain.name = data.name;
                    domain.networkDomain = data.networkDomain;
                    domain.parentDomainName = data.parentDomainName;
                    domain.parentDomainId = data.parentDomainId;
                    domain.path = data.path;
                    domain.save(flush: true); 
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
    
    def updateDefaultDomain(referenceId) {
        
        def defaultDomain = Domain.findByIsDefault(true)
        defaultDomain?.isDefault = false;
        defaultDomain?.save(flush:true)
        
        def domain = Domain.findByReferenceId(referenceId)
        domain.isDefault = true;
        domain.save(flush:true)
        
        return ["OK"]
        
    }
    
    
}
