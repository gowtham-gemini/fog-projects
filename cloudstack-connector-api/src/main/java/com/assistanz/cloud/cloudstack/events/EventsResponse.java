
package com.assistanz.cloud.cloudstack.events;

/**
 *
 * @author gowtham
 */
class EventsResponse {

    /**
     * the ID of the event
     */
    private String id;

    /**
     * the account name for the account that owns the object being acted on in the event (e.g. the owner of the virtual
     * machine, ip address, or security group)
     */
    private String account;

    /**
     * the date the event was created
     */
    private String created;

    /**
     * a brief description of the event
     */
    private String description;

    /**
     * the name of the account's domain
     */
    private String domain;

    /**
     * the id of the account's domain
     */
    private String domainId;

    /**
     * the event level (INFO, WARN, ERROR)
     */
    private String level;

    /**
     * whether the event is parented
     */
    private String parentId;

    /**
     * the project name of the address
     */
    private String project;

    /**
     * the project id of the ip address
     */
    private String projectId;

    /**
     * the state of the event
     */
    private String state;

    /**
     * the type of the event (see event types)
     */
    private String type;

    /**
     * the name of the user who performed the action (can be different from the account if an admin is performing an
     * action for a user, e.g. starting/stopping a user's virtual machine)
     */
    private String userName;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
