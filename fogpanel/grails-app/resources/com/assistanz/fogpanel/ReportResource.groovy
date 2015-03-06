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

@Path('/api/report')
class ReportResource {
    
    ReportService reportService

    @GET
    @Produces('text/plain')
    String getReportRepresentation() {
        'Report'
    }
    
    @GET
    @Path("/serviceChangeLog")
    @Produces(MediaType.APPLICATION_JSON)
    def getServiceChangeLogHistory() {
        
        reportService.getServiceChangeLogHistory() as JSON 
        
    }
    
    
    @POST
    @Path("/billableItemChartReport")
    @Produces(MediaType.APPLICATION_JSON)
    def billableItemChartReport(@RequestBody String requestBody) {
        
        // convert string to json object
        def requestData = JSON.parse(requestBody)  
        
        reportService.billableItemChartReport(requestData) as JSON
    }
    
    @POST
    @Path("/billableItemCountChartReport")
    @Produces(MediaType.APPLICATION_JSON)
    def billableItemCountChartReport(@RequestBody String requestBody) {
        
        // convert string to json object
        def requestData = JSON.parse(requestBody)  
        
        reportService.billableItemCountChartReport(requestData) as JSON
    }
    
}
