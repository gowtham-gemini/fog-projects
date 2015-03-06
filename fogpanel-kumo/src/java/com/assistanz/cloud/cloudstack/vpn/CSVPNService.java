package com.assistanz.cloud.cloudstack.vpn;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



/**
 * 
 * @author Gowtham
 *
 */
public class CSVPNService {
	
	private CloudStackServer server;
	
	public CSVPNService(CloudStackServer server) {
		this.server = server;
	}
	
	/**
	 * Creates a  remote access vpn
	 * 
	 * @param publicIpId public ip address id of the vpn server
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public CreateRemoteAccessVpnResponse createRemoteAccessVpn(String publicIpId,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("createRemoteAccessVpn", optional);
		arguments.add(new NameValuePair("publicipid",  publicIpId));
		
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getCreateRemoteAccessVpnResponse(responseDocument);
	}
        
    /**
     * Retrieves the current status of asynchronous job for VPN.
     * 
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public VPNJobResponse vpnJobResult(String asychronousJobid) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("queryAsyncJobResult", null);
            arguments.add(new NameValuePair("jobid",  asychronousJobid));

            Document responseDocument = server.makeRequest(arguments);

            return getVPNJobResultResponse(responseDocument);
    }
    
    /**
     * Retrieves the current status of asynchronous job for VPN Users.
     * 
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
//    public VPNUserJobResponse vpnUsersJobResult(String asychronousJobid) 
//                    throws Exception {
//
//            LinkedList<NameValuePair> arguments = 
//                    server.getDefaultQuery("queryAsyncJobResult", null);
//            arguments.add(new NameValuePair("jobid",  asychronousJobid));
//
//            Document responseDocument = server.makeRequest(arguments);
//
//            return getVPNJobResultResponse(responseDocument);
//    }
    
    /**
     * Converts XML document into VolumeJobResultResponse object
     * 
     * @param doc
     * @return
     */
    private VPNJobResponse getVPNJobResultResponse(Document doc) {
        VPNJobResponse response = new VPNJobResponse();

        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousAccountId(node.getTextContent());
        }
        
        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousUserId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCmd(node.getTextContent());                   
        }
        
        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobStatus(node.getTextContent());
        }
        
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobProcStatus(node.getTextContent());
        }
        
        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCreated(node.getTextContent());
        }                

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobProcStatus(node.getTextContent());
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

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            NodeList node = list.item(0).getChildNodes();    
            for (int index = 0; index < node.getLength(); index++) {
                Node nodeProperty = node.item(index);
                if (nodeProperty.getNodeName().equals("remoteaccessvpn")) {                    
                    NodeList childNodeProperties =  nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                    Node property = childNodeProperties.item(childIndex);
                        if (property.getNodeName().equals("id")) {
                            response.setVpnId(property.getTextContent());
                        } else if (property.getNodeName().equals("account")) {
                            response.setAccount(property.getTextContent());
                        } else if (property.getNodeName().equals("publicipid")) {
                            response.setPublicIpId(property.getTextContent());
                        } else if (property.getNodeName().equals("iprange")) {
                            response.setIpRange(property.getTextContent());
                        } else if (property.getNodeName().equals("presharedkey")) {
                            response.setPreSharedKey(property.getTextContent());
                        } else if (property.getNodeName().equals("domainid")) {
                            response.setDomainId(property.getTextContent());
                        } else if (property.getNodeName().equals("domain")) {
                            response.setDomain(property.getTextContent());
                        } else if (property.getNodeName().equals("state")) {
                            response.setState(property.getTextContent());
                        } else if (property.getNodeName().equals("publicip")) {
                            response.setPublicIp(property.getTextContent());
                        } 
                    }
                    
                } 
            }
        }                                                

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobId(node.getTextContent());
        }       
        return response;
            
    }
	
	/**
	 * Converts XML document into CreateRemoteAccessVpnResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private CreateRemoteAccessVpnResponse getCreateRemoteAccessVpnResponse(Document doc) {
		CreateRemoteAccessVpnResponse response = new CreateRemoteAccessVpnResponse();

		// get account from XML and set as the account of the remote access vpn
		NodeList list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRemoteAccessVpnAccount(node.getTextContent());
		}
		
		// get domain from XML and set as the domain name of the account of the remote access vpn
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRemoteAccessVpnDomain(node.getTextContent());
		}
		
		// get domainid from XML and set as the domain id of the account of the remote access vpn
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRemoteAccessVpnDomainId(node.getTextContent());
		}
		
		// get iprange from XML and set as the range of ips to allocate to the clients
		list = doc.getElementsByTagName("iprange");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRemoteAccessVpnIpRange(node.getTextContent());
		}
		
		// get presharedkey from XML and set as the ipsec preshared key
		list = doc.getElementsByTagName("presharedkey");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRemoteAccessVpnPresharedKey(node.getTextContent());
		}
		
		// get project from XML and set as the project name of the vpn
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVpnProjectName(node.getTextContent());
		}
		
		// get projectid from XML and set as the project id of the vpn
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVpnProjectId(node.getTextContent());
		}
		
		// get publicip from XML and set as the public ip address of the vpn server
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVpnPublicIp(node.getTextContent());
		}
		
		// get publicipid from XML and set as the public ip address of the vpn server
		list = doc.getElementsByTagName("publicipid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVpnPublicipId(node.getTextContent());
		}
		
		// get state from XML and set as the state of the rule
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRuleState(node.getTextContent());
		}
                
                // get state from XML and set as the state of the rule
		list = doc.getElementsByTagName("jobid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setJobId(node.getTextContent());
		}
		
		return response;
		
	}
	
	/**
	 * Destroys a  remote access vpn
	 * 
	 * @param publicIpId public ip address id of the vpn server
	 * @return
	 * @throws Exception
	 */
	public DeleteRemoteAccessVpnResponse deleteRemoteAccessVpn(String publicIpId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("deleteRemoteAccessVpn", null);
		arguments.add(new NameValuePair("publicipid",  publicIpId));
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getDeleteRemoteAccessVpnResponse(responseDocument);
	}

	
	/**
	 * Converts XML document into DeleteRemoteAccessVpnResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private DeleteRemoteAccessVpnResponse getDeleteRemoteAccessVpnResponse(Document doc) {
		DeleteRemoteAccessVpnResponse response = new DeleteRemoteAccessVpnResponse();

		// get displaytext from XML and set any text associated with the success or failure on Delete VPN
		NodeList list = doc.getElementsByTagName("displaytext");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDisplaytext(node.getTextContent());
		}
		//get success from XML and any text associated with the success or failure on deleting Delete VPN
		list = doc.getElementsByTagName("success");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setSuccess(node.getTextContent());
		}
		//get success from XML and any text associated with the success or failure on deleting Delete VPN
		list = doc.getElementsByTagName("jobid");   
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setJobId(node.getTextContent());
		}
		return response;
	}
	
	/**
	 * Lists remote access vpns
	 * 
	 * @param publicIpId public ip address id of the vpn server
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListRemoteAccessVpnsResponse listRemoteAccessVpns(String publicIpId,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listRemoteAccessVpns", optional);
		arguments.add(new NameValuePair("publicipid",  publicIpId));
		
		
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getListRemoteAccessVpnsResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListRemoteAccessVpnsResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListRemoteAccessVpnsResponse getListRemoteAccessVpnsResponse(Document doc) {
		ListRemoteAccessVpnsResponse response = new ListRemoteAccessVpnsResponse();

		// get account from XML and set as the account of the remote access vpn
		NodeList list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRemoteAccessVpnAccount(node.getTextContent());
		}
		
		// get domain from XML and set as the domain name of the account of the remote access vpn
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRemoteAccessVpnDomain(node.getTextContent());
		}
		
		// get domainid from XML and set as the domain id of the account of the remote access vpn
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRemoteAccessVpnDomainId(node.getTextContent());
		}
		
		// get iprange from XML and set as the range of ips to allocate to the clients
		list = doc.getElementsByTagName("iprange");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRemoteAccessVpnIpRange(node.getTextContent());
		}
		
		// get presharedkey from XML and set as the ipsec preshared key
		list = doc.getElementsByTagName("presharedkey");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRemoteAccessVpnPresharedKey(node.getTextContent());
		}
		
		// get project from XML and set as the project name of the vpn
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVpnProjectName(node.getTextContent());
		}
		
		// get projectid from XML and set as the project id of the vpn
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVpnProjectId(node.getTextContent());
		}
		
		// get publicip from XML and set as the public ip address of the vpn server
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVpnPublicIp(node.getTextContent());
		}
		
		// get publicipid from XML and set as the public ip address of the vpn server
		list = doc.getElementsByTagName("publicipid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVpnPublicipId(node.getTextContent());
		}
		
		// get state from XML and set as the state of the rule
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRuleState(node.getTextContent());
		}
		
		return response;
		
	}
        
        /**
         * Adds vpn users
         * 
         * @param password
         * @param username
         * @param optional
         * @return
         * @throws Exception 
         */
        public AddVpnUserResponse addVpnUser(String password, String username,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("addVpnUser", optional);
		arguments.add(new NameValuePair("password",  password));
		arguments.add(new NameValuePair("username",  username));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getAddVpnUserResponse(responseDocument);
	}
        
        /**
         * Converts XML document into ListRemoteAccessVpnsResponse object
         * 
         * @param doc
         * @return 
         */
        private AddVpnUserResponse getAddVpnUserResponse(Document doc) {
		AddVpnUserResponse response = new AddVpnUserResponse();

		// the vpn userID
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setVpnUserId(node.getTextContent());
		}
		
                //the account of the remote access vpn
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setAccount(node.getTextContent());
		}
                //the domain name of the account of the remote access vpn
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDomain(node.getTextContent());
		}
                //the domain id of the account of the remote access vpn
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setDomainId(node.getTextContent());
		}
                //the project name of the vpn
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setProjectName(node.getTextContent());
		}
                //the project id of the vpn
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setProjectId(node.getTextContent());
		}
                //the state of the Vpn User
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setState(node.getTextContent());
		}
                //the username of the vpn user
		list = doc.getElementsByTagName("username");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setUsername(node.getTextContent());
		}
		//the job ID of the vpn user
		list = doc.getElementsByTagName("jobid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setJobId(node.getTextContent());
		}
                
		return response;
	}
        
        /**
         * Removes vpn user
         * 
         * @param username
         * @param optional
         * @return
         * @throws Exception 
         */
        public RemoveVpnUserResponse removeVpnUser(String username,HashMap<String,String> optional) 
                throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("removeVpnUser", optional);
            arguments.add(new NameValuePair("username",  username));

            Document responseDocument = server.makeRequest(arguments);

            return getRemoveVpnUserResponse(responseDocument);
	}
        
        /**
         * Converts XML document into RemoveVpnUserResponse object
         * 
         * @param doc
         * @return 
         */
        private RemoveVpnUserResponse getRemoveVpnUserResponse(Document doc) {
		RemoveVpnUserResponse response = new RemoveVpnUserResponse();

            // get displaytext from XML and set any text associated with the success or failure on Delete VPN
            NodeList list = doc.getElementsByTagName("displaytext");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setDisplaytext(node.getTextContent());
            }
            //get success from XML and any text associated with the success or failure on deleting Delete VPN
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
         * Lists vpn users
         * 
         * @param optional
         * @return
         * @throws Exception 
         */
        public ListVpnUsersResponse listVpnUsers(HashMap<String,String> optional) 
                throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("listVpnUsers", optional);
            
            Document responseDocument = server.makeRequest(arguments);

            return getListVpnUsersResponse(responseDocument);
	}
        
        /**
         * Converts XML document into ListVpnUsersResponse object
         * 
         * @param doc
         * @return 
         */
        private ListVpnUsersResponse getListVpnUsersResponse(Document doc) {
            ListVpnUsersResponse response = new ListVpnUsersResponse();
                
            NodeList list = doc.getElementsByTagName("vpnuser");    

            List<VpnUserResponse> vpnUsers = new LinkedList<VpnUserResponse>();  

             if (list.getLength() > 0) {
                 
                 for (int vpnUserIndex = 0; vpnUserIndex < list.getLength(); vpnUserIndex++) {
                     Node vpnUserNode = list.item(vpnUserIndex);
                     
                    VpnUserResponse vpnUser = new VpnUserResponse(); 
                    NodeList vpnUserProperties = vpnUserNode.getChildNodes();
                    for (int childIndex = 0; childIndex < vpnUserProperties.getLength(); childIndex++) {
                        
                        Node property = vpnUserProperties.item(childIndex);
                        
                        if (property.getNodeName().equals("id")) {
                            vpnUser.setVpnUserId(property.getTextContent());
                        } else if (property.getNodeName().equals("account")) {
                            vpnUser.setAccount(property.getTextContent());
                        } else if (property.getNodeName().equals("domain")) {
                            vpnUser.setDomain(property.getTextContent());
                        } else if (property.getNodeName().equals("domainid")) {
                            vpnUser.setDomainId(property.getTextContent());
                        }else if (property.getNodeName().equals("project")) {
                            vpnUser.setProjectName(property.getTextContent());
                        } else if (property.getNodeName().equals("projectid")) {
                            vpnUser.setProjectId(property.getTextContent());
                        } else if (property.getNodeName().equals("state")) {
                            vpnUser.setState(property.getTextContent());
                        } else if (property.getNodeName().equals("username")) {
                            vpnUser.setUsername(property.getTextContent());
                        }  
                    }
                    vpnUsers.add(vpnUser);
                }
                response.setVpnUsers(vpnUsers);
             }
             return response;
	}
        
        
    /**
     * Creates site to site vpn local gateway
     *
     * @param vpcId public ip address id of the vpn gateway
     * @return
     * @throws Exception
     */
    public CreateVpnGatewayResponse createVpnGateway(String vpcId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createVpnGateway", null);
        arguments.add(new NameValuePair("vpcid", vpcId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateVpnGatewayResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVpnGatewayResponse object
     *
     * @param doc
     * @return
     */
    private CreateVpnGatewayResponse getCreateVpnGatewayResponse(Document doc) {
        CreateVpnGatewayResponse response = new CreateVpnGatewayResponse();

        // get id from XML and set as the id of the remote access vpn
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnGatewayId(node.getTextContent());
        }

        // get account from XML and set as the account of the remote access vpn
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain id of the owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get project name from XML and set as the project name
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get project id from XML and set as the project id
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get public ip from XML and set as the public ip
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get removed from XML and set as the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get vpc id from XML and set as the vpc id of gateway
        list = doc.getElementsByTagName("vpcid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpcId(node.getTextContent());
        }
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        return response;
    }
    
    /**
     * Lists site to site vpn gateways
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListVpnGatewaysResponse listVpnGateways(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVpnGateways", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListVpnGatewaysResponse(responseDocument);
    }

    /**
     * Converts XML document into ListVpnGatewaysResponse object
     *
     * @param doc
     * @return
     */
    private ListVpnGatewaysResponse getListVpnGatewaysResponse(Document doc) {
        ListVpnGatewaysResponse response = new ListVpnGatewaysResponse();

        NodeList list = doc.getElementsByTagName("vpngateway");
        List<VpnGatewayResponse> vpnGateways = new LinkedList<VpnGatewayResponse>();
        if (list.getLength() > 0) {
            for (int vpnGatewayIndex = 0; vpnGatewayIndex < list.getLength(); vpnGatewayIndex++) {
                Node vpnGatewayNode = list.item(vpnGatewayIndex);

                VpnGatewayResponse vpnGateway = new VpnGatewayResponse();
                NodeList vpnGatewayProperties = vpnGatewayNode.getChildNodes();
                for (int childIndex = 0; childIndex < vpnGatewayProperties.getLength(); childIndex++) {
                    Node property = vpnGatewayProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        vpnGateway.setVpnGatewayId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        vpnGateway.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        vpnGateway.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        vpnGateway.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        vpnGateway.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        vpnGateway.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("publicip")) {
                        vpnGateway.setPublicIp(property.getTextContent());
                    } else if (property.getNodeName().equals("removed")) {
                        vpnGateway.setRemoved(property.getTextContent());
                    } else if (property.getNodeName().equals("vpcid")) {
                        vpnGateway.setVpcId(property.getTextContent());
                    }
                }
                vpnGateways.add(vpnGateway);
            }

        }
        response.setVpnGateways(vpnGateways);
        return response;
    }
    
    /**
     * Delete site to site vpn gateway
     *
     * @param customerGatewayId id of customer gateway
     * @return
     * @throws Exception
     */
    public DeleteVpnGatewayResponse deleteVpnGateway(String customerGatewayId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteVpnGateway", null);
        arguments.add(new NameValuePair("id", customerGatewayId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteVpnGatewayResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteVpnGatewayResponse object
     *
     * @param doc
     * @return
     */
    private DeleteVpnGatewayResponse getDeleteVpnGatewayResponse(Document doc) {
        DeleteVpnGatewayResponse response = new DeleteVpnGatewayResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete VPN
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete VPN
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
    
    public DeleteVpnCustomerGatewayResponse deleteVpnCustomerGateway(String customerGatewayId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteVpnCustomerGateway", null);
        arguments.add(new NameValuePair("id", customerGatewayId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteVpnCustomerGatewayResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteVpnCustomerGatewayResponse object
     *
     * @param doc
     * @return
     */
    private DeleteVpnCustomerGatewayResponse getDeleteVpnCustomerGatewayResponse(Document doc) {
        DeleteVpnCustomerGatewayResponse response = new DeleteVpnCustomerGatewayResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete VPN
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete VPN
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
     * Creates site to site vpn customer gateway
     *
     * @param cidrList guest cidr list of the customer gateway
     * @param espPolicy ESP policy of the customer gateway
     * @param gateway public ip address id of the customer gateway
     * @param ikePolicy IKE policy of the customer gateway
     * @param ipsecPsk IPsec Preshared-Key of the customer gateway
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateVpnCustomerGatewayResponse createVpnCustomerGateway(String cidrList, String espPolicy,
            String gateway, String ikePolicy, String ipsecPsk, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createVpnCustomerGateway", optional);
        arguments.add(new NameValuePair("cidrlist", cidrList));
        arguments.add(new NameValuePair("esppolicy", espPolicy));
        arguments.add(new NameValuePair("gateway", gateway));
        arguments.add(new NameValuePair("ikepolicy", ikePolicy));
        arguments.add(new NameValuePair("ipsecpsk", ipsecPsk));
        Document responseDocument = server.makeRequest(arguments);

        return getCreateVpnCustomerGatewayResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVpnCustomerGatewayResponse object
     *
     * @param doc
     * @return
     */
    private CreateVpnCustomerGatewayResponse getCreateVpnCustomerGatewayResponse(Document doc) {
        CreateVpnCustomerGatewayResponse response = new CreateVpnCustomerGatewayResponse();

        // get id from XML and set as the id of the remote access vpn
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnGatewayId(node.getTextContent());
        }

        // get account from XML and set as the account of the remote access vpn
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get cidrlist from XML and set as guest cidr list of the customer gateway
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain id of the owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get dpd from XML and set if DPD is enabled for customer gateway
        list = doc.getElementsByTagName("dpd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDpd(node.getTextContent());
        }

        // get esplifetime from XML and set as Lifetime of ESP SA of customer gateway
        list = doc.getElementsByTagName("esplifetime");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEspLifetime(node.getTextContent());
        }

        // get esppolicy from XML and set as IPsec policy of customer gateway
        list = doc.getElementsByTagName("esppolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEspPolicy(node.getTextContent());
        }

        // get gateway from XML and set as public ip address id of the customer gateway
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get ikelifetime from XML and set as Lifetime of IKE SA of customer gateway
        list = doc.getElementsByTagName("ikelifetime");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIkeLifetime(node.getTextContent());
        }

        // get ikepolicy from XML and set as IKE policy of customer gateway
        list = doc.getElementsByTagName("ikepolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIkePolicy(node.getTextContent());
        }

        // get ipaddress from XML and set as guest ip of the customer gateway
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get ipsecpsk from XML and set as IPsec preshared-key of customer gateway
        list = doc.getElementsByTagName("ipsecpsk");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpSecPsk(node.getTextContent());
        }

        // get name from XML and set as name of the customer gateway
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name 
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id 
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get removed from XML and set as the date and time the host was removed 
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        return response;
    }
    
    /**
     * Update site to site vpn customer gateway
     *
     * @param id
     * @param cidrList guest cidr list of the customer gateway
     * @param espPolicy ESP policy of the customer gateway
     * @param gateway public ip address id of the customer gateway
     * @param ikePolicy IKE policy of the customer gateway
     * @param ipsecPsk IPsec Preshared-Key of the customer gateway
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateVpnCustomerGatewayResponse updateVpnCustomerGateway(String id, String cidrList, String espPolicy,
            String gateway, String ikePolicy, String ipsecPsk, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateVpnCustomerGateway", optional);
        arguments.add(new NameValuePair("id", id));
        arguments.add(new NameValuePair("cidrlist", cidrList));
        arguments.add(new NameValuePair("esppolicy", espPolicy));
        arguments.add(new NameValuePair("gateway", gateway));
        arguments.add(new NameValuePair("ikepolicy", ikePolicy));
        arguments.add(new NameValuePair("ipsecpsk", ipsecPsk));
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateVpnCustomerGatewayResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateVpnCustomerGatewayResponse object
     *
     * @param doc
     * @return
     */
    private UpdateVpnCustomerGatewayResponse getUpdateVpnCustomerGatewayResponse(Document doc) {
        UpdateVpnCustomerGatewayResponse response = new UpdateVpnCustomerGatewayResponse();

        // get id from XML and set as the id of the remote access vpn
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnGatewayId(node.getTextContent());
        }

        // get account from XML and set as the account of the remote access vpn
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get cidrlist from XML and set as guest cidr list of the customer gateway
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain id of the owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get dpd from XML and set if DPD is enabled for customer gateway
        list = doc.getElementsByTagName("dpd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDpd(node.getTextContent());
        }

        // get esplifetime from XML and set as Lifetime of ESP SA of customer gateway
        list = doc.getElementsByTagName("esplifetime");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEspLifetime(node.getTextContent());
        }

        // get esppolicy from XML and set as IPsec policy of customer gateway
        list = doc.getElementsByTagName("esppolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEspPolicy(node.getTextContent());
        }

        // get gateway from XML and set as public ip address id of the customer gateway
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get ikelifetime from XML and set as Lifetime of IKE SA of customer gateway
        list = doc.getElementsByTagName("ikelifetime");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIkeLifetime(node.getTextContent());
        }

        // get ikepolicy from XML and set as IKE policy of customer gateway
        list = doc.getElementsByTagName("ikepolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIkePolicy(node.getTextContent());
        }

        // get ipaddress from XML and set as guest ip of the customer gateway
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get ipsecpsk from XML and set as IPsec preshared-key of customer gateway
        list = doc.getElementsByTagName("ipsecpsk");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpSecPsk(node.getTextContent());
        }

        // get name from XML and set as name of the customer gateway
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name 
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id 
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get removed from XML and set as the date and time the host was removed 
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        return response;
    }
    
    /**
     * Lists site to site vpn connection gateways
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListVpnConnectionsResponse listVpnConnections(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listVpnConnections", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListVpnConnectionsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListVpnConnectionsResponse object
     *
     * @param doc
     * @return
     */
    private ListVpnConnectionsResponse getListVpnConnectionsResponse(Document doc) {
        ListVpnConnectionsResponse response = new ListVpnConnectionsResponse();

        NodeList list = doc.getElementsByTagName("vpnconnection");
        List<VpnConnectionResponse> vpnConnections = new LinkedList<VpnConnectionResponse>();
        if (list.getLength() > 0) {
            for (int vpnConnectionIndex = 0; vpnConnectionIndex < list.getLength(); vpnConnectionIndex++) {
                Node vpnConnectionNode = list.item(vpnConnectionIndex);

                VpnConnectionResponse vpnConnection = new VpnConnectionResponse();
                NodeList vpnConnectionProperties = vpnConnectionNode.getChildNodes();
                for (int childIndex = 0; childIndex < vpnConnectionProperties.getLength(); childIndex++) {
                    Node property = vpnConnectionProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        vpnConnection.setVpnGatewayId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        vpnConnection.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("cidrlist")) {
                        vpnConnection.setCidrList(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        vpnConnection.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        vpnConnection.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("dpd")) {
                        vpnConnection.setDpd(property.getTextContent());
                    } else if (property.getNodeName().equals("esplifetime")) {
                        vpnConnection.setEspLifetime(property.getTextContent());
                    } else if (property.getNodeName().equals("esppolicy")) {
                        vpnConnection.setEspPolicy(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        vpnConnection.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ikelifetime")) {
                        vpnConnection.setIkeLifetime(property.getTextContent());
                    } else if (property.getNodeName().equals("ikepolicy")) {
                        vpnConnection.setIkePolicy(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        vpnConnection.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("ipsecpsk")) {
                        vpnConnection.setIpSecPsk(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        vpnConnection.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        vpnConnection.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        vpnConnection.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("removed")) {
                        vpnConnection.setRemoved(property.getTextContent());
                    }else if (property.getNodeName().equals("s2scustomergatewayid")) {
                        vpnConnection.setS2sCustomerGatewayId(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        vpnConnection.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("s2svpngatewayid")) {
                        vpnConnection.setS2sVpnGatewayId(property.getTextContent());
                    }
                }
                vpnConnections.add(vpnConnection);
            }

        }
        response.setVpnConnections(vpnConnections);
        return response;
    }
    
    /**
     * Creates site to site vpn connection
     *
     * @param s2sCustomerGatewayId id of the customer gateway
     * @param s2sVpnGatewayId id of the vpn gateway
     * @return
     * @throws Exception
     */
    public CreateVpnConnectionResponse createVpnConnection(String s2sCustomerGatewayId,
            String s2sVpnGatewayId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createVpnConnection", null);
        arguments.add(new NameValuePair("s2sCustomerGatewayId", s2sCustomerGatewayId));
        arguments.add(new NameValuePair("s2sVpnGatewayId", s2sVpnGatewayId));
        Document responseDocument = server.makeRequest(arguments);

        return getCreateVpnConnectionResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVpnCustomerGatewayResponse object
     *
     * @param doc
     * @return
     */
    private CreateVpnConnectionResponse getCreateVpnConnectionResponse(Document doc) {
        CreateVpnConnectionResponse response = new CreateVpnConnectionResponse();

        // get id from XML and set as the id of the remote access vpn
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnGatewayId(node.getTextContent());
        }

        // get account from XML and set as the account of the remote access vpn
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get cidrlist from XML and set as guest cidr list of the customer gateway
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain id of the owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get dpd from XML and set if DPD is enabled for customer gateway
        list = doc.getElementsByTagName("dpd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDpd(node.getTextContent());
        }

        // get esplifetime from XML and set as Lifetime of ESP SA of customer gateway
        list = doc.getElementsByTagName("esplifetime");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEspLifetime(node.getTextContent());
        }

        // get esppolicy from XML and set as IPsec policy of customer gateway
        list = doc.getElementsByTagName("esppolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEspPolicy(node.getTextContent());
        }

        // get gateway from XML and set as public ip address id of the customer gateway
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get ikelifetime from XML and set as Lifetime of IKE SA of customer gateway
        list = doc.getElementsByTagName("ikelifetime");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIkeLifetime(node.getTextContent());
        }

        // get ikepolicy from XML and set as IKE policy of customer gateway
        list = doc.getElementsByTagName("ikepolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIkePolicy(node.getTextContent());
        }

        // get ipaddress from XML and set as guest ip of the customer gateway
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get ipsecpsk from XML and set as IPsec preshared-key of customer gateway
        list = doc.getElementsByTagName("ipsecpsk");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpSecPsk(node.getTextContent());
        }

        // get name from XML and set as name of the customer gateway
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name 
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id 
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get removed from XML and set as the date and time the host was removed 
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get s2scustomergatewayid from XML and set as the customer gateway ID 
        list = doc.getElementsByTagName("s2scustomergatewayid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setS2sCustomerGatewayId(node.getTextContent());
        }

        // get s2svpngatewayid from XML and set as the vpn gateway ID 
        list = doc.getElementsByTagName("s2svpngatewayid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setS2sVpnGatewayId(node.getTextContent());
        }

        // get state from XML and set as the state of vpn connection
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        return response;
    }
    
    
    /**
     * Reset site to site vpn connection
     *
     * @param vpnConnectionId id of the customer gateway
     * @param optional
     * @return
     * @throws Exception
     */
    public ResetVpnConnectionResponse resetVpnConnection(String vpnConnectionId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("resetVpnConnection", optional);
        arguments.add(new NameValuePair("id", vpnConnectionId));

        Document responseDocument = server.makeRequest(arguments);

        return getResetVpnConnectionResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateVpnCustomerGatewayResponse object
     *
     * @param doc
     * @return
     */
    private ResetVpnConnectionResponse getResetVpnConnectionResponse(Document doc) {
        ResetVpnConnectionResponse response = new ResetVpnConnectionResponse();

        // get id from XML and set as the id of the remote access vpn
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVpnGatewayId(node.getTextContent());
        }

        // get account from XML and set as the account of the remote access vpn
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }
        
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get cidrlist from XML and set as guest cidr list of the customer gateway
        list = doc.getElementsByTagName("cidrlist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCidrList(node.getTextContent());
        }

        // get domain from XML and set as the domain name of the owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set as the domain id of the owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get dpd from XML and set if DPD is enabled for customer gateway
        list = doc.getElementsByTagName("dpd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDpd(node.getTextContent());
        }

        // get esplifetime from XML and set as Lifetime of ESP SA of customer gateway
        list = doc.getElementsByTagName("esplifetime");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEspLifetime(node.getTextContent());
        }

        // get esppolicy from XML and set as IPsec policy of customer gateway
        list = doc.getElementsByTagName("esppolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEspPolicy(node.getTextContent());
        }

        // get gateway from XML and set as public ip address id of the customer gateway
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get ikelifetime from XML and set as Lifetime of IKE SA of customer gateway
        list = doc.getElementsByTagName("ikelifetime");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIkeLifetime(node.getTextContent());
        }

        // get ikepolicy from XML and set as IKE policy of customer gateway
        list = doc.getElementsByTagName("ikepolicy");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIkePolicy(node.getTextContent());
        }

        // get ipaddress from XML and set as guest ip of the customer gateway
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get ipsecpsk from XML and set as IPsec preshared-key of customer gateway
        list = doc.getElementsByTagName("ipsecpsk");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpSecPsk(node.getTextContent());
        }

        // get name from XML and set as name of the customer gateway
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get project from XML and set as the project name 
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get projectid from XML and set as the project id 
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get removed from XML and set as the date and time the host was removed 
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get s2scustomergatewayid from XML and set as the customer gateway ID 
        list = doc.getElementsByTagName("s2scustomergatewayid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setS2sCustomerGatewayId(node.getTextContent());
        }

        // get s2svpngatewayid from XML and set as the vpn gateway ID 
        list = doc.getElementsByTagName("s2svpngatewayid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setS2sVpnGatewayId(node.getTextContent());
        }

        // get state from XML and set as the state of vpn connection
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        return response;
    }
    
    
    /**
     * Delete site to site vpn connection
     *
     * @param vpnConnectionId id of customer gateway
     * @return
     * @throws Exception
     */
    public DeleteVpnConnectionResponse deleteVpnConnection(String vpnConnectionId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteVpnConnection", null);
        arguments.add(new NameValuePair("id", vpnConnectionId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteVpnConnectionResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteVpnConnectionResponse object
     *
     * @param doc
     * @return
     */
    private DeleteVpnConnectionResponse getDeleteVpnConnectionResponse(Document doc) {
        DeleteVpnConnectionResponse response = new DeleteVpnConnectionResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete VPN
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete VPN
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
    
    
    
}
