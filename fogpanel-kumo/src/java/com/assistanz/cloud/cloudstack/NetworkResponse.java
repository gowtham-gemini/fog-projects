package com.assistanz.cloud.cloudstack;

import java.util.List;

/**
 *
 * @author Santhosh
 */
public class NetworkResponse {

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
     * acl type - access type to the network
     */
    private String aclType;

    /**
     * Broadcast domain type of the network
     */
    private String broadcastDomainType;

    /**
     * broadcast uri of the network This parameter is visible to ROOT admins only
     */
    private String broadcastUri;

    /**
     * list networks available for vm deployment
     */
    private String canUseForDeploy;

    /**
     * Cloudstack managed address space, all CloudStack managed VMs get IP address from CIDR
     */
    private String cidr;

    /**
     * an optional field, whether to the display the network to the end user or not
     */
    private String displayNetwork;

    /**
     * the displaytext of the network
     */
    private String displayText;

    /**
     * the first DNS for the network
     */
    private String dns1;

    /**
     * the second DNS for the network
     */
    private String dns2;

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
     * the cidr of IPv6 network
     */
    private String ip6Cidr;

    /**
     * the gateway of IPv6 network
     */
    private String ip6Gateway;

    /**
     * true if network is default, false otherwise
     */
    private String isDefault;

    /**
     * list networks that are persistent
     */
    private String isPersistent;

    /**
     * true if network is system, false otherwise
     */
    private String isSystem;

    /**
     * the name of the network
     */
    private String networkName;

    /**
     * the network's netmask
     */
    private String netmask;

    /**
     * the network CIDR of the guest network configured with IP reservation It is the summation of CIDR and
     * RESERVED_IP_RANGE
     */
    private String networkCidr;

    /**
     * the network domain
     */
    private String networkDomain;

    /**
     * availability of the network offering the network is created from
     */
    private String networkOfferingAvailability;

    /**
     * true if network offering is ip conserve mode enabled
     */
    private String networkOfferingConserveMode;

    /**
     * display text of the network offering the network is created from
     */
    private String networkOfferingDisplayText;

    /**
     * network offering id the network is created from
     */
    private String networkOfferingId;

    /**
     * name of the network offering the network is created from
     */
    private String networkOfferingName;

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
     * related to what other network configuration
     */
    private String related;

    /**
     * the network's IP range not to be used by CloudStack guest VMs and can be used for non CloudStack purposes
     */
    private String reservedIpRange;

    /**
     * true network requires restart
     */
    private String restartRequired;

    /**
     * true if network supports specifying ip ranges, false otherwise
     */
    private String specifyIpRanges;

    /**
     * state of the network
     */
    private String state;

    /**
     * true if users from subdomains can access the domain level network
     */
    private String subDomainAccess;

    /**
     * the traffic type of the network
     */
    private String trafficType;

    /**
     * the type of the network
     */
    private String networkType;

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

    /**
     * the list of supported services
     */
    private List<ServiceResponse> services;

    /**
     * list of tags associated with the virtual machine
     */
    private List<TagsResponse> tagss;

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

    public String getAclType() {
        return aclType;
    }

    public void setAclType(String aclType) {
        this.aclType = aclType;
    }

    public String getBroadcastDomainType() {
        return broadcastDomainType;
    }

    public void setBroadcastDomainType(String broadcastDomainType) {
        this.broadcastDomainType = broadcastDomainType;
    }

    public String getBroadcastUri() {
        return broadcastUri;
    }

    public void setBroadcastUri(String broadcastUri) {
        this.broadcastUri = broadcastUri;
    }

    public String getCanUseForDeploy() {
        return canUseForDeploy;
    }

    public void setCanUseForDeploy(String canUseForDeploy) {
        this.canUseForDeploy = canUseForDeploy;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getDisplayNetwork() {
        return displayNetwork;
    }

    public void setDisplayNetwork(String displayNetwork) {
        this.displayNetwork = displayNetwork;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
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

    public String getIp6Cidr() {
        return ip6Cidr;
    }

    public void setIp6Cidr(String ip6Cidr) {
        this.ip6Cidr = ip6Cidr;
    }

    public String getIp6Gateway() {
        return ip6Gateway;
    }

    public void setIp6Gateway(String ip6Gateway) {
        this.ip6Gateway = ip6Gateway;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsPersistent() {
        return isPersistent;
    }

    public void setIsPersistent(String isPersistent) {
        this.isPersistent = isPersistent;
    }

    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getNetmask() {
        return netmask;
    }

    public void setNetmask(String netmask) {
        this.netmask = netmask;
    }

    public String getNetworkCidr() {
        return networkCidr;
    }

    public void setNetworkCidr(String networkCidr) {
        this.networkCidr = networkCidr;
    }

    public String getNetworkDomain() {
        return networkDomain;
    }

    public void setNetworkDomain(String networkDomain) {
        this.networkDomain = networkDomain;
    }

    public String getNetworkOfferingAvailability() {
        return networkOfferingAvailability;
    }

    public void setNetworkOfferingAvailability(String networkOfferingAvailability) {
        this.networkOfferingAvailability = networkOfferingAvailability;
    }

    public String getNetworkOfferingConserveMode() {
        return networkOfferingConserveMode;
    }

    public void setNetworkOfferingConserveMode(String networkOfferingConserveMode) {
        this.networkOfferingConserveMode = networkOfferingConserveMode;
    }

    public String getNetworkOfferingDisplayText() {
        return networkOfferingDisplayText;
    }

    public void setNetworkOfferingDisplayText(String networkOfferingDisplayText) {
        this.networkOfferingDisplayText = networkOfferingDisplayText;
    }

    public String getNetworkOfferingId() {
        return networkOfferingId;
    }

    public void setNetworkOfferingId(String networkOfferingId) {
        this.networkOfferingId = networkOfferingId;
    }

    public String getNetworkOfferingName() {
        return networkOfferingName;
    }

    public void setNetworkOfferingName(String networkOfferingName) {
        this.networkOfferingName = networkOfferingName;
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

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public String getReservedIpRange() {
        return reservedIpRange;
    }

    public void setReservedIpRange(String reservedIpRange) {
        this.reservedIpRange = reservedIpRange;
    }

    public String getRestartRequired() {
        return restartRequired;
    }

    public void setRestartRequired(String restartRequired) {
        this.restartRequired = restartRequired;
    }

    public String getSpecifyIpRanges() {
        return specifyIpRanges;
    }

    public void setSpecifyIpRanges(String specifyIpRanges) {
        this.specifyIpRanges = specifyIpRanges;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSubDomainAccess() {
        return subDomainAccess;
    }

    public void setSubDomainAccess(String subDomainAccess) {
        this.subDomainAccess = subDomainAccess;
    }

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
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

    public List<ServiceResponse> getServices() {
        return services;
    }

    public void setServices(List<ServiceResponse> services) {
        this.services = services;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
