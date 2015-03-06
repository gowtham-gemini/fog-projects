package com.assistanz.cloud.cloudstack.accounts;

/**
 *
 * @author Gowtham
 */
public class ListProjectAccountsResponse {
    
    /**
     *  The Id of the project
     */
    private String projectId;
    
    /**
     * the account name of the project's owner
     */
    private String accountName;
    
    /**
     * the display text of the project
     */
    private String displaytext;
    
    /**
     * the domain name where the project belongs to
     */
    private String domainName;
    
    /**
     * the domain id the project belongs to
     */
    private String domainid;
    
    /**
     * the name of the project
     */
    private String projectName;
    
    /**
     * 	the state of the project
     */
    private String projectState;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getDisplaytext() {
        return displaytext;
    }

    public void setDisplaytext(String displaytext) {
        this.displaytext = displaytext;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainid() {
        return domainid;
    }

    public void setDomainid(String domainid) {
        this.domainid = domainid;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }
   
}
