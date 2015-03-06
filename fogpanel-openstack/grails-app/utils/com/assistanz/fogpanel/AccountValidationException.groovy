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
class AccountValidationException extends FogPanelException {
    public AccountValidationException() {
        super();
    }        
    public AccountValidationException(String message) {
        super(message);
    }    
    public AccountValidationException(String message, Throwable cause) {
        super(message, cause);
    }         
    public AccountValidationException(Throwable cause) {
        super(cause);
    }    
    protected AccountValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
	
}

