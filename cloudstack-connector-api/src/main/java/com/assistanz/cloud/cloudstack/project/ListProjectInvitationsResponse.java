package com.assistanz.cloud.cloudstack.project;

import java.util.List;

/**
 *
 * @author Santhosh Bheeman
 */
public class ListProjectInvitationsResponse {

    /*
     * Lists projects and provides detailed information for listed projects
     */
    private List<ProjectInvitationResponse> projectInvitations;

    public List<ProjectInvitationResponse> getProjectInvitations() {
        return projectInvitations;
    }

    public void setProjectInvitations(List<ProjectInvitationResponse> projectInvitations) {
        this.projectInvitations = projectInvitations;
    }

}
