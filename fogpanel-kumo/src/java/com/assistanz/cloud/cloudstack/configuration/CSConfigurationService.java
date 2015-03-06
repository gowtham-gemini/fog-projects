package com.assistanz.cloud.cloudstack.configuration;

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
public class CSConfigurationService {
	
    private CloudStackServer server;

    public CSConfigurationService(CloudStackServer server) {
            this.server = server;
    }
	
    /**
     * Updates a configuration.
     * 
     * @param configurationName The name of the configuration
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateConfigurationResponse updateConfiguration(String configurationName,
                    HashMap<String,String> optional)throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("updateConfiguration", optional);
            arguments.add(new NameValuePair("name", configurationName));

            Document responseDocument = server.makeRequest(arguments);

            return getUpdateConfigurationResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateConfigurationResponse object
     * 
     * @param doc
     * @return
     */
    private UpdateConfigurationResponse getUpdateConfigurationResponse(Document doc) {
            UpdateConfigurationResponse response = new UpdateConfigurationResponse();

           // get category from XML and set as the category of the configuration
       NodeList list = doc.getElementsByTagName("category");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setConfigurationCategory(node.getNodeValue());
       }

       // get description from XML and set as the description of the configuration
       list = doc.getElementsByTagName("description");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setConfigurationDescription(node.getNodeValue());
       }

       // get name from XML and set as the name of the configuration
       list = doc.getElementsByTagName("name");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setConfigurationName(node.getNodeValue());
       }

       // get value from XML and set as the value of the configuration
       list = doc.getElementsByTagName("value");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setConfigurationValue(node.getNodeValue());
       }

       return response;
   }
	
    /**
     * Lists all configurations.
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public ListConfigurationResponse listConfigurations(HashMap<String,String> optional)
                   throws Exception {

            LinkedList<NameValuePair> arguments = 
                   server.getDefaultQuery("listConfigurations", optional);

           Document responseDocument = server.makeRequest(arguments);

           return getListConfigurationResponse(responseDocument);

    }
	
    /**
     * Converts XML document into ListConfigurationResponse object
     * 
     * @param doc
     * @return
     */
    private ListConfigurationResponse getListConfigurationResponse(Document doc) {
            ListConfigurationResponse response = new ListConfigurationResponse();

           // get category from XML and set as the category of the configuration
       NodeList list = doc.getElementsByTagName("category");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setConfigurationCategory(node.getTextContent());
       }

       // get description from XML and set as the description of the configuration
       list = doc.getElementsByTagName("description");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setConfigurationDescription(node.getTextContent());
       }

       // get name from XML and set as the name of the configuration
       list = doc.getElementsByTagName("name");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setConfigurationName(node.getTextContent());
       }

       // get value from XML and set as the value of the configuration
       list = doc.getElementsByTagName("value");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setConfigurationValue(node.getTextContent());
       }

       return response;

    }

    /**
     * Lists capabilities
     * 
     * @return
     * @throws Exception
     */
    public ListCapabilitiesResponse listCapabilities()
                           throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listCapabilities", null);

        Document responseDocument = server.makeRequest(arguments);

        return getListCapabilitiesResponse(responseDocument);

    }

    /**
     * Converts XML document into ListCapabilitiesResponse object
     * 
     * @param doc
     * @return
     */
    private ListCapabilitiesResponse getListCapabilitiesResponse(Document doc) {
            ListCapabilitiesResponse response = new ListCapabilitiesResponse();

           // get allowusercreateprojects from XML and set as true if regular user is allowed to create projects
       NodeList list = doc.getElementsByTagName("allowusercreateprojects");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setAllowUserCreateProjects(node.getTextContent());
       }

       // get cloudstackversion from XML and set as version of the cloud stack
       list = doc.getElementsByTagName("cloudstackversion");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setCloudStackVersion(node.getTextContent());
       }
       
       
       // get cloudstackversion from XML and set maximum size that can be specified when create disk from disk offering with custom size
       list = doc.getElementsByTagName("customdiskofferingmaxsize");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setCustomDiskofferingMaxSize(node.getTextContent());
       }
       

       // get projectinviterequired from XML and set If invitation confirmation is required when add account to project
       list = doc.getElementsByTagName("projectinviterequired");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setProjectInviteRequired(node.getTextContent());
       }

       // get securitygroupsenabled from XML and set as true if security groups support is enabled, otherwise false
       list = doc.getElementsByTagName("securitygroupsenabled");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setSecurityGroupsEnabled(node.getTextContent());
       }

       // get supportELB from XML and set as true if region supports elastic load balancer on basic zones
       list = doc.getElementsByTagName("supportELB");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setSupportELB(node.getTextContent());
       }

       // get userpublictemplateenabled from XML and set as true if user and domain admins can set templates to be shared, otherwise false
       list = doc.getElementsByTagName("userpublictemplateenabled");
       if (list.getLength() > 0) {
           Node node = list.item(0);
           response.setUserPublicTemplateEnabled(node.getTextContent());
       }

       return response;

    }

}
