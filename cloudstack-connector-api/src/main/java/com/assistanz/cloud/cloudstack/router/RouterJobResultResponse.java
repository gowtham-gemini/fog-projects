package com.assistanz.cloud.cloudstack.router;

import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import java.util.List;

/**
 *
 * @author gowtham
 */
class RouterJobResultResponse {

    /**
     * the account that executed the async command
     */
    private String asynchronousAccountId;

    /**
     * the async command executed
     */
    private String asynchronousCmd;

    /**
     * the created date of the job
     */
    private String asynchronousCreated;

    /**
     * the unique ID of the instance/entity object related to the job
     */
    private String asynchronousJobInstanceId;

    /**
     * the instance/entity object related to the job
     */
    private String asynchronousJobInstanceType;

    /**
     * the progress information of the PENDING job
     */
    private String asynchronousJobProgressStatus;

    /**
     * the result reason
     */
    private String asynchronousJobResult;

    /**
     * the result code for the job
     */
    private String asynchronousJobResultCode;

    /**
     * the result type
     */
    private String asynchronousJobResultType;

    /**
     * the current job status-should be 0 for PENDING
     */
    private String asynchronousJobStatus;

    /**
     * the user that executed the async command
     */
    private String asynchronousUserId;

    /**
     * the ID of the async job
     */
    private String asynchronousJobId;

    /**
     * the current error Code of the latest async job acting on this object
     */
    private String errorCode;
    /**
     * the current error Text of the latest async job acting on this object
     */
    private String errorText;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

     /**
     * the id of the router
     */
    private String id;

    /**
     * the account associated with the router
     */
    private String account;

    /**
     * the date and time the router was created
     */
    private String created;

    /**
     * the first DNS for the router
     */
    private String dns1;

    /**
     * the second DNS for the router
     */
    private String dns2;

    /**
     * the domain associated with the router
     */
    private String domain;

    /**
     * the domain ID associated with the router
     */
    private String domainId;

    /**
     * the gateway for the router
     */
    private String gateway;

    /**
     * the guest IP address for the router
     */
    private String guestIpAddress;

    /**
     * the guest MAC address for the router
     */
    private String guestMacAddress;

    /**
     * the guest netmask for the router
     */
    private String guestNetmask;

    /**
     * the ID of the corresponding guest network
     */
    private String guestNetworkId;

    /**
     * the host ID for the router
     */
    private String hostId;

    /**
     * the hostname for the router
     */
    private String hostName;

    /**
     * the first IPv6 DNS for the router
     */
    private String ip6Dns1;

    /**
     * the second IPv6 DNS for the router
     */
    private String ip6Dns2;

    /**
     * if this router is an redundant virtual router
     */
    private String isRedundantRouter;

    /**
     * the link local IP address for the router
     */
    private String linkLocalIp;

    /**
     * the link local MAC address for the router
     */
    private String linkLocalMacAddress;

    /**
     * the link local netmask for the router
     */
    private String linkLocalNetmask;

    /**
     * the ID of the corresponding link local network
     */
    private String linkLocalNetworkId;

    /**
     * the name of the router
     */
    private String name;

    /**
     * the network domain for the router
     */
    private String networkDomain;

    /**
     * the Pod ID for the router
     */
    private String podId;

    /**
     * the project name of the address
     */
    private String project;

    /**
     * the project id of the ipaddress
     */
    private String projectId;

    /**
     * the public IP address for the router
     */
    private String publicIp;

    /**
     * the public MAC address for the router
     */
    private String publicMacAddress;

    /**
     * the public netmask for the router
     */
    private String publicNetmask;

    /**
     * the ID of the corresponding public network
     */
    private String publicNetworkId;

    /**
     * the state of redundant virtual router
     */
    private String redundantState;

    /**
     * the role of the domain router
     */
    private String role;

    /**
     * the version of scripts
     */
    private String scriptsVersion;

    /**
     * the ID of the service offering of the virtual machine
     */
    private String serviceOfferingId;

    /**
     * the name of the service offering of the virtual machine
     */
    private String serviceOfferingName;

    /**
     * the state of the router
     */
    private String state;

    /**
     * the template ID for the router
     */
    private String templateId;

    /**
     * the version of template
     */
    private String templateVersion;

    /**
     * VPC the router belongs to
     */
    private String vpcId;

    /**
     * the Zone ID for the router
     */
    private String zoneId;

    /**
     * the Zone name for the router
     */
    private String zoneName;

    /**
     * the list of NetworkInterfaceCards associated with virtual machine
     */
    private List<NetworkInterfaceCardResponse> networkInterfaceCards;

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

    public String getGuestIpAddress() {
        return guestIpAddress;
    }

    public void setGuestIpAddress(String guestIpAddress) {
        this.guestIpAddress = guestIpAddress;
    }

    public String getGuestMacAddress() {
        return guestMacAddress;
    }

    public void setGuestMacAddress(String guestMacAddress) {
        this.guestMacAddress = guestMacAddress;
    }

    public String getGuestNetmask() {
        return guestNetmask;
    }

    public void setGuestNetmask(String guestNetmask) {
        this.guestNetmask = guestNetmask;
    }

