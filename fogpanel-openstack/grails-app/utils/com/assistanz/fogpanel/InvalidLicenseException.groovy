package com.assistanz.fogpanel

/**
 *
 * @author Gowtham
 */
public class InvalidLicenseException extends RuntimeException {
    
    public InvalidLicenseException() {
        super();
    }
    
    public InvalidLicenseException(String message) {
        super(message);
    }
    
    public InvalidLicenseException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public InvalidLicenseException(Throwable cause) {
        super(cause);
    }
    
    protected InvalidLicenseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    
}

