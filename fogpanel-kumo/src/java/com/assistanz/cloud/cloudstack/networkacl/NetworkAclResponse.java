package com.assistanz.cloud.cloudstack.networkacl;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class NetworkAclResponse {

    /**
     * the ID of the ACL Item
     */
    private String aclItemId;

    /**
     * the ID of the ACL this item belongs to
     */
    private String aclId;

    /**
     * Action of ACL Item, Allow/Deny
     */
    private String action;

    /**
     * the CIDR list to forward traffic from
     */
    private String cidrList;

    /**
     * the ending port of ACL's port range
     */
    private String endPort;

    /**
     * error code for this ICMP message
     */
    private String icmpCode;

    /**
     * type of the ICMP message being sent
     */
    private String icmpType;

    /**
     * Number of the ACL Item
     */
    private String number;

    /**
     * the protocol of the ACL
     */
    private String protocol;

    /**
     * the starting port of ACL's port range
     */
    private String startPort;

    /**
     * the state of the rule
     */
    private String state;

    /**
     * the traffic type for the ACL
     */
    private String trafficType;

    /**
     * the list of resource tags associated with the network ACLs
     */
    private List<TagsResponse> tagss;

    public String getAclItemId() {
        return aclItemId;
    }

    public void setAclItemId(String aclItemId) {
        this.aclItemId = aclItemId;
    }

    public String getAclId() {
        return aclId;
    }

    public void setAclId(String aclId) {
        this.aclId = aclId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
