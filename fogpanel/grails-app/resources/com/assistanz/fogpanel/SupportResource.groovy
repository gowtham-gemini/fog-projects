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
import org.codehaus.groovy.grails.commons.ApplicationHolder
import grails.converters.JSON

@Path('/api/support')
class SupportResource {

    SupportService supportService
    
    @GET
    @Produces('text/plain')
    String getSupportRepresentation() {
        'Support'
    }
    
    
    @POST
    @Path("/department/add")
    @Produces(MediaType.APPLICATION_JSON)
    def addDepartment(@RequestBody String requestBody) {
        try {  
            supportService.addDepartment(requestBody);
            ["OK"] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    
    @GET
    @Path("/ticket/list")
    @Produces(MediaType.APPLICATION_JSON)
    def listTicket(@QueryParam("department") String department, 
        @QueryParam("state") String state) {
        try {  
            supportService.listTicket(department, state)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @GET
    @Path("/ticket/notification")
    @Produces(MediaType.APPLICATION_JSON)
    def ticketNotification() {
        try {  
            supportService.ticketNotification() as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    
    @GET
    @Path("/ticket/status/count") 
    @Produces(MediaType.APPLICATION_JSON)
    def ticketStatusCount(@QueryParam("department") String department, @QueryParam("account") String account) {
        try {  
            supportService.ticketStatusCount(department, account)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex) { 
                [ex] as JSON 
        }
    }
    
    
    @GET
    @Path("/ticket/filter") 
    @Produces(MediaType.APPLICATION_JSON)
    def ticketFilter(@QueryParam("departmentId") String departmentId, @QueryParam("status") String status, 
        @QueryParam("userId") String userId, 
        @QueryParam("subject") String subject) {
        try {  
            supportService.ticketFilter(departmentId, status, userId, subject)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex) { 
                [ex] as JSON 
        }
    }
    
    
    
    @POST
    @Path("/ticket/add")
    @Produces(MediaType.APPLICATION_JSON)
    def addTicket(@RequestBody String requestBody) {
        try {  
            supportService.addTicket(requestBody);
            ["OK"] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @POST
    @Path("/ticket/reply")
    @Produces(MediaType.APPLICATION_JSON)
    def replyTicket(@RequestBody String requestBody) {
        try {  
            supportService.replyTicket(requestBody);
            ["OK"] as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @GET
    @Path("/ticket/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getTicket(@PathParam("id") String id) {
        try {  
            supportService.getTicket(id)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @POST
    @Path("/defaultReply/add")
    @Produces(MediaType.APPLICATION_JSON)
    def addDefinedReply(@RequestBody String requestBody) {
        try {  
            supportService.addDefinedReply(requestBody);
            ["OK"] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @GET
    @Path("/department/list")
    @Produces(MediaType.APPLICATION_JSON)
    def listDepartment() {
        try {  
            supportService.listDepartment()as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @GET
    @Path("/defaultReply/list")
    @Produces(MediaType.APPLICATION_JSON)
    def listDefaultReply(@QueryParam("department") String department) {
        try {  
            supportService.listDefaultReply(department)as JSON; 
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @GET
    @Path("/ticket/count")
    @Produces(MediaType.APPLICATION_JSON)
    def ticketCount() {
        try {  
            supportService.ticketCount()as JSON; 
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    
    
    @GET
    @Path("/defaultReply/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def getDefaultReply(@PathParam("id") String id) {
        try {  
            supportService.getDefaultReply(id)as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @PUT
    @Path("/department/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def updateDepartment(@RequestBody String requestBody) {
        try {  
            supportService.updateDepartment(requestBody)
                ["OK"] as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @PUT
    @Path("/defaultReply/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def updateDefaultReply(@RequestBody String requestBody) {
        try {  
            supportService.updateDefaultReply(requestBody);
            ["OK"] as JSON
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    
    @PUT
    @Path("/defaultReply/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteDefaultReply(@PathParam("id") String id) {
        try {  
            supportService.deleteDefaultReply(id)
            ["OK"]as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @PUT
    @Path("/department/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def deleteDep(@PathParam("id") String id) {
        try {  
            supportService.deleteDep(id)
            ["OK"]as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
    
    @GET
    @Path("/graph")
    @Produces(MediaType.APPLICATION_JSON)
    def supportGraph() {
        try {  
            supportService.supportGraph()as JSON;
        } catch (ValidationException ex) {
                [ex] as JSON 
        } catch (NullPointerException ex){
                [ex] as JSON 
        } catch (Exception ex){ 
                [ex] as JSON 
        }
    }
}
