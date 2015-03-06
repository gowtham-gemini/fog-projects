package com.assistanz.fogpanel

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

@Path('/api/networkAcl')
class NetworkAclResource {

    def networkAclService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getNetworkAclRepresentation(@QueryParam("vpcId") String vpcId, @QueryParam("referenceId") String referenceId) {
        try {            
            networkAclService.listNetworkAcl(vpcId, referenceId) as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def createNetworkAcl(@RequestBody String requestBody) {
        try {     
            
            def requestData = JSON.parse(requestBody)
            
            networkAclService.createNetworkAcl(requestData) as JSON  
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @POST
    @Path("/job")
    @Produces(MediaType.APPLICATION_JSON)
    def getNetworkAclJob(@RequestBody String requestBody) {
         try {   
            def requestData = JSON.parse(requestBody)
        
            networkAclService.getNetworkAclJob(requestData.jobId) as JSON 
        
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/change/")
    @Produces(MediaType.APPLICATION_JSON)
    def changeACL(@RequestBody String requestBody) {
        
         try {  
             
            def requestData = JSON.parse(requestBody)
            networkAclService.changeACL(requestData) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
        
    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteNetworkAcl(@RequestBody String requestBody) {
        try {                
            def requestData = JSON.parse(requestBody)
            networkAclService.deleteNetworkAcl(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/rule/add")
    @Produces(MediaType.APPLICATION_JSON)
    def createNetworkAclRule(@RequestBody String requestBody) {
        try {  
            def requestData = JSON.parse(requestBody)
            networkAclService.createNetworkAclRule(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @PUT
    @Path("/rule/edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def editAclRule(@RequestBody String requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)  
            
            networkAclService.editAclRule(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }           
    }
    
    @GET
    @Path("/rule/list")
    @Produces(MediaType.APPLICATION_JSON)
    def getNetworkAclRules(@QueryParam("aclId") String aclId, @QueryParam("referenceId") String referenceId) {        
        
        try {  
            networkAclService.getNetworkAclRules(aclId, referenceId) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/rule/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteNetworkAclRule(@RequestBody String requestBody) {
        try {                
            def requestData = JSON.parse(requestBody)
            networkAclService.deleteNetworkAclRule(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
}
