package com.assistanz.cloud.cloudstack.internallb;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
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
public class CSInternalLBService {

    private CloudStackServer server;

    public CSInternalLBService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Configures an Internal Load Balancer element
     *
     * @param internalLbProviderId the ID of the internal lb provider
     * @param enabled Enables/Disables the Internal Load Balancer element
     * @return
     * @throws Exception
     */
    public ConfigureInternalLoadBalancerElementResponse configureInternalLoadBalancerElement(String internalLbProviderId, String enabled)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("configureInternalLoadBalancerElement", null);
        arguments.add(new NameValuePair("id", internalLbProviderId));
        arguments.add(new NameValuePair("enabled", enabled));

        Document responseDocument = server.makeRequest(arguments);

        return getConfigureInternalLoadBalancerElementResponse(responseDocument);
    }

    /**
     * Converts XML document into ConfigureInternalLoadBalancerElementResponse object
     *
     * @param doc
     * @return
     */
    private ConfigureInternalLoadBalancerElementResponse getConfigureInternalLoadBalancerElementResponse(Document doc) {
        ConfigureInternalLoadBalancerElementResponse response = new ConfigureInternalLoadBalancerElementResponse();

        // get id from XML and set as the id of the internal load balancer element
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get enabled from XML and set Enabled/Disabled of the element      
        list = doc.getElementsByTagName("enabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEnabled(node.getTextContent());
        }

        // get nspid from XML and set the physical network service provider id of the element
        list = doc.getElementsByTagName("nspid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNspId(node.getTextContent());
        }

        return response;
    }

    /**
     * Create an Internal Load Balancer element
     *
     * @param networkServiceProviderId the network service provider ID of the internal load balancer element
     * @return
     * @throws Exception
     */
    public CreateInternalLoadBalancerElementResponse createInternalLoadBalancerElement(String networkServiceProviderId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createInternalLoadBalancerElement", null);
        arguments.add(new NameValuePair("nspid", networkServiceProviderId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateInternalLoadBalancerElementResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateInternalLoadBalancerElementResponse object
     *
     * @param doc
     * @return
     */
    private CreateInternalLoadBalancerElementResponse getCreateInternalLoadBalancerElementResponse(Document doc) {
        CreateInternalLoadBalancerElementResponse response = new CreateInternalLoadBalancerElementResponse();

        // get id from XML and set as the id of the internal load balancer element
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get enabled from XML and set Enabled/Disabled of the element      
        list = doc.getElementsByTagName("enabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEnabled(node.getTextContent());
        }

        // get nspid from XML and set the physical network service provider id of the element
        list = doc.getElementsByTagName("nspid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNspId(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all available Internal Load Balancer elements.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListInternalLoadBalancerElementsResponse listInternalLoadBalancerElements(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listInternalLoadBalancerElements", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListInternalLoadBalancerElementsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListInternalLoadBalancerElementsResponse object
     *
     * @param doc
     * @return
     */
    private ListInternalLoadBalancerElementsResponse getListInternalLoadBalancerElementsResponse(Document doc) {
        ListInternalLoadBalancerElementsResponse response = new ListInternalLoadBalancerElementsResponse();

        NodeList list = doc.getElementsByTagName("internaloadbalancerelement");

        List<InternalLoadBalancerElementResponse> internalLoadBalancerElements
                = new LinkedList<InternalLoadBalancerElementResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node internalLoadBalancerElementNode = list.item(Index);
            InternalLoadBalancerElementResponse internalLoadBalancerElement
                    = new InternalLoadBalancerElementResponse();

            NodeList internalLoadBalancerElementProperties = internalLoadBalancerElementNode.getChildNodes();
            for (int childIndex = 0; childIndex < internalLoadBalancerElementProperties.getLength(); childIndex++) {
                Node property = internalLoadBalancerElementProperties.item(childIndex);

                if (property.getNodeName().equals("id")) {
                    internalLoadBalancerElement.setId(property.getTextContent());
                } else if (property.getNodeName().equals("enabled")) {
                    internalLoadBalancerElement.setEnabled(property.getTextContent());
                } else if (property.getNodeName().equals("nspid")) {
                    internalLoadBalancerElement.setNspId(property.getTextContent());
                }
            }
            internalLoadBalancerElements.add(internalLoadBalancerElement);
        }

        response.setInternalLoadBalancerElements(internalLoadBalancerElements);
        return response;
    }

    /**
     * Stops an Internal LB vm.
     *
     * @param internalLbVmId the ID of the internal lb vm
     * @param optional
     * @return
     * @throws Exception
     */
    public StopInternalLoadBalancerVMResponse stopInternalLoadBalancerVM(String internalLbVmId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("stopInternalLoadBalancerVM", optional);
        arguments.add(new NameValuePair("id", internalLbVmId));

        Document responseDocument = server.makeRequest(arguments);

        return getStopInternalLoadBalancerVMResponse(responseDocument);
    }

    /**
     * Converts XML document into StopInternalLoadBalancerVMResponse object
     *
     * @param doc
     * @return
     */
    private StopInternalLoadBalancerVMResponse getStopInternalLoadBalancerVMResponse(Document doc) {
        StopInternalLoadBalancerVMResponse response = new StopInternalLoadBalancerVMResponse();

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
            response.setDomainId(node.getTextContent());
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
     * Starts an existing internal lb vm.
     *
     * @param internalLbVmId the ID of the internal lb vm
     * @return
     * @throws Exception
     */
    public StartInternalLoadBalancerVMResponse startInternalLoadBalancerVM(String internalLbVmId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("startInternalLoadBalancerVM", null);
        arguments.add(new NameValuePair("id", internalLbVmId));

        Document responseDocument = server.makeRequest(arguments);

        return getStartInternalLoadBalancerVMResponse(responseDocument);
    }

    /**
     * Converts XML document into StartInternalLoadBalancerVMResponse object
     *
     * @param doc
     * @return
     */
    private StartInternalLoadBalancerVMResponse getStartInternalLoadBalancerVMResponse(Document doc) {
        StartInternalLoadBalancerVMResponse response = new StartInternalLoadBalancerVMResponse();

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
            response.setDomainId(node.getTextContent());
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
     * Retrieves the current status of asynchronous job for internalloadbalancerelement.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public InternalLoadBalancerElementJobResultResponse internalLoadBalancerElementJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getInternalLoadBalancerElementJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into InternalLoadBalancerElementJobResultResponse object
     *
     * @param doc
     * @return
     */
    private InternalLoadBalancerElementJobResultResponse getInternalLoadBalancerElementJobResultResponse(Document doc) {
        InternalLoadBalancerElementJobResultResponse response = new InternalLoadBalancerElementJobResultResponse();

        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCmd(node.getTextContent());
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobProgressStatus(node.getTextContent());
        }

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            NodeList node = list.item(0).getChildNodes();
            for (int index = 0; index < node.getLength(); index++) {
                Node nodeProperty = node.item(index);
                if (nodeProperty.getNodeName().equals("internalloadbalancerelement")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("enabled")) {
                            response.setEnabled(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("nspid")) {
                            response.setNspId(childNodeProperty.getTextContent());
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
            response.setAsychronousJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for internalloadbalancerVM.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public InternalLoadBalancerVmJobResultResponse internalLoadBalancerVM(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getInternalLoadBalancerVmJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into InternalLoadBalancerVMJobResultResponse object
     *
     * @param doc
     * @return
     */
    private InternalLoadBalancerVmJobResultResponse getInternalLoadBalancerVmJobResultResponse(Document doc) {
        InternalLoadBalancerVmJobResultResponse response = new InternalLoadBalancerVmJobResultResponse();

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
     * List internal LB VMs.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListInternalLoadBalancerVMsResponse listInternalLoadBalancerVMs(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listInternalLoadBalancerVMs", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListInternalLoadBalancerVMsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListInternalLoadBalancerVMsResponse object
     *
     * @param doc
     * @return
     */
    private ListInternalLoadBalancerVMsResponse getListInternalLoadBalancerVMsResponse(Document doc) {
        ListInternalLoadBalancerVMsResponse response = new ListInternalLoadBalancerVMsResponse();

        NodeList list = doc.getElementsByTagName("internalloadbalancervm");

        List<InternalLoadBalancerVMResponse> internalLoadBalancerVms = new LinkedList<InternalLoadBalancerVMResponse>();
        if (list.getLength() > 0) {
            for (int internalLoadBalancerVmIndex = 0; internalLoadBalancerVmIndex < list.getLength();
                    internalLoadBalancerVmIndex++) {
                Node internalLoadBalancerVmNode = list.item(internalLoadBalancerVmIndex);

                InternalLoadBalancerVMResponse internalLoadBalancerVm = new InternalLoadBalancerVMResponse();
                List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
                NodeList internalLoadBalancerVmProperties = internalLoadBalancerVmNode.getChildNodes();
                for (int childIndex = 0; childIndex < internalLoadBalancerVmProperties.getLength(); childIndex++) {
                    Node property = internalLoadBalancerVmProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        internalLoadBalancerVm.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        internalLoadBalancerVm.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        internalLoadBalancerVm.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("dns1")) {
                        internalLoadBalancerVm.setDns1(property.getTextContent());
                    } else if (property.getNodeName().equals("dns2")) {
                        internalLoadBalancerVm.setDns2(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        internalLoadBalancerVm.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        internalLoadBalancerVm.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        internalLoadBalancerVm.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("guestipaddress")) {
                        internalLoadBalancerVm.setGuestIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("guestmacaddress")) {
                        internalLoadBalancerVm.setGuestMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("guestnetmask")) {
                        internalLoadBalancerVm.setGuestNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("guestnetworkid")) {
                        internalLoadBalancerVm.setGuestNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostid")) {
                        internalLoadBalancerVm.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostname")) {
                        internalLoadBalancerVm.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6dns1")) {
                        internalLoadBalancerVm.setIp6Dns1(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6dns2")) {
                        internalLoadBalancerVm.setIp6Dns2(property.getTextContent());
                    } else if (property.getNodeName().equals("isredundantrouter")) {
                        internalLoadBalancerVm.setIsRedundantRouter(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalip")) {
                        internalLoadBalancerVm.setLinkLocalIp(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalmacaddress")) {
                        internalLoadBalancerVm.setLinkLocalMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalnetmask")) {
                        internalLoadBalancerVm.setLinkLocalNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalnetworkid")) {
                        internalLoadBalancerVm.setLinkLocalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        internalLoadBalancerVm.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkdomain")) {
                        internalLoadBalancerVm.setNetworkDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        internalLoadBalancerVm.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        internalLoadBalancerVm.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        internalLoadBalancerVm.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("publicip")) {
                        internalLoadBalancerVm.setPublicIp(property.getTextContent());
                    } else if (property.getNodeName().equals("publicmacaddress")) {
                        internalLoadBalancerVm.setPublicMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("publicnetmask")) {
                        internalLoadBalancerVm.setPublicNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("publicnetworkid")) {
                        internalLoadBalancerVm.setPublicNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("reduntantstate")) {
                        internalLoadBalancerVm.setRedundantState(property.getTextContent());
                    } else if (property.getNodeName().equals("role")) {
                        internalLoadBalancerVm.setRole(property.getTextContent());
                    } else if (property.getNodeName().equals("scriptversion")) {
                        internalLoadBalancerVm.setScriptsVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingid")) {
                        internalLoadBalancerVm.setServiceOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingname")) {
                        internalLoadBalancerVm.setServiceOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        internalLoadBalancerVm.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("templatedid")) {
                        internalLoadBalancerVm.setTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("templateversion")) {
                        internalLoadBalancerVm.setTemplateVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcid")) {
                        internalLoadBalancerVm.setVpcId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        internalLoadBalancerVm.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        internalLoadBalancerVm.setZoneName(property.getTextContent());
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
                internalLoadBalancerVm.setNetworkInterfaceCards(networkInterfaceCards);
                internalLoadBalancerVms.add(internalLoadBalancerVm);
                response.setInternalLoadBalancerVMs(internalLoadBalancerVms);
            }
        }
        return response;
    }

}
