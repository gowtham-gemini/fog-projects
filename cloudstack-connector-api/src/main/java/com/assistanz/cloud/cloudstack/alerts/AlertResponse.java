package com.assistanz.cloud.cloudstack.alerts;

/**
 *
 * @author Gowtham
 *
 */
public class AlertResponse {

    /**
     * the id of the alert
     */
    private String id;

    /**
     * description of the alert
     */
    private String description;

    /**
     * the date and time the alert was sent
     */
    private String sent;

    /**
     * the alert type
     */
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
