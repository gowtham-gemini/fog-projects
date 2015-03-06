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
class InvalidUserNameException extends FogPanelException {
    public InvalidUserNameException() {
        super();
    }        
    public InvalidUserNameException(String message) {
        super(message);
    }    
    public InvalidUserNameException(String message, Throwable cause) {
        super(message, cause);
    }    
    public InvalidUserNameException(Throwable cause) {
        super(cause);
    }    
    protected InvalidUserNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    
}

