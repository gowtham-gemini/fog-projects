package com.assistanz.cloud.cloudstack.network;

import java.util.List;

import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;

/**
 *
 * @author Gowtham
 *
 */
public class ListF5LoadBalancerNetworksResponse {

    /**
     * the id of the network
     */
    private String networkId;

    /**
     * owner of the network
     */
    private String networkAccount;

    /**
     * acl type - access type to the network
     */
    private String networkAclType;

    /**
     * Broadcast domain type of the network
     */
    private String networkBroadcastDomainType;

    /**
     * broadcast uri of the network
     */
    private String networkBroadcastUri;

    /**
     * the cidr the network
     */
    private String networkCidr;

    /**
     * the displaytext of the network
     */
    private String networkDisplayText;

    /**
     * the first DNS for the network
     */
    private String networkDnsFirst;

    /**
     * the second DNS for the network
     */
    private String networkDnsSecond;

    /**
     * the domain name of the network owner
     */
    private String networkOwnerDomain;

    /**
     * the domain id of the network owner
     */
    private String networkOwnerDomainId;

    /**
     * the network's gateway
     */
    private String networkGateway;

    /**
     * true if network is default, false otherwise
     */
    private String networkIsDefault;

    /**
     * true if network is system, false otherwise
     */
    private String networkIsSystem;

    /**
     * the name of the network
     */
    private String networkName;

    /**
     * the network's netmask
     */
    private String networkNetmask;

    /**
     * the network domain
     */
    private String networkDomain;

    /**
     * availability of the network offering the network is created from
     */
    private String networkOfferingAvailabilit;

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
    private String addressProjectName;

    /**
     * the project id of the ipaddress
     */
    private String addressProjectId;

    /**
     * related to what other network configuration
     */
    private String networkRelated;

    /**
     * true network requires restart
     */
    private String networkRestartRequired;

    /**
     * true if network supports specifying ip ranges, false otherwise
     */
    private String networkSpecifyIpRanges;

    /**
     * state of the network
     */
    private String networkState;

    /**
     * true if users from subdomains can access the domain level network
     */
    private String networkSubdomainAccess;

    /**
     * the traffic type of the network
     */
    private String networkTrafficType;

    /**
     * the type of the network
     */
    private String networkType;

    /**
     * the vlan of the network
     */
    private String networkVlan;

    /**
     * zone id of the network
     */
    private String networkZoneId;

    /**
     * the name of the zone the network belongs to
     */
    private String networkZoneName;

    /**
     * the list of supported services
     */
    private List<ServiceResponse> services;

    /**
     * the list of capabilities
     */
    private List<CapabilityResponse> capabilities;

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public String getNetworkAccount() {
        return networkAccount;
    }

    public void setNetworkAccount(String networkAccount) {
        this.networkAccount = networkAccount;
    }

    public String getNetworkAclType() {
        return networkAclType;
    }

    public void setNetworkAclType(String networkAclType) {
        this.networkAclType = networkAclType;
    }

    public String getNetworkBroadcastDomainType() {
        return networkBroadcastDomainType;
    }

    public void setNetworkBroadcastDomainType(String networkBroadcastDomainType) {
        this.networkBroadcastDomainType = networkBroadcastDomainType;
    }

    public String getNetworkBroadcastUri() {
        return networkBroadcastUri;
    }

    public void setNetworkBroadcastUri(String networkBroadcastUri) {
        this.networkBroadcastUri = networkBroadcastUri;
    }

    public String getNetworkCidr() {
        return networkCidr;
    }

    public void setNetworkCidr(String networkCidr) {
        this.networkCidr = networkCidr;
    }

    public String getNetworkDisplayText() {
        return networkDisplayText;
    }

    public void setNetworkDisplayText(String networkDisplayText) {
        this.networkDisplayText = networkDisplayText;
    }

    public String getNetworkDnsFirst() {
        return networkDnsFirst;
    }

    public void setNetworkDnsFirst(String networkDnsFirst) {
        this.networkDnsFirst = networkDnsFirst;
    }

    public String getNetworkDnsSecond() {
        return networkDnsSecond;
    }

