package com.assistanz.cloud.cloudstack.networkacl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.TagsResponse;

/**
 *
 * @author Santhosh
 *
 */
public class CSNetworkACLService {

    private CloudStackServer server;

    public CSNetworkACLService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates a ACL rule in the given network (the network has to belong to VPC).
     *
     * @param protocol the protocol for the ACL rule
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateNetworkAclResponse createNetworkACL(String protocol, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createNetworkACL", optional);
        arguments.add(new NameValuePair("protocol", protocol));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateNetworkAclResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateNetworkAclResponse object
     *
     * @param doc
     * @return
     */
    private CreateNetworkAclResponse getCreateNetworkAclResponse(Document doc) {
        CreateNetworkAclResponse response = new CreateNetworkAclResponse();

        // get the ID of the ACL Item
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclItemId(node.getTextContent());
        }

        //get the ID of the ACL this item belongs to
        list = doc.getElementsByTagName("aclid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclId(node.getTextContent());
        }
        
        //get the ID of the ACL this item belongs to
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        //get Action of ACL Item. Allow/Deny
        list = doc.getElementsByTagName("action");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAction(node.getTextContent());
        }

        //get the cidr list to forward traffic from
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        //get the ending port of ACL's port range
        list = doc.getElementsByTagName("endport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndPort(node.getTextContent());
        }

        //get error code for this icmp message
        list = doc.getElementsByTagName("icmpcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIcmpCode(node.getTextContent());
        }

        //get type of the icmp message being sent
        list = doc.getElementsByTagName("icmptype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIcmpType(node.getTextContent());
        }

