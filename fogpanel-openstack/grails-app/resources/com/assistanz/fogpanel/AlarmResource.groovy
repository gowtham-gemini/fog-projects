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

@Path('/api/alarm')
class AlarmResource {

    AlarmService alarmService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getAlarmList(@QueryParam("serverId") String instanceId, @QueryParam("alarmId") String alarmId) {
        try {
            alarmService.getList(instanceId, alarmId)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/fileSystem/PartitionList")
    @Produces(MediaType.APPLICATION_JSON)
    def getFileSystemPartitionList(@QueryParam("osType") String osType, @QueryParam("deviceId") String deviceId) {
        try {
          
            alarmService.getFileSystemPartitionTemplates(osType,deviceId)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def add(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            alarmService.create(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteAlarm(@RequestBody String requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)
            alarmService.delete(requestData) as JSON

        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/enable")
    @Produces(MediaType.APPLICATION_JSON)
    def enableAlarm(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            alarmService.enable(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/disable")
    @Produces(MediaType.APPLICATION_JSON)
    def disableAlarm(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            alarmService.disable(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
        
    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    def updateAlarm(@RequestBody String requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)
            alarmService.update(requestData)as JSON
            
        } catch (Exception ex) {
                [ex] as JSON
        }
    }   
    
    
}
