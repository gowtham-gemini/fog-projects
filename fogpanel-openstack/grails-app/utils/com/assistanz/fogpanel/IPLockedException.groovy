package com.assistanz.fogpanel

import org.springframework.security.core.AuthenticationException
import org.springframework.security.authentication.AccountStatusException 
import org.springframework.security.authentication.BadCredentialsException 

/**
 *
 * @author gowtham
 */
class IPLockedException extends AccountStatusException {
    
    public IPLockedException(String msg) {
        super(msg);
    }
    
    public IPLockedException(String msg, Throwable t)  {
        super(msg, t);
    }
}

