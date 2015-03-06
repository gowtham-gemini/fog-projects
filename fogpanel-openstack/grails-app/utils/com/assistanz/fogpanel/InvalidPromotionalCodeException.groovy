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
class InvalidPromotionalCodeException extends FogPanelException {
    public InvalidPromotionalCodeException() {
        super();
    }        
    public InvalidPromotionalCodeException(String message) {
        super(message);
    }    
    public InvalidPromotionalCodeException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidPromotionalCodeException(Throwable cause) {
        super(cause);
    }    
    protected InvalidPromotionalCodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

