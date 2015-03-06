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

@Path('/api/securityGroup')
class SecurityGroupResource {
    
    SecurityGroupService securityGroupService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getSecurityGroupRepresentation() {
        try {
             securityGroupService.list() as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }  
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@PathParam("id") String id) {
        try {
            securityGroupService.list(id) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }  
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {
            securityGroupService.create(requestBody)
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
    
    @POST
    @Path("/ingress")
    @Produces(MediaType.APPLICATION_JSON)
    def ingress(@RequestBody String requestBody) {
        try {
            securityGroupService.ingress(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
    
    @DELETE
    @Path("/ingress/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteIngress(@PathParam("id") String id) {
        try {
            securityGroupService.deleteIngress(id) 
            "OK"
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
    
    @DELETE
    @Path("/egress/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteEgress(@PathParam("id") String id) {
        try {
            securityGroupService.deleteEgress(id) 
            "OK"
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
    
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteFirewal(@PathParam("id") String id) {
        try {
            securityGroupService.deleteSecurityGroup(id) as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }  
    }
    
    
    @POST
    @Path("/egress")
    @Produces(MediaType.APPLICATION_JSON)
    def egress(@RequestBody String requestBody) {
        try {
            securityGroupService.egress(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
    
    @POST
    @Path("/job")
    @Produces(MediaType.APPLICATION_JSON)
    def job(@RequestBody String requestBody) {
        try {
            securityGroupService.job(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
}
