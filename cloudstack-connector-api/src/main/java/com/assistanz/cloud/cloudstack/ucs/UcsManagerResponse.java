package com.assistanz.cloud.cloudstack.ucs;

/**
 *
 * @author Santhosh
 *
 */
public class UcsManagerResponse {

    /**
     * the ID of the ucs manager
     */
    private String id;

    /**
     * the name of ucs manager
     */
    private String name;

    /**
     * the url of ucs manager
     */
    private String url;

    /**
     * the zone ID of ucs manager
     */
    private String zoneId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
