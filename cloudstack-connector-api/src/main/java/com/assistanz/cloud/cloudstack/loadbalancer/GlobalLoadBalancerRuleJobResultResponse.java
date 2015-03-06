package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 */
class GlobalLoadBalancerRuleJobResultResponse {

    /**
     * the account that executed the async command
     */
    String asynchronousAccountId;

    /**
     * the async command executed
     */
    String asynchronousCmd;

    /**
     * the created date of the job
     */
    String asynchronousCreated;

    /**
     * the unique ID of the instance/entity object related to the job
     */
    String asynchronousJobInstanceId;

    /**
     * the instance/entity object related to the job
     */
    String asynchronousJobInstanceType;

    /**
     * the progress information of the PENDING job
     */
    String asynchronousJobProgressStatus;

    /**
     * the result reason
     */
    String asynchronousJobResult;

    /**
     * the result code for the job
     */
    String asynchronousJobResultCode;

    /**
     * the result type
     */
    String asynchronousJobResultType;

    /**
     * the current job status-should be 0 for PENDING
     */
    String asynchronousJobStatus;

    /**
     * the user that executed the async command
     */
    String asynchronousUserId;

    /**
     * the ID of the async job
     */
    String asynchronousJobId;

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

    /**
     * global load balancer rule ID
     */
    private String id;

    /**
     * the account of the load balancer rule
     */
    private String account;

    /**
     * the description of the global load balancer rule
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
    private String gslbLbMethod;

    /**
     * GSLB service type
     */
    private String gslbServiceType;

    /**
     * session persistence method used for the global load balancer
     */
    private String gslbStickySessionMethodName;

    /**
     * name of the global load balancer rule
     */
    private String name;

    /**
     * the project name of the load balancer
     *
     */
    private String project;

    /**
     * the project id of the load balancer
     */
    private String projectId;

    /**
     * Region Id in which global load balancer is created
     */
    private String regionId;

    /**
     * List of load balancer rules that are part of GSLB rule
     */
    private List<LoadBalancerRuleResponse> loadBalancerRules;

    /**
     * the list of resource tags associated with load balancer
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

    public String getGslbLbMethod() {
        return gslbLbMethod;
    }

    public void setGslbLbMethod(String gslbLbMethod) {
        this.gslbLbMethod = gslbLbMethod;
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

    public List<LoadBalancerRuleResponse> getLoadBalancerRules() {
        return loadBalancerRules;
    }

    public void setLoadBalancerRules(List<LoadBalancerRuleResponse> loadBalancerRules) {
        this.loadBalancerRules = loadBalancerRules;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
    }

}
