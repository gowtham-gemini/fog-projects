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
class InvalidStatException extends FogPanelException {
    public InvalidStatException() {
        super();
    }        
    public InvalidStatException(String message) {
        super(message);
    }    
    public InvalidStatException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidStatException(Throwable cause) {
        super(cause);
    }    
    protected InvalidStatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    } 
}

