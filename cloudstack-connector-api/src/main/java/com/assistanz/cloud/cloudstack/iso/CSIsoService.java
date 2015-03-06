package com.assistanz.cloud.cloudstack.iso;

import com.assistanz.cloud.cloudstack.AffinityGroupResponse;
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

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("attachIso", null);
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
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineIds")) {
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
                        networkInterfaceCard.setId(networkInterfaceCardProperty.getTextContent());
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
     * Detaches any ISO file (if any) currently attached to a virtual machine.
     *
     * @param virtualMachineId The ID of the virtual machine
     * @return
     * @throws Exception
     */
    public DetachISOResponse detachIso(String virtualMachineId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("detachIso", null);
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
                    } else if (affinityGroupProperty.getNodeName().equals("virtualmachineIds")) {
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
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < securityGroupProperties.getLength(); childIndex++) {
                    Node property = list.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        securityGroup.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        securityGroup.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        securityGroup.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        securityGroup.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        securityGroup.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        securityGroup.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        securityGroup.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        securityGroup.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("egressrule")) {
                        NodeList egressRuleProperties = property.getChildNodes();
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
                            response.setEgressRules(egressRules);
                        }

                    } else if (property.getNodeName().equals("ingressrule")) {
                        NodeList ingressRuleProperties = property.getChildNodes();
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
                            response.setIngressRules(ingressRules);
                        }
                    } else if (property.getNodeName().equals("tags")) {
                        NodeList tagsProperties = property.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                property = tagsProperties.item(tagsIndex);

                                if (property.getNodeName().equals("account")) {
                                    tags.setAccount(property.getTextContent());
                                } else if (property.getNodeName().equals("customer")) {
                                    tags.setCustomer(property.getTextContent());
                                } else if (property.getNodeName().equals("domain")) {
                                    tags.setDomain(property.getTextContent());
                                } else if (property.getNodeName().equals("domainid")) {
                                    tags.setDomainId(property.getTextContent());
                                } else if (property.getNodeName().equals("key")) {
                                    tags.setKey(property.getTextContent());
                                } else if (property.getNodeName().equals("project")) {
                                    tags.setProject(property.getTextContent());
                                } else if (property.getNodeName().equals("projectid")) {
                                    tags.setProjectId(property.getTextContent());
                                } else if (property.getNodeName().equals("resourceid")) {
                                    tags.setResourceId(property.getTextContent());
                                } else if (property.getNodeName().equals("resourcetype")) {
                                    tags.setResourceType(property.getTextContent());
                                } else if (property.getNodeName().equals("value")) {
                                    tags.setValue(property.getTextContent());
                                }

                            }

                            tagss.add(tags);
                            response.setTagss(tagss);
                        }
                    } else if (property.getNodeName().equals("jobid")) {
                        securityGroup.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        securityGroup.setJobStatus(property.getTextContent());
                    }

                    securityGroups.add(securityGroup);
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
    public ListIsoResponse listIsos(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listIsos", optional);

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

                IsoResponse iso = new IsoResponse();
                List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                NodeList isoProperties = isoNode.getChildNodes();

                for (int childIndex = 0; childIndex < isoProperties.getLength(); childIndex++) {
                    Node property = isoProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        iso.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        iso.setAccount(property.getTextContent());
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
                        iso.setDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        iso.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        iso.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("format")) {
                        iso.setFormat(property.getTextContent());
                    } else if (property.getNodeName().equals("hostid")) {
                        iso.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostname")) {
                        iso.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                        iso.setHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("isdynamicallyscalable")) {
                        iso.setIsDynamicallyScalable(property.getTextContent());
                    } else if (property.getNodeName().equals("isextractable")) {
                        iso.setIsExtractable(property.getTextContent());
                    } else if (property.getNodeName().equals("isfeatured")) {
                        iso.setIsFeatured(property.getTextContent());
                    } else if (property.getNodeName().equals("ispublic")) {
                        iso.setIsPublic(property.getTextContent());
                    } else if (property.getNodeName().equals("isready")) {
                        iso.setIsReady(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        iso.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("ostypeid")) {
                        iso.setOsTypeId(property.getTextContent());
                    } else if (property.getNodeName().equals("ostypename")) {
                        iso.setOsTypeName(property.getTextContent());
                    } else if (property.getNodeName().equals("passwordenabled")) {
                        iso.setPasswordEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        iso.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        iso.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("removed")) {
                        iso.setRemoved(property.getTextContent());
                    } else if (property.getNodeName().equals("size")) {
                        iso.setSize(property.getTextContent());
                    } else if (property.getNodeName().equals("sourcetemplateid")) {
                        iso.setSourceTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("sshkeyenabled")) {
                        iso.setSshKeyEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("status")) {
                        iso.setStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("templatetag")) {
                        iso.setTemplateTag(property.getTextContent());
                    } else if (property.getNodeName().equals("templatetype")) {
                        iso.setTemplateType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        iso.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        iso.setZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("tags")) {
                        NodeList tagsProperties = property.getChildNodes();
                        if (tagsProperties.getLength() > 0) {
                            TagsResponse tags = new TagsResponse();
                            for (int tagsIndex = 0; tagsIndex < tagsProperties.getLength(); tagsIndex++) {
                                property = tagsProperties.item(tagsIndex);

                                if (property.getNodeName().equals("account")) {
                                    tags.setAccount(property.getTextContent());
                                } else if (property.getNodeName().equals("customer")) {
                                    tags.setCustomer(property.getTextContent());
                                } else if (property.getNodeName().equals("domain")) {
                                    tags.setDomain(property.getTextContent());
                                } else if (property.getNodeName().equals("domainid")) {
                                    tags.setDomainId(property.getTextContent());
                                } else if (property.getNodeName().equals("key")) {
                                    tags.setKey(property.getTextContent());
                                } else if (property.getNodeName().equals("project")) {
                                    tags.setProject(property.getTextContent());
                                } else if (property.getNodeName().equals("projectid")) {
                                    tags.setProjectId(property.getTextContent());
                                } else if (property.getNodeName().equals("resourceid")) {
                                    tags.setResourceId(property.getTextContent());
                                } else if (property.getNodeName().equals("resourcetype")) {
                                    tags.setResourceType(property.getTextContent());
                                } else if (property.getNodeName().equals("value")) {
                                    tags.setValue(property.getTextContent());
                                }

                            }

                            tagss.add(tags);
                            response.setTagss(tagss);
                        }
                    } else if (property.getNodeName().equals("jobid")) {
                        iso.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        iso.setJobStatus(property.getTextContent());
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
            String IsoName, String IsoUrl, String zoneId, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("registerIso", optional);
        arguments.add(new NameValuePair("displaytext", displayText));
        arguments.add(new NameValuePair("name", IsoName));
        arguments.add(new NameValuePair("url", IsoUrl));
        arguments.add(new NameValuePair("zoneid", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getRegisterISOResponse(responseDocument);
    }

    /**
     * Converts XML document into RegisterISOResponse object
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
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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
            response.setCreated(node.getTextContent());
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
            response.setDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setFormat(node.getTextContent());
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

        // get isdynamicallyscalable from XML and set true if template contains XS/VMWare tools
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get isPublic from XML and set as true if this template is a public template, false otherwise 
        list = doc.getElementsByTagName("ispublic");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPublic(node.getTextContent());
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
            response.setName(node.getTextContent());
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
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
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
            response.setSize(node.getTextContent());
        }

        // get sourcetemplateid from XML and set as the template ID of the parent template if present
        list = doc.getElementsByTagName("sourcetemplateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceTemplateId(node.getTextContent());
        }

        // get sshkeyenabled from XML and set as true if template is sshkey enabled, false otherwise
        list = doc.getElementsByTagName("sshkeyenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSshKeyEnabled(node.getTextContent());
        }

        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
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
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the name of the zone for this template
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // gets the list of resource tags associated with tempate
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
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateIso", optional);
        arguments.add(new NameValuePair("id", imageFileId));

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
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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
            response.setCreated(node.getTextContent());
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
            response.setDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setFormat(node.getTextContent());
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

        // get isdynamicallyscalable from XML and set true if template contains XS/VMWare tools
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get isPublic from XML and set as true if this template is a public template, false otherwise 
        list = doc.getElementsByTagName("ispublic");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPublic(node.getTextContent());
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
            response.setSourceTemplateId(node.getTextContent());
        }

        // get sshkeyenabled from XML and set as true if template is sshkey enabled, false otherwise
        list = doc.getElementsByTagName("sshkeyenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSshKeyEnabled(node.getTextContent());
        }

        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
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

        // gets the list of resource tags associated with tempate
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
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteIso", optional);
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
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("copyIso", optional);
        arguments.add(new NameValuePair("id", templateId));
        arguments.add(new NameValuePair("destzoneid", destinationZoneId));
        arguments.add(new NameValuePair("sourcezoneid", sourceZoneId));

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
            response.setId(node.getTextContent());
        }

        // get account from XML and set as the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
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
            response.setCreated(node.getTextContent());
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
            response.setDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
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
            response.setFormat(node.getTextContent());
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

        // get isdynamicallyscalable from XML and set true if template contains XS/VMWare tools
        list = doc.getElementsByTagName("isdynamicallyscalable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsDynamicallyScalable(node.getTextContent());
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

        // get isPublic from XML and set as true if this template is a public template, false otherwise 
        list = doc.getElementsByTagName("ispublic");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPublic(node.getTextContent());
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
            response.setName(node.getTextContent());
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
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
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
            response.setSize(node.getTextContent());
        }

        // get sourcetemplateid from XML and set as the template ID of the parent template if present
        list = doc.getElementsByTagName("sourcetemplateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceTemplateId(node.getTextContent());
        }

        // get sshkeyenabled from XML and set as true if template is sshkey enabled, false otherwise
        list = doc.getElementsByTagName("sshkeyenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSshKeyEnabled(node.getTextContent());
        }

        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
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
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the name of the zone for this template
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        // gets the list of resource tags associated with tempate
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
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateIsoPermissions", optional);
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
     * Retrieves the current status of asynchronous job for VirtualMachine.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public IsoJobResultResponse isoJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getIsoJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into IsoJobResultResponse object
     *
     * @param doc
     * @return
     */
    private IsoJobResultResponse getIsoJobResultResponse(Document doc) {
        IsoJobResultResponse response = new IsoJobResultResponse();

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
                        } else if (property.getNodeName().equals("keypair")) {
                            response.setKeyPair(property.getTextContent());
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
                        } else if (property.getNodeName().equals("servicestate")) {
                            response.setServiceState(property.getTextContent());
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
     * List iso visibility and all accounts that have permissions to view this iso.
     *
     * @param templateId the template ID
     * @param optional
     * @return
     * @throws Exception
     */
    public ListISOPermissionResponse listIsoPermissions(String templateId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listIsoPermissions", optional);
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

        // List iso visibility and all accounts that have permissions to view this iso
        NodeList list = doc.getElementsByTagName("isopermission");
        List<IsoPermissionResponse> isoPermissions = new LinkedList<IsoPermissionResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node isoPermissionNode = list.item(index);

                IsoPermissionResponse isoPermission = new IsoPermissionResponse();

                NodeList isoPermissionProperties = isoPermissionNode.getChildNodes();
                for (int childIndex = 0; childIndex < isoPermissionProperties.getLength(); childIndex++) {
                    Node property = isoPermissionProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        isoPermission.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        isoPermission.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        isoPermission.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("ispublic")) {
                        isoPermission.setIsPublic(property.getTextContent());
                    } else if (property.getNodeName().equals("projectids")) {
                        isoPermission.setProjectIds(property.getTextContent());
                    }
                }
                isoPermissions.add(isoPermission);
            }
        }

        response.setIsoPermissions(isoPermissions);
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
            String mode, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("extractIso", optional);
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
            response.setId(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the extracted object belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get created from XML and set as the time and date the object was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get extractId from XML and set as the upload id of extracted object
        list = doc.getElementsByTagName("extractId");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractId(node.getTextContent());
        }

        // get extractMode from XML and set as the mode of extraction - upload or download
        list = doc.getElementsByTagName("extractMode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractMode(node.getTextContent());
        }

        // get name from XML and set as the name of the extracted object
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get state from XML and set as the state of the extracted object
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get status from XML and set as the status of the extraction
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStatus(node.getTextContent());
        }

        // get storagetype from XML and set as type of the storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setStorageType(node.getTextContent());
        }

        // get uploadpercentage from XML and set as the percentage of the entity uploaded to the specified location
        list = doc.getElementsByTagName("uploadpercentage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUploadPercentage(node.getTextContent());
        }

        // get url from XML and set as if mode = upload then url of the uploaded entity. 
        // if mode = download the url from which the entity can be downloaded
        list = doc.getElementsByTagName("url");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUrl(node.getTextContent());
        }

        // get zoneid from XML and set as the zone ID the object was extracted from
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the zone name the object was extracted from
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        return response;
    }
}
