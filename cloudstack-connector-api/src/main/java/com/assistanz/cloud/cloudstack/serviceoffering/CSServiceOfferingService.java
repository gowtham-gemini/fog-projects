package com.assistanz.cloud.cloudstack.serviceoffering;

import java.util.HashMap;
import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
//import org.apache.http.message.BasicNameValuePair;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSServiceOfferingService {

    private CloudStackServer server;

    public CSServiceOfferingService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates a service offering.
     *
     * @param cpuNumber the CPU number of the service offering
     * @param cpuSpeed the CPU speed of the service offering in MHz.
     * @param displayText the display text of the service offering
     * @param memory the total memory of the service offering in MB
     * @param name the name of the service offering
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateServiceOfferingResponse createServiceOffering(String cpuNumber, String cpuSpeed,
            String displayText, String memory, String name,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createServiceOffering", optional);
        arguments.add(new NameValuePair("cpunumber", cpuNumber));
        arguments.add(new NameValuePair("cpuspeed", cpuSpeed));
        arguments.add(new NameValuePair("displaytext", displayText));
        arguments.add(new NameValuePair("memory", memory));
        arguments.add(new NameValuePair("name", name));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateServiceOfferingResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateServiceOfferingResponse object
     *
     * @param doc
     * @return
     */
    private CreateServiceOfferingResponse getCreateServiceOfferingResponse(Document doc) {
        CreateServiceOfferingResponse response = new CreateServiceOfferingResponse();

        // get id from XML and set as the id of the service offering
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get cpunumber from XML and set as the number of CPU
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }

        // get cpuspeed from XML and set as the clock rate CPU speed in Mhz
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }

        // get created from XML and set the date this service offering was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get defaultuse from XML and set is this a default system vm offering
        list = doc.getElementsByTagName("defaultuse");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDefaultUse(node.getTextContent());
        }

        // get deploymentplanner from XML and set system type for the service offering
        list = doc.getElementsByTagName("deploymentplanner");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDeploymentPlanner(node.getTextContent());
        }

        // get bytesreadrate for the service offering
        list = doc.getElementsByTagName("diskBytesReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesReadRate(node.getTextContent());
        }

        // get byteswriterate for the service offering
        list = doc.getElementsByTagName("diskBytesWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesWriteRate(node.getTextContent());
        }

        // get io requests read rate of the service offering
        list = doc.getElementsByTagName("diskIopsReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsReadRate(node.getTextContent());
        }

        // get io requests write rate for the service offering
        list = doc.getElementsByTagName("diskIopsWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsWriteRate(node.getTextContent());
        }

        // get displaytext from XML and set as the display text for the service offering
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get displaytext from XML and set as the domain name of the service offering
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain id of the service offering
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainid(node.getTextContent());
        }
        // get hosttags from XML and set as the host tag for the service offering 
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostTags(node.getTextContent());
        }

        // get issystem from XML and set as is this a system vm offering
        list = doc.getElementsByTagName("issystem");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsSystem(node.getTextContent());
        }

        /* Set to true if the vm needs to be volatile, i.e., on every reboot of vm from API 
         * root disk is discarded and creates a new root disk 
         */
        list = doc.getElementsByTagName("isvolatile");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsVolatile(node.getTextContent());
        }

        // get limitcpuuse from XML and set limit cpu for the service offering
        list = doc.getElementsByTagName("limitcpuuse");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLimitCpuUse(node.getTextContent());
        }

        // get memory from XML and set memory for the service offering
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set as the name for the service offering
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkrate from XML and set network rate for the service offering
        list = doc.getElementsByTagName("networkrate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRate(node.getTextContent());
        }

        // get offerha from XML and set offer HA for the service offering
        list = doc.getElementsByTagName("offerha");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOfferHa(node.getTextContent());
        }

        // get offerha from XML and set offer HA for the service offering
        list = doc.getElementsByTagName("serviceofferingdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingDetails(node.getTextContent());
        }

        // get storagetype from XML and set storage type for the service offering
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getTextContent());
        }

        // get systemvmtype from XML and set system type for the service offering
        list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVmType(node.getTextContent());
        }

        // get tags from XML and set tags for the service offering
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        return response;
    }

    /**
     * Deletes a service offering
     *
     * @param serviceOfferingId the ID of the service offering
     * @return
     * @throws Exception
     */
    public DeleteServiceOfferingResponse deleteServiceOffering(String serviceOfferingId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteServiceOffering", null);
        arguments.add(new NameValuePair("id", serviceOfferingId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteServiceOfferingResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteServiceOfferingResponse object
     *
     * @param doc
     * @return
     */
    private DeleteServiceOfferingResponse getDeleteServiceOfferingResponse(Document doc) {
        DeleteServiceOfferingResponse response = new DeleteServiceOfferingResponse();

        // get displaytext from XML and set Any text associated with the success or 
        // failure on Deleting Service Offering
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get success from XML and set Return true if Deleting Service Offering operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * updates a service offering
     *
     * @param serviceOfferingId the ID of the service offering
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateServiceOfferingResponse updateServiceOffering(String serviceOfferingId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateServiceOffering", optional);
        arguments.add(new NameValuePair("id", serviceOfferingId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateServiceOfferingResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateServiceOfferingResponse object
     *
     * @param doc
     * @return
     */
    private UpdateServiceOfferingResponse getUpdateServiceOfferingResponse(Document doc) {
        UpdateServiceOfferingResponse response = new UpdateServiceOfferingResponse();

        // get id from XML and set as the id of the service offering
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get cpunumber from XML and set as the number of CPU
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }

        // get cpuspeed from XML and set as the clock rate CPU speed in Mhz
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }

        // get created from XML and set the date this service offering was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get defaultuse from XML and set is this a default system vm offering
        list = doc.getElementsByTagName("defaultuse");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDefaultUse(node.getTextContent());
        }

        // get deploymentplanner from XML and set system type for the service offering
        list = doc.getElementsByTagName("deploymentplanner");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDeploymentPlanner(node.getTextContent());
        }

        // get bytesreadrate for the service offering
        list = doc.getElementsByTagName("diskBytesReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesReadRate(node.getTextContent());
        }

        // get byteswriterate for the service offering
        list = doc.getElementsByTagName("diskBytesWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskBytesWriteRate(node.getTextContent());
        }

        // get io requests read rate of the service offering
        list = doc.getElementsByTagName("diskIopsReadRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsReadRate(node.getTextContent());
        }

        // get io requests write rate for the service offering
        list = doc.getElementsByTagName("diskIopsWriteRate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIopsWriteRate(node.getTextContent());
        }

        // get displaytext from XML and set as the display text for the service offering
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get displaytext from XML and set as the domain name of the service offering
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain id of the service offering
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainid(node.getTextContent());
        }
        // get hosttags from XML and set as the host tag for the service offering 
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostTags(node.getTextContent());
        }

        // get issystem from XML and set as is this a system vm offering
        list = doc.getElementsByTagName("issystem");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsSystem(node.getTextContent());
        }

        /* Set to true if the vm needs to be volatile, i.e., on every reboot of vm from API 
         * root disk is discarded and creates a new root disk 
         */
        list = doc.getElementsByTagName("isvolatile");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsVolatile(node.getTextContent());
        }

        // get limitcpuuse from XML and set limit cpu for the service offering
        list = doc.getElementsByTagName("limitcpuuse");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLimitCpuUse(node.getTextContent());
        }

        // get memory from XML and set memory for the service offering
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set as the name for the service offering
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkrate from XML and set network rate for the service offering
        list = doc.getElementsByTagName("networkrate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkRate(node.getTextContent());
        }

        // get offerha from XML and set offer HA for the service offering
        list = doc.getElementsByTagName("offerha");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOfferHa(node.getTextContent());
        }

        // get offerha from XML and set offer HA for the service offering
        list = doc.getElementsByTagName("serviceofferingdetails");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingDetails(node.getTextContent());
        }

        // get storagetype from XML and set storage type for the service offering
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getTextContent());
        }

        // get systemvmtype from XML and set system type for the service offering
        list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVmType(node.getTextContent());
        }

        // get tags from XML and set tags for the service offering
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists all available service offerings
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListServiceOfferingsResponse listServiceOfferings(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listServiceOfferings", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListServiceOfferingsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListServiceOfferingsResponse object
     *
     * @param doc
     * @return
     */
    private ListServiceOfferingsResponse getListServiceOfferingsResponse(Document doc) {
        ListServiceOfferingsResponse response = new ListServiceOfferingsResponse();

        NodeList list = doc.getElementsByTagName("serviceoffering");

        List<ServiceOfferingResponse> serviceOfferings = new LinkedList<ServiceOfferingResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node serviceOfferingNode = list.item(index);

                if (serviceOfferingNode == null) {
                    continue;
                }

                ServiceOfferingResponse serviceOffering = new ServiceOfferingResponse();

                NodeList serviceOfferingProperties = serviceOfferingNode.getChildNodes();
                for (int childIndex = 0; childIndex < serviceOfferingProperties.getLength(); childIndex++) {
                    Node property = serviceOfferingProperties.item(childIndex);

                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }

                    if (property.getNodeName().equals("id")) {
                        serviceOffering.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("cpunumber")) {
                        serviceOffering.setCpuNumber(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuspeed")) {
                        serviceOffering.setCpuSpeed(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        serviceOffering.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("defaultuse")) {
                        serviceOffering.setDefaultUse(property.getTextContent());
                    } else if (property.getNodeName().equals("deploymentplanner")) {
                        serviceOffering.setDeploymentPlanner(property.getTextContent());
                    } else if (property.getNodeName().equals("diskBytesReadRate")) {
                        serviceOffering.setDiskBytesReadRate(property.getTextContent());
                    } else if (property.getNodeName().equals("diskBytesWriteRate")) {
                        serviceOffering.setDiskBytesWriteRate(property.getTextContent());
                    } else if (property.getNodeName().equals("diskIopsReadRate")) {
                        serviceOffering.setDiskIopsReadRate(property.getTextContent());
                    } else if (property.getNodeName().equals("diskIopsWriteRate")) {
                        serviceOffering.setDiskIopsWriteRate(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                        serviceOffering.setDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        serviceOffering.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        serviceOffering.setDomainid(property.getTextContent());
                    } else if (property.getNodeName().equals("hosttags")) {
                        serviceOffering.setHostTags(property.getTextContent());
                    } else if (property.getNodeName().equals("issystem")) {
                        serviceOffering.setIsSystem(property.getTextContent());
                    } else if (property.getNodeName().equals("limitcpuuse")) {
                        serviceOffering.setLimitCpuUse(property.getTextContent());
                    } else if (property.getNodeName().equals("memory")) {
                        serviceOffering.setMemory(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        serviceOffering.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkrate")) {
                        serviceOffering.setNetworkRate(property.getTextContent());
                    } else if (property.getNodeName().equals("offerha")) {
                        serviceOffering.setOfferHa(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingdetails")) {
                        serviceOffering.setServiceOfferingDetails(property.getTextContent());
                    } else if (property.getNodeName().equals("storagetype")) {
                        serviceOffering.setStorageType(property.getTextContent());
                    } else if (property.getNodeName().equals("systemvmtype")) {
                        serviceOffering.setSystemVmType(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        serviceOffering.setTags(property.getTextContent());
                    }

                }
                serviceOfferings.add(serviceOffering);
            }
        }
        response.setServiceOfferings(serviceOfferings);
        return response;

    }

}
