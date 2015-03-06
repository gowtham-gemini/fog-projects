package com.assistanz.cloud.cloudstack.iso;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.iso.ListIsoResponse;
import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;

/**
 * 
 * @author Gowtham
 *
 */
public class CSIsoService {
	
	private CloudStackServer server;
	
	public CSIsoService(CloudStackServer server) {
		this.server = server;
	}
	
    /**
     * Attaches an ISO to a virtual machine.
     * 
     * @param isoId the ID of the ISO file
     * @param virtualMachineId the ID of the virtual machine
     * @return
     * @throws Exception
     */
    public AttachISOResponse attachIso(String isoId, String virtualMachineId) throws Exception {

        LinkedList<NameValuePair> arguments = 
        server.getDefaultQuery("attachIso", null);
        arguments.add(new NameValuePair("id", isoId));
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getAttachISOResponse(responseDocument);
    }
	
    /**
     * Converts XML document into AttachISOResponse object
     *
     * @param doc
     * @return
     */
    private AttachISOResponse getAttachISOResponse(Document doc) {
    	AttachISOResponse response = new AttachISOResponse();
    	
    	// get Id from XML and set the ID of the virtual machine      
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }
        
        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }
        
        // get cpunumber from XML and set the number of CPU this virtual machine is running with
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }
        
        // get cpuspeed from XML and set the speed of each cpu
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }
        
        // get cpuused from XML and set the amount of the virtual machine's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuUsed(node.getTextContent());
        }
        
        // get created from XML and set the date when this virtual machine was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }
        
        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }
        
        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }
        
        // get domainid from XML and the ID of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }
        
        // get forvirtualnetwork from XML and set the virtual network for the service offering
        list = doc.getElementsByTagName("forvirtualnetwork");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setForVirtualNetwork(node.getTextContent());
        }
        
        // get group from XML and set the group name of the virtual machine
        list = doc.getElementsByTagName("group");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGroup(node.getTextContent());
        }
        
        // get groupid from XML and set the group ID of the virtual machine
        list = doc.getElementsByTagName("groupid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGroupId(node.getTextContent());
        }
        
        // get guestosid from XML and set Os type ID of the virtual machine
        list = doc.getElementsByTagName("guestosid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestOsId(node.getTextContent());
        }
        
        // get haenable from XML and set true if high-availability is enabled, false otherwise
        list = doc.getElementsByTagName("haenable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHighEnable(node.getTextContent());
        }
        
        // get hostid from XML and set the ID of the host for the virtual machine
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }
        
        // get hostname from XML and set the name of the host for the virtual machine
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }
        
        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHyperVisor(node.getTextContent());
        }
        
        // get isodisplaytext from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("isodisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoDisplayText(node.getTextContent());
        }
        
        // get isoid from XML and the set the ID of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("isoid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoId(node.getTextContent());
        }
        
        // get isoname from XML and set the name of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("isoname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoName(node.getTextContent());
        }
        
        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoDisplayText(node.getTextContent());
        }
        
        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachinename(node.getTextContent());
        }
        
        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbbsRead(node.getTextContent());
        }
        
        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbbsWrite(node.getTextContent());
        }
        
        // get password from XML and set the password (if exists) of the virtual machine
        list = doc.getElementsByTagName("password");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPassword(node.getTextContent());
        }
        
        // get passwordenabled from XML and set true if the password rest feature is enabled, false otherwise
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the vm
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectName(node.getTextContent());
        }
        
        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }
        
        // get publicip from XML and set as public IP address id associated with vm via Static nat rule
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }
        
        // get publicipid from XML and set as public IP address id associated with vm via Static nat rule
        list = doc.getElementsByTagName("publicipid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicipId(node.getTextContent());
        }
        
        // get rootdeviceid from XML and set as device ID of the root volume
        list = doc.getElementsByTagName("rootdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRootDeviceId(node.getTextContent());
        }
        
        // get rootdevicetype from XML and set as device type of the root volume
        list = doc.getElementsByTagName("rootdevicetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRootDeviceType(node.getTextContent());
        }
        
        // get serviceofferingid from XML and set as the ID of the service offering of the virtual machine
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }
        
        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }
        
        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachinestate(node.getTextContent());
        }
        
        // get templatedisplaytext from XML and set as an alternate display text of the template for the virtual machine
        list = doc.getElementsByTagName("templatedisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDisplayText(node.getTextContent());
        }
        
        // get templateid from XML and set as 	the ID of the template for the virtual machine. 
        // A -1 is returned if the virtual machine was created from an ISO file.
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }
        
        // get templatename from XML and set the name of the template for the virtual machine
        list = doc.getElementsByTagName("templatename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateName(node.getTextContent());
        }
        
        // get zoneid from XML and set as the ID of the availablility zone for the virtual machine
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }
        
        // get zonename from XML and set as the name of the availability zone for the virtual machine
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        //the list of NetworkInterfaceCards associated with virtual machine
        list = doc.getElementsByTagName("nic");
        if (list.getLength() > 0) {
            List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node networkInterfaceCardNode = list.item(index);
                NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                
                
                NodeList networkInterfaceCardProperties = networkInterfaceCardNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkInterfaceCardProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                    	networkInterfaceCard.setNetworkInterfaceCardId(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcasturi")) {
                    	networkInterfaceCard.setBroadcastUri(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                    	networkInterfaceCard.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                    	networkInterfaceCard.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                    	networkInterfaceCard.setIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("isolationuri")) {
                    	networkInterfaceCard.setIsolationUri(property.getTextContent());
                    } else if (property.getNodeName().equals("macaddress")) {
                    	networkInterfaceCard.setMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                    	networkInterfaceCard.setNetMask(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                    	networkInterfaceCard.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                    	networkInterfaceCard.setTrafficType(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	networkInterfaceCard.setNetworkInterfaceCardType(property.getTextContent());
                    }     
                }
            
                networkInterfaceCards.add(networkInterfaceCard);
                response.setNetworkInterfaceCards(networkInterfaceCards);
            }
        }
        
        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                
                
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < securityGroupProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                    	securityGroup.setSecurityGroupId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                    	securityGroup.setSecurityGroupAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                    	securityGroup.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                    	securityGroup.setSecurityGroupDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                    	securityGroup.setSecurityGroupDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	securityGroup.setSecurityGroupName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                    	securityGroup.setSecurityGroupProjectName(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                    	securityGroup.setSecurityGroupProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("egressrule")){
            			list = doc.getElementsByTagName("egressrule");
                    	if (list.getLength() > 0) {
	                        List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();            
	                        for (index = 0; index < list.getLength(); index++) {
	                            Node egressRuleNode = list.item(index);
	                            EgressRuleResponse egressRule = new EgressRuleResponse();
	                            
	                            
	                            NodeList egressRuleProperties = egressRuleNode.getChildNodes();
	                            for (childIndex = 0; childIndex < egressRuleProperties.getLength(); childIndex++) {
	                                 property = list.item(childIndex);;
	                                
	                                 if (property.getNodeName().equals("account")) {
	                                	 egressRule.setSecurityGroupRuleAccount(property.getTextContent());
	                                 } else if (property.getNodeName().equals("cidr")) {
	                                	 egressRule.setCidr(property.getTextContent());
	                                 } else if (property.getNodeName().equals("endport")) {
	                                	 egressRule.setEndPort(property.getTextContent());
	                                 } else if (property.getNodeName().equals("icmpcode")) {
	                                	 egressRule.setIcmpCode(property.getTextContent());
	                                 } else if (property.getNodeName().equals("icmptype")) {
	                                	 egressRule.setIcmpType(property.getTextContent());
	                                 } else if (property.getNodeName().equals("protocol")) {
	                                	 egressRule.setProtocol(property.getTextContent());
	                                 } else if (property.getNodeName().equals("ruleid")) {
	                                	 egressRule.setSecurityGroupRuleId(property.getTextContent());
	                                 } else if (property.getNodeName().equals("securitygroupname")) {
	                                	 egressRule.setSecurityGroupName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("startport")) {
	                                	 egressRule.setStartPort(property.getTextContent());
	                                 }
	                                
	                            } 
		                        
	                            egressRules.add(egressRule);
		                        response.setEgressRules(egressRules);
	                        }
			                
                    	}
                    } else if (property.getNodeName().equals("ingressrule")){
            			list = doc.getElementsByTagName("ingressrule");
                    	if (list.getLength() > 0) {
	                        List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();            
	                        for (index = 0; index < list.getLength(); index++) {
	                            Node egressRuleNode = list.item(index);
	                            IngressRuleResponse ingressRule = new IngressRuleResponse();
	                            
	                            
	                            NodeList egressRuleProperties = egressRuleNode.getChildNodes();
	                            for (childIndex = 0; childIndex < egressRuleProperties.getLength(); childIndex++) {
	                                 property = list.item(childIndex);;
	                                
	                                 if (property.getNodeName().equals("account")) {
	                                	 ingressRule.setSecurityGroupRuleAccount(property.getTextContent());
	                                 } else if (property.getNodeName().equals("cidr")) {
	                                	 ingressRule.setCidr(property.getTextContent());
	                                 } else if (property.getNodeName().equals("endport")) {
	                                	 ingressRule.setEndPort(property.getTextContent());
	                                 } else if (property.getNodeName().equals("icmpcode")) {
	                                	 ingressRule.setIcmpCode(property.getTextContent());
	                                 } else if (property.getNodeName().equals("icmptype")) {
	                                	 ingressRule.setIcmpType(property.getTextContent());
	                                 } else if (property.getNodeName().equals("protocol")) {
	                                	 ingressRule.setProtocol(property.getTextContent());
	                                 } else if (property.getNodeName().equals("ruleid")) {
	                                	 ingressRule.setSecurityGroupRuleId(property.getTextContent());
	                                 } else if (property.getNodeName().equals("securitygroupname")) {
	                                	 ingressRule.setSecurityGroupName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("startport")) {
	                                	 ingressRule.setStartPort(property.getTextContent());
	                                 }
	                                
	                            } 
		                        
	                            ingressRules.add(ingressRule);
		                        response.setIngressRules(ingressRules);
	                        }
			                
                    	}
                    }else if (property.getNodeName().equals("jobid")) {
                    	securityGroup.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                    	securityGroup.setJobStatus(property.getTextContent());
                    } 	
                
                    securityGroups.add(securityGroup);
                    response.setSecurityGroups(securityGroups);
                }
            }
        } 
        // get jobid number from XML and set the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        

        // get jobstatus number from XML and set the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }
    	             
        return response;
    }
    
    /**
     * Detaches any ISO file (if any) currently attached to a virtual machine.
     * 
     * @param virtualMachineId The ID of the virtual machine
     * @return
     * @throws Exception
     */
	public DetachISOResponse detachIso(String virtualMachineId) throws Exception {
				
		LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("detachIso", null);
		arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

		Document responseDocument = server.makeRequest(arguments);

        return getDetachISOResponse(responseDocument);
	}
	
    /**
     * Converts XML document into DetachISOResponse object
     *
     * @param doc
     * @return
     */
    private DetachISOResponse getDetachISOResponse(Document doc) {
    	DetachISOResponse response = new DetachISOResponse();
    	
    	// get Id from XML and set the ID of the virtual machine      
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }
        
        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }
        
        // get cpunumber from XML and set the number of CPU this virtual machine is running with
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }
        
        // get cpuspeed from XML and set the speed of each cpu
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }
        
        // get cpuused from XML and set the amount of the virtual machine's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuUsed(node.getTextContent());
        }
        
        // get created from XML and set the date when this virtual machine was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }
        
        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }
        
        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }
        
        // get domainid from XML and the ID of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }
        
        // get forvirtualnetwork from XML and set the virtual network for the service offering
        list = doc.getElementsByTagName("forvirtualnetwork");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setForVirtualNetwork(node.getTextContent());
        }
        
        // get group from XML and set the group name of the virtual machine
        list = doc.getElementsByTagName("group");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGroup(node.getTextContent());
        }
        
        // get groupid from XML and set the group ID of the virtual machine
        list = doc.getElementsByTagName("groupid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGroupId(node.getTextContent());
        }
        
        // get guestosid from XML and set Os type ID of the virtual machine
        list = doc.getElementsByTagName("guestosid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGuestOsId(node.getTextContent());
        }
        
        // get haenable from XML and set true if high-availability is enabled, false otherwise
        list = doc.getElementsByTagName("haenable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHighEnable(node.getTextContent());
        }
        
        // get hostid from XML and set the ID of the host for the virtual machine
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }
        
        // get hostname from XML and set the name of the host for the virtual machine
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }
        
        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHyperVisor(node.getTextContent());
        }
        
        // get isodisplaytext from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("isodisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoDisplayText(node.getTextContent());
        }
        
        // get isoid from XML and the set the ID of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("isoid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoId(node.getTextContent());
        }
        
        // get isoname from XML and set the name of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("isoname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoName(node.getTextContent());
        }
        
        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoDisplayText(node.getTextContent());
        }
        
        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachinename(node.getTextContent());
        }
        
        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbbsRead(node.getTextContent());
        }
        
        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbbsWrite(node.getTextContent());
        }
        
        // get password from XML and set the password (if exists) of the virtual machine
        list = doc.getElementsByTagName("password");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPassword(node.getTextContent());
        }
        
        // get passwordenabled from XML and set true if the password rest feature is enabled, false otherwise
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the vm
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectName(node.getTextContent());
        }
        
        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }
        
        // get publicip from XML and set as public IP address id associated with vm via Static nat rule
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }
        
        // get publicipid from XML and set as public IP address id associated with vm via Static nat rule
        list = doc.getElementsByTagName("publicipid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicipId(node.getTextContent());
        }
        
        // get rootdeviceid from XML and set as device ID of the root volume
        list = doc.getElementsByTagName("rootdeviceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRootDeviceId(node.getTextContent());
        }
        
        // get rootdevicetype from XML and set as device type of the root volume
        list = doc.getElementsByTagName("rootdevicetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRootDeviceType(node.getTextContent());
        }
        
        // get serviceofferingid from XML and set as the ID of the service offering of the virtual machine
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }
        
        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingName(node.getTextContent());
        }
        
        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachinestate(node.getTextContent());
        }
        
        // get templatedisplaytext from XML and set as an alternate display text of the template for the virtual machine
        list = doc.getElementsByTagName("templatedisplaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDisplayText(node.getTextContent());
        }
        
        // get templateid from XML and set as 	the ID of the template for the virtual machine. 
        // A -1 is returned if the virtual machine was created from an ISO file.
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }
        
        // get templatename from XML and set the name of the template for the virtual machine
        list = doc.getElementsByTagName("templatename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateName(node.getTextContent());
        }
        
        // get zoneid from XML and set as the ID of the availablility zone for the virtual machine
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }
        
        // get zonename from XML and set as the name of the availability zone for the virtual machine
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        //the list of NetworkInterfaceCards associated with virtual machine
        list = doc.getElementsByTagName("nic");
        if (list.getLength() > 0) {
            List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node networkInterfaceCardNode = list.item(index);
                NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                
                
                NodeList networkInterfaceCardProperties = networkInterfaceCardNode.getChildNodes();
                for (int childIndex = 0; childIndex < networkInterfaceCardProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                    	networkInterfaceCard.setNetworkInterfaceCardId(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcasturi")) {
                    	networkInterfaceCard.setBroadcastUri(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                    	networkInterfaceCard.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                    	networkInterfaceCard.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                    	networkInterfaceCard.setIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("isolationuri")) {
                    	networkInterfaceCard.setIsolationUri(property.getTextContent());
                    } else if (property.getNodeName().equals("macaddress")) {
                    	networkInterfaceCard.setMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                    	networkInterfaceCard.setNetMask(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                    	networkInterfaceCard.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                    	networkInterfaceCard.setTrafficType(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	networkInterfaceCard.setNetworkInterfaceCardType(property.getTextContent());
                    }     
                }
            
                networkInterfaceCards.add(networkInterfaceCard);
                response.setNetworkInterfaceCards(networkInterfaceCards);
            }
        }
        
        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                
                
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < securityGroupProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);
                    
                    if (property.getNodeName().equals("id")) {
                    	securityGroup.setSecurityGroupId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                    	securityGroup.setSecurityGroupAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                    	securityGroup.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                    	securityGroup.setSecurityGroupDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                    	securityGroup.setSecurityGroupDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	securityGroup.setSecurityGroupName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                    	securityGroup.setSecurityGroupProjectName(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                    	securityGroup.setSecurityGroupProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("egressrule")){
            			list = doc.getElementsByTagName("egressrule");
                    	if (list.getLength() > 0) {
	                        List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();            
	                        for (index = 0; index < list.getLength(); index++) {
	                            Node egressRuleNode = list.item(index);
	                            EgressRuleResponse egressRule = new EgressRuleResponse();
	                            
	                            
	                            NodeList egressRuleProperties = egressRuleNode.getChildNodes();
	                            for (childIndex = 0; childIndex < egressRuleProperties.getLength(); childIndex++) {
	                                 property = list.item(childIndex);;
	                                
	                                 if (property.getNodeName().equals("account")) {
	                                	 egressRule.setSecurityGroupRuleAccount(property.getTextContent());
	                                 } else if (property.getNodeName().equals("cidr")) {
	                                	 egressRule.setCidr(property.getTextContent());
	                                 } else if (property.getNodeName().equals("endport")) {
	                                	 egressRule.setEndPort(property.getTextContent());
	                                 } else if (property.getNodeName().equals("icmpcode")) {
	                                	 egressRule.setIcmpCode(property.getTextContent());
	                                 } else if (property.getNodeName().equals("icmptype")) {
	                                	 egressRule.setIcmpType(property.getTextContent());
	                                 } else if (property.getNodeName().equals("protocol")) {
	                                	 egressRule.setProtocol(property.getTextContent());
	                                 } else if (property.getNodeName().equals("ruleid")) {
	                                	 egressRule.setSecurityGroupRuleId(property.getTextContent());
	                                 } else if (property.getNodeName().equals("securitygroupname")) {
	                                	 egressRule.setSecurityGroupName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("startport")) {
	                                	 egressRule.setStartPort(property.getTextContent());
	                                 }
	                                
	                            } 
		                        
	                            egressRules.add(egressRule);
		                        response.setEgressRules(egressRules);
	                        }
			                
                    	}
                    } else if (property.getNodeName().equals("ingressrule")){
            			list = doc.getElementsByTagName("ingressrule");
                    	if (list.getLength() > 0) {
	                        List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();            
	                        for (index = 0; index < list.getLength(); index++) {
	                            Node egressRuleNode = list.item(index);
	                            IngressRuleResponse ingressRule = new IngressRuleResponse();
	                            
	                            
	                            NodeList egressRuleProperties = egressRuleNode.getChildNodes();
	                            for (childIndex = 0; childIndex < egressRuleProperties.getLength(); childIndex++) {
	                                 property = list.item(childIndex);;
	                                
	                                 if (property.getNodeName().equals("account")) {
	                                	 ingressRule.setSecurityGroupRuleAccount(property.getTextContent());
	                                 } else if (property.getNodeName().equals("cidr")) {
	                                	 ingressRule.setCidr(property.getTextContent());
	                                 } else if (property.getNodeName().equals("endport")) {
	                                	 ingressRule.setEndPort(property.getTextContent());
	                                 } else if (property.getNodeName().equals("icmpcode")) {
	                                	 ingressRule.setIcmpCode(property.getTextContent());
	                                 } else if (property.getNodeName().equals("icmptype")) {
	                                	 ingressRule.setIcmpType(property.getTextContent());
	                                 } else if (property.getNodeName().equals("protocol")) {
	                                	 ingressRule.setProtocol(property.getTextContent());
	                                 } else if (property.getNodeName().equals("ruleid")) {
	                                	 ingressRule.setSecurityGroupRuleId(property.getTextContent());
	                                 } else if (property.getNodeName().equals("securitygroupname")) {
	                                	 ingressRule.setSecurityGroupName(property.getTextContent());
	                                 } else if (property.getNodeName().equals("startport")) {
	                                	 ingressRule.setStartPort(property.getTextContent());
	                                 }
	                                
	                            } 
		                        
	                            ingressRules.add(ingressRule);
		                        response.setIngressRules(ingressRules);
	                        }
			                
                    	}
                    }else if (property.getNodeName().equals("jobid")) {
                    	securityGroup.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                    	securityGroup.setJobStatus(property.getTextContent());
                    } 	
                
                    securityGroups.add(securityGroup);
                    response.setSecurityGroups(securityGroups);
                }
            }
        } 
        // get jobid number from XML and set the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        

        // get jobstatus number from XML and set the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }
    	             
        return response;
    }
    
    /**
     * Lists all available ISO files.
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public ListIsoResponse listIsos(HashMap<String,String> optional) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
            server.getDefaultQuery("listIsos", optional);

            Document responseDocument = server.makeRequest(arguments);

        return getListISOResponse(responseDocument);
    }
	
    /**
     * Converts XML document into ListISOResponse object
     *
     * @param doc
     * @return
     */
    private ListIsoResponse getListISOResponse(Document doc) {
    	ListIsoResponse response = new ListIsoResponse();
    	        
        NodeList list = doc.getElementsByTagName("iso");
        
        List<IsoResponse> isos = new LinkedList<IsoResponse>();
        
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node isoNode = list.item(index);

                if (isoNode == null) {
                    continue;
                }  

                IsoResponse iso = new IsoResponse();
                
                NodeList isoProperties = isoNode.getChildNodes();
                
                for (int childIndex = 0; childIndex < isoProperties.getLength(); childIndex++) {
                    Node property = isoProperties.item(childIndex);
                    
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                    
                    if (property.getNodeName().equals("id")) {
                        iso.setIsoId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                    	iso.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                    	iso.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("bootable")) {
                    	iso.setBootable(property.getTextContent());
                    } else if (property.getNodeName().equals("checksum")) {
                    	iso.setChecksum(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                    	iso.setIsoCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("crossZones")) {
                    	iso.setCrossZones(property.getTextContent());
                    } else if (property.getNodeName().equals("details")) {
                    	iso.setDetails(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                    	iso.setIsoDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                    	iso.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                    	iso.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("format")) {
                    	iso.setIsoFormat(property.getTextContent());
                    } else if (property.getNodeName().equals("hostid")) {
                    	iso.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostname")) {
                    	iso.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                    	iso.setHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("isextractable")) {
                    	iso.setIsExtractable(property.getTextContent());
                    } else if (property.getNodeName().equals("isfeatured")) {
                    	iso.setIsFeatured(property.getTextContent());
                    } else if (property.getNodeName().equals("ispublic")) {
                    	iso.setIsPublic(property.getTextContent());
                    } else if (property.getNodeName().equals("isready")) {
                    	iso.setIsReady(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	iso.setIsoName(property.getTextContent());
                    } else if (property.getNodeName().equals("ostypeid")) {
                    	iso.setOsTypeId(property.getTextContent());
                    } else if (property.getNodeName().equals("ostypename")) {
                    	iso.setOsTypeName(property.getTextContent());
                    } else if (property.getNodeName().equals("passwordenabled")) {
                    	iso.setPasswordEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                    	iso.setIsoProjectName(property.getTextContent());
                    }else if (property.getNodeName().equals("jobstatus")) {
                    	iso.setJobStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                    	iso.setIsoProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("removed")) {
                    	iso.setRemoved(property.getTextContent());
                    } else if (property.getNodeName().equals("size")) {
                    	iso.setIsoSize(property.getTextContent());
                    } else if (property.getNodeName().equals("sourcetemplateid")) {
                    	iso.setSourceIsoId(property.getTextContent());
                    } else if (property.getNodeName().equals("status")) {
                    	iso.setIsoStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("templatetag")) {
                    	iso.setIsoTag(property.getTextContent());
                    } else if (property.getNodeName().equals("templatetype")) {
                    	iso.setIsoType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	iso.setIsoZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                    	iso.setIsoZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("jobid")) {
                    	iso.setJobId(property.getTextContent());
                    }                   
                }
                isos.add(iso);
            }
        }
        response.setIsos(isos);
        return response;  
    }
    
    /**
     * Registers an existing ISO into the Cloud.com Cloud.
     * 
     * @param displayText The display text of the ISO. This is usually used for display purposes.
     * @param IsoName The name of the ISO
     * @param IsoUrl The URL to where the ISO is currently being hosted
     * @param zoneId The ID of the zone you wish to register the ISO to.
     * @param optional
     * @return
     * @throws Exception
     */
    public RegisterISOResponse registerIso(String displayText,
        String IsoName, String IsoUrl, String zoneId, HashMap<String,String> optional)throws Exception {

        LinkedList<NameValuePair> arguments = 
        server.getDefaultQuery("registerIso", optional);
        arguments.add(new NameValuePair("displaytext", displayText));
        arguments.add(new NameValuePair("name", IsoName));
        arguments.add(new NameValuePair("url", IsoUrl));
        arguments.add(new NameValuePair("zoneid", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getRegisterISOResponse(responseDocument);
    }
	
    /**
     * Converts XML document into ListISOResponse object
     *
     * @param doc
     * @return
     */
    private RegisterISOResponse getRegisterISOResponse(Document doc) {
    	RegisterISOResponse response = new RegisterISOResponse();
    	
        // get id from XML and set as the template ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoId(node.getTextContent());
        }

        // get account from XML and set as 	the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the template belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get bootable from XML and set as true if the ISO is bootable, otherwise false 
        list = doc.getElementsByTagName("bootable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBootable(node.getTextContent());
        }

        // get checksum from XML and set as checksum of the template
        list = doc.getElementsByTagName("checksum");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setChecksum(node.getTextContent());
        }

        // get created from XML and set as the date this template was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoCreated(node.getTextContent());
        }

        // get crossZones from XML and set as true if the template is managed across all Zones, otherwise false
        list = doc.getElementsByTagName("crossZones");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCrossZones(node.getTextContent());
        }

        // get details from XML and set as additional key/value details tied with template
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get displaytext from XML and set as the template display text
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get domainid from XML and set the ID of the domain to which the template belongsl
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get format from XML and set as the format of the template.
        list = doc.getElementsByTagName("format");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoFormat(node.getTextContent());
        }
        
        // get hostid from XML and set as the ID of the secondary storage host for the template
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set as the name of the secondary storage host for the template
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the template is extractable, otherwise false
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get isfeatured from XML and set as true if this template is a featured template,otherwise false 
        list = doc.getElementsByTagName("isfeatured");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsFeatured(node.getTextContent());
        }
        
        // get isready from XML and set as true if the template is ready to be deployed from, otherwise false.
        list = doc.getElementsByTagName("isready");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsReady(node.getTextContent());
        }
        
        // get name from XML and set as the template name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoName(node.getTextContent());
        }
        
        // get ostypeid from XML and set as the ID of the OS type for this template.
        list = doc.getElementsByTagName("ostypeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeId(node.getTextContent());
        }
        
        // get ostypename from XML and set as the Name of the OS type for this template.
        list = doc.getElementsByTagName("ostypename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeName(node.getTextContent());
        }
        
        // get passwordenabled from XML and set as true if the reset password feature is enabled,otherwise false 
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the template
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoProjectId(node.getTextContent());
        }
        
        // get removed from XML and set as the date this template was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }
        
        // get size from XML and set as the size of the template
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoSize(node.getTextContent());
        }
        
        // get sourcetemplateid from XML and set as the template ID of the parent template if present
        list = doc.getElementsByTagName("sourcetemplateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceIsoId(node.getTextContent());
        }
        
        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoStatus(node.getTextContent());
        }
        
        // get templatetag from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetag");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoTag(node.getTextContent());
        }
        
        // get templatetype from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoType(node.getTextContent());
        }
        
        // get zoneid from XML and set the ID of the zone for this template
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoZoneId(node.getTextContent());
        }
        
        // get zonename from XML and set the name of the zone for this template
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsoZoneName(node.getTextContent());
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
     * Updates an ISO file
     * 
     * @param imageFileId the ID of the image file
     * @param optional
     * @return
     * @throws Exception
     */
	public UpdateISOResponse updateIso(String imageFileId,
			HashMap<String,String> optional)throws Exception {
		
		LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateIso", optional);
		arguments.add(new NameValuePair("id",  imageFileId));
						
		Document responseDocument = server.makeRequest(arguments);

        return getUpdateISOResponse(responseDocument);
	}
	
    /**
     * Converts XML document into UpdateISOResponse object
     *
     * @param doc
     * @return
     */
    private UpdateISOResponse getUpdateISOResponse(Document doc) {
    	UpdateISOResponse response = new UpdateISOResponse();
    	
        // get id from XML and set as the template ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get account from XML and set as 	the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the template belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get bootable from XML and set as true if the ISO is bootable, otherwise false 
        list = doc.getElementsByTagName("bootable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBootable(node.getTextContent());
        }

        // get checksum from XML and set as checksum of the template
        list = doc.getElementsByTagName("checksum");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setChecksum(node.getTextContent());
        }

        // get created from XML and set as the date this template was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateCreated(node.getTextContent());
        }

        // get crossZones from XML and set as true if the template is managed across all Zones, otherwise false
        list = doc.getElementsByTagName("crossZones");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCrossZones(node.getTextContent());
        }

        // get details from XML and set as additional key/value details tied with template
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get displaytext from XML and set as the template display text
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get domainid from XML and set the ID of the domain to which the template belongsl
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get format from XML and set as the format of the template.
        list = doc.getElementsByTagName("format");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateFormat(node.getTextContent());
        }
        
        // get hostid from XML and set as the ID of the secondary storage host for the template
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set as the name of the secondary storage host for the template
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the template is extractable, otherwise false
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get isfeatured from XML and set as true if this template is a featured template,otherwise false 
        list = doc.getElementsByTagName("isfeatured");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsFeatured(node.getTextContent());
        }
        
        // get isready from XML and set as true if the template is ready to be deployed from, otherwise false.
        list = doc.getElementsByTagName("isready");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsReady(node.getTextContent());
        }
        
        // get name from XML and set as the template name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateName(node.getTextContent());
        }
        
        // get ostypeid from XML and set as the ID of the OS type for this template.
        list = doc.getElementsByTagName("ostypeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeId(node.getTextContent());
        }
        
        // get ostypename from XML and set as the Name of the OS type for this template.
        list = doc.getElementsByTagName("ostypename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeName(node.getTextContent());
        }
        
        // get passwordenabled from XML and set as true if the reset password feature is enabled,otherwise false 
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the template
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectId(node.getTextContent());
        }
        
        // get removed from XML and set as the date this template was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }
        
        // get size from XML and set as the size of the template
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateSize(node.getTextContent());
        }
        
        // get sourcetemplateid from XML and set as the template ID of the parent template if present
        list = doc.getElementsByTagName("sourcetemplateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceTemplateId(node.getTextContent());
        }
        
        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateStatus(node.getTextContent());
        }
        
        // get templatetag from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetag");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTag(node.getTextContent());
        }
        
        // get templatetype from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateType(node.getTextContent());
        }
        
        // get zoneid from XML and set the ID of the zone for this template
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneId(node.getTextContent());
        }
        
        // get zonename from XML and set the name of the zone for this template
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneName(node.getTextContent());
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
     * Deletes an ISO file.
     * 
     * @param isoFileId the ID of the ISO file
     * @param optional
     * @return
     * @throws Exception
     */
	public DeleteISOResponse deleteIso(String isoFileId, 
			HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteIso", optional);
        arguments.add(new NameValuePair("id", isoFileId));
       
        Document responseDocument = server.makeRequest(arguments);

        return getDeleteISOResponse(responseDocument);
    }
	
	/**
     * Converts XML document into DeleteISOResponse object
     * 
     * @param doc
     * @return 
     */
    private DeleteISOResponse getDeleteISOResponse(Document doc) {
    	DeleteISOResponse response = new DeleteISOResponse();

	    // get displaytext from XML and set Any text associated with the success or 
	    // failure on Deleting ISO file
	    NodeList list = doc.getElementsByTagName("displaytext");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDisplayText(node.getTextContent());
	    }
	    
	    // get success from XML and set Return true if Deleting ISO file operation 
	    // is executed successfully
	    list = doc.getElementsByTagName("success");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setSuccess(node.getTextContent());
	    }
	    
	     return response;
    }
    
    /**
     * Copies a template from one zone to another.
     * 
     * @param templateId The Template ID.
     * @param destinationZoneId ID of the zone the template is being copied to.
     * @param sourceZoneId ID of the zone the template is currently hosted on.
     * @param optional
     * @return
     * @throws Exception
     */
	public CopyISOResponse copyIso(String templateId,
			String destinationZoneId, String sourceZoneId,
			HashMap<String,String> optional)throws Exception {
		
		LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("copyIso", optional);
		arguments.add(new NameValuePair("id",  templateId));
		arguments.add(new NameValuePair("destzoneid",  destinationZoneId));
		arguments.add(new NameValuePair("sourcezoneid",  sourceZoneId));
						
		Document responseDocument = server.makeRequest(arguments);

        return getCopyISOResponse(responseDocument);
	}
	
    /**
     * Converts XML document into CopyISOResponse object
     *
     * @param doc
     * @return
     */
    private CopyISOResponse getCopyISOResponse(Document doc) {
    	CopyISOResponse response = new CopyISOResponse();
    	
        // get id from XML and set as the template ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get account from XML and set as 	the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the template belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get bootable from XML and set as true if the ISO is bootable, otherwise false 
        list = doc.getElementsByTagName("bootable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBootable(node.getTextContent());
        }

        // get checksum from XML and set as checksum of the template
        list = doc.getElementsByTagName("checksum");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setChecksum(node.getTextContent());
        }

        // get created from XML and set as the date this template was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateCreated(node.getTextContent());
        }

        // get crossZones from XML and set as true if the template is managed across all Zones, otherwise false
        list = doc.getElementsByTagName("crossZones");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCrossZones(node.getTextContent());
        }

        // get details from XML and set as additional key/value details tied with template
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get displaytext from XML and set as the template display text
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get domainid from XML and set the ID of the domain to which the template belongsl
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get format from XML and set as the format of the template.
        list = doc.getElementsByTagName("format");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateFormat(node.getTextContent());
        }
        
        // get hostid from XML and set as the ID of the secondary storage host for the template
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set as the name of the secondary storage host for the template
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the template is extractable, otherwise false
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get isfeatured from XML and set as true if this template is a featured template,otherwise false 
        list = doc.getElementsByTagName("isfeatured");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsFeatured(node.getTextContent());
        }
        
        // get isready from XML and set as true if the template is ready to be deployed from, otherwise false.
        list = doc.getElementsByTagName("isready");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsReady(node.getTextContent());
        }
        
        // get name from XML and set as the template name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateName(node.getTextContent());
        }
        
        // get ostypeid from XML and set as the ID of the OS type for this template.
        list = doc.getElementsByTagName("ostypeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeId(node.getTextContent());
        }
        
        // get ostypename from XML and set as the Name of the OS type for this template.
        list = doc.getElementsByTagName("ostypename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeName(node.getTextContent());
        }
        
        // get passwordenabled from XML and set as true if the reset password feature is enabled,otherwise false 
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the template
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectId(node.getTextContent());
        }
        
        // get removed from XML and set as the date this template was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }
        
        // get size from XML and set as the size of the template
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateSize(node.getTextContent());
        }
        
        // get sourcetemplateid from XML and set as the template ID of the parent template if present
        list = doc.getElementsByTagName("sourcetemplateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceTemplateId(node.getTextContent());
        }
        
        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateStatus(node.getTextContent());
        }
        
        // get templatetag from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetag");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTag(node.getTextContent());
        }
        
        // get templatetype from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateType(node.getTextContent());
        }
        
        // get zoneid from XML and set the ID of the zone for this template
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneId(node.getTextContent());
        }
        
        // get zonename from XML and set the name of the zone for this template
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneName(node.getTextContent());
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
     * Updates iso permissions
     * 
     * @param templateId the template ID
     * @param optional
     * @return
     * @throws Exception
     */
	public UpdateISOPermissionResponse updateIsoPermissions(String templateId, 
			HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateIsoPermissions", optional);
        arguments.add(new NameValuePair("id", templateId));
       
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateISOPermissionResponse(responseDocument);
    }
	
	/**
     * Converts XML document into UpdateISOPermissionResponse object
     * 
     * @param doc
     * @return 
     */
    private UpdateISOPermissionResponse getUpdateISOPermissionResponse(Document doc) {
    	UpdateISOPermissionResponse response = new UpdateISOPermissionResponse();

	    // get displaytext from XML and set Any text associated with the success or 
	    // failure on updating ISO file
	    NodeList list = doc.getElementsByTagName("displaytext");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDisplayText(node.getTextContent());
	    }
	    
	    // get success from XML and set Return true if updating ISO file operation 
	    // is executed successfully
	    list = doc.getElementsByTagName("success");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setSuccess(node.getTextContent());
	    }
	    
	     return response;
    }
    
    /**
     * List template visibility and all accounts that have permissions to view this template.
     * 
     * @param templateId the template ID
     * @param optional
     * @return
     * @throws Exception
     */
	public ListISOPermissionResponse listIsoPermissions(String templateId, 
			HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateIsoPermissions", optional);
        arguments.add(new NameValuePair("id", templateId));
       
        Document responseDocument = server.makeRequest(arguments);

        return getListISOPermissionResponse(responseDocument);
    }
	
	/**
     * Converts XML document into ListISOPermissionResponse object
     * 
     * @param doc
     * @return 
     */
    private ListISOPermissionResponse getListISOPermissionResponse(Document doc) {
    	ListISOPermissionResponse response = new ListISOPermissionResponse();
    	
        // get id from XML and set as the template ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get account from XML and set as 	the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountList(node.getTextContent());
        }
        
        // get domainid from XML and set the ID of the domain to which the template belongsl
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }
        
        // get ispublic from XML and set as true if this template is a public template, false otherwise
        list = doc.getElementsByTagName("ispublic");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPublic(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectIdList(node.getTextContent());
        }

	     return response;
    }
    
    /**
     * Extracts an ISO
     * 
     * @param isoFileId The ID of the ISO file
     * @param mode The mode of extraction - HTTP_DOWNLOAD or FTP_UPLOAD
     * @param optional
     * @return
     * @throws Exception
     */
	public ExtractISOResponse extractIso(String isoFileId, 
			String mode, HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("extractIso", optional);
        arguments.add(new NameValuePair("id", isoFileId));
        arguments.add(new NameValuePair("mode", mode));
       
        Document responseDocument = server.makeRequest(arguments);

        return getExtractISOResponse(responseDocument);
    }
	
    /**
     * Converts XML document into ExtractISOResponse object
     *
     * @param doc
     * @return
     */
    private ExtractISOResponse getExtractISOResponse(Document doc) {
    	ExtractISOResponse response = new ExtractISOResponse();

        // get id from XML and set as the id of extracted object
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectId(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the extracted object belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectaccountid(node.getTextContent());
        }
        
        // get created from XML and set as the time and date the object was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectcreated(node.getTextContent());
        }
        
        // get extractId from XML and set as the upload id of extracted object
        list = doc.getElementsByTagName("extractId");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUploadExtractedObjectId(node.getTextContent());
        }
        
        // get extractMode from XML and set as the mode of extraction - upload or download
        list = doc.getElementsByTagName("extractMode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectMode(node.getTextContent());
        }
        
        // get name from XML and set as the name of the extracted object
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectName(node.getTextContent());
        }
        
        // get state from XML and set as the state of the extracted object
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectState(node.getTextContent());
        }
        
        // get status from XML and set as the status of the extraction
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectStatus(node.getTextContent());
        }
        
        // get storagetype from XML and set as type of the storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectStorageType(node.getTextContent());
        }
        
        // get uploadpercentage from XML and set as the percentage of the entity uploaded to the specified location
        list = doc.getElementsByTagName("uploadpercentage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectUploadPercentage(node.getTextContent());
        }
        
        // get url from XML and set as if mode = upload then url of the uploaded entity. 
        // if mode = download the url from which the entity can be downloaded
        list = doc.getElementsByTagName("url");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectUrl(node.getTextContent());
        }
        
        // get zoneid from XML and set as the zone ID the object was extracted from
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectZoneid(node.getTextContent());
        }
        
        // get zonename from XML and set as the zone name the object was extracted from
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectZonename(node.getTextContent());
        }
        
        return response;
    }
}
