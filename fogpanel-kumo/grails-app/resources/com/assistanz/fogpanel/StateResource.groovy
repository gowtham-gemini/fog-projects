package com.assistanz.fogpanel

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
import grails.converters.deep.JSON

@Path('/api/state')
class StateResource {

    @GET
     @Produces(MediaType.APPLICATION_JSON)
    String getStateRepresentation() {
        'State'
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def get(@PathParam("id") String id) {
        State.findAllWhere(country: Country.findById(id)) as JSON
    }
    
}
