package com.assistanz.cloud.cloudstack.securitygroup;

import java.util.List;
import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.TagsResponse;

/**
 *
 * @author Gowtham
 *
 */
public class CreateSecurityGroupResponse {

    /**
     * the ID of the security group
     */
    private String id;

    /**
     * the account owning the security group
     */
    private String account;

    /**
     * the description of the security group
     */
    private String description;
    /**
     * the domain name of the security group
     */
    private String domain;

    /**
     * the domain ID of the security group
     */
    private String domainId;

    /**
     * the name of the security group
     */
    private String name;

    /**
     * the project name of the group
     */
    private String project;

    /**
     * the project id of the group
     */
    private String projectId;

    /**
     * the list of egress rules associated with the security group
     */
    private List<EgressRuleResponse> egressRules;

    /**
     * the list of ingress rules associated with the security group
     */
    private List<IngressRuleResponse> ingressRules;

    /**
     * the list of resource tags associated with the rule
     */
    private List<TagsResponse> tagss;
    
    /**
     * the ID of the latest async job acting on this object
     */
    private String jobId;

    /**
     * the current status of the latest async job acting on this object
     */
    private String jobStatus;

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

    public List<EgressRuleResponse> getEgressRules() {
        return egressRules;
    }

    public void setEgressRules(List<EgressRuleResponse> egressRules) {
        this.egressRules = egressRules;
    }

    public List<IngressRuleResponse> getIngressRules() {
        return ingressRules;
    }

    public void setIngressRules(List<IngressRuleResponse> ingressRules) {
        this.ingressRules = ingressRules;
    }

    public List<TagsResponse> getTagss() {
        return tagss;
    }

    public void setTagss(List<TagsResponse> tagss) {
        this.tagss = tagss;
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

}
