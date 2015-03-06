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


@Path('/api/template')
class TemplateResource {
    
    TemplateService templateService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getTemplateRepresentation(@QueryParam("name") String name, @QueryParam("baseOs") String baseOs, 
        @QueryParam("myTemplate") String myTemplate, @QueryParam("appTemplate") String appTemplate, @QueryParam("templateReferenceId") String templateReferenceId) {
        templateService.list(name, baseOs, myTemplate, appTemplate, templateReferenceId) as JSON
    }   
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pullFromCloudStack")
    def pullTemplateFromCloudStack() {
        try { 
            templateService.pullTemplateFromCloudStack()
            ["OK"] as JSON;
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def count(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("appTemplate") String appTemplate, @QueryParam("myTemplate") String myTemplate, 
        @QueryParam("baseOs") String baseOs) {
        try {           
            templateService.count(zoneReferenceId, appTemplate, myTemplate, baseOs) as JSON 
            
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }            
    }
    
    @GET
    @Path("/userCount")
    @Produces(MediaType.APPLICATION_JSON)
    def userCount() {
        try {           
            templateService.userCount() as JSON             
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }            
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    String get(@PathParam("id") Long id) {
        try { 
            templateService.getTemplateById(id) as JSON;
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/byZone")
    @Produces(MediaType.APPLICATION_JSON)
    String listTemplateByZone(@QueryParam("tempId") String tempId, @QueryParam("zoneId") String zoneId) {
        templateService.listTemplateByZone(tempId, zoneId) as JSON
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {
                         
            templateService.create(requestBody)
                ["OK"]
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
    @Path("/copyTemplate")
    @Produces(MediaType.APPLICATION_JSON)
    def copy(@RequestBody String requestBody) {
        try {
                    
            templateService.copyTemplate(requestBody)
                ["OK"]
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try {  
            templateService.update(requestBody);
                ["OK"]
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex){
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
            templateService.job(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/deleteJob")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteJob(@RequestBody String requestBody) {
        try {
            templateService.deleteJob(requestBody)as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    def listByOsType(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("baseOs") String baseOs, 
        @QueryParam("myTemplate") String myTemplate, @QueryParam("appTemplate") String appTemplate) {
        try {            
            templateService.listByOs(zoneReferenceId, baseOs, myTemplate, appTemplate) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/byCategory")
    @Produces(MediaType.APPLICATION_JSON)
    def byCategory(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("hypervisor") String hypervisor, @QueryParam("osType") String osType, @QueryParam("architecture") String architecture) {
        try {            
            templateService.templatesByCategory(zoneReferenceId, hypervisor, osType, architecture) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    @GET
    @Path("/pagination")
    @Produces(MediaType.APPLICATION_JSON)
    def templatesPagination(@QueryParam("page") String pageNo, @QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("hypervisor") String hypervisor, 
        @QueryParam("osType") String osType, @QueryParam("architecture") String architecture, @QueryParam("name") String name) {
        try {            
            templateService.templatesPagination(pageNo, zoneReferenceId, hypervisor, osType, architecture, name) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    def filterTemplate(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("hypervisor") String hypervisor, 
        @QueryParam("osType") String osType, @QueryParam("architecture") String architecture, @QueryParam("name") String name) {
        try {            
            templateService.filterTemplate(zoneReferenceId, hypervisor, osType, architecture, name) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }    
    
    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@RequestBody String requestBody) {
        try {
            templateService.delete(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
	
}
