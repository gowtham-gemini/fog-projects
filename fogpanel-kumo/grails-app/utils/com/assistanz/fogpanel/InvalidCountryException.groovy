/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.fogpanel

/**
 *
 * @author nandhini
 */
class InvalidCountryException extends FogPanelException {
    public InvalidCountryException() {
        super();
    }        
    public InvalidCountryException(String message) {
        super(message);
    }    
    public InvalidCountryException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidCountryException(Throwable cause) {
        super(cause);
    }    
    protected InvalidCountryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    } 
}

