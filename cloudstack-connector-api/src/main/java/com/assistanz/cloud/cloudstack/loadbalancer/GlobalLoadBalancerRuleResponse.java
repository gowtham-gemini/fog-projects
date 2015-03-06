package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class GlobalLoadBalancerRuleResponse {

    /**
     * the global load balancer rule ID
     */
    private String id;

    /**
     * the account of the load balancer rule
     */
    private String account;

    /**
     * the description of the load balancer
     */
    private String description;

    /**
     * the domain of the load balancer rule
     */
    private String domain;

    /**
     * the domain ID of the load balancer rule
     */
    private String domainId;

    /**
     * DNS domain name given for the global load balancer
     */
    private String gslbDomainName;

    /**
     * Load balancing method used for the global load balancer
     */
    private String gslbMethod;

    /**
     * GSLB service type
     */
    private String gslbServiceType;

    /**
     * session persistence method used for the global load balancer
     */
    private String gslbStickySessionMethodName;

    /**
     * the name of the load balancer
     */
    private String name;

    /**
     * the project name of the load balancer
     */
    private String project;

    /**
     * the project id of the load balancer
     */
    private String projectId;

    /**
     * the Region Id in which global load balancer is created
     */
    private String regionId;

    /**
     * List of load balancer rules that are part of GSLB rule
     */
    private List<LoadBalancerRuleResponse> loadBalancerRules;

    /**
     * Lists the resource tags associated with load balancer
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getGslbDomainName() {
        return gslbDomainName;
    }

    public void setGslbDomainName(String gslbDomainName) {
        this.gslbDomainName = gslbDomainName;
    }

    public String getGslbMethod() {
        return gslbMethod;
    }

    public void setGslbMethod(String gslbMethod) {
        this.gslbMethod = gslbMethod;
    }

    public String getGslbServiceType() {
        return gslbServiceType;
    }

    public void setGslbServiceType(String gslbServiceType) {
        this.gslbServiceType = gslbServiceType;
    }

    public String getGslbStickySessionMethodName() {
        return gslbStickySessionMethodName;
    }

    public void setGslbStickySessionMethodName(String gslbStickySessionMethodName) {
        this.gslbStickySessionMethodName = gslbStickySessionMethodName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

    public List<LoadBalancerRuleResponse> getLoadBalancerRules() {
        return loadBalancerRules;
    }

    public void setLoadBalancerRules(List<LoadBalancerRuleResponse> loadBalancerRules) {
        this.loadBalancerRules = loadBalancerRules;
    }

}
