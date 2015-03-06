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
import grails.converters.deep.JSON


@Path('/api/volume')
class VolumeResource {
    
    VolumeService volumeService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getVolumeRepresentation(@QueryParam("virtualMachineReferenceId") String virtualMachineReferenceId, @QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("name") String name) {
       volumeService.list(virtualMachineReferenceId, zoneReferenceId, name) as JSON
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def count(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("status") String status) {
        try {           
            volumeService.count(zoneReferenceId, status) as JSON 
            
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }    
        
    }
    
    @GET
    @Path("/storageResizeList")
    @Produces(MediaType.APPLICATION_JSON)
    def storageList(@QueryParam("volumeReferenceId") String volumeReferenceId) {
        try {           
            volumeService.getStorageForResize(volumeReferenceId) as JSON 
            
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }    
        
    }
    
    @POST
    @Path("/attach")
    @Produces(MediaType.APPLICATION_JSON)
    def attachVolume(@RequestBody String requestBody) {
        try {
            volumeService.attachVolume(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/resizeVolume")
    @Produces(MediaType.APPLICATION_JSON)
    def resizeVolume(@RequestBody String requestBody) {
        try {
            volumeService.resizeVolume(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/detach")
    @Produces(MediaType.APPLICATION_JSON)
    def detachVolume(@RequestBody String requestBody) {
        try {
            volumeService.detachVolume(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {
            volumeService.create(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/job")
    @Produces(MediaType.APPLICATION_JSON)
    def job(@RequestBody String requestBody) {
        try {
            volumeService.job(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/resizeJob")
    @Produces(MediaType.APPLICATION_JSON)
    def resizeJob(@RequestBody String requestBody) {
        try {
            volumeService.resizeJob(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") String id) {        
         try {
            volumeService.deleteVolume(id)
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }  
    
    @POST
    @Path("/sendEmail")
    @Produces(MediaType.APPLICATION_JSON)
    def sendMail(@RequestBody String requestBody) {   
        try {
            volumeService.sendMail(requestBody) as JSON;
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }        
    }
    
}
