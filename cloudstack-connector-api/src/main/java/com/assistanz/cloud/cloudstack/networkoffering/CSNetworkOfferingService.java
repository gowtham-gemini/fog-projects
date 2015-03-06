package com.assistanz.cloud.cloudstack.networkoffering;

import com.assistanz.cloud.cloudstack.CapabilityResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.ProviderResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 * *
 *
 * @author Santhosh
 *
 */
public class CSNetworkOfferingService {

    private CloudStackServer server;

    public CSNetworkOfferingService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates a network offering.
     *
     * @param displayText the display text of the network offering
     * @param guestIpType Guest type of the network offering: Shared or Isolated
     * @param networkOfferingName the name of the network offering
     * @param supportedServices services supported by the network offering
     * @param trafficType the traffic type for the network offering
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateNetworkOfferingResponse createNetworkOffering(String displayText,
            String guestIpType, String networkOfferingName, String supportedServices,
            String trafficType, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createNetworkOffering", optional);
        arguments.add(new NameValuePair("displaytext", displayText));
        arguments.add(new NameValuePair("guestiptype", guestIpType));
        arguments.add(new NameValuePair("name", networkOfferingName));
        arguments.add(new NameValuePair("supportedservices", supportedServices));
        arguments.add(new NameValuePair("traffictype", trafficType));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateNetworkOfferingResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateZoneResponse object
     *
     * @param doc
     * @return
     */
    private CreateNetworkOfferingResponse getCreateNetworkOfferingResponse(Document doc) {
        CreateNetworkOfferingResponse response = new CreateNetworkOfferingResponse();

        // get the id of the network offering    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get availability of the network offering      
        list = doc.getElementsByTagName("availability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAvailability(node.getTextContent());
        }

        // get true if network offering is ip conserve mode enabled      
        list = doc.getElementsByTagName("conservemode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setConserveMode(node.getTextContent());
        }

        // get the date this network offering was created   
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get additional key/value details tied with network offering   
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get displaytext from XML and set the displaytext of the zone      
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get true if network offering supports persistent networks, false otherwise    
        list = doc.getElementsByTagName("egressdefaultpolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEgressDefaultPolicy(node.getTextContent());
        }

        // get true if network offering can be used by VPC networks only      
        list = doc.getElementsByTagName("forvpc");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setForVpc(node.getTextContent());
        }

        // get guestiptype from XML and set guest type of the network offering, can be Shared or Isolated      
        list = doc.getElementsByTagName("guestiptype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestIpType(node.getTextContent());
        }

        // get true if network offering is default, false otherwise      
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // get true if network offering supports persistent networks, false otherwise      
        list = doc.getElementsByTagName("ispersistent");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPersistent(node.getTextContent());
        }

