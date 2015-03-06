package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.domain.CSDomainService
import com.assistanz.cloud.cloudstack.domain.DomainResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder

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
            def response = domainServer().listDomains();
            
            for(Iterator<DomainResponse> iter = response.domains.iterator(); iter.hasNext();) {
                def data = iter.next();
                
                Domain domain = Domain.findByReferenceId(data.domainId);
                if(!domain) {
                    domain = new Domain();
                    domain.referenceId = data.domainId;
                    domain.hasChild = Boolean.parseBoolean(data.domainHasChild);;
                    domain.level = Integer.parseInt(data.domainLevel);
                    domain.name = data.domainName;
                    domain.networkDomain = data.networkDomain;
                    domain.parentDomainName = data.parentDomainName;
                    domain.parentDomainId = data.parentDomainId;
                    domain.path = data.domainPath;
                    domain.save(flush: true); 
                }
            }
             return Domain.findAll();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
        
    }
}
