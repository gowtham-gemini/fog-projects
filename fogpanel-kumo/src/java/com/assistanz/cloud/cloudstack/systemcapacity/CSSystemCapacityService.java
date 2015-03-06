package com.assistanz.cloud.cloudstack.systemcapacity;

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
public class CSSystemCapacityService {
	
	private CloudStackServer server;
	
	public CSSystemCapacityService(CloudStackServer server) {
		this.server = server;
	}

    /**
     * Lists all the system wide capacities
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public ListCapacityServiceResponse listCapacity( 
        HashMap<String,String> optional) throws Exception {

            LinkedList<NameValuePair> arguments = 
            server.getDefaultQuery("listCapacity", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListCapacityServiceResponse(responseDocument);
    }
	
    /**
     * Converts XML document into ListCapacityServiceResponse object
     * 
     * @param doc
     * @return
     */
    private ListCapacityServiceResponse getListCapacityServiceResponse(Document doc) {
        ListCapacityServiceResponse response = new ListCapacityServiceResponse();
    	  

            
        NodeList list = doc.getElementsByTagName("capacity");

        List<CapacityResponse> capacities = new LinkedList<CapacityResponse>();            
        if (list.getLength() > 0) {
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node capacityNode = list.item(Index);

                if (capacityNode == null) {
                    continue;
                }  
                
                CapacityResponse capacity = new CapacityResponse();
                        
                NodeList capacityProperties = capacityNode.getChildNodes();
                for (int childIndex = 0; childIndex < capacityProperties.getLength(); childIndex++) {
                    Node property = capacityProperties.item(childIndex);
                    
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                                       
                    if (property.getNodeName().equals("capacitytotal")) {
                        capacity.setCapacityTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("capacityused")) {
                    	capacity.setCapacityUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                    	capacity.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                    	capacity.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("percentused")) {
                    	capacity.setPercentUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                    	capacity.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                    	capacity.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	capacity.setCapacityType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	capacity.setZoneId(property.getTextContent());
                    }  else if (property.getNodeName().equals("zonename")) {
                    	capacity.setZoneName(property.getTextContent());
                    } 
                                    
                }
                capacities.add(capacity);
            }
        } 
        response.setCapacities(capacities);
        return response;
        
    } 

}
