package com.assistanz.cloud.cloudstack.project;

import java.util.List;

/**
 *
 * @author Santhosh Bheeman
 */
public class ListProjectsResponse {

    /*
     * Lists projects and provides detailed information for listed projects
     */
    private List<ProjectResponse> projects;

    public List<ProjectResponse> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectResponse> projects) {
        this.projects = projects;
    }

}
