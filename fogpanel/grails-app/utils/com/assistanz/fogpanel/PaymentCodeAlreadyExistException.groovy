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
class PaymentCodeAlreadyExistException extends FogPanelException {
    public PaymentCodeAlreadyExistException() {
        super();
    }        
    public PaymentCodeAlreadyExistException(String message) {
        super(message);
    }    
    public PaymentCodeAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }         
    public PaymentCodeAlreadyExistException(Throwable cause) {
        super(cause);
    }    
    protected PaymentCodeAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }   
	
}

