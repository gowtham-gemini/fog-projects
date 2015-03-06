package com.assistanz.cloud.cloudstack.ldap;

import java.util.HashMap;
import java.util.LinkedList;

import com.assistanz.cloud.cloudstack.CloudStackServer;
//import org.apache.http.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Santhosh
 *
 */
public class CSLDAPService {

    private CloudStackServer server;

    public CSLDAPService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Configure the LDAP context for this site.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public LDAPConfigServiceResponse ldapConfig(HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("ldapConfig", optional);

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
            response.setBindDn(node.getTextContent());
        }

        // get bindpass from XML and set as distinguished name password
        list = doc.getElementsByTagName("bindpass");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBindPass(node.getTextContent());
        }

        // get hostname from XML and set as Hostname or ip address of the ldap server
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get port from XML and set as Specify the LDAP port if required, default is 389
        list = doc.getElementsByTagName("port");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPort(node.getTextContent());
        }

        // get queryfilter from XML and set as 	You specify a query filter here, which narrows down the users, 
        // who can be part of this domain
        list = doc.getElementsByTagName("queryfilter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setQueryFilter(node.getTextContent());
        }

        // get searchbase from XML and set as The search base defines the starting point for 
        // the search in the directory tree Example: dc=cloud,dc=com
        list = doc.getElementsByTagName("searchbase");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSearchBase(node.getTextContent());
        }

        // get ssl from XML and set as Check Use SSL if the external LDAP server is configured for LDAP over SSL
        list = doc.getElementsByTagName("port");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSsl(node.getTextContent());
        }

        return response;
    }

    /**
     * Remove the LDAP context for this site.
     *
     * @return
     * @throws Exception
     */
    public LDAPRemoveServiceResponse ldapRemove() throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("ldapConfig", null);

        Document responseDocument = server.makeRequest(arguments);

        return getLDAPRemoveServiceResponse(responseDocument);
    }

    /**
     * Converts XML document into LDAPRemoveServiceResponse object
     *
     * @param doc
     * @return
     */
    private LDAPRemoveServiceResponse getLDAPRemoveServiceResponse(Document doc) {
        LDAPRemoveServiceResponse response = new LDAPRemoveServiceResponse();

        // get binddn from XML and set as distinguished name of a user
        NodeList list = doc.getElementsByTagName("binddn");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBindDn(node.getTextContent());
        }

        // get bindpass from XML and set as distinguished name password
        list = doc.getElementsByTagName("bindpass");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBindPass(node.getTextContent());
        }

        // get hostname from XML and set as Hostname or ip address of the ldap server
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get port from XML and set as Specify the LDAP port if required, default is 389
        list = doc.getElementsByTagName("port");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPort(node.getTextContent());
        }

        // get queryfilter from XML and set as 	You specify a query filter here, which narrows down the users, 
        // who can be part of this domain
        list = doc.getElementsByTagName("queryfilter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setQueryFilter(node.getTextContent());
        }

        // get searchbase from XML and set as The search base defines the starting point for 
        // the search in the directory tree Example: dc=cloud,dc=com
        list = doc.getElementsByTagName("searchbase");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSearchBase(node.getTextContent());
        }

        // get ssl from XML and set as Check Use SSL if the external LDAP server is configured for LDAP over SSL
        list = doc.getElementsByTagName("port");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSsl(node.getTextContent());
        }

        return response;
    }

}
