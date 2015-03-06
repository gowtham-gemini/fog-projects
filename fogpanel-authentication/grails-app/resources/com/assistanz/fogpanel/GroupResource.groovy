package com.assistanz.fogpanel

import grails.converters.JSON
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType;

@Path('/api/group')
class GroupResource {
    
    GroupService groupService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    def getGroupsList() {
        try { 
            groupService.getList() as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/view")
    def getGroupView() {
        try { 
            groupService.getView() as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/user/list")
    @Produces(MediaType.APPLICATION_JSON)
    String getUserDetails(@QueryParam("id") String id) {
        try {
            Console.print(id)
            groupService.getUserDetails(id) as JSON
        } catch(Exception ex){
            throw ex
        }
        
    }
    
    
}
