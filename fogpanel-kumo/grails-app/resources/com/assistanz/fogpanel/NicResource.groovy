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

@Path('/api/nic')
class NicResource {
    NicService nicService
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getNic(@QueryParam("vmReferenceId") String vmReferenceId) {
        nicService.listNic(vmReferenceId);
    }
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    String list(@QueryParam("vmReferenceId") String vmReferenceId) {         
        try { 
            nicService.list(vmReferenceId) as JSON;        
        } catch (Exception ex){
                [ex] as JSON
        }        
    }
    
    @POST
    @Path("/remove/")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteNic(@RequestBody String requestBody) {
        try {
            nicService.deleteNic(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }           
    }
    
    @POST
    @Path("/remove/job")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteNicJob(@RequestBody String requestBody) {
        try {
            nicService.deleteNicJob(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/ip/acquire")
    @Produces(MediaType.APPLICATION_JSON)
    def acquireIp(@RequestBody String requestBody) {
        try {
            nicService.acquireIp(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/ip/list/")
    @Produces(MediaType.APPLICATION_JSON)
    def getNetworkNicIPList(@QueryParam("nicId") String nicId, @QueryParam("vmId") String vmId) {
        try {
            nicService.getNetworkNicIPList(nicId, vmId) as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/ip/remove")
    @Produces(MediaType.APPLICATION_JSON)
    def revokeIp(@RequestBody String requestBody) {
        try {
            nicService.revokeIp(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/ip/remove/job")
    @Produces(MediaType.APPLICATION_JSON)
    def revokeIpJob(@RequestBody String requestBody) {
        try {
            nicService.revokeIpJob(requestBody)as JSON;
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
            nicService.acquireIpJob(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
}
