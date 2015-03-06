package com.assistanz.cloud.cloudstack.events;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;


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
	public ListEventsResponse listEvents(HashMap<String,String> optional) 
			 throws Exception {
	        
	        LinkedList<NameValuePair> arguments = 
	                server.getDefaultQuery("listEvents", optional);
	        
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
                        event.setEventId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                    	event.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                    	event.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                    	event.setEventDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                    	event.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                    	event.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("level")) {
                    	event.setEventLevel(property.getTextContent());
                    } else if (property.getNodeName().equals("parentid")) {
                    	event.setEventParentId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                    	event.setProjectName(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                    	event.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                    	event.setEventState(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	event.setEventType(property.getTextContent());
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
					
		// get name from XML and set as the  event type name
	    NodeList list = doc.getElementsByTagName("name");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setEventTypeName(node.getNodeValue());
	    }
	    
	    return response;
	    
	 }
}
