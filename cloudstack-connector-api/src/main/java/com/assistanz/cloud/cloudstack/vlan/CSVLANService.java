package com.assistanz.cloud.cloudstack.vlan;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSVLANService {

    private CloudStackServer server;

    public CSVLANService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates a VLAN IP range
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateVlanIpRangeResponse createVlanIpRange(String startIp,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createVlanIpRange", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getCreateVlanIpRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVlanIpRangeResponse object
     *
     * @param doc
     * @return
     */
    private CreateVlanIpRangeResponse getCreateVlanIpRangeResponse(Document doc) {
        CreateVlanIpRangeResponse response = new CreateVlanIpRangeResponse();

        // get id from XML and set as the ID of the VLAN IP range
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account of the VLAN IP range
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get description from XML and set as the description of the VLAN IP range
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get domain from XML and set as the domain of the VLAN IP range
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domainid of the VLAN IP range
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get endip from XML and set as the endip of the VLAN IP range
        list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndIp(node.getTextContent());
        }

        // get endipv6 from XML and set as the end ipv6 of the VLAN IP range
        list = doc.getElementsByTagName("endipv6");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndIpv6(node.getTextContent());
        }

        // get forvirtualnetwork from XML and set as the virtual network for the VLAN IP range
        list = doc.getElementsByTagName("forvirtualnetwork");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setForVirtualNetwork(node.getTextContent());
        }

        // get gateway from XML and set as the gateway for the VLAN IP range
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get ip6cidr from XML and set as the cidr of IPv6 network
        list = doc.getElementsByTagName("ip6cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Cidr(node.getTextContent());
        }

        // get ip6gateway from XML and set as the gateway of IPv6 network
        list = doc.getElementsByTagName("ip6gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Gateway(node.getTextContent());
        }

        // get netmask from XML and set as the netmask for the VLAN IP range
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetmask(node.getTextContent());
        }

        // get networkid from XML and set as the networkid for the VLAN IP range
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get physicalnetworkid from XML and set as the physicalnetworkid for the VLAN IP range
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get podid from XML and set as the podid for the VLAN IP range
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the podname for the VLAN IP range
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get project from XML and set as the project name for the VLAN IP range
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the projectid for the VLAN IP range
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get startip from XML and set as the startip for the VLAN IP range
        list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartIp(node.getTextContent());
        }

        // get startipv6 from XML and set as the start ipv6 of the VLAN IP range
        list = doc.getElementsByTagName("startipv6");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartIpv6(node.getTextContent());
        }

        // get vlan from XML and set as the ID or VID of the VLAN.
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get zoneid from XML and set as the zoneid for the VLAN IP range
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;

    }

    /**
     * Creates a VLAN IP range
     *
     * @param vlanId the id of the VLAN IP range
     * @return
     * @throws Exception
     */
    public DeleteVlanIpRangeResponse deleteVlanIpRange(String vlanId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteVlanIpRange", null);
        arguments.add(new NameValuePair("id", vlanId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteVlanIpRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteVlanIpRangeResponse object
     *
     * @param doc
     * @return
     */
    private DeleteVlanIpRangeResponse getDeleteVlanIpRangeResponse(Document doc) {
        DeleteVlanIpRangeResponse response = new DeleteVlanIpRangeResponse();

        // get displaytext from XML and set any text associated with the success or failure 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and true if operation is executed successfully, false otherwise
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all VLAN IP ranges
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListVlanIpRangesResponse listVlanIpRanges(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVlanIpRanges", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListVlanIpRangesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListVlanIpRangesResponse object
     *
     * @param doc
     * @return
     */
    private ListVlanIpRangesResponse getListVlanIpRangesResponse(Document doc) {
        ListVlanIpRangesResponse response = new ListVlanIpRangesResponse();

        NodeList list = doc.getElementsByTagName("vlaniprange");
        List<VlanIpRangeResponse> vlanIpRanges = new LinkedList<VlanIpRangeResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node vlanIpRangeNode = list.item(index);
                VlanIpRangeResponse vlanIpRange = new VlanIpRangeResponse();
                NodeList vlanIpRangeProperties = vlanIpRangeNode.getChildNodes();
                for (int childIndex = 0; childIndex < vlanIpRangeProperties.getLength(); childIndex++) {
                    Node property = vlanIpRangeProperties.item(childIndex);
                    if (property.getNodeName().equals("id")) {
                        vlanIpRange.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        vlanIpRange.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        vlanIpRange.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        vlanIpRange.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        vlanIpRange.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("endip")) {
                        vlanIpRange.setEndIp(property.getTextContent());
                    } else if (property.getNodeName().equals("forvirtualnetwork")) {
                        vlanIpRange.setForVirtualNetwork(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        vlanIpRange.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6cidr")) {
                        vlanIpRange.setIp6Cidr(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6gateway")) {
                        vlanIpRange.setIp6Gateway(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                        vlanIpRange.setNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        vlanIpRange.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        vlanIpRange.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        vlanIpRange.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        vlanIpRange.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        vlanIpRange.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("startip")) {
                        vlanIpRange.setStartIp(property.getTextContent());
                    } else if (property.getNodeName().equals("startipv6")) {
                        vlanIpRange.setStartIpv6(property.getTextContent());
                    } else if (property.getNodeName().equals("vlan")) {
                        vlanIpRange.setVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        vlanIpRange.setZoneId(property.getTextContent());
                    }
                }
                vlanIpRanges.add(vlanIpRange);
            }
        }
        response.setVlanIpRanges(vlanIpRanges);
        return response;
    }

    /**
     * Dedicates a guest vlan range to an account
     *
     * @param vlanAccount
     * @param vlanDomainId
     * @param vlanPhysicalNetworkId
     * @param vlanRange
     * @param optional
     * @return
     * @throws Exception
     */
    public DedicateGuestVlanRangeResponse dedicateGuestVlanRange(String vlanAccount, String vlanDomainId,
            String vlanPhysicalNetworkId, String vlanRange, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("dedicateGuestVlanRange", optional);
        arguments.add(new NameValuePair("account", vlanAccount));
        arguments.add(new NameValuePair("domainid", vlanDomainId));
        arguments.add(new NameValuePair("physicalnetworkid", vlanPhysicalNetworkId));
        arguments.add(new NameValuePair("vlanrange", vlanRange));

        Document responseDocument = server.makeRequest(arguments);

        return getDedicateGuestVlanRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into DedicateGuestVlanRangeResponse object
     *
     * @param doc
     * @return
     */
    private DedicateGuestVlanRangeResponse getDedicateGuestVlanRangeResponse(Document doc) {
        DedicateGuestVlanRangeResponse response = new DedicateGuestVlanRangeResponse();

        // get id from XML and set as the ID of the guest VLAN range
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account of the guest VLAN range
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the guest VLAN range
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain ID of the guest VLAN range
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get guestvlanrange from XML and set as the guest VLAN range
        list = doc.getElementsByTagName("guestvlanrange");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestVlanRange(node.getTextContent());
        }

        // get physicalnetworkid from XML and set as the physical network of the guest vlan range
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get project from XML and set as the project name of the guest vlan range
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the guest vlan range
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get zoneid from XML and set as the zoneid of the guest vlan range
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * Releases a dedicated guest vlan range to the system
     *
     * @param vlanRangeId the ID of the dedicated guest vlan range
     * @return
     * @throws Exception
     */
    public ReleaseDedicatedGuestVlanRangeResponse releaseDedicatedGuestVlanRange(String vlanRangeId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("releaseDedicatedGuestVlanRange", null);
        arguments.add(new NameValuePair("id", vlanRangeId));

        Document responseDocument = server.makeRequest(arguments);

        return getReleaseDedicatedGuestVlanRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into ReleaseDedicatedGuestVlanRangeResponse object
     *
     * @param doc
     * @return
     */
    private ReleaseDedicatedGuestVlanRangeResponse getReleaseDedicatedGuestVlanRangeResponse(Document doc) {
        ReleaseDedicatedGuestVlanRangeResponse response = new ReleaseDedicatedGuestVlanRangeResponse();

        // get displaytext from XML and set any text associated with the success or failure 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and true if operation is executed successfully, false otherwise
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists dedicated guest vlan ranges
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListDedicatedGuestVlanRangesResponse listDedicatedGuestVlanRanges(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listDedicatedGuestVlanRanges", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListDedicatedGuestVlanRangesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListDedicatedGuestVlanRangesResponse object
     *
     * @param doc
     * @return
     */
    private ListDedicatedGuestVlanRangesResponse getListDedicatedGuestVlanRangesResponse(Document doc) {
        ListDedicatedGuestVlanRangesResponse response = new ListDedicatedGuestVlanRangesResponse();

        NodeList list = doc.getElementsByTagName("dedicatedguestvlanranges");
        List<DedicatedGuestVlanRangeResponse> dedicatedGuestVlanRanges = new LinkedList<DedicatedGuestVlanRangeResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node dedicatedGuestVlanRangeNode = list.item(index);
                DedicatedGuestVlanRangeResponse dedicatedGuestVlanRange = new DedicatedGuestVlanRangeResponse();
                NodeList dedicatedGuestVlanRangeProperties = dedicatedGuestVlanRangeNode.getChildNodes();
                for (int childIndex = 0; childIndex < dedicatedGuestVlanRangeProperties.getLength(); childIndex++) {
                    Node property = dedicatedGuestVlanRangeProperties.item(childIndex);
                    if (property.getNodeName().equals("id")) {
                        dedicatedGuestVlanRange.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        dedicatedGuestVlanRange.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        dedicatedGuestVlanRange.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        dedicatedGuestVlanRange.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("guestvlanrange")) {
                        dedicatedGuestVlanRange.setGuestVlanRange(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        dedicatedGuestVlanRange.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        dedicatedGuestVlanRange.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        dedicatedGuestVlanRange.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        dedicatedGuestVlanRange.setZoneId(property.getTextContent());
                    }
                }
                dedicatedGuestVlanRanges.add(dedicatedGuestVlanRange);
            }
        }
        response.setDedicatedGuestVlanRanges(dedicatedGuestVlanRanges);
        return response;
    }

}
