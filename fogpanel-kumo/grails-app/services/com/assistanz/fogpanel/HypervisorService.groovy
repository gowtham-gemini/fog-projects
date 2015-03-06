package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.hypervisors.CSHypervisorsService
import com.assistanz.cloud.cloudstack.hypervisors.HypervisorResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder

class HypervisorService {
    
    
    def get(String referenceZoneId) {
        try{
            
            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

            CloudStackServer server =
                    new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

            CSHypervisorsService csHypervisorsService = new CSHypervisorsService(server);

            ArrayList<ArrayList<String>> hypervisorList = new ArrayList<ArrayList<String>>();
            def zone = Zone.findByReferenceZoneId(referenceZoneId);
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("zoneid", referenceZoneId);
            
            def response = csHypervisorsService.listHypervisors(optional)
           
            if(referenceZoneId == "-1") {
                for(Iterator<HypervisorResponse> iter = response.hypervisors.iterator(); iter.hasNext();) {
                def data = iter.next();
                    HashMap<String,String> hypervisorItem = new HashMap<String,String>();   
                    hypervisorItem.put("name", data.hypervisorName);
                    hypervisorList.add(hypervisorItem);
                }
                return hypervisorList;
            } 
            for(Iterator<HypervisorResponse> iter = response.hypervisors.iterator(); iter.hasNext();) {
                def data = iter.next();
                Hypervisor hypervisor = Hypervisor.findWhere(name: data.hypervisorName, zone: zone);
                if (!hypervisor) {
                    hypervisor  = new Hypervisor(); 
                    hypervisor.name = data.hypervisorName;   
                    hypervisor.zone = zone;
                    hypervisor.save(flush: true);  
                    if (hypervisor.hasErrors()) {
                        throw new ValidationException(hypervisor.errors.allErrors);
                    }
                }        
            }
            def result = Hypervisor.findAllWhere(zone: zone)
                    
            for(int i=0; i < result.size(); i++) { 
                def item = result[i]; 
                HashMap<String,String> hypervisorItem = new HashMap<String,String>();   
                hypervisorItem.put("name", item.name);
                hypervisorItem.put("id", item.id);
                hypervisorList.add(hypervisorItem);
            }
            return hypervisorList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }

    }
}
