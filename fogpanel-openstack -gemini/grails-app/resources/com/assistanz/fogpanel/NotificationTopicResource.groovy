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

@Path('/api/notificationTopic')
class NotificationTopicResource {
    
    NotificationTopicService notificationTopicService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getNotificationTopicRepresentation() {
       
        notificationTopicService.list() as JSON
        
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    String getNotificationTopic(@PathParam("id") String id) {
       
        notificationTopicService.getNotificationTopic(id) as JSON
        
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def addTopic(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
            
            notificationTopicService.addTopic(requestData) as JSON;
            
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def editTopic(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
            
            notificationTopicService.editTopic(requestData) as JSON;
            
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @POST
    @Path("/addSubscriber")
    @Produces(MediaType.APPLICATION_JSON)
    def addSubscriber(@RequestBody String requestBody) {
        try {
            
            // convert string to json object
            def requestData = JSON.parse(requestBody)      
            
            notificationTopicService.addSubscriber(requestData)as JSON;
            
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/removeSubscriber/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def removeSubscriber(@PathParam("id") String id) {
        try {
           
            def response = notificationTopicService.removeSubscriber(id)
            return response;
            
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def removeTopic(@PathParam("id") String id) {
        try {
           
            def response = notificationTopicService.removeTopic(id)
            return response;
            
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    
}
