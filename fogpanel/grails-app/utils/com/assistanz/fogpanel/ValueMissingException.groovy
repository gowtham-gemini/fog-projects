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
class ValueMissingException extends FogPanelException {
    public ValueMissingException() {
        super();
    }    
    public ValueMissingException(String message) {
        super(message);
    }    
    public ValueMissingException(String message, Throwable cause) {
        super(message, cause);
    }    
    public ValueMissingException(Throwable cause) {
        super(cause);
    }    
    protected ValueMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    
}

