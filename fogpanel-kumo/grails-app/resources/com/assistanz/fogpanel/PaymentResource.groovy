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

@Path('/api/payment')
class PaymentResource { 

    PaymentService paymentService;
    PaymentCCAvenueService paymentCCAvenueService;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getPaymentRepresentation(@QueryParam("accountId") Long accountId) {
        paymentService.list(accountId) as JSON
    }
    
    @GET
    @Path('/count')
    @Produces(MediaType.APPLICATION_JSON)
    String count(@QueryParam("month") String month) {
        paymentService.count(month) as JSON
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def makePayment(@RequestBody String requestBody) {
        try {
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
            
            paymentService.addManualPayment(requestData)
                ["OK"] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        }  catch (Exception ex){
                [ex] as JSON
        }           
    }
    
    @POST
    @Path('/saveCard')
    @Produces(MediaType.APPLICATION_JSON)
    def saveCard(@RequestBody String requestBody) {
        try {
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
            
            paymentService.saveCard(requestData)
                ["OK"] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
    
    @GET
    @Path('/chart')
    @Produces(MediaType.APPLICATION_JSON)
    def chartData(@QueryParam("accountId") Long accountId) {
        try {                        
            paymentService.chartData(accountId)as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
    @GET
    @Path('/chart/month')
    @Produces(MediaType.APPLICATION_JSON)
    def paymentChartData(@QueryParam("accountId") Long accountId) {
        try {                        
            paymentService.paymentChartData(accountId)as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
    @GET
    @Path('/pending')
    @Produces(MediaType.APPLICATION_JSON)
    def pendingPayment() {
        try {                        
            paymentService.pendingPaymentList()as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    @POST
    @Path('/ccAvenue')
    @Produces(MediaType.APPLICATION_JSON)
    def ccAvenuePayment(@RequestBody String requestBody) {
        try {
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
            paymentCCAvenueService.check(requestData)as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
    
    @POST
    @Path('/manualPaymentEntry')
    @Produces(MediaType.APPLICATION_JSON)
    def manualEntryForPayment(@RequestBody String requestBody) {
        try {
            //convert requestBody to json
            def requestData = JSON.parse(requestBody)
            
            paymentService.manualEntryForPayment(requestData)
                ["OK"] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }           
    }
}
