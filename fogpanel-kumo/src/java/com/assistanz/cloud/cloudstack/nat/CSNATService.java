package com.assistanz.cloud.cloudstack.nat;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assistanz.cloud.cloudstack.CloudStackServer;



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
	public EnableStaticNatResponse enableStaticNat(String ipAddressId,
			String virtualMachineId, HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("enableStaticNat", optional);
		arguments.add(new NameValuePair("ipaddressid",  ipAddressId));
		arguments.add(new NameValuePair("virtualmachineid",  virtualMachineId));
		
		
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
	 * @param protocol 	the protocol for the rule. Valid values are TCP or UDP.
	 * @param startPort the start port for the rule
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public CreateIpForwardingRuleResponse createIpForwardingRule(String ipAddressId,
			String protocol, String startPort, HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("createIpForwardingRule", optional);
		arguments.add(new NameValuePair("ipaddressid",  ipAddressId));
		arguments.add(new NameValuePair("protocol",  protocol));
		arguments.add(new NameValuePair("startport",  startPort));
		
		
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
		    response.setPortForwardingRuleId(node.getTextContent());
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
		    response.setPortForwardingRuleIpAddress(node.getTextContent());
		}
		
		// get ipaddressid from XML and set as the ip address id of the port forwarding rule
		list = doc.getElementsByTagName("ipaddressid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleIpaddressId(node.getTextContent());
		}
		
		// get privateendport from XML and set as the private end port of the port forwarding rule
		list = doc.getElementsByTagName("privateendport");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRulePrivateEndPort(node.getTextContent());
		}
		
		// get privateport from XML and set as the private port of the port forwarding rule
		list = doc.getElementsByTagName("privateport");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRulePrivateStartPort(node.getTextContent());
		}
		
		// get protocol from XML and set as the protocol of the port forwarding rule
		list = doc.getElementsByTagName("protocol");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleProtocol(node.getTextContent());
		}
		
		// get publicendport from XML and set as the public end port of the port forwarding rule
		list = doc.getElementsByTagName("publicendport");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRulePublicEndPort(node.getTextContent());
		}
		
		// get publicport from XML and set as the public port of the port forwarding rule
		list = doc.getElementsByTagName("publicport");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRulePublicStartPort(node.getTextContent());
		}
		
		// get state from XML and set as the state of the port forwarding rule
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleState(node.getTextContent());
		}
		
		// get virtualmachinedisplayname from XML and set as the virtual machine display name of the port forwarding rule
		list = doc.getElementsByTagName("virtualmachinedisplayname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleVirtualmachineDisplayName(node.getTextContent());
		}
		
		// get virtualmachineid from XML and set as the virtual machine display name of the port forwarding rule
		list = doc.getElementsByTagName("virtualmachineid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleVirtualMachineId(node.getTextContent());
		}
		
		// get virtualmachinename from XML and set as the virtual machine name of the port forwarding rule
		list = doc.getElementsByTagName("virtualmachinename");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleVirtualMachineName(node.getTextContent());
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

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteIpForwardingRule", null);
		arguments.add(new NameValuePair("id",  ipForwardingRuleId));
		
		
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
	public ListIpForwardingRuleResponse listIpForwardingRule(
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listIpForwardingRule", optional);
		
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getListIpForwardingRuleResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListIpForwardingRuleResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListIpForwardingRuleResponse getListIpForwardingRuleResponse(Document doc) {
		ListIpForwardingRuleResponse response = new ListIpForwardingRuleResponse();

		// get id from XML and set as the ID of the port forwarding rule
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleId(node.getTextContent());
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
		    response.setPortForwardingRuleIpAddress(node.getTextContent());
		}
		
		// get ipaddressid from XML and set as the ip address id of the port forwarding rule
		list = doc.getElementsByTagName("ipaddressid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleIpaddressId(node.getTextContent());
		}
		
		// get privateendport from XML and set as the private end port of the port forwarding rule
		list = doc.getElementsByTagName("privateendport");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRulePrivateEndPort(node.getTextContent());
		}
		
		// get privateport from XML and set as the private port of the port forwarding rule
		list = doc.getElementsByTagName("privateport");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRulePrivateStartPort(node.getTextContent());
		}
		
		// get protocol from XML and set as the protocol of the port forwarding rule
		list = doc.getElementsByTagName("protocol");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleProtocol(node.getTextContent());
		}
		
		// get publicendport from XML and set as the public end port of the port forwarding rule
		list = doc.getElementsByTagName("publicendport");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRulePublicEndPort(node.getTextContent());
		}
		
		// get publicport from XML and set as the public port of the port forwarding rule
		list = doc.getElementsByTagName("publicport");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRulePublicStartPort(node.getTextContent());
		}
		
		// get state from XML and set as the state of the port forwarding rule
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleState(node.getTextContent());
		}
		
		// get virtualmachinedisplayname from XML and set as the virtual machine display name of the port forwarding rule
		list = doc.getElementsByTagName("virtualmachinedisplayname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleVirtualmachineDisplayName(node.getTextContent());
		}
		
		// get virtualmachineid from XML and set as the virtual machine display name of the port forwarding rule
		list = doc.getElementsByTagName("virtualmachineid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleVirtualMachineId(node.getTextContent());
		}
		
		// get virtualmachinename from XML and set as the virtual machine name of the port forwarding rule
		list = doc.getElementsByTagName("virtualmachinename");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setPortForwardingRuleVirtualMachineName(node.getTextContent());
		}
		
		return response;
	}
        
       public DisableStaticNatResponse disableStaticNat(String ipaddressid) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("disableStaticNat", null);
		arguments.add(new NameValuePair("ipaddressid",  ipaddressid));
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getDisableStaticNatResponse(responseDocument);
	} 
        
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
        
	
	/**
	 * Disables static rule for given ip address
	 * 
	 * @param ipForwardingRuleId
	 * @return
	 * @throws Exception
	 */
	public DisableIpForwardingRuleResponse disableIpForwardingRule(String ipForwardingRuleId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("disableIpForwardingRule", null);
		arguments.add(new NameValuePair("id",  ipForwardingRuleId));
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getDisableIpForwardingRuleResponse(responseDocument);
	}

	
	/**
	 * Converts XML document into DisableIpForwardingRuleResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private DisableIpForwardingRuleResponse getDisableIpForwardingRuleResponse(Document doc) {
		DisableIpForwardingRuleResponse response = new DisableIpForwardingRuleResponse();

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
		
		return response;
	}
}
