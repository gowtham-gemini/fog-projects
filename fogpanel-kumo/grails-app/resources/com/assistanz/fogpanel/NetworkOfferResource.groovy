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
import com.assistanz.fogpanel.NetworkOffer
import javax.ws.rs.DELETE
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.QueryParam
import grails.converters.deep.JSON

@Path('/api/networkOffer')
class NetworkOfferResource {
    
    NetworkOfferService networkOfferService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getNetworkOfferRepresentation(@QueryParam("zoneReferenceId") String zoneReferenceId) {
        networkOfferService.list(zoneReferenceId) as JSON
    }
    
    
    @GET
    @Path("/forVpc/")
    @Produces(MediaType.APPLICATION_JSON)
    String getNetworkOfferForVpc(@QueryParam("vpcId") String vpcId) {
        networkOfferService.getNetworkOfferForVpc(vpcId) as JSON
    }
    
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@PathParam("id") String id) {
        networkOfferService.getNetworkOffer(id) as JSON
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try {
             // convert string to json object
            def requestData = JSON.parse(requestBody)
             
            def newNetworkOffer = new NetworkOffer();
            newNetworkOffer.name = requestData.name
            newNetworkOffer.description = requestData.description
            newNetworkOffer.networkRate = requestData.networkRate
            newNetworkOffer.tags = requestData.tags
            newNetworkOffer.specifyVlan = requestData.vLan
            newNetworkOffer.conserveMode = requestData.conserveMode
            newNetworkOffer.vpn = requestData.supportedServices[0].vpn
            newNetworkOffer.dhcp = requestData.supportedServices[0].dhcp
            newNetworkOffer.dns = requestData.supportedServices[0].dns
            newNetworkOffer.firewall = requestData.supportedServices[0].firewall
            newNetworkOffer.loadBalancer = requestData.supportedServices[0].loadBalancer
            newNetworkOffer.userData = requestData.supportedServices[0].userData
            newNetworkOffer.sourceNat = requestData.supportedServices[0].sourceNat
            newNetworkOffer.staticNat = requestData.supportedServices[0].staticNat
            newNetworkOffer.portForwarding = requestData.supportedServices[0].portForwarding
            newNetworkOffer.securityGroups = requestData.supportedServices[0].securityGroups
            
            for(int i = 0; i < requestData.zoneCosts.length(); i++){

                Double cost = new Double(requestData.zoneCosts[i].cost);
                if(cost == 0.0){
                    throw new NullPointerException("cost cannot be zero");
                }
                newNetworkOffer.addToNetworkOfferZoneCosts(new NetworkOfferZoneCost(
                        zone:Zone.get(requestData.zoneCosts[i].zoneId), 
                        cost: cost))            
            }
            networkOfferService.create(newNetworkOffer) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try{
            // convert string to json object
            def requestData = JSON.parse(requestBody)

            NetworkOfferZoneCost existsNetworkOfferZoneCost = NetworkOfferZoneCost.findByNetworkOffer(NetworkOffer.get(requestData.id));

            if(existsNetworkOfferZoneCost){

                for(int i = 0; i < requestData.zoneCosts.length(); i++){

                    Double cost = new Double(requestData.zoneCosts[i].cost);
                    if(cost == 0.0){
                        throw new NullPointerException("cost cannot be zero");
                    }
                }
                def oldNetworkOffer = NetworkOffer.get(requestData.id)
                oldNetworkOffer.networkOfferZoneCosts.clear()

                def cost = []
                cost += oldNetworkOffer.networkOfferZoneCosts

                cost.each { networkOfferZoneCost ->
                    oldNetworkOffer.removeFromNetworkOfferZoneCosts(networkOfferZoneCost)
                }

            }    
            def networkOffer =  NetworkOffer.get(requestData.id);   

            for(int i = 0; i < requestData.zoneCosts.length(); i++){

                Double cost = new Double(requestData.zoneCosts[i].cost);
                if(cost == 0.0){
                    throw new NullPointerException("cost cannot be zero");
                }
                networkOffer.addToNetworkOfferZoneCosts(new NetworkOfferZoneCost(
                        NetworkOffer : networkOffer.get(requestData.id),
                        zone:Zone.get(requestData.zoneCosts[i].zoneId), 
                        cost: cost))            
            }
            networkOfferService.update(networkOffer, requestData.name, requestData.description)
                ["OK"]
        }catch (ValidationException ex) {
                [ex] as JSON
        }catch (NullPointerException ex){
                [ex] as JSON
        }catch (Exception ex){
                [ex] as JSON
        }
    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def delete(@PathParam("id") String id) {
         try {
            def response = networkOfferService.delete(id)
                [response] as JSON                   
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        } 
    }
    
}
