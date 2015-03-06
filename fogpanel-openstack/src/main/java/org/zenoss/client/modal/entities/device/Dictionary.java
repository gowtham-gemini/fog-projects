package org.zenoss.client.modal.entities.device;

import org.json.simple.JSONObject;

public class Dictionary {

    private String id;
    private String uid;
    private Boolean hidden;
    private String path;
    private String uuid;
    private Boolean leaf;
    private Boolean hasNoGlobalRoles;
    private String zPythonClass;
    private String iconCls;
    private String text;
    private String name;
    private String severity;
    private String type;
    private String description;
    private String port;

    //Add text entity
    private Dictionary(Builder builder) {
        this.id = builder.id;
        this.uid = builder.uid;
        this.hidden = builder.hidden;
        this.path = builder.path;
        this.uuid = builder.uuid;
        this.leaf = builder.leaf;
        this.hasNoGlobalRoles = builder.hasNoGlobalRoles;
        this.zPythonClass = builder.zPythonClass;
        this.iconCls = builder.iconCls;
        this.text = builder.text;
        this.name = builder.name;
        this.severity = builder.severity;
        this.type = builder.type;
        this.description = builder.description;
        this.port = builder.port;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Boolean getHasNoGlobalRoles() {
        return hasNoGlobalRoles;
    }

    public void setHasNoGlobalRoles(Boolean hasNoGlobalRoles) {
        this.hasNoGlobalRoles = hasNoGlobalRoles;
    }

    public String getzPythonClass() {
        return zPythonClass;
    }

    public void setzPythonClass(String zPythonClass) {
        this.zPythonClass = zPythonClass;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    
    
    public static class Builder {

        private String id;
        private String uid;
        private Boolean hidden;
        private String path;
        private String uuid;
        private Boolean leaf;
        private Boolean hasNoGlobalRoles;
        private String zPythonClass;
        private String iconCls;
        private String text;
        private String name;
        private String severity;
        private String type;
        private String description;
        private String port;

        public Builder(String id, String uid) {
            this.id = id;
            this.uid = uid;
        }

        public Builder hidden(Boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder uuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder zPythonClass(String zPythonClass) {
            this.zPythonClass = zPythonClass;
            return this;
        }

        public Builder iconCls(String iconCls) {
            this.iconCls = iconCls;
            return this;
        }

        public Builder leaf(Boolean leaf) {
            this.leaf = leaf;
            return this;
        }

        public Builder hasNoGlobalRoles(Boolean hasNoGlobalRoles) {
            this.hasNoGlobalRoles = hasNoGlobalRoles;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder severity(String severity) {
            this.severity = severity;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }
        
        public Builder port(String port) {
            this.port = port;
            return this;
        }

        public Dictionary build() {
            return new Dictionary(this);
        }
    }

    public static Dictionary convert(JSONObject jsonObject) {
        String id = (String) jsonObject.get("id");
        String uid = (String) jsonObject.get("uid");
        Boolean hidden = (Boolean) jsonObject.get("hidden");
        String path = (String) jsonObject.get("path");
        String uuid = (String) jsonObject.get("uuid");
        Boolean leaf = (Boolean) jsonObject.get("leaf");
        Boolean hasNoGlobalRoles = (Boolean) jsonObject.get("hasNoGlobalRoles");
        String zPythonClass = (String) jsonObject.get("zPythonClass");
        String iconCls = (String) jsonObject.get("iconCls");
        String text = (String) jsonObject.get("text");
        String name = (String) jsonObject.get("name");
        
        String severity = "";
        // type cast long to string
        if(jsonObject.get("severity") != null ) {
            severity = jsonObject.get("severity").toString();
        }
        
        String type = (String) jsonObject.get("type");
        String description = (String) jsonObject.get("description");
        String port = "";
        // type cast long to string
        if(jsonObject.get("port") != null) {
            port = jsonObject.get("port").toString();
        }
        
        return new Builder(id, uid).hidden(hidden).path(path).uuid(uuid).leaf(leaf).hasNoGlobalRoles(hasNoGlobalRoles)
                .zPythonClass(zPythonClass).iconCls(iconCls).
                text(text).
                name(name).
                severity(severity).
                type(type).
                description(description).
                port(port).
                build();
    }
    public static Dictionary convertLimited(JSONObject jsonObject) {
        
        String path = (String) jsonObject.get("path");
        String name = (String) jsonObject.get("name");
        String text = (String) jsonObject.get("value");
        
        return new Builder("", "").path(path).name(name).text(text).
                build();
    }
}
