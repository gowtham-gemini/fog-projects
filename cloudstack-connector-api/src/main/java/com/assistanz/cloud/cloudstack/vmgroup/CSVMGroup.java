package com.assistanz.cloud.cloudstack.vmgroup;

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
public class CSVMGroup {

    private CloudStackServer server;

    public CSVMGroup(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates a vm group
     *
     * @param instanceGroupName the name of the instance group
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateInstanceGroupResponse createInstanceGroup(String instanceGroupName,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createInstanceGroup", optional);
        arguments.add(new NameValuePair("name", instanceGroupName));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateInstanceGroupResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateInstanceGroupResponse object
     *
     * @param doc
     * @return
     */
    private CreateInstanceGroupResponse getCreateInstanceGroupResponse(Document doc) {
        CreateInstanceGroupResponse response = new CreateInstanceGroupResponse();

        // get id from XML and set as the id of the instance group
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account owning the instance group
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get created from XML and set as the time and date the instance group was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the instance group
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain ID of the instance group
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get name from XML and set as the name of the instance group
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name of the group
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the projectid name of the group
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        return response;

    }

    /**
     * Deletes a vm group
     *
     * @param instanceGroupId the ID of the instance group
     * @return
     * @throws Exception
     */
    public DeleteInstanceGroupResponse deleteInstanceGroup(String instanceGroupId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteInstanceGroup", null);
        arguments.add(new NameValuePair("id", instanceGroupId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteInstanceGroupResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteInstanceGroupResponse object
     *
     * @param doc
     * @return
     */
    private DeleteInstanceGroupResponse getDeleteInstanceGroupResponse(Document doc) {
        DeleteInstanceGroupResponse response = new DeleteInstanceGroupResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Instance Group
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Instance Group
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Updates a vm group
     *
     * @param instanceGroupId Instance group ID
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateInstanceGroupResponse updateInstanceGroup(String instanceGroupId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateInstanceGroup", optional);
        arguments.add(new NameValuePair("id", instanceGroupId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateInstanceGroupResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateInstanceGroupResponse object
     *
     * @param doc
     * @return
     */
    private UpdateInstanceGroupResponse getUpdateInstanceGroupResponse(Document doc) {
        UpdateInstanceGroupResponse response = new UpdateInstanceGroupResponse();

        // get id from XML and set as the id of the instance group
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account owning the instance group
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get created from XML and set as the time and date the instance group was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the instance group
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain ID of the instance group
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get name from XML and set as the name of the instance group
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name of the group
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the projectid name of the group
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        return response;

    }

    /**
     * Lists vm groups
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListInstanceGroupsResponse listInstanceGroups(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listInstanceGroups", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListInstanceGroupsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListInstanceGroupsResponse object
     *
     * @param doc
     * @return
     */
    private ListInstanceGroupsResponse getListInstanceGroupsResponse(Document doc) {
        ListInstanceGroupsResponse response = new ListInstanceGroupsResponse();

        NodeList list = doc.getElementsByTagName("instancegroup");

        List<InstanceGroupResponse> instanceGroups = new LinkedList<InstanceGroupResponse>();
        if (list.getLength() > 0) {
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node instanceGroupNode = list.item(Index);

                InstanceGroupResponse instanceGroup = new InstanceGroupResponse();

                NodeList instanceGroupProperties = instanceGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < instanceGroupProperties.getLength(); childIndex++) {
                    Node property = instanceGroupProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        instanceGroup.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        instanceGroup.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        instanceGroup.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        instanceGroup.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        instanceGroup.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        instanceGroup.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        instanceGroup.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        instanceGroup.setProjectId(property.getTextContent());
                    }
                }
                instanceGroups.add(instanceGroup);
            }
        }
        response.setInstanceGroups(instanceGroups);
        return response;
    }
}
