package com.assistanz.cloud.cloudstack.portableip;

import java.util.HashMap;
import java.util.LinkedList;

//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Santhosh
 *
 */
public class CSPortableIpService {

    private CloudStackServer server;

    public CSPortableIpService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * adds a range of portable public IP's to a region
     *
     * @param endIp the ending IP address in the portable IP range
     * @param gateway the gateway for the portable IP range
     * @param netmask the netmask of the portable IP range
     * @param regionId Id of the Region
     * @param startIp the beginning IP address in the portable IP range
     * @param optional
     * @return
     * @throws Exception
     */
    public CreatePortableIpRangeResponse createPortableIpRange(String endIp, String gateway,
            String netmask, String regionId, String startIp, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createPortableIpRange", optional);
        arguments.add(new NameValuePair("endip", endIp));
        arguments.add(new NameValuePair("gateway", gateway));
        arguments.add(new NameValuePair("netmask", netmask));
        arguments.add(new NameValuePair("regionid", regionId));
        arguments.add(new NameValuePair("startip", startIp));

        Document responseDocument = server.makeRequest(arguments);

        return getCreatePortableIpRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into CreatePortableIpRangeResponse object
     *
     * @param doc
     * @return
     */
    private CreatePortableIpRangeResponse getCreatePortableIpRangeResponse(Document doc) {
        CreatePortableIpRangeResponse response = new CreatePortableIpRangeResponse();

        // get id from XML and set as portable IP range ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get endip from XML and set as the end ip of the portable IP range
        list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndIp(node.getTextContent());
        }

        // get gateway from XML and set as the gateway of the VLAN IP range
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get netmask from XML and set as the netmask of the VLAN IP range
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetmask(node.getTextContent());
        }

        // get regionid from XML and set as the Region Id in which portable ip range is provisioned
        list = doc.getElementsByTagName("regionid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRegionId(node.getTextContent());
        }

