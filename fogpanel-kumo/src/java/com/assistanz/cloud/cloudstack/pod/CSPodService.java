package com.assistanz.cloud.cloudstack.pod;

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
public class CSPodService {
	
    private CloudStackServer server;

    public CSPodService(CloudStackServer server) {
        this.server = server;
    }
	
	/**
    * Creates a new Pod.
    * 
    * @param podGateway The gateway for the Pod
    * @param podName The name of the Pod
    * @param podNetMask The Net mask for the Pod
    * @param podStartIp The starting IP address for the Pod
    * @param podZoneId The Zone ID in which the Pod will be created 
    * @param optional
    * @return
    * @throws Exception
    */
   public CreatePodResponse createPod(String podGateway,
                   String podName, String podNetMask, String podStartIp, String podZoneId,
                   HashMap<String,String> optional) 
                                   throws Exception {

       LinkedList<NameValuePair> arguments = 
               server.getDefaultQuery("createPod", optional);
       arguments.add(new NameValuePair("gateway", podGateway));
       arguments.add(new NameValuePair("name", podName));
       arguments.add(new NameValuePair("netmask", podNetMask));
       arguments.add(new NameValuePair("startip", podStartIp));
       arguments.add(new NameValuePair("zoneid", podZoneId));



       Document responseDocument = server.makeRequest(arguments);

       return getCreatePodResponse(responseDocument);
   }
	
    /**
     * Converts XML document into CreatePodResponse object
     * 
     * @param doc
     * @return
     */
    private CreatePodResponse getCreatePodResponse(Document doc) {
            CreatePodResponse response = new CreatePodResponse();

        // get id from XML and set as the ID of the Pod
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get allocationstate from XML and set the allocationstate of the pod      
        list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodAllocationState(node.getTextContent());
        }

