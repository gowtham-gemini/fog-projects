package com.assistanz.cloud.cloudstack.configuration;

import java.util.List;

public class ListConfigurationsResponse {

    /**
     * Lists all configurations
     */
    private List<ConfigurationResponse> configurations;

    public List<ConfigurationResponse> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<ConfigurationResponse> configurations) {
        this.configurations = configurations;
    }

}
