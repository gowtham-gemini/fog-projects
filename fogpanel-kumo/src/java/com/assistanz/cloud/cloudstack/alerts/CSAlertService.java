package com.assistanz.cloud.cloudstack.alerts;

import java.util.HashMap;
import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.commons.httpclient.NameValuePair;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;



/**
 * 
 * @author Gowtham
 *
 */
public class CSAlertService {
	
    private CloudStackServer server;

    public CSAlertService(CloudStackServer server) {
            this.server = server;
    }
	
    /**
     * Lists all alerts.
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public ListAlertResponse listAlerts(HashMap<String,String> optional) 
                     throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listAlerts", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getAlertResponse(responseDocument);
     }
	 
    /**
     * Converts XML document into AlertResponse object
     * 
     * @param doc
     * @return
     */
    private ListAlertResponse getAlertResponse(Document doc) {
        ListAlertResponse response = new ListAlertResponse();

                  
        NodeList list = doc.getElementsByTagName("alert");

        List<AlertResponse> alerts = new LinkedList<AlertResponse>();             
					
        if (list.getLength() > 0) {
            
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node alertNode = list.item(Index);

                if (alertNode == null) {
                    continue;
                }  
                
                
                AlertResponse alert = new AlertResponse();
                
                
                NodeList alertProperties = alertNode.getChildNodes();
                for (int childIndex = 0; childIndex < alertProperties.getLength(); childIndex++) {
                    Node property = alertProperties.item(childIndex);
                    
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                                       
                    if (property.getNodeName().equals("id")) {
                        alert.setAlertId(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                    	alert.setAlertDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("sent")) {
                    	alert.setAlertSent(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	alert.setAlertType(property.getTextContent());
                    }                                     
                }
                alerts.add(alert);
            }
        
        }
        response.setAlerts(alerts);
        return response;
            
    }
}
