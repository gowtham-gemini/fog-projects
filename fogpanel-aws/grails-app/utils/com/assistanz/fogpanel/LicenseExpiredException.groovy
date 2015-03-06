package com.assistanz.fogpanel

/**
 *
 * @author Gowtham
 */
public class LicenseExpiredException extends RuntimeException {
    
    public LicenseExpiredException() {
        super();
    }
    
    public LicenseExpiredException(String message) {
        super(message);
    }
    
    public LicenseExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public LicenseExpiredException(Throwable cause) {
        super(cause);
    }
    
    protected LicenseExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    
}

