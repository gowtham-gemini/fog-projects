package com.assistanz.fogpanel

import java.util.List;
import java.util.Scanner;
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

@Path('/api/monitoring')
class MonitoringResource {
    MonitoringService monitoringService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    def getFlavorRepresentation(@QueryParam("deviceId") String deviceId, @QueryParam("graphType") String graphType, 
        @QueryParam("size") String size, @QueryParam("dRange") String dRange) {
        try {            
            def stream = monitoringService.getGraph(deviceId, graphType, size, dRange) as JSON;  
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }                                        
    }
    
    @GET
    @Path("/ipServices")
    @Produces(MediaType.APPLICATION_JSON)
    def getIPServiceList(@QueryParam("deviceId") String deviceId) { 
        try {
            monitoringService.getIPServiceList(deviceId) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/deviceStatus")
    @Produces(MediaType.APPLICATION_JSON)
    def getdeviceStatus(@QueryParam("deviceId") String deviceId) { 
        try {
            monitoringService.getMonitoringDeviceStatus(deviceId) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/collectorList")
    @Produces(MediaType.APPLICATION_JSON)
    def collectorList() {
        try {
            monitoringService.getCollectorList()as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/deviceList")
    @Produces(MediaType.APPLICATION_JSON)
    def getDeviceList(@QueryParam("instanceId") String instanceId) {
        try {
            monitoringService.deviceList(instanceId)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/addDevice")
    @Produces(MediaType.APPLICATION_JSON)
    def addDevice(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            monitoringService.enableMonitorJob(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
     @POST
     @Path("/getDeviceStatus")
     @Produces(MediaType.APPLICATION_JSON)
     def getDeviceStatus(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
             monitoringService.deviceEnabledStatus(requestData)as JSON
         } catch (Exception ex) {
                [ex] as JSON
        }
     }
     
    @POST
    @Path("/disbleDevice")
    @Produces(MediaType.APPLICATION_JSON)
    def disbleDevice(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            monitoringService.disableMonitoringDevice(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }        
}
