package com.assistanz.cloud.cloudstack.vpc;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 */
public class StaticRouteResponse {

    /**
     * the id of the static route
     */
    private String staticRouteId;

    /**
     * the owner of the static route
     */
    private String accountName;

    /**
     * static route CIDR
     */
    private String cidr;

    /**
     * the domain associated with the StaticRoute
     */
    private String domainName;

    /**
     * the domain id of the VPC owner
     */
    private String domainId;

    /**
     * VPC gateway the route is created for
     */
    private String gatewayId;

    /**
     * the project name of the VPC
     */
    private String project;

    /**
     * the project id of the VPC
     */
    private String projectId;

    /**
     * state of the VPC, Can be Inactive/Enabled
     */
    private String state;

    /**
     * the ID of the domain in which the virtual machine exists
     */
    private String vpcId;

    /**
     * list of tags associated with the virtual machine
     */
    private List<TagsResponse> tagss;

    public String getStaticRouteId() {
        return staticRouteId;
    }

    public void setStaticRouteId(String staticRouteId) {
        this.staticRouteId = staticRouteId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
