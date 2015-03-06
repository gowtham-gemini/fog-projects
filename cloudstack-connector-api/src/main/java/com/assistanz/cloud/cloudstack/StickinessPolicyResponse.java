package com.assistanz.cloud.cloudstack;

/**
 *
 * @author Gowtham
 *
 */
public class StickinessPolicyResponse {

    /**
     * the LB Stickiness policy ID
     */
    private String id;

    /**
     * the description of the Stickiness policy
     */
    private String description;

    /**
     * the method name of the Stickiness policy
     */
    private String methodName;

    /**
     * the name of the Stickiness policy
     */
    private String name;

    /**
     * the params of the policy
     */
    private String params;

    /**
     * the state of the policy
     */
    private String state;

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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
