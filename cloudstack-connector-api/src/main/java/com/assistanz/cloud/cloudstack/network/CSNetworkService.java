package com.assistanz.cloud.cloudstack.network;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.CloudStackServer;
//import com.assistanz.cloud.cloudstack.NetworkResponse;
import com.assistanz.cloud.cloudstack.ProviderResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;
import com.assistanz.cloud.cloudstack.TagsResponse;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham, Santhosh
 *
 */
public class CSNetworkService {

    private CloudStackServer server;

    public CSNetworkService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Dedicates a Public IP range to an account
     *
     * @param vlanIpRangeId the id of the VLAN IP range
     * @param vlanAccountName account who will own the VLAN
     * @param domainId domain ID of the account owning a VLAN
     * @param optional
     * @return
     * @throws Exception
     */
    public DedicatePublicIpRangeResponse dedicatePublicIpRange(String vlanIpRangeId,
            String vlanAccountName, String domainId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("dedicatePublicIpRange", optional);
        arguments.add(new NameValuePair("id", vlanIpRangeId));
        arguments.add(new NameValuePair("account", vlanAccountName));
        arguments.add(new NameValuePair("domainid", domainId));

        Document responseDocument = server.makeRequest(arguments);

        return getDedicatePublicIpRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into DedicatePublicIpRangeResponse object
     *
     * @param doc
     * @return
     */
    private DedicatePublicIpRangeResponse getDedicatePublicIpRangeResponse(Document doc) {
        DedicatePublicIpRangeResponse response = new DedicatePublicIpRangeResponse();

        // get the ID of the VLAN IP range
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get the account of the VLAN IP range
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get the description of the VLAN IP range
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get the domain name of the VLAN IP range
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get the domain ID of the VLAN IP range
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the end ip of the VLAN IP range
        list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndIp(node.getTextContent());
        }

        // get the end ipv6 of the VLAN IP range
        list = doc.getElementsByTagName("endipv6");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndIpv6(node.getTextContent());
        }