        // get maximum number of concurrents connections to be handled by lb      
        list = doc.getElementsByTagName("maxconnections");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxConnections(node.getTextContent());
        }

        // get the name of the network offering     
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get data transfer rate in megabits per second allowed.    
        list = doc.getElementsByTagName("networkrate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRate(node.getTextContent());
        }

        // get the ID of the service offering used by virtual router provider     
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get true if network offering supports specifying ip ranges, false otherwise      
        list = doc.getElementsByTagName("specifyipranges");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSpecifyIpRanges(node.getTextContent());
        }

        // get true if network offering supports vlans, false otherwise      
        list = doc.getElementsByTagName("specifyvlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSpecifyVlan(node.getTextContent());
        }

        // get state of the network offering. Can be Disabled/Enabled/Inactive      
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get the tags for the network offering      
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get the traffic type for the network offering      
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTrafficType(node.getTextContent());
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
                                property = capabilityProperties.item(capabilityIndex);

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

                    } else if (property.getNodeName().equals("provider")) {
                        NodeList providerProperties = property.getChildNodes();
                        if (providerProperties.getLength() > 0) {
                            ProviderResponse provider = new ProviderResponse();
                            for (int providerIndex = 0; providerIndex < providerProperties.getLength(); providerIndex++) {
                                property = providerProperties.item(providerIndex);

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
        return response;

    }

    /**
     * Updates a network offering.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateNetworkOfferingResponse updateNetworkOffering(HashMap<String, String> optional)
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

        // get the id of the network offering    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get availability of the network offering      
        list = doc.getElementsByTagName("availability");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAvailability(node.getTextContent());
        }

        // get true if network offering is ip conserve mode enabled      
        list = doc.getElementsByTagName("conservemode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setConserveMode(node.getTextContent());
        }

        // get the date this network offering was created   
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get additional key/value details tied with network offering   
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get displaytext from XML and set the displaytext of the zone      
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get true if network offering supports persistent networks, false otherwise    
        list = doc.getElementsByTagName("egressdefaultpolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEgressDefaultPolicy(node.getTextContent());
        }

        // get true if network offering can be used by VPC networks only      
        list = doc.getElementsByTagName("forvpc");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setForVpc(node.getTextContent());
        }

        // get guestiptype from XML and set guest type of the network offering, can be Shared or Isolated      
        list = doc.getElementsByTagName("guestiptype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestIpType(node.getTextContent());
        }

        // get true if network offering is default, false otherwise      
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // get true if network offering supports persistent networks, false otherwise      
        list = doc.getElementsByTagName("ispersistent");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPersistent(node.getTextContent());
        }

        // get maximum number of concurrents connections to be handled by lb      
        list = doc.getElementsByTagName("maxconnections");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxConnections(node.getTextContent());
        }

        // get the name of the network offering     
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get data transfer rate in megabits per second allowed.    
        list = doc.getElementsByTagName("networkrate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRate(node.getTextContent());
        }

        // get the ID of the service offering used by virtual router provider     
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get true if network offering supports specifying ip ranges, false otherwise      
        list = doc.getElementsByTagName("specifyipranges");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSpecifyIpRanges(node.getTextContent());
        }

        // get true if network offering supports vlans, false otherwise      
        list = doc.getElementsByTagName("specifyvlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSpecifyVlan(node.getTextContent());
        }

        // get state of the network offering. Can be Disabled/Enabled/Inactive      
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get the tags for the network offering      
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get the traffic type for the network offering      
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTrafficType(node.getTextContent());
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
                                property = capabilityProperties.item(capabilityIndex);

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

                    } else if (property.getNodeName().equals("provider")) {
                        NodeList providerProperties = property.getChildNodes();
                        if (providerProperties.getLength() > 0) {
                            ProviderResponse provider = new ProviderResponse();
                            for (int providerIndex = 0; providerIndex < providerProperties.getLength(); providerIndex++) {
                                property = providerProperties.item(providerIndex);

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
        return response;

    }

    /**
     * Deletes a network offering.
     *
     * @param networkOfferingId
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

        // any text associated with the success or failure      
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // true if operation is executed successfully
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
    public ListNetworkOfferingsResponse listNetworkOfferings(HashMap<String, String> optional) throws Exception {

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

        // Lists all available network offerings
        NodeList list = doc.getElementsByTagName("networkoffering");
        List<NetworkOfferingResponse> networkOfferings = new LinkedList<NetworkOfferingResponse>();

        if (list.getLength() > 0) {
            for (int NetworkOfferingIndex = 0; NetworkOfferingIndex < list.getLength(); NetworkOfferingIndex++) {
                Node networkOfferingNode = list.item(NetworkOfferingIndex);

                NetworkOfferingResponse networkOffering = new NetworkOfferingResponse();

                NodeList networkOfferingProperties = networkOfferingNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkOfferingProperties.getLength(); childIndex++) {
                    Node property = networkOfferingProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        networkOffering.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("availability")) {
                        networkOffering.setAvailability(property.getTextContent());
                    } else if (property.getNodeName().equals("conservemode")) {
                        networkOffering.setConserveMode(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        networkOffering.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("details")) {
                        networkOffering.setDetails(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                        networkOffering.setDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("egressdefaultpolicy")) {
                        networkOffering.setEgressDefaultPolicy(property.getTextContent());
                    } else if (property.getNodeName().equals("forvpc")) {
                        networkOffering.setForVpc(property.getTextContent());
                    } else if (property.getNodeName().equals("guestiptype")) {
                        networkOffering.setGuestIpType(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        networkOffering.setIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("ispersistent")) {
                        networkOffering.setIsPersistent(property.getTextContent());
                    } else if (property.getNodeName().equals("maxconnections")) {
                        networkOffering.setMaxConnections(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        networkOffering.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkrate")) {
                        networkOffering.setNetworkRate(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingid")) {
                        networkOffering.setServiceOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("specifyipranges")) {
                        networkOffering.setSpecifyIpRanges(property.getTextContent());
                    } else if (property.getNodeName().equals("specifyvlan")) {
                        networkOffering.setSpecifyVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        networkOffering.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        networkOffering.setTags(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                        networkOffering.setTrafficType(property.getTextContent());
                    } else if (property.getNodeName().equals("service")) {
                        list = doc.getElementsByTagName("service");
                        if (list.getLength() > 0) {
                            List<ServiceResponse> services = new LinkedList<ServiceResponse>();

                            for (int index = 0; index < list.getLength(); index++) {

                                Node serviceNode = list.item(index);
                                ServiceResponse service = new ServiceResponse();
                                List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                                List<ProviderResponse> providers = new LinkedList<ProviderResponse>();

                                NodeList serviceProperties = serviceNode.getChildNodes();
                                for (childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                                    property = list.item(childIndex);

                                    if (property.getNodeName().equals("name")) {
                                        service.setName(property.getTextContent());
                                        services.add(service);
                                    } else if (property.getNodeName().equals("capability")) {
                                        NodeList capabilityProperties = property.getChildNodes();
                                        if (list.getLength() > 0) {
                                            CapabilityResponse capability = new CapabilityResponse();
                                            for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
                                                property = capabilityProperties.item(capabilityIndex);

                                                if (property.getNodeName().equals("canchooseservicecapability")) {
                                                    capability.setCanChooseServiceCapability(property.getTextContent());
                                                } else if (property.getNodeName().equals("name")) {
                                                    capability.setName(property.getTextContent());
                                                } else if (property.getNodeName().equals("value")) {
                                                    capability.setValue(property.getTextContent());
                                                }

                                            }

                                            capabilities.add(capability);

                                        }
                                    } else if (property.getNodeName().equals("provider")) {
                                        NodeList providerProperties = property.getChildNodes();
                                        if (list.getLength() > 0) {
                                            ProviderResponse provider = new ProviderResponse();
                                            for (int providerIndex = 0; providerIndex < providerProperties.getLength(); providerIndex++) {
                                                property = providerProperties.item(providerIndex);

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

                                        }
                                    }
                                }

                                service.setCapabilities(capabilities);
                                service.setProviders(providers);
                                services.add(service);
                            }
                            networkOffering.setServices(services);
                            networkOfferings.add(networkOffering);
                        }
                    }
                }

            }
        }
        response.setNetworkOfferings(networkOfferings);
        return response;
    }

}
