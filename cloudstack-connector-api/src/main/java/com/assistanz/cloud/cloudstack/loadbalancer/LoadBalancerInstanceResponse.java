package com.assistanz.cloud.cloudstack.loadbalancer;

/**
 *
 * @author Santhosh
 *
 */
public class LoadBalancerInstanceResponse {

    /**
     * the instance ID
     */
    private String id;

    /**
     * the ip address of the instance
     */
    private String ipAddress;

    /**
     * the name of the instance
     */
    private String name;

    /**
     * the state of the instance
     */
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
