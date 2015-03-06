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
import javax.ws.rs.QueryParam
import grails.converters.deep.JSON

@Path('/api/captcha')
class CaptchaResource {
    
    def simpleCaptchaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getCaptchaRepresentation() {
        'Captcha'
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def validate(@RequestBody String requestBody) {
               
        def requestData = JSON.parse(requestBody)
               
        boolean captchaValid = simpleCaptchaService.validateCaptcha(requestData.captchaValue)   
        [captchaValid] as JSON
    }
}
