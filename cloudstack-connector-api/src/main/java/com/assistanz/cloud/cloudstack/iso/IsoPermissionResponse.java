package com.assistanz.cloud.cloudstack.iso;

/**
 *
 * @author Santhosh
 */
class IsoPermissionResponse {

    /**
     * the template ID
     */
    private String id;

    /**
     * the list of accounts the template is available for
     */
    private String account;

    /**
     * the ID of the domain to which the template belongs
     */
    private String domainId;

    /**
     * true if this template is a public template, false otherwise
     */
    private String isPublic;

    /**
     * the list of projects the template is available for
     */
    private String projectIds;

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

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(String projectIds) {
        this.projectIds = projectIds;
    }

}
