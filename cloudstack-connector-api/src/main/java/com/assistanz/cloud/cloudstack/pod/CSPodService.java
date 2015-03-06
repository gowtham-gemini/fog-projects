package com.assistanz.cloud.cloudstack.pod;

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
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createPod", optional);
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
            response.setId(node.getTextContent());
        }

        // get allocationstate from XML and set the allocationstate of the pod      
        list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAllocationState(node.getTextContent());
        }

        // get endip from XML and set the end ip of the pod      
        list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndIp(node.getTextContent());
        }

        // get gateway from XML and set the gateway of the pod      
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get name from XML and set the name of the pod      
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get netmask from XML and set the net mask of the pod      
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetMask(node.getTextContent());
        }

        // get startip from XML and set the start ip of the pod      
        list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartIp(node.getTextContent());
        }

        // get zoneid from XML and set the zone id of the pod      
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zone name of the pod      
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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

                    if (property.getNodeName().equals("capaciytotal")) {
                        podCapacity.setCapacityTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("capacityused")) {
                        podCapacity.setCapacityUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                        podCapacity.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                        podCapacity.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("percentused")) {
                        podCapacity.setPercentUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        podCapacity.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        podCapacity.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        podCapacity.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        podCapacity.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
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
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updatePod", optional);
        arguments.add(new NameValuePair("id", podId));

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
            response.setId(node.getTextContent());
        }

        // get allocationstate from XML and set the allocationstate of the pod      
        list = doc.getElementsByTagName("allocationstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAllocationState(node.getTextContent());
        }

        // get endip from XML and set the end ip of the pod      
        list = doc.getElementsByTagName("endip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndIp(node.getTextContent());
        }

        // get gateway from XML and set the gateway of the pod      
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get name from XML and set the name of the pod      
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get netmask from XML and set the net mask of the pod      
        list = doc.getElementsByTagName("netmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetMask(node.getTextContent());
        }

        // get startip from XML and set the start ip of the pod      
        list = doc.getElementsByTagName("startip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStartIp(node.getTextContent());
        }

        // get zoneid from XML and set the zone id of the pod      
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zone name of the pod      
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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

                    if (property.getNodeName().equals("capaciytotal")) {
                        podCapacity.setCapacityTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("capacityused")) {
                        podCapacity.setCapacityUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                        podCapacity.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                        podCapacity.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("percentused")) {
                        podCapacity.setPercentUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        podCapacity.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        podCapacity.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        podCapacity.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        podCapacity.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
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

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deletePod", null);
        arguments.add(new NameValuePair("id", podId));

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
    public ListPodsResponse listPods(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listPods", optional);

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
                        pod.setId(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("allocationstate")) {
                        pod.setAllocationState(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("endip")) {
                        pod.setEndIp(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("gateway")) {
                        pod.setGateway(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("name")) {
                        pod.setName(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("netmask")) {
                        pod.setNetMask(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("startip")) {
                        pod.setStartIp(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("zoneid")) {
                        pod.setZoneId(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("zonename")) {
                        pod.setZoneName(childProperty.getTextContent());
                    } else if (childProperty.getNodeName().equals("capacity")) {
                        NodeList capacityProperties = childProperty.getChildNodes();
                        if (capacityProperties.getLength() > 0) {
                            CapacityResponse capacity = new CapacityResponse();
                            for (int capacityIndex = 0; capacityIndex < capacityProperties.getLength(); capacityIndex++) {
                                Node capacityProperty = capacityProperties.item(capacityIndex);
                                if (capacityProperty.getNodeName().equals("capacitytotal")) {
                                    capacity.setCapacityTotal(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("capacityused")) {
                                    capacity.setCapacityUsed(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("clusterid")) {
                                    capacity.setClusterId(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("clustername")) {
                                    capacity.setClusterName(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("percentused")) {
                                    capacity.setPercentUsed(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("podid")) {
                                    capacity.setPodId(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("podname")) {
                                    capacity.setPodName(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("type")) {
                                    capacity.setType(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("zoneid")) {
                                    capacity.setZoneId(capacityProperty.getTextContent());
                                } else if (capacityProperty.getNodeName().equals("zonename")) {
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

    /**
     * Dedicates a Pod.
     *
     * @param domainId the ID of the containing domain
     * @param podId the ID of the Pod
     * @param optional
     * @return
     * @throws Exception
     */
    public DedicatePodResponse dedicatePod(String domainId,
            String podId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("dedicatePod", optional);
        arguments.add(new NameValuePair("domainid", domainId));
        arguments.add(new NameValuePair("podid", podId));

        Document responseDocument = server.makeRequest(arguments);

        return getDedicatePodResponse(responseDocument);
    }

    /**
     * Converts XML document into DedicatePodResponse object
     *
     * @param doc
     * @return
     */
    private DedicatePodResponse getDedicatePodResponse(Document doc) {
        DedicatePodResponse response = new DedicatePodResponse();

        // get id from XML and set as the ID of the dedicated resource
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get accountid from XML and set the Account Id to which the Pod is dedicated      
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get affinitygroupid from XML and set the Dedication Affinity Group ID of the pod     
        list = doc.getElementsByTagName("affinitygroupid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAffinityGroupId(node.getTextContent());
        }

        // get domainid from XML and set the domain ID to which the Pod is dedicated   
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get podid from XML and set the ID of the Pod      
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set the Name of the Pod      
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        return response;
    }

    /**
     * Release the dedication for the pod.
     *
     * @param podId the ID of the Pod
     * @return
     * @throws Exception
     */
    public ReleaseDedicatedPodResponse releaseDedicatedPod(String podId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("releaseDedicatedPod", null);
        arguments.add(new NameValuePair("id", podId));

        Document responseDocument = server.makeRequest(arguments);

        return getReleaseDedicatedPodResponse(responseDocument);
    }

    /**
     * Converts XML document into ReleaseDedicatedPodResponse object
     *
     * @param doc
     * @return
     */
    private ReleaseDedicatedPodResponse getReleaseDedicatedPodResponse(Document doc) {
        ReleaseDedicatedPodResponse response = new ReleaseDedicatedPodResponse();

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
     * Lists dedicated pods.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListDedicatedPodsResponse listDedicatedPods(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listDedicatedPods", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListDedicatedPodsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListDedicatedPodsResponse object
     *
     * @param doc
     * @return
     */
    private ListDedicatedPodsResponse getListDedicatedPodsResponse(Document doc) {
        ListDedicatedPodsResponse response = new ListDedicatedPodsResponse();

        NodeList list = doc.getElementsByTagName("dedicatedpod");

        List<DedicatedPodResponse> dedicatedPods = new LinkedList<DedicatedPodResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node dedicatedPodNode = list.item(Index);
            DedicatedPodResponse dedicatedPod = new DedicatedPodResponse();

            NodeList dedicatedPodProperties = dedicatedPodNode.getChildNodes();
            for (int childIndex = 0; childIndex < dedicatedPodProperties.getLength(); childIndex++) {
                Node property = dedicatedPodProperties.item(childIndex);

                if (property.getNodeName().equals("id")) {
                    dedicatedPod.setId(property.getTextContent());
                } else if (property.getNodeName().equals("accountid")) {
                    dedicatedPod.setAccountId(property.getTextContent());
                } else if (property.getNodeName().equals("affinitygroupid")) {
                    dedicatedPod.setAffinityGroupId(property.getTextContent());
                } else if (property.getNodeName().equals("domainid")) {
                    dedicatedPod.setDomainId(property.getTextContent());
                } else if (property.getNodeName().equals("podid")) {
                    dedicatedPod.setPodId(property.getTextContent());
                } else if (property.getNodeName().equals("podname")) {
                    dedicatedPod.setPodName(property.getTextContent());
                }
            }
            dedicatedPods.add(dedicatedPod);
        }

        response.setDedicatedPods(dedicatedPods);
        return response;
    }

}
