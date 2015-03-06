package com.assistanz.cloud.cloudstack.storage;

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
public class CSStorageService {
	
	private CloudStackServer server;
	
	public CSStorageService(CloudStackServer server) {
		this.server = server;
	}
	
	/**
	 * Create a LUN from a pool
	 * 
	 * @param poolName The pool name
	 * @param lunSize The LUN size.
	 * @return
	 * @throws Exception
	 */
	public CreateLunOnFilerResponse createLunOnFiler(String poolName,
			String lunSize) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("createLunOnFiler", null);
		arguments.add(new NameValuePair("name",  poolName));
		arguments.add(new NameValuePair("size",  lunSize));
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getCreateLunOnFilerResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into CreateLunOnFilerResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private CreateLunOnFilerResponse getCreateLunOnFilerResponse(Document doc) {
		CreateLunOnFilerResponse response = new CreateLunOnFilerResponse();

		// get ipaddress from XML and set as the storage ip address
		NodeList list = doc.getElementsByTagName("ipaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setStorageIpAddress(node.getNodeValue());
		}
		
		//get iqn from XML and set as the ISCSI Qualified name
		list = doc.getElementsByTagName("iqn");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setIscsiQualifiedName(node.getNodeValue());
		}
		
		//get path from XML and set as the storage pool path
		list = doc.getElementsByTagName("path");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setStoragePoolPath(node.getNodeValue());
		}
		
		return response;
	}
	
	/**
	 * List LUN
	 * 
	 * @param poolName the pool name
	 * @return
	 * @throws Exception
	 */
	public ListLunsOnFilerResponse listLunsOnFiler(String poolName) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listLunsOnFiler", null);
		arguments.add(new NameValuePair("poolname",  poolName));
		
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getListLunsOnFilerResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListLunsOnFilerResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListLunsOnFilerResponse getListLunsOnFilerResponse(Document doc) {
		ListLunsOnFilerResponse response = new ListLunsOnFilerResponse();

		// get id from XML and set as the lun id
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setLunId(node.getNodeValue());
		}
		
		//get iqn from XML and set as the lun ISCSI Qualified name
		list = doc.getElementsByTagName("iqn");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setLunIqn(node.getNodeValue());
		}
		
		//get name from XML and set as the lun name
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setLunName(node.getNodeValue());
		}
		
		//get volumeid from XML and set as the lun volume id
		list = doc.getElementsByTagName("volumeid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setLunVolumeId(node.getNodeValue());
		}
		
		return response;
	}
	
	/**
	 * Associate a LUN with a guest IQN
	 *  
	 * @param lunIqn  Guest IQN to which the LUN associate.
	 * @param lunName the LUN name
	 * @return
	 * @throws Exception
	 */
	public AssociateLunResponse associateLun( String lunIqn, String lunName) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("associateLun", null);
		arguments.add(new NameValuePair("iqn",  lunIqn));
		arguments.add(new NameValuePair("name",  lunName));
		
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getAssociateLunResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into AssociateLunResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private AssociateLunResponse getAssociateLunResponse(Document doc) {
		AssociateLunResponse response = new AssociateLunResponse();

		// get id from XML and set as the lun id
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setLunId(node.getNodeValue());
		}
		
		//get ipaddress from XML and set as the lun ip address
		list = doc.getElementsByTagName("ipaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setLunIpAddress(node.getNodeValue());
		}
		
		//get targetiqn from XML and set as the lun target iqn
		list = doc.getElementsByTagName("targetiqn");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setTargetIqn(node.getNodeValue());
		}
		
		return response;
	}
	
	
	/**
	 * Dissociate a LUN
	 * 
	 * @param lunIqn Guest IQN
	 * @param lunPath LUN path
	 * @throws Exception
	 */
	public void dissociateLun( String lunIqn, String lunPath) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("dissociateLun", null);
		arguments.add(new NameValuePair("iqn",  lunIqn));
		arguments.add(new NameValuePair("path",  lunPath));
					
	}
	
	/**
	 * Destroy a LUN
	 * 
	 * @param lunPath LUN path
	 * @throws Exception
	 */
	public void destroyLunOnFiler(String lunPath) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("dissociateLun", null);
		arguments.add(new NameValuePair("path",  lunPath));
					
	}

}
