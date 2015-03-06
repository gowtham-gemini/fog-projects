package com.assistanz.cloud.cloudstack.volume;

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
public class CSVolumeService {

    private CloudStackServer server;

    public CSVolumeService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Attaches a disk volume to a virtual machine.
     *
     * @param diskVolumeId The ID of the disk volume
     * @param virtualMachineId The ID of the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public AttachVolumeResponse attachVolume(String diskVolumeId,
            String virtualMachineId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("attachVolume", optional);
        arguments.add(new NameValuePair("id", diskVolumeId));
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getAttachVolumeResponse(responseDocument);
    }

    /**
     * Converts XML document into AttachVolumeResponse object
     *
     * @param doc
     * @return
     */
    private AttachVolumeResponse getAttachVolumeResponse(Document doc) {
        AttachVolumeResponse response = new AttachVolumeResponse();

        // get id from XML and set as ID of the disk volume
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account associated with the disk volume
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get attached from XML and set as the date the volume was attached to a VM instance
        list = doc.getElementsByTagName("attached");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAttached(node.getTextContent());
        }

        // get created from XML and set as the date the disk volume was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // the boolean state of whether the volume is destroyed or not
        list = doc.getElementsByTagName("destroyed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestroyed(node.getTextContent());
        }

        // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
        //This tag is not returned when the volume is detached.
        list = doc.getElementsByTagName("deviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDeviceId(node.getTextContent());
        }

        // get bytes read rate of the disk volume
        list = doc.getElementsByTagName("diskBytesReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesReadRate(node.getTextContent());
        }

        // get bytes write rate of the disk volume
        list = doc.getElementsByTagName("diskBytesWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesWriteRate(node.getTextContent());
        }

        // get io requests read rate of the disk volume
        list = doc.getElementsByTagName("diskIopsReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsReadRate(node.getTextContent());
        }

        // get io requests write rate of the disk volume
        list = doc.getElementsByTagName("diskIopsWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsWriteRate(node.getTextContent());
        }

        // get diskofferingdisplaytext from XML and set as the display text of the disk offering
        list = doc.getElementsByTagName("diskofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingDisplayText(node.getTextContent());
        }

        // get diskofferingid from XML and set as the ID of the disk offering
        list = doc.getElementsByTagName("diskofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingId(node.getTextContent());
        }

        // get diskofferingname from XML and set as the name of the disk offering
        list = doc.getElementsByTagName("diskofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingName(node.getTextContent());
        }

