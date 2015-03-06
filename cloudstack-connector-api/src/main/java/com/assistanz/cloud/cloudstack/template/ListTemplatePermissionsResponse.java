package com.assistanz.cloud.cloudstack.template;

import java.util.List;

public class ListTemplatePermissionsResponse {

    /**
     * List traffic monitor Hosts
     */
    private List<TemplatePermissionResponse> templatePermissions;

    public List<TemplatePermissionResponse> getTemplatePermissions() {
        return templatePermissions;
    }

    public void setTemplatePermissions(List<TemplatePermissionResponse> templatePermissions) {
        this.templatePermissions = templatePermissions;
    }    
}
