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



@Path('/api/virtualMachine')
class VirtualMachineResource {
    
    VirtualMachineService virtualMachineService
    VmUsageService vmUsageService
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getVirtualMachineRepresentation(@QueryParam("volumeReferenceId") String volumeReferenceId, @QueryParam("securityGroupReferenceId") String securityGroupReferenceId) {
       virtualMachineService.list(volumeReferenceId, securityGroupReferenceId) as JSON
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def count(@QueryParam("accountId") Long accountId, @QueryParam("status") String status, @QueryParam("zoneReferenceId") String zoneReferenceId) {
        try {           
            virtualMachineService.count(accountId, status, zoneReferenceId) as JSON 
            
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }    
        
    }
    
    @GET
    @Path("/count/byZone")
    @Produces(MediaType.APPLICATION_JSON)
    def userResourceCount(@QueryParam("accountId") Long accountId, @QueryParam("status") String status, @QueryParam("zoneReferenceId") String zoneReferenceId) {
        try {           
            virtualMachineService.userResourceCount(accountId, status, zoneReferenceId) as JSON 
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }  
    }
    
    @GET
    @Path("/byDisk")
    @Produces(MediaType.APPLICATION_JSON)
    def byDisk(@QueryParam("volumeReferenceId") String volumeReferenceId) {
        try {           
            virtualMachineService.byDisk(volumeReferenceId) as JSON             
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }    
        
    }
    
    
    @GET
    @Path("/getHost")
    @Produces(MediaType.APPLICATION_JSON)
    def getHost(@QueryParam("vmId") String vmId) {
        try {           
            virtualMachineService.getHost(vmId) as JSON             
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }    
        
    }
    
    @POST
    @Path("/migrate")
    @Produces(MediaType.APPLICATION_JSON)
    def migrateInstance(@RequestBody String requestBody) {
        try {
            virtualMachineService.migrateInstance(requestBody)as JSON
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
    @Path("/migrate/job")
    @Produces(MediaType.APPLICATION_JSON)
    def migrateVmJob(@RequestBody String requestBody) {
        try {
            virtualMachineService.migrateVmJob(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {
            virtualMachineService.create(requestBody)as JSON
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
    @Path("/console")
    @Produces(MediaType.APPLICATION_JSON)
    def console(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
            virtualMachineService.console(requestData.referenceId)as JSON;
           
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getItem(@PathParam("id") String id) {
        try {
           virtualMachineService.getInstance(id) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/byVMId")
    @Produces(MediaType.APPLICATION_JSON)
    def getVM(@QueryParam("vmId") Long vmId) {
        virtualMachineService.getByVMId(vmId)
    }
    
    @POST
    @Path("/stop")
    @Produces(MediaType.APPLICATION_JSON)
    def stop(@RequestBody String requestBody) {
        try {
           // convert string to json object
           def requestData = JSON.parse(requestBody)    
           String forced = new Boolean(requestData.forced).toString();
           virtualMachineService.stop(requestData.referenceId, forced)as JSON;
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
            virtualMachineService.reboot(requestData.referenceId)as JSON;
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
            def resul = virtualMachineService.delete(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/changeService")
    @Produces(MediaType.APPLICATION_JSON)
    def changeServiceOffering(@RequestBody String requestBody) {
        try {
            virtualMachineService.changeServiceOffering(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/listVolume")
    @Produces(MediaType.APPLICATION_JSON)
    def listVolume(@RequestBody String requestBody) {
        try {
            virtualMachineService.listVolume(requestBody) as JSON            
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/getPassword/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getPassword(@PathParam("id") String id) {
        try {                        
            virtualMachineService.getPassword(id)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/resetPassword")
    @Produces(MediaType.APPLICATION_JSON)
    def resetPassword(@RequestBody String requestBody) {
        try {
            virtualMachineService.resetPassword(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/attach")
    @Produces(MediaType.APPLICATION_JSON)
    def attachIso(@RequestBody String requestBody) {
        try {
            virtualMachineService.attachIso(requestBody) as JSON          
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try {  
            virtualMachineService.update(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex){
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
    def detachIso(@RequestBody String requestBody) {
        try {
            virtualMachineService.detachIso(requestBody) as JSON     
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/restore")
    @Produces(MediaType.APPLICATION_JSON)
    def restoreInstance(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody) 
            virtualMachineService.restoreInstance(requestData.referenceId) as JSON     
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/reset")
    @Produces(MediaType.APPLICATION_JSON)
    def resetInstance(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody) 
            virtualMachineService.resetInstance(requestData.referenceId) as JSON     
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/reset/job")
    @Produces(MediaType.APPLICATION_JSON)
    def resetVMJob(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody) 
            virtualMachineService.resetVMJob(requestData.jobId) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/job")
    @Produces(MediaType.APPLICATION_JSON)
    def job(@RequestBody String requestBody) {
        try {
            virtualMachineService.job(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/job/attachIso")
    @Produces(MediaType.APPLICATION_JSON)
    def attachIsoJob(@RequestBody String requestBody) {
        try {
            virtualMachineService.attachIsoJob(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/createVm/job")
    @Produces(MediaType.APPLICATION_JSON)
    def createVmJob(@RequestBody String requestBody) {
        try {
            virtualMachineService.createVmJob(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/usage/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getData(@PathParam("id") String id) {
        try {
            vmUsageService.getData(id) as JSON
            
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }    
        
    }
    
    @POST
    @Path("/sendEmail")
    @Produces(MediaType.APPLICATION_JSON)
    def sendMail(@RequestBody String requestBody) {   
        try {
            virtualMachineService.sendMail(requestBody) as JSON;
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }        
    }
    
    
    @POST
    @Path("/ip/acquire")
    @Produces(MediaType.APPLICATION_JSON)
    def acquireIp(@RequestBody String requestBody) {
        try {
            virtualMachineService.acquireIp(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/ip/remove")
    @Produces(MediaType.APPLICATION_JSON)
    def revokeIp(@RequestBody String requestBody) {
        try {
            virtualMachineService.revokeIp(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/ip/remove/job")
    @Produces(MediaType.APPLICATION_JSON)
    def revokeIpJob(@RequestBody String requestBody) {
        try {
            virtualMachineService.revokeIpJob(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/ip/acquire/job")
    @Produces(MediaType.APPLICATION_JSON)
    def acquireIpJob(@RequestBody String requestBody) {
        try {
            virtualMachineService.acquireIpJob(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path("ip/list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def listVmIp(@PathParam("id") String id) {
        try {
            virtualMachineService.listVmIp(id) as JSON
            
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }    
        
    }
    
    @POST
    @Path("/addNicToVM")
    @Produces(MediaType.APPLICATION_JSON)
    def addNicToVM(@RequestBody String requestBody) {
        try {
            virtualMachineService.addNicToVM(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/addNicToVM/job")
    @Produces(MediaType.APPLICATION_JSON)
    def addNicToVMJob(@RequestBody String requestBody) {
        virtualMachineService.addNicToVMJob(requestBody) as JSON
    }  
    
    @POST
    @Path("/updateDefaultNic/job")
    @Produces(MediaType.APPLICATION_JSON)
    def updateDefaultNicJob(@RequestBody String requestBody) {
        try {
            virtualMachineService.updateDefaultNicJob(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    
    @POST
    @Path("/updateDefaultNic")
    @Produces(MediaType.APPLICATION_JSON)
    def updateDefaultNic(@RequestBody String requestBody) {
        try {
            virtualMachineService.updateDefaultNic(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
}            
