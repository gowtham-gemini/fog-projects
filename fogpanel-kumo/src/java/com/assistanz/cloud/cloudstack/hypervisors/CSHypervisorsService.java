package com.assistanz.cloud.cloudstack.hypervisors;

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
public class CSHypervisorsService {
	
	private CloudStackServer server;
	
	public CSHypervisorsService(CloudStackServer server) {
		this.server = server;
	}
	
	/**
	 * List Hypervisor
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListHypervisorsResponse listHypervisors(
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listHypervisors", optional);
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getListHypervisorsResponse(responseDocument);
	}
	
    /**
     * Converts XML document into ListHypervisorsResponse object
     * 
     * @param doc
     * @return
     */
    private ListHypervisorsResponse getListHypervisorsResponse(Document doc) {
        ListHypervisorsResponse response = new ListHypervisorsResponse();
                
        //list of Hypervisor
        NodeList list = doc.getElementsByTagName("hypervisor");

        List<HypervisorResponse> hypervisors = new LinkedList<HypervisorResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node hypervisorNode = list.item(index);

                if (hypervisorNode == null) {
                    continue;
                }  

                HypervisorResponse hypervisor = new HypervisorResponse();                        
                NodeList hypervisorProperties = hypervisorNode.getChildNodes();
                for (int childIndex = 0; childIndex < hypervisorProperties.getLength(); childIndex++) {
                    Node property = hypervisorProperties.item(childIndex);

                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }

                    if (property.getNodeName().equals("name")) {
                        hypervisor.setHypervisorName(property.getTextContent());
                    }
                }
                hypervisors.add(hypervisor);
            }
        } 
        response.setHypervisors(hypervisors);
        return response;
    }
	
	/**
	 * Updates a hypervisor capabilities.
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
    public UpdateHypervisorCapabilitiesResponse updateHypervisorCapabilities(
            HashMap<String, String> optional) throws Exception {


        LinkedList<NameValuePair> arguments =
                server.getDefaultQuery("updateHypervisorCapabilities", optional);
       
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateHypervisorCapabilitiesResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateHypervisorCapabilitiesResponse object
     *
     * @param doc
     * @return
     */
    private UpdateHypervisorCapabilitiesResponse getUpdateHypervisorCapabilitiesResponse(Document doc) {
    	UpdateHypervisorCapabilitiesResponse response = new UpdateHypervisorCapabilitiesResponse();

        // get id from XML and set as the id of the service offering
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getNodeValue());
        }

        // get cpunumber from XML and set as the number of CPU
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getNodeValue());
        }

        // get cpuspeed from XML and set as the clock rate CPU speed in Mhz
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getNodeValue());
        }

        // get created from XML and set the date this service offering was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getNodeValue());
        }

        // get defaultuse from XML and set is this a default system vm offering
        list = doc.getElementsByTagName("defaultuse");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDefaultUse(node.getNodeValue());
        }

        // get displaytext from XML and set as the display text for the service offering
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getNodeValue());
        }

        // get displaytext from XML and set as the domain name of the service offering
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingDomain(node.getNodeValue());
        }

        // get domainid from XML and set as the domain id of the service offering
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingDomainid(node.getNodeValue());
        }

        // get hosttags from XML and set as the host tag for the service offering 
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingHostTags(node.getNodeValue());
        }

        // get issystem from XML and set as is this a system vm offering
        list = doc.getElementsByTagName("issystem");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsSystem(node.getNodeValue());
        }

        // get limitcpuuse from XML and set limit cpu for the service offering
        list = doc.getElementsByTagName("limitcpuuse");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLimitCpuUse(node.getNodeValue());
        }

        // get memory from XML and set memory for the service offering
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getNodeValue());
        }

        // get name from XML and set as the name for the service offering
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getNodeValue());
        }

        // get networkrate from XML and set network rate for the service offering
        list = doc.getElementsByTagName("networkrate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingNetworkRate(node.getNodeValue());
        }

        // get offerha from XML and set offer HA for the service offering
        list = doc.getElementsByTagName("offerha");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingHa(node.getNodeValue());
        }

        // get storagetype from XML and set storage type for the service offering
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getNodeValue());
        }

        // get systemvmtype from XML and set system type for the service offering
        list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineType(node.getNodeValue());
        }

        // get tags from XML and set tags for the service offering
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingTags(node.getNodeValue());
        }

        return response;
    }
    
    /**
     * Lists all hypervisor capabilities
     * 
     * @param optional
     * @return
     * @throws Exception
     */
	public ListHypervisorCapabilitiesResponse listHypervisorCapabilities(
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listHypervisorCapabilities", optional);
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getListHypervisorCapabilitiesResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListHypervisorCapabilitiesResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListHypervisorCapabilitiesResponse getListHypervisorCapabilitiesResponse(Document doc) {
		ListHypervisorCapabilitiesResponse response = new ListHypervisorCapabilitiesResponse();

		// get id from XML and set as the ID of the hypervisor capabilities row
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setHypervisorCapabilitiesId(node.getNodeValue());
		}
		
        // get hypervisor from XML and set as the hypervisor type
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorType(node.getNodeValue());
        }
        
        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getNodeValue());
        }
        
        // get securitygroupenabled from XML and set as true if security group is supported
        list = doc.getElementsByTagName("securitygroupenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSecurityGroupEnabled(node.getNodeValue());
        }
		
		return response;
	}

}
