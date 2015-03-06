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
import org.openstack4j.model.compute.ActionResponse;

@Path('/api/network')
class NetworkResource {
    
    NetworkService networkService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String listNetwork(@QueryParam("referenceId") String referenceId) {
        
        networkService.listNetwork(referenceId) as JSON
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pullFromOpenstack")
    def pullFlavorFromOpenstack() {
        try { 
            networkService.pullNetworkFromOpenstack()
            ["OK"] as JSON;
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def createNetwork(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            
            networkService.createNetwork(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def updateNetwork(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)

            networkService.updateNetwork(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteNetwork(@PathParam("id") String referenceId) {  
        try {

            ActionResponse actionResponse = networkService.deleteNetwork(referenceId)
            
            def result = "OK";
                        
            if (actionResponse.isSuccess() == false) {
                result = actionResponse.getFault();
                return result
            } else {
                return result;
            }   
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/subnetList")
    def getSubnetList(@QueryParam("networkId") String networkId, @QueryParam("subnetId") String subnetId) {
        try { 
            networkService.subnetListForNetwork(networkId, subnetId) as JSON

        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/subnetListWithDetail")
   def subnetListWithDetail() {
       try { 
           networkService.detailedSubnetList()as JSON
       } catch (Exception ex) {
                [ex] as JSON
        }
   }
    
    @POST
    @Path("/addSubnet")
    @Produces(MediaType.APPLICATION_JSON)
    def createSubnet(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            
            networkService.createNetworkSubnet(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @PUT
    @Path("/updateSubnet/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def updateSubnet(@RequestBody String requestBody) {
        
        try { 
             
            def requestData = JSON.parse(requestBody)
            
            networkService.updateSubnetNetwork(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
        
    }
    
    @DELETE
    @Path("/deleteSubnet/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteSubnets(@PathParam("id") String referenceId) {  
        try {
            
            ActionResponse actionResponse = networkService.deleteSubnetNetwork(referenceId)
            def result = "OK";
            
            if (actionResponse.isSuccess() == false) {
                result = actionResponse.getFault();
                return result
            } else {
                return result;
            }    
            
            
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
//    
    @GET
    @Path("/portList")
    @Produces(MediaType.APPLICATION_JSON)
    def portList(@QueryParam("networkId") String networkId) {
        try {
             networkService.networkPortList(networkId) as JSON;
             
        } catch(Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/portView")
    def portView(@QueryParam("portId") String portId) {
        try {
            networkService.networkPortView(portId) as JSON;
            
        } catch(Exception ex) {
            [ex] as JSON
        }
    }
    
    @PUT
    @Path("/updatePort/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def portUpdate(@RequestBody String requestBody) {
        
         try { 
             
            def requestData = JSON.parse(requestBody)
            
            networkService.networkPortUpdate(requestData) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/listByAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    def networkListByAdmin(@QueryParam("referenceId") String referenceId) {
        try{
            
            networkService.adminNetworkList(referenceId)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/isExternal")
    @Produces(MediaType.APPLICATION_JSON)
    def externalNetworkList() {
        try {
            networkService.getExternalNetworkList() as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/createByAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    def createByAdmin(@RequestBody String requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)
            
            networkService.createNetworkByAdmin(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/deleteByAdmin/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteNetworkByAdminUser(@PathParam("id") String referenceId) {  
        try {

            ActionResponse actionResponse = networkService.deleteNetworkByAdmin(referenceId)
            
            def result = "OK";
            
            if (actionResponse.isSuccess() == false) {
                result = actionResponse.getFault();
                return result
            } else {
                return result;
            }    
            
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/createPortByAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    def createPort(@RequestBody String requestBody) {
        try {
            
            def requestData = JSON.parse(requestBody)
            
            networkService.createPortByAdmin(requestData)as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @DELETE
    @Path("/deletePortByAdmin/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deletePortByAdmin(@PathParam("id") String referenceId) {
        try{
            ActionResponse actionResponse = networkService.deletePort(referenceId)
            
            def result = "OK";
            
            if (actionResponse.isSuccess() == false) {
                result = actionResponse.getFault();
                return result
            } else {
                return result;
            }
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
}
