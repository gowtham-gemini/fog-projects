package com.assistanz.cloud.cloudstack.pool;

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
public class CSPoolService {
	
	private CloudStackServer server;
	
	public CSPoolService(CloudStackServer server) {
	    this.server = server;
	}
	
    /**
     * Lists storage pools
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public ListStoragePoolsResponse listStoragePools(HashMap<String,String> optional) 
        throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listStoragePools", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListStoragePoolsResponse(responseDocument);
    }
	
    /**
     * Converts XML document into ListStoragePoolsResponse object
     * 
     * @param doc
     * @return 
     */
    private ListStoragePoolsResponse getListStoragePoolsResponse(Document doc) {
        ListStoragePoolsResponse response = new ListStoragePoolsResponse();
	
        NodeList list = doc.getElementsByTagName("storagepool");
        List<StoragePoolResponse> storagePools = new LinkedList<StoragePoolResponse>(); 
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node storagePoolNode = list.item(index);
                StoragePoolResponse storagePool = new StoragePoolResponse();
                NodeList storagePoolProperties = storagePoolNode.getChildNodes();
                for (int childIndex = 0; childIndex < storagePoolProperties.getLength(); childIndex++) {
                    Node property = storagePoolProperties.item(childIndex);
                    if (property.getNodeName().equals("id")) {
                        storagePool.setStoragePoolId(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                    	storagePool.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                    	storagePool.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                    	storagePool.setCreated(property.getTextContent());
                    }  else if (property.getNodeName().equals("disksizeused")) {
                    	storagePool.setDiskSizeUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizeallocated")) {
                    	storagePool.setDiskSizeAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizetotal")) {
                    	storagePool.setDiskSizeTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                    	storagePool.setStoragePoolIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	storagePool.setStoragePoolName(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                    	storagePool.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                    	storagePool.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                    	storagePool.setStoragePoolState(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	storagePool.setStoragePoolType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	storagePool.setStoragePoolZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                    	storagePool.setStoragePoolZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("jobid")) {
                    	storagePool.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                    	storagePool.setJobStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                    	storagePool.setStoragePoolTags(property.getTextContent());
                    } else if (property.getNodeName().equals("path")) {
                    	storagePool.setStoragePoolPath(property.getTextContent());
                    } 
                }
                 storagePools.add(storagePool);
            }
        }
        response.setStoragePools(storagePools);
        return response;
    }
	
    /**
     * Creates a storage pool.
     * 
     * @param StoragePoolName the name for the storage pool
     * @param StoragePoolURL the URL of the storage pool
     * @param StoragePoolZoneId the Zone ID for the storage pool
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateStoragePoolResponse createStoragePool(String StoragePoolName,String StoragePoolURL, String StoragePoolZoneId,
        HashMap<String,String> optional) throws Exception {
	    
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createStoragePool", optional);
        arguments.add(new NameValuePair("name", StoragePoolName));
        arguments.add(new NameValuePair("url", StoragePoolURL));
        arguments.add(new NameValuePair("zoneid", StoragePoolZoneId));
	    
        Document responseDocument = server.makeRequest(arguments);
	
        return getCreateStoragePoolResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateStoragePoolResponse object
     * 
     * @param doc
     * @return 
     */
    private CreateStoragePoolResponse getCreateStoragePoolResponse(Document doc) {
        CreateStoragePoolResponse response = new CreateStoragePoolResponse();
		
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
     * Updates a storage pool.
     * 
     * @param StoragePoolId The Id of the storage pool
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateStoragePoolResponse updateStoragePool(String StoragePoolId, HashMap<String,String> optional) 
        throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateStoragePool", optional);
        arguments.add(new NameValuePair("id", StoragePoolId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateStoragePoolResponse(responseDocument);
    }
	
    /**
     * Converts XML document into UpdateStoragePoolResponse object
     * 
     * @param doc
     * @return 
     */
    private UpdateStoragePoolResponse getUpdateStoragePoolResponse(Document doc) {
        UpdateStoragePoolResponse response = new UpdateStoragePoolResponse();

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
     * Deletes a storage pool.
     * 
     * @param StoragePoolId The Storage pool id
     * @return
     * @throws Exception
     */
    public DeleteStoragePoolResponse deleteStoragePool(String StoragePoolId) 
        throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteStoragePool", null);
        arguments.add(new NameValuePair("id",StoragePoolId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteStoragePoolResponse(responseDocument);

    }

    /**
      * Converts XML document into DeleteStoragePoolResponse object
      * 
      * @param doc
      * @return 
      */
    private DeleteStoragePoolResponse getDeleteStoragePoolResponse(Document doc) {
        DeleteStoragePoolResponse response = new DeleteStoragePoolResponse();
	    
        // get displaytext from XML and set Any text associated with the success or 
        // failure on Deleting a StoragePool
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getNodeValue());
        }

        // get success from XML and set Return true if Delete StoragePool operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getNodeValue());
        }
	    
        return response;
    }

    public void createPool(String algorithm, String poolName) throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createPool", null);
        arguments.add(new NameValuePair("algorithm",algorithm));
        arguments.add(new NameValuePair("name",poolName));

    }
	
    public void deletePool(String poolName) throws Exception {

        LinkedList<NameValuePair> arguments = 
        server.getDefaultQuery("deletePool", null);
        arguments.add(new NameValuePair("name",poolName));

    }
	
    public void modifyPool(String algorithm, String poolName) throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("modifyPool", null);
        arguments.add(new NameValuePair("algorithm",algorithm));
        arguments.add(new NameValuePair("name",poolName));

    }
	
    /**
     * List Pool
     * 
     * @return
     */
    public ListPoolsResponse loout() {

    return getListPoolsResponse(null);
    }
	
    /**
      * Converts XML document into ListPoolsResponse object
      * 
      * @param doc
      * @return 
      */
    private ListPoolsResponse getListPoolsResponse(Document doc) {
        ListPoolsResponse response = new ListPoolsResponse();
	    
        // get id from XML and set as the pool id 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPoolId(node.getNodeValue());
        }

        // get algorithm from XML and set as pool algorithm
        list = doc.getElementsByTagName("algorithm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPoolAlgorithm(node.getNodeValue());
        }

        // get name from XML and set as pool name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPoolName(node.getNodeValue());
        }
	    
        return response;
    }
	
}
