package com.assistanz.fogpanel

import grails.converters.deep.JSON
import javax.ws.rs.GET
import javax.ws.rs.POST;
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType;
import org.springframework.web.bind.annotation.RequestBody

@Path('/api/paymentGateways')
class PaymentGatewaysResource {
    
    PaymentGatewaysService paymentGatewaysService
    
    @GET
    @Path("/listGateways")
    @Produces(MediaType.APPLICATION_JSON)
    def getGateways() {
        paymentGatewaysService.getGateways() as JSON
    }
    
    @POST
    @Path("/gateways")
    @Produces(MediaType.APPLICATION_JSON)
    def updatePaymentGateways(@RequestBody String requestBody) {
        try {            
            paymentGatewaysService.updatePaymentGateways(requestBody) as JSON
        } catch(Exception ex){
                [ex] as JSON
        }        
    }   
}
