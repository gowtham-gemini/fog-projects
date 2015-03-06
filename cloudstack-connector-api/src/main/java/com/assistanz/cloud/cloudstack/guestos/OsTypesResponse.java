package com.assistanz.cloud.cloudstack.guestos;

/**
 *
 * @author Gowtham
 */
class OsTypesResponse {

    /**
     * the ID of the OS type
     */
    private String id;

    /**
     * the name/description of the OS type
     */
    private String description;

    /**
     * the ID of the OS category
     */
    private String osCategoryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOsCategoryId() {
        return osCategoryId;
    }

    public void setOsCategoryId(String osCategoryId) {
        this.osCategoryId = osCategoryId;
    }

}
