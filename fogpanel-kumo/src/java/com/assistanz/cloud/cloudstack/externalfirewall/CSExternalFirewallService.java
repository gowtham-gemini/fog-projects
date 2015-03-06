package com.assistanz.cloud.cloudstack.externalfirewall;


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
public class CSExternalFirewallService {
	
	private CloudStackServer server;
	
	public CSExternalFirewallService(CloudStackServer server) {
		this.server = server;
	}
	
	
	/**
	 * Adds an external firewall appliance
	 * 
	 * @param externalFirewallPassword Password of the external firewall appliance.
	 * @param externalFirewallUrl URL of the external firewall appliance.
	 * @param externalFirewallUserName Username of the external firewall appliance.
	 * @param externalFirewallZoneId Zone in which to add the external firewall appliance.
	 * @return
	 * @throws Exception
	 */
	public AddExternalFirewallResponse addExternalFirewall(String externalFirewallPassword,
			String externalFirewallUrl, String externalFirewallUserName,
			String externalFirewallZoneId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("addExternalFirewall", null);
		arguments.add(new NameValuePair("password",   externalFirewallPassword));
		arguments.add(new NameValuePair("url",   externalFirewallUrl));
		arguments.add(new NameValuePair("username",   externalFirewallUserName));
		arguments.add(new NameValuePair("zoneid",   externalFirewallZoneId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getAddExternalFirewallResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into AddExternalFirewallResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private AddExternalFirewallResponse getAddExternalFirewallResponse(Document doc) {
		AddExternalFirewallResponse response = new AddExternalFirewallResponse();

		// get id from XML and set as the ID of the external firewall
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallId(node.getNodeValue());
		}
		
		// get ipaddress from XML and set as the management IP address of the external firewall
		list = doc.getElementsByTagName("ipaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallIpAddress(node.getNodeValue());
		}
		
		// get numretries from XML and set as the number of times to retry requests to the external firewall
		list = doc.getElementsByTagName("numretries");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setNumRetriesExternalFirewall(node.getNodeValue());
		}
		
		// get privateinterface from XML and set as the private interface of the external firewall
		list = doc.getElementsByTagName("privateinterface");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallPrivateInterface(node.getNodeValue());
		}
		
		// get privatezone from XML and set as the private zone of the external firewall
		list = doc.getElementsByTagName("privatezone");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallPrivateZone(node.getNodeValue());
		}
		
		// get publicinterface from XML and set as the public interface of the external firewall
		list = doc.getElementsByTagName("publicinterface");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallPublicInterface(node.getNodeValue());
		}
		
		// get publiczone from XML and set as the public zone of the external firewall
		list = doc.getElementsByTagName("publiczone");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallPublicZone(node.getNodeValue());
		}
		
		// get timeout from XML and set as the timeout of the external firewall
		list = doc.getElementsByTagName("timeout");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallTimeout(node.getNodeValue());
		}
		
		// get usageinterface from XML and set as the usage interface of the external firewall
		list = doc.getElementsByTagName("usageinterface");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallUsageInterface(node.getNodeValue());
		}
		
		// get username from XML and set as the username that's used to log in to the external firewall
		list = doc.getElementsByTagName("username");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallUserName(node.getNodeValue());
		}
		
		// get zoneid from XML and set as the zone ID of the external firewall
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallZoneId(node.getNodeValue());
		}
		
		return response;
	}	
	
	/**
	 * Deletes an external firewall appliance.
	 * 
	 * @param externalFirewallId Id of the external firewall appliance.
	 * @return
	 * @throws Exception
	 */
	public DeleteExternalFirewallResponse deleteExternalFirewall(String externalFirewallId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteExternalFirewall", null);
		arguments.add(new NameValuePair("id",  externalFirewallId));
				
		Document responseDocument = server.makeRequest(arguments);
		
		return getDeleteExternalFirewallResponse(responseDocument);
	}

	
	/**
	 * Converts XML document into DeleteExternalFirewallResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private DeleteExternalFirewallResponse getDeleteExternalFirewallResponse(Document doc) {
		DeleteExternalFirewallResponse response = new DeleteExternalFirewallResponse();

		// get displaytext from XML and set any text associated with the success or failure on Delete external firewall 
		NodeList list = doc.getElementsByTagName("displaytext");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDisplaytext(node.getNodeValue());
		}
		//get success from XML and any text associated with the success or failure on deleting Delete external firewall 
		list = doc.getElementsByTagName("success");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setSuccess(node.getNodeValue());
		}
		
		return response;
	}
	
	/**
	 * List external firewall appliances.
	 * 
	 * @param externalFirewallZoneId the zone Id
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListExternalFirewallsResponse listExternalFirewalls(
			String externalFirewallZoneId, HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listExternalFirewalls", optional);
		arguments.add(new NameValuePair("zoneid",   externalFirewallZoneId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getListExternalFirewallsResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListExternalFirewallsResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListExternalFirewallsResponse getListExternalFirewallsResponse(Document doc) {
		ListExternalFirewallsResponse response = new ListExternalFirewallsResponse();

		// get id from XML and set as the ID of the external firewall
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallId(node.getNodeValue());
		}
		
		// get ipaddress from XML and set as the management IP address of the external firewall
		list = doc.getElementsByTagName("ipaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallIpAddress(node.getNodeValue());
		}
		
		// get numretries from XML and set as the number of times to retry requests to the external firewall
		list = doc.getElementsByTagName("numretries");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setNumRetriesExternalFirewall(node.getNodeValue());
		}
		
		// get privateinterface from XML and set as the private interface of the external firewall
		list = doc.getElementsByTagName("privateinterface");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallPrivateInterface(node.getNodeValue());
		}
		
		// get privatezone from XML and set as the private zone of the external firewall
		list = doc.getElementsByTagName("privatezone");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallPrivateZone(node.getNodeValue());
		}
		
		// get publicinterface from XML and set as the public interface of the external firewall
		list = doc.getElementsByTagName("publicinterface");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallPublicInterface(node.getNodeValue());
		}
		
		// get publiczone from XML and set as the public zone of the external firewall
		list = doc.getElementsByTagName("publiczone");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallPublicZone(node.getNodeValue());
		}
		
		// get timeout from XML and set as the timeout of the external firewall
		list = doc.getElementsByTagName("timeout");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallTimeout(node.getNodeValue());
		}
		
		// get usageinterface from XML and set as the usage interface of the external firewall
		list = doc.getElementsByTagName("usageinterface");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallUsageInterface(node.getNodeValue());
		}
		
		// get username from XML and set as the username that's used to log in to the external firewall
		list = doc.getElementsByTagName("username");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallUserName(node.getNodeValue());
		}
		
		// get zoneid from XML and set as the zone ID of the external firewall
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setExternalFirewallZoneId(node.getNodeValue());
		}
		
		return response;
	}

}
