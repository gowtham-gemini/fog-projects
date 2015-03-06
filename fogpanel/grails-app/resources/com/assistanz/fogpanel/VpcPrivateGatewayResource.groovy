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

@Path('/api/vpcPrivateGateway')
class VpcPrivateGatewayResource {
    
    def vpcPrivateGatewayService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getVpcPrivateGatewayRepresentation(@QueryParam("vpcId") String vpcId, @QueryParam("referenceId") String referenceId, @QueryParam("zoneReferenceId") String zoneReferenceId) {
        try {            
            vpcPrivateGatewayService.list(vpcId, referenceId, zoneReferenceId) as JSON   
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def createVPCPrivateGateway(@RequestBody String requestBody) {
        try {     
            def requestData = JSON.parse(requestBody)
            vpcPrivateGatewayService.createVPCPrivateGateway(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @POST
    @Path("/create/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def createVPCPrivateGatewayJob(@RequestBody String requestBody) {
        try {  
            def requestData = JSON.parse(requestBody)
            vpcPrivateGatewayService.createVPCPrivateGatewayJob(requestData.jobId) as JSON
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
    def deleteVPCPrivateGatewayJob(@RequestBody String requestBody) {
        try {   
            
            def requestData = JSON.parse(requestBody)
            vpcPrivateGatewayService.deleteVPCPrivateGatewayJob(requestData.jobId) as JSON
            
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
    def deleteVPCPrivateGateway(@RequestBody String requestBody) {
        try {   
            def requestData = JSON.parse(requestBody)
            
            vpcPrivateGatewayService.deleteVPCPrivateGateway(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
        
    @GET
    @Path("/physicalNetwork/list/")
    @Produces(MediaType.APPLICATION_JSON)
    def getPhysicalNetwork(@QueryParam("vpcId") String vpcId) {
        try {            
            vpcPrivateGatewayService.getPhysicalNetwork(vpcId) as JSON   
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/staticRoute/list/")
    @Produces(MediaType.APPLICATION_JSON)
    def getStaticRouteList(@QueryParam("vpcPrivateGatewayId") String vpcPrivateGatewayId) {
        try {            
            vpcPrivateGatewayService.getStaticRouteList(vpcPrivateGatewayId) as JSON   
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/staticRoute/create/")
    @Produces(MediaType.APPLICATION_JSON)
    def createStaticRoute(@RequestBody String requestBody) {
        try {       
            def requestData = JSON.parse(requestBody)
            vpcPrivateGatewayService.createStaticRoute(requestData) as JSON   
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/staticRoute/create/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def createStaticRouteJob(@RequestBody String requestBody) {
        try {  
            def requestData = JSON.parse(requestBody)
            vpcPrivateGatewayService.createStaticRouteJob(requestData.jobId) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }

    
    @POST
    @Path("/staticRoute/delete/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteStaticRouteJob(@RequestBody String requestBody) {
        try {   
            
            def requestData = JSON.parse(requestBody)
            vpcPrivateGatewayService.deleteStaticRouteJob(requestData.jobId) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
        
    
    @POST
    @Path("/staticRoute/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteStaticRoute(@RequestBody String requestBody) {
        try {   
            def requestData = JSON.parse(requestBody)
            
            vpcPrivateGatewayService.deleteStaticRoute(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
}
