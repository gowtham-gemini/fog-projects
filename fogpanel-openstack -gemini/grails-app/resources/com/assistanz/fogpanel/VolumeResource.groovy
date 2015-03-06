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
import grails.converters.deep.JSON;
import org.openstack4j.model.compute.ActionResponse;

@Path('/api/volume')
class VolumeResource {

    VolumeService volumeService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    def getList() {
        volumeService.getList() as JSON
    }
    
    @GET
    @Path("/listForAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    def getListForAdmin(@QueryParam("referenceId") String referenceId) {
        volumeService.getVolumeListForAdmin(referenceId) as JSON
    }
    
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    def volumeView(@QueryParam("referenceId") String referenceId) {
        try {
            volumeService.view(referenceId) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            volumeService.create(requestData) as JSON
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
            println("requestBody"+requestData);
            volumeService.update(requestData) as JSON
        } catch (Exception ex) {
            ex.printStackTrace();
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") String id) {
        try {
            ActionResponse actionResponse = volumeService.delete(id)
            
            def result = "OK";
                        
            if (actionResponse.isSuccess() == false) {
                result = actionResponse.getFault();
                return result
            } else {
                return result;
            } 
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/deleteVolumeByAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteByAdmin(@RequestBody String requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)
            volumeService.deleteByAdminUser(requestData) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def count() {
        try {
            
            volumeService.volumeCount() as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
         
    @GET
    @Path("/availableResource")
    @Produces(MediaType.APPLICATION_JSON)
    def getAvailableResources() {
        volumeService.getAvailableResources() as JSON
    }
    
    @POST
    @Path("/attachedToInstance")
    @Produces(MediaType.APPLICATION_JSON)
    def attachToInstance(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            volumeService.attachToInstance(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/detachVolume")
    @Produces(MediaType.APPLICATION_JSON)
    def detachVolume(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            volumeService.detachVolume(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/detachedVolumeList")
    @Produces(MediaType.APPLICATION_JSON)
    def getDetachedVolumeList() {
        volumeService.detachedVolumeList() as JSON
    }
      
}
