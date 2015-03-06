package com.assistanz.cloud.cloudstack.registration;

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
public class CSRegistrationService {
	
	private CloudStackServer server;
	
	public CSRegistrationService(CloudStackServer server) {
        this.server = server;
    }
	
	/**
	 * Register a public key in a keypair under a certain name
	 * 
	 * @param keyPairName Name of the keypair
	 * @param publickey Public key material of the keypai
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public RegistrationServiceResponse registration(String keyPairName, String publickey,
			HashMap<String, String> optional)throws Exception {
		

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("registration", optional);
        arguments.add(new NameValuePair("name", keyPairName));
        arguments.add(new NameValuePair("napublickeyme", publickey));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getRegistrationServiceResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into RegistrationServiceesponse object
	 * 
	 * @param doc
	 * @return
	 */
	private RegistrationServiceResponse getRegistrationServiceResponse(Document doc) {
		RegistrationServiceResponse response = new RegistrationServiceResponse();
				
		// get fingerprint from XML and set as Fingerprint of the public key
        NodeList list = doc.getElementsByTagName("fingerprint");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFingerPrint(node.getNodeValue());
        }
                
        // get name from XML and set as Name of the keypair
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPairName(node.getNodeValue());
        }
        
        // get privatekey from XML and set as Private key
        list = doc.getElementsByTagName("privatekey");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateKey(node.getNodeValue());
        }
		
		return response;
	}

}
