package com.assistanz.cloud.cloudstack.network;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.ServiceResponse;
import org.w3c.dom.Element;

/**
 * 
 * @author Gowtham
 *
 */
public class CSNetworkService {
	
	private CloudStackServer server;
    
    public CSNetworkService(CloudStackServer server) {
        this.server = server;
    }
    
    /**
     * Creates a network offering
     * 
     * @param networkOfferingDisplayText the display text of the network offering
     * @param networkOfferingGuestIpType guest type of the network offering: Shared or Isolated
     * @param networkOfferingName the name of the network offering
     * @param networkOfferingSupportedServices services supported by the network offering
     * @param networkOfferingTrafficType the traffic type for the network offering. Supported type in current release is GUEST only
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateNetworkOfferingResponse createNetworkOffering(String networkOfferingDisplayText,
    		String networkOfferingGuestIpType, String networkOfferingName,
    		String networkOfferingSupportedServices, String networkOfferingTrafficType, HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createNetworkOffering", optional);
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
                for (int childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("name")) {
                    	service.setServiceName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                    	service.setServiceProvider(property.getTextContent());
                    }  else if (property.getNodeName().equals("capability")){
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
	                                	 capability.setCapabilityName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("value")) {
	                                	 capability.setCapabilityValue(property.getTextContent());
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
     * Updates a network offering.
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateNetworkOfferingResponse updateNetworkOffering(
    		HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateNetworkOffering", optional);
       
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
                for (int childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("name")) {
                    	service.setServiceName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                    	service.setServiceProvider(property.getTextContent());
                    }  else if (property.getNodeName().equals("capability")){
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
	                                	 capability.setCapabilityName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("value")) {
	                                	 capability.setCapabilityValue(property.getTextContent());
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
     * Deletes a network offering.
     * 
     * @param networkOfferingId the ID of the network offering
     * @return
     * @throws Exception
     */
	public DeleteNetworkOfferingResponse deleteNetworkOffering(String networkOfferingId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteNetworkOffering", null);
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
    		HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listNetworkOfferings", optional);
       
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
                
//                if (networkOfferingNode == null) {
//                    continue;
//                }  
                
                NetworkOfferingResponse networkOffering = new NetworkOfferingResponse();
                List<ServiceResponse> services = new LinkedList<ServiceResponse>();             
                NodeList networkOfferingProperties = networkOfferingNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkOfferingProperties.getLength(); childIndex++) {
                    Node property = networkOfferingProperties.item(childIndex);
//                   if (property == null || property.getNodeName() == null) {
//                        continue;
//                    }
                    
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
                    }  else if (property.getNodeName().equals("service")) {
                        NodeList serviceProperties =  property.getChildNodes();
                        if (serviceProperties.getLength() > 0) { 
                            ServiceResponse service = new ServiceResponse(); 
                            List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>(); 
                            for (int serviceIndex = 0; serviceIndex < serviceProperties.getLength(); serviceIndex++) {
                                Node serviceProperty = serviceProperties.item(serviceIndex);                               
                                if (serviceProperty.getNodeName().equals("name")) {
                                    service.setServiceName(serviceProperty.getTextContent());
                                } else if (serviceProperty.getNodeName().equals("provider")) {
                                    Node providerProperties = serviceProperty.getFirstChild();
                                    service.setServiceProvider(providerProperties.getTextContent());
                                } else if (serviceProperty.getNodeName().equals("capability")){
                                    NodeList capabilityProperties = serviceProperty.getChildNodes();
                                    if (capabilityProperties.getLength() > 0) {
                                        CapabilityResponse capability = new CapabilityResponse();
                                        for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
                                            Node capabilityProperty = capabilityProperties.item(capabilityIndex);     
                                            if (capabilityProperty.getNodeName().equals("name")) {
                                                capability.setCapabilityName(capabilityProperty.getTextContent()); 
                                            } else if (capabilityProperty.getNodeName().equals("value")) {
                                                capability.setCapabilityValue(capabilityProperty.getTextContent());
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
     * @param displaytext the display text of the network
     * @param name the name of the network
     * @param networkofferingid the network offering id
     * @param zoneid the Zone ID for the network
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateNetworkResponse createNetwork(String displaytext,
            String name, String networkofferingid, String zoneid,
            HashMap<String,String> optional) 
            throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createNetwork", optional);
        arguments.add(new NameValuePair("displaytext", displaytext));
        arguments.add(new NameValuePair("name", name));
        arguments.add(new NameValuePair("networkofferingid", networkofferingid));
        arguments.add(new NameValuePair("zoneid", zoneid));

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
                    
//                    if (property.getNodeName().equals("name")) {
//                    	service.setServiceName(property.getTextContent());
//                    } else if (property.getNodeName().equals("provider")) {
//                    	service.setServiceProvider(property.getTextContent());
//                    }  else if (property.getNodeName().equals("capability")){
//            			list = doc.getElementsByTagName("capability");
//                    	if (list.getLength() > 0) {
//	                        List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();            
//	                        for (index = 0; index < list.getLength(); index++) {
//	                            Node capabilityNode = list.item(index);
//	                            CapabilityResponse capability = new CapabilityResponse();
//	                            
//	                            NodeList capabilityProperties = capabilityNode.getChildNodes();
//	                            for (childIndex = 0; childIndex < capabilityProperties.getLength(); childIndex++) {
//	                                 property = list.item(childIndex);;
//	                                
//	                                 if (property.getNodeName().equals("canchooseservicecapability")) {
//	                                	 capability.setCanChooseServiceCapability(property.getTextContent());
//	                                 } else if (property.getNodeName().equals("name")) {
//	                                	 capability.setCapabilityName(property.getTextContent());
//	                                 } else if (property.getNodeName().equals("value")) {
//	                                	 capability.setCapabilityValue(property.getTextContent());
//	                                 }
//	                                
//	                            } 
//		                        
//	                            capabilities.add(capability);
//		                        response.setCapabilities(capabilities);
//	                        }
//			                
//                    	}
//                    } 
                  
                
                    services.add(service);
                    response.setServices(services);
                }
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

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteNetwork", null);
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
    public ListNetworksResponse listNetworks(HashMap<String,String> optional) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("listNetworks", optional);

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
            for (int networkIndex = 0; networkIndex < list.getLength(); networkIndex++) {
                Node networkNode = list.item(networkIndex);
                                    
                NetworkResponse network = new NetworkResponse();
                List<ServiceResponse> services = new LinkedList<ServiceResponse>();             
                NodeList networkProperties = networkNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkProperties.getLength(); childIndex++) {
                    Node property = networkProperties.item(childIndex);
                
                    if (property.getNodeName().equals("id")) {
                        network.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                    	network.setNetworkAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	network.setNetworkName(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	network.setNetworkType(property.getTextContent());
                    } else if (property.getNodeName().equals("cidr")) {
                    	network.setNetworkCidr(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                    	network.setNetworkDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("networkofferingid")) {
                    	network.setNetworkOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                    	network.setNetworkGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	network.setNetworkZoneId(property.getTextContent());
                    }  else if (property.getNodeName().equals("state")) {
                    	network.setNetworkState(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                    	network.setNetworkNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("networkdomain")) {
                    	network.setNetworkDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                    	network.setNetworkTrafficType(property.getTextContent());
                    } else if (property.getNodeName().equals("ispersistent")) {
                    	network.setIsPersistent(property.getTextContent());
                    } else if (property.getNodeName().equals("restartrequired")) {
                    	network.setNetworkRestartRequired(property.getTextContent());
                    } else if (property.getNodeName().equals("aclid")) {
                    	network.setAclId(property.getTextContent());
                    } 
                }
                networks.add(network);
            }
            response.setNetworks(networks);
        }        
        
//    	// get Id from XML and set the id of the network 
//    	NodeList list = doc.getElementsByTagName("id");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkId(node.getTextContent());
//        }
//        
//        // get account from XML and set	the owner of the network
//        list = doc.getElementsByTagName("availability");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkAccount(node.getTextContent());
//        }
//        
//        // get acltype from XML and set	acl type - access type to the network
//        list = doc.getElementsByTagName("acltype");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkAclType(node.getTextContent());
//        }
//        
//        // get broadcastdomaintype from XML and set	Broadcast domain type of the network
//        list = doc.getElementsByTagName("broadcastdomaintype");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkBroadcastDomainType(node.getTextContent());
//        }
//        
//        // get broadcasturi from XML and set broadcast uri of the network
//        list = doc.getElementsByTagName("broadcasturi");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkBroadcastUri(node.getTextContent());
//        }
//        
//        // get cidr from XML and set cidr of the network
//        list = doc.getElementsByTagName("cidr");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkCidr(node.getTextContent());
//        }
//        
//        // get displaytext from XML and set displaytext of the network
//        list = doc.getElementsByTagName("displaytext");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkDisplayText(node.getTextContent());
//        }
//        
//        // get dns1 from XML and set dns1 of the network
//        list = doc.getElementsByTagName("dns1");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkDnsFirst(node.getTextContent());
//        }
//        
//        // get dns2 from XML and set dns2 of the network
//        list = doc.getElementsByTagName("dns2");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkDnsSecond(node.getTextContent());
//        }
//        
//        // get domain from XML and set the domain name of the network owner
//        list = doc.getElementsByTagName("domain");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkOwnerDomain(node.getTextContent());
//        }
//        
//        // get domainid from XML and set the domain id of the network owner
//        list = doc.getElementsByTagName("domainid");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkOwnerDomainId(node.getTextContent());
//        }
//        
//        // get gateway from XML and set the network's gateway
//        list = doc.getElementsByTagName("gateway");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkGateway(node.getTextContent());
//        }
//        
//        // get isdefault from XML and set true if network is default, false otherwise
//        list = doc.getElementsByTagName("isdefault");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkIsDefault(node.getTextContent());
//        }
//        
//        // get issystem from XML and set true if network is system, false otherwise
//        list = doc.getElementsByTagName("issystem");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkIsSystem(node.getTextContent());
//        }
//        
//        // get name from XML and set the name of the network
//        list = doc.getElementsByTagName("name");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkName(node.getTextContent());
//        }
//        
//        // get netmask from XML and set the netmask of the network
//        list = doc.getElementsByTagName("netmask");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkNetmask(node.getTextContent());
//        }
//        
//        // get networkdomain from XML and set the networkdomain of the network
//        list = doc.getElementsByTagName("networkdomain");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkDomain(node.getTextContent());
//        }
//        
//        // get networkofferingavailability from XML and set availability of the network offering the network is created from
//        list = doc.getElementsByTagName("networkofferingavailability");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkOfferingAvailabilit(node.getTextContent());
//        }
//        
//        // get networkofferingdisplaytext from XML and set display text of the network offering the network is created from
//        list = doc.getElementsByTagName("networkofferingdisplaytext");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkOfferingDisplayText(node.getTextContent());
//        }
//        
//        // get networkofferingid from XML and set network offering id the network is created from
//        list = doc.getElementsByTagName("networkofferingid");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkOfferingId(node.getTextContent());
//        }
//        
//        // get networkofferingname from XML and set	name of the network offering the network is created from
//        list = doc.getElementsByTagName("networkofferingname");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkOfferingName(node.getTextContent());
//        }
//        
//        // get physicalnetworkid from XML and set the physical network id
//        list = doc.getElementsByTagName("physicalnetworkid");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setPhysicalNetworkId(node.getTextContent());
//        }
//        
//        // get project from XML and set the project name of the address
//        list = doc.getElementsByTagName("project");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setAddressProjectName(node.getTextContent());
//        }
//        
//        // get projectid from XML and set the project id of the address
//        list = doc.getElementsByTagName("projectid");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setAddressProjectId(node.getTextContent());
//        }
//        
//        // get related from XML and set related to what other network configuration
//        list = doc.getElementsByTagName("related");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkRelated(node.getTextContent());
//        }
//        
//        // get restartrequired from XML and set true network requires restart
//        list = doc.getElementsByTagName("restartrequired");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkRestartRequired(node.getTextContent());
//        }
//        
//        // get specifyipranges from XML and set true if network supports specifying ip ranges, false otherwise
//        list = doc.getElementsByTagName("specifyipranges");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkSpecifyIpRanges(node.getTextContent());
//        }
//        
//        // get state from XML and set state of the network
//        list = doc.getElementsByTagName("state");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkState(node.getTextContent());
//        }
//        
//        // get subdomainaccess from XML and set subdomainaccess of the network
//        list = doc.getElementsByTagName("subdomainaccess");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkSubdomainAccess(node.getTextContent());
//        }
//        
//        // get traffictype from XML and set traffictype of the network
//        list = doc.getElementsByTagName("traffictype");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkTrafficType(node.getTextContent());
//        }
//        
//        // get type from XML and set type of the network
//        list = doc.getElementsByTagName("type");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkType(node.getTextContent());
//        }
//        
//        // get vlan from XML and set vlan of the network
//        list = doc.getElementsByTagName("vlan");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkVlan(node.getTextContent());
//        }
//        
//        // get zoneid from XML and set zoneid of the network
//        list = doc.getElementsByTagName("zoneid");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkZoneId(node.getTextContent());
//        }
//        
//        // get zonename from XML and set zonename of the network
//        list = doc.getElementsByTagName("zonename");
//        if (list.getLength() > 0) {
//            Node node = list.item(0);
//            response.setNetworkZoneName(node.getTextContent());
//        }
//                
//        //the list of supported services
//        list = doc.getElementsByTagName("service");
//        if (list.getLength() > 0) {
//            List<ServiceResponse> services = new LinkedList<ServiceResponse>();            
//            for (int index = 0; index < list.getLength(); index++) {
//                Node serviceNode = list.item(index);
//                ServiceResponse service = new ServiceResponse();
//                
//                
//                NodeList serviceProperties = serviceNode.getChildNodes();
//                for (int childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
//                    Node property = list.item(childIndex);
//                    
//                    if (property.getNodeName().equals("name")) {
//                    	service.setServiceName(property.getTextContent());
//                    } else if (property.getNodeName().equals("provider")) {
//                    	service.setServiceProvider(property.getTextContent());
//                    }  else if (property.getNodeName().equals("capability")){
//            			list = doc.getElementsByTagName("capability");
//                    	if (list.getLength() > 0) {
//	                        List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();            
//	                        for (index = 0; index < list.getLength(); index++) {
//	                            Node capabilityNode = list.item(index);
//	                            CapabilityResponse capability = new CapabilityResponse();
//	                            
//	                            
//	                            NodeList capabilityProperties = capabilityNode.getChildNodes();
//	                            for (childIndex = 0; childIndex < capabilityProperties.getLength(); childIndex++) {
//	                                 property = list.item(childIndex);;
//	                                
//	                                 if (property.getNodeName().equals("canchooseservicecapability")) {
//	                                	 capability.setCanChooseServiceCapability(property.getTextContent());
//	                                 } else if (property.getNodeName().equals("name")) {
//	                                	 capability.setCapabilityName(property.getTextContent());
//	                                 } else if (property.getNodeName().equals("value")) {
//	                                	 capability.setCapabilityValue(property.getTextContent());
//	                                 }
//	                                
//	                            } 
//		                        
//	                            capabilities.add(capability);
//		                        response.setCapabilities(capabilities);
//	                        }
//			                
//                    	}
//                    } 
//                  
//                
//                    services.add(service);
//                    response.setServices(services);
//                }
//            }
//        } 
        
        return response;
    } 
    
    /**
     * Restarts the network; 
     * includes 
     * 1) restarting network elements - virtual routers, dhcp servers 
     * 2) reapplying all public ips 
     * 3) reapplying loadBalancing/portForwarding rules
     * 
     * @param networkId the network id to restart
     * @param optional
     * @return
     * @throws Exception
     */
	public RestartNetworkResponse restartNetwork(String networkId,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("restartNetwork", optional);
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
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
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
                    	service.setServiceName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                    	service.setServiceProvider(property.getTextContent());
                    }  else if (property.getNodeName().equals("capability")){
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
	                                	 capability.setCapabilityName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("value")) {
	                                	 capability.setCapabilityValue(property.getTextContent());
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
     * Updates a network
     * 
     * @param networkId the ID of the network
     * @param optional
     * @return
     * @throws Exception
     */
	public UpdateNetworkResponse updateNetwork(String networkId,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("updateNetwork", optional);
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
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
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
                    	service.setServiceName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                    	service.setServiceProvider(property.getTextContent());
                    }  else if (property.getNodeName().equals("capability")){
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
	                                	 capability.setCapabilityName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("value")) {
	                                	 capability.setCapabilityValue(property.getTextContent());
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
     * Creates a physical network
     * 
     * @param physicalNetworkName the name of the physical network
     * @param physicalNetworkZoneId the Zone ID for the physical network
     * @param optional
     * @return
     * @throws Exception
     */
	public CreatePhysicalNetworkResponse createPhysicalNetwork(String physicalNetworkName,
			String physicalNetworkZoneId, HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("createPhysicalNetwork", optional);
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
            response.setPhysicalNetworkId(node.getTextContent());
        }
        
        // get broadcastdomainrange from XML and set Broadcast domain range of the physical network
        list = doc.getElementsByTagName("broadcastdomainrange");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkBroadcastDomainRange(node.getTextContent());
        }
        
        // get domainid from XML and set the domain id of the physical network owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkDomainId(node.getTextContent());
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
            response.setPhysicalNetworkName(node.getTextContent());
        }
        
        // get networkspeed from XML and set the networkspeed for physical network
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkSpeed(node.getTextContent());
        }
        
        // get state from XML and set the state for physical network
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkState(node.getTextContent());
        }
        
        // get tags from XML and set the tags for physical network
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkTags(node.getTextContent());
        }
        
        // get vlan from XML and set the vlan for physical network
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkVlan(node.getTextContent());
        }
        
        // get zoneid from XML and set the zoneid for physical network
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkZoneId(node.getTextContent());
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

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deletePhysicalNetwork", null);
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
                        physicalNetwork.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcastdomainrange")) {
                        physicalNetwork.setPhysicalNetworkBroadcastDomainRange(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        physicalNetwork.setPhysicalNetworkDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        physicalNetwork.setPhysicalNetworkDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("isolationmethods")) {
                        physicalNetwork.setIsolationMethods(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        physicalNetwork.setPhysicalNetworkName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkspeed")) {
                        physicalNetwork.setPhysicalNetworkSpeed(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        physicalNetwork.setPhysicalNetworkState(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        physicalNetwork.setPhysicalNetworkTags(property.getTextContent());
                    } else if (property.getNodeName().equals("vlan")) {
                        physicalNetwork.setPhysicalNetworkVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        physicalNetwork.setPhysicalNetworkZoneId(property.getTextContent());
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
			 HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("updatePhysicalNetwork", optional);
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
            response.setPhysicalNetworkId(node.getTextContent());
        }
        
        // get broadcastdomainrange from XML and set Broadcast domain range of the physical network
        list = doc.getElementsByTagName("broadcastdomainrange");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkBroadcastDomainRange(node.getTextContent());
        }
        
        // get domainid from XML and set the domain id of the physical network owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkDomainId(node.getTextContent());
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
            response.setPhysicalNetworkName(node.getTextContent());
        }
        
        // get networkspeed from XML and set the networkspeed for physical network
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkSpeed(node.getTextContent());
        }
        
        // get state from XML and set the state for physical network
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkState(node.getTextContent());
        }
        
