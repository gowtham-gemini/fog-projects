package com.assistanz.cloud.cloudstack.cluster;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CapacityResponse;
import com.assistanz.cloud.cloudstack.CloudStackServer;

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
     * @param zoneId the Zone ID for the cluster
     * @param optional
     * @return
     * @throws Exception
     */
    public AddClusterResponse addCluster(String clusterName,
                    String clusterType, String hypervisor, String zoneId,
                    HashMap<String,String> optional) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("addCluster", optional);
            arguments.add(new NameValuePair("clustername",  clusterName));
            arguments.add(new NameValuePair("clustertype",  clusterType));
            arguments.add(new NameValuePair("hypervisor",  hypervisor));
            arguments.add(new NameValuePair("zoneid",  zoneId));

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
            response.setClusterId(node.getNodeValue());
        }
	    
    	// get allocationstate from XML and set the allocationstate of the cluster      
    	list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterAllocationState(node.getNodeValue());
        }
        
    	// get clustertype from XML and set the type of the cluster 
    	list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getNodeValue());
        }
        
    	// get hypervisortype from XML and set as the hypervisor type of the cluster      
    	list = doc.getElementsByTagName("hypervisortype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorType(node.getNodeValue());
        }
        
    	// get managedstate from XML and set whether this cluster is managed by cloudstack     
    	list = doc.getElementsByTagName("managedstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterManagedState(node.getNodeValue());
        }
        
    	// get name from XML and set the name of the cluster      
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getNodeValue());
        }
        
    	// get podid from XML and set the Pod ID of the cluster    
    	list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterPodId(node.getNodeValue());
        }
        
    	// get podname from XML and set the Pod name of the cluster      
    	list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterPodName(node.getNodeValue());
        }
        
    	// get zoneid from XML and set the zone id of the cluster      
    	list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterZoneId(node.getNodeValue());
        }
        
    	// get zonename from XML and set the zone name of the cluster      
    	list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterZoneName(node.getNodeValue());
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
                    
                    if (property.getNodeName().equals("id")) {
                    	clusterCapacity.setCapacityTotal(property.getNodeValue());
                    } else if (property.getNodeName().equals("broadcasturi")) {
                    	clusterCapacity.setCapacityUsed(property.getNodeValue());
                    } else if (property.getNodeName().equals("gateway")) {
                    	clusterCapacity.setClusterId(property.getNodeValue());
                    } else if (property.getNodeName().equals("ipaddress")) {
                    	clusterCapacity.setClusterName(property.getNodeValue());
                    } else if (property.getNodeName().equals("isdefault")) {
                    	clusterCapacity.setPercentUsed(property.getNodeValue());
                    } else if (property.getNodeName().equals("isolationuri")) {
                    	clusterCapacity.setPodId(property.getNodeValue());
                    } else if (property.getNodeName().equals("macaddress")) {
                    	clusterCapacity.setPodName(property.getNodeValue());
                    } else if (property.getNodeName().equals("netmask")) {
                    	clusterCapacity.setCapacityType(property.getNodeValue());
                    } else if (property.getNodeName().equals("networkid")) {
                    	clusterCapacity.setZoneId(property.getNodeValue());
                    } else if (property.getNodeName().equals("traffictype")) {
                    	clusterCapacity.setZoneName(property.getNodeValue());
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

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("deleteCluster", null);
            arguments.add(new NameValuePair("id",  clusterId));


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
            response.setDisplaytext(node.getNodeValue());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete cluster
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getNodeValue());
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
    public UpdateClusterResponse updateCluster(String clusterId,HashMap<String,String> optional) 
        throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateCluster", optional);
        arguments.add(new NameValuePair("id",  clusterId));

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
            response.setClusterId(node.getNodeValue());
        }
	    
    	// get allocationstate from XML and set the allocationstate of the cluster      
    	list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterAllocationState(node.getNodeValue());
        }
        
    	// get clustertype from XML and set the type of the cluster 
    	list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getNodeValue());
        }
        
    	// get hypervisortype from XML and set as the hypervisor type of the cluster      
    	list = doc.getElementsByTagName("hypervisortype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorType(node.getNodeValue());
        }
        
    	// get managedstate from XML and set whether this cluster is managed by cloudstack     
    	list = doc.getElementsByTagName("managedstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterManagedState(node.getNodeValue());
        }
        
    	// get name from XML and set the name of the cluster      
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getNodeValue());
        }
        
    	// get podid from XML and set the Pod ID of the cluster    
    	list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterPodId(node.getNodeValue());
        }
        
    	// get podname from XML and set the Pod name of the cluster      
    	list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterPodName(node.getNodeValue());
        }
        
    	// get zoneid from XML and set the zone id of the cluster      
    	list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterZoneId(node.getNodeValue());
        }
        
    	// get zonename from XML and set the zone name of the cluster      
    	list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterZoneName(node.getNodeValue());
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
                    
                    if (property.getNodeName().equals("id")) {
                    	clusterCapacity.setCapacityTotal(property.getNodeValue());
                    } else if (property.getNodeName().equals("broadcasturi")) {
                    	clusterCapacity.setCapacityUsed(property.getNodeValue());
                    } else if (property.getNodeName().equals("gateway")) {
                    	clusterCapacity.setClusterId(property.getNodeValue());
                    } else if (property.getNodeName().equals("ipaddress")) {
                    	clusterCapacity.setClusterName(property.getNodeValue());
                    } else if (property.getNodeName().equals("isdefault")) {
                    	clusterCapacity.setPercentUsed(property.getNodeValue());
                    } else if (property.getNodeName().equals("isolationuri")) {
                    	clusterCapacity.setPodId(property.getNodeValue());
                    } else if (property.getNodeName().equals("macaddress")) {
                    	clusterCapacity.setPodName(property.getNodeValue());
                    } else if (property.getNodeName().equals("netmask")) {
                    	clusterCapacity.setCapacityType(property.getNodeValue());
                    } else if (property.getNodeName().equals("networkid")) {
                    	clusterCapacity.setZoneId(property.getNodeValue());
                    } else if (property.getNodeName().equals("traffictype")) {
                    	clusterCapacity.setZoneName(property.getNodeValue());
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
    public ListClustersResponse listClusters(HashMap<String,String> optional) 
        throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listClusters", optional);
        
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
                        cluster.setClusterId(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("allocationstate")) {
                        cluster.setClusterAllocationState(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("clustertype")) {
                        cluster.setClusterType(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("hypervisortype")) {
                        cluster.setHypervisorType(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("name")) {
                        cluster.setClusterName(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("managedstate")) {
                        cluster.setClusterManagedState(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("podid")) {
                        cluster.setClusterPodId(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("podname")) {
                        cluster.setClusterPodName(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("zoneid")) {
                        cluster.setClusterZoneId(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("zonename")) {
                        cluster.setClusterZoneName(childProperty.getTextContent());
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
                                    capacity.setCapacityType(capacityProperty.getTextContent());
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
}
