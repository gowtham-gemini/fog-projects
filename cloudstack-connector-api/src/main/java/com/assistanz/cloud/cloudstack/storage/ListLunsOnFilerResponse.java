package com.assistanz.cloud.cloudstack.storage;

/**
 *
 * @author Gowtham
 *
 */
public class ListLunsOnFilerResponse {

    /**
     * lun id
     */
    private String lunId;

    /**
     * lun iqn
     */
    private String lunIqn;

    /**
     * lun name
     */
    private String lunName;

    /**
     * volume id
     */
    private String lunVolumeId;

    public String getLunId() {
        return lunId;
    }

    public void setLunId(String lunId) {
        this.lunId = lunId;
    }

    public String getLunIqn() {
        return lunIqn;
    }

    public void setLunIqn(String lunIqn) {
        this.lunIqn = lunIqn;
    }

    public String getLunName() {
        return lunName;
    }

    public void setLunName(String lunName) {
        this.lunName = lunName;
    }

    public String getLunVolumeId() {
        return lunVolumeId;
    }

    public void setLunVolumeId(String lunVolumeId) {
        this.lunVolumeId = lunVolumeId;
    }

}
