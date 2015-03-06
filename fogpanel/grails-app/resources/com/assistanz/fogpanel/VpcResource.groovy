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

@Path('/api/vpc')
class VpcResource {

    def vpcService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getVpcRepresentation(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("referenceId") String referenceId) {
        try {            
            vpcService.list(zoneReferenceId, referenceId) as JSON   
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/list/site2siteGatewayBase")
    @Produces(MediaType.APPLICATION_JSON)
    String getVpc(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("referenceId") String referenceId) {
        try {            
            vpcService.listVpcBySite2SiteGateway(zoneReferenceId, referenceId) as JSON   
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/stat/")
    @Produces(MediaType.APPLICATION_JSON)
    def getVPCStats(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("vpcId") String vpcId) {
        try {            
            vpcService.getVPCStats(zoneReferenceId, vpcId) as JSON   
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    
    
    @GET
    @Path("/vpcTopology/")
    @Produces(MediaType.APPLICATION_JSON)
    def getVPCTopology(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("vpcId") String vpcId) {
        try {            
            vpcService.getVPCTopology(zoneReferenceId, vpcId) as JSON   
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    
    @GET
    @Path("/vpcOffering/")
    @Produces(MediaType.APPLICATION_JSON)
    def listVPCOffering() {
        try {            
            vpcService.listVPCOffering() as JSON   
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @PUT
    @Path("/edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def editVpc(@RequestBody String requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)  
            
            vpcService.editVpc(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }           
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def createVPC(@RequestBody String requestBody) {
        try {     
            
            def requestData = JSON.parse(requestBody)
            
            vpcService.createVPC(requestData) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @GET
    @Path("/s2s/list/")
    @Produces(MediaType.APPLICATION_JSON)
    def listS2SVPN(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("vpcId") String vpcId) {
        try {            
            vpcService.listS2SVPN(zoneReferenceId, vpcId) as JSON   
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/s2s/create/")
    @Produces(MediaType.APPLICATION_JSON)
    def createS2SVPN(@RequestBody String requestBody) {
        try {     
            
            def requestData = JSON.parse(requestBody)
            
            vpcService.createS2SVPN(requestData) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @POST
    @Path("/s2s/remove/")
    @Produces(MediaType.APPLICATION_JSON)
    def removeS2SVPN(@RequestBody String requestBody) {
        try {     
            
            def requestData = JSON.parse(requestBody)
            
            vpcService.removeS2SVPN(requestData) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @POST
    @Path("/s2s/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def getS2SVPNJob(@RequestBody String requestBody) {
        
         try {  
             
            def requestData = JSON.parse(requestBody)
            vpcService.getS2SVPNJob(requestData.jobId) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/s2s/remove/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def getRemoveS2SVPNJob(@RequestBody String requestBody) {
        
         try {  
             
            def requestData = JSON.parse(requestBody)
            vpcService.getRemoveS2SVPNJob(requestData.jobId, requestData.vpnId) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/tier/create")
    @Produces(MediaType.APPLICATION_JSON)
    def createTier(@RequestBody String requestBody) {
        try {     
            
            def requestData = JSON.parse(requestBody)
            
            vpcService.createTier(requestData) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @GET
    @Path("/list/tier")
    @Produces(MediaType.APPLICATION_JSON)
    def listTier(@QueryParam("vpcId") String vpcId) {
        try { 
            vpcService.listTier(vpcId) as JSON   
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
    def getVPCCreateJob(@RequestBody String requestBody) {
        
         try {  
             
            def requestData = JSON.parse(requestBody)
            vpcService.getVPCCreateJob(requestData.jobId) as JSON
            
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
    def deleteVPCJob(@RequestBody String requestBody) {
        try {   
            
            def requestData = JSON.parse(requestBody)
            vpcService.deleteVPCJob(requestData.jobId) as JSON
            
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
    def removeVPC(@RequestBody String requestBody) {
        try {   
            def requestData = JSON.parse(requestBody)
            
            vpcService.deleteVPC(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/restart/")
    @Produces(MediaType.APPLICATION_JSON)
    def restartVPC(@RequestBody String requestBody) {
        try {                                
            def requestData = JSON.parse(requestBody)
            vpcService.restartVPC(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/tier/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def removeTier(@RequestBody String requestBody) {
        try {   
            def requestData = JSON.parse(requestBody)
            
            vpcService.removeTier(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/ip/list/")
    @Produces(MediaType.APPLICATION_JSON)
    def getNetworkIpAddressesList(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("vpcId") String vpcId) {        
        vpcService.getVpcIpAddressesList(zoneReferenceId, vpcId) as JSON
    }
    
    @POST
    @Path("/tier/delete/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteTierJob(@RequestBody String requestBody) {
        try {   
            
            def requestData = JSON.parse(requestBody)
            vpcService.deleteTierJob(requestData) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/tier/restart/")
    @Produces(MediaType.APPLICATION_JSON)
    def restartTier(@RequestBody String requestBody) {
        try {                                
            def requestData = JSON.parse(requestBody)
            vpcService.restartTier(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST 
    @Path("/tier/restart/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def restartTierJob(@RequestBody String requestBody) {
        try {   
            
            def requestData = JSON.parse(requestBody)
            vpcService.restartTierJob(requestData.jobId) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    @POST 
    @Path("/restart/job/")
    @Produces(MediaType.APPLICATION_JSON)
    def restartVPCJob(@RequestBody String requestBody) {
        try {   
            
            def requestData = JSON.parse(requestBody)
            vpcService.restartVPCJob(requestData.jobId) as JSON
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @PUT
    @Path("/tier/edit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def updateTier(@RequestBody String requestBody) {
        def requestData = JSON.parse(requestBody)
        vpcService.updateTier(requestData) as JSON
    }
    
    @POST
    @Path("/ip/acquire")
    @Produces(MediaType.APPLICATION_JSON)
    def acquireIpForVpc(@RequestBody String requestBody) {
        try {                                
            def requestData = JSON.parse(requestBody)
            vpcService.acquireIpForVpc(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    
    @POST
    @Path("/ip/release")
    @Produces(MediaType.APPLICATION_JSON)
    def releaseIpForNetwork(@RequestBody String requestBody) {
        try {                                
            def requestData = JSON.parse(requestBody)
            vpcService.releaseIpForNetwork(requestBody) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    @GET
    @Path("/ip/release/job/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def releaseIpJobForNetwork(@PathParam("id") String id) {
        try {                                
            def requestData = JSON.parse(requestBody)
            vpcService.releaseIpJobForNetwork(id) as JSON 
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
           
    }
    
    
    @POST
    @Path("/ip/acquire/job")
    @Produces(MediaType.APPLICATION_JSON)
    def acquireIpJobForVpc(@RequestBody String requestBody) {
        try {                                
            def requestData = JSON.parse(requestBody)
            vpcService.acquireIpJobForVpc(requestData) as JSON   
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex){
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/vpcOffering/pullFromCloudStack")
    def pullVPCOfferingFromCloudStack() {
        try { 
            vpcService.pullVPCOfferingFromCloudStackJobStart()
            ["OK"] as JSON;
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pullFromCloudStack")
    def pullVPCFromCloudStack() {
        try { 
            vpcService.pullVPCFromCloudStackJobStart()
            ["OK"] as JSON;
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
}