        // an optional field whether to the display the volume to the end user or not
        list = doc.getElementsByTagName("displayvolume");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVolume(node.getTextContent());
        }

        // get domain from XML and set as the domain associated with the disk volume
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the ID of the domain associated with the disk volume
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get hypervisor from XML and set as the Hypervisor the volume belongs to 
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the volume is extractable, false otherwise
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get max iops of the disk volume
        list = doc.getElementsByTagName("maxiops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxIops(node.getTextContent());
        }

        // get min iops of the disk volume
        list = doc.getElementsByTagName("miniops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMinIops(node.getTextContent());
        }

        // get name of the disk volume
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get serviceofferingdisplaytext from XML and set as the display text of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingDisplayText(node.getTextContent());
        }

        // get serviceofferingid from XML and set as the ID of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set as the name of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get size from XML and set as the size of the disk volume
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSize(node.getTextContent());
        }

        // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
        list = doc.getElementsByTagName("snapshotid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotId(node.getTextContent());
        }

        // get state from XML and set as the state of the disk volume
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get state from XML and set as the status of the disk volume
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
        }

        // get storage from XML and set as the name of the primary storage hosting the disk volume
        list = doc.getElementsByTagName("storage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorage(node.getTextContent());
        }

        // get storagetype from XML and set as the shared or local storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getTextContent());
        }

        // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get virtualmachineid from XML and set as the id of the virtual machine
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get vmdisplayname from XML and set as the display name of the virtual machine
        list = doc.getElementsByTagName("vmdisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmDisplayName(node.getTextContent());
        }

        // get vmname from XML and set as the name of the virtual machine
        list = doc.getElementsByTagName("vmname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmName(node.getTextContent());
        }

        // get vmstate from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("vmstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmState(node.getTextContent());
        }

        // get zoneid from XML and set as the ID of the availability zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the name of the availability zone
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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

        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;

    }

    /**
     * Resize a disk volume from a virtual machine
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ResizeVolumeResponse resizeVolume(HashMap<String, String> optional) throws Exception {
        LinkedList<NameValuePair> arguments = server.getDefaultQuery("resizeVolume", optional);
        Document responseDocument = server.makeRequest(arguments);

        return getResizeVolumeResponse(responseDocument);

    }

    /**
     * Converts XML document into DetachVolumeResponse object
     *
     * @param doc
     * @return
     */
    private ResizeVolumeResponse getResizeVolumeResponse(Document doc) {
        ResizeVolumeResponse response = new ResizeVolumeResponse();

        // get id from XML and set as ID of the disk volume
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account associated with the disk volume
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get attached from XML and set as the date the volume was attached to a VM instance
        list = doc.getElementsByTagName("attached");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAttached(node.getTextContent());
        }

        // get created from XML and set as the date the disk volume was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // the boolean state of whether the volume is destroyed or not
        list = doc.getElementsByTagName("destroyed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestroyed(node.getTextContent());
        }

        // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
        //This tag is not returned when the volume is detached.
        list = doc.getElementsByTagName("deviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDeviceId(node.getTextContent());
        }

        // get bytes read rate of the disk volume
        list = doc.getElementsByTagName("diskBytesReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesReadRate(node.getTextContent());
        }

        // get bytes write rate of the disk volume
        list = doc.getElementsByTagName("diskBytesWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesWriteRate(node.getTextContent());
        }

        // get io requests read rate of the disk volume
        list = doc.getElementsByTagName("diskIopsReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsReadRate(node.getTextContent());
        }

        // get io requests write rate of the disk volume
        list = doc.getElementsByTagName("diskIopsWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsWriteRate(node.getTextContent());
        }

        // get diskofferingdisplaytext from XML and set as the display text of the disk offering
        list = doc.getElementsByTagName("diskofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingDisplayText(node.getTextContent());
        }

        // get diskofferingid from XML and set as the ID of the disk offering
        list = doc.getElementsByTagName("diskofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingId(node.getTextContent());
        }

        // get diskofferingname from XML and set as the name of the disk offering
        list = doc.getElementsByTagName("diskofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingName(node.getTextContent());
        }

        // an optional field whether to the display the volume to the end user or not
        list = doc.getElementsByTagName("displayvolume");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVolume(node.getTextContent());
        }

        // get domain from XML and set as the domain associated with the disk volume
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the ID of the domain associated with the disk volume
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get hypervisor from XML and set as the Hypervisor the volume belongs to 
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the volume is extractable, false otherwise
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get max iops of the disk volume
        list = doc.getElementsByTagName("maxiops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxIops(node.getTextContent());
        }

        // get min iops of the disk volume
        list = doc.getElementsByTagName("miniops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMinIops(node.getTextContent());
        }

        // get name of the disk volume
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get serviceofferingdisplaytext from XML and set as the display text of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingDisplayText(node.getTextContent());
        }

        // get serviceofferingid from XML and set as the ID of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set as the name of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get size from XML and set as the size of the disk volume
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSize(node.getTextContent());
        }

        // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
        list = doc.getElementsByTagName("snapshotid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotId(node.getTextContent());
        }

        // get state from XML and set as the state of the disk volume
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get state from XML and set as the status of the disk volume
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
        }

        // get storage from XML and set as the name of the primary storage hosting the disk volume
        list = doc.getElementsByTagName("storage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorage(node.getTextContent());
        }

        // get storagetype from XML and set as the shared or local storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getTextContent());
        }

        // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get virtualmachineid from XML and set as the id of the virtual machine
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get vmdisplayname from XML and set as the display name of the virtual machine
        list = doc.getElementsByTagName("vmdisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmDisplayName(node.getTextContent());
        }

        // get vmname from XML and set as the name of the virtual machine
        list = doc.getElementsByTagName("vmname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmName(node.getTextContent());
        }

        // get vmstate from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("vmstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmState(node.getTextContent());
        }

        // get zoneid from XML and set as the ID of the availability zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the name of the availability zone
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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

        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;
    }

    /**
     * Detaches a disk volume from a virtual machine
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public DetachVolumeResponse detachVolume(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("detachVolume", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getDetachVolumeResponse(responseDocument);
    }

    /**
     * Converts XML document into DetachVolumeResponse object
     *
     * @param doc
     * @return
     */
    private DetachVolumeResponse getDetachVolumeResponse(Document doc) {
        DetachVolumeResponse response = new DetachVolumeResponse();

        // get id from XML and set as ID of the disk volume
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account associated with the disk volume
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get attached from XML and set as the date the volume was attached to a VM instance
        list = doc.getElementsByTagName("attached");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAttached(node.getTextContent());
        }

        // get created from XML and set as the date the disk volume was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // the boolean state of whether the volume is destroyed or not
        list = doc.getElementsByTagName("destroyed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestroyed(node.getTextContent());
        }

        // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
        //This tag is not returned when the volume is detached.
        list = doc.getElementsByTagName("deviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDeviceId(node.getTextContent());
        }

        // get bytes read rate of the disk volume
        list = doc.getElementsByTagName("diskBytesReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesReadRate(node.getTextContent());
        }

        // get bytes write rate of the disk volume
        list = doc.getElementsByTagName("diskBytesWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesWriteRate(node.getTextContent());
        }

        // get io requests read rate of the disk volume
        list = doc.getElementsByTagName("diskIopsReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsReadRate(node.getTextContent());
        }

        // get io requests write rate of the disk volume
        list = doc.getElementsByTagName("diskIopsWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsWriteRate(node.getTextContent());
        }

        // get diskofferingdisplaytext from XML and set as the display text of the disk offering
        list = doc.getElementsByTagName("diskofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingDisplayText(node.getTextContent());
        }

        // get diskofferingid from XML and set as the ID of the disk offering
        list = doc.getElementsByTagName("diskofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingId(node.getTextContent());
        }

        // get diskofferingname from XML and set as the name of the disk offering
        list = doc.getElementsByTagName("diskofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingName(node.getTextContent());
        }

        // an optional field whether to the display the volume to the end user or not
        list = doc.getElementsByTagName("displayvolume");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVolume(node.getTextContent());
        }

        // get domain from XML and set as the domain associated with the disk volume
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the ID of the domain associated with the disk volume
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get hypervisor from XML and set as the Hypervisor the volume belongs to 
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the volume is extractable, false otherwise
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get max iops of the disk volume
        list = doc.getElementsByTagName("maxiops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxIops(node.getTextContent());
        }

        // get min iops of the disk volume
        list = doc.getElementsByTagName("miniops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMinIops(node.getTextContent());
        }

        // get name of the disk volume
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get serviceofferingdisplaytext from XML and set as the display text of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingDisplayText(node.getTextContent());
        }

        // get serviceofferingid from XML and set as the ID of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set as the name of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get size from XML and set as the size of the disk volume
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSize(node.getTextContent());
        }

        // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
        list = doc.getElementsByTagName("snapshotid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotId(node.getTextContent());
        }

        // get state from XML and set as the state of the disk volume
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get state from XML and set as the status of the disk volume
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
        }

        // get storage from XML and set as the name of the primary storage hosting the disk volume
        list = doc.getElementsByTagName("storage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorage(node.getTextContent());
        }

        // get storagetype from XML and set as the shared or local storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getTextContent());
        }

        // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get virtualmachineid from XML and set as the id of the virtual machine
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get vmdisplayname from XML and set as the display name of the virtual machine
        list = doc.getElementsByTagName("vmdisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmDisplayName(node.getTextContent());
        }

        // get vmname from XML and set as the name of the virtual machine
        list = doc.getElementsByTagName("vmname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmName(node.getTextContent());
        }

        // get vmstate from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("vmstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmState(node.getTextContent());
        }

        // get zoneid from XML and set as the ID of the availability zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the name of the availability zone
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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

        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;

    }

    /**
     * Creates a disk volume from a disk offering. This disk volume must still be attached to a virtual machine to make
     * use of it.
     *
     * @param diskVolumeName The name of the disk volume
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateVolumeResponse createVolume(String diskVolumeName, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createVolume", optional);
        arguments.add(new NameValuePair("name", diskVolumeName));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateVolumeResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVolumeResponse object
     *
     * @param doc
     * @return
     */
    private CreateVolumeResponse getCreateVolumeResponse(Document doc) {
        CreateVolumeResponse response = new CreateVolumeResponse();

        // get id from XML and set as ID of the disk volume
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account associated with the disk volume
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get attached from XML and set as the date the volume was attached to a VM instance
        list = doc.getElementsByTagName("attached");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAttached(node.getTextContent());
        }

        // get created from XML and set as the date the disk volume was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // the boolean state of whether the volume is destroyed or not
        list = doc.getElementsByTagName("destroyed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestroyed(node.getTextContent());
        }

        // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
        //This tag is not returned when the volume is detached.
        list = doc.getElementsByTagName("deviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDeviceId(node.getTextContent());
        }

        // get bytes read rate of the disk volume
        list = doc.getElementsByTagName("diskBytesReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesReadRate(node.getTextContent());
        }

        // get bytes write rate of the disk volume
        list = doc.getElementsByTagName("diskBytesWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesWriteRate(node.getTextContent());
        }

        // get io requests read rate of the disk volume
        list = doc.getElementsByTagName("diskIopsReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsReadRate(node.getTextContent());
        }

        // get io requests write rate of the disk volume
        list = doc.getElementsByTagName("diskIopsWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsWriteRate(node.getTextContent());
        }

        // get diskofferingdisplaytext from XML and set as the display text of the disk offering
        list = doc.getElementsByTagName("diskofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingDisplayText(node.getTextContent());
        }

        // get diskofferingid from XML and set as the ID of the disk offering
        list = doc.getElementsByTagName("diskofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingId(node.getTextContent());
        }

        // get diskofferingname from XML and set as the name of the disk offering
        list = doc.getElementsByTagName("diskofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingName(node.getTextContent());
        }

        // an optional field whether to the display the volume to the end user or not
        list = doc.getElementsByTagName("displayvolume");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVolume(node.getTextContent());
        }

        // get domain from XML and set as the domain associated with the disk volume
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the ID of the domain associated with the disk volume
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get hypervisor from XML and set as the Hypervisor the volume belongs to 
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the volume is extractable, false otherwise
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get max iops of the disk volume
        list = doc.getElementsByTagName("maxiops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxIops(node.getTextContent());
        }

        // get min iops of the disk volume
        list = doc.getElementsByTagName("miniops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMinIops(node.getTextContent());
        }

        // get name of the disk volume
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get serviceofferingdisplaytext from XML and set as the display text of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingDisplayText(node.getTextContent());
        }

        // get serviceofferingid from XML and set as the ID of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set as the name of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get size from XML and set as the size of the disk volume
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSize(node.getTextContent());
        }

        // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
        list = doc.getElementsByTagName("snapshotid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotId(node.getTextContent());
        }

        // get state from XML and set as the state of the disk volume
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get state from XML and set as the status of the disk volume
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
        }

        // get storage from XML and set as the name of the primary storage hosting the disk volume
        list = doc.getElementsByTagName("storage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorage(node.getTextContent());
        }

        // get storagetype from XML and set as the shared or local storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getTextContent());
        }

        // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get virtualmachineid from XML and set as the id of the virtual machine
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get vmdisplayname from XML and set as the display name of the virtual machine
        list = doc.getElementsByTagName("vmdisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmDisplayName(node.getTextContent());
        }

        // get vmname from XML and set as the name of the virtual machine
        list = doc.getElementsByTagName("vmname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmName(node.getTextContent());
        }

        // get vmstate from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("vmstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmState(node.getTextContent());
        }

        // get zoneid from XML and set as the ID of the availability zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the name of the availability zone
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
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
     * Deletes a detached disk volume.
     *
     * @param diskVolumeId The ID of the disk volume
     * @return
     * @throws Exception
     */
    public DeleteVolumeResponse deleteVolume(String diskVolumeId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteVolume", null);
        arguments.add(new NameValuePair("id", diskVolumeId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteVolumeResponse(responseDocument);

    }

    /**
     * Converts XML document into DeleteVolumeResponse object
     *
     * @param doc
     * @return
     */
    private DeleteVolumeResponse getDeleteVolumeResponse(Document doc) {
        DeleteVolumeResponse response = new DeleteVolumeResponse();

        // get displaytext from XML and set Any text associated with the success or 
        // failure on Deleting a volume
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get success from XML and set Return true if Delete volume operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all volumes.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListVolumeResponse listVolumes(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVolumes", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListVolumeResponse(responseDocument);
    }

    /**
     * Converts XML document into ListVolumeResponse object
     *
     * @param doc
     * @return
     */
    private ListVolumeResponse getListVolumeResponse(Document doc) {
        ListVolumeResponse response = new ListVolumeResponse();

        //list of volumes
        NodeList list = doc.getElementsByTagName("volume");

        List<VolumeResponse> volumes = new LinkedList<VolumeResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node volumeNode = list.item(index);

                VolumeResponse volume = new VolumeResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList volumeProperties = volumeNode.getChildNodes();
                for (int childIndex = 0; childIndex < volumeProperties.getLength(); childIndex++) {
                    Node property = volumeProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        volume.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        volume.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("attached")) {
                        volume.setAttached(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        volume.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("destroyed")) {
                        volume.setDestroyed(property.getTextContent());
                    } else if (property.getNodeName().equals("deviceid")) {
                        volume.setDeviceId(property.getTextContent());
                    } else if (property.getNodeName().equals("diskBytesReadRate")) {
                        volume.setDiskBytesReadRate(property.getTextContent());
                    } else if (property.getNodeName().equals("diskBytesWriteRate")) {
                        volume.setDiskBytesWriteRate(property.getTextContent());
                    } else if (property.getNodeName().equals("diskIopsReadRate")) {
                        volume.setDiskIopsReadRate(property.getTextContent());
                    } else if (property.getNodeName().equals("diskIopsWriteRate")) {
                        volume.setDiskIopsWriteRate(property.getTextContent());
                    } else if (property.getNodeName().equals("diskofferingdisplaytext")) {
                        volume.setDiskOfferingDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("diskofferingid")) {
                        volume.setDiskOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("diskofferingname")) {
                        volume.setDiskOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("displayvolume")) {
                        volume.setDisplayVolume(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        volume.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        volume.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                        volume.setHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("isextractable")) {
                        volume.setIsExtractable(property.getTextContent());
                    } else if (property.getNodeName().equals("maxiops")) {
                        volume.setMaxIops(property.getTextContent());
                    } else if (property.getNodeName().equals("miniops")) {
                        volume.setMinIops(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        volume.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        volume.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        volume.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingdisplaytext")) {
                        volume.setServiceOfferingDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingid")) {
                        volume.setServiceOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingname")) {
                        volume.setServiceOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("size")) {
                        volume.setSize(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshotid")) {
                        volume.setSnapshotId(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        volume.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("status")) {
                        volume.setStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("storage")) {
                        volume.setStorage(property.getTextContent());
                    } else if (property.getNodeName().equals("storagetype")) {
                        volume.setStorageType(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        volume.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachineid")) {
                        volume.setVirtualMachineId(property.getTextContent());
                    } else if (property.getNodeName().equals("vmdisplayname")) {
                        volume.setVmDisplayName(property.getTextContent());
                    } else if (property.getNodeName().equals("vmname")) {
                        volume.setVmName(property.getTextContent());
                    } else if (property.getNodeName().equals("vmstate")) {
                        volume.setVmState(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        volume.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        volume.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        NodeList tagsProperties = property.getChildNodes();
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
                        volume.setTagss(tagss);
                    } else if (property.getNodeName().equals("jobid")) {
                        volume.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        volume.setJobStatus(property.getTextContent());
                    }

                }

                volumes.add(volume);
            }
        }

        response.setVolumes(volumes);
        return response;
    }

    /**
     * Extracts volume
     *
     * @param volumeId The ID of the volume
     * @param extractMode The mode of extraction - HTTP_DOWNLOAD or FTP_UPLOAD
     * @param zoneId The ID of the zone where the volume is located
     * @param optional
     * @return
     * @throws Exception
     */
    public ExtractVolumeResponse extractVolume(String volumeId,
            String extractMode, String zoneId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("extractVolume", optional);
        arguments.add(new NameValuePair("id", volumeId));
        arguments.add(new NameValuePair("mode", extractMode));
        arguments.add(new NameValuePair("zoneid", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getExtractVolumeResponse(responseDocument);
    }

    /**
     * Converts XML document into ExtractVolumeResponse object
     *
     * @param doc
     * @return
     */
    private ExtractVolumeResponse getExtractVolumeResponse(Document doc) {
        ExtractVolumeResponse response = new ExtractVolumeResponse();

        // get id from XML and set as the id of extracted object
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the extracted object belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get created from XML and set as the time and date the object was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get extractId from XML and set as the upload id of extracted object
        list = doc.getElementsByTagName("extractId");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractId(node.getTextContent());
        }

        // get extractMode from XML and set as the mode of extraction - upload or download
        list = doc.getElementsByTagName("extractMode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractMode(node.getTextContent());
        }

        // get name from XML and set as the name of the extracted object
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get state from XML and set as the state of the extracted object
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get status from XML and set as the status of the extraction
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
        }

        // get storagetype from XML and set as type of the storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getTextContent());
        }

        // get uploadpercentage from XML and set as the percentage of the entity uploaded to the specified location
        list = doc.getElementsByTagName("uploadpercentage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUploadPercentage(node.getTextContent());
        }

        // get url from XML and set as if mode = upload then url of the uploaded entity. 
        // if mode = download the url from which the entity can be downloaded
        list = doc.getElementsByTagName("url");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUrl(node.getTextContent());
        }

        // get zoneid from XML and set as the zone ID the object was extracted from
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the zone name the object was extracted from
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        return response;
    }

    /**
     * Migrate volume
     *
     * @param storageId destination storage pool ID to migrate the volume to
     * @param volumeId the ID of the volume
     * @return
     * @throws Exception
     */
    public MigrateVolumeResponse migrateVolume(String storageId, String volumeId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("migrateVolume", null);
        arguments.add(new NameValuePair("storageid", storageId));
        arguments.add(new NameValuePair("volumeid", volumeId));

        Document responseDocument = server.makeRequest(arguments);

        return getMigrateVolumeResponse(responseDocument);
    }

    /**
     * Converts XML document into MigrateVolumeResponse object
     *
     * @param doc
     * @return
     */
    private MigrateVolumeResponse getMigrateVolumeResponse(Document doc) {
        MigrateVolumeResponse response = new MigrateVolumeResponse();

        // get id from XML and set as ID of the disk volume
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account associated with the disk volume
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get attached from XML and set as the date the volume was attached to a VM instance
        list = doc.getElementsByTagName("attached");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAttached(node.getTextContent());
        }

        // get created from XML and set as the date the disk volume was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // the boolean state of whether the volume is destroyed or not
        list = doc.getElementsByTagName("destroyed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestroyed(node.getTextContent());
        }

        // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
        //This tag is not returned when the volume is detached.
        list = doc.getElementsByTagName("deviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDeviceId(node.getTextContent());
        }

        // get bytes read rate of the disk volume
        list = doc.getElementsByTagName("diskBytesReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesReadRate(node.getTextContent());
        }

        // get bytes write rate of the disk volume
        list = doc.getElementsByTagName("diskBytesWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesWriteRate(node.getTextContent());
        }

        // get io requests read rate of the disk volume
        list = doc.getElementsByTagName("diskIopsReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsReadRate(node.getTextContent());
        }

        // get io requests write rate of the disk volume
        list = doc.getElementsByTagName("diskIopsWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsWriteRate(node.getTextContent());
        }

        // get diskofferingdisplaytext from XML and set as the display text of the disk offering
        list = doc.getElementsByTagName("diskofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingDisplayText(node.getTextContent());
        }

        // get diskofferingid from XML and set as the ID of the disk offering
        list = doc.getElementsByTagName("diskofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingId(node.getTextContent());
        }

        // get diskofferingname from XML and set as the name of the disk offering
        list = doc.getElementsByTagName("diskofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingName(node.getTextContent());
        }

        // an optional field whether to the display the volume to the end user or not
        list = doc.getElementsByTagName("displayvolume");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVolume(node.getTextContent());
        }

        // get domain from XML and set as the domain associated with the disk volume
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the ID of the domain associated with the disk volume
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get hypervisor from XML and set as the Hypervisor the volume belongs to 
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the volume is extractable, false otherwise
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get max iops of the disk volume
        list = doc.getElementsByTagName("maxiops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxIops(node.getTextContent());
        }

        // get min iops of the disk volume
        list = doc.getElementsByTagName("miniops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMinIops(node.getTextContent());
        }

        // get name of the disk volume
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get serviceofferingdisplaytext from XML and set as the display text of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingDisplayText(node.getTextContent());
        }

        // get serviceofferingid from XML and set as the ID of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set as the name of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get size from XML and set as the size of the disk volume
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSize(node.getTextContent());
        }

        // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
        list = doc.getElementsByTagName("snapshotid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotId(node.getTextContent());
        }

        // get state from XML and set as the state of the disk volume
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get state from XML and set as the status of the disk volume
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
        }

        // get storage from XML and set as the name of the primary storage hosting the disk volume
        list = doc.getElementsByTagName("storage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorage(node.getTextContent());
        }

        // get storagetype from XML and set as the shared or local storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getTextContent());
        }

        // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get virtualmachineid from XML and set as the id of the virtual machine
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get vmdisplayname from XML and set as the display name of the virtual machine
        list = doc.getElementsByTagName("vmdisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmDisplayName(node.getTextContent());
        }

        // get vmname from XML and set as the name of the virtual machine
        list = doc.getElementsByTagName("vmname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmName(node.getTextContent());
        }

        // get vmstate from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("vmstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmState(node.getTextContent());
        }

        // get zoneid from XML and set as the ID of the availability zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the name of the availability zone
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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

        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;
    }

    /**
     * Create a volume
     *
     * @param aggregateName aggregate name.
     * @param ipAddress ip address.
     * @param password password.
     * @param poolName pool name.
     * @param volumeSize volume size.
     * @param userName user name.
     * @param volumeName volume name.
     * @param optional
     * @throws Exception
     */
    public void createVolumeOnFiler(String aggregateName,
            String ipAddress, String password, String poolName,
            String volumeSize, String userName, String volumeName,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createVolumeOnFiler", optional);
        arguments.add(new NameValuePair("aggregatename", aggregateName));
        arguments.add(new NameValuePair("ipaddress", ipAddress));
        arguments.add(new NameValuePair("password", password));
        arguments.add(new NameValuePair("poolname", poolName));
        arguments.add(new NameValuePair("size", volumeSize));
        arguments.add(new NameValuePair("username", userName));
        arguments.add(new NameValuePair("volumename", volumeName));

    }

    /**
     * Destroy a Volume
     *
     * @param aggregateName aggregate name.
     * @param ipAddress ip address.
     * @param volumeName volume name.
     * @param optional
     * @throws Exception
     */
    public void destroyVolumeOnFiler(String aggregateName, String ipAddress, String volumeName,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("destroyVolumeOnFiler", optional);
        arguments.add(new NameValuePair("aggregatename", aggregateName));
        arguments.add(new NameValuePair("ipaddress", ipAddress));
        arguments.add(new NameValuePair("volumename", volumeName));

    }

    /**
     * List Volumes
     *
     * @param poolName pool name.
     * @return
     * @throws Exception
     */
    public ListVolumesOnFilerResponse listVolumesOnFiler(String poolName)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("migrateVolume", null);
        arguments.add(new NameValuePair("poolname", poolName));

        Document responseDocument = server.makeRequest(arguments);

        return getListVolumesOnFilerResponse(responseDocument);
    }

    /**
     * Converts XML document into ListVolumesOnFilerResponse object
     *
     * @param doc
     * @return
     */
    private ListVolumesOnFilerResponse getListVolumesOnFilerResponse(Document doc) {
        ListVolumesOnFilerResponse response = new ListVolumesOnFilerResponse();

        // get id from XML and set as the volume id
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeId(node.getTextContent());
        }

        // get aggregatename from XML and set as the Aggregate name
        list = doc.getElementsByTagName("aggregatename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAggregateName(node.getTextContent());
        }

        // get ipaddress from XML and set as the ip address
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get poolname from XML and set as the pool name
        list = doc.getElementsByTagName("poolname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPoolName(node.getTextContent());
        }

        // get size from XML and set as the volume size
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeSize(node.getTextContent());
        }

        // get snapshotpolicy from XML and set as the snapshot policy
        list = doc.getElementsByTagName("snapshotpolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapShotPolicy(node.getTextContent());
        }

        // get snapshotreservation from XML and set as the snapshot reservation
        list = doc.getElementsByTagName("snapshotreservation");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapShotReservation(node.getTextContent());
        }

        // get volumename from XML and set as the Volume name
        list = doc.getElementsByTagName("volumename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeName(node.getTextContent());
        }

        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for volume.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public VolumeJobResultResponse volumeJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getVolumeJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into VolumeJobResultResponse object
     *
     * @param doc
     * @return
     */
    private VolumeJobResultResponse getVolumeJobResultResponse(Document doc) {
        VolumeJobResultResponse response = new VolumeJobResultResponse();

        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousCmd(node.getTextContent());
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobProgressStatus(node.getTextContent());
        }
        
        // get jobstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobStatus(node.getTextContent());
        }

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            NodeList node = list.item(0).getChildNodes();
            for (int index = 0; index < node.getLength(); index++) {
                Node nodeProperty = node.item(index);
                if (nodeProperty.getNodeName().equals("volume")) {
                    List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("attached")) {
                            response.setAttached(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("created")) {
                            response.setCreated(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("desroyed")) {
                            response.setDestroyed(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("deviceid")) {
                            response.setDeviceId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("diskBytesReadRate")) {
                            response.setDiskBytesReadRate(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("diskBytesWriteRate")) {
                            response.setDiskBytesWriteRate(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("diskIopsReadRate")) {
                            response.setDiskIopsReadRate(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("diskIopsWriteRate")) {
                            response.setDiskIopsWriteRate(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("diskofferingdisplaytext")) {
                            response.setDiskOfferingDisplayText(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("diskofferingid")) {
                            response.setDiskOfferingId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("diskofferingname")) {
                            response.setDiskOfferingName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hypervisor")) {
                            response.setHypervisor(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("isextractable")) {
                            response.setIsExtractable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("maxiops")) {
                            response.setMaxIops(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("miniops")) {
                            response.setMinIops(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("serviceofferingdisplaytext")) {
                            response.setServiceOfferingDisplayText(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("serviceofferingid")) {
                            response.setServiceOfferingId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("serviceofferingname")) {
                            response.setServiceOfferingName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("size")) {
                            response.setSize(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("snapshotid")) {
                            response.setSnapShotId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("status")) {
                            response.setStatus(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("storage")) {
                            response.setStorage(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("storagetype")) {
                            response.setStorageType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("type")) {
                            response.setType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("virtualmachineid")) {
                            response.setVirtualMachineId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmdisplayname")) {
                            response.setVmDisplayName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmname")) {
                            response.setVmName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmstate")) {
                            response.setVmState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zonename")) {
                            response.setZoneName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("jobid")) {
                            response.setJobId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("jobstatus")) {
                            response.setJobStatus(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("tags")) {
                            NodeList tagsProperties = childNodeProperty.getChildNodes();
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
                            response.setTagss(tagss);
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
            response.setAsynchronousJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * Updates the Volume.
     *
     * @param diskVolumeId The ID of the disk volume
     * @param path The url path of the volume
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateVolumeResponse updateVolume(String diskVolumeId,
            String path, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("attachVolume", optional);
        arguments.add(new NameValuePair("id", diskVolumeId));
        arguments.add(new NameValuePair("path", path));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateVolumeResponse(responseDocument);
    }

    /**
     * Converts XML document into AttachVolumeResponse object
     *
     * @param doc
     * @return
     */
    private UpdateVolumeResponse getUpdateVolumeResponse(Document doc) {
        UpdateVolumeResponse response = new UpdateVolumeResponse();

        // get id from XML and set as ID of the disk volume
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account associated with the disk volume
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get attached from XML and set as the date the volume was attached to a VM instance
        list = doc.getElementsByTagName("attached");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAttached(node.getTextContent());
        }

        // get created from XML and set as the date the disk volume was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // the boolean state of whether the volume is destroyed or not
        list = doc.getElementsByTagName("destroyed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestroyed(node.getTextContent());
        }

        // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
        //This tag is not returned when the volume is detached.
        list = doc.getElementsByTagName("deviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDeviceId(node.getTextContent());
        }

        // get bytes read rate of the disk volume
        list = doc.getElementsByTagName("diskBytesReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesReadRate(node.getTextContent());
        }

        // get bytes write rate of the disk volume
        list = doc.getElementsByTagName("diskBytesWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesWriteRate(node.getTextContent());
        }

        // get io requests read rate of the disk volume
        list = doc.getElementsByTagName("diskIopsReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsReadRate(node.getTextContent());
        }

        // get io requests write rate of the disk volume
        list = doc.getElementsByTagName("diskIopsWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsWriteRate(node.getTextContent());
        }

        // get diskofferingdisplaytext from XML and set as the display text of the disk offering
        list = doc.getElementsByTagName("diskofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingDisplayText(node.getTextContent());
        }

        // get diskofferingid from XML and set as the ID of the disk offering
        list = doc.getElementsByTagName("diskofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingId(node.getTextContent());
        }

        // get diskofferingname from XML and set as the name of the disk offering
        list = doc.getElementsByTagName("diskofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingName(node.getTextContent());
        }

        // an optional field whether to the display the volume to the end user or not
        list = doc.getElementsByTagName("displayvolume");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVolume(node.getTextContent());
        }

        // get domain from XML and set as the domain associated with the disk volume
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the ID of the domain associated with the disk volume
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get hypervisor from XML and set as the Hypervisor the volume belongs to 
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the volume is extractable, false otherwise
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get max iops of the disk volume
        list = doc.getElementsByTagName("maxiops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxIops(node.getTextContent());
        }

        // get min iops of the disk volume
        list = doc.getElementsByTagName("miniops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMinIops(node.getTextContent());
        }

        // get name of the disk volume
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get serviceofferingdisplaytext from XML and set as the display text of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingdisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingDisplayText(node.getTextContent());
        }

        // get serviceofferingid from XML and set as the ID of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get serviceofferingname from XML and set as the name of the service offering for root disk
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }

        // get size from XML and set as the size of the disk volume
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSize(node.getTextContent());
        }

        // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
        list = doc.getElementsByTagName("snapshotid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSnapshotId(node.getTextContent());
        }

        // get state from XML and set as the state of the disk volume
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get state from XML and set as the status of the disk volume
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
        }

        // get storage from XML and set as the name of the primary storage hosting the disk volume
        list = doc.getElementsByTagName("storage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorage(node.getTextContent());
        }

        // get storagetype from XML and set as the shared or local storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getTextContent());
        }

        // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get virtualmachineid from XML and set as the id of the virtual machine
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get vmdisplayname from XML and set as the display name of the virtual machine
        list = doc.getElementsByTagName("vmdisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmDisplayName(node.getTextContent());
        }

        // get vmname from XML and set as the name of the virtual machine
        list = doc.getElementsByTagName("vmname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmName(node.getTextContent());
        }

        // get vmstate from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("vmstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmState(node.getTextContent());
        }

        // get zoneid from XML and set as the ID of the availability zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the name of the availability zone
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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

        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;

    }
}
