package org.zenoss.client.modal.entities.routers;

public enum NodeType {
    CLASS {
        @Override
        public String toString() {
            return "class";
        }
    },

    ORGANIZER {
        @Override
        public String toString() {
            return "organizer";
        }
    },

}
