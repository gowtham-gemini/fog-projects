package com.assistanz.cloud.cloudstack.ssh;

import com.assistanz.cloud.cloudstack.AffinityGroupResponse;
import java.util.HashMap;
import java.util.LinkedList;
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
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSSSHService {

    private CloudStackServer server;

    public CSSSHService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Resets the SSH Key for virtual machine The virtual machine must be in a "Stopped" state
     *
     *
     * @param virtualMachineId The ID of the virtual machine
     * @param sshKeyPair name of the ssh key pair used to login to the virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public ResetSSHKeyForVirtualMachineResponse resetSSHKeyForVirtualMachine(String virtualMachineId,
            String sshKeyPair, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("resetSSHKeyForVirtualMachine", optional);
        arguments.add(new NameValuePair("id", virtualMachineId));
        arguments.add(new NameValuePair("keypair", sshKeyPair));

        Document responseDocument = server.makeRequest(arguments);

        return getResetSSHKeyForVirtualMachineResponse(responseDocument);
    }

    /**
     * Converts XML document into ResetSSHKeyForVirtualMachineResponse object
     *
     * @param doc
     * @return
     */
    private ResetSSHKeyForVirtualMachineResponse getResetSSHKeyForVirtualMachineResponse(Document doc) {
        ResetSSHKeyForVirtualMachineResponse response = new ResetSSHKeyForVirtualMachineResponse();

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
     * Register a public key in a keypair under a certain name
     *
     * @param keyPairName Name of the keypair
     * @param publicKey Public key material of the keypair
     * @param optional
     * @return
     * @throws Exception
     */
    public RegisterSSHKeyPairResponse registerSSHKeyPair(String keyPairName,
            String publicKey, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("registerSSHKeyPair", optional);
        arguments.add(new NameValuePair("name", keyPairName));
        arguments.add(new NameValuePair("publickey", publicKey));

        Document responseDocument = server.makeRequest(arguments);

        return getRegisterSSHKeyPairResponse(responseDocument);
    }

    /**
     * Converts XML document into RegisterSSHKeyPairResponse object
     *
     * @param doc
     * @return
     */
    private RegisterSSHKeyPairResponse getRegisterSSHKeyPairResponse(Document doc) {
        RegisterSSHKeyPairResponse response = new RegisterSSHKeyPairResponse();

        // get fingerprint from XML and set Fingerprint of the public key
        NodeList list = doc.getElementsByTagName("fingerprint");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setFingerPrint(node.getTextContent());
        }

        // get name from XML and set Name of the keypair
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get privatekey from XML and set private key
        list = doc.getElementsByTagName("privatekey");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateKey(node.getTextContent());
        }

        return response;
    }

    /**
     * Create a new keypair and returns the private key
     *
     * @param keyPairName Name of the keypair
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateSSHKeyPairResponse createSSHKeyPair(String keyPairName,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createSSHKeyPair", optional);
        arguments.add(new NameValuePair("name", keyPairName));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateSSHKeyPairResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateSSHKeyPairResponse object
     *
     * @param doc
     * @return
     */
    private CreateSSHKeyPairResponse getCreateSSHKeyPairResponse(Document doc) {
        CreateSSHKeyPairResponse response = new CreateSSHKeyPairResponse();                
        NodeList list = doc.getElementsByTagName("keypair"); 
        
        // get fingerprint from XML and set Fingerprint of the public key       
        if (list.getLength() > 0) {        
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node keyNode = list.item(Index);
                if (keyNode == null) {
                    continue;
                }
            
                NodeList keyProperties = keyNode.getChildNodes();
                for (int childIndex = 0; childIndex < keyProperties.getLength(); childIndex++) {                
                    Node property = keyProperties.item(childIndex);
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }                
                    if (property.getNodeName().equals("fingerprint")) {
                        response.setFingerPrint(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        response.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("privatekey")) {
                        response.setPrivateKey(property.getTextContent());
                    } 
                }            
            }                
        }
        return response;
    }

    /**
     * Deletes a keypair by name
     *
     * @param keyPairName The Name of the keypair
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteSSHKeyPairResponse deleteSSHKeyPair(String keyPairName,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteSSHKeyPair", optional);
        arguments.add(new NameValuePair("name", keyPairName));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteSSHKeyPairResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteSSHKeyPairResponse object
     *
     * @param doc
     * @return
     */
    private DeleteSSHKeyPairResponse getDeleteSSHKeyPairResponse(Document doc) {
        DeleteSSHKeyPairResponse response = new DeleteSSHKeyPairResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete SSH KeyPair
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }

        //get success from XML and any text associated with the success or failure on deleting Delete SSH KeyPair
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * List registered key pairs
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListSSHKeyPairsResponse listSSHKeyPairs(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listSSHKeyPairs", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListSSHKeyPairsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListSSHKeyPairsResponse object
     *
     * @param doc
     * @return
     */
    private ListSSHKeyPairsResponse getListSSHKeyPairsResponse(Document doc) {
        ListSSHKeyPairsResponse response = new ListSSHKeyPairsResponse();

        NodeList list = doc.getElementsByTagName("sshkeypair");

        List<SSHKeyPairResponse> sshKeyPairs = new LinkedList<SSHKeyPairResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node sshKeyPairNode = list.item(Index);
            SSHKeyPairResponse sshKeyPair = new SSHKeyPairResponse();

            NodeList sshKeyPairProperties = sshKeyPairNode.getChildNodes();
            for (int childIndex = 0; childIndex < sshKeyPairProperties.getLength(); childIndex++) {
                Node property = sshKeyPairProperties.item(childIndex);

                if (property.getNodeName().equals("fingerprint")) {
                    sshKeyPair.setFingerPrint(property.getTextContent());
                } else if (property.getNodeName().equals("name")) {
                    sshKeyPair.setName(property.getTextContent());
                } else if (property.getNodeName().equals("privatekey")) {
                    sshKeyPair.setPrivateKey(property.getTextContent());
                }
            }
            sshKeyPairs.add(sshKeyPair);
        }

        response.setSshKeyPairs(sshKeyPairs);
        return response;

    }

}
