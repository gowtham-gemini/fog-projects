package com.assistanz.cloud.cloudstack.snapshot;

import java.util.HashMap;
import java.util.LinkedList;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;


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
        HashMap<String,String> optional) throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createSnapshot", optional);
        arguments.add(new NameValuePair("volumeid",  diskVolumeId));



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
            response.setSnapshotId(node.getTextContent());
        }

        // get account from XML and set as the account associated with the snapshot
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotAccount(node.getTextContent());
        }

        // get created from XML and set as the date the snapshot was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotCreated(node.getTextContent());
        }

        // get domain from XML and set as he domain name of the snapshot's account
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain ID of the snapshot's account
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotDomainId(node.getTextContent());
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
            response.setSnapshotName(node.getTextContent());
        }

        // get project from XML and set as the project name of the snapshot
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotProjectName(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the snapshot
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotProjectId(node.getTextContent());
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
            response.setSnapshotState(node.getTextContent());
        }

        // get volumeid from XML and set as the ID of the disk volume
        list = doc.getElementsByTagName("volumeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeId(node.getTextContent());
        }

        // get volumename from XML and set as the name of the disk volume
        list = doc.getElementsByTagName("volumename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeName(node.getTextContent());
        }

        // get volumetype from XML and set type for the disk volume
        list = doc.getElementsByTagName("volumetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeType(node.getTextContent());
        }

        // get jobid from XML and set the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobid(node.getTextContent());
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
        HashMap<String,String> optional) throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listSnapshots", optional);

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

                if (snapShotNode == null) {
                    continue;
                }  
                SnapShotResponse snapShot = new SnapShotResponse();

                NodeList snapShotProperties = snapShotNode.getChildNodes();

                for (int childIndex = 0; childIndex < snapShotProperties.getLength(); childIndex++) {
                    Node property = snapShotProperties.item(childIndex);

                    if (property == null || property.getNodeName() == null) {
                            continue;
                    }

                    if (property.getNodeName().equals("id")) {
                        snapShot.setSnapshotId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        snapShot.setSnapshotAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        snapShot.setSnapshotCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        snapShot.setSnapshotDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        snapShot.setSnapshotDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("intervaltype")) {
                        snapShot.setIntervalType(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        snapShot.setSnapshotName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        snapShot.setSnapshotProjectName(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        snapShot.setSnapshotProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshottype")) {
                        snapShot.setSnapshotType(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        snapShot.setSnapshotState(property.getTextContent());
                    } else if (property.getNodeName().equals("volumeid")) {
                        snapShot.setDiskVolumeId(property.getTextContent());
                    } else if (property.getNodeName().equals("volumename")) {
                        snapShot.setDiskVolumeName(property.getTextContent());
                    } else if (property.getNodeName().equals("volumetype")) {
                        snapShot.setDiskVolumeType(property.getTextContent());
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

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteSnapshot", null);
        arguments.add(new NameValuePair("id",  snapshotId));


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
     *  Creates a snapshot policy for the account.
     * 
     * @param snapshotPolicyIntervalType valid values are HOURLY, DAILY, WEEKLY, and MONTHLY
     * @param snapshotPolicyMaxSnaps maximum number of snapshots to retain
     * @param snapshotPolicySchedule time the snapshot is scheduled to be taken. 
     * 								 Format is:* if HOURLY, MM* if DAILY, MM:HH* if WEEKLY, MM:HH:DD (1-7)* 
     * 											 if MONTHLY, MM:HH:DD (1-28)
     * @param snapshotPolicyTimeZone Specifies a timezone for this command. 
     * 								 For more information on the timezone parameter, see Time Zone Format.
     * @param diskvolumeId the ID of the disk volume
     * @return
     * @throws Exception
     */
    public CreateSnapshotPolicyResponse createSnapshotPolicy(String snapshotPolicyIntervalType,
        String snapshotPolicyMaxSnaps, String snapshotPolicySchedule, String snapshotPolicyTimeZone, String diskvolumeId) throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createSnapshotPolicy", null);
        arguments.add(new NameValuePair("intervaltype",  snapshotPolicyIntervalType));
        arguments.add(new NameValuePair("maxsnaps",  snapshotPolicyMaxSnaps));
        arguments.add(new NameValuePair("schedule",  snapshotPolicySchedule));
        arguments.add(new NameValuePair("timezone",  snapshotPolicyTimeZone));
        arguments.add(new NameValuePair("volumeid",  diskvolumeId));

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
            response.setSnapshotPolicyId(node.getTextContent());
        }

        // get intervaltype from XML and set as the interval type of the snapshot policy
        list = doc.getElementsByTagName("intervaltype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotPolicyIntervalType(node.getTextContent());
        }

        // get maxsnaps from XML and set as maximum number of snapshots retained
        list = doc.getElementsByTagName("maxsnaps");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxsnaps(node.getTextContent());
        }

        // get schedule from XML and set as time the snapshot is scheduled to be taken
        list = doc.getElementsByTagName("schedule");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotSchedule(node.getTextContent());
        }

        // get timezone from XML and set as the time zone of the snapshot policy
        list = doc.getElementsByTagName("timezone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotPolicyTimeZone(node.getTextContent());
        }

        // get volumeid from XML and set as the ID of the disk volume
        list = doc.getElementsByTagName("volumeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeId(node.getTextContent());
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
        HashMap<String,String> optional) throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteSnapshotPolicies", optional);

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
    public ListSnapshotPoliciesResponse listSnapshotPolicies(String diskvolumeId, HashMap<String,String> optional) throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listSnapshotPolicies", optional);

        arguments.add(new NameValuePair("volumeid",  diskvolumeId));

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
                        snapShotPolicy.setSnapshotPolicyId(property.getTextContent());
                    } else if (property.getNodeName().equals("intervaltype")) {
                        snapShotPolicy.setSnapshotPolicyIntervalType(property.getTextContent());
                    } else if (property.getNodeName().equals("maxsnaps")) {
                        snapShotPolicy.setMaxSnaps(property.getTextContent());
                    } else if (property.getNodeName().equals("schedule")) {
                        snapShotPolicy.setSnapshotSchedule(property.getTextContent());
                    } else if (property.getNodeName().equals("timezone")) {
                        snapShotPolicy.setSnapshotPolicyTimeZone(property.getTextContent());
                    } else if (property.getNodeName().equals("volumeid")) {
                        snapShotPolicy.setDiskVolumeId(property.getTextContent());
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

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("queryAsyncJobResult", null);
            arguments.add(new NameValuePair("jobid",  asychronousJobid));

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
                    NodeList childNodeProperties =  nodeProperty.getChildNodes();
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
                } else if(nodeProperty.getNodeName().equals("errorcode")) {
                    response.setErrorCode(nodeProperty.getTextContent());
                } else if(nodeProperty.getNodeName().equals("errortext")) {
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
        HashMap<String,String> optional) throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createVMSnapshot", optional);
        arguments.add(new NameValuePair("virtualmachineid",  virtualmachineid));



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
            response.setVMSnapshotId(node.getTextContent());
        }

        // get account from XML and set as the account associated with the snapshot
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotAccount(node.getTextContent());
        }

        // get created from XML and set as the date the snapshot was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotCreated(node.getTextContent());
        }

        // get domain from XML and set as he domain name of the snapshot's account
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain ID of the VM snapshot's account
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotDomainId(node.getTextContent());
        }
        
        // get name from XML and set as the date name of the VM snapshot
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotName(node.getTextContent());
        }

        // get project from XML and set as the project name of the VM snapshot
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotProjectName(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the VM snapshot
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotProjectId(node.getTextContent());
        }
        
        // get parentName from XML and set as the project name of the VM snapshot
        list = doc.getElementsByTagName("parentName");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotParentName(node.getTextContent());
        }

        // get parent from XML and set as the parent id of the VM snapshot
        list = doc.getElementsByTagName("parent");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotParentId(node.getTextContent());
        }

        // get snapshottype from XML and set as the type of the VM snapshot
        list = doc.getElementsByTagName("snapshottype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotType(node.getTextContent());
        }

        // get state from XML and set as the date the state of the VM snapshot
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotState(node.getTextContent());
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
        
        // get description from XML and set as description for the VM snapshot
        list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotDescription(node.getTextContent());
        }
        
        // get displayname from XML and set as displayname for the VM snapshot
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotDisplayName(node.getTextContent());
        }
        
         // get current from XML and set as isCurrent for the VM snapshot
        list = doc.getElementsByTagName("current");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVMSnapshotCurrent(node.getTextContent());
        }
        
        // get jobid from XML and set the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobid(node.getTextContent());
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
     * List virtual machine snapshot by conditions
     * 
     * @param optional
     * @return
     * @throws Exception 
     */
    public ListVMSnapshotsResponse listVMSnapshot(HashMap<String,String> optional) throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listVMSnapshot", optional);

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
                        vmsnapShot.setVMSnapshotId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        vmsnapShot.setVMSnapshotAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        vmsnapShot.setVMSnapshotCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        vmsnapShot.setVMSnapshotDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        vmsnapShot.setVMSnapshotDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        vmsnapShot.setVMSnapshotName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        vmsnapShot.setVMSnapshotProjectName(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        vmsnapShot.setVMSnapshotProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        vmsnapShot.setVMSnapshotType(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachineid")) {
                        vmsnapShot.setVirtualMachineId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        vmsnapShot.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        vmsnapShot.setVMSnapshotState(property.getTextContent());
                    } else if (property.getNodeName().equals("current")) {
                        vmsnapShot.setVMSnapshotCurrent(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        vmsnapShot.setVMSnapshotDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("displayname")) {
                        vmsnapShot.setVMSnapshotDisplayName(property.getTextContent());
                    } else if (property.getNodeName().equals("parent")) {
                        vmsnapShot.setVMSnapshotParentId(property.getTextContent());
                    } else if (property.getNodeName().equals("parentName")) {
                        vmsnapShot.setVMSnapshotParentName(property.getTextContent());
                    } else if (property.getNodeName().equals("jobid")) {
                        vmsnapShot.setJobid(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        vmsnapShot.setJobStatus(property.getTextContent());
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

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteVMSnapshot", null);
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

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("revertToVMSnapshot", null);
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

        // get displaytext from XML and set any text associated with the success or failure on deleting snapshot 
        NodeList list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }
        
        // get jobid from XML and set as jobid
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
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

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("queryAsyncJobResult", null);
            arguments.add(new NameValuePair("jobid",  asychronousJobid));

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
                    NodeList childNodeProperties =  nodeProperty.getChildNodes();
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
                } else if(nodeProperty.getNodeName().equals("errorcode")) {
                    response.setErrorCode(nodeProperty.getTextContent());
                } else if(nodeProperty.getNodeName().equals("errortext")) {
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
