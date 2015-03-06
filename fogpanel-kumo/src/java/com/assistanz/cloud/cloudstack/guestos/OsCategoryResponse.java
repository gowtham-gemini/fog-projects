package com.assistanz.cloud.cloudstack.guestos;

/**
 *
 * @author Az-PHP
 */
class OsCategoryResponse {
    
    /**
     * the ID of the OS category
     */
    String OsCategoryId;	

    /**
     * the name of the OS category
     */
    String OsCategoryName;

    public String getOsCategoryId() {
            return OsCategoryId;
    }

    public void setOsCategoryId(String osCategoryId) {
            OsCategoryId = osCategoryId;
    }

    public String getOsCategoryName() {
            return OsCategoryName;
    }

    public void setOsCategoryName(String osCategoryName) {
            OsCategoryName = osCategoryName;
    }	
    
    
    
}
