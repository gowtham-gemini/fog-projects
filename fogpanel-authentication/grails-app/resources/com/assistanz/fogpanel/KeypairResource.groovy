package com.assistanz.fogpanel

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.RequestBody;

@Path('/api/keypair')
class KeypairResource {
    
    KeypairService keypairService

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    String getSSHKeyList() {  
        keypairService.listSSHKey() as JSON  
    }    
    
    @DELETE
    @Path("/deletePrivateKey/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteSSHPrivateKey(@PathParam("id") String id) {
        
        keypairService.deletePrivateKey(id) as JSON
        return "OK";
    }
       
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def createSSHKey(@RequestBody String requestBody) {
        try {  
            
            def requestData = JSON.parse(requestBody)            
            keypairService.createSSHKey(requestData) as JSON;  
            
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
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteSSHKey(@RequestBody String requestBody) {
        try {  
            
            def requestData = JSON.parse(requestBody)
            keypairService.deleteSSHKey(requestData)as JSON;
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
}
