package com.assistanz.fogpanel

import org.springframework.security.core.AuthenticationException
import org.springframework.security.authentication.AccountStatusException 
import org.springframework.security.authentication.BadCredentialsException 


class CSDomainUserNotFound extends AccountStatusException {
    
    public CSDomainUserNotFound(String msg) {
        super(msg);
    }
    
    public CSDomainUserNotFound(String msg, Throwable t)  {
        super(msg, t);
    }
}

