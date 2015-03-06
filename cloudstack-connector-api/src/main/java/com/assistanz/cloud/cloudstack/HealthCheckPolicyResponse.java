package com.assistanz.cloud.cloudstack;

/**
 *
 * @author Santhosh
 *
 */
public class HealthCheckPolicyResponse {

    /**
     * the LB HealthCheck policy ID
     */
    private String id;

    /**
     * the description of the healthcheck policy
     */
    private String description;

    /**
     * Amount of time between health checks
     */
    private String healthCheckInterval;

    /**
     * Number of consecutive health check success before declaring an instance healthy
     */
    private String healthCheckThresshold;

    /**
     * the pingpath of the healthcheck policy
     */
    private String pingPath;

    /**
     * Time to wait when receiving a response from the health check
     */
    private String responseTime;

    /**
     * the state of the policy
     */
    private String state;

    /**
     * Number of consecutive health check failures before declaring an instance unhealthy
     */
    private String unHealthCheckThresshold;

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

    public String getHealthCheckInterval() {
        return healthCheckInterval;
    }

    public void setHealthCheckInterval(String healthCheckInterval) {
        this.healthCheckInterval = healthCheckInterval;
    }

    public String getHealthCheckThresshold() {
        return healthCheckThresshold;
    }

    public void setHealthCheckThresshold(String healthCheckThresshold) {
        this.healthCheckThresshold = healthCheckThresshold;
    }

    public String getPingPath() {
        return pingPath;
    }

    public void setPingPath(String pingPath) {
        this.pingPath = pingPath;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUnHealthCheckThresshold() {
        return unHealthCheckThresshold;
    }

    public void setUnHealthCheckThresshold(String unHealthCheckThresshold) {
        this.unHealthCheckThresshold = unHealthCheckThresshold;
    }

}
