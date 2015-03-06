/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.licensor;

/**
 *
 * @author Sujai
 */
public interface Licensor {
    
    /**
     * 
     * @author Sujai
     */
    enum Status {
        VALID, FREE_TRIAL, EMERGENCY_TRIAL, EXPIRED, LOCKED
    };
    
    /**
     * Validate a license.
     * 
     * @param license the license parameter
     * @param activeHosts the number of active hosts present
     * @return the client license
     * @throws LicenseException if the component cannot be granted a license
     */
    License validate(License license,  Long activeHosts) 
            throws LicenseException;
    
    /**
     * 
     * @param license the license parameter
     * @param activeHosts the number of active hosts present
     * @return the client license
     * @throws LicenseException if the component cannot be granted a license
     */    
    License register(License license,  Long activeHosts) 
            throws LicenseException;
    
    /**
     * Checks the data from the DB and provides a response.
     *
     * @param payload the payload parameter
     * @return Valid or not
     */
    Boolean isValid(String payload);
    
    /**
     * 
     * @param payload the payload parameter
     * @return the product
     */
    String getProduct(String payload);
    
    /**
     * 
     * @param license the license 
     * @param maxHosts the maxHosts present
     * @param activeHosts the activeHosts present
     * @param status the status 
     * @param expirationTimestamp the expirationTimestamp 
     * @param freeDays the number of freeDays 
     * @param product the product parameter
     * @return the payload in encrypted format
     */
    String buildPayload(License license, Long maxHosts, Long activeHosts, 
            String status, Long expirationTimestamp, Long freeDays, 
            String product);
    
    /**
     * 
     * @param checksum the checksum parameter
     * @param activeHosts the number of activeHosts present
     * @return the status
     */   
    Status getStatus(String checksum, Long activeHosts);
}
