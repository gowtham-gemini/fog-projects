package com.assistanz.cloud.cloudstack.configuration;

import java.util.List;

public class ListDeploymentPlannersResponse {

    /**
     * Lists all DeploymentPlanners available.
     */
    private List<DeploymentPlannerResponse> deploymentPlanners;

    public List<DeploymentPlannerResponse> getDeploymentPlanners() {
        return deploymentPlanners;
    }

    public void setDeploymentPlanners(List<DeploymentPlannerResponse> deploymentPlanners) {
        this.deploymentPlanners = deploymentPlanners;
    }

}
