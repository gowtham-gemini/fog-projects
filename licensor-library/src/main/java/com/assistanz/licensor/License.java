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
public class License {
    
    private String requestID;
    private String product;
    private String hostName;
    private String initialVersion;
    private String currentVersion;
    private String hostIP;
    private Long sockets;
    private String email;
    private Long lastUpdatedDate;
    private Long deployedDate;
    private Long registrationDate;
    private String timeZone;
    private String generatedKey;
    private String checksum;
    private String additionalData;
    private boolean valid;
    private String errorMessage;

    /**
     * 
     * @return the requestId
     */
    public String getRequestID() {
        return requestID;
    }

    /**
     * 
     * @param requestID the requestId parameter
     */
    public void setRequestID(final String requestID) {
        this.requestID = requestID;
    }
    
    /**
     * 
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * 
     * @param hostName the host name 
     */
    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }
   
    /**
     * 
     * @return the host IP
     */
    public String getHostIP() {
        return hostIP;
    }

    /**
     * 
     * @param hostIP the host IP 
     */
    public void setHostIP(final String hostIP) {
        this.hostIP = hostIP;
    }

    /**
     * 
     * @return the sockets
     */
    public Long getSockets() {
        return sockets;
    }

    /**
     * 
     * @param sockets to set sockets 
     */
    public void setSockets(final Long sockets) {
        this.sockets = sockets;
    }

    /**
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email to set email 
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * 
     * @return the last updated date
     */
    public Long getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    /**
     * 
     * @param lastUpdatedDate the lastUpdatedDate parameter
     */
    public void setLastUpdatedDate(final Long lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    /**
     * 
     * @return the deployedDate
     */
    public Long getDeployedDate() {
        return deployedDate;
    }

    /**
     * 
     * @param deployedDate the depaloyedDate parameter 
     */
    public void setDeployedDate(final Long deployedDate) {
        this.deployedDate = deployedDate;
    }
    
    /**
     * 
     * @return  the registration date
     */
    public Long getRegistrationDate() {
        return registrationDate;
    }
 
    /**
     * 
     * @param registrationDate to set registration date 
     */
    public void setRegistrationDate(final Long registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * 
     * @return the timeZone 
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * 
     * @param timeZone the timeZone 
     */
    public void setTimeZone(final String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * 
     * @return the generatedKey 
     */
    public String getGeneratedKey() {
        return generatedKey;
    }
 
    /**
     * 
     * @param generatedKey to set generatedKey 
     */
    public void setGeneratedKey(final String generatedKey) {
        this.generatedKey = generatedKey;
    }

    /**
     * 
     * @return the checksum
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * 
     * @param checksum the checksum parameter
     */
    public void setChecksum(final String checksum) {
        this.checksum = checksum;
    }

    /**
     * 
     * @return the additional data
     */
    public String getAdditionalData() {
        return additionalData;
    }

    /**
     * 
     * @param additionalData the additionalData 
     */
    public void setAdditionalData(final String additionalData) {
        this.additionalData = additionalData;
    }

    /**
     * 
     * @return the initialVersion
     */
    public String getInitialVersion() {
        return initialVersion;
    }

    /**
     * 
     * @param initialVersion the initial version 
     */
    public void setInitialVersion(final String initialVersion) {
        this.initialVersion = initialVersion;
    }

    /**
     * 
     * @return the current version 
     */
    public String getCurrentVersion() {
        return currentVersion;
    }

    /**
     * 
     * @param currentVersion the currentVersion 
     */
    public void setCurrentVersion(final String currentVersion) {
        this.currentVersion = currentVersion;
    }

    /**
     * 
     * @return Valid or not
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * 
     * @param valid the valid parameter
     */
    public void setValid(final boolean valid) {
        this.valid = valid;
    }

    /**
     * 
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 
     * @param errorMessage to set errorMessage 
     */
    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * 
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * 
     * @param product the product parameter 
     */
    public void setProduct(final String product) {
        this.product = product;
    }
}
