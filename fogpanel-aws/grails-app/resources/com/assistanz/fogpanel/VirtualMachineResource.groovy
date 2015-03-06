package com.assistanz.fogpanel

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
import org.springframework.web.bind.annotation.RequestBody

import grails.converters.deep.JSON

@Path('/api/virtualMachine')
class VirtualMachineResource {
    
    VirtualMachineService virtualMachineService
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    String getVMList(@QueryParam("currentRegionId") String currentRegionId) {
        
        try {
            virtualMachineService.getVMList(currentRegionId) as JSON
        } catch(Exception Ex) {
            throw Ex
        }

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
    
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    def viewVM(@QueryParam("regionId") String regionId, @QueryParam("referenceId") String referenceId) {
        
        virtualMachineService.viewServer(regionId, referenceId) as JSON
    }
    
    @POST
    @Path("/terminate")
    @Produces(MediaType.APPLICATION_JSON)
    def terminate(@RequestBody String requestBody) {
        try {            
            // convert string to json object
            def requestData = JSON.parse(requestBody);
            virtualMachineService.terminate(requestData)as JSON;
            } catch (ValidationException ex) {
                [ex] as JSON
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
            virtualMachineService.stop(requestData)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
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
            virtualMachineService.start(requestData)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }

}
