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
class InvalidBillingTypeException extends FogPanelException {
    public InvalidBillingTypeException() {
        super();
    }        
    public InvalidBillingTypeException(String message) {
        super(message);
    }    
    public InvalidBillingTypeException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidBillingTypeException(Throwable cause) {
        super(cause);
    }    
    protected InvalidBillingTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    } 	
}

