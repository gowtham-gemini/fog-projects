package com.assistanz.fogpanel

import org.springframework.security.core.AuthenticationException
import org.springframework.security.authentication.AccountStatusException 
import org.springframework.security.authentication.BadCredentialsException 


class CSAccountNotFound extends AccountStatusException {
    
    public CSAccountNotFound(String msg) {
        super(msg);
    }
    
    public CSAccountNotFound(String msg, Throwable t)  {
        super(msg, t);
    }
}

