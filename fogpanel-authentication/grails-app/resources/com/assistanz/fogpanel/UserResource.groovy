package com.assistanz.fogpanel

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
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
import com.auth.uitls.LdapUtils
import grails.converters.deep.JSON


@Path('/api/user')
class UserResource {
    
    UserService userService
    GroupService  groupService 
    ProjectService projectService    
    LdapUtils ldapUtils           

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getUserRepresentation() {
        try {             
            println("in index")
            //            ldapUtils.getAllUsers() as JSON
            userService.list()
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/currentUser")
    @Produces(MediaType.APPLICATION_JSON)
    def getCurrentUser() {
        userService.getCurrentUser() as JSON
    }
    
    @PUT
    @Path("/resetPassword")
    @Produces(MediaType.APPLICATION_JSON)
    def resetPassword(@RequestBody String requestBody) {
        try {  
            def response = userService.resetPassword(requestBody)
            return response;
                
        } catch (ValidationException ex) {
            [ex] as JSON
        } catch (NullPointerException ex) {
            [ex] as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/setupVerification")
    @Produces(MediaType.APPLICATION_JSON)
    def userSetupVerfication() {
        try {  
            userService.userSetupVerfication() as JSON
        } catch (Exception ex){
            [ex] as JSON
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/pullFromLdap")
    def pullUsersFromLdap() {
        try {             
            
            println("Pull all from LDAP ")
            
            userService.syncLdapUsers()
            groupService.syncLdapGroups()        
            userService.assignAdminRole()        
            userService.assignUserRole()
            projectService.syncProject()            
            
            println("after pull all LDAP ")
            
            ["OK"] as JSON;
            
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/list")
    String getUsers(@QueryParam("accountId") String accountId) {
        try { 
            userService.getUsersByAccountId(accountId) as JSON;
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/userList")
    String getAllUsers() {
        try { 
            Console.print("Resource")
            userService.listUsers() as JSON;
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    def create(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            userService.create(requestData) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    def update(@RequestBody String requestBody) {
        try { 
             
            def requestData = JSON.parse(requestBody)
            println("requestBody"+requestData);
            userService.update(requestData) as JSON
        } catch (Exception ex) {
            ex.printStackTrace();
            [ex] as JSON
        }
    }
    
    @GET
    @Path("/view")
    @Produces(MediaType.APPLICATION_JSON)
    def viewUsers(@QueryParam("id") String id) {
        try { 
            userService.viewUser(id) as JSON
        } catch (Exception ex) {
            [ex] as JSON
        }
    }
}