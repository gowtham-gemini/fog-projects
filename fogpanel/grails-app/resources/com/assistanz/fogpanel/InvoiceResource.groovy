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
import com.assistanz.fogpanel.Config

@Path('/api/invoice')
class InvoiceResource {

    InvoiceService invoiceService
    def springSecurityService
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getInvoiceRepresentation(@QueryParam("accountId") Long accountId) {
        invoiceService.list(accountId) as JSON
    }
    
    @GET
    @Path('/chart')
    @Produces(MediaType.APPLICATION_JSON)
    def chartData(@QueryParam("accountId") Long accountId) {
        try {                        
            invoiceService.chartData(accountId)as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
//    @GET
//    @Path('/createNewInvoiceForPrepaid')
//    @Produces(MediaType.APPLICATION_JSON)
//    def createNewInvoiceForPrepaid() {
//        try {                        
//            invoiceService.createNewPrepaidInvoice()as JSON
//        } catch (Exception ex) {
//                [ex] as JSON
//        } catch (ValidationException ex) {
//                [ex] as JSON
//        }
//    }
    
    @GET
    @Path('/chart/billableItem')
    @Produces(MediaType.APPLICATION_JSON)
    def billableItemChartData() {
        try {                        
            invoiceService.billableItemChartData()as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
    @GET 
    @Path('/chart/dailyUsage')
    @Produces(MediaType.APPLICATION_JSON)
    def dailyUsageChartData() {
        try {                        
            invoiceService.dailyUsageChartData()as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
    @GET
    @Path('/customItem')
    @Produces(MediaType.APPLICATION_JSON)
    def customItem() {
        try {                        
            invoiceService.customItem()as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
    @GET
    @Path('/incomeForecast')
    @Produces(MediaType.APPLICATION_JSON)
    def incomeForecast() {
        try {                        
            invoiceService.incomeForecast()as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
        
    }
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def addManualItem(@RequestBody String requestBody) {
        
        try {  
            // convert string to json object
            def requestData = JSON.parse(requestBody)  
            
            invoiceService.addManualItem(requestData);
                ["OK"] as JSON  
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        } 
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def count(@QueryParam("status") String status) {
        try {           
            invoiceService.count(status) as JSON 
            
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }    
        
    }
    
    @GET
    @Path('/lateFee')
    @Produces(MediaType.APPLICATION_JSON)
    def addLateFee(@RequestBody String requestBody) {
        try {
//            def account = springSecurityService.currentUser.account
//            def invoice = Invoice.findWhere(account: account, status: InvoiceStatus.values()[6])
                        
            invoiceService.addLateFee();
                ["OK"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    def get(@PathParam("id") String id) {
//        return Invoice.get(id) as JSON
//    }
    
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getInvoice(@PathParam("id") Long id) {
        invoiceService.getInvoice(id) as JSON
    }
    
    @GET
    @Path('/checkCreditLimit')
    @Produces(MediaType.APPLICATION_JSON)
    def checkCreditLimit(@RequestBody String requestBody) {
        try {
            def account = springSecurityService.currentUser.account
                        
            invoiceService.processForCreditLimit(account);
                ["OK"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        }
        
    }
    
    
    @POST 
    @Path("/refund")
    @Produces(MediaType.APPLICATION_JSON)
    def refundAmount(@RequestBody String requestBody) {
        try {  
            invoiceService.addRefundAmount(requestBody) as JSON;
        } catch (RuntimeException ex) {
                [ex] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
}
