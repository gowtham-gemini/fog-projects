package com.assistanz.cloud.cloudstack.securitygroup;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;

/**
 * 
 * @author Gowtham
 *
 */
public class CSSecurityGroupService {
	
    private CloudStackServer server;

        public CSSecurityGroupService(CloudStackServer server) {
            this.server = server;
    }

    /**
     * Creates a security group
     * 
     * @param securityGroupName name of the security group
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateSecurityGroupResponse createSecurityGroup(String securityGroupName, 
        HashMap<String,String> optional) throws Exception {

        LinkedList<NameValuePair> arguments = 
        server.getDefaultQuery("createSecurityGroup", optional);
        arguments.add(new NameValuePair("name", securityGroupName));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateSecurityGroupResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateSecurityGroupResponse object
     *
     * @param doc
     * @return
     */
    private CreateSecurityGroupResponse getCreateSecurityGroupResponse(Document doc) {
        CreateSecurityGroupResponse response = new CreateSecurityGroupResponse();

                // get id from XML and set as the ID of the security group
                NodeList list = doc.getElementsByTagName("id");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setSecurityGroupId(node.getTextContent());
                }

                // get account from XML and set as the account owning the security group
                list = doc.getElementsByTagName("account");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setSecurityGroupAccount(node.getTextContent());
                }

                // get description from XML and set as the description of the security group
                list = doc.getElementsByTagName("description");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setDescription(node.getTextContent());
                }

                // get domain from XML and set as the domain name for the security group
                list = doc.getElementsByTagName("domain");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setSecurityGroupDomainName(node.getTextContent());
                }

                // get domainid from XML and set as the domain id for the security group
                list = doc.getElementsByTagName("domainid");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setSecurityGroupDomainId(node.getTextContent());
                }

                // get name from XML and set as the name  for the security group
                list = doc.getElementsByTagName("name");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setSecurityGroupName(node.getTextContent());
                }

                // get project from XML and set as the project name for the security group
                list = doc.getElementsByTagName("project");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setSecurityGroupProjectName(node.getTextContent());
                }


                // get projectid from XML and set as the projectid for the security group
                list = doc.getElementsByTagName("projectid");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setSecurityGroupProjectId(node.getTextContent());
                }

                //the list of egress rules associated with the security group
                list = doc.getElementsByTagName("egressrule");
        if (list.getLength() > 0) {
            List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node egressRuleNode = list.item(index);
                EgressRuleResponse egressRule = new EgressRuleResponse();


                NodeList egressRuleProperties = egressRuleNode.getChildNodes();
                for (int childIndex = 0; childIndex < egressRuleProperties.getLength(); childIndex++) {
                        Node property = list.item(childIndex);;

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
        //	the list of ingress rules associated with the security group
                list = doc.getElementsByTagName("ingressrule");
        if (list.getLength() > 0) {
            List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>();            
            for (int index = 0; index < list.getLength(); index++) {
                Node egressRuleNode = list.item(index);
                IngressRuleResponse ingressRule = new IngressRuleResponse();


                NodeList egressRuleProperties = egressRuleNode.getChildNodes();
                for (int childIndex = 0; childIndex < egressRuleProperties.getLength(); childIndex++) {
                     Node property = list.item(childIndex);;

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

        return response;
    }

    /**
     * Deletes security group
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteSecurityGroupResponse deleteSecurityGroup(HashMap<String,String> optional) 
            throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteSecurityGroup", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteSecurityGroupResponse(responseDocument);
    }


        /**
         * Converts XML document into DeleteSecurityGroupResponse object
         * 
         * @param doc
         * @return
         */
        private DeleteSecurityGroupResponse getDeleteSecurityGroupResponse(Document doc) {
                DeleteSecurityGroupResponse response = new DeleteSecurityGroupResponse();

                // get displaytext from XML and set any text associated with the success or failure on Delete Security Group
                NodeList list = doc.getElementsByTagName("displaytext");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setDisplaytext(node.getTextContent());
                }
                //get success from XML and any text associated with the success or failure on deleting Delete Security Group
                list = doc.getElementsByTagName("success");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setSuccess(node.getTextContent());
                }

                return response;
        }

    /**
     * Authorizes a particular ingress rule for this security group
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public AuthorizeSecurityGroupIngressResponse authorizeSecurityGroupIngress(HashMap<String,String> optional) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("authorizeSecurityGroupIngress", optional);

            Document responseDocument = server.makeRequest(arguments);

            return getAuthorizeSecurityGroupIngressResponse(responseDocument);
    }

    /**
     * Converts XML document into AuthorizeSecurityGroupIngressResponse object
     * 
     * @param doc
     * @return
     */
    private AuthorizeSecurityGroupIngressResponse getAuthorizeSecurityGroupIngressResponse(Document doc) {
            AuthorizeSecurityGroupIngressResponse response = new AuthorizeSecurityGroupIngressResponse();

            // get account from XML and set as the account owning the security group rule
            NodeList list = doc.getElementsByTagName("account");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupAccount(node.getTextContent());
            }

            // get cidr from XML and set as the CIDR notation for the base IP address of the security group rule
            list = doc.getElementsByTagName("cidr");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupCidr(node.getTextContent());
            }
            
            // get jobid from XML and set as the job id for the security group rule
            list = doc.getElementsByTagName("jobid");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setJobId(node.getTextContent());
            }

            // get endport from XML and set as the ending IP of the security group rule 
            list = doc.getElementsByTagName("endport");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupEndport(node.getTextContent());
            }

            // get icmpcode from XML and set as the code for the ICMP message response
            list = doc.getElementsByTagName("icmpcode");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupEndport(node.getTextContent());
            }

            // get icmptype from XML and set as the type of the ICMP message response
            list = doc.getElementsByTagName("icmptype");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupStartport(node.getTextContent());
            }

            // get protocol from XML and set as the protocol of the security group rule
            list = doc.getElementsByTagName("protocol");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupProtocol(node.getTextContent());
            }

            // get ruleid from XML and set as the id of the security group rule
            list = doc.getElementsByTagName("ruleid");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupRuleId(node.getTextContent());
            }

            // get securitygroupname from XML and set as the name of the security group 
            list = doc.getElementsByTagName("securitygroupname");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupName(node.getTextContent());
            }

            // get startport from XML and set as the startport of the security group 
            list = doc.getElementsByTagName("startport");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupStartport(node.getTextContent());
            }

            return response;
    }

    /**
     * Deletes a particular ingress rule from this security group
     * 
     * @param ingressRuleId The ID of the ingress rule
     * @param optional
     * @return
     * @throws Exception
     */
    public RevokeSecurityGroupIngressResponse revokeSecurityGroupIngress(String ingressRuleId) throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("revokeSecurityGroupIngress", null);
            arguments.add(new NameValuePair("id",  ingressRuleId));

            Document responseDocument = server.makeRequest(arguments);

            return getRevokeSecurityGroupIngressResponse(responseDocument);
    }


        /**
         * Converts XML document into RevokeSecurityGroupIngressResponse object
         * 
         * @param doc
         * @return
         */
        private RevokeSecurityGroupIngressResponse getRevokeSecurityGroupIngressResponse(Document doc) {
                RevokeSecurityGroupIngressResponse response = new RevokeSecurityGroupIngressResponse();

                // get displaytext from XML and set any text associated with the success or failure on revoke Security Group Ingress
                NodeList list = doc.getElementsByTagName("displaytext");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setDisplaytext(node.getTextContent());
                }
                //get success from XML and any text associated with the success or failure on deleting revoke Security Group Ingress
                list = doc.getElementsByTagName("success");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setSuccess(node.getTextContent());
                }

                return response;
        }

    /**
     * Authorizes a particular egress rule for this security group
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public AuthorizeSecurityGroupEgressResponse authorizeSecurityGroupEgress(HashMap<String,String> optional) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("authorizeSecurityGroupEgress", optional);

            Document responseDocument = server.makeRequest(arguments);

            return getAuthorizeSecurityGroupEgressResponse(responseDocument);
    }

    /**
     * Converts XML document into AuthorizeSecurityGroupEgressResponse object
     * 
     * @param doc
     * @return
     */
    private AuthorizeSecurityGroupEgressResponse getAuthorizeSecurityGroupEgressResponse(Document doc) {
            AuthorizeSecurityGroupEgressResponse response = new AuthorizeSecurityGroupEgressResponse();

            // get account from XML and set as the account owning the security group rule
            NodeList list = doc.getElementsByTagName("account");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupAccount(node.getTextContent());
            }

            // get cidr from XML and set as the CIDR notation for the base IP address of the security group rule
            list = doc.getElementsByTagName("cidr");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupCidr(node.getTextContent());
            }

            // get endport from XML and set as the ending IP of the security group rule 
            list = doc.getElementsByTagName("endport");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupEndport(node.getTextContent());
            }

            // get icmpcode from XML and set as the code for the ICMP message response
            list = doc.getElementsByTagName("icmpcode");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupIcmpCode(node.getTextContent());
            }

            // get icmptype from XML and set as the type of the ICMP message response
            list = doc.getElementsByTagName("icmptype");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupIcmpType(node.getTextContent());
            }

            // get protocol from XML and set as the protocol of the security group rule
            list = doc.getElementsByTagName("protocol");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupProtocol(node.getTextContent());
            }

            // get ruleid from XML and set as the id of the security group rule
            list = doc.getElementsByTagName("ruleid");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupRuleId(node.getTextContent());
            }

            // get securitygroupname from XML and set as the name of the security group 
            list = doc.getElementsByTagName("securitygroupname");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupName(node.getTextContent());
            }

            // get startport from XML and set as the startport of the security group 
            list = doc.getElementsByTagName("startport");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setSecurityGroupStartport(node.getTextContent());
            }
            
            // get jobid from XML and set as the job id for the security group rule
            list = doc.getElementsByTagName("jobid");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setJobId(node.getTextContent());
            }

            return response;
    }

    /**
     * Deletes a particular egress rule from this security group
     * 
     * @param egressRuleId The ID of the egress rule
     * @param optional
     * @return
     * @throws Exception
     */
    public RevokeSecurityGroupEgressResponse revokeSecurityGroupEgress(String egressRuleId) throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("revokeSecurityGroupEgress", null);
            arguments.add(new NameValuePair("id", egressRuleId));

            Document responseDocument = server.makeRequest(arguments);

            return getRevokeSecurityGroupEgressResponse(responseDocument);
    }


        /**
         * Converts XML document into RevokeSecurityGroupEgressResponse object
         * 
         * @param doc
         * @return
         */
        private RevokeSecurityGroupEgressResponse getRevokeSecurityGroupEgressResponse(Document doc) {
                RevokeSecurityGroupEgressResponse response = new RevokeSecurityGroupEgressResponse();

                // get displaytext from XML and set any text associated with the success or failure on revoke Security Group Egress
                NodeList list = doc.getElementsByTagName("displaytext");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setDisplaytext(node.getTextContent());
                }
                //get success from XML and any text associated with the success or failure on deleting revoke Security Group Egress
                list = doc.getElementsByTagName("success");
                if (list.getLength() > 0) {
                    Node node = list.item(0);
                    response.setSuccess(node.getTextContent());
                }

                return response;
        }

    /**
     * Lists security groups
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public ListSecurityGroupsResponse listSecurityGroups(
        HashMap<String,String> optional) throws Exception {

        LinkedList<NameValuePair> arguments = 
        server.getDefaultQuery("listSecurityGroups", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListSecurityGroupsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListSecurityGroupsResponse object
     *
     * @param doc
     * @return
     */
    private ListSecurityGroupsResponse getListSecurityGroupsResponse(Document doc) {
        ListSecurityGroupsResponse response = new ListSecurityGroupsResponse();
        
         NodeList list = doc.getElementsByTagName("securitygroup");
         List<SecurityGroupResponse> securityGroups = new LinkedList<SecurityGroupResponse>(); 
         if (list.getLength() > 0) {
             for (int securityGroupIndex = 0; securityGroupIndex < list.getLength(); securityGroupIndex++) {
                Node securityGroupNode = list.item(securityGroupIndex);
                SecurityGroupResponse securityGroup = new SecurityGroupResponse();
                List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>(); 
                List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>(); 
                NodeList securityGroupProperties = securityGroupNode.getChildNodes();
                    for (int childIndex = 0; childIndex < securityGroupProperties.getLength(); childIndex++) {
                        Node childProperty = securityGroupProperties.item(childIndex);
                        if (childProperty.getNodeName().equals("id")) {
                            securityGroup.setSecurityGroupId(childProperty.getTextContent());
                        } else if (childProperty.getNodeName().equals("account")) {
                            securityGroup.setSecurityGroupAccount(childProperty.getTextContent());
                        } else if (childProperty.getNodeName().equals("description")) {
                            securityGroup.setDescription(childProperty.getTextContent());
                        } else if (childProperty.getNodeName().equals("domain")) {
                            securityGroup.setSecurityGroupDomainName(childProperty.getTextContent());
                        } else if (childProperty.getNodeName().equals("domainid")) {
                            securityGroup.setSecurityGroupDomainId(childProperty.getTextContent());
                        } else if (childProperty.getNodeName().equals("name")) {
                            securityGroup.setSecurityGroupName(childProperty.getTextContent());
                        } else if (childProperty.getNodeName().equals("project")) {
                            securityGroup.setSecurityGroupProjectName(childProperty.getTextContent());
                        } else if (childProperty.getNodeName().equals("projectid")) {
                            securityGroup.setSecurityGroupProjectId(childProperty.getTextContent());
                        } else if (childProperty.getNodeName().equals("egressrule")) {
                            NodeList egressRuleProperties = childProperty.getChildNodes();
                            if (egressRuleProperties.getLength() > 0) {
                                EgressRuleResponse egressRule = new EgressRuleResponse();
                                for (int egressRuleIndex = 0; egressRuleIndex < egressRuleProperties.getLength(); egressRuleIndex++) {
                                    Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                    if (egressRuleProperty.getNodeName().equals("account")) {
                                        egressRule.setSecurityGroupRuleAccount(egressRuleProperty.getTextContent());
                                    } else if (egressRuleProperty.getNodeName().equals("cidr")) {
                                       egressRule.setCidr(egressRuleProperty.getTextContent());
                                    } else if (egressRuleProperty.getNodeName().equals("endport")) {
                                        egressRule.setEndPort(egressRuleProperty.getTextContent());
                                    } else if (egressRuleProperty.getNodeName().equals("icmpcode")) {
                                        egressRule.setEndPort(egressRuleProperty.getTextContent());
                                    } else if (egressRuleProperty.getNodeName().equals("icmptype")) {
                                        egressRule.setStartPort(egressRuleProperty.getTextContent());
                                    } else if (egressRuleProperty.getNodeName().equals("protocol")) {
                                        egressRule.setProtocol(egressRuleProperty.getTextContent());
                                    } else if (egressRuleProperty.getNodeName().equals("ruleid")) {
                                        egressRule.setSecurityGroupRuleId(egressRuleProperty.getTextContent());
                                    } else if (egressRuleProperty.getNodeName().equals("securitygroupname")) {
                                        egressRule.setSecurityGroupName(egressRuleProperty.getTextContent());
                                    } else if (egressRuleProperty.getNodeName().equals("startport")) {
                                        egressRule.setStartPort(egressRuleProperty.getTextContent());
                                    }
                                }
                                egressRules.add(egressRule);
                            }
                        } else if (childProperty.getNodeName().equals("ingressrule")) {
                            NodeList ingressRuleProperties = childProperty.getChildNodes();
                                if (ingressRuleProperties.getLength() > 0) {
                                   IngressRuleResponse ingressRule = new IngressRuleResponse();
                                   for (int ingressRuleIndex = 0; ingressRuleIndex < ingressRuleProperties.getLength(); ingressRuleIndex++) {
                                        Node ingressRuleProperty = ingressRuleProperties.item(ingressRuleIndex);	                                
                                        if (ingressRuleProperty.getNodeName().equals("account")) {
                                            ingressRule.setSecurityGroupRuleAccount(ingressRuleProperty.getTextContent());
                                        } else if (ingressRuleProperty.getNodeName().equals("cidr")) {
                                            ingressRule.setCidr(ingressRuleProperty.getTextContent());
                                        } else if (ingressRuleProperty.getNodeName().equals("endport")) {
                                            ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                        } else if (ingressRuleProperty.getNodeName().equals("icmpcode")) {
                                            ingressRule.setEndPort(ingressRuleProperty.getTextContent());
                                        } else if (ingressRuleProperty.getNodeName().equals("icmptype")) {
                                            ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                        } else if (ingressRuleProperty.getNodeName().equals("protocol")) {
                                            ingressRule.setProtocol(ingressRuleProperty.getTextContent());
                                        } else if (ingressRuleProperty.getNodeName().equals("ruleid")) {
                                            ingressRule.setSecurityGroupRuleId(ingressRuleProperty.getTextContent());
                                        } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                            ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                        } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                            ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                        }	                                
                                   }
                                   ingressRules.add(ingressRule);
                                }
                            } else if (childProperty.getNodeName().equals("jobid")) {
                                 securityGroup.setJobId(childProperty.getTextContent());
                            } else if (childProperty.getNodeName().equals("jobstatus")) {
                                 securityGroup.setJobStatus(childProperty.getTextContent());
                            }
                    }
                    securityGroup.setEgressRules(egressRules);
                    securityGroup.setIngressRules(ingressRules);
                    securityGroups.add(securityGroup);  
             }
         }
         response.setSecurityGroups(securityGroups); 
        return response;
    }
    
    /**
     * Retrieves the current status of ingress job for volume.
     * 
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public SecuritygroupJobResultResponse securitygroupJobResult(String asychronousJobid) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("queryAsyncJobResult", null);
            arguments.add(new NameValuePair("jobid",  asychronousJobid));

            Document responseDocument = server.makeRequest(arguments);

            return getSecuritygroupJobResultResponse(responseDocument);
    }
	
    /**
     * Converts XML document into getIngressJobResultResponse object
     * 
     * @param doc
     * @return
     */
    private SecuritygroupJobResultResponse getSecuritygroupJobResultResponse(Document doc) {
        SecuritygroupJobResultResponse response = new SecuritygroupJobResultResponse();

        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCmd(node.getTextContent());                   
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobProgressStatus(node.getTextContent());
        }

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
             NodeList node = list.item(0).getChildNodes();    
             for (int index = 0; index < node.getLength(); index++) {
                 Node nodeProperty = node.item(index);
                 if (nodeProperty.getNodeName().equals("securitygroup")) {
                        List<EgressRuleResponse> egressRules = new LinkedList<EgressRuleResponse>(); 
                        List<IngressRuleResponse> ingressRules = new LinkedList<IngressRuleResponse>(); 
                        NodeList childNodeProperties =  nodeProperty.getChildNodes();
                        for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                            Node securityGroupProperty = childNodeProperties.item(childIndex);    
                            if (securityGroupProperty.getNodeName().equals("id")) {
                                response.setSecurityGroupId(securityGroupProperty.getTextContent());
                            } else if (securityGroupProperty.getNodeName().equals("account")) {
                                 response.setSecurityGroupAccount(securityGroupProperty.getTextContent());
                            } else if (securityGroupProperty.getNodeName().equals("description")) {
                                 response.setDescription(securityGroupProperty.getTextContent());
                            } else if (securityGroupProperty.getNodeName().equals("domain")) {
                                 response.setSecurityGroupDomainName(securityGroupProperty.getTextContent());
                            } else if (securityGroupProperty.getNodeName().equals("domainid")) {
                                 response.setSecurityGroupDomainId(securityGroupProperty.getTextContent());
                            } else if (securityGroupProperty.getNodeName().equals("name")) {
                                 response.setSecurityGroupName(securityGroupProperty.getTextContent());
                            } else if (securityGroupProperty.getNodeName().equals("project")) {
                                 response.setSecurityGroupProjectName(securityGroupProperty.getTextContent());
                            } else if (securityGroupProperty.getNodeName().equals("projectid")) {
                                 response.setSecurityGroupProjectId(securityGroupProperty.getTextContent());
                            } else if (securityGroupProperty.getNodeName().equals("egressrule")) {
                                NodeList egressRuleProperties = securityGroupProperty.getChildNodes();
                                if (egressRuleProperties.getLength() > 0) {
                                    EgressRuleResponse egressRule = new EgressRuleResponse();
                                    for (int egressRuleIndex = 0; egressRuleIndex < egressRuleProperties.getLength(); egressRuleIndex++) {
                                        Node egressRuleProperty = egressRuleProperties.item(egressRuleIndex);
                                        if (egressRuleProperty.getNodeName().equals("account")) {
                                            egressRule.setSecurityGroupRuleAccount(egressRuleProperty.getTextContent());
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
                                            egressRule.setSecurityGroupRuleId(egressRuleProperty.getTextContent());
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
                                            ingressRule.setSecurityGroupRuleAccount(ingressRuleProperty.getTextContent());
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
                                            ingressRule.setSecurityGroupRuleId(ingressRuleProperty.getTextContent());
                                        } else if (ingressRuleProperty.getNodeName().equals("securitygroupname")) {
                                            ingressRule.setSecurityGroupName(ingressRuleProperty.getTextContent());
                                        } else if (ingressRuleProperty.getNodeName().equals("startport")) {
                                            ingressRule.setStartPort(ingressRuleProperty.getTextContent());
                                        }	                                
                                   }
                                   ingressRules.add(ingressRule);
                                }
                            } 
                        
                        }
                        response.setEgressRules(egressRules);
                        response.setIngressRules(ingressRules);
                    }
            }
             
        }
        
        // get errortext from XML and set as the errortext for the job
        list = doc.getElementsByTagName("errortext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setErrortext(node.getTextContent());
        }
                
        // get jobresultcode from XML and set as the result code for the job
        list = doc.getElementsByTagName("jobresultcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobId(node.getTextContent());
        }

        return response;
    }
    
}
