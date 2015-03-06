package com.assistanz.cloud.cloudstack.imagestore;

/**
 *
 * @author Santhosh
 */
class ImageStoreResponse {

    /**
     * the ID of the image store
     */
    private String id;

    /**
     * the details of the image store
     */
    private String details;

    /**
     * the name of the image store
     */
    private String name;

    /**
     * the protocol of the image store
     */
    private String protocol;

    /**
     * the provider name of the image store
     */
    private String providerName;

    /**
     * the scope of the image store
     */
    private String scope;

    /**
     * the url of the image store
     */
    private String url;

    /**
     * the Zone ID of the image store
     */
    private String zoneId;

    /**
     * the Zone name of the image store
     */
    private String zoneName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
 
}
