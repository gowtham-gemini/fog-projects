package com.assistanz.cloud.cloudstack.router;

import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;


/**
 * 
 * @author Gowtham
 *
 */
public class CSRouterService {
	
	private CloudStackServer server;
	
	public CSRouterService(CloudStackServer server) {
        this.server = server;
	}
	
	/**
	 * Starts a router.
	 * 
	 * @param routerId 	the ID of the router
	 * @return
	 * @throws Exception
	 */
	public StartRouterResponse startRouter(String routerId) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("startRouter", null);
	    arguments.add(new NameValuePair("id",  routerId));

	    Document responseDocument = server.makeRequest(arguments);

	    return getStartRouterResponse(responseDocument);
	}
	
	
    /**
     * Converts XML document into StartRouterResponse object
     *
     * @param doc
     * @return
     */
    private StartRouterResponse getStartRouterResponse(Document doc) {
    	StartRouterResponse response = new StartRouterResponse();
    	
    	// get id from XML and set the id of the router    
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterId(node.getNodeValue());
        }
        
    	// get account from XML and set the account associated with the router
    	list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterAccount(node.getNodeValue());
        }
        
    	// get created from XML and set the date and time the router was created
    	list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterCreated(node.getNodeValue());
        }
        
    	// get dns1 from XML and set the dns first for the router
    	list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterDnsFirst(node.getNodeValue());
        }
        
    	// get dns2 from XML and set the dns second for the router
    	list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterDnsSecond(node.getNodeValue());
        }
        
    	// get domain from XML and set the domain for the router
    	list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterDomain(node.getNodeValue());
        }
        
    	// get domainid from XML and set the domainid for the router
    	list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterDomainid(node.getNodeValue());
        }
        
    	// get gateway from XML and set the gateway for the router
    	list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterGateway(node.getNodeValue());
        }
        
    	// get guestipaddress from XML and set the guest ip address for the router
    	list = doc.getElementsByTagName("guestipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterGuestIpAddress(node.getNodeValue());
        }
        
    	// get guestmacaddress from XML and set the guest maca ddress for the router
    	list = doc.getElementsByTagName("guestmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterGuestMacAddress(node.getNodeValue());
        }
        
    	// get guestnetmask from XML and set the guest net mask for the router
    	list = doc.getElementsByTagName("guestnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterGuestNetmask(node.getNodeValue());
        }
        
    	// get guestnetworkid from XML and set the guest net workid for the router
    	list = doc.getElementsByTagName("guestnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterGuestNetworkId(node.getNodeValue());
        }
        
    	// get hostid from XML and set the host id for the router
    	list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterHostId(node.getNodeValue());
        }
        
    	// get hostname from XML and set the host name for the router
    	list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterHostName(node.getNodeValue());
        }
        
    	// get isredundantrouter from XML and set the is redundant router for the router
    	list = doc.getElementsByTagName("isredundantrouter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterIsRedundant(node.getNodeValue());
        }
        
    	// get linklocalip from XML and set the linkl ocal ip for the router
    	list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterLinkLocalIp(node.getNodeValue());
        }
        
    	// get linklocalmacaddress from XML and set the link local mac address for the router
    	list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterLinkLocalMacAddress(node.getNodeValue());
        }
        
    	// get linklocalnetmask from XML and set the link local net mask for the router
    	list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterLinkLocalNetmask(node.getNodeValue());
        }
        
    	// get linklocalnetworkid from XML and set the link local network id for the router
    	list = doc.getElementsByTagName("linklocalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterLinkLocalNetworkId(node.getNodeValue());
        }
        
    	// get name from XML and set the name for the router
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterName(node.getNodeValue());
        }
        
    	// get networkdomain from XML and set the networkdomain for the router
    	list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterNetworkDomain(node.getNodeValue());
        }
        
    	// get podid from XML and set the podid for the router
    	list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterPodId(node.getNodeValue());
        }
        
    	// get project from XML and set the project name for the router
    	list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterProject(node.getNodeValue());
        }
        
    	// get projectid from XML and set the project id for the router
    	list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterProjectId(node.getNodeValue());
        }
        
    	// get publicip from XML and set the public ip for the router
    	list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterPublicIp(node.getNodeValue());
        }
        
    	// get publicmacaddress from XML and set the public mac address  for the router
    	list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterPublicMacAddress(node.getNodeValue());
        }
        
    	// get publicnetmask from XML and set the public net mask for the router
    	list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterPublicNetmask(node.getNodeValue());
        }
        
    	// get publicnetworkid from XML and set the public network id for the router
    	list = doc.getElementsByTagName("publicnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterPublicNetworkId(node.getNodeValue());
        }
        
    	// get redundantstate from XML and set the redundant state for the router
    	list = doc.getElementsByTagName("redundantstate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterRedundantState(node.getNodeValue());
        }
        
    	// get scriptsversion from XML and set the script sversion for the router
    	list = doc.getElementsByTagName("scriptsversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterScriptsVersion(node.getNodeValue());
        }
        
    	// get serviceofferingid from XML and set the service offering id for the router
    	list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterServiceOfferingId(node.getNodeValue());
        }
        
    	// get serviceofferingname from XML and set the service offering name for the router
    	list = doc.getElementsByTagName("serviceofferingname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterServiceOfferingName(node.getNodeValue());
        }
        
    	// get state from XML and set the state for the router
    	list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterState(node.getNodeValue());
        }
        
    	// get templateid from XML and set the templateid for the router
    	list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterTemplateId(node.getNodeValue());
        }
        
    	// get templateversion from XML and set the templateversion for the router
    	list = doc.getElementsByTagName("templateversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterTemplateVersion(node.getNodeValue());
        }
        
    	// get zoneid from XML and set the zoneid for the router
    	list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterZoneId(node.getNodeValue());
        }
        
    	// get zonename from XML and set the zonename for the router
    	list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRouterZoneName(node.getNodeValue());
        }
        
        return response;
    }    
    
    /**
     * Reboots router
     * 
     * @param routerId 	the ID of the router
     * @return
     * @throws Exception
     */
	public RebootRouterResponse rebootRouter(String routerId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("rebootRouter", null);
		arguments.add(new NameValuePair("id",  routerId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getRebootRouterResponse(responseDocument);
	}


	/**
	 *  Converts XML document into RebootRouterResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private RebootRouterResponse getRebootRouterResponse(Document doc) {
		RebootRouterResponse response = new RebootRouterResponse();

		// get id from XML and set the id of the router    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterId(node.getNodeValue());
		}
		
		// get account from XML and set the account associated with the router
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterAccount(node.getNodeValue());
		}
		
		// get created from XML and set the date and time the router was created
		list = doc.getElementsByTagName("created");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterCreated(node.getNodeValue());
		}
		
		// get dns1 from XML and set the dns first for the router
		list = doc.getElementsByTagName("dns1");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDnsFirst(node.getNodeValue());
		}
		
		// get dns2 from XML and set the dns second for the router
		list = doc.getElementsByTagName("dns2");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDnsSecond(node.getNodeValue());
		}
		
		// get domain from XML and set the domain for the router
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set the domainid for the router
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomainid(node.getNodeValue());
		}
		
		// get gateway from XML and set the gateway for the router
		list = doc.getElementsByTagName("gateway");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGateway(node.getNodeValue());
		}
		
		// get guestipaddress from XML and set the guest ip address for the router
		list = doc.getElementsByTagName("guestipaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestIpAddress(node.getNodeValue());
		}
		
		// get guestmacaddress from XML and set the guest maca ddress for the router
		list = doc.getElementsByTagName("guestmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestMacAddress(node.getNodeValue());
		}
		
		// get guestnetmask from XML and set the guest net mask for the router
		list = doc.getElementsByTagName("guestnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestNetmask(node.getNodeValue());
		}
		
		// get guestnetworkid from XML and set the guest net workid for the router
		list = doc.getElementsByTagName("guestnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestNetworkId(node.getNodeValue());
		}
		
		// get hostid from XML and set the host id for the router
		list = doc.getElementsByTagName("hostid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterHostId(node.getNodeValue());
		}
		
		// get hostname from XML and set the host name for the router
		list = doc.getElementsByTagName("hostname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterHostName(node.getNodeValue());
		}
		
		// get isredundantrouter from XML and set the is redundant router for the router
		list = doc.getElementsByTagName("isredundantrouter");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterIsRedundant(node.getNodeValue());
		}
		
		// get linklocalip from XML and set the linkl ocal ip for the router
		list = doc.getElementsByTagName("linklocalip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalIp(node.getNodeValue());
		}
		
		// get linklocalmacaddress from XML and set the link local mac address for the router
		list = doc.getElementsByTagName("linklocalmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalMacAddress(node.getNodeValue());
		}
		
		// get linklocalnetmask from XML and set the link local net mask for the router
		list = doc.getElementsByTagName("linklocalnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalNetmask(node.getNodeValue());
		}
		
		// get linklocalnetworkid from XML and set the link local network id for the router
		list = doc.getElementsByTagName("linklocalnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalNetworkId(node.getNodeValue());
		}
		
		// get name from XML and set the name for the router
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterName(node.getNodeValue());
		}
		
		// get networkdomain from XML and set the networkdomain for the router
		list = doc.getElementsByTagName("networkdomain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterNetworkDomain(node.getNodeValue());
		}
		
		// get podid from XML and set the podid for the router
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPodId(node.getNodeValue());
		}
		
		// get project from XML and set the project name for the router
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterProject(node.getNodeValue());
		}
		
		// get projectid from XML and set the project id for the router
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterProjectId(node.getNodeValue());
		}
		
		// get publicip from XML and set the public ip for the router
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicIp(node.getNodeValue());
		}
		
		// get publicmacaddress from XML and set the public mac address  for the router
		list = doc.getElementsByTagName("publicmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicMacAddress(node.getNodeValue());
		}
		
		// get publicnetmask from XML and set the public net mask for the router
		list = doc.getElementsByTagName("publicnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicNetmask(node.getNodeValue());
		}
		
		// get publicnetworkid from XML and set the public network id for the router
		list = doc.getElementsByTagName("publicnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicNetworkId(node.getNodeValue());
		}
		
		// get redundantstate from XML and set the redundant state for the router
		list = doc.getElementsByTagName("redundantstate");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterRedundantState(node.getNodeValue());
		}
		
		// get scriptsversion from XML and set the script sversion for the router
		list = doc.getElementsByTagName("scriptsversion");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterScriptsVersion(node.getNodeValue());
		}
		
		// get serviceofferingid from XML and set the service offering id for the router
		list = doc.getElementsByTagName("serviceofferingid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterServiceOfferingId(node.getNodeValue());
		}
		
		// get serviceofferingname from XML and set the service offering name for the router
		list = doc.getElementsByTagName("serviceofferingname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterServiceOfferingName(node.getNodeValue());
		}
		
		// get state from XML and set the state for the router
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterState(node.getNodeValue());
		}
		
		// get templateid from XML and set the templateid for the router
		list = doc.getElementsByTagName("templateid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterTemplateId(node.getNodeValue());
		}
		
		// get templateversion from XML and set the templateversion for the router
		list = doc.getElementsByTagName("templateversion");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterTemplateVersion(node.getNodeValue());
		}
		
		// get zoneid from XML and set the zoneid for the router
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterZoneId(node.getNodeValue());
		}
		
		// get zonename from XML and set the zonename for the router
		list = doc.getElementsByTagName("zonename");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterZoneName(node.getNodeValue());
		}
		
		return response;
	} 
	
	/**
	 * Stops a router.
	 * 
	 * @param routerId 	the ID of the router
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public StopRouterResponse stopRouter(String routerId,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("stopRouter", optional);
		arguments.add(new NameValuePair("id",  routerId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getStopRouterResponse(responseDocument);
	}


	/**
	 *  Converts XML document into StopRouterResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private StopRouterResponse getStopRouterResponse(Document doc) {
		StopRouterResponse response = new StopRouterResponse();

		// get id from XML and set the id of the router    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterId(node.getNodeValue());
		}
		
		// get account from XML and set the account associated with the router
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterAccount(node.getNodeValue());
		}
		
		// get created from XML and set the date and time the router was created
		list = doc.getElementsByTagName("created");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterCreated(node.getNodeValue());
		}
		
		// get dns1 from XML and set the dns first for the router
		list = doc.getElementsByTagName("dns1");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDnsFirst(node.getNodeValue());
		}
		
		// get dns2 from XML and set the dns second for the router
		list = doc.getElementsByTagName("dns2");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDnsSecond(node.getNodeValue());
		}
		
		// get domain from XML and set the domain for the router
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set the domainid for the router
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomainid(node.getNodeValue());
		}
		
		// get gateway from XML and set the gateway for the router
		list = doc.getElementsByTagName("gateway");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGateway(node.getNodeValue());
		}
		
		// get guestipaddress from XML and set the guest ip address for the router
		list = doc.getElementsByTagName("guestipaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestIpAddress(node.getNodeValue());
		}
		
		// get guestmacaddress from XML and set the guest maca ddress for the router
		list = doc.getElementsByTagName("guestmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestMacAddress(node.getNodeValue());
		}
		
		// get guestnetmask from XML and set the guest net mask for the router
		list = doc.getElementsByTagName("guestnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestNetmask(node.getNodeValue());
		}
		
		// get guestnetworkid from XML and set the guest net workid for the router
		list = doc.getElementsByTagName("guestnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestNetworkId(node.getNodeValue());
		}
		
		// get hostid from XML and set the host id for the router
		list = doc.getElementsByTagName("hostid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterHostId(node.getNodeValue());
		}
		
		// get hostname from XML and set the host name for the router
		list = doc.getElementsByTagName("hostname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterHostName(node.getNodeValue());
		}
		
		// get isredundantrouter from XML and set the is redundant router for the router
		list = doc.getElementsByTagName("isredundantrouter");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterIsRedundant(node.getNodeValue());
		}
		
		// get linklocalip from XML and set the linkl ocal ip for the router
		list = doc.getElementsByTagName("linklocalip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalIp(node.getNodeValue());
		}
		
		// get linklocalmacaddress from XML and set the link local mac address for the router
		list = doc.getElementsByTagName("linklocalmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalMacAddress(node.getNodeValue());
		}
		
		// get linklocalnetmask from XML and set the link local net mask for the router
		list = doc.getElementsByTagName("linklocalnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalNetmask(node.getNodeValue());
		}
		
		// get linklocalnetworkid from XML and set the link local network id for the router
		list = doc.getElementsByTagName("linklocalnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalNetworkId(node.getNodeValue());
		}
		
		// get name from XML and set the name for the router
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterName(node.getNodeValue());
		}
		
		// get networkdomain from XML and set the networkdomain for the router
		list = doc.getElementsByTagName("networkdomain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterNetworkDomain(node.getNodeValue());
		}
		
		// get podid from XML and set the podid for the router
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPodId(node.getNodeValue());
		}
		
		// get project from XML and set the project name for the router
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterProject(node.getNodeValue());
		}
		
		// get projectid from XML and set the project id for the router
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterProjectId(node.getNodeValue());
		}
		
		// get publicip from XML and set the public ip for the router
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicIp(node.getNodeValue());
		}
		
		// get publicmacaddress from XML and set the public mac address  for the router
		list = doc.getElementsByTagName("publicmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicMacAddress(node.getNodeValue());
		}
		
		// get publicnetmask from XML and set the public net mask for the router
		list = doc.getElementsByTagName("publicnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicNetmask(node.getNodeValue());
		}
		
		// get publicnetworkid from XML and set the public network id for the router
		list = doc.getElementsByTagName("publicnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicNetworkId(node.getNodeValue());
		}
		
		// get redundantstate from XML and set the redundant state for the router
		list = doc.getElementsByTagName("redundantstate");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterRedundantState(node.getNodeValue());
		}
		
		// get scriptsversion from XML and set the script sversion for the router
		list = doc.getElementsByTagName("scriptsversion");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterScriptsVersion(node.getNodeValue());
		}
		
		// get serviceofferingid from XML and set the service offering id for the router
		list = doc.getElementsByTagName("serviceofferingid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterServiceOfferingId(node.getNodeValue());
		}
		
		// get serviceofferingname from XML and set the service offering name for the router
		list = doc.getElementsByTagName("serviceofferingname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterServiceOfferingName(node.getNodeValue());
		}
		
		// get state from XML and set the state for the router
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterState(node.getNodeValue());
		}
		
		// get templateid from XML and set the templateid for the router
		list = doc.getElementsByTagName("templateid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterTemplateId(node.getNodeValue());
		}
		
		// get templateversion from XML and set the templateversion for the router
		list = doc.getElementsByTagName("templateversion");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterTemplateVersion(node.getNodeValue());
		}
		
		// get zoneid from XML and set the zoneid for the router
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterZoneId(node.getNodeValue());
		}
		
		// get zonename from XML and set the zonename for the router
		list = doc.getElementsByTagName("zonename");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterZoneName(node.getNodeValue());
		}
		
		return response;
	} 
	
	/**
	 * Destroys a router.
	 * 
	 * @param routerId 	the ID of the router
	 * @return
	 * @throws Exception
	 */
	public DestroyRouterResponse destroyRouter(String routerId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("destroyRouter", null);
		arguments.add(new NameValuePair("id",  routerId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getDestroyRouterResponse(responseDocument);
	}


	/**
	 *  Converts XML document into DestroyRouterResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private DestroyRouterResponse getDestroyRouterResponse(Document doc) {
		DestroyRouterResponse response = new DestroyRouterResponse();

		// get id from XML and set the id of the router    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterId(node.getNodeValue());
		}
		
		// get account from XML and set the account associated with the router
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterAccount(node.getNodeValue());
		}
		
		// get created from XML and set the date and time the router was created
		list = doc.getElementsByTagName("created");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterCreated(node.getNodeValue());
		}
		
		// get dns1 from XML and set the dns first for the router
		list = doc.getElementsByTagName("dns1");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDnsFirst(node.getNodeValue());
		}
		
		// get dns2 from XML and set the dns second for the router
		list = doc.getElementsByTagName("dns2");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDnsSecond(node.getNodeValue());
		}
		
		// get domain from XML and set the domain for the router
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set the domainid for the router
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomainid(node.getNodeValue());
		}
		
		// get gateway from XML and set the gateway for the router
		list = doc.getElementsByTagName("gateway");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGateway(node.getNodeValue());
		}
		
		// get guestipaddress from XML and set the guest ip address for the router
		list = doc.getElementsByTagName("guestipaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestIpAddress(node.getNodeValue());
		}
		
		// get guestmacaddress from XML and set the guest maca ddress for the router
		list = doc.getElementsByTagName("guestmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestMacAddress(node.getNodeValue());
		}
		
		// get guestnetmask from XML and set the guest net mask for the router
		list = doc.getElementsByTagName("guestnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestNetmask(node.getNodeValue());
		}
		
		// get guestnetworkid from XML and set the guest net workid for the router
		list = doc.getElementsByTagName("guestnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestNetworkId(node.getNodeValue());
		}
		
		// get hostid from XML and set the host id for the router
		list = doc.getElementsByTagName("hostid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterHostId(node.getNodeValue());
		}
		
		// get hostname from XML and set the host name for the router
		list = doc.getElementsByTagName("hostname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterHostName(node.getNodeValue());
		}
		
		// get isredundantrouter from XML and set the is redundant router for the router
		list = doc.getElementsByTagName("isredundantrouter");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterIsRedundant(node.getNodeValue());
		}
		
		// get linklocalip from XML and set the linkl ocal ip for the router
		list = doc.getElementsByTagName("linklocalip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalIp(node.getNodeValue());
		}
		
		// get linklocalmacaddress from XML and set the link local mac address for the router
		list = doc.getElementsByTagName("linklocalmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalMacAddress(node.getNodeValue());
		}
		
		// get linklocalnetmask from XML and set the link local net mask for the router
		list = doc.getElementsByTagName("linklocalnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalNetmask(node.getNodeValue());
		}
		
		// get linklocalnetworkid from XML and set the link local network id for the router
		list = doc.getElementsByTagName("linklocalnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalNetworkId(node.getNodeValue());
		}
		
		// get name from XML and set the name for the router
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterName(node.getNodeValue());
		}
		
		// get networkdomain from XML and set the networkdomain for the router
		list = doc.getElementsByTagName("networkdomain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterNetworkDomain(node.getNodeValue());
		}
		
		// get podid from XML and set the podid for the router
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPodId(node.getNodeValue());
		}
		
		// get project from XML and set the project name for the router
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterProject(node.getNodeValue());
		}
		
		// get projectid from XML and set the project id for the router
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterProjectId(node.getNodeValue());
		}
		
		// get publicip from XML and set the public ip for the router
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicIp(node.getNodeValue());
		}
		
		// get publicmacaddress from XML and set the public mac address  for the router
		list = doc.getElementsByTagName("publicmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicMacAddress(node.getNodeValue());
		}
		
		// get publicnetmask from XML and set the public net mask for the router
		list = doc.getElementsByTagName("publicnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicNetmask(node.getNodeValue());
		}
		
		// get publicnetworkid from XML and set the public network id for the router
		list = doc.getElementsByTagName("publicnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicNetworkId(node.getNodeValue());
		}
		
		// get redundantstate from XML and set the redundant state for the router
		list = doc.getElementsByTagName("redundantstate");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterRedundantState(node.getNodeValue());
		}
		
		// get scriptsversion from XML and set the script sversion for the router
		list = doc.getElementsByTagName("scriptsversion");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterScriptsVersion(node.getNodeValue());
		}
		
		// get serviceofferingid from XML and set the service offering id for the router
		list = doc.getElementsByTagName("serviceofferingid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterServiceOfferingId(node.getNodeValue());
		}
		
		// get serviceofferingname from XML and set the service offering name for the router
		list = doc.getElementsByTagName("serviceofferingname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterServiceOfferingName(node.getNodeValue());
		}
		
		// get state from XML and set the state for the router
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterState(node.getNodeValue());
		}
		
		// get templateid from XML and set the templateid for the router
		list = doc.getElementsByTagName("templateid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterTemplateId(node.getNodeValue());
		}
		
		// get templateversion from XML and set the templateversion for the router
		list = doc.getElementsByTagName("templateversion");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterTemplateVersion(node.getNodeValue());
		}
		
		// get zoneid from XML and set the zoneid for the router
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterZoneId(node.getNodeValue());
		}
		
		// get zonename from XML and set the zonename for the router
		list = doc.getElementsByTagName("zonename");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterZoneName(node.getNodeValue());
		}
		
		return response;
	} 
	
	/**
	 * Upgrades domain router to a new service offering
	 * 
	 * @param routerId The ID of the router
	 * @param routerServiceOfferingId the service offering ID to apply to the domain router
	 * @return
	 * @throws Exception
	 */
	public ChangeServiceForRouterResponse changeServiceForRouter(String routerId,
			String routerServiceOfferingId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("changeServiceForRouter", null);
		arguments.add(new NameValuePair("id",  routerId));
		arguments.add(new NameValuePair("id",  routerServiceOfferingId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getChangeServiceForRouterResponse(responseDocument);
	}


	/**
	 *  Converts XML document into ChangeServiceForRouterResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ChangeServiceForRouterResponse getChangeServiceForRouterResponse(Document doc) {
		ChangeServiceForRouterResponse response = new ChangeServiceForRouterResponse();

		// get id from XML and set the id of the router    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterId(node.getNodeValue());
		}
		
		// get account from XML and set the account associated with the router
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterAccount(node.getNodeValue());
		}
		
		// get created from XML and set the date and time the router was created
		list = doc.getElementsByTagName("created");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterCreated(node.getNodeValue());
		}
		
		// get dns1 from XML and set the dns first for the router
		list = doc.getElementsByTagName("dns1");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDnsFirst(node.getNodeValue());
		}
		
		// get dns2 from XML and set the dns second for the router
		list = doc.getElementsByTagName("dns2");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDnsSecond(node.getNodeValue());
		}
		
		// get domain from XML and set the domain for the router
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set the domainid for the router
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomainid(node.getNodeValue());
		}
		
		// get gateway from XML and set the gateway for the router
		list = doc.getElementsByTagName("gateway");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGateway(node.getNodeValue());
		}
		
		// get guestipaddress from XML and set the guest ip address for the router
		list = doc.getElementsByTagName("guestipaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestIpAddress(node.getNodeValue());
		}
		
		// get guestmacaddress from XML and set the guest maca ddress for the router
		list = doc.getElementsByTagName("guestmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestMacAddress(node.getNodeValue());
		}
		
		// get guestnetmask from XML and set the guest net mask for the router
		list = doc.getElementsByTagName("guestnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestNetmask(node.getNodeValue());
		}
		
		// get guestnetworkid from XML and set the guest net workid for the router
		list = doc.getElementsByTagName("guestnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestNetworkId(node.getNodeValue());
		}
		
		// get hostid from XML and set the host id for the router
		list = doc.getElementsByTagName("hostid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterHostId(node.getNodeValue());
		}
		
		// get hostname from XML and set the host name for the router
		list = doc.getElementsByTagName("hostname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterHostName(node.getNodeValue());
		}
		
		// get isredundantrouter from XML and set the is redundant router for the router
		list = doc.getElementsByTagName("isredundantrouter");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterIsRedundant(node.getNodeValue());
		}
		
		// get linklocalip from XML and set the linkl ocal ip for the router
		list = doc.getElementsByTagName("linklocalip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalIp(node.getNodeValue());
		}
		
		// get linklocalmacaddress from XML and set the link local mac address for the router
		list = doc.getElementsByTagName("linklocalmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalMacAddress(node.getNodeValue());
		}
		
		// get linklocalnetmask from XML and set the link local net mask for the router
		list = doc.getElementsByTagName("linklocalnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalNetmask(node.getNodeValue());
		}
		
		// get linklocalnetworkid from XML and set the link local network id for the router
		list = doc.getElementsByTagName("linklocalnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalNetworkId(node.getNodeValue());
		}
		
		// get name from XML and set the name for the router
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterName(node.getNodeValue());
		}
		
		// get networkdomain from XML and set the networkdomain for the router
		list = doc.getElementsByTagName("networkdomain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterNetworkDomain(node.getNodeValue());
		}
		
		// get podid from XML and set the podid for the router
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPodId(node.getNodeValue());
		}
		
		// get project from XML and set the project name for the router
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterProject(node.getNodeValue());
		}
		
		// get projectid from XML and set the project id for the router
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterProjectId(node.getNodeValue());
		}
		
		// get publicip from XML and set the public ip for the router
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicIp(node.getNodeValue());
		}
		
		// get publicmacaddress from XML and set the public mac address  for the router
		list = doc.getElementsByTagName("publicmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicMacAddress(node.getNodeValue());
		}
		
		// get publicnetmask from XML and set the public net mask for the router
		list = doc.getElementsByTagName("publicnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicNetmask(node.getNodeValue());
		}
		
		// get publicnetworkid from XML and set the public network id for the router
		list = doc.getElementsByTagName("publicnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicNetworkId(node.getNodeValue());
		}
		
		// get redundantstate from XML and set the redundant state for the router
		list = doc.getElementsByTagName("redundantstate");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterRedundantState(node.getNodeValue());
		}
		
		// get scriptsversion from XML and set the script sversion for the router
		list = doc.getElementsByTagName("scriptsversion");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterScriptsVersion(node.getNodeValue());
		}
		
		// get serviceofferingid from XML and set the service offering id for the router
		list = doc.getElementsByTagName("serviceofferingid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterServiceOfferingId(node.getNodeValue());
		}
		
		// get serviceofferingname from XML and set the service offering name for the router
		list = doc.getElementsByTagName("serviceofferingname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterServiceOfferingName(node.getNodeValue());
		}
		
		// get state from XML and set the state for the router
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterState(node.getNodeValue());
		}
		
		// get templateid from XML and set the templateid for the router
		list = doc.getElementsByTagName("templateid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterTemplateId(node.getNodeValue());
		}
		
		// get templateversion from XML and set the templateversion for the router
		list = doc.getElementsByTagName("templateversion");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterTemplateVersion(node.getNodeValue());
		}
		
		// get zoneid from XML and set the zoneid for the router
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterZoneId(node.getNodeValue());
		}
		
		// get zonename from XML and set the zonename for the router
		list = doc.getElementsByTagName("zonename");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterZoneName(node.getNodeValue());
		}
		
		return response;
	} 
	
	/**
	 * List routers.
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListRoutersResponse listRouters(
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("stopRouter", optional);
				
		Document responseDocument = server.makeRequest(arguments);
		
		return getListRoutersResponse(responseDocument);
	}


	/**
	 *  Converts XML document into ListRoutersResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListRoutersResponse getListRoutersResponse(Document doc) {
		ListRoutersResponse response = new ListRoutersResponse();

		// get id from XML and set the id of the router    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterId(node.getNodeValue());
		}
		
		// get account from XML and set the account associated with the router
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterAccount(node.getNodeValue());
		}
		
		// get created from XML and set the date and time the router was created
		list = doc.getElementsByTagName("created");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterCreated(node.getNodeValue());
		}
		
		// get dns1 from XML and set the dns first for the router
		list = doc.getElementsByTagName("dns1");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDnsFirst(node.getNodeValue());
		}
		
		// get dns2 from XML and set the dns second for the router
		list = doc.getElementsByTagName("dns2");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDnsSecond(node.getNodeValue());
		}
		
		// get domain from XML and set the domain for the router
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set the domainid for the router
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomainid(node.getNodeValue());
		}
		
		// get gateway from XML and set the gateway for the router
		list = doc.getElementsByTagName("gateway");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGateway(node.getNodeValue());
		}
		
		// get guestipaddress from XML and set the guest ip address for the router
		list = doc.getElementsByTagName("guestipaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestIpAddress(node.getNodeValue());
		}
		
		// get guestmacaddress from XML and set the guest maca ddress for the router
		list = doc.getElementsByTagName("guestmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestMacAddress(node.getNodeValue());
		}
		
		// get guestnetmask from XML and set the guest net mask for the router
		list = doc.getElementsByTagName("guestnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestNetmask(node.getNodeValue());
		}
		
		// get guestnetworkid from XML and set the guest net workid for the router
		list = doc.getElementsByTagName("guestnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterGuestNetworkId(node.getNodeValue());
		}
		
		// get hostid from XML and set the host id for the router
		list = doc.getElementsByTagName("hostid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterHostId(node.getNodeValue());
		}
		
		// get hostname from XML and set the host name for the router
		list = doc.getElementsByTagName("hostname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterHostName(node.getNodeValue());
		}
		
		// get isredundantrouter from XML and set the is redundant router for the router
		list = doc.getElementsByTagName("isredundantrouter");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterIsRedundant(node.getNodeValue());
		}
		
		// get linklocalip from XML and set the linkl ocal ip for the router
		list = doc.getElementsByTagName("linklocalip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalIp(node.getNodeValue());
		}
		
		// get linklocalmacaddress from XML and set the link local mac address for the router
		list = doc.getElementsByTagName("linklocalmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalMacAddress(node.getNodeValue());
		}
		
		// get linklocalnetmask from XML and set the link local net mask for the router
		list = doc.getElementsByTagName("linklocalnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalNetmask(node.getNodeValue());
		}
		
		// get linklocalnetworkid from XML and set the link local network id for the router
		list = doc.getElementsByTagName("linklocalnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterLinkLocalNetworkId(node.getNodeValue());
		}
		
		// get name from XML and set the name for the router
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterName(node.getNodeValue());
		}
		
		// get networkdomain from XML and set the networkdomain for the router
		list = doc.getElementsByTagName("networkdomain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterNetworkDomain(node.getNodeValue());
		}
		
		// get podid from XML and set the podid for the router
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPodId(node.getNodeValue());
		}
		
		// get project from XML and set the project name for the router
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterProject(node.getNodeValue());
		}
		
		// get projectid from XML and set the project id for the router
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterProjectId(node.getNodeValue());
		}
		
		// get publicip from XML and set the public ip for the router
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicIp(node.getNodeValue());
		}
		
		// get publicmacaddress from XML and set the public mac address  for the router
		list = doc.getElementsByTagName("publicmacaddress");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicMacAddress(node.getNodeValue());
		}
		
		// get publicnetmask from XML and set the public net mask for the router
		list = doc.getElementsByTagName("publicnetmask");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicNetmask(node.getNodeValue());
		}
		
		// get publicnetworkid from XML and set the public network id for the router
		list = doc.getElementsByTagName("publicnetworkid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterPublicNetworkId(node.getNodeValue());
		}
		
		// get redundantstate from XML and set the redundant state for the router
		list = doc.getElementsByTagName("redundantstate");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterRedundantState(node.getNodeValue());
		}
		
		// get scriptsversion from XML and set the script sversion for the router
		list = doc.getElementsByTagName("scriptsversion");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterScriptsVersion(node.getNodeValue());
		}
		
		// get serviceofferingid from XML and set the service offering id for the router
		list = doc.getElementsByTagName("serviceofferingid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterServiceOfferingId(node.getNodeValue());
		}
		
		// get serviceofferingname from XML and set the service offering name for the router
		list = doc.getElementsByTagName("serviceofferingname");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterServiceOfferingName(node.getNodeValue());
		}
		
		// get state from XML and set the state for the router
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterState(node.getNodeValue());
		}
		
		// get templateid from XML and set the templateid for the router
		list = doc.getElementsByTagName("templateid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterTemplateId(node.getNodeValue());
		}
		
		// get templateversion from XML and set the templateversion for the router
		list = doc.getElementsByTagName("templateversion");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterTemplateVersion(node.getNodeValue());
		}
		
		// get zoneid from XML and set the zoneid for the router
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterZoneId(node.getNodeValue());
		}
		
		// get zonename from XML and set the zonename for the router
		list = doc.getElementsByTagName("zonename");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterZoneName(node.getNodeValue());
		}
		
		return response;
	} 
	
	/**
	 * Create a virtual router element.
	 * 
	 * @param networkServiceProviderId 	the network service provider ID of the virtual router element
	 * @return
	 * @throws Exception
	 */
	public CreateVirtualRouterElementResponse createVirtualRouterElement(String networkServiceProviderId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("createVirtualRouterElement", null);
		arguments.add(new NameValuePair("nspid",  networkServiceProviderId));
				
		Document responseDocument = server.makeRequest(arguments);
		
		return getCreateVirtualRouterElementResponse(responseDocument);
	}
	
	/**
	 *  Converts XML document into CreateVirtualRouterElementResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private CreateVirtualRouterElementResponse getCreateVirtualRouterElementResponse(Document doc) {
		CreateVirtualRouterElementResponse response = new CreateVirtualRouterElementResponse();

		// get id from XML and set the id of the router    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterId(node.getNodeValue());
		}
		
		// get account from XML and set the account associated with the router
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterAccount(node.getNodeValue());
		}
		
		// get domain from XML and set the domain for the router
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set the domainid for the router
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomainId(node.getNodeValue());
		}
		
		// get enabled from XML and set Enabled/Disabled the service provider for router
		list = doc.getElementsByTagName("enabled");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterEnabled(node.getNodeValue());
		}
		
		// get nspid from XML and set the physical network service provider id of the provider
		list = doc.getElementsByTagName("nspid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setNetworkServiceProviderId(node.getNodeValue());
		}
		
		// get project from XML and the project name of the address
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setAddressProjectName(node.getNodeValue());
		}
		
		// get projectid from XML and set the project id of the ipaddress
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setIpaddressProjectId(node.getNodeValue());
		}
		
		return response;
	}	
	
	/**
	 * Configures a virtual router element.
	 * 
	 * @param virtualRouterProviderId the ID of the virtual router provider
	 * @param virtualRouterProvideEnabled Enabled/Disabled the service provider
	 * @return
	 * @throws Exception
	 */
	public ConfigureVirtualRouterElementResponse configureVirtualRouterElement(String virtualRouterProviderId,
			String virtualRouterProvideEnabled) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("configureVirtualRouterElement", null);
		arguments.add(new NameValuePair("id",  virtualRouterProviderId));
		arguments.add(new NameValuePair("enabled",  virtualRouterProvideEnabled));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getConfigureVirtualRouterElementResponse(responseDocument);
	}
	
	/**
	 *  Converts XML document into ConfigureVirtualRouterElementResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ConfigureVirtualRouterElementResponse getConfigureVirtualRouterElementResponse(Document doc) {
		ConfigureVirtualRouterElementResponse response = new ConfigureVirtualRouterElementResponse();

		// get id from XML and set the id of the router    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterId(node.getNodeValue());
		}
		
		// get account from XML and set the account associated with the router
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterAccount(node.getNodeValue());
		}
		
		// get domain from XML and set the domain for the router
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set the domainid for the router
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomainId(node.getNodeValue());
		}
		
		// get enabled from XML and set Enabled/Disabled the service provider for router
		list = doc.getElementsByTagName("enabled");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterEnabled(node.getNodeValue());
		}
		
		// get nspid from XML and set the physical network service provider id of the provider
		list = doc.getElementsByTagName("nspid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setNetworkServiceProviderId(node.getNodeValue());
		}
		
		// get project from XML and the project name of the address
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setAddressProjectName(node.getNodeValue());
		}
		
		// get projectid from XML and set the project id of the ipaddress
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setIpaddressProjectId(node.getNodeValue());
		}
		

		return response;
	}
	
	/**
	 * Lists all available virtual router elements.
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListVirtualRouterElementsResponse listVirtualRouterElements(
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listVirtualRouterElements", optional);
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getListVirtualRouterElementsResponse(responseDocument);
	}
	
	/**
	 *  Converts XML document into ListVirtualRouterElementsResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListVirtualRouterElementsResponse getListVirtualRouterElementsResponse(Document doc) {
		ListVirtualRouterElementsResponse response = new ListVirtualRouterElementsResponse();

		// get id from XML and set the id of the router    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterId(node.getNodeValue());
		}
		
		// get account from XML and set the account associated with the router
		list = doc.getElementsByTagName("account");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterAccount(node.getNodeValue());
		}
		
		// get domain from XML and set the domain for the router
		list = doc.getElementsByTagName("domain");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomain(node.getNodeValue());
		}
		
		// get domainid from XML and set the domainid for the router
		list = doc.getElementsByTagName("domainid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterDomainId(node.getNodeValue());
		}
		
		// get enabled from XML and set Enabled/Disabled the service provider for router
		list = doc.getElementsByTagName("enabled");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setRouterEnabled(node.getNodeValue());
		}
		
		// get nspid from XML and set the physical network service provider id of the provider
		list = doc.getElementsByTagName("nspid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setNetworkServiceProviderId(node.getNodeValue());
		}
		
		// get project from XML and the project name of the address
		list = doc.getElementsByTagName("project");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setAddressProjectName(node.getNodeValue());
		}
		
		// get projectid from XML and set the project id of the ipaddress
		list = doc.getElementsByTagName("projectid");
		if (list.getLength() > 0) {
		    Node node = list.item(0);
		    response.setIpaddressProjectId(node.getNodeValue());
		}
		

		return response;
	}

}