        // get the virtual network for the VLAN IP range
        list = doc.getElementsByTagName("forvirtualnetwork");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setForVirtualNetwork(node.getTextContent());
        }

        // get the gateway of the VLAN IP range
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get the cidr of IPv6 network
        list = doc.getElementsByTagName("ip6cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Cidr(node.getTextContent());
        }

        // get the gateway of IPv6 network
        list = doc.getElementsByTagName("ip6gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Gateway(node.getTextContent());
        }

        // get the netmask of the VLAN IP range
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetmask(node.getTextContent());
        }

        // get the network id of vlan range
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get the physical network this belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get the Pod ID for the VLAN IP range
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get the Pod name for the VLAN IP range
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get the project name of the vlan range
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the vlan range
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get the start ip of the VLAN IP range
        list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartIp(node.getTextContent());
        }

        // get the start ipv6 of the VLAN IP range
        list = doc.getElementsByTagName("startipv6");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartIpv6(node.getTextContent());
        }

        // get the ID or VID of the VLAN
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get the Zone ID of the VLAN IP range
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;

    }

    /**
     * Releases a Public IP range back to the system pool.
     *
     * @param publicIpRangeId the id of the Public IP range
     * @return
     * @throws Exception
     */
    public ReleasePublicIpRangeResponse releasePublicIpRange(String publicIpRangeId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("releasePublicIpRange", null);
        arguments.add(new NameValuePair("id", publicIpRangeId));

        Document responseDocument = server.makeRequest(arguments);

        return getReleasePublicIpRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into ReleasePublicIpRange object
     *
     * @param doc
     * @return
     */
    private ReleasePublicIpRangeResponse getReleasePublicIpRangeResponse(Document doc) {
        ReleasePublicIpRangeResponse response = new ReleasePublicIpRangeResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Network Offering
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }

        // get success from XML and any text associated with the success or failure on deleting Delete Network Offering
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Creates a network offering
     *
     * @param networkOfferingDisplayText the display text of the network offering
     * @param networkOfferingGuestIpType guest type of the network offering: Shared or Isolated
     * @param networkOfferingName the name of the network offering
     * @param networkOfferingSupportedServices services supported by the network offering
     * @param networkOfferingTrafficType the traffic type for the network offering. Supported type in current release is
     * GUEST only
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateNetworkOfferingResponse createNetworkOffering(String networkOfferingDisplayText,
            String networkOfferingGuestIpType, String networkOfferingName,
            String networkOfferingSupportedServices, String networkOfferingTrafficType, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createNetworkOffering", optional);
        arguments.add(new NameValuePair("displaytext", networkOfferingDisplayText));
        arguments.add(new NameValuePair("guestiptype", networkOfferingGuestIpType));
        arguments.add(new NameValuePair("name", networkOfferingName));
        arguments.add(new NameValuePair("supportedservices", networkOfferingSupportedServices));
        arguments.add(new NameValuePair("traffictype", networkOfferingTrafficType));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateNetworkOfferingResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateNetworkOfferingResponse object
     *
     * @param doc
     * @return
     */
    private CreateNetworkOfferingResponse getCreateNetworkOfferingResponse(Document doc) {
        CreateNetworkOfferingResponse response = new CreateNetworkOfferingResponse();

        // get Id from XML and set the id of the network offering
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingId(node.getTextContent());
        }

        // get availability from XML and set availability of the network offering
        list = doc.getElementsByTagName("availability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingAvailability(node.getTextContent());
        }

        // get conservemode from XML and set true if network offering is ip conserve mode enabled
        list = doc.getElementsByTagName("conservemode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingConserveMode(node.getTextContent());
        }

        // get created from XML and set the date this network offering was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingCreated(node.getTextContent());
        }

        // get displaytext from XML and set alternate display text of the network offering.
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingDisplayText(node.getTextContent());
        }

        // get guestiptype from XML and set guest type of the network offering, can be Shared or Isolated
        list = doc.getElementsByTagName("guestiptype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingGuestIpType(node.getTextContent());
        }

        // get isdefault from XML and set true if network offering is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingIsDefault(node.getTextContent());
        }

        // get name from XML and set the name of the network offering
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingName(node.getTextContent());
        }

        // get networkrate from XML and set data transfer rate in megabits per second allowed.
        list = doc.getElementsByTagName("networkrate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRate(node.getTextContent());
        }

        // get serviceofferingid from XML and set the ID of the service offering used by virtual router provider
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get specifyipranges from XML and set true if network offering supports specifying ip ranges, false otherwise
        list = doc.getElementsByTagName("specifyipranges");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingSpecifyIpRanges(node.getTextContent());
        }

        // get specifyvlan from XML and set true if network offering supports vlans, false otherwise
        list = doc.getElementsByTagName("specifyvlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingSpecifyVlan(node.getTextContent());
        }

        // get state from XML and set state of the network offering. Can be Disabled/Enabled/Inactive
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingState(node.getTextContent());
        }

        // get tags from XML and set the tags for the network offering
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingTags(node.getTextContent());
        }

        // get traffictype from XML and set the traffic type for the network offering, supported types are Public, Management, Control, Guest, Vlan or Storage.
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingTrafficType(node.getTextContent());
        }

        //the list of supported services
        list = doc.getElementsByTagName("service");
        if (list.getLength() > 0) {
            List<ServiceResponse> services = new LinkedList<ServiceResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node serviceNode = list.item(index);
                ServiceResponse service = new ServiceResponse();

                NodeList serviceProperties = serviceNode.getChildNodes();
                List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                for (int childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        service.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                        service.setServiceProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("capability")) {
                        NodeList capabilityProperties = property.getChildNodes();
                        if (capabilityProperties.getLength() > 0) {
                            CapabilityResponse capability = new CapabilityResponse();

                            for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
                                Node capabilityProperty = capabilityProperties.item(capabilityIndex);;

                                if (capabilityProperty.getNodeName().equals("canchooseservicecapability")) {
                                    capability.setCanChooseServiceCapability(property.getTextContent());
                                } else if (capabilityProperty.getNodeName().equals("name")) {
                                    capability.setName(property.getTextContent());
                                } else if (capabilityProperty.getNodeName().equals("value")) {
                                    capability.setValue(property.getTextContent());
                                }

                            }

                            capabilities.add(capability);
                            response.setCapabilities(capabilities);
                        }

                    }
                }

                services.add(service);
                response.setServices(services);
            }
        }

        return response;
    }

    /**
     * Updates a network offering.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateNetworkOfferingResponse updateNetworkOffering(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateNetworkOffering", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateNetworkOfferingResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateNetworkOfferingResponse object
     *
     * @param doc
     * @return
     */
    private UpdateNetworkOfferingResponse getUpdateNetworkOfferingResponse(Document doc) {
        UpdateNetworkOfferingResponse response = new UpdateNetworkOfferingResponse();

        // get Id from XML and set the id of the network offering
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingId(node.getTextContent());
        }

        // get availability from XML and set availability of the network offering
        list = doc.getElementsByTagName("availability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingAvailability(node.getTextContent());
        }

        // get conservemode from XML and set true if network offering is ip conserve mode enabled
        list = doc.getElementsByTagName("conservemode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingConserveMode(node.getTextContent());
        }

        // get created from XML and set the date this network offering was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingCreated(node.getTextContent());
        }

        // get displaytext from XML and set alternate display text of the network offering.
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingDisplayText(node.getTextContent());
        }

        // get guestiptype from XML and set guest type of the network offering, can be Shared or Isolated
        list = doc.getElementsByTagName("guestiptype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingGuestIpType(node.getTextContent());
        }

        // get isdefault from XML and set true if network offering is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingIsDefault(node.getTextContent());
        }

        // get name from XML and set the name of the network offering
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingName(node.getTextContent());
        }

        // get networkrate from XML and set data transfer rate in megabits per second allowed.
        list = doc.getElementsByTagName("networkrate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRate(node.getTextContent());
        }

        // get serviceofferingid from XML and set the ID of the service offering used by virtual router provider
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get specifyipranges from XML and set true if network offering supports specifying ip ranges, false otherwise
        list = doc.getElementsByTagName("specifyipranges");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingSpecifyIpRanges(node.getTextContent());
        }

        // get specifyvlan from XML and set true if network offering supports vlans, false otherwise
        list = doc.getElementsByTagName("specifyvlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingSpecifyVlan(node.getTextContent());
        }

        // get state from XML and set state of the network offering. Can be Disabled/Enabled/Inactive
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingState(node.getTextContent());
        }
        // get tags from XML and set the tags for the network offering
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingTags(node.getTextContent());
        }

        // get traffictype from XML and set the traffic type for the network offering, supported types are Public, Management, Control, Guest, Vlan or Storage.
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingTrafficType(node.getTextContent());
        }

        //the list of supported services
        list = doc.getElementsByTagName("service");
        if (list.getLength() > 0) {
            List<ServiceResponse> services = new LinkedList<ServiceResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node serviceNode = list.item(index);
                ServiceResponse service = new ServiceResponse();

                NodeList serviceProperties = serviceNode.getChildNodes();
                List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                for (int childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        service.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                        service.setServiceProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("capability")) {
                        NodeList capabilityProperties = property.getChildNodes();
                        if (capabilityProperties.getLength() > 0) {
                            CapabilityResponse capability = new CapabilityResponse();

                            for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
                                Node capabilityProperty = capabilityProperties.item(capabilityIndex);;

                                if (capabilityProperty.getNodeName().equals("canchooseservicecapability")) {
                                    capability.setCanChooseServiceCapability(property.getTextContent());
                                } else if (capabilityProperty.getNodeName().equals("name")) {
                                    capability.setName(property.getTextContent());
                                } else if (capabilityProperty.getNodeName().equals("value")) {
                                    capability.setValue(property.getTextContent());
                                }

                            }

                            capabilities.add(capability);
                            response.setCapabilities(capabilities);
                        }

                    }
                }

                services.add(service);
                response.setServices(services);
            }
        }

        return response;
    }

    /**
     * Deletes a network offering.
     *
     * @param networkOfferingId the ID of the network offering
     * @return
     * @throws Exception
     */
    public DeleteNetworkOfferingResponse deleteNetworkOffering(String networkOfferingId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteNetworkOffering", null);
        arguments.add(new NameValuePair("id", networkOfferingId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteNetworkOfferingResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteNetworkOfferingResponse object
     *
     * @param doc
     * @return
     */
    private DeleteNetworkOfferingResponse getDeleteNetworkOfferingResponse(Document doc) {
        DeleteNetworkOfferingResponse response = new DeleteNetworkOfferingResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Network Offering
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Network Offering
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all available network offerings.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNetworkOfferingsResponse listNetworkOfferings(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNetworkOfferings", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListNetworkOfferingsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListNetworkOfferingsResponse object
     *
     * @param doc
     * @return
     */
    private ListNetworkOfferingsResponse getListNetworkOfferingsResponse(Document doc) {
        ListNetworkOfferingsResponse response = new ListNetworkOfferingsResponse();

        NodeList list = doc.getElementsByTagName("networkoffering");

        List<NetworkOfferingResponse> networkOfferings = new LinkedList<NetworkOfferingResponse>();
        if (list.getLength() > 0) {
            for (int networkOfferingIndex = 0; networkOfferingIndex < list.getLength(); networkOfferingIndex++) {
                Node networkOfferingNode = list.item(networkOfferingIndex);

                  // if (networkOfferingNode == null) {
                  //    continue;
                  // }  
                NetworkOfferingResponse networkOffering = new NetworkOfferingResponse();
                List<ServiceResponse> services = new LinkedList<ServiceResponse>();
                NodeList networkOfferingProperties = networkOfferingNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkOfferingProperties.getLength(); childIndex++) {
                    Node property = networkOfferingProperties.item(childIndex);
                    // if (property == null || property.getNodeName() == null) {
                    //      continue;
                    // }

                    if (property.getNodeName().equals("id")) {
                        networkOffering.setNetworkOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("availability")) {
                        networkOffering.setNetworkOfferingAvailability(property.getTextContent());
                    } else if (property.getNodeName().equals("conservemode")) {
                        networkOffering.setNetworkOfferingConserveMode(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        networkOffering.setNetworkOfferingCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                        networkOffering.setNetworkOfferingDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("guestiptype")) {
                        networkOffering.setNetworkOfferingGuestIpType(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        networkOffering.setNetworkOfferingIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        networkOffering.setNetworkOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkrate")) {
                        networkOffering.setNetworkRate(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingid")) {
                        networkOffering.setServiceOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("specifyipranges")) {
                        networkOffering.setNetworkOfferingSpecifyIpRanges(property.getTextContent());
                    } else if (property.getNodeName().equals("specifyvlan")) {
                        networkOffering.setNetworkOfferingSpecifyVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        networkOffering.setNetworkOfferingState(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        networkOffering.setNetworkOfferingTags(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                        networkOffering.setNetworkOfferingTrafficType(property.getTextContent());
                    } else if (property.getNodeName().equals("service")) {
                        NodeList serviceProperties = property.getChildNodes();
                        if (serviceProperties.getLength() > 0) {
                            ServiceResponse service = new ServiceResponse();
                            List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                            for (int serviceIndex = 0; serviceIndex < serviceProperties.getLength(); serviceIndex++) {
                                Node serviceProperty = serviceProperties.item(serviceIndex);
                                if (serviceProperty.getNodeName().equals("name")) {
                                    service.setName(serviceProperty.getTextContent());
                                } else if (serviceProperty.getNodeName().equals("provider")) {
                                    Node providerProperties = serviceProperty.getFirstChild();
                                    service.setServiceProvider(providerProperties.getTextContent());
                                } else if (serviceProperty.getNodeName().equals("capability")) {
                                    NodeList capabilityProperties = serviceProperty.getChildNodes();
                                    if (capabilityProperties.getLength() > 0) {
                                        CapabilityResponse capability = new CapabilityResponse();
                                        for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
                                            Node capabilityProperty = capabilityProperties.item(capabilityIndex);
                                            if (capabilityProperty.getNodeName().equals("name")) {
                                                capability.setName(capabilityProperty.getTextContent());
                                            } else if (capabilityProperty.getNodeName().equals("value")) {
                                                capability.setValue(capabilityProperty.getTextContent());
                                            } else if (capabilityProperty.getNodeName().equals("canchooseservicecapability")) {
                                                capability.setCanChooseServiceCapability(capabilityProperty.getTextContent());
                                            }
                                        }
                                        capabilities.add(capability);
                                    }
                                }
                            }
                            service.setCapabilities(capabilities);
                            services.add(service);
                        }

                    }
                }
                networkOffering.setServices(services);
                networkOfferings.add(networkOffering);
            }
        }
        response.setNetworkOfferings(networkOfferings);
        return response;
    }

    /**
     * Creates a network
     *
     * @param networkOfferingDisplayText the display text of the network
     * @param networkOfferingName the name of the network
     * @param networkOfferingId the network offering id
     * @param networkOfferingZoneId the Zone ID for the network
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateNetworkResponse createNetwork(String networkOfferingDisplayText,
            String networkOfferingName, String networkOfferingId, String networkOfferingZoneId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createNetwork", optional);
        arguments.add(new NameValuePair("displaytext", networkOfferingDisplayText));
        arguments.add(new NameValuePair("name", networkOfferingName));
        arguments.add(new NameValuePair("networkofferingid", networkOfferingId));
        arguments.add(new NameValuePair("zoneid", networkOfferingZoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateNetworkResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateNetworkResponse object
     *
     * @param doc
     * @return
     */
    private CreateNetworkResponse getCreateNetworkResponse(Document doc) {
        CreateNetworkResponse response = new CreateNetworkResponse();

        // get Id from XML and set the id of the network 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set	the owner of the network
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get ACL Id associated with the VPC network
        list = doc.getElementsByTagName("aclid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclId(node.getTextContent());
        }

        // get acltype from XML and set	acl type - access type to the network
        list = doc.getElementsByTagName("acltype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclType(node.getTextContent());
        }    

        // get broadcastdomaintype from XML and set Broadcast domain type of the network
        list = doc.getElementsByTagName("broadcastdomaintype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBroadcastDomainType(node.getTextContent());
        }

        // get broadcasturi from XML and set broadcast uri of the network
        list = doc.getElementsByTagName("broadcasturi");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBroadcastUri(node.getTextContent());
        }

        // get list networks available for vm deployment
        list = doc.getElementsByTagName("canusefordeploy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCanUseForDeploy(node.getTextContent());
        }

        // get cidr from XML and set cidr of the network
        list = doc.getElementsByTagName("cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidr(node.getTextContent());
        }

        // get an optional field, whether to the display the network to the end user or not
        list = doc.getElementsByTagName("displaynetwork");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayNetwork(node.getTextContent());
        }

        // get displaytext from XML and set displaytext of the network
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get dns1 from XML and set dns1 of the network
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set dns2 of the network
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get domain from XML and set the domain name of the network owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domain id of the network owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get gateway from XML and set the network's gateway
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get the cidr of IPv6 network
        list = doc.getElementsByTagName("ip6cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Cidr(node.getTextContent());
        }

        // get the gateway of IPv6 network
        list = doc.getElementsByTagName("ip6gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Gateway(node.getTextContent());
        }

        // get isdefault from XML and set true if network is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // get list networks that are persistent
        list = doc.getElementsByTagName("ispersistent");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPersistent(node.getTextContent());
        }

        // get issystem from XML and set true if network is system, false otherwise
        list = doc.getElementsByTagName("issystem");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsSystem(node.getTextContent());
        }

        // get name from XML and set the name of the network
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get netmask from XML and set the netmask of the network
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetmask(node.getTextContent());
        }

        // the network CIDR of the guest network configured with IP reservation. 
        list = doc.getElementsByTagName("networkcidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkCidr(node.getTextContent());
        }

        // get networkdomain from XML and set the networkdomain of the network
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get networkofferingavailability from XML and set availability of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingavailability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingAvailability(node.getTextContent());
        }

        // get true if network offering is ip conserve mode enabled
        list = doc.getElementsByTagName("networkofferingconservemode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingConserveMode(node.getTextContent());
        }

        // get networkofferingdisplaytext from XML and set display text of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingDisplayText(node.getTextContent());
        }

        // get networkofferingid from XML and set network offering id the network is created from
        list = doc.getElementsByTagName("networkofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingId(node.getTextContent());
        }

        // get networkofferingname from XML and set name of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingName(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network id
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get project from XML and set the project name of the address
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set the project id of the address
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get related from XML and set related to what other network configuration
        list = doc.getElementsByTagName("related");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRelated(node.getTextContent());
        }

        // get the network's IP range not to be used by CloudStack guest VMs and can be used for non CloudStack purposes
        list = doc.getElementsByTagName("reservediprange");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReservedIpRange(node.getTextContent());
        }

        // get restartrequired from XML and set true network requires restart
        list = doc.getElementsByTagName("restartrequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRestartRequired(node.getTextContent());
        }

        // get specifyipranges from XML and set true if network supports specifying ip ranges, false otherwise
        list = doc.getElementsByTagName("specifyipranges");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSpecifyIpRanges(node.getTextContent());
        }

        // get state from XML and set state of the network
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get subdomainaccess from XML and set subdomainaccess of the network
        list = doc.getElementsByTagName("subdomainaccess");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSubdomainAccess(node.getTextContent());
        }

        // get traffictype from XML and set traffictype of the network
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTrafficType(node.getTextContent());
        }

        // get type from XML and set type of the network
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get vlan from XML and set vlan of the network
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get VPC the network belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get zoneid from XML and set zoneid of the network
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set zonename of the network
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        //the list of supported services
        list = doc.getElementsByTagName("service");
        if (list.getLength() > 0) {
            List<ServiceResponse> services = new LinkedList<ServiceResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node serviceNode = list.item(index);
                ServiceResponse service = new ServiceResponse();
                List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                List<ProviderResponse> providers = new LinkedList<ProviderResponse>();
                NodeList serviceProperties = serviceNode.getChildNodes();
                for (int childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        service.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("capability")) {
                        NodeList capabilityProperties = property.getChildNodes();
                        if (capabilityProperties.getLength() > 0) {
                            CapabilityResponse capability = new CapabilityResponse();

                            for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
                                Node capabilityProperty = capabilityProperties.item(capabilityIndex);

                                if (capabilityProperty.getNodeName().equals("canchooseservicecapability")) {
                                    capability.setCanChooseServiceCapability(property.getTextContent());
                                } else if (capabilityProperty.getNodeName().equals("name")) {
                                    capability.setName(property.getTextContent());
                                } else if (capabilityProperty.getNodeName().equals("value")) {
                                    capability.setValue(property.getTextContent());
                                }

                            }

                            capabilities.add(capability);
                            response.setCapabilities(capabilities);
                        }

                    } else if (property.getNodeName().equals("provider")) {
                        NodeList providerProperties = property.getChildNodes();
                        if (providerProperties.getLength() > 0) {
                            ProviderResponse provider = new ProviderResponse();
                            for (int providerIndex = 0; providerIndex < list.getLength(); providerIndex++) {
                                Node providerProperty = providerProperties.item(providerIndex);

                                if (providerProperty.getNodeName().equals("id")) {
                                    provider.setId(property.getTextContent());
                                } else if (providerProperty.getNodeName().equals("canenableindividualservice")) {
                                    provider.setCanEnableIndividualService(property.getTextContent());
                                } else if (providerProperty.getNodeName().equals("destinationphysicalnetworkid")) {
                                    provider.setDestinationPhysicalNetworkId(property.getTextContent());
                                } else if (providerProperty.getNodeName().equals("name")) {
                                    provider.setName(property.getTextContent());
                                } else if (providerProperty.getNodeName().equals("physicalnetworkid")) {
                                    provider.setPhysicalNetworkId(property.getTextContent());
                                } else if (providerProperty.getNodeName().equals("servicelist")) {
                                    provider.setServiceList(property.getTextContent());
                                } else if (providerProperty.getNodeName().equals("state")) {
                                    provider.setState(property.getTextContent());
                                }

                            }

                            providers.add(provider);
                            response.setProviders(providers);
                        }

                    }
                }
                services.add(service);
                response.setServices(services);
            }
        }

        // the list of resource tags associated with network
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node tagsNode = list.item(index);
                TagsResponse tags = new TagsResponse();
                NodeList tagsProperties = tagsNode.getChildNodes();
                for (int childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                    Node tagsProperty = tagsProperties.item(childIndex);
                    if (tagsProperty.getNodeName().equals("account")) {
                        tags.setAccount(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("customer")) {
                        tags.setCustomer(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domain")) {
                        tags.setDomain(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                        tags.setDomainId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("key")) {
                        tags.setKey(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("project")) {
                        tags.setProject(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                        tags.setProjectId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                        tags.setResourceId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                        tags.setResourceType(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("value")) {
                        tags.setValue(tagsProperty.getTextContent());
                    }

                }
                tagss.add(tags);
                response.setTagss(tagss);
            }
        }
        return response;
    }

    /**
     * Deletes a network
     *
     * @param networkId the ID of the network
     * @return
     * @throws Exception
     */
    public DeleteNetworkResponse deleteNetwork(String networkId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteNetwork", null);
        arguments.add(new NameValuePair("id", networkId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteNetworkResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteNetworkResponse object
     *
     * @param doc
     * @return
     */
    private DeleteNetworkResponse getDeleteNetworkResponse(Document doc) {
        DeleteNetworkResponse response = new DeleteNetworkResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Network 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Network 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all available networks.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNetworksResponse listNetworks(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNetworks", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListNetworksResponse(responseDocument);
    }

    /**
     * Converts XML document into ListNetworksResponse object
     *
     * @param doc
     * @return
     */
    private ListNetworksResponse getListNetworksResponse(Document doc) {
        ListNetworksResponse response = new ListNetworksResponse();

        NodeList list = doc.getElementsByTagName("network");        
        List<NetworkResponse> networks = new LinkedList<NetworkResponse>();        
        if (list.getLength() > 0) {
            for (int listNetworkIndex = 0; listNetworkIndex < list.getLength(); listNetworkIndex++) {
                Node listNetworkNode = list.item(listNetworkIndex);

                NetworkResponse network = new NetworkResponse();                                               
                List<ServiceResponse> services = new LinkedList<ServiceResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                List<ProviderResponse> providers = new LinkedList<ProviderResponse>();
                NodeList listNetworkProperties = listNetworkNode.getChildNodes();
                for (int childIndex = 0; childIndex < listNetworkProperties.getLength(); childIndex++) {
                    Node property = listNetworkProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        network.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        network.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        network.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        network.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("acltype")) {
                        network.setAclType(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcastdomaintype")) {
                        network.setBroadcastDomainType(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcasturi")) {
                        network.setBroadcastUri(property.getTextContent());
                    } else if (property.getNodeName().equals("canusefordeploy")) {
                        network.setCanUseForDeploy(property.getTextContent());
                    } else if (property.getNodeName().equals("cidr")) {
                        network.setCidr(property.getTextContent());
                    } else if (property.getNodeName().equals("displaynetwork")) {
                        network.setDisplayNetwork(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                        network.setDisplayText(property.getTextContent());                    
                    } else if (property.getNodeName().equals("dns1")) {
                        network.setDns1(property.getTextContent());
                    } else if (property.getNodeName().equals("dns2")) {
                        network.setDns2(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        network.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        network.setDomainId(property.getTextContent());
                    }else if (property.getNodeName().equals("networkofferingid")) {
                        network.setNetworkOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        network.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6cidr")) {
                        network.setIp6Cidr(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6gateway")) {
                        network.setIp6Gateway(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        network.setIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("ispersistent")) {
                        network.setIsPersistent(property.getTextContent());
                    } else if (property.getNodeName().equals("issystem")) {
                        network.setIsSystem(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        network.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        network.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                    	network.setNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("networkdomain")) {
                        network.setNetworkDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingavailability")) {
                        network.setNetworkOfferingAvailability(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingconservemode")) {
                        network.setNetworkOfferingConserveMode(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingdisplaytext")) {
                        network.setNetworkOfferingDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingid")) {
                        network.setNetworkOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingname")) {
                        network.setNetworkOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        network.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        network.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        network.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("related")) {
                        network.setRelated(property.getTextContent());
                    } else if (property.getNodeName().equals("reservediprange")) {
                        network.setReservedIpRange(property.getTextContent());
                    } else if (property.getNodeName().equals("restartrequired")) {
                        network.setRestartRequired(property.getTextContent());
                    } else if (property.getNodeName().equals("specifyipranges")) {
                        network.setSpecifyIpRanges(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        network.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("subdomainaccess")) {
                        network.setSubDomainAccess(property.getTextContent());
                    } else if (property.getNodeName().equals("networkcidr")) {
                        network.setNetworkCidr(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                        network.setTrafficType(property.getTextContent());
                    } else if (property.getNodeName().equals("ispersistent")) {
                        network.setIsPersistent(property.getTextContent());
                    } else if (property.getNodeName().equals("restartrequired")) {
                        network.setRestartRequired(property.getTextContent());
                    } else if (property.getNodeName().equals("aclid")) {
                        network.setAclId(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        network.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("vlan")) {
                        network.setVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcid")) {
                        network.setVpcId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        network.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        network.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("service")) {
//                        NodeList serviceProperties = property.getChildNodes();
//                        if (serviceProperties.getLength() > 0) {
//                            ServiceResponse service = new ServiceResponse();
//                            for (int serviceIndex = 0; serviceIndex < list.getLength(); serviceIndex++) {
//                                Node serviceProperty = serviceProperties.item(serviceIndex);
//                                if (serviceProperty.getNodeName().equals("name")) {
//                                    service.setName(property.getTextContent());
//                                } else if (serviceProperty.getNodeName().equals("capability")) {
//                                    NodeList capabilityProperties = serviceProperty.getChildNodes();
//                                    if (capabilityProperties.getLength() > 0) {
//                                        CapabilityResponse capability = new CapabilityResponse();
//                                        for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
//                                            Node capabilityProperty = capabilityProperties.item(capabilityIndex);
//                                            if (capabilityProperty.getNodeName().equals("canchooseservicecapability")) {
//                                                capability.setCanChooseServiceCapability(property.getTextContent());
//                                            } else if (capabilityProperty.getNodeName().equals("name")) {
//                                                capability.setName(property.getTextContent());
//                                            } else if (capabilityProperty.getNodeName().equals("value")) {
//                                                capability.setValue(property.getTextContent());
//                                            }
//                                        }
//                                        capabilities.add(capability);
//                                    }
//                                } else if (serviceProperty.getNodeName().equals("provider")) {
//                                    NodeList providerProperties = serviceProperty.getChildNodes();
//                                    if (providerProperties.getLength() > 0) {
//                                        ProviderResponse provider = new ProviderResponse();
//                                        for (int providerIndex = 0; providerIndex < providerProperties.getLength(); providerIndex++) {
//                                            Node providerProperty = providerProperties.item(providerIndex);
//                                            if (providerProperty.getNodeName().equals("id")) {
//                                                provider.setId(property.getTextContent());
//                                            } else if (property.getNodeName().equals("canenableindividualservice")) {
//                                                provider.setCanEnableIndividualService(property.getTextContent());
//                                            } else if (property.getNodeName().equals("destinationphysicalnetworkid")) {
//                                                provider.setDestinationPhysicalNetworkId(property.getTextContent());
//                                            } else if (property.getNodeName().equals("name")) {
//                                                provider.setName(property.getTextContent());
//                                            } else if (property.getNodeName().equals("physicalnetworkid")) {
//                                                provider.setPhysicalNetworkId(property.getTextContent());
//                                            } else if (property.getNodeName().equals("servicelist")) {
//                                                provider.setServiceList(property.getTextContent());
//                                            } else if (property.getNodeName().equals("state")) {
//                                                provider.setState(property.getTextContent());
//                                            }
//                                        }
//                                        providers.add(provider);
//                                    }
//                                }
//                            }
//                            service.setCapabilities(capabilities);
//                            service.setProviders(providers);
//                            services.add(service);
//                        }
                    } else if (property.getNodeName().equals("tags")) {
//                        NodeList tagsProperties = property.getChildNodes();
//                        if (tagsProperties.getLength() > 0) {
//                            TagsResponse tags = new TagsResponse();
//                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
//                                property = tagsProperties.item(tagsIndex);
//
//                                if (property.getNodeName().equals("account")) {
//                                    tags.setAccount(property.getTextContent());
//                                } else if (property.getNodeName().equals("customer")) {
//                                    tags.setCustomer(property.getTextContent());
//                                } else if (property.getNodeName().equals("domain")) {
//                                    tags.setDomain(property.getTextContent());
//                                } else if (property.getNodeName().equals("domainid")) {
//                                    tags.setDomainId(property.getTextContent());
//                                } else if (property.getNodeName().equals("key")) {
//                                    tags.setKey(property.getTextContent());
//                                } else if (property.getNodeName().equals("project")) {
//                                    tags.setProject(property.getTextContent());
//                                } else if (property.getNodeName().equals("projectid")) {
//                                    tags.setProjectId(property.getTextContent());
//                                } else if (property.getNodeName().equals("resourceid")) {
//                                    tags.setResourceId(property.getTextContent());
//                                } else if (property.getNodeName().equals("resourcetype")) {
//                                    tags.setResourceType(property.getTextContent());
//                                } else if (property.getNodeName().equals("value")) {
//                                    tags.setValue(property.getTextContent());
//                                }
//                            }
//                            tagss.add(tags);
//                        }
                    }
//                    network.setServices(services);
//                    network.setTagss(tagss);                
                }
                networks.add(network);
            }
            response.setListNetworks(networks);
        }            
        return response;
    }

    /**
     * Restarts the network; includes 1) restarting network elements - virtual routers, dhcp servers 2) reapplying all
     * public ips 3) reapplying loadBalancing/portForwarding rules
     *
     * @param networkId the network id to restart
     * @param optional
     * @return
     * @throws Exception
     */
    public RestartNetworkResponse restartNetwork(String networkId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("restartNetwork", optional);
        arguments.add(new NameValuePair("id", networkId));

        Document responseDocument = server.makeRequest(arguments);

        return getRestartNetworkResponse(responseDocument);
    }

    /**
     * Converts XML document into RestartNetworkResponse object
     *
     * @param doc
     * @return
     */
    private RestartNetworkResponse getRestartNetworkResponse(Document doc) {
        RestartNetworkResponse response = new RestartNetworkResponse();

        // get Id from XML and set the id of the network 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get the account the public IP address is associated with
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get date the public IP address was acquired
        list = doc.getElementsByTagName("allocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAllocated(node.getTextContent());
        }

        // get the ID of the Network associated with the IP address
        list = doc.getElementsByTagName("associatednetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAssociatedNetworkId(node.getTextContent());
        }

        // get the name of the Network associated with the IP address
        list = doc.getElementsByTagName("associatednetworkname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAssociatedNetworkName(node.getTextContent());
        }

        // get domain from XML and set the domain name of the network owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domain id of the network owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the virtual network for the IP address
        list = doc.getElementsByTagName("forvirtualnetwork");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setForVirtualNetwork(node.getTextContent());
        }

        // get public IP address
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get public IP portable across the zones
        list = doc.getElementsByTagName("isportable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPortable(node.getTextContent());
        }

        // get the IP address is a source nat address, false otherwise
        list = doc.getElementsByTagName("issourcenat");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsSourceNat(node.getTextContent());
        }

        // get true if this ip is for static nat, false otherwise
        list = doc.getElementsByTagName("isstaticnat");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsStaticNat(node.getTextContent());
        }

        // get true if this ip is system ip
        list = doc.getElementsByTagName("issystem");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsSystem(node.getTextContent());
        }

        // get the ID of the Network where ip belongs to
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get the physical network this belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get the project name of the address
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the ipaddress
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get purpose of the IP address
        list = doc.getElementsByTagName("purpose");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPurpose(node.getTextContent());
        }

        // get state from XML and set state of the network
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get virtual machine display name the ip address is assigned to
        list = doc.getElementsByTagName("virtualmachinedisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineDisplayName(node.getTextContent());
        }

        // get virutal machine name the ip address is assigned to 
        list = doc.getElementsByTagName("virtualmachinename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineName(node.getTextContent());
        }

        // get the ID of the VLAN associated with the IP address
        list = doc.getElementsByTagName("vlanid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlanId(node.getTextContent());
        }

        // get the VLAN associated with the IP address
        list = doc.getElementsByTagName("vlanname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlanName(node.getTextContent());
        }

        // get virtual machine ip address
        list = doc.getElementsByTagName("vmipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmIpAddress(node.getTextContent());
        }

        // get VPC the ip belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get zoneid from XML and set zoneid of the network
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set zonename of the network
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // gets associated tag values for the virtual machine
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node tagsNode = list.item(index);
                TagsResponse tags = new TagsResponse();
                NodeList tagsProperties = tagsNode.getChildNodes();
                for (int childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                    Node tagsProperty = tagsProperties.item(childIndex);
                    if (tagsProperty.getNodeName().equals("account")) {
                        tags.setAccount(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("customer")) {
                        tags.setCustomer(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domain")) {
                        tags.setDomain(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                        tags.setDomainId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("key")) {
                        tags.setKey(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("project")) {
                        tags.setProject(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                        tags.setProjectId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                        tags.setResourceId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                        tags.setResourceType(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("value")) {
                        tags.setValue(tagsProperty.getTextContent());
                    }

                }
                tagss.add(tags);
                response.setTagss(tagss);
            }
        }

        // get jobid from XML and set the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;
    }

    /**
     * Updates a network
     *
     * @param networkId the ID of the network
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateNetworkResponse updateNetwork(String networkId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateNetwork", optional);
        arguments.add(new NameValuePair("id", networkId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateNetworkResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateNetworkResponse object
     *
     * @param doc
     * @return
     */
    private UpdateNetworkResponse getUpdateNetworkResponse(Document doc) {
        UpdateNetworkResponse response = new UpdateNetworkResponse();

        // get Id from XML and set the id of the network 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set	the owner of the network
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get ACL Id associated with the VPC network
        list = doc.getElementsByTagName("aclid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclId(node.getTextContent());
        }

        // get acltype from XML and set	acl type - access type to the network
        list = doc.getElementsByTagName("acltype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclType(node.getTextContent());
        }

        // get broadcastdomaintype from XML and set Broadcast domain type of the network
        list = doc.getElementsByTagName("broadcastdomaintype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBroadcastDomainType(node.getTextContent());
        }

        // get broadcasturi from XML and set broadcast uri of the network
        list = doc.getElementsByTagName("broadcasturi");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBroadcastUri(node.getTextContent());
        }

        // get list networks available for vm deployment
        list = doc.getElementsByTagName("canusefordeploy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCanUseForDeploy(node.getTextContent());
        }

        // get cidr from XML and set cidr of the network
        list = doc.getElementsByTagName("cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidr(node.getTextContent());
        }

        // get an optional field, whether to the display the network to the end user or not
        list = doc.getElementsByTagName("displaynetwork");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayNetwork(node.getTextContent());
        }

        // get displaytext from XML and set displaytext of the network
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get dns1 from XML and set dns1 of the network
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set dns2 of the network
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get domain from XML and set the domain name of the network owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domain id of the network owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get gateway from XML and set the network's gateway
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get the cidr of IPv6 network
        list = doc.getElementsByTagName("ip6cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Cidr(node.getTextContent());
        }

        // get the gateway of IPv6 network
        list = doc.getElementsByTagName("ip6gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Gateway(node.getTextContent());
        }

        // get isdefault from XML and set true if network is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // get list networks that are persistent
        list = doc.getElementsByTagName("ispersistent");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPersistent(node.getTextContent());
        }

        // get issystem from XML and set true if network is system, false otherwise
        list = doc.getElementsByTagName("issystem");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsSystem(node.getTextContent());
        }

        // get name from XML and set the name of the network
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get netmask from XML and set the netmask of the network
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetmask(node.getTextContent());
        }

        // the network CIDR of the guest network configured with IP reservation. 
        list = doc.getElementsByTagName("networkcidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkCidr(node.getTextContent());
        }

        // get networkdomain from XML and set the networkdomain of the network
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get networkofferingavailability from XML and set availability of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingavailability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingAvailability(node.getTextContent());
        }

        // get true if network offering is ip conserve mode enabled
        list = doc.getElementsByTagName("networkofferingconservemode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingConserveMode(node.getTextContent());
        }

        // get networkofferingdisplaytext from XML and set display text of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingDisplayText(node.getTextContent());
        }

        // get networkofferingid from XML and set network offering id the network is created from
        list = doc.getElementsByTagName("networkofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingId(node.getTextContent());
        }

        // get networkofferingname from XML and set name of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingName(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network id
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get project from XML and set the project name of the address
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set the project id of the address
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get related from XML and set related to what other network configuration
        list = doc.getElementsByTagName("related");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRelated(node.getTextContent());
        }

        // get the network's IP range not to be used by CloudStack guest VMs and can be used for non CloudStack purposes
        list = doc.getElementsByTagName("reservediprange");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setReservedIpRange(node.getTextContent());
        }

        // get restartrequired from XML and set true network requires restart
        list = doc.getElementsByTagName("restartrequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRestartRequired(node.getTextContent());
        }

        // get specifyipranges from XML and set true if network supports specifying ip ranges, false otherwise
        list = doc.getElementsByTagName("specifyipranges");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSpecifyIpRanges(node.getTextContent());
        }

        // get state from XML and set state of the network
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get subdomainaccess from XML and set subdomainaccess of the network
        list = doc.getElementsByTagName("subdomainaccess");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSubdomainAccess(node.getTextContent());
        }

        // get traffictype from XML and set traffictype of the network
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTrafficType(node.getTextContent());
        }

        // get type from XML and set type of the network
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get vlan from XML and set vlan of the network
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get VPC the network belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get zoneid from XML and set zoneid of the network
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set zonename of the network
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        //the list of supported services
        list = doc.getElementsByTagName("service");
        if (list.getLength() > 0) {
            List<ServiceResponse> services = new LinkedList<ServiceResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node serviceNode = list.item(index);
                ServiceResponse service = new ServiceResponse();

                NodeList serviceProperties = serviceNode.getChildNodes();
                for (int childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        service.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("capability")) {
                        list = doc.getElementsByTagName("capability");
                        if (list.getLength() > 0) {
                            List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                            for (index = 0; index < list.getLength(); index++) {
                                Node capabilityNode = list.item(index);
                                CapabilityResponse capability = new CapabilityResponse();

                                NodeList capabilityProperties = capabilityNode.getChildNodes();
                                for (childIndex = 0; childIndex < capabilityProperties.getLength(); childIndex++) {
                                    property = list.item(childIndex);;

                                    if (property.getNodeName().equals("canchooseservicecapability")) {
                                        capability.setCanChooseServiceCapability(property.getTextContent());
                                    } else if (property.getNodeName().equals("name")) {
                                        capability.setName(property.getTextContent());
                                    } else if (property.getNodeName().equals("value")) {
                                        capability.setValue(property.getTextContent());
                                    }

                                }

                                capabilities.add(capability);
                                response.setCapabilities(capabilities);
                            }

                        }
                    } else if (property.getNodeName().equals("provider")) {
                        list = doc.getElementsByTagName("provider");
                        if (list.getLength() > 0) {
                            List<ProviderResponse> providers = new LinkedList<ProviderResponse>();
                            for (index = 0; index < list.getLength(); index++) {
                                Node providerNode = list.item(index);
                                ProviderResponse provider = new ProviderResponse();

                                NodeList providerProperties = providerNode.getChildNodes();
                                for (childIndex = 0; childIndex < providerProperties.getLength(); childIndex++) {
                                    property = list.item(childIndex);

                                    if (property.getNodeName().equals("id")) {
                                        provider.setId(property.getTextContent());
                                    } else if (property.getNodeName().equals("canenableindividualservice")) {
                                        provider.setCanEnableIndividualService(property.getTextContent());
                                    } else if (property.getNodeName().equals("destinationphysicalnetworkid")) {
                                        provider.setDestinationPhysicalNetworkId(property.getTextContent());
                                    } else if (property.getNodeName().equals("name")) {
                                        provider.setName(property.getTextContent());
                                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                                        provider.setPhysicalNetworkId(property.getTextContent());
                                    } else if (property.getNodeName().equals("servicelist")) {
                                        provider.setServiceList(property.getTextContent());
                                    } else if (property.getNodeName().equals("state")) {
                                        provider.setState(property.getTextContent());
                                    }

                                }

                                providers.add(provider);
                                response.setProviders(providers);
                            }

                        }
                    }
                    services.add(service);
                    response.setServices(services);
                }
            }
        }

        // the list of resource tags associated with network
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node tagsNode = list.item(index);
                TagsResponse tags = new TagsResponse();
                NodeList tagsProperties = tagsNode.getChildNodes();
                for (int childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                    Node tagsProperty = tagsProperties.item(childIndex);
                    if (tagsProperty.getNodeName().equals("account")) {
                        tags.setAccount(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("customer")) {
                        tags.setCustomer(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domain")) {
                        tags.setDomain(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                        tags.setDomainId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("key")) {
                        tags.setKey(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("project")) {
                        tags.setProject(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                        tags.setProjectId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                        tags.setResourceId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                        tags.setResourceType(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("value")) {
                        tags.setValue(tagsProperty.getTextContent());
                    }

                }
                tagss.add(tags);
                response.setTagss(tagss);
            }
        }
        return response;
    }

    /**
     * Creates a physical network
     *
     * @param physicalNetworkName the name of the physical network
     * @param physicalNetworkZoneId the Zone ID for the physical network
     * @param optional
     * @return
     * @throws Exception
     */
    public CreatePhysicalNetworkResponse createPhysicalNetwork(String physicalNetworkName,
            String physicalNetworkZoneId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createPhysicalNetwork", optional);
        arguments.add(new NameValuePair("name", physicalNetworkName));
        arguments.add(new NameValuePair("zoneid", physicalNetworkZoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreatePhysicalNetworkResponse(responseDocument);
    }

    /**
     * Converts XML document into CreatePhysicalNetworkResponse object
     *
     * @param doc
     * @return
     */
    private CreatePhysicalNetworkResponse getCreatePhysicalNetworkResponse(Document doc) {
        CreatePhysicalNetworkResponse response = new CreatePhysicalNetworkResponse();

        // get Id from XML and set the id of the physical network 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get broadcastdomainrange from XML and set Broadcast domain range of the physical network
        list = doc.getElementsByTagName("broadcastdomainrange");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBroadcastDomainRange(node.getTextContent());
        }

        // get domainid from XML and set the domain id of the physical network owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get isolationmethods from XML and set the isolation methods for physical network
        list = doc.getElementsByTagName("isolationmethods");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsolationMethods(node.getTextContent());
        }

        // get name from XML and set the name for physical network
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkspeed from XML and set the networkspeed for physical network
        list = doc.getElementsByTagName("networkspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkSpeed(node.getTextContent());
        }

        // get state from XML and set the state for physical network
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get tags from XML and set the tags for physical network
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get vlan from XML and set the vlan for physical network
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get zoneid from XML and set the zoneid for physical network
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * Deletes a Physical Network.
     *
     * @param physicalNetworkId the ID of the Physical network
     * @return
     * @throws Exception
     */
    public DeletePhysicalNetworkResponse deletePhysicalNetwork(String physicalNetworkId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deletePhysicalNetwork", null);
        arguments.add(new NameValuePair("id", physicalNetworkId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeletePhysicalNetworkResponse(responseDocument);
    }

    /**
     * Converts XML document into DeletePhysicalNetworkResponse object
     *
     * @param doc
     * @return
     */
    private DeletePhysicalNetworkResponse getDeletePhysicalNetworkResponse(Document doc) {
        DeletePhysicalNetworkResponse response = new DeletePhysicalNetworkResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Physical Network 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Physical Network 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists physical networks
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListPhysicalNetworksResponse listPhysicalNetworks(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listPhysicalNetworks", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListPhysicalNetworksResponse(responseDocument);
    }

    /**
     * Converts XML document into ListPhysicalNetworksResponse object
     *
     * @param doc
     * @return
     */
    private ListPhysicalNetworksResponse getListPhysicalNetworksResponse(Document doc) {
        ListPhysicalNetworksResponse response = new ListPhysicalNetworksResponse();

        NodeList list = doc.getElementsByTagName("physicalnetwork");
        List<PhysicalNetworkResponse> physicalNetworks = new LinkedList<PhysicalNetworkResponse>();
        if (list.getLength() > 0) {
            for (int physicalNetworkIndex = 0; physicalNetworkIndex < list.getLength(); physicalNetworkIndex++) {
                Node physicalNetworkNode = list.item(physicalNetworkIndex);

                PhysicalNetworkResponse physicalNetwork = new PhysicalNetworkResponse();
                NodeList physicalNetworkProperties = physicalNetworkNode.getChildNodes();
                for (int childIndex = 0; childIndex < physicalNetworkProperties.getLength(); childIndex++) {
                    Node property = physicalNetworkProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        physicalNetwork.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcastdomainrange")) {
                        physicalNetwork.setBroadcastDomainRange(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        physicalNetwork.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("isolationmethods")) {
                        physicalNetwork.setIsolationMethods(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        physicalNetwork.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkspeed")) {
                        physicalNetwork.setNetworkSpeed(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        physicalNetwork.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        physicalNetwork.setTags(property.getTextContent());
                    } else if (property.getNodeName().equals("vlan")) {
                        physicalNetwork.setVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        physicalNetwork.setZoneId(property.getTextContent());
                    }
                }
                physicalNetworks.add(physicalNetwork);
            }
        }
        response.setPhysicalNetworks(physicalNetworks);
        return response;
    }

    /**
     * Updates a physical network
     *
     * @param physicalNetworkId physical network id
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdatePhysicalNetworkResponse updatePhysicalNetwork(String physicalNetworkId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updatePhysicalNetwork", optional);
        arguments.add(new NameValuePair("id", physicalNetworkId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdatePhysicalNetworkResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdatePhysicalNetworkResponse object
     *
     * @param doc
     * @return
     */
    private UpdatePhysicalNetworkResponse getUpdatePhysicalNetworkResponse(Document doc) {
        UpdatePhysicalNetworkResponse response = new UpdatePhysicalNetworkResponse();

        // get Id from XML and set the id of the physical network 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get broadcastdomainrange from XML and set Broadcast domain range of the physical network
        list = doc.getElementsByTagName("broadcastdomainrange");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBroadcastDomainRange(node.getTextContent());
        }

        // get domainid from XML and set the domain id of the physical network owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get isolationmethods from XML and set the isolation methods for physical network
        list = doc.getElementsByTagName("isolationmethods");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsolationMethods(node.getTextContent());
        }

        // get name from XML and set the name for physical network
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkspeed from XML and set the networkspeed for physical network
        list = doc.getElementsByTagName("networkspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkSpeed(node.getTextContent());
        }

        // get state from XML and set the state for physical network
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get tags from XML and set the tags for physical network
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get vlan from XML and set the vlan for physical network
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get zoneid from XML and set the zoneid for physical network
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all network services provided by CloudStack or for the given Provider.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListSupportedNetworkServicesResponse listSupportedNetworkServices(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listSupportedNetworkServices", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListSupportedNetworkServicesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListSupportedNetworkServicesResponse object
     *
     * @param doc
     * @return
     */
    private ListSupportedNetworkServicesResponse getListSupportedNetworkServicesResponse(Document doc) {
        ListSupportedNetworkServicesResponse response = new ListSupportedNetworkServicesResponse();

        NodeList list = doc.getElementsByTagName("supportednetworkservice");
        List<NetworkServicesResponse> networkServices = new LinkedList<NetworkServicesResponse>();
        if (list.getLength() > 0) {
            for (int networkServiceIndex = 0; networkServiceIndex < list.getLength(); networkServiceIndex++) {
                Node networkServiceNode = list.item(networkServiceIndex);

                NetworkServicesResponse networkService = new NetworkServicesResponse();
                List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                List<ProviderResponse> providers = new LinkedList<ProviderResponse>();
                NodeList networkServiceProperties = networkServiceNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkServiceProperties.getLength(); childIndex++) {
                    Node property = networkServiceProperties.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        networkService.setServiceName(property.getTextContent());
                    } else if (property.getNodeName().equals("capability")) {
                        NodeList capabilityProperties = property.getChildNodes();
                        if (capabilityProperties.getLength() > 0) {
                            CapabilityResponse capability = new CapabilityResponse();
                            for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
                                Node capabilityProperty = capabilityProperties.item(capabilityIndex);

                                if (capabilityProperty.getNodeName().equals("canchooseservicecapability")) {
                                    capability.setCanChooseServiceCapability(property.getTextContent());
                                } else if (capabilityProperty.getNodeName().equals("name")) {
                                    capability.setName(property.getTextContent());
                                } else if (capabilityProperty.getNodeName().equals("value")) {
                                    capability.setValue(property.getTextContent());
                                }
                            }
                            capabilities.add(capability);
                        }

                    } else if (property.getNodeName().equals("provider")) {
                        NodeList providerProperties = property.getChildNodes();
                        if (providerProperties.getLength() > 0) {
                            ProviderResponse provider = new ProviderResponse();
                            for (int providerIndex = 0; providerIndex < providerProperties.getLength(); providerIndex++) {
                                Node providerProperty = providerProperties.item(providerIndex);
                                if (providerProperty.getNodeName().equals("id")) {
                                    provider.setId(property.getTextContent());
                                } else if (property.getNodeName().equals("canenableindividualservice")) {
                                    provider.setCanEnableIndividualService(property.getTextContent());
                                } else if (property.getNodeName().equals("destinationphysicalnetworkid")) {
                                    provider.setDestinationPhysicalNetworkId(property.getTextContent());
                                } else if (property.getNodeName().equals("name")) {
                                    provider.setName(property.getTextContent());
                                } else if (property.getNodeName().equals("physicalnetworkid")) {
                                    provider.setPhysicalNetworkId(property.getTextContent());
                                } else if (property.getNodeName().equals("servicelist")) {
                                    provider.setServiceList(property.getTextContent());
                                } else if (property.getNodeName().equals("state")) {
                                    provider.setState(property.getTextContent());
                                }
                            }
                            providers.add(provider);
                        }
                    }
                }
                networkService.setCapabilities(capabilities);
                networkService.setProviders(providers);
                networkServices.add(networkService);
            }
        }
        response.setNetworkServices(networkServices);
        return response;
    }

    /**
     * Adds a network serviceProvider to a physical network
     *
     * @param physicalNetworkName the name for the physical network service provider
     * @param physicalNetworkId the Physical Network ID to add the provider to
     * @param optional
     * @return
     * @throws Exception
     */
    public AddNetworkServiceProviderResponse addNetworkServiceProvider(String physicalNetworkName,
            String physicalNetworkId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addNetworkServiceProvider", optional);
        arguments.add(new NameValuePair("name", physicalNetworkName));
        arguments.add(new NameValuePair("physicalnetworkid", physicalNetworkId));

        Document responseDocument = server.makeRequest(arguments);

        return getAddNetworkServiceProviderResponse(responseDocument);
    }

    /**
     * Converts XML document into AddNetworkServiceProviderResponse object
     *
     * @param doc
     * @return
     */
    private AddNetworkServiceProviderResponse getAddNetworkServiceProviderResponse(Document doc) {
        AddNetworkServiceProviderResponse response = new AddNetworkServiceProviderResponse();

        // get id from XML and set uuid of the network provider
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get canenableindividualservice from XML and set true if individual services can be enabled/disabled
        list = doc.getElementsByTagName("canenableindividualservice");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCanEnableIndividualService(node.getTextContent());
        }

        // get destinationphysicalnetworkid from XML and set the destination physical network
        list = doc.getElementsByTagName("destinationphysicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestinationPhysicalNetworkid(node.getTextContent());
        }

        // get name from XML and set the provider name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network this belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get servicelist from XML and set services for this provider
        list = doc.getElementsByTagName("servicelist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceList(node.getTextContent());
        }

        // get state from XML and set state of the network provider
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        return response;
    }

    /**
     * Deletes a Network Service Provider.
     *
     * @param networkServiceProviderId the ID of the network service provider
     * @return
     * @throws Exception
     */
    public DeleteNetworkServiceProviderResponse deleteNetworkServiceProvider(String networkServiceProviderId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteNetworkServiceProvider", null);
        arguments.add(new NameValuePair("id", networkServiceProviderId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteNetworkServiceProviderResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteNetworkServiceProviderResponse object
     *
     * @param doc
     * @return
     */
    private DeleteNetworkServiceProviderResponse getDeleteNetworkServiceProviderResponse(Document doc) {
        DeleteNetworkServiceProviderResponse response = new DeleteNetworkServiceProviderResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Network provider
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Network provider
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists network serviceproviders for a given physical network.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNetworkServiceProvidersResponse listNetworkServiceProviders(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNetworkServiceProviders", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListNetworkServiceProvidersResponse(responseDocument);
    }

    /**
     * Converts XML document into ListNetworkServiceProvidersResponse object
     *
     * @param doc
     * @return
     */
    private ListNetworkServiceProvidersResponse getListNetworkServiceProvidersResponse(Document doc) {
        ListNetworkServiceProvidersResponse response = new ListNetworkServiceProvidersResponse();

        NodeList list = doc.getElementsByTagName("networkserviceprovider");
        List<NetworkServiceProviderResponse> networkServiceProviders = new LinkedList<NetworkServiceProviderResponse>();
        if (list.getLength() > 0) {
            for (int networkServiceProviderIndex = 0; networkServiceProviderIndex < list.getLength(); networkServiceProviderIndex++) {
                Node networkServiceProviderNode = list.item(networkServiceProviderIndex);

                NetworkServiceProviderResponse networkServiceProvider = new NetworkServiceProviderResponse();
                NodeList networkServiceProviderProperties = networkServiceProviderNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkServiceProviderProperties.getLength(); childIndex++) {
                    Node property = networkServiceProviderProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        networkServiceProvider.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("canenableindividualservice")) {
                        networkServiceProvider.setCanEnableIndividualService(property.getTextContent());
                    } else if (property.getNodeName().equals("destinationphysicalnetworkid")) {
                        networkServiceProvider.setDestinationPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        networkServiceProvider.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        networkServiceProvider.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("servicelist")) {
                        networkServiceProvider.setServiceList(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        networkServiceProvider.setState(property.getTextContent());
                    }
                }
                networkServiceProviders.add(networkServiceProvider);
            }
        }
        response.setNetworkServiceProviders(networkServiceProviders);
        return response;
    }

    /**
     * Updates a network serviceProvider of a physical network
     *
     * @param networkServiceProviderId network service provider id
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateNetworkServiceProviderResponse updateNetworkServiceProvider(String networkServiceProviderId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateNetworkServiceProvider", optional);
        arguments.add(new NameValuePair("id", networkServiceProviderId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateNetworkServiceProviderResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateNetworkServiceProviderResponse object
     *
     * @param doc
     * @return
     */
    private UpdateNetworkServiceProviderResponse getUpdateNetworkServiceProviderResponse(Document doc) {
        UpdateNetworkServiceProviderResponse response = new UpdateNetworkServiceProviderResponse();

        // get id from XML and set uuid of the network provider
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get canenableindividualservice from XML and set true if individual services can be enabled/disabled
        list = doc.getElementsByTagName("canenableindividualservice");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCanEnableIndividualService(node.getTextContent());
        }

        // get destinationphysicalnetworkid from XML and set the destination physical network
        list = doc.getElementsByTagName("destinationphysicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestinationPhysicalNetworkid(node.getTextContent());
        }

        // get name from XML and set the provider name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network this belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get servicelist from XML and set services for this provider
        list = doc.getElementsByTagName("servicelist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceList(node.getTextContent());
        }

        // get state from XML and set state of the network provider
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        return response;
    }

    /**
     * Creates a Storage network IP range.
     *
     * @param storageNetworkGateway the gateway for storage network
     * @param storageNetworkNetmask the netmask for storage network
     * @param storageNetworkPodId UUID of pod where the ip range belongs to
     * @param storageNetworkStartIp the beginning IP address
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateStorageNetworkIpRangeResponse createStorageNetworkIpRange(String storageNetworkGateway,
            String storageNetworkNetmask, String storageNetworkPodId,
            String storageNetworkStartIp, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createStorageNetworkIpRange", optional);
        arguments.add(new NameValuePair("gateway", storageNetworkGateway));
        arguments.add(new NameValuePair("netmask", storageNetworkNetmask));
        arguments.add(new NameValuePair("podid", storageNetworkPodId));
        arguments.add(new NameValuePair("startip", storageNetworkStartIp));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateStorageNetworkIpRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateStorageNetworkIpRangeResponse object
     *
     * @param doc
     * @return
     */
    private CreateStorageNetworkIpRangeResponse getCreateStorageNetworkIpRangeResponse(Document doc) {
        CreateStorageNetworkIpRangeResponse response = new CreateStorageNetworkIpRangeResponse();

        // get id from XML and set the uuid of storage network IP range.
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get endip from XML and set true if individual services can be enabled/disabled
        list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndIp(node.getTextContent());
        }

        // get gateway from XML and set	the gateway of the storage network IP range
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get netmask from XML and set	the netmask of the storage network IP range
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetmask(node.getTextContent());
        }

        // get networkid from XML and set the network uuid of storage network IP range range
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get podid from XML and set the Pod uuid for the storage network IP range
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get startip from XML and set the start ip of the storage network IP range
        list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartIp(node.getTextContent());
        }

        // get vlan from XML and set the ID or VID of the VLAN.
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get zoneid from XML and set the Zone uuid of the storage network IP range
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * Deletes a storage network IP Range.
     *
     * @param storageNetworkId
     * @return
     * @throws Exception
     */
    public DeleteStorageNetworkIpRangeResponse deleteStorageNetworkIpRange(String storageNetworkId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteStorageNetworkIpRange", null);
        arguments.add(new NameValuePair("id", storageNetworkId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteStorageNetworkIpRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteStorageNetworkIpRangeResponse object
     *
     * @param doc
     * @return
     */
    private DeleteStorageNetworkIpRangeResponse getDeleteStorageNetworkIpRangeResponse(Document doc) {
        DeleteStorageNetworkIpRangeResponse response = new DeleteStorageNetworkIpRangeResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Storage Network Ip Range 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Storage Network Ip Range 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * List a storage network IP range.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListStorageNetworkIpRangeResponse listStorageNetworkIpRange(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listStorageNetworkIpRange ", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListStorageNetworkIpRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into ListStorageNetworkIpRangeResponse object
     *
     * @param doc
     * @return
     */
    private ListStorageNetworkIpRangeResponse getListStorageNetworkIpRangeResponse(Document doc) {
        ListStorageNetworkIpRangeResponse response = new ListStorageNetworkIpRangeResponse();

        NodeList list = doc.getElementsByTagName("storagenetworkiprange");
        List<StorageNetworkIpRangeResponse> storageNetworkIpRanges = new LinkedList<StorageNetworkIpRangeResponse>();
        if (list.getLength() > 0) {
            for (int storageNetworkIpRangeIndex = 0; storageNetworkIpRangeIndex < list.getLength(); storageNetworkIpRangeIndex++) {
                Node storageNetworkIpRangeNode = list.item(storageNetworkIpRangeIndex);

                StorageNetworkIpRangeResponse storageNetworkIpRange = new StorageNetworkIpRangeResponse();
                NodeList storageNetworkIpRangeProperties = storageNetworkIpRangeNode.getChildNodes();
                for (int childIndex = 0; childIndex < storageNetworkIpRangeProperties.getLength(); childIndex++) {
                    Node property = storageNetworkIpRangeProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        storageNetworkIpRange.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("endip")) {
                        storageNetworkIpRange.setEndIp(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        storageNetworkIpRange.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                        storageNetworkIpRange.setNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        storageNetworkIpRange.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        storageNetworkIpRange.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("startip")) {
                        storageNetworkIpRange.setStartIp(property.getTextContent());
                    } else if (property.getNodeName().equals("vlan")) {
                        storageNetworkIpRange.setVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        storageNetworkIpRange.setZoneId(property.getTextContent());
                    }
                }
                storageNetworkIpRanges.add(storageNetworkIpRange);
            }
        }
        response.setStorageNetworkIpRanges(storageNetworkIpRanges);
        return response;
    }

    /**
     * Update a Storage network IP range, only allowed when no IPs in this range have been allocated.
     *
     * @param storageNetworkId
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateStorageNetworkIpRangeResponse updateStorageNetworkIpRange(String storageNetworkId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateStorageNetworkIpRange ", optional);
        arguments.add(new NameValuePair("id", storageNetworkId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateStorageNetworkIpRangeResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateStorageNetworkIpRangeResponse object
     *
     * @param doc
     * @return
     */
    private UpdateStorageNetworkIpRangeResponse getUpdateStorageNetworkIpRangeResponse(Document doc) {
        UpdateStorageNetworkIpRangeResponse response = new UpdateStorageNetworkIpRangeResponse();

        // get id from XML and set the uuid of storage network IP range.
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get endip from XML and set true if individual services can be enabled/disabled
        list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndIp(node.getTextContent());
        }

        // get gateway from XML and set	the gateway of the storage network IP range
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get netmask from XML and set	the netmask of the storage network IP range
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetmask(node.getTextContent());
        }

        // get networkid from XML and set the network uuid of storage network IP range range
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get podid from XML and set the Pod uuid for the storage network IP range
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get startip from XML and set the start ip of the storage network IP range
        list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartIp(node.getTextContent());
        }

        // get vlan from XML and set the ID or VID of the VLAN.
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get zoneid from XML and set the Zone uuid of the storage network IP range
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * lists network that are using a Nicira NVP device.
     *
     * @param nvpDeviceId
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNiciraNvpDeviceNetworksResponse listNiciraNvpDeviceNetworks(String nvpDeviceId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNiciraNvpDeviceNetworks", optional);
        arguments.add(new NameValuePair("id", nvpDeviceId));

        Document responseDocument = server.makeRequest(arguments);

        return getListNiciraNvpDeviceNetworksResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateStorageNetworkIpRangeResponse object
     *
     * @param doc
     * @return
     */
    private ListNiciraNvpDeviceNetworksResponse getListNiciraNvpDeviceNetworksResponse(Document doc) {
        ListNiciraNvpDeviceNetworksResponse response = new ListNiciraNvpDeviceNetworksResponse();

        NodeList list = doc.getElementsByTagName("niciranvpdevicenetwork");
        List<NiciraNvpDeviceNetworkResponse> niciraNvpDeviceNetworks = new LinkedList<NiciraNvpDeviceNetworkResponse>();
        if (list.getLength() > 0) {
            for (int niciraNvpDeviceNetworkIndex = 0; niciraNvpDeviceNetworkIndex < list.getLength(); niciraNvpDeviceNetworkIndex++) {
                Node niciraNvpDeviceNetworkNode = list.item(niciraNvpDeviceNetworkIndex);

                NiciraNvpDeviceNetworkResponse niciraNvpDeviceNetwork = new NiciraNvpDeviceNetworkResponse();
                List<ServiceResponse> services = new LinkedList<ServiceResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                List<ProviderResponse> providers = new LinkedList<ProviderResponse>();
                NodeList niciraNvpDeviceNetworkProperties = niciraNvpDeviceNetworkNode.getChildNodes();
                for (int childIndex = 0; childIndex < niciraNvpDeviceNetworkProperties.getLength(); childIndex++) {
                    Node property = niciraNvpDeviceNetworkProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        niciraNvpDeviceNetwork.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        niciraNvpDeviceNetwork.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("aclid")) {
                        niciraNvpDeviceNetwork.setAclId(property.getTextContent());
                    } else if (property.getNodeName().equals("acltype")) {
                        niciraNvpDeviceNetwork.setAclType(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcastdomaintype")) {
                        niciraNvpDeviceNetwork.setBroadcastDomainType(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcasturi")) {
                        niciraNvpDeviceNetwork.setBroadcastUri(property.getTextContent());
                    } else if (property.getNodeName().equals("canusefordeploy")) {
                        niciraNvpDeviceNetwork.setCanUseForDeploy(property.getTextContent());
                    } else if (property.getNodeName().equals("cidr")) {
                        niciraNvpDeviceNetwork.setCidr(property.getTextContent());
                    } else if (property.getNodeName().equals("displaynetwork")) {
                        niciraNvpDeviceNetwork.setDisplayNetwork(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                        niciraNvpDeviceNetwork.setDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("dns1")) {
                        niciraNvpDeviceNetwork.setDns1(property.getTextContent());
                    } else if (property.getNodeName().equals("dns2")) {
                        niciraNvpDeviceNetwork.setDns2(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        niciraNvpDeviceNetwork.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        niciraNvpDeviceNetwork.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        niciraNvpDeviceNetwork.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6cidr")) {
                        niciraNvpDeviceNetwork.setIp6Cidr(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6gateway")) {
                        niciraNvpDeviceNetwork.setIp6Gateway(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        niciraNvpDeviceNetwork.setIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("ispersistent")) {
                        niciraNvpDeviceNetwork.setIsPersistent(property.getTextContent());
                    } else if (property.getNodeName().equals("issystem")) {
                        niciraNvpDeviceNetwork.setIsSystem(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        niciraNvpDeviceNetwork.setNetworkName(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                        niciraNvpDeviceNetwork.setNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("networkcidr")) {
                        niciraNvpDeviceNetwork.setNetworkCidr(property.getTextContent());
                    } else if (property.getNodeName().equals("networkdomain")) {
                        niciraNvpDeviceNetwork.setNetworkDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingavailability")) {
                        niciraNvpDeviceNetwork.setNetworkOfferingAvailability(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingconservemode")) {
                        niciraNvpDeviceNetwork.setNetworkOfferingConserveMode(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingdisplaytext")) {
                        niciraNvpDeviceNetwork.setNetworkOfferingDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingid")) {
                        niciraNvpDeviceNetwork.setNetworkOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingname")) {
                        niciraNvpDeviceNetwork.setNetworkOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        niciraNvpDeviceNetwork.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        niciraNvpDeviceNetwork.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        niciraNvpDeviceNetwork.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("related")) {
                        niciraNvpDeviceNetwork.setRelated(property.getTextContent());
                    } else if (property.getNodeName().equals("reservediprange")) {
                        niciraNvpDeviceNetwork.setReservedIpRange(property.getTextContent());
                    } else if (property.getNodeName().equals("restartrequired")) {
                        niciraNvpDeviceNetwork.setRestartRequired(property.getTextContent());
                    } else if (property.getNodeName().equals("specifyipranges")) {
                        niciraNvpDeviceNetwork.setSpecifyIpRanges(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        niciraNvpDeviceNetwork.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("subdomainaccess")) {
                        niciraNvpDeviceNetwork.setSubDomainAccess(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                        niciraNvpDeviceNetwork.setTrafficType(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        niciraNvpDeviceNetwork.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("vlan")) {
                        niciraNvpDeviceNetwork.setVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcid")) {
                        niciraNvpDeviceNetwork.setVpcId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        niciraNvpDeviceNetwork.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        niciraNvpDeviceNetwork.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("service")) {
                        NodeList serviceProperties = property.getChildNodes();
                        if (serviceProperties.getLength() > 0) {
                            ServiceResponse service = new ServiceResponse();

                            for (int serviceIndex = 0; serviceIndex < list.getLength(); serviceIndex++) {
                                Node serviceProperty = serviceProperties.item(serviceIndex);
                                if (serviceProperty.getNodeName().equals("name")) {
                                    service.setName(property.getTextContent());
                                } else if (serviceProperty.getNodeName().equals("capability")) {
                                    NodeList capabilityProperties = serviceProperty.getChildNodes();
                                    if (capabilityProperties.getLength() > 0) {
                                        CapabilityResponse capability = new CapabilityResponse();
                                        for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
                                            Node capabilityProperty = capabilityProperties.item(capabilityIndex);

                                            if (capabilityProperty.getNodeName().equals("canchooseservicecapability")) {
                                                capability.setCanChooseServiceCapability(property.getTextContent());
                                            } else if (capabilityProperty.getNodeName().equals("name")) {
                                                capability.setName(property.getTextContent());
                                            } else if (capabilityProperty.getNodeName().equals("value")) {
                                                capability.setValue(property.getTextContent());
                                            }
                                        }
                                        capabilities.add(capability);
                                    }
                                } else if (serviceProperty.getNodeName().equals("provider")) {
                                    NodeList providerProperties = serviceProperty.getChildNodes();
                                    if (providerProperties.getLength() > 0) {
                                        ProviderResponse provider = new ProviderResponse();
                                        for (int providerIndex = 0; providerIndex < providerProperties.getLength(); providerIndex++) {
                                            Node providerProperty = providerProperties.item(providerIndex);
                                            if (providerProperty.getNodeName().equals("id")) {
                                                provider.setId(property.getTextContent());
                                            } else if (property.getNodeName().equals("canenableindividualservice")) {
                                                provider.setCanEnableIndividualService(property.getTextContent());
                                            } else if (property.getNodeName().equals("destinationphysicalnetworkid")) {
                                                provider.setDestinationPhysicalNetworkId(property.getTextContent());
                                            } else if (property.getNodeName().equals("name")) {
                                                provider.setName(property.getTextContent());
                                            } else if (property.getNodeName().equals("physicalnetworkid")) {
                                                provider.setPhysicalNetworkId(property.getTextContent());
                                            } else if (property.getNodeName().equals("servicelist")) {
                                                provider.setServiceList(property.getTextContent());
                                            } else if (property.getNodeName().equals("state")) {
                                                provider.setState(property.getTextContent());
                                            }
                                        }
                                        providers.add(provider);
                                    }
                                }
                            }
                            service.setCapabilities(capabilities);
                            service.setProviders(providers);
                            services.add(service);
                        }
                    } else if (property.getNodeName().equals("tags")) {
                        list = doc.getElementsByTagName("tags");
                        if (list.getLength() > 0) {
                            for (int index = 0; index < list.getLength(); index++) {
                                Node tagsNode = list.item(index);
                                TagsResponse tags = new TagsResponse();
                                NodeList tagsProperties = tagsNode.getChildNodes();
                                for (childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                                    Node tagsProperty = tagsProperties.item(childIndex);
                                    if (tagsProperty.getNodeName().equals("account")) {
                                        tags.setAccount(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("customer")) {
                                        tags.setCustomer(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("domain")) {
                                        tags.setDomain(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                                        tags.setDomainId(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("key")) {
                                        tags.setKey(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("project")) {
                                        tags.setProject(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                                        tags.setProjectId(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                                        tags.setResourceId(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                                        tags.setResourceType(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("value")) {
                                        tags.setValue(tagsProperty.getTextContent());
                                    }

                                }
                                tagss.add(tags);
                            }
                        }
                    }
                }
                niciraNvpDeviceNetwork.setServices(services);
                niciraNvpDeviceNetwork.setTagss(tagss);
                niciraNvpDeviceNetworks.add(niciraNvpDeviceNetwork);

            }
        }
        response.setNiciraNvpDeviceNetworks(niciraNvpDeviceNetworks);
        return response;
    }

    /**
     * Lists supported methods of network isolation.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNetworkIsolationMethodsResponse listNetworkIsolationMethods(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNetworkIsolationMethods", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListNetworkIsolationMethodsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListNetworkIsolationMethodsResponse object
     *
     * @param doc
     * @return
     */
    private ListNetworkIsolationMethodsResponse getListNetworkIsolationMethodsResponse(Document doc) {
        ListNetworkIsolationMethodsResponse response = new ListNetworkIsolationMethodsResponse();

        NodeList list = doc.getElementsByTagName("networkisolationmethod");
        List<NetworkIsolationMethodResponse> networkIsolationMethods = new LinkedList<NetworkIsolationMethodResponse>();
        if (list.getLength() > 0) {
            for (int networkIsolationMethodIndex = 0; networkIsolationMethodIndex < list.getLength(); networkIsolationMethodIndex++) {
                Node networkIsolationMethodNode = list.item(networkIsolationMethodIndex);

                NetworkIsolationMethodResponse networkIsolationMethod = new NetworkIsolationMethodResponse();
                NodeList networkIsolationMethodProperties = networkIsolationMethodNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkIsolationMethodProperties.getLength(); childIndex++) {
                    Node property = networkIsolationMethodProperties.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        networkIsolationMethod.setName(property.getTextContent());
                    }
                }
                networkIsolationMethods.add(networkIsolationMethod);
            }
        }
        response.setNetworkIsolationMethods(networkIsolationMethods);
        return response;
    }

    /**
     * Adds a network device of one of the following types: ExternalDhcp, ExternalFirewall, ExternalLoadBalancer,
     * PxeServer
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public AddNetworkDeviceResponse addNetworkDevice(
            HashMap<String, String> optional)
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

        // get id from XML and set the uuid of storage network IP range.
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDeviceId(node.getTextContent());
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

        // get id from XML and set the uuid of storage network IP range.
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDeviceId(node.getTextContent());
        }

        return response;
    }

    /**
     * Deletes network device.
     *
     * @param networkDeviceId Id of network device to delete
     * @return
     * @throws Exception
     */
    public DeleteNetworkDeviceResponse listF5LoadBalancerNetworks(String networkDeviceId
    )
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listF5LoadBalancerNetworks", null);
        arguments.add(new NameValuePair("id", networkDeviceId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteNetworkDeviceResponse(responseDocument);
    }

    /**
     * Converts XML document into ArchiveEventsResponse object
     *
     * @param doc
     * @return
     */
    private DeleteNetworkDeviceResponse getDeleteNetworkDeviceResponse(Document doc) {
        DeleteNetworkDeviceResponse response = new DeleteNetworkDeviceResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Network Device
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Network Devicee 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * ists network that are using a F5 load balancer device
     *
     * @param loadBalancerDeviceId f5 load balancer device ID
     * @param optional
     * @return
     * @throws Exception
     */
    public ListF5LoadBalancerNetworksResponse listF5LoadBalancerNetworks(String loadBalancerDeviceId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listF5LoadBalancerNetworks", optional);
        arguments.add(new NameValuePair("lbdeviceid", loadBalancerDeviceId));

        Document responseDocument = server.makeRequest(arguments);

        return getListF5LoadBalancerNetworksResponse(responseDocument);
    }

    /**
     * Converts XML document into ListF5LoadBalancerNetworksResponse object
     *
     * @param doc
     * @return
     */
    private ListF5LoadBalancerNetworksResponse getListF5LoadBalancerNetworksResponse(Document doc) {
        ListF5LoadBalancerNetworksResponse response = new ListF5LoadBalancerNetworksResponse();

        // get Id from XML and set the id of the network 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get account from XML and set	the owner of the network
        list = doc.getElementsByTagName("availability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAccount(node.getTextContent());
        }

        // get acltype from XML and set	acl type - access type to the network
        list = doc.getElementsByTagName("acltype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAclType(node.getTextContent());
        }

        // get broadcastdomaintype from XML and set	Broadcast domain type of the network
        list = doc.getElementsByTagName("broadcastdomaintype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkBroadcastDomainType(node.getTextContent());
        }

        // get broadcasturi from XML and set broadcast uri of the network
        list = doc.getElementsByTagName("broadcasturi");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkBroadcastUri(node.getTextContent());
        }

        // get cidr from XML and set cidr of the network
        list = doc.getElementsByTagName("cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkCidr(node.getTextContent());
        }

        // get displaytext from XML and set displaytext of the network
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDisplayText(node.getTextContent());
        }

        // get dns1 from XML and set dns1 of the network
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDnsFirst(node.getTextContent());
        }

        // get dns2 from XML and set dns2 of the network
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDnsSecond(node.getTextContent());
        }

        // get domain from XML and set the domain name of the network owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOwnerDomain(node.getTextContent());
        }

        // get domainid from XML and set the domain id of the network owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOwnerDomainId(node.getTextContent());
        }

        // get gateway from XML and set the network's gateway
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkGateway(node.getTextContent());
        }

        // get isdefault from XML and set true if network is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkIsDefault(node.getTextContent());
        }

        // get issystem from XML and set true if network is system, false otherwise
        list = doc.getElementsByTagName("issystem");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkIsSystem(node.getTextContent());
        }

        // get name from XML and set the name of the network
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkName(node.getTextContent());
        }

        // get netmask from XML and set the netmask of the network
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkNetmask(node.getTextContent());
        }

        // get networkdomain from XML and set the networkdomain of the network
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get networkofferingavailability from XML and set availability of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingavailability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingAvailabilit(node.getTextContent());
        }

        // get networkofferingdisplaytext from XML and set display text of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingDisplayText(node.getTextContent());
        }

        // get networkofferingid from XML and set network offering id the network is created from
        list = doc.getElementsByTagName("networkofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingId(node.getTextContent());
        }

        // get networkofferingname from XML and set	name of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingName(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network id
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get project from XML and set the project name of the address
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAddressProjectName(node.getTextContent());
        }

        // get projectid from XML and set the project id of the address
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAddressProjectId(node.getTextContent());
        }

        // get related from XML and set related to what other network configuration
        list = doc.getElementsByTagName("related");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRelated(node.getTextContent());
        }

        // get restartrequired from XML and set true network requires restart
        list = doc.getElementsByTagName("restartrequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRestartRequired(node.getTextContent());
        }

        // get specifyipranges from XML and set true if network supports specifying ip ranges, false otherwise
        list = doc.getElementsByTagName("specifyipranges");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkSpecifyIpRanges(node.getTextContent());
        }

        // get state from XML and set state of the network
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkState(node.getTextContent());
        }

        // get subdomainaccess from XML and set subdomainaccess of the network
        list = doc.getElementsByTagName("subdomainaccess");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkSubdomainAccess(node.getTextContent());
        }

        // get traffictype from XML and set traffictype of the network
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTrafficType(node.getTextContent());
        }

        // get type from XML and set type of the network
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkType(node.getTextContent());
        }

        // get vlan from XML and set vlan of the network
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkVlan(node.getTextContent());
        }

        // get zoneid from XML and set zoneid of the network
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkZoneId(node.getTextContent());
        }

        // get zonename from XML and set zonename of the network
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkZoneName(node.getTextContent());
        }

        //the list of supported services
        list = doc.getElementsByTagName("service");
        if (list.getLength() > 0) {
            List<ServiceResponse> services = new LinkedList<ServiceResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node serviceNode = list.item(index);
                ServiceResponse service = new ServiceResponse();

                NodeList serviceProperties = serviceNode.getChildNodes();
                for (int childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        service.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                        service.setServiceProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("capability")) {
                        list = doc.getElementsByTagName("capability");
                        if (list.getLength() > 0) {
                            List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                            for (index = 0; index < list.getLength(); index++) {
                                Node capabilityNode = list.item(index);
                                CapabilityResponse capability = new CapabilityResponse();

                                NodeList capabilityProperties = capabilityNode.getChildNodes();
                                for (childIndex = 0; childIndex < capabilityProperties.getLength(); childIndex++) {
                                    property = list.item(childIndex);

                                    if (property.getNodeName().equals("canchooseservicecapability")) {
                                        capability.setCanChooseServiceCapability(property.getTextContent());
                                    } else if (property.getNodeName().equals("name")) {
                                        capability.setName(property.getTextContent());
                                    } else if (property.getNodeName().equals("value")) {
                                        capability.setValue(property.getTextContent());
                                    }

                                }

                                capabilities.add(capability);
                                response.setCapabilities(capabilities);
                            }

                        }
                    }

                    services.add(service);
                    response.setServices(services);
                }
            }
        }

        return response;
    }

    /**
     * lists network that are using SRX firewall device
     *
     * @param netscalerLoadBalancerDeviceId netscaler load balancer device ID
     * @param optional
     * @return
     * @throws Exception
     */
    public ListSrxFirewallNetworksResponse listSrxFirewallNetworks(String netscalerLoadBalancerDeviceId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listSrxFirewallNetworks", optional);
        arguments.add(new NameValuePair("lbdeviceid", netscalerLoadBalancerDeviceId));

        Document responseDocument = server.makeRequest(arguments);

        return getListSrxFirewallNetworksResponse(responseDocument);
    }

    /**
     * Converts XML document into listSrxFirewallNetworks object
     *
     * @param doc
     * @return
     */
    private ListSrxFirewallNetworksResponse getListSrxFirewallNetworksResponse(Document doc) {
        ListSrxFirewallNetworksResponse response = new ListSrxFirewallNetworksResponse();

        // get Id from XML and set the id of the network 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get account from XML and set	the owner of the network
        list = doc.getElementsByTagName("availability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAccount(node.getTextContent());
        }

        // get acltype from XML and set	acl type - access type to the network
        list = doc.getElementsByTagName("acltype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAclType(node.getTextContent());
        }

        // get broadcastdomaintype from XML and set	Broadcast domain type of the network
        list = doc.getElementsByTagName("broadcastdomaintype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkBroadcastDomainType(node.getTextContent());
        }

        // get broadcasturi from XML and set broadcast uri of the network
        list = doc.getElementsByTagName("broadcasturi");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkBroadcastUri(node.getTextContent());
        }

        // get cidr from XML and set cidr of the network
        list = doc.getElementsByTagName("cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkCidr(node.getTextContent());
        }

        // get displaytext from XML and set displaytext of the network
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDisplayText(node.getTextContent());
        }

        // get dns1 from XML and set dns1 of the network
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDnsFirst(node.getTextContent());
        }

        // get dns2 from XML and set dns2 of the network
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDnsSecond(node.getTextContent());
        }

        // get domain from XML and set the domain name of the network owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOwnerDomain(node.getTextContent());
        }

        // get domainid from XML and set the domain id of the network owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOwnerDomainId(node.getTextContent());
        }

        // get gateway from XML and set the network's gateway
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkGateway(node.getTextContent());
        }

        // get isdefault from XML and set true if network is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkIsDefault(node.getTextContent());
        }

        // get issystem from XML and set true if network is system, false otherwise
        list = doc.getElementsByTagName("issystem");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkIsSystem(node.getTextContent());
        }

        // get name from XML and set the name of the network
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkName(node.getTextContent());
        }

        // get netmask from XML and set the netmask of the network
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkNetmask(node.getTextContent());
        }

        // get networkdomain from XML and set the networkdomain of the network
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get networkofferingavailability from XML and set availability of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingavailability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingAvailabilit(node.getTextContent());
        }

        // get networkofferingdisplaytext from XML and set display text of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingDisplayText(node.getTextContent());
        }

        // get networkofferingid from XML and set network offering id the network is created from
        list = doc.getElementsByTagName("networkofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingId(node.getTextContent());
        }

        // get networkofferingname from XML and set	name of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingName(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network id
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get project from XML and set the project name of the address
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAddressProjectName(node.getTextContent());
        }

        // get projectid from XML and set the project id of the address
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAddressProjectId(node.getTextContent());
        }

        // get related from XML and set related to what other network configuration
        list = doc.getElementsByTagName("related");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRelated(node.getTextContent());
        }

        // get restartrequired from XML and set true network requires restart
        list = doc.getElementsByTagName("restartrequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRestartRequired(node.getTextContent());
        }

        // get specifyipranges from XML and set true if network supports specifying ip ranges, false otherwise
        list = doc.getElementsByTagName("specifyipranges");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkSpecifyIpRanges(node.getTextContent());
        }

        // get state from XML and set state of the network
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkState(node.getTextContent());
        }

        // get subdomainaccess from XML and set subdomainaccess of the network
        list = doc.getElementsByTagName("subdomainaccess");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkSubdomainAccess(node.getTextContent());
        }

        // get traffictype from XML and set traffictype of the network
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTrafficType(node.getTextContent());
        }

        // get type from XML and set type of the network
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkType(node.getTextContent());
        }

        // get vlan from XML and set vlan of the network
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkVlan(node.getTextContent());
        }

        // get zoneid from XML and set zoneid of the network
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkZoneId(node.getTextContent());
        }

        // get zonename from XML and set zonename of the network
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkZoneName(node.getTextContent());
        }

        //the list of supported services
        list = doc.getElementsByTagName("service");
        if (list.getLength() > 0) {
            List<ServiceResponse> services = new LinkedList<ServiceResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node serviceNode = list.item(index);
                ServiceResponse service = new ServiceResponse();

                NodeList serviceProperties = serviceNode.getChildNodes();
                for (int childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        service.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                        service.setServiceProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("capability")) {
                        list = doc.getElementsByTagName("capability");
                        if (list.getLength() > 0) {
                            List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                            for (index = 0; index < list.getLength(); index++) {
                                Node capabilityNode = list.item(index);
                                CapabilityResponse capability = new CapabilityResponse();

                                NodeList capabilityProperties = capabilityNode.getChildNodes();
                                for (childIndex = 0; childIndex < capabilityProperties.getLength(); childIndex++) {
                                    property = list.item(childIndex);;

                                    if (property.getNodeName().equals("canchooseservicecapability")) {
                                        capability.setCanChooseServiceCapability(property.getTextContent());
                                    } else if (property.getNodeName().equals("name")) {
                                        capability.setName(property.getTextContent());
                                    } else if (property.getNodeName().equals("value")) {
                                        capability.setValue(property.getTextContent());
                                    }

                                }

                                capabilities.add(capability);
                                response.setCapabilities(capabilities);
                            }

                        }
                    }

                    services.add(service);
                    response.setServices(services);
                }
            }
        }

        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for network.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public NetworkJobResultResponse networkJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getNetworkJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into NetworkJobResultResponse object
     *
     * @param doc
     * @return
     */
    private NetworkJobResultResponse getNetworkJobResultResponse(Document doc) {
        NetworkJobResultResponse response = new NetworkJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("network")) {
                    List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("allocated")) {
                            response.setAllocated(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("associatednetworkid")) {
                            response.setAssociatedNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("associatednetworkname")) {
                            response.setAssociatedNetworkName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("forvirtualnetwork")) {
                            response.setForVirtualNetwork(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ipaddress")) {
                            response.setIpAddress(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("isportable")) {
                            response.setIsPortable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("issourcenat")) {
                            response.setIsSourceNat(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("issystem")) {
                            response.setIsSystem(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkid")) {
                            response.setNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("physicalnetworkid")) {
                            response.setPhysicalNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("purpose")) {
                            response.setPurpose(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("virtualmachinedisplayname")) {
                            response.setVirtualMachineDisplayName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("virtualmachineid")) {
                            response.setVirtualMachineId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("virtualmachinename")) {
                            response.setVirtualMachineName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vlanid")) {
                            response.setVlanId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zonename")) {
                            response.setZoneName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("tags")) {
                            NodeList tagsProperties = childNodeProperty.getChildNodes();
                            if (tagsProperties.getLength() > 0) {
                                TagsResponse tags = new TagsResponse();
                                for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                    Node tagsProperty = tagsProperties.item(tagsIndex);

                                    if (tagsProperty.getNodeName().equals("account")) {
                                        tags.setAccount(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("customer")) {
                                        tags.setCustomer(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("domain")) {
                                        tags.setDomain(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                                        tags.setDomainId(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("key")) {
                                        tags.setKey(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("project")) {
                                        tags.setProject(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                                        tags.setProjectId(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                                        tags.setResourceId(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                                        tags.setResourceType(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("value")) {
                                        tags.setValue(tagsProperty.getTextContent());
                                    }

                                }
                                tagss.add(tags);
                            }
                            response.setTagss(tagss);
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
     * Retrieves the current status of asynchronous job for physicalNetwork.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public PhysicalNetworkJobResultResponse physicalNetworkJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getPhysicalNetworkJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into PhysicalNetworkJobResultResponse object
     *
     * @param doc
     * @return
     */
    private PhysicalNetworkJobResultResponse getPhysicalNetworkJobResultResponse(Document doc) {
        PhysicalNetworkJobResultResponse response = new PhysicalNetworkJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("physicalnetwork")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("broadcastdomainrange")) {
                            response.setBroadcastDomainRange(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("isolationmethods")) {
                            response.setIsolationMethods(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkspeed")) {
                            response.setNetworkSpeed(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("tags")) {
                            response.setTags(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vlan")) {
                            response.setVlan(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
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
     * Retrieves the current status of asynchronous job for networkServiceProvider.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public NetworkServiceProviderJobResultResponse networkServiceProviderJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getNetworkServiceProviderJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into NetworkServiceProviderJobResultResponse object
     *
     * @param doc
     * @return
     */
    private NetworkServiceProviderJobResultResponse getNetworkServiceProviderJobResultResponse(Document doc) {
        NetworkServiceProviderJobResultResponse response = new NetworkServiceProviderJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("networkserviceprovider")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("canenableindividualservice")) {
                            response.setCanEnableIndividualService(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("destinationphysicalnetworkid")) {
                            response.setDestinationPhysicalNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("physicalnetworkid")) {
                            response.setPhysicalNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("servicelist")) {
                            response.setServiceList(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
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
     * Retrieves the current status of asynchronous job for storageNetworkIpRange.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public StorageNetworkIpRangeJobResultResponse storageNetworkIpRangeJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getStorageNetworkIpRangeJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into StorageNetworkIpRangeJobResultResponse object
     *
     * @param doc
     * @return
     */
    private StorageNetworkIpRangeJobResultResponse getStorageNetworkIpRangeJobResultResponse(Document doc) {
        StorageNetworkIpRangeJobResultResponse response = new StorageNetworkIpRangeJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("networkserviceprovider")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("endip")) {
                            response.setEndIp(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("gateway")) {
                            response.setGateway(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("netmask")) {
                            response.setNetmask(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkid")) {
                            response.setNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("podid")) {
                            response.setPodId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("startip")) {
                            response.setStartIp(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vlan")) {
                            response.setVlan(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
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
     * lists network that are using a netscaler load balancer device
     *
     * @param netscalerLoadBalancerDeviceId
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNetscalerLoadBalancerNetworksResponse listNetscalerLoadBalancerNetworks(String netscalerLoadBalancerDeviceId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNetscalerLoadBalancerNetworks", optional);
        arguments.add(new NameValuePair("lbdeviceid", netscalerLoadBalancerDeviceId));

        Document responseDocument = server.makeRequest(arguments);

        return getListNetscalerLoadBalancerNetworksResponse(responseDocument);
    }

    /**
     * Converts XML document into listSrxFirewallNetworks object
     *
     * @param doc
     * @return
     */
    private ListNetscalerLoadBalancerNetworksResponse getListNetscalerLoadBalancerNetworksResponse(Document doc) {
        ListNetscalerLoadBalancerNetworksResponse response = new ListNetscalerLoadBalancerNetworksResponse();

        // get Id from XML and set the id of the network 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get account from XML and set	the owner of the network
        list = doc.getElementsByTagName("availability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAccount(node.getTextContent());
        }

        // get acltype from XML and set	acl type - access type to the network
        list = doc.getElementsByTagName("acltype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAclType(node.getTextContent());
        }

        // get broadcastdomaintype from XML and set	Broadcast domain type of the network
        list = doc.getElementsByTagName("broadcastdomaintype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkBroadcastDomainType(node.getTextContent());
        }

        // get broadcasturi from XML and set broadcast uri of the network
        list = doc.getElementsByTagName("broadcasturi");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkBroadcastUri(node.getTextContent());
        }

        // get cidr from XML and set cidr of the network
        list = doc.getElementsByTagName("cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkCidr(node.getTextContent());
        }

        // get displaytext from XML and set displaytext of the network
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDisplayText(node.getTextContent());
        }

        // get dns1 from XML and set dns1 of the network
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDnsFirst(node.getTextContent());
        }

        // get dns2 from XML and set dns2 of the network
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDnsSecond(node.getTextContent());
        }

        // get domain from XML and set the domain name of the network owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOwnerDomain(node.getTextContent());
        }

        // get domainid from XML and set the domain id of the network owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOwnerDomainId(node.getTextContent());
        }

        // get gateway from XML and set the network's gateway
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkGateway(node.getTextContent());
        }

        // get isdefault from XML and set true if network is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkIsDefault(node.getTextContent());
        }

        // get issystem from XML and set true if network is system, false otherwise
        list = doc.getElementsByTagName("issystem");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkIsSystem(node.getTextContent());
        }

        // get name from XML and set the name of the network
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkName(node.getTextContent());
        }

        // get netmask from XML and set the netmask of the network
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkNetmask(node.getTextContent());
        }

        // get networkdomain from XML and set the networkdomain of the network
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get networkofferingavailability from XML and set availability of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingavailability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingAvailabilit(node.getTextContent());
        }

        // get networkofferingdisplaytext from XML and set display text of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingDisplayText(node.getTextContent());
        }

        // get networkofferingid from XML and set network offering id the network is created from
        list = doc.getElementsByTagName("networkofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingId(node.getTextContent());
        }

        // get networkofferingname from XML and set	name of the network offering the network is created from
        list = doc.getElementsByTagName("networkofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkOfferingName(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network id
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get project from XML and set the project name of the address
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAddressProjectName(node.getTextContent());
        }

        // get projectid from XML and set the project id of the address
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAddressProjectId(node.getTextContent());
        }

        // get related from XML and set related to what other network configuration
        list = doc.getElementsByTagName("related");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRelated(node.getTextContent());
        }

        // get restartrequired from XML and set true network requires restart
        list = doc.getElementsByTagName("restartrequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRestartRequired(node.getTextContent());
        }

        // get specifyipranges from XML and set true if network supports specifying ip ranges, false otherwise
        list = doc.getElementsByTagName("specifyipranges");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkSpecifyIpRanges(node.getTextContent());
        }

        // get state from XML and set state of the network
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkState(node.getTextContent());
        }

        // get subdomainaccess from XML and set subdomainaccess of the network
        list = doc.getElementsByTagName("subdomainaccess");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkSubdomainAccess(node.getTextContent());
        }

        // get traffictype from XML and set traffictype of the network
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTrafficType(node.getTextContent());
        }

        // get type from XML and set type of the network
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkType(node.getTextContent());
        }

        // get vlan from XML and set vlan of the network
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkVlan(node.getTextContent());
        }

        // get zoneid from XML and set zoneid of the network
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkZoneId(node.getTextContent());
        }

        // get zonename from XML and set zonename of the network
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkZoneName(node.getTextContent());
        }

        //the list of supported services
        list = doc.getElementsByTagName("service");
        if (list.getLength() > 0) {
            List<ServiceResponse> services = new LinkedList<ServiceResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node serviceNode = list.item(index);
                ServiceResponse service = new ServiceResponse();

                NodeList serviceProperties = serviceNode.getChildNodes();
                for (int childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        service.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                        service.setServiceProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("capability")) {
                        list = doc.getElementsByTagName("capability");
                        if (list.getLength() > 0) {
                            List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                            for (index = 0; index < list.getLength(); index++) {
                                Node capabilityNode = list.item(index);
                                CapabilityResponse capability = new CapabilityResponse();

                                NodeList capabilityProperties = capabilityNode.getChildNodes();
                                for (childIndex = 0; childIndex < capabilityProperties.getLength(); childIndex++) {
                                    property = list.item(childIndex);;

                                    if (property.getNodeName().equals("canchooseservicecapability")) {
                                        capability.setCanChooseServiceCapability(property.getTextContent());
                                    } else if (property.getNodeName().equals("name")) {
                                        capability.setName(property.getTextContent());
                                    } else if (property.getNodeName().equals("value")) {
                                        capability.setValue(property.getTextContent());
                                    }

                                }

                                capabilities.add(capability);
                                response.setCapabilities(capabilities);
                            }

                        }
                    }

                    services.add(service);
                    response.setServices(services);
                }
            }
        }

        return response;
    }
}
