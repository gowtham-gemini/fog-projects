package com.assistanz.fogpanel

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PUT
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType;
import com.assistanz.fogpanel.Zone
import javax.ws.rs.DELETE
import javax.ws.rs.PathParam
import grails.converters.JSON
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.QueryParam;
import org.springframework.web.bind.annotation.RequestBody;

@Path('/api/zone')
class ZoneResource {

    ZoneService zoneService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getZoneRepresentation() {
        zoneService.list() as JSON
    }
    	
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@PathParam("id") String id) {
        Zone.findByReferenceZoneId(id) as JSON
    }
    
    @GET
    @Path("/byId")
    @Produces(MediaType.APPLICATION_JSON)
    def getZone(@QueryParam("zoneId") String zoneId) {
        zoneService.getCurrentZone(zoneId) as JSON
    }
    
    
    
    @GET
    @Path("/alert")
    @Produces(MediaType.TEXT_HTML)
    def getAlert() {
        zoneService.listAlerts()
    }
    
    
    @GET
    @Path("/capacity")
    @Produces(MediaType.APPLICATION_JSON)
    def listCapacity(@QueryParam("type") String type, 
        @QueryParam("itemId") String itemId) {
        zoneService.listCapacity(itemId, type)
    }
    
    @GET
    @Path("/rateCard")
    @Produces(MediaType.APPLICATION_JSON)
    def listAllOffers(@QueryParam("zoneReferenceId") String zoneReferenceId) {
        zoneService.listAllOffers(zoneReferenceId) as JSON
    }

    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    def update(@RequestBody String requestBody) {
        try {
            zoneService.update(requestBody) as JSON;
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
}
