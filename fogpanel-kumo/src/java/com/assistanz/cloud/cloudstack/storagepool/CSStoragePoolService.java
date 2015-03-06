package com.assistanz.cloud.cloudstack.storagepool;


import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assistanz.cloud.cloudstack.CloudStackServer;



/**
 * 
 * @author Gowtham
 *
 */
public class CSStoragePoolService {
	
private CloudStackServer server;
	
	public CSStoragePoolService(CloudStackServer server) {
        this.server = server;
	}
	
	/**
	 * Puts storage pool into maintenance state
	 * 
	 * @param primaryStorageId The Primary storage pool id
	 * @return
	 * @throws Exception
	 */
	public EnableStorageMaintenanceResponse enableStorageMaintenance(String primaryStorageId) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("enableStorageMaintenance", null);
	    arguments.add(new NameValuePair("id",  primaryStorageId));
	    
	    
	    Document responseDocument = server.makeRequest(arguments);

	    return getEnableStorageMaintenanceResponse(responseDocument);
	}
	
    /**
     * Converts XML document into EnableStorageMaintenanceResponse object
     * 
     * @param doc
     * @return 
     */
    private EnableStorageMaintenanceResponse getEnableStorageMaintenanceResponse(Document doc) {
    	EnableStorageMaintenanceResponse response = new EnableStorageMaintenanceResponse();
        
	    // get id from XML and set as the ID of the storage pool
	    NodeList list = doc.getElementsByTagName("id");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolId(node.getNodeValue());
	    }
	    
	    // get clusterid from XML and set as the ID of the cluster for the storage pool
	    list = doc.getElementsByTagName("clusterid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setClusterId(node.getNodeValue());
	    }
	    
	    // get clusterid from XML and set as the ID of the cluster for the storage pool
	    list = doc.getElementsByTagName("clusterid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setClusterId(node.getNodeValue());
	    }
	    
	    // get clustername from XML and set as the name of the cluster for the storage pool
	    list = doc.getElementsByTagName("clustername");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setClusterName(node.getNodeValue());
	    }
	    
	    // get created from XML and set as the date and time the storage pool was created
	    list = doc.getElementsByTagName("created");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setCreated(node.getNodeValue());
	    }
	    
	    // get disksizeallocated from XML and set as the host's currently allocated disk size
	    list = doc.getElementsByTagName("disksizeallocated");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDiskSizeAllocated(node.getNodeValue());
	    }
	    
	    // get disksizetotal from XML and set as the total disk size of the storage pool
	    list = doc.getElementsByTagName("disksizetotal");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDiskSizeTotal(node.getNodeValue());
	    }
	    
	    // get disksizeused from XML and set as the host's currently used disk size
	    list = doc.getElementsByTagName("disksizeused");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDiskSizeUsed(node.getNodeValue());
	    }
	    
	    // get ipaddress from XML and set as the IP address of the storage pool
	    list = doc.getElementsByTagName("ipaddress");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolIpAddress(node.getNodeValue());
	    }
	    
	    // get name from XML and set as the name of the storage pool
	    list = doc.getElementsByTagName("name");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolName(node.getNodeValue());
	    }
	    
	    // get path from XML and set as the storage pool path
	    list = doc.getElementsByTagName("path");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolPath(node.getNodeValue());
	    }
	    
	    // get podid from XML and set as the Pod ID of the storage pool
	    list = doc.getElementsByTagName("podid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setPodId(node.getNodeValue());
	    }
	    
	    // get podname from XML and set as the Pod name of the storage pool
	    list = doc.getElementsByTagName("podname");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setPodName(node.getNodeValue());
	    }
	    
	    // get state from XML and set as the state of the storage pool
	    list = doc.getElementsByTagName("state");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolState(node.getNodeValue());
	    }
	    
	    // get tags from XML and set as the tags for the storage pool
	    list = doc.getElementsByTagName("tags");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolTags(node.getNodeValue());
	    }
	    
	    // get type from XML and set as the storage pool type
	    list = doc.getElementsByTagName("type");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolType(node.getNodeValue());
	    }
	    
	    // get zoneid from XML and set as the Zone ID of the storage pool
	    list = doc.getElementsByTagName("zoneid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolZoneId(node.getNodeValue());
	    }
	    
	    // get zonename from XML and set as the Zone name of the storage pool
	    list = doc.getElementsByTagName("zonename");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolZoneName(node.getNodeValue());
	    }
	    
	    // get jobid from XML and set as the ID of the latest async job acting on this object
	    list = doc.getElementsByTagName("jobid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobId(node.getNodeValue());
	    }
	    
	    // get jobstatus from XML and set as the current status of the latest async job acting on this object
	    list = doc.getElementsByTagName("jobstatus");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobStatus(node.getNodeValue());
	    }
        
       return response;
    }
	    
    
    /**
     * Cancels maintenance for primary storage
     * 
     * @param primaryStorageId The primary storage ID
     * @return
     * @throws Exception
     */
	public CancelStorageMaintenanceResponse cancelStorageMaintenance(String primaryStorageId) 
			throws Exception {
	
		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("cancelStorageMaintenance", null);
		arguments.add(new NameValuePair("id",  primaryStorageId));
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getCancelStorageMaintenanceResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into CancelStorageMaintenanceResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private CancelStorageMaintenanceResponse getCancelStorageMaintenanceResponse(Document doc) {
		CancelStorageMaintenanceResponse response = new CancelStorageMaintenanceResponse();
	
	    // get id from XML and set as the ID of the storage pool
	    NodeList list = doc.getElementsByTagName("id");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolId(node.getNodeValue());
	    }
	    
	    // get clusterid from XML and set as the ID of the cluster for the storage pool
	    list = doc.getElementsByTagName("clusterid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setClusterId(node.getNodeValue());
	    }
	    
	    // get clusterid from XML and set as the ID of the cluster for the storage pool
	    list = doc.getElementsByTagName("clusterid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setClusterId(node.getNodeValue());
	    }
	    
	    // get clustername from XML and set as the name of the cluster for the storage pool
	    list = doc.getElementsByTagName("clustername");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setClusterName(node.getNodeValue());
	    }
	    
	    // get created from XML and set as the date and time the storage pool was created
	    list = doc.getElementsByTagName("created");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setCreated(node.getNodeValue());
	    }
	    
	    // get disksizeallocated from XML and set as the host's currently allocated disk size
	    list = doc.getElementsByTagName("disksizeallocated");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDiskSizeAllocated(node.getNodeValue());
	    }
	    
	    // get disksizetotal from XML and set as the total disk size of the storage pool
	    list = doc.getElementsByTagName("disksizetotal");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDiskSizeTotal(node.getNodeValue());
	    }
	    
	    // get disksizeused from XML and set as the host's currently used disk size
	    list = doc.getElementsByTagName("disksizeused");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDiskSizeUsed(node.getNodeValue());
	    }
	    
	    // get ipaddress from XML and set as the IP address of the storage pool
	    list = doc.getElementsByTagName("ipaddress");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolIpAddress(node.getNodeValue());
	    }
	    
	    // get name from XML and set as the name of the storage pool
	    list = doc.getElementsByTagName("name");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolName(node.getNodeValue());
	    }
	    
	    // get path from XML and set as the storage pool path
	    list = doc.getElementsByTagName("path");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolPath(node.getNodeValue());
	    }
	    
	    // get podid from XML and set as the Pod ID of the storage pool
	    list = doc.getElementsByTagName("podid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setPodId(node.getNodeValue());
	    }
	    
	    // get podname from XML and set as the Pod name of the storage pool
	    list = doc.getElementsByTagName("podname");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setPodName(node.getNodeValue());
	    }
	    
	    // get state from XML and set as the state of the storage pool
	    list = doc.getElementsByTagName("state");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolState(node.getNodeValue());
	    }
	    
	    // get tags from XML and set as the tags for the storage pool
	    list = doc.getElementsByTagName("tags");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolTags(node.getNodeValue());
	    }
	    
	    // get type from XML and set as the storage pool type
	    list = doc.getElementsByTagName("type");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolType(node.getNodeValue());
	    }
	    
	    // get zoneid from XML and set as the Zone ID of the storage pool
	    list = doc.getElementsByTagName("zoneid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolZoneId(node.getNodeValue());
	    }
	    
	    // get zonename from XML and set as the Zone name of the storage pool
	    list = doc.getElementsByTagName("zonename");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setStoragePoolZoneName(node.getNodeValue());
	    }
	    
	    // get jobid from XML and set as the ID of the latest async job acting on this object
	    list = doc.getElementsByTagName("jobid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobId(node.getNodeValue());
	    }
	    
	    // get jobstatus from XML and set as the current status of the latest async job acting on this object
	    list = doc.getElementsByTagName("jobstatus");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobStatus(node.getNodeValue());
	    }
		
		return response;
	}
    
    

}
