package com.assistanz.cloud.cloudstack.apidiscovery;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ApiListResponse {

    /**
     * description of the api
     */
    private String description;

    /**
     * true if api is asynchronous
     */
    private String isAsync;

    /**
     * the name of the api command
     */
    private String name;

    /**
     * comma separated related apis
     */
    private String related;

    /**
     * version of CloudStack the api was introduced in
     */
    private String since;

    /**
     * response field type
     */
    private String type;

    /**
     * the list params the api accepts
     */
    private List<ParamsResponse> paramss;

    /**
     * api response fields
     */
    private List<ApiResponse> apis;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsAsync() {
        return isAsync;
    }

    public void setIsAsync(String isAsync) {
        this.isAsync = isAsync;
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

    public List<ParamsResponse> getParamss() {
        return paramss;
    }

    public void setParamss(List<ParamsResponse> paramss) {
        this.paramss = paramss;
    }

    public List<ApiResponse> getApis() {
        return apis;
    }

    public void setApis(List<ApiResponse> apis) {
        this.apis = apis;
    }
 
}
