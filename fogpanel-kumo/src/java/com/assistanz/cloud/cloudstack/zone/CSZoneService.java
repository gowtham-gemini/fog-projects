package com.assistanz.cloud.cloudstack.zone;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.CapacityResponse;
import com.assistanz.cloud.cloudstack.ZoneResponse;


/***
 * 
 * @author Gowtham
 *
 */
public class CSZoneService {
	
	private CloudStackServer server;
	
	public CSZoneService(CloudStackServer server) {
            this.server = server;
	}
	
	/**
	 * Creates a Zone.
	 * 
	 * @param firstDns the first DNS for the Zone
	 * @param firstInternalDns the first internal DNS for the Zone
	 * @param zoneName the name of the Zone
	 * @param zoneNetworkType network type of the zone, can be Basic or Advanced
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public CreateZoneResponse createZone(String firstDns,
			String firstInternalDns, String zoneName,
			String zoneNetworkType, HashMap<String,String> optional) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("createZone", optional);
	    arguments.add(new NameValuePair("dns1",  firstDns));
	    arguments.add(new NameValuePair("internaldns1",  firstInternalDns ));
	    arguments.add(new NameValuePair("name", zoneName));
	    arguments.add(new NameValuePair("networktype", zoneNetworkType));
	    
	    Document responseDocument = server.makeRequest(arguments);

	    return getCreateZoneResponse(responseDocument);
	}
	
    /**
     * Converts XML document into CreateZoneResponse object
     *
     * @param doc
     * @return
     */
    private CreateZoneResponse getCreateZoneResponse(Document doc) {
    	CreateZoneResponse response = new CreateZoneResponse();
    	
    	// get Id from XML and set the Zone id     
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getNodeValue());
        }
        
    	// get allocationstate from XML and set the allocation state of the zone      
    	list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAllocationState(node.getNodeValue());
        }
        
    	// get description from XML and set the description of the zone      
    	list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneDescription(node.getNodeValue());
        }
        
    	// get dhcpprovider from XML and set the dhcp provider of the zone      
    	list = doc.getElementsByTagName("dhcpprovider");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDhcpProvider(node.getNodeValue());
        }
        
    	// get displaytext from XML and set the displaytext of the zone      
    	list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneDisplayText(node.getNodeValue());
        }
        
    	// get dns1 from XML and set the dns first of the zone      
    	list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDnsFirst(node.getNodeValue());
        }
        
    	// get dns2 from XML and set the dns second of the zone      
    	list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDnsSecond(node.getNodeValue());
        }
        
    	// get domain from XML and set the domain of the zone      
    	list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneDomain(node.getNodeValue());
        }
        
    	// get domainid from XML and set the domainid of the zone      
    	list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneDomainId(node.getNodeValue());
        }
        
    	// get domainname from XML and set the domain name of the zone      
    	list = doc.getElementsByTagName("domainname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneDomainName(node.getNodeValue());
        }
        
    	// get guestcidraddress from XML and set the guest CIDR  address of the zone      
    	list = doc.getElementsByTagName("guestcidraddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestCidrAddress(node.getNodeValue());
        }
        
    	// get internaldns1 from XML and set the internal dns first of the zone      
    	list = doc.getElementsByTagName("internaldns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInternalDnsFirst(node.getNodeValue());
        }
        
    	// get internaldns2 from XML and set the internal dns second of the zone      
    	list = doc.getElementsByTagName("internaldns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInternalDnsSecond(node.getNodeValue());
        }
        
    	// get name from XML and set the name of the zone      
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getNodeValue());
        }
        
    	// get networktype from XML and set the network type of the zone      
    	list = doc.getElementsByTagName("networktype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneNetworkType(node.getNodeValue());
        }
        
    	// get securitygroupsenabled from XML and set the security groups enabled of the zone      
    	list = doc.getElementsByTagName("securitygroupsenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecurityGroupsEnabled(node.getNodeValue());
        }
        
    	// get vlan from XML and set the vlan of the zone      
    	list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getNodeValue());
        }
        
    	// get zonetoken from XML and set the zone token of the zone      
    	list = doc.getElementsByTagName("zonetoken");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneToken(node.getNodeValue());
        }
        
        //the list of Zone Capacity associated with Zone
        list = doc.getElementsByTagName("capacity");
        if (list.getLength() > 0) {
            List<CapacityResponse> zoneCapacities = new LinkedList<CapacityResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node zoneCapacityNode = list.item(index);
                CapacityResponse zoneCapacity = new CapacityResponse();
                
                
                NodeList zoneCapacityProperties = zoneCapacityNode.getChildNodes();
                for (int childIndex = 0; childIndex < zoneCapacityProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("capacitytotal")) {
                    	zoneCapacity.setCapacityTotal(property.getNodeValue());
                    } else if (property.getNodeName().equals("capacityused")) {
                    	zoneCapacity.setCapacityUsed(property.getNodeValue());
                    } else if (property.getNodeName().equals("clusterid")) {
                    	zoneCapacity.setClusterId(property.getNodeValue());
                    } else if (property.getNodeName().equals("clustername")) {
                    	zoneCapacity.setClusterName(property.getNodeValue());
                    } else if (property.getNodeName().equals("percentused")) {
                    	zoneCapacity.setPercentUsed(property.getNodeValue());
                    } else if (property.getNodeName().equals("podid")) {
                    	zoneCapacity.setPodId(property.getNodeValue());
                    } else if (property.getNodeName().equals("podname")) {
                    	zoneCapacity.setPodName(property.getNodeValue());
                    } else if (property.getNodeName().equals("type")) {
                    	zoneCapacity.setCapacityType(property.getNodeValue());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	zoneCapacity.setZoneId(property.getNodeValue());
                    } else if (property.getNodeName().equals("zonename")) {
                    	zoneCapacity.setZoneName(property.getNodeValue());
                    }  
                }
            
                zoneCapacities.add(zoneCapacity);
                response.setZoneCapacities(zoneCapacities);
            }
        }
                      
        return response;
        
    }
        
    /**
     * Updates a Zone.
     * 
     * @param zoneId the ID of the Zone
     * @param optional
     * @return
     * @throws Exception
     */
	public UpdateZoneResponse updateZone(String zoneId,
			HashMap<String,String> optional) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("updateZone", optional);
	    arguments.add(new NameValuePair("id",  zoneId));
	    
	    
	    Document responseDocument = server.makeRequest(arguments);

	    return getUpdateZoneResponse(responseDocument);
	}
	
    /**
     * Converts XML document into UpdateZoneResponse object
     *
     * @param doc
     * @return
     */
    private UpdateZoneResponse getUpdateZoneResponse(Document doc) {
    	UpdateZoneResponse response = new UpdateZoneResponse();
    	
    	// get Id from XML and set the Zone id     
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getNodeValue());
        }
        
    	// get allocationstate from XML and set the allocation state of the zone      
    	list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAllocationState(node.getNodeValue());
        }
        
    	// get description from XML and set the description of the zone      
    	list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneDescription(node.getNodeValue());
        }
        
    	// get dhcpprovider from XML and set the dhcp provider of the zone      
    	list = doc.getElementsByTagName("dhcpprovider");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDhcpProvider(node.getNodeValue());
        }
        
    	// get displaytext from XML and set the displaytext of the zone      
    	list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneDisplayText(node.getNodeValue());
        }
        
    	// get dns1 from XML and set the dns first of the zone      
    	list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDnsFirst(node.getNodeValue());
        }
        
    	// get dns2 from XML and set the dns second of the zone      
    	list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDnsSecond(node.getNodeValue());
        }
        
    	// get domain from XML and set the domain of the zone      
    	list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneDomain(node.getNodeValue());
        }
        
    	// get domainid from XML and set the domainid of the zone      
    	list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneDomainId(node.getNodeValue());
        }
        
    	// get domainname from XML and set the domain name of the zone      
    	list = doc.getElementsByTagName("domainname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneDomainName(node.getNodeValue());
        }
        
    	// get guestcidraddress from XML and set the guest CIDR  address of the zone      
    	list = doc.getElementsByTagName("guestcidraddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestCidrAddress(node.getNodeValue());
        }
        
    	// get internaldns1 from XML and set the internal dns first of the zone      
    	list = doc.getElementsByTagName("internaldns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInternalDnsFirst(node.getNodeValue());
        }
        
    	// get internaldns2 from XML and set the internal dns second of the zone      
    	list = doc.getElementsByTagName("internaldns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInternalDnsSecond(node.getNodeValue());
        }
        
    	// get name from XML and set the name of the zone      
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getNodeValue());
        }
        
    	// get networktype from XML and set the network type of the zone      
    	list = doc.getElementsByTagName("networktype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneNetworkType(node.getNodeValue());
        }
        
    	// get securitygroupsenabled from XML and set the security groups enabled of the zone      
    	list = doc.getElementsByTagName("securitygroupsenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecurityGroupsEnabled(node.getNodeValue());
        }
        
    	// get vlan from XML and set the vlan of the zone      
    	list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getNodeValue());
        }
        
    	// get zonetoken from XML and set the zone token of the zone      
    	list = doc.getElementsByTagName("zonetoken");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneToken(node.getNodeValue());
        }
        
        //the list of Zone Capacity associated with Zone
        list = doc.getElementsByTagName("capacity");
        if (list.getLength() > 0) {
            List<CapacityResponse> zoneCapacities = new LinkedList<CapacityResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node zoneCapacityNode = list.item(index);
                CapacityResponse zoneCapacity = new CapacityResponse();
                
                
                NodeList zoneCapacityProperties = zoneCapacityNode.getChildNodes();
                for (int childIndex = 0; childIndex < zoneCapacityProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("capacitytotal")) {
                    	zoneCapacity.setCapacityTotal(property.getNodeValue());
                    } else if (property.getNodeName().equals("capacityused")) {
                    	zoneCapacity.setCapacityUsed(property.getNodeValue());
                    } else if (property.getNodeName().equals("clusterid")) {
                    	zoneCapacity.setClusterId(property.getNodeValue());
                    } else if (property.getNodeName().equals("clustername")) {
                    	zoneCapacity.setClusterName(property.getNodeValue());
                    } else if (property.getNodeName().equals("percentused")) {
                    	zoneCapacity.setPercentUsed(property.getNodeValue());
                    } else if (property.getNodeName().equals("podid")) {
                    	zoneCapacity.setPodId(property.getNodeValue());
                    } else if (property.getNodeName().equals("podname")) {
                    	zoneCapacity.setPodName(property.getNodeValue());
                    } else if (property.getNodeName().equals("type")) {
                    	zoneCapacity.setCapacityType(property.getNodeValue());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	zoneCapacity.setZoneId(property.getNodeValue());
                    } else if (property.getNodeName().equals("zonename")) {
                    	zoneCapacity.setZoneName(property.getNodeValue());
                    }  
                }
            
                zoneCapacities.add(zoneCapacity);
                response.setZoneCapacities(zoneCapacities);
            }
        }
                      
        return response;
        
    }
    
    /**
     * Deletes a Zone.
     * 
     * @param zoneId the ID of the Zone
     * @param optional
     * @return
     * @throws Exception
     */
	public DeleteZoneResponse deleteZone(String zoneId) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("deleteZone", null);
	    arguments.add(new NameValuePair("id",  zoneId));
	    
	    
	    Document responseDocument = server.makeRequest(arguments);

	    return getDeleteZoneResponse(responseDocument);
	}
	
    /**
     * Converts XML document into DeleteZoneResponse object
     *
     * @param doc
     * @return
     */
    private DeleteZoneResponse getDeleteZoneResponse(Document doc) {
    	DeleteZoneResponse response = new DeleteZoneResponse();
    	
    	// get displaytext from XML and set any text associated with the success or failure on deleting zone 
    	NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getNodeValue());
        }
        //get success from XML and any text associated with the success or failure on deleting zone 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getNodeValue());
        }
        
        return response;
    }
    
    /**
     * Lists zones
     * 
     * @param optional
     * @return
     * @throws Exception
     */
	public ListZonesResponse listZones(
                    HashMap<String,String> optional) 
                                throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("listZones", optional);
	    
	    Document responseDocument = server.makeRequest(arguments);
            
	    return getListZonesResponse(responseDocument);             
	}
	
    /**
     * Converts XML document into ListZonesResponse object
     *
     * @param doc
     * @return
     */
    private ListZonesResponse getListZonesResponse(Document doc) {
    	ListZonesResponse response = new ListZonesResponse();
    	
         //list of zone
        NodeList list = doc.getElementsByTagName("zone");

        List<ZoneResponse> zones = new LinkedList<ZoneResponse>();            
        if (list.getLength() > 0) {
            for (int zoneIndex = 0; zoneIndex < list.getLength(); zoneIndex++) {
                Node zoneNode = list.item(zoneIndex);

                if (zoneNode == null) {
                    continue;
                }  
                
                ZoneResponse zone = new ZoneResponse();
                        
                NodeList zoneProperties = zoneNode.getChildNodes();
                for (int childIndex = 0; childIndex < zoneProperties.getLength(); childIndex++) {
                    Node property = zoneProperties.item(childIndex);
                    
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                    if (property.getNodeName().equals("id")) {
                        zone.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("allocationstate")) {
                    	zone.setAllocationState(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                    	zone.setZoneDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("dhcpprovider")) {
                    	zone.setDhcpProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                    	zone.setZoneDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("dns1")) {
                    	zone.setDnsFirst(property.getTextContent());
                    } else if (property.getNodeName().equals("dns2")) {
                    	zone.setDnsSecond(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                    	zone.setZoneNetworkDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                    	zone.setZoneDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("domainname")) {
                    	zone.setZoneDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("guestcidraddress")) {
                    	zone.setGuestCidrAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("internaldns1")) {
                    	zone.setInternalDnsFirst(property.getTextContent());
                    } else if (property.getNodeName().equals("internaldns2")) {
                    	zone.setInternalDnsSecond(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	zone.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("networktype")) {
                    	zone.setZoneNetworkType(property.getTextContent());
                    } else if (property.getNodeName().equals("securitygroupsenabled")) {
                    	zone.setSecurityGroupsEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("vlan")) {
                    	zone.setVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("zonetoken")) {
                    	zone.setZoneToken(property.getTextContent());
                    } else if (property.getNodeName().equals("capacity")){
                        NodeList capacityList = doc.getElementsByTagName("capacity");
                        if (capacityList.getLength() > 0) {
                            List<CapacityResponse> zoneCapacities = new LinkedList<CapacityResponse>();            
                            for (int index = 0; index < capacityList.getLength(); index++) {
                                Node zoneCapacityNode = capacityList.item(index);
                                CapacityResponse zoneCapacity = new CapacityResponse();


                                NodeList zoneCapacityProperties = zoneCapacityNode.getChildNodes();
                                for (childIndex = 0; childIndex < zoneCapacityProperties.getLength(); childIndex++) {
                                    property = zoneCapacityProperties.item(childIndex);

                                    if (property.getNodeName().equals("capacitytotal")) {
                                        zoneCapacity.setCapacityTotal(property.getTextContent());
                                    } else if (property.getNodeName().equals("capacityused")) {
                                        zoneCapacity.setCapacityUsed(property.getNodeValue());
                                    } else if (property.getNodeName().equals("clusterid")) {
                                        zoneCapacity.setClusterId(property.getNodeValue());
                                    } else if (property.getNodeName().equals("clustername")) {
                                        zoneCapacity.setClusterName(property.getNodeValue());
                                    } else if (property.getNodeName().equals("percentused")) {
                                        zoneCapacity.setPercentUsed(property.getNodeValue());
                                    } else if (property.getNodeName().equals("podid")) {
                                        zoneCapacity.setPodId(property.getNodeValue());
                                    } else if (property.getNodeName().equals("podname")) {
                                        zoneCapacity.setPodName(property.getNodeValue());
                                    } else if (property.getNodeName().equals("type")) {
                                        zoneCapacity.setCapacityType(property.getNodeValue());
                                    } else if (property.getNodeName().equals("zoneid")) {
                                        zoneCapacity.setZoneId(property.getNodeValue());
                                    } else if (property.getNodeName().equals("zonename")) {
                                        zoneCapacity.setZoneName(property.getNodeValue());
                                    }  
                                }

                                zoneCapacities.add(zoneCapacity);
                                response.setZoneCapacities(zoneCapacities);
                            }
                        }
                    } 
                
                    
                }
                zones.add(zone);
            }
        } 
        response.setZones(zones);
        return response;
        
    }

}


