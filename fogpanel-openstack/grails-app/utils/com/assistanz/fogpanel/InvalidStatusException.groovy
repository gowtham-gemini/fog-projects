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
class InvalidStatusException extends FogPanelException {
    public InvalidStatusException() {
        super();
    }        
    public InvalidStatusException(String message) {
        super(message);
    }    
    public InvalidStatusException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidStatusException(Throwable cause) {
        super(cause);
    }    
    protected InvalidStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }	
}

