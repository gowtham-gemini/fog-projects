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

@Path('/api/router')
class RouterResource {
    
    RouterService routerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    String list(@QueryParam("referenceId") String referenceId) {
      
        routerService.listRouter(referenceId) as JSON
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON) 
    def create(@RequestBody String requestBody) {
        
        try{
           def requestData = JSON.parse(requestBody)
           routerService.createRouter(requestData) as JSON
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
           routerService.updateRouter(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") String referenceId) {
        try {
            ActionResponse actionResponse = routerService.deleteRouter(referenceId)
            
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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/interfaceList")
    def interfaceList(@QueryParam("routerId") String routerId) {
        try{
            routerService.interfacesList(routerId)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/addInterface")
    def addInterface(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            routerService.attachInterface(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/deleteInterface")
    def detachInterface(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            routerService.deleteInterface(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/setGateway")
    def setGateway(@RequestBody String requestBody) {
        try{
            def requestData = JSON.parse(requestBody)
            routerService.setGateWay(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/clearGateway")
    def removeGateway(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            routerService.clearGateWay(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
}
