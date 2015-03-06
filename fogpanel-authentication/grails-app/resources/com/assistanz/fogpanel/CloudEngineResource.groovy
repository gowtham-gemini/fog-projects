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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import grails.converters.deep.JSON

@Path('/api/cloudEngine')
class CloudEngineResource {
    
    
    CloudEngineService cloudEngineService
 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String list() {

        cloudEngineService.list() as JSON
    }
    
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    def cloudEngineInfo(@QueryParam("engineId") String engineId) {
       try {
           cloudEngineService.cloudEngineDetail(engineId) as JSON
       } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            
            cloudEngineService.create(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/verify")
    @Produces(MediaType.APPLICATION_JSON)
    def verifyLink(@QueryParam("cloudEngineId") String cloudEngineId) {
        try {
            cloudEngineService.verifyCloudEngineUrl(cloudEngineId) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/changeStatus")
    @Produces(MediaType.APPLICATION_JSON)
    def changeStatus(@QueryParam("cloudEngineId") String cloudEngineId) {
        try {
            cloudEngineService.changeCloudEngineStatus(cloudEngineId) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try { 
            Console.print(requestBody + "ffffffffffffffffffffffffff")
            def requestData = JSON.parse(requestBody)
            
            cloudEngineService.updateCloudEngine(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/hardCodedType")
    @Produces(MediaType.APPLICATION_JSON) 
    String getCloudEngineType() {
        ArrayList<ArrayList<String>> engineTypeList = new ArrayList<ArrayList<String>>();  
    
        // item 1
        HashMap item1 = new HashMap();
        item1.put("id", "FOG-OS");
        item1.put("name", "FOG-OS");
        engineTypeList.add(item1)
        
        // item 2
        HashMap item2 = new HashMap();
        item2.put("id", "FOG-AWS");
        item2.put("name", "FOG-AWS");
        engineTypeList.add(item2)
        
        // item 3
        HashMap item3 = new HashMap();
        item3.put("id", "FOG-CS");
        item3.put("name", "FOG-CS");
        engineTypeList.add(item3)
            
            
        engineTypeList as JSON; 
        
    }
    
    @GET
    @Path("/edit")
    @Produces(MediaType.APPLICATION_JSON)
    String getCloudEngineDetails(@QueryParam("id") String id) {
        try {
           cloudEngineService.getCloudEngineDetails(id) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
        
    }
    @GET
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    String deleteCloudEngine(@QueryParam("id") String id) {
        try {
           cloudEngineService.deleteCloudEngine(id) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
        
    }
    
    @GET
    @Path("/getSessionInfo")
    @Produces(MediaType.APPLICATION_JSON)
    def getSessionDetails() {
        try {
            cloudEngineService.getSessionDetails () as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/session/getUser")
    @Produces(MediaType.APPLICATION_JSON)
    def getUserInfo(@QueryParam("sessionId") String sessionId) {
        try {
            cloudEngineService.getUserDetails (sessionId) as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    

}
