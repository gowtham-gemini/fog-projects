package com.assistanz.fogpanel

import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Gowtham
 */
class DebugLogger {
	
    
    private static final log = LogFactory.getLog(this)
    
    public static BigInteger printLog(info) {
         def debug =  ApplicationHolder.getApplication().config.debug.log
        
        if(debug == true) {
            Console.print(info)
            log.debug(info) 
        }
    }
    
}

