/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.fogpanel;

import com.assistanz.fogpanel.FogPanelException;
/**
 *
 * @author nandhini
 */
public class CloudStackException extends  FogPanelException {
    public CloudStackException() {
        super();
    }
    
    public CloudStackException(String message) {
        super(message);
    }
    
    public CloudStackException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CloudStackException(Throwable cause) {
        super(cause);
    }
    
    protected CloudStackException(String message, Throwable cause,
        boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
