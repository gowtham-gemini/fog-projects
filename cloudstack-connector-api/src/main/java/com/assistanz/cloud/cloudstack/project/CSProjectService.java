package com.assistanz.cloud.cloudstack.project;

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
 * @author Gowtham
 *
 */
public class CSProjectService {

    private CloudStackServer server;

    public CSProjectService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates a project
     *
     * @param projectDisplayText The display text of the project
     * @param projectName The name of the project
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateProjectResponse createProject(String projectDisplayText,
            String projectName, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createProject", optional);
        arguments.add(new NameValuePair("displaytext", projectDisplayText));
        arguments.add(new NameValuePair("name", projectName));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateProjectResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateProjectResponse object
     *
     * @param doc
     * @return
     */
    private CreateProjectResponse getCreateProjectResponse(Document doc) {
        CreateProjectResponse response = new CreateProjectResponse();

        // get the Id of the project 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get the account name of the project's owner
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get the total number of cpu cores available to be created for this account
        list = doc.getElementsByTagName("cpuavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAvailable(node.getTextContent());
        }

        // get the total number of cpu cores the account can own
        list = doc.getElementsByTagName("cpulimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuLimit(node.getTextContent());
        }

        // get the total number of cpu cores owned account 
        list = doc.getElementsByTagName("cputotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuTotal(node.getTextContent());
        }

        // get the displaytext of the project
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get the name of the Domain the account belongs too
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get id of the Domain the account belongs too
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }

        // get the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }

        // get the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }

        // get the total memory (in MB) available to be created for this account
        list = doc.getElementsByTagName("memoryavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAvailable(node.getTextContent());
        }

        // get the total memory (in MB) the account can own
        list = doc.getElementsByTagName("memorylimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryLimit(node.getTextContent());
        }

        // get the total memory (in MB) owned by account
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get the total number of networks available to be created for this account
        list = doc.getElementsByTagName("networkavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAvailable(node.getTextContent());
        }

        // get the total number of networks the account can own
        list = doc.getElementsByTagName("networklimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkLimit(node.getTextContent());
        }

        // get the total number of networks owned by account
        list = doc.getElementsByTagName("networktotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTotal(node.getTextContent());
        }

        // get the total primary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("primarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageAvailable(node.getTextContent());
        }

