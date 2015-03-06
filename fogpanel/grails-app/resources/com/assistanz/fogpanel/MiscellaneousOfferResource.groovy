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



@Path('/api/miscellaneousOffer')
class MiscellaneousOfferResource {
    MiscellaneousOfferService miscellaneousOfferService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getMiscellaneousOfferRepresentation(@QueryParam("clusterReferenceId") String clusterReferenceId,
    @QueryParam("miscellaneousOfferId") String miscellaneousOfferId) {
        miscellaneousOfferService.list(clusterReferenceId, miscellaneousOfferId) as JSON
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/byZone")
    String getMisc(@QueryParam("zoneReferenceId") String zoneReferenceId, @QueryParam("miscellaneousOfferId") String miscellaneousOfferId) {
        miscellaneousOfferService.getMisc(zoneReferenceId, miscellaneousOfferId) as JSON
    }
    
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@PathParam("id") Long id) {        
        try { 
            miscellaneousOfferService.getCurrentMiscOffer(id) as JSON
        } catch (Exception ex){
                [ex] as JSON
        }         
    }
    
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    def create(@RequestBody String requestBody) {
//        try{
//            
//            // convert string to json object
//            def requestData = JSON.parse(requestBody)
//            
//            def newMiscellaneousOffer = new MiscellaneousOffer();
//            newMiscellaneousOffer.name = requestData.name
//            newMiscellaneousOffer.description = requestData.description
//            newMiscellaneousOffer.unit = requestData.unit
////                       
//            for(int i = 0; i < requestData.zoneCosts.length(); i++){
//          
//                Double zoneCost = new Double(requestData.zoneCosts[i].cost);
//                if(zoneCost == 0.0){
//                    throw new NullPointerException("cost cannot be zero");
//                }
//                newMiscellaneousOffer.addToMiscellaneousOfferZoneCosts(new MiscellaneousOfferZoneCost(
//                        zone:Zone.get(requestData.zoneCosts[i].zoneId), 
//                        cost: zoneCost))            
//            }
//           
//         newMiscellaneousOffer.create(newMiscellaneousOffer)
//                ["OK"]
//        }catch (ValidationException ex) {
//                [ex] as JSON
//        }catch (NullPointerException ex){
//                [ex] as JSON
//        }catch (Exception ex){
//                [ex] as JSON
//        }
//        
//    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try{
            miscellaneousOfferService.update(requestBody)
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
