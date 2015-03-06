package com.assistanz.cloud.cloudstack.loadbalancer;

/**
 *
 * @author Santhosh
 *
 */
public class LoadBalancerResponse {

    /**
     * the instance port of the load balancer rule
     */
    private String instancePort;

    /**
     * the source port of the load balancer rule
     */
    private String sourcePort;

    /**
     * the state of the load balancer rule
     */
    private String state;

    public String getInstancePort() {
        return instancePort;
    }

    public void setInstancePort(String instancePort) {
        this.instancePort = instancePort;
    }

    public String getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
