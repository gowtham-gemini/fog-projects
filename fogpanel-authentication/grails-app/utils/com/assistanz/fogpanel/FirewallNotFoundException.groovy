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
class FirewallNotFoundException extends FogPanelException {
    public FirewallNotFoundException() {
        super();
    }        
    public FirewallNotFoundException(String message) {
        super(message);
    }    
    public FirewallNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }         
    public FirewallNotFoundException(Throwable cause) {
        super(cause);
    }    
    protected FirewallNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }	
}

