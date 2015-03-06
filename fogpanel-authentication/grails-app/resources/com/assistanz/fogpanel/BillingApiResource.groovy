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
import javax.ws.rs.FormParam
import javax.ws.rs.PathParam
import javax.ws.rs.core.Response
import javax.ws.rs.core.Request
import javax.ws.rs.core.UriInfo;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.core.Context;
import javax.ws.rs.QueryParam
import grails.converters.deep.JSON
import com.assistanz.fogpanel.Config
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat
import java.text.SimpleDateFormat

@Path('/api/admin/billing')
class BillingApiResource {

    BillingApiService billingApiService
    APIArgumentValidation apiArgumentValidation = new APIArgumentValidation()
    
    @GET
    @Path("/listInvoice")
    @Produces(MediaType.APPLICATION_JSON)
    def getInvoice(@QueryParam("userName") String username, 
        @QueryParam("fromDate") String fromdate, @QueryParam("toDate") String todate, String date, @QueryParam("page") Integer pageNo, 
        @QueryParam("recordPerPage") Integer recordPerPage) {
        try {
            if(username) {
                apiArgumentValidation.emailValidation(username)
            }
            if(fromdate) {
                apiArgumentValidation.dateValidation(fromdate)
            }
            if(todate) {
                apiArgumentValidation.dateValidation(todate)
            }
            if(fromdate && todate) {
                apiArgumentValidation.dateValidation(fromdate, todate)
            }
            
            if(pageNo) {                
                apiArgumentValidation.numberValidation(pageNo);
            }
            if(recordPerPage) {                
                apiArgumentValidation.numberValidation(recordPerPage);
            }
            billingApiService.getInvoice(username, fromdate, todate, pageNo, recordPerPage)                          
        } catch (Exception ex) {
            [ex.message] as JSON
        } catch (RuntimeException ex) {
            [ex.message] as JSON
        } 
    }
    
    @GET
    @Path("/currentUsage")
    @Produces(MediaType.APPLICATION_JSON)
    def getCurrentUsage(@QueryParam("userName") String username) {
        try {
            if(username) {
                apiArgumentValidation.emailValidation(username)
            }
            billingApiService.getCurrentUsage(username)  
        } catch (Exception ex) {
                [ex.message] as JSON
        } catch (RuntimeException ex) {
                [ex.message] as JSON
        } 
    }
    
    
    @GET
    @Path("/listPayment")
    @Produces(MediaType.APPLICATION_JSON)
    def getPayments(@QueryParam("userName") String username, @QueryParam("fromDate") String fromdate, @QueryParam("toDate") String todate,
    @QueryParam("page") Integer pageNo, @QueryParam("recordPerPage") Integer recordPerPage) {
        try {
            if(username) {
                apiArgumentValidation.emailValidation(username)
            }
            if(fromdate) {
                apiArgumentValidation.dateValidation(fromdate)
            }
            if(todate) {
                apiArgumentValidation.dateValidation(todate)
            }
            if(fromdate && todate) {
                apiArgumentValidation.dateValidation(fromdate, todate)
            }
            
            if(pageNo) {                
                apiArgumentValidation.numberValidation(pageNo);
            }
            if(recordPerPage) {                
                apiArgumentValidation.numberValidation(recordPerPage);
            }
            billingApiService.getPayments(username, fromdate, todate, pageNo, recordPerPage)
        
        } catch (Exception ex) {
            [ex.message] as JSON
        } catch (RuntimeException ex) {
            [ex.message] as JSON
        }      
    }
    
    @GET
    @Path("/addPayment")
    @Produces(MediaType.APPLICATION_JSON)
    def addPayments(@QueryParam("userName") String username, @QueryParam("amount") String amount, @QueryParam("date") String date, 
        @QueryParam("paymentCode") String paymentCode) { 
       try {      
            if(username && amount && date && paymentCode) {
                apiArgumentValidation.emailValidation(username)
                apiArgumentValidation.paymentCodeValidation(paymentCode)
                apiArgumentValidation.amountValidation(amount)
                apiArgumentValidation.dateValidation(date)
                billingApiService.addPayments(username, amount, date, paymentCode)  
            } else {
                def errorResponse = "";
                if(!username) {
                    errorResponse += "{'errorCode':'1000','message':'userName required'},"
                } else if(!amount) {
                     errorResponse += "{'errorCode':'1000','message':'amount required'},"
                } else if(!date) {
                     errorResponse += "{'errorCode':'1000','message':'date required'},"
                } else if(!paymentCode) {
                     errorResponse += "{'errorCode':'1000','message':'paymentCode required'},"
                }
                [errorResponse] as JSON
            }
        } catch (Exception ex) {
            [ex.message] as JSON
        } catch (RuntimeException ex) {
            [ex.message] as JSON
        } 
    }
}
