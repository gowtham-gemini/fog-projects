package com.assistanz.fogpanel

import java.util.logging.Level;
import java.util.logging.Logger;
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

@Path('/api/public')
class PublicResource {

    AccountService accountService;
    PromotionService promotionService 
    UsageService usageService 
    def simpleCaptchaService;
    
    
       
    @GET
    @Path("/country")
    @Produces(MediaType.APPLICATION_JSON)
    def getcountry() {
         Country.findAll()as JSON;
    }

    @GET
    @Path("/termsAndCondition")
    @Produces(MediaType.APPLICATION_JSON)
    def getConfig() {
        Config.findByName(Config.ORGANISATION_BILLING_TERMS_CONDITIONS) as JSON
    }

    
    @GET
    @Path("/currentUsage")
    @Produces(MediaType.APPLICATION_JSON)
    def currentUsage() {
        try {
            usageService.list() as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/accountValidate")
    @Produces(MediaType.APPLICATION_JSON)
    def accountValidate(@QueryParam("email") String email, 
        @QueryParam("username") String userName) {
        try {
           accountService.list(email, userName) as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/promotionValidate")
    @Produces(MediaType.APPLICATION_JSON)
    def promotionValidate(@QueryParam("code") String code) {
        try {
            promotionService.validate(code)as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
    @GET
    @Path("/state")
    @Produces(MediaType.APPLICATION_JSON)
    def getState(@QueryParam("code") String code) {
        State.findAllWhere(country: Country.findById(code)) as JSON
    }
    
    @POST
    @Path("/captcha")
    @Produces(MediaType.APPLICATION_JSON)
    def validate(@RequestBody String requestBody) {
               
        def requestData = JSON.parse(requestBody)
               
        boolean captchaValid = simpleCaptchaService.validateCaptcha(requestData.captchaValue)   
        [captchaValid] as JSON
    }
    
    
    @POST
    @Path("/accountSignup")
    @Produces(MediaType.APPLICATION_JSON)
    def signup(@RequestBody String requestBody) {
        HashMap errorResult = new HashMap();
                       
        try {
            accountService.registerAccount(requestBody);
                ["OK"] as JSON
        } catch (ValidationException ex) {
            errorResult.put("Exception", "ValidationException");
            errorResult.put("ExceptionResult",ex);
                [errorResult] as JSON
        } catch (Exception ex){
            errorResult.put("Exception", "other");
            errorResult.put("ExceptionResult", ex);
                [errorResult] as JSON
        } 
        
    }
    
    @POST
    @Path("/forgotPassword/sendEmail")
    @Produces(MediaType.APPLICATION_JSON)
    def forgotPasswordMail(@RequestBody String requestBody) {   
        try {
            accountService.forgotPasswordMail(requestBody) as JSON;
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }        
    }
    
    @POST
    @Path("/resendconfirmMail")
    @Produces(MediaType.APPLICATION_JSON)
    def resendconfirmMail(@RequestBody String requestBody) {
        try {
            accountService.resendconfirmMail(requestBody) as JSON;
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }        
    }
    
    @POST
    @Path("/forgotPassword/resetPassword")
    @Produces(MediaType.APPLICATION_JSON) 
    def resetPassword(@RequestBody String requestBody) {
        try {
//            accountService.registerAccount(requestBody);
                ["OK"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }        
    }
}
