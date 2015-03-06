package com.assistanz.cloud.cloudstack.hypervisors;

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
public class CSHypervisorsService {

    private CloudStackServer server;

    public CSHypervisorsService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * List Hypervisor
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListHypervisorsResponse listHypervisors(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listHypervisors", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListHypervisorsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListHypervisorsResponse object
     *
     * @param doc
     * @return
     */
    private ListHypervisorsResponse getListHypervisorsResponse(Document doc) {
        ListHypervisorsResponse response = new ListHypervisorsResponse();

        //list of Hypervisor
        NodeList list = doc.getElementsByTagName("hypervisor");

        List<HypervisorResponse> hypervisors = new LinkedList<HypervisorResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node hypervisorNode = list.item(index);

                HypervisorResponse hypervisor = new HypervisorResponse();
                NodeList hypervisorProperties = hypervisorNode.getChildNodes();
                for (int childIndex = 0; childIndex < hypervisorProperties.getLength(); childIndex++) {
                    Node property = hypervisorProperties.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        hypervisor.setName(property.getTextContent());
                    }
                }
                hypervisors.add(hypervisor);
            }
        }
        response.setHypervisors(hypervisors);
        return response;
    }

    /**
     * Updates a hypervisor capabilities.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateHypervisorCapabilitiesResponse updateHypervisorCapabilities(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateHypervisorCapabilities", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateHypervisorCapabilitiesResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateHypervisorCapabilitiesResponse object
     *
     * @param doc
     * @return
     */
    private UpdateHypervisorCapabilitiesResponse getUpdateHypervisorCapabilitiesResponse(Document doc) {
        UpdateHypervisorCapabilitiesResponse response = new UpdateHypervisorCapabilitiesResponse();

        // get id from XML and set as the ID of the hypervisor capabilities row
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get hypervisor from XML and set as the hypervisor type
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getTextContent());
        }

        // get maxdatavolumeslimit from XML and set as the maximum number of Data Volumes that can be attached for this hypervisor
        list = doc.getElementsByTagName("maxdatavolumeslimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxDataVolumesLimit(node.getTextContent());
        }

        // get maxguestslimit from XML and set as the maximum number of guest vms recommended for this hypervisor
        list = doc.getElementsByTagName("maxguestslimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxGuestsLimit(node.getTextContent());
        }

        // get maxhostspercluster from XML and set as the maximum number of Hosts per cluster for this hypervisor
        list = doc.getElementsByTagName("maxhostspercluster");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxHostsPerCluster(node.getTextContent());
        }

        // get securitygroupenabled from XML and set as true if security group is supported
        list = doc.getElementsByTagName("securitygroupenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecurityGroupEnabled(node.getTextContent());
        }

        // get storagemotionenabled from XML and set true if storage motion is supported
        list = doc.getElementsByTagName("storagemotionenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageMotionEnabled(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all hypervisor capabilities
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListHypervisorCapabilitiesResponse listHypervisorCapabilities(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listHypervisorCapabilities", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListHypervisorCapabilitiesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListHypervisorCapabilitiesResponse object
     *
     * @param doc
     * @return
     */
    private ListHypervisorCapabilitiesResponse getListHypervisorCapabilitiesResponse(Document doc) {
        ListHypervisorCapabilitiesResponse response = new ListHypervisorCapabilitiesResponse();

        // Lists all hypervisor capabilities
        NodeList list = doc.getElementsByTagName("hypervisorcapabilities");

        List<HypervisorCapabilitiesResponse> hypervisorCapabilitiess = new LinkedList<HypervisorCapabilitiesResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node hypervisorCapabilitiesNode = list.item(index);

                HypervisorCapabilitiesResponse hypervisorCapabilities = new HypervisorCapabilitiesResponse();
                NodeList hypervisorCapabilitiesProperties = hypervisorCapabilitiesNode.getChildNodes();
                for (int childIndex = 0; childIndex < hypervisorCapabilitiesProperties.getLength(); childIndex++) {
                    Node property = hypervisorCapabilitiesProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        hypervisorCapabilities.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                        hypervisorCapabilities.setHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisorversion")) {
                        hypervisorCapabilities.setHypervisorVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("maxdatavolumeslimit")) {
                        hypervisorCapabilities.setMaxDataVolumesLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("maxguestslimit")) {
                        hypervisorCapabilities.setMaxGuestsLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("maxhostspercluster")) {
                        hypervisorCapabilities.setMaxHostsPerCluster(property.getTextContent());
                    } else if (property.getNodeName().equals("securitygroupenabled")) {
                        hypervisorCapabilities.setSecurityGroupEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("storagemotionenabled")) {
                        hypervisorCapabilities.setStorageMotionEnabled(property.getTextContent());
                    }
                }
                hypervisorCapabilitiess.add(hypervisorCapabilities);
            }
        }
        response.setHypervisorCapabilitiess(hypervisorCapabilitiess);
        return response;

    }

}
