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
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.QueryParam
import grails.converters.deep.JSON

@Path('/api/region')
class RegionResource {

    RegionService regionService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    def getRegionList() {
        regionService.getRegionList() as JSON
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def addRegion(@RequestBody String requestBody) {
        try {
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
            
            regionService.addRegion(requestData) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") Long id) {        
        try {
            regionService.deleteRegion(id) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getRegionName (@PathParam("id") String id) {
        try {
            regionService.getRegionName(id) as JSON
            
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @PUT 
    @Path("/update/{id}") 	 
    @Produces(MediaType.APPLICATION_JSON)
    def updateRegion(@RequestBody String requestBody) {     
        def requestData = JSON.parse(requestBody);
        try {  
            regionService.updateRegion(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }  
    
    @GET
    @Path("/pullFromOpenstack")
    @Produces(MediaType.APPLICATION_JSON)
    def pullRegionsFromOpenStack () {
        try {
            regionService.pullRegionJobStart();
            ["OK"] as JSON;
            
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
}
