package com.assistanz.cloud.cloudstack.events;

import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class ListEventsResponse {
	
    /**
     * the list of events
     */
    private List<EventsResponse> events;

    public List<EventsResponse> getEvents() {
        return events;
    }

    public void setEvents(List<EventsResponse> events) {
        this.events = events;
    }
}
