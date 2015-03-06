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
class InvalidAppTemplateException extends FogPanelException {
    public InvalidAppTemplateException() {
        super();
    }        
    public InvalidAppTemplateException(String message) {
        super(message);
    }    
    public InvalidAppTemplateException(String message, Throwable cause) {
        super(message, cause);
    }         
    public InvalidAppTemplateException(Throwable cause) {
        super(cause);
    }    
    protected InvalidAppTemplateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    } 		
}

