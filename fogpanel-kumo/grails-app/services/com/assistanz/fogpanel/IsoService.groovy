package com.assistanz.fogpanel

import com.assistanz.cloud.cloudstack.CloudStackServer
import com.assistanz.cloud.cloudstack.iso.CSIsoService
import com.assistanz.cloud.cloudstack.iso.IsoResponse
import groovy.json.*
import grails.converters.deep.JSON
import org.codehaus.groovy.grails.commons.ApplicationHolder

class IsoService {
    
    def springSecurityService;

    def isoServer() {
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSIsoService csIsoService = new CSIsoService(server);
        
        return csIsoService;
        
    }
    
    
    def list() {

        def user = springSecurityService.currentUser
        def role = springSecurityService.getPrincipal().getAuthorities()
        
        HashMap<String,String> optional = new HashMap<String,String>(); 
        optional.put("listall", "true");
        optional.put("isofilter", "all");
        
        def response = isoServer().listIsos(optional)
        for(Iterator<IsoResponse> iter = response.isos.iterator(); iter.hasNext();) {
            def data = iter.next();           
            Iso iso = Iso.findByIsoReferenceId(data.isoId);
            if (!iso) {   
                iso  = new Iso();
                iso.isoReferenceId = data.isoId;
                iso.name = data.isoName;
                iso.description = data.isoDisplayText;
                iso.extractable = Boolean.parseBoolean(data.isExtractable);                    
                iso.featured = Boolean.parseBoolean(data.isFeatured);
                iso.isPublic = Boolean.parseBoolean(data.isPublic);
                iso.url = "default";
                iso.bootable = data.bootable
                iso.zone = Zone.findByReferenceZoneId(data.isoZoneId);
                if (data.bootable == "true") {
                     def osType = OsType.findByReferenceOsTypeId(data.osTypeId);
                    iso.osType =  osType;
                    iso.osCategory = osType.osCategory;  
                } else if(data.bootable == "false") {
                    iso.osType =  null;
                    iso.osCategory = null;  
                }
                
                iso.deleted = false;     
                iso.save(flush: true);           
            } else if(iso) {
               
                
            }
        }   
        ArrayList<ArrayList<String>> isoList = new ArrayList<ArrayList<String>>(); 
        def result = Iso.findAllWhere(deleted: false);
         for(int i=0; i < result.size(); i++) { 
            def item = result[i]; 
            HashMap<String,String> isoItem = new HashMap<String,String>(); 
            isoItem.put("isoReferenceId", item.isoReferenceId);
            isoItem.put("name", item.name);
            isoItem.put("bootable", item.bootable);
            isoItem.put("description", item.description);
            isoItem.put("extractable", item.extractable);
            isoItem.put("featured", item.featured);
            isoItem.put("isPublic", item.isPublic);
            isoList.add(isoItem);
        } 
        return isoList;
        
//        if(role.iterator().next() == "ROLE_ADMIN" ) {
//              
//        } else if(role.iterator().next() == "ROLE_FREE_USER" ||"ROLE_PAID_USER") {
//               result = Iso.findAllWhere(deleted: false, user: user); 
//        } 
        
    }
    
    def create(requestBody) {
        
         // convert string to json object
        def requestData = JSON.parse(requestBody)  
         
        HashMap<String,String> optional = new HashMap<String,String>();  
        
        optional.put("isfeatured",  new Boolean(requestData.featured).toString());
        optional.put("isextractable", new Boolean(requestData.extractable).toString());
        optional.put("ispublic", new Boolean(requestData.isPublic).toString());
        optional.put("ostypeid", new String(requestData.osType));
        optional.put("bootable", new String(requestData.bootable));
        optional.put("account", new String(requestData.osType));
        optional.put("domainid", new String(requestData.domainid));
                        
        def response = isoServer().registerIso(requestData.displaytext, requestData.name, requestData.url, requestData.zoneReferenceId, optional)
        
    }
    
    
}
