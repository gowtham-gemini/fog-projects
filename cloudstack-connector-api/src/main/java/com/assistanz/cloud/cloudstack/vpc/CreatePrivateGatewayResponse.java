package com.assistanz.cloud.cloudstack.vpc;

/**
 *
 * @author Santhosh
 */
public class CreatePrivateGatewayResponse {

    /**
     * the id of the network
     */
    private String id;

    /**
     * the owner of the network
     */
    private String account;

    /**
     * ACL Id associated with the VPC network
     */
    private String aclId;

    /**
     * the domain name of the network owner
     */
    private String domain;

    /**
     * the domain id of the network owner
     */
    private String domainId;

    /**
     * the network's gateway
     */
    private String gateway;

    /**
     * the domain id of the network owner
     */
    private String ipAddress;

    /**
     * the network's netmask
     */
    private String netmask;

    /**
     * the physical network id
     */
    private String physicalNetworkId;

    /**
     * the project name of the address
     */
    private String project;

    /**
     * the project id of the ipaddress
     */
    private String projectId;

    /**
     * Souce Nat enable status
     */
    private String sourceNatSupported;

    /**
     * state of the network
     */
    private String state;

    /**
     * The vlan of the network, This parameter is visible to ROOT admins only
     */
    private String vlan;

    /**
     * VPC the network belongs to
     */
    private String vpcId;

    /**
     * zone id of the network
     */
    private String zoneId;

    /**
     * the name of the zone the network belongs to
     */
    private String zoneName;
    
    private String jobId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAclId() {
        return aclId;
    }

    public void setAclId(String aclId) {
        this.aclId = aclId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getPhysicalNetworkId() {
        return physicalNetworkId;
    }

    public void setPhysicalNetworkId(String physicalNetworkId) {
        this.physicalNetworkId = physicalNetworkId;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getSourceNatSupported() {
        return sourceNatSupported;
    }

    public void setSourceNatSupported(String sourceNatSupported) {
        this.sourceNatSupported = sourceNatSupported;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

}
