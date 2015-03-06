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
class UserNameAlreadyExistException extends FogPanelException {
    public UserNameAlreadyExistException() {
        super();
    }    
    
    public UserNameAlreadyExistException(String message) {
        super(message);
    }
    
    public UserNameAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public UserNameAlreadyExistException(Throwable cause) {
        super(cause);
    }
    
    protected UserNameAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    
}

