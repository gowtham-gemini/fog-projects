package org.zenoss.client.modal.entities.device;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class LocationNode {

    private String id;
    private String contextUid;
    private String description;
    private String address;
    private String type;

    private LocationNode(Builder builder) {
        this.id = builder.id;
        this.contextUid = builder.contextUid;
        this.description = builder.description;
        this.address = builder.address;

        //always ’organizer’ in this case
        this.type = "organizer";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContextUid() {
        return contextUid;
    }

    public void setContextUid(String contextUid) {
        this.contextUid = contextUid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public static class Builder {
        private String id;
        private String contextUid;
        private String description;
        private String address;


        public Builder(String id, String contextUid) {
            this.id = id;
            this.contextUid = contextUid;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public LocationNode build() {
            return new LocationNode(this);
        }
    }

    public Map toHashMap() {
        Map map = new HashMap();

        map.put("id", this.id);
        map.put("type", this.type);
        map.put("contextUid", this.contextUid);
        map.put("address", this.address);
        map.put("description", this.description);
        
        return map;
    }

}
