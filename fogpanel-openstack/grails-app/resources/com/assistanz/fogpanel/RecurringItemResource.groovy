package com.assistanz.fogpanel

import java.util.logging.Level;
import java.util.logging.Logger;
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
import com.assistanz.fogpanel.Config


@Path('/api/recurringItem')
class RecurringItemResource {
    
    RecurringItemService recurringItemService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getRecurringItemRepresentation(@QueryParam("accountId") Long accountId) {
        recurringItemService.list(accountId) as JSON
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    String get(@PathParam("id") Long id) {
        try { 
            recurringItemService.getCurrentData(id) as JSON
            
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
         try {
            // convert string to json object
            def requestData = JSON.parse(requestBody)
            
            recurringItemService.update(requestData) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") Long id) {        
         try {
            recurringItemService.delete(id)
            return "OK"
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path('/count')
    @Produces(MediaType.APPLICATION_JSON)
    def count() {
        try {                        
            recurringItemService.count()as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
    
    @GET
    @Path('/user/count')
    @Produces(MediaType.APPLICATION_JSON)
    def getCurrentUserRecuringItem() {
        try {                        
            recurringItemService.getCurrentUserRecuringItem()as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
}
