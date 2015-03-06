package com.assistanz.cloud.cloudstack.systemvm;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.commons.httpclient.NameValuePair;

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
     * Starts a system virtual machine.
     *
     * @param systemVirtualMachineId The ID of the system virtual machine
     * @return
     * @throws Exception
     */
    public StartSystemVmResponse startSystemVm(String systemVirtualMachineId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("startSystemVm", null);
        arguments.add(new NameValuePair("id", systemVirtualMachineId));

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
            response.setId(node.getTextContent());
        }

        // get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
        list = doc.getElementsByTagName("activeviewersessions");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setActiveViewerSessions(node.getTextContent());
        }

        // get created from XML and set the date and time the system VM was created  
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the first DNS for the system VM
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the second DNS for the system VM
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the system VM
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get hostid from XML and set the host id for the system VM
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the system VM
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get jobid from XML and set the job id for the system VM
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set the jobstatus for the system VM
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        // get linklocalip from XML and set the link local ip address for the system VM
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get name from XML and set the name for the system VM
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the network domain for the system VM
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the system VM
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get privateip from XML and set the private ip for the system VM
        list = doc.getElementsByTagName("privateip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateIp(node.getTextContent());
        }

        // get privatemacaddress from XML and set the private mac address for the system VM
        list = doc.getElementsByTagName("privatemacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateMacAddress(node.getTextContent());
        }

        // get privatenetmask from XML and set the private netmask for the system VM
        list = doc.getElementsByTagName("privatenetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateNetmask(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the system VM
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address for the system VM
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the system VM
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get state from XML and set state mask for the system VM
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get systemvmtype from XML and set systemvm type for the system VM
        list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVmType(node.getTextContent());
        }

        // get templateid from XML and set template id  for the system VM
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get zoneid from XML and set zone id for the system VM
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set zone name for the system VM
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        return response;
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

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("rebootSystemVm", null);
        arguments.add(new NameValuePair("id", systemVirtualMachineId));

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
            response.setId(node.getTextContent());
        }

        // get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
        list = doc.getElementsByTagName("activeviewersessions");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setActiveViewerSessions(node.getTextContent());
        }

        // get created from XML and set the date and time the system VM was created  
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the first DNS for the system VM
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the second DNS for the system VM
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the system VM
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get hostid from XML and set the host id for the system VM
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the system VM
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get jobid from XML and set the job id for the system VM
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set the jobstatus for the system VM
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        // get linklocalip from XML and set the link local ip address for the system VM
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get name from XML and set the name for the system VM
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the network domain for the system VM
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the system VM
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get privateip from XML and set the private ip for the system VM
        list = doc.getElementsByTagName("privateip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateIp(node.getTextContent());
        }

        // get privatemacaddress from XML and set the private mac address for the system VM
        list = doc.getElementsByTagName("privatemacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateMacAddress(node.getTextContent());
        }

        // get privatenetmask from XML and set the private netmask for the system VM
        list = doc.getElementsByTagName("privatenetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateNetmask(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the system VM
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address for the system VM
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the system VM
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get state from XML and set state mask for the system VM
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get systemvmtype from XML and set systemvm type for the system VM
        list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVmType(node.getTextContent());
        }

        // get templateid from XML and set template id  for the system VM
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get zoneid from XML and set zone id for the system VM
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set zone name for the system VM
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("stopSystemVm", optional);
        arguments.add(new NameValuePair("id", systemVirtualMachineId));

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
            response.setId(node.getTextContent());
        }

        // get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
        list = doc.getElementsByTagName("activeviewersessions");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setActiveViewerSessions(node.getTextContent());
        }

        // get created from XML and set the date and time the system VM was created  
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the first DNS for the system VM
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the second DNS for the system VM
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the system VM
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get hostid from XML and set the host id for the system VM
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the system VM
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get jobid from XML and set the job id for the system VM
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set the jobstatus for the system VM
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        // get linklocalip from XML and set the link local ip address for the system VM
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get name from XML and set the name for the system VM
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the network domain for the system VM
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the system VM
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get privateip from XML and set the private ip for the system VM
        list = doc.getElementsByTagName("privateip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateIp(node.getTextContent());
        }

        // get privatemacaddress from XML and set the private mac address for the system VM
        list = doc.getElementsByTagName("privatemacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateMacAddress(node.getTextContent());
        }

        // get privatenetmask from XML and set the private netmask for the system VM
        list = doc.getElementsByTagName("privatenetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateNetmask(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the system VM
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address for the system VM
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the system VM
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get state from XML and set state mask for the system VM
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get systemvmtype from XML and set systemvm type for the system VM
        list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVmType(node.getTextContent());
        }

        // get templateid from XML and set template id  for the system VM
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get zoneid from XML and set zone id for the system VM
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set zone name for the system VM
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        return response;
    }

    /**
     * Destroys a system virtual machine.
     *
     * @param systemVirtualMachineId
     * @return
     * @throws Exception
     */
    public DestroySystemVmResponse destroySystemVm(String systemVirtualMachineId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("stopSystemVm", null);
        arguments.add(new NameValuePair("id", systemVirtualMachineId));

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
            response.setId(node.getTextContent());
        }

        // get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
        list = doc.getElementsByTagName("activeviewersessions");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setActiveViewerSessions(node.getTextContent());
        }

        // get created from XML and set the date and time the system VM was created  
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the first DNS for the system VM
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the second DNS for the system VM
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the system VM
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get hostid from XML and set the host id for the system VM
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the system VM
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get jobid from XML and set the job id for the system VM
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set the jobstatus for the system VM
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        // get linklocalip from XML and set the link local ip address for the system VM
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get name from XML and set the name for the system VM
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the network domain for the system VM
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the system VM
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get privateip from XML and set the private ip for the system VM
        list = doc.getElementsByTagName("privateip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateIp(node.getTextContent());
        }

        // get privatemacaddress from XML and set the private mac address for the system VM
        list = doc.getElementsByTagName("privatemacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateMacAddress(node.getTextContent());
        }

        // get privatenetmask from XML and set the private netmask for the system VM
        list = doc.getElementsByTagName("privatenetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateNetmask(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the system VM
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address for the system VM
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the system VM
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get state from XML and set state mask for the system VM
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get systemvmtype from XML and set systemvm type for the system VM
        list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVmType(node.getTextContent());
        }

        // get templateid from XML and set template id  for the system VM
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get zoneid from XML and set zone id for the system VM
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set zone name for the system VM
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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
    public ListSystemVmsResponse listSystemVms(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listSystemVms", optional);

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

        NodeList list = doc.getElementsByTagName("systemvm");
        List<SystemVmResponse> systemVms = new LinkedList<SystemVmResponse>();
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node systemVmNode = list.item(index);
                SystemVmResponse systemVm = new SystemVmResponse();
                NodeList systemVmProperties = systemVmNode.getChildNodes();
                for (int childIndex = 0; childIndex < systemVmProperties.getLength(); childIndex++) {
                    Node property = systemVmProperties.item(childIndex);
                    if (property.getNodeName().equals("id")) {
                        systemVm.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("activeviewersessions")) {
                        systemVm.setActiveViewerSessions(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        systemVm.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("dns1")) {
                        systemVm.setDns1(property.getTextContent());
                    } else if (property.getNodeName().equals("dns2")) {
                        systemVm.setDns2(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        systemVm.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("hostid")) {
                        systemVm.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostname")) {
                        systemVm.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("jobid")) {
                        systemVm.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        systemVm.setJobStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalip")) {
                        systemVm.setLinkLocalIp(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalmacaddress")) {
                        systemVm.setLinkLocalMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("linklocalnetmask")) {
                        systemVm.setLinkLocalNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        systemVm.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkdomain")) {
                        systemVm.setNetworkDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        systemVm.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("privateip")) {
                        systemVm.setPrivateIp(property.getTextContent());
                    } else if (property.getNodeName().equals("privatemacaddress")) {
                        systemVm.setPrivateMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("privatenetmask")) {
                        systemVm.setPrivateNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("publicip")) {
                        systemVm.setPublicIp(property.getTextContent());
                    } else if (property.getNodeName().equals("publicmacaddress")) {
                        systemVm.setPublicMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("publicnetmask")) {
                        systemVm.setPublicNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        systemVm.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("systemvmtype")) {
                        systemVm.setSystemVmType(property.getTextContent());
                    } else if (property.getNodeName().equals("templateid")) {
                        systemVm.setTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        systemVm.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        systemVm.setZoneName(property.getTextContent());
                    }
                }
                systemVms.add(systemVm);
            }
        }
        response.setSystemVms(systemVms);
        return response;

    }

    /**
     * Attempts Migration of a system virtual machine to the host specified.
     *
     * @param virtualMachineHostId
     * @param virtualMachineId
     * @return
     * @throws Exception
     */
    public MigrateSystemVmResponse migrateSystemVm(String virtualMachineHostId,
            String virtualMachineId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("migrateSystemVm", null);
        arguments.add(new NameValuePair("hostid", virtualMachineHostId));
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

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
            response.setId(node.getTextContent());
        }

        // get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
        list = doc.getElementsByTagName("activeviewersessions");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setActiveViewerSessions(node.getTextContent());
        }

        // get created from XML and set the date and time the system VM was created  
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the first DNS for the system VM
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the second DNS for the system VM
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the system VM
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get hostid from XML and set the host id for the system VM
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the system VM
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get jobid from XML and set the job id for the system VM
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set the jobstatus for the system VM
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        // get linklocalip from XML and set the link local ip address for the system VM
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get name from XML and set the name for the system VM
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the network domain for the system VM
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the system VM
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get privateip from XML and set the private ip for the system VM
        list = doc.getElementsByTagName("privateip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateIp(node.getTextContent());
        }

        // get privatemacaddress from XML and set the private mac address for the system VM
        list = doc.getElementsByTagName("privatemacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateMacAddress(node.getTextContent());
        }

        // get privatenetmask from XML and set the private netmask for the system VM
        list = doc.getElementsByTagName("privatenetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateNetmask(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the system VM
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address for the system VM
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the system VM
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get state from XML and set state mask for the system VM
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get systemvmtype from XML and set systemvm type for the system VM
        list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVmType(node.getTextContent());
        }

        // get templateid from XML and set template id  for the system VM
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get zoneid from XML and set zone id for the system VM
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set zone name for the system VM
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        return response;
    }

    /**
     * Changes the service offering for a system vm (console proxy or secondary storage).
     *
     * @param systemVirtualMachineId
     * @param serviceOfferingId
     * @return
     * @throws Exception
     */
    public ChangeServiceForSystemVmResponse changeServiceForSystemVm(String systemVirtualMachineId,
            String serviceOfferingId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("changeServiceForSystemVm", null);
        arguments.add(new NameValuePair("id", systemVirtualMachineId));
        arguments.add(new NameValuePair("serviceofferingid", serviceOfferingId));

        Document responseDocument = server.makeRequest(arguments);

        return getChangeServiceForSystemVmResponse(responseDocument);
    }

    /**
     * Converts XML document into changeServiceForSystemVmResponse object
     *
     * @param doc
     * @return
     */
    private ChangeServiceForSystemVmResponse getChangeServiceForSystemVmResponse(Document doc) {
        ChangeServiceForSystemVmResponse response = new ChangeServiceForSystemVmResponse();

        // get Id from XML and set the ID of the system VM    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
        list = doc.getElementsByTagName("activeviewersessions");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setActiveViewerSessions(node.getTextContent());
        }

        // get created from XML and set the date and time the system VM was created  
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the first DNS for the system VM
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the second DNS for the system VM
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the system VM
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get hostid from XML and set the host id for the system VM
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the system VM
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get jobid from XML and set the job id for the system VM
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set the jobstatus for the system VM
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        // get linklocalip from XML and set the link local ip address for the system VM
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get name from XML and set the name for the system VM
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the network domain for the system VM
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the system VM
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get privateip from XML and set the private ip for the system VM
        list = doc.getElementsByTagName("privateip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateIp(node.getTextContent());
        }

        // get privatemacaddress from XML and set the private mac address for the system VM
        list = doc.getElementsByTagName("privatemacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateMacAddress(node.getTextContent());
        }

        // get privatenetmask from XML and set the private netmask for the system VM
        list = doc.getElementsByTagName("privatenetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateNetmask(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the system VM
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address for the system VM
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the system VM
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get state from XML and set state mask for the system VM
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get systemvmtype from XML and set systemvm type for the system VM
        list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVmType(node.getTextContent());
        }

        // get templateid from XML and set template id  for the system VM
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get zoneid from XML and set zone id for the system VM
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set zone name for the system VM
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        return response;
    }

    /**
     * Scale the service offering for a system VM (console proxy or secondary storage).
     *
     * @param systemVirtualMachineId
     * @param serviceOfferingId
     * @return
     * @throws Exception
     */
    public ScaleSystemVmResponse scaleSystemVm(String systemVirtualMachineId,
            String serviceOfferingId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("scaleSystemVm", null);
        arguments.add(new NameValuePair("id", systemVirtualMachineId));
        arguments.add(new NameValuePair("serviceofferingid", serviceOfferingId));

        Document responseDocument = server.makeRequest(arguments);

        return getScaleSystemVmResponse(responseDocument);
    }

    /**
     * Converts XML document into scaleSystemVmResponse object
     *
     * @param doc
     * @return
     */
    private ScaleSystemVmResponse getScaleSystemVmResponse(Document doc) {
        ScaleSystemVmResponse response = new ScaleSystemVmResponse();

        // get Id from XML and set the ID of the system VM    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get activeviewersessions from XML and set as the number of active console sessions for the console proxy system vm
        list = doc.getElementsByTagName("activeviewersessions");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setActiveViewerSessions(node.getTextContent());
        }

        // get created from XML and set the date and time the system VM was created  
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get dns1 from XML and set the first DNS for the system VM
        list = doc.getElementsByTagName("dns1");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns1(node.getTextContent());
        }

        // get dns2 from XML and set the second DNS for the system VM
        list = doc.getElementsByTagName("dns2");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDns2(node.getTextContent());
        }

        // get gateway from XML and set the gateway for the system VM
        list = doc.getElementsByTagName("gateway");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGateway(node.getTextContent());
        }

        // get hostid from XML and set the host id for the system VM
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the host name for the system VM
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get jobid from XML and set the job id for the system VM
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set the jobstatus for the system VM
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        // get linklocalip from XML and set the link local ip address for the system VM
        list = doc.getElementsByTagName("linklocalip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalIp(node.getTextContent());
        }

        // get linklocalmacaddress from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalMacAddress(node.getTextContent());
        }

        // get linklocalnetmask from XML and set the link local mac address for the system VM
        list = doc.getElementsByTagName("linklocalnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLinkLocalNetmask(node.getTextContent());
        }

        // get name from XML and set the name for the system VM
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the network domain for the system VM
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get podid from XML and set the podid for the system VM
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get privateip from XML and set the private ip for the system VM
        list = doc.getElementsByTagName("privateip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateIp(node.getTextContent());
        }

        // get privatemacaddress from XML and set the private mac address for the system VM
        list = doc.getElementsByTagName("privatemacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateMacAddress(node.getTextContent());
        }

        // get privatenetmask from XML and set the private netmask for the system VM
        list = doc.getElementsByTagName("privatenetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateNetmask(node.getTextContent());
        }

        // get publicip from XML and set the public ip for the system VM
        list = doc.getElementsByTagName("publicip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicIp(node.getTextContent());
        }

        // get publicmacaddress from XML and set the public mac address for the system VM
        list = doc.getElementsByTagName("publicmacaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicMacAddress(node.getTextContent());
        }

        // get publicnetmask from XML and set the public net mask for the system VM
        list = doc.getElementsByTagName("publicnetmask");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicNetmask(node.getTextContent());
        }

        // get state from XML and set state mask for the system VM
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get systemvmtype from XML and set systemvm type for the system VM
        list = doc.getElementsByTagName("systemvmtype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSystemVmType(node.getTextContent());
        }

        // get templateid from XML and set template id  for the system VM
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get zoneid from XML and set zone id for the system VM
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set zone name for the system VM
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        return response;
    }

}
