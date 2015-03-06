package com.assistanz.cloud.cloudstack.swift;

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
public class CSSwiftService {
	
	private CloudStackServer server;
	
	public CSSwiftService(CloudStackServer server) {
		this.server = server;
	}
	
	/**
	 * Adds Swift.
	 * 
	 * @param swiftUrl 	the URL for swift
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public AddSwiftResponse addSwift(String swiftUrl,
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("addSwift", optional);
		arguments.add(new NameValuePair("url",  swiftUrl));
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getAddSwiftResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into AddSwiftResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private AddSwiftResponse getAddSwiftResponse(Document doc) {
		AddSwiftResponse response = new AddSwiftResponse();

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
	 * List Swift.
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListSwiftsResponse listSwifts(
			HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listSwifts", optional);
		
		Document responseDocument = server.makeRequest(arguments);
		
		return getListSwiftsResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListSwiftsResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListSwiftsResponse getListSwiftsResponse(Document doc) {
		ListSwiftsResponse response = new ListSwiftsResponse();

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

}
