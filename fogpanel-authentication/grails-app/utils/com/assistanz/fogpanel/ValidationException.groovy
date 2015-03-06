package com.assistanz.fogpanel

/**
 *
 * @author Gowtham
 */
class ValidationException extends RuntimeException {
    
    List errors;
    String error;
    
    public ValidationException(List errors) {
        this.errors = errors;
    }
    
    public ValidationException(String error) {
        this.error = error;
    }	
}

