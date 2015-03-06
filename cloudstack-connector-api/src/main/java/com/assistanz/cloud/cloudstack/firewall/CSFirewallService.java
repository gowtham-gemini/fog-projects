package com.assistanz.cloud.cloudstack.firewall;

import java.util.HashMap;
import java.util.LinkedList;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSFirewallService {

    private CloudStackServer server;

    public CSFirewallService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Lists all port forwarding rules for an IP address
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListPortForwardingRulesResponse listPortForwardingRules(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listPortForwardingRules", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListPortForwardingRulesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListPortForwardingRulesResponse object
     *
     * @param doc
     * @return
     */
    private ListPortForwardingRulesResponse getListPortForwardingRulesResponse(Document doc) {
        ListPortForwardingRulesResponse response = new ListPortForwardingRulesResponse();

        NodeList list = doc.getElementsByTagName("portforwardingrule");

        List<PortForwardingRulesResponse> portForwardingRules = new LinkedList<PortForwardingRulesResponse>();
        if (list.getLength() > 0) {
            for (int portForwardingRuleIndex = 0; portForwardingRuleIndex < list.getLength(); portForwardingRuleIndex++) {
                Node portForwardingRuleNode = list.item(portForwardingRuleIndex);

                PortForwardingRulesResponse portForwardingRule = new PortForwardingRulesResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList portForwardingRuleProperties = portForwardingRuleNode.getChildNodes();
                for (int childIndex = 0; childIndex < portForwardingRuleProperties.getLength(); childIndex++) {
                    Node property = portForwardingRuleProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        portForwardingRule.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        portForwardingRule.setCidrList(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        portForwardingRule.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddressid")) {
                        portForwardingRule.setIpAddressId(property.getTextContent());
                    } else if (property.getNodeName().equals("privateendport")) {
                        portForwardingRule.setPrivateEndPort(property.getTextContent());
                    } else if (property.getNodeName().equals("privateport")) {
                    	portForwardingRule.setPrivatePort(property.getTextContent());
                    } else if (property.getNodeName().equals("protocol")) {
                        portForwardingRule.setProtocol(property.getTextContent());
                    } else if (property.getNodeName().equals("publicendport")) {
                        portForwardingRule.setPublicEndPort(property.getTextContent());
                    } else if (property.getNodeName().equals("publicport")) {
                        portForwardingRule.setPublicPort(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        portForwardingRule.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachinedisplayname")) {
                        portForwardingRule.setVirtualMachineDisplayName(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachineid")) {
                        portForwardingRule.setVirtualMachineId(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachinename")) {
                        portForwardingRule.setVirtualMachineName(property.getTextContent());
                    } else if (property.getNodeName().equals("vmguestip")) {
                        portForwardingRule.setVmGuestIp(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        NodeList tagsProperties = property.getChildNodes();
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
                portForwardingRule.setTagss(tagss);
                portForwardingRules.add(portForwardingRule);
                response.setPortForwardingRules(portForwardingRules);
            }
        }
        return response;

    }

    /**
     * Creates a port forwarding rule
     *
     * @param portForwardingRuleIpAddressid the IP address id of the port forwarding rule
     * @param portForwardingRulePrivatePort the starting port of port forwarding rule's private port range
     * @param portForwardingRuleProtocol the protocol for the port fowarding rule. Valid values are TCP or UDP.
     * @param portForwardingRulePublicPort the s tarting port of port forwarding rule's public port range
     * @param portForwardingRuleVirtualMachineId the ID of the virtual machine for the port forwarding rule
     * @param optional
     * @return
     * @throws Exception
     */
    public CreatePortForwardingRuleResponse createPortForwardingRule(String portForwardingRuleIpAddressid,
            String portForwardingRulePrivatePort, String portForwardingRuleProtocol,
            String portForwardingRulePublicPort, String portForwardingRuleVirtualMachineId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createPortForwardingRule", optional);
        arguments.add(new NameValuePair("ipaddressid", portForwardingRuleIpAddressid));
        arguments.add(new NameValuePair("privateport", portForwardingRulePrivatePort));
        arguments.add(new NameValuePair("protocol", portForwardingRuleProtocol));
        arguments.add(new NameValuePair("publicport", portForwardingRulePublicPort));
        arguments.add(new NameValuePair("virtualmachineid", portForwardingRuleVirtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreatePortForwardingRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into CreatePortForwardingRuleResponse object
     *
     * @param doc
     * @return
     */
    private CreatePortForwardingRuleResponse getCreatePortForwardingRuleResponse(Document doc) {
        CreatePortForwardingRuleResponse response = new CreatePortForwardingRuleResponse();

        // get Id from XML and set the ID of the port forwarding rule     
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get cidrlist from XML and set the cidr list to forward traffic from   
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        // get ipaddress from XML and set the public ip address for the port forwarding rule   
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get ipaddressid from XML and set the public ip address id for the port forwarding rule
        list = doc.getElementsByTagName("ipaddressid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddressId(node.getTextContent());
        }

        // get privateendport from XML and set the ending port of port forwarding rule's private port range
        list = doc.getElementsByTagName("privateendport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateEndPort(node.getTextContent());
        }

        // get privateport from XML and set the starting port of port forwarding rule's private port range
        list = doc.getElementsByTagName("privateport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivatePort(node.getTextContent());
        }

        // get protocol from XML and set the protocol of the port forwarding rule
        list = doc.getElementsByTagName("protocol");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProtocol(node.getTextContent());
        }

        // get publicendport from XML and set the ending port of port forwarding rule's private port range
        list = doc.getElementsByTagName("publicendport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicEndPort(node.getTextContent());
        }

        // get publicport from XML and set the starting port of port forwarding rule's public port range
        list = doc.getElementsByTagName("publicport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicPort(node.getTextContent());
        }

        // get state from XML and set the state of the rule
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get virtualmachinedisplayname from XML and set the VM display name for the port forwarding rule
        list = doc.getElementsByTagName("virtualmachinedisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineDisplayName(node.getTextContent());
        }

        // get virtualmachineid from XML and set the VM ID for the port forwarding rule
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get virtualmachinename from XML and set the VM name for the port forwarding rule
        list = doc.getElementsByTagName("virtualmachinename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineName(node.getTextContent());
        }

        // get the vm ip address for the port forwarding rule
        list = doc.getElementsByTagName("vmguestip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmGuestIp(node.getTextContent());
        }
        // get virtualmachinename from XML and set the VM name for the port forwarding rule
    	list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        // gets the list of resource tags associated with the rule
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
     * Deletes a port forwarding rule
     *
     * @param portForwardingRuleId the ID of the port forwarding rule
     * @return
     * @throws Exception
     */
    public DeletePortForwardingRuleResponse deletePortForwardingRule(String portForwardingRuleId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deletePortForwardingRule", null);
        arguments.add(new NameValuePair("id", portForwardingRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeletePortForwardingRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into DeletePortForwardingRuleResponse object
     *
     * @param doc
     * @return
     */
    private DeletePortForwardingRuleResponse getDeletePortForwardingRuleResponse(Document doc) {
        DeletePortForwardingRuleResponse response = new DeletePortForwardingRuleResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete PortForwarding Rule
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on Delete PortForwarding Rule 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }
        
        //get success from XML and any text associated with the success or failure on Delete PortForwarding Rule 
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * Updates a port forwarding rule
     *
     * @param portForwardingRuleIpAddressid the IP address id of the port forwarding rule
     * @param portForwardingRulePrivatePort the starting port of port forwarding rule's private port range
     * @param portForwardingRuleProtocol the protocol for the port fowarding rule. Valid values are TCP or UDP.
     * @param portForwardingRulePublicPort the s tarting port of port forwarding rule's public port range
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdatePortForwardingRuleResponse updatePortForwardingRule(String portForwardingRuleIpAddressid,
            String portForwardingRulePrivatePort, String portForwardingRuleProtocol,
            String portForwardingRulePublicPort, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("UpdatePortForwardingRule", optional);
        arguments.add(new NameValuePair("ipaddressid", portForwardingRuleIpAddressid));
        arguments.add(new NameValuePair("privateport", portForwardingRulePrivatePort));
        arguments.add(new NameValuePair("protocol", portForwardingRuleProtocol));
        arguments.add(new NameValuePair("publicport", portForwardingRulePublicPort));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdatePortForwardingRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdatePortForwardingRuleResponse object
     *
     * @param doc
     * @return
     */
    private UpdatePortForwardingRuleResponse getUpdatePortForwardingRuleResponse(Document doc) {
        UpdatePortForwardingRuleResponse response = new UpdatePortForwardingRuleResponse();

        // get Id from XML and set the ID of the port forwarding rule     
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get cidrlist from XML and set the cidr list to forward traffic from   
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        // get ipaddress from XML and set the public ip address for the port forwarding rule   
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get ipaddressid from XML and set the public ip address id for the port forwarding rule
        list = doc.getElementsByTagName("ipaddressid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddressId(node.getTextContent());
        }

        // get privateendport from XML and set the ending port of port forwarding rule's private port range
        list = doc.getElementsByTagName("privateendport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateEndPort(node.getTextContent());
        }

        // get privateport from XML and set the starting port of port forwarding rule's private port range
        list = doc.getElementsByTagName("privateport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivatePort(node.getTextContent());
        }

        // get protocol from XML and set the protocol of the port forwarding rule
        list = doc.getElementsByTagName("protocol");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProtocol(node.getTextContent());
        }

        // get publicendport from XML and set the ending port of port forwarding rule's private port range
        list = doc.getElementsByTagName("publicendport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicEndPort(node.getTextContent());
        }

        // get publicport from XML and set the starting port of port forwarding rule's public port range
        list = doc.getElementsByTagName("publicport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicPort(node.getTextContent());
        }

        // get state from XML and set the state of the rule
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get virtualmachinedisplayname from XML and set the VM display name for the port forwarding rule
        list = doc.getElementsByTagName("virtualmachinedisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineDisplayName(node.getTextContent());
        }

        // get virtualmachineid from XML and set the VM ID for the port forwarding rule
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get virtualmachinename from XML and set the VM name for the port forwarding rule
        list = doc.getElementsByTagName("virtualmachinename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineName(node.getTextContent());
        }

        // get the vm ip address for the port forwarding rule
        list = doc.getElementsByTagName("vmguestip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmGuestIp(node.getTextContent());
        }

        // gets the list of resource tags associated with the rule
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
     * Creates a firewall rule for a given ip address
     *
     * @param ipAddressId
     * @param firewallProtocol the protocol for the firewall rule. Valid values are TCP/UDP/ICMP.
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateFirewallRuleResponse createFirewallRule(String ipAddressId,
            String firewallProtocol, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createFirewallRule", optional);
        arguments.add(new NameValuePair("ipaddressid", ipAddressId));
        arguments.add(new NameValuePair("protocol", firewallProtocol));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateFirewallRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateFirewallRuleResponse object
     *
     * @param doc
     * @return
     */
    private CreateFirewallRuleResponse getCreateFirewallRuleResponse(Document doc) {
        CreateFirewallRuleResponse response = new CreateFirewallRuleResponse();

        // get Id from XML and set the ID of the firewall rule    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get cidrlist from XML and set the cidr list to forward traffic from   
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        // get endport from XML and set the ending port of firewall rule's port range   
        list = doc.getElementsByTagName("endport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndPort(node.getTextContent());
        }

        // get icmpcode from XML and set the error code for this icmp message
        list = doc.getElementsByTagName("icmpcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIcmpCode(node.getTextContent());
        }

        // get icmptype from XML and set the type of the icmp message being sent
        list = doc.getElementsByTagName("icmptype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIcmpType(node.getTextContent());
        }

        // get ipaddress from XML and set the public ip address for the port forwarding rule
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get ipaddressid from XML and set the public ip address id for the port forwarding rule
        list = doc.getElementsByTagName("ipaddressid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddressId(node.getTextContent());
        }

        // get the network id of the firewall rule
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get protocol from XML and set the protocol of the firewall rule
        list = doc.getElementsByTagName("protocol");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProtocol(node.getTextContent());
        }

        // get startport from XML and set the startport of the firewall rule
        list = doc.getElementsByTagName("startport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartPort(node.getTextContent());
        }

        // get state from XML and set the state of the firewall rule
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }
        
        // get state from XML and set the state of the firewall rule
    	list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // gets the list of resource tags associated with the rule
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
     * Deletes a firewall rule
     *
     * @param firewallRuleId the ID of the firewall rule
     * @return
     * @throws Exception
     */
    public DeleteFirewallRuleResponse deleteFirewallRule(String firewallRuleId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteFirewallRule", null);
        arguments.add(new NameValuePair("id", firewallRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteFirewallRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteFirewallRuleResponse object
     *
     * @param doc
     * @return
     */
    private DeleteFirewallRuleResponse getDeleteFirewallRuleResponse(Document doc) {
        DeleteFirewallRuleResponse response = new DeleteFirewallRuleResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Firewall Rule
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on Delete Firewall Rule
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all firewall rules for an IP address.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListFirewallRulesResponse listFirewallRules(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listFirewallRules", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListFirewallRulesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListFirewallRulesResponse object
     *
     * @param doc
     * @return
     */
    private ListFirewallRulesResponse getListFirewallRulesResponse(Document doc) {
        ListFirewallRulesResponse response = new ListFirewallRulesResponse();

        NodeList list = doc.getElementsByTagName("firewallrule");

        List<FirewallRuleResponse> firewallRules = new LinkedList<FirewallRuleResponse>();
        if (list.getLength() > 0) {
            for (int firewallRuleIndex = 0; firewallRuleIndex < list.getLength(); firewallRuleIndex++) {
                Node firewallRuleNode = list.item(firewallRuleIndex);

                FirewallRuleResponse firewallRule = new FirewallRuleResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList firewallRuleProperties = firewallRuleNode.getChildNodes();
                for (int childIndex = 0; childIndex < firewallRuleProperties.getLength(); childIndex++) {
                    Node property = firewallRuleProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        firewallRule.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        firewallRule.setCidrList(property.getTextContent());
                    } else if (property.getNodeName().equals("endport")) {
                        firewallRule.setEndPort(property.getTextContent());
                    } else if (property.getNodeName().equals("icmpcode")) {
                        firewallRule.setIcmpCode(property.getTextContent());
                    } else if (property.getNodeName().equals("icmptype")) {
                        firewallRule.setIcmpType(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        firewallRule.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddressid")) {
                        firewallRule.setIpAddressId(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        firewallRule.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("protocol")) {
                        firewallRule.setProtocol(property.getTextContent());
                    } else if (property.getNodeName().equals("startport")) {
                        firewallRule.setStartPort(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        firewallRule.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        NodeList tagsProperties = property.getChildNodes();
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
                firewallRule.setTagss(tagss);
                firewallRules.add(firewallRule);
                response.setFirewallRules(firewallRules);
            }
        }
        return response;
    }

    /**
     * Creates a egress firewall rule for a given network
     *
     * @param networkId
     * @param firewallProtocol the protocol for the firewall rule. Valid values are TCP/UDP/ICMP.
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateEgressFirewallRuleResponse createEgressFirewallRule(String networkId,
            String firewallProtocol, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createEgressFirewallRule", optional);
        arguments.add(new NameValuePair("networkid", networkId));
        arguments.add(new NameValuePair("protocol", firewallProtocol));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateEgressFirewallRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateEgressFirewallRuleResponse object
     *
     * @param doc
     * @return
     */
    private CreateEgressFirewallRuleResponse getCreateEgressFirewallRuleResponse(Document doc) {
        CreateEgressFirewallRuleResponse response = new CreateEgressFirewallRuleResponse();

        // get Id from XML and set the ID of the firewall rule    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get cidrlist from XML and set the cidr list to forward traffic from   
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        // get endport from XML and set the ending port of firewall rule's port range   
        list = doc.getElementsByTagName("endport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndPort(node.getTextContent());
        }

        // get icmpcode from XML and set the error code for this icmp message
        list = doc.getElementsByTagName("icmpcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIcmpCode(node.getTextContent());
        }

        // get icmptype from XML and set the type of the icmp message being sent
        list = doc.getElementsByTagName("icmptype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIcmpType(node.getTextContent());
        }

        // get ipaddress from XML and set the public ip address for the port forwarding rule
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get ipaddressid from XML and set the public ip address id for the port forwarding rule
        list = doc.getElementsByTagName("ipaddressid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddressId(node.getTextContent());
        }

        // get the network id of the firewall rule
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get protocol from XML and set the protocol of the firewall rule
        list = doc.getElementsByTagName("protocol");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProtocol(node.getTextContent());
        }

        // get startport from XML and set the startport of the firewall rule
        list = doc.getElementsByTagName("startport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartPort(node.getTextContent());
        }

        // get state from XML and set the state of the firewall rule
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // gets the list of resource tags associated with the rule
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
     * Deletes an egress firewall rule
     *
     * @param firewallRuleId the ID of the firewall rule
     * @return
     * @throws Exception
     */
    public DeleteEgressFirewallRuleResponse deleteEgressFirewallRule(String firewallRuleId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteEgressFirewallRule", null);
        arguments.add(new NameValuePair("id", firewallRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteEgressFirewallRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteEgressFirewallRuleResponse object
     *
     * @param doc
     * @return
     */
    private DeleteEgressFirewallRuleResponse getDeleteEgressFirewallRuleResponse(Document doc) {
        DeleteEgressFirewallRuleResponse response = new DeleteEgressFirewallRuleResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Firewall Rule
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on Delete Firewall Rule
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all egress firewall rules for network id.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListEgressFirewallRulesResponse listEgressFirewallRules(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listEgressFirewallRules", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListEgressFirewallRulesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListFirewallRulesResponse object
     *
     * @param doc
     * @return
     */
    private ListEgressFirewallRulesResponse getListEgressFirewallRulesResponse(Document doc) {
        ListEgressFirewallRulesResponse response = new ListEgressFirewallRulesResponse();

        NodeList list = doc.getElementsByTagName("firewallrule");

        List<EgressRuleResponse> egressFirewallRules = new LinkedList<EgressRuleResponse>();
        if (list.getLength() > 0) {
            for (int egressFirewallRuleIndex = 0; egressFirewallRuleIndex < list.getLength(); egressFirewallRuleIndex++) {
                Node egressFirewallRuleNode = list.item(egressFirewallRuleIndex);

                EgressRuleResponse egressFirewallRule = new EgressRuleResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList egressFirewallRuleProperties = egressFirewallRuleNode.getChildNodes();
                for (int childIndex = 0; childIndex < egressFirewallRuleProperties.getLength(); childIndex++) {
                    Node property = egressFirewallRuleProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        egressFirewallRule.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        egressFirewallRule.setCidrList(property.getTextContent());
                    } else if (property.getNodeName().equals("endport")) {
                        egressFirewallRule.setEndPort(property.getTextContent());
                    } else if (property.getNodeName().equals("icmpcode")) {
                        egressFirewallRule.setIcmpCode(property.getTextContent());
                    } else if (property.getNodeName().equals("icmptype")) {
                        egressFirewallRule.setIcmpType(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        egressFirewallRule.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddressid")) {
                        egressFirewallRule.setIpAddressId(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        egressFirewallRule.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("protocol")) {
                        egressFirewallRule.setProtocol(property.getTextContent());
                    } else if (property.getNodeName().equals("startport")) {
                        egressFirewallRule.setStartPort(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        egressFirewallRule.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        NodeList tagsProperties = property.getChildNodes();
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
                egressFirewallRule.setTagss(tagss);
                egressFirewallRules.add(egressFirewallRule);
                response.setEgressFirewallRules(egressFirewallRules);
            }
        }
        return response;
    }

    /**
     * Adds a SRX firewall device
     *
     * @param srxNetworkDeviceType supports only JuniperSRXFirewall
     * @param srxPassword Credentials to reach SRX firewall device
     * @param srxPhysicalNetworkId the Physical Network ID
     * @param srxUrl URL of the SRX appliance.
     * @param srxUserName Credentials to reach SRX firewall device
     * @param optional
     * @return
     * @throws Exception
     */
    public AddSrxFirewallResponse addSrxFirewall(String srxNetworkDeviceType,
            String srxPassword, String srxPhysicalNetworkId,
            String srxUrl, String srxUserName,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addSrxFirewall", optional);
        arguments.add(new NameValuePair("networkdevicetype", srxNetworkDeviceType));
        arguments.add(new NameValuePair("password", srxPassword));
        arguments.add(new NameValuePair("physicalnetworkid", srxPhysicalNetworkId));
        arguments.add(new NameValuePair("url", srxUrl));
        arguments.add(new NameValuePair("username", srxUserName));

        Document responseDocument = server.makeRequest(arguments);

        return getAddSrxFirewallResponse(responseDocument);
    }

    /**
     * Converts XML document into AddSrxFirewallResponse object
     *
     * @param doc
     * @return
     */
    private AddSrxFirewallResponse getAddSrxFirewallResponse(Document doc) {
        AddSrxFirewallResponse response = new AddSrxFirewallResponse();

        // get fwdevicecapacity from XML and set the device capacity the SRX firewall   
        NodeList list = doc.getElementsByTagName("fwdevicecapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceCapacity(node.getTextContent());
        }

        // get fwdeviceid from XML and set the device id of the SRX firewall   
        list = doc.getElementsByTagName("fwdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceId(node.getTextContent());
        }

        // get fwdevicename from XML and set the device name of the SRX firewall   
        list = doc.getElementsByTagName("fwdevicename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceName(node.getTextContent());
        }

        // get fwdevicestate from XML and set the device state of the SRX firewall   
        list = doc.getElementsByTagName("fwdevicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceState(node.getTextContent());
        }

        // get ipaddress from XML and set the ip address of the SRX firewall   
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallIpAddress(node.getTextContent());
        }

        // get numretries from XML and set the number of times to retry requests to the external firewall
        list = doc.getElementsByTagName("numretries");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallNumRetries(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network to which this SRX firewall belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPhysicalNetworkId(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network to which this SRX firewall belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPhysicalNetworkId(node.getTextContent());
        }

        // get privateinterface from XML and set the private interface of the external firewall
        list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPrivateInterface(node.getTextContent());
        }

        // get privatezone from XML and set the private security zone of the external firewall
        list = doc.getElementsByTagName("privatezone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPrivateZone(node.getTextContent());
        }

        // get provider from XML and set name of the provider
        list = doc.getElementsByTagName("provider");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallProviderName(node.getTextContent());
        }

        // get publicinterface from XML and set the public interface of the external firewall
        list = doc.getElementsByTagName("publicinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPublicInterface(node.getTextContent());
        }

        // get publiczone from XML and set the public security zone of the external firewall
        list = doc.getElementsByTagName("publiczone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPublicZone(node.getTextContent());
        }

        // get timeout from XML and set the timeout (in seconds) for requests to the external firewall
        list = doc.getElementsByTagName("timeout");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallTimeOut(node.getTextContent());
        }

        // get usageinterface from XML and set the usage interface of the external firewall
        list = doc.getElementsByTagName("usageinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallUsageInterface(node.getTextContent());
        }

        // get username from XML and set the username that's used to log in to the external firewall
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallUserName(node.getTextContent());
        }

        // get zoneid from XML and set the zone ID of the external firewall
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * SRX firewall device ID
     *
     * @param srxFierwallDeviceId
     * @param optional
     * @return
     * @throws Exception
     */
    public ConfigureSrxFirewallResponse configureSrxFirewall(String srxFierwallDeviceId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("configureSrxFirewall", optional);
        arguments.add(new NameValuePair("fwdeviceid", srxFierwallDeviceId));

        Document responseDocument = server.makeRequest(arguments);

        return getConfigureSrxFirewallResponse(responseDocument);
    }

    /**
     * Converts XML document into ConfigureSrxFirewallResponse object
     *
     * @param doc
     * @return
     */
    private ConfigureSrxFirewallResponse getConfigureSrxFirewallResponse(Document doc) {
        ConfigureSrxFirewallResponse response = new ConfigureSrxFirewallResponse();

        // get fwdevicecapacity from XML and set the device capacity the SRX firewall   
        NodeList list = doc.getElementsByTagName("fwdevicecapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceCapacity(node.getTextContent());
        }

        // get fwdeviceid from XML and set the device id of the SRX firewall   
        list = doc.getElementsByTagName("fwdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceId(node.getTextContent());
        }

        // get fwdevicename from XML and set the device name of the SRX firewall   
        list = doc.getElementsByTagName("fwdevicename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceName(node.getTextContent());
        }

        // get fwdevicestate from XML and set the device state of the SRX firewall   
        list = doc.getElementsByTagName("fwdevicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceState(node.getTextContent());
        }

        // get ipaddress from XML and set the ip address of the SRX firewall   
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallIpAddress(node.getTextContent());
        }

        // get numretries from XML and set the number of times to retry requests to the external firewall
        list = doc.getElementsByTagName("numretries");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallNumRetries(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network to which this SRX firewall belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPhysicalNetworkId(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network to which this SRX firewall belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPhysicalNetworkId(node.getTextContent());
        }

        // get privateinterface from XML and set the private interface of the external firewall
        list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPrivateInterface(node.getTextContent());
        }

        // get privatezone from XML and set the private security zone of the external firewall
        list = doc.getElementsByTagName("privatezone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPrivateZone(node.getTextContent());
        }

        // get provider from XML and set name of the provider
        list = doc.getElementsByTagName("provider");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallProviderName(node.getTextContent());
        }

        // get publicinterface from XML and set the public interface of the external firewall
        list = doc.getElementsByTagName("publicinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPublicInterface(node.getTextContent());
        }

        // get publiczone from XML and set the public security zone of the external firewall
        list = doc.getElementsByTagName("publiczone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPublicZone(node.getTextContent());
        }

        // get timeout from XML and set the timeout (in seconds) for requests to the external firewall
        list = doc.getElementsByTagName("timeout");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallTimeOut(node.getTextContent());
        }

        // get usageinterface from XML and set the usage interface of the external firewall
        list = doc.getElementsByTagName("usageinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallUsageInterface(node.getTextContent());
        }

        // get username from XML and set the username that's used to log in to the external firewall
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallUserName(node.getTextContent());
        }

        // get zoneid from XML and set the zone ID of the external firewall
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * lists SRX firewall devices in a physical network
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListSrxFirewallsResponse listSrxFirewalls(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listSrxFirewalls", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListSrxFirewallsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListSrxFirewallsResponse object
     *
     * @param doc
     * @return
     */
    private ListSrxFirewallsResponse getListSrxFirewallsResponse(Document doc) {
        ListSrxFirewallsResponse response = new ListSrxFirewallsResponse();

        // get fwdevicecapacity from XML and set the device capacity the SRX firewall   
        NodeList list = doc.getElementsByTagName("fwdevicecapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceCapacity(node.getTextContent());
        }

        // get fwdeviceid from XML and set the device id of the SRX firewall   
        list = doc.getElementsByTagName("fwdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceId(node.getTextContent());
        }

        // get fwdevicename from XML and set the device name of the SRX firewall   
        list = doc.getElementsByTagName("fwdevicename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceName(node.getTextContent());
        }

        // get fwdevicestate from XML and set the device state of the SRX firewall   
        list = doc.getElementsByTagName("fwdevicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallDeviceState(node.getTextContent());
        }

        // get ipaddress from XML and set the ip address of the SRX firewall   
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallIpAddress(node.getTextContent());
        }

        // get numretries from XML and set the number of times to retry requests to the external firewall
        list = doc.getElementsByTagName("numretries");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallNumRetries(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network to which this SRX firewall belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPhysicalNetworkId(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network to which this SRX firewall belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPhysicalNetworkId(node.getTextContent());
        }

        // get privateinterface from XML and set the private interface of the external firewall
        list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPrivateInterface(node.getTextContent());
        }

        // get privatezone from XML and set the private security zone of the external firewall
        list = doc.getElementsByTagName("privatezone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPrivateZone(node.getTextContent());
        }

        // get provider from XML and set name of the provider
        list = doc.getElementsByTagName("provider");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallProviderName(node.getTextContent());
        }

        // get publicinterface from XML and set the public interface of the external firewall
        list = doc.getElementsByTagName("publicinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPublicInterface(node.getTextContent());
        }

        // get publiczone from XML and set the public security zone of the external firewall
        list = doc.getElementsByTagName("publiczone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallPublicZone(node.getTextContent());
        }

        // get timeout from XML and set the timeout (in seconds) for requests to the external firewall
        list = doc.getElementsByTagName("timeout");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallTimeOut(node.getTextContent());
        }

        // get usageinterface from XML and set the usage interface of the external firewall
        list = doc.getElementsByTagName("usageinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallUsageInterface(node.getTextContent());
        }

        // get username from XML and set the username that's used to log in to the external firewall
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallUserName(node.getTextContent());
        }

        // get zoneid from XML and set the zone ID of the external firewall
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSrxFirewallZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * delete a SRX firewall device
     *
     * @param srxFierwallDeviceId the srx firewall device ID
     * @return
     * @throws Exception
     */
    public DeleteSrxFirewallResponse deleteSrxFirewall(String srxFierwallDeviceId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteSrxFirewall", null);
        arguments.add(new NameValuePair("id", srxFierwallDeviceId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteSrxFirewallResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteSrxFirewallResponse object
     *
     * @param doc
     * @return
     */
    private DeleteSrxFirewallResponse getDeleteSrxFirewallResponse(Document doc) {
        DeleteSrxFirewallResponse response = new DeleteSrxFirewallResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete srx firewall 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on Delete srx firewall 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

}
