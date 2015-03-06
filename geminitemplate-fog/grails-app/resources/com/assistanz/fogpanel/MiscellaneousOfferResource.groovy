package com.assistanz.fogpanel

import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PUT
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType;
import com.assistanz.fogpanel.MiscellaneousOffer
import org.springframework.web.bind.annotation.RequestBody;
import javax.ws.rs.DELETE
import javax.ws.rs.PathParam
import grails.converters.JSON
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.QueryParam
import com.assistanz.fogpanel.MiscellaneousOfferService

@Path('/api/miscellaneousOffer')
class MiscellaneousOfferResource {
    
    MiscellaneousOfferService miscellaneousOfferService
    
    @GET
//    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@QueryParam("id") String id) {        
        try {            
            miscellaneousOfferService.getCurrentMiscOffer(id) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }         
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try{
            miscellaneousOfferService.updateMisc(requestBody)
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
    
}
