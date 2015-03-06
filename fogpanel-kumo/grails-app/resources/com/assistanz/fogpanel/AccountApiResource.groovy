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
import javax.ws.rs.QueryParam
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.QueryParam
import grails.converters.deep.JSON
import com.assistanz.fogpanel.Config
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path('/api/admin/account')
class AccountApiResource {    
    AccountApiService accountApiService
    APIArgumentValidation apiArgumentValidation = new APIArgumentValidation()
    
    @GET
    @Path("/createAccount")
    @Produces(MediaType.APPLICATION_JSON) 
    def createAccount(@QueryParam("userName") String username, @QueryParam("firstName") String firstname, 
                        @QueryParam("lastName") String lastname, 
                        @QueryParam("password") String password,  @QueryParam("streetAddress") String street,  
                        @QueryParam("city") String city, @QueryParam("country") String country, @QueryParam("state") String state, @QueryParam("zip") String zip,
                        @QueryParam("phoneNumber") String phonenumber, @QueryParam("extendedAddress") String streetExtension) { 
        try {
                       
            if(username && firstname && lastname && password && street && city && country && state && zip && phonenumber) {
                Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
                Matcher mat = pattern.matcher(username);
                if(mat.matches()) {
                    def account = Account.findByUserName(username)
                    if(account) {
                        throw new UserNameAlreadyExistException("{'errorCode':'4000' ,'message':'userName already exists'}");
                    }
                } else {
                    throw new InvalidUserNameException("{'errorCode':'2000' ,'message':'Invalid userName value'}");
                }
                apiArgumentValidation.countryValidation(country)
                apiArgumentValidation.stateValidation(state)
                accountApiService.createAccount(username, firstname, lastname, password, street, city, country, state, zip, phonenumber, streetExtension)
            } else {
                def errorResponse = "";
                
                if(!username) {
                    errorResponse += "{'errorCode':'1000','message':'userName required'},"
                } 
                if(!firstname) {
                    errorResponse += "{'errorCode':'1000','message':'firstName required'},"
                } 
                if(!lastname) {
                    errorResponse += "{'errorCode':'1000','message':'lastName required'},"
                } 
                if(!password) {
                    errorResponse += "{'errorCode':'1000','message':'password required'},"
                } 
                if(!street) {
                    errorResponse += "{'errorCode':'1000','message':'streetAddress required'},"
                } 
                if(!city) {
                    errorResponse += "{'errorCode':'1000','message':'city required'},"
                } 
                if(!country) {
                    errorResponse += "{'errorCode':'1000','message':'country required'},"
                } 
                if(!state) {
                    errorResponse += "{'errorCode':'1000','message':'state required'},"
                } 
                if(!zip) {
                    errorResponse += "{'errorCode':'1000','message':'zip required'},"
                } 
                if(!phonenumber) {
                     errorResponse += "{'errorCode':'1000','message':'phonenumber required'},"
                }
                [errorResponse] as JSON
            }
                
        } catch (RuntimeException ex) {
            [ex.message] as JSON
        } catch (Exception ex) {
            [ex.message] as JSON
        }
    }
    
    @GET
    @Path("/listAccount")
    @Produces(MediaType.APPLICATION_JSON) 
    def listAccount(@QueryParam("status") String status, @QueryParam("fromDate") String fromDate, @QueryParam("toDate") String toDate, @QueryParam("page") String pageNo, @QueryParam("recordPerPage") String recordPerPage) { 
        try {                        
            if(fromDate) {
                apiArgumentValidation.dateValidation(fromDate)
            }
            if(toDate) {
                apiArgumentValidation.dateValidation(toDate)
            }
            if(fromDate && toDate) {
                apiArgumentValidation.dateValidation(fromDate, toDate)
            }
            if(status) {
                 apiArgumentValidation.accountStatusValidation(status)
            }
            if(pageNo) {                
                apiArgumentValidation.numberValidation(pageNo);
            }
            if(recordPerPage) {                
                apiArgumentValidation.numberValidation(recordPerPage);
            }
            accountApiService.listAccount(status, fromDate, toDate, pageNo, recordPerPage) as JSON;
        } catch (Exception ex) {            
            [ex.message] as JSON
        } catch (RuntimeException ex) {
            [ex.message] as JSON
        } 
    }
}
