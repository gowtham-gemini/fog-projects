package com.assistanz.cloud.cloudstack.configuration;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateConfiguration", optional);
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

        // get id from XML and set as the value of the configuration
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get category from XML and set as the category of the configuration
        list = doc.getElementsByTagName("category");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCategory(node.getTextContent());
        }

        // get description from XML and set as the description of the configuration
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get name from XML and set as the name of the configuration
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get scope from XML and set as the scope(zone/cluster/pool/account) of the parameter that needs to be updated
        list = doc.getElementsByTagName("scope");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScope(node.getTextContent());
        }

        // get value from XML and set as the value of the configuration
        list = doc.getElementsByTagName("value");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setValue(node.getTextContent());
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
    public ListConfigurationsResponse listConfigurations(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listConfigurations", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListConfigurationsResponse(responseDocument);

    }

    /**
     * Converts XML document into ListConfigurationsResponse object
     *
     * @param doc
     * @return
     */
    private ListConfigurationsResponse getListConfigurationsResponse(Document doc) {
        ListConfigurationsResponse response = new ListConfigurationsResponse();

        NodeList list = doc.getElementsByTagName("configuration");

        List<ConfigurationResponse> configurations = new LinkedList<ConfigurationResponse>();
        if (list.getLength() > 0) {
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node configurationNode = list.item(Index);

                ConfigurationResponse configuration = new ConfigurationResponse();

                NodeList configurationProperties = configurationNode.getChildNodes();
                for (int childIndex = 0; childIndex < configurationProperties.getLength(); childIndex++) {
                    Node property = configurationProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        configuration.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("category")) {
                        configuration.setCategory(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        configuration.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        configuration.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("scope")) {
                        configuration.setScope(property.getTextContent());
                    } else if (property.getNodeName().equals("value")) {
                        configuration.setValue(property.getTextContent());
                    }
                }
                configurations.add(configuration);
            }
        }
        response.setConfigurations(configurations);
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

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listCapabilities", null);

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

        NodeList list = doc.getElementsByTagName("capability");

        List<CapabilitiesResponse> capabilitiess = new LinkedList<CapabilitiesResponse>();
        if (list.getLength() > 0) {
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node capabilitiesNode = list.item(Index);

                CapabilitiesResponse capabilities = new CapabilitiesResponse();

                NodeList capabilitiesProperties = capabilitiesNode.getChildNodes();
                for (int childIndex = 0; childIndex < capabilitiesProperties.getLength(); childIndex++) {
                    Node property = capabilitiesProperties.item(childIndex);

                    if (property.getNodeName().equals("allowusercreateprojects")) {
                        capabilities.setAllowUserCreateProjects(property.getTextContent());
                    } else if (property.getNodeName().equals("apilimitinterval")) {
                        capabilities.setApiLimitInterval(property.getTextContent());
                    } else if (property.getNodeName().equals("apilimitmax")) {
                        capabilities.setApiLimitMax(property.getTextContent());
                    } else if (property.getNodeName().equals("cloudstackversion")) {
                        capabilities.setCloudstackVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("customdiskofferingmaxsize")) {
                        capabilities.setCustomDiskOfferingMaxSize(property.getTextContent());
                    } else if (property.getNodeName().equals("KVMsnapshotenabled")) {
                        capabilities.setKVMSnapshotEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("projectinviterequired")) {
                        capabilities.setProjectInviteRequired(property.getTextContent());
                    } else if (property.getNodeName().equals("regionsecondaryenabled")) {
                        capabilities.setRegionSecondaryEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("securitygroupsenabled")) {
                        capabilities.setSecurityGroupsEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("supportELB")) {
                        capabilities.setSupportELB(property.getTextContent());
                    } else if (property.getNodeName().equals("userpublictemplateenabled")) {
                        capabilities.setUserPublicTemplateEnabled(property.getTextContent());
                    }
                }
                capabilitiess.add(capabilities);
            }
        }
        response.setCapabilitiess(capabilitiess);
        return response;

    }

    /**
     * Lists all DeploymentPlanners available.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListDeploymentPlannersResponse listDeploymentPlanners(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listDeploymentPlanners", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListDeploymentPlannersResponse(responseDocument);

    }

    /**
     * Converts XML document into ListDeploymentPlannersResponse object
     *
     * @param doc
     * @return
     */
    private ListDeploymentPlannersResponse getListDeploymentPlannersResponse(Document doc) {
        ListDeploymentPlannersResponse response = new ListDeploymentPlannersResponse();

        NodeList list = doc.getElementsByTagName("configuration");

        List<DeploymentPlannerResponse> deploymentPlanners = new LinkedList<DeploymentPlannerResponse>();
        if (list.getLength() > 0) {
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node deploymentPlannerNode = list.item(Index);

                DeploymentPlannerResponse deploymentPlanner = new DeploymentPlannerResponse();

                NodeList deploymentPlannerProperties = deploymentPlannerNode.getChildNodes();
                for (int childIndex = 0; childIndex < deploymentPlannerProperties.getLength(); childIndex++) {
                    Node property = deploymentPlannerProperties.item(childIndex);

                    if (property.getNodeName().equals("name")) {
                        deploymentPlanner.setName(property.getTextContent());
                    }
                }
                deploymentPlanners.add(deploymentPlanner);
            }
        }
        response.setDeploymentPlanners(deploymentPlanners);
        return response;
    }

}
