package org.zenoss.client.modal.entities.routers;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Node {

    private String id;
    private String description;
    private NodeType type;

    private Node(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.description = builder.description;
    }

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

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public static class Builder {
        private String id;
        private String description;
        private NodeType type;

        public Builder(String id, NodeType type) {
            this.id = id;
            this.type = type;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Node build() {
            return new Node(this);
        }
    }

    public Map toHashMap() {
        Map map = new HashMap();

        map.put("id", this.id);
        map.put("type", this.type.toString());
        map.put("description", this.description);

        return map;
    }

}
