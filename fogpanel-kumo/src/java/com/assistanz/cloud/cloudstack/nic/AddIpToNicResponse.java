/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.nic;

/**
 *
 * @author gowtham
 */
class AddIpToNicResponse {
    
    
    /**
     * the ID of the secondary private IP addr
     */
    String id;

    /**
     * Secondary IP address
     */
    String ipaddress;

    /**
     * the ID of the network
     */
    String networkid;	

    /**
     * the ID of the nic
     */        
    String nicid;	

    /**
     * the ID of the vm
     */  
    String virtualmachineid;	
    
    String jobId; 

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

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

    public String getNetworkid() {
        return networkid;
    }

    public void setNetworkid(String networkid) {
        this.networkid = networkid;
    }

    public String getNicid() {
        return nicid;
    }

    public void setNicid(String nicid) {
        this.nicid = nicid;
    }

    public String getVirtualmachineid() {
        return virtualmachineid;
    }

    public void setVirtualmachineid(String virtualmachineid) {
        this.virtualmachineid = virtualmachineid;
    }
    
    
    
}
