package com.assistanz.cloud.cloudstack.virtualmachine;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.AffinityGroupResponse;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;
import com.assistanz.cloud.cloudstack.TagsResponse;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSVirtualMachineService {

    private CloudStackServer server;

    public CSVirtualMachineService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates and automatically starts a virtual machine based on a service offering, disk offering, and template.
     *
     * @param serviceOfferingId The ID of the service offering for the virtual machine
     * @param templateId The ID of the template for the virtual machine
     * @param zoneId Availability zone for the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public DeployVirtualMachineResponse deployVirtualMachine(String serviceOfferingId,
            String templateId, String zoneId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deployVirtualMachine", optional);
        arguments.add(new NameValuePair("serviceofferingid", serviceOfferingId));
        arguments.add(new NameValuePair("templateid", templateId));
        arguments.add(new NameValuePair("zoneid", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeployVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into DeployVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private DeployVirtualMachineResponse getDeployVirtualMachineResponse(Document doc) {
        DeployVirtualMachineResponse response = new DeployVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }
        
        // get jobid number from XML and set the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());            
        }
        
        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);
                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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
            

            // get jobstatus number from XML and set the current status of the latest async job acting on this object
            list = doc.getElementsByTagName("jobstatus");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setJobStatus(node.getTextContent());
            }
        }

        return response;
    }

    /**
     * Destroys a virtual machine. Once destroyed, only the administrator can recover it.
     *
     * @param virtualMachineId The ID of the virtual machine
     * @return
     * @throws Exception
     */
    public DestroyVirtualMachineResponse destroyVirtualMachine(String virtualMachineId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("destroyVirtualMachine", null);
        arguments.add(new NameValuePair("id", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getDestroyVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into DestroyVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private DestroyVirtualMachineResponse getDestroyVirtualMachineResponse(Document doc) {
        DestroyVirtualMachineResponse response = new DestroyVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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
        
        // get zoneid from XML and set as the ID of the availablility zone for the virtual machine
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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
        }

        return response;
    }

    /**
     * Reboots a virtual machine.
     *
     * @param virtualMachineId The ID of the virtual machine
     * @return
     * @throws Exception
     */
    public RebootVirtualMachineResponse rebootVirtualMachine(String virtualMachineId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("rebootVirtualMachine", null);
        arguments.add(new NameValuePair("id", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getRebootVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into RebootVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private RebootVirtualMachineResponse getRebootVirtualMachineResponse(Document doc) {
        RebootVirtualMachineResponse response = new RebootVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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
        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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
            
        }

        return response;
    }

    /**
     * Starts a virtual machine.
     *
     * @param virtualMachineId The ID of the virtual machine
     * @return
     * @throws Exception
     */
    public StartVirtualMachineResponse startVirtualMachine(String virtualMachineId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("startVirtualMachine", null);
        arguments.add(new NameValuePair("id", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getStartVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into StartVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private StartVirtualMachineResponse getStartVirtualMachineResponse(Document doc) {
        StartVirtualMachineResponse response = new StartVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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
        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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
            
        }

        return response;
    }

    /**
     * Stops a virtual machine.
     *
     * @param virtualMachineId The ID of the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public StopVirtualMachineResponse stopVirtualMachine(String virtualMachineId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("stopVirtualMachine", optional);
        arguments.add(new NameValuePair("id", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getStopVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into StopVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private StopVirtualMachineResponse getStopVirtualMachineResponse(Document doc) {
        StopVirtualMachineResponse response = new StopVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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
        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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
            
        }

        return response;
    }

    /**
     * Resets the password for virtual machine. The virtual machine must be in a "Stopped" state and the template must
     * already support this feature for this command to take effect. [async]
     *
     * @param virtualMachineId
     * @param optional
     * @return
     * @throws Exception
     */
    public ResetPasswordForVirtualMachineResponse resetPasswordForVirtualMachine(String virtualMachineId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("resetPasswordForVirtualMachine", null);
        arguments.add(new NameValuePair("id", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getResetPasswordForVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into ResetPasswordForVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private ResetPasswordForVirtualMachineResponse getResetPasswordForVirtualMachineResponse(Document doc) {
        ResetPasswordForVirtualMachineResponse response = new ResetPasswordForVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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
        }

        return response;

    }

    /**
     * Changes the service offering for a virtual machine. The virtual machine must be in a "Stopped" state for this
     * command to take effect.
     *
     * @param virtualMachineId The ID of the virtual machine
     * @param serviceOfferingId the service offering ID to apply to the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public ChangeServiceForVirtualMachineResponse changeServiceForVirtualMachine(String virtualMachineId,
            String serviceOfferingId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("changeServiceForVirtualMachine", null);
        arguments.add(new NameValuePair("id", virtualMachineId));
        arguments.add(new NameValuePair("serviceofferingid", serviceOfferingId));

        Document responseDocument = server.makeRequest(arguments);

        return getChangeServiceForVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into ChangeServiceForVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private ChangeServiceForVirtualMachineResponse getChangeServiceForVirtualMachineResponse(Document doc) {
        ChangeServiceForVirtualMachineResponse response = new ChangeServiceForVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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
        }

        return response;

    }

    /**
     * Updates parameters of a virtual machine.
     *
     * @param virtualMachineId The ID of the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateVirtualMachineResponse updateVirtualMachine(String virtualMachineId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateVirtualMachine", optional);
        arguments.add(new NameValuePair("id", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private UpdateVirtualMachineResponse getUpdateVirtualMachineResponse(Document doc) {
        UpdateVirtualMachineResponse response = new UpdateVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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
        }

        return response;
    }

    /**
     * Recovers a virtual machine.
     *
     * @param virtualMachineId The ID of the virtual machine
     * @return
     * @throws Exception
     */
    public RecoverVirtualMachineResponse recoverVirtualMachine(String virtualMachineId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("recoverVirtualMachine", null);
        arguments.add(new NameValuePair("id", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getRecoverVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into RecoverVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private RecoverVirtualMachineResponse getRecoverVirtualMachineResponse(Document doc) {
        RecoverVirtualMachineResponse response = new RecoverVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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
        }

        return response;

    }

    /**
     * List the virtual machines owned by the account.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListVirtualMachinesResponse listVirtualMachines(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVirtualMachines", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListVirtualMachinesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListLoadBalancerRuleInstancesResponse object
     *
     * @param doc
     * @return
     */
    private ListVirtualMachinesResponse getListVirtualMachinesResponse(Document doc) {
        ListVirtualMachinesResponse response = new ListVirtualMachinesResponse();

        NodeList list = doc.getElementsByTagName("virtualmachine");

        List<VirtualMachineResponse> virtualMachines = new LinkedList<VirtualMachineResponse>();
        if (list.getLength() > 0) {
            for (int virtualMachineIndex = 0; virtualMachineIndex < list.getLength(); virtualMachineIndex++) {
                Node virtualMachineNode = list.item(virtualMachineIndex);

                VirtualMachineResponse virtualMachine = new VirtualMachineResponse();
                List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
                List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
                List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList virtualMachineProperties = virtualMachineNode.getChildNodes();
                for (int childIndex = 0; childIndex < virtualMachineProperties.getLength(); childIndex++) {
                    Node property = virtualMachineProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        virtualMachine.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        virtualMachine.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("cpunumber")) {
                        virtualMachine.setCpuNumber(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuspeed")) {
                        virtualMachine.setCpuSpeed(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        virtualMachine.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("diskioread")) {
                        virtualMachine.setDiskIoRead(property.getTextContent());
                    } else if (property.getNodeName().equals("diskiowrite")) {
                        virtualMachine.setDiskIoWrite(property.getTextContent());
                    } else if (property.getNodeName().equals("diskkbsread")) {
                        virtualMachine.setDiskKbsRead(property.getTextContent());
                    } else if (property.getNodeName().equals("diskkbswrite")) {
                        virtualMachine.setDiskKbsWrite(property.getTextContent());
                    } else if (property.getNodeName().equals("displayname")) {
                        virtualMachine.setDisplayName(property.getTextContent());
                    } else if (property.getNodeName().equals("displayvm")) {
                        virtualMachine.setDisplayVm(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        virtualMachine.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuused")) {
                        virtualMachine.setCpuUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        virtualMachine.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("forvirtualnetwork")) {
                        virtualMachine.setForVirtualNetwork(property.getTextContent());
                    } else if (property.getNodeName().equals("group")) {
                        virtualMachine.setGroup(property.getTextContent());
                    } else if (property.getNodeName().equals("groupid")) {
                        virtualMachine.setGroupId(property.getTextContent());
                    } else if (property.getNodeName().equals("guestosid")) {
                        virtualMachine.setGuestOsId(property.getTextContent());
                    } else if (property.getNodeName().equals("haenable")) {
                        virtualMachine.setHaEnable(property.getTextContent());
                    } else if (property.getNodeName().equals("hostid")) {
                        virtualMachine.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostname")) {
                        virtualMachine.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                        virtualMachine.setHyperVisor(property.getTextContent());
                    } else if (property.getNodeName().equals("instancename")) {
                        virtualMachine.setInstanceName(property.getTextContent());
                    } else if (property.getNodeName().equals("isdynamicallyscalable")) {
                        virtualMachine.setIsDynamicallyScalable(property.getTextContent());
                    } else if (property.getNodeName().equals("isodisplaytext")) {
                        virtualMachine.setIsoDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("isoid")) {
                        virtualMachine.setIsoId(property.getTextContent());
                    } else if (property.getNodeName().equals("isoname")) {
                        virtualMachine.setIsoName(property.getTextContent());
                    } else if (property.getNodeName().equals("memory")) {
                        virtualMachine.setMemory(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        virtualMachine.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbsread")) {
                        virtualMachine.setNetworkKbsRead(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbswrite")) {
                        virtualMachine.setNetworkKbsWrite(property.getTextContent());
                    } else if (property.getNodeName().equals("password")) {
                        virtualMachine.setPassword(property.getTextContent());
                    } else if (property.getNodeName().equals("passwordenabled")) {
                        virtualMachine.setPasswordEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        virtualMachine.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        virtualMachine.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("publicip")) {
                        virtualMachine.setPublicIp(property.getTextContent());
                    } else if (property.getNodeName().equals("publicipid")) {
                        virtualMachine.setPublicIpId(property.getTextContent());
                    } else if (property.getNodeName().equals("rootdeviceid")) {
                        virtualMachine.setRootDeviceId(property.getTextContent());
                    } else if (property.getNodeName().equals("rootdevicetype")) {
                        virtualMachine.setRootDeviceType(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingid")) {
                        virtualMachine.setServiceOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingname")) {
                        virtualMachine.setServiceOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        virtualMachine.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("templatedisplaytext")) {
                        virtualMachine.setTemplateDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("templateid")) {
                        virtualMachine.setTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("templatename")) {
                        virtualMachine.setTemplateName(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        virtualMachine.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        virtualMachine.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("affinitygroup")) {
                        NodeList affinityGroupProperties = property.getChildNodes();
                        if (affinityGroupProperties.getLength() > 0) {
                            AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                            for (int affinityGroupIndex = 0; affinityGroupIndex < affinityGroupProperties.getLength(); affinityGroupIndex++) {
                                Node affinityGroupProperty = affinityGroupProperties.item(affinityGroupIndex);
                                if (affinityGroupProperty.getNodeName().equals("id")) {
                                    affinityGroup.setId(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("account")) {
                                    affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("description")) {
                                    affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                                    affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                                    affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("name")) {
                                    affinityGroup.setName(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("type")) {
                                    affinityGroup.setType(affinityGroupProperty.getTextContent());
                                } else if (affinityGroupProperty.getNodeName().equals("virtualmachineids")) {
                                    affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                                }
                            }

                            affinityGroups.add(affinityGroup);
                        }
                    } else if (property.getNodeName().equals("nic")) {
                        NodeList networkInterfaceCardProperties = property.getChildNodes();
                        if (networkInterfaceCardProperties.getLength() > 0) {
                            NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                            for (int networkInterfaceCardIndex = 0; networkInterfaceCardIndex < networkInterfaceCardProperties.getLength(); networkInterfaceCardIndex++) {
                                Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(networkInterfaceCardIndex);
                                if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                                    networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                                    networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                                    networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                                    networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                                    networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                                    networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                                    networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                                    networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                                    networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                                    networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                                    networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                                    networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                                    networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                                    networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                                    networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                                } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                                    networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                                }
                            }
                            networkInterfaceCards.add(networkInterfaceCard);
                        }

                    } else if (property.getNodeName().equals("securitygroup")) {
                        NodeList securityGroupProperties = property.getChildNodes();
                        if (securityGroupProperties.getLength() > 0) {
                            SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                            List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                            List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                            for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                                Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);
                                if (securityGroupProperty.getNodeName().equals("id")) {
                                    securityGroup.setId(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("account")) {
                                    securityGroup.setAccount(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("description")) {
                                    securityGroup.setDescription(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("domain")) {
                                    securityGroup.setDomain(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                                    securityGroup.setDomainId(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("name")) {
                                    securityGroup.setName(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("project")) {
                                    securityGroup.setProject(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                                    securityGroup.setProjectId(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                                    NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                                    if (egressRuleProperties.getLength() > 0) {
                                        EgressRuleResponse egressRule = new EgressRuleResponse();
                                        for (int egressRuleIndex = 0; egressRuleIndex < egressRuleProperties.getLength(); egressRuleIndex++) {
                                            Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                            if (egressRuleProperty.getNodeName().equals("account")) {
                                                egressRule.setAccount(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                                egressRule.setCidr(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                                egressRule.setEndPort(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                                egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                                egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                                egressRule.setProtocol(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                                egressRule.setRuleId(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                                egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                            } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                                egressRule.setStartPort(egressRuleProperty.getTextContent());
                                            }
                                        }
                                        egressRules.add(egressRule);
                                    }
                                } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                                    NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                                    if (ingressRuleProperties.getLength() > 0) {
                                        IngressRuleResponse ingressRule = new IngressRuleResponse();
                                        for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                            Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);
                                            if (ingressRuleProperty.getNodeName().equals("account")) {
                                                ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                                ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                                ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                                ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                                ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                                ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                                ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                                ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                            } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                                ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                            }
                                        }
                                        ingressRules.add(ingressRule);
                                    }
                                } else if (securityGroupProperty.getNodeName().equals("tags")) {
                                    NodeList tagsProperties = securityGroupProperty.getChildNodes();
                                    if (tagsProperties.getLength() > 0) {
                                        TagsResponse tags = new TagsResponse();
                                        for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                            Node tagsProperty = tagsProperties.item(tagsIndex);
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
                                    }
                                } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                                    securityGroup.setJobId(securityGroupProperty.getTextContent());
                                } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                                    securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                                }
                            }
                            securityGroup.setEgressRules(egressRules);
                            securityGroup.setIngressRules(ingressRules);
                            securityGroup.setTagss(tagss);
                            securityGroups.add(securityGroup);
                        }
                    } else if (property.getNodeName().equals("tags")) {
                        NodeList tagsProperties = property.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);
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
                                tagss.add(tags);
                            }

                        }
                    } else if (property.getNodeName().equals("jobid")) {
                        virtualMachine.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        virtualMachine.setJobStatus(property.getTextContent());
                    }
                }
                virtualMachine.setTagss(tagss);
                virtualMachine.setAffinityGroups(affinityGroups);
                virtualMachine.setNetworkInterfaceCards(networkInterfaceCards);
                virtualMachine.setSecurityGroups(securityGroups);
                virtualMachines.add(virtualMachine);
            }
        }
        response.setVirtualMachines(virtualMachines);
        return response;
    }

    /**
     * Returns an encrypted password for the virtual machine
     *
     * @param virtualMachineId the ID of the virtual machine
     * @return
     * @throws Exception
     */
    public GetVirtualMachinePasswordResponse getVMPassword(String virtualMachineId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("getVMPassword", null);
        arguments.add(new NameValuePair("id", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getGetVirtualMachinePasswordResponse(responseDocument);
    }

    /**
     * Converts XML document into GetVirtualMachinePasswordResponse object
     *
     * @param doc
     * @return
     */
    private GetVirtualMachinePasswordResponse getGetVirtualMachinePasswordResponse(Document doc) {
        GetVirtualMachinePasswordResponse response = new GetVirtualMachinePasswordResponse();

        // get encryptedpassword from XML and set the encrypted password  of the virtual machine      
        NodeList list = doc.getElementsByTagName("encryptedpassword");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEncryptedPassword(node.getTextContent());
        }

        return response;

    }

    /**
     * attempts Migration of a Virtual Machine to a different host or Root volume of the Virtual Machine to a different
     * storage pool
     *
     * @param virtualMachineId the ID of the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public MigrateVirtualMachineResponse migrateVirtualMachine(String virtualMachineId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("migrateVirtualMachine", optional);
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getMigrateVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into MigrateVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private MigrateVirtualMachineResponse getMigrateVirtualMachineResponse(Document doc) {
        MigrateVirtualMachineResponse response = new MigrateVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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
        }

        return response;

    }

    /**
     * Move a user virtual Machine to another user under same domain.
     *
     * @param newName Account name of the new virtual Machine owner.
     * @param newDomainId Domain id of the new virtual Machine owner.
     * @param virtualMachineId the virtual Machine ID of the user virtual machine to be moved
     * @param optional
     * @return
     * @throws Exception
     */
    public AssignVirtualMachineResponse assignVirtualMachine(String newName,
            String newDomainId, String virtualMachineId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("assignVirtualMachine", optional);
        arguments.add(new NameValuePair("account", newName));
        arguments.add(new NameValuePair("domainid", newDomainId));
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getAssignVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into AssignVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private AssignVirtualMachineResponse getAssignVirtualMachineResponse(Document doc) {
        AssignVirtualMachineResponse response = new AssignVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                        networkInterfaceCard.setId(property.getTextContent());
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
                        networkInterfaceCard.setType(property.getTextContent());
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
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }
        }

        // gets associated tags values for the virtual machine
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
     * Restore a Virtual Machine to original template or specific snapshot
     *
     * @param virtualMachineId Virtual Machine ID
     * @return
     * @throws Exception
     */
    public RestoreVirtualMachineResponse restoreVirtualMachine(String virtualMachineId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("restoreVirtualMachine", null);
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getRestoreVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into RestoreVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private RestoreVirtualMachineResponse getRestoreVirtualMachineResponse(Document doc) {
        RestoreVirtualMachineResponse response = new RestoreVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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
        
        // get jobid number from XML and set the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }

            // gets associated tag values for the virtual machine
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

            // get jobstatus number from XML and set the current status of the latest async job acting on this object
            list = doc.getElementsByTagName("jobstatus");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setJobStatus(node.getTextContent());
            }
        }

        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for VirtualMachine.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public VirtualMachineJobResultResponse virtualMachineJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getVirtualMachineJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into VolumeJobResultResponse object
     *
     * @param doc
     * @return
     */
    private VirtualMachineJobResultResponse getVirtualMachineJobResultResponse(Document doc) {
        VirtualMachineJobResultResponse response = new VirtualMachineJobResultResponse();

        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousCmd(node.getTextContent());
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobProgressStatus(node.getTextContent());
        }

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            NodeList node = list.item(0).getChildNodes();
            for (int index = 0; index < node.getLength(); index++) {
                Node nodeProperty = node.item(index);
                if (nodeProperty.getNodeName().equals("virtualmachine")) {
                    List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
                    List<NetworkInterfaceCardResponse> networkInterfaceCards = new LinkedList<NetworkInterfaceCardResponse>();
                    List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
                    List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node property = childNodeProperties.item(childIndex);
                        if (property.getNodeName().equals("id")) {
                            response.setId(property.getTextContent());
                        } else if (property.getNodeName().equals("account")) {
                            response.setAccount(property.getTextContent());
                        } else if (property.getNodeName().equals("cpunumber")) {
                            response.setCpuNumber(property.getTextContent());
                        } else if (property.getNodeName().equals("cpuspeed")) {
                            response.setCpuSpeed(property.getTextContent());
                        } else if (property.getNodeName().equals("created")) {
                            response.setCreated(property.getTextContent());
                        } else if (property.getNodeName().equals("diskioread")) {
                            response.setDiskIoRead(property.getTextContent());
                        } else if (property.getNodeName().equals("diskiowrite")) {
                            response.setDiskIoWrite(property.getTextContent());
                        } else if (property.getNodeName().equals("diskkbsread")) {
                            response.setDiskKbsRead(property.getTextContent());
                        } else if (property.getNodeName().equals("diskkbswrite")) {
                            response.setDiskKbsWrite(property.getTextContent());
                        } else if (property.getNodeName().equals("displayname")) {
                            response.setDisplayName(property.getTextContent());
                        } else if (property.getNodeName().equals("displayvm")) {
                            response.setDisplayVm(property.getTextContent());
                        } else if (property.getNodeName().equals("domain")) {
                            response.setDomain(property.getTextContent());
                        } else if (property.getNodeName().equals("domainid")) {
                            response.setDomainId(property.getTextContent());
                        } else if (property.getNodeName().equals("forvirtualnetwork")) {
                            response.setForVirtualNetwork(property.getTextContent());
                        } else if (property.getNodeName().equals("group")) {
                            response.setGroup(property.getTextContent());
                        } else if (property.getNodeName().equals("groupid")) {
                            response.setGroupId(property.getTextContent());
                        } else if (property.getNodeName().equals("guestosid")) {
                            response.setGuestOsId(property.getTextContent());
                        } else if (property.getNodeName().equals("haenable")) {
                            response.setHaEnable(property.getTextContent());
                        } else if (property.getNodeName().equals("hostid")) {
                            response.setHostId(property.getTextContent());
                        } else if (property.getNodeName().equals("hostname")) {
                            response.setHostName(property.getTextContent());
                        } else if (property.getNodeName().equals("hypervisor")) {
                            response.setHyperVisor(property.getTextContent());
                        } else if (property.getNodeName().equals("instancename")) {
                            response.setInstanceName(property.getTextContent());
                        } else if (property.getNodeName().equals("isdynamicallyscalable")) {
                            response.setIsDynamicallyScalable(property.getTextContent());
                        } else if (property.getNodeName().equals("isodisplaytext")) {
                            response.setIsoDisplayText(property.getTextContent());
                        } else if (property.getNodeName().equals("isoid")) {
                            response.setIsoId(property.getTextContent());
                        } else if (property.getNodeName().equals("isoname")) {
                            response.setIsoName(property.getTextContent());
                        } else if (property.getNodeName().equals("memory")) {
                            response.setMemory(property.getTextContent());
                        } else if (property.getNodeName().equals("name")) {
                            response.setName(property.getTextContent());
                        } else if (property.getNodeName().equals("networkkbsread")) {
                            response.setNetworkKbsRead(property.getTextContent());
                        } else if (property.getNodeName().equals("networkkbswrite")) {
                            response.setNetworkKbsWrite(property.getTextContent());
                        } else if (property.getNodeName().equals("password")) {
                            response.setPassword(property.getTextContent());
                        } else if (property.getNodeName().equals("passwordenabled")) {
                            response.setPasswordEnabled(property.getTextContent());
                        } else if (property.getNodeName().equals("project")) {
                            response.setProject(property.getTextContent());
                        } else if (property.getNodeName().equals("projectid")) {
                            response.setProjectId(property.getTextContent());
                        } else if (property.getNodeName().equals("publicip")) {
                            response.setPublicIp(property.getTextContent());
                        } else if (property.getNodeName().equals("publicipid")) {
                            response.setPublicIpId(property.getTextContent());
                        } else if (property.getNodeName().equals("rootdeviceid")) {
                            response.setRootDeviceId(property.getTextContent());
                        } else if (property.getNodeName().equals("rootdevicetype")) {
                            response.setRootDeviceType(property.getTextContent());
                        } else if (property.getNodeName().equals("serviceofferingid")) {
                            response.setServiceOfferingId(property.getTextContent());
                        } else if (property.getNodeName().equals("serviceofferingname")) {
                            response.setServiceOfferingName(property.getTextContent());
                        } else if (property.getNodeName().equals("state")) {
                            response.setState(property.getTextContent());
                        } else if (property.getNodeName().equals("templatedisplaytext")) {
                            response.setTemplateDisplayText(property.getTextContent());
                        } else if (property.getNodeName().equals("templateid")) {
                            response.setTemplateId(property.getTextContent());
                        } else if (property.getNodeName().equals("templatename")) {
                            response.setTemplateName(property.getTextContent());
                        } else if (property.getNodeName().equals("zoneid")) {
                            response.setZoneId(property.getTextContent());
                        } else if (property.getNodeName().equals("zonename")) {
                            response.setZoneName(property.getTextContent());
                        } else if (property.getNodeName().equals("affinitygroup")) {
                            NodeList affinityGroupProperties = property.getChildNodes();
                            if (affinityGroupProperties.getLength() > 0) {
                                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                                for (int affinityGroupIndex = 0; affinityGroupIndex < affinityGroupProperties.getLength(); affinityGroupIndex++) {
                                    Node affinityGroupProperty = affinityGroupProperties.item(affinityGroupIndex);
                                    if (affinityGroupProperty.getNodeName().equals("id")) {
                                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineids")) {
                                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                                    }
                                }
                                affinityGroups.add(affinityGroup);
                            }

                        } else if (property.getNodeName().equals("nic")) {
                            NodeList networkInterfaceCardProperties = property.getChildNodes();
                            if (networkInterfaceCardProperties.getLength() > 0) {
                                NetworkInterfaceCardResponse networkInterfaceCard = new NetworkInterfaceCardResponse();
                                for (int networkInterfaceCardIndex = 0; networkInterfaceCardIndex < networkInterfaceCardProperties.getLength(); networkInterfaceCardIndex++) {
                                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(networkInterfaceCardIndex);
                                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                                    }
                                }
                                networkInterfaceCards.add(networkInterfaceCard);
                            }

                        } else if (property.getNodeName().equals("securitygroup")) {
                            NodeList securityGroupProperties = property.getChildNodes();
                            if (securityGroupProperties.getLength() > 0) {
                                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);
                                    if (securityGroupProperty.getNodeName().equals("id")) {
                                        securityGroup.setId(securityGroupProperty.getTextContent());
                                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                                        securityGroup.setName(securityGroupProperty.getTextContent());
                                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                                        securityGroup.setProject(securityGroupProperty.getTextContent());
                                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                                        if (egressRuleProperties.getLength() > 0) {
                                            EgressRuleResponse egressRule = new EgressRuleResponse();
                                            for (int egressRuleIndex = 0; egressRuleIndex < egressRuleProperties.getLength(); egressRuleIndex++) {
                                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                                if (egressRuleProperty.getNodeName().equals("account")) {
                                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                                }
                                            }
                                            egressRules.add(egressRule);
                                        }
                                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                                        if (ingressRuleProperties.getLength() > 0) {
                                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);
                                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                                }
                                            }
                                            ingressRules.add(ingressRule);
                                        }
                                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                                        if (tagsProperties.getLength() > 0) {
                                            TagsResponse tags = new TagsResponse();
                                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                                Node tagsProperty = tagsProperties.item(tagsIndex);

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
                                        }
                                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                                    }
                                }
                                securityGroup.setTagss(tagss);
                                securityGroup.setEgressRules(egressRules);
                                securityGroup.setIngressRules(ingressRules);
                                securityGroups.add(securityGroup);
                            }
                        } else if (property.getNodeName().equals("tags")) {
                            NodeList tagsProperties = property.getChildNodes();
                            if (tagsProperties.getLength() > 0) {
                                TagsResponse tags = new TagsResponse();
                                for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                    Node tagsProperty = tagsProperties.item(tagsIndex);

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
                            }
                        } else if (property.getNodeName().equals("jobid")) {
                            response.setJobId(property.getTextContent());
                        } else if (property.getNodeName().equals("jobstatus")) {
                            response.setJobStatus(property.getTextContent());
                        }
                    }
                    response.setNetworkInterfaceCards(networkInterfaceCards);
                    response.setSecurityGroups(securityGroups);
                } else if (nodeProperty.getNodeName().equals("errorcode")) {
                    response.setErrorCode(nodeProperty.getTextContent());
                } else if (nodeProperty.getNodeName().equals("errortext")) {
                    response.setErrorText(nodeProperty.getTextContent());
                }
            }
        }

        // get jobresultcode from XML and set as the result code for the job
        list = doc.getElementsByTagName("jobresultcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsynchronousJobId(node.getTextContent());
        }

        return response;
    }

    /**
     * attempts Migration of a Virtual Machine to a different host or Root volume of the Virtual Machine to a different
     * storage pool
     *
     * @param virtualMachineId the ID of the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public MigrateVirtualMachineWithVolumeResponse migrateVirtualMachineWithVolume(String hostId, String virtualMachineId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("migrateVirtualMachineWithVolume", optional);
        arguments.add(new NameValuePair("hostid", hostId));
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getMigrateVirtualMachineWithVolumeResponse(responseDocument);
    }

    /**
     * Converts XML document into MigrateVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private MigrateVirtualMachineWithVolumeResponse getMigrateVirtualMachineWithVolumeResponse(Document doc) {
        MigrateVirtualMachineWithVolumeResponse response = new MigrateVirtualMachineWithVolumeResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }
        }

        // gets associated tag values for the virtual machine
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
     * cleans VM Reservations in database
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public CleanVMReservationsResponse cleanVMReservations()
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("cleanVMReservations", null);

        Document responseDocument = server.makeRequest(arguments);

        return getCleanVMReservationsResponse(responseDocument);
    }

    /**
     * Converts XML document into cleanVMReservationsResponse object
     *
     * @param doc
     * @return
     */
    private CleanVMReservationsResponse getCleanVMReservationsResponse(Document doc) {
        CleanVMReservationsResponse response = new CleanVMReservationsResponse();

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
     * Adds VM to specified network by creating a NIC
     *
     * @param networkId
     *
     * @param virtualMachineId the ID of the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public AddNicToVirtualMachineResponse addNicToVirtualMachine(String networkId, String virtualMachineId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addNicToVirtualMachine", optional);
        arguments.add(new NameValuePair("networkid", networkId));
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getAddNicToVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into MigrateVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private AddNicToVirtualMachineResponse getAddNicToVirtualMachineResponse(Document doc) {
        AddNicToVirtualMachineResponse response = new AddNicToVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }
        }

        // gets associated tag values for the virtual machine
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
     * Removes VM from specified network by deleting a NIC
     *
     * @param nicId The ID of the network interface card
     * @param virtualmachineId The ID of the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public RemoveNicFromVirtualMachineResponse removeNicFromVirtualMachine(String nicId,
            String virtualMachineId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("removeNicFromVirtualMachine", optional);
        arguments.add(new NameValuePair("nicid", nicId));
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getRemoveNicFromVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into DeployVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private RemoveNicFromVirtualMachineResponse getRemoveNicFromVirtualMachineResponse(Document doc) {
        RemoveNicFromVirtualMachineResponse response = new RemoveNicFromVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }
        }

        // gets associated tags values for the virtual machine
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
     * Changes the default NIC on a VM
     *
     * @param nicId The ID of the network interface card
     * @param virtualmachineId The ID of the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateDefaultNicForVirtualMachineResponse updateDefaultNicForVirtualMachine(String nicId,
            String virtualMachineId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateDefaultNicForVirtualMachine", optional);
        arguments.add(new NameValuePair("nicid", nicId));
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateDefaultNicForVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into DeployVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private UpdateDefaultNicForVirtualMachineResponse getUpdateDefaultNicForVirtualMachineResponse(Document doc) {
        UpdateDefaultNicForVirtualMachineResponse response = new UpdateDefaultNicForVirtualMachineResponse();

        // get Id from XML and set the ID of the virtual machine      
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account associated with the virtual machine
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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

        // get diskioread from XML and set the diskioread associated with the virtual machine
        list = doc.getElementsByTagName("diskioread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoRead(node.getTextContent());
        }

        // get diskiowrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskiowrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskIoWrite(node.getTextContent());
        }

        // get diskkbsread from XML and set the diskkbsread associated with the virtual machine
        list = doc.getElementsByTagName("diskkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsRead(node.getTextContent());
        }

        // get diskkbswrite from XML and set the diskiowrite associated with the virtual machine
        list = doc.getElementsByTagName("diskkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskKbsWrite(node.getTextContent());
        }

        // get displayname from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayName(node.getTextContent());
        }

        // get displayvm from XML and set user generated name. The name of the virtual machine is returned if no displayname exists.
        list = doc.getElementsByTagName("displayvm");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayVm(node.getTextContent());
        }

        // get domain from XML and set the name of the domain in which the virtual machine exists
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setHaEnable(node.getTextContent());
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

        // get projectid from XML and the project id of the vm
        list = doc.getElementsByTagName("instancename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInstanceName(node.getTextContent());
        }

        // true if vm contains XS/VMWare tools inorder to support dynamic scaling of VM cpu/memory
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get keyboard from XML set and as an alternate display text of the ISO attached to the virtual machine
        list = doc.getElementsByTagName("keypair");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKeyPair(node.getTextContent());
        }

        // get memory from XML and set the memory allocated for the virtual machine
        list = doc.getElementsByTagName("memory");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemory(node.getTextContent());
        }

        // get name from XML and set the name of the virtual machine
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set the incoming network traffic on the virtual machine
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkKbsWrite(node.getTextContent());
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
            response.setProject(node.getTextContent());
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
            response.setPublicIpId(node.getTextContent());
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

        // get serviceofferingname from XML and set as the name of the service offering of the virtual machine
        list = doc.getElementsByTagName("servicestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceState(node.getTextContent());
        }

        // get state from XML and set as the state of the virtual machine
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
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

        //the list of AffinityGroups associated with virtual machine
        list = doc.getElementsByTagName("affinitygroup");
        if (list.getLength() > 0) {
            List<AffinityGroupResponse> affinityGroups = new LinkedList<AffinityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node affinityGroupNode = list.item(index);
                AffinityGroupResponse affinityGroup = new AffinityGroupResponse();
                NodeList affinityGroupProperties = affinityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < affinityGroupProperties.getLength(); childIndex++) {
                    Node affinityGroupProperty = affinityGroupProperties.item(childIndex);

                    if (affinityGroupProperty.getNodeName().equals("id")) {
                        affinityGroup.setId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("account")) {
                        affinityGroup.setAccount(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("description")) {
                        affinityGroup.setDescription(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domain")) {
                        affinityGroup.setDomain(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("domainid")) {
                        affinityGroup.setDomainId(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("name")) {
                        affinityGroup.setName(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("type")) {
                        affinityGroup.setType(affinityGroupProperty.getTextContent());
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineVirtualMachineIds")) {
                        affinityGroup.setVirtualMachineIds(affinityGroupProperty.getTextContent());
                    }
                }
                affinityGroups.add(affinityGroup);
            }
            response.setAffinityGroups(affinityGroups);
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
                    Node networkInterfaceCardProperty = networkInterfaceCardProperties.item(childIndex);

                    if (networkInterfaceCardProperty.getNodeName().equals("id")) {
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("broadcasturi")) {
                        networkInterfaceCard.setBroadcastUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("gateway")) {
                        networkInterfaceCard.setGateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6address")) {
                        networkInterfaceCard.setIp6Address(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6cidr")) {
                        networkInterfaceCard.setIp6Cidr(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ip6gateway")) {
                        networkInterfaceCard.setIp6Gateway(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("ipaddress")) {
                        networkInterfaceCard.setIpAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isdefault")) {
                        networkInterfaceCard.setIsDefault(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("isolationuri")) {
                        networkInterfaceCard.setIsolationUri(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("macaddress")) {
                        networkInterfaceCard.setMacAddress(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("netmask")) {
                        networkInterfaceCard.setNetMask(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkid")) {
                        networkInterfaceCard.setNetworkId(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("networkname")) {
                        networkInterfaceCard.setNetworkName(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("secondaryip")) {
                        networkInterfaceCard.setSecondaryIp(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("traffictype")) {
                        networkInterfaceCard.setTrafficType(networkInterfaceCardProperty.getTextContent());
                    } else if (networkInterfaceCardProperty.getNodeName().equals("type")) {
                        networkInterfaceCard.setType(networkInterfaceCardProperty.getTextContent());
                    }
                }
                networkInterfaceCards.add(networkInterfaceCard);
            }
            response.setNetworkInterfaceCards(networkInterfaceCards);
        }

        //list of security groups associated with the virtual machine
        list = doc.getElementsByTagName("securitygroup");
        if (list.getLength() > 0) {
            List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>();
            for (int index = 0; index < list.getLength(); index++) {
                Node securityGroupNode = list.item(index);
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                for (int securityGroupIndex = 0; securityGroupIndex < securityGroupProperties.getLength(); securityGroupIndex++) {
                    Node securityGroupProperty = securityGroupProperties.item(securityGroupIndex);

                    if (securityGroupProperty.getNodeName().equals("id")) {
                        securityGroup.setId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("account")) {
                        securityGroup.setAccount(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("description")) {
                        securityGroup.setDescription(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domain")) {
                        securityGroup.setDomain(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("name")) {
                        securityGroup.setName(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("project")) {
                        securityGroup.setProject(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                        if (egressRuleProperties.getLength() > 0) {
                            EgressRuleResponse egressRule = new EgressRuleResponse();
                            for (int egressRuleIndex = 0; egressRuleIndex < list.getLength(); egressRuleIndex++) {
                                Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                if (egressRuleProperty.getNodeName().equals("account")) {
                                    egressRule.setAccount(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                    egressRule.setCidr(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                    egressRule.setEndPort(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                    egressRule.setIcmpCode(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                    egressRule.setIcmpType(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                    egressRule.setProtocol(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                    egressRule.setRuleId(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                    egressRule.setStartPort(egressRuleProperty.getTextContent());
                                }

                            }

                            egressRules.add(egressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = securityGroupProperty.getChildNodes();
                        if (ingressRuleProperties.getLength() > 0) {
                            IngressRuleResponse ingressRule = new IngressRuleResponse();
                            for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);

                                if (ingressRuleProperty.getNodeName().equals("account")) {
                                    ingressRule.setAccount(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                    ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                    ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                    ingressRule.setIcmpCode(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                    ingressRule.setIcmpType(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                    ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                    ingressRule.setRuleId(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                    ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                    ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                }

                            }
                            ingressRules.add(ingressRule);

                        }
                    } else if (securityGroupProperty.getNodeName().equals("tags")) {
                        NodeList tagsProperties = securityGroupProperty.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                Node tagsProperty = tagsProperties.item(tagsIndex);

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

                        }
                    } else if (securityGroupProperty.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(securityGroupProperty.getTextContent());
                    } else if (securityGroupProperty.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(securityGroupProperty.getTextContent());
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroup.setTagss(tagss);
                    securityGroups.add(securityGroup);
                }
                response.setSecurityGroups(securityGroups);
            }
        }

        // gets associated tags values for the virtual machine
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

}