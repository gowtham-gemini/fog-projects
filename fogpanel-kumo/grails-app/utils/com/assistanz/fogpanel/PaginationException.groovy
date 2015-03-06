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
class PaginationException extends FogPanelException {
    public PaginationException() {
        super();
    }        
    public PaginationException(String message) {
        super(message);
    }    
    public PaginationException(String message, Throwable cause) {
        super(message, cause);
    }         
    public PaginationException(Throwable cause) {
        super(cause);
    }    
    protected PaginationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

