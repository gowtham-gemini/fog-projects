package com.assistanz.fogpanel

import com.assistanz.fogpanel.GeneralConstants;
import java.util.StringTokenizer;
import java.lang.Object;
import org.codehaus.groovy.grails.validation.routines.InetAddressValidator;

/**
 *
 * @author Gowtham
 */
class Util {
            
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
}

