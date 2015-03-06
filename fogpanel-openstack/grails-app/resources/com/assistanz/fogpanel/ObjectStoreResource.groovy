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
import grails.converters.deep.JSON;
import org.openstack4j.model.compute.ActionResponse;

@Path('/api/objectStore')
class ObjectStoreResource {
    
    ObjectStoreService objectStoreService;

    @GET
    @Path("/container/list")
    @Produces(MediaType.APPLICATION_JSON)
    String getContainerList() {
        try {
             objectStoreService.containerList() as JSON
        } catch(Exception e) {
            throw e;
        }
    }
    
    @GET
    @Path("/container/view")
    @Produces(MediaType.APPLICATION_JSON)
    def containerDetails(@QueryParam("containerId") String id) {
        try {
            objectStoreService.viewContainer(id) as JSON
        } catch(Exception e) {
            throw e;
        }
    }
    
    @GET
    @Path("/container/objectList")
    @Produces(MediaType.APPLICATION_JSON)
    def getObjectList(@QueryParam("containerId") String containerId, @QueryParam("objectName") String objectName) {
        try {
            objectStoreService.objectList(containerId, objectName) as JSON
        } catch(Exception e) {
            throw e;
        }
    }
    
    @GET
    @Path("/object/view")
    @Produces(MediaType.APPLICATION_JSON)
    def getObjectDetails(@QueryParam("containerId") String containerId, @QueryParam("objectName") String objectName, @QueryParam("pathName") String pathName) {
        try {
            objectStoreService.viewObject(containerId, objectName, pathName) as JSON
        } catch(Exception e) {
            
        }
    }
    
    
    @POST
    @Path("/container/create")
    @Produces(MediaType.APPLICATION_JSON)
    def containerCreation(@RequestBody String requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)
            objectStoreService.createContainer(requestData) as JSON

        } catch(Exception e) {
            throw e;
        }
    }
    
    @POST
    @Path("/object/createPseudoFolder")
    @Produces(MediaType.APPLICATION_JSON)
    def pseudoFolderCreation(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            objectStoreService.addPseudoFolder(requestData)as JSON
            
        } catch(Exception e) {
            throw e;
        }
    }
    
    @PUT
    @Path("/container/updateAccess/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def containerAccessUpdate(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            objectStoreService.updateContainerAccess(requestData) as JSON
        } catch(Exception e) {
            throw e;
        }
    }
    
    @PUT
    @Path("/object/upload/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def upload(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
//            objectStoreService.uploadObject(requestData) as JSON
        } catch(Exception e) {
            throw e;
        }
    }
    
    @POST
    @Path("/object/copy")
    @Produces(MediaType.APPLICATION_JSON)
    def copy(@RequestBody String requestBody) {
        try {
            def requestData = JSON.parse(requestBody)
            objectStoreService.copyObject(requestData)as JSON
        } catch(Exception e) {
            throw e;
        }
    }  
    
    @POST
    @Path("/container/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def removeContainer(@RequestBody String requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)
            objectStoreService.deleteContainer(requestData) as JSON 
                        
        } catch(Exception e) {
            throw e;
        }
    }
    
    @POST
    @Path("/object/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def removeObject(@RequestBody String requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)
            objectStoreService.deleteObject(requestData)as JSON
        } catch(Exception e) {
            throw e;
        }
    }
    
    
}
