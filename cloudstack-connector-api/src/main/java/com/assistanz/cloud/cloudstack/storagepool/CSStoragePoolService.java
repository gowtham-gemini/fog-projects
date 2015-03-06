package com.assistanz.cloud.cloudstack.storagepool;

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
public class CSStoragePoolService {

    private CloudStackServer server;

    public CSStoragePoolService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Lists storage pools.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListStoragePoolsResponse listStoragePools(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listStoragePools", optional);

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
                        storagePool.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("capacityiops")) {
                        storagePool.setCapacityIops(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                        storagePool.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                        storagePool.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        storagePool.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizeallocated")) {
                        storagePool.setDiskSizeAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizetotal")) {
                        storagePool.setDiskSizeTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizeused")) {
                        storagePool.setDiskSizeUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                        storagePool.setHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        storagePool.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        storagePool.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("path")) {
                        storagePool.setPath(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        storagePool.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        storagePool.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("scope")) {
                        storagePool.setScope(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        storagePool.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("suitableformigration")) {
                        storagePool.setSuitableForMigration(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        storagePool.setTags(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        storagePool.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        storagePool.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        storagePool.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("jobid")) {
                        storagePool.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        storagePool.setJobStatus(property.getTextContent());
                    }
                }
                storagePools.add(storagePool);
            }
        }
        response.setStoragePools(storagePools);
        return response;

    }

    /**
     * Lists storage providers.
     *
     * @param storageProviderType the type of storage provider
     * @param optional
     * @return
     * @throws Exception
     */
    public ListStorageProvidersResponse listStorageProviders(String storageProviderType,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listStorageProviders", optional);
        arguments.add(new NameValuePair("type", storageProviderType));

        Document responseDocument = server.makeRequest(arguments);

        return getListStorageProvidersResponse(responseDocument);
    }

    /**
     * Converts XML document into ListStorageProvidersResponse object
     *
     * @param doc
     * @return
     */
    private ListStorageProvidersResponse getListStorageProvidersResponse(Document doc) {
        ListStorageProvidersResponse response = new ListStorageProvidersResponse();

        NodeList list = doc.getElementsByTagName("dataStoreProvider");
        List<StorageProviderResponse> storageProviders = new LinkedList<StorageProviderResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node storageProviderNode = list.item(index);
                StorageProviderResponse storageProvider = new StorageProviderResponse();
                NodeList storageProviderProperties = storageProviderNode.getChildNodes();
                for (int childIndex = 0; childIndex < storageProviderProperties.getLength(); childIndex++) {
                    Node property = storageProviderProperties.item(childIndex);
                    if (property.getNodeName().equals("name")) {
                        storageProvider.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        storageProvider.setType(property.getTextContent());
                    }
                }
                storageProviders.add(storageProvider);
            }
        }
        response.setStorageProviders(storageProviders);
        return response;

    }

    /**
     * Creates a storage pool
     *
     * @param storagePoolName the name for the storage pool
     * @param storagePoolUrl the URL of the storage pool
     * @param zoneId the Zone ID for the storage pool
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateStoragePoolResponse createStoragePool(String storagePoolName,
            String storagePoolUrl, String zoneId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("cancelStoragePool", optional);
        arguments.add(new NameValuePair("name", storagePoolName));
        arguments.add(new NameValuePair("url", storagePoolUrl));
        arguments.add(new NameValuePair("zoneid", zoneId));

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
            response.setId(node.getTextContent());
        }

        // get capacityiops from XML and set as the ID of the cluster for the storage pool
        list = doc.getElementsByTagName("capacityiops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapacityIops(node.getTextContent());
        }

        // get clusterid from XML and set as the ID of the cluster for the storage pool
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the name of the cluster for the storage pool
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get created from XML and set as the date and time the storage pool was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the storage pool
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get disksizeused from XML and set as the host's currently used disk size
        list = doc.getElementsByTagName("disksizeused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeUsed(node.getTextContent());
        }

        // get hypervisor from XML and set as the hypervisor type of the storage pool
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the storage pool
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get name from XML and set as the name of the storage pool
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get path from XML and set as the storage pool path
        list = doc.getElementsByTagName("path");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPath(node.getTextContent());
        }

        // get podid from XML and set as the Pod ID of the storage pool
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the storage pool
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get scope from XML and set as the scope for the storage pool
        list = doc.getElementsByTagName("scope");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScope(node.getTextContent());
        }

        // get state from XML and set as the state of the storage pool
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this pool is suitable to migrate a volume, false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }

        // get tags from XML and set as the tags for the storage pool
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get type from XML and set as the storage pool type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get zoneid from XML and set as the Zone ID of the storage pool
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the storage pool
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
     * Updates a storage pool
     *
     * @param storagePoolId the Id of the storage pool
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateStoragePoolResponse updateStoragePool(String storagePoolId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateStoragePool", optional);
        arguments.add(new NameValuePair("id", storagePoolId));

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
            response.setId(node.getTextContent());
        }

        // get capacityiops from XML and set as the ID of the cluster for the storage pool
        list = doc.getElementsByTagName("capacityiops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapacityIops(node.getTextContent());
        }

        // get clusterid from XML and set as the ID of the cluster for the storage pool
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the name of the cluster for the storage pool
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get created from XML and set as the date and time the storage pool was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the storage pool
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get disksizeused from XML and set as the host's currently used disk size
        list = doc.getElementsByTagName("disksizeused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeUsed(node.getTextContent());
        }

        // get hypervisor from XML and set as the hypervisor type of the storage pool
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the storage pool
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get name from XML and set as the name of the storage pool
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get path from XML and set as the storage pool path
        list = doc.getElementsByTagName("path");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPath(node.getTextContent());
        }

        // get podid from XML and set as the Pod ID of the storage pool
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the storage pool
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get scope from XML and set as the scope for the storage pool
        list = doc.getElementsByTagName("scope");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScope(node.getTextContent());
        }

        // get state from XML and set as the state of the storage pool
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this pool is suitable to migrate a volume, false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }

        // get tags from XML and set as the tags for the storage pool
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get type from XML and set as the storage pool type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get zoneid from XML and set as the Zone ID of the storage pool
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the storage pool
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
     * Deletes a storage pool
     *
     * @param storagePoolId Storage pool id
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteStoragePoolResponse deleteStoragePool(String storagePoolId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteStoragePool", null);
        arguments.add(new NameValuePair("id", storagePoolId));

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

        // get displaytext from XML and set Any text associated with the success or failure 
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
     * Puts storage pool into maintenance state
     *
     * @param primaryStorageId The Primary storage pool id
     * @return
     * @throws Exception
     */
    public EnableStorageMaintenanceResponse enableStorageMaintenance(String primaryStorageId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("enableStorageMaintenance", null);
        arguments.add(new NameValuePair("id", primaryStorageId));

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
            response.setId(node.getTextContent());
        }

        // get capacityiops from XML and set as the ID of the cluster for the storage pool
        list = doc.getElementsByTagName("capacityiops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapacityIops(node.getTextContent());
        }

        // get clusterid from XML and set as the ID of the cluster for the storage pool
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the name of the cluster for the storage pool
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get created from XML and set as the date and time the storage pool was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the storage pool
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get disksizeused from XML and set as the host's currently used disk size
        list = doc.getElementsByTagName("disksizeused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeUsed(node.getTextContent());
        }

        // get hypervisor from XML and set as the hypervisor type of the storage pool
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the storage pool
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get name from XML and set as the name of the storage pool
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get path from XML and set as the storage pool path
        list = doc.getElementsByTagName("path");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPath(node.getTextContent());
        }

        // get podid from XML and set as the Pod ID of the storage pool
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the storage pool
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get scope from XML and set as the scope for the storage pool
        list = doc.getElementsByTagName("scope");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScope(node.getTextContent());
        }

        // get state from XML and set as the state of the storage pool
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this pool is suitable to migrate a volume, false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }

        // get tags from XML and set as the tags for the storage pool
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get type from XML and set as the storage pool type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get zoneid from XML and set as the Zone ID of the storage pool
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the storage pool
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
     * Cancels maintenance for primary storage
     *
     * @param primaryStorageId The primary storage ID
     * @return
     * @throws Exception
     */
    public CancelStorageMaintenanceResponse cancelStorageMaintenance(String primaryStorageId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("cancelStorageMaintenance", null);
        arguments.add(new NameValuePair("id", primaryStorageId));

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
            response.setId(node.getTextContent());
        }

        // get capacityiops from XML and set as the ID of the cluster for the storage pool
        list = doc.getElementsByTagName("capacityiops");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapacityIops(node.getTextContent());
        }

        // get clusterid from XML and set as the ID of the cluster for the storage pool
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the name of the cluster for the storage pool
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get created from XML and set as the date and time the storage pool was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the storage pool
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get disksizeused from XML and set as the host's currently used disk size
        list = doc.getElementsByTagName("disksizeused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeUsed(node.getTextContent());
        }

        // get hypervisor from XML and set as the hypervisor type of the storage pool
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the storage pool
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get name from XML and set as the name of the storage pool
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get path from XML and set as the storage pool path
        list = doc.getElementsByTagName("path");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPath(node.getTextContent());
        }

        // get podid from XML and set as the Pod ID of the storage pool
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the storage pool
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get scope from XML and set as the scope for the storage pool
        list = doc.getElementsByTagName("scope");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScope(node.getTextContent());
        }

        // get state from XML and set as the state of the storage pool
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this pool is suitable to migrate a volume, false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }

        // get tags from XML and set as the tags for the storage pool
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get type from XML and set as the storage pool type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get zoneid from XML and set as the Zone ID of the storage pool
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the storage pool
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
     * Lists storage pools available for migration of a volume.
     *
     * @param volumeId the ID of the volume
     * @param optional
     * @return
     * @throws Exception
     */
    public FindStoragePoolsForMigrationResponse findStoragePoolsForMigration(String volumeId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listStoragePools", optional);
        arguments.add(new NameValuePair("id", volumeId));

        Document responseDocument = server.makeRequest(arguments);

        return getFindStoragePoolsForMigrationResponse(responseDocument);
    }

    /**
     * Converts XML document into FindStoragePoolsForMigrationResponse object
     *
     * @param doc
     * @return
     */
    private FindStoragePoolsForMigrationResponse getFindStoragePoolsForMigrationResponse(Document doc) {
        FindStoragePoolsForMigrationResponse response = new FindStoragePoolsForMigrationResponse();

        NodeList list = doc.getElementsByTagName("storagepoolformigration");
        List<FindStoragePoolForMigrationResponse> findStoragePoolForMigrations
                = new LinkedList<FindStoragePoolForMigrationResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node findStoragePoolForMigrationNode = list.item(index);
                FindStoragePoolForMigrationResponse findStoragePoolForMigration = new FindStoragePoolForMigrationResponse();
                NodeList findStoragePoolForMigrationProperties = findStoragePoolForMigrationNode.getChildNodes();
                for (int childIndex = 0; childIndex < findStoragePoolForMigrationProperties.getLength(); childIndex++) {
                    Node property = findStoragePoolForMigrationProperties.item(childIndex);
                    if (property.getNodeName().equals("id")) {
                        findStoragePoolForMigration.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("capacityiops")) {
                        findStoragePoolForMigration.setCapacityIops(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                        findStoragePoolForMigration.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                        findStoragePoolForMigration.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        findStoragePoolForMigration.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizeallocated")) {
                        findStoragePoolForMigration.setDiskSizeAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizetotal")) {
                        findStoragePoolForMigration.setDiskSizeTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizeused")) {
                        findStoragePoolForMigration.setDiskSizeUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                        findStoragePoolForMigration.setHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        findStoragePoolForMigration.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        findStoragePoolForMigration.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("path")) {
                        findStoragePoolForMigration.setPath(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        findStoragePoolForMigration.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        findStoragePoolForMigration.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("scope")) {
                        findStoragePoolForMigration.setScope(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        findStoragePoolForMigration.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("suitableformigration")) {
                        findStoragePoolForMigration.setSuitableForMigration(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        findStoragePoolForMigration.setTags(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        findStoragePoolForMigration.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        findStoragePoolForMigration.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        findStoragePoolForMigration.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("jobid")) {
                        findStoragePoolForMigration.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        findStoragePoolForMigration.setJobStatus(property.getTextContent());
                    }
                }
                findStoragePoolForMigrations.add(findStoragePoolForMigration);
            }
        }
        response.setFindStoragePoolForMigrations(findStoragePoolForMigrations);
        return response;

    }

}
