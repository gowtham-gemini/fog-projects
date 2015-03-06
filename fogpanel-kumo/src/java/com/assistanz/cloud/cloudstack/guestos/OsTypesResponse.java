package com.assistanz.cloud.cloudstack.guestos;

/**
 *
 * @author Gowtham
 */
class OsTypesResponse {
    
    /**
     * the ID of the OS type
     */
    String osTypeId;	

    /**
     * the name/description of the OS type
     */
    String osTypeDescription;

    /**
     * the ID of the OS category
     */
    String osCategoryId;

    public String getOsTypeId() {
            return osTypeId;
    }

    public void setOsTypeId(String osTypeId) {
            this.osTypeId = osTypeId;
    }

    public String getOsTypeDescription() {
            return osTypeDescription;
    }

    public void setOsTypeDescription(String osTypeDescription) {
            this.osTypeDescription = osTypeDescription;
    }

    public String getOsCategoryId() {
            return osCategoryId;
    }

    public void setOsCategoryId(String osCategoryId) {
            this.osCategoryId = osCategoryId;
    }    
}
