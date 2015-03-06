package com.assistanz.cloud.cloudstack.zone;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.CapacityResponse;
import com.assistanz.cloud.cloudstack.ZoneResponse;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 *
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
     * @param dns1 the first DNS for the Zone
     * @param internalDns1 the first internal DNS for the Zone
     * @param name the name of the Zone
     * @param networkType network type of the zone, can be Basic or Advanced
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateZoneResponse createZone(String dns1,
            String internalDns1, String name,
            String networkType, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createZone", optional);
        arguments.add(new NameValuePair("dns1", dns1));
        arguments.add(new NameValuePair("internaldns1", internalDns1));
        arguments.add(new NameValuePair("name", name));
        arguments.add(new NameValuePair("networktype", networkType));

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

        // get id from XML and set the Zone id     
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get affinitygroupid from XML and set the UUID of the affinity group associated, null for public zones    
        list = doc.getElementsByTagName("affinitygroupid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAffinityGroupId(node.getTextContent());
        }

        // get allocationstate from XML and set the allocation state of the zone      
        list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAllocationState(node.getTextContent());
        }

        // get description from XML and set the description of the zone      
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get dhcpprovider from XML and set the dhcp provider of the zone      
        list = doc.getElementsByTagName("dhcpprovider");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDhcpProvider(node.getTextContent());
        }

        // get displaytext from XML and set the displaytext of the zone      
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get dns1 from XML and set the dns first of the zone      
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the dns second of the zone      
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get domain from XML and set the domain of the zone      
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domainid of the zone      
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get domainname from XML and set the domain name of the zone      
        list = doc.getElementsByTagName("domainname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get guestcidraddress from XML and set the guest CIDR  address of the zone      
        list = doc.getElementsByTagName("guestcidraddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestCidrAddress(node.getTextContent());
        }

        // get internaldns1 from XML and set the internal dns first of the zone      
        list = doc.getElementsByTagName("internaldns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInternalDns1(node.getTextContent());
        }

        // get internaldns2 from XML and set the internal dns second of the zone      
        list = doc.getElementsByTagName("internaldns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInternalDns2(node.getTextContent());
        }

        // get ip6dns1 from XML and set the first IPv6 DNS for the Zone      
        list = doc.getElementsByTagName("ip6dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns1(node.getTextContent());
        }

        // get ip6dns2 from XML and set the Second IPv6 DNS for the Zone      
        list = doc.getElementsByTagName("ip6dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns2(node.getTextContent());
        }

        // get localstorageenabled from XML and set true if local storage offering enabled, false otherwise      
        list = doc.getElementsByTagName("localstorageenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLocalStorageEnabled(node.getTextContent());
        }

        // get name from XML and set the name of the zone      
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networktype from XML and set the network type of the zone      
        list = doc.getElementsByTagName("networktype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkType(node.getTextContent());
        }

        // get securitygroupsenabled from XML and set the security groups enabled of the zone      
        list = doc.getElementsByTagName("securitygroupsenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecurityGroupsEnabled(node.getTextContent());
        }

        // get vlan from XML and set the vlan of the zone      
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get zonetoken from XML and set the zone token of the zone      
        list = doc.getElementsByTagName("zonetoken");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneToken(node.getTextContent());
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
                        zoneCapacity.setCapacityTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("capacityused")) {
                        zoneCapacity.setCapacityUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                        zoneCapacity.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                        zoneCapacity.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("percentused")) {
                        zoneCapacity.setPercentUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        zoneCapacity.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        zoneCapacity.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        zoneCapacity.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        zoneCapacity.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        zoneCapacity.setZoneName(property.getTextContent());
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
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateZone", optional);
        arguments.add(new NameValuePair("id", zoneId));

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

        // get id from XML and set the Zone id     
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get affinitygroupid from XML and set the UUID of the affinity group associated, null for public zones    
        list = doc.getElementsByTagName("affinitygroupid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAffinityGroupId(node.getTextContent());
        }

        // get allocationstate from XML and set the allocation state of the zone      
        list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAllocationState(node.getTextContent());
        }

        // get description from XML and set the description of the zone      
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get dhcpprovider from XML and set the dhcp provider of the zone      
        list = doc.getElementsByTagName("dhcpprovider");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDhcpProvider(node.getTextContent());
        }

        // get displaytext from XML and set the displaytext of the zone      
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get dns1 from XML and set the dns first of the zone      
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the dns second of the zone      
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get domain from XML and set the domain of the zone      
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domainid of the zone      
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get domainname from XML and set the domain name of the zone      
        list = doc.getElementsByTagName("domainname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get guestcidraddress from XML and set the guest CIDR  address of the zone      
        list = doc.getElementsByTagName("guestcidraddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestCidrAddress(node.getTextContent());
        }

        // get internaldns1 from XML and set the internal dns first of the zone      
        list = doc.getElementsByTagName("internaldns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInternalDns1(node.getTextContent());
        }

        // get internaldns2 from XML and set the internal dns second of the zone      
        list = doc.getElementsByTagName("internaldns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInternalDns2(node.getTextContent());
        }

        // get ip6dns1 from XML and set the first IPv6 DNS for the Zone      
        list = doc.getElementsByTagName("ip6dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns1(node.getTextContent());
        }

        // get ip6dns2 from XML and set the Second IPv6 DNS for the Zone      
        list = doc.getElementsByTagName("ip6dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIp6Dns2(node.getTextContent());
        }

        // get localstorageenabled from XML and set true if local storage offering enabled, false otherwise      
        list = doc.getElementsByTagName("localstorageenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLocalStorageEnabled(node.getTextContent());
        }

        // get name from XML and set the name of the zone      
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networktype from XML and set the network type of the zone      
        list = doc.getElementsByTagName("networktype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkType(node.getTextContent());
        }

        // get securitygroupsenabled from XML and set the security groups enabled of the zone      
        list = doc.getElementsByTagName("securitygroupsenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecurityGroupsEnabled(node.getTextContent());
        }

        // get vlan from XML and set the vlan of the zone      
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get zonetoken from XML and set the zone token of the zone      
        list = doc.getElementsByTagName("zonetoken");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneToken(node.getTextContent());
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
                        zoneCapacity.setCapacityTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("capacityused")) {
                        zoneCapacity.setCapacityUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                        zoneCapacity.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                        zoneCapacity.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("percentused")) {
                        zoneCapacity.setPercentUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        zoneCapacity.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        zoneCapacity.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        zoneCapacity.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        zoneCapacity.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        zoneCapacity.setZoneName(property.getTextContent());
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

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteZone", null);
        arguments.add(new NameValuePair("id", zoneId));

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
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting zone 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
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
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listZones", optional);

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

                ZoneResponse zone = new ZoneResponse();
                List<CapacityResponse> zoneCapacities = new LinkedList<CapacityResponse>();
                NodeList zoneProperties = zoneNode.getChildNodes();
                for (int childIndex = 0; childIndex < zoneProperties.getLength(); childIndex++) {
                    Node property = zoneProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        zone.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("affinitygroupid")) {
                        zone.setAffinityGroupId(property.getTextContent());
                    } else if (property.getNodeName().equals("allocationstate")) {
                        zone.setAllocationState(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        zone.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("dhcpprovider")) {
                        zone.setDhcpProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                        zone.setDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("dns1")) {
                        zone.setDns1(property.getTextContent());
                    } else if (property.getNodeName().equals("dns2")) {
                        zone.setDns2(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        zone.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        zone.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("domainname")) {
                        zone.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("guestcidraddress")) {
                        zone.setGuestCidrAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("internaldns1")) {
                        zone.setInternalDns1(property.getTextContent());
                    } else if (property.getNodeName().equals("internaldns2")) {
                        zone.setInternalDns2(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6dns1")) {
                        zone.setIp6Dns1(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6dns2")) {
                        zone.setIp6Dns2(property.getTextContent());
                    } else if (property.getNodeName().equals("localstorageenabled")) {
                        zone.setLocalStorageEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        zone.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networktype")) {
                        zone.setNetworkType(property.getTextContent());
                    } else if (property.getNodeName().equals("securitygroupsenabled")) {
                        zone.setSecurityGroupsEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("vlan")) {
                        zone.setVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("zonetoken")) {
                        zone.setZoneToken(property.getTextContent());
                    } else if (property.getNodeName().equals("capacity")) {
                        NodeList capacityList = doc.getElementsByTagName("capacity");
                        if (capacityList.getLength() > 0) {
                            for (int index = 0; index < capacityList.getLength(); index++) {
                                Node zoneCapacityNode = capacityList.item(index);
                                CapacityResponse zoneCapacity = new CapacityResponse();

                                NodeList zoneCapacityProperties = zoneCapacityNode.getChildNodes();
                                for (childIndex = 0; childIndex < zoneCapacityProperties.getLength(); childIndex++) {
                                    property = zoneCapacityProperties.item(childIndex);

                                    if (property.getNodeName().equals("capacitytotal")) {
                                        zoneCapacity.setCapacityTotal(property.getTextContent());
                                    } else if (property.getNodeName().equals("capacityused")) {
                                        zoneCapacity.setCapacityUsed(property.getTextContent());
                                    } else if (property.getNodeName().equals("clusterid")) {
                                        zoneCapacity.setClusterId(property.getTextContent());
                                    } else if (property.getNodeName().equals("clustername")) {
                                        zoneCapacity.setClusterName(property.getTextContent());
                                    } else if (property.getNodeName().equals("percentused")) {
                                        zoneCapacity.setPercentUsed(property.getTextContent());
                                    } else if (property.getNodeName().equals("podid")) {
                                        zoneCapacity.setPodId(property.getTextContent());
                                    } else if (property.getNodeName().equals("podname")) {
                                        zoneCapacity.setPodName(property.getTextContent());
                                    } else if (property.getNodeName().equals("type")) {
                                        zoneCapacity.setType(property.getTextContent());
                                    } else if (property.getNodeName().equals("zoneid")) {
                                        zoneCapacity.setZoneId(property.getTextContent());
                                    } else if (property.getNodeName().equals("zonename")) {
                                        zoneCapacity.setZoneName(property.getTextContent());
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

    /**
     * Dedicates a Zone.
     *
     * @param domainId the ID of the containing domain
     * @param zoneId the ID of the zone
     * @param optional
     * @return
     * @throws Exception
     */
    public DedicateZoneResponse dedicateZone(String domainId,
            String zoneId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("dedicateZone", optional);
        arguments.add(new NameValuePair("domainid", domainId));
        arguments.add(new NameValuePair("zoneid", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getDedicateZoneResponse(responseDocument);
    }

    /**
     * Converts XML document into DedicateZoneResponse object
     *
     * @param doc
     * @return
     */
    private DedicateZoneResponse getDedicateZoneResponse(Document doc) {
        DedicateZoneResponse response = new DedicateZoneResponse();

        // get id from XML and set the ID of the dedicated resource     
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get accountid from XML and set the Account Id to which the Zone is dedicated   
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get affinitygroupid from XML and set the Dedication Affinity Group ID of the zone     
        list = doc.getElementsByTagName("affinitygroupid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAffinityGroupId(node.getTextContent());
        }

        // get domainid from XML and set the domain ID to which the Zone is dedicated      
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get zoneid from XML and set the ID of the Zone     
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the Name of the Zone      
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        return response;
    }

    /**
     * Release dedication of zone.
     *
     * @param zoneId the ID of the Zone
     * @return
     * @throws Exception
     */
    public ReleaseDedicatedZoneResponse releaseDedicatedZone(String zoneId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("releaseDedicatedZone", null);
        arguments.add(new NameValuePair("id", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getReleaseDedicatedZoneResponse(responseDocument);
    }

    /**
     * Converts XML document into ReleaseDedicatedZoneResponse object
     *
     * @param doc
     * @return
     */
    private ReleaseDedicatedZoneResponse getReleaseDedicatedZoneResponse(Document doc) {
        ReleaseDedicatedZoneResponse response = new ReleaseDedicatedZoneResponse();

        // get displaytext from XML and set any text associated with the success or failure on deleting zone 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting zone 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * List dedicated zones.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListDedicatedZonesResponse listDedicatedZones(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listDedicatedZones", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListDedicatedZonesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListDedicatedZonesResponse object
     *
     * @param doc
     * @return
     */
    private ListDedicatedZonesResponse getListDedicatedZonesResponse(Document doc) {
        ListDedicatedZonesResponse response = new ListDedicatedZonesResponse();

        NodeList list = doc.getElementsByTagName("dedicatedzone");

        List<DedicatedZoneResponse> dedicatedZones = new LinkedList<DedicatedZoneResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node dedicatedZoneNode = list.item(Index);
            DedicatedZoneResponse dedicatedZone = new DedicatedZoneResponse();

            NodeList dedicatedZoneProperties = dedicatedZoneNode.getChildNodes();
            for (int childIndex = 0; childIndex < dedicatedZoneProperties.getLength(); childIndex++) {
                Node property = dedicatedZoneProperties.item(childIndex);

                if (property.getNodeName().equals("id")) {
                    dedicatedZone.setId(property.getTextContent());
                } else if (property.getNodeName().equals("accountid")) {
                    dedicatedZone.setAccountId(property.getTextContent());
                } else if (property.getNodeName().equals("affinitygroupid")) {
                    dedicatedZone.setAffinityGroupId(property.getTextContent());
                } else if (property.getNodeName().equals("domainid")) {
                    dedicatedZone.setDomainId(property.getTextContent());
                } else if (property.getNodeName().equals("zoneid")) {
                    dedicatedZone.setZoneId(property.getTextContent());
                } else if (property.getNodeName().equals("zonename")) {
                    dedicatedZone.setZoneName(property.getTextContent());
                }
            }
            dedicatedZones.add(dedicatedZone);
        }

        response.setDedicatedZones(dedicatedZones);
        return response;
    }

}
