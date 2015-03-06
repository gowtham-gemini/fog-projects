package com.assistanz.cloud.cloudstack.resourcemetadata;

import java.util.HashMap;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Santhosh
 *
 */
public class CSResourceMetadataService {

    private CloudStackServer server;

    public CSResourceMetadataService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds detail for the Resource
     *
     * @param details Map of (key/value pairs)
     * @param resourceId resource id to create the details for
     * @param resourceType type of the resource
     * @return
     * @throws Exception
     */
    public AddResourceDetailResponse addResourceDetail(String details, String resourceId,
            String resourceType) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addResourceDetail", null);
        arguments.add(new NameValuePair("details", details));
        arguments.add(new NameValuePair("resourceid", resourceId));
        arguments.add(new NameValuePair("resourceType", resourceType));
        Document responseDocument = server.makeRequest(arguments);

        return getAddResourceDetailResponse(responseDocument);

    }

    /**
     * Converts XML document into AddResourceDetailResponse object
     *
     * @param doc
     * @return
     */
    private AddResourceDetailResponse getAddResourceDetailResponse(Document doc) {
        AddResourceDetailResponse response = new AddResourceDetailResponse();

        /* get displaytext from XML and set Any text associated with the success or 
         failure on updating  project invitation */
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        /* get success from XML and set Return true if update project invitation operation 
         is executed successfully */
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Removes detail for the Resource
     *
     * @param resourceId Delete details for resource id
     * @param resourceType Delete detail by resource type
     * @param optional
     * @return
     * @throws Exception
     */
    public RemoveResourceDetailResponse removeResourceDetail(String resourceId, String resourceType,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("removeResourceDetail", optional);
        arguments.add(new NameValuePair("resourceid", resourceId));
        arguments.add(new NameValuePair("resourcetype", resourceType));

        Document responseDocument = server.makeRequest(arguments);

        return getRemoveResourceDetailResponse(responseDocument);

    }

    /**
     * Converts XML document into RemoveResourceDetailResponse object
     *
     * @param doc
     * @return
     */
    private RemoveResourceDetailResponse getRemoveResourceDetailResponse(Document doc) {
        RemoveResourceDetailResponse response = new RemoveResourceDetailResponse();

        /* get displaytext from XML and set Any text associated with the success or 
         failure on deleting  project invitation */
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        /* get success from XML and set Return true if delete project invitation operation 
         is executed successfully */
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * List resource detail(s)
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListResourceDetailsResponse listResourceDetails(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listResourceDetails", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListResourceDetailsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListResourceDetailsResponse object
     *
     * @param doc
     * @return
     */
    private ListResourceDetailsResponse getListResourceDetailsResponse(Document doc) {
        ListResourceDetailsResponse response = new ListResourceDetailsResponse();

        NodeList list = doc.getElementsByTagName("resourcedetail");

        List<ResourceDetailResponse> resourceDetails = new LinkedList<ResourceDetailResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node resourceDetailNode = list.item(index);
                ResourceDetailResponse resourceDetail = new ResourceDetailResponse();
                NodeList resourceDetailProperties = resourceDetailNode.getChildNodes();
                for (int childIndex = 0; childIndex < resourceDetailProperties.getLength(); childIndex++) {
                    Node property = resourceDetailProperties.item(childIndex);

                    if (property.getNodeName().equals("account")) {
                        resourceDetail.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("customer")) {
                        resourceDetail.setCustomer(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        resourceDetail.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        resourceDetail.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("key")) {
                        resourceDetail.setKey(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        resourceDetail.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        resourceDetail.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("resourceid")) {
                        resourceDetail.setResourceId(property.getTextContent());
                    } else if (property.getNodeName().equals("resourcetype")) {
                        resourceDetail.setResourceType(property.getTextContent());
                    } else if (property.getNodeName().equals("value")) {
                        resourceDetail.setValue(property.getTextContent());
                    }
                }
                resourceDetails.add(resourceDetail);
            }
        }
        response.setResourceDetails(resourceDetails);
        return response;
    }

}
