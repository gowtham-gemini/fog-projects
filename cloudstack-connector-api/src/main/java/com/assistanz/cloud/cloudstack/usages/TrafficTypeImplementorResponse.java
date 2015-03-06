package com.assistanz.cloud.cloudstack.usages;

/**
 *
 * @author Santhosh
 */
class TrafficTypeImplementorResponse {

    /**
     * network traffic type
     */
    private String trafficType;

    /**
     * implementor of network traffic type
     */
    private String trafficTypeImplementor;

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public String getTrafficTypeImplementor() {
        return trafficTypeImplementor;
    }

    public void setTrafficTypeImplementor(String trafficTypeImplementor) {
        this.trafficTypeImplementor = trafficTypeImplementor;
    }
}
