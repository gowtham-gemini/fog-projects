package com.assistanz.cloud.cloudstack.systemvm;

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
public class CSSystemVirtualMachineService {
	
	private CloudStackServer server;
	
	public CSSystemVirtualMachineService(CloudStackServer server) {
        this.server = server;
	}
	
	/**
	 * Reboots a system VM.
	 * 
	 * @param systemVirtualMachineId The ID of the system virtual machine
	 * @return
	 * @throws Exception
	 */
	public RebootSystemVmResponse rebootSystemVm(String systemVirtualMachineId) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("rebootSystemVm", null);
	    arguments.add(new NameValuePair("id",   systemVirtualMachineId));
	 
	    Document responseDocument = server.makeRequest(arguments);

	    return getRebootSystemVmResponse(responseDocument);
	}
	
    /**
     * Converts XML document into RebootSystemVmResponse object
     *
     * @param doc
     * @return
     */
    private RebootSystemVmResponse getRebootSystemVmResponse(Document doc) {
    	RebootSystemVmResponse response = new RebootSystemVmResponse();
    	
    	// get Id from XML and set the ID of the system VM    
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineId(node.getNodeValue());
        }
        
    	// get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
    	list = doc.getElementsByTagName("activeviewersessions");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineActiveViewerSessions(node.getNodeValue());
        }
        
    	// get created from XML and set the date and time the system VM was created  
    	list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineCreated(node.getNodeValue());
        }
        
    	// get dns1 from XML and set the first DNS for the system VM
    	list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineDnsFirst(node.getNodeValue());
        }
        
    	// get dns2 from XML and set the second DNS for the system VM
    	list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineDnsSecond(node.getNodeValue());
        }
        
    	// get gateway from XML and set the gateway for the system VM
    	list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineGateway(node.getNodeValue());
        }
        
    	// get hostid from XML and set the host id for the system VM
    	list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineHostId(node.getNodeValue());
        }
        
    	// get hostname from XML and set the host name for the system VM
    	list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineHostName(node.getNodeValue());
        }
        
      	// get jobid from XML and set the job id for the system VM
    	list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineJobId(node.getNodeValue());
        }
        
        
      	// get jobstatus from XML and set the jobstatus for the system VM
    	list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineJobStatus(node.getNodeValue());
        }
        
      	// get linklocalip from XML and set the link local ip address for the system VM
    	list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineLinkLocalIp(node.getNodeValue());
        }
        
        
      	// get linklocalmacaddress from XML and set the link local mac address for the system VM
    	list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineLinkLocalMacAddress(node.getNodeValue());
        }
        
      	// get linklocalnetmask from XML and set the link local mac address for the system VM
    	list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineLinkLocalNetmask(node.getNodeValue());
        }
        
      	// get name from XML and set the name for the system VM
    	list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineName(node.getNodeValue());
        }
        
      	// get networkdomain from XML and set the network domain for the system VM
    	list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineNetworkDomain(node.getNodeValue());
        }
        
      	// get podid from XML and set the podid for the system VM
    	list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachinePodId(node.getNodeValue());
        }
        
        
      	// get privateip from XML and set the private ip for the system VM
    	list = doc.getElementsByTagName("privateip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachinePrivateIp(node.getNodeValue());
        }
        
      	// get privatemacaddress from XML and set the private mac address for the system VM
    	list = doc.getElementsByTagName("privatemacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachinePrivateMacAddress(node.getNodeValue());
        }
        
        
      	// get privatenetmask from XML and set the private netmask for the system VM
    	list = doc.getElementsByTagName("privatenetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachinePrivateNetmask(node.getNodeValue());
        }
        
      	// get publicip from XML and set the public ip for the system VM
    	list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachinePublicIp(node.getNodeValue());
        }
        
      	// get publicmacaddress from XML and set the public mac address for the system VM
    	list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachinePublicMacAddress(node.getNodeValue());
        }
        
      	// get publicnetmask from XML and set the public net mask for the system VM
    	list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachinePublicNetmask(node.getNodeValue());
        }
        
      	// get state from XML and set state mask for the system VM
    	list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineState(node.getNodeValue());
        }
        
      	// get systemvmtype from XML and set systemvm type for the system VM
    	list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineType(node.getNodeValue());
        }
        
      	// get templateid from XML and set template id  for the system VM
    	list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineTemplateId(node.getNodeValue());
        }
        
      	// get zoneid from XML and set zone id for the system VM
    	list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineZoneId(node.getNodeValue());
        }
        
      	// get zonename from XML and set zone name for the system VM
    	list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVirtualMachineZoneName(node.getNodeValue());
        }
        
        return response;
    } 
    
    /**
     * Starts a system virtual machine.
     * 
     * @param systemVirtualMachineId The ID of the system virtual machine
     * @return
     * @throws Exception
     */
	public StartSystemVmResponse startSystemVm(String systemVirtualMachineId) 
					throws Exception {
	    
	    LinkedList<NameValuePair> arguments = 
	            server.getDefaultQuery("startSystemVm", null);
	    arguments.add(new NameValuePair("id",   systemVirtualMachineId));
	 
	    Document responseDocument = server.makeRequest(arguments);

	    return getStartSystemVmResponse(responseDocument);
	}
	
   /**
    * Converts XML document into StartSystemVmResponse object
    *
    * @param doc
    * @return
    */
   private StartSystemVmResponse getStartSystemVmResponse(Document doc) {
   	StartSystemVmResponse response = new StartSystemVmResponse();
   	
	   	// get Id from XML and set the ID of the system VM    
	   	NodeList list = doc.getElementsByTagName("id");
	   	if (list.getLength() > 0) {
	           Node node = list.item(0);
	           response.setSystemVirtualMachineId(node.getNodeValue());
	   	}
       
	   	// get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
	   	list = doc.getElementsByTagName("activeviewersessions");
	   	if (list.getLength() > 0) {
	           Node node = list.item(0);
	           response.setSystemVirtualMachineActiveViewerSessions(node.getNodeValue());
	   	}
       
	   	// get created from XML and set the date and time the system VM was created  
	   	list = doc.getElementsByTagName("created");
	   	if (list.getLength() > 0) {
	           Node node = list.item(0);
	           response.setSystemVirtualMachineCreated(node.getNodeValue());
	   	}
       
	   	// get dns1 from XML and set the first DNS for the system VM
	   	list = doc.getElementsByTagName("dns1");
	   	if (list.getLength() > 0) {
	           Node node = list.item(0);
	           response.setSystemVirtualMachineDnsFirst(node.getNodeValue());
	   	}
	       
		// get dns2 from XML and set the second DNS for the system VM
		list = doc.getElementsByTagName("dns2");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineDnsSecond(node.getNodeValue());
		}
	       
	   	// get gateway from XML and set the gateway for the system VM
	   	list = doc.getElementsByTagName("gateway");
	   	if (list.getLength() > 0) {
	           Node node = list.item(0);
	           response.setSystemVirtualMachineGateway(node.getNodeValue());
	   	}
       
		// get hostid from XML and set the host id for the system VM
		list = doc.getElementsByTagName("hostid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineHostId(node.getNodeValue());
		}
       
		// get hostname from XML and set the host name for the system VM
		list = doc.getElementsByTagName("hostname");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineHostName(node.getNodeValue());
		}
       
		// get jobid from XML and set the job id for the system VM
		list = doc.getElementsByTagName("jobid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineJobId(node.getNodeValue());
		}
		   
       
		// get jobstatus from XML and set the jobstatus for the system VM
		list = doc.getElementsByTagName("jobstatus");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineJobStatus(node.getNodeValue());
		}
       
		// get linklocalip from XML and set the link local ip address for the system VM
		list = doc.getElementsByTagName("linklocalip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalIp(node.getNodeValue());
		}
       
   
		// get linklocalmacaddress from XML and set the link local mac address for the system VM
		list = doc.getElementsByTagName("linklocalmacaddress");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalMacAddress(node.getNodeValue());
		}
       
		// get linklocalnetmask from XML and set the link local mac address for the system VM
		list = doc.getElementsByTagName("linklocalnetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalNetmask(node.getNodeValue());
		}
       
		// get name from XML and set the name for the system VM
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineName(node.getNodeValue());
		}
		   
		// get networkdomain from XML and set the network domain for the system VM
		list = doc.getElementsByTagName("networkdomain");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineNetworkDomain(node.getNodeValue());
		}
		   
		// get podid from XML and set the podid for the system VM
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePodId(node.getNodeValue());
		}
		   
       
		// get privateip from XML and set the private ip for the system VM
		list = doc.getElementsByTagName("privateip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateIp(node.getNodeValue());
		}
       
		// get privatemacaddress from XML and set the private mac address for the system VM
		list = doc.getElementsByTagName("privatemacaddress");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateMacAddress(node.getNodeValue());
		}
       
   
		// get privatenetmask from XML and set the private netmask for the system VM
		list = doc.getElementsByTagName("privatenetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateNetmask(node.getNodeValue());
		}
       
		// get publicip from XML and set the public ip for the system VM
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicIp(node.getNodeValue());
		}
       
		// get publicmacaddress from XML and set the public mac address for the system VM
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicMacAddress(node.getNodeValue());
		}
       
		// get publicnetmask from XML and set the public net mask for the system VM
		list = doc.getElementsByTagName("publicnetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicNetmask(node.getNodeValue());
		}
       
		// get state from XML and set state mask for the system VM
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineState(node.getNodeValue());
		}
       
		// get systemvmtype from XML and set systemvm type for the system VM
		list = doc.getElementsByTagName("systemvmtype");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineType(node.getNodeValue());
		}
       
		// get templateid from XML and set template id  for the system VM
		list = doc.getElementsByTagName("systemvmtype");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineTemplateId(node.getNodeValue());
		}
       
		// get zoneid from XML and set zone id for the system VM
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineZoneId(node.getNodeValue());
		}
       
		// get zonename from XML and set zone name for the system VM
		list = doc.getElementsByTagName("zonename");
	   	if (list.getLength() > 0) {
		           Node node = list.item(0);
		           response.setSystemVirtualMachineZoneName(node.getNodeValue());
	   	}
       
       return response;
   	}
   
   	/**
   	 * Stops a system VM.
   	 * 
   	 * @param systemVirtualMachineId The ID of the system virtual machine
   	 * @param optional
   	 * @return
   	 * @throws Exception
   	 */
	public StopSystemVmResponse stopSystemVm(String systemVirtualMachineId,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("stopSystemVm", optional);
		arguments.add(new NameValuePair("id",   systemVirtualMachineId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getStopSystemVmResponse(responseDocument);
	}

	/**
	 * Converts XML document into StopSystemVmResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private StopSystemVmResponse getStopSystemVmResponse(Document doc) {
		StopSystemVmResponse response = new StopSystemVmResponse();
	
		// get Id from XML and set the ID of the system VM    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineId(node.getNodeValue());
		}
	
		// get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
		list = doc.getElementsByTagName("activeviewersessions");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineActiveViewerSessions(node.getNodeValue());
		}
	
		// get created from XML and set the date and time the system VM was created  
		list = doc.getElementsByTagName("created");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineCreated(node.getNodeValue());
		}
	
		// get dns1 from XML and set the first DNS for the system VM
		list = doc.getElementsByTagName("dns1");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineDnsFirst(node.getNodeValue());
		}
		   
		// get dns2 from XML and set the second DNS for the system VM
		list = doc.getElementsByTagName("dns2");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineDnsSecond(node.getNodeValue());
		}
		   
			// get gateway from XML and set the gateway for the system VM
			list = doc.getElementsByTagName("gateway");
			if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineGateway(node.getNodeValue());
			}
		
		// get hostid from XML and set the host id for the system VM
		list = doc.getElementsByTagName("hostid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineHostId(node.getNodeValue());
		}
		
		// get hostname from XML and set the host name for the system VM
		list = doc.getElementsByTagName("hostname");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineHostName(node.getNodeValue());
		}
		
		// get jobid from XML and set the job id for the system VM
		list = doc.getElementsByTagName("jobid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineJobId(node.getNodeValue());
		}
		   
		
		// get jobstatus from XML and set the jobstatus for the system VM
		list = doc.getElementsByTagName("jobstatus");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineJobStatus(node.getNodeValue());
		}
		
		// get linklocalip from XML and set the link local ip address for the system VM
		list = doc.getElementsByTagName("linklocalip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalIp(node.getNodeValue());
		}
		
		
		// get linklocalmacaddress from XML and set the link local mac address for the system VM
		list = doc.getElementsByTagName("linklocalmacaddress");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalMacAddress(node.getNodeValue());
		}
		
		// get linklocalnetmask from XML and set the link local mac address for the system VM
		list = doc.getElementsByTagName("linklocalnetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalNetmask(node.getNodeValue());
		}
		
		// get name from XML and set the name for the system VM
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineName(node.getNodeValue());
		}
		   
		// get networkdomain from XML and set the network domain for the system VM
		list = doc.getElementsByTagName("networkdomain");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineNetworkDomain(node.getNodeValue());
		}
		   
		// get podid from XML and set the podid for the system VM
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePodId(node.getNodeValue());
		}
		   
		
		// get privateip from XML and set the private ip for the system VM
		list = doc.getElementsByTagName("privateip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateIp(node.getNodeValue());
		}
		
		// get privatemacaddress from XML and set the private mac address for the system VM
		list = doc.getElementsByTagName("privatemacaddress");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateMacAddress(node.getNodeValue());
		}
		
		
		// get privatenetmask from XML and set the private netmask for the system VM
		list = doc.getElementsByTagName("privatenetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateNetmask(node.getNodeValue());
		}
		
		// get publicip from XML and set the public ip for the system VM
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicIp(node.getNodeValue());
		}
		
		// get publicmacaddress from XML and set the public mac address for the system VM
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicMacAddress(node.getNodeValue());
		}
		
		// get publicnetmask from XML and set the public net mask for the system VM
		list = doc.getElementsByTagName("publicnetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicNetmask(node.getNodeValue());
		}
		
		// get state from XML and set state mask for the system VM
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineState(node.getNodeValue());
		}
		
		// get systemvmtype from XML and set systemvm type for the system VM
		list = doc.getElementsByTagName("systemvmtype");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineType(node.getNodeValue());
		}
		
		// get templateid from XML and set template id  for the system VM
		list = doc.getElementsByTagName("systemvmtype");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineTemplateId(node.getNodeValue());
		}
		
		// get zoneid from XML and set zone id for the system VM
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineZoneId(node.getNodeValue());
		}
		
		// get zonename from XML and set zone name for the system VM
		list = doc.getElementsByTagName("zonename");
			if (list.getLength() > 0) {
		           Node node = list.item(0);
		           response.setSystemVirtualMachineZoneName(node.getNodeValue());
			}
		
		return response;
	}
	
	/**
	 * Destroyes a system virtual machine.
	 * 
	 * @param systemVirtualMachineId
	 * @return
	 * @throws Exception
	 */
	public DestroySystemVmResponse destroySystemVm(String systemVirtualMachineId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("stopSystemVm", null);
		arguments.add(new NameValuePair("id",   systemVirtualMachineId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getDestroySystemVmResponse(responseDocument);
	}

	/**
	 * Converts XML document into DestroySystemVmResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private DestroySystemVmResponse getDestroySystemVmResponse(Document doc) {
		DestroySystemVmResponse response = new DestroySystemVmResponse();
	
		// get Id from XML and set the ID of the system VM    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineId(node.getNodeValue());
		}
	
		// get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
		list = doc.getElementsByTagName("activeviewersessions");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineActiveViewerSessions(node.getNodeValue());
		}
	
		// get created from XML and set the date and time the system VM was created  
		list = doc.getElementsByTagName("created");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineCreated(node.getNodeValue());
		}
	
		// get dns1 from XML and set the first DNS for the system VM
		list = doc.getElementsByTagName("dns1");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineDnsFirst(node.getNodeValue());
		}
		   
		// get dns2 from XML and set the second DNS for the system VM
		list = doc.getElementsByTagName("dns2");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineDnsSecond(node.getNodeValue());
		}
		   
			// get gateway from XML and set the gateway for the system VM
			list = doc.getElementsByTagName("gateway");
			if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineGateway(node.getNodeValue());
			}
		
		// get hostid from XML and set the host id for the system VM
		list = doc.getElementsByTagName("hostid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineHostId(node.getNodeValue());
		}
		
		// get hostname from XML and set the host name for the system VM
		list = doc.getElementsByTagName("hostname");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineHostName(node.getNodeValue());
		}
		
		// get jobid from XML and set the job id for the system VM
		list = doc.getElementsByTagName("jobid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineJobId(node.getNodeValue());
		}
		   
		
		// get jobstatus from XML and set the jobstatus for the system VM
		list = doc.getElementsByTagName("jobstatus");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineJobStatus(node.getNodeValue());
		}
		
		// get linklocalip from XML and set the link local ip address for the system VM
		list = doc.getElementsByTagName("linklocalip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalIp(node.getNodeValue());
		}
		
		
		// get linklocalmacaddress from XML and set the link local mac address for the system VM
		list = doc.getElementsByTagName("linklocalmacaddress");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalMacAddress(node.getNodeValue());
		}
		
		// get linklocalnetmask from XML and set the link local mac address for the system VM
		list = doc.getElementsByTagName("linklocalnetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalNetmask(node.getNodeValue());
		}
		
		// get name from XML and set the name for the system VM
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineName(node.getNodeValue());
		}
		   
		// get networkdomain from XML and set the network domain for the system VM
		list = doc.getElementsByTagName("networkdomain");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineNetworkDomain(node.getNodeValue());
		}
		   
		// get podid from XML and set the podid for the system VM
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePodId(node.getNodeValue());
		}
		   
		
		// get privateip from XML and set the private ip for the system VM
		list = doc.getElementsByTagName("privateip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateIp(node.getNodeValue());
		}
		
		// get privatemacaddress from XML and set the private mac address for the system VM
		list = doc.getElementsByTagName("privatemacaddress");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateMacAddress(node.getNodeValue());
		}
		
		
		// get privatenetmask from XML and set the private netmask for the system VM
		list = doc.getElementsByTagName("privatenetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateNetmask(node.getNodeValue());
		}
		
		// get publicip from XML and set the public ip for the system VM
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicIp(node.getNodeValue());
		}
		
		// get publicmacaddress from XML and set the public mac address for the system VM
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicMacAddress(node.getNodeValue());
		}
		
		// get publicnetmask from XML and set the public net mask for the system VM
		list = doc.getElementsByTagName("publicnetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicNetmask(node.getNodeValue());
		}
		
		// get state from XML and set state mask for the system VM
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineState(node.getNodeValue());
		}
		
		// get systemvmtype from XML and set systemvm type for the system VM
		list = doc.getElementsByTagName("systemvmtype");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineType(node.getNodeValue());
		}
		
		// get templateid from XML and set template id  for the system VM
		list = doc.getElementsByTagName("systemvmtype");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineTemplateId(node.getNodeValue());
		}
		
		// get zoneid from XML and set zone id for the system VM
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineZoneId(node.getNodeValue());
		}
		
		// get zonename from XML and set zone name for the system VM
		list = doc.getElementsByTagName("zonename");
			if (list.getLength() > 0) {
		           Node node = list.item(0);
		           response.setSystemVirtualMachineZoneName(node.getNodeValue());
			}
		
		return response;
	} 
	
	/**
	 * List system virtual machines.
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListSystemVmsResponse listSystemVms(HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("stopSystemVm", optional);
				
		Document responseDocument = server.makeRequest(arguments);
		
		return getListSystemVmsResponse(responseDocument);
	}

	/**
	 * Converts XML document into ListSystemVmsResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListSystemVmsResponse getListSystemVmsResponse(Document doc) {
		ListSystemVmsResponse response = new ListSystemVmsResponse();
	
		// get Id from XML and set the ID of the system VM    
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineId(node.getNodeValue());
		}
	
		// get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
		list = doc.getElementsByTagName("activeviewersessions");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineActiveViewerSessions(node.getNodeValue());
		}
	
		// get created from XML and set the date and time the system VM was created  
		list = doc.getElementsByTagName("created");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineCreated(node.getNodeValue());
		}
	
		// get dns1 from XML and set the first DNS for the system VM
		list = doc.getElementsByTagName("dns1");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineDnsFirst(node.getNodeValue());
		}
		   
		// get dns2 from XML and set the second DNS for the system VM
		list = doc.getElementsByTagName("dns2");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineDnsSecond(node.getNodeValue());
		}
		   
			// get gateway from XML and set the gateway for the system VM
			list = doc.getElementsByTagName("gateway");
			if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineGateway(node.getNodeValue());
			}
		
		// get hostid from XML and set the host id for the system VM
		list = doc.getElementsByTagName("hostid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineHostId(node.getNodeValue());
		}
		
		// get hostname from XML and set the host name for the system VM
		list = doc.getElementsByTagName("hostname");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineHostName(node.getNodeValue());
		}
		
		// get jobid from XML and set the job id for the system VM
		list = doc.getElementsByTagName("jobid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineJobId(node.getNodeValue());
		}
		   
		
		// get jobstatus from XML and set the jobstatus for the system VM
		list = doc.getElementsByTagName("jobstatus");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineJobStatus(node.getNodeValue());
		}
		
		// get linklocalip from XML and set the link local ip address for the system VM
		list = doc.getElementsByTagName("linklocalip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalIp(node.getNodeValue());
		}
		
		
		// get linklocalmacaddress from XML and set the link local mac address for the system VM
		list = doc.getElementsByTagName("linklocalmacaddress");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalMacAddress(node.getNodeValue());
		}
		
		// get linklocalnetmask from XML and set the link local mac address for the system VM
		list = doc.getElementsByTagName("linklocalnetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineLinkLocalNetmask(node.getNodeValue());
		}
		
		// get name from XML and set the name for the system VM
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineName(node.getNodeValue());
		}
		   
		// get networkdomain from XML and set the network domain for the system VM
		list = doc.getElementsByTagName("networkdomain");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineNetworkDomain(node.getNodeValue());
		}
		   
		// get podid from XML and set the podid for the system VM
		list = doc.getElementsByTagName("podid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePodId(node.getNodeValue());
		}
		   
		
		// get privateip from XML and set the private ip for the system VM
		list = doc.getElementsByTagName("privateip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateIp(node.getNodeValue());
		}
		
		// get privatemacaddress from XML and set the private mac address for the system VM
		list = doc.getElementsByTagName("privatemacaddress");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateMacAddress(node.getNodeValue());
		}
		
		
		// get privatenetmask from XML and set the private netmask for the system VM
		list = doc.getElementsByTagName("privatenetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePrivateNetmask(node.getNodeValue());
		}
		
		// get publicip from XML and set the public ip for the system VM
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicIp(node.getNodeValue());
		}
		
		// get publicmacaddress from XML and set the public mac address for the system VM
		list = doc.getElementsByTagName("publicip");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicMacAddress(node.getNodeValue());
		}
		
		// get publicnetmask from XML and set the public net mask for the system VM
		list = doc.getElementsByTagName("publicnetmask");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachinePublicNetmask(node.getNodeValue());
		}
		
		// get state from XML and set state mask for the system VM
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineState(node.getNodeValue());
		}
		
		// get systemvmtype from XML and set systemvm type for the system VM
		list = doc.getElementsByTagName("systemvmtype");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineType(node.getNodeValue());
		}
		
		// get templateid from XML and set template id  for the system VM
		list = doc.getElementsByTagName("systemvmtype");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineTemplateId(node.getNodeValue());
		}
		
		// get zoneid from XML and set zone id for the system VM
		list = doc.getElementsByTagName("zoneid");
		if (list.getLength() > 0) {
		       Node node = list.item(0);
		       response.setSystemVirtualMachineZoneId(node.getNodeValue());
		}
		
		// get zonename from XML and set zone name for the system VM
		list = doc.getElementsByTagName("zonename");
			if (list.getLength() > 0) {
		           Node node = list.item(0);
		           response.setSystemVirtualMachineZoneName(node.getNodeValue());
			}
		
		return response;
	} 
	
	public MigrateSystemVmResponse migrateSystemVm(String virtualMachineHostId,
			String virtualMachineId) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("migrateSystemVm", null);
		arguments.add(new NameValuePair("hostid",   virtualMachineHostId));
		arguments.add(new NameValuePair("virtualmachineid",   virtualMachineId));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getMigrateSystemVmResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into MigrateSystemVmResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private MigrateSystemVmResponse getMigrateSystemVmResponse(Document doc) {
		MigrateSystemVmResponse response = new MigrateSystemVmResponse();
	
		// get Id from XML and set the ID of the system VM
		NodeList list = doc.getElementsByTagName("id");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineId(node.getNodeValue());
		}
	
		// get hostid from XML and set as the host ID for the system VM
		list = doc.getElementsByTagName("hostid");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineHostId(node.getNodeValue());
		}
		
		// get name from XML and set as the name for the system VM
		list = doc.getElementsByTagName("name");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineName(node.getNodeValue());
		}
		
		// get role from XML and set as the role for the system VM
		list = doc.getElementsByTagName("role");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineRole(node.getNodeValue());
		}
		
		// get state from XML and set as the state for the system VM
		list = doc.getElementsByTagName("state");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineState(node.getNodeValue());
		}
		
		// get systemvmtype from XML and set as the type for the system VM
		list = doc.getElementsByTagName("systemvmtype");
		if (list.getLength() > 0) {
	       Node node = list.item(0);
	       response.setSystemVirtualMachineType(node.getNodeValue());
		}
		
		return response;
	}	
   
   

}
