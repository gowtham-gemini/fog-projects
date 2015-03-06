package com.assistanz.cloud.cloudstack.loadbalancer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;
import com.assistanz.cloud.cloudstack.StickinessPolicyResponse;
import com.assistanz.cloud.cloudstack.TagsResponse;

/**
 * 
 * @author Gowtham
 *
 */
public class CSLoadBalancerService {
	
	private CloudStackServer server;
	
	public CSLoadBalancerService(CloudStackServer server) {
        this.server = server;
	}
	
	/**
	 * Creates a load balancer rule
	 * 
	 * @param loadBalancerAlgorithm load balancer algorithm (source, roundrobin, leastconn)
	 * @param loadBalancerName 	name of the load balancer rule
	 * @param loadBalancerPrivatePort the private port of the private ip address/virtual machine where the network traffic will be load balanced to
	 * @param loadBalancerPublicPort the public port from where the network traffic will be load balanced from
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public CreateLoadBalancerRuleResponse createLoadBalancerRule(String loadBalancerAlgorithm,
			String loadBalancerName, String loadBalancerPrivatePort, 
			String loadBalancerPublicPort, HashMap<String,String> optional) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("createLoadBalancerRule", optional);
	    arguments.add(new NameValuePair("algorithm",  loadBalancerAlgorithm));
	    arguments.add(new NameValuePair("name",  loadBalancerName));
	    arguments.add(new NameValuePair("privateport",  loadBalancerPrivatePort));
	    arguments.add(new NameValuePair("publicport",  loadBalancerPublicPort));
	   
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
    	
    	// get id from XML and set the load balancer rule ID   
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerId(node.getTextContent());
        }
        
    	// get account from XML and set the account of the load balancer rule 
    	list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerAccount(node.getTextContent());
        }
        
    	// get algorithm from XML and set the load balancer algorithm (source, roundrobin, leastconn)
    	list = doc.getElementsByTagName("algorithm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerAlgorithm(node.getTextContent());
        }
        
    	// get cidrlist from XML and set the cidr list to forward traffic from
    	list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerCidrList(node.getTextContent());
        }
        
    	// get description from XML and set the description of the load balancer
    	list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDescription(node.getTextContent());
        }
        
    	// get domain from XML and set the domain of the load balancer
    	list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDomain(node.getTextContent());
        }
        
    	// get domainid from XML and set the domainid of the load balancer
    	list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDomainId(node.getTextContent());
        }
        
    	// get name from XML and set the name of the load balancer
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerName(node.getTextContent());
        }
        
    	// get privateport from XML and set the privateport of the load balancer
    	list = doc.getElementsByTagName("privateport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPrivatePort(node.getTextContent());
        }
        
    	// get project from XML and set the project name of the load balancer
    	list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerProject(node.getTextContent());
        }
        
    	// get projectid from XML and set the project id of the load balancer
    	list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerProjectId(node.getTextContent());
        }
        
    	// get publicip from XML and set the public ip of the load balancer
    	list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPublicIpId(node.getTextContent());
        }
        
    	// get publicipid from XML and set the public ip id of the load balancer
    	list = doc.getElementsByTagName("publicipid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPublicIpId(node.getTextContent());
        }
        
    	// get publicport from XML and set the public port of the load balancer
    	list = doc.getElementsByTagName("publicport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPublicPort(node.getTextContent());
        }
        
    	// get state from XML and set the state of the load balancer
    	list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerState(node.getTextContent());
        }
        
    	// get zoneid from XML and set the zoneid of the load balancer
    	list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerZoneId(node.getTextContent());
        }
        
        return response;
    } 
    
    /**
     * Deletes a load balancer rule
     * 
     * @param loadBalancerId the ID of the load balancer rule
     * @return
     * @throws Exception
     */
	public DeleteLoadBalancerRuleResponse deleteLoadBalancerRule(String loadBalancerId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteLoadBalancerRule", null);
		arguments.add(new NameValuePair("id",  loadBalancerId));
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getDeleteLoadBalancerRuleResponse(responseDocument);
	}

