package com.assistanz.cloud.cloudstack.host;

import java.util.HashMap;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
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
     * @param hostHypervisorType hypervisor type of the host
     * @param hostPassword the password for the host
     * @param podId
     * @param hostUrl the host URL
     * @param hostUserName the username for the host
     * @param hostZoneId the Zone ID for the host
     * @param optional
     * @return
     * @throws Exception
     */
    public AddHostResponse addHost(String hostHypervisorType, String hostPassword,
            String podId, String hostUrl, String hostUserName,
            String hostZoneId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addHost", optional);
        arguments.add(new NameValuePair("hypervisor", hostHypervisorType));
        arguments.add(new NameValuePair("password", hostPassword));
        arguments.add(new NameValuePair("podid", podId));
        arguments.add(new NameValuePair("url", hostUrl));
        arguments.add(new NameValuePair("username", hostUserName));
        arguments.add(new NameValuePair("zoneid", hostZoneId));

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
            response.setId(node.getTextContent());
        }

        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAverageLoad(node.getTextContent());
        }

        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapabilities(node.getTextContent());
        }

        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getTextContent());
        }

        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAllocated(node.getTextContent());
        }

        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }

        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }

        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuUsed(node.getTextContent());
        }

        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuWithOverProvisioning(node.getTextContent());
        }

        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisconnected(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEvents(node.getTextContent());
        }

        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getTextContent());
        }

        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getTextContent());
        }

        // get lastpinged from XML and set the date and time the host was last pinged
        list = doc.getElementsByTagName("lastpinged");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLastPinged(node.getTextContent());
        }

        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setManagementServerId(node.getTextContent());
        }

        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAllocated(node.getTextContent());
        }

        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryUsed(node.getTextContent());
        }

        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsWrite(node.getTextContent());
        }

        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryId(node.getTextContent());
        }

        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryName(node.getTextContent());
        }

        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setResourceState(node.getTextContent());
        }

        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVersion(node.getTextContent());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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
     * Reconnects a host.
     *
     * @param hostId the host id
     * @return
     * @throws Exception
     */
    public ReconnectHostResponse reconnectHost(String hostId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("reconnectHost", null);
        arguments.add(new NameValuePair("id", hostId));

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
            response.setId(node.getTextContent());
        }

        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAverageLoad(node.getTextContent());
        }

        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapabilities(node.getTextContent());
        }

        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getTextContent());
        }

        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAllocated(node.getTextContent());
        }

        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }

        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }

        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuUsed(node.getTextContent());
        }

        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuWithOverProvisioning(node.getTextContent());
        }

        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisconnected(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEvents(node.getTextContent());
        }

        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getTextContent());
        }

        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getTextContent());
        }

        // get lastpinged from XML and set the date and time the host was last pinged
        list = doc.getElementsByTagName("lastpinged");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLastPinged(node.getTextContent());
        }

        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setManagementServerId(node.getTextContent());
        }

        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAllocated(node.getTextContent());
        }

        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryUsed(node.getTextContent());
        }

        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsWrite(node.getTextContent());
        }

        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryId(node.getTextContent());
        }

        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryName(node.getTextContent());
        }

        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setResourceState(node.getTextContent());
        }

        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVersion(node.getTextContent());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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
     * Updates a host.
     *
     * @param hostId the ID of the host to update
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateHostResponse updateHost(String hostId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateHost", optional);
        arguments.add(new NameValuePair("id", hostId));

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
            response.setId(node.getTextContent());
        }

        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAverageLoad(node.getTextContent());
        }

        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapabilities(node.getTextContent());
        }

        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getTextContent());
        }

        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAllocated(node.getTextContent());
        }

        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }

        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }

        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuUsed(node.getTextContent());
        }

        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuWithOverProvisioning(node.getTextContent());
        }

        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisconnected(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEvents(node.getTextContent());
        }

        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getTextContent());
        }

        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getTextContent());
        }

        // get lastpinged from XML and set the date and time the host was last pinged
        list = doc.getElementsByTagName("lastpinged");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLastPinged(node.getTextContent());
        }

        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setManagementServerId(node.getTextContent());
        }

        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAllocated(node.getTextContent());
        }

        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryUsed(node.getTextContent());
        }

        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsWrite(node.getTextContent());
        }

        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryId(node.getTextContent());
        }

        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryName(node.getTextContent());
        }

        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setResourceState(node.getTextContent());
        }

        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVersion(node.getTextContent());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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
     * Deletes a host.
     *
     * @param hostId the host id
     * @param optional
     * @return
     * @throws Exception
     */
    public DeleteHostResponse deleteHost(String hostId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteHost", optional);
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
            response.setDisplayText(node.getTextContent());
        }

        // get success from XML and set Return true if Delete host operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Prepares a host for maintenance.
     *
     * @param hostId the host ID
     * @return
     * @throws Exception
     */
    public PrepareHostForMaintenanceResponse prepareHostForMaintenance(String hostId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("prepareHostForMaintenance", null);
        arguments.add(new NameValuePair("id", hostId));

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
            response.setId(node.getTextContent());
        }

        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAverageLoad(node.getTextContent());
        }

        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapabilities(node.getTextContent());
        }

        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getTextContent());
        }

        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAllocated(node.getTextContent());
        }

        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }

        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }

        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuUsed(node.getTextContent());
        }

        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuWithOverProvisioning(node.getTextContent());
        }

        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisconnected(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEvents(node.getTextContent());
        }

        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getTextContent());
        }

        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getTextContent());
        }

        // get lastpinged from XML and set the date and time the host was last pinged
        list = doc.getElementsByTagName("lastpinged");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLastPinged(node.getTextContent());
        }

        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setManagementServerId(node.getTextContent());
        }

        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAllocated(node.getTextContent());
        }

        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryUsed(node.getTextContent());
        }

        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsWrite(node.getTextContent());
        }

        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryId(node.getTextContent());
        }

        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryName(node.getTextContent());
        }

        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setResourceState(node.getTextContent());
        }

        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVersion(node.getTextContent());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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
     * Cancels host maintenance.
     *
     * @param hostId the host ID
     * @return
     * @throws Exception
     */
    public CancelHostMaintenanceResponse cancelHostMaintenance(String hostId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("cancelHostMaintenance", null);
        arguments.add(new NameValuePair("id", hostId));

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
            response.setId(node.getTextContent());
        }

        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAverageLoad(node.getTextContent());
        }

        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapabilities(node.getTextContent());
        }

        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getTextContent());
        }

        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAllocated(node.getTextContent());
        }

        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }

        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }

        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuUsed(node.getTextContent());
        }

        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuWithOverProvisioning(node.getTextContent());
        }

        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisconnected(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEvents(node.getTextContent());
        }

        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getTextContent());
        }

        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getTextContent());
        }

        // get lastpinged from XML and set the date and time the host was last pinged
        list = doc.getElementsByTagName("lastpinged");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLastPinged(node.getTextContent());
        }

        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setManagementServerId(node.getTextContent());
        }

        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAllocated(node.getTextContent());
        }

        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryUsed(node.getTextContent());
        }

        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsWrite(node.getTextContent());
        }

        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryId(node.getTextContent());
        }

        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryName(node.getTextContent());
        }

        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setResourceState(node.getTextContent());
        }

        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVersion(node.getTextContent());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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
     * Find hosts suitable for migrating a virtual machine.
     *
     * @param virtualMachineid find hosts to which this VM can be migrated and flag the hosts with enough CPU/RAM to
     * host the VM
     * @return
     * @throws Exception
     */
    public FindHostsForMigrationResponse findHostsForMigration(String virtualMachineid) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("findHostsForMigration", null);
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineid));

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
                        host.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("averageload")) {
                        host.setAverageLoad(property.getTextContent());
                    } else if (property.getNodeName().equals("capabilities")) {
                        host.setCapabilities(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                        host.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                        host.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("clustertype")) {
                        host.setClusterType(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuallocated")) {
                        host.setCpuAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("cpunumber")) {
                        host.setCpuNumber(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuspeed")) {
                        host.setCpuSpeed(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuused")) {
                        host.setCpuUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuwithoverprovisioning")) {
                        host.setCpuWithOverProvisioning(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        host.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("disconnected")) {
                        host.setDisconnected(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizeallocated")) {
                        host.setDiskSizeAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizetotal")) {
                        host.setDiskSizeTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("events")) {
                        host.setEvents(property.getTextContent());
                    } else if (property.getNodeName().equals("hasenoughcapacity")) {
                        host.setHasEnoughCapacity(property.getTextContent());
                    } else if (property.getNodeName().equals("hosttags")) {
                        host.setTags(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                        host.setHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisorversion")) {
                        host.setHypervisorVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        host.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("islocalstorageactive")) {
                        host.setIsLocalStorageActive(property.getTextContent());
                    } else if (property.getNodeName().equals("lastpinged")) {
                        host.setLastPinged(property.getTextContent());
                    } else if (property.getNodeName().equals("managementserverid")) {
                        host.setManagementServerId(property.getTextContent());
                    } else if (property.getNodeName().equals("memoryallocated")) {
                        host.setMemoryAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("memorytotal")) {
                        host.setMemoryTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("memoryused")) {
                        host.setMemoryUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        host.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbsread")) {
                        host.setNetworKbsRead(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbswrite")) {
                        host.setNetworKbsWrite(property.getTextContent());
                    } else if (property.getNodeName().equals("oscategoryid")) {
                        host.setOsCategoryId(property.getTextContent());
                    } else if (property.getNodeName().equals("oscategoryname")) {
                        host.setOsCategoryName(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        host.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        host.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("removed")) {
                        host.setRemoved(property.getTextContent());
                    } else if (property.getNodeName().equals("resourcestate")) {
                        host.setResourceState(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        host.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("suitableformigration")) {
                        host.setSuitableForMigration(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        host.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("version")) {
                        host.setVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        host.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        host.setZoneName(property.getTextContent());
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
    public ListHostsResponse listHosts(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listHosts", optional);

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
                        host.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("averageload")) {
                        host.setAverageLoad(property.getTextContent());
                    } else if (property.getNodeName().equals("capabilities")) {
                        host.setCapabilities(property.getTextContent());
                    } else if (property.getNodeName().equals("clusterid")) {
                        host.setClusterId(property.getTextContent());
                    } else if (property.getNodeName().equals("clustername")) {
                        host.setClusterName(property.getTextContent());
                    } else if (property.getNodeName().equals("clustertype")) {
                        host.setClusterType(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuallocated")) {
                        host.setCpuAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("cpunumber")) {
                        host.setCpuNumber(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuspeed")) {
                        host.setCpuSpeed(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuused")) {
                        host.setCpuUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("cpuwithoverprovisioning")) {
                        host.setCpuWithOverProvisioning(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        host.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("disconnected")) {
                        host.setDisconnected(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizeallocated")) {
                        host.setDiskSizeAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("disksizetotal")) {
                        host.setDiskSizeTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("events")) {
                        host.setEvents(property.getTextContent());
                    } else if (property.getNodeName().equals("hasenoughcapacity")) {
                        host.setHasEnoughCapacity(property.getTextContent());
                    } else if (property.getNodeName().equals("hosttags")) {
                        host.setTags(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                        host.setHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisorversion")) {
                        host.setHypervisorVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        host.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("islocalstorageactive")) {
                        host.setIsLocalStorageActive(property.getTextContent());
                    } else if (property.getNodeName().equals("lastpinged")) {
                        host.setLastPinged(property.getTextContent());
                    } else if (property.getNodeName().equals("managementserverid")) {
                        host.setManagementServerId(property.getTextContent());
                    } else if (property.getNodeName().equals("memoryallocated")) {
                        host.setMemoryAllocated(property.getTextContent());
                    } else if (property.getNodeName().equals("memorytotal")) {
                        host.setMemoryTotal(property.getTextContent());
                    } else if (property.getNodeName().equals("memoryused")) {
                        host.setMemoryUsed(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        host.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbsread")) {
                        host.setNetworKbsRead(property.getTextContent());
                    } else if (property.getNodeName().equals("networkkbswrite")) {
                        host.setNetworKbsWrite(property.getTextContent());
                    } else if (property.getNodeName().equals("oscategoryid")) {
                        host.setOsCategoryId(property.getTextContent());
                    } else if (property.getNodeName().equals("oscategoryname")) {
                        host.setOsCategoryName(property.getTextContent());
                    } else if (property.getNodeName().equals("podid")) {
                        host.setPodId(property.getTextContent());
                    } else if (property.getNodeName().equals("podname")) {
                        host.setPodName(property.getTextContent());
                    } else if (property.getNodeName().equals("removed")) {
                        host.setRemoved(property.getTextContent());
                    } else if (property.getNodeName().equals("resourcestate")) {
                        host.setResourceState(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        host.setState(property.getTextContent());
                    } else if (property.getNodeName().equals("suitableformigration")) {
                        host.setSuitableForMigration(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        host.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("version")) {
                        host.setVersion(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        host.setZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                        host.setZoneName(property.getTextContent());
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
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addSecondaryStorage", optional);
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
            response.setId(node.getTextContent());
        }

        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAverageLoad(node.getTextContent());
        }

        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapabilities(node.getTextContent());
        }

        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getTextContent());
        }

        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAllocated(node.getTextContent());
        }

        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }

        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }

        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuUsed(node.getTextContent());
        }

        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuWithOverProvisioning(node.getTextContent());
        }

        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisconnected(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEvents(node.getTextContent());
        }

        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getTextContent());
        }

        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getTextContent());
        }

        // get lastpinged from XML and set the date and time the host was last pinged
        list = doc.getElementsByTagName("lastpinged");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLastPinged(node.getTextContent());
        }

        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setManagementServerId(node.getTextContent());
        }

        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAllocated(node.getTextContent());
        }

        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryUsed(node.getTextContent());
        }

        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsWrite(node.getTextContent());
        }

        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryId(node.getTextContent());
        }

        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryName(node.getTextContent());
        }

        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setResourceState(node.getTextContent());
        }

        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVersion(node.getTextContent());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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
     * Update password of a host/pool on management server.
     *
     * @param password the new password for the host/cluster
     * @param userName the username for the host/cluster
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateHostPasswordResponse updateHostPassword(String password,
            String userName, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateHostPassword", optional);
        arguments.add(new NameValuePair("password", password));
        arguments.add(new NameValuePair("username", userName));

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
            response.setDisplayText(node.getTextContent());
        }

        // get success from XML and set Return true if update a host password operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for host.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public HostJobResultResponse hostJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getHostJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into HostJobResultResponse object
     *
     * @param doc
     * @return
     */
    private HostJobResultResponse getHostJobResultResponse(Document doc) {
        HostJobResultResponse response = new HostJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("host")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("averageload")) {
                            response.setAverageLoad(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("capabilities")) {
                            response.setCapabilities(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("clusterid")) {
                            response.setClusterId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("clustername")) {
                            response.setClusterName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("clustertype")) {
                            response.setClusterType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("cpuallocated")) {
                            response.setCpuAllocated(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("cpunumber")) {
                            response.setCpuNumber(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("cpuspeed")) {
                            response.setCpuSpeed(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("cpuused")) {
                            response.setCpuUsed(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("cpuwithoverprovisioning")) {
                            response.setCpuWithOverProvisioning(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("created")) {
                            response.setCreated(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("disconnected")) {
                            response.setDisconnected(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("disksizeallocated")) {
                            response.setDiskSizeAllocated(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("disksizetotal")) {
                            response.setDiskSizeTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("events")) {
                            response.setEvents(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hahost")) {
                            response.setHaHost(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hasenoughcapacity")) {
                            response.setHasEnoughCapacity(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hosttags")) {
                            response.setHostTags(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hypervisor")) {
                            response.setHypervisor(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hypervisorversion")) {
                            response.setHypervisorVersion(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ipaddress")) {
                            response.setIpAddress(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("islocalstorageactive")) {
                            response.setIsLocalStorageActive(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("lastpinged")) {
                            response.setLastPinged(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("managementserverid")) {
                            response.setManagementServerId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("memoryallocated")) {
                            response.setMemoryAllocated(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("memorytotal")) {
                            response.setMemoryTotal(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("memoryused")) {
                            response.setMemoryUsed(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkkbsread")) {
                            response.setNetworkKbsRead(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkkbswrite")) {
                            response.setNetworkKbsWrite(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("oscategoryid")) {
                            response.setOsCategoryId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("oscategoryname")) {
                            response.setOsCategoryName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("podid")) {
                            response.setPodId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("podname")) {
                            response.setPodName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("removed")) {
                            response.setRemoved(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("resourcestate")) {
                            response.setResourceState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("suitableformigration")) {
                            response.setSuitableForMigration(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("type")) {
                            response.setType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("version")) {
                            response.setVersion(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zonename")) {
                            response.setZoneName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("jobid")) {
                            response.setJobId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("jobstatus")) {
                            response.setJobStatus(childNodeProperty.getTextContent());
                        }
                    }

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
     * Releases host reservation.
     *
     * @param hostId the host ID
     * @return
     * @throws Exception
     */
    public ReleaseHostReservationResponse releaseHostReservation(String hostId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("releaseHostReservation", null);
        arguments.add(new NameValuePair("id", hostId));

        Document responseDocument = server.makeRequest(arguments);

        return getReleaseHostReservationResponse(responseDocument);

    }

    /**
     * Converts XML document into ReleaseHostReservationResponse object
     *
     * @param doc
     * @return
     */
    private ReleaseHostReservationResponse getReleaseHostReservationResponse(Document doc) {
        ReleaseHostReservationResponse response = new ReleaseHostReservationResponse();

        // get displaytext from XML and set Any text associated with the success or 
        // failure on updating a host password
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get success from XML and set Return true if update a host password operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Add a BAREMETAL host.
     *
     * @param hostHypervisorType hypervisor type of the host
     * @param hostPassword the password for the host
     * @param podId
     * @param hostUrl the host URL
     * @param hostUserName the username for the host
     * @param hostZoneId the Zone ID for the host
     * @param optional
     * @return
     * @throws Exception
     */
    public AddBaremetalHostResponse addBaremetalHost(String hostHypervisorType, String hostPassword,
            String podId, String hostUrl, String hostUserName,
            String hostZoneId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addBaremetalHost", optional);
        arguments.add(new NameValuePair("hypervisor", hostHypervisorType));
        arguments.add(new NameValuePair("password", hostPassword));
        arguments.add(new NameValuePair("podid", podId));
        arguments.add(new NameValuePair("url", hostUrl));
        arguments.add(new NameValuePair("username", hostUserName));
        arguments.add(new NameValuePair("zoneid", hostZoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getAddBaremetalHostResponse(responseDocument);
    }

    /**
     * Converts XML document into AddBaremetalHostResponse object
     *
     * @param doc
     * @return
     */
    private AddBaremetalHostResponse getAddBaremetalHostResponse(Document doc) {
        AddBaremetalHostResponse response = new AddBaremetalHostResponse();

        // get id from XML and set as the ID of the host
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get averageload from XML and set as the cpu average load on the host
        list = doc.getElementsByTagName("averageload");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAverageLoad(node.getTextContent());
        }

        // get capabilities from XML and set as the capabilities of the host
        list = doc.getElementsByTagName("capabilities");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCapabilities(node.getTextContent());
        }

        // get clusterid from XML and set as the cluster ID of the host
        list = doc.getElementsByTagName("clusterid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterId(node.getTextContent());
        }

        // get clustername from XML and set as the cluster name of the host
        list = doc.getElementsByTagName("clustername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterName(node.getTextContent());
        }

        // get clustertype from XML and set as the cluster type of the cluster that host belongs to
        list = doc.getElementsByTagName("clustertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setClusterType(node.getTextContent());
        }

        // get cpuallocated from XML and set as the amount of the host's CPU currently allocated
        list = doc.getElementsByTagName("cpuallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuAllocated(node.getTextContent());
        }

        // get cpunumber from XML and set as the CPU number of the host
        list = doc.getElementsByTagName("cpunumber");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuNumber(node.getTextContent());
        }

        // get cpuspeed from XML and set as the CPU speed of the host
        list = doc.getElementsByTagName("cpuspeed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuSpeed(node.getTextContent());
        }

        // get cpuused from XML and set as the amount of the host's CPU currently used
        list = doc.getElementsByTagName("cpuused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuUsed(node.getTextContent());
        }

        // get cpuwithoverprovisioning from XML and set as the amount of the host's CPU after applying the cpu.overprovisioning.factor 
        list = doc.getElementsByTagName("cpuwithoverprovisioning");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCpuWithOverProvisioning(node.getTextContent());
        }

        // get created from XML and set as the date and time the host was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get disconnected from XML and set as true if the host is disconnected. False otherwise.
        list = doc.getElementsByTagName("disconnected");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisconnected(node.getTextContent());
        }

        // get disksizeallocated from XML and set as the host's currently allocated disk size
        list = doc.getElementsByTagName("disksizeallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeAllocated(node.getTextContent());
        }

        // get disksizetotal from XML and set as the total disk size of the host
        list = doc.getElementsByTagName("disksizetotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSizeTotal(node.getTextContent());
        }

        // get events from XML and set as events available for the host
        list = doc.getElementsByTagName("events");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEvents(node.getTextContent());
        }

        // get hasenoughcapacity from XML and set as 	true if this host has enough CPU and RAM capacity to migrate a VM to it, false otherwise
        list = doc.getElementsByTagName("hasenoughcapacity");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasEnoughCapacity(node.getTextContent());
        }

        // get hosttags from XML and set as comma-separated list of tags for the host
        list = doc.getElementsByTagName("hosttags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTags(node.getTextContent());
        }

        // get hypervisor from XML and set as the host hypervisor
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get hypervisorversion from XML and set as the hypervisor version
        list = doc.getElementsByTagName("hypervisorversion");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisorVersion(node.getTextContent());
        }

        // get ipaddress from XML and set as the IP address of the host
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get islocalstorageactive from XML and set as true if local storage is active, false otherwise
        list = doc.getElementsByTagName("islocalstorageactive");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsLocalStorageActive(node.getTextContent());
        }

        // get lastpinged from XML and set the date and time the host was last pinged
        list = doc.getElementsByTagName("lastpinged");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLastPinged(node.getTextContent());
        }

        // get managementserverid from XML and set as the management server ID of the host
        list = doc.getElementsByTagName("managementserverid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setManagementServerId(node.getTextContent());
        }

        // get memoryallocated from XML and set as the amount of the host's memory currently allocated
        list = doc.getElementsByTagName("memoryallocated");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryAllocated(node.getTextContent());
        }

        // get memorytotal from XML and set as the memory total of the host
        list = doc.getElementsByTagName("memorytotal");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryTotal(node.getTextContent());
        }

        // get memoryused from XML and set as the amount of the host's memory currently used
        list = doc.getElementsByTagName("memoryused");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMemoryUsed(node.getTextContent());
        }

        // get name from XML and set as the name of the host
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkkbsread from XML and set as the incoming network traffic on the host
        list = doc.getElementsByTagName("networkkbsread");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsRead(node.getTextContent());
        }

        // get networkkbswrite from XML and set as the outgoing network traffic on the host
        list = doc.getElementsByTagName("networkkbswrite");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworKbsWrite(node.getTextContent());
        }

        // get oscategoryid from XML and set as the OS category ID of the host
        list = doc.getElementsByTagName("oscategoryid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryId(node.getTextContent());
        }

        // get oscategoryname from XML and set as the OS category name of the host
        list = doc.getElementsByTagName("oscategoryname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsCategoryName(node.getTextContent());
        }

        // get podid from XML and set as the Pod id of the host
        list = doc.getElementsByTagName("podid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodId(node.getTextContent());
        }

        // get podname from XML and set as the Pod name of the host
        list = doc.getElementsByTagName("podname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPodName(node.getTextContent());
        }

        // get removed from XML and the date and time the host was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get resourcestate from XML and set as the resource state of the host
        list = doc.getElementsByTagName("resourcestate");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setResourceState(node.getTextContent());
        }

        // get state from XML and set as the state of the host
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get suitableformigration from XML and set as true if this host is suitable(has enough capacity and satisfies all conditions like hosttags, max guests vm limit etc) to migrate a VM to it , false otherwise
        list = doc.getElementsByTagName("suitableformigration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuitableForMigration(node.getTextContent());
        }
        // get type from XML and set as the host type
        list = doc.getElementsByTagName("type");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setType(node.getTextContent());
        }

        // get version from XML and set as the host version
        list = doc.getElementsByTagName("version");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVersion(node.getTextContent());
        }
        // get zoneid from XML and set as user Time Zone
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set as the Zone name of the hoste
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
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
     * Dedicates a host
     *
     * @param domainId
     * @param hostId
     * @param optional
     * @return
     * @throws Exception
     */
    public DedicateHostResponse dedicateHost(String domainId, String hostId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("dedicateHost", optional);
        arguments.add(new NameValuePair("domainid", domainId));
        arguments.add(new NameValuePair("hostid", hostId));

        Document responseDocument = server.makeRequest(arguments);

        return getDedicateHostResponse(responseDocument);
    }

    /**
     * Converts XML document into DedicateHostResponse object
     *
     * @param doc
     * @return
     */
    private DedicateHostResponse getDedicateHostResponse(Document doc) {
        DedicateHostResponse response = new DedicateHostResponse();

        // get id from XML and set the ID of the dedicated resource   
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get accountid from XML and set the Account ID of the host   
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get affinitygroupid from XML and set the Dedication Affinity Group ID of the host   
        list = doc.getElementsByTagName("affinitygroupid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAffinityGroupId(node.getTextContent());
        }

        // get domainid from XML and set the domain ID of the host   
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get hostid from XML and set the ID of the host   
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set the name of the host
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        return response;
    }

    /**
     * Release the dedication for host.
     *
     * @param hostId the host ID
     * @return
     * @throws Exception
     */
    public ReleaseDedicatedHostResponse releaseDedicatedHost(String hostId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("releaseDedicatedHost", null);
        arguments.add(new NameValuePair("id", hostId));

        Document responseDocument = server.makeRequest(arguments);

        return getReleaseDedicatedHostResponse(responseDocument);

    }

    /**
     * Converts XML document into ReleaseDedicatedHostResponse object
     *
     * @param doc
     * @return
     */
    private ReleaseDedicatedHostResponse getReleaseDedicatedHostResponse(Document doc) {
        ReleaseDedicatedHostResponse response = new ReleaseDedicatedHostResponse();

        // get displaytext from XML and set Any text associated with the success or 
        // failure on updating a host password
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get success from XML and set Return true if update a host password operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists dedicated hosts.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListDedicatedHostsResponse listDedicatedHosts(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listDedicatedHosts", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListDedicatedHostsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListDedicatedHostsResponse object
     *
     * @param doc
     * @return
     */
    private ListDedicatedHostsResponse getListDedicatedHostsResponse(Document doc) {
        ListDedicatedHostsResponse response = new ListDedicatedHostsResponse();

        // Lists dedicated hosts
        NodeList list = doc.getElementsByTagName("dedicatedhost");
        List<DedicatedHostResponse> dedicatedHosts
                = new LinkedList<DedicatedHostResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node dedicatedHostNode = list.item(index);

                DedicatedHostResponse dedicatedHost = new DedicatedHostResponse();

                NodeList dedicatedHostProperties = dedicatedHostNode.getChildNodes();
                for (int childIndex = 0; childIndex < dedicatedHostProperties.getLength(); childIndex++) {
                    Node property = dedicatedHostProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        dedicatedHost.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        dedicatedHost.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("affinitygroupid")) {
                        dedicatedHost.setAffinityGroupId(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        dedicatedHost.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostid")) {
                        dedicatedHost.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostname")) {
                        dedicatedHost.setHostName(property.getTextContent());
                    }
                }
                dedicatedHosts.add(dedicatedHost);
            }
        }

        response.setDedicatedHosts(dedicatedHosts);
        return response;
    }

}
