package com.assistanz.fogpanel

import org.springframework.security.core.AuthenticationException
import org.springframework.security.authentication.AccountStatusException 
import org.springframework.security.authentication.BadCredentialsException 


class OSAccountNotFound extends AccountStatusException {
    
    public OSAccountNotFound(String msg) {
        super(msg);
    }
    
    public OSAccountNotFound(String msg, Throwable t)  {
        super(msg, t);
    }
}

