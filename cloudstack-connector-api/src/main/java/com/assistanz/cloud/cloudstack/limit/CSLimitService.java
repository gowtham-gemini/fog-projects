package com.assistanz.cloud.cloudstack.limit;

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
public class CSLimitService {

    private CloudStackServer server;

    public CSLimitService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Updates resource limits for an account or domain.
     *
     * @param resourceType Type of resource to update
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateResourceLimitResponse updateResourceLimit(String resourceType,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateResourceLimit", optional);
        arguments.add(new NameValuePair("resourcetype", resourceType));

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
            response.setAccount(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the resource limit
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain id of the resource limit
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get max from XML and set as the maximum number of the resource. A -1 means the resource currently has no limit.
        list = doc.getElementsByTagName("max");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMax(node.getTextContent());
        }

        // get project from XML and set as the project name of the resource limit
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the projectid name of the resource limit
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get resourcetype from XML and set as resource type. Values include 0, 1, 2, 3, 4. See the resourceType parameter for more information on these values.
        list = doc.getElementsByTagName("resourcetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setResourceType(node.getTextContent());
        }

        return response;
    }

    /**
     * Recalculate and update resource count for an account or domain.
     *
     * @param domainId updates resource counts for a specified account in this domain
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateResourceCountResponse updateResourceCount(String domainId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateResourceCount", optional);
        arguments.add(new NameValuePair("domainid", domainId));

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
            response.setAccount(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the resource limit
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain id of the resource limit
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get project from XML and set as the project name of the resource limit
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the projectid name of the resource limit
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get resourcecount from XML and set as resource count
        list = doc.getElementsByTagName("resourcecount");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setResourceCount(node.getTextContent());
        }

        // get resourcetype from XML and set as resource type. Values include 0, 1, 2, 3, 4. See the resourceType parameter for more information on these values.
        list = doc.getElementsByTagName("resourcetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setResourceType(node.getTextContent());
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
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listResourceLimits", optional);

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

        NodeList list = doc.getElementsByTagName("event");

        List<ResourceLimitResponse> resourceLimits = new LinkedList<ResourceLimitResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node resourceLimitNode = list.item(Index);
            ResourceLimitResponse resourceLimit = new ResourceLimitResponse();

            NodeList resourceLimitProperties = resourceLimitNode.getChildNodes();
            for (int childIndex = 0; childIndex < resourceLimitProperties.getLength(); childIndex++) {
                Node property = resourceLimitProperties.item(childIndex);

                if (property.getNodeName().equals("account")) {
                    resourceLimit.setAccount(property.getTextContent());
                } else if (property.getNodeName().equals("domain")) {
                    resourceLimit.setDomain(property.getTextContent());
                } else if (property.getNodeName().equals("domainid")) {
                    resourceLimit.setDomainId(property.getTextContent());
                } else if (property.getNodeName().equals("max")) {
                    resourceLimit.setMax(property.getTextContent());
                } else if (property.getNodeName().equals("project")) {
                    resourceLimit.setProject(property.getTextContent());
                } else if (property.getNodeName().equals("projectid")) {
                    resourceLimit.setProjectId(property.getTextContent());
                } else if (property.getNodeName().equals("resourcetype")) {
                    resourceLimit.setResourceType(property.getTextContent());
                }
            }
            resourceLimits.add(resourceLimit);
        }

        response.setResourceLimits(resourceLimits);
        return response;
    }

    /**
     * Get API limit count for the caller.
     *
     * @return
     * @throws Exception
     */
    public ApiLimitResponse getApiLimit() throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("getApiLimit", null);

        Document responseDocument = server.makeRequest(arguments);

        return getApiLimitResponse(responseDocument);
    }

    /**
     * Converts XML document into ApiLimitResponse object
     *
     * @param doc
     * @return
     */
    private ApiLimitResponse getApiLimitResponse(Document doc) {
        ApiLimitResponse response = new ApiLimitResponse();

        // get account from XML and set the account name of the api remaining count
        NodeList list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get accountid from XML and set as the account uuid of the api remaining count
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get apiallowed from XML and set as currently allowed number of apis
        list = doc.getElementsByTagName("apiAllowed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setApiAllowed(node.getTextContent());
        }

        // get apiIssued from XML and set as number of api already issued
        list = doc.getElementsByTagName("apiIssued");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setApiIssued(node.getTextContent());
        }

        // get expireafter from XML and set as seconds left to reset counters
        list = doc.getElementsByTagName("expireAfter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExpireAfter(node.getTextContent());
        }

        return response;
    }

    /**
     * Reset api count.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ResetApiLimitResponse resetApiLimit(HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("resetApiLimit", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getResetApiLimitResponse(responseDocument);
    }

    /**
     * Converts XML document into ResetApiLimitResponse object
     *
     * @param doc
     * @return
     */
    private ResetApiLimitResponse getResetApiLimitResponse(Document doc) {
        ResetApiLimitResponse response = new ResetApiLimitResponse();

        // get account from XML and set the account name of the api remaining count
        NodeList list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get accountid from XML and set as the account uuid of the api remaining count
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get apiallowed from XML and set as currently allowed number of apis
        list = doc.getElementsByTagName("apiAllowed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setApiAllowed(node.getTextContent());
        }

        // get apiIssued from XML and set as number of api already issued
        list = doc.getElementsByTagName("apiIssued");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setApiIssued(node.getTextContent());
        }

        // get expireafter from XML and set as seconds left to reset counters
        list = doc.getElementsByTagName("expireAfter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExpireAfter(node.getTextContent());
        }

        return response;
    }

}
