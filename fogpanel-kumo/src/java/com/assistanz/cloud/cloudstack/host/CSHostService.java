package com.assistanz.cloud.cloudstack.host;

import java.util.HashMap;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;

/**
 * 
 * @author Gowtham
 *
 */
public class CSHostService {
	
	private CloudStackServer server;
	
	public CSHostService(CloudStackServer server) {
        this.server = server;
	}
	
	/**
	 * Adds a new host.
	 * 
	 * @param HosthypervisorType hypervisor type of the host
	 * @param HostPassword the password for the host
	 * @param HostUrl the host URL
	 * @param HostUserName the username for the host
	 * @param HostZoneId the Zone ID for the host
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public AddHostResponse addHost(String HosthypervisorType, 
			String HostPassword, String HostUrl, String HostUserName, 
			String HostZoneId, HashMap<String,String> optional) 
					throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("addHost", optional);
        arguments.add(new NameValuePair("hypervisor", HosthypervisorType));
        arguments.add(new NameValuePair("password", HostPassword));
        arguments.add(new NameValuePair("url", HostUrl));
        arguments.add(new NameValuePair("username", HostUserName));
        arguments.add(new NameValuePair("zoneid", HostZoneId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getAddHostResponse(responseDocument);
    }
	
    /**
     * Converts XML document into AddHostResponse object
     * 
     * @param doc
     * @return 
     */
    private AddHostResponse getAddHostResponse(Document doc) {
    	AddHostResponse response = new AddHostResponse();
        
        // get id from XML and set as the ID of the host
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getNodeValue());
        }
        
        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostAverageLoad(node.getNodeValue());
        }
        
        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCapabilities(node.getNodeValue());
        }
        
        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterId(node.getNodeValue());
        }
        
        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterName(node.getNodeValue());
        }
        
        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterType(node.getNodeValue());
        }
        
        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuAllocated(node.getNodeValue());
        }
        
        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuNumber(node.getNodeValue());
        }
        
        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuSpeed(node.getNodeValue());
        }
        
        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuUsed(node.getNodeValue());
        }
        
        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuWithOverProvisioning(node.getNodeValue());
        }
        
        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCreatedDate(node.getNodeValue());
        }
        
        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDisconnected(node.getNodeValue());
        }
        
        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeAllocated(node.getNodeValue());
        }
        
        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeTotal(node.getNodeValue());
        }
        
        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostEvents(node.getNodeValue());
        }
        
        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getNodeValue());
        }
        
        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostTags(node.getNodeValue());
        }
        
        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostHypervisor(node.getNodeValue());
        }
        
        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getNodeValue());
        }
        
        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostIpAddress(node.getNodeValue());
        }
        
        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getNodeValue());
        }
        
        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostManagementServerId(node.getNodeValue());
        }
        
        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryAllocated(node.getNodeValue());
        }
        
        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryTotal(node.getNodeValue());
        }
        
        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryUsed(node.getNodeValue());
        }
        
        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getNodeValue());
        }
        
        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsRead(node.getNodeValue());
        }
        
        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsWrite(node.getNodeValue());
        }
        
        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryId(node.getNodeValue());
        }
        
        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryName(node.getNodeValue());
        }
        
        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodId(node.getNodeValue());
        }
        
        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodName(node.getNodeValue());
        }
        
        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostRemovedDate(node.getNodeValue());
        }
        
        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostResourceState(node.getNodeValue());
        }
        
        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostSate(node.getNodeValue());
        }
        
        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getNodeValue());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostType(node.getNodeValue());
        }
        
        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostVersion(node.getNodeValue());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneId(node.getNodeValue());
        }
        
        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneName(node.getNodeValue());
        }
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getNodeValue());
        }
        
        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getNodeValue());
        }
        
       return response;
    }
    
    /**
     * Reconnects a host.
     * 
     * @param HostId the host id
     * @return
     * @throws Exception
     */
    public ReconnectHostResponse reconnectHost(String HostId) 
					throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("reconnectHost", null);
        arguments.add(new NameValuePair("id", HostId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getReconnectHostResponse(responseDocument);
    }
    
    /**
     * Converts XML document into ReconnectHostResponse object
     * 
     * @param doc
     * @return 
     */
    private ReconnectHostResponse getReconnectHostResponse(Document doc) {
    	ReconnectHostResponse response = new ReconnectHostResponse();
        
        // get id from XML and set as the ID of the host
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getNodeValue());
        }
        
        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostAverageLoad(node.getNodeValue());
        }
        
        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCapabilities(node.getNodeValue());
        }
        
        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterId(node.getNodeValue());
        }
        
        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterName(node.getNodeValue());
        }
        
        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterType(node.getNodeValue());
        }
        
        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuAllocated(node.getNodeValue());
        }
        
        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuNumber(node.getNodeValue());
        }
        
        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuSpeed(node.getNodeValue());
        }
        
        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuUsed(node.getNodeValue());
        }
        
        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuWithOverProvisioning(node.getNodeValue());
        }
        
        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCreatedDate(node.getNodeValue());
        }
        
        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDisconnected(node.getNodeValue());
        }
        
        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeAllocated(node.getNodeValue());
        }
        
        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeTotal(node.getNodeValue());
        }
        
        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostEvents(node.getNodeValue());
        }
        
        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getNodeValue());
        }
        
        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostTags(node.getNodeValue());
        }
        
        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostHypervisor(node.getNodeValue());
        }
        
        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getNodeValue());
        }
        
        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostIpAddress(node.getNodeValue());
        }
        
        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getNodeValue());
        }
        
        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostManagementServerId(node.getNodeValue());
        }
        
        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryAllocated(node.getNodeValue());
        }
        
        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryTotal(node.getNodeValue());
        }
        
        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryUsed(node.getNodeValue());
        }
        
        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getNodeValue());
        }
        
        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsRead(node.getNodeValue());
        }
        
        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsWrite(node.getNodeValue());
        }
        
        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryId(node.getNodeValue());
        }
        
        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryName(node.getNodeValue());
        }
        
        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodId(node.getNodeValue());
        }
        
        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodName(node.getNodeValue());
        }
        
        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostRemovedDate(node.getNodeValue());
        }
        
        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostResourceState(node.getNodeValue());
        }
        
        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostSate(node.getNodeValue());
        }
        
        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getNodeValue());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostType(node.getNodeValue());
        }
        
        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostVersion(node.getNodeValue());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneId(node.getNodeValue());
        }
        
        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneName(node.getNodeValue());
        }
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getNodeValue());
        }
        
        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getNodeValue());
        }
        
       return response;
    }
    
    /**
     * Updates a host.
     * 
     * @param HostId the ID of the host to update
     * @param optional
     * @return
     * @throws Exception
     */
	public UpdateHostResponse updateHost(String HostId, 
			HashMap<String,String> optional) 
					throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateHost", optional);
        arguments.add(new NameValuePair("id", HostId));
                
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateHostResponse(responseDocument);
    }
	
    /**
     * Converts XML document into UpdateHostResponse object
     * 
     * @param doc
     * @return 
     */
    private UpdateHostResponse getUpdateHostResponse(Document doc) {
    	UpdateHostResponse response = new UpdateHostResponse();
        
        // get id from XML and set as the ID of the host
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getNodeValue());
        }
        
        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostAverageLoad(node.getNodeValue());
        }
        
        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCapabilities(node.getNodeValue());
        }
        
        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterId(node.getNodeValue());
        }
        
        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterName(node.getNodeValue());
        }
        
        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterType(node.getNodeValue());
        }
        
        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuAllocated(node.getNodeValue());
        }
        
        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuNumber(node.getNodeValue());
        }
        
        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuSpeed(node.getNodeValue());
        }
        
        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuUsed(node.getNodeValue());
        }
        
        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuWithOverProvisioning(node.getNodeValue());
        }
        
        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCreatedDate(node.getNodeValue());
        }
        
        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDisconnected(node.getNodeValue());
        }
        
        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeAllocated(node.getNodeValue());
        }
        
        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeTotal(node.getNodeValue());
        }
        
        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostEvents(node.getNodeValue());
        }
        
        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getNodeValue());
        }
        
        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostTags(node.getNodeValue());
        }
        
        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostHypervisor(node.getNodeValue());
        }
        
        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getNodeValue());
        }
        
        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostIpAddress(node.getNodeValue());
        }
        
        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getNodeValue());
        }
        
        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostManagementServerId(node.getNodeValue());
        }
        
        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryAllocated(node.getNodeValue());
        }
        
        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryTotal(node.getNodeValue());
        }
        
        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryUsed(node.getNodeValue());
        }
        
        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getNodeValue());
        }
        
        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsRead(node.getNodeValue());
        }
        
        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsWrite(node.getNodeValue());
        }
        
        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryId(node.getNodeValue());
        }
        
        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryName(node.getNodeValue());
        }
        
        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodId(node.getNodeValue());
        }
        
        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodName(node.getNodeValue());
        }
        
        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostRemovedDate(node.getNodeValue());
        }
        
        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostResourceState(node.getNodeValue());
        }
        
        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostSate(node.getNodeValue());
        }
        
        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getNodeValue());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostType(node.getNodeValue());
        }
        
        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostVersion(node.getNodeValue());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneId(node.getNodeValue());
        }
        
        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneName(node.getNodeValue());
        }
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getNodeValue());
        }
        
        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getNodeValue());
        }
        
       return response;
    }
    
    /**
     * Deletes a host.
     * 
     * @param hostId the host id
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteHostResponse deleteHost(String hostId, 
    		HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteHost", optional);
        arguments.add(new NameValuePair("id", hostId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getDeleteHostResponse(responseDocument);
        
    }
    
    /**
      * Converts XML document into DeleteHostResponse object
      * 
      * @param doc
      * @return 
      */
    private DeleteHostResponse getDeleteHostResponse(Document doc) {
    	DeleteHostResponse response = new DeleteHostResponse();
        
        // get displaytext from XML and set Any text associated with the success or 
        // failure on Deleting a host
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getNodeValue());
        }
        
        // get success from XML and set Return true if Delete host operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getNodeValue());
        }
        
         return response;
    }
    
    /**
     * Prepares a host for maintenance.
     * 
     * @param HostId the host ID
     * @return
     * @throws Exception
     */
	public PrepareHostForMaintenanceResponse prepareHostForMaintenance(String HostId) 
					throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("prepareHostForMaintenance", null);
        arguments.add(new NameValuePair("id", HostId));
                
        Document responseDocument = server.makeRequest(arguments);

        return getPrepareHostForMaintenanceResponse(responseDocument);
    }
	
    /**
     * Converts XML document into PrepareHostForMaintenanceResponse object
     * 
     * @param doc
     * @return 
     */
    private PrepareHostForMaintenanceResponse getPrepareHostForMaintenanceResponse(Document doc) {
    	PrepareHostForMaintenanceResponse response = new PrepareHostForMaintenanceResponse();
        
        // get id from XML and set as the ID of the host
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getNodeValue());
        }
        
        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostAverageLoad(node.getNodeValue());
        }
        
        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCapabilities(node.getNodeValue());
        }
        
        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterId(node.getNodeValue());
        }
        
        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterName(node.getNodeValue());
        }
        
        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterType(node.getNodeValue());
        }
        
        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuAllocated(node.getNodeValue());
        }
        
        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuNumber(node.getNodeValue());
        }
        
        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuSpeed(node.getNodeValue());
        }
        
        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuUsed(node.getNodeValue());
        }
        
        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuWithOverProvisioning(node.getNodeValue());
        }
        
        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCreatedDate(node.getNodeValue());
        }
        
        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDisconnected(node.getNodeValue());
        }
        
        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeAllocated(node.getNodeValue());
        }
        
        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeTotal(node.getNodeValue());
        }
        
        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostEvents(node.getNodeValue());
        }
        
        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getNodeValue());
        }
        
        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostTags(node.getNodeValue());
        }
        
        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostHypervisor(node.getNodeValue());
        }
        
        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getNodeValue());
        }
        
        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostIpAddress(node.getNodeValue());
        }
        
        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getNodeValue());
        }
        
        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostManagementServerId(node.getNodeValue());
        }
        
        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryAllocated(node.getNodeValue());
        }
        
        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryTotal(node.getNodeValue());
        }
        
        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryUsed(node.getNodeValue());
        }
        
        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getNodeValue());
        }
        
        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsRead(node.getNodeValue());
        }
        
        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsWrite(node.getNodeValue());
        }
        
        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryId(node.getNodeValue());
        }
        
        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryName(node.getNodeValue());
        }
        
        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodId(node.getNodeValue());
        }
        
        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodName(node.getNodeValue());
        }
        
        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostRemovedDate(node.getNodeValue());
        }
        
        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostResourceState(node.getNodeValue());
        }
        
        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostSate(node.getNodeValue());
        }
        
        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getNodeValue());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostType(node.getNodeValue());
        }
        
        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostVersion(node.getNodeValue());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneId(node.getNodeValue());
        }
        
        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneName(node.getNodeValue());
        }
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getNodeValue());
        }
        
        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getNodeValue());
        }
        
       return response;
    }
    
    /**
     * Cancels host maintenance.
     * 
     * @param HostId the host ID
     * @return
     * @throws Exception
     */
	public CancelHostMaintenanceResponse cancelHostMaintenance(String HostId) 
					throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("cancelHostMaintenance", null);
        arguments.add(new NameValuePair("id", HostId));
                
        Document responseDocument = server.makeRequest(arguments);

        return getCancelHostMaintenanceResponse(responseDocument);
    }
	
    /**
     * Converts XML document into CancelHostMaintenanceResponse object
     * 
     * @param doc
     * @return 
     */
    private CancelHostMaintenanceResponse getCancelHostMaintenanceResponse(Document doc) {
    	CancelHostMaintenanceResponse response = new CancelHostMaintenanceResponse();
        
        // get id from XML and set as the ID of the host
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getNodeValue());
        }
        
        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostAverageLoad(node.getNodeValue());
        }
        
        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCapabilities(node.getNodeValue());
        }
        
        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterId(node.getNodeValue());
        }
        
        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterName(node.getNodeValue());
        }
        
        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterType(node.getNodeValue());
        }
        
        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuAllocated(node.getNodeValue());
        }
        
        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuNumber(node.getNodeValue());
        }
        
        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuSpeed(node.getNodeValue());
        }
        
        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuUsed(node.getNodeValue());
        }
        
        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuWithOverProvisioning(node.getNodeValue());
        }
        
        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCreatedDate(node.getNodeValue());
        }
        
        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDisconnected(node.getNodeValue());
        }
        
        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeAllocated(node.getNodeValue());
        }
        
        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeTotal(node.getNodeValue());
        }
        
        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostEvents(node.getNodeValue());
        }
        
        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getNodeValue());
        }
        
        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostTags(node.getNodeValue());
        }
        
        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostHypervisor(node.getNodeValue());
        }
        
        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getNodeValue());
        }
        
        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostIpAddress(node.getNodeValue());
        }
        
        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getNodeValue());
        }
        
        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostManagementServerId(node.getNodeValue());
        }
        
        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryAllocated(node.getNodeValue());
        }
        
        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryTotal(node.getNodeValue());
        }
        
        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryUsed(node.getNodeValue());
        }
        
        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getNodeValue());
        }
        
        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsRead(node.getNodeValue());
        }
        
        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsWrite(node.getNodeValue());
        }
        
        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryId(node.getNodeValue());
        }
        
        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryName(node.getNodeValue());
        }
        
        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodId(node.getNodeValue());
        }
        
        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodName(node.getNodeValue());
        }
        
        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostRemovedDate(node.getNodeValue());
        }
        
        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostResourceState(node.getNodeValue());
        }
        
        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostSate(node.getNodeValue());
        }
        
        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getNodeValue());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostType(node.getNodeValue());
        }
        
        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostVersion(node.getNodeValue());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneId(node.getNodeValue());
        }
        
        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneName(node.getNodeValue());
        }
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getNodeValue());
        }
        
        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getNodeValue());
        }
        
       return response;
    }
    
    
    /**
     * Find hosts suitable for migrating a virtual machine.
     * 
     * @param virtualmachineid find hosts to which this VM can be migrated and flag the hosts with enough CPU/RAM to host the VM
     * @return
     * @throws Exception 
     */
    public FindHostsForMigrationResponse findHostsForMigration(String virtualmachineid) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("findHostsForMigration", null);
        arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));
        
        Document responseDocument = server.makeRequest(arguments);

        return getFindHostsForMigrationResponse(responseDocument);
    }
    
    private FindHostsForMigrationResponse getFindHostsForMigrationResponse(Document doc) {
    	FindHostsForMigrationResponse response = new FindHostsForMigrationResponse();
        
        NodeList list = doc.getElementsByTagName("host");
        List<HostResponse> hosts = new LinkedList<HostResponse>();  
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node hostNode = list.item(index);
                HostResponse host = new HostResponse();
                NodeList hostProperties = hostNode.getChildNodes();
                for (int childIndex = 0; childIndex < hostProperties.getLength(); childIndex++) {
                    Node property = hostProperties.item(childIndex);
                    if (property.getNodeName().equals("id")) {
                        host.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("averageload")) {
                    	host.setHostAverageLoad(property.getTextContent());
                    } else if (property.getNodeName().equals("capabilities")) {
                    	host.setHostCapabilities(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                    	host.setHostClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                    	host.setHostClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("clustertype")) {
                    	host.setHostClusterType(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuallocated")) {
                    	host.setHostCpuAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("cpunumber")) {
                    	host.setHostCpuNumber(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuspeed")) {
                    	host.setHostCpuSpeed(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuused")) {
                    	host.setHostCpuUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuwithoverprovisioning")) {
                    	host.setHostCpuWithOverProvisioning(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                    	host.setHostCreatedDate(property.getTextContent());
                    } else if (property.getNodeName().equals("disconnected")) {
                    	host.setHostDisconnected(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizeallocated")) {
                    	host.setHostDiskSizeAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizetotal")) {
                    	host.setHostDiskSizeTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("events")) {
                    	host.setHostEvents(property.getTextContent());
                    } else if (property.getNodeName().equals("hasenoughcapacity")) {
                    	host.setHasEnoughCapacity(property.getTextContent());
                    } else if (property.getNodeName().equals("hosttags")) {
                    	host.setHostTags(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                    	host.setHostHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisorversion")) {
                    	host.setHypervisorVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                    	host.setHostIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("islocalstorageactive")) {
                    	host.setIsLocalStorageActive(property.getTextContent());
                    } else if (property.getNodeName().equals("managementserverid")) {
                    	host.setHostManagementServerId(property.getTextContent());
                    } else if (property.getNodeName().equals("memoryallocated")) {
                    	host.setHostMemoryAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("memorytotal")) {
                    	host.setHostMemoryTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("memoryused")) {
                    	host.setHostMemoryUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	host.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbsread")) {
                    	host.setHostNetworKbsRead(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbswrite")) {
                    	host.setHostNetworKbsWrite(property.getTextContent());
                    } else if (property.getNodeName().equals("oscategoryid")) {
                    	host.setHostOsCategoryId(property.getTextContent());
                    } else if (property.getNodeName().equals("oscategoryname")) {
                    	host.setHostOsCategoryName(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                    	host.setHostPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                    	host.setHostPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("removed")) {
                    	host.setHostRemovedDate(property.getTextContent());
                    } else if (property.getNodeName().equals("resourcestate")) {
                    	host.setHostResourceState(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                    	host.setHostSate(property.getTextContent());
                    } else if (property.getNodeName().equals("suitableformigration")) {
                    	host.setSuitableForMigration(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	host.setHostType(property.getTextContent());
                    } else if (property.getNodeName().equals("version")) {
                    	host.setHostVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	host.setHostZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                    	host.setHostZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("jobid")) {
                    	host.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                    	host.setJobStatus(property.getTextContent());
                    } 
                 }
                 hosts.add(host);
            }
        }
        response.setHosts(hosts);
        return response; 
    }
    
    /**
     * Lists hosts.
     * 
     * @param optional
     * @return
     * @throws Exception
     */
	public ListHostsResponse listHosts(HashMap<String,String> optional) 
            throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listHosts", optional);
        
        Document responseDocument = server.makeRequest(arguments);

        return getListHostsResponse(responseDocument);
    }
	
    /**
     * Converts XML document into ListHostsResponse object
     * 
     * @param doc
     * @return 
     */
    private ListHostsResponse getListHostsResponse(Document doc) {
    	ListHostsResponse response = new ListHostsResponse();
        
        NodeList list = doc.getElementsByTagName("host");
        List<HostResponse> hosts = new LinkedList<HostResponse>();  
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node hostNode = list.item(index);
                HostResponse host = new HostResponse();
                NodeList hostProperties = hostNode.getChildNodes();
                for (int childIndex = 0; childIndex < hostProperties.getLength(); childIndex++) {
                    Node property = hostProperties.item(childIndex);
                    if (property.getNodeName().equals("id")) {
                        host.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("averageload")) {
                    	host.setHostAverageLoad(property.getTextContent());
                    } else if (property.getNodeName().equals("capabilities")) {
                    	host.setHostCapabilities(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                    	host.setHostClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                    	host.setHostClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("clustertype")) {
                    	host.setHostClusterType(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuallocated")) {
                    	host.setHostCpuAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("cpunumber")) {
                    	host.setHostCpuNumber(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuspeed")) {
                    	host.setHostCpuSpeed(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuused")) {
                    	host.setHostCpuUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuwithoverprovisioning")) {
                    	host.setHostCpuWithOverProvisioning(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                    	host.setHostCreatedDate(property.getTextContent());
                    } else if (property.getNodeName().equals("disconnected")) {
                    	host.setHostDisconnected(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizeallocated")) {
                    	host.setHostDiskSizeAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizetotal")) {
                    	host.setHostDiskSizeTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("events")) {
                    	host.setHostEvents(property.getTextContent());
                    } else if (property.getNodeName().equals("hasenoughcapacity")) {
                    	host.setHasEnoughCapacity(property.getTextContent());
                    } else if (property.getNodeName().equals("hosttags")) {
                    	host.setHostTags(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                    	host.setHostHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisorversion")) {
                    	host.setHypervisorVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                    	host.setHostIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("islocalstorageactive")) {
                    	host.setIsLocalStorageActive(property.getTextContent());
                    } else if (property.getNodeName().equals("managementserverid")) {
                    	host.setHostManagementServerId(property.getTextContent());
                    } else if (property.getNodeName().equals("memoryallocated")) {
                    	host.setHostMemoryAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("memorytotal")) {
                    	host.setHostMemoryTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("memoryused")) {
                    	host.setHostMemoryUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	host.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbsread")) {
                    	host.setHostNetworKbsRead(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbswrite")) {
                    	host.setHostNetworKbsWrite(property.getTextContent());
                    } else if (property.getNodeName().equals("oscategoryid")) {
                    	host.setHostOsCategoryId(property.getTextContent());
                    } else if (property.getNodeName().equals("oscategoryname")) {
                    	host.setHostOsCategoryName(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                    	host.setHostPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                    	host.setHostPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("removed")) {
                    	host.setHostRemovedDate(property.getTextContent());
                    } else if (property.getNodeName().equals("resourcestate")) {
                    	host.setHostResourceState(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                    	host.setHostSate(property.getTextContent());
                    } else if (property.getNodeName().equals("suitableformigration")) {
                    	host.setSuitableForMigration(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	host.setHostType(property.getTextContent());
                    } else if (property.getNodeName().equals("version")) {
                    	host.setHostVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	host.setHostZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                    	host.setHostZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("jobid")) {
                    	host.setJobId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                    	host.setJobStatus(property.getTextContent());
                    } 
                 }
                 hosts.add(host);
            }
        }
        response.setHosts(hosts);
        return response;     
    }
    
    /**
     * Adds secondary storage.
     * 
     * @param secondaryStorageUrl the URL for the secondary storage
     * @param optional
     * @return
     * @throws Exception
     */
	public AddSecondaryStorageResponse addSecondaryStorage(String secondaryStorageUrl, 
			HashMap<String,String> optional)throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("addSecondaryStorage", optional);
        arguments.add(new NameValuePair("url", secondaryStorageUrl));
                
        Document responseDocument = server.makeRequest(arguments);

        return getAddSecondaryStorageResponse(responseDocument);
    }
	
    /**
     * Converts XML document into AddSecondaryStorageResponse object
     * 
     * @param doc
     * @return 
     */
    private AddSecondaryStorageResponse getAddSecondaryStorageResponse(Document doc) {
    	AddSecondaryStorageResponse response = new AddSecondaryStorageResponse();
        
        // get id from XML and set as the ID of the host
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getNodeValue());
        }
        
        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostAverageLoad(node.getNodeValue());
        }
        
        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCapabilities(node.getNodeValue());
        }
        
        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterId(node.getNodeValue());
        }
        
        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterName(node.getNodeValue());
        }
        
        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostClusterType(node.getNodeValue());
        }
        
        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuAllocated(node.getNodeValue());
        }
        
        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuNumber(node.getNodeValue());
        }
        
        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuSpeed(node.getNodeValue());
        }
        
        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuUsed(node.getNodeValue());
        }
        
        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCpuWithOverProvisioning(node.getNodeValue());
        }
        
        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostCreatedDate(node.getNodeValue());
        }
        
        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDisconnected(node.getNodeValue());
        }
        
        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeAllocated(node.getNodeValue());
        }
        
        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostDiskSizeTotal(node.getNodeValue());
        }
        
        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostEvents(node.getNodeValue());
        }
        
        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getNodeValue());
        }
        
        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostTags(node.getNodeValue());
        }
        
        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostHypervisor(node.getNodeValue());
        }
        
        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getNodeValue());
        }
        
        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostIpAddress(node.getNodeValue());
        }
        
        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getNodeValue());
        }
        
        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostManagementServerId(node.getNodeValue());
        }
        
        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryAllocated(node.getNodeValue());
        }
        
        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryTotal(node.getNodeValue());
        }
        
        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostMemoryUsed(node.getNodeValue());
        }
        
        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getNodeValue());
        }
        
        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsRead(node.getNodeValue());
        }
        
        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostNetworKbbsWrite(node.getNodeValue());
        }
        
        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryId(node.getNodeValue());
        }
        
        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostOsCategoryName(node.getNodeValue());
        }
        
        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodId(node.getNodeValue());
        }
        
        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostPodName(node.getNodeValue());
        }
        
        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostRemovedDate(node.getNodeValue());
        }
        
        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostResourceState(node.getNodeValue());
        }
        
        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostSate(node.getNodeValue());
        }
        
        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getNodeValue());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostType(node.getNodeValue());
        }
        
        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostVersion(node.getNodeValue());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneId(node.getNodeValue());
        }
        
        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostZoneName(node.getNodeValue());
        }
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getNodeValue());
        }
        
        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getNodeValue());
        }
        
       return response;
    }
    
    /**
     * Update password of a host/pool on management server.
     * 
     * @param password 	the new password for the host/cluster
     * @param userName the username for the host/cluster
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateHostPasswordResponse updateHostPassword(String password, 
    		String userName, HashMap<String,String> optional) 
    				throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateHostPassword", optional);
        arguments.add(new NameValuePair("password", password));
        arguments.add(new NameValuePair("userName", userName));
        
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateHostPasswordResponse(responseDocument);
        
    }
    
    /**
      * Converts XML document into UpdateHostPasswordResponse object
      * 
      * @param doc
      * @return 
      */
    private UpdateHostPasswordResponse getUpdateHostPasswordResponse(Document doc) {
    	UpdateHostPasswordResponse response = new UpdateHostPasswordResponse();
        
        // get displaytext from XML and set Any text associated with the success or 
        // failure on updating a host password
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getNodeValue());
        }
        
        // get success from XML and set Return true if update a host password operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getNodeValue());
        }
        
         return response;
    }
}
