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

@Path('/api/admin/staticData')
class PublicApiResource {

    APIArgumentValidation apiArgumentValidation = new APIArgumentValidation()
       
    @GET
    @Path("/listCountry")
    @Produces(MediaType.APPLICATION_JSON)
    def getcountry() {
        def countrylist = Country.findAll();
         
        ArrayList<ArrayList<String>> countryInfo = new ArrayList<ArrayList<String>>();
        for(def country: countrylist) {
            HashMap countryItem = new HashMap();                
            countryItem.put("countryId", country.id);
            countryItem.put("countryName", country.countryName);
            countryInfo.add(countryItem)
        }
        
        return countryInfo as JSON
        
    }
    
    @GET 
    @Path("/listBillableItem")
    @Produces(MediaType.APPLICATION_JSON)
    def getBillableItems() { 
        def billableItemlist = BillableItem.findAll();
         
        ArrayList<ArrayList<String>> billableItemInfo = new ArrayList<ArrayList<String>>();
        for(def billableItem: billableItemlist) {
            HashMap item = new HashMap();                
            item.put("id", billableItem.id);
            item.put("name", billableItem.name);
            billableItemInfo.add(item)
        }
        return billableItemInfo as JSON
    }
    
    
    
    @GET
    @Path("/listState")
    @Produces(MediaType.APPLICATION_JSON)
    def getState(@QueryParam("country") String country) {
        
        try {
            if(country) {
                
                apiArgumentValidation.countryValidation(country)            
                def statelist = State.findAllWhere(country: Country.findById(country))

                ArrayList<ArrayList<String>> stateInfo = new ArrayList<ArrayList<String>>();
                for(def state: statelist) {
                    HashMap stateItem = new HashMap();                
                    stateItem.put("stateId", state.id);
                    stateItem.put("stateName", state.stateName);
                    stateInfo.add(stateItem)
                }

                return stateInfo as JSON
            } else {
               ["{'errorCode':'1000','message':'Required field missing'}"] as JSON 
            }
            
           
        } catch (ValidationException ex) {
            [ex.message] as JSON
        } catch (RuntimeException ex) {
            [ex.message] as JSON
        } catch (NullPointerException ex) {
            [ex.message] as JSON
        } catch (Exception ex){
            [ex.message] as JSON
        }
        
    }
}
