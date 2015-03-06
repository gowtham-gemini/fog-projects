package com.assistanz.cloud.cloudstack.loadbalancer;

import com.assistanz.cloud.cloudstack.AffinityGroupResponse;
import java.util.HashMap;
import java.util.LinkedList;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.TagsResponse;
import com.assistanz.cloud.cloudstack.StickinessPolicyResponse;
import com.assistanz.cloud.cloudstack.HealthCheckPolicyResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;

import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Santhosh
 */
public class CSLoadBalancerService {

    private CloudStackServer server;

    public CSLoadBalancerService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates a load balancer rule
     *
     * @param algorithm load balancer algorithm(source,roundrobin,leastconn)
     * @param name name of the load balancer rule
     * @param privatePort the private port of the private ip address/virtual machine
     * @param publicPort the public port from where the network traffic will be load balanced from
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateLoadBalancerRuleResponse createLoadBalancerRule(String algorithm, String name, String privatePort,
            String publicPort, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createLoadBalancerRule", optional);
        arguments.add(new NameValuePair("algorithm", algorithm));
        arguments.add(new NameValuePair("name", name));
        arguments.add(new NameValuePair("privateport", privatePort));
        arguments.add(new NameValuePair("publicport", publicPort));
        Document responseDocument = server.makeRequest(arguments);

        return getCreateLoadBalancerRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private CreateLoadBalancerRuleResponse getCreateLoadBalancerRuleResponse(Document doc) {
        CreateLoadBalancerRuleResponse response = new CreateLoadBalancerRuleResponse();

        // get the load balancer rule ID   
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get the account of the load balancer rule
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get the load balancer algorithm
        list = doc.getElementsByTagName("algorithm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAlgorithm(node.getTextContent());
        }

        // get the cidr list to forward traffic from
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        // get the description of the load balancer
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get the domain of the load balancer rule
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get the domain ID of the load balancer rule
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the name of the load balancer
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get the id of the guest network the lb rule belongs to
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get the private port
        list = doc.getElementsByTagName("privateport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivatePort(node.getTextContent());
        }

        // get the project name of the load balancer
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the load balancer
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // the public ip address
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get the public ip address id
        list = doc.getElementsByTagName("publicipid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIpId(node.getTextContent());
        }

        // get the public port
        list = doc.getElementsByTagName("publicport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicPort(node.getTextContent());
        }

        // get the state of the rule
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get the id of the zone the rule belongs to
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get the list of resource tags associated with load balancer
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
     * Deletes a load balancer rule.
     *
     * @param loadBalancerRuleId
     * @return
     * @throws Exception
     */
    public DeleteLoadBalancerRuleResponse deleteLoadBalancerRule(String loadBalancerRuleId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteLoadBalancerRule", null);
        arguments.add(new NameValuePair("id", loadBalancerRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteLoadBalancerRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private DeleteLoadBalancerRuleResponse getDeleteLoadBalancerRuleResponse(Document doc) {
        DeleteLoadBalancerRuleResponse response = new DeleteLoadBalancerRuleResponse();

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
     * Removes a virtual machine or a list of virtual machines from a load balancer rule.
     *
     * @param loadBalancerRuleId The ID of the load balancer rule
     * @param virtualMachineIds the list of IDs of the virtual machines
     * @return
     * @throws Exception
     */
    public RemoveFromLoadBalancerRuleResponse removeFromLoadBalancerRule(String loadBalancerRuleId,
            String virtualMachineIds) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("removeFromLoadBalancerRule", null);
        arguments.add(new NameValuePair("id", loadBalancerRuleId));
        arguments.add(new NameValuePair("virtualmachineids", virtualMachineIds));

        Document responseDocument = server.makeRequest(arguments);

        return getRemoveFromLoadBalancerRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into RemoveFromLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private RemoveFromLoadBalancerRuleResponse getRemoveFromLoadBalancerRuleResponse(Document doc) {
        RemoveFromLoadBalancerRuleResponse response = new RemoveFromLoadBalancerRuleResponse();

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
     * Assigns virtual machine or a list of virtual machines to a load balancer rule
     *
     * @param loadBalancerRuleId The ID of the load balancer rule
     * @param virtualMachineIds the list of IDs of the virtual machines
     * @return
     * @throws Exception
     */
    public AssignToLoadBalancerRuleResponse assignToLoadBalancerRule(String loadBalancerRuleId,
            String virtualMachineIds) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("assignToLoadBalancerRule", null);
        arguments.add(new NameValuePair("id", loadBalancerRuleId));
        arguments.add(new NameValuePair("virtualmachineids", virtualMachineIds));

        Document responseDocument = server.makeRequest(arguments);

        return getAssignToLoadBalancerRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into AssignToLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private AssignToLoadBalancerRuleResponse getAssignToLoadBalancerRuleResponse(Document doc) {
        AssignToLoadBalancerRuleResponse response = new AssignToLoadBalancerRuleResponse();

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
     * Creates a Load Balancer stickiness policy
     *
     * @param lbruleId the ID of the load balancer rule
     * @param methodName name of the LB Stickiness policy method
     * @param name name of the LB Stickiness policy
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateLBStickinessPolicyResponse createLBStickinessPolicy(String lbruleId, String methodName,
            String name, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createLBStickinessPolicy", optional);
        arguments.add(new NameValuePair("lbruleid", lbruleId));
        arguments.add(new NameValuePair("methodname", methodName));
        arguments.add(new NameValuePair("name", name));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateLBStickinessPolicyResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private CreateLBStickinessPolicyResponse getCreateLBStickinessPolicyResponse(Document doc) {
        CreateLBStickinessPolicyResponse response = new CreateLBStickinessPolicyResponse();

        // get the account of the Stickiness policy
        NodeList list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get the description of the Stickiness policy
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get the domain of the Stickiness policy
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get the domain ID of the Stickiness policy
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the lbrule ID of the Stickiness policy
        list = doc.getElementsByTagName("lbruleid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLbRuleId(node.getTextContent());
        }

        // get the name of the Stickiness policy
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get the state of the policy
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get the id of the zone the Stickiness policy belongs to
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get the list of stickinesspolicies
        list = doc.getElementsByTagName("stickinesspolicy");
        if (list.getLength() > 0) {
            List<StickinessPolicyResponse> stickinessPolicies = new LinkedList<StickinessPolicyResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node stickinessPolicyNode = list.item(index);
                StickinessPolicyResponse stickinessPolicy = new StickinessPolicyResponse();
                NodeList stickinessPolicyProperties = stickinessPolicyNode.getChildNodes();
                for (int childIndex = 0; childIndex < stickinessPolicyProperties.getLength(); childIndex++) {
                    Node stickinessPolicyProperty = stickinessPolicyProperties.item(childIndex);
                    if (stickinessPolicyProperty.getNodeName().equals("id")) {
                        stickinessPolicy.setId(stickinessPolicyProperty.getTextContent());
                    } else if (stickinessPolicyProperty.getNodeName().equals("description")) {
                        stickinessPolicy.setDescription(stickinessPolicyProperty.getTextContent());
                    } else if (stickinessPolicyProperty.getNodeName().equals("methodname")) {
                        stickinessPolicy.setMethodName(stickinessPolicyProperty.getTextContent());
                    } else if (stickinessPolicyProperty.getNodeName().equals("name")) {
                        stickinessPolicy.setName(stickinessPolicyProperty.getTextContent());
                    } else if (stickinessPolicyProperty.getNodeName().equals("params")) {
                        stickinessPolicy.setParams(stickinessPolicyProperty.getTextContent());
                    } else if (stickinessPolicyProperty.getNodeName().equals("state")) {
                        stickinessPolicy.setState(stickinessPolicyProperty.getTextContent());
                    }

                }
                stickinessPolicies.add(stickinessPolicy);
                response.setStickinessPolicies(stickinessPolicies);
            }
        }

        return response;
    }

    /**
     * Deletes a LB stickiness policy.
     *
     * @param lbStickinessPolicyId
     * @return
     * @throws Exception
     */
    public DeleteLBStickinessPolicyResponse deleteLBStickinessPolicy(String lbStickinessPolicyId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteLoadBalancerRule", null);
        arguments.add(new NameValuePair("id", lbStickinessPolicyId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteLBStickinessPolicyResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteLBStickinessPolicyResponse object
     *
     * @param doc
     * @return
     */
    private DeleteLBStickinessPolicyResponse getDeleteLBStickinessPolicyResponse(Document doc) {
        DeleteLBStickinessPolicyResponse response = new DeleteLBStickinessPolicyResponse();

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
     * Lists load balancer rules.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListLoadBalancerRulesResponse listLoadBalancerRules(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listLoadBalancerRules", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListLoadBalancerRulesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListLoadBalancerRulesResponse object
     *
     * @param doc
     * @return
     */
    private ListLoadBalancerRulesResponse getListLoadBalancerRulesResponse(Document doc) {
        ListLoadBalancerRulesResponse response = new ListLoadBalancerRulesResponse();

        NodeList list = doc.getElementsByTagName("loadbalancerrule");

        List<LoadBalancerRuleResponse> loadBalancerRules = new LinkedList<LoadBalancerRuleResponse>();
        if (list.getLength() > 0) {
            for (int loadBalancerRuleIndex = 0; loadBalancerRuleIndex < list.getLength(); loadBalancerRuleIndex++) {
                Node loadBalancerRuleNode = list.item(loadBalancerRuleIndex);

                LoadBalancerRuleResponse loadBalancerRule = new LoadBalancerRuleResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList loadBalancerRuleProperties = loadBalancerRuleNode.getChildNodes();
                for (int childIndex = 0; childIndex < loadBalancerRuleProperties.getLength(); childIndex++) {
                    Node property = loadBalancerRuleProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        loadBalancerRule.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        loadBalancerRule.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("algorithm")) {
                        loadBalancerRule.setAlgorithm(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        loadBalancerRule.setCidrList(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        loadBalancerRule.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        loadBalancerRule.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        loadBalancerRule.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        loadBalancerRule.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        loadBalancerRule.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("privateport")) {
                        loadBalancerRule.setPrivatePort(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        loadBalancerRule.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        loadBalancerRule.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("publicip")) {
                        loadBalancerRule.setPublicIp(property.getTextContent());
                    } else if (property.getNodeName().equals("publicport")) {
                        loadBalancerRule.setPublicPort(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        loadBalancerRule.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        loadBalancerRule.setZoneId(property.getTextContent());
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
                loadBalancerRule.setTagss(tagss);
                loadBalancerRules.add(loadBalancerRule);
                response.setLoadBalancerRules(loadBalancerRules);
            }
        }
        return response;
    }

    /**
     * Lists LBStickiness policies.
     *
     * @param lbRuleId
     * @param optional
     * @return
     * @throws Exception
     */
    public ListLBStickinessPoliciesResponse listLBStickinessPolicies(String lbRuleId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listLBStickinessPolicies", optional);
        arguments.add(new NameValuePair("lbruleid", lbRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getListLBStickinessPoliciesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListLBStickinessPoliciesResponse object
     *
     * @param doc
     * @return
     */
    private ListLBStickinessPoliciesResponse getListLBStickinessPoliciesResponse(Document doc) {
        ListLBStickinessPoliciesResponse response = new ListLBStickinessPoliciesResponse();

        NodeList list = doc.getElementsByTagName("lbstickinesspolicy");

        List<LBStickinessPolicyResponse> lbStickinessPolicies = new LinkedList<LBStickinessPolicyResponse>();
        if (list.getLength() > 0) {
            for (int lbStickinessPolicyIndex = 0; lbStickinessPolicyIndex < list.getLength(); lbStickinessPolicyIndex++) {
                Node lbStickinessPolicyNode = list.item(lbStickinessPolicyIndex);

                LBStickinessPolicyResponse lbStickinessPolicy = new LBStickinessPolicyResponse();
                List<StickinessPolicyResponse> stickinessPolicies = new LinkedList<StickinessPolicyResponse>();
                NodeList lbStickinessPolicyProperties = lbStickinessPolicyNode.getChildNodes();
                for (int childIndex = 0; childIndex < lbStickinessPolicyProperties.getLength(); childIndex++) {
                    Node property = lbStickinessPolicyProperties.item(childIndex);

                    if (property.getNodeName().equals("account")) {
                        lbStickinessPolicy.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        lbStickinessPolicy.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        lbStickinessPolicy.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        lbStickinessPolicy.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("lbruleid")) {
                        lbStickinessPolicy.setLbRuleId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        lbStickinessPolicy.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        lbStickinessPolicy.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        lbStickinessPolicy.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("stickinesspolicy")) {
                        NodeList stickinessPolicyProperties = property.getChildNodes();
                        if (stickinessPolicyProperties.getLength() > 0) {
                            StickinessPolicyResponse stickinessPolicy = new StickinessPolicyResponse();
                            for (int stickinessPolicyIndex = 0; stickinessPolicyIndex < stickinessPolicyProperties.getLength(); stickinessPolicyIndex++) {
                                Node stickinessPolicyProperty = stickinessPolicyProperties.item(stickinessPolicyIndex);
                                if (stickinessPolicyProperty.getNodeName().equals("id")) {
                                    stickinessPolicy.setId(stickinessPolicyProperty.getTextContent());
                                } else if (stickinessPolicyProperty.getNodeName().equals("description")) {
                                    stickinessPolicy.setDescription(stickinessPolicyProperty.getTextContent());
                                } else if (stickinessPolicyProperty.getNodeName().equals("methodname")) {
                                    stickinessPolicy.setMethodName(stickinessPolicyProperty.getTextContent());
                                } else if (stickinessPolicyProperty.getNodeName().equals("name")) {
                                    stickinessPolicy.setName(stickinessPolicyProperty.getTextContent());
                                } else if (stickinessPolicyProperty.getNodeName().equals("params")) {
                                    stickinessPolicy.setParams(stickinessPolicyProperty.getTextContent());
                                } else if (stickinessPolicyProperty.getNodeName().equals("state")) {
                                    stickinessPolicy.setState(stickinessPolicyProperty.getTextContent());
                                }
                            }
                            stickinessPolicies.add(stickinessPolicy);
                        }
                    }

                }
                lbStickinessPolicy.setStickinessPolicies(stickinessPolicies);
                lbStickinessPolicies.add(lbStickinessPolicy);
                response.setLbStickinessPolicies(lbStickinessPolicies);
            }
        }
        return response;
    }

    /**
     * Lists load balancer HealthCheck policies.
     *
     * @param lbRuleId
     * @param optional
     * @return
     * @throws Exception
     */
    public ListLBHealthCheckPoliciesResponse listLBHealthCheckPolicies(String lbRuleId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listLBHealthCheckPolicies", optional);
        arguments.add(new NameValuePair("lbruleid", lbRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getListLBHealthCheckPoliciesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListLBHealthCheckPoliciesResponse object
     *
     * @param doc
     * @return
     */
    private ListLBHealthCheckPoliciesResponse getListLBHealthCheckPoliciesResponse(Document doc) {
        ListLBHealthCheckPoliciesResponse response = new ListLBHealthCheckPoliciesResponse();

        NodeList list = doc.getElementsByTagName("lbhealthcheckpolicy");

        List<LBHealthCheckPolicyResponse> lbHealthCheckPolicies = new LinkedList<LBHealthCheckPolicyResponse>();
        if (list.getLength() > 0) {
            for (int lbHealthCheckPolicyIndex = 0; lbHealthCheckPolicyIndex < list.getLength(); lbHealthCheckPolicyIndex++) {
                Node lbHealthCheckPolicyNode = list.item(lbHealthCheckPolicyIndex);

                LBHealthCheckPolicyResponse lbHealthCheckPolicy = new LBHealthCheckPolicyResponse();
                List<HealthCheckPolicyResponse> healthCheckPolicies = new LinkedList<HealthCheckPolicyResponse>();
                NodeList lbHealthCheckPolicyProperties = lbHealthCheckPolicyNode.getChildNodes();
                for (int childIndex = 0; childIndex < lbHealthCheckPolicyProperties.getLength(); childIndex++) {
                    Node property = lbHealthCheckPolicyProperties.item(childIndex);

                    if (property.getNodeName().equals("account")) {
                        lbHealthCheckPolicy.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        lbHealthCheckPolicy.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        lbHealthCheckPolicy.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("lbruleid")) {
                        lbHealthCheckPolicy.setLbRuleId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        lbHealthCheckPolicy.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("healthcheckpolicy")) {
                        NodeList healthCheckPolicyProperties = property.getChildNodes();
                        if (healthCheckPolicyProperties.getLength() > 0) {
                            HealthCheckPolicyResponse healthCheckPolicy = new HealthCheckPolicyResponse();
                            for (int healthCheckPolicyIndex = 0; healthCheckPolicyIndex < healthCheckPolicyProperties.getLength(); healthCheckPolicyIndex++) {
                                Node healthCheckPolicyProperty = healthCheckPolicyProperties.item(healthCheckPolicyIndex);
                                if (healthCheckPolicyProperty.getNodeName().equals("id")) {
                                    healthCheckPolicy.setId(healthCheckPolicyProperty.getTextContent());
                                } else if (healthCheckPolicyProperty.getNodeName().equals("description")) {
                                    healthCheckPolicy.setDescription(healthCheckPolicyProperty.getTextContent());
                                } else if (healthCheckPolicyProperty.getNodeName().equals("healthcheckinterval")) {
                                    healthCheckPolicy.setHealthCheckInterval(healthCheckPolicyProperty.getTextContent());
                                } else if (healthCheckPolicyProperty.getNodeName().equals("healthcheckthresshold")) {
                                    healthCheckPolicy.setHealthCheckThresshold(healthCheckPolicyProperty.getTextContent());
                                } else if (healthCheckPolicyProperty.getNodeName().equals("pingpath")) {
                                    healthCheckPolicy.setPingPath(healthCheckPolicyProperty.getTextContent());
                                } else if (healthCheckPolicyProperty.getNodeName().equals("responsetime")) {
                                    healthCheckPolicy.setResponseTime(healthCheckPolicyProperty.getTextContent());
                                } else if (healthCheckPolicyProperty.getNodeName().equals("state")) {
                                    healthCheckPolicy.setState(healthCheckPolicyProperty.getTextContent());
                                } else if (healthCheckPolicyProperty.getNodeName().equals("unhealthcheckthreshold")) {
                                    healthCheckPolicy.setUnHealthCheckThresshold(healthCheckPolicyProperty.getTextContent());
                                }
                            }
                            healthCheckPolicies.add(healthCheckPolicy);
                        }
                    }

                }
                lbHealthCheckPolicy.setHealthCheckPolicies(healthCheckPolicies);
                lbHealthCheckPolicies.add(lbHealthCheckPolicy);
                response.setLbHealthCheckPolicies(lbHealthCheckPolicies);
            }
        }
        return response;
    }

    /**
     * Creates a Load Balancer health check policy
     *
     * @param lbRuleId the ID of the load balancer rule
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateLBHealthCheckPolicyResponse createLBHealthCheckPolicy(String lbRuleId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createLBHealthCheckPolicy", optional);
        arguments.add(new NameValuePair("lbruleid", lbRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateLBHealthCheckPolicyResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateLBHealthCheckPolicyResponse object
     *
     * @param doc
     * @return
     */
    private CreateLBHealthCheckPolicyResponse getCreateLBHealthCheckPolicyResponse(Document doc) {
        CreateLBHealthCheckPolicyResponse response = new CreateLBHealthCheckPolicyResponse();

        // get the account of the HealthCheck policy
        NodeList list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get the domain of the HealthCheck policy
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get the domain ID of the HealthCheck policy
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the lbrule ID of the HealthCheck policy
        list = doc.getElementsByTagName("lbruleid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLbRuleId(node.getTextContent());
        }

        // get the zoneid the Stickiness policy belongs to
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get the list of healthcheckpolicies
        list = doc.getElementsByTagName("healthcheckpolicy");
        if (list.getLength() > 0) {
            List<HealthCheckPolicyResponse> healthCheckPolicies = new LinkedList<HealthCheckPolicyResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node healthCheckPolicyNode = list.item(index);
                HealthCheckPolicyResponse healthCheckPolicy = new HealthCheckPolicyResponse();
                NodeList healthCheckPolicyProperties = healthCheckPolicyNode.getChildNodes();
                for (int childIndex = 0; childIndex < healthCheckPolicyProperties.getLength(); childIndex++) {
                    Node healthCheckPolicyProperty = healthCheckPolicyProperties.item(childIndex);
                    if (healthCheckPolicyProperty.getNodeName().equals("id")) {
                        healthCheckPolicy.setId(healthCheckPolicyProperty.getTextContent());
                    } else if (healthCheckPolicyProperty.getNodeName().equals("description")) {
                        healthCheckPolicy.setDescription(healthCheckPolicyProperty.getTextContent());
                    } else if (healthCheckPolicyProperty.getNodeName().equals("healthcheckinterval")) {
                        healthCheckPolicy.setHealthCheckInterval(healthCheckPolicyProperty.getTextContent());
                    } else if (healthCheckPolicyProperty.getNodeName().equals("healthcheckthresshold")) {
                        healthCheckPolicy.setHealthCheckThresshold(healthCheckPolicyProperty.getTextContent());
                    } else if (healthCheckPolicyProperty.getNodeName().equals("pingpath")) {
                        healthCheckPolicy.setPingPath(healthCheckPolicyProperty.getTextContent());
                    } else if (healthCheckPolicyProperty.getNodeName().equals("responsetime")) {
                        healthCheckPolicy.setResponseTime(healthCheckPolicyProperty.getTextContent());
                    } else if (healthCheckPolicyProperty.getNodeName().equals("state")) {
                        healthCheckPolicy.setState(healthCheckPolicyProperty.getTextContent());
                    } else if (healthCheckPolicyProperty.getNodeName().equals("unhealthcheckthreshold")) {
                        healthCheckPolicy.setUnHealthCheckThresshold(healthCheckPolicyProperty.getTextContent());
                    }

                }
                healthCheckPolicies.add(healthCheckPolicy);
                response.setHealthCheckPolicies(healthCheckPolicies);
            }
        }

        return response;
    }

    /**
     * Deletes a load balancer HealthCheck policy.
     *
     * @param lbHealthCheckPolicyId
     * @return
     * @throws Exception
     */
    public DeleteLBHealthCheckPolicyResponse deleteLBHealthCheckPolicy(String lbHealthCheckPolicyId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteLBHealthCheckPolicy", null);
        arguments.add(new NameValuePair("id", lbHealthCheckPolicyId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteLBHealthCheckPolicyResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteLBHealthCheckPolicyResponse object
     *
     * @param doc
     * @return
     */
    private DeleteLBHealthCheckPolicyResponse getDeleteLBHealthCheckPolicyResponse(Document doc) {
        DeleteLBHealthCheckPolicyResponse response = new DeleteLBHealthCheckPolicyResponse();

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
     * List all virtual machine instances that are assigned to a load balancer rule.
     *
     * @param lbRuleId the ID of the load balancer rule
     * @param optional
     * @return
     * @throws Exception
     */
    public ListLoadBalancerRuleInstancesResponse listLoadBalancerRuleInstances(String lbRuleId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVirtualMachines", optional);
        arguments.add(new NameValuePair("lbruleid", lbRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getListLoadBalancerRuleInstancesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListLoadBalancerRuleInstancesResponse object
     *
     * @param doc
     * @return
     */
    private ListLoadBalancerRuleInstancesResponse getListLoadBalancerRuleInstancesResponse(Document doc) {
        ListLoadBalancerRuleInstancesResponse response = new ListLoadBalancerRuleInstancesResponse();

        NodeList list = doc.getElementsByTagName("loadbalancerruleinstance");

        List<LoadBalancerRuleInstanceResponse> loadBalancerRuleInstances = new LinkedList<LoadBalancerRuleInstanceResponse>();
        if (list.getLength() > 0) {
            for (int loadBalancerRuleInstanceIndex = 0; loadBalancerRuleInstanceIndex < list.getLength(); loadBalancerRuleInstanceIndex++) {
                Node loadBalancerRuleInstanceNode = list.item(loadBalancerRuleInstanceIndex);

                LoadBalancerRuleInstanceResponse loadBalancerRuleInstance = new LoadBalancerRuleInstanceResponse();
                List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
                List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
                List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList loadBalancerRuleInstanceProperties = loadBalancerRuleInstanceNode.getChildNodes();
                for (int childIndex = 0; childIndex < loadBalancerRuleInstanceProperties.getLength(); childIndex++) {
                    Node property = loadBalancerRuleInstanceProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        loadBalancerRuleInstance.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        loadBalancerRuleInstance.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("cpunumber")) {
                        loadBalancerRuleInstance.setCpuNumber(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuspeed")) {
                        loadBalancerRuleInstance.setCpuSpeed(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuused")) {
                        loadBalancerRuleInstance.setCpuUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        loadBalancerRuleInstance.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("diskioread")) {
                        loadBalancerRuleInstance.setDiskIoRead(property.getTextContent());
                    } else if (property.getNodeName().equals("diskiowrite")) {
                        loadBalancerRuleInstance.setDiskIoWrite(property.getTextContent());
                    } else if (property.getNodeName().equals("diskkbsread")) {
                        loadBalancerRuleInstance.setDiskKbsRead(property.getTextContent());
                    } else if (property.getNodeName().equals("diskkbswrite")) {
                        loadBalancerRuleInstance.setDiskKbsWrite(property.getTextContent());
                    } else if (property.getNodeName().equals("displayname")) {
                        loadBalancerRuleInstance.setDisplayName(property.getTextContent());
                    } else if (property.getNodeName().equals("displayvm")) {
                        loadBalancerRuleInstance.setDisplayVm(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        loadBalancerRuleInstance.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        loadBalancerRuleInstance.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("forvirtualnetwork")) {
                        loadBalancerRuleInstance.setForVirtualNetwork(property.getTextContent());
                    } else if (property.getNodeName().equals("group")) {
                        loadBalancerRuleInstance.setGroup(property.getTextContent());
                    } else if (property.getNodeName().equals("groupid")) {
                        loadBalancerRuleInstance.setGroupId(property.getTextContent());
                    } else if (property.getNodeName().equals("guestosid")) {
                        loadBalancerRuleInstance.setGuestOsId(property.getTextContent());
                    } else if (property.getNodeName().equals("haenable")) {
                        loadBalancerRuleInstance.setHighEnable(property.getTextContent());
                    } else if (property.getNodeName().equals("hostid")) {
                        loadBalancerRuleInstance.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostname")) {
                        loadBalancerRuleInstance.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                        loadBalancerRuleInstance.setHyperVisor(property.getTextContent());
                    } else if (property.getNodeName().equals("instancename")) {
                        loadBalancerRuleInstance.setInstanceName(property.getTextContent());
                    } else if (property.getNodeName().equals("isdynamicallyscalable")) {
                        loadBalancerRuleInstance.setIsDynamicallyScalable(property.getTextContent());
                    } else if (property.getNodeName().equals("isodisplaytext")) {
                        loadBalancerRuleInstance.setIsoDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("isoid")) {
                        loadBalancerRuleInstance.setIsoId(property.getTextContent());
                    } else if (property.getNodeName().equals("isoname")) {
                        loadBalancerRuleInstance.setIsoName(property.getTextContent());
                    } else if (property.getNodeName().equals("memory")) {
                        loadBalancerRuleInstance.setMemory(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        loadBalancerRuleInstance.setVirtualMachineName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbsread")) {
                        loadBalancerRuleInstance.setNetworkKbsRead(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbswrite")) {
                        loadBalancerRuleInstance.setNetworkKbsWrite(property.getTextContent());
                    } else if (property.getNodeName().equals("password")) {
                        loadBalancerRuleInstance.setPassword(property.getTextContent());
                    } else if (property.getNodeName().equals("passwordenabled")) {
                        loadBalancerRuleInstance.setPasswordEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        loadBalancerRuleInstance.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        loadBalancerRuleInstance.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("publicip")) {
                        loadBalancerRuleInstance.setPublicIp(property.getTextContent());
                    } else if (property.getNodeName().equals("publicipid")) {
                        loadBalancerRuleInstance.setPublicIpId(property.getTextContent());
                    } else if (property.getNodeName().equals("rootdeviceid")) {
                        loadBalancerRuleInstance.setRootDeviceId(property.getTextContent());
                    } else if (property.getNodeName().equals("rootdevicetype")) {
                        loadBalancerRuleInstance.setRootDeviceType(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingid")) {
                        loadBalancerRuleInstance.setServiceOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingname")) {
                        loadBalancerRuleInstance.setServiceOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        loadBalancerRuleInstance.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("templatedisplaytext")) {
                        loadBalancerRuleInstance.setTemplateDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("templateid")) {
                        loadBalancerRuleInstance.setTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("templatename")) {
                        loadBalancerRuleInstance.setTemplateName(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        loadBalancerRuleInstance.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        loadBalancerRuleInstance.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("affinitygroup")) {
                        NodeList affinityGroupProperties = property.getChildNodes();
                        if (affinityGroupProperties.getLength() > 0) {
                            AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                            for (int affinityGroupIndex = 0; affinityGroupIndex < affinityGroupProperties.getLength(); affinityGroupIndex++) {
                                Node affinityGroupProperty = affinityGroupProperties.item(affinityGroupIndex);
                                if (affinityGroupProperty.getNodeName().equals("id")) {
                                    affinityGroup.setId(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("account")) {
                                    affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("description")) {
                                    affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                                    affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                                    affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("name")) {
                                    affinityGroup.setName(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("type")) {
                                    affinityGroup.setType(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("virtualmachineids")) {
                                    affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                                }
                            }

                            affinityGroups.add(affinityGroup);
                        }
                    } else if (property.getNodeName().equals("nic")) {
                        NodeList networkInterfaceCardProperties = property.getChildNodes();
                        if (networkInterfaceCardProperties.getLength() > 0) {
                            NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                            for (int networkInterfaceCardIndex = 0; networkInterfaceCardIndex < networkInterfaceCardProperties.getLength(); networkInterfaceCardIndex++) {
                                Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(networkInterfaceCardIndex);
                                if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                                    networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                                    networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                                    networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                                    networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                                    networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                                    networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                                    networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                                    networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                                    networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                                    networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                                    networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                                    networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                                    networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                                    networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                                    networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                                    networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                                }
                            }
                            networkInterfaceCards.add(networkInterfaceCard);
                        }

                    } else if (property.getNodeName().equals("securitygroup")) {
                        NodeList securityGroupProperties = property.getChildNodes();
                        if (securityGroupProperties.getLength() > 0) {
                            SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                            List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                            List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                            for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                                Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);
                                if (securityGroupProperty.getNodeName().equals("id")) {
                                    securityGroup.setId(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("account")) {
                                    securityGroup.setAccount(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("description")) {
                                    securityGroup.setDescription(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("domain")) {
                                    securityGroup.setDomain(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                                    securityGroup.setDomainId(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("name")) {
                                    securityGroup.setName(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("project")) {
                                    securityGroup.setProject(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                                    securityGroup.setProjectId(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                                    NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                                    if (egressRuleProperties.getLength() > 0) {
                                        EgressRuleResponse egressRule = new EgressRuleResponse();
                                        for (int egressRuleIndex = 0; egressRuleIndex < egressRuleProperties.getLength(); egressRuleIndex++) {
                                            Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                            if (egressRuleProperty.getNodeName().equals("account")) {
                                                egressRule.setAccount(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                                egressRule.setCidr(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                                egressRule.setEndPort(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                                egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                                egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                                egressRule.setProtocol(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                                egressRule.setRuleId(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                                egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                                egressRule.setStartPort(egressRuleProperty.getTextContent());
                                            }
                                        }
                                        egressRules.add(egressRule);
                                    }
                                } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                                    NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                                    if (ingressRuleProperties.getLength() > 0) {
                                        IngressRuleResponse ingressRule = new IngressRuleResponse();
                                        for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                            Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);
                                            if (ingressRuleProperty.getNodeName().equals("account")) {
                                                ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                                ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                                ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                                ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                                ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                                ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                                ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                                ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                                ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                            }
                                        }
                                        ingressRules.add(ingressRule);
                                    }
                                } else if (securityGroupProperty.getNodeName().equals("tags")) {
                                    NodeList tagsProperties = securityGroupProperty.getChildNodes();
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
                                } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                                    securityGroup.setJobId(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                                    securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                                }
                            }
                            securityGroup.setEgressRules(egressRules);
                            securityGroup.setIngressRules(ingressRules);
                            securityGroup.setTagss(tagss);
                            securityGroups.add(securityGroup);
                        }
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
                                tagss.add(tags);
                            }

                        }
                    } else if (property.getNodeName().equals("jobid")) {
                        loadBalancerRuleInstance.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        loadBalancerRuleInstance.setJobStatus(property.getTextContent());
                    }
                }
                loadBalancerRuleInstance.setTagss(tagss);
                loadBalancerRuleInstance.setAffinityGroups(affinityGroups);
                loadBalancerRuleInstance.setNetworkInterfaceCards(networkInterfaceCards);
                loadBalancerRuleInstance.setSecurityGroups(securityGroups);
                loadBalancerRuleInstances.add(loadBalancerRuleInstance);
            }
        }
        response.setLoadBalancerRuleInstances(loadBalancerRuleInstances);
        return response;
    }

    /**
     * Updates load balancer
     *
     * @param lbRuleId the ID of the load balancer rule
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateLoadBalancerRuleResponse updateLoadBalancerRule(String lbRuleId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateLoadBalancerRule", optional);
        arguments.add(new NameValuePair("id", lbRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateLoadBalancerRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private UpdateLoadBalancerRuleResponse getUpdateLoadBalancerRuleResponse(Document doc) {
        UpdateLoadBalancerRuleResponse response = new UpdateLoadBalancerRuleResponse();

        // get the load balancer rule ID   
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get the account of the load balancer rule
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get the load balancer algorithm
        list = doc.getElementsByTagName("algorithm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAlgorithm(node.getTextContent());
        }

        // get the cidr list to forward traffic from
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        // get the description of the load balancer
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get the domain of the load balancer rule
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get the domain ID of the load balancer rule
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the name of the load balancer
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get the id of the guest network the lb rule belongs to
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get the private port
        list = doc.getElementsByTagName("privateport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivatePort(node.getTextContent());
        }

        // get the project name of the load balancer
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the load balancer
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // the public ip address
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get the public ip address id
        list = doc.getElementsByTagName("publicipid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIpId(node.getTextContent());
        }

        // get the public port
        list = doc.getElementsByTagName("publicport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicPort(node.getTextContent());
        }

        // get the state of the rule
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get the id of the zone the rule belongs to
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get the list of resource tags associated with load balancer
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
     * Creates a global load balancer rule
     *
     * @param gslbDomainName domain name for the GSLB service
     * @param gslbServiceType GSLB service type (tcp, udp)
     * @param loadBalancerRuleName name of the load balancer rule
     * @param regionId region where the global load balancer is going to be created
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateGlobalLoadBalancerRuleResponse createGlobalLoadBalancerRule(String gslbDomainName,
            String gslbServiceType, String loadBalancerRuleName, String regionId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createGlobalLoadBalancerRule", optional);
        arguments.add(new NameValuePair("gslbdomainname", gslbDomainName));
        arguments.add(new NameValuePair("gslbservicetype", gslbServiceType));
        arguments.add(new NameValuePair("loadbalancerrulename", loadBalancerRuleName));
        arguments.add(new NameValuePair("regionid", regionId));
        Document responseDocument = server.makeRequest(arguments);

        return getCreateGlobalLoadBalancerRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateGlobalLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private CreateGlobalLoadBalancerRuleResponse getCreateGlobalLoadBalancerRuleResponse(Document doc) {
        CreateGlobalLoadBalancerRuleResponse response = new CreateGlobalLoadBalancerRuleResponse();

        // get the global load balancer rule ID   
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGlobalLoadBalancerRuleId(node.getTextContent());
        }

        // get the account of the load balancer rule
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get the description of the global load balancer rule
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get the domain of the load balancer rule
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get the domain ID of the load balancer rule
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the DNS domain name given for the global load balancer
        list = doc.getElementsByTagName("gslbdomainname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGslbDomainName(node.getTextContent());
        }

        // get the Load balancing method used for the global load balancer
        list = doc.getElementsByTagName("gslbmethod");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGslbMethod(node.getTextContent());
        }

        // get the GSLB service type
        list = doc.getElementsByTagName("gslbservicetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGslbServiceType(node.getTextContent());
        }

        // get the id of the guest network the lb rule belongs to
        list = doc.getElementsByTagName("gslbstickysessionmethodname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGslbStickySessionMethodName(node.getTextContent());
        }

        // get the name of the global load balancer rule
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGlobalLoadBalancerRuleName(node.getTextContent());
        }

        // get the project name of the load balancer
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the load balancer
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get the Region Id in which global load balancer is created
        list = doc.getElementsByTagName("regionid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRegionId(node.getTextContent());
        }

        list = doc.getElementsByTagName("loadbalancerrule");

        List<LoadBalancerRuleResponse> loadBalancerRules = new LinkedList<LoadBalancerRuleResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node loadBalancerRuleNode = list.item(index);

                LoadBalancerRuleResponse loadBalancerRule = new LoadBalancerRuleResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList loadBalancerRuleProperties = loadBalancerRuleNode.getChildNodes();
                for (int childIndex = 0; childIndex < loadBalancerRuleProperties.getLength(); childIndex++) {
                    Node property = loadBalancerRuleProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        loadBalancerRule.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        loadBalancerRule.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("algorithm")) {
                        loadBalancerRule.setAlgorithm(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        loadBalancerRule.setCidrList(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        loadBalancerRule.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        loadBalancerRule.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        loadBalancerRule.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        loadBalancerRule.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        loadBalancerRule.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("privateport")) {
                        loadBalancerRule.setPrivatePort(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        loadBalancerRule.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        loadBalancerRule.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("publicip")) {
                        loadBalancerRule.setPublicIp(property.getTextContent());
                    } else if (property.getNodeName().equals("publicport")) {
                        loadBalancerRule.setPublicPort(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        loadBalancerRule.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        loadBalancerRule.setZoneId(property.getTextContent());
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
                loadBalancerRule.setTagss(tagss);
                loadBalancerRules.add(loadBalancerRule);
                response.setLoadBalancerRules(loadBalancerRules);
            }
        }
        return response;
    }

    /**
     * Deletes a global load balancer rule.
     *
     * @param globalLoadBalancerRuleId the ID of the global load balancer rule
     * @return
     * @throws Exception
     */
    public DeleteGlobalLoadBalancerRuleResponse deleteGlobalLoadBalancerRule(String globalLoadBalancerRuleId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteGlobalLoadBalancerRule", null);
        arguments.add(new NameValuePair("id", globalLoadBalancerRuleId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteGlobalLoadBalancerRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private DeleteGlobalLoadBalancerRuleResponse getDeleteGlobalLoadBalancerRuleResponse(Document doc) {
        DeleteGlobalLoadBalancerRuleResponse response = new DeleteGlobalLoadBalancerRuleResponse();

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
     * update global load balancer rules
     *
     * @param id the ID of the global load balancer rule
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateGlobalLoadBalancerRuleResponse updateGlobalLoadBalancerRule(String id,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateGlobalLoadBalancerRule", optional);
        arguments.add(new NameValuePair("id", id));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateGlobalLoadBalancerRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateGlobalLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private UpdateGlobalLoadBalancerRuleResponse getUpdateGlobalLoadBalancerRuleResponse(Document doc) {
        UpdateGlobalLoadBalancerRuleResponse response = new UpdateGlobalLoadBalancerRuleResponse();

        // get the global load balancer rule ID   
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get the account of the load balancer rule
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get the description of the global load balancer rule
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get the domain of the load balancer rule
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get the domain ID of the load balancer rule
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the DNS domain name given for the global load balancer
        list = doc.getElementsByTagName("gslbdomainname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGslbDomainName(node.getTextContent());
        }

        // get the Load balancing method used for the global load balancer
        list = doc.getElementsByTagName("gslbmethod");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGslbMethod(node.getTextContent());
        }

        // get the GSLB service type
        list = doc.getElementsByTagName("gslbservicetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGslbServiceType(node.getTextContent());
        }

        // get the id of the guest network the lb rule belongs to
        list = doc.getElementsByTagName("gslbstickysessionmethodname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGslbStickySessionMethodName(node.getTextContent());
        }

        // get the name of the global load balancer rule
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get the project name of the load balancer
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the load balancer
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get the Region Id in which global load balancer is created
        list = doc.getElementsByTagName("regionid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRegionId(node.getTextContent());
        }

        list = doc.getElementsByTagName("loadbalancerrule");

        List<LoadBalancerRuleResponse> loadBalancerRules = new LinkedList<LoadBalancerRuleResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node loadBalancerRuleNode = list.item(index);

                LoadBalancerRuleResponse loadBalancerRule = new LoadBalancerRuleResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList loadBalancerRuleProperties = loadBalancerRuleNode.getChildNodes();
                for (int childIndex = 0; childIndex < loadBalancerRuleProperties.getLength(); childIndex++) {
                    Node property = loadBalancerRuleProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        loadBalancerRule.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        loadBalancerRule.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("algorithm")) {
                        loadBalancerRule.setAlgorithm(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        loadBalancerRule.setCidrList(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        loadBalancerRule.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        loadBalancerRule.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        loadBalancerRule.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        loadBalancerRule.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        loadBalancerRule.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("privateport")) {
                        loadBalancerRule.setPrivatePort(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        loadBalancerRule.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        loadBalancerRule.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("publicip")) {
                        loadBalancerRule.setPublicIp(property.getTextContent());
                    } else if (property.getNodeName().equals("publicport")) {
                        loadBalancerRule.setPublicPort(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        loadBalancerRule.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        loadBalancerRule.setZoneId(property.getTextContent());
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
                loadBalancerRule.setTagss(tagss);
                loadBalancerRules.add(loadBalancerRule);
                response.setLoadBalancerRules(loadBalancerRules);
            }
        }
        return response;
    }

    /**
     * Lists load balancer rules.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListGlobalLoadBalancerRulesResponse listGlobalLoadBalancerRules(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listGlobalLoadBalancerRules", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListGlobalLoadBalancerRulesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListGlobalLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private ListGlobalLoadBalancerRulesResponse getListGlobalLoadBalancerRulesResponse(Document doc) {
        ListGlobalLoadBalancerRulesResponse response = new ListGlobalLoadBalancerRulesResponse();

        NodeList list = doc.getElementsByTagName("globalloadbalancerrule");

        List<GlobalLoadBalancerRuleResponse> globalLoadBalancerRules = new LinkedList<GlobalLoadBalancerRuleResponse>();
        if (list.getLength() > 0) {
            for (int globalLoadBalancerRuleIndex = 0; globalLoadBalancerRuleIndex < list.getLength();
                    globalLoadBalancerRuleIndex++) {
                Node globalLoadBalancerRuleNode = list.item(globalLoadBalancerRuleIndex);

                GlobalLoadBalancerRuleResponse globalLoadBalancerRule = new GlobalLoadBalancerRuleResponse();
                List<LoadBalancerRuleResponse> loadBalancerRules = new LinkedList<LoadBalancerRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList globalLoadBalancerRuleProperties = globalLoadBalancerRuleNode.getChildNodes();
                for (int childIndex = 0; childIndex < globalLoadBalancerRuleProperties.getLength(); childIndex++) {
                    Node property = globalLoadBalancerRuleProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        globalLoadBalancerRule.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        globalLoadBalancerRule.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        globalLoadBalancerRule.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        globalLoadBalancerRule.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        globalLoadBalancerRule.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("gslbdomainname")) {
                        globalLoadBalancerRule.setGslbDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("gslbmethod")) {
                        globalLoadBalancerRule.setGslbMethod(property.getTextContent());
                    } else if (property.getNodeName().equals("gslbservicetype")) {
                        globalLoadBalancerRule.setGslbServiceType(property.getTextContent());
                    } else if (property.getNodeName().equals("gslbstickysesssionmethodname")) {
                        globalLoadBalancerRule.setGslbStickySessionMethodName(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        globalLoadBalancerRule.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        globalLoadBalancerRule.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        globalLoadBalancerRule.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("regionid")) {
                        globalLoadBalancerRule.setRegionId(property.getTextContent());
                    } else if (property.getNodeName().equals("loadbalancerrule")) {
                        NodeList loadBalancerRuleProperties = property.getChildNodes();
                        if (loadBalancerRuleProperties.getLength() > 0) {
                            LoadBalancerRuleResponse loadBalancerRule = new LoadBalancerRuleResponse();
                            for (int loadBalancerRuleIndex = 0; loadBalancerRuleIndex
                                    < loadBalancerRuleProperties.getLength(); loadBalancerRuleIndex++) {
                                Node loadBalancerRuleProperty = loadBalancerRuleProperties.item(loadBalancerRuleIndex);
                                if (loadBalancerRuleProperty.getNodeName().equals("id")) {
                                    loadBalancerRule.setId(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("account")) {
                                    loadBalancerRule.setAccount(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("algorithm")) {
                                    loadBalancerRule.setAlgorithm(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("cidrlist")) {
                                    loadBalancerRule.setCidrList(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("description")) {
                                    loadBalancerRule.setDomainId(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("domain")) {
                                    loadBalancerRule.setDomain(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("domainid")) {
                                    loadBalancerRule.setDomainId(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("name")) {
                                    loadBalancerRule.setName(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("networkid")) {
                                    loadBalancerRule.setNetworkId(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("privateport")) {
                                    loadBalancerRule.setPrivatePort(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("project")) {
                                    loadBalancerRule.setProject(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("projectid")) {
                                    loadBalancerRule.setProjectId(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("publicip")) {
                                    loadBalancerRule.setPublicIp(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("publicipid")) {
                                    loadBalancerRule.setPublicIpId(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("publicport")) {
                                    loadBalancerRule.setPublicPort(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("state")) {
                                    loadBalancerRule.setState(loadBalancerRuleProperty.getTextContent());
                                } else if (loadBalancerRuleProperty.getNodeName().equals("zoneid")) {
                                    loadBalancerRule.setZoneId(loadBalancerRuleProperty.getTextContent());
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
                            loadBalancerRule.setTagss(tagss);
                            loadBalancerRules.add(loadBalancerRule);
                        }
                    }

                }
                globalLoadBalancerRule.setLoadBalancerRules(loadBalancerRules);
                globalLoadBalancerRules.add(globalLoadBalancerRule);
            }

        }
        response.setGlobalLoadBalancerRules(globalLoadBalancerRules);
        return response;
    }

    /**
     * Removes a load balancer rule association with global load balancer rule.
     *
     * @param loadBalancerRuleId the ID of the load balancer rule
     * @param loadBalancerRuleList the list load balancer rules that will be assigned to gloabal load balacner rule
     * @param optional
     * @return
     * @throws Exception
     */
    public RemoveFromGlobalLoadBalancerRuleResponse removeFromGlobalLoadBalancerRule(String loadBalancerRuleId,
            String loadBalancerRuleList, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("removeFromGlobalLoadBalancerRule", optional);
        arguments.add(new NameValuePair("id", loadBalancerRuleId));
        arguments.add(new NameValuePair("loadbalancerrulelist", loadBalancerRuleList));

        Document responseDocument = server.makeRequest(arguments);

        return getRemoveFromGlobalLoadBalancerRuleResponse(responseDocument);
    }

    /**
     * Converts XML document into RemoveFromGlobalLoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private RemoveFromGlobalLoadBalancerRuleResponse getRemoveFromGlobalLoadBalancerRuleResponse(Document doc) {
        RemoveFromGlobalLoadBalancerRuleResponse response = new RemoveFromGlobalLoadBalancerRuleResponse();

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
     * Creates a Load Balancer
     *
     * @param algorithm the load balancer algorithm
     * @param instancePort the TCP port of the virtual machine where the network traffic will be load balanced to
     * @param name name of the Load Balancer
     * @param networkId the guest network the Load Balancer will be created for
     * @param scheme the load balancer scheme
     * @param sourceIpAddressNetworkId the network id of the source ip address
     * @param sourcePort the source port the network traffic will be load balanced from
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateLoadBalancerResponse createLoadBalancer(String algorithm, String instancePort,
            String name, String networkId, String scheme, String sourceIpAddressNetworkId,
            String sourcePort, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createLoadBalancer", optional);
        arguments.add(new NameValuePair("algorithm", algorithm));
        arguments.add(new NameValuePair("instanceport", instancePort));
        arguments.add(new NameValuePair("name", name));
        arguments.add(new NameValuePair("networkid", networkId));
        arguments.add(new NameValuePair("scheme", scheme));
        arguments.add(new NameValuePair("sourceipaddressnetworkid", sourceIpAddressNetworkId));
        arguments.add(new NameValuePair("sourceport", sourcePort));
        Document responseDocument = server.makeRequest(arguments);

        return getCreateLoadBalancerResponse(responseDocument);
    }


    /**
     * Converts XML document into CreateLoadBalancerResponse object
     *
     * @param doc
     * @return
     */
    private CreateLoadBalancerResponse getCreateLoadBalancerResponse(Document doc) {
        CreateLoadBalancerResponse response = new CreateLoadBalancerResponse();

        // get the load balancer rule ID   
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get the account of the load balancer rule
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }
        
        // get the jobid of the load balancer rule
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        // get the load balancer algorithm
        list = doc.getElementsByTagName("algorithm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAlgorithm(node.getTextContent());
        }

        // get the description of the load balancer
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get the domain of the load balancer rule
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get the domain ID of the load balancer rule
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the name of the load balancer
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get the id of the guest network the lb rule belongs to
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get the project name of the load balancer
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get the project id of the load balancer
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get the Load Balancer source ip
        list = doc.getElementsByTagName("sourceipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceIpAddress(node.getTextContent());
        }

        // get the Load Balancer source ip network id
        list = doc.getElementsByTagName("sourceipaddressnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceIpAddressNetworkId(node.getTextContent());
        }

        // get the list of instances associated with the Load Balancer
        list = doc.getElementsByTagName("loadbalancerinstance");
        if (list.getLength() > 0) {
            List<LoadBalancerInstanceResponse> loadBalancerInstances = new LinkedList<LoadBalancerInstanceResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node loadBalancerInstanceNode = list.item(index);
                LoadBalancerInstanceResponse loadBalancerInstance = new LoadBalancerInstanceResponse();
                NodeList loadBalancerInstanceProperties = loadBalancerInstanceNode.getChildNodes();
                for (int childIndex = 0; childIndex < loadBalancerInstanceProperties.getLength(); childIndex++) {
                    Node loadBalancerInstanceProperty = loadBalancerInstanceProperties.item(childIndex);
                    if (loadBalancerInstanceProperty.getNodeName().equals("id")) {
                        loadBalancerInstance.setId(loadBalancerInstanceProperty.getTextContent());
                    } else if (loadBalancerInstanceProperty.getNodeName().equals("ipaddress")) {
                        loadBalancerInstance.setIpAddress(loadBalancerInstanceProperty.getTextContent());
                    } else if (loadBalancerInstanceProperty.getNodeName().equals("name")) {
                        loadBalancerInstance.setName(loadBalancerInstanceProperty.getTextContent());
                    } else if (loadBalancerInstanceProperty.getNodeName().equals("state")) {
                        loadBalancerInstance.setState(loadBalancerInstanceProperty.getTextContent());
                    }
                }
                loadBalancerInstances.add(loadBalancerInstance);
                response.setLoadBalancerInstances(loadBalancerInstances);
            }
        }

        // get the list of rules associated with the Load Balancer
        list = doc.getElementsByTagName("loadbalancerrule");
        if (list.getLength() > 0) {
            List<LoadBalancerResponse> loadBalancers = new LinkedList<LoadBalancerResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node loadBalancerNode = list.item(index);
                LoadBalancerResponse loadBalancer = new LoadBalancerResponse();
                NodeList loadBalancerProperties = loadBalancerNode.getChildNodes();
                for (int childIndex = 0; childIndex < loadBalancerProperties.getLength(); childIndex++) {
                    Node loadBalancerProperty = loadBalancerProperties.item(childIndex);
                    if (loadBalancerProperty.getNodeName().equals("instanceport")) {
                        loadBalancer.setInstancePort(loadBalancerProperty.getTextContent());
                    } else if (loadBalancerProperty.getNodeName().equals("sourceport")) {
                        loadBalancer.setSourcePort(loadBalancerProperty.getTextContent());
                    } else if (loadBalancerProperty.getNodeName().equals("state")) {
                        loadBalancer.setState(loadBalancerProperty.getTextContent());
                    }
                }
                loadBalancers.add(loadBalancer);
                response.setLoadBalancers(loadBalancers);
            }
        }

        // get the list of resource tags associated with load balancer
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
     * Lists Load Balancers.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListLoadBalancersResponse listLoadBalancers(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listLoadBalancers", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListLoadBalancersResponse(responseDocument);
    }

    /**
     * Converts XML document into ListLoadBalancersResponse object
     *
     * @param doc
     * @return
     */
    private ListLoadBalancersResponse getListLoadBalancersResponse(Document doc) {
        ListLoadBalancersResponse response = new ListLoadBalancersResponse();

        NodeList list = doc.getElementsByTagName("loadbalancer");

        List<LoadBalancersResponse> loadBalancerLists = new LinkedList<LoadBalancersResponse>();
        if (list.getLength() > 0) {
            for (int loadBalancersIndex = 0; loadBalancersIndex < list.getLength(); loadBalancersIndex++) {
                Node loadBalancersNode = list.item(loadBalancersIndex);

                LoadBalancersResponse loadBalancerList = new LoadBalancersResponse();
                List<LoadBalancerInstanceResponse> loadBalancerInstances = new LinkedList<LoadBalancerInstanceResponse>();
                List<LoadBalancerResponse> loadBalancers = new LinkedList<LoadBalancerResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList loadBalancersProperties = loadBalancersNode.getChildNodes();
                for (int childIndex = 0; childIndex < loadBalancersProperties.getLength(); childIndex++) {
                    Node property = loadBalancersProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        loadBalancerList.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        loadBalancerList.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("algorithm")) {
                        loadBalancerList.setAlgorithm(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        loadBalancerList.setCidr(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        loadBalancerList.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        loadBalancerList.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        loadBalancerList.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        loadBalancerList.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        loadBalancerList.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("privateport")) {
                        loadBalancerList.setPrivatePort(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        loadBalancerList.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        loadBalancerList.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("sourceipaddress")) {
                        loadBalancerList.setSourceIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("sourceipaddressnetworkid")) {
                        loadBalancerList.setSourceIpAddressNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("loadbalancerinstance")) {
                        NodeList loadBalancerInstanceProperties = property.getChildNodes();
                        if (loadBalancerInstanceProperties.getLength() > 0) {
                            LoadBalancerInstanceResponse loadBalancerInstance = new LoadBalancerInstanceResponse();
                            for (int loadBalancerInstanceIndex = 0; loadBalancerInstanceIndex
                                    < loadBalancerInstanceProperties.getLength(); loadBalancerInstanceIndex++) {
                                Node loadBalancerInstanceProperty = loadBalancerInstanceProperties.item(loadBalancerInstanceIndex);
                                if (loadBalancerInstanceProperty.getNodeName().equals("id")) {
                                    loadBalancerInstance.setId(loadBalancerInstanceProperty.getTextContent());
                                } else if (loadBalancerInstanceProperty.getNodeName().equals("ipaddress")) {
                                    loadBalancerInstance.setIpAddress(loadBalancerInstanceProperty.getTextContent());
                                } else if (loadBalancerInstanceProperty.getNodeName().equals("name")) {
                                    loadBalancerInstance.setName(loadBalancerInstanceProperty.getTextContent());
                                } else if (loadBalancerInstanceProperty.getNodeName().equals("state")) {
                                    loadBalancerInstance.setState(loadBalancerInstanceProperty.getTextContent());
                                }
                            }
                            loadBalancerInstances.add(loadBalancerInstance);
                        }
                    } else if (property.getNodeName().equals("loadbalancerrule")) {
                        NodeList loadBalancerProperties = property.getChildNodes();
                        if (loadBalancerProperties.getLength() > 0) {
                            LoadBalancerResponse loadBalancer = new LoadBalancerResponse();
                            for (int loadBalancerIndex = 0; loadBalancerIndex
                                    < loadBalancerProperties.getLength(); loadBalancerIndex++) {
                                Node loadBalancerProperty = loadBalancerProperties.item(loadBalancerIndex);
                                if (loadBalancerProperty.getNodeName().equals("instanceport")) {
                                    loadBalancer.setInstancePort(loadBalancerProperty.getTextContent());
                                } else if (loadBalancerProperty.getNodeName().equals("sourceport")) {
                                    loadBalancer.setSourcePort(loadBalancerProperty.getTextContent());
                                } else if (loadBalancerProperty.getNodeName().equals("state")) {
                                    loadBalancer.setState(loadBalancerProperty.getTextContent());
                                }
                            }
                            loadBalancers.add(loadBalancer);
                        }
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
                loadBalancerList.setLoadBalancerInstances(loadBalancerInstances);
                loadBalancerList.setLoadBalancers(loadBalancers);
                loadBalancerList.setTagss(tagss);
                loadBalancerLists.add(loadBalancerList);
            }
        }
        response.setLoadBalancerLists(loadBalancerLists);
        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for loadbalancerrule.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public LoadBalancerRuleJobResultResponse loadBalancerRuleJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getLoadBalancerRuleJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into LoadBalancerRuleJobResultResponse object
     *
     * @param doc
     * @return
     */
    private LoadBalancerRuleJobResultResponse getLoadBalancerRuleJobResultResponse(Document doc) {
        LoadBalancerRuleJobResultResponse response = new LoadBalancerRuleJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("loadbalancerrule")) {
                    List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("algorithm")) {
                            response.setAlgorithm(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("cidrlist")) {
                            response.setCidrList(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("description")) {
                            response.setDescription(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkid")) {
                            response.setNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("privateport")) {
                            response.setPrivatePort(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("publicip")) {
                            response.setPublicIp(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("publicipid")) {
                            response.setPublicIpId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("publicport")) {
                            response.setPublicPort(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
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
     * Retrieves the current status of asynchronous job for lbStickinessPolicy.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public LbStickinessPolicyJobResultResponse lbStickinessPolicyJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getLbStickinessPolicyJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into LbStickinessPolicyJobResultResponse object
     *
     * @param doc
     * @return
     */
    private LbStickinessPolicyJobResultResponse getLbStickinessPolicyJobResultResponse(Document doc) {
        LbStickinessPolicyJobResultResponse response = new LbStickinessPolicyJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("lbstickinesspolicy")) {
                    List<StickinessPolicyResponse> stickinessPolicies = new LinkedList<StickinessPolicyResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("description")) {
                            response.setDescription(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("lbruleid")) {
                            response.setLbRuleId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneId")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("stickinesspolicy")) {
                            NodeList stickinessPolicyProperties = childNodeProperty.getChildNodes();
                            if (stickinessPolicyProperties.getLength() > 0) {
                                StickinessPolicyResponse stickinessPolicy = new StickinessPolicyResponse();
                                for (int stickinessPolicyIndex = 0; stickinessPolicyIndex < stickinessPolicyProperties.getLength(); stickinessPolicyIndex++) {
                                    Node stickinessPolicyProperty = stickinessPolicyProperties.item(stickinessPolicyIndex);

                                    if (stickinessPolicyProperty.getNodeName().equals("id")) {
                                        stickinessPolicy.setId(stickinessPolicyProperty.getTextContent());
                                    } else if (stickinessPolicyProperty.getNodeName().equals("description")) {
                                        stickinessPolicy.setDescription(stickinessPolicyProperty.getTextContent());
                                    } else if (stickinessPolicyProperty.getNodeName().equals("methodname")) {
                                        stickinessPolicy.setMethodName(stickinessPolicyProperty.getTextContent());
                                    } else if (stickinessPolicyProperty.getNodeName().equals("name")) {
                                        stickinessPolicy.setName(stickinessPolicyProperty.getTextContent());
                                    } else if (stickinessPolicyProperty.getNodeName().equals("params")) {
                                        stickinessPolicy.setParams(stickinessPolicyProperty.getTextContent());
                                    } else if (stickinessPolicyProperty.getNodeName().equals("state")) {
                                        stickinessPolicy.setState(stickinessPolicyProperty.getTextContent());
                                    }

                                }
                                stickinessPolicies.add(stickinessPolicy);
                            }
                            response.setStickinessPolicies(stickinessPolicies);
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
     * Retrieves the current status of asynchronous job for lbHealthCheckPolicy.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public LbHealthCheckPolicyJobResultResponse lbHealthCheckPolicyJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getLbHealthCheckPolicyJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into LbHealthCheckPolicyJobResultResponse object
     *
     * @param doc
     * @return
     */
    private LbHealthCheckPolicyJobResultResponse getLbHealthCheckPolicyJobResultResponse(Document doc) {
        LbHealthCheckPolicyJobResultResponse response = new LbHealthCheckPolicyJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("lbhealthcheckpolicy")) {
                    List<HealthCheckPolicyResponse> healthCheckPolicies = new LinkedList<HealthCheckPolicyResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("lbruleid")) {
                            response.setLbRuleId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneId")) {
                            response.setZoneId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("healthcheckpolicy")) {
                            NodeList healthCheckPolicyProperties = childNodeProperty.getChildNodes();
                            if (healthCheckPolicyProperties.getLength() > 0) {
                                HealthCheckPolicyResponse healthCheckPolicy = new HealthCheckPolicyResponse();
                                for (int healthCheckPolicyIndex = 0; healthCheckPolicyIndex < healthCheckPolicyProperties.getLength(); healthCheckPolicyIndex++) {
                                    Node healthCheckPolicyProperty = healthCheckPolicyProperties.item(healthCheckPolicyIndex);

                                    if (healthCheckPolicyProperty.getNodeName().equals("id")) {
                                        healthCheckPolicy.setId(healthCheckPolicyProperty.getTextContent());
                                    } else if (healthCheckPolicyProperty.getNodeName().equals("description")) {
                                        healthCheckPolicy.setDescription(healthCheckPolicyProperty.getTextContent());
                                    } else if (healthCheckPolicyProperty.getNodeName().equals("healthcheckinterval")) {
                                        healthCheckPolicy.setHealthCheckInterval(healthCheckPolicyProperty.getTextContent());
                                    } else if (healthCheckPolicyProperty.getNodeName().equals("healthcheckthresshold")) {
                                        healthCheckPolicy.setHealthCheckThresshold(healthCheckPolicyProperty.getTextContent());
                                    } else if (healthCheckPolicyProperty.getNodeName().equals("pingpath")) {
                                        healthCheckPolicy.setPingPath(healthCheckPolicyProperty.getTextContent());
                                    } else if (healthCheckPolicyProperty.getNodeName().equals("responsetime")) {
                                        healthCheckPolicy.setResponseTime(healthCheckPolicyProperty.getTextContent());
                                    } else if (healthCheckPolicyProperty.getNodeName().equals("state")) {
                                        healthCheckPolicy.setState(healthCheckPolicyProperty.getTextContent());
                                    } else if (healthCheckPolicyProperty.getNodeName().equals("unhealthcheckthresshold")) {
                                        healthCheckPolicy.setUnHealthCheckThresshold(healthCheckPolicyProperty.getTextContent());
                                    }

                                }
                                healthCheckPolicies.add(healthCheckPolicy);
                            }
                            response.setHealthCheckPolicies(healthCheckPolicies);
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
     * Retrieves the current status of asynchronous job for lbHealthCheckPolicy.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public GlobalLoadBalancerRuleJobResultResponse globalLoadBalancerRuleJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getGlobalLoadBalancerRuleJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into GlobalLoadBalancerRuleJobResultResponse object
     *
     * @param doc
     * @return
     */
    private GlobalLoadBalancerRuleJobResultResponse getGlobalLoadBalancerRuleJobResultResponse(Document doc) {
        GlobalLoadBalancerRuleJobResultResponse response = new GlobalLoadBalancerRuleJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("globalloadbalancerrule")) {
                    List<LoadBalancerRuleResponse> loadBalancerRules = new LinkedList<LoadBalancerRuleResponse>();
                    List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("gslbdomainname")) {
                            response.setGslbDomainName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("gslblbmethod")) {
                            response.setGslbLbMethod(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("gslbservicetype")) {
                            response.setGslbServiceType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("gslbstickysessionmethodname")) {
                            response.setGslbStickySessionMethodName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("regionid")) {
                            response.setRegionId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("loadbalancerrule")) {
                            NodeList loadBalancerRuleProperties = childNodeProperty.getChildNodes();
                            if (loadBalancerRuleProperties.getLength() > 0) {
                                LoadBalancerRuleResponse loadBalancerRule = new LoadBalancerRuleResponse();
                                for (int loadBalancerRuleIndex = 0; loadBalancerRuleIndex < loadBalancerRuleProperties.getLength(); loadBalancerRuleIndex++) {
                                    Node loadBalancerRuleProperty = loadBalancerRuleProperties.item(loadBalancerRuleIndex);

                                    if (loadBalancerRuleProperty.getNodeName().equals("id")) {
                                        loadBalancerRule.setId(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("account")) {
                                        loadBalancerRule.setAccount(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("algorithm")) {
                                        loadBalancerRule.setAlgorithm(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("cidrlist")) {
                                        loadBalancerRule.setCidrList(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("description")) {
                                        loadBalancerRule.setDescription(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("domain")) {
                                        loadBalancerRule.setDomain(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("domainid")) {
                                        loadBalancerRule.setDomainId(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("name")) {
                                        loadBalancerRule.setName(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("networkid")) {
                                        loadBalancerRule.setNetworkId(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("privateport")) {
                                        loadBalancerRule.setPrivatePort(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("project")) {
                                        loadBalancerRule.setProject(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("projectid")) {
                                        loadBalancerRule.setProjectId(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("publicip")) {
                                        loadBalancerRule.setPublicIp(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("publicip")) {
                                        loadBalancerRule.setPublicIp(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("publicipid")) {
                                        loadBalancerRule.setPublicIpId(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("publicport")) {
                                        loadBalancerRule.setPublicPort(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("state")) {
                                        loadBalancerRule.setState(loadBalancerRuleProperty.getTextContent());
                                    } else if (loadBalancerRuleProperty.getNodeName().equals("zoneid")) {
                                        loadBalancerRule.setZoneId(loadBalancerRuleProperty.getTextContent());
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
                                    loadBalancerRules.add(loadBalancerRule);
                                }
                                response.setLoadBalancerRules(loadBalancerRules);
                            }
                        } else if (nodeProperty.getNodeName().equals("errorcode")) {
                            response.setErrorCode(nodeProperty.getTextContent());
                        } else if (nodeProperty.getNodeName().equals("errortext")) {
                            response.setErrorText(nodeProperty.getTextContent());
                        }
                    }
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
     * Retrieves the current status of asynchronous job for loadBalancer.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public LoadBalancerJobResultResponse loadBalancerJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getLoadBalancerJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into loadBalancerJobResultResponse object
     *
     * @param doc
     * @return
     */
    private LoadBalancerJobResultResponse getLoadBalancerJobResultResponse(Document doc) {
        LoadBalancerJobResultResponse response = new LoadBalancerJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("loadbalancer")) {
                    List<LoadBalancerInstanceResponse> loadBalancerInstances = new LinkedList<LoadBalancerInstanceResponse>();
                    List<LoadBalancerResponse> loadBalancers = new LinkedList<LoadBalancerResponse>();
                    List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("algorithm")) {
                            response.setAlgorithm(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("description")) {
                            response.setDescription(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkid")) {
                            response.setNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("sourceipaddress")) {
                            response.setSourceIpAddress(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("sourceipaddressnetworkid")) {
                            response.setSourceIpAddressNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("loadbalancerinstance")) {
                            NodeList loadBalancerInstanceProperties = childNodeProperty.getChildNodes();
                            if (loadBalancerInstanceProperties.getLength() > 0) {
                                LoadBalancerInstanceResponse loadBalancerInstance = new LoadBalancerInstanceResponse();
                                for (int loadBalancerInstanceIndex = 0; loadBalancerInstanceIndex < loadBalancerInstanceProperties.getLength(); loadBalancerInstanceIndex++) {
                                    Node loadBalancerInstanceProperty = loadBalancerInstanceProperties.item(loadBalancerInstanceIndex);

                                    if (loadBalancerInstanceProperty.getNodeName().equals("id")) {
                                        loadBalancerInstance.setId(loadBalancerInstanceProperty.getTextContent());
                                    } else if (loadBalancerInstanceProperty.getNodeName().equals("ipaddress")) {
                                        loadBalancerInstance.setIpAddress(loadBalancerInstanceProperty.getTextContent());
                                    } else if (loadBalancerInstanceProperty.getNodeName().equals("name")) {
                                        loadBalancerInstance.setName(loadBalancerInstanceProperty.getTextContent());
                                    } else if (loadBalancerInstanceProperty.getNodeName().equals("state")) {
                                        loadBalancerInstance.setState(loadBalancerInstanceProperty.getTextContent());
                                    } else if (childNodeProperty.getNodeName().equals("loadBalancer")) {
                                        NodeList loadBalancerProperties = childNodeProperty.getChildNodes();
                                        if (loadBalancerProperties.getLength() > 0) {
                                            LoadBalancerResponse loadBalancer = new LoadBalancerResponse();
                                            for (int loadBalancerIndex = 0; loadBalancerIndex < loadBalancerProperties.getLength(); loadBalancerIndex++) {
                                                Node loadBalancerProperty = loadBalancerProperties.item(loadBalancerIndex);
                                                if (loadBalancerProperty.getNodeName().equals("instanceport")) {
                                                    loadBalancer.setInstancePort(loadBalancerProperty.getTextContent());
                                                } else if (loadBalancerProperty.getNodeName().equals("sourceport")) {
                                                    loadBalancer.setSourcePort(loadBalancerProperty.getTextContent());
                                                } else if (loadBalancerProperty.getNodeName().equals("state")) {
                                                    loadBalancer.setState(loadBalancerProperty.getTextContent());
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
                                                loadBalancers.add(loadBalancer);
                                            }
                                            response.setLoadBalancers(loadBalancers);
                                        }
                                        loadBalancerInstances.add(loadBalancerInstance);
                                    }
                                    response.setLoadBalancerInstances(loadBalancerInstances);
                                }
                            }

                        } else if (nodeProperty.getNodeName().equals("errorcode")) {
                            response.setErrorCode(nodeProperty.getTextContent());
                        } else if (nodeProperty.getNodeName().equals("errortext")) {
                            response.setErrorText(nodeProperty.getTextContent());
                        }
                    }
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
     * Deletes a load balancer.
     *
     * @param loadBalancerId the ID of the load balancer
     * @return
     * @throws Exception
     */
    public DeleteLoadBalancerResponse deleteLoadBalancer(String loadBalancerId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteLoadBalancer", null);
        arguments.add(new NameValuePair("id", loadBalancerId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteLoadBalancerResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteLoadBalancerResponse object
     *
     * @param doc
     * @return
     */
    private DeleteLoadBalancerResponse getDeleteLoadBalancerResponse(Document doc) {
        DeleteLoadBalancerResponse response = new DeleteLoadBalancerResponse();

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

}
