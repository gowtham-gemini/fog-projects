package com.assistanz.cloud.cloudstack.events;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListEventTypesResponse {

    /**
     * List Event Types
     */
    private List<EventTypesResponse> eventTypes;

    public List<EventTypesResponse> getEventTypes() {
        return eventTypes;
    }

    public void setEventTypes(List<EventTypesResponse> eventTypes) {
        this.eventTypes = eventTypes;
    }

}
