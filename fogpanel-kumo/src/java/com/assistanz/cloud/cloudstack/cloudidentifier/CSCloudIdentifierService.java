package com.assistanz.cloud.cloudstack.cloudidentifier;


import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assistanz.cloud.cloudstack.CloudStackServer;


import org.apache.commons.httpclient.NameValuePair;

/**
 * 
 * @author Gowtham
 *
 */
public class CSCloudIdentifierService {
	
	 private CloudStackServer server;
	 
	 public CSCloudIdentifierService(CloudStackServer server) {
	        this.server = server;
	    }
	 
	 /**
	  * Retrieves a cloud identifier.
	  * 
	  * @param userId The user ID for the cloud identifier
	  * @return
	  * @throws Exception
	  */
	 public CloudIdentifierResponse getCloudIdentifier(String userId) 
			 throws Exception {
	        
	        LinkedList<NameValuePair> arguments = 
	                server.getDefaultQuery("getCloudIdentifier", null);
	        arguments.add(new NameValuePair("userid", userId));
	        	        
	        Document responseDocument = server.makeRequest(arguments);

	        return getCloudIdentifierResponse(responseDocument);
	  }
	
 	  /**
	   * Converts XML document into CloudIdentifierResponse object
	   * 
	   * @param doc
	   * @return
	   */
	  private CloudIdentifierResponse getCloudIdentifierResponse(Document doc) {
			CloudIdentifierResponse response = new CloudIdentifierResponse();
					
			// get cloudidentifier from XML and set as the cloud identifier
	        NodeList list = doc.getElementsByTagName("cloudidentifier");
	        if (list.getLength() > 0) {
	            Node node = list.item(0);
	            response.setCloudIdentifier(node.getNodeValue());
	        }
	                
	        // get signature from XML and set as the signed response for the cloud identifier
	        list = doc.getElementsByTagName("signature");
	        if (list.getLength() > 0) {
	            Node node = list.item(0);
	            response.setSignature(node.getNodeValue());
	        }
	        
	        // get userid from XML and set as the user ID for the cloud identifier
	        list = doc.getElementsByTagName("userid");
	        if (list.getLength() > 0) {
	            Node node = list.item(0);
	            response.setUserId(node.getNodeValue());
	        }
	        	        			
			return response;
		}
}
