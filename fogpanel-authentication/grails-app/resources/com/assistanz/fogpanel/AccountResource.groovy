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
import com.assistanz.fogpanel.AccountService;
import org.codehaus.groovy.grails.commons.ApplicationHolder


@Path('/api/account')
class AccountResource {
	
    AccountService accountService;
    UserService userService
    UsageService  usageService;
    
//    BandwidthService bandwidthService
//    SecurityGroupService securityGroupService
//    PaymentService paymentService

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getAccountRepresentation(@QueryParam("email") String email, 
        @QueryParam("username") String userName) {
        try {
            println("in list ")
           accountService.list(email, userName) as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    String get(@PathParam("id") String id) {
        try { 
            accountService.getAccountFromId(id) as JSON;
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/invitation")
    @Produces(MediaType.APPLICATION_JSON)
    String getInvitations() {
        try { 
            accountService.getInvitation() as JSON;
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    @GET
    @Path("/invitation/email/validate")
    @Produces(MediaType.APPLICATION_JSON)
    String validateEmail(@QueryParam("email") String email) {
        try { 
            accountService.validateInvitationEmail(email) as JSON;
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/invitation/send")
    @Produces(MediaType.APPLICATION_JSON)
    def sendSignupInvitation(@RequestBody String requestBody) {
        try {             
            accountService.sendSignupInvitation(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        } 
    }
    
    @POST
    @Path("/invitation/update")
    @Produces(MediaType.APPLICATION_JSON)
    def updateInvitation(@RequestBody String requestBody) {
        try {             
            accountService.updateInvitation(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        } 
    }
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    def count() {
        try {           
            accountService.count() as JSON             
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }            
    }
    
    @GET
    @Path("/creditLimit")
    @Produces(MediaType.APPLICATION_JSON)
    def creditLimitStatus() {
        try {           
            accountService.creditLimitStatus() as JSON             
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }            
    }
    
    @POST
    @Path("/addAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    def addAdminUser(@RequestBody String requestBody) {
        try {           
            accountService.addAdminUser(requestBody)
             ["OK"] as JSON   
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }            
    }
    
    @GET
    @Path("/listAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    def listAdminUser() {
        try {           
            accountService.listAdminUser() as JSON             
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }            
    }
    
    @GET
    @Path("/validateAdmin")
    @Produces(MediaType.APPLICATION_JSON)
    def validateAdmin(@QueryParam("username") String userName) {
        try {           
            accountService.validateAdminUserName(userName) as JSON    
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }            
    }
    
    @GET
    @Path("/currentAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def getCurrentAccount() {
        accountService.getCurrentAccount() as JSON 
    }
    
    @GET
    @Path("/cardVerified")
    @Produces(MediaType.APPLICATION_JSON)
    def cardVerified() {
        accountService.cardVerified() as JSON
    }
    
    @GET
    @Path("/currentUsage")
    @Produces(MediaType.APPLICATION_JSON)
    def currentUsage() {
        try {
//            usageService.populateUsageRecord() as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/currentVMSnapUsage")
    @Produces(MediaType.APPLICATION_JSON)
    def currentVMSnapUsage() {
        try {
//            usageService.calcVMSnapshotUsageForAllAccount() as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/cleanCancelAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def cleanCancelAccount() {
        try {
            accountService.cleanCancelAccount() as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path('/dailyUsageCheck')
    @Produces(MediaType.APPLICATION_JSON)
    def dailyUsageCheck() {
        try {
//            usageService.dailyUsageCheck();
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        } 
        
    }
    
    @GET
    @Path("/calcVMSnapshotUsageForAllAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def calcVMSnapshotUsageForAllAccount() {
        try {
            invoiceService.calcVMSnapshotUsageForAllAccount() as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    @GET
    @Path("/getAPIKey/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getAPIkey(@PathParam("id") String id) {
        try {
            accountService.getAPIkey(id) as JSON
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
    
    @PUT 
    @Path("/resetAPIKey/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def resetAPIKey(@PathParam("id") String id) {
        try {  
            accountService.resetAPIKey(id) 
            
            ["OK"] as JSON;
               
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @PUT 
    @Path("/revokeAPIKey/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def revokeAPIKey(@PathParam("id") String id) {
        try {  
            accountService.revokeAPIKey(id) 
            
            ["OK"] as JSON;
               
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @PUT 
    @Path("/grantAPIKey/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def grantAPIKey(@PathParam("id") String id) {
        try {  
            accountService.grantAPIKey(id) 
            
            ["OK"] as JSON;
               
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    
    @GET 
    @Path("/getBand")
    @Produces(MediaType.APPLICATION_JSON)
    def getUsageRec() {
        try {
//            bandwidthService.calc() as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }    
    
    @GET
    @Path("/ipAddress")
    @Produces(MediaType.APPLICATION_JSON)
    def getIpAddressesList() {
        accountService.getIpAddressesList() as JSON
    }
        
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def signup(@RequestBody String requestBody) {
        try {
            accountService.registerAccount(requestBody);
                ["OK"] as JSON
        } catch (CardValidationException ex) {
                [ex] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        } 
        
    }
    
    @PUT 
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try {  
            accountService.update(requestBody) as JSON;
               
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @PUT 
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteAdminUser(@RequestBody String requestBody) {
        try {  
            accountService.deleteAdminUser(requestBody);
                ["OK"] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST 
    @Path("/admin/resetPassword")
    @Produces(MediaType.APPLICATION_JSON)
    def resetPasswordForAdminUser(@RequestBody String requestBody) {
        try {  
            accountService.resetPasswordForAdminUser(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/cancelAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def cancelAccount(@RequestBody String requestBody) {
        try {  
            accountService.cancelAccount(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST 
    @Path("/resetPassword")
    @Produces(MediaType.APPLICATION_JSON)
    def resetPassword(@RequestBody String requestBody) {
        try {  
            accountService.resetPasswordForAccount(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/disableAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def disableAccount(@RequestBody String requestBody) {
        try {  
            accountService.disableAccount(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/enableAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def enableAccount(@RequestBody String requestBody) {
        try {  
            accountService.enableAccount(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/suspendAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def suspendAccount(@RequestBody String requestBody) {
        try {  
            accountService.suspendAccount(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/closeAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def closeAccount(@RequestBody String requestBody) {
        try {  
            accountService.closeAccount(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/settings/sendMail")
    @Produces(MediaType.APPLICATION_JSON)
    def sendSettingsInfoMail(@RequestBody String requestBody) {
        try {             
            accountService.sendSettingsInfoMail(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/lockAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def lockAccount(@RequestBody String requestBody) {
        try {  
            accountService.lockAccount(requestBody) as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    @POST
    @Path("/unLockAccount")
    @Produces(MediaType.APPLICATION_JSON)
    def unLockAccount(@RequestBody String requestBody) {
        try {  
            accountService.unLockAccount(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    
     
    @GET
    @Path("/bandwidth")
    @Produces(MediaType.APPLICATION_JSON)
    def getBandwidth(@QueryParam("zoneReferenceId") String zoneReferenceId) {
        try {  

//            bandwidthService.count(zoneReferenceId) as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/usage")
    @Produces(MediaType.APPLICATION_JSON)
    def getUsage() {
        try {  
            usageService.usageCount() as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    
    @GET
    @Path("/resourceLimit")
    @Produces(MediaType.APPLICATION_JSON)
    def resourceLimit() {
        try {  
            accountService.resourceLimit() as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/SSHKey/list")
    @Produces(MediaType.APPLICATION_JSON)
    String getSSHKeyList() {  
//        accountService.listSSHKey() as JSON  
    }
    
    
    @POST
    @Path("/SSHKey/create")
    @Produces(MediaType.APPLICATION_JSON)
    def createSSHKey(@RequestBody String requestBody) {
        try {  
//            accountService.createSSHKey(requestBody) as JSON;  
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/SSHKey/reset")
    @Produces(MediaType.APPLICATION_JSON)
    def resetSSHKey(@RequestBody String requestBody) {
        try {  
//            accountService.resetSSHKeyForVirtualMachine(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    
    
    @POST
    @Path("/SSHKey/delete")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteSSHKey(@RequestBody String requestBody) {
        try {  
//            accountService.deleteSSHKey(requestBody)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON
        } catch (LicenseExpiredException ex) {
                [result: "licenseExpired", message: "License Expired! Contact Support"] as JSON
        } catch (NullPointerException ex){
                [ex] as JSON
        } catch (Exception ex){
                [ex] as JSON
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pullFromOpenStack")
    def pullAccountsFromOpenStack() {
        try { 

            ["OK"] as JSON;
        } catch (Exception ex) {
                [ex] as JSON
        }
    }
    
//    @GET
//    @Path("/view")
//    @Produces(MediaType.APPLICATION_JSON)
//    def viewUsers(@QueryParam("id") String id) {
//        try { 
//            userService.viewUser(id) as JSON
//        } catch (Exception ex) {
//                [ex] as JSON
//        }
//    }
    
}
