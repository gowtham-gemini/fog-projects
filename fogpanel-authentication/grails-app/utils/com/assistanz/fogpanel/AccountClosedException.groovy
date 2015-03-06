package com.assistanz.fogpanel

import org.springframework.security.core.AuthenticationException
import org.springframework.security.authentication.AccountStatusException 
import org.springframework.security.authentication.BadCredentialsException 

/**
 *
 * @author gowtham
 */
class AccountClosedException extends AccountStatusException {
    
    public AccountClosedException(String msg) {
        super(msg);
    }
    
    public AccountClosedException(String msg, Throwable t)  {
        super(msg, t);
    }
}

