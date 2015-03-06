package com.assistanz.cloud.cloudstack.snapshot;

import com.assistanz.cloud.cloudstack.AffinityGroupResponse;
import java.util.HashMap;
import java.util.LinkedList;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;
import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSSnapshotService {

    private CloudStackServer server;

    public CSSnapshotService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates an instant snapshot of a volume.
     *
     * @param diskVolumeId The ID of the disk volume
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateSnapshotResponse createSnapshot(String diskVolumeId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createSnapshot", optional);
        arguments.add(new NameValuePair("volumeid", diskVolumeId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateSnapshotResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateSnapshotResponse object
     *
     * @param doc
     * @return
     */
    private CreateSnapshotResponse getCreateSnapshotResponse(Document doc) {
        CreateSnapshotResponse response = new CreateSnapshotResponse();

        // get id from XML and set as ID of the snapshot
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account associated with the snapshot
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get created from XML and set as the date the snapshot was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get domain from XML and set as he domain name of the snapshot's account
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain ID of the snapshot's account
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get intervaltype from XML and set as valid types are hourly, daily, weekly, monthy, template, and none.
        list = doc.getElementsByTagName("intervaltype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIntervalType(node.getTextContent());
        }

        // get name from XML and set as the date name of the snapshot
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name of the snapshot
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the snapshot
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get snapshottype from XML and set as the type of the snapshot
        list = doc.getElementsByTagName("snapshottype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotType(node.getTextContent());
        }

        // get state from XML and set as the date the state of the snapshot
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get volumeid from XML and set as the ID of the disk volume
        list = doc.getElementsByTagName("volumeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeId(node.getTextContent());
        }

        // get volumename from XML and set as the name of the disk volume
        list = doc.getElementsByTagName("volumename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeName(node.getTextContent());
        }

        // get volumetype from XML and set type for the disk volume
        list = doc.getElementsByTagName("volumetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeType(node.getTextContent());
        }

        // get id of the availability zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // list of resource tags associated with snapshot
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

        // get jobid from XML and set the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all available snapshots for the account.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListSnapshotsResponse listSnapshots(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listSnapshots", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListSnapshotsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListSnapshotsResponse object
     *
     * @param doc
     * @return
     */
    private ListSnapshotsResponse getListSnapshotsResponse(Document doc) {
        ListSnapshotsResponse response = new ListSnapshotsResponse();

        NodeList list = doc.getElementsByTagName("snapshot");

        List<SnapShotResponse> snapShots = new LinkedList<SnapShotResponse>();

        if (list.getLength() > 0) {

            for (int index = 0; index < list.getLength(); index++) {
                Node snapShotNode = list.item(index);

                SnapShotResponse snapShot = new SnapShotResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList snapShotProperties = snapShotNode.getChildNodes();

                for (int childIndex = 0; childIndex < snapShotProperties.getLength(); childIndex++) {
                    Node property = snapShotProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        snapShot.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        snapShot.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        snapShot.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        snapShot.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        snapShot.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("intervaltype")) {
                        snapShot.setIntervalType(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        snapShot.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        snapShot.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        snapShot.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshottype")) {
                        snapShot.setSnapshotType(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        snapShot.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("volumeid")) {
                        snapShot.setVolumeId(property.getTextContent());
                    } else if (property.getNodeName().equals("volumename")) {
                        snapShot.setVolumeName(property.getTextContent());
                    } else if (property.getNodeName().equals("volumetype")) {
                        snapShot.setVolumeType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        snapShot.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        NodeList tagsProperties = property.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
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
                        snapShot.setTagss(tagss);
                    } else if (property.getNodeName().equals("jobid")) {
                        snapShot.setJobid(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        snapShot.setJobStatus(property.getTextContent());
                    }

                }
                snapShots.add(snapShot);
            }
        }
        response.setSnapShots(snapShots);
        return response;
    }

    /**
     * Deletes a snapshot of a disk volume
     *
     * @param snapshotId The ID of the snapshot
     * @return
     * @throws Exception
     */
    public DeleteSnapshotResponse deleteSnapshot(String snapshotId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteSnapshot", null);
        arguments.add(new NameValuePair("id", snapshotId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteSnapshotResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteSnapshotResponse object
     *
     * @param doc
     * @return
     */
    private DeleteSnapshotResponse getDeleteSnapshotResponse(Document doc) {
        DeleteSnapshotResponse response = new DeleteSnapshotResponse();

        // get displaytext from XML and set any text associated with the success or failure on deleting snapshot 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }

        //get success from XML and any text associated with the success or failure on deleting snapshot 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }
        
        //get success from XML and any text associated with the success or failure on deleting snapshot 
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * Creates a snapshot policy for the account.
     *
     * @param snapshotPolicyIntervalType valid values are HOURLY, DAILY, WEEKLY, and MONTHLY
     * @param snapshotPolicyMaxSnaps maximum number of snapshots to retain
     * @param snapshotPolicySchedule time the snapshot is scheduled to be taken. Format is:* if HOURLY, MM* if DAILY,
     * MM:HH* if WEEKLY, MM:HH:DD (1-7)* if MONTHLY, MM:HH:DD (1-28)
     * @param snapshotPolicyTimeZone Specifies a timezone for this command. For more information on the timezone
     * parameter, see Time Zone Format.
     * @param diskvolumeId the ID of the disk volume
     * @return
     * @throws Exception
     */
    public CreateSnapshotPolicyResponse createSnapshotPolicy(String snapshotPolicyIntervalType,
            String snapshotPolicyMaxSnaps, String snapshotPolicySchedule, String snapshotPolicyTimeZone, String diskvolumeId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createSnapshotPolicy", null);
        arguments.add(new NameValuePair("intervaltype", snapshotPolicyIntervalType));
        arguments.add(new NameValuePair("maxsnaps", snapshotPolicyMaxSnaps));
        arguments.add(new NameValuePair("schedule", snapshotPolicySchedule));
        arguments.add(new NameValuePair("timezone", snapshotPolicyTimeZone));
        arguments.add(new NameValuePair("volumeid", diskvolumeId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateSnapshotPolicyResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateSnapshotPolicyResponse object
     *
     * @param doc
     * @return
     */
    private CreateSnapshotPolicyResponse getCreateSnapshotPolicyResponse(Document doc) {
        CreateSnapshotPolicyResponse response = new CreateSnapshotPolicyResponse();

        // get id from XML and set as ID of the snapshot policy
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get intervaltype from XML and set as the interval type of the snapshot policy
        list = doc.getElementsByTagName("intervaltype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIntervalType(node.getTextContent());
        }

        // get maxsnaps from XML and set as maximum number of snapshots retained
        list = doc.getElementsByTagName("maxsnaps");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxSnaps(node.getTextContent());
        }

        // get schedule from XML and set as time the snapshot is scheduled to be taken
        list = doc.getElementsByTagName("schedule");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSchedule(node.getTextContent());
        }

        // get timezone from XML and set as the time zone of the snapshot policy
        list = doc.getElementsByTagName("timezone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTimeZone(node.getTextContent());
        }

        // get volumeid from XML and set as the ID of the disk volume
        list = doc.getElementsByTagName("volumeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeId(node.getTextContent());
        }

        return response;

    }

    /**
     * Deletes snapshot policies for the account.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteSnapshotPoliciesResponse deleteSnapshotPolicies(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteSnapshotPolicies", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteSnapshotPoliciesResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteSnapshotResponse object
     *
     * @param doc
     * @return
     */
    private DeleteSnapshotPoliciesResponse getDeleteSnapshotPoliciesResponse(Document doc) {
        DeleteSnapshotPoliciesResponse response = new DeleteSnapshotPoliciesResponse();

        // get displaytext from XML and set any text associated with the success or failure on deleting snapshot Policies
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting snapshot Policies
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists snapshot policies.
     *
     * @param diskvolumeId the ID of the disk volume
     * @param optional
     * @return
     * @throws Exception
     */
    public ListSnapshotPoliciesResponse listSnapshotPolicies(String diskvolumeId, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listSnapshotPolicies", optional);

        arguments.add(new NameValuePair("volumeid", diskvolumeId));

        Document responseDocument = server.makeRequest(arguments);

        return getListSnapshotPoliciesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListSnapshotPoliciesResponse object
     *
     * @param doc
     * @return
     */
    private ListSnapshotPoliciesResponse getListSnapshotPoliciesResponse(Document doc) {
        ListSnapshotPoliciesResponse response = new ListSnapshotPoliciesResponse();

        NodeList list = doc.getElementsByTagName("snapshotpolicy");

        List<SnapShotPolicyResponse> snapShotPolicies = new LinkedList<SnapShotPolicyResponse>();

        if (list.getLength() > 0) {

            for (int index = 0; index < list.getLength(); index++) {
                Node snapShotPolicyNode = list.item(index);

                if (snapShotPolicyNode == null) {
                    continue;
                }
                SnapShotPolicyResponse snapShotPolicy = new SnapShotPolicyResponse();

                NodeList snapShotPolicyProperties = snapShotPolicyNode.getChildNodes();

                for (int childIndex = 0; childIndex < snapShotPolicyProperties.getLength(); childIndex++) {
                    Node property = snapShotPolicyProperties.item(childIndex);

                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }

                    if (property.getNodeName().equals("id")) {
                        snapShotPolicy.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("intervaltype")) {
                        snapShotPolicy.setIntervalType(property.getTextContent());
                    } else if (property.getNodeName().equals("maxsnaps")) {
                        snapShotPolicy.setMaxSnaps(property.getTextContent());
                    } else if (property.getNodeName().equals("schedule")) {
                        snapShotPolicy.setSchedule(property.getTextContent());
                    } else if (property.getNodeName().equals("timezone")) {
                        snapShotPolicy.setTimeZone(property.getTextContent());
                    } else if (property.getNodeName().equals("volumeid")) {
                        snapShotPolicy.setVolumeId(property.getTextContent());
                    }
                }
                snapShotPolicies.add(snapShotPolicy);
            }
        }
        response.setSnapShotPolicies(snapShotPolicies);
        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for snapshot.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public SnapshotJobResultResponse snapshotJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getSnapshotJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into VolumeJobResultResponse object
     *
     * @param doc
     * @return
     */
    private SnapshotJobResultResponse getSnapshotJobResultResponse(Document doc) {
        SnapshotJobResultResponse response = new SnapshotJobResultResponse();

        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCmd(node.getTextContent());
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobProgressStatus(node.getTextContent());
        }

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            NodeList node = list.item(0).getChildNodes();
            for (int index = 0; index < node.getLength(); index++) {
                Node nodeProperty = node.item(index);
                if (nodeProperty.getNodeName().equals("snapshot")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setSnapshotId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setSnapshotAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("created")) {
                            response.setSnapshotCreated(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setSnapshotDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setSnapshotDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("intervaltype")) {
                            response.setIntervalType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setSnapshotName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setSnapshotProjectName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setSnapshotProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("snapshottype")) {
                            response.setSnapshotType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setSnapshotState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("volumeid")) {
                            response.setDiskVolumeId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("volumename")) {
                            response.setDiskVolumeName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("volumetype")) {
                            response.setDiskVolumeType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("jobid")) {
                            response.setJobid(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("jobstatus")) {
                            response.setJobStatus(childNodeProperty.getTextContent());
                        }
                    }
                } else if (nodeProperty.getNodeName().equals("errorcode")) {
                    response.setErrorCode(nodeProperty.getTextContent());
                } else if (nodeProperty.getNodeName().equals("errortext")) {
                    response.setErrorText(nodeProperty.getTextContent());
                }
            }
        }

        // get jobresultcode from XML and set as the result code for the job
        list = doc.getElementsByTagName("jobresultcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * Creates snapshot for a vm.
     *
     * @param virtualmachineid
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateVMSnapshotResponse createVMSnapshot(String virtualmachineid,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createVMSnapshot", optional);
        arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateVMSnapshotResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVMSnapshotResponse object
     *
     * @param doc
     * @return
     */
    private CreateVMSnapshotResponse getCreateVMSnapshotResponse(Document doc) {
        CreateVMSnapshotResponse response = new CreateVMSnapshotResponse();

        // get id from XML and set as ID of the snapshot
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account associated with the snapshot
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get created from XML and set as the date the snapshot was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get current from XML and set as isCurrent for the VM snapshot
        list = doc.getElementsByTagName("current");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCurrent(node.getTextContent());
        }

        // get description from XML and set as description for the VM snapshot
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        // get displayname from XML and set as displayname for the VM snapshot
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get domain from XML and set as he domain name of the snapshot's account
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain ID of the VM snapshot's account
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get name from XML and set as the date name of the VM snapshot
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get parent from XML and set as the parent id of the VM snapshot
        list = doc.getElementsByTagName("parent");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setParent(node.getTextContent());
        }

        // get parentName from XML and set as the project name of the VM snapshot
        list = doc.getElementsByTagName("parentName");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setParentName(node.getTextContent());
        }

        // get project from XML and set as the project name of the VM snapshot
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the VM snapshot
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get state from XML and set as the date the state of the VM snapshot
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get snapshottype from XML and set as the type of the VM snapshot
        list = doc.getElementsByTagName("snapshottype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get virtualmachineid from XML and set as the virtualmachine id for the VM snapshot
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get zoneid from XML and set as the zone id for the VM snapshot
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }
        
        // get jobid from XML and set the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobid(node.getTextContent());
        }

        return response;
    }

    /**
     * List virtual machine snapshot by conditions
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListVMSnapshotsResponse listVMSnapshot(HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVMSnapshot", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListVMSnapshotsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListVMSnapshotsResponse object
     *
     * @param doc
     * @return
     */
    private ListVMSnapshotsResponse getListVMSnapshotsResponse(Document doc) {
        ListVMSnapshotsResponse response = new ListVMSnapshotsResponse();

        NodeList list = doc.getElementsByTagName("vmSnapshot");

        List<VMSnapShotResponse> vmsnapShots = new LinkedList<VMSnapShotResponse>();

        if (list.getLength() > 0) {

            for (int index = 0; index < list.getLength(); index++) {
                Node snapShotNode = list.item(index);

                if (snapShotNode == null) {
                    continue;
                }
                VMSnapShotResponse vmsnapShot = new VMSnapShotResponse();

                NodeList snapShotProperties = snapShotNode.getChildNodes();

                for (int childIndex = 0; childIndex < snapShotProperties.getLength(); childIndex++) {
                    Node property = snapShotProperties.item(childIndex);

                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }

                    if (property.getNodeName().equals("id")) {
                        vmsnapShot.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        vmsnapShot.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        vmsnapShot.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("current")) {
                        vmsnapShot.setCurrent(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        vmsnapShot.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("displayname")) {
                        vmsnapShot.setDisplayName(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        vmsnapShot.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        vmsnapShot.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        vmsnapShot.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("parent")) {
                        vmsnapShot.setParent(property.getTextContent());
                    } else if (property.getNodeName().equals("parentName")) {
                        vmsnapShot.setParentName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        vmsnapShot.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        vmsnapShot.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        vmsnapShot.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        vmsnapShot.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachineid")) {
                        vmsnapShot.setVirtualMachineId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        vmsnapShot.setZoneId(property.getTextContent());
                    }
                }
                vmsnapShots.add(vmsnapShot);
            }
        }
        response.setVmsnapShots(vmsnapShots);
        return response;
    }

    /**
     * Deletes a vmsnapshot.
     *
     * @param vmsnapshotid
     * @return
     * @throws Exception
     */
    public DeleteVMSnapshotResponse deleteVMSnapshot(String vmsnapshotid) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteVMSnapshot", null);
        arguments.add(new NameValuePair("vmsnapshotid", vmsnapshotid));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteVMSnapshotResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteVMSnapshotResponse object
     *
     * @param doc
     * @return
     */
    private DeleteVMSnapshotResponse getDeleteVMSnapshotResponse(Document doc) {
        DeleteVMSnapshotResponse response = new DeleteVMSnapshotResponse();

        // get displaytext from XML and set any text associated with the success or failure on deleting snapshot 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }

        // get jobid from XML and set as jobid
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobid(node.getTextContent());
        }

        //get success from XML and any text associated with the success or failure on deleting snapshot 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Revert VM from a vmsnapshot.
     *
     * @param vmsnapshotid
     * @return
     * @throws Exception
     */
    public RevertToVMSnapshotResponse revertToVMSnapshot(String vmsnapshotid) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("revertToVMSnapshot", null);
        arguments.add(new NameValuePair("vmsnapshotid", vmsnapshotid));

        Document responseDocument = server.makeRequest(arguments);

        return getRevertToVMSnapshotResponse(responseDocument);
    }

    /**
     * Converts XML document into RevertToVMSnapshotResponse object
     *
     * @param doc
     * @return
     */
    private RevertToVMSnapshotResponse getRevertToVMSnapshotResponse(Document doc) {
        RevertToVMSnapshotResponse response = new RevertToVMSnapshotResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get cpunumber from XML and set the number of CPU this virtual machine is running with
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }

        // get cpuspeed from XML and set the speed of each cpu
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }

        // get cpuused from XML and set the amount of the virtual machine's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuUsed(node.getTextContent());
        }

        // get created from XML and set the date when this virtual machine was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and the ID of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get forvirtualnetwork from XML and set the virtual network for the service offering
        list = doc.getElementsByTagName("forvirtualnetwork");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setForVirtualNetwork(node.getTextContent());
        }

        // get group from XML and set the group name of the virtual machine
        list = doc.getElementsByTagName("group");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGroup(node.getTextContent());
        }

        // get groupid from XML and set the group ID of the virtual machine
        list = doc.getElementsByTagName("groupid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGroupId(node.getTextContent());
        }

        // get guestosid from XML and set Os type ID of the virtual machine
        list = doc.getElementsByTagName("guestosid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestOsId(node.getTextContent());
        }

        // get haenable from XML and set true if high-availability is enabled, false otherwise
        list = doc.getElementsByTagName("haenable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHaEnable(node.getTextContent());
        }

        // get hostid from XML and set the ID of the host for the virtual machine
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the name of the host for the virtual machine
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHyperVisor(node.getTextContent());
        }

        // get instancename from XML and set as instance name of the user vm; this parameter is returned to the ROOT admin only
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // get isdynamicallyscalable from XML and set as true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
        }

        // get isodisplaytext from XML and set as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("isodisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoDisplayText(node.getTextContent());
        }

        // get isoid from XML and set as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("isodisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoDisplayText(node.getTextContent());
        }

        // get isoid from XML and set the ID of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("isoid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoId(node.getTextContent());
        }

        // get isoname from XML and set the name of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("isoname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoName(node.getTextContent());
        }

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkKbsRead from XML set and the incoming network traffic on the vm
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbsread from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
        }

        // get password from XML and set the password (if exists) of the virtual machine
        list = doc.getElementsByTagName("password");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPassword(node.getTextContent());
        }

        // get passwordenabled from XML and set true if the password rest feature is enabled, false otherwise
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }

        // get project from XML and set as the project name of the vm
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get publicip from XML and set as public IP address id associated with vm via Static nat rule
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicipid from XML and set as public IP address id associated with vm via Static nat rule
        list = doc.getElementsByTagName("publicipid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicipId(node.getTextContent());
        }

        // get rootdeviceid from XML and set as device ID of the root volume
        list = doc.getElementsByTagName("rootdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRootDeviceId(node.getTextContent());
        }

        // get rootdevicetype from XML and set as device type of the root volume
        list = doc.getElementsByTagName("rootdevicetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRootDeviceType(node.getTextContent());
        }

        // get serviceofferingid from XML and set as the ID of the service offering of the virtual machine
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get servicestate from XML and set as State of the Service from LB rule
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get templatedisplaytext from XML and set as an alternate display text of the template for the virtual machine
        list = doc.getElementsByTagName("templatedisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDisplayText(node.getTextContent());
        }

        // get templateid from XML and set as 	the ID of the template for the virtual machine. 
        // A -1 is returned if the virtual machine was created from an ISO file.
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get templatename from XML and set the name of the template for the virtual machine
        list = doc.getElementsByTagName("templatename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateName(node.getTextContent());
        }

        // get zoneid from XML and set as the ID of the availablility zone for the virtual machine
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the name of the availability zone for the virtual machine
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
        }

        //the list of NetworkInterfaceCards associated with virtual machine
        list = doc.getElementsByTagName("nic");
        if (list.getLength() > 0) {
            List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node networkInterfaceCardNode = list.item(index);
                NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                NodeList networkInterfaceCardProperties = networkInterfaceCardNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkInterfaceCardProperties.getLength(); childIndex++) {
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);
                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
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

            // get jobid number from XML and set the ID of the latest async job acting on this object
            list = doc.getElementsByTagName("jobid");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setJobId(node.getTextContent());
            }

            // get jobstatus number from XML and set the current status of the latest async job acting on this object
            list = doc.getElementsByTagName("jobstatus");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setJobStatus(node.getTextContent());
            }
        }

        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for VM snapshot.
     *
     * @param asychronousJobid
     * @return
     * @throws Exception
     */
    public VMSnapshotJobResultResponse vmSnapshotJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getVMSnapshotJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into VMSnapshotJobResultResponse object
     *
     * @param doc
     * @return
     */
    private VMSnapshotJobResultResponse getVMSnapshotJobResultResponse(Document doc) {
        VMSnapshotJobResultResponse response = new VMSnapshotJobResultResponse();

        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCmd(node.getTextContent());
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobProgressStatus(node.getTextContent());
        }

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            NodeList node = list.item(0).getChildNodes();
            for (int index = 0; index < node.getLength(); index++) {
                Node nodeProperty = node.item(index);
                if (nodeProperty.getNodeName().equals("vmsnapshot")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setVMSnapshotId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setVMSnapshotAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("created")) {
                            response.setVMSnapshotCreated(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setVMSnapshotDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setVMSnapshotDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setVMSnapshotName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setVMSnapshotProjectName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setVMSnapshotProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("snapshottype")) {
                            response.setVMSnapshotType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("virtualmachineid")) {
                            response.setVirtualMachineId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setVMSnapshotState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("current")) {
                            response.setVMSnapshotCurrent(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("description")) {
                            response.setVMSnapshotDescription(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("displayname")) {
                            response.setVMSnapshotDisplayName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("parent")) {
                            response.setVMSnapshotParentId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("parentName")) {
                            response.setVMSnapshotParentName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("jobid")) {
                            response.setJobid(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("jobstatus")) {
                            response.setJobStatus(childNodeProperty.getTextContent());
                        }
                    }
                } else if (nodeProperty.getNodeName().equals("errorcode")) {
                    response.setErrorCode(nodeProperty.getTextContent());
                } else if (nodeProperty.getNodeName().equals("errortext")) {
                    response.setErrorText(nodeProperty.getTextContent());
                }
            }
        }

        // get jobresultcode from XML and set as the result code for the job
        list = doc.getElementsByTagName("jobresultcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobId(node.getTextContent());
        }

        return response;
    }

}
