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


@Path('/api/SSO/')
class SSOResource {
    
    SSOService ssoservice
    
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    def getCredentials(@QueryParam("token") String token) { 
        println("**********sso ********")
        try {       
            
            if(token != null && !token.empty) {
                
                println("******* token *****"+token);
                println("******* ssoservice *****"+(ssoservice==null));
                
                ssoservice.getCredentials(token) as JSON;
            }
        } catch (Exception ex) {            
            [ex.message] as JSON
        } 
    }
	
}

