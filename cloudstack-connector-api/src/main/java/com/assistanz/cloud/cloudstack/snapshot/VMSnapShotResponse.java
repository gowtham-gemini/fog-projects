package com.assistanz.cloud.cloudstack.snapshot;

/**
 *
 * @author gowtham
 */
class VMSnapShotResponse {

    /**
     * ID of the virtual machine
     */
    private String id;

    /**
     * the account associated with the VMSnapshot
     */
    private String account;

    /**
     * the date the VMSnapshot was created
     */
    private String created;
        
    /**
     * indicates if this is current snapshot
     */
    private String current;

    /**
     * ID of the VMSnapshot description
     */
    private String description;

    /**
     * ID of the VMSnapshot display name
     */
    private String displayName;

    /**
     * the domain name of the VMSnapshot's account
     */
    private String domain;

    /**
     * the domain ID of the VMSnapshot's account
     */
    private String domainId;

    /**
     * name of the VMSnapshot
     */
    private String name;

    /**
     * the parent ID of the vm snapshot
     */
    private String parent;
    
    /**
     * the parent displayName of the vm snapshot
     */
    private String parentName;

    /**
     * the project name of the VMSnapshot
     */
    private String project;

    /**
     * the project id of the VMSnapshot
     */
    private String projectId;

    /**
     * the state of the VMSnapshot. BackedUp means that VMSnapshot is ready to be used; Creating - the VMSnapshot is
     * being allocated on the primary storage; BackingUp - the VMSnapshot is being backed up on secondary storage
     */
    private String state;
    
    /**
     * the type of the VMSnapshot
     */
    private String type;

    /**
     * the vm ID of the vm snapshot
     */
    private String virtualMachineId;
   
    /**
     * ID of the zone
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    public String getVirtualMachineId() {
        return virtualMachineId;
    }

    public void setVirtualMachineId(String virtualMachineId) {
        this.virtualMachineId = virtualMachineId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }
    
}
