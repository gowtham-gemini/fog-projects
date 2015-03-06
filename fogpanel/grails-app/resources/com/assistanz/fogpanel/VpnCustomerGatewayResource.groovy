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

@Path('/api/VpnCustomerGateway')
class VpnCustomerGatewayResource {
    
    def vpnCustomerGatewayService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getVPNCustomerGatewayRepresentation() {
        vpnCustomerGatewayService.list() as JSON 
    }
    
    @GET
    @Path("/count/")
    @Produces(MediaType.APPLICATION_JSON)
    String getVPNStat(@QueryParam("zoneId") String zoneId, @QueryParam("vpcId") String vpcId) {
        try {     
            vpnCustomerGatewayService.getVPNStat(zoneId , vpcId) as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }           
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def createVPNCustomerGateway(@RequestBody String requestBody) {
        try {     
            def requestData = JSON.parse(requestBody)            
            vpnCustomerGatewayService.createVPNCustomerGateway(requestData) as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }           
    }
    
    @PUT
    @Path("/edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def updateVPNCustomerGateway(@RequestBody String requestBody) {
        try {     
            def requestData = JSON.parse(requestBody)            
            vpnCustomerGatewayService.updateVPNCustomerGateway(requestData) as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/edit/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def editVPNCustomerGatewayJob(@RequestBody String requestBody) {        
        try {               
            def requestData = JSON.parse(requestBody)
            vpnCustomerGatewayService.editVPNCustomerGatewayJob(requestData.jobId) as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/create/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def createVPNCustomerGatewayJob(@RequestBody String requestBody) {        
        try {               
            def requestData = JSON.parse(requestBody)
            vpnCustomerGatewayService.createVPNCustomerGatewayJob(requestData.jobId) as JSON            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
        
    @POST
    @Path("/delete/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def getRemoveVPNCustomerGatewayJob(@RequestBody String requestBody) {
        try {   
            
            def requestData = JSON.parse(requestBody)
            vpnCustomerGatewayService.getRemoveVPNCustomerGatewayJob(requestData.jobId) as JSON
            
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
    def removeVPNCustomerGateway(@RequestBody String requestBody) {
        try {   
            def requestData = JSON.parse(requestBody)
            
            vpnCustomerGatewayService.removeVPNCustomerGateway(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/vpnConnection/list")
    @Produces(MediaType.APPLICATION_JSON)
    String listVPNConnection(@QueryParam("vpcId") String vpcId, @QueryParam("zoneId") String zoneId) {
        vpnCustomerGatewayService.listVPNConnection(vpcId, zoneId) as JSON 
    }
    
    @POST  
    @Path("/vpnConnection/create/")
    @Produces(MediaType.APPLICATION_JSON)
    def createVPNConnection(@RequestBody String requestBody) {
        try {     
            def requestData = JSON.parse(requestBody)
            
            vpnCustomerGatewayService.createVPNConnection(requestData) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @POST 
    @Path("/vpnConnection/create/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def createVPNConnectionJob(@RequestBody String requestBody) {
        
        try {  
             
            def requestData = JSON.parse(requestBody)
            vpnCustomerGatewayService.createVPNConnectionJob(requestData.jobId) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/vpnConnection/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def removeVPNConnection(@RequestBody String requestBody) {
        try {   
            def requestData = JSON.parse(requestBody)
            
            vpnCustomerGatewayService.removeVPNConnection(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/vpnConnection/delete/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def getRemoveVPNConnectionJob(@RequestBody String requestBody) {
        try {   
            
            def requestData = JSON.parse(requestBody)
            vpnCustomerGatewayService.getRemoveVPNConnectionJob(requestData.jobId) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    @POST
    @Path("/vpnConnection/restart")
    @Produces(MediaType.APPLICATION_JSON)
    def restartVPNConnection(@RequestBody String requestBody) {
        try {   
            def requestData = JSON.parse(requestBody)
            
            vpnCustomerGatewayService.restartVPNConnection(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/vpnConnection/restart/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def getRestartVPNConnectionJob(@RequestBody String requestBody) {
        try {   
            
            def requestData = JSON.parse(requestBody)
            vpnCustomerGatewayService.getRestartVPNConnectionJob(requestData.jobId) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
}