    public String getGuestNetworkId() {
        return guestNetworkId;
    }

    public void setGuestNetworkId(String guestNetworkId) {
        this.guestNetworkId = guestNetworkId;
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

    public String getIp6Dns1() {
        return ip6Dns1;
    }

    public void setIp6Dns1(String ip6Dns1) {
        this.ip6Dns1 = ip6Dns1;
    }

    public String getIp6Dns2() {
        return ip6Dns2;
    }

    public void setIp6Dns2(String ip6Dns2) {
        this.ip6Dns2 = ip6Dns2;
    }

    public String getIsRedundantRouter() {
        return isRedundantRouter;
    }

    public void setIsRedundantRouter(String isRedundantRouter) {
        this.isRedundantRouter = isRedundantRouter;
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

    public String getLinkLocalNetworkId() {
        return linkLocalNetworkId;
    }

    public void setLinkLocalNetworkId(String linkLocalNetworkId) {
        this.linkLocalNetworkId = linkLocalNetworkId;
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

    public String getPublicNetworkId() {
        return publicNetworkId;
    }

    public void setPublicNetworkId(String publicNetworkId) {
        this.publicNetworkId = publicNetworkId;
    }

    public String getRedundantState() {
        return redundantState;
    }

    public void setRedundantState(String redundantState) {
        this.redundantState = redundantState;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getScriptsVersion() {
        return scriptsVersion;
    }

    public void setScriptsVersion(String scriptsVersion) {
        this.scriptsVersion = scriptsVersion;
    }

    public String getServiceOfferingId() {
        return serviceOfferingId;
    }

    public void setServiceOfferingId(String serviceOfferingId) {
        this.serviceOfferingId = serviceOfferingId;
    }

    public String getServiceOfferingName() {
        return serviceOfferingName;
    }

    public void setServiceOfferingName(String serviceOfferingName) {
        this.serviceOfferingName = serviceOfferingName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTemplateVersion() {
        return templateVersion;
    }

    public void setTemplateVersion(String templateVersion) {
        this.templateVersion = templateVersion;
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

    public List<NetworkInterfaceCardResponse> getNetworkInterfaceCards() {
        return networkInterfaceCards;
    }

    public void setNetworkInterfaceCards(List<NetworkInterfaceCardResponse> networkInterfaceCards) {
        this.networkInterfaceCards = networkInterfaceCards;
    }

    public String getAsynchronousAccountId() {
        return asynchronousAccountId;
    }

    public void setAsynchronousAccountId(String asynchronousAccountId) {
        this.asynchronousAccountId = asynchronousAccountId;
    }

    public String getAsynchronousCmd() {
        return asynchronousCmd;
    }

    public void setAsynchronousCmd(String asynchronousCmd) {
        this.asynchronousCmd = asynchronousCmd;
    }

    public String getAsynchronousCreated() {
        return asynchronousCreated;
    }

    public void setAsynchronousCreated(String asynchronousCreated) {
        this.asynchronousCreated = asynchronousCreated;
    }

    public String getAsynchronousJobInstanceId() {
        return asynchronousJobInstanceId;
    }

    public void setAsynchronousJobInstanceId(String asynchronousJobInstanceId) {
        this.asynchronousJobInstanceId = asynchronousJobInstanceId;
    }

    public String getAsynchronousJobInstanceType() {
        return asynchronousJobInstanceType;
    }

    public void setAsynchronousJobInstanceType(String asynchronousJobInstanceType) {
        this.asynchronousJobInstanceType = asynchronousJobInstanceType;
    }

    public String getAsynchronousJobProgressStatus() {
        return asynchronousJobProgressStatus;
    }

    public void setAsynchronousJobProgressStatus(String asynchronousJobProgressStatus) {
        this.asynchronousJobProgressStatus = asynchronousJobProgressStatus;
    }

    public String getAsynchronousJobResult() {
        return asynchronousJobResult;
    }

    public void setAsynchronousJobResult(String asynchronousJobResult) {
        this.asynchronousJobResult = asynchronousJobResult;
    }

    public String getAsynchronousJobResultCode() {
        return asynchronousJobResultCode;
    }

    public void setAsynchronousJobResultCode(String asynchronousJobResultCode) {
        this.asynchronousJobResultCode = asynchronousJobResultCode;
    }

    public String getAsynchronousJobResultType() {
        return asynchronousJobResultType;
    }

    public void setAsynchronousJobResultType(String asynchronousJobResultType) {
        this.asynchronousJobResultType = asynchronousJobResultType;
    }

    public String getAsynchronousJobStatus() {
        return asynchronousJobStatus;
    }

    public void setAsynchronousJobStatus(String asynchronousJobStatus) {
        this.asynchronousJobStatus = asynchronousJobStatus;
    }

    public String getAsynchronousUserId() {
        return asynchronousUserId;
    }

    public void setAsynchronousUserId(String asynchronousUserId) {
        this.asynchronousUserId = asynchronousUserId;
    }

    public String getAsynchronousJobId() {
        return asynchronousJobId;
    }

    public void setAsynchronousJobId(String asynchronousJobId) {
        this.asynchronousJobId = asynchronousJobId;
    }
    
}

       

