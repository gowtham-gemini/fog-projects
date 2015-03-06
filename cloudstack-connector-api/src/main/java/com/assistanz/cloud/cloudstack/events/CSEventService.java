package com.assistanz.cloud.cloudstack.events;

import java.util.HashMap;
import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSEventService {

    private CloudStackServer server;

    public CSEventService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * A command to list events.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListEventsResponse listEvents(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listEvents", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListEventsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListEventsResponse object
     *
     * @param doc
     * @return
     */
    private ListEventsResponse getListEventsResponse(Document doc) {
        ListEventsResponse response = new ListEventsResponse();

        NodeList list = doc.getElementsByTagName("event");

        List<EventsResponse> events = new LinkedList<EventsResponse>();

        if (list.getLength() > 0) {

            for (int Index = 0; Index < list.getLength(); Index++) {
                Node eventNode = list.item(Index);

                if (eventNode == null) {
                    continue;
                }

                EventsResponse event = new EventsResponse();

                NodeList alertProperties = eventNode.getChildNodes();
                for (int childIndex = 0; childIndex < alertProperties.getLength(); childIndex++) {
                    Node property = alertProperties.item(childIndex);

                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }

                    if (property.getNodeName().equals("id")) {
                        event.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        event.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        event.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        event.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        event.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        event.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("level")) {
                        event.setLevel(property.getTextContent());
                    } else if (property.getNodeName().equals("parentid")) {
                        event.setParentId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        event.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        event.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        event.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        event.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("username")) {
                        event.setUserName(property.getTextContent());
                    }
                }
                events.add(event);
            }

        }
        response.setEvents(events);
        return response;
    }

    /**
     * List Event Types
     *
     * @return
     * @throws Exception
     */
    public ListEventTypesResponse listEventTypes()
            throws Exception {

        return getListEventTypesResponse(null);
    }

    /**
     * Converts XML document into ListEventsResponse object
     *
     * @param doc
     * @return
     */
    private ListEventTypesResponse getListEventTypesResponse(Document doc) {
        ListEventTypesResponse response = new ListEventTypesResponse();

        NodeList list = doc.getElementsByTagName("eventtypes");

        List<EventTypesResponse> eventTypes = new LinkedList<EventTypesResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node eventNode = list.item(Index);
            EventTypesResponse eventType = new EventTypesResponse();

            NodeList eventProperties = eventNode.getChildNodes();
            for (int childIndex = 0; childIndex < eventProperties.getLength(); childIndex++) {
                Node property = eventProperties.item(childIndex);

                if (property.getNodeName().equals("name")) {
                    eventType.setName(property.getTextContent());
                }
                eventTypes.add(eventType);
            }
        }
        response.setEventTypes(eventTypes);
        return response;
    }

    /**
     * Archive one or more events
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ArchiveEventsResponse archiveEvents(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("archiveEvents", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getArchiveEventsResponse(responseDocument);
    }

    /**
     * Converts XML document into ArchiveEventsResponse object
     *
     * @param doc
     * @return
     */
    private ArchiveEventsResponse getArchiveEventsResponse(Document doc) {
        ArchiveEventsResponse response = new ArchiveEventsResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Ip Forwarding Rule 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Ip Forwarding Rule 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Delete one or more events
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteEventsResponse deleteEvents(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteEvents", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteEventsResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteEventsResponse object
     *
     * @param doc
     * @return
     */
    private DeleteEventsResponse getDeleteEventsResponse(Document doc) {
        DeleteEventsResponse response = new DeleteEventsResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Ip Forwarding Rule 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Ip Forwarding Rule 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

}
