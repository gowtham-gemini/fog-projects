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

@Path('/api/securityGroup')
class SecurityGroupResource {

    SecurityGroupService securityGroupService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    def listSecurityGroup(@QueryParam("referenceId") String referenceId) {
        
        securityGroupService.listSecurityGroup(referenceId) as JSON
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def createSecurityGroup(@RequestBody String requestBody) {
        try { 

            def requestData = JSON.parse(requestBody) 
            
            securityGroupService.createSecurityGroup(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    def updateSecurityGroup(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)

            securityGroupService.updateSecurityGroup(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") String id) {  
        try {
            securityGroupService.deleteSecurityGroup(id) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/rule")
    @Produces(MediaType.APPLICATION_JSON)
    def getSecurityGroupRules(@QueryParam("referenceId") String referenceId) {

        securityGroupService.getSecurityGroupRules(referenceId) as JSON
    }
    
    @POST
    @Path("/rule")
    @Produces(MediaType.APPLICATION_JSON)
    def createSecurityGroupRule(@RequestBody String requestBody) {
        try { 

            def requestData = JSON.parse(requestBody)
            
            securityGroupService.createSecurityGroupRule(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/rule/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteRule(@PathParam("id") String referenceId) {  
        try {

            securityGroupService.deleteSecurityGroupRule(referenceId) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
}
