package com.assistanz.cloud.cloudstack.internallb;

/**
 *
 * @author Santhosh
 */
class InternalLoadBalancerElementResponse {

    /**
     * the internal load balancer element
     */
    private String id;

    /**
     * set Enabled/Disabled of the element
     */
    private String enabled;

    /**
     * the physical network service provider id of the element
     */
    private String nspId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getNspId() {
        return nspId;
    }

    public void setNspId(String nspId) {
        this.nspId = nspId;
    }

}
