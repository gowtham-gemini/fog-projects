package com.assistanz.cloud.cloudstack.cluster;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CapacityResponse;
import com.assistanz.cloud.cloudstack.CloudStackServer;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSClusterService {

    private CloudStackServer server;

    public CSClusterService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds a new cluster
     *
     * @param clusterName the cluster name
     * @param clusterType type of the cluster: CloudManaged, ExternalManaged
     * @param hypervisor hypervisor type of the cluster: XenServer,KVM,VMware,Hyperv,BareMetal,Simulator
     * @param podId the Pod ID for the host
     * @param zoneId the Zone ID for the cluster
     * @param optional
     * @return
     * @throws Exception
     */
    public AddClusterResponse addCluster(String clusterName, String clusterType,
            String hypervisor, String podId, String zoneId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addCluster", optional);
        arguments.add(new NameValuePair("clustername", clusterName));
        arguments.add(new NameValuePair("clustertype", clusterType));
        arguments.add(new NameValuePair("hypervisor", hypervisor));
        arguments.add(new NameValuePair("podid", podId));
        arguments.add(new NameValuePair("zoneid", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getAddClusterResponse(responseDocument);
    }

    /**
     * Converts XML document into AddClusterResponse object
     *
     * @param doc
     * @return
     */
    private AddClusterResponse getAddClusterResponse(Document doc) {
        AddClusterResponse response = new AddClusterResponse();

        // get id from XML and set as the ID of the cluster 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get allocationstate from XML and set the allocationstate of the cluster      
        list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAllocationState(node.getTextContent());
        }

        // get clustertype from XML and set the type of the cluster 
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getTextContent());
        }

        // get cpuovercommitratio from XML and set the cpu overcommit ratio of the cluster
        list = doc.getElementsByTagName("cpuovercommitratio");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuOverCommitRatio(node.getTextContent());
        }

        // get hypervisortype from XML and set as the hypervisor type of the cluster      
        list = doc.getElementsByTagName("hypervisortype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorType(node.getTextContent());
        }

        // get managedstate from XML and set whether this cluster is managed by cloudstack     
        list = doc.getElementsByTagName("managedstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setManagedState(node.getTextContent());
        }

        // get memeoryovercommitratio from XML and set the memory overcommit ratio of the cluster     
        list = doc.getElementsByTagName("memoryovercommitratio");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryOverCommitRatio(node.getTextContent());
        }

        // get name from XML and set the name of the cluster      
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get podid from XML and set the Pod ID of the cluster    
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set the Pod name of the cluster      
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get zoneid from XML and set the zone id of the cluster      
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zone name of the cluster      
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        //the list of Zone Capacity associated with Zone
        list = doc.getElementsByTagName("capacity");
        if (list.getLength() > 0) {
            List<CapacityResponse> clusterCapacitys = new LinkedList<CapacityResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node clusterCapacityNode = list.item(index);
                CapacityResponse clusterCapacity = new CapacityResponse();

                NodeList clusterCapacityProperties = clusterCapacityNode.getChildNodes();
                for (int childIndex = 0; childIndex < clusterCapacityProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("capacitytotal")) {
                        clusterCapacity.setCapacityTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("capacityused")) {
                        clusterCapacity.setCapacityUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                        clusterCapacity.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                        clusterCapacity.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("percentused")) {
                        clusterCapacity.setPercentUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        clusterCapacity.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        clusterCapacity.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        clusterCapacity.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        clusterCapacity.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        clusterCapacity.setZoneName(property.getTextContent());
                    }
                }

                clusterCapacitys.add(clusterCapacity);
                response.setClusterCapacitys(clusterCapacitys);
            }
        }

        return response;
    }

    /**
     * Deletes a cluster.
     *
     * @param clusterId the cluster ID
     * @return
     * @throws Exception
     */
    public DeleteClusterResponse deleteCluster(String clusterId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteCluster", null);
        arguments.add(new NameValuePair("id", clusterId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteClusterResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteClusterResponse object
     *
     * @param doc
     * @return
     */
    private DeleteClusterResponse getDeleteClusterResponse(Document doc) {
        DeleteClusterResponse response = new DeleteClusterResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete cluster
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        // get success from XML and any text associated with the success or failure on deleting Delete cluster
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Updates an existing cluster
     *
     * @param clusterId the ID of the Cluster
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateClusterResponse updateCluster(String clusterId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateCluster", optional);
        arguments.add(new NameValuePair("id", clusterId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateClusterResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateClusterResponse object
     *
     * @param doc
     * @return
     */
    private UpdateClusterResponse getUpdateClusterResponse(Document doc) {
        UpdateClusterResponse response = new UpdateClusterResponse();

        // get id from XML and set as the ID of the cluster 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get allocationstate from XML and set the allocationstate of the cluster      
        list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAllocationState(node.getTextContent());
        }

        // get clustertype from XML and set the type of the cluster 
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getTextContent());
        }

        // get cpuovercommitratio from XML and set the cpu overcommit ratio of the cluster
        list = doc.getElementsByTagName("cpuovercommitratio");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuOverCommitRatio(node.getTextContent());
        }

        // get hypervisortype from XML and set as the hypervisor type of the cluster      
        list = doc.getElementsByTagName("hypervisortype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorType(node.getTextContent());
        }

        // get managedstate from XML and set whether this cluster is managed by cloudstack     
        list = doc.getElementsByTagName("managedstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setManagedState(node.getTextContent());
        }

        // get memeoryovercommitratio from XML and set the memory overcommit ratio of the cluster     
        list = doc.getElementsByTagName("memoryovercommitratio");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryOverCommitRatio(node.getTextContent());
        }

        // get name from XML and set the name of the cluster      
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get podid from XML and set the Pod ID of the cluster    
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set the Pod name of the cluster      
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get zoneid from XML and set the zone id of the cluster      
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zone name of the cluster      
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        //the list of Zone Capacity associated with Zone
        list = doc.getElementsByTagName("capacity");
        if (list.getLength() > 0) {
            List<CapacityResponse> clusterCapacitys = new LinkedList<CapacityResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node clusterCapacityNode = list.item(index);
                CapacityResponse clusterCapacity = new CapacityResponse();

                NodeList clusterCapacityProperties = clusterCapacityNode.getChildNodes();
                for (int childIndex = 0; childIndex < clusterCapacityProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("capacitytotal")) {
                        clusterCapacity.setCapacityTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("capacityused")) {
                        clusterCapacity.setCapacityUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                        clusterCapacity.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                        clusterCapacity.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("percentused")) {
                        clusterCapacity.setPercentUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        clusterCapacity.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        clusterCapacity.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        clusterCapacity.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        clusterCapacity.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        clusterCapacity.setZoneName(property.getTextContent());
                    }
                }

                clusterCapacitys.add(clusterCapacity);
                response.setClusterCapacitys(clusterCapacitys);
            }
        }

        return response;
    }

    /**
     * Lists clusters.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListClustersResponse listClusters(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listClusters", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListClustersResponse(responseDocument);
    }

    /**
     * Converts XML document into ListClustersResponse object
     *
     * @param doc
     * @return
     */
    private ListClustersResponse getListClustersResponse(Document doc) {
        ListClustersResponse response = new ListClustersResponse();

        NodeList list = doc.getElementsByTagName("cluster");
        List<ClusterResponse> clusters = new LinkedList<ClusterResponse>();
        if (list.getLength() > 0) {
            for (int clusterIndex = 0; clusterIndex < list.getLength(); clusterIndex++) {
                Node clusterNode = list.item(clusterIndex);
                ClusterResponse cluster = new ClusterResponse();
                List<CapacityResponse> capacitys = new LinkedList<CapacityResponse>();
                NodeList clusterProperties = clusterNode.getChildNodes();
                for (int childIndex = 0; childIndex < clusterProperties.getLength(); childIndex++) {
                    Node childProperty = clusterProperties.item(childIndex);
                    if (childProperty.getNodeName().equals("id")) {
                        cluster.setId(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("allocationstate")) {
                        cluster.setAllocationState(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("clustertype")) {
                        cluster.setClusterType(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("cpuovercommitratio")) {
                        cluster.setCpuOverCommitRatio(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("hypervisortype")) {
                        cluster.setHypervisorType(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("managedstate")) {
                        cluster.setManagedState(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("memoryovercommitratio")) {
                        cluster.setMemoryOverCommitRatio(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("name")) {
                        cluster.setName(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("podid")) {
                        cluster.setPodId(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("podname")) {
                        cluster.setPodName(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("zoneid")) {
                        cluster.setZoneId(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("zonename")) {
                        cluster.setZoneName(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("capacity")) {
                        NodeList capacityProperties = childProperty.getChildNodes();
                        if (capacityProperties.getLength() > 0) {
                            CapacityResponse capacity = new CapacityResponse();
                            for (int capacityIndex = 0; capacityIndex < capacityProperties.getLength(); capacityIndex++) {
                                Node capacityProperty = capacityProperties.item(capacityIndex);
                                if (capacityProperty.getNodeName().equals("id")) {
                                    capacity.setCapacityTotal(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("broadcasturi")) {
                                    capacity.setCapacityUsed(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("gateway")) {
                                    capacity.setClusterId(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("ipaddress")) {
                                    capacity.setClusterName(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("isdefault")) {
                                    capacity.setPercentUsed(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("isolationuri")) {
                                    capacity.setPodId(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("macaddress")) {
                                    capacity.setPodName(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("netmask")) {
                                    capacity.setType(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("networkid")) {
                                    capacity.setZoneId(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("traffictype")) {
                                    capacity.setZoneName(capacityProperty.getTextContent());
                                }
                            }
                            capacitys.add(capacity);
                        }
                    }
                }
                cluster.setCapacitys(capacitys);
                clusters.add(cluster);
            }
        }
        response.setClusters(clusters);
        return response;
    }

    /**
     * Dedicate an existing cluster.
     *
     * @param clusterId the ID of the Cluster
     * @param domainId the ID of the containing domain
     * @param optional
     * @return
     * @throws Exception
     */
    public DedicateClusterResponse dedicateCluster(String clusterId,
            String domainId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("dedicateCluster", optional);
        arguments.add(new NameValuePair("clusterid", clusterId));
        arguments.add(new NameValuePair("domainid", domainId));

        Document responseDocument = server.makeRequest(arguments);

        return getDedicateClusterResponse(responseDocument);
    }

    /**
     * Converts XML document into DedicateClusterResponse object
     *
     * @param doc
     * @return
     */
    private DedicateClusterResponse getDedicateClusterResponse(Document doc) {
        DedicateClusterResponse response = new DedicateClusterResponse();

        // get id from XML and set as the ID of the dedicated resource
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get accountid from XML and set the Account ID of the cluster      
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get affinitygroupid from XML and set the Dedication Affinity Group ID of the cluster     
        list = doc.getElementsByTagName("affinitygroupid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAffinityGroupId(node.getTextContent());
        }

        // get clusterid from XML and set the ID of the cluster     
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set the Name of the cluster      
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get domainid from XML and set the domain ID to which the Pod is dedicated   
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        return response;
    }

    /**
     * Release the dedication for cluster.
     *
     * @param clusterId the ID of the Cluster
     * @return
     * @throws Exception
     */
    public ReleaseDedicatedClusterResponse releaseDedicatedCluster(String clusterId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("releaseDedicatedCluster", null);
        arguments.add(new NameValuePair("clusterid", clusterId));

        Document responseDocument = server.makeRequest(arguments);

        return getReleaseDedicatedClusterResponse(responseDocument);
    }

    /**
     * Converts XML document into ReleaseDedicatedClusterResponse object
     *
     * @param doc
     * @return
     */
    private ReleaseDedicatedClusterResponse getReleaseDedicatedClusterResponse(Document doc) {
        ReleaseDedicatedClusterResponse response = new ReleaseDedicatedClusterResponse();

        // get displaytext from XML and set any text associated with the success or failure on deleting cluster 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting cluster 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists dedicated clusters.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListDedicatedClustersResponse listDedicatedClusters(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listDedicatedClusters", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListDedicatedClustersResponse(responseDocument);
    }

    /**
     * Converts XML document into ListDedicatedClustersResponse object
     *
     * @param doc
     * @return
     */
    private ListDedicatedClustersResponse getListDedicatedClustersResponse(Document doc) {
        ListDedicatedClustersResponse response = new ListDedicatedClustersResponse();

        NodeList list = doc.getElementsByTagName("dedicatedpod");

        List<DedicatedClusterResponse> dedicatedClusters = new LinkedList<DedicatedClusterResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node dedicatedClusterNode = list.item(Index);
            DedicatedClusterResponse dedicatedCluster = new DedicatedClusterResponse();

            NodeList dedicatedClusterProperties = dedicatedClusterNode.getChildNodes();
            for (int childIndex = 0; childIndex < dedicatedClusterProperties.getLength(); childIndex++) {
                Node property = dedicatedClusterProperties.item(childIndex);

                if (property.getNodeName().equals("id")) {
                    dedicatedCluster.setId(property.getTextContent());
                } else if (property.getNodeName().equals("accountid")) {
                    dedicatedCluster.setAccountId(property.getTextContent());
                } else if (property.getNodeName().equals("affinitygroupid")) {
                    dedicatedCluster.setAffinityGroupId(property.getTextContent());
                } else if (property.getNodeName().equals("clusterid")) {
                    dedicatedCluster.setClusterId(property.getTextContent());
                } else if (property.getNodeName().equals("clustername")) {
                    dedicatedCluster.setClusterName(property.getTextContent());
                } else if (property.getNodeName().equals("domainid")) {
                    dedicatedCluster.setDomainId(property.getTextContent());
                }
            }
            dedicatedClusters.add(dedicatedCluster);
        }

        response.setDedicatedClusters(dedicatedClusters);
        return response;
    }

}