        // get the total primary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("primarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageLimit(node.getTextContent());
        }

        // get the total primary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("primarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageTotal(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("secondarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageAvailable(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("secondarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageLimit(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("secondarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageTotal(node.getTextContent());
        }

        // get the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }

        // get the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }

        // get the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }

        // get the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }

        // get the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }

        // get the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }

        // get the total number of virtual machines available for this account to acquire
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }

        // get the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }

        // get the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }

        // get the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }

        // get the total number of virtual machines deployed by this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmTotal(node.getTextContent());
        }

        // get the total volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeAvailable(node.getTextContent());
        }

        // get the total volume which can be used by this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }

        // get the total volume being used by this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }

        // get the total number of vpcs available to be created for this account
        list = doc.getElementsByTagName("vpcavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcAvailable(node.getTextContent());
        }

        // get the total number of vpcs the account can own
        list = doc.getElementsByTagName("vpclimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcLimit(node.getTextContent());
        }

        // get the total number of vpcs owned by account
        list = doc.getElementsByTagName("vpctotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcTotal(node.getTextContent());
        }

        // gets associated tag values for the virtual machine
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node tagsNode = list.item(index);
                TagsResponse tags = new TagsResponse();
                NodeList tagsProperties = tagsNode.getChildNodes();
                for (int childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                    Node tagsProperty = tagsProperties.item(childIndex);
                    if (tagsProperty.getNodeName().equals("account")) {
                        tags.setAccount(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("customer")) {
                        tags.setCustomer(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domain")) {
                        tags.setDomain(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                        tags.setDomainId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("key")) {
                        tags.setKey(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("project")) {
                        tags.setProject(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                        tags.setProjectId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                        tags.setResourceId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                        tags.setResourceType(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("value")) {
                        tags.setValue(tagsProperty.getTextContent());
                    }

                }
                tagss.add(tags);
                response.setTagss(tagss);
            }
        }

        return response;
    }

    /**
     * Deletes a project
     *
     * @param projectId id of the invitation
     * @return
     * @throws Exception
     */
    public DeleteProjectResponse deleteProject(String projectId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteProject", null);
        arguments.add(new NameValuePair("id", projectId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteProjectResponse(responseDocument);

    }

    /**
     * Converts XML document into DeleteProjectResponse object
     *
     * @param doc
     * @return
     */
    private DeleteProjectResponse getDeleteProjectResponse(Document doc) {
        DeleteProjectResponse response = new DeleteProjectResponse();

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
     * Updates a project
     *
     * @param projectId the id of the project to be modified
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateProjectResponse updateProject(String projectId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateProject", optional);
        arguments.add(new NameValuePair("id", projectId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateProjectResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateProjectResponse object
     *
     * @param doc
     * @return
     */
    private UpdateProjectResponse getUpdateProjectResponse(Document doc) {
        UpdateProjectResponse response = new UpdateProjectResponse();

        // get the Id of the project 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get the account name of the project's owner
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get the total number of cpu cores available to be created for this account
        list = doc.getElementsByTagName("cpuavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAvailable(node.getTextContent());
        }

        // get the total number of cpu cores the account can own
        list = doc.getElementsByTagName("cpulimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuLimit(node.getTextContent());
        }

        // get the total number of cpu cores owned account 
        list = doc.getElementsByTagName("cputotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuTotal(node.getTextContent());
        }

        // get the displaytext of the project
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get the name of the Domain the account belongs too
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get id of the Domain the account belongs too
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }

        // get the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }

        // get the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }

        // get the total memory (in MB) available to be created for this account
        list = doc.getElementsByTagName("memoryavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAvailable(node.getTextContent());
        }

        // get the total memory (in MB) the account can own
        list = doc.getElementsByTagName("memorylimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryLimit(node.getTextContent());
        }

        // get the total memory (in MB) owned by account
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get the total number of networks available to be created for this account
        list = doc.getElementsByTagName("networkavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAvailable(node.getTextContent());
        }

        // get the total number of networks the account can own
        list = doc.getElementsByTagName("networklimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkLimit(node.getTextContent());
        }

        // get the total number of networks owned by account
        list = doc.getElementsByTagName("networktotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTotal(node.getTextContent());
        }

        // get the total primary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("primarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageAvailable(node.getTextContent());
        }

        // get the total primary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("primarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageLimit(node.getTextContent());
        }

        // get the total primary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("primarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageTotal(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("secondarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageAvailable(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("secondarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageLimit(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("secondarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageTotal(node.getTextContent());
        }

        // get the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }

        // get the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }

        // get the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }

        // get the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }

        // get the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }

        // get the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }

        // get the total number of virtual machines available for this account to acquire
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }

        // get the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }

        // get the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }

        // get the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }

        // get the total number of virtual machines deployed by this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmTotal(node.getTextContent());
        }

        // get the total volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeAvailable(node.getTextContent());
        }

        // get the total volume which can be used by this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }

        // get the total volume being used by this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }

        // get the total number of vpcs available to be created for this account
        list = doc.getElementsByTagName("vpcavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcAvailable(node.getTextContent());
        }

        // get the total number of vpcs the account can own
        list = doc.getElementsByTagName("vpclimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcLimit(node.getTextContent());
        }

        // get the total number of vpcs owned by account
        list = doc.getElementsByTagName("vpctotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcTotal(node.getTextContent());
        }

        // gets associated tag values for the virtual machine
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node tagsNode = list.item(index);
                TagsResponse tags = new TagsResponse();
                NodeList tagsProperties = tagsNode.getChildNodes();
                for (int childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                    Node tagsProperty = tagsProperties.item(childIndex);
                    if (tagsProperty.getNodeName().equals("account")) {
                        tags.setAccount(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("customer")) {
                        tags.setCustomer(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domain")) {
                        tags.setDomain(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                        tags.setDomainId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("key")) {
                        tags.setKey(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("project")) {
                        tags.setProject(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                        tags.setProjectId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                        tags.setResourceId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                        tags.setResourceType(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("value")) {
                        tags.setValue(tagsProperty.getTextContent());
                    }

                }
                tagss.add(tags);
                response.setTagss(tagss);
            }
        }

        return response;
    }

    /**
     * Activates a project
     *
     * @param projectId the id of the project to be Activated
     * @return
     * @throws Exception
     */
    public ActivateProjectResponse activateProject(String projectId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("activateProject", null);
        arguments.add(new NameValuePair("id", projectId));

        Document responseDocument = server.makeRequest(arguments);

        return getActivateProjectResponse(responseDocument);
    }

    /**
     * Converts XML document into ActivateProjectResponse object
     *
     * @param doc
     * @return
     */
    private ActivateProjectResponse getActivateProjectResponse(Document doc) {
        ActivateProjectResponse response = new ActivateProjectResponse();

        // get the Id of the project 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get the account name of the project's owner
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get the total number of cpu cores available to be created for this account
        list = doc.getElementsByTagName("cpuavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAvailable(node.getTextContent());
        }

        // get the total number of cpu cores the account can own
        list = doc.getElementsByTagName("cpulimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuLimit(node.getTextContent());
        }

        // get the total number of cpu cores owned account 
        list = doc.getElementsByTagName("cputotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuTotal(node.getTextContent());
        }

        // get the displaytext of the project
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get the name of the Domain the account belongs too
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get id of the Domain the account belongs too
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }

        // get the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }

        // get the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }

        // get the total memory (in MB) available to be created for this account
        list = doc.getElementsByTagName("memoryavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAvailable(node.getTextContent());
        }

        // get the total memory (in MB) the account can own
        list = doc.getElementsByTagName("memorylimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryLimit(node.getTextContent());
        }

        // get the total memory (in MB) owned by account
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get the total number of networks available to be created for this account
        list = doc.getElementsByTagName("networkavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAvailable(node.getTextContent());
        }

        // get the total number of networks the account can own
        list = doc.getElementsByTagName("networklimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkLimit(node.getTextContent());
        }

        // get the total number of networks owned by account
        list = doc.getElementsByTagName("networktotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTotal(node.getTextContent());
        }

        // get the total primary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("primarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageAvailable(node.getTextContent());
        }

        // get the total primary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("primarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageLimit(node.getTextContent());
        }

        // get the total primary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("primarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageTotal(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("secondarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageAvailable(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("secondarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageLimit(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("secondarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageTotal(node.getTextContent());
        }

        // get the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }

        // get the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }

        // get the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }

        // get the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }

        // get the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }

        // get the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }

        // get the total number of virtual machines available for this account to acquire
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }

        // get the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }

        // get the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }

        // get the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }

        // get the total number of virtual machines deployed by this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmTotal(node.getTextContent());
        }

        // get the total volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeAvailable(node.getTextContent());
        }

        // get the total volume which can be used by this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }

        // get the total volume being used by this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }

        // get the total number of vpcs available to be created for this account
        list = doc.getElementsByTagName("vpcavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcAvailable(node.getTextContent());
        }

        // get the total number of vpcs the account can own
        list = doc.getElementsByTagName("vpclimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcLimit(node.getTextContent());
        }

        // get the total number of vpcs owned by account
        list = doc.getElementsByTagName("vpctotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcTotal(node.getTextContent());
        }

        // gets associated tag values for the virtual machine
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node tagsNode = list.item(index);
                TagsResponse tags = new TagsResponse();
                NodeList tagsProperties = tagsNode.getChildNodes();
                for (int childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                    Node tagsProperty = tagsProperties.item(childIndex);
                    if (tagsProperty.getNodeName().equals("account")) {
                        tags.setAccount(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("customer")) {
                        tags.setCustomer(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domain")) {
                        tags.setDomain(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                        tags.setDomainId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("key")) {
                        tags.setKey(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("project")) {
                        tags.setProject(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                        tags.setProjectId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                        tags.setResourceId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                        tags.setResourceType(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("value")) {
                        tags.setValue(tagsProperty.getTextContent());
                    }

                }
                tagss.add(tags);
                response.setTagss(tagss);
            }
        }
        return response;
    }

    /**
     * Suspends a project The id of the project to be suspended
     *
     * @param projectId
     * @return
     * @throws Exception
     */
    public SuspendProjectResponse suspendProject(String projectId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("suspendProject", null);
        arguments.add(new NameValuePair("id", projectId));

        Document responseDocument = server.makeRequest(arguments);

        return getSuspendProjectResponse(responseDocument);
    }

    /**
     * Converts XML document into SuspendProjectResponse object
     *
     * @param doc
     * @return
     */
    private SuspendProjectResponse getSuspendProjectResponse(Document doc) {
        SuspendProjectResponse response = new SuspendProjectResponse();

        // get the Id of the project 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get the account name of the project's owner
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get the total number of cpu cores available to be created for this account
        list = doc.getElementsByTagName("cpuavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAvailable(node.getTextContent());
        }

        // get the total number of cpu cores the account can own
        list = doc.getElementsByTagName("cpulimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuLimit(node.getTextContent());
        }

        // get the total number of cpu cores owned account 
        list = doc.getElementsByTagName("cputotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuTotal(node.getTextContent());
        }

        // get the displaytext of the project
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get the name of the Domain the account belongs too
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get id of the Domain the account belongs too
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get the total number of public ip addresses available for this account to acquire
        list = doc.getElementsByTagName("ipavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAvailable(node.getTextContent());
        }

        // get the total number of public ip addresses this account can acquire
        list = doc.getElementsByTagName("iplimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpLimit(node.getTextContent());
        }

        // get the total number of public ip addresses allocated for this account
        list = doc.getElementsByTagName("iptotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpTotal(node.getTextContent());
        }

        // get the total memory (in MB) available to be created for this account
        list = doc.getElementsByTagName("memoryavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAvailable(node.getTextContent());
        }

        // get the total memory (in MB) the account can own
        list = doc.getElementsByTagName("memorylimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryLimit(node.getTextContent());
        }

        // get the total memory (in MB) owned by account
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get the name of the account
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get the total number of networks available to be created for this account
        list = doc.getElementsByTagName("networkavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkAvailable(node.getTextContent());
        }

        // get the total number of networks the account can own
        list = doc.getElementsByTagName("networklimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkLimit(node.getTextContent());
        }

        // get the total number of networks owned by account
        list = doc.getElementsByTagName("networktotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkTotal(node.getTextContent());
        }

        // get the total primary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("primarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageAvailable(node.getTextContent());
        }

        // get the total primary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("primarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageLimit(node.getTextContent());
        }

        // get the total primary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("primarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrimaryStorageTotal(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) available to be used for this account
        list = doc.getElementsByTagName("secondarystorageavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageAvailable(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) the account can own
        list = doc.getElementsByTagName("secondarystoragelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageLimit(node.getTextContent());
        }

        // get the total secondary storage space (in GiB) owned by account
        list = doc.getElementsByTagName("secondarystoragetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecondaryStorageTotal(node.getTextContent());
        }

        // get the total number of snapshots available for this account
        list = doc.getElementsByTagName("snapshotavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAvailable(node.getTextContent());
        }

        // get the total number of snapshots which can be stored by this account
        list = doc.getElementsByTagName("snapshotlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotLimit(node.getTextContent());
        }

        // get the total number of snapshots stored by this account
        list = doc.getElementsByTagName("snapshottotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotTotal(node.getTextContent());
        }

        // get the state of the account
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get the total number of templates available to be created by this account
        list = doc.getElementsByTagName("templateavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateAvailable(node.getTextContent());
        }

        // get the total number of templates which can be created by this account
        list = doc.getElementsByTagName("templatelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateLimit(node.getTextContent());
        }

        // get the total number of templates which have been created by this account
        list = doc.getElementsByTagName("templatetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTotal(node.getTextContent());
        }

        // get the total number of virtual machines available for this account to acquire
        list = doc.getElementsByTagName("vmavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmAvailable(node.getTextContent());
        }

        // get the total number of virtual machines that can be deployed by this account
        list = doc.getElementsByTagName("vmlimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmLimit(node.getTextContent());
        }

        // get the total number of virtual machines running for this account
        list = doc.getElementsByTagName("vmrunning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmRunning(node.getTextContent());
        }

        // get the total number of virtual machines stopped for this account
        list = doc.getElementsByTagName("vmstopped");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmStopped(node.getTextContent());
        }

        // get the total number of virtual machines deployed by this account
        list = doc.getElementsByTagName("vmtotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmTotal(node.getTextContent());
        }

        // get the total volume available for this account
        list = doc.getElementsByTagName("volumeavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeAvailable(node.getTextContent());
        }

        // get the total volume which can be used by this account
        list = doc.getElementsByTagName("volumelimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeLimit(node.getTextContent());
        }

        // get the total volume being used by this account
        list = doc.getElementsByTagName("volumetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeTotal(node.getTextContent());
        }

        // get the total number of vpcs available to be created for this account
        list = doc.getElementsByTagName("vpcavailable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcAvailable(node.getTextContent());
        }

        // get the total number of vpcs the account can own
        list = doc.getElementsByTagName("vpclimit");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcLimit(node.getTextContent());
        }

        // get the total number of vpcs owned by account
        list = doc.getElementsByTagName("vpctotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcTotal(node.getTextContent());
        }

        // gets associated tag values for the virtual machine
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node tagsNode = list.item(index);
                TagsResponse tags = new TagsResponse();
                NodeList tagsProperties = tagsNode.getChildNodes();
                for (int childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                    Node tagsProperty = tagsProperties.item(childIndex);
                    if (tagsProperty.getNodeName().equals("account")) {
                        tags.setAccount(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("customer")) {
                        tags.setCustomer(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domain")) {
                        tags.setDomain(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                        tags.setDomainId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("key")) {
                        tags.setKey(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("project")) {
                        tags.setProject(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                        tags.setProjectId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                        tags.setResourceId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                        tags.setResourceType(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("value")) {
                        tags.setValue(tagsProperty.getTextContent());
                    }

                }
                tagss.add(tags);
                response.setTagss(tagss);
            }
        }
        return response;
    }

    /**
     * Lists projects and provides detailed information for listed projects
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListProjectsResponse listProjects(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listProjects", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListProjectsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListProjectsResponse object
     *
     * @param doc
     * @return
     */
    private ListProjectsResponse getListProjectsResponse(Document doc) {
        ListProjectsResponse response = new ListProjectsResponse();

        NodeList list = doc.getElementsByTagName("project");

        List<ProjectResponse> projects = new LinkedList<ProjectResponse>();
        if (list.getLength() > 0) {
            for (int listProjectIndex = 0; listProjectIndex < list.getLength(); listProjectIndex++) {
                Node listProjectNode = list.item(listProjectIndex);

                ProjectResponse project = new ProjectResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList listProjectProperties = listProjectNode.getChildNodes();
                for (int childIndex = 0; childIndex < listProjectProperties.getLength(); childIndex++) {
                    Node property = listProjectProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        project.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        project.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuavailable")) {
                        project.setCpuAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("cpulimit")) {
                        project.setCpuLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("cputotal")) {
                        project.setCpuTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                        project.setDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        project.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        project.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("ipavailable")) {
                        project.setIpAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("iplimit")) {
                        project.setIpLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("iptotal")) {
                        project.setIpTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("memoryavailable")) {
                        project.setMemoryAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("memorylimit")) {
                        project.setMemoryLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("memorytotal")) {
                        project.setMemoryTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        project.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkavailable")) {
                        project.setNetworkAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("networklimit")) {
                        project.setNetworkLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("networktotal")) {
                        project.setNetworkTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("primarystorageavailable")) {
                        project.setPrimaryStorageAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("primarystoragelimit")) {
                        project.setPrimaryStorageLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("primarystoragetotal")) {
                        project.setPrimaryStorageTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("secondarystorageavailable")) {
                        project.setSecondaryStorageAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("secondarystoragelimit")) {
                        project.setSecondaryStorageLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("secondarystoragetotal")) {
                        project.setSecondaryStorageTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshotavailable")) {
                        project.setSnapshotAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshotlimit")) {
                        project.setSnapshotLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshottotal")) {
                        project.setSnapshotTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        project.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("templateavailable")) {
                        project.setTemplateAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("templatelimit")) {
                        project.setTemplateLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("templatetotal")) {
                        project.setTemplateTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("vmavailable")) {
                        project.setVmAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("vmlimit")) {
                        project.setVmLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("vmrunning")) {
                        project.setVmRunning(property.getTextContent());
                    } else if (property.getNodeName().equals("vmstopped")) {
                        project.setVmStopped(property.getTextContent());
                    } else if (property.getNodeName().equals("vmtotal")) {
                        project.setVmTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("volumeavailable")) {
                        project.setVolumeAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("volumelimit")) {
                        project.setVolumeLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("volumetotal")) {
                        project.setVolumeTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcavailable")) {
                        project.setVpcAvailable(property.getTextContent());
                    } else if (property.getNodeName().equals("vpclimit")) {
                        project.setVpcLimit(property.getTextContent());
                    } else if (property.getNodeName().equals("vpctotal")) {
                        project.setVpcTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        NodeList tagsProperties = property.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < list.getLength(); tagsIndex++) {
                                property = tagsProperties.item(tagsIndex);

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
                        project.setTagss(tagss);
                    }

                }

                projects.add(project);

            }
        }
        response.setProjects(projects);
        return response;

    }

    /**
     * Lists projects and provides detailed information for listed projects
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListProjectInvitationsResponse listProjectInvitations(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listProjectInvitations", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListProjectInvitationsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListProjectInvitationsResponse object
     *
     * @param doc
     * @return
     */
    private ListProjectInvitationsResponse getListProjectInvitationsResponse(Document doc) {
        ListProjectInvitationsResponse response = new ListProjectInvitationsResponse();

        NodeList list = doc.getElementsByTagName("projectinvitation");

        List<ProjectInvitationResponse> projectInvitations = new LinkedList<ProjectInvitationResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node projectInvitationNode = list.item(index);
                ProjectInvitationResponse projectInvitation = new ProjectInvitationResponse();
                NodeList projectInvitationProperties = projectInvitationNode.getChildNodes();
                for (int childIndex = 0; childIndex < projectInvitationProperties.getLength(); childIndex++) {
                    Node property = projectInvitationProperties.item(childIndex);
                    if (property.getNodeName().equals("id")) {
                        projectInvitation.setInvitationId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        projectInvitation.setProjectAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        projectInvitation.setProjectDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        projectInvitation.setProjectDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("email")) {
                        projectInvitation.setProjectEmail(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        projectInvitation.setProjectName(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        projectInvitation.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        projectInvitation.setProjectState(property.getTextContent());
                    }
                }
                projectInvitations.add(projectInvitation);
            }
        }
        response.setProjectInvitations(projectInvitations);
        return response;
    }

    /**
     * Accepts or declines project invitation
     *
     * @param projectId The	id of the project to join
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateProjectInvitationResponse updateProjectInvitation(String projectId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateProjectInvitation", null);
        arguments.add(new NameValuePair("projectid", projectId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateProjectInvitationResponse(responseDocument);

    }

    /**
     * Converts XML document into UpdateProjectInvitationResponse object
     *
     * @param doc
     * @return
     */
    private UpdateProjectInvitationResponse getUpdateProjectInvitationResponse(Document doc) {
        UpdateProjectInvitationResponse response = new UpdateProjectInvitationResponse();

        // get displaytext from XML and set Any text associated with the success or 
        // failure on updating  project invitation
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get success from XML and set Return true if update project invitation operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * declines project invitation
     *
     * @param invitationId id of the invitation
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteProjectInvitationResponse deleteProjectInvitation(String invitationId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteProjectInvitation", null);
        arguments.add(new NameValuePair("id", invitationId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteProjectInvitationResponse(responseDocument);

    }

    /**
     * Converts XML document into DeleteProjectInvitationResponse object
     *
     * @param doc
     * @return
     */
    private DeleteProjectInvitationResponse getDeleteProjectInvitationResponse(Document doc) {
        DeleteProjectInvitationResponse response = new DeleteProjectInvitationResponse();

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
}
