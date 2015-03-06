package com.assistanz.cloud.cloudstack.apidiscovery;

/**
 *
 * @author Santhosh
 *
 */
public class ApiResponse {

    /**
     * description of the api response field
     */
    private String description;

    /**
     * the name of the api response field
     */
    private String name;

    /**
     * api response fields
     */
    private String response;

    /**
     * response field type
     */
    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
