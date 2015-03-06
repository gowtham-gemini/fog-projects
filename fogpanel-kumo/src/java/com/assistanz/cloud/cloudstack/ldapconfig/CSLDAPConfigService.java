package com.assistanz.cloud.cloudstack.ldapconfig;

import java.util.HashMap;
import java.util.LinkedList;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Gowtham
 *
 */
public class CSLDAPConfigService {
	
	private CloudStackServer server;
	
	public CSLDAPConfigService(CloudStackServer server) {
        this.server = server;
    }
	
	/**
	 * Configure the LDAP context for this site.
	 * 
	 * @param hostName Host name or Ip address of the ldap server eg: my.ldap.com
	 * @param queryFilter You specify a query filter here, 
	 * 					  which narrows down the users, who can be part of this domain.
	 * @param searchBase The search base defines the starting point for the search in the directory tree 
	 * 					 Example: dc=cloud,dc=com.
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public LDAPConfigServiceResponse ldapConfig(String hostName, String queryFilter,
			String searchBase, HashMap<String, String> optional)throws Exception {
		

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("ldapConfig", optional);
        arguments.add(new NameValuePair("hostname", hostName));
        arguments.add(new NameValuePair("hostname", queryFilter));
        arguments.add(new NameValuePair("searchbase", searchBase));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getLDAPConfigServiceResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into LDAPConfigServiceResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private LDAPConfigServiceResponse getLDAPConfigServiceResponse(Document doc) {
		LDAPConfigServiceResponse response = new LDAPConfigServiceResponse();
				
		// get binddn from XML and set as distinguished name of a user
        NodeList list = doc.getElementsByTagName("binddn");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBindDistinguishedName(node.getNodeValue());
        }
                
        // get bindpass from XML and set as distinguished name password
        list = doc.getElementsByTagName("bindpass");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBindDistinguishedNamePass(node.getNodeValue());
        }
        
        // get hostname from XML and set as Hostname or ip address of the ldap server
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getNodeValue());
        }
        
        // get port from XML and set as Specify the LDAP port if required, default is 389
        list = doc.getElementsByTagName("port");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLdpaPort(node.getNodeValue());
        }
        
        // get port from XML and set as Check Use SSL if the external LDAP server is configured for LDAP over SSL
        list = doc.getElementsByTagName("port");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSslPort(node.getNodeValue());
        }
        
        // get queryfilter from XML and set as 	You specify a query filter here, which narrows down the users, 
        // who can be part of this domain
        list = doc.getElementsByTagName("queryfilter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setQueryFilter(node.getNodeValue());
        }
        
        // get searchbase from XML and set as The search base defines the starting point for 
        // the search in the directory tree Example: dc=cloud,dc=com
        list = doc.getElementsByTagName("searchbase");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSearchBase(node.getNodeValue());
        }
		
		return response;
	}


}
