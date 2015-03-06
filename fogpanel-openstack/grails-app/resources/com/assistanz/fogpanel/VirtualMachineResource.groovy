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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import grails.converters.deep.JSON

@Path('/api/virtualMachine')
class VirtualMachineResource {
    
    VirtualMachineService virtualMachineService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getVMList(@QueryParam("referenceId") String referenceId) {
        
        virtualMachineService.list(referenceId) as JSON
    }
    
    
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    def getVMInfo(@QueryParam("referenceId") String referenceId) {
        
        virtualMachineService.viewServer(referenceId) as JSON
    }
    
    @GET
    @Path("/listForAttachement")
    @Produces(MediaType.APPLICATION_JSON)
    def volumePageVmList() {
        
        virtualMachineService.vmListForAttachement() as JSON
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def instanceCount() {
        try {
            virtualMachineService.count() as JSON
            
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/getLog")
    @Produces(MediaType.APPLICATION_JSON)
    def getLog(@QueryParam("referenceId") String referenceId) {
        
        virtualMachineService.getLog(referenceId) as JSON
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def createVM(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            
            virtualMachineService.createVM(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/takeVMSnap")
    @Produces(MediaType.APPLICATION_JSON)
    def takeVMSnapshot(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
            virtualMachineService.takeVMSnapshot(requestData)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/start")
    @Produces(MediaType.APPLICATION_JSON)
    def start(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
            virtualMachineService.start(requestData.referenceId)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/stop")
    @Produces(MediaType.APPLICATION_JSON)
    def stop(@RequestBody String requestBody) {
        try {
           // convert string to json object
           def requestData = JSON.parse(requestBody)    
            virtualMachineService.stop(requestData.referenceId)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/actions")
    @Produces(MediaType.APPLICATION_JSON)
    def instanceActions(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
            virtualMachineService.changeInstanceActions(requestData)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/reboot")
    @Produces(MediaType.APPLICATION_JSON)
    def reboot(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
            virtualMachineService.reboot(requestData)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/resize")
    @Produces(MediaType.APPLICATION_JSON)
    def resize(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
            virtualMachineService.resizeInstance(requestData)as JSON;
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
            
            // convert string to json object
            def requestData = JSON.parse(requestBody);
            virtualMachineService.delete(requestData)as JSON;
            } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/rebuild")
    @Produces(MediaType.APPLICATION_JSON)
    def rebuild(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
            
            virtualMachineService.rebuildInstance(requestData)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/attachedVolumes")
    @Produces(MediaType.APPLICATION_JSON)
    def getAttchedVolumes(@QueryParam("serverId") String serverId) {
        
        virtualMachineService.getAttchedVolumeList(serverId) as JSON
    }
    
    @GET
    @Path("/nicList")
    @Produces(MediaType.APPLICATION_JSON)
    def getNicList(@QueryParam("serverId") String serverId) {
        
        virtualMachineService.vmNicList(serverId) as JSON
    }
    
    @GET
    @Path("/pieChartCount")
    @Produces(MediaType.APPLICATION_JSON)   
    def getVMCount() {
        try {  
            virtualMachineService.getVMCount() as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
}
