package com.assistanz.fogpanel

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces

@Path('/api/usage')
class UsageResource {
    
    UsageService usageService

    @GET
    @Produces('text/plain')
    String getUsageRepresentation() {
       
        usageService.populateUsageRecords()
        
    }
}
