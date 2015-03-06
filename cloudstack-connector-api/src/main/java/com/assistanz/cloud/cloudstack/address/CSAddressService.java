package com.assistanz.cloud.cloudstack.address;

import java.util.HashMap;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSAddressService {

    private CloudStackServer server;

    public CSAddressService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Acquires and associates a public IP to an account.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public AssociateIpAddressResponse associateIpAddress(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("associateIpAddress", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getAssociateIpAddressResponse(responseDocument);
    }

    /**
     * Converts XML document into AssociateIpAddressResponse object
     *
     * @param doc
     * @return
     */
    private AssociateIpAddressResponse getAssociateIpAddressResponse(Document doc) {
        AssociateIpAddressResponse response = new AssociateIpAddressResponse();

        // get id from XML and set as the public IP address id
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account the public IP address is associated with
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get allocated from XML and set as the allocated date the public IP address was acquired
        list = doc.getElementsByTagName("allocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAllocated(node.getTextContent());
        }

        // get associatednetworkid from XML and set as the ID of the Network associated with the IP address
        list = doc.getElementsByTagName("associatednetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAssociatedNetworkid(node.getTextContent());
        }

        // get associatednetworkname from XML and set as the name of the Network associated with the IP address
        list = doc.getElementsByTagName("associatednetworkname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAssociatedNetworkName(node.getTextContent());
        }

        // get domain from XML and set as the domain the public IP address is associated with
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain ID the public IP address is associated with
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get forvirtualnetwork from XML and set as the virtual network for the IP address
        list = doc.getElementsByTagName("forvirtualnetwork");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setForVirtualnetwork(node.getTextContent());
        }

        // get ipaddress from XML and set as the public IP address
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get isportable from XML and set is public IP portable across the zones
        list = doc.getElementsByTagName("isportable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPortable(node.getTextContent());
        }

        // get issourcenat from XML and set as true if the IP address is a source nat address, false otherwise
        list = doc.getElementsByTagName("issourcenat");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsSourceNat(node.getTextContent());
        }

        // get isstaticnat from XML and set as true if this ip is for static nat, false otherwise
        list = doc.getElementsByTagName("isstaticnat");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsStaticNat(node.getTextContent());
        }

        // get issystem from XML and set as true if this ip is system ip (was allocated as a part of deployVm or createLbRule)
        list = doc.getElementsByTagName("issystem");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsSystem(node.getTextContent());
        }

        // get networkid from XML and set as the ID of the Network where ip belongs to
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkId(node.getTextContent());
        }

        // get physicalnetworkid from XML and set as the ID of the physical network id where ip belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get project from XML and set as the project name of the address
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the address
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get purpose from XML and set as the purpose of the address
        list = doc.getElementsByTagName("purpose");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPurpose(node.getTextContent());
        }

        // get state from XML and set as the state of the address
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get virtualmachinedisplayname from XML and set as virutal machine display name the ip address is assigned to (not null only for static nat Ip)
        list = doc.getElementsByTagName("virtualmachinedisplayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineDisplayName(node.getTextContent());
        }

        // get virtualmachineid from XML and set as virutal machine id the ip address is assigned to (not null only for static nat Ip)
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        // get virtualmachinename from XML and set as virutal machine name the ip address is assigned to (not null only for static nat Ip)
        list = doc.getElementsByTagName("virtualmachinename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineName(node.getTextContent());
        }

        // get vlanid from XML and set as the vlanid of the address
        list = doc.getElementsByTagName("vlanid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlanId(node.getTextContent());
        }

        // get vlanname from XML and set as the vlanname of the address
        list = doc.getElementsByTagName("vlanname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVlanName(node.getTextContent());
        }

        // get vmipaddress from XML and set as the virutal machine (dnat) ip address (not null only for static nat Ip)
        list = doc.getElementsByTagName("vmipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmIpAddress(node.getTextContent());
        }

        // get vpcid from XML and set as VPC the ip belongs to
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }

        // get zoneid from XML and set as the zoneid of the address
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the zonename of the address
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }
        // get jobid from XML and set as the jobid of the address
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set as the jobstatus of the address
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }
        // gets the list of resource tags associated with ip address
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node tagsNode = list.item(index);
                TagsResponse tags = new TagsResponse();
                NodeList tagsProperties = tagsNode.getChildNodes();
                for (int childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                    Node tagsProperty = tagsProperties.item(childIndex);
                    if (tagsProperty.getNodeName().equals("account")) {
                        tags.setAccount(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("customer")) {
                        tags.setCustomer(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domain")) {
                        tags.setDomain(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                        tags.setDomainId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("key")) {
                        tags.setKey(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("project")) {
                        tags.setProject(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                        tags.setProjectId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                        tags.setResourceId(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                        tags.setResourceType(tagsProperty.getTextContent());
                    } else if (tagsProperty.getNodeName().equals("value")) {
                        tags.setValue(tagsProperty.getTextContent());
                    }

                }
                tagss.add(tags);
                response.setTagss(tagss);
            }
        }        
        return response;
    }

    /**
     * Disassociates an ip address from the account.
     *
     * @param publicIpAddressId the id of the public ip address to disassociate
     * @return
     * @throws Exception
     */
    public DisassociateIpAddressResponse disassociateIpAddress(String publicIpAddressId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("disassociateIpAddress", null);
        arguments.add(new NameValuePair("id", publicIpAddressId));

        Document responseDocument = server.makeRequest(arguments);

        return getDisassociateIpAddressResponse(responseDocument);
    }

    /**
     * Converts XML document into DisassociateIpAddressResponse object
     *
     * @param doc
     * @return
     */
    private DisassociateIpAddressResponse getDisassociateIpAddressResponse(Document doc) {
        DisassociateIpAddressResponse response = new DisassociateIpAddressResponse();

        // get displaytext from XML and set any text associated with the success or failure on disassociate public ip address
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting disassociate public ip address
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }        
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        } 
        return response;

    }

    /**
     * Lists all public ip addresses
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListPublicIpAddressesResponse listPublicIpAddresses(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listPublicIpAddresses", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListPublicIpAddressesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListPublicIpAddressesResponse object
     *
     * @param doc
     * @return
     */
    private ListPublicIpAddressesResponse getListPublicIpAddressesResponse(Document doc) {
        ListPublicIpAddressesResponse response = new ListPublicIpAddressesResponse();

        //list of PublicIpAddress
        NodeList list = doc.getElementsByTagName("publicipaddress");

        List<IpAddressResponse> ipAddresses = new LinkedList<IpAddressResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node ipAddressNode = list.item(index);

                IpAddressResponse ipAddress = new IpAddressResponse();

                NodeList ipAddressProperties = ipAddressNode.getChildNodes();
                for (int childIndex = 0; childIndex < ipAddressProperties.getLength(); childIndex++) {
                    Node property = ipAddressProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        ipAddress.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        ipAddress.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("allocated")) {
                        ipAddress.setAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("associatednetworkid")) {
                        ipAddress.setAssociatedNetworkid(property.getTextContent());
                    } else if (property.getNodeName().equals("forvirtualnetwork")) {
                        ipAddress.setForVirtualnetwork(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        ipAddress.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("issourcenat")) {
                        ipAddress.setIsSourceNat(property.getTextContent());
                    } else if (property.getNodeName().equals("isstaticnat")) {
                        ipAddress.setIsStaticNat(property.getTextContent());
                    } else if (property.getNodeName().equals("issystem")) {
                        ipAddress.setIsSystem(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        ipAddress.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        ipAddress.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        ipAddress.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        ipAddress.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        ipAddress.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        ipAddress.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("purpose")) {
                        ipAddress.setPurpose(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        ipAddress.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachinedisplayname")) {
                        ipAddress.setVirtualMachineDisplayName(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachineid")) {
                        ipAddress.setVirtualMachineId(property.getTextContent());
                    } else if (property.getNodeName().equals("vlanid")) {
                        ipAddress.setVlanId(property.getTextContent());
                    } else if (property.getNodeName().equals("vlanname")) {
                        ipAddress.setVlanName(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        ipAddress.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        ipAddress.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        list = doc.getElementsByTagName("tags");
                        if (list.getLength() > 0) {
                            List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                            for (index = 0; index < list.getLength(); index++) {
                                Node tagsNode = list.item(index);
                                TagsResponse tags = new TagsResponse();
                                NodeList tagsProperties = tagsNode.getChildNodes();
                                for (childIndex = 0; childIndex < tagsProperties.getLength(); childIndex++) {
                                    Node tagsProperty = tagsProperties.item(childIndex);

                                    if (tagsProperty.getNodeName().equals("account")) {
                                        tags.setAccount(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("customer")) {
                                        tags.setCustomer(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("domain")) {
                                        tags.setDomain(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("domainid")) {
                                        tags.setDomainId(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("key")) {
                                        tags.setKey(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("project")) {
                                        tags.setProject(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("projectid")) {
                                        tags.setProjectId(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("resourceid")) {
                                        tags.setResourceId(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("resourcetype")) {
                                        tags.setResourceType(tagsProperty.getTextContent());
                                    } else if (tagsProperty.getNodeName().equals("value")) {
                                        tags.setValue(tagsProperty.getTextContent());
                                    }

                                }

                                tagss.add(tags);
                                response.setTagss(tagss);
                            }
                        }

                    } else if (property.getNodeName().equals("jobid")) {
                        ipAddress.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        ipAddress.setJobStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcid")) {
                        ipAddress.setVpcId(property.getTextContent());
                    } 
                }
                ipAddresses.add(ipAddress);
            }
        }
        response.setIpAddresses(ipAddresses);
        return response;
    }
    
    /**
     * Retrieves the current status of asynchronous job for Add Ip for network.
     * 
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public AddIpJobResultResponse addIpJobResult(String asychronousJobid) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("queryAsyncJobResult", null);
            arguments.add(new NameValuePair("jobid",  asychronousJobid));

            Document responseDocument = server.makeRequest(arguments);

            return getIpJobResultResponse(responseDocument);
    }
    
     /**
     * Converts XML document into AddIpJobResultResponse object
     * 
     * @param doc
     * @return 
     */
    private AddIpJobResultResponse getIpJobResultResponse(Document doc) {
    	AddIpJobResultResponse response = new AddIpJobResultResponse();
        
        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCommand(node.getTextContent());                   
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobProgressStatus(node.getTextContent());
        }
        
        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            NodeList node = list.item(0).getChildNodes();    
            for (int index = 0; index < node.getLength(); index++) {
                Node nodeProperty = node.item(index);
                if (nodeProperty.getNodeName().equals("ipaddress")) {
                    NodeList childNodeProperties =  nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setSecondaryIpId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ipaddress")) {
                            response.setSecondaryIp(childNodeProperty.getTextContent());
                        } 
                    }
                }
            }
        
        }
        
        // get jobresultcode from XML and set as the result code for the job
        list = doc.getElementsByTagName("jobresultcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        return response;
    }
    
}
