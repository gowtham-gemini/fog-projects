package com.assistanz.cloud.cloudstack.configuration;

/**
 *
 * @author Gowtham
 *
 */
public class ConfigurationResponse {

    /**
     * the value of the configuration
     */
    private String id;

    /**
     * the category of the configuration
     */
    private String category;

    /**
     * the description of the configuration
     */
    private String description;

    /**
     * the name of the configuration
     */
    private String name;

    /**
     * scope(zone/cluster/pool/account) of the parameter that needs to be updated
     */
    private String scope;

    /**
     * the value of the configuration
     */
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
