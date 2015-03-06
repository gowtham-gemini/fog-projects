package com.assistanz.cloud.cloudstack.resourcetags;

import java.util.HashMap;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Santhosh
 *
 */
public class CSResourceTagsService {

    private CloudStackServer server;

    public CSResourceTagsService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates resource tag(s)
     *
     * @param resourceIds list of resources to create the tags for
     * @param resourceType type of the resource
     * @param tags Map of tags (key/value pairs)
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateTagsResponse createTags(String resourceIds, String resourceType,
            String tags, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createTags", optional);
        arguments.add(new NameValuePair("resourceids", resourceIds));
        arguments.add(new NameValuePair("resourcetype", resourceType));
        arguments.add(new NameValuePair("tags", tags));
        Document responseDocument = server.makeRequest(arguments);

        return getCreateTagsResponse(responseDocument);

    }

    /**
     * Converts XML document into CreateTagsResponse object
     *
     * @param doc
     * @return
     */
    private CreateTagsResponse getCreateTagsResponse(Document doc) {
        CreateTagsResponse response = new CreateTagsResponse();

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
     * Deleting resource tag(s)
     *
     * @param resourceIds Delete tags for resource id(s)
     * @param resourceType Delete tag by resource type
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteTagsResponse deleteTags(String resourceIds, String resourceType,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteTags", optional);
        arguments.add(new NameValuePair("resourceids", resourceIds));
        arguments.add(new NameValuePair("resourcetype", resourceType));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteTagsResponse(responseDocument);

    }

    /**
     * Converts XML document into DeleteTagsResponse object
     *
     * @param doc
     * @return
     */
    private DeleteTagsResponse getDeleteTagsResponse(Document doc) {
        DeleteTagsResponse response = new DeleteTagsResponse();

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
     * List resource tag(s)
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListTagsResponse listTags(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listTags", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListTagsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListTagsResponse object
     *
     * @param doc
     * @return
     */
    private ListTagsResponse getListTagsResponse(Document doc) {
        ListTagsResponse response = new ListTagsResponse();

        NodeList list = doc.getElementsByTagName("tags");

        List<TagsResponse> tagss = new LinkedList<TagsResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node tagsNode = list.item(index);
                TagsResponse tags = new TagsResponse();
                NodeList tagsProperties = tagsNode.getChildNodes();
                for (int childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                    Node property = tagsProperties.item(childIndex);
                    if (property.getNodeName().equals("account")) {
                        tags.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("customer")) {
                        tags.setCustomer(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        tags.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        tags.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("key")) {
                        tags.setKey(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        tags.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        tags.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("resourceid")) {
                        tags.setResourceId(property.getTextContent());
                    } else if (property.getNodeName().equals("resourcetype")) {
                        tags.setResourceType(property.getTextContent());
                    } else if (property.getNodeName().equals("value")) {
                        tags.setValue(property.getTextContent());
                    }
                }
                tagss.add(tags);
            }
        }
        response.setTagss(tagss);
        return response;
    }

}
