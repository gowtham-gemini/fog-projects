package com.assistanz.cloud.cloudstack.networkdevice;

import java.util.HashMap;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Santhosh
 *
 */
public class CSNetworkDeviceService {

    private CloudStackServer server;

    public CSNetworkDeviceService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds a network device of one of the following types: ExternalDhcp, ExternalFirewall, ExternalLoadBalancer,
     * PxeServer
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public AddNetworkDeviceResponse addNetworkDevice(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addNetworkDevice", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getAddNetworkDeviceResponse(responseDocument);
    }

    /**
     * Converts XML document into AddNetworkDeviceResponse object
     *
     * @param doc
     * @return
     */
    private AddNetworkDeviceResponse getAddNetworkDeviceResponse(Document doc) {
        AddNetworkDeviceResponse response = new AddNetworkDeviceResponse();

        // get id from XML and set the ID of the network device
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        return response;
    }

    /**
     * List network devices
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNetworkDeviceResponse listNetworkDevice(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNetworkDevice", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListNetworkDeviceResponse(responseDocument);
    }

    /**
     * Converts XML document into ListNetworkDeviceResponse object
     *
     * @param doc
     * @return
     */
    private ListNetworkDeviceResponse getListNetworkDeviceResponse(Document doc) {
        ListNetworkDeviceResponse response = new ListNetworkDeviceResponse();

        NodeList list = doc.getElementsByTagName("networkdevice");

        List<NetworkDeviceResponse> networkDevices = new LinkedList<NetworkDeviceResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node networkDeviceNode = list.item(index);
                NetworkDeviceResponse networkDevice = new NetworkDeviceResponse();
                NodeList networkDeviceProperties = networkDeviceNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkDeviceProperties.getLength(); childIndex++) {
                    Node property = networkDeviceProperties.item(childIndex);
                    if (property.getNodeName().equals("id")) {
                        networkDevice.setId(property.getTextContent());
                    }
                }
                networkDevices.add(networkDevice);
            }
        }
        response.setNetworkDevices(networkDevices);
        return response;
    }

    /**
     * Deletes network device
     *
     * @param networkDeviceId Id of network device to delete
     * @return
     * @throws Exception
     */
    public DeleteNetworkDeviceResponse deleteNetworkDevice(String networkDeviceId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteNetworkDevice", null);
        arguments.add(new NameValuePair("id", networkDeviceId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteNetworkDeviceResponse(responseDocument);

    }

    /**
     * Converts XML document into DeleteNetworkDeviceResponse object
     *
     * @param doc
     * @return
     */
    private DeleteNetworkDeviceResponse getDeleteNetworkDeviceResponse(Document doc) {
        DeleteNetworkDeviceResponse response = new DeleteNetworkDeviceResponse();

        /* get displaytext from XML and set Any text associated with the success or 
         failure on deleting  project invitation */
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        /* get success from XML and set Return true if delete project invitation operation 
         is executed successfully */
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

}
