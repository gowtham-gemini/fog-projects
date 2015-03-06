package com.assistanz.cloud.cloudstack.apidiscovery;

/**
 *
 * @author Santhosh
 *
 */
public class ParamsResponse {

    /**
     * description of the api parameter
     */
    private String description;

    /**
     * length of the parameter
     */
    private String length;

    /**
     * the name of the api parameter
     */
    private String name;

    /**
     * comma separated related apis to get the parameter
     */
    private String related;

    /**
     * true if this parameter is required for the api request
     */
    private String required;

    /**
     * version of CloudStack the api was introduced in
     */
    private String since;

    /**
     * parameter type
     */
    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
