package com.assistanz.cloud.cloudstack.ucs;

/**
 *
 * @author Santhosh
 *
 */
public class AssociateUcsProfileToBladeResponse {

    /**
     * ucs blade id
     */
    private String id;

    /**
     * ucs blade dn
     */
    private String bladeDn;

    /**
     * cloudstack host id this blade associates to
     */
    private String hostId;

    /**
     * associated ucs profile dn
     */
    private String profileDn;

    /**
     * ucs manager id
     */
    private String ucsManagerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBladeDn() {
        return bladeDn;
    }

    public void setBladeDn(String bladeDn) {
        this.bladeDn = bladeDn;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getProfileDn() {
        return profileDn;
    }

    public void setProfileDn(String profileDn) {
        this.profileDn = profileDn;
    }

    public String getUcsManagerId() {
        return ucsManagerId;
    }

    public void setUcsManagerId(String ucsManagerId) {
        this.ucsManagerId = ucsManagerId;
    }

}
