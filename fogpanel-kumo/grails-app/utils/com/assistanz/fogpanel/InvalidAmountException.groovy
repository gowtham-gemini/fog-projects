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
class InvalidAmountException extends FogPanelException {
    public InvalidAmountException() {
        super();
    }        
    public InvalidAmountException(String message) {
        super(message);
    }    
    public InvalidAmountException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidAmountException(Throwable cause) {
        super(cause);
    }    
    protected InvalidAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }  	
}

