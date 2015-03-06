package com.assistanz.fogpanel

import javax.ws.rs.QueryParam
import java.util.Scanner
import java.io.ByteArrayOutputStream

import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityService

class MonitoringController {
    
    MonitoringService monitoringService;
    
    @Secured(['permitAll']) 
    def getGraph(@QueryParam("deviceId") String deviceId) {       
        
        def base64String = monitoringService.getGraph("/zport/dmd/Devices/Server/Linux/devices/192.168.1.162");        
        render base64String;
        
    }   
}
