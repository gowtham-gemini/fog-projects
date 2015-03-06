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
import grails.converters.deep.JSON

@Path('/api/notification')
class NotificationResource {

    NotificationService notificationService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getNotificationRepresentation() {
        try {
            notificationService.list() as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/testMailConfig/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    String sendMailTest(@PathParam("id") String id) {
        try {
            notificationService.send(id, "Mail Config", "<h1>Mail Config Test<h1>") as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/event")
    @Produces(MediaType.APPLICATION_JSON)
    String eventList() {
        try {
            notificationService.eventList() as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
     
    @GET
    @Path("/cloudUsage")
    @Produces(MediaType.APPLICATION_JSON)
    String cloudUsageAlerts() {
        try {
            notificationService.cloudUsageAlerts() as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/alerts")
    @Produces(MediaType.APPLICATION_JSON)
    String alertsList() {
        try {
            notificationService.csAlertsList() as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/listEmail")
    @Produces(MediaType.APPLICATION_JSON)
    String listEmail() {
        try {
            notificationService.listEmail() as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/billingAlerts")
    @Produces(MediaType.APPLICATION_JSON)
    String billingAlerts() {
        try {
            notificationService.billingAlerts() as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/healthByZone")
    @Produces(MediaType.APPLICATION_JSON)
    String billingAlerts(@QueryParam("zoneReferenceId") String zoneReferenceId) {
        try {
            notificationService.healthByZone(zoneReferenceId) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @PUT
    @Path("/billingAlerts/view/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    String viewBillingNotification(@PathParam("id") Long id) {
        try {
            notificationService.viewBillingNotification(id) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @PUT
    @Path("/cloudUsage/view/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    String viewCloudUsageNotification(@PathParam("id") Long id) {
        try {
            notificationService.viewCloudUsageNotification(id) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    
    @POST
    @Path("/addEmail")
    @Produces(MediaType.APPLICATION_JSON)
    String addEmail(@RequestBody String requestBody) {
        try {
            notificationService.addEmail(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/deleteEmail")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteEmail(@RequestBody String requestBody) {
        try {
            notificationService.deleteEmail(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
}
