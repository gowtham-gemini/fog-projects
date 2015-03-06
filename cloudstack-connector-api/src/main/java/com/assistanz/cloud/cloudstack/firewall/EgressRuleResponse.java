package com.assistanz.cloud.cloudstack.firewall;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class EgressRuleResponse {

    /**
     * the ID of the firewall rule
     */
    private String id;

    /**
     * the cidr list to forward traffic from
     */
    private String cidrList;

    /**
     * the ending port of firewall rule's port range
     */
    private String endPort;

    /**
     * error code for this icmp message
     */
    private String icmpCode;

    /**
     * type of the icmp message being sent
     */
    private String icmpType;

    /**
     * the public ip address for the port forwarding rule
     */
    private String ipAddress;

    /**
     * the public ip address id for the port forwarding rule
     */
    private String ipAddressId;

    /**
     * the network id of the firewall rule
     */
    private String networkId;

    /**
     * the protocol of the firewall rule
     */
    private String protocol;

    /**
     * the starting port of firewall rule's port range
     */
    private String startPort;

    /**
     * the state of the rule
     */
    private String state;

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

    public String getEndPort() {
        return endPort;
    }

    public void setEndPort(String endPort) {
        this.endPort = endPort;
    }

    public String getIcmpCode() {
        return icmpCode;
    }

    public void setIcmpCode(String icmpCode) {
        this.icmpCode = icmpCode;
    }

    public String getIcmpType() {
        return icmpType;
    }

    public void setIcmpType(String icmpType) {
        this.icmpType = icmpType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddressId() {
        return ipAddressId;
    }

    public void setIpAddressId(String ipAddressId) {
        this.ipAddressId = ipAddressId;
    }

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getStartPort() {
        return startPort;
    }

    public void setStartPort(String startPort) {
        this.startPort = startPort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
