package com.assistanz.cloud.cloudstack.vlan;

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
public class CSVLANService {
	
	private CloudStackServer server;
	
	public CSVLANService(CloudStackServer server) {
		this.server = server;
	}
	
	/**
	 * Creates a VLAN IP range
	 * 
	 * @param startIp the beginning IP address in the VLAN IP range
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public CreateVlanIpRangeResponse createVlanIpRange(String startIp,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("createVlanIpRange", optional);
		arguments.add(new NameValuePair("startip",  startIp));
		
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getCreateVlanIpRangeResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into CreateVlanIpRangeResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private CreateVlanIpRangeResponse getCreateVlanIpRangeResponse(Document doc) {
		CreateVlanIpRangeResponse response = new CreateVlanIpRangeResponse();

		// get id from XML and set as the ID of the VLAN IP range
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanId(node.getNodeValue());
		}
		
		// get account from XML and set as the account of the VLAN IP range
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanAccount(node.getNodeValue());
		}
		
		// get description from XML and set as the description of the VLAN IP range
		list = doc.getElementsByTagName("description");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanDescription(node.getNodeValue());
		}
		
		// get domain from XML and set as the domain of the VLAN IP range
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set as the domainid of the VLAN IP range
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanDomainId(node.getNodeValue());
		}
		
		// get endip from XML and set as the endip of the VLAN IP range
		list = doc.getElementsByTagName("endip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanEndIp(node.getNodeValue());
		}
		
		// get forvirtualnetwork from XML and set as the virtual network for the VLAN IP range
		list = doc.getElementsByTagName("forvirtualnetwork");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanVirtualNetwork(node.getNodeValue());
		}
		
		
		// get gateway from XML and set as the gateway for the VLAN IP range
		list = doc.getElementsByTagName("gateway");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanGateway(node.getNodeValue());
		}
		
		// get netmask from XML and set as the netmask for the VLAN IP range
		list = doc.getElementsByTagName("netmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanNetmask(node.getNodeValue());
		}
		
		// get networkid from XML and set as the networkid for the VLAN IP range
		list = doc.getElementsByTagName("networkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanNetworkId(node.getNodeValue());
		}
		
		// get physicalnetworkid from XML and set as the physicalnetworkid for the VLAN IP range
		list = doc.getElementsByTagName("physicalnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanPhysicalNetworkId(node.getNodeValue());
		}
		
		// get podid from XML and set as the podid for the VLAN IP range
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanPodId(node.getNodeValue());
		}
		
		// get podname from XML and set as the podname for the VLAN IP range
		list = doc.getElementsByTagName("podname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanPodName(node.getNodeValue());
		}
		
		// get project from XML and set as the project name for the VLAN IP range
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanProjectName(node.getNodeValue());
		}
		
		// get projectid from XML and set as the projectid for the VLAN IP range
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanProjectId(node.getNodeValue());
		}
		
		// get startip from XML and set as the startip for the VLAN IP range
		list = doc.getElementsByTagName("startip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanStartIp(node.getNodeValue());
		}
		
		// get vlan from XML and set as the ID or VID of the VLAN.
		list = doc.getElementsByTagName("vlan");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVid(node.getNodeValue());
		}
		
		// get zoneid from XML and set as the zoneid for the VLAN IP range
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanZoneId(node.getNodeValue());
		}
		
		return response;
		
	}
	
	/**
	 * Creates a VLAN IP range
	 * 
	 * @param vlanId the id of the VLAN IP range
	 * @return
	 * @throws Exception
	 */
	public DeleteVlanIpRangeResponse deleteVlanIpRange(String vlanId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteRemoteAccessVpn", null);
		arguments.add(new NameValuePair("id",  vlanId));
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getDeleteVlanIpRangeResponse(responseDocument);
	}

	
	/**
	 * Converts XML document into DeleteVlanIpRangeResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private DeleteVlanIpRangeResponse getDeleteVlanIpRangeResponse(Document doc) {
		DeleteVlanIpRangeResponse response = new DeleteVlanIpRangeResponse();

		// get displaytext from XML and set any text associated with the success or failure on Delete VLAN 
		NodeList list = doc.getElementsByTagName("displaytext");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDisplaytext(node.getNodeValue());
		}
		//get success from XML and any text associated with the success or failure on deleting Delete VLAN 
		list = doc.getElementsByTagName("success");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setSuccess(node.getNodeValue());
		}
		
		return response;
	}
	
	/**
	 * Lists all VLAN IP ranges
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListVlanIpRangesResponse listVlanIpRanges(
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listVlanIpRanges", optional);
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getListVlanIpRangesResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListVlanIpRangesResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListVlanIpRangesResponse getListVlanIpRangesResponse(Document doc) {
		ListVlanIpRangesResponse response = new ListVlanIpRangesResponse();

		// get id from XML and set as the ID of the VLAN IP range
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanId(node.getNodeValue());
		}
		
		// get account from XML and set as the account of the VLAN IP range
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanAccount(node.getNodeValue());
		}
		
		// get description from XML and set as the description of the VLAN IP range
		list = doc.getElementsByTagName("description");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanDescription(node.getNodeValue());
		}
		
		// get domain from XML and set as the domain of the VLAN IP range
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set as the domainid of the VLAN IP range
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanDomainId(node.getNodeValue());
		}
		
		// get endip from XML and set as the endip of the VLAN IP range
		list = doc.getElementsByTagName("endip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanEndIp(node.getNodeValue());
		}
		
		// get forvirtualnetwork from XML and set as the virtual network for the VLAN IP range
		list = doc.getElementsByTagName("forvirtualnetwork");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanVirtualNetwork(node.getNodeValue());
		}
		
		
		// get gateway from XML and set as the gateway for the VLAN IP range
		list = doc.getElementsByTagName("gateway");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanGateway(node.getNodeValue());
		}
		
		// get netmask from XML and set as the netmask for the VLAN IP range
		list = doc.getElementsByTagName("netmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanNetmask(node.getNodeValue());
		}
		
		// get networkid from XML and set as the networkid for the VLAN IP range
		list = doc.getElementsByTagName("networkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanNetworkId(node.getNodeValue());
		}
		
		// get physicalnetworkid from XML and set as the physicalnetworkid for the VLAN IP range
		list = doc.getElementsByTagName("physicalnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanPhysicalNetworkId(node.getNodeValue());
		}
		
		// get podid from XML and set as the podid for the VLAN IP range
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanPodId(node.getNodeValue());
		}
		
		// get podname from XML and set as the podname for the VLAN IP range
		list = doc.getElementsByTagName("podname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanPodName(node.getNodeValue());
		}
		
		// get project from XML and set as the project name for the VLAN IP range
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanProjectName(node.getNodeValue());
		}
		
		// get projectid from XML and set as the projectid for the VLAN IP range
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanProjectId(node.getNodeValue());
		}
		
		// get startip from XML and set as the startip for the VLAN IP range
		list = doc.getElementsByTagName("startip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanStartIp(node.getNodeValue());
		}
		
		// get vlan from XML and set as the ID or VID of the VLAN.
		list = doc.getElementsByTagName("vlan");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVid(node.getNodeValue());
		}
		
		// get zoneid from XML and set as the zoneid for the VLAN IP range
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVlanZoneId(node.getNodeValue());
		}
		
		return response;
		
	}

}
