package com.assistanz.cloud.cloudstack.certificate;

import java.util.HashMap;
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
public class CSCertificateService {
	
	private CloudStackServer server;
	 
	 public CSCertificateService(CloudStackServer server) {
	        this.server = server;
	 }
	 
	 /**
	  * Uploads custom certificate
	  * 
	  * @param certificate the custom certificate to be uploaded
	  * @param domainSuffix DNS domain suffix that the certificate is granted for
	  * @param optional
	  * @return
	  * @throws Exception
	  */
	 public CertificateResponse uploadCustomCertificate(String certificate, 
			 String domainSuffix, HashMap<String,String> optional) 
			 throws Exception {
	        
	        LinkedList<NameValuePair> arguments = 
	                server.getDefaultQuery("uploadCustomCertificate", optional);
	        arguments.add(new NameValuePair("certificate", certificate));
	        arguments.add(new NameValuePair("domainsuffix", domainSuffix));
	        	        	        
	        Document responseDocument = server.makeRequest(arguments);

	        return getCertificateResponse(responseDocument);
	 }
	 
	 /**
	   * Converts XML document into CertificateResponse object
	   * 
	   * @param doc
	   * @return
	   */
	  private CertificateResponse getCertificateResponse(Document doc) {
		  CertificateResponse response = new CertificateResponse();
					
			// get message from XML and set as the message of the certificate upload operation
	        NodeList list = doc.getElementsByTagName("message");
	        if (list.getLength() > 0) {
	            Node node = list.item(0);
	            response.setMessage(node.getNodeValue());
	        }
	        
	        return response;
		}
}
