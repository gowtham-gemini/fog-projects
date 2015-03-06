/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.firewall;

/**
 *
 * @author gowtham
 */
class EgressRuleResponse {
    
    /**
     * the ID of the firewall rule
     */
    String id;

    /**
     * the cidr list to forward traffic from
     */
    String cidrList;

    /**
     * the ending port of firewall rule's port range
     */
    String endPort;

    /**
     * error code for this icmp message
     */
    String icmpCode;

    /**
     * type of the icmp message being sent
     */
    String icmpType;

    /**
     * the public ip address for the port forwarding rule
     */
    String ipAddress;

    /**
     * the protocol of the firewall rule
     */
    String protocol;

    /**
     * the starting port of firewall rule's port range
     */
    String startPort;	

    /**
     * the state of the rule
     */
    String state;

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
    
}