    public void setNetworkDnsSecond(String networkDnsSecond) {
        this.networkDnsSecond = networkDnsSecond;
    }

    public String getNetworkOwnerDomain() {
        return networkOwnerDomain;
    }

    public void setNetworkOwnerDomain(String networkOwnerDomain) {
        this.networkOwnerDomain = networkOwnerDomain;
    }

    public String getNetworkOwnerDomainId() {
        return networkOwnerDomainId;
    }

    public void setNetworkOwnerDomainId(String networkOwnerDomainId) {
        this.networkOwnerDomainId = networkOwnerDomainId;
    }

    public String getNetworkGateway() {
        return networkGateway;
    }

    public void setNetworkGateway(String networkGateway) {
        this.networkGateway = networkGateway;
    }

    public String getNetworkIsDefault() {
        return networkIsDefault;
    }

    public void setNetworkIsDefault(String networkIsDefault) {
        this.networkIsDefault = networkIsDefault;
    }

    public String getNetworkIsSystem() {
        return networkIsSystem;
    }

    public void setNetworkIsSystem(String networkIsSystem) {
        this.networkIsSystem = networkIsSystem;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getNetworkNetmask() {
        return networkNetmask;
    }

    public void setNetworkNetmask(String networkNetmask) {
        this.networkNetmask = networkNetmask;
    }

    public String getNetworkDomain() {
        return networkDomain;
    }

    public void setNetworkDomain(String networkDomain) {
        this.networkDomain = networkDomain;
    }

    public String getNetworkOfferingAvailabilit() {
        return networkOfferingAvailabilit;
    }

    public void setNetworkOfferingAvailabilit(String networkOfferingAvailabilit) {
        this.networkOfferingAvailabilit = networkOfferingAvailabilit;
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

    public String getAddressProjectName() {
        return addressProjectName;
    }

    public void setAddressProjectName(String addressProjectName) {
        this.addressProjectName = addressProjectName;
    }

    public String getAddressProjectId() {
        return addressProjectId;
    }

    public void setAddressProjectId(String addressProjectId) {
        this.addressProjectId = addressProjectId;
    }

    public String getNetworkRelated() {
        return networkRelated;
    }

    public void setNetworkRelated(String networkRelated) {
        this.networkRelated = networkRelated;
    }

    public String getNetworkRestartRequired() {
        return networkRestartRequired;
    }

    public void setNetworkRestartRequired(String networkRestartRequired) {
        this.networkRestartRequired = networkRestartRequired;
    }

    public String getNetworkSpecifyIpRanges() {
        return networkSpecifyIpRanges;
    }

    public void setNetworkSpecifyIpRanges(String networkSpecifyIpRanges) {
        this.networkSpecifyIpRanges = networkSpecifyIpRanges;
    }

    public String getNetworkState() {
        return networkState;
    }

    public void setNetworkState(String networkState) {
        this.networkState = networkState;
    }

    public String getNetworkSubdomainAccess() {
        return networkSubdomainAccess;
    }

    public void setNetworkSubdomainAccess(String networkSubdomainAccess) {
        this.networkSubdomainAccess = networkSubdomainAccess;
    }

    public String getNetworkTrafficType() {
        return networkTrafficType;
    }

    public void setNetworkTrafficType(String networkTrafficType) {
        this.networkTrafficType = networkTrafficType;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getNetworkVlan() {
        return networkVlan;
    }

    public void setNetworkVlan(String networkVlan) {
        this.networkVlan = networkVlan;
    }

    public String getNetworkZoneId() {
        return networkZoneId;
    }

    public void setNetworkZoneId(String networkZoneId) {
        this.networkZoneId = networkZoneId;
    }

    public String getNetworkZoneName() {
        return networkZoneName;
    }

    public void setNetworkZoneName(String networkZoneName) {
        this.networkZoneName = networkZoneName;
    }

    public List<ServiceResponse> getServices() {
        return services;
    }

    public void setServices(List<ServiceResponse> services) {
        this.services = services;
    }

    public List<CapabilityResponse> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<CapabilityResponse> capabilities) {
        this.capabilities = capabilities;
    }

}
