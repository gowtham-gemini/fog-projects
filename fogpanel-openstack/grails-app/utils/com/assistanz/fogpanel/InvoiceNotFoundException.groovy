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
class InvoiceNotFoundException extends FogPanelException {
    public InvoiceNotFoundException() {
        super();
    }        
    public InvoiceNotFoundException(String message) {
        super(message);
    }    
    public InvoiceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvoiceNotFoundException(Throwable cause) {
        super(cause);
    }    
    protected InvoiceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    	
}

