package com.assistanz.cloud.cloudstack.alerts;

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
    public ListAlertResponse listAlerts(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listAlerts", optional);

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

                AlertResponse alert = new AlertResponse();

                NodeList alertProperties = alertNode.getChildNodes();
                for (int childIndex = 0; childIndex < alertProperties.getLength(); childIndex++) {
                    Node property = alertProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        alert.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        alert.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("sent")) {
                        alert.setSent(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        alert.setType(property.getTextContent());
                    }
                }
                alerts.add(alert);
            }

        }
        response.setAlerts(alerts);
        return response;

    }

    /**
     * Archive one or more alerts
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ArchiveAlertsResponse archiveAlerts(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("archiveAlerts", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getArchiveAlertsResponse(responseDocument);
    }

    /**
     * Converts XML document into ArchiveAlertsResponse object
     *
     * @param doc
     * @return
     */
    private ArchiveAlertsResponse getArchiveAlertsResponse(Document doc) {
        ArchiveAlertsResponse response = new ArchiveAlertsResponse();

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
     * Delete one or more alerts.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteAlertsResponse deleteAlerts(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteAlerts", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteAlertsResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteAlertsResponse object
     *
     * @param doc
     * @return
     */
    private DeleteAlertsResponse getDeleteAlertsResponse(Document doc) {
        DeleteAlertsResponse response = new DeleteAlertsResponse();

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
