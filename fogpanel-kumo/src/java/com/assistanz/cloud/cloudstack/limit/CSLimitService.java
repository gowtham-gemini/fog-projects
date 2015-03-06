package com.assistanz.cloud.cloudstack.limit;

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
public class CSLimitService {
	
	private CloudStackServer server;
	
	public CSLimitService(CloudStackServer server) {
		this.server = server;
	}
	
	/**
	 * Updates resource limits for an account or domain.
	 * 
	 * @param resourceType
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public UpdateResourceLimitResponse updateResourceLimit(String resourceType,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("updateResourceLimit", optional);
		arguments.add(new NameValuePair("resourcetype",  resourceType));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getUpdateResourceLimitResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into UpdateResourceLimitResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private UpdateResourceLimitResponse getUpdateResourceLimitResponse(Document doc) {
		UpdateResourceLimitResponse response = new UpdateResourceLimitResponse();

		// get account from XML and set as the account of the resource limit
		NodeList list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitAccount(node.getNodeValue());
		}
		
		// get domain from XML and set as the domain name of the resource limit
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set as the domain id of the resource limit
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitDomainId(node.getNodeValue());
		}
		
		// get max from XML and set as the maximum number of the resource. A -1 means the resource currently has no limit.
		list = doc.getElementsByTagName("max");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitMax(node.getNodeValue());
		}
		
		// get project from XML and set as the project name of the resource limit
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitProjectName(node.getNodeValue());
		}
		
		// get projectid from XML and set as the projectid name of the resource limit
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitProjectId(node.getNodeValue());
		}
		
		// get resourcetype from XML and set as resource type. Values include 0, 1, 2, 3, 4. See the resourceType parameter for more information on these values.
		list = doc.getElementsByTagName("resourcetype");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourcetype(node.getNodeValue());
		}
		
		return response;
	}	
	
	/**
	 * Recalculate and update resource count for an account or domain.
	 * 
	 * @param domainId
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public UpdateResourceCountResponse updateResourceCount(String domainId,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("updateResourceCount", optional);
		arguments.add(new NameValuePair("domainid",  domainId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getUpdateResourceCountResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into UpdateResourceCountResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private UpdateResourceCountResponse getUpdateResourceCountResponse(Document doc) {
		UpdateResourceCountResponse response = new UpdateResourceCountResponse();

		// get account from XML and set as the account of the resource limit
		NodeList list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitAccount(node.getNodeValue());
		}
		
		// get domain from XML and set as the domain name of the resource limit
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set as the domain id of the resource limit
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitDomainId(node.getNodeValue());
		}
		
		// get max from XML and set as the maximum number of the resource. A -1 means the resource currently has no limit.
		list = doc.getElementsByTagName("max");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitMax(node.getNodeValue());
		}
		
		// get project from XML and set as the project name of the resource limit
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitProjectName(node.getNodeValue());
		}
		
		// get projectid from XML and set as the projectid name of the resource limit
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitProjectId(node.getNodeValue());
		}
		
		// get resourcetype from XML and set as resource type. Values include 0, 1, 2, 3, 4. See the resourceType parameter for more information on these values.
		list = doc.getElementsByTagName("resourcetype");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourcetype(node.getNodeValue());
		}
		
		return response;
	}
	
	/**
	 * Lists resource limits.
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListResourceLimitsResponse listResourceLimits(
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listResourceLimits", optional);
				
		Document responseDocument = server.makeRequest(arguments);
		
		return getListResourceLimitsResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListResourceLimitsResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListResourceLimitsResponse getListResourceLimitsResponse(Document doc) {
		ListResourceLimitsResponse response = new ListResourceLimitsResponse();

		// get account from XML and set as the account of the resource limit
		NodeList list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitAccount(node.getNodeValue());
		}
		
		// get domain from XML and set as the domain name of the resource limit
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set as the domain id of the resource limit
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitDomainId(node.getNodeValue());
		}
		
		// get max from XML and set as the maximum number of the resource. A -1 means the resource currently has no limit.
		list = doc.getElementsByTagName("max");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitMax(node.getNodeValue());
		}
		
		// get project from XML and set as the project name of the resource limit
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitProjectName(node.getNodeValue());
		}
		
		// get projectid from XML and set as the projectid name of the resource limit
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourceLimitProjectId(node.getNodeValue());
		}
		
		// get resourcetype from XML and set as resource type. Values include 0, 1, 2, 3, 4. See the resourceType parameter for more information on these values.
		list = doc.getElementsByTagName("resourcetype");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setResourcetype(node.getNodeValue());
		}
		
		return response;
	}


}
