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
class InvalidBaseOSException extends FogPanelException {
    public InvalidBaseOSException() {
        super();
    }        
    public InvalidBaseOSException(String message) {
        super(message);
    }    
    public InvalidBaseOSException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidBaseOSException(Throwable cause) {
        super(cause);
    }    
    protected InvalidBaseOSException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    } 	
}

