package com.assistanz.fogpanel

import com.assistanz.fogpanel.GeneralConstants;
import java.util.StringTokenizer;
import javax.servlet.http.HttpSession
import java.lang.Object;
import org.codehaus.groovy.grails.validation.routines.InetAddressValidator;
import org.codehaus.groovy.grails.web.servlet.mvc.GrailsWebRequest
import org.codehaus.groovy.grails.web.util.WebUtils
import java.math.BigInteger;
import java.security.MessageDigest
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
/**
 *
 * @author Gowtham
 */
class Util {
    def springSecurityService
    public static BigInteger getNumberFromIp(String ip) {
        StringTokenizer ipToken = new StringTokenizer(ip, ".");
        if(ipToken.countTokens() == 4) {
            Long[] ipArray = new Long[4];
            for(int i=0; i < 4; i++ ) {
                ipArray[i] = Long.parseLong(ipToken.nextElement());
            } 
            BigInteger  ipValue = (ipArray[0]*GeneralConstants.IP_L1) + (ipArray[1]*GeneralConstants.IP_L3) + (ipArray[2]*GeneralConstants.IP_L3) + (ipArray[3]);
        return  ipValue; 
        } else {
             throw new Exception("invalid ip address");
        }  
    }
    
    /**
    * Validate ip address with regular expression
    * @param ip ip address for validation
    * @return true valid ip address, false invalid ip address
    */
    public static boolean validate(final String ip){	
         InetAddressValidator  isnetAddressValidator = new InetAddressValidator()
           return isnetAddressValidator.isValidInet4Address(ip)
    }
    
    /**
     Returns currently logged user's password
    */
    public static String getCurrentSessionPassword() {
        
        GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
        def request = webUtils.getCurrentRequest()  
        HttpSession session = request.getSession(false)
        String password = (String)session.getAttribute("password");
        return password;
    }
    /**
     Returns currently logged user's domain name
    */
    public static String getCurrentSessionDomainName() {
        
        GrailsWebRequest webUtils = WebUtils.retrieveGrailsWebRequest()
        def request = webUtils.getCurrentRequest()  
        HttpSession session = request.getSession(false)
        String domainName = (String)session.getAttribute("domainName");
        return domainName;
    }
    
    /**
    Returns token string
    */
   public static String getToken(String userName) {
        
        
        Date date = new Date();
        def time = new Timestamp(date.getTime())
        def token = userName + date.toString()
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(token.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        
        String hashtext = number.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        
        return hashtext;
    }
    
}

