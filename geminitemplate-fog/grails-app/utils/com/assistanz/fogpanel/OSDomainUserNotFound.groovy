package com.assistanz.fogpanel

import org.springframework.security.core.AuthenticationException
import org.springframework.security.authentication.AccountStatusException 
import org.springframework.security.authentication.BadCredentialsException 


class OSDomainUserNotFound extends AccountStatusException {
    
    public OSDomainUserNotFound(String msg) {
        super(msg);
    }
    
    public OSDomainUserNotFound(String msg, Throwable t)  {
        super(msg, t);
    }
}

