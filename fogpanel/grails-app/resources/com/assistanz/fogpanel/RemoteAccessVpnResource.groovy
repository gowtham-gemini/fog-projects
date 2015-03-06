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
import grails.converters.JSON

@Path('/api/remoteAccessVpn')
class RemoteAccessVpnResource {
    IpAddressService ipAddressService
    @GET
    @Produces('text/plain')
    String getRemoteAccessVpnRepresentation() {
        'RemoteAccessVpn'
    }    
    
    @POST
    @Path("/enable")
    @Produces(MediaType.APPLICATION_JSON)
    def enableVPN(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)            
            ipAddressService.enableVPNService(requestData.ipAddressId) as JSON
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
    @Path("/disable")
    @Produces(MediaType.APPLICATION_JSON)
    def disableVPN(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)            
            ipAddressService.disableVPNService(requestData.ipAddressId) as JSON
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
    @Path("/vpnEnableJob")
    @Produces(MediaType.APPLICATION_JSON)
    def vpnEnableJob(@RequestBody String requestBody) {
        try {            
            ipAddressService.vpnEnableJob(requestBody) as JSON
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
    @Path("/vpnDisableJob")
    @Produces(MediaType.APPLICATION_JSON)
    def vpnDisableJob(@RequestBody String requestBody) {
        try {                 
            ipAddressService.vpnDisableJob(requestBody) as JSON
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
    @Path("/addVPNUser")
    @Produces(MediaType.APPLICATION_JSON)
    def addVpnUser(@RequestBody String requestBody) {
        try {                            
            ipAddressService.addVpnUser(requestBody) as JSON
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
    
    
    @GET
    @Path("/vpnUsers/validate")
    @Produces(MediaType.APPLICATION_JSON)
    String validateVpnUser(@QueryParam("vpnUserName") String vpnUserName) {
        try {
            ipAddressService.validateVpnUser(vpnUserName) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }    
    }    
    
    @GET
    @Path("/byPublicIp")
    @Produces(MediaType.APPLICATION_JSON)
    String getVpnByIP(@QueryParam("ipAddressId") String ipAddressId) {
        try {
            ipAddressService.getVpnByIP(ipAddressId) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }    
    } 
    
    @POST
    @Path("/deleteVpnUser")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteVpnUser(@RequestBody String requestBody) {
        try {                            
            ipAddressService.deleteVpnUser(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }           
    }
    @POST
    @Path("/deleteVpnUser/job")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteVpnUserJob(@RequestBody String requestBody) {
        try {                            
            ipAddressService.deleteVpnUserJob(requestBody) as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }           
    }
    
    @GET
    @Path("/listVPNUsers")
    @Produces(MediaType.APPLICATION_JSON)
    String listVPNUsers() {
        try {                            
            ipAddressService.listVPNUsers() as JSON;
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
    @Path("/addVPNUser/job")
    @Produces(MediaType.APPLICATION_JSON)
    def addVpnUserJob(@RequestBody String requestBody) {
        try {                            
            ipAddressService.addVpnUserJob(requestBody) as JSON
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
