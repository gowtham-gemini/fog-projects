package com.assistanz.cloud.cloudstack.router;

import java.util.HashMap;
import java.util.LinkedList;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSRouterService {

    private CloudStackServer server;

    public CSRouterService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Starts a router.
     *
     * @param routerId the ID of the router
     * @return
     * @throws Exception
     */
    public StartRouterResponse startRouter(String routerId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("startRouter", null);
        arguments.add(new NameValuePair("id", routerId));

        Document responseDocument = server.makeRequest(arguments);

        return getStartRouterResponse(responseDocument);
    }

    /**
     * Converts XML document into StartRouterResponse object
     *
     * @param doc
     * @return
     */
    private StartRouterResponse getStartRouterResponse(Document doc) {
        StartRouterResponse response = new StartRouterResponse();

        // get id from XML and set the id of the router    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the router
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get created from XML and set the date and time the router was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the dns first for the router
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the dns second for the router
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get domain from XML and set the domain for the router
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domainid for the router
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainid(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the router
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get guestipaddress from XML and set the guest ip address for the router
        list = doc.getElementsByTagName("guestipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestIpAddress(node.getTextContent());
        }

        // get guestmacaddress from XML and set the guest maca ddress for the router
        list = doc.getElementsByTagName("guestmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestMacAddress(node.getTextContent());
        }

        // get guestnetmask from XML and set the guest net mask for the router
        list = doc.getElementsByTagName("guestnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestNetmask(node.getTextContent());
        }

        // get guestnetworkid from XML and set the guest net workid for the router
        list = doc.getElementsByTagName("guestnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestNetworkId(node.getTextContent());
        }

        // get hostid from XML and set the host id for the router
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the router
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get ip6dns1 from XML and set the first IPv6 DNS for the router
        list = doc.getElementsByTagName("ip6dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns1(node.getTextContent());
        }

        // get ip6dns2 from XML and set the second IPv6 DNS for the router
        list = doc.getElementsByTagName("ip6dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns2(node.getTextContent());
        }

        // get isredundantrouter from XML and set the is redundant router for the router
        list = doc.getElementsByTagName("isredundantrouter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsRedundantRouter(node.getTextContent());
        }

        // get linklocalip from XML and set the linkl ocal ip for the router
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the router
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local net mask for the router
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get linklocalnetworkid from XML and set the link local network id for the router
        list = doc.getElementsByTagName("linklocalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetworkId(node.getTextContent());
        }

        // get name from XML and set the name for the router
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the networkdomain for the router
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the router
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get project from XML and set the project name for the router
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set the project id for the router
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the router
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address  for the router
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the router
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get publicnetworkid from XML and set the public network id for the router
        list = doc.getElementsByTagName("publicnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetworkId(node.getTextContent());
        }

        // get redundantstate from XML and set the redundant state for the router
        list = doc.getElementsByTagName("redundantstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRedundantState(node.getTextContent());
        }

        // get role from XML and set role of the domain router
        list = doc.getElementsByTagName("role");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRole(node.getTextContent());
        }

        // get scriptsversion from XML and set the script sversion for the router
        list = doc.getElementsByTagName("scriptsversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScriptsVersion(node.getTextContent());
        }

        // get serviceofferingid from XML and set the service offering id for the router
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set the service offering name for the router
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get state from XML and set the state for the router
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get templateid from XML and set the templateid for the router
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get templateversion from XML and set the templateversion for the router
        list = doc.getElementsByTagName("templateversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateVersion(node.getTextContent());
        }

        // get vpcid from XML and set VPC the router belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get zoneid from XML and set the zoneid for the router
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zonename for the router
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // the list of nics associated with the router
        list = doc.getElementsByTagName("nic");
        if (list.getLength() > 0) {
            List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node networkInterfaceCardNode = list.item(index);
                NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                NodeList networkInterfaceCardProperties = networkInterfaceCardNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkInterfaceCardProperties.getLength(); childIndex++) {
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        return response;
    }

    /**
     * Reboots router
     *
     * @param routerId the ID of the router
     * @return
     * @throws Exception
     */
    public RebootRouterResponse rebootRouter(String routerId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("rebootRouter", null);
        arguments.add(new NameValuePair("id", routerId));

        Document responseDocument = server.makeRequest(arguments);

        return getRebootRouterResponse(responseDocument);
    }

    /**
     * Converts XML document into RebootRouterResponse object
     *
     * @param doc
     * @return
     */
    private RebootRouterResponse getRebootRouterResponse(Document doc) {
        RebootRouterResponse response = new RebootRouterResponse();

        // get id from XML and set the id of the router    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the router
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get created from XML and set the date and time the router was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the dns first for the router
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the dns second for the router
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get domain from XML and set the domain for the router
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domainid for the router
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainid(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the router
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get guestipaddress from XML and set the guest ip address for the router
        list = doc.getElementsByTagName("guestipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestIpAddress(node.getTextContent());
        }

        // get guestmacaddress from XML and set the guest maca ddress for the router
        list = doc.getElementsByTagName("guestmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestMacAddress(node.getTextContent());
        }

        // get guestnetmask from XML and set the guest net mask for the router
        list = doc.getElementsByTagName("guestnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestNetmask(node.getTextContent());
        }

        // get guestnetworkid from XML and set the guest net workid for the router
        list = doc.getElementsByTagName("guestnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestNetworkId(node.getTextContent());
        }

        // get hostid from XML and set the host id for the router
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the router
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get ip6dns1 from XML and set the first IPv6 DNS for the router
        list = doc.getElementsByTagName("ip6dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns1(node.getTextContent());
        }

        // get ip6dns2 from XML and set the second IPv6 DNS for the router
        list = doc.getElementsByTagName("ip6dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns2(node.getTextContent());
        }

        // get isredundantrouter from XML and set the is redundant router for the router
        list = doc.getElementsByTagName("isredundantrouter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsRedundantRouter(node.getTextContent());
        }

        // get linklocalip from XML and set the linkl ocal ip for the router
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the router
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local net mask for the router
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get linklocalnetworkid from XML and set the link local network id for the router
        list = doc.getElementsByTagName("linklocalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetworkId(node.getTextContent());
        }

        // get name from XML and set the name for the router
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the networkdomain for the router
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the router
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get project from XML and set the project name for the router
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set the project id for the router
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the router
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address  for the router
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the router
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get publicnetworkid from XML and set the public network id for the router
        list = doc.getElementsByTagName("publicnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetworkId(node.getTextContent());
        }

        // get redundantstate from XML and set the redundant state for the router
        list = doc.getElementsByTagName("redundantstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRedundantState(node.getTextContent());
        }

        // get role from XML and set role of the domain router
        list = doc.getElementsByTagName("role");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRole(node.getTextContent());
        }

        // get scriptsversion from XML and set the script sversion for the router
        list = doc.getElementsByTagName("scriptsversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScriptsVersion(node.getTextContent());
        }

        // get serviceofferingid from XML and set the service offering id for the router
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set the service offering name for the router
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get state from XML and set the state for the router
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get templateid from XML and set the templateid for the router
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get templateversion from XML and set the templateversion for the router
        list = doc.getElementsByTagName("templateversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateVersion(node.getTextContent());
        }

        // get vpcid from XML and set VPC the router belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get zoneid from XML and set the zoneid for the router
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zonename for the router
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // the list of nics associated with the router
        list = doc.getElementsByTagName("nic");
        if (list.getLength() > 0) {
            List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node networkInterfaceCardNode = list.item(index);
                NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                NodeList networkInterfaceCardProperties = networkInterfaceCardNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkInterfaceCardProperties.getLength(); childIndex++) {
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        return response;
    }

    /**
     * Stops a router.
     *
     * @param routerId the ID of the router
     * @param optional
     * @return
     * @throws Exception
     */
    public StopRouterResponse stopRouter(String routerId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("stopRouter", optional);
        arguments.add(new NameValuePair("id", routerId));

        Document responseDocument = server.makeRequest(arguments);

        return getStopRouterResponse(responseDocument);
    }

    /**
     * Converts XML document into StopRouterResponse object
     *
     * @param doc
     * @return
     */
    private StopRouterResponse getStopRouterResponse(Document doc) {
        StopRouterResponse response = new StopRouterResponse();

        // get id from XML and set the id of the router    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the router
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get created from XML and set the date and time the router was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the dns first for the router
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the dns second for the router
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get domain from XML and set the domain for the router
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domainid for the router
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainid(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the router
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get guestipaddress from XML and set the guest ip address for the router
        list = doc.getElementsByTagName("guestipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestIpAddress(node.getTextContent());
        }

        // get guestmacaddress from XML and set the guest maca ddress for the router
        list = doc.getElementsByTagName("guestmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestMacAddress(node.getTextContent());
        }

        // get guestnetmask from XML and set the guest net mask for the router
        list = doc.getElementsByTagName("guestnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestNetmask(node.getTextContent());
        }

        // get guestnetworkid from XML and set the guest net workid for the router
        list = doc.getElementsByTagName("guestnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestNetworkId(node.getTextContent());
        }

        // get hostid from XML and set the host id for the router
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the router
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get ip6dns1 from XML and set the first IPv6 DNS for the router
        list = doc.getElementsByTagName("ip6dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns1(node.getTextContent());
        }

        // get ip6dns2 from XML and set the second IPv6 DNS for the router
        list = doc.getElementsByTagName("ip6dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns2(node.getTextContent());
        }

        // get isredundantrouter from XML and set the is redundant router for the router
        list = doc.getElementsByTagName("isredundantrouter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsRedundantRouter(node.getTextContent());
        }

        // get linklocalip from XML and set the linkl ocal ip for the router
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the router
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local net mask for the router
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get linklocalnetworkid from XML and set the link local network id for the router
        list = doc.getElementsByTagName("linklocalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetworkId(node.getTextContent());
        }

        // get name from XML and set the name for the router
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the networkdomain for the router
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the router
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get project from XML and set the project name for the router
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set the project id for the router
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the router
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address  for the router
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the router
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get publicnetworkid from XML and set the public network id for the router
        list = doc.getElementsByTagName("publicnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetworkId(node.getTextContent());
        }

        // get redundantstate from XML and set the redundant state for the router
        list = doc.getElementsByTagName("redundantstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRedundantState(node.getTextContent());
        }

        // get role from XML and set role of the domain router
        list = doc.getElementsByTagName("role");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRole(node.getTextContent());
        }

        // get scriptsversion from XML and set the script sversion for the router
        list = doc.getElementsByTagName("scriptsversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScriptsVersion(node.getTextContent());
        }

        // get serviceofferingid from XML and set the service offering id for the router
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set the service offering name for the router
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get state from XML and set the state for the router
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get templateid from XML and set the templateid for the router
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get templateversion from XML and set the templateversion for the router
        list = doc.getElementsByTagName("templateversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateVersion(node.getTextContent());
        }

        // get vpcid from XML and set VPC the router belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get zoneid from XML and set the zoneid for the router
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zonename for the router
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // the list of nics associated with the router
        list = doc.getElementsByTagName("nic");
        if (list.getLength() > 0) {
            List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node networkInterfaceCardNode = list.item(index);
                NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                NodeList networkInterfaceCardProperties = networkInterfaceCardNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkInterfaceCardProperties.getLength(); childIndex++) {
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        return response;
    }

    /**
     * Destroys a router.
     *
     * @param routerId the ID of the router
     * @return
     * @throws Exception
     */
    public DestroyRouterResponse destroyRouter(String routerId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("destroyRouter", null);
        arguments.add(new NameValuePair("id", routerId));

        Document responseDocument = server.makeRequest(arguments);

        return getDestroyRouterResponse(responseDocument);
    }

    /**
     * Converts XML document into DestroyRouterResponse object
     *
     * @param doc
     * @return
     */
    private DestroyRouterResponse getDestroyRouterResponse(Document doc) {
        DestroyRouterResponse response = new DestroyRouterResponse();

        // get id from XML and set the id of the router    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the router
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get created from XML and set the date and time the router was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the dns first for the router
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the dns second for the router
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get domain from XML and set the domain for the router
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domainid for the router
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainid(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the router
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get guestipaddress from XML and set the guest ip address for the router
        list = doc.getElementsByTagName("guestipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestIpAddress(node.getTextContent());
        }

        // get guestmacaddress from XML and set the guest maca ddress for the router
        list = doc.getElementsByTagName("guestmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestMacAddress(node.getTextContent());
        }

        // get guestnetmask from XML and set the guest net mask for the router
        list = doc.getElementsByTagName("guestnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestNetmask(node.getTextContent());
        }

        // get guestnetworkid from XML and set the guest net workid for the router
        list = doc.getElementsByTagName("guestnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestNetworkId(node.getTextContent());
        }

        // get hostid from XML and set the host id for the router
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the router
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get ip6dns1 from XML and set the first IPv6 DNS for the router
        list = doc.getElementsByTagName("ip6dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns1(node.getTextContent());
        }

        // get ip6dns2 from XML and set the second IPv6 DNS for the router
        list = doc.getElementsByTagName("ip6dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns2(node.getTextContent());
        }

        // get isredundantrouter from XML and set the is redundant router for the router
        list = doc.getElementsByTagName("isredundantrouter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsRedundantRouter(node.getTextContent());
        }

        // get linklocalip from XML and set the linkl ocal ip for the router
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the router
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local net mask for the router
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get linklocalnetworkid from XML and set the link local network id for the router
        list = doc.getElementsByTagName("linklocalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetworkId(node.getTextContent());
        }

        // get name from XML and set the name for the router
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the networkdomain for the router
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the router
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get project from XML and set the project name for the router
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set the project id for the router
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the router
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address  for the router
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the router
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get publicnetworkid from XML and set the public network id for the router
        list = doc.getElementsByTagName("publicnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetworkId(node.getTextContent());
        }

        // get redundantstate from XML and set the redundant state for the router
        list = doc.getElementsByTagName("redundantstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRedundantState(node.getTextContent());
        }

        // get role from XML and set role of the domain router
        list = doc.getElementsByTagName("role");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRole(node.getTextContent());
        }

        // get scriptsversion from XML and set the script sversion for the router
        list = doc.getElementsByTagName("scriptsversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScriptsVersion(node.getTextContent());
        }

        // get serviceofferingid from XML and set the service offering id for the router
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set the service offering name for the router
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get state from XML and set the state for the router
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get templateid from XML and set the templateid for the router
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get templateversion from XML and set the templateversion for the router
        list = doc.getElementsByTagName("templateversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateVersion(node.getTextContent());
        }

        // get vpcid from XML and set VPC the router belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get zoneid from XML and set the zoneid for the router
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zonename for the router
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // the list of nics associated with the router
        list = doc.getElementsByTagName("nic");
        if (list.getLength() > 0) {
            List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node networkInterfaceCardNode = list.item(index);
                NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                NodeList networkInterfaceCardProperties = networkInterfaceCardNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkInterfaceCardProperties.getLength(); childIndex++) {
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        return response;
    }

    /**
     * Upgrades domain router to a new service offering
     *
     * @param routerId The ID of the router
     * @param routerServiceOfferingId the service offering ID to apply to the domain router
     * @return
     * @throws Exception
     */
    public ChangeServiceForRouterResponse changeServiceForRouter(String routerId,
            String routerServiceOfferingId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("changeServiceForRouter", null);
        arguments.add(new NameValuePair("id", routerId));
        arguments.add(new NameValuePair("id", routerServiceOfferingId));

        Document responseDocument = server.makeRequest(arguments);

        return getChangeServiceForRouterResponse(responseDocument);
    }

    /**
     * Converts XML document into ChangeServiceForRouterResponse object
     *
     * @param doc
     * @return
     */
    private ChangeServiceForRouterResponse getChangeServiceForRouterResponse(Document doc) {
        ChangeServiceForRouterResponse response = new ChangeServiceForRouterResponse();

        // get id from XML and set the id of the router    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the router
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get created from XML and set the date and time the router was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the dns first for the router
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the dns second for the router
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get domain from XML and set the domain for the router
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domainid for the router
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainid(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the router
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get guestipaddress from XML and set the guest ip address for the router
        list = doc.getElementsByTagName("guestipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestIpAddress(node.getTextContent());
        }

        // get guestmacaddress from XML and set the guest maca ddress for the router
        list = doc.getElementsByTagName("guestmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestMacAddress(node.getTextContent());
        }

        // get guestnetmask from XML and set the guest net mask for the router
        list = doc.getElementsByTagName("guestnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestNetmask(node.getTextContent());
        }

        // get guestnetworkid from XML and set the guest net workid for the router
        list = doc.getElementsByTagName("guestnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestNetworkId(node.getTextContent());
        }

        // get hostid from XML and set the host id for the router
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the router
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get ip6dns1 from XML and set the first IPv6 DNS for the router
        list = doc.getElementsByTagName("ip6dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns1(node.getTextContent());
        }

        // get ip6dns2 from XML and set the second IPv6 DNS for the router
        list = doc.getElementsByTagName("ip6dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns2(node.getTextContent());
        }

        // get isredundantrouter from XML and set the is redundant router for the router
        list = doc.getElementsByTagName("isredundantrouter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsRedundantRouter(node.getTextContent());
        }

        // get linklocalip from XML and set the linkl ocal ip for the router
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the router
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local net mask for the router
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get linklocalnetworkid from XML and set the link local network id for the router
        list = doc.getElementsByTagName("linklocalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetworkId(node.getTextContent());
        }

        // get name from XML and set the name for the router
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the networkdomain for the router
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the router
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get project from XML and set the project name for the router
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set the project id for the router
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the router
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address  for the router
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the router
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get publicnetworkid from XML and set the public network id for the router
        list = doc.getElementsByTagName("publicnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetworkId(node.getTextContent());
        }

        // get redundantstate from XML and set the redundant state for the router
        list = doc.getElementsByTagName("redundantstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRedundantState(node.getTextContent());
        }

        // get role from XML and set role of the domain router
        list = doc.getElementsByTagName("role");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRole(node.getTextContent());
        }

        // get scriptsversion from XML and set the script sversion for the router
        list = doc.getElementsByTagName("scriptsversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScriptsVersion(node.getTextContent());
        }

        // get serviceofferingid from XML and set the service offering id for the router
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set the service offering name for the router
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get state from XML and set the state for the router
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get templateid from XML and set the templateid for the router
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get templateversion from XML and set the templateversion for the router
        list = doc.getElementsByTagName("templateversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateVersion(node.getTextContent());
        }

        // get vpcid from XML and set VPC the router belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get zoneid from XML and set the zoneid for the router
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zonename for the router
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // the list of nics associated with the router
        list = doc.getElementsByTagName("nic");
        if (list.getLength() > 0) {
            List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node networkInterfaceCardNode = list.item(index);
                NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                NodeList networkInterfaceCardProperties = networkInterfaceCardNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkInterfaceCardProperties.getLength(); childIndex++) {
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        return response;
    }

    /**
     * List routers.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListRoutersResponse listRouters(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listRouters", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListRoutersResponse(responseDocument);
    }

    /**
     * Converts XML document into ListRoutersResponse object
     *
     * @param doc
     * @return
     */
    private ListRoutersResponse getListRoutersResponse(Document doc) {
        ListRoutersResponse response = new ListRoutersResponse();

        NodeList list = doc.getElementsByTagName("router");

        List<RouterResponse> routers = new LinkedList<RouterResponse>();
        if (list.getLength() > 0) {
            for (int routerIndex = 0; routerIndex < list.getLength(); routerIndex++) {
                Node routerNode = list.item(routerIndex);

                RouterResponse router = new RouterResponse();
                List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
                NodeList routerProperties = routerNode.getChildNodes();
                for (int childIndex = 0; childIndex < routerProperties.getLength(); childIndex++) {
                    Node property = routerProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        router.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        router.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        router.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("dns1")) {
                        router.setDns1(property.getTextContent());
                    } else if (property.getNodeName().equals("dns2")) {
                        router.setDns2(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        router.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        router.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        router.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("guestipaddress")) {
                        router.setGuestIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("guestmacaddress")) {
                        router.setGuestMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("guestnetmask")) {
                        router.setGuestNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("guestnetworkid")) {
                        router.setGuestNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostid")) {
                        router.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostname")) {
                        router.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6dns1")) {
                        router.setIp6Dns1(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6dns2")) {
                        router.setIp6Dns2(property.getTextContent());
                    } else if (property.getNodeName().equals("isredundantrouter")) {
                        router.setIsRedundantRouter(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalip")) {
                        router.setLinkLocalIp(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalmacaddress")) {
                        router.setLinkLocalMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalnetmask")) {
                        router.setLinkLocalNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalnetworkid")) {
                        router.setLinkLocalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        router.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkdomain")) {
                        router.setNetworkDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        router.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        router.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        router.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("publicip")) {
                        router.setPublicIp(property.getTextContent());
                    } else if (property.getNodeName().equals("publicmacaddress")) {
                        router.setPublicMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("publicnetmask")) {
                        router.setPublicNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("publicnetworkid")) {
                        router.setPublicNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("reduntantstate")) {
                        router.setRedundantState(property.getTextContent());
                    } else if (property.getNodeName().equals("role")) {
                        router.setRole(property.getTextContent());
                    } else if (property.getNodeName().equals("scriptsversion")) {
                        router.setScriptsVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingid")) {
                        router.setServiceOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingname")) {
                        router.setServiceOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        router.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("templatedid")) {
                        router.setTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("templateversion")) {
                        router.setTemplateVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcid")) {
                        router.setVpcId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        router.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        router.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("nic")) {
                        NodeList networkInterfaceCardProperties = property.getChildNodes();
                        if (networkInterfaceCardProperties.getLength() > 0) {
                            NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                            for (int networkInterfaceCardIndex = 0; networkInterfaceCardIndex < networkInterfaceCardProperties.getLength(); networkInterfaceCardIndex++) {
                                Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(networkInterfaceCardIndex);
                                if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                                    networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                                    networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                                    networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                                    networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                                    networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                                    networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                                    networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                                    networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                                    networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                                    networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                                    networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                                    networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                                    networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                                    networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                                    networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                                    networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                                }
                            }
                            networkInterfaceCards.add(networkInterfaceCard);
                        }

                    }
                }
                router.setNetworkInterfaceCards(networkInterfaceCards);
                routers.add(router);
                response.setRouters(routers);
            }
        }
        return response;
    }

    /**
     * Create a virtual router element.
     *
     * @param networkServiceProviderId the network service provider ID of the virtual router element
     * @return
     * @throws Exception
     */
    public CreateVirtualRouterElementResponse createVirtualRouterElement(String networkServiceProviderId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createVirtualRouterElement", null);
        arguments.add(new NameValuePair("nspid", networkServiceProviderId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateVirtualRouterElementResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVirtualRouterElementResponse object
     *
     * @param doc
     * @return
     */
    private CreateVirtualRouterElementResponse getCreateVirtualRouterElementResponse(Document doc) {
        CreateVirtualRouterElementResponse response = new CreateVirtualRouterElementResponse();

        // get id from XML and set the id of the router    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the router
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get domain from XML and set the domain for the router
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domainid for the router
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get enabled from XML and set Enabled/Disabled the service provider for router
        list = doc.getElementsByTagName("enabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEnabled(node.getTextContent());
        }

        // get nspid from XML and set the physical network service provider id of the provider
        list = doc.getElementsByTagName("nspid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNspId(node.getTextContent());
        }

        // get project from XML and the project name of the address
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set the project id of the ipaddress
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for router.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public RouterJobResultResponse routerJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getRouterJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into RouterJobResultResponse object
     *
     * @param doc
     * @return
     */
    private RouterJobResultResponse getRouterJobResultResponse(Document doc) {
        RouterJobResultResponse response = new RouterJobResultResponse();

        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousCmd(node.getTextContent());
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobProgressStatus(node.getTextContent());
        }

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            NodeList node = list.item(0).getChildNodes();
            for (int index = 0; index < node.getLength(); index++) {
                Node nodeProperty = node.item(index);
                if (nodeProperty.getNodeName().equals("router")) {
                    List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("created")) {
                            response.setCreated(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("dns1")) {
                            response.setDns1(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("dns2")) {
                            response.setDns2(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("gateway")) {
                            response.setGateway(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("guestipaddress")) {
                            response.setGuestIpAddress(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("guestnetmask")) {
                            response.setGuestNetmask(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("guestnetworkid")) {
                            response.setGuestNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hostid")) {
                            response.setHostId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hostname")) {
                            response.setHostName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ip6dns1")) {
                            response.setIp6Dns1(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ip6dns2")) {
                            response.setIp6Dns2(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("isredundantrouter")) {
                            response.setIsRedundantRouter(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("linklocalip")) {
                            response.setLinkLocalIp(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("linklocalmacaddress")) {
                            response.setLinkLocalMacAddress(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("linklocalnetmask")) {
                            response.setLinkLocalNetmask(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("linklocalnetworkid")) {
                            response.setLinkLocalNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkdomain")) {
                            response.setNetworkDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("podid")) {
                            response.setPodId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("publicip")) {
                            response.setPublicIp(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("publicmacaddress")) {
                            response.setPublicMacAddress(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("publicnetmask")) {
                            response.setPublicNetmask(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("publicnetworkid")) {
                            response.setPublicNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("redundantststate")) {
                            response.setRedundantState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("role")) {
                            response.setRole(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("scriptsversion")) {
                            response.setScriptsVersion(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("serviceofferingid")) {
                            response.setServiceOfferingId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("serviceofferingname")) {
                            response.setServiceOfferingName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("templateId")) {
                            response.setTemplateId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("templateversion")) {
                            response.setTemplateVersion(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vpcid")) {
                            response.setVpcId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zonename")) {
                            response.setZoneName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("nic")) {
                            NodeList networkInterfaceCardProperties = childNodeProperty.getChildNodes();
                            if (networkInterfaceCardProperties.getLength() > 0) {
                                NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                                for (int networkInterfaceCardIndex = 0; networkInterfaceCardIndex < networkInterfaceCardProperties.getLength(); networkInterfaceCardIndex++) {
                                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(networkInterfaceCardIndex);

                                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                                    }

                                }
                                networkInterfaceCards.add(networkInterfaceCard);
                            }
                            response.setNetworkInterfaceCards(networkInterfaceCards);
                        }
                    }
                } else if (nodeProperty.getNodeName().equals("errorcode")) {
                    response.setErrorCode(nodeProperty.getTextContent());
                } else if (nodeProperty.getNodeName().equals("errortext")) {
                    response.setErrorText(nodeProperty.getTextContent());
                }
            }
        }

        // get jobresultcode from XML and set as the result code for the job
        list = doc.getElementsByTagName("jobresultcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * Configures a virtual router element.
     *
     * @param virtualRouterProviderId the ID of the virtual router provider
     * @param virtualRouterProvideEnabled Enabled/Disabled the service provider
     * @return
     * @throws Exception
     */
    public ConfigureVirtualRouterElementResponse configureVirtualRouterElement(String virtualRouterProviderId,
            String virtualRouterProvideEnabled)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("configureVirtualRouterElement", null);
        arguments.add(new NameValuePair("id", virtualRouterProviderId));
        arguments.add(new NameValuePair("enabled", virtualRouterProvideEnabled));

        Document responseDocument = server.makeRequest(arguments);

        return getConfigureVirtualRouterElementResponse(responseDocument);
    }

    /**
     * Converts XML document into ConfigureVirtualRouterElementResponse object
     *
     * @param doc
     * @return
     */
    private ConfigureVirtualRouterElementResponse getConfigureVirtualRouterElementResponse(Document doc) {
        ConfigureVirtualRouterElementResponse response = new ConfigureVirtualRouterElementResponse();

        // get id from XML and set the id of the router    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the router
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get domain from XML and set the domain for the router
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domainid for the router
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get enabled from XML and set Enabled/Disabled the service provider for router
        list = doc.getElementsByTagName("enabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEnabled(node.getTextContent());
        }

        // get nspid from XML and set the physical network service provider id of the provider
        list = doc.getElementsByTagName("nspid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNspId(node.getTextContent());
        }

        // get project from XML and the project name of the address
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set the project id of the ipaddress
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all available virtual router elements.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListVirtualRouterElementsResponse listVirtualRouterElements(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVirtualRouterElements", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListVirtualRouterElementsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListVirtualRouterElementsResponse object
     *
     * @param doc
     * @return
     */
    private ListVirtualRouterElementsResponse getListVirtualRouterElementsResponse(Document doc) {
        ListVirtualRouterElementsResponse response = new ListVirtualRouterElementsResponse();

        // Lists traffic types of a given physical network
        NodeList list = doc.getElementsByTagName("virtualrouter");
        List<VirtualRouterElementResponse> virtualRouterElements
                = new LinkedList<VirtualRouterElementResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node virtualRouterElementNode = list.item(index);

                VirtualRouterElementResponse virtualRouterElement = new VirtualRouterElementResponse();

                NodeList virtualRouterElementProperties = virtualRouterElementNode.getChildNodes();
                for (int childIndex = 0; childIndex < virtualRouterElementProperties.getLength(); childIndex++) {
                    Node property = virtualRouterElementProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        virtualRouterElement.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        virtualRouterElement.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        virtualRouterElement.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        virtualRouterElement.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("enabled")) {
                        virtualRouterElement.setEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("nspid")) {
                        virtualRouterElement.setNspId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        virtualRouterElement.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        virtualRouterElement.setProjectId(property.getTextContent());
                    }
                }
                virtualRouterElements.add(virtualRouterElement);
            }
        }

        response.setVirtualRouterElements(virtualRouterElements);
        return response;
    }
}
