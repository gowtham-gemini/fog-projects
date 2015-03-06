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
class VirtualMachineNotFoundException extends FogPanelException {
    public VirtualMachineNotFoundException() {
        super();
    }        
    public VirtualMachineNotFoundException(String message) {
        super(message);
    }    
    public VirtualMachineNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }         
    public VirtualMachineNotFoundException(Throwable cause) {
        super(cause);
    }    
    protected VirtualMachineNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }   	
}

