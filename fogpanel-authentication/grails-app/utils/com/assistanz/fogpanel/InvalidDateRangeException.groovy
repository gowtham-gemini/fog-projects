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
class InvalidDateRangeException extends FogPanelException {
    public InvalidDateRangeException() {
        super();
    }        
    public InvalidDateRangeException(String message) {
        super(message);
    }    
    public InvalidDateRangeException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidDateRangeException(Throwable cause) {
        super(cause);
    }    
    protected InvalidDateRangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    	
}

