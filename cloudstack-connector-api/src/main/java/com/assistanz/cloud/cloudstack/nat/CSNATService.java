package com.assistanz.cloud.cloudstack.nat;

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
public class CSNATService {

    private CloudStackServer server;

    public CSNATService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Enables static nat for given ip address
     *
     * @param ipAddressId the public IP address id for which static nat feature is being enabled
     * @param virtualMachineId the ID of the virtual machine for enabling static nat feature
     * @param optional
     * @return
     * @throws Exception
     */
    public EnableStaticNatResponse enableStaticNat(String ipAddressId, String virtualMachineId, HashMap<String,String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("enableStaticNat", optional);
        arguments.add(new NameValuePair("ipaddressid", ipAddressId));
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getEnableStaticNatResponse(responseDocument);
    }

    /**
     * Converts XML document into EnableStaticNatResponse object
     *
     * @param doc
     * @return
     */
    private EnableStaticNatResponse getEnableStaticNatResponse(Document doc) {
        EnableStaticNatResponse response = new EnableStaticNatResponse();

        // get displaytext from XML and set any text associated with the success or failure on enable StaticNat 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }

        //get success from XML and any text associated with the success or failure on enable StaticNat 
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
     * Creates an ip forwarding rule
     *
     * @param ipAddressId the public IP address id of the forwarding rule, already associated via associateIp
     * @param protocol the protocol for the rule. Valid values are TCP or UDP.
     * @param startPort the start port for the rule
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateIpForwardingRuleResponse createIpForwardingRule(String ipAddressId,
            String protocol, String startPort, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createIpForwardingRule", optional);
        arguments.add(new NameValuePair("ipaddressid", ipAddressId));
        arguments.add(new NameValuePair("protocol", protocol));
        arguments.add(new NameValuePair("startport", startPort));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateIpForwardingRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateIpForwardingRuleResponse object
     *
     * @param doc
     * @return
     */
    private CreateIpForwardingRuleResponse getCreateIpForwardingRuleResponse(Document doc) {
        CreateIpForwardingRuleResponse response = new CreateIpForwardingRuleResponse();

        // get id from XML and set as the ID of the port forwarding rule
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get cidrlist from XML and set as the cidr list of the port forwarding rule
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        // get ipaddress from XML and set as the ip address of the port forwarding rule
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get ipaddressid from XML and set as the ip address id of the port forwarding rule
        list = doc.getElementsByTagName("ipaddressid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpaddressId(node.getTextContent());
        }

        // get privateendport from XML and set as the private end port of the port forwarding rule
        list = doc.getElementsByTagName("privateendport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateEndPort(node.getTextContent());
        }

        // get privateport from XML and set as the private port of the port forwarding rule
        list = doc.getElementsByTagName("privateport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivatePort(node.getTextContent());
        }

        // get protocol from XML and set as the protocol of the port forwarding rule
        list = doc.getElementsByTagName("protocol");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProtocol(node.getTextContent());
        }

        // get publicendport from XML and set as the public end port of the port forwarding rule
        list = doc.getElementsByTagName("publicendport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicEndPort(node.getTextContent());
        }

        // get publicport from XML and set as the public port of the port forwarding rule
        list = doc.getElementsByTagName("publicport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicPort(node.getTextContent());
        }

        // get state from XML and set as the state of the port forwarding rule
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get virtualmachinedisplayname from XML and set as the virtual machine display name of the port forwarding rule
        list = doc.getElementsByTagName("virtualmachinedisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineDisplayName(node.getTextContent());
        }

        // get virtualmachineid from XML and set as the virtual machine display name of the port forwarding rule
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get virtualmachinename from XML and set as the virtual machine name of the port forwarding rule
        list = doc.getElementsByTagName("virtualmachinename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineName(node.getTextContent());
        }

        // get the virtualmachine ip address for the port forwarding rule
        list = doc.getElementsByTagName("vmguestip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmGuestIp(node.getTextContent());
        }

        // get the list of resource tags associated with the rule
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
     * Deletes an ip forwarding rule
     *
     * @param ipForwardingRuleId the id of the forwarding rule
     * @return
     * @throws Exception
     */
    public DeleteIpForwardingRuleResponse deleteIpForwardingRule(String ipForwardingRuleId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteIpForwardingRule", null);
        arguments.add(new NameValuePair("id", ipForwardingRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteIpForwardingRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteIpForwardingRuleResponse object
     *
     * @param doc
     * @return
     */
    private DeleteIpForwardingRuleResponse getDeleteIpForwardingRuleResponse(Document doc) {
        DeleteIpForwardingRuleResponse response = new DeleteIpForwardingRuleResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Ip Forwarding Rule 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Ip Forwarding Rule 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * List the ip forwarding rules
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListIpForwardingRulesResponse listIpForwardingRules(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listIpForwardingRules", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListIpForwardingRulesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListIpForwardingRuleResponse object
     *
     * @param doc
     * @return
     */
    private ListIpForwardingRulesResponse getListIpForwardingRulesResponse(Document doc) {
        ListIpForwardingRulesResponse response = new ListIpForwardingRulesResponse();

        NodeList list = doc.getElementsByTagName("ipforwardingrule");

        List<IpForwardingRuleResponse> ipForwardingRules = new LinkedList<IpForwardingRuleResponse>();
        if (list.getLength() > 0) {
            for (int ipForwardingRuleIndex = 0; ipForwardingRuleIndex < list.getLength(); ipForwardingRuleIndex++) {
                Node ipForwardingRuleNode = list.item(ipForwardingRuleIndex);

                IpForwardingRuleResponse ipForwardingRule = new IpForwardingRuleResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList ipForwardingRuleProperties = ipForwardingRuleNode.getChildNodes();
                for (int childIndex = 0; childIndex < ipForwardingRuleProperties.getLength(); childIndex++) {
                    Node property = ipForwardingRuleProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        ipForwardingRule.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        ipForwardingRule.setCidrList(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        ipForwardingRule.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddressid")) {
                        ipForwardingRule.setIpAddressId(property.getTextContent());
                    } else if (property.getNodeName().equals("privateendport")) {
                        ipForwardingRule.setPrivateEndPort(property.getTextContent());
                    } else if (property.getNodeName().equals("privateport")) {
                        ipForwardingRule.setPrivatePort(property.getTextContent());
                    } else if (property.getNodeName().equals("protocol")) {
                        ipForwardingRule.setProtocol(property.getTextContent());
                    } else if (property.getNodeName().equals("publicendport")) {
                        ipForwardingRule.setPublicEndPort(property.getTextContent());
                    } else if (property.getNodeName().equals("publicport")) {
                        ipForwardingRule.setPublicPort(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        ipForwardingRule.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachinedisplayname")) {
                        ipForwardingRule.setVirtualMachineDisplayName(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachineid")) {
                        ipForwardingRule.setVirtualMachineId(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachinename")) {
                        ipForwardingRule.setVirtualMachineName(property.getTextContent());
                    } else if (property.getNodeName().equals("vmguestip")) {
                        ipForwardingRule.setVmGuestIp(property.getTextContent());
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
                ipForwardingRule.setTagss(tagss);
                ipForwardingRules.add(ipForwardingRule);
            }
        }
        response.setIpForwardingRules(ipForwardingRules);
        return response;
    }

    /**
     * Disables static rule for given ip address
     *
     * @param ipAddressId
     * @return
     * @throws Exception
     */
    public DisableStaticNatResponse disableStaticNat(String ipAddressId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("disableStaticNat", null);
        arguments.add(new NameValuePair("ipaddressid", ipAddressId));

        Document responseDocument = server.makeRequest(arguments);

        return getDisableStaticNatResponse(responseDocument);
    }

    /**
     * Converts XML document into DisableStaticNatResponse object
     *
     * @param doc
     * @return
     */
    private DisableStaticNatResponse getDisableStaticNatResponse(Document doc) {
        DisableStaticNatResponse response = new DisableStaticNatResponse();

        // get displaytext from XML and set any text associated with the success or failure on Disable Ip Forwarding Rule 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on Disable Ip Forwarding Rule 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }
        
        //get success from XML and any text associated with the success or failure on Disable Ip Forwarding Rule 
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        return response;
    }
}