        // get startip from XML and set as the start ip of the portable IP range
        list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartIp(node.getTextContent());
        }

        // get vlan from XML and set as the ID or VID of the VLAN.
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get List of portable IP and association with zone/network/vpc details that are part of GSLB rule
        list = doc.getElementsByTagName("portableipaddress");
        if (list.getLength() > 0) {
            List<PortableIpAddressResponse> portableIpAddresses = new LinkedList<PortableIpAddressResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node portableIpAddressNode = list.item(index);
                PortableIpAddressResponse portableIpAddress = new PortableIpAddressResponse();
                NodeList portableIpAddressProperties = portableIpAddressNode.getChildNodes();
                for (int childIndex = 0; childIndex < portableIpAddressProperties.getLength(); childIndex++) {
                    Node portableIpAddressProperty = portableIpAddressProperties.item(childIndex);
                    if (portableIpAddressProperty.getNodeName().equals("account")) {
                        portableIpAddress.setAccountId(portableIpAddressProperty.getTextContent());
                    } else if (portableIpAddressProperty.getNodeName().equals("allocated")) {
                        portableIpAddress.setAllocated(portableIpAddressProperty.getTextContent());
                    } else if (portableIpAddressProperty.getNodeName().equals("domainid")) {
                        portableIpAddress.setDomainId(portableIpAddressProperty.getTextContent());
                    } else if (portableIpAddressProperty.getNodeName().equals("ipaddress")) {
                        portableIpAddress.setIpAddress(portableIpAddressProperty.getTextContent());
                    } else if (portableIpAddressProperty.getNodeName().equals("networkid")) {
                        portableIpAddress.setNetworkId(portableIpAddressProperty.getTextContent());
                    } else if (portableIpAddressProperty.getNodeName().equals("physicalnetworkid")) {
                        portableIpAddress.setPhysicalNetworkId(portableIpAddressProperty.getTextContent());
                    } else if (portableIpAddressProperty.getNodeName().equals("regionid")) {
                        portableIpAddress.setRegionId(portableIpAddressProperty.getTextContent());
                    } else if (portableIpAddressProperty.getNodeName().equals("state")) {
                        portableIpAddress.setState(portableIpAddressProperty.getTextContent());
                    } else if (portableIpAddressProperty.getNodeName().equals("vpcid")) {
                        portableIpAddress.setVpcId(portableIpAddressProperty.getTextContent());
                    } else if (portableIpAddressProperty.getNodeName().equals("zoneid")) {
                        portableIpAddress.setZoneId(portableIpAddressProperty.getTextContent());
                    }
                }
                portableIpAddresses.add(portableIpAddress);
                response.setPortableIpAddresses(portableIpAddresses);
            }
        }
        return response;

    }

    /**
     * deletes a range of portable public IP's associated with a region
     *
     * @param portableIpRangeId Id of the portable ip range
     * @return
     * @throws Exception
     */
    public DeletePortableIpRangeResponse deletePortableIpRange(String portableIpRangeId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deletePortableIpRange", null);
        arguments.add(new NameValuePair("id", portableIpRangeId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeletePortableIpRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into DeletePortableIpRangeResponse object
     *
     * @param doc
     * @return
     */
    private DeletePortableIpRangeResponse getDeletePortableIpRangeResponse(Document doc) {
        DeletePortableIpRangeResponse response = new DeletePortableIpRangeResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Ip Forwarding Rule 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
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
     * list portable IP ranges
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListPortableIpRangesResponse listPortableIpRanges(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listPortableIpRanges", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListPortableIpRangesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListPortableIpRangesResponse object
     *
     * @param doc
     * @return
     */
    private ListPortableIpRangesResponse getListPortableIpRangesResponse(Document doc) {
        ListPortableIpRangesResponse response = new ListPortableIpRangesResponse();

        NodeList list = doc.getElementsByTagName("ipforwardingrule");

        List<PortableIpRangeResponse> portableIpRanges = new LinkedList<PortableIpRangeResponse>();
        if (list.getLength() > 0) {
            for (int portableIpRangeIndex = 0; portableIpRangeIndex < list.getLength(); portableIpRangeIndex++) {
                Node portableIpRangeNode = list.item(portableIpRangeIndex);

                PortableIpRangeResponse portableIpRange = new PortableIpRangeResponse();
                List<PortableIpAddressResponse> portableIpAddresses = new LinkedList<PortableIpAddressResponse>();
                NodeList portableIpRangeProperties = portableIpRangeNode.getChildNodes();
                for (int childIndex = 0; childIndex < portableIpRangeProperties.getLength(); childIndex++) {
                    Node property = portableIpRangeProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        portableIpRange.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("endip")) {
                        portableIpRange.setEndIp(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        portableIpRange.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                        portableIpRange.setNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("regionid")) {
                        portableIpRange.setRegionId(property.getTextContent());
                    } else if (property.getNodeName().equals("startip")) {
                        portableIpRange.setStartIp(property.getTextContent());
                    } else if (property.getNodeName().equals("vlan")) {
                        portableIpRange.setVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("portableipaddress")) {
                        NodeList portableIpAddressProperties = property.getChildNodes();
                        if (portableIpAddressProperties.getLength() > 0) {
                            PortableIpAddressResponse portableIpAddress = new PortableIpAddressResponse();
                            for (int portableIpAddressIndex = 0; portableIpAddressIndex
                                    < portableIpAddressProperties.getLength(); portableIpAddressIndex++) {
                                Node portableIpAddressProperty = portableIpAddressProperties.item(portableIpAddressIndex);
                                if (portableIpAddressProperty.getNodeName().equals("accountid")) {
                                    portableIpAddress.setAccountId(portableIpAddressProperty.getTextContent());
                                } else if (portableIpAddressProperty.getNodeName().equals("allocated")) {
                                    portableIpAddress.setAllocated(portableIpAddressProperty.getTextContent());
                                } else if (portableIpAddressProperty.getNodeName().equals("domainid")) {
                                    portableIpAddress.setDomainId(portableIpAddressProperty.getTextContent());
                                } else if (portableIpAddressProperty.getNodeName().equals("ipaddress")) {
                                    portableIpAddress.setIpAddress(portableIpAddressProperty.getTextContent());
                                } else if (portableIpAddressProperty.getNodeName().equals("networkid")) {
                                    portableIpAddress.setNetworkId(portableIpAddressProperty.getTextContent());
                                } else if (portableIpAddressProperty.getNodeName().equals("physicalnetworkid")) {
                                    portableIpAddress.setPhysicalNetworkId(portableIpAddressProperty.getTextContent());
                                } else if (portableIpAddressProperty.getNodeName().equals("regionid")) {
                                    portableIpAddress.setRegionId(portableIpAddressProperty.getTextContent());
                                } else if (portableIpAddressProperty.getNodeName().equals("state")) {
                                    portableIpAddress.setState(portableIpAddressProperty.getTextContent());
                                } else if (portableIpAddressProperty.getNodeName().equals("vpcid")) {
                                    portableIpAddress.setVpcId(portableIpAddressProperty.getTextContent());
                                } else if (portableIpAddressProperty.getNodeName().equals("zoneid")) {
                                    portableIpAddress.setZoneId(portableIpAddressProperty.getTextContent());
                                }
                            }
                            portableIpAddresses.add(portableIpAddress);
                        }
                    }
                }
                portableIpRange.setPortableIpAddresses(portableIpAddresses);
                portableIpRanges.add(portableIpRange);
            }
        }
        response.setPortableIpRanges(portableIpRanges);
        return response;
    }

}