        // get tags from XML and set the tags for physical network
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkTags(node.getTextContent());
        }
        
        // get vlan from XML and set the vlan for physical network
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkVlan(node.getTextContent());
        }
        
        // get zoneid from XML and set the zoneid for physical network
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkZoneId(node.getTextContent());
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
                     HashMap<String,String> optional) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("listSupportedNetworkServices", optional);

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
        
    	// get name from XML and set the service name 
    	NodeList list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceName(node.getTextContent());
        }
        
        // get provider from XML and set the service provider name
        list = doc.getElementsByTagName("provider");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceProvider(node.getTextContent());
        }
        
        list = doc.getElementsByTagName("capability");
    	if (list.getLength() > 0) {
            List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node capabilityNode = list.item(index);
                CapabilityResponse capability = new CapabilityResponse();
                
                
                NodeList capabilityProperties = capabilityNode.getChildNodes();
                for (int childIndex = 0; childIndex < capabilityProperties.getLength(); childIndex++) {
                     Node property = list.item(childIndex);;
                    
                     if (property.getNodeName().equals("canchooseservicecapability")) {
                    	 capability.setCanChooseServiceCapability(property.getTextContent());
                     } else if (property.getNodeName().equals("name")) {
                    	 capability.setCapabilityName(property.getTextContent());
                     } else if (property.getNodeName().equals("value")) {
                    	 capability.setCapabilityValue(property.getTextContent());
                     }
                    
                } 
                
                capabilities.add(capability);
                response.setCapabilities(capabilities);
            }
            
    	}
    	
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
			String physicalNetworkId, HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("addNetworkServiceProvider", optional);
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
            response.setNetworkProviderId(node.getTextContent());
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
            response.setProviderName(node.getTextContent());
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
            response.setProviderServiceList(node.getTextContent());
        }
        
        // get state from XML and set state of the network provider
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkProviderState(node.getTextContent());
        }
        
        return response;
    } 
    
    /**
     * Deletes a Network Service Provider.
     * 
     * @param networkServiceProviderId 	the ID of the network service provider
     * @return
     * @throws Exception
     */
	public DeleteNetworkServiceProviderResponse deleteNetworkServiceProvider(String networkServiceProviderId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteNetworkServiceProvider", null);
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
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listNetworkServiceProviders", optional);
		
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
        
    	// get id from XML and set uuid of the network provider
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkProviderId(node.getTextContent());
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
            response.setProviderName(node.getTextContent());
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
            response.setProviderServiceList(node.getTextContent());
        }
        
        // get state from XML and set state of the network provider
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkProviderState(node.getTextContent());
        }
        
        return response;
    } 
    
    /**
     * Updates a network serviceProvider of a physical network
     * 
     * @param networkServiceProviderId 	network service provider id
     * @param optional
     * @return
     * @throws Exception
     */
	public UpdateNetworkServiceProviderResponse updateNetworkServiceProvider(String networkServiceProviderId, 
			 HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("updateNetworkServiceProvider", optional);
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
            response.setNetworkProviderId(node.getTextContent());
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
            response.setProviderName(node.getTextContent());
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
            response.setProviderServiceList(node.getTextContent());
        }
        
        // get state from XML and set state of the network provider
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkProviderState(node.getTextContent());
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
			String storageNetworkStartIp, HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("createStorageNetworkIpRange", optional);
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
            response.setStorageNetworkId(node.getTextContent());
        }
        
        // get endip from XML and set true if individual services can be enabled/disabled
        list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkEndIp(node.getTextContent());
        }
        
        // get gateway from XML and set	the gateway of the storage network IP range
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkGateway(node.getTextContent());
        }
        
        // get netmask from XML and set	the netmask of the storage network IP range
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkNetmask(node.getTextContent());
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
            response.setStorageNetworkPodId(node.getTextContent());
        }
        
        // get startip from XML and set the start ip of the storage network IP range
        list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkStartIp(node.getTextContent());
        }
        
        // get vlan from XML and set the ID or VID of the VLAN.
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkVlan(node.getTextContent());
        }
        
        // get zoneid from XML and set the Zone uuid of the storage network IP range
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkZoneId(node.getTextContent());
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

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteStorageNetworkIpRange", null);
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
	public ListStorageNetworkIpRangeResponse listStorageNetworkIpRange (
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listStorageNetworkIpRange ", optional);
		
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
        
    	// get id from XML and set the uuid of storage network IP range.
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkId(node.getTextContent());
        }
        
        // get endip from XML and set true if individual services can be enabled/disabled
        list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkEndIp(node.getTextContent());
        }
        
        // get gateway from XML and set	the gateway of the storage network IP range
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkGateway(node.getTextContent());
        }
        
        // get netmask from XML and set	the netmask of the storage network IP range
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkNetmask(node.getTextContent());
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
            response.setStorageNetworkPodId(node.getTextContent());
        }
        
        // get startip from XML and set the start ip of the storage network IP range
        list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkStartIp(node.getTextContent());
        }
        
        // get vlan from XML and set the ID or VID of the VLAN.
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkVlan(node.getTextContent());
        }
        
        // get zoneid from XML and set the Zone uuid of the storage network IP range
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageNetworkZoneId(node.getTextContent());
        }
        
        return response;
    }
    
	public void updateStorageNetworkIpRange (String storageNetworkId, 
			 HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("updateStorageNetworkIpRange ", optional);
		arguments.add(new NameValuePair("id", storageNetworkId));
				
	}
	
	/**
	 * Adds a network device of one of the following types: ExternalDhcp, ExternalFirewall, ExternalLoadBalancer, PxeServer
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public AddNetworkDeviceResponse addNetworkDevice(
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("addNetworkDevice", optional);
				
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
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listNetworkDevice", optional);
				
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

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listF5LoadBalancerNetworks", null);
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
	 * @param loadBalancerDeviceId 	f5 load balancer device ID
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListF5LoadBalancerNetworksResponse listF5LoadBalancerNetworks(String loadBalancerDeviceId,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listF5LoadBalancerNetworks", optional);
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
                    	service.setServiceName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                    	service.setServiceProvider(property.getTextContent());
                    }  else if (property.getNodeName().equals("capability")){
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
	                                	 capability.setCapabilityName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("value")) {
	                                	 capability.setCapabilityValue(property.getTextContent());
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
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listSrxFirewallNetworks", optional);
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
                    	service.setServiceName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                    	service.setServiceProvider(property.getTextContent());
                    }  else if (property.getNodeName().equals("capability")){
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
	                                	 capability.setCapabilityName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("value")) {
	                                	 capability.setCapabilityValue(property.getTextContent());
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
     * lists network that are using a netscaler load balancer device
     * 
     * @param netscalerLoadBalancerDeviceId
     * @param optional
     * @return
     * @throws Exception
     */
	public ListNetscalerLoadBalancerNetworksResponse listNetscalerLoadBalancerNetworks(String netscalerLoadBalancerDeviceId,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listNetscalerLoadBalancerNetworks", optional);
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
                    	service.setServiceName(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                    	service.setServiceProvider(property.getTextContent());
                    }  else if (property.getNodeName().equals("capability")){
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
	                                	 capability.setCapabilityName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("value")) {
	                                	 capability.setCapabilityValue(property.getTextContent());
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
