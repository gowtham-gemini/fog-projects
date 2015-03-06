/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.licensor;

/**
 *
 * @author gowtham
 */
class LicenseException extends Exception {
    
    /**
     * Constructor.
     */
    public LicenseException() {
        super();
    }
    
    /**
     * 
     * @param message the license exception message 
     */
    public LicenseException(final String message) {
        super(message);
    }
    
    /**
     * 
     * @param message the license exception message 
     * @param cause the cause of exception
     */
    public LicenseException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 
     * @param cause the cause of exception
     */
    public LicenseException(final Throwable cause) {
        super(cause);
    }
    
    /**
     * 
     * @param message the license exception message 
     * @param cause the cause of exception
     * @param enableSuppression the enableSuppression parameter
     * @param writableStackTrace the writableStackTrace parameter
     */
    protected LicenseException(final String message,
            final Throwable cause, final boolean enableSuppression, 
            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    
}
