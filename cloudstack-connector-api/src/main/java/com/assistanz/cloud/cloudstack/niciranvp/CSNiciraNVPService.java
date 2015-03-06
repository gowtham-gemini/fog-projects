package com.assistanz.cloud.cloudstack.niciranvp;

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
public class CSNiciraNVPService {

    private CloudStackServer server;

    public CSNiciraNVPService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds a Nicira NVP device
     *
     * @param hostName Hostname of ip address of the Nicira NVP Controller
     * @param password Credentials to access the Nicira Controller API
     * @param physicalNetworkId the Physical Network ID
     * @param transportZoneUuid The Transportzone UUID configured on the Nicira Controller
     * @param userName Credentials to access the Nicira Controller API
     * @param optional
     * @return
     * @throws Exception
     */
    public AddNiciraNvpDeviceResponse addNiciraNvpDevice(String hostName, String password, String physicalNetworkId,
            String transportZoneUuid, String userName, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addNiciraNvpDevice", optional);
        arguments.add(new NameValuePair("hostname", hostName));
        arguments.add(new NameValuePair("password", password));
        arguments.add(new NameValuePair("physicalnetworkid", physicalNetworkId));
        arguments.add(new NameValuePair("transportzoneuuid", transportZoneUuid));
        arguments.add(new NameValuePair("username", userName));

        Document responseDocument = server.makeRequest(arguments);

        return getAddNiciraNvpDeviceResponse(responseDocument);
    }

    /**
     * Converts XML document into AddNiciraNvpDeviceResponse object
     *
     * @param doc
     * @return
     */
    private AddNiciraNvpDeviceResponse getAddNiciraNvpDeviceResponse(Document doc) {
        AddNiciraNvpDeviceResponse response = new AddNiciraNvpDeviceResponse();

        // get hostname from XML and set the controller Ip address
        NodeList list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get l3gatewayserviceuuid from XML and set this L3 gateway service Uuid
        list = doc.getElementsByTagName("l3gatewayserviceuuid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setL3GatewayServiceUuid(node.getTextContent());
        }

        // get niciradevicename from XML and set device name
        list = doc.getElementsByTagName("niciradevicename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNiciraDeviceName(node.getTextContent());
        }

        // get nvpdeviceid from XML and set the device id of the Nicire Nvp
        list = doc.getElementsByTagName("nvpdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNvpDeviceId(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network to which this Nirica Nvp belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get provider from XML and set name of the provider
        list = doc.getElementsByTagName("provider");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProvider(node.getTextContent());
        }

        // get transportzoneuuid from XML ansd set the transport zone Uuid
        list = doc.getElementsByTagName("transportzoneuuid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTransportZoneUuid(node.getTextContent());
        }

        return response;
    }

    /**
     * delete a nicira nvp device
     *
     * @param nvpDeviceId Nicira device ID
     * @return
     * @throws Exception
     */
    public DeleteNiciraNvpDeviceResponse deleteNiciraNvpDevice(String nvpDeviceId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteNiciraNvpDevice", null);
        arguments.add(new NameValuePair("id", nvpDeviceId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteNiciraNvpDeviceResponse(responseDocument);

    }

    /**
     * Converts XML document into DeleteNiciraNvpDeviceResponse object
     *
     * @param doc
     * @return
     */
    private DeleteNiciraNvpDeviceResponse getDeleteNiciraNvpDeviceResponse(Document doc) {
        DeleteNiciraNvpDeviceResponse response = new DeleteNiciraNvpDeviceResponse();

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

    /**
     * Lists Nicira NVP devices
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNiciraNvpDevicesResponse listNiciraNvpDevices(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNiciraNvpDevices", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListNiciraNvpDevicesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListNiciraNvpDevicesResponse object
     *
     * @param doc
     * @return
     */
    private ListNiciraNvpDevicesResponse getListNiciraNvpDevicesResponse(Document doc) {
        ListNiciraNvpDevicesResponse response = new ListNiciraNvpDevicesResponse();

        NodeList list = doc.getElementsByTagName("niciranvpdevice");

        List<NiciraNvpDeviceResponse> niciraNvpDevices = new LinkedList<NiciraNvpDeviceResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node niciraNvpDeviceNode = list.item(index);
                NiciraNvpDeviceResponse niciraNvpDevice = new NiciraNvpDeviceResponse();
                NodeList niciraNvpDeviceProperties = niciraNvpDeviceNode.getChildNodes();
                for (int childIndex = 0; childIndex < niciraNvpDeviceProperties.getLength(); childIndex++) {
                    Node property = niciraNvpDeviceProperties.item(childIndex);
                    if (property.getNodeName().equals("hostname")) {
                        niciraNvpDevice.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("l3gatewayserviceuuid")) {
                        niciraNvpDevice.setL3GatewayServiceUuid(property.getTextContent());
                    } else if (property.getNodeName().equals("niciradevicename")) {
                        niciraNvpDevice.setNiciraDeviceName(property.getTextContent());
                    } else if (property.getNodeName().equals("nvpdeviceid")) {
                        niciraNvpDevice.setNvpDeviceId(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        niciraNvpDevice.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                        niciraNvpDevice.setProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("transportzoneuuid")) {
                        niciraNvpDevice.setTransportZoneUuid(property.getTextContent());
                    }
                }
                niciraNvpDevices.add(niciraNvpDevice);
            }
        }
        response.setNiciraNvpDevices(niciraNvpDevices);
        return response;
    }

}
