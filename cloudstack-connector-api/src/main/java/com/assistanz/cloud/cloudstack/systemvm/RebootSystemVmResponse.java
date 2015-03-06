package com.assistanz.cloud.cloudstack.systemvm;

/**
 *
 * @author Gowtham
 *
 */
public class RebootSystemVmResponse {

    /**
     * the ID of the system VM
     */
    private String id;

    /**
     * the number of active console sessions for the console proxy system vm
     */
    private String activeViewerSessions;

    /**
     * the date and time the system VM was created
     */
    private String created;

    /**
     * the first DNS for the system VM
     */
    private String dns1;

    /**
     * the second DNS for the system VM
     */
    private String dns2;

    /**
     * the gateway for the system VM
     */
    private String gateway;

    /**
     * the host ID for the system VM
     */
    private String hostId;

    /**
     * the hostname for the system VM
     */
    private String hostName;

    /**
     * the job ID associated with the system VM. This is only displayed if the router listed is part of a currently
     * running asynchronous job.
     */
    private String jobId;

    /**
     * the job status associated with the system VM. This is only displayed if the router listed is part of a currently
     * running asynchronous job.
     */
    private String jobStatus;

    /**
     * the link local IP address for the system vm
     */
    private String linkLocalIp;

    /**
     * the link local MAC address for the system vm
     */
    private String linkLocalMacAddress;

    /**
     * the link local netmask for the system vm
     */
    private String linkLocalNetmask;

    /**
     * the name of the system VM
     */
    private String name;

    /**
     * the network domain for the system VM
     */
    private String networkDomain;

    /**
     * the Pod ID for the system VM
     */
    private String podId;

    /**
     * the private IP address for the system VM
     */
    private String privateIp;

    /**
     * the private MAC address for the system VM
     */
    private String privateMacAddress;

    /**
     * the private netmask for the system VM
     */
    private String privateNetmask;

    /**
     * the public IP address for the system VM
     */
    private String publicIp;

    /**
     * the public MAC address for the system VM
     */
    private String publicMacAddress;

    /**
     * the public netmask for the system VM
     */
    private String publicNetmask;

    /**
     * the state of the system VM
     */
    private String state;

    /**
     * the system VM type
     */
    private String systemVmType;

    /**
     * the template ID for the system VM
     */
    private String templateId;

    /**
     * the Zone ID for the system VM
     */
    private String zoneId;

    /**
     * the Zone name for the system VM
     */
    private String zoneName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActiveViewerSessions() {
        return activeViewerSessions;
    }

    public void setActiveViewerSessions(String activeViewerSessions) {
        this.activeViewerSessions = activeViewerSessions;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDns1() {
        return dns1;
    }

    public void setDns1(String dns1) {
        this.dns1 = dns1;
    }

    public String getDns2() {
        return dns2;
    }

    public void setDns2(String dns2) {
        this.dns2 = dns2;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getLinkLocalIp() {
        return linkLocalIp;
    }

    public void setLinkLocalIp(String linkLocalIp) {
        this.linkLocalIp = linkLocalIp;
    }

    public String getLinkLocalMacAddress() {
        return linkLocalMacAddress;
    }

    public void setLinkLocalMacAddress(String linkLocalMacAddress) {
        this.linkLocalMacAddress = linkLocalMacAddress;
    }

    public String getLinkLocalNetmask() {
        return linkLocalNetmask;
    }

    public void setLinkLocalNetmask(String linkLocalNetmask) {
        this.linkLocalNetmask = linkLocalNetmask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetworkDomain() {
        return networkDomain;
    }

    public void setNetworkDomain(String networkDomain) {
        this.networkDomain = networkDomain;
    }

    public String getPodId() {
        return podId;
    }

    public void setPodId(String podId) {
        this.podId = podId;
    }

    public String getPrivateIp() {
        return privateIp;
    }

    public void setPrivateIp(String privateIp) {
        this.privateIp = privateIp;
    }

    public String getPrivateMacAddress() {
        return privateMacAddress;
    }

    public void setPrivateMacAddress(String privateMacAddress) {
        this.privateMacAddress = privateMacAddress;
    }

    public String getPrivateNetmask() {
        return privateNetmask;
    }

    public void setPrivateNetmask(String privateNetmask) {
        this.privateNetmask = privateNetmask;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getPublicMacAddress() {
        return publicMacAddress;
    }

    public void setPublicMacAddress(String publicMacAddress) {
        this.publicMacAddress = publicMacAddress;
    }

    public String getPublicNetmask() {
        return publicNetmask;
    }

    public void setPublicNetmask(String publicNetmask) {
        this.publicNetmask = publicNetmask;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSystemVmType() {
        return systemVmType;
    }

    public void setSystemVmType(String systemVmType) {
        this.systemVmType = systemVmType;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

}
