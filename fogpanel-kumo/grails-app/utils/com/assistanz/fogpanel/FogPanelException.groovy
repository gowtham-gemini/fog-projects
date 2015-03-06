package com.assistanz.fogpanel

/**
 *
 * @author Gowtham
 */
public class FogPanelException extends RuntimeException {
    
    public FogPanelException() {
        super();
    }
    
    public FogPanelException(String message) {
        super(message);
    }
    
    public FogPanelException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public FogPanelException(Throwable cause) {
        super(cause);
    }
    
    protected FogPanelException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }    
}

