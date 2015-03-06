package com.assistanz.cloud.cloudstack.networkacl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.TagsResponse;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

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
            response.setId(node.getTextContent());
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
            response.setId(node.getTextContent());
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
                        networkAcl.setId(property.getTextContent());
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
            response.setId(node.getTextContent());
        }

        //get Description of the ACL
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        //get the Name of the ACL
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        //get Id of the VPC this ACL is associated with
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
     * Retrieves the current status of asynchronous job for networkACL.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public NetworkAclJobResultResponse networkACLJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getNetworkAclJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into NetworkAclJobResultResponse object
     *
     * @param doc
     * @return
     */
    private NetworkAclJobResultResponse getNetworkAclJobResultResponse(Document doc) {
        NetworkAclJobResultResponse response = new NetworkAclJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("networkacl")) {
                    List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("aclid")) {
                            response.setAclId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("action")) {
                            response.setAction(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("cidrlist")) {
                            response.setCidrList(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("endport")) {
                            response.setEndPort(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("icmpcode")) {
                            response.setIcmpCode(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("icmptype")) {
                            response.setIcmpType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("number")) {
                            response.setNumber(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("protocol")) {
                            response.setProtocol(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("startport")) {
                            response.setStartPort(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("traffictype")) {
                            response.setTrafficType(childNodeProperty.getTextContent());
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
     * Retrieves the current status of asynchronous job for networkacllist.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public NetworkAclListJobResultResponse networkAclList(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getNetworkAclListJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into NetworKAclListJobResultResponse object
     *
     * @param doc
     * @return
     */
    private NetworkAclListJobResultResponse getNetworkAclListJobResultResponse(Document doc) {
        NetworkAclListJobResultResponse response = new NetworkAclListJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("networkacllist")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("description")) {
                            response.setDescription(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vpcid")) {
                            response.setVpcId(childNodeProperty.getTextContent());
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

        List<NetworkAclListResponse> networkAclLists = new LinkedList<NetworkAclListResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node networkAclListNode = list.item(index);

                NetworkAclListResponse networkAclList = new NetworkAclListResponse();

                NodeList networkAclListProperties = networkAclListNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkAclListProperties.getLength(); childIndex++) {
                    Node property = networkAclListProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        networkAclList.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        networkAclList.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        networkAclList.setName(property.getTextContent());
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
