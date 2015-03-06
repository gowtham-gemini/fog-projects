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
import com.assistanz.fogpanel.DiskOffer
import javax.ws.rs.DELETE
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.QueryParam
import grails.converters.JSON

@Path('/api/diskOffer')
class DiskOfferResource {
    
    DiskOfferService diskOfferService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getDiskOfferRepresentation(@QueryParam("clusterReferenceId") String clusterReferenceId, 
        @QueryParam("name") String name, @QueryParam("zoneReferenceId") String zoneReferenceId,
        @QueryParam("diskOfferReferenceId") String diskOfferReferenceId, @QueryParam("customized") String custom) {
        
        try {            
            diskOfferService.list(clusterReferenceId, zoneReferenceId, name, diskOfferReferenceId, custom) as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def count() {
        try {           
            diskOfferService.count() as JSON 
            
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }         
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pullFromCloudStack")
    def pullComputeOfferFromCloudStack() {
        try { 
            diskOfferService.pullDiskOfferFromCloudStackJobStart()
            ["OK"] as JSON;
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/byComputingOffer")
    @Produces(MediaType.APPLICATION_JSON)
    def getByComputingOffer(@QueryParam("clusterReferenceId") String clusterReferenceId, @QueryParam("tags") String tags,  @QueryParam("customized") Boolean customized, @QueryParam("storageType") String storageType) {
        try {    
            diskOfferService.getByComputingOffer(clusterReferenceId, tags, customized, storageType) as JSON
            
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }         
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/zone")
    def listByZone(@QueryParam("zoneId") Long zoneId) {
        try { 
            diskOfferService.listByZone(zoneId)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/custom")
    def checkCustom(@QueryParam("clusterReferenceId") String clusterReferenceId, @QueryParam("tags") String tags, @QueryParam("storageType") String storageType) {
        try { 
            diskOfferService.checkCustom(clusterReferenceId, tags, storageType) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@PathParam("id") String id) {
        try { 
            diskOfferService.getCurrentDisk(id) as JSON
        } catch (Exception ex){
                [ex] as JSON
        } 
        
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) { 
        try {                   
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            def newDiskOffer = new DiskOffer();
            newDiskOffer.name = requestData.name
            newDiskOffer.description = requestData.description
            newDiskOffer.size = requestData.diskSize
            newDiskOffer.minSize = requestData.minSize
            newDiskOffer.maxSize = requestData.maxSize
            newDiskOffer.storageType = requestData.storageType            
            newDiskOffer.customized = requestData.customDisk
            
            if(requestData.storageTag == null || requestData.storageTag == "") {
               newDiskOffer.tags = null
            } else {
                newDiskOffer.tags = requestData.storageTag
            }
            
            newDiskOffer.qoSType = requestData.qoSType
            
            if(requestData.qoSType == "hypervisor") {
                newDiskOffer.diskReadRateBPS = requestData.diskReadRateBPS
                newDiskOffer.diskWriteRateBPS = requestData.diskWriteRateBPS
                newDiskOffer.diskReadRateIOPS = requestData.diskReadRateIOPS
                newDiskOffer.diskWriteRateIOPS = requestData.diskWriteRateIOPS
            } else if(requestData.qoSType == "storage") {
                
                newDiskOffer.isCustomizedIops = requestData.isCustomizedIops
                if(requestData.isCustomizedIops == false || requestData.isCustomizedIops == "false") {
                    newDiskOffer.minIOPS = requestData.minIOPS
                    newDiskOffer.maxIOPS = requestData.maxIOPS
                }
            }
            newDiskOffer.isPublic = requestData.isPublic
            if(requestData.isPublic == true || requestData.isPublic == "true") {
                
            } else {
                newDiskOffer.domain = Domain.findByReferenceId(requestData.domainId) 
            }
            
            newDiskOffer.hypervisorSnapReserve = requestData.hypervisorSnapReserve
            
            for(int i = 0; i < requestData.zoneCosts.length(); i++) {
          
                Double cost = new Double(requestData.zoneCosts[i].cost);
                Double minimumCost = new Double(requestData.zoneCosts[i].minimumCost);
                newDiskOffer.addToDiskOfferZoneCosts(new DiskOfferZoneCost(
                        zone:Zone.get(requestData.zoneCosts[i].zoneId), 
                        cost: cost,
                        minimumCost:minimumCost))       
            }
           
         diskOfferService.create(newDiskOffer) as JSON;
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
            
            diskOfferService.update(requestData, requestData.name, requestData.description)
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
        try{
            // convert string to json object
            def requestData = JSON.parse(requestBody) 
            
            diskOfferService.delete(String.valueOf(requestData.diskOfferId), requestData.forced) as JSON
            
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
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
            
            diskOfferService.disable(String.valueOf(requestData.computingOfferId), requestData.status) as JSON
                          
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
}
