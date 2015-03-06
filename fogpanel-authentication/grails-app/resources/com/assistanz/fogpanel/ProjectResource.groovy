package com.assistanz.fogpanel

import grails.converters.JSON
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestBody

@Path('/api/project')
class ProjectResource {

    ProjectService projectService
    
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    String getProjectList() {
        try {             
            
            projectService.getProjectList() as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/cloudEngines")
    @Produces(MediaType.APPLICATION_JSON)
    String getCloudEngines() {
        try {             
            
            projectService.getCloudEngines() as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }    
    
    @POST
    @Path("/assign/cloudEngine")
    @Produces(MediaType.APPLICATION_JSON)
    String assignCloudEngine(@RequestBody String requestBody) {
        try {             
            def requestData = JSON.parse(requestBody)
            projectService.assignCloudEngine(requestData) as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/assigned/list")
    @Produces(MediaType.APPLICATION_JSON)
    String getAssignedProjects() {
        
        try {                         
            
            projectService.getAssignedProjects() as JSON
            
        } catch (Exception ex){
            [ex] as JSON
        }
    }    
}
