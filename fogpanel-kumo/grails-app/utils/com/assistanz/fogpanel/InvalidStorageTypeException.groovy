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
class InvalidStorageTypeException extends FogPanelException {
    public InvalidStorageTypeException() {
        super();
    }        
    public InvalidStorageTypeException(String message) {
        super(message);
    }    
    public InvalidStorageTypeException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidStorageTypeException(Throwable cause) {
        super(cause);
    }    
    protected InvalidStorageTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }	
}

