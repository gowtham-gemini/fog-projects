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
import grails.converters.deep.JSON;
import org.openstack4j.model.compute.ActionResponse;

@Path('/api/floatingIp')
class FloatingIpResource {
    
    FloatingIpService floatingIpService;

    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    String getFloatingIpList(@QueryParam("referenceId") String referenceId) {
        try{ 
            floatingIpService.list(referenceId)as JSON;
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/portList")
    @Produces(MediaType.APPLICATION_JSON) 
    def getPorts(@QueryParam("instanceId") String instanceId) {
        try {
            floatingIpService.getPortList(instanceId)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/instanceIp")
    @Produces(MediaType.APPLICATION_JSON)
    def instanceIps(@QueryParam("referenceId") String referenceId) {
        try {
            floatingIpService.getInstanceIp(referenceId)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON) 
    def create(@RequestBody String requestBody) {
        
        try{
           def requestData = JSON.parse(requestBody)
           floatingIpService.create(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
        
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") String referenceId) {
        try {
            ActionResponse actionResponse = floatingIpService.delete(referenceId)
            def result = "OK";
            
            if (actionResponse.isSuccess() == false) {
                result = actionResponse.getFault();
                return result
            } else {
                return result;
            }   
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @PUT
    @Path("/associate/{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    def associateToPort(@RequestBody String requestBody) {
        
        try {
           def requestData = JSON.parse(requestBody)
           floatingIpService.associate(requestData)as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @PUT
    @Path("/disAssociate/{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    def  disAssociatePort(@RequestBody String requestBody) {
        
        try {
           def requestData = JSON.parse(requestBody)
           floatingIpService.disAssociate(requestData)as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
}
