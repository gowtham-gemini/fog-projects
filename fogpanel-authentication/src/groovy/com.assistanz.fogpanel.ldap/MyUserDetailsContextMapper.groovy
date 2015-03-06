package com.assistanz.fogpanel.ldap


import com.assistanz.fogpanel.PersistentSession
import com.assistanz.fogpanel.Role
import com.assistanz.fogpanel.User
import com.assistanz.fogpanel.ldap.MyUserDetails
import org.springframework.ldap.core.DirContextAdapter
import org.springframework.ldap.core.DirContextOperations
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils

class MyUserDetailsContextMapper implements UserDetailsContextMapper {
    UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection authorities) {
        
        //Grab the specific Active Directory information you want
//        String fullname = ctx.originalAttrs.attrs['uid'].values[0]
//        //  String email = ctx.originalAttrs.attrs['mail'].values[0].toString().toLowerCase()
//        def pass = ctx.getStringAttribute("objectClass");
//        def pass1 = ctx.getAttributes();
//        def pass2 = ctx.getStringAttribute("objectClass[2]");
//        println("Password  :"  + pass)  

        
         //getting request object
        GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
        def httpRequest = webUtils.getCurrentRequest()  
        
        //getting session
        def session = httpRequest.getSession(false)
        
        println("************************** session info ****************")
        
        
        // persisting the session info in db
        try {
            println("session id:  "+session.getId())
            PersistentSession  persistentSession = new PersistentSession()
            persistentSession.creationTime = System.currentTimeMillis()
            persistentSession.lastAccessedTime = persistentSession.creationTime
            persistentSession.id = session.getId()
            
            def jsonBuilder = new groovy.json.JsonBuilder()
            jsonBuilder.data(
                uid: username,
                pwd: session?.getAttribute('password')
            )
            def jsonData = jsonBuilder.toPrettyString();
            
            def encoded = jsonData.bytes.encodeBase64().toString()
            
//            def decoded = new String(encoded.decodeBase64())
           
            persistentSession.data = encoded;

            persistentSession.save(failOnError: true, flush: true)
            println("*** session info persisted");
        }
        catch (e) {
            println("inside exception")
            throw e
        }
        

        def userDetails
      
        //        def password = ctx.getAttributes("userPassword");
        
//        println("Password  :"  + pass)            

        //        def password = ctx.getAttributes("userPassword");       

        User.withTransaction { status ->            

            
            User user  = User.findByUsername(username)                                                    
            println(user)
            authorities = user ?.authorities.collect { new GrantedAuthorityImpl(it.authority) }                        
//            authorities = authorities ?: GormUserDetailsService.NO_ROLES                                                    
            println(" Is user presernt ? :  " + user != null )                                                          
            println(" Is user presernt ? :  " + authorities )                                                          
            //User password set to null 
            userDetails = new MyUserDetails(username,  "", 
                user.enabled, !user.accountExpired, !user.passwordExpired,
                !user.accountLocked, authorities,user.firstName,
                user.email,user.lastName,user.uuid)                                            
            
        }                          
        return userDetails            
    }    

    void mapUserToContext(UserDetails user, DirContextAdapter ctx) {        
        
        println(" Not able to map user in the context ")        
        
        throw new IllegalStateException("Only retrieving data from LDAP is currently supported")
    }

}
