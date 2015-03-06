package com.assistanz.cloud.cloudstack.nic;

/**
 *
 * @author gowtham
 */
class AddIpToNicResponse {

    /**
     * the ID of the secondary private IP addr
     */
    private String id;

    /**
     * Secondary IP address
     */
    private String ipaddress;

    /**
     * the ID of the network
     */
    private String networkId;

    /**
     * the ID of the nic
     */
    private String nicId;

    /**
     * the ID of the job
     */
    private String jobId;
    
    /**
     * the ID of the vm
     */
    private String virtualMachineId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getNicId() {
        return nicId;
    }

    public void setNicId(String nicId) {
        this.nicId = nicId;
    }

    public String getVirtualMachineId() {
        return virtualMachineId;
    }

    public void setVirtualMachineId(String virtualMachineId) {
        this.virtualMachineId = virtualMachineId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
