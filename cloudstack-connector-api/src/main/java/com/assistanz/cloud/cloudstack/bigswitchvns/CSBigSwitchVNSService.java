package com.assistanz.cloud.cloudstack.bigswitchvns;

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
public class CSBigSwitchVNSService {

    private CloudStackServer server;

    public CSBigSwitchVNSService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds a BigSwitch VNS device
     *
     * @param hostName Hostname of ip address of the BigSwitch VNS Controller
     * @param physicalNetworkId the Physical Network ID
     * @return
     * @throws Exception
     */
    public AddBigSwitchVnsDeviceResponse addBigSwitchVnsDevice(String hostName, String physicalNetworkId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addBigSwitchVnsDevice", null);
        arguments.add(new NameValuePair("hostname", hostName));
        arguments.add(new NameValuePair("physicalnetworkid", physicalNetworkId));

        Document responseDocument = server.makeRequest(arguments);

        return getAddBigSwitchVnsDeviceResponse(responseDocument);
    }

    /**
     * Converts XML document into AddBigSwitchVnsDeviceResponse object
     *
     * @param doc
     * @return
     */
    private AddBigSwitchVnsDeviceResponse getAddBigSwitchVnsDeviceResponse(Document doc) {
        AddBigSwitchVnsDeviceResponse response = new AddBigSwitchVnsDeviceResponse();

        // get bigswitchdevicename from XML and set the device name
        NodeList list = doc.getElementsByTagName("bigswitchdevicename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBigSwitchDeviceName(node.getTextContent());
        }

        // get hostname from XML and set the controller Ip address
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network to which this BigSwitch Vns belongs to
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

        // get vnsdeviceid from XML and set device id of the BigSwitch Vns
        list = doc.getElementsByTagName("vnsdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVnsDeviceId(node.getTextContent());
        }

        return response;
    }

    /**
     * delete a bigswitch vns device
     *
     * @param vnsDeviceId BigSwitch device ID
     * @return
     * @throws Exception
     */
    public DeleteBigSwitchVnsDeviceResponse deleteBigSwitchVnsDevice(String vnsDeviceId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteBigSwitchVnsDevice", null);
        arguments.add(new NameValuePair("vnsdeviceid", vnsDeviceId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteBigSwitchVnsDeviceResponse(responseDocument);

    }

    /**
     * Converts XML document into DeleteBigSwitchVnsDeviceResponse object
     *
     * @param doc
     * @return
     */
    private DeleteBigSwitchVnsDeviceResponse getDeleteBigSwitchVnsDeviceResponse(Document doc) {
        DeleteBigSwitchVnsDeviceResponse response = new DeleteBigSwitchVnsDeviceResponse();

        /* get displaytext from XML and set Any text associated with the success or 
         failure  */
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        /* get success from XML and set Return true if delete operation 
         is executed successfully */
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists BigSwitch Vns devices
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListBigSwitchVnsDevicesResponse listBigSwitchVnsDevices(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listBigSwitchVnsDevices", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListBigSwitchVnsDevicesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListBigSwitchVnsDevicesResponse object
     *
     * @param doc
     * @return
     */
    private ListBigSwitchVnsDevicesResponse getListBigSwitchVnsDevicesResponse(Document doc) {
        ListBigSwitchVnsDevicesResponse response = new ListBigSwitchVnsDevicesResponse();

        NodeList list = doc.getElementsByTagName("bigswitchdevicevns");

        List<BigSwitchVnsDeviceResponse> bigSwitchVnsDevices = new LinkedList<BigSwitchVnsDeviceResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node bigSwitchVnsDeviceNode = list.item(index);
                BigSwitchVnsDeviceResponse bigSwitchVnsDevice = new BigSwitchVnsDeviceResponse();
                NodeList bigSwitchVnsDeviceProperties = bigSwitchVnsDeviceNode.getChildNodes();
                for (int childIndex = 0; childIndex < bigSwitchVnsDeviceProperties.getLength(); childIndex++) {
                    Node property = bigSwitchVnsDeviceProperties.item(childIndex);
                    if (property.getNodeName().equals("bigswitchdevicename")) {
                        bigSwitchVnsDevice.setBigSwitchDeviceName(property.getTextContent());
                    } else if (property.getNodeName().equals("hostname")) {
                        bigSwitchVnsDevice.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        bigSwitchVnsDevice.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                        bigSwitchVnsDevice.setProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("vnsdeviceid")) {
                        bigSwitchVnsDevice.setVnsDeviceId(property.getTextContent());
                    }
                }
                bigSwitchVnsDevices.add(bigSwitchVnsDevice);
            }
        }
        response.setBigSwitchVnsDevices(bigSwitchVnsDevices);
        return response;
    }

}
