package com.assistanz.cloud.cloudstack.nat;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class CreateIpForwardingRuleResponse {

    /**
     * the ID of the port forwarding rule
     */
    private String id;

    /**
     * the cidr list to forward traffic from
     */
    private String cidrList;

    /**
     * the public ip address for the port forwarding rule
     */
    private String ipAddress;

    /**
     * the public ip address id for the port forwarding rule
     */
    private String ipaddressId;

    /**
     * the ending port of port forwarding rule's private port range
     */
    private String privateEndPort;

    /**
     * the starting port of port forwarding rule's private port range
     */
    private String privatePort;

    /**
     * the protocol of the port forwarding rule
     */
    private String protocol;

    /**
     * the ending port of port forwarding rule's private port range
     */
    private String publicEndPort;

    /**
     * the starting port of port forwarding rule's public port range
     */
    private String publicPort;

    /**
     * the state of the rule
     */
    private String state;

    /**
     * the VM display name for the port forwarding rule
     */
    private String virtualMachineDisplayName;

    /**
     * the VM ID for the port forwarding rule
     */
    private String virtualMachineId;

    /**
     * the VM name for the port forwarding rule
     */
    private String virtualMachineName;

    /**
     * the VM IP address for the port forwarding rule
     */
    private String vmGuestIp;

    /**
     * the list of resource tags associated with the rule
     */
    private List<TagsResponse> tagss;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCidrList() {
        return cidrList;
    }

    public void setCidrList(String cidrList) {
        this.cidrList = cidrList;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpaddressId() {
        return ipaddressId;
    }

    public void setIpaddressId(String ipaddressId) {
        this.ipaddressId = ipaddressId;
    }

    public String getPrivateEndPort() {
        return privateEndPort;
    }

    public void setPrivateEndPort(String privateEndPort) {
        this.privateEndPort = privateEndPort;
    }

    public String getPrivatePort() {
        return privatePort;
    }

    public void setPrivatePort(String privatePort) {
        this.privatePort = privatePort;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getPublicEndPort() {
        return publicEndPort;
    }

    public void setPublicEndPort(String publicEndPort) {
        this.publicEndPort = publicEndPort;
    }

    public String getPublicPort() {
        return publicPort;
    }

    public void setPublicPort(String publicPort) {
        this.publicPort = publicPort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVirtualMachineDisplayName() {
        return virtualMachineDisplayName;
    }

    public void setVirtualMachineDisplayName(String virtualMachineDisplayName) {
        this.virtualMachineDisplayName = virtualMachineDisplayName;
    }

    public String getVirtualMachineId() {
        return virtualMachineId;
    }

    public void setVirtualMachineId(String virtualMachineId) {
        this.virtualMachineId = virtualMachineId;
    }

    public String getVirtualMachineName() {
        return virtualMachineName;
    }

    public void setVirtualMachineName(String virtualMachineName) {
        this.virtualMachineName = virtualMachineName;
    }

    public String getVmGuestIp() {
        return vmGuestIp;
    }

    public void setVmGuestIp(String vmGuestIp) {
        this.vmGuestIp = vmGuestIp;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
