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

@Path('/api/storagePool')
class StoragePoolResource {

    StoragePoolService storagePoolService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getStoragePoolRepresentation(@QueryParam("clusterReferenceId") String clusterReferenceId, 
        @QueryParam("storageType") String storageType) {
        try { 
            storagePoolService.list(clusterReferenceId, storageType) as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/capacity/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    String getCapacity(@PathParam("id") String id) {
        storagePoolService.getCapacity(id) as JSON
    }
    
    
}
