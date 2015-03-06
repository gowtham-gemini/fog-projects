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

@Path('/api/promotion')
class PromotionResource {

    PromotionService promotionService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getPromotionRepresentation() {
        promotionService.list() as JSON
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    String get(@PathParam("id") Long id) {
        try { 
            Promotion.get(id) as JSON;
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {
            promotionService.create(requestBody)
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
    
    
    @GET
    @Path("/validate")
    @Produces(MediaType.APPLICATION_JSON)
    def validate(@QueryParam("code") String code) {
        try {
            promotionService.validate(code)as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") String id) {
        try {
            promotionService.deletePromotionCode(id)
                
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
}