	/**
	 *  Converts XML document into DeleteLoadBalancerRuleResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private DeleteLoadBalancerRuleResponse getDeleteLoadBalancerRuleResponse(Document doc) {
		DeleteLoadBalancerRuleResponse response = new DeleteLoadBalancerRuleResponse();

		// get displaytext from XML and set any text associated with the success or failure on Delete Load Balancer Rule
		NodeList list = doc.getElementsByTagName("displaytext");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDisplaytext(node.getTextContent());
		}
		//get success from XML and any text associated with the success or failure on Delete Load Balancer Rule 
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
	 * @param loadBalancerId The ID of the load balancer rule
	 * @param virtualMachineIds the list of IDs of the virtual machines that are being removed from the load balancer rule (i.e. virtualMachineIds=1,2,3)
	 * @return
	 * @throws Exception
	 */
	public RemoveFromLoadBalancerRuleResponse removeFromLoadBalancerRule(String loadBalancerId,
			String virtualMachineIds) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("removeFromLoadBalancerRule", null);
		arguments.add(new NameValuePair("id",  loadBalancerId));
		arguments.add(new NameValuePair("virtualmachineids",  virtualMachineIds));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getRemoveFromLoadBalancerRuleResponse(responseDocument);
	}

	/**
	 *  Converts XML document into RemoveFromLoadBalancerRuleResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private RemoveFromLoadBalancerRuleResponse getRemoveFromLoadBalancerRuleResponse(Document doc) {
		RemoveFromLoadBalancerRuleResponse response = new RemoveFromLoadBalancerRuleResponse();
		
		// get displaytext from XML and set any text associated with the success or failure on Remove Load Balancer Rule
		NodeList list = doc.getElementsByTagName("displaytext");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDisplaytext(node.getTextContent());
		}
		//get success from XML and any text associated with the success or failure on Remove LoadBalancer Rule 
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
	 * Assigns virtual machine or a list of virtual machines to a load balancer rule.
	 * 
	 * @param loadBalancerId the ID of the load balancer rule
	 * @param virtualMachineIds 	the list of IDs of the virtual machine that are being assigned to the load balancer rule(i.e. virtualMachineIds=1,2,3)
	 * @return
	 * @throws Exception
	 */
	public AssignToLoadBalancerRuleResponse assignToLoadBalancerRule(String loadBalancerId,
			 String virtualMachineIds) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("assignToLoadBalancerRule", null);
		arguments.add(new NameValuePair("id",  loadBalancerId));
		arguments.add(new NameValuePair("virtualmachineids",  virtualMachineIds));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getAssignToLoadBalancerRuleResponse(responseDocument);
	}

	/**
	 *  Converts XML document into AssignToLoadBalancerRuleResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private AssignToLoadBalancerRuleResponse getAssignToLoadBalancerRuleResponse(Document doc) {
		AssignToLoadBalancerRuleResponse response = new AssignToLoadBalancerRuleResponse();

		// get displaytext from XML and set any text associated with the success or failure on Assign Load Balancer Rule
		NodeList list = doc.getElementsByTagName("displaytext");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDisplaytext(node.getTextContent());
		}
		//get success from XML and any text associated with the success or failure on Assign LoadBalancer Rule 
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
	 * @param lbRuleId 	the ID of the load balancer rule
	 * @param stickinessPolicyMethodName name of the LB Stickiness policy method, possible values can be obtained from ListNetworks API
	 * @param stickinessPolicyName name of the LB Stickiness policy
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public CreateLBStickinessPolicyResponse createLBStickinessPolicy(String lbRuleId,
			String stickinessPolicyMethodName, String stickinessPolicyName,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("createLBStickinessPolicy", optional);
		arguments.add(new NameValuePair("lbruleid",  lbRuleId));
		arguments.add(new NameValuePair("methodname",  stickinessPolicyMethodName));
		arguments.add(new NameValuePair("name",  stickinessPolicyName));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getCreateLBStickinessPolicyResponse(responseDocument);
	}
	
    /**
     * Converts XML document into CreateLBStickinessPolicyResponse object
     *
     * @param doc
     * @return
     */
    private CreateLBStickinessPolicyResponse getCreateLBStickinessPolicyResponse(Document doc) {
    	CreateLBStickinessPolicyResponse response = new CreateLBStickinessPolicyResponse();
    	
    	// get account from XML and set the account of the Stickiness policy   
    	NodeList list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyAccount(node.getTextContent());
        }
        
    	// get description from XML and set the description of the Stickiness policy 
    	list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyDescription(node.getTextContent());
        }
        
    	// get domain from XML and set the domain of the Stickiness policy 
    	list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyDomain(node.getTextContent());
        }
        
    	// get domainid from XML and set the domainid of the Stickiness policy 
    	list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyDomainId(node.getTextContent());
        }
        
    	// get lbruleid from XML and set the LB rule id of the Stickiness policy 
    	list = doc.getElementsByTagName("lbruleid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLbRuleId(node.getTextContent());
        }
        
    	// get name from XML and set the name of the Stickiness policy 
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyName(node.getTextContent());
        }
        
    	// get state from XML and set the state of the Stickiness policy 
    	list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyState(node.getTextContent());
        }
        
    	// get zoneid from XML and set the zoneid of the Stickiness policy 
    	list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyZoneId(node.getTextContent());
        }
        
        //the list of stickinesspolicies
        list = doc.getElementsByTagName("stickinesspolicy");
        if (list.getLength() > 0) {
            List<StickinessPolicyResponse> LbStickinessPolicys = new LinkedList<StickinessPolicyResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node LbStickinessPolicyNode = list.item(index);
                StickinessPolicyResponse LbStickinessPolicy = new StickinessPolicyResponse();
                
                
                NodeList LbStickinessPolicyProperties = LbStickinessPolicyNode.getChildNodes();
                for (int childIndex = 0; childIndex < LbStickinessPolicyProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                    	LbStickinessPolicy.setLbStickinessPolicyId(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                    	LbStickinessPolicy.setStickinessPolicyDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("methodname")) {
                    	LbStickinessPolicy.setStickinessPolicyMethodName(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	LbStickinessPolicy.setStickinessPolicyName(property.getTextContent());
                    } else if (property.getNodeName().equals("params")) {
                    	LbStickinessPolicy.setStickinessPolicyParams(property.getTextContent());
                    }else if (property.getNodeName().equals("state")) {
                    	LbStickinessPolicy.setStickinessPolicyState(property.getTextContent());
                    }
                }    
            
                LbStickinessPolicys.add(LbStickinessPolicy);
                response.setLbStickinessPolicys(LbStickinessPolicys);
            }
        }
        
        return response;
    }
    
    /**
     * Deletes a LB stickiness policy.
     * 
     * @param LBStickinessPolicyId the ID of the LB stickiness policy
     * @return
     * @throws Exception
     */
	public DeleteLBStickinessPolicyResponse deleteLBStickinessPolicy(String LBStickinessPolicyId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteLBStickinessPolicy", null);
		arguments.add(new NameValuePair("id",   LBStickinessPolicyId));
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getDeleteLBStickinessPolicyResponse(responseDocument);
	}

	/**
	 *  Converts XML document into DeleteLBStickinessPolicyResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private DeleteLBStickinessPolicyResponse getDeleteLBStickinessPolicyResponse(Document doc) {
		DeleteLBStickinessPolicyResponse response = new DeleteLBStickinessPolicyResponse();

		// get displaytext from XML and set any text associated with the success or failure on Delete LB stickiness policy
		NodeList list = doc.getElementsByTagName("displaytext");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDisplaytext(node.getTextContent());
		}
		//get success from XML and any text associated with the success or failure on Delete LB stickiness policy 
		list = doc.getElementsByTagName("success");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setSuccess(node.getTextContent());
		}

		return response;
	}
	
	/**
	 * Lists LBStickiness policies
	 * 
	 * @param lbRuleId the ID of the load balancer rule
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListLBStickinessPoliciesResponse listLBStickinessPolicies(String lbRuleId,
						HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listLBStickinessPolicies", optional);
		arguments.add(new NameValuePair("lbruleid",  lbRuleId));
	
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
    	
    	// get account from XML and set the account of the Stickiness policy   
    	NodeList list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyAccount(node.getTextContent());
        }
        
    	// get description from XML and set the description of the Stickiness policy 
    	list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyDescription(node.getTextContent());
        }
        
    	// get domain from XML and set the domain of the Stickiness policy 
    	list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyDomain(node.getTextContent());
        }
        
    	// get domainid from XML and set the domainid of the Stickiness policy 
    	list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyDomainId(node.getTextContent());
        }
        
    	// get lbruleid from XML and set the LB rule id of the Stickiness policy 
    	list = doc.getElementsByTagName("lbruleid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLbRuleId(node.getTextContent());
        }
        
    	// get name from XML and set the name of the Stickiness policy 
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyName(node.getTextContent());
        }
        
    	// get state from XML and set the state of the Stickiness policy 
    	list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyState(node.getTextContent());
        }
        
    	// get zoneid from XML and set the zoneid of the Stickiness policy 
    	list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStickinessPolicyZoneId(node.getTextContent());
        }
        
        //the list of stickinesspolicies
        list = doc.getElementsByTagName("stickinesspolicy");
        if (list.getLength() > 0) {
            List<StickinessPolicyResponse> LbStickinessPolicys = new LinkedList<StickinessPolicyResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node LbStickinessPolicyNode = list.item(index);
                StickinessPolicyResponse LbStickinessPolicy = new StickinessPolicyResponse();
                
                
                NodeList LbStickinessPolicyProperties = LbStickinessPolicyNode.getChildNodes();
                for (int childIndex = 0; childIndex < LbStickinessPolicyProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                    	LbStickinessPolicy.setLbStickinessPolicyId(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                    	LbStickinessPolicy.setStickinessPolicyDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("methodname")) {
                    	LbStickinessPolicy.setStickinessPolicyMethodName(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	LbStickinessPolicy.setStickinessPolicyName(property.getTextContent());
                    } else if (property.getNodeName().equals("params")) {
                    	LbStickinessPolicy.setStickinessPolicyParams(property.getTextContent());
                    }else if (property.getNodeName().equals("state")) {
                    	LbStickinessPolicy.setStickinessPolicyState(property.getTextContent());
                    }
                }    
            
                LbStickinessPolicys.add(LbStickinessPolicy);
                response.setLbStickinessPolicys(LbStickinessPolicys);
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
	public ListLoadBalancerRulesResponse listLoadBalancerRules(
			 HashMap<String,String> optional) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("listLoadBalancerRules", optional);
	   
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

        List<LoadBalancerRulesResponse> loadBalancerRules = new LinkedList<LoadBalancerRulesResponse>();            
        if (list.getLength() > 0) {
            
            for (int LBIndex = 0; LBIndex < list.getLength(); LBIndex++) {
                Node LBNode = list.item(LBIndex);
                
                LoadBalancerRulesResponse loadBalancerRule = new LoadBalancerRulesResponse();
                NodeList LBProperties = LBNode.getChildNodes();
                for (int childIndex = 0; childIndex < LBProperties.getLength(); childIndex++) {
                    Node property = LBProperties.item(childIndex);
                    
                    
                    if (property.getNodeName().equals("id")) {
                        loadBalancerRule.setLoadBalancerId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                    	loadBalancerRule.setLoadBalancerAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("algorithm")) {
                    	loadBalancerRule.setLoadBalancerAlgorithm(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	loadBalancerRule.setLoadBalancerName(property.getTextContent());
                    } else if (property.getNodeName().equals("privateport")) {
                    	loadBalancerRule.setLoadBalancerPrivatePort(property.getTextContent());
                    } else if (property.getNodeName().equals("publicport")) {
                    	loadBalancerRule.setLoadBalancerPublicPort(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                    	loadBalancerRule.setLoadBalancerState(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                    	loadBalancerRule.setNetworkId(property.getTextContent());
                    }
                }
                loadBalancerRules.add(loadBalancerRule);
            }
            response.setLoadBalancerRules(loadBalancerRules);
        }
            	
        return response;
    } 
    
    /**
     * List all virtual machine instances that are assigned to a load balancer rule.
     * 
     * @param loadBalancerRuleId the ID of the load balancer rule
     * @param optional
     * @return
     * @throws Exception
     */
	public ListLoadBalancerRuleInstancesResponse listLoadBalancerRuleInstances(String loadBalancerRuleId,
			 HashMap<String,String> optional) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("listLoadBalancerRuleInstances", optional);
	    arguments.add(new NameValuePair("id",   loadBalancerRuleId));
	    
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
        
            for (int LBIndex = 0; LBIndex < list.getLength(); LBIndex++) {
                Node LBNode = list.item(LBIndex);
                
                LoadBalancerRuleInstanceResponse vm = new LoadBalancerRuleInstanceResponse();
                NodeList LBProperties = LBNode.getChildNodes();
                for (int childIndex = 0; childIndex < LBProperties.getLength(); childIndex++) {
                    Node property = LBProperties.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                        vm.setVirtualMachineId(property.getTextContent());
                    } else if (property.getNodeName().equals("displayname")) {
                    	vm.setDisplayName(property.getTextContent());
                    } 
                }
                loadBalancerRuleInstances.add(vm);
            }
            response.setLoadBalancerRuleInstances(loadBalancerRuleInstances);
        }      
        
        return response;

    }
    
    /**
     * Updates load balancer
     * 
     * @param loadBalancerId the id of the load balancer rule to update
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateLoadBalancerRuleResponse updateLoadBalancerRule(String loadBalancerId,
                     HashMap<String,String> optional) 
                                    throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateLoadBalancerRule", optional);
        arguments.add(new NameValuePair("id",  loadBalancerId));

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
    	
    	// get id from XML and set the load balancer rule ID   
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerId(node.getTextContent());
        }
        
    	// get account from XML and set the account of the load balancer rule 
    	list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerAccount(node.getTextContent());
        }
        
    	// get algorithm from XML and set the load balancer algorithm (source, roundrobin, leastconn)
    	list = doc.getElementsByTagName("algorithm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerAlgorithm(node.getTextContent());
        }
        
    	// get cidrlist from XML and set the cidr list to forward traffic from
    	list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerCidrList(node.getTextContent());
        }
        
    	// get description from XML and set the description of the load balancer
    	list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDescription(node.getTextContent());
        }
        
    	// get domain from XML and set the domain of the load balancer
    	list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDomain(node.getTextContent());
        }
        
    	// get domainid from XML and set the domainid of the load balancer
    	list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDomainId(node.getTextContent());
        }
        
    	// get name from XML and set the name of the load balancer
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerName(node.getTextContent());
        }
        
    	// get privateport from XML and set the privateport of the load balancer
    	list = doc.getElementsByTagName("privateport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPrivatePort(node.getTextContent());
        }
        
    	// get project from XML and set the project name of the load balancer
    	list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerProject(node.getTextContent());
        }
        
    	// get projectid from XML and set the project id of the load balancer
    	list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerProjectId(node.getTextContent());
        }
        
    	// get publicip from XML and set the public ip of the load balancer
    	list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPublicIpId(node.getTextContent());
        }
        
    	// get publicipid from XML and set the public ip id of the load balancer
    	list = doc.getElementsByTagName("publicipid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPublicIpId(node.getTextContent());
        }
        
    	// get publicport from XML and set the public port of the load balancer
    	list = doc.getElementsByTagName("publicport");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPublicPort(node.getTextContent());
        }
        
    	// get state from XML and set the state of the load balancer
    	list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerState(node.getTextContent());
        }
        
    	// get zoneid from XML and set the zoneid of the load balancer
    	list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerZoneId(node.getTextContent());
        }
        
        return response;
    } 
    
    /**
     * Adds a F5 BigIP load balancer device
     * 
     * @param f5LoadBalancerNetworkDeviceType supports only F5BigIpLoadBalancer
     * @param f5LoadBalancerPassword Credentials to reach F5 BigIP load balancer device
     * @param f5LoadBalancerPhysicalNetworkId the Physical Network ID
     * @param f5LoadBalancerUrl URL of the F5 load balancer appliance.
     * @param f5LoadBalancerUserName Credentials to reach F5 BigIP load balancer device
     * @param optional
     * @return
     * @throws Exception
     */
	public AddF5LoadBalancerRuleResponse addF5LoadBalancer(String f5LoadBalancerNetworkDeviceType,
			String f5LoadBalancerPassword, String f5LoadBalancerPhysicalNetworkId, 
			String f5LoadBalancerUrl, String f5LoadBalancerUserName, HashMap<String,String> optional) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("addF5LoadBalancer", optional);
	    arguments.add(new NameValuePair("networkdevicetype",  f5LoadBalancerNetworkDeviceType));
	    arguments.add(new NameValuePair("password",  f5LoadBalancerPassword));
	    arguments.add(new NameValuePair("physicalnetworkid",  f5LoadBalancerPhysicalNetworkId));
	    arguments.add(new NameValuePair("url",  f5LoadBalancerUrl));
	    arguments.add(new NameValuePair("username",  f5LoadBalancerUserName));
	    
	    Document responseDocument = server.makeRequest(arguments);

	    return getAddF5LoadBalancerRuleResponse(responseDocument);
	}
	
    /**
     * Converts XML document into AddF5LoadBalancerRuleResponse object
     *
     * @param doc
     * @return
     */
    private AddF5LoadBalancerRuleResponse getAddF5LoadBalancerRuleResponse(Document doc) {
    	AddF5LoadBalancerRuleResponse response = new AddF5LoadBalancerRuleResponse();
    	
    	// get inline from XML and set true if device is inline with firewall device  
    	NodeList list = doc.getElementsByTagName("inline");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirewallInlineDevice(node.getTextContent());
        }
        
    	// get ipaddress from XML and set the management IP address of the external load balancer
    	list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExternalLoadBalancerIpaddress(node.getTextContent());
        }
        
    	// get ipaddress from XML and set the device capacity
    	list = doc.getElementsByTagName("lbdevicecapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceCapacity(node.getTextContent());
        }
        
    	// get lbdevicededicated from XML and set true if device is dedicated for an account
    	list = doc.getElementsByTagName("lbdevicededicated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceDedicated(node.getTextContent());
        }
        
    	// get lbdeviceid from XML and set thedevice id of the F5 load balancer
    	list = doc.getElementsByTagName("lbdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceId(node.getTextContent());
        }
        
    	// get lbdevicename from XML and set the device name of the  load balancer
    	list = doc.getElementsByTagName("lbdevicename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceName(node.getTextContent());
        }
        
    	// get lbdevicestate from XML and set the device state of the  load balancer
    	list = doc.getElementsByTagName("lbdevicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceState(node.getTextContent());
        }
        
    	// get physicalnetworkid from XML and set the physical network to which this F5 device belongs to
    	list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPhysicalNetworkId(node.getTextContent());
        }
        
    	// get privateinterface from XML and set the private interface of the  load balancer
    	list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPrivateInterface(node.getTextContent());
        }
        
    	// get provider from XML and set the provider of the  load balancer
    	list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerProvider(node.getTextContent());
        }
        
    	// get publicinterface from XML and set the public interface of the  load balancer
    	list = doc.getElementsByTagName("publicinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPublicInterface(node.getTextContent());
        }
        
        return response;
    }   
    
    /**
     * configures a F5 load balancer device
     * 
     * @param f5LoadBalancerNetworkDeviceID the F5 load balancer device ID
     * @param optional
     * @return
     * @throws Exception
     */
	public ConfigureF5LoadBalancerResponse configureF5LoadBalancer(String f5LoadBalancerNetworkDeviceId,
			 HashMap<String,String> optional) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("configureF5LoadBalancer", optional);
	    arguments.add(new NameValuePair("lbdeviceid",  f5LoadBalancerNetworkDeviceId));
	   
	    Document responseDocument = server.makeRequest(arguments);

	    return getConfigureF5LoadBalancerResponse(responseDocument);
	}
	
    /**
     * Converts XML document into ConfigureF5LoadBalancerResponse object
     *
     * @param doc
     * @return
     */
    private ConfigureF5LoadBalancerResponse getConfigureF5LoadBalancerResponse(Document doc) {
    	ConfigureF5LoadBalancerResponse response = new ConfigureF5LoadBalancerResponse();
    	
    	// get inline from XML and set true if device is inline with firewall device  
    	NodeList list = doc.getElementsByTagName("inline");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirewallInlineDevice(node.getTextContent());
        }
        
    	// get ipaddress from XML and set the management IP address of the external load balancer
    	list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExternalLoadBalancerIpaddress(node.getTextContent());
        }
        
    	// get ipaddress from XML and set the device capacity
    	list = doc.getElementsByTagName("lbdevicecapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceCapacity(node.getTextContent());
        }
        
    	// get lbdevicededicated from XML and set true if device is dedicated for an account
    	list = doc.getElementsByTagName("lbdevicededicated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceDedicated(node.getTextContent());
        }
        
    	// get lbdeviceid from XML and set thedevice id of the F5 load balancer
    	list = doc.getElementsByTagName("lbdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceId(node.getTextContent());
        }
        
    	// get lbdevicename from XML and set the device name of the  load balancer
    	list = doc.getElementsByTagName("lbdevicename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceName(node.getTextContent());
        }
        
    	// get lbdevicestate from XML and set the device state of the  load balancer
    	list = doc.getElementsByTagName("lbdevicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceState(node.getTextContent());
        }
        
    	// get physicalnetworkid from XML and set the physical network to which this F5 device belongs to
    	list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPhysicalNetworkId(node.getTextContent());
        }
        
    	// get privateinterface from XML and set the private interface of the  load balancer
    	list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPrivateInterface(node.getTextContent());
        }
        
    	// get provider from XML and set the provider of the  load balancer
    	list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerProvider(node.getTextContent());
        }
        
    	// get publicinterface from XML and set the public interface of the  load balancer
    	list = doc.getElementsByTagName("publicinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPublicInterface(node.getTextContent());
        }
        
        return response;
    }
    
    /**
     * delete a F5 load balancer device
     * 
     * @param f5LoadBalancerNetworkDeviceID f5 load balancer device ID
     * @return
     * @throws Exception
     */
	public DeleteF5LoadBalancerResponse deleteF5LoadBalancer(String f5LoadBalancerNetworkDeviceId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteF5LoadBalancer", null);
		arguments.add(new NameValuePair("lbdeviceid",  f5LoadBalancerNetworkDeviceId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getDeleteF5LoadBalancerResponse(responseDocument);
	}

	/**
	 *  Converts XML document into DeleteF5LoadBalancerResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private DeleteF5LoadBalancerResponse getDeleteF5LoadBalancerResponse(Document doc) {
		DeleteF5LoadBalancerResponse response = new DeleteF5LoadBalancerResponse();

		// get displaytext from XML and set any text associated with the success or failure on Delete F5Load Balancer
		NodeList list = doc.getElementsByTagName("displaytext");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDisplaytext(node.getTextContent());
		}
		//get success from XML and any text associated with the success or failure on Delete F5Load Balancer
		list = doc.getElementsByTagName("success");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setSuccess(node.getTextContent());
		}

		return response;
	}
	
	/**
	 * delete a netscaler load balancer device
	 * 
	 * @param f5LoadBalancerNetworkDeviceId netscaler load balancer device ID
	 * @return
	 * @throws Exception
	 */
	public DeleteNetscalerLoadBalancerResponse deleteNetscalerLoadBalancer(String f5LoadBalancerNetworkDeviceId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteNetscalerLoadBalancer", null);
		arguments.add(new NameValuePair("lbdeviceid",  f5LoadBalancerNetworkDeviceId));
				
		Document responseDocument = server.makeRequest(arguments);
		
		return getDeleteNetscalerLoadBalancerResponse(responseDocument);
	}

	/**
	 *  Converts XML document into DeleteNetscalerLoadBalancerResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private DeleteNetscalerLoadBalancerResponse getDeleteNetscalerLoadBalancerResponse(Document doc) {
		DeleteNetscalerLoadBalancerResponse response = new DeleteNetscalerLoadBalancerResponse();

		// get displaytext from XML and set any text associated with the success or failure on Delete Netscaler load balancer
		NodeList list = doc.getElementsByTagName("displaytext");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDisplaytext(node.getTextContent());
		}
		//get success from XML and any text associated with the success or failure on Delete Netscaler load balancer
		list = doc.getElementsByTagName("success");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setSuccess(node.getTextContent());
		}

		return response;
	}
	
	/**
	 * lists F5 load balancer devices
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListF5LoadBalancersResponse listF5LoadBalancers(
			 HashMap<String,String> optional) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("listF5LoadBalancers", optional);
	    	    
	    Document responseDocument = server.makeRequest(arguments);

	    return getListF5LoadBalancersResponse(responseDocument);
	}
	
    /**
     * Converts XML document into ListF5LoadBalancersResponse object
     *
     * @param doc
     * @return
     */
    private ListF5LoadBalancersResponse getListF5LoadBalancersResponse(Document doc) {
    	ListF5LoadBalancersResponse response = new ListF5LoadBalancersResponse();
    	
    	// get inline from XML and set true if device is inline with firewall device  
    	NodeList list = doc.getElementsByTagName("inline");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirewallInlineDevice(node.getTextContent());
        }
        
    	// get ipaddress from XML and set the management IP address of the external load balancer
    	list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExternalLoadBalancerIpaddress(node.getTextContent());
        }
        
    	// get ipaddress from XML and set the device capacity
    	list = doc.getElementsByTagName("lbdevicecapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceCapacity(node.getTextContent());
        }
        
    	// get lbdevicededicated from XML and set true if device is dedicated for an account
    	list = doc.getElementsByTagName("lbdevicededicated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceDedicated(node.getTextContent());
        }
        
    	// get lbdeviceid from XML and set thedevice id of the F5 load balancer
    	list = doc.getElementsByTagName("lbdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceId(node.getTextContent());
        }
        
    	// get lbdevicename from XML and set the device name of the  load balancer
    	list = doc.getElementsByTagName("lbdevicename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceName(node.getTextContent());
        }
        
    	// get lbdevicestate from XML and set the device state of the  load balancer
    	list = doc.getElementsByTagName("lbdevicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceState(node.getTextContent());
        }
        
    	// get physicalnetworkid from XML and set the physical network to which this F5 device belongs to
    	list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPhysicalNetworkId(node.getTextContent());
        }
        
    	// get privateinterface from XML and set the private interface of the  load balancer
    	list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPrivateInterface(node.getTextContent());
        }
        
    	// get provider from XML and set the provider of the  load balancer
    	list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerProvider(node.getTextContent());
        }
        
    	// get publicinterface from XML and set the public interface of the  load balancer
    	list = doc.getElementsByTagName("publicinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPublicInterface(node.getTextContent());
        }
        
        return response;
    }
    
    /**
     * Adds a netscaler load balancer device
     * 
     * @param netscalerLoadBalancerNetworkDeviceType Netscaler device type supports NetscalerMPXLoadBalancer, NetscalerVPXLoadBalancer, NetscalerSDXLoadBalancer
     * @param netscalerLoadBalancerPassword Credentials to reach netscaler load balancer device
     * @param netscalerLoadBalancerPhysicalNetworkId the Physical Network ID
     * @param netscalerLoadBalancerUrl URL of the netscaler load balancer appliance.
     * @param netscalerLoadBalancerUserName Credentials to reach netscaler load balancer device
     * @return
     * @throws Exception
     */
	public AddNetscalerLoadBalancerResponse addNetscalerLoadBalancer(String netscalerLoadBalancerNetworkDeviceType,
			String netscalerLoadBalancerPassword, String netscalerLoadBalancerPhysicalNetworkId, 
			String netscalerLoadBalancerUrl, String netscalerLoadBalancerUserName) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("addNetscalerLoadBalancer", null);
	    arguments.add(new NameValuePair("networkdevicetype",  netscalerLoadBalancerNetworkDeviceType));
	    arguments.add(new NameValuePair("password",  netscalerLoadBalancerPassword));
	    arguments.add(new NameValuePair("physicalnetworkid",  netscalerLoadBalancerPhysicalNetworkId));
	    arguments.add(new NameValuePair("url",  netscalerLoadBalancerUrl));
	    arguments.add(new NameValuePair("username",  netscalerLoadBalancerUserName));
	    
	    Document responseDocument = server.makeRequest(arguments);

	    return getAddNetscalerLoadBalancerResponse(responseDocument);
	}
	
    /**
     * Converts XML document into AddNetscalerLoadBalancerResponse object
     *
     * @param doc
     * @return
     */
    private AddNetscalerLoadBalancerResponse getAddNetscalerLoadBalancerResponse(Document doc) {
    	AddNetscalerLoadBalancerResponse response = new AddNetscalerLoadBalancerResponse();
    	
    	// get inline from XML and set true if device is inline with firewall device  
    	NodeList list = doc.getElementsByTagName("inline");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirewallInlineDevice(node.getTextContent());
        }
        
    	// get ipaddress from XML and set the management IP address of the external load balancer
    	list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExternalLoadBalancerIpaddress(node.getTextContent());
        }
        
    	// get ipaddress from XML and set the device capacity
    	list = doc.getElementsByTagName("lbdevicecapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceCapacity(node.getTextContent());
        }
        
    	// get lbdevicededicated from XML and set true if device is dedicated for an account
    	list = doc.getElementsByTagName("lbdevicededicated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceDedicated(node.getTextContent());
        }
        
    	// get lbdeviceid from XML and set thedevice id of the netscaler load balancer
    	list = doc.getElementsByTagName("lbdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceId(node.getTextContent());
        }
        
    	// get lbdevicename from XML and set the device name of the  load balancer
    	list = doc.getElementsByTagName("lbdevicename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceName(node.getTextContent());
        }
        
    	// get lbdevicestate from XML and set the device state of the  load balancer
    	list = doc.getElementsByTagName("lbdevicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerDeviceState(node.getTextContent());
        }
        
    	// get physicalnetworkid from XML and set the physical network to which this netscaler device belongs to
    	list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPhysicalNetworkId(node.getTextContent());
        }
        
    	// get privateinterface from XML and set the private interface of the  load balancer
    	list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPrivateInterface(node.getTextContent());
        }
        
    	// get provider from XML and set the provider of the  load balancer
    	list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerProvider(node.getTextContent());
        }
        
    	// get publicinterface from XML and set the public interface of the load balancer
    	list = doc.getElementsByTagName("publicinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLoadBalancerPublicInterface(node.getTextContent());
        }
        
        return response;
    } 
    
    /**
     * configures a netscaler load balancer device
     * 
     * @param netscalerLoadBalancerNetworkDeviceId Netscaler load balancer device ID
     * @param optional
     * @return
     * @throws Exception
     */
	public ConfigureNetscalerLoadBalancerResponse configureNetscalerLoadBalancer(String netscalerLoadBalancerNetworkDeviceId,
			 HashMap<String,String> optional) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("configureNetscalerLoadBalancer", optional);
	    arguments.add(new NameValuePair("lbdeviceid",  netscalerLoadBalancerNetworkDeviceId));
	   
	    Document responseDocument = server.makeRequest(arguments);

	    return getConfigureNetscalerLoadBalancerResponse(responseDocument);
	}
	
	/**
     * Converts XML document into ConfigureNetscalerLoadBalancerResponse object
     *
     * @param doc
     * @return
     */
	private ConfigureNetscalerLoadBalancerResponse getConfigureNetscalerLoadBalancerResponse(Document doc) {
		ConfigureNetscalerLoadBalancerResponse response = new ConfigureNetscalerLoadBalancerResponse();
   	
   		// get inline from XML and set true if device is inline with firewall device  
   		NodeList list = doc.getElementsByTagName("inline");
        if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setFirewallInlineDevice(node.getTextContent());
        }
       
        // get ipaddress from XML and set the management IP address of the external load balancer
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setExternalLoadBalancerIpaddress(node.getTextContent());
        }
       
        // get ipaddress from XML and set the device capacity
        list = doc.getElementsByTagName("lbdevicecapacity");
        if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setLoadBalancerDeviceCapacity(node.getTextContent());
        }
       
        // get lbdevicededicated from XML and set true if device is dedicated for an account
        list = doc.getElementsByTagName("lbdevicededicated");
        if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setLoadBalancerDeviceDedicated(node.getTextContent());
        }
       
        // get lbdeviceid from XML and set thedevice id of the Netscaler load balancer
        list = doc.getElementsByTagName("lbdeviceid");
        if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setLoadBalancerDeviceId(node.getTextContent());
        }
       
        // get lbdevicename from XML and set the device name of the  load balancer
        list = doc.getElementsByTagName("lbdevicename");
        if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setLoadBalancerDeviceName(node.getTextContent());
        }
       
        // get lbdevicestate from XML and set the device state of the  load balancer
        list = doc.getElementsByTagName("lbdevicestate");
        if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setLoadBalancerDeviceState(node.getTextContent());
        }
       
        // get physicalnetworkid from XML and set the physical network to which this Netscaler device belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setLoadBalancerPhysicalNetworkId(node.getTextContent());
        }
       
        // get privateinterface from XML and set the private interface of the load balancer
        list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setLoadBalancerPrivateInterface(node.getTextContent());
        }
       
        // get provider from XML and set the provider of the  load balancer
        list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setLoadBalancerProvider(node.getTextContent());
        }
       
        // get publicinterface from XML and set the public interface of the  load balancer
        list = doc.getElementsByTagName("publicinterface");
        if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setLoadBalancerPublicInterface(node.getTextContent());
        }
       
        return response;
	}
	
	/**
	 * lists netscaler load balancer devices
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListNetscalerLoadBalancersResponse listNetscalerLoadBalancers(
			 HashMap<String,String> optional) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("listNetscalerLoadBalancers", optional);
	    	   
	    Document responseDocument = server.makeRequest(arguments);

	    return getListNetscalerLoadBalancersResponse(responseDocument);
	}
	
	/**
    * Converts XML document into ListNetscalerLoadBalancersResponse object
    *
    * @param doc
    * @return
    */
	private ListNetscalerLoadBalancersResponse getListNetscalerLoadBalancersResponse(Document doc) {
		ListNetscalerLoadBalancersResponse response = new ListNetscalerLoadBalancersResponse();
  	
  		// get inline from XML and set true if device is inline with firewall device  
  		NodeList list = doc.getElementsByTagName("inline");
       if (list.getLength() > 0) {
          Node node = list.item(0);
          response.setFirewallInlineDevice(node.getTextContent());
       }
      
       // get ipaddress from XML and set the management IP address of the external load balancer
       list = doc.getElementsByTagName("ipaddress");
       if (list.getLength() > 0) {
          Node node = list.item(0);
          response.setExternalLoadBalancerIpaddress(node.getTextContent());
       }
      
       // get ipaddress from XML and set the device capacity
       list = doc.getElementsByTagName("lbdevicecapacity");
       if (list.getLength() > 0) {
          Node node = list.item(0);
          response.setLoadBalancerDeviceCapacity(node.getTextContent());
       }
      
       // get lbdevicededicated from XML and set true if device is dedicated for an account
       list = doc.getElementsByTagName("lbdevicededicated");
       if (list.getLength() > 0) {
          Node node = list.item(0);
          response.setLoadBalancerDeviceDedicated(node.getTextContent());
       }
      
       // get lbdeviceid from XML and set thedevice id of the Netscaler load balancer
       list = doc.getElementsByTagName("lbdeviceid");
       if (list.getLength() > 0) {
          Node node = list.item(0);
          response.setLoadBalancerDeviceId(node.getTextContent());
       }
      
       // get lbdevicename from XML and set the device name of the  load balancer
       list = doc.getElementsByTagName("lbdevicename");
       if (list.getLength() > 0) {
          Node node = list.item(0);
          response.setLoadBalancerDeviceName(node.getTextContent());
       }
      
       // get lbdevicestate from XML and set the device state of the  load balancer
       list = doc.getElementsByTagName("lbdevicestate");
       if (list.getLength() > 0) {
          Node node = list.item(0);
          response.setLoadBalancerDeviceState(node.getTextContent());
       }
      
       // get physicalnetworkid from XML and set the physical network to which this Netscaler device belongs to
       list = doc.getElementsByTagName("physicalnetworkid");
       if (list.getLength() > 0) {
          Node node = list.item(0);
          response.setLoadBalancerPhysicalNetworkId(node.getTextContent());
       }
      
       // get privateinterface from XML and set the private interface of the load balancer
       list = doc.getElementsByTagName("privateinterface");
       if (list.getLength() > 0) {
          Node node = list.item(0);
          response.setLoadBalancerPrivateInterface(node.getTextContent());
       }
      
       // get provider from XML and set the provider of the  load balancer
       list = doc.getElementsByTagName("privateinterface");
       if (list.getLength() > 0) {
          Node node = list.item(0);
          response.setLoadBalancerProvider(node.getTextContent());
       }
      
       // get publicinterface from XML and set the public interface of the  load balancer
       list = doc.getElementsByTagName("publicinterface");
       if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setLoadBalancerPublicInterface(node.getTextContent());
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
                        loadBalancerList.setLbRuleId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        loadBalancerList.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("algorithm")) {
                        loadBalancerList.setAlgorithm(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        loadBalancerList.setCidrList(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        loadBalancerList.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        loadBalancerList.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        loadBalancerList.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        loadBalancerList.setLoadBalancerName(property.getTextContent());
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
                                    loadBalancerInstance.setInstanceId(loadBalancerInstanceProperty.getTextContent());
                                } else if (loadBalancerInstanceProperty.getNodeName().equals("ipaddress")) {
                                    loadBalancerInstance.setIpAddress(loadBalancerInstanceProperty.getTextContent());
                                } else if (loadBalancerInstanceProperty.getNodeName().equals("name")) {
                                    loadBalancerInstance.setInstanceName(loadBalancerInstanceProperty.getTextContent());
                                } else if (loadBalancerInstanceProperty.getNodeName().equals("state")) {
                                    loadBalancerInstance.setInstanceState(loadBalancerInstanceProperty.getTextContent());
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
            response.setLoadBalancerRuleId(node.getTextContent());
        }

        // get the account of the load balancer rule
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }
        
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
            response.setDomainName(node.getTextContent());
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
            response.setLoadBalancerName(node.getTextContent());
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
                        loadBalancerInstance.setInstanceId(loadBalancerInstanceProperty.getTextContent());
                    } else if (loadBalancerInstanceProperty.getNodeName().equals("ipaddress")) {
                        loadBalancerInstance.setIpAddress(loadBalancerInstanceProperty.getTextContent());
                    } else if (loadBalancerInstanceProperty.getNodeName().equals("name")) {
                        loadBalancerInstance.setInstanceName(loadBalancerInstanceProperty.getTextContent());
                    } else if (loadBalancerInstanceProperty.getNodeName().equals("state")) {
                        loadBalancerInstance.setInstanceState(loadBalancerInstanceProperty.getTextContent());
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
     * Deletes a load balancer.
     *
     * @param loadBalancerId the ID of the load balancer
     * @param optional
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
