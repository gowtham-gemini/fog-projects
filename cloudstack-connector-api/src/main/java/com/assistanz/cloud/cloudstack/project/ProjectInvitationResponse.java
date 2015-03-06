package com.assistanz.cloud.cloudstack.project;

/**
 *
 * @author Gowtham
 *
 */
public class ProjectInvitationResponse {

    /**
     * the id of the invitation
     */
    private String invitationId;

    /**
     * the account name of the project's owner
     */
    private String projectAccount;

    /**
     * the domain name where the project belongs to
     */
    private String projectDomain;

    /**
     * the domain id the project belongs to
     */
    private String projectDomainId;

    /**
     * the email the invitation was sent to
     */
    private String projectEmail;

    /**
     * the name of the project
     */
    private String projectName;

    /**
     * the id of the project
     */
    private String projectId;

    /**
     * the state of the project
     */
    private String projectState;

    public String getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(String invitationId) {
        this.invitationId = invitationId;
    }

    public String getProjectAccount() {
        return projectAccount;
    }

    public void setProjectAccount(String projectAccount) {
        this.projectAccount = projectAccount;
    }

    public String getProjectDomain() {
        return projectDomain;
    }

    public void setProjectDomain(String projectDomain) {
        this.projectDomain = projectDomain;
    }

    public String getProjectDomainId() {
        return projectDomainId;
    }

    public void setProjectDomainId(String projectDomainId) {
        this.projectDomainId = projectDomainId;
    }

    public String getProjectEmail() {
        return projectEmail;
    }

    public void setProjectEmail(String projectEmail) {
        this.projectEmail = projectEmail;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

}
