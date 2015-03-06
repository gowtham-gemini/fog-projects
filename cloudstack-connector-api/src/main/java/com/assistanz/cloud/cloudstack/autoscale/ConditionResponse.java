package com.assistanz.cloud.cloudstack.autoscale;

/**
 *
 * @author Santhosh
 *
 */
public class ConditionResponse {

    /**
     * the id of the Condition
     */
    private String id;

    /**
     * the owner of the Condition
     */
    private String account;

    /**
     * Details of the Counter
     */
    private String counter;

    /**
     * the domain name of the owner
     */
    private String domain;

    /**
     * the domain id of the Condition owner
     */
    private String domainId;

    /**
     * the project name of the Condition
     */
    private String project;

    /**
     * the project id of the Condition
     */
    private String projectId;

    /**
     * Relational Operator to be used with threshold
     */
    private String relationalOperator;

    /**
     * Threshold Value for the counter
     */
    private String threshold;

    /**
     * zone id of counter
     */
    private String zoneId;

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

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
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

    public String getRelationalOperator() {
        return relationalOperator;
    }

    public void setRelationalOperator(String relationalOperator) {
        this.relationalOperator = relationalOperator;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

}
