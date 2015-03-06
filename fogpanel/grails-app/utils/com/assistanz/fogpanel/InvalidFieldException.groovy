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
class InvalidFieldException extends FogPanelException {
    public InvalidFieldException() {
        super();
    }        
    public InvalidFieldException(String message) {
        super(message);
    }    
    public InvalidFieldException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidFieldException(Throwable cause) {
        super(cause);
    }    
    protected InvalidFieldException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    	
}

