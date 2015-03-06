package com.assistanz.cloud.cloudstack.volume;

import java.util.HashMap;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
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
                    String virtualMachineId, HashMap<String,String> optional) 
                                    throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("attachVolume", optional);
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
            response.setDiskVolumeId(node.getTextContent());
        }
        
        // get account from XML and set as the account associated with the disk volume
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeAccount(node.getTextContent());
        }
        
        // get attached from XML and set as the date the volume was attached to a VM instance
        list = doc.getElementsByTagName("attached");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeAttachedDate(node.getTextContent());
        }
        
        // get created from XML and set as the date the disk volume was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeCreatedDate(node.getTextContent());
        }
        
        // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
        //This tag is not returned when the volume is detached.
        list = doc.getElementsByTagName("deviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDeviceId(node.getTextContent());
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
        
        // get domain from XML and set as the domain associated with the disk volume
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDomain(node.getTextContent());
        }
        
        // get domainid from XML and set as the ID of the domain associated with the disk volume
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDomainId(node.getTextContent());
        }
        
        // get hypervisor from XML and set as the Hypervisor the volume belongs to 
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeHypervisor(node.getTextContent());
        }
        
        // get isextractable from XML and set as true if the volume is extractable, false otherwise
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeIsExtractable(node.getTextContent());
        }
        
        // get name from XML and set as the name of the disk volume
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeName(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeProjectId(node.getTextContent());
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
            response.setDiskVolumeSize(node.getTextContent());
        }
        
        // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
        list = doc.getElementsByTagName("snapshotid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeSnapShotId(node.getTextContent());
        }
        
        // get state from XML and set as the state of the disk volume
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeState(node.getTextContent());
        }
        
        // get state from XML and set as the status of the disk volume
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeStatus(node.getTextContent());
        }
        
        // get storage from XML and set as the name of the primary storage hosting the disk volume
        list = doc.getElementsByTagName("storage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeStorage(node.getTextContent());
        }
        
        // get storagetype from XML and set as the shared or local storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeStorageType(node.getTextContent());
        }
        
        // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeType(node.getTextContent());
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
            response.setVirtualMachineName(node.getTextContent());
        }
        
        // get vmname from XML and set as the name of the virtual machine
        list = doc.getElementsByTagName("vmname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineName(node.getTextContent());
        }
        
        // get vmstate from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("vmstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineState(node.getTextContent());
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
        
        return response;
            
    }
    
    /**
     * Resize a disk volume from a virtual machine
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public  ResizeVolumeResponse resizeVolume(HashMap<String, String> optional) throws Exception {
        LinkedList<NameValuePair> arguments =  server.getDefaultQuery("resizeVolume", optional);
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
            response.setDiskVolumeId(node.getTextContent());
        }
        
        // get account from XML and set as the account associated with the disk volume
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeAccount(node.getTextContent());
        }
        
        // get attached from XML and set as the date the volume was attached to a VM instance
        list = doc.getElementsByTagName("attached");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeAttachedDate(node.getTextContent());
        }
        
        // get created from XML and set as the date the disk volume was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeCreatedDate(node.getTextContent());
        }
        
        // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
        //This tag is not returned when the volume is detached.
        list = doc.getElementsByTagName("deviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDeviceId(node.getTextContent());
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
        
        // get domain from XML and set as the domain associated with the disk volume
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDomain(node.getTextContent());
        }
        
        // get domainid from XML and set as the ID of the domain associated with the disk volume
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDomainId(node.getTextContent());
        }
        
        // get hypervisor from XML and set as the Hypervisor the volume belongs to 
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeHypervisor(node.getTextContent());
        }
        
        // get isextractable from XML and set as true if the volume is extractable, false otherwise
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeIsExtractable(node.getTextContent());
        }
        
        // get name from XML and set as the name of the disk volume
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeName(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeProjectId(node.getTextContent());
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
            response.setDiskVolumeSize(node.getTextContent());
        }
        
        // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
        list = doc.getElementsByTagName("snapshotid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeSnapShotId(node.getTextContent());
        }
        
        // get state from XML and set as the state of the disk volume
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeState(node.getTextContent());
        }
        
        // get storage from XML and set as the name of the primary storage hosting the disk volume
        list = doc.getElementsByTagName("storage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeStorage(node.getTextContent());
        }
        
        // get storagetype from XML and set as the shared or local storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeStorageType(node.getTextContent());
        }
        
        // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeType(node.getTextContent());
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
            response.setVirtualMachineName(node.getTextContent());
        }
        
        // get vmname from XML and set as the name of the virtual machine
        list = doc.getElementsByTagName("vmname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineName(node.getTextContent());
        }
        
        // get vmstate from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("vmstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineState(node.getTextContent());
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
        
        return response;
    }
    
    
    /**
     * Detaches a disk volume from a virtual machine
     * 
     * @param optional
     * @return
     * @throws Exception
     */
	public DetachVolumeResponse detachVolume(HashMap<String,String> optional) 
					throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("detachVolume", optional);
       
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
            response.setDiskVolumeId(node.getTextContent());
        }
        
        // get account from XML and set as the account associated with the disk volume
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeAccount(node.getTextContent());
        }
        
        // get attached from XML and set as the date the volume was attached to a VM instance
        list = doc.getElementsByTagName("attached");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeAttachedDate(node.getTextContent());
        }
        
        // get created from XML and set as the date the disk volume was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeCreatedDate(node.getTextContent());
        }
        
        // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
        //This tag is not returned when the volume is detached.
        list = doc.getElementsByTagName("deviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDeviceId(node.getTextContent());
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
        
        // get domain from XML and set as the domain associated with the disk volume
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDomain(node.getTextContent());
        }
        
        // get domainid from XML and set as the ID of the domain associated with the disk volume
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDomainId(node.getTextContent());
        }
        
        // get hypervisor from XML and set as the Hypervisor the volume belongs to 
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeHypervisor(node.getTextContent());
        }
        
        // get isextractable from XML and set as true if the volume is extractable, false otherwise
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeIsExtractable(node.getTextContent());
        }
        
        // get name from XML and set as the name of the disk volume
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeName(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeProjectId(node.getTextContent());
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
            response.setDiskVolumeSize(node.getTextContent());
        }
        
        // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
        list = doc.getElementsByTagName("snapshotid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeSnapShotId(node.getTextContent());
        }
        
        // get state from XML and set as the state of the disk volume
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeState(node.getTextContent());
        }
        
        // get storage from XML and set as the name of the primary storage hosting the disk volume
        list = doc.getElementsByTagName("storage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeStorage(node.getTextContent());
        }
        
        // get storagetype from XML and set as the shared or local storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeStorageType(node.getTextContent());
        }
        
        // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeType(node.getTextContent());
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
            response.setVirtualMachineName(node.getTextContent());
        }
        
        // get vmname from XML and set as the name of the virtual machine
        list = doc.getElementsByTagName("vmname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineName(node.getTextContent());
        }
        
        // get vmstate from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("vmstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineState(node.getTextContent());
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
        
        return response;
            
    }
    
    /**
     * Creates a disk volume from a disk offering. 
     * This disk volume must still be attached to a virtual machine to make use of it.
     * 
     * @param diskVolumeName The name of the disk volume
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateVolumeResponse createVolume(String diskVolumeName, HashMap<String,String> optional)
            throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createVolume", optional);
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
            response.setDiskVolumeId(node.getTextContent());
        }
        
        // get account from XML and set as the account associated with the disk volume
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeAccount(node.getTextContent());
        }
        
        // get attached from XML and set as the date the volume was attached to a VM instance
        list = doc.getElementsByTagName("attached");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeAttachedDate(node.getTextContent());
        }
        
        // get state from XML and set as the status of the disk volume
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeStatus(node.getTextContent());
        }
        
        // get created from XML and set as the date the disk volume was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeCreatedDate(node.getTextContent());
        }
        
        // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
        //This tag is not returned when the volume is detached.
        list = doc.getElementsByTagName("deviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDeviceId(node.getTextContent());
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
        
        // get domain from XML and set as the domain associated with the disk volume
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDomain(node.getTextContent());
        }
        
        // get domainid from XML and set as the ID of the domain associated with the disk volume
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeDomainId(node.getTextContent());
        }
        
        // get hypervisor from XML and set as the Hypervisor the volume belongs to 
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVolumeHypervisor(node.getTextContent());
        }
        
        // get isextractable from XML and set as true if the volume is extractable, false otherwise
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeIsExtractable(node.getTextContent());
        }
        
        // get name from XML and set as the name of the disk volume
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeName(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the vpn
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the vpn
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeProjectId(node.getTextContent());
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
            response.setDiskVolumeSize(node.getTextContent());
        }
        
        // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
        list = doc.getElementsByTagName("snapshotid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeSnapShotId(node.getTextContent());
        }
        
        // get state from XML and set as the state of the disk volume
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeState(node.getTextContent());
        }
        
        // get storage from XML and set as the name of the primary storage hosting the disk volume
        list = doc.getElementsByTagName("storage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeStorage(node.getTextContent());
        }
        
        // get storagetype from XML and set as the shared or local storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeStorageType(node.getTextContent());
        }
        
        // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskVolumeType(node.getTextContent());
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
            response.setVirtualMachineName(node.getTextContent());
        }
        
        // get vmname from XML and set as the name of the virtual machine
        list = doc.getElementsByTagName("vmname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineName(node.getTextContent());
        }
        
        // get vmstate from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("vmstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineState(node.getTextContent());
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
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteVolume", null);
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
    public ListVolumeResponse listVolumes(HashMap<String,String> optional) 
                throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listVolumes", optional);

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

                if (volumeNode == null) {
                    continue;
                }  
                
                VolumeResponse volume = new VolumeResponse();
                        
                NodeList volumeProperties = volumeNode.getChildNodes();
                for (int childIndex = 0; childIndex < volumeProperties.getLength(); childIndex++) {
                    Node property = volumeProperties.item(childIndex);
                    
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                    
                    if (property.getNodeName().equals("id")) {
                        volume.setDiskVolumeId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                    	volume.setDiskVolumeAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("attached")) {
                    	volume.setDiskVolumeAttachedDate(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                    	volume.setDiskVolumeCreatedDate(property.getTextContent());
                    } else if (property.getNodeName().equals("destroyed")) {
                    	volume.setDiskVolumeDestroyedState(property.getTextContent());
                    } else if (property.getNodeName().equals("deviceid")) {
                    	volume.setDiskVolumeDeviceId(property.getTextContent());
                    } else if (property.getNodeName().equals("diskofferingdisplaytext")) {
                    	volume.setDiskOfferingDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("diskofferingid")) {
                    	volume.setDiskOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("diskofferingname")) {
                    	volume.setDiskOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                    	volume.setDiskVolumeDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                    	volume.setDiskVolumeDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                    	volume.setVolumeHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("isextractable")) {
                    	volume.setDiskVolumeIsExtractable(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	volume.setDiskVolumeName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                    	volume.setDiskVolumeProjectName(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                    	volume.setDiskVolumeProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingdisplaytext")) {
                    	volume.setServiceOfferingDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingid")) {
                    	volume.setServiceOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingname")) {
                    	volume.setServiceOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("size")) {
                    	volume.setDiskVolumeSize(property.getTextContent());
                    } else if (property.getNodeName().equals("snapshotid")) {
                    	volume.setDiskVolumeSnapShotId(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                    	volume.setDiskVolumeState(property.getTextContent());
                    } else if (property.getNodeName().equals("status")) {
                    	volume.setDiskVolumeStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("storage")) {
                    	volume.setDiskVolumeStorage(property.getTextContent());
                    }else if (property.getNodeName().equals("storagetype")) {
                    	volume.setDiskVolumeStorageType(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	volume.setDiskVolumeType(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachineid")) {
                    	volume.setVirtualMachineId(property.getTextContent());
                    } else if (property.getNodeName().equals("vmdisplayname")) {
                    	volume.setVirtualMachineDisplayName(property.getTextContent());
                    } else if (property.getNodeName().equals("vmname")) {
                    	volume.setVirtualMachineName(property.getTextContent());
                    } else if (property.getNodeName().equals("vmstate")) {
                    	volume.setVirtualMachineState(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	volume.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                    	volume.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	volume.setZoneId(property.getTextContent());
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
			String extractMode, String zoneId, HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("extractVolume", optional);
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
            response.setExtractedObjectId(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the extracted object belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectaccountid(node.getTextContent());
        }
        
        // get created from XML and set as the time and date the object was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectcreated(node.getTextContent());
        }
        
        // get extractId from XML and set as the upload id of extracted object
        list = doc.getElementsByTagName("extractId");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUploadExtractedObjectId(node.getTextContent());
        }
        
        // get extractMode from XML and set as the mode of extraction - upload or download
        list = doc.getElementsByTagName("extractMode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectMode(node.getTextContent());
        }
        
        // get name from XML and set as the name of the extracted object
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectName(node.getTextContent());
        }
        
        // get state from XML and set as the state of the extracted object
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectState(node.getTextContent());
        }
        
        // get status from XML and set as the status of the extraction
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectStatus(node.getTextContent());
        }
        
        // get storagetype from XML and set as type of the storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectStorageType(node.getTextContent());
        }
        
        // get uploadpercentage from XML and set as the percentage of the entity uploaded to the specified location
        list = doc.getElementsByTagName("uploadpercentage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectUploadPercentage(node.getTextContent());
        }
        
        // get url from XML and set as if mode = upload then url of the uploaded entity. 
        // if mode = download the url from which the entity can be downloaded
        list = doc.getElementsByTagName("url");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectUrl(node.getTextContent());
        }
        
        // get zoneid from XML and set as the zone ID the object was extracted from
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectZoneid(node.getTextContent());
        }
        
        // get zonename from XML and set as the zone name the object was extracted from
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectZonename(node.getTextContent());
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

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("migrateVolume", null);
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
                response.setDiskVolumeId(node.getTextContent());
            }

            // get account from XML and set as the account associated with the disk volume
            list = doc.getElementsByTagName("account");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeAccount(node.getTextContent());
            }

            // get attached from XML and set as the date the volume was attached to a VM instance
            list = doc.getElementsByTagName("attached");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeAttachedDate(node.getTextContent());
            }

            // get created from XML and set as the date the disk volume was created
            list = doc.getElementsByTagName("created");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeCreatedDate(node.getTextContent());
            }

            // get deviceid from XML and set as the ID of the device on user vm the volume is attached to. 
            //This tag is not returned when the volume is detached.
            list = doc.getElementsByTagName("deviceid");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeDeviceId(node.getTextContent());
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

            // get domain from XML and set as the domain associated with the disk volume
            list = doc.getElementsByTagName("domain");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeDomain(node.getTextContent());
            }

            // get domainid from XML and set as the ID of the domain associated with the disk volume
            list = doc.getElementsByTagName("domainid");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeDomainId(node.getTextContent());
            }

            // get hypervisor from XML and set as the Hypervisor the volume belongs to 
            list = doc.getElementsByTagName("hypervisor");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setVolumeHypervisor(node.getTextContent());
            }

            // get isextractable from XML and set as true if the volume is extractable, false otherwise
            list = doc.getElementsByTagName("isextractable");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeIsExtractable(node.getTextContent());
            }

            // get name from XML and set as the name of the disk volume
            list = doc.getElementsByTagName("name");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeName(node.getTextContent());
            }

            // get project from XML and set as the project name of the vpn
            list = doc.getElementsByTagName("project");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeProjectName(node.getTextContent());
            }

            // get projectid from XML and set as the project id of the vpn
            list = doc.getElementsByTagName("projectid");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeProjectId(node.getTextContent());
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
                response.setDiskVolumeSize(node.getTextContent());
            }

            // get snapshotid from XML and set as the ID of the snapshot from which this volume was created
            list = doc.getElementsByTagName("snapshotid");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeSnapShotId(node.getTextContent());
            }

            // get state from XML and set as the state of the disk volume
            list = doc.getElementsByTagName("state");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeState(node.getTextContent());
            }

            // get storage from XML and set as the name of the primary storage hosting the disk volume
            list = doc.getElementsByTagName("storage");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeStorage(node.getTextContent());
            }

            // get storagetype from XML and set as the shared or local storage
            list = doc.getElementsByTagName("storagetype");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeStorageType(node.getTextContent());
            }

            // get type from XML and set as the type of the disk volume (ROOT or DATADISK)
            list = doc.getElementsByTagName("type");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDiskVolumeType(node.getTextContent());
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
                response.setVirtualMachineName(node.getTextContent());
            }

            // get vmname from XML and set as the name of the virtual machine
            list = doc.getElementsByTagName("vmname");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setVirtualMachineName(node.getTextContent());
            }

            // get vmstate from XML and set as the state of the virtual machine
            list = doc.getElementsByTagName("vmstate");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setVirtualMachineState(node.getTextContent());
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
                    HashMap<String,String> optional) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("createVolumeOnFiler", optional);
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
            HashMap<String,String> optional) 
                    throws Exception {

        LinkedList<NameValuePair> arguments = 
            server.getDefaultQuery("destroyVolumeOnFiler", optional);
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

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("migrateVolume", null);
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

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("queryAsyncJobResult", null);
            arguments.add(new NameValuePair("jobid",  asychronousJobid));

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
                if (nodeProperty.getNodeName().equals("volume")) {
                    NodeList childNodeProperties =  nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setDiskVolumeId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setDiskVolumeAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("attached")) {
                            response.setDiskVolumeAttachedDate(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("created")) {
                            response.setDiskVolumeCreatedDate(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("destroyed")) {
                            response.setDiskVolumeDestroyedState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("deviceid")) {
                            response.setDiskVolumeDeviceId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("diskofferingdisplaytext")) {
                            response.setDiskOfferingDisplayText(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("diskofferingid")) {
                            response.setDiskOfferingId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("diskofferingname")) {
                            response.setDiskOfferingName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDiskVolumeDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDiskVolumeDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hypervisor")) {
                            response.setVolumeHypervisor(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("isextractable")) {
                            response.setDiskVolumeIsExtractable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setDiskVolumeName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setDiskVolumeProjectName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setDiskVolumeProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("serviceofferingdisplaytext")) {
                            response.setServiceOfferingDisplayText(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("serviceofferingid")) {
                            response.setServiceOfferingId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("serviceofferingname")) {
                            response.setServiceOfferingName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("size")) {
                            response.setDiskVolumeSize(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("snapshotid")) {
                            response.setDiskVolumeSnapShotId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setDiskVolumeState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("status")) {
                            response.setDiskVolumeStatus(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("storage")) {
                            response.setDiskVolumeStorage(childNodeProperty.getTextContent());
                        }else if (childNodeProperty.getNodeName().equals("storagetype")) {
                            response.setDiskVolumeStorageType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("type")) {
                            response.setDiskVolumeType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("virtualmachineid")) {
                            response.setVirtualMachineId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmdisplayname")) {
                            response.setVirtualMachineDisplayName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmname")) {
                            response.setVirtualMachineName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmstate")) {
                            response.setVirtualMachineState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zonename")) {
                            response.setZoneName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("jobid")) {
                            response.setJobId(childNodeProperty.getTextContent());
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
