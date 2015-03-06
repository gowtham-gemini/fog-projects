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
import com.assistanz.fogpanel.SecurityGroupTemplateService

@Path('/api/securityGroupTemplate')
class SecurityGroupTemplateResource {
    
    SecurityGroupTemplateService securityGroupTemplateService
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getSecurityGroupTemplateRepresentation(@QueryParam("securityGroupTemplateId") String securityGroupTemplateId, @QueryParam("securityGroupType") String securityGroupType, @QueryParam("baseOs") String baseOs, @QueryParam("disabled") String disabled) {
        try {               
             securityGroupTemplateService.list(securityGroupTemplateId, securityGroupType, baseOs, disabled) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }  
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@PathParam("id") Long id) {
        try {
            securityGroupTemplateService.get(id) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }  
    }
        
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {           
            securityGroupTemplateService.createSecurityGroup(requestBody);            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }           
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try {  
            securityGroupTemplateService.disableSecurityGroup(requestBody);
              
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
    
    @POST
    @Path("/ingress")
    @Produces(MediaType.APPLICATION_JSON)
    def ingress(@RequestBody String requestBody) {
        try {
            securityGroupTemplateService.createRule(requestBody)
             
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
    @Path("ingress/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleterule(@PathParam("id") Long id) {       
         try {
             securityGroupTemplateService.deleterule(id)
             
            
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }  
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") Long id) {        
         try {
             securityGroupTemplateService.deleteFirewal(id)
             
            
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }  
}