        // get endip from XML and set the end ip of the pod      
        list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodEndIp(node.getTextContent());
        }

        // get gateway from XML and set the gateway of the pod      
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodGateway(node.getTextContent());
        }

        // get name from XML and set the name of the pod      
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get netmask from XML and set the net mask of the pod      
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodNetMask(node.getTextContent());
        }

        // get startip from XML and set the start ip of the pod      
        list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodStartIp(node.getTextContent());
        }

        // get zoneid from XML and set the zone id of the pod      
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zone name of the pod      
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodZoneName(node.getTextContent());
        }

        //the list of Zone Capacity associated with Zone
        list = doc.getElementsByTagName("capacity");
        if (list.getLength() > 0) {
            List<CapacityResponse> podCapacitys = new LinkedList<CapacityResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node podCapacityNode = list.item(index);
                CapacityResponse podCapacity = new CapacityResponse();


                NodeList podCapacityProperties = podCapacityNode.getChildNodes();
                for (int childIndex = 0; childIndex < podCapacityProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        podCapacity.setCapacityTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcasturi")) {
                        podCapacity.setCapacityUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        podCapacity.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        podCapacity.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        podCapacity.setPercentUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("isolationuri")) {
                        podCapacity.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("macaddress")) {
                        podCapacity.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                        podCapacity.setCapacityType(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        podCapacity.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                        podCapacity.setZoneName(property.getTextContent());
                    }  
                }

                podCapacitys.add(podCapacity);
                response.setPodCapacitys(podCapacitys);
            }
        }

        return response;
    }
	
    /**
     * Updates a Pod.
     * 
     * @param podId the ID of the Pod
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdatePodResponse updatePod(String podId,
                    HashMap<String,String> optional) 
                                    throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updatePod", optional);
        arguments.add(new NameValuePair("gateway", podId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdatePodResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdatePodResponse object
     * 
     * @param doc
     * @return
     */
    private UpdatePodResponse getUpdatePodResponse(Document doc) {
            UpdatePodResponse response = new UpdatePodResponse();
		
        // get id from XML and set as the ID of the Pod
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }
	    
    	// get allocationstate from XML and set the allocationstate of the pod      
    	list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodAllocationState(node.getTextContent());
        }
        
    	// get endip from XML and set the end ip of the pod      
    	list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodEndIp(node.getTextContent());
        }
        
    	// get gateway from XML and set the gateway of the pod      
    	list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodGateway(node.getTextContent());
        }
        
    	// get name from XML and set the name of the pod      
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }
        
    	// get netmask from XML and set the net mask of the pod      
    	list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodNetMask(node.getTextContent());
        }
        
    	// get startip from XML and set the start ip of the pod      
    	list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodStartIp(node.getTextContent());
        }
        
    	// get zoneid from XML and set the zone id of the pod      
    	list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodZoneId(node.getTextContent());
        }
        
    	// get zonename from XML and set the zone name of the pod      
    	list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodZoneName(node.getTextContent());
        }
        
        //the list of Zone Capacity associated with Zone
        list = doc.getElementsByTagName("capacity");
        if (list.getLength() > 0) {
            List<CapacityResponse> podCapacitys = new LinkedList<CapacityResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node podCapacityNode = list.item(index);
                CapacityResponse podCapacity = new CapacityResponse();
                
                
                NodeList podCapacityProperties = podCapacityNode.getChildNodes();
                for (int childIndex = 0; childIndex < podCapacityProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                    	podCapacity.setCapacityTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcasturi")) {
                    	podCapacity.setCapacityUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                    	podCapacity.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                    	podCapacity.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                    	podCapacity.setPercentUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("isolationuri")) {
                    	podCapacity.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("macaddress")) {
                    	podCapacity.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                    	podCapacity.setCapacityType(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                    	podCapacity.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                    	podCapacity.setZoneName(property.getTextContent());
                    }  
                }
            
                podCapacitys.add(podCapacity);
                response.setPodCapacitys(podCapacitys);
            }
        }
	
        return response;
    }
	
    /**
     * Deletes a Pod.
     * 
     * @param podId the ID of the Pod
     * @return
     * @throws Exception
     */
    public DeletePodResponse deletePod(String podId) 
                    throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deletePod", null);
        arguments.add(new NameValuePair("id",  podId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getDeletePodResponse(responseDocument);
    }	


    /**
     * Converts XML document into DeletePodResponse object
     * 
     * @param doc
     * @return
     */
    private DeletePodResponse getDeletePodResponse(Document doc) {
            DeletePodResponse response = new DeletePodResponse();

        // get displaytext from XML and set any text associated with the success or failure on deleting pod 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting pod 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }
	
	
    /**
     * Lists all Pods.
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public ListPodsResponse listPods(HashMap<String,String> optional) 
        throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listPods", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListPodsResponse(responseDocument);
    }
	
    /**
     * Converts XML document into ListPodsResponse object
     * 
     * @param doc
     * @return
     */
    private ListPodsResponse getListPodsResponse(Document doc) {
        ListPodsResponse response = new ListPodsResponse();
            
        NodeList list = doc.getElementsByTagName("pod");    
        List<PodResponse> pods = new LinkedList<PodResponse>(); 
	if (list.getLength() > 0) {
            for (int podIndex = 0; podIndex < list.getLength(); podIndex++) {
                Node podNode = list.item(podIndex);
                PodResponse pod = new PodResponse();
                List<CapacityResponse> capacitys = new LinkedList<CapacityResponse>();
                NodeList podProperties = podNode.getChildNodes();
                for (int childIndex = 0; childIndex < podProperties.getLength(); childIndex++) {
                     Node childProperty = podProperties.item(childIndex);
                     if (childProperty.getNodeName().equals("id")) {
                        pod.setPodId(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("allocationstate")) {
                        pod.setPodAllocationState(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("endip")) {
                        pod.setPodEndIp(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("gateway")) {
                        pod.setPodGateway(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("name")) {
                        pod.setPodName(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("netmask")) {
                        pod.setPodNetMask(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("startip")) {
                        pod.setPodStartIp(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("zoneid")) {
                        pod.setPodZoneId(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("zonename")) {
                        pod.setPodZoneName(childProperty.getTextContent());
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
                pod.setCapacitys(capacitys);
                pods.add(pod);  
            }
        }
        response.setPods(pods); 
        return response;
    }

}
