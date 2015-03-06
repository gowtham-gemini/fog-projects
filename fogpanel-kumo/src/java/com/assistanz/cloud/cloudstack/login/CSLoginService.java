package com.assistanz.cloud.cloudstack.login;

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
public class CSLoginService {

	private CloudStackServer server;
	
	public CSLoginService(CloudStackServer server) {
		this.server = server;
		
	}
	
	public LoginServiceResponse login(String username, String password,
			HashMap<String, String> optional)throws Exception {
		
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("login", optional);
        arguments.add(new NameValuePair("username", username));
        arguments.add(new NameValuePair("password", password));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getLoginServiceResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into RegistrationServiceesponse object
	 * 
	 * @param doc
	 * @return
	 */
	private LoginServiceResponse getLoginServiceResponse(Document doc) {
		LoginServiceResponse response = new LoginServiceResponse();
		
		
		// get username from XML and set as Username
        NodeList list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserName(node.getNodeValue());
        }
                       
        // get userid from XML and set as user id
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserId(node.getNodeValue());
        }
        
        // get password from XML and set as password
        list = doc.getElementsByTagName("password");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPassword(node.getNodeValue());
        }
        
        // get domainid from XML and set as domainid
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getNodeValue());
        }
        
        // get timeout from XML and set as timeout
        list = doc.getElementsByTagName("timeout");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTimeout(node.getNodeValue());
        }
                
        // get account from XML and set as account Name 
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getNodeValue());
        }
        
        // get firstname from XML and set as firstname
        list = doc.getElementsByTagName("firstname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFirstName(node.getNodeValue());
        }
        
        // get lastname from XML and set as lastname
        list = doc.getElementsByTagName("lastname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLastName(node.getNodeValue());
        }
        
        // get type from XML and set as account type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountType(node.getNodeValue());
        }
        
        // get timezone from XML and set as timezone
        list = doc.getElementsByTagName("timezone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTimeZone(node.getNodeValue());
        }
        
        // get timezoneoffset from XML and set as time zone offset
        list = doc.getElementsByTagName("timezoneoffset");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTimeZoneOffset(node.getNodeValue());
        }
        
        // get sessionkey from XML and set as session key
        list = doc.getElementsByTagName("sessionkey");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSessionKey(node.getNodeValue());
        }
		
		return response;
	}

}
