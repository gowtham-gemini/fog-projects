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
import com.assistanz.fogpanel.ComputingOffer
import javax.ws.rs.DELETE
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import grails.converters.JSON

@Path('/api/computingOffer')
class ComputingOfferResource {
    
    ComputingOfferService computingOfferService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getComputingOfferRepresentation(@QueryParam("clusterReferenceId") String clusterReferenceId, 
        @QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("name") String name, 
        @QueryParam("baseOs") String baseOs, @QueryParam("templateReferenceId") String templateReferenceId, 
        @QueryParam("storageType") String storageType) {
        try { 
            computingOfferService.list(zoneReferenceId, name, templateReferenceId, storageType) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/zone")
    def listByZone(@QueryParam("zoneId") Long zoneId) {
        try { 
            computingOfferService.listByZone(zoneId)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pullFromCloudStack")
    def pullComputeOfferFromCloudStack() {
        try { 
            computingOfferService.pullComputeOfferFromCloudStackJobStart()
            ["OK"] as JSON;
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/cluster")
    def listByCluster(@QueryParam("clusterReferenceId") String clusterReferenceId, @QueryParam("baseOs") String baseOs, @QueryParam("tags") String tags, @QueryParam("type") String type, @QueryParam("templateReferenceId") String templateReferenceId) {
        try { 
            computingOfferService.listByCluster(clusterReferenceId, baseOs, tags, type, templateReferenceId) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
          
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@PathParam("id") String id) {        
        try { 
            computingOfferService.getCurrentOffer(id) as JSON
        } catch (Exception ex){
                [ex] as JSON
        }        
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def count() {
        try {           
            computingOfferService.count() as JSON 
            
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }    
        
    }
    
    @POST
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    def search(@RequestBody String requestBody) {
        
        def requestData = JSON.parse(requestBody)
        def value = requestData.value.toString(); 
        ComputingOffer.findAllByNameIlike('%' + value + '%')as JSON;
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {
             // convert string to json object
            def requestData = JSON.parse(requestBody)
             
            def newComputingOffer = new ComputingOffer();
            newComputingOffer.name = requestData.name
            newComputingOffer.description = requestData.description   
            newComputingOffer.cpuNumber = requestData.cpuNumber
            newComputingOffer.coreCpuSpeed = requestData.cpuSpeed
            newComputingOffer.memory = requestData.memory
            newComputingOffer.bandWidth = requestData.bandWidth
             
            
//            newComputingOffer.defaultUse = requestData.defaultUse
            newComputingOffer.hostTags = requestData.hostTag
            newComputingOffer.diskIO = requestData.diskIO
//            newComputingOffer.isSystem = requestData.isSystem
            newComputingOffer.limitCpuUse = requestData.cpuCap
            newComputingOffer.networkRate = requestData.networkRate
            newComputingOffer.offerHA = requestData.offerHa
            newComputingOffer.storageType = requestData.storageType
            
            if(requestData.storageTag == null || requestData.storageTag == "") {
               newComputingOffer.tags = null
            } else {
                newComputingOffer.tags = requestData.storageTag 
            }
            
            newComputingOffer.diskReadRateBPS = requestData.diskReadRateBPS 
            newComputingOffer.diskWriteRateBPS = requestData.diskWriteRateBPS
            newComputingOffer.diskReadRateIOPS = requestData.diskReadRateIOPS
            newComputingOffer.diskWriteRateIOPS = requestData.diskWriteRateIOPS
                        
            for(int i = 0; i < requestData.zoneCosts.length(); i++) {

                Double cost = new Double(requestData.zoneCosts[i].cost);
                Double setupCost = new Double(requestData.zoneCosts[i].setupCost);
                Double minimumCost = new Double(requestData.zoneCosts[i].minimumCost);
                
                newComputingOffer.addToComputingOfferZoneCosts(new ComputingOfferZoneCost(
                        zone:Zone.get(requestData.zoneCosts[i].zoneId), 
                        cost: cost,
                        setupCost:setupCost,
                        minimumCost:minimumCost))            
            }            
            computingOfferService.create(newComputingOffer) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
           
    }
	
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try {
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            computingOfferService.update(requestData, requestData.name, requestData.description);
                ["OK"]
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }

    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody) 
            
            computingOfferService.delete(String.valueOf(requestData.computingOfferId), requestData.forced) as JSON
                          
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/action")
    @Produces(MediaType.APPLICATION_JSON)
    def disable(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody) 
            
            computingOfferService.disable(String.valueOf(requestData.computingOfferId), requestData.status) as JSON
                          
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }

    
    @PUT
    @Path("/order/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def updateOrder(@RequestBody String requestBody) {
         try {
            
            computingOfferService.updateOrder(requestBody) as JSON;
         
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
}
