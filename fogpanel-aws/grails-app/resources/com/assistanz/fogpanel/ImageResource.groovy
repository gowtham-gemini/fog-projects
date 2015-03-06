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

@Path('/api/image')
class ImageResource {
    
    ImageService imageService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getImageRepresentation() {
        
        try {
            imageService.listImages() as JSON
        } catch(Exception Ex) {
            throw Ex
        }

    }
    
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    String getImageDetails(@QueryParam("referenceId") String referenceId) {
        try {
           imageService.view(referenceId)
        } catch (Exception ex) {
            [ex] as JSON
        }
        
    }
    
    @GET
    @Path("/listByAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def userImages(@QueryParam("referenceId") String referenceId) {
        try {
           imageService.userImageList(referenceId) as JSON
        } catch (Exception ex) {
              [ex] as JSON
        }
    }
    
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pullFromAws")
    def pullFlavorFromAws() {
        try { 
            imageService.pullImageFromAws()
            ["OK"] as JSON;
        } catch (Exception ex) {
                
        }
    }    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def createImages(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            
            imageService.createImages(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @PUT 
    @Path("/update/{id}") 	 
    @Produces(MediaType.APPLICATION_JSON)
    def updateImage(@RequestBody String requestBody) {     
        def requestData = JSON.parse(requestBody);
        try {  
            imageService.updateImage(requestData) as JSON
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }     
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") String id) {        
        try {
            imageService.deleteCurrentImage(id) as JSON
            ["OK"]
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/count")
    def count() {
        try { 
            imageService.imageCount() as JSON

        } catch (Exception ex) {
                [ex] as JSON
        }
    } 
    
    
}
