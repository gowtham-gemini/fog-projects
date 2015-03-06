package com.assistanz.fogpanel

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
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.QueryParam
import grails.converters.JSON

@Path('/api/cloudMaintenance')
class CloudMaintenanceResource {

    CloudMaintenanceService cloudMaintenanceService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getCloudMaintenanceRepresentation() {
        cloudMaintenanceService.list()as JSON
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def add(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            cloudMaintenanceService.add(requestData) as JSON            
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }           
    }
    
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            cloudMaintenanceService.update(requestData) as JSON            
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }  
    }
    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@RequestBody String requestBody) {
        try{
            // convert string to json object
            def requestData = JSON.parse(requestBody)             
            cloudMaintenanceService.delete(requestData) as JSON
            
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
        
    }
   
    
}
