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

@Path('/api/snapShot')
class SnapShotResource {
    
    SnapShotService snapShotService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getSnapShotRepresentation(@QueryParam("volumeReferenceId") String volumeReferenceId, @QueryParam("name") String name) {
        snapShotService.list(volumeReferenceId, name) as JSON
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def count(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("status") String status) {
        try {           
            snapShotService.count(zoneReferenceId, status) as JSON 
            
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }    
        
    }
    
    
    @GET
    @Path("/listVMSnapshot")
    @Produces(MediaType.APPLICATION_JSON) 
    def listVMSnapshot(@QueryParam("vmId") String vmId, @QueryParam("zoneReferenceId") String zoneReferenceId) {
        snapShotService.listVMSnapshot(vmId, zoneReferenceId) as JSON
    }
        
    
    @POST
    @Path("/createVMSnapshot")
    @Produces(MediaType.APPLICATION_JSON)
    def createVMSnapshot(@RequestBody String requestBody) {
        try {
            snapShotService.createVMSnapshot(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/deleteVMSnapshot")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteVMSnapshot(@RequestBody String requestBody) {
        try {
            snapShotService.deleteVMSnapshot(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/deleteVMSnapshotJob")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteVMSnapshotJob(@RequestBody String requestBody) {
        try {
            snapShotService.deleteVMSnapshotJob(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/revertVMSnapshot")
    @Produces(MediaType.APPLICATION_JSON)
    def revertVMSnapshot(@RequestBody String requestBody) {
        try {
            snapShotService.revertVMSnapshot(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/revertVMSnapshotJob")
    @Produces(MediaType.APPLICATION_JSON)
    def revertVMSnapshotJob(@RequestBody String requestBody) {
        try {
            snapShotService.revertVMSnapshotJob(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/createVMSnapshotJob")
    @Produces(MediaType.APPLICATION_JSON)
    def createVMSnapshotJob(@RequestBody String requestBody) {
        try {
            snapShotService.createVMSnapshotJob(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {
            snapShotService.create(requestBody) as JSON
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
            snapShotService.job(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/deleteJob")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteJob(@RequestBody String requestBody) {
        try {
            snapShotService.deleteJob(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/createVolume")
    @Produces(MediaType.APPLICATION_JSON)
    def createVolume(@RequestBody String requestBody) {
        try {
            snapShotService.createVolume(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/createTemplate")
    @Produces(MediaType.APPLICATION_JSON)
    def createTemplate(@RequestBody String requestBody) {
        try {
            snapShotService.createTemplate(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@RequestBody String requestBody) {        
        try {
            snapShotService.delete(requestBody)
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
            snapShotService.sendMail(requestBody) as JSON;
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }        
    }
    
    
}
