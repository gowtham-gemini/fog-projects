package com.assistanz.fogpanel

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.springframework.web.bind.annotation.RequestBody;
import javax.ws.rs.GET
import javax.ws.rs.POST;
import javax.ws.rs.Path
import javax.ws.rs.PUT
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DELETE
import javax.ws.rs.FormParam
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.QueryParam
import grails.converters.deep.JSON
import com.assistanz.fogpanel.Config
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.assistanz.fogpanel.CloudStackException;
@Path('/api/admin/virtualMachine')
class VirtualMachineApiResource {
    VirtualMachineApiService virtulaMachineApiService = new VirtualMachineApiService();
    APIArgumentValidation apiArgumentValidation = new APIArgumentValidation()
    
    @GET
    @Path("/createVM")
    @Produces(MediaType.APPLICATION_JSON)
    def apiCreateVM(@QueryParam("computingOfferReferenceId") String computingofferreferenceid, @QueryParam("firewallId") String firewallid, 
        @QueryParam("templateReferenceId") String templatereferenceid,  @QueryParam("userName") String username,  
        @QueryParam("zoneReferenceId") String zonereferenceid, @QueryParam("billingType") String billingtype) { 
       try {  
           if(computingofferreferenceid && firewallid && templatereferenceid && username && zonereferenceid) {                     
               apiArgumentValidation.resourceTypeValidation(computingofferreferenceid, "computingOffer")              
               apiArgumentValidation.resourceTypeValidation(firewallid, "firewall")               
               apiArgumentValidation.resourceTypeValidation(templatereferenceid, "template")               
               apiArgumentValidation.emailValidation(username)               
               apiArgumentValidation.resourceTypeValidation(zonereferenceid, "zone")
               if(billingtype) {
                  apiArgumentValidation.billingTypeValidation(billingtype) 
               }
               virtulaMachineApiService.apiCreateVM(computingofferreferenceid, firewallid, templatereferenceid, username, zonereferenceid, billingtype)                 
           } else {
                def errorResponse = "";
                if(!computingofferreferenceid) {
                     errorResponse += "{'errorCode':'1000','message':'computingOfferReferenceId required'},"
                } else if(!firewallid) {
                     errorResponse += "{'errorCode':'1000','message':'firewallId required'},"
                } else if(!templatereferenceid) {
                     errorResponse += "{'errorCode':'1000','message':'templateReferenceId required'},"
                } else if(!username) {
                     errorResponse += "{'errorCode':'1000','message':'userName required'},"
                } else if(!zonereferenceid) {
                     errorResponse += "{'errorCode':'1000','message':'zoneReferenceId required'},"
                }
                [errorResponse] as JSON                
            }
        } catch (CloudStackException cloudStackException) {
            throw new CloudStackException(cloudStackException)        
        } catch (RuntimeException ex) {
            [ex.message] as JSON
        } catch (Exception ex) {
            [ex.message] as JSON
        } 
    }
    
    @GET
    @Path("/getVM")
    @Produces(MediaType.APPLICATION_JSON)
    String getJob(@QueryParam("referenceId") String referenceId) {
        try {
            if(referenceId) {
                apiArgumentValidation.resourceTypeValidation(referenceId, "virtualMachine");                
            }            
            virtulaMachineApiService.getVm(referenceId) as JSON
        } catch (CloudStackException cloudStackException) {
            throw new CloudStackException(cloudStackException)        
        } catch (RuntimeException ex) {
            [ex.message] as JSON
        } catch (Exception ex) {
            [ex.message] as JSON
        }                           
    }
}