        //get Number of the ACL Item
        list = doc.getElementsByTagName("number");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNumber(node.getTextContent());
        }

        //get the protocol of the ACL
        list = doc.getElementsByTagName("protocol");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProtocol(node.getTextContent());
        }

        //get the starting port of ACL's port range
        list = doc.getElementsByTagName("startport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartPort(node.getTextContent());
        }

        //get the state of the rule
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        //get the traffic type for the ACL
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTrafficType(node.getTextContent());
        }

        // gets the list of resource tags associated with the network ACLs
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
     * Updates ACL Item with specified Id.
     *
     * @param networkAclItemId the ID of the network ACL Item
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateNetworkAclItemResponse updateNetworkACLItem(String networkAclItemId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateNetworkACLItem", optional);
        arguments.add(new NameValuePair("id", networkAclItemId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateNetworkAclItemResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateNetworkAclItemResponse object
     *
     * @param doc
     * @return
     */
    private UpdateNetworkAclItemResponse getUpdateNetworkAclItemResponse(Document doc) {
        UpdateNetworkAclItemResponse response = new UpdateNetworkAclItemResponse();

        // get the ID of the ACL Item
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclItemId(node.getTextContent());
        }

        //get the ID of the ACL this item belongs to
        list = doc.getElementsByTagName("aclid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclId(node.getTextContent());
        }

        //get Action of ACL Item. Allow/Deny
        list = doc.getElementsByTagName("action");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAction(node.getTextContent());
        }

        //get the cidr list to forward traffic from
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        //get the ending port of ACL's port range
        list = doc.getElementsByTagName("endport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndPort(node.getTextContent());
        }

        //get error code for this icmp message
        list = doc.getElementsByTagName("icmpcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIcmpCode(node.getTextContent());
        }

        //get type of the icmp message being sent
        list = doc.getElementsByTagName("icmptype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIcmpType(node.getTextContent());
        }

        //get Number of the ACL Item
        list = doc.getElementsByTagName("number");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNumber(node.getTextContent());
        }

        //get the protocol of the ACL
        list = doc.getElementsByTagName("protocol");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProtocol(node.getTextContent());
        }

        //get the starting port of ACL's port range
        list = doc.getElementsByTagName("startport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartPort(node.getTextContent());
        }

        //get the state of the rule
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        //get the traffic type for the ACL
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTrafficType(node.getTextContent());
        }
        
        //get the jobid for the ACL
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // gets the list of resource tags associated with the network ACLs
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
     * Deletes a Network ACL
     *
     * @param networkAclId the ID of the network ACL
     * @return
     * @throws Exception
     */
    public DeleteNetworkAclResponse deleteNetworkACL(String networkAclId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteNetworkACL", null);
        arguments.add(new NameValuePair("id", networkAclId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteNetworkAclResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteNetworkAclResponse object
     *
     * @param doc
     * @return
     */
    private DeleteNetworkAclResponse getDeleteNetworkAclResponse(Document doc) {
        DeleteNetworkAclResponse response = new DeleteNetworkAclResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete VPN
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete VPN
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }
        
        //get success from XML and any text associated with the success or failure on deleting Delete VPN
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all network ACL items.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNetworkAclsResponse listNetworkACLs(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNetworkACLs", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListNetworkAclsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListNetworkAclsResponse object
     *
     * @param doc
     * @return
     */
    private ListNetworkAclsResponse getListNetworkAclsResponse(Document doc) {
        ListNetworkAclsResponse response = new ListNetworkAclsResponse();

        NodeList list = doc.getElementsByTagName("networkacl");   

        List<NetworkAclResponse> networkAcls = new LinkedList<NetworkAclResponse>();
        if (list.getLength() > 0) {
            for (int networkAclIndex = 0; networkAclIndex < list.getLength(); networkAclIndex++) {
                Node networkAclNode = list.item(networkAclIndex);

                NetworkAclResponse networkAcl = new NetworkAclResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList networkAclProperties = networkAclNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkAclProperties.getLength(); childIndex++) {
                    Node property = networkAclProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        networkAcl.setAclItemId(property.getTextContent());
                    } else if (property.getNodeName().equals("aclid")) {
                        networkAcl.setAclId(property.getTextContent());
                    } else if (property.getNodeName().equals("action")) {
                        networkAcl.setAction(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        networkAcl.setCidrList(property.getTextContent());
                    } else if (property.getNodeName().equals("endport")) {
                        networkAcl.setEndPort(property.getTextContent());
                    } else if (property.getNodeName().equals("icmpcode")) {
                        networkAcl.setIcmpCode(property.getTextContent());
                    } else if (property.getNodeName().equals("icmptype")) {
                        networkAcl.setIcmpType(property.getTextContent());
                    } else if (property.getNodeName().equals("number")) {
                        networkAcl.setNumber(property.getTextContent());
                    } else if (property.getNodeName().equals("protocol")) {
                        networkAcl.setProtocol(property.getTextContent());
                    } else if (property.getNodeName().equals("startport")) {
                        networkAcl.setStartPort(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        networkAcl.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                        networkAcl.setTrafficType(property.getTextContent());
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
                networkAcl.setTagss(tagss);
                networkAcls.add(networkAcl);
                response.setNetworkAcls(networkAcls);
            }
        }
        return response;
    }

    /**
     * Creates a Network ACL for the given VPC.
     *
     * @param networkAclListName Name of the network ACL List
     * @param vpcId Id of the VPC associated with this network ACL List
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateNetworkAclListResponse createNetworkACLList(String networkAclListName,
            String vpcId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createNetworkACLList", optional);
        arguments.add(new NameValuePair("name", networkAclListName));
        arguments.add(new NameValuePair("vpcid", vpcId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateNetworkAclListResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateNetworkAclListResponse object
     *
     * @param doc
     * @return
     */
    private CreateNetworkAclListResponse getCreateNetworkAclListResponse(Document doc) {
        CreateNetworkAclListResponse response = new CreateNetworkAclListResponse();

        // get the ID of the ACL
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclId(node.getTextContent());
        }

        //get Description of the ACL
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclDescription(node.getTextContent());
        }
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        //get the Name of the ACL
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAclName(node.getTextContent());
        }

        //get Id of the VPC this ACL is associated with
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }
        return response;
    }

    /**
     * Deletes a Network ACL
     *
     * @param networkAclId the ID of the network ACL
     * @return
     * @throws Exception
     */
    public DeleteNetworkAclListResponse deleteNetworkACLList(String networkAclId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteNetworkACLList", null);
        arguments.add(new NameValuePair("id", networkAclId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteNetworkAclListResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteNetworkAclListResponse object
     *
     * @param doc
     * @return
     */
    private DeleteNetworkAclListResponse getDeleteNetworkAclListResponse(Document doc) {

        DeleteNetworkAclListResponse response = new DeleteNetworkAclListResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete VPN
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete VPN
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }
        
        //get success from XML and any text associated with the success or failure on deleting Delete VPN
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * Replaces ACL associated with a Network or private gateway
     *
     * @param networkAclId the ID of the network ACL
     * @return
     * @throws Exception
     */
    public ReplaceNetworkAclListResponse replaceNetworkACLList(String networkAclId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("replaceNetworkACLList", optional);
        arguments.add(new NameValuePair("aclid", networkAclId));

        Document responseDocument = server.makeRequest(arguments);

        return getReplaceNetworkAclListResponse(responseDocument);
    }

    /**
     * Converts XML document into ReplaceNetworkAclListResponse object
     *
     * @param doc
     * @return
     */
    private ReplaceNetworkAclListResponse getReplaceNetworkAclListResponse(Document doc) {

        ReplaceNetworkAclListResponse response = new ReplaceNetworkAclListResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete VPN
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        
        //get success from XML and any text associated with the success or failure on deleting Delete VPN
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
     * Lists all network ACLs.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNetworkAclListsResponse listNetworkACLLists(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNetworkACLLists", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListNetworkAclListsResponse(responseDocument);
    }

    private ListNetworkAclListsResponse getListNetworkAclListsResponse(Document doc) {
        ListNetworkAclListsResponse response = new ListNetworkAclListsResponse();

        // List all network ACLs
        NodeList list = doc.getElementsByTagName("networkacllist");
        
        NodeList countList = doc.getElementsByTagName("count");
        if (countList.getLength() > 0) {
            Node node = countList.item(0); 
            response.setCount(node.getTextContent());
        }
        
        List<NetworkAclListResponse> networkAclLists = new LinkedList<NetworkAclListResponse>();

        if (list.getLength() > 0) {
           
            for (int index = 0; index < list.getLength(); index++) {
                Node networkAclListNode = list.item(index);
                
                NetworkAclListResponse networkAclList = new NetworkAclListResponse();

                NodeList networkAclListProperties = networkAclListNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkAclListProperties.getLength(); childIndex++) {
                    Node property = networkAclListProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        networkAclList.setAclId(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        networkAclList.setAclDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        networkAclList.setAclName(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcid")) {
                        networkAclList.setVpcId(property.getTextContent());
                    }
                }
                networkAclLists.add(networkAclList);
            }
        }

        response.setNetworkAclLists(networkAclLists);
        return response;
    }

}
