package com.assistanz.cloud.cloudstack.vpc;

import java.util.HashMap;
import java.util.LinkedList;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.TagsResponse;
import com.assistanz.cloud.cloudstack.NetworkResponse;
import com.assistanz.cloud.cloudstack.CapabilityResponse;
import com.assistanz.cloud.cloudstack.ProviderResponse;
import com.assistanz.cloud.cloudstack.ServiceResponse;
import java.util.List;

/**
 *
 * @author Santhosh
 */
public class CSVPCService {

    private CloudStackServer server;

    public CSVPCService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates a VPC
     *
     * @param cidr cidr address id of the vpc server
     * @param displayText displayText of the vpc server
     * @param name name of the vpc server
     * @param vpcOfferingId
     * @param zoneId
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateVpcResponse createVPC(String cidr, String displayText, String name,
            String vpcOfferingId, String zoneId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createVPC", optional);
        arguments.add(new NameValuePair("cidr", cidr));
        arguments.add(new NameValuePair("displaytext", displayText));
        arguments.add(new NameValuePair("name", name));
        arguments.add(new NameValuePair("vpcofferingid", vpcOfferingId));
        arguments.add(new NameValuePair("zoneid", zoneId));
        Document responseDocument = server.makeRequest(arguments);

        return getCreateVpcResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVPCResponse object
     *
     * @param doc
     * @return
     */
    private CreateVpcResponse getCreateVpcResponse(Document doc) {
        CreateVpcResponse response = new CreateVpcResponse();

        // get the id of the VPC      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }
        // get the job id of the VPC      
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get the owner of the VPC
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get the cidr the VPC
        list = doc.getElementsByTagName("cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidr(node.getTextContent());
        }

        // get the date this VPC was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // an alternate display text of the VPC
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get the domain name of the owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get the domain id of the VPC owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the name of the VPC
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcName(node.getTextContent());
        }

        // get the network domain of the VPC
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get the project name of the VPC
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the VPC
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // true VPC requires restart
        list = doc.getElementsByTagName("restartrequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRestartRequired(node.getTextContent());
        }

        // get state of the VPC. Can be Inactive/Enabled
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get vpc offering id the VPC is created from
        list = doc.getElementsByTagName("vpcofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcOfferingId(node.getTextContent());
        }

        // get zone id of the vpc
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get the name of the zone the VPC belongs to
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // get the list of networks belonging to the VPC
        list = doc.getElementsByTagName("network");
        if (list.getLength() > 0) {
            List<NetworkResponse> networks = new LinkedList<NetworkResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node networkNode = list.item(index);
                NetworkResponse network = new NetworkResponse();
                NodeList networkProperties = networkNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkProperties.getLength(); childIndex++) {
                    Node networkProperty = networkProperties.item(childIndex);

                    if (networkProperty.getNodeName().equals("id")) {
                        network.setId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("account")) {
                        network.setAccount(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("aclid")) {
                        network.setAclId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("acltype")) {
                        network.setAclType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("broadcastdomaintype")) {
                        network.setBroadcastDomainType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("broadcasturi")) {
                        network.setBroadcastUri(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("canusefordeploy")) {
                        network.setCanUseForDeploy(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("cidr")) {
                        network.setCidr(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("displaynetwork")) {
                        network.setDisplayNetwork(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("displaytext")) {
                        network.setDisplayText(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("dns1")) {
                        network.setDns1(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("dns2")) {
                        network.setDns2(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("domain")) {
                        network.setDomain(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("domainid")) {
                        network.setDomainId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("gateway")) {
                        network.setGateway(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("ip6cidr")) {
                        network.setIp6Cidr(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("ip6gateway")) {
                        network.setIp6Gateway(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("isdefault")) {
                        network.setIsDefault(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("ispersistent")) {
                        network.setIsPersistent(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("issystem")) {
                        network.setIsSystem(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("name")) {
                        network.setNetworkName(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("netmask")) {
                        network.setNetmask(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkcidr")) {
                        network.setNetworkCidr(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkdomain")) {
                        network.setNetworkDomain(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingavailability")) {
                        network.setNetworkOfferingAvailability(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingconservemode")) {
                        network.setNetworkOfferingConserveMode(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingdisplaytext")) {
                        network.setNetworkOfferingDisplayText(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingid")) {
                        network.setNetworkOfferingId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingname")) {
                        network.setNetworkOfferingName(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("physicalnetworkid")) {
                        network.setPhysicalNetworkId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("project")) {
                        network.setProject(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("projectid")) {
                        network.setProjectId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("related")) {
                        network.setRelated(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("reservediprange")) {
                        network.setReservedIpRange(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("restartrequired")) {
                        network.setRestartRequired(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("specifyipranges")) {
                        network.setSpecifyIpRanges(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("state")) {
                        network.setState(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("subdomainaccess")) {
                        network.setSubDomainAccess(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("traffictype")) {
                        network.setTrafficType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("type")) {
                        network.setNetworkType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("vlan")) {
                        network.setVlan(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("vpcid")) {
                        network.setVpcId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("zoneid")) {
                        network.setZoneId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("zonename")) {
                        network.setZoneName(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("service")) {
                        list = doc.getElementsByTagName("service");
                        if (list.getLength() > 0) {
                            List<ServiceResponse> services = new LinkedList<ServiceResponse>();
                            for (index = 0; index < list.getLength(); index++) {

                                Node serviceNode = list.item(index);
                                ServiceResponse service = new ServiceResponse();

                                NodeList serviceProperties = serviceNode.getChildNodes();
                                for (childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                                    networkProperty = list.item(childIndex);

                                    if (networkProperty.getNodeName().equals("name")) {
                                        service.setServiceName(networkProperty.getTextContent());
                                        services.add(service);
                                    } else if (networkProperty.getNodeName().equals("capability")) {

                                        list = doc.getElementsByTagName("capability");
                                        if (list.getLength() > 0) {
                                            List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                                            // TODO: values to be set properly
                                            for (index = 0; index < list.getLength(); index++) {
                                                Node capabilityNode = list.item(index);
                                                CapabilityResponse capability = new CapabilityResponse();

                                                NodeList capabilityProperties = capabilityNode.getChildNodes();
                                                for (childIndex = 0; childIndex < capabilityProperties.getLength(); childIndex++) {
                                                    networkProperty = list.item(childIndex);
                                                }
                                            }
                                            // capabilities.add(capability);
                                            // response.setCapabilitys(capabilities);        
                                        }

                                    } else if (networkProperty.getNodeName().equals("provider")) {

                                        list = doc.getElementsByTagName("provider");
                                        if (list.getLength() > 0) {
                                            List<ProviderResponse> providers = new LinkedList<ProviderResponse>();
                                            // TODO: values to be set properly 
                                            for (index = 0; index < list.getLength(); index++) {
                                                Node providerNode = list.item(index);
                                                ProviderResponse provider = new ProviderResponse();

                                                NodeList providerProperties = providerNode.getChildNodes();
                                                for (childIndex = 0; childIndex < providerProperties.getLength(); childIndex++) {
                                                    networkProperty = list.item(childIndex);
                                                }
                                            }
                                            // providers.add(provider);
                                            // response.setProviders(providers);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (networkProperty.getNodeName().equals("tags")) {
                        list = doc.getElementsByTagName("tags");
                        if (list.getLength() > 0) {
                            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                            for (index = 0; index < list.getLength(); index++) {
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
                                response.setTagss(tagss);
                            }
                        }
                    }

                    networks.add(network);
                }
                response.setNetworks(networks);
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
                                            capability.setCapabilityName(property.getTextContent());
                                        } else if (property.getNodeName().equals("value")) {
                                            capability.setCapabilityValue(property.getTextContent());
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

            // gets associated tag values for the vpc
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

        }
        return response;
    }

    /**
     * List VPCs.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListVpcsResponse listVPCs(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVPCs", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListVpcsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListVPCsResponse object
     *
     * @param doc
     * @return
     */
    private ListVpcsResponse getListVpcsResponse(Document doc) {
        ListVpcsResponse response = new ListVpcsResponse();

        NodeList list = doc.getElementsByTagName("vpc");

        List<VpcResponse> vpcs = new LinkedList<VpcResponse>();

        if (list.getLength() > 0) {
            for (int vpcIndex = 0; vpcIndex < list.getLength(); vpcIndex++) {
                Node vpcNode = list.item(vpcIndex);

                VpcResponse vpc = new VpcResponse();
                List<NetworkResponse> networks = new LinkedList<NetworkResponse>();
                List<ServiceResponse> services = new LinkedList<ServiceResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                List<ProviderResponse> providers = new LinkedList<ProviderResponse>();
                NodeList vpcProperties = vpcNode.getChildNodes();
                for (int childIndex = 0; childIndex < vpcProperties.getLength(); childIndex++) {
                    Node property = vpcProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        vpc.setVpcId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        vpc.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("cidr")) {
                        vpc.setCidr(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        vpc.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                        vpc.setDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        vpc.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        vpc.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        vpc.setVpcName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkdomain")) {
                        vpc.setNetworkDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        vpc.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        vpc.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("restartrequired")) {
                        vpc.setRestartRequired(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        vpc.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcofferingid")) {
                        vpc.setVpcOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        vpc.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        vpc.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("network")) {
                        NodeList networkProperties = property.getChildNodes();
                        if (networkProperties.getLength() > 0) {
                            NetworkResponse network = new NetworkResponse();
                            for (int networkIndex = 0; networkIndex < networkProperties.getLength(); networkIndex++) {
                                Node networkProperty = networkProperties.item(networkIndex);
                                if (networkProperty.getNodeName().equals("id")) {
                                    network.setId(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("account")) {
                                    network.setAccount(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("aclid")) {
                                    network.setAclId(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("acltype")) {
                                    network.setAclType(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("broadcastdomaintype")) {
                                    network.setBroadcastDomainType(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("broadcasturi")) {
                                    network.setBroadcastUri(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("canusefordeploy")) {
                                    network.setCanUseForDeploy(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("cidr")) {
                                    network.setCidr(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("displaynetwork")) {
                                    network.setDisplayNetwork(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("displaytext")) {
                                    network.setDisplayText(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("dns1")) {
                                    network.setDns1(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("dns2")) {
                                    network.setDns2(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("domain")) {
                                    network.setDomain(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("domainid")) {
                                    network.setDomainId(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("gateway")) {
                                    network.setGateway(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("ip6cidr")) {
                                    network.setIp6Cidr(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("ip6gateway")) {
                                    network.setIp6Gateway(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("isdefault")) {
                                    network.setIsDefault(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("ispersistent")) {
                                    network.setIsPersistent(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("issystem")) {
                                    network.setIsSystem(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("name")) {
                                    network.setNetworkName(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("netmask")) {
                                    network.setNetmask(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("networkcidr")) {
                                    network.setNetworkCidr(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("networkdomain")) {
                                    network.setNetworkDomain(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("networkofferingavailability")) {
                                    network.setNetworkOfferingAvailability(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("networkofferingconservemode")) {
                                    network.setNetworkOfferingConserveMode(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("networkofferingdisplaytext")) {
                                    network.setNetworkOfferingDisplayText(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("networkofferingid")) {
                                    network.setNetworkOfferingId(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("networkofferingname")) {
                                    network.setNetworkOfferingName(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("physicalnetworkid")) {
                                    network.setPhysicalNetworkId(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("project")) {
                                    network.setProject(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("projectid")) {
                                    network.setProjectId(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("related")) {
                                    network.setRelated(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("reservediprange")) {
                                    network.setReservedIpRange(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("restartrequired")) {
                                    network.setRestartRequired(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("specifyipranges")) {
                                    network.setSpecifyIpRanges(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("state")) {
                                    network.setState(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("subdomainaccess")) {
                                    network.setSubDomainAccess(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("traffictype")) {
                                    network.setTrafficType(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("type")) {
                                    network.setNetworkType(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("vlan")) {
                                    network.setVlan(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("vpcid")) {
                                    network.setVpcId(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("zoneid")) {
                                    network.setZoneId(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("zonename")) {
                                    network.setZoneName(networkProperty.getTextContent());
                                } else if (networkProperty.getNodeName().equals("service")) {
                                    NodeList serviceProperties = networkProperty.getChildNodes();
                                    if (serviceProperties.getLength() > 0) {
                                        ServiceResponse service = new ServiceResponse();
                                        for (int serviceIndex = 0; serviceIndex < serviceProperties.getLength(); serviceIndex++) {
                                            Node serviceProperty = serviceProperties.item(serviceIndex);
                                            if (networkProperty.getNodeName().equals("name")) {
                                                service.setServiceName(networkProperty.getTextContent());
                                            } else if (networkProperty.getNodeName().equals("capability")) {
                                                NodeList capabilityProperties = networkProperty.getChildNodes();
                                                if (capabilityProperties.getLength() > 0) {
                                                    CapabilityResponse capability = new CapabilityResponse();
                                                    for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
                                                        Node capabilityProperty = capabilityProperties.item(capabilityIndex);
                                                    }
                                                }
                                        // capabilities.add(capability);
                                                // response.setCapabilitys(capabilities);        
                                            } else if (networkProperty.getNodeName().equals("provider")) {
                                                NodeList providerProperties = networkProperty.getChildNodes();
                                                if (providerProperties.getLength() > 0) {
                                                    ProviderResponse provider = new ProviderResponse();
                                                    for (int providerIndex = 0; providerIndex < providerProperties.getLength(); providerIndex++) {
                                                        Node providerProperty = providerProperties.item(providerIndex);

                                                    }
                                                // providers.add(provider);
                                                    // response.setProviders(providers);
                                                }
                                            }
                                            services.add(service);
                                        }
                                    }
                                } else if (networkProperty.getNodeName().equals("tags")) {
                                    NodeList tagsProperties = networkProperty.getChildNodes();
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

                                }
                            }
                            network.setServices(services);
                            network.setTagss(tagss);
                            networks.add(network);
                        }

                    } else if (property.getNodeName().equals("service")) {
//                        NodeList serviceProperties = property.getChildNodes();
//                        if (serviceProperties.getLength() > 0) {
//                            ServiceResponse service = new ServiceResponse();
//
//                            for (int serviceIndex = 0; serviceIndex < list.getLength(); serviceIndex++) {
//                                Node serviceProperty = serviceProperties.item(serviceIndex);
//                                if (serviceProperty.getNodeName().equals("name")) {
//                                    service.setServiceName(property.getTextContent());
//                                } else if (serviceProperty.getNodeName().equals("capability")) {
//                                    NodeList capabilityProperties = serviceProperty.getChildNodes();
//                                    if (capabilityProperties.getLength() > 0) {
//                                        CapabilityResponse capability = new CapabilityResponse();
//                                        for (int capabilityIndex = 0; capabilityIndex < capabilityProperties.getLength(); capabilityIndex++) {
//                                            Node capabilityProperty = capabilityProperties.item(capabilityIndex);
//
//                                            if (capabilityProperty.getNodeName().equals("canchooseservicecapability")) {
//                                                capability.setCanChooseServiceCapability(property.getTextContent());
//                                            } else if (capabilityProperty.getNodeName().equals("name")) {
//                                                capability.setCapabilityName(property.getTextContent());
//                                            } else if (capabilityProperty.getNodeName().equals("value")) {
//                                                capability.setCapabilityValue(property.getTextContent());
//                                            }
//                                        }
//                                        capabilities.add(capability);
//                                    }
//
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
//                        list = doc.getElementsByTagName("tags");
//                        if (list.getLength() > 0) {
//                            for (int index = 0; index < list.getLength(); index++) {
//                                Node tagsNode = list.item(index);
//                                TagsResponse tags = new TagsResponse();
//                                NodeList tagsProperties = tagsNode.getChildNodes();
//                                for (childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
//                                    Node tagsProperty = tagsProperties.item(childIndex);
//                                    if (tagsProperty.getNodeName().equals("account")) {
//                                        tags.setAccount(tagsProperty.getTextContent());
//                                    } else if (tagsProperty.getNodeName().equals("customer")) {
//                                        tags.setCustomer(tagsProperty.getTextContent());
//                                    } else if (tagsProperty.getNodeName().equals("domain")) {
//                                        tags.setDomain(tagsProperty.getTextContent());
//                                    } else if (tagsProperty.getNodeName().equals("domainid")) {
//                                        tags.setDomainId(tagsProperty.getTextContent());
//                                    } else if (tagsProperty.getNodeName().equals("key")) {
//                                        tags.setKey(tagsProperty.getTextContent());
//                                    } else if (tagsProperty.getNodeName().equals("project")) {
//                                        tags.setProject(tagsProperty.getTextContent());
//                                    } else if (tagsProperty.getNodeName().equals("projectid")) {
//                                        tags.setProjectId(tagsProperty.getTextContent());
//                                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
//                                        tags.setResourceId(tagsProperty.getTextContent());
//                                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
//                                        tags.setResourceType(tagsProperty.getTextContent());
//                                    } else if (tagsProperty.getNodeName().equals("value")) {
//                                        tags.setValue(tagsProperty.getTextContent());
//                                    }
//
//                                }
//                                tagss.add(tags);
//                            }
//                        }

                    }
                }
                vpc.setNetworks(networks);
                vpc.setServices(services);
                vpc.setTagss(tagss);
                vpcs.add(vpc);
            }
        }
        response.setVpcs(vpcs);
        return response;
    }

    /**
     * Delete VPC.
     *
     * @param vpcId
     * @return
     * @throws Exception
     */
    public DeleteVpcResponse deleteVPC(String vpcId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteVPC", null);
        arguments.add(new NameValuePair("id", vpcId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteVpcResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteVPCResponse object
     *
     * @param doc
     * @return
     */
    private DeleteVpcResponse getDeleteVpcResponse(Document doc) {
        DeleteVpcResponse response = new DeleteVpcResponse();

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
        
        // true if operation is executed successfully
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * Updates a VPC
     *
     * @param vpcId the id of the VPC
     * @param vpcName name of the VPC
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateVpcResponse updateVPC(String vpcId, String vpcName,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateVPC", optional);
        arguments.add(new NameValuePair("id", vpcId));
        arguments.add(new NameValuePair("name", vpcName));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateVpcResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateVPCResponse object
     *
     * @param doc
     * @return
     */
    private UpdateVpcResponse getUpdateVpcResponse(Document doc) {
        UpdateVpcResponse response = new UpdateVpcResponse();

        // get the id of the VPC      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get the owner of the VPC
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get the cidr the VPC
        list = doc.getElementsByTagName("cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidr(node.getTextContent());
        }

        // get the date this VPC was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // an alternate display text of the VPC
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get the domain name of the owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get the domain id of the VPC owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the name of the VPC
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcName(node.getTextContent());
        }

        // get the network domain of the VPC
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get the project name of the VPC
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the VPC
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // true VPC requires restart
        list = doc.getElementsByTagName("restartrequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRestartRequired(node.getTextContent());
        }

        // get state of the VPC. Can be Inactive/Enabled
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get vpc offering id the VPC is created from
        list = doc.getElementsByTagName("vpcofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcOfferingId(node.getTextContent());
        }

        // get zone id of the vpc
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get the name of the zone the VPC belongs to
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // get the list of networks belonging to the VPC
        list = doc.getElementsByTagName("network");
        if (list.getLength() > 0) {
            List<NetworkResponse> networks = new LinkedList<NetworkResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node networkNode = list.item(index);
                NetworkResponse network = new NetworkResponse();
                NodeList networkProperties = networkNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkProperties.getLength(); childIndex++) {
                    Node networkProperty = networkProperties.item(childIndex);

                    if (networkProperty.getNodeName().equals("id")) {
                        network.setId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("account")) {
                        network.setAccount(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("aclid")) {
                        network.setAclId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("acltype")) {
                        network.setAclType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("broadcastdomaintype")) {
                        network.setBroadcastDomainType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("broadcasturi")) {
                        network.setBroadcastUri(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("canusefordeploy")) {
                        network.setCanUseForDeploy(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("cidr")) {
                        network.setCidr(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("displaynetwork")) {
                        network.setDisplayNetwork(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("displaytext")) {
                        network.setDisplayText(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("dns1")) {
                        network.setDns1(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("dns2")) {
                        network.setDns2(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("domain")) {
                        network.setDomain(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("domainid")) {
                        network.setDomainId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("gateway")) {
                        network.setGateway(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("ip6cidr")) {
                        network.setIp6Cidr(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("ip6gateway")) {
                        network.setIp6Gateway(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("isdefault")) {
                        network.setIsDefault(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("ispersistent")) {
                        network.setIsPersistent(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("issystem")) {
                        network.setIsSystem(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("name")) {
                        network.setNetworkName(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("netmask")) {
                        network.setNetmask(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkcidr")) {
                        network.setNetworkCidr(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkdomain")) {
                        network.setNetworkDomain(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingavailability")) {
                        network.setNetworkOfferingAvailability(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingconservemode")) {
                        network.setNetworkOfferingConserveMode(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingdisplaytext")) {
                        network.setNetworkOfferingDisplayText(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingid")) {
                        network.setNetworkOfferingId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingname")) {
                        network.setNetworkOfferingName(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("physicalnetworkid")) {
                        network.setPhysicalNetworkId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("project")) {
                        network.setProject(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("projectid")) {
                        network.setProjectId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("related")) {
                        network.setRelated(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("reservediprange")) {
                        network.setReservedIpRange(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("restartrequired")) {
                        network.setRestartRequired(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("specifyipranges")) {
                        network.setSpecifyIpRanges(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("state")) {
                        network.setState(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("subdomainaccess")) {
                        network.setSubDomainAccess(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("traffictype")) {
                        network.setTrafficType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("type")) {
                        network.setNetworkType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("vlan")) {
                        network.setVlan(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("vpcid")) {
                        network.setVpcId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("zoneid")) {
                        network.setZoneId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("zonename")) {
                        network.setZoneName(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("service")) {
                        list = doc.getElementsByTagName("service");
                        if (list.getLength() > 0) {
                            List<ServiceResponse> services = new LinkedList<ServiceResponse>();
                            for (index = 0; index < list.getLength(); index++) {

                                Node serviceNode = list.item(index);
                                ServiceResponse service = new ServiceResponse();

                                NodeList serviceProperties = serviceNode.getChildNodes();
                                for (childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                                    networkProperty = list.item(childIndex);

                                    if (networkProperty.getNodeName().equals("name")) {
                                        service.setServiceName(networkProperty.getTextContent());
                                        services.add(service);
                                    } else if (networkProperty.getNodeName().equals("capability")) {

                                        list = doc.getElementsByTagName("capability");
                                        if (list.getLength() > 0) {
                                            List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                                            // TODO: values to be set properly
                                            for (index = 0; index < list.getLength(); index++) {
                                                Node capabilityNode = list.item(index);
                                                CapabilityResponse capability = new CapabilityResponse();

                                                NodeList capabilityProperties = capabilityNode.getChildNodes();
                                                for (childIndex = 0; childIndex < capabilityProperties.getLength(); childIndex++) {
                                                    networkProperty = list.item(childIndex);
                                                }
                                            }
                                            // capabilities.add(capability);
                                            // response.setCapabilitys(capabilities);        
                                        }

                                    } else if (networkProperty.getNodeName().equals("provider")) {

                                        list = doc.getElementsByTagName("provider");
                                        if (list.getLength() > 0) {
                                            List<ProviderResponse> providers = new LinkedList<ProviderResponse>();
                                            // TODO: values to be set properly 
                                            for (index = 0; index < list.getLength(); index++) {
                                                Node providerNode = list.item(index);
                                                ProviderResponse provider = new ProviderResponse();

                                                NodeList providerProperties = providerNode.getChildNodes();
                                                for (childIndex = 0; childIndex < providerProperties.getLength(); childIndex++) {
                                                    networkProperty = list.item(childIndex);
                                                }
                                            }
                                            // providers.add(provider);
                                            // response.setProviders(providers);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (networkProperty.getNodeName().equals("tags")) {
                        list = doc.getElementsByTagName("tags");
                        if (list.getLength() > 0) {
                            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                            for (index = 0; index < list.getLength(); index++) {
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
                                response.setTagss(tagss);
                            }
                        }
                    }

                    networks.add(network);
                }
                response.setNetworks(networks);
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
                                            capability.setCapabilityName(property.getTextContent());
                                        } else if (property.getNodeName().equals("value")) {
                                            capability.setCapabilityValue(property.getTextContent());
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

            // gets associated tag values for the vpc
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

        }
        return response;
    }

    /**
     * Restart VPC.
     *
     * @param vpcId
     * @return
     * @throws Exception
     */
    public RestartVpcResponse restartVPC(String vpcId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("restartVPC", null);
        arguments.add(new NameValuePair("id", vpcId));

        Document responseDocument = server.makeRequest(arguments);

        return getRestartVpcResponse(responseDocument);
    }

    /**
     * Converts XML document into RestartVPCResponse object
     *
     * @param doc
     * @return
     */
    private RestartVpcResponse getRestartVpcResponse(Document doc) {
        RestartVpcResponse response = new RestartVpcResponse();

        // get the id of the VPC      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get the owner of the VPC
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }
        
        // get the owner of the VPC
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get the cidr the VPC
        list = doc.getElementsByTagName("cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidr(node.getTextContent());
        }

        // get the date this VPC was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // an alternate display text of the VPC
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get the domain name of the owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get the domain id of the VPC owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the name of the VPC
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcName(node.getTextContent());
        }

        // get the network domain of the VPC
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get the project name of the VPC
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the VPC
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // true VPC requires restart
        list = doc.getElementsByTagName("restartrequired");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRestartRequired(node.getTextContent());
        }

        // get state of the VPC. Can be Inactive/Enabled
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get vpc offering id the VPC is created from
        list = doc.getElementsByTagName("vpcofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcOfferingId(node.getTextContent());
        }

        // get zone id of the vpc
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get the name of the zone the VPC belongs to
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // get the list of networks belonging to the VPC
        list = doc.getElementsByTagName("network");
        if (list.getLength() > 0) {
            List<NetworkResponse> networks = new LinkedList<NetworkResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node networkNode = list.item(index);
                NetworkResponse network = new NetworkResponse();
                NodeList networkProperties = networkNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkProperties.getLength(); childIndex++) {
                    Node networkProperty = networkProperties.item(childIndex);

                    if (networkProperty.getNodeName().equals("id")) {
                        network.setId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("account")) {
                        network.setAccount(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("aclid")) {
                        network.setAclId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("acltype")) {
                        network.setAclType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("broadcastdomaintype")) {
                        network.setBroadcastDomainType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("broadcasturi")) {
                        network.setBroadcastUri(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("canusefordeploy")) {
                        network.setCanUseForDeploy(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("cidr")) {
                        network.setCidr(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("displaynetwork")) {
                        network.setDisplayNetwork(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("displaytext")) {
                        network.setDisplayText(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("dns1")) {
                        network.setDns1(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("dns2")) {
                        network.setDns2(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("domain")) {
                        network.setDomain(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("domainid")) {
                        network.setDomainId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("gateway")) {
                        network.setGateway(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("ip6cidr")) {
                        network.setIp6Cidr(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("ip6gateway")) {
                        network.setIp6Gateway(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("isdefault")) {
                        network.setIsDefault(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("ispersistent")) {
                        network.setIsPersistent(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("issystem")) {
                        network.setIsSystem(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("name")) {
                        network.setNetworkName(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("netmask")) {
                        network.setNetmask(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkcidr")) {
                        network.setNetworkCidr(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkdomain")) {
                        network.setNetworkDomain(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingavailability")) {
                        network.setNetworkOfferingAvailability(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingconservemode")) {
                        network.setNetworkOfferingConserveMode(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingdisplaytext")) {
                        network.setNetworkOfferingDisplayText(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingid")) {
                        network.setNetworkOfferingId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("networkofferingname")) {
                        network.setNetworkOfferingName(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("physicalnetworkid")) {
                        network.setPhysicalNetworkId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("project")) {
                        network.setProject(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("projectid")) {
                        network.setProjectId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("related")) {
                        network.setRelated(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("reservediprange")) {
                        network.setReservedIpRange(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("restartrequired")) {
                        network.setRestartRequired(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("specifyipranges")) {
                        network.setSpecifyIpRanges(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("state")) {
                        network.setState(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("subdomainaccess")) {
                        network.setSubDomainAccess(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("traffictype")) {
                        network.setTrafficType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("type")) {
                        network.setNetworkType(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("vlan")) {
                        network.setVlan(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("vpcid")) {
                        network.setVpcId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("zoneid")) {
                        network.setZoneId(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("zonename")) {
                        network.setZoneName(networkProperty.getTextContent());
                    } else if (networkProperty.getNodeName().equals("service")) {
                        list = doc.getElementsByTagName("service");
                        if (list.getLength() > 0) {
                            List<ServiceResponse> services = new LinkedList<ServiceResponse>();
                            for (index = 0; index < list.getLength(); index++) {

                                Node serviceNode = list.item(index);
                                ServiceResponse service = new ServiceResponse();

                                NodeList serviceProperties = serviceNode.getChildNodes();
                                for (childIndex = 0; childIndex < serviceProperties.getLength(); childIndex++) {
                                    networkProperty = list.item(childIndex);

                                    if (networkProperty.getNodeName().equals("name")) {
                                        service.setServiceName(networkProperty.getTextContent());
                                        services.add(service);
                                    } else if (networkProperty.getNodeName().equals("capability")) {

                                        list = doc.getElementsByTagName("capability");
                                        if (list.getLength() > 0) {
                                            List<CapabilityResponse> capabilities = new LinkedList<CapabilityResponse>();
                                            // TODO: values to be set properly
                                            for (index = 0; index < list.getLength(); index++) {
                                                Node capabilityNode = list.item(index);
                                                CapabilityResponse capability = new CapabilityResponse();

                                                NodeList capabilityProperties = capabilityNode.getChildNodes();
                                                for (childIndex = 0; childIndex < capabilityProperties.getLength(); childIndex++) {
                                                    networkProperty = list.item(childIndex);
                                                }
                                            }
                                            // capabilities.add(capability);
                                            // response.setCapabilitys(capabilities);        
                                        }

                                    } else if (networkProperty.getNodeName().equals("provider")) {

                                        list = doc.getElementsByTagName("provider");
                                        if (list.getLength() > 0) {
                                            List<ProviderResponse> providers = new LinkedList<ProviderResponse>();
                                            // TODO: values to be set properly 
                                            for (index = 0; index < list.getLength(); index++) {
                                                Node providerNode = list.item(index);
                                                ProviderResponse provider = new ProviderResponse();

                                                NodeList providerProperties = providerNode.getChildNodes();
                                                for (childIndex = 0; childIndex < providerProperties.getLength(); childIndex++) {
                                                    networkProperty = list.item(childIndex);
                                                }
                                            }
                                            // providers.add(provider);
                                            // response.setProviders(providers);
                                        }
                                    }
                                }
                            }
                        }
                    } else if (networkProperty.getNodeName().equals("tags")) {
                        list = doc.getElementsByTagName("tags");
                        if (list.getLength() > 0) {
                            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                            for (index = 0; index < list.getLength(); index++) {
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
                                response.setTagss(tagss);
                            }
                        }
                    }

                    networks.add(network);
                }
                response.setNetworks(networks);
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
                                            capability.setCapabilityName(property.getTextContent());
                                        } else if (property.getNodeName().equals("value")) {
                                            capability.setCapabilityValue(property.getTextContent());
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

            // gets associated tag values for the vpc
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

        }
        return response;
    }

    /**
     * Creates a VPCOffering
     *
     * @param displayText displayText of the vpc server
     * @param name name of the vpc server
     * @param vpcOfferingId the id of the vpc offering
     * @param supportedServices services supported by the vpc offering
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateVpcOfferingResponse createVPCOffering(String displayText, String name,
            String vpcOfferingId, String supportedServices, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createVPCOffering", optional);
        arguments.add(new NameValuePair("displaytext", displayText));
        arguments.add(new NameValuePair("name", name));
        arguments.add(new NameValuePair("vpcofferingid", vpcOfferingId));
        arguments.add(new NameValuePair("supportedservices", supportedServices));
        Document responseDocument = server.makeRequest(arguments);

        return getCreateVpcOfferingResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVPCOfferingResponse object
     *
     * @param doc
     * @return
     */
    private CreateVpcOfferingResponse getCreateVpcOfferingResponse(Document doc) {
        CreateVpcOfferingResponse response = new CreateVpcOfferingResponse();

        // get the id of the VPC      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get the date this VPC was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // an alternate display text of the VPC
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // true if vpc offering is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // get the name of the VPC
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcName(node.getTextContent());
        }

        // get state of the VPC. Can be Inactive/Enabled
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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
                                        capability.setCapabilityName(property.getTextContent());
                                    } else if (property.getNodeName().equals("value")) {
                                        capability.setCapabilityValue(property.getTextContent());
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
        return response;
    }

    /**
     * Updates a VPCOffering
     *
     * @param vpcOfferingId the id of the vpc offering
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateVpcOfferingResponse updateVPCOffering(String vpcOfferingId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateVPCOffering", optional);
        arguments.add(new NameValuePair("id", vpcOfferingId));
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateVpcOfferingResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateVPCOfferingResponse object
     *
     * @param doc
     * @return
     */
    private UpdateVpcOfferingResponse getUpdateVpcOfferingResponse(Document doc) {
        UpdateVpcOfferingResponse response = new UpdateVpcOfferingResponse();

        // get the id of the VPC      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get the date this VPC was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // an alternate display text of the VPC
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // true if vpc offering is default, false otherwise
        list = doc.getElementsByTagName("isdefault");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDefault(node.getTextContent());
        }

        // get the name of the VPC
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcName(node.getTextContent());
        }

        // get state of the VPC. Can be Inactive/Enabled
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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
                                        capability.setCapabilityName(property.getTextContent());
                                    } else if (property.getNodeName().equals("value")) {
                                        capability.setCapabilityValue(property.getTextContent());
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
        return response;
    }

    /**
     * Delete VPCOffering.
     *
     * @param vpcOfferingId
     * @return
     * @throws Exception
     */
    public DeleteVpcOfferingResponse deleteVPCOffering(String vpcOfferingId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteVPCOffering", null);
        arguments.add(new NameValuePair("id", vpcOfferingId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteVpcOfferingResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteVPCOfferingResponse object
     *
     * @param doc
     * @return
     */
    private DeleteVpcOfferingResponse getDeleteVpcOfferingResponse(Document doc) {
        DeleteVpcOfferingResponse response = new DeleteVpcOfferingResponse();

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
     * List VPCOfferings.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListVpcOfferingsResponse listVPCOfferings(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVPCOfferings", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListVpcOfferingsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListVPCsResponse object
     *
     * @param doc
     * @return
     */
    private ListVpcOfferingsResponse getListVpcOfferingsResponse(Document doc) {
        ListVpcOfferingsResponse response = new ListVpcOfferingsResponse();

        NodeList list = doc.getElementsByTagName("vpcoffering");

        List<VpcOfferingResponse> vpcOfferings = new LinkedList<VpcOfferingResponse>();
        if (list.getLength() > 0) {
            for (int vpcOfferingIndex = 0; vpcOfferingIndex < list.getLength(); vpcOfferingIndex++) {
                Node vpcOfferingNode = list.item(vpcOfferingIndex);
                List<ServiceResponse> services = new LinkedList<ServiceResponse>();
                VpcOfferingResponse vpcOffering = new VpcOfferingResponse();
                NodeList vpcOfferingProperties = vpcOfferingNode.getChildNodes();
                for (int childIndex = 0; childIndex < vpcOfferingProperties.getLength(); childIndex++) {
                    Node property = vpcOfferingProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        vpcOffering.setVpcId(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        vpcOffering.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                        vpcOffering.setDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        vpcOffering.setIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        vpcOffering.setVpcOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        vpcOffering.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("service")) {
//                        list = doc.getElementsByTagName("service");
                        NodeList serviceProperties = property.getChildNodes();
                        
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
                vpcOffering.setServices(services);
                vpcOfferings.add(vpcOffering);
            }
        }
        response.setVpcOfferings(vpcOfferings);
        return response;
    }

    /**
     * Creates a PrivateGateway
     *
     * @param gateway the gateway of the Private gateway
     * @param ipAddress the IP address of the Private gateway
     * @param netmask the netmask of the Private gateway
     * @param vlan the Vlan for the private gateway
     * @param vpcId the VPC network belongs to
     * @param optional
     * @return
     * @throws Exception
     */
    public CreatePrivateGatewayResponse createPrivateGateway(String gateway, String ipAddress, String netmask,
            String vlan, String vpcId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createPrivateGateway", optional);
        arguments.add(new NameValuePair("gateway", gateway));
        arguments.add(new NameValuePair("ipaddress", ipAddress));
        arguments.add(new NameValuePair("netmask", netmask));
        arguments.add(new NameValuePair("vlan", vlan));
        arguments.add(new NameValuePair("vpcid", vpcId));
        Document responseDocument = server.makeRequest(arguments);

        return getCreatePrivateGatewayResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVPCResponse object
     *
     * @param doc
     * @return
     */
    private CreatePrivateGatewayResponse getCreatePrivateGatewayResponse(Document doc) {
        CreatePrivateGatewayResponse response = new CreatePrivateGatewayResponse();

        // get the id of the VPC      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateGatewayId(node.getTextContent());
        }

        // get the owner of the VPC
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateGatewayAccount(node.getTextContent());
        }

        // ACL Id set for private gateway
        list = doc.getElementsByTagName("aclid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclId(node.getTextContent());
        }

        // get the domain name of the owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get the domain id of the VPC owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the gateway
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get the private gateway's ip address
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get the private gateway's netmask
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetmask(node.getTextContent());
        }

        // get the physical network id
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get the project name of the private gateway
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the VPC
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get the Souce Nat enable status
        list = doc.getElementsByTagName("sourcenatsupported");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceNatSupported(node.getTextContent());
        }

        // get state of the VPC. Can be Inactive/Enabled
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get the vlan of the private gateway
        list = doc.getElementsByTagName("vlan");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlan(node.getTextContent());
        }

        // get VPC the private gateaway belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get zone id of the vpc
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get the name of the zone the VPC belongs to
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

        return response;
    }

    /**
     * List PrivateGateways.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListPrivateGatewaysResponse listPrivateGateways(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listPrivateGateways", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListPrivateGatewaysResponse(responseDocument);
    }

    /**
     * Converts XML document into ListPrivateGatewaysResponse object
     *
     * @param doc
     * @return
     */
    private ListPrivateGatewaysResponse getListPrivateGatewaysResponse(Document doc) {
        ListPrivateGatewaysResponse response = new ListPrivateGatewaysResponse();

        NodeList list = doc.getElementsByTagName("privategateway");
        List<PrivateGatewayResponse> privateGateways = new LinkedList<PrivateGatewayResponse>();
        if (list.getLength() > 0) {
            for (int privateGatewayIndex = 0; privateGatewayIndex < list.getLength(); privateGatewayIndex++) {
                Node privateGatewayNode = list.item(privateGatewayIndex);

                PrivateGatewayResponse privateGateway = new PrivateGatewayResponse();
                NodeList privateGatewayProperties = privateGatewayNode.getChildNodes();
                for (int childIndex = 0; childIndex < privateGatewayProperties.getLength(); childIndex++) {
                    Node property = privateGatewayProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        privateGateway.setPrivateGatewayId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        privateGateway.setPrivateGatewayAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("aclid")) {
                        privateGateway.setAclId(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        privateGateway.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        privateGateway.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        privateGateway.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        privateGateway.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                        privateGateway.setNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        privateGateway.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        privateGateway.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        privateGateway.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("sourcenatsupported")) {
                        privateGateway.setSourceNatSupported(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        privateGateway.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("vlan")) {
                        privateGateway.setVlan(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcid")) {
                        privateGateway.setVpcId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        privateGateway.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        privateGateway.setZoneName(property.getTextContent());
                    }
                }
                privateGateways.add(privateGateway);

            }

        }
        response.setPrivateGateways(privateGateways);
        return response;
    }

    /**
     * Deletes a Private gateway.
     *
     * @param privateGatewayId
     * @return
     * @throws Exception
     */
    public DeletePrivateGatewayResponse deletePrivateGateway(String privateGatewayId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deletePrivateGateway", null);
        arguments.add(new NameValuePair("id", privateGatewayId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeletePrivateGatewayResponse(responseDocument);
    }

    /**
     * Converts XML document into DeletePrivateGatewayResponse object
     *
     * @param doc
     * @return
     */
    private DeletePrivateGatewayResponse getDeletePrivateGatewayResponse(Document doc) {

        DeletePrivateGatewayResponse response = new DeletePrivateGatewayResponse();

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
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        return response;
    }

    /**
     * Creates a static route
     *
     * @param cidr cidr address id of the vpc server
     * @param gatewayId
     * @return
     * @throws Exception
     */
    public CreateStaticRouteResponse createStaticRoute(String cidr, String gatewayId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createStaticRoute", null);
        arguments.add(new NameValuePair("cidr", cidr));
        arguments.add(new NameValuePair("gatewayid", gatewayId));
        Document responseDocument = server.makeRequest(arguments);

        return getCreateStaticRouteResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateStaticRouteResponse object
     *
     * @param doc
     * @return
     */
    private CreateStaticRouteResponse getCreateStaticRouteResponse(Document doc) {
        CreateStaticRouteResponse response = new CreateStaticRouteResponse();

        // get the ID of static route      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStaticRouteId(node.getTextContent());
        }

        // get the account associated with the static route
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get static route CIDR
        list = doc.getElementsByTagName("cidr");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidr(node.getTextContent());
        }

        // get the domain associated with the static route
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get the ID of the domain associated with the static route
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get VPC gateway the route is created for
        list = doc.getElementsByTagName("gatewayid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGatewayId(node.getTextContent());
        }

        // get the project name of the static route
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the static route
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get state of the VPC. Can be Inactive/Enabled
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get VPC the static route belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // gets the list of resource tags associated with static route
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
     * Deletes a Static Route.
     *
     * @param staticRouteId
     * @return
     * @throws Exception
     */
    public DeleteStaticRouteResponse deleteStaticRoute(String staticRouteId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteStaticRoute", null);
        arguments.add(new NameValuePair("id", staticRouteId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteStaticRouteResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteStaticRouteResponse object
     *
     * @param doc
     * @return
     */
    private DeleteStaticRouteResponse getDeleteStaticRouteResponse(Document doc) {

        DeleteStaticRouteResponse response = new DeleteStaticRouteResponse();

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
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        return response;
    }

    /**
     * List StaticRoutes.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListStaticRoutesResponse listStaticRoutes(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listStaticRoutes", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListStaticRoutesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListStaticRoutesResponse object
     *
     * @param doc
     * @return
     */
    private ListStaticRoutesResponse getListStaticRoutesResponse(Document doc) {
        ListStaticRoutesResponse response = new ListStaticRoutesResponse();

        NodeList list = doc.getElementsByTagName("staticroute");
        List<StaticRouteResponse> staticRoutes = new LinkedList<StaticRouteResponse>();
        if (list.getLength() > 0) {
            for (int staticRouteIndex = 0; staticRouteIndex < list.getLength(); staticRouteIndex++) {
                Node staticRouteNode = list.item(staticRouteIndex);

                StaticRouteResponse staticRoute = new StaticRouteResponse();

                NodeList staticRouteProperties = staticRouteNode.getChildNodes();
                for (int childIndex = 0; childIndex < staticRouteProperties.getLength(); childIndex++) {
                    Node property = staticRouteProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        staticRoute.setStaticRouteId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        staticRoute.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("cidr")) {
                        staticRoute.setCidr(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        staticRoute.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        staticRoute.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("gatewayid")) {
                        staticRoute.setGatewayId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        staticRoute.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        staticRoute.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        staticRoute.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcid")) {
                        staticRoute.setVpcId(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        list = doc.getElementsByTagName("tags");
                        if (list.getLength() > 0) {
                            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
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
                                response.setTagss(tagss);
                            }
                        }
                    }
                }
                staticRoutes.add(staticRoute);
                response.setStaticRoutes(staticRoutes);
            }

        }
        return response;
    }
}
