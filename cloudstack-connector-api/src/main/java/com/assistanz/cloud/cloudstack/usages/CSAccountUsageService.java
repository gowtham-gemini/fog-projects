package com.assistanz.cloud.cloudstack.usages;

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
public class CSAccountUsageService {

    private CloudStackServer server;

    public CSAccountUsageService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds traffic type to a physical network
     *
     * @param physicalNetworkId The Physical Network ID
     * @param trafficType The trafficType to be added to the physical network
     * @param optional
     * @return
     * @throws Exception
     */
    public AddTrafficTypeResponse addTrafficType(String physicalNetworkId, String trafficType,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addTrafficType", optional);
        arguments.add(new NameValuePair("physicalnetworkid", physicalNetworkId));
        arguments.add(new NameValuePair("traffictype", trafficType));

        Document responseDocument = server.makeRequest(arguments);

        return getAddTrafficTypeResponse(responseDocument);
    }

    /**
     * Converts XML document into AddTrafficTypeResponse object
     *
     * @param doc
     * @return
     */
    private AddTrafficTypeResponse getAddTrafficTypeResponse(Document doc) {
        AddTrafficTypeResponse response = new AddTrafficTypeResponse();

        // get id from XML and set as id of the network provider
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get kvmnetworklabel from XML and set as The network name label of the physical 
        //device dedicated to this traffic on a KVM host
        list = doc.getElementsByTagName("kvmnetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKvmNetworkLabel(node.getTextContent());
        }

        // get physicalnetworkid from XML and set as the physical network this belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get traffictype from XML and set as 	the trafficType to be added to the physical network
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTrafficType(node.getTextContent());
        }

        // get vmwarenetworklabel from XML and set as The network name label of the physical device 
        //dedicated to this traffic on a VMware host
        list = doc.getElementsByTagName("vmwarenetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmwareNetworkLabel(node.getTextContent());
        }

        // get xennetworklabel from XML and set as The network name label of the physical device 
        //dedicated to this traffic on a XenServer host
        list = doc.getElementsByTagName("xennetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setXenNetworkLabel(node.getTextContent());
        }

        return response;
    }

    /**
     * Deletes traffic type of a physical network
     *
     * @param trafficTypeId traffic type id
     * @return
     * @throws Exception
     */
    public DeleteTrafficTypeResponse deleteTrafficType(String trafficTypeId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteTrafficType", null);
        arguments.add(new NameValuePair("id", trafficTypeId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteTrafficTypeResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteTrafficTypeResponse object
     *
     * @param doc
     * @return
     */
    private DeleteTrafficTypeResponse getDeleteTrafficTypeResponse(Document doc) {
        DeleteTrafficTypeResponse response = new DeleteTrafficTypeResponse();

        // get displaytext from XML and set as any text associated with the success or failure on deleting traffic type
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }
        // get success from XML and set as true if operation is executed successfully on deleting traffic type
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists traffic types of a given physical network.
     *
     * @param physicalNetworkId the Physical Network ID
     * @param optional
     * @return
     * @throws Exception
     */
    public ListTrafficTypesResponse listTrafficTypes(String physicalNetworkId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listTrafficTypes", optional);
        arguments.add(new NameValuePair("physicalnetworkid", physicalNetworkId));

        Document responseDocument = server.makeRequest(arguments);

        return getListTrafficTypesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListTrafficTypeResponse object
     *
     * @param doc
     * @return
     */
    private ListTrafficTypesResponse getListTrafficTypesResponse(Document doc) {
        ListTrafficTypesResponse response = new ListTrafficTypesResponse();

        // Lists traffic types of a given physical network
        NodeList list = doc.getElementsByTagName("traffictype");
        List<TrafficTypeResponse> trafficTypes = new LinkedList<TrafficTypeResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node trafficTypeNode = list.item(index);

                TrafficTypeResponse trafficType = new TrafficTypeResponse();

                NodeList trafficTypeProperties = trafficTypeNode.getChildNodes();
                for (int childIndex = 0; childIndex < trafficTypeProperties.getLength(); childIndex++) {
                    Node property = trafficTypeProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        trafficType.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("canenableindividualservice")) {
                        trafficType.setCanEnableIndividualService(property.getTextContent());
                    } else if (property.getNodeName().equals("destinationphysicalnetworkid")) {
                        trafficType.setDestinationPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        trafficType.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        trafficType.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("servicelist")) {
                        trafficType.setServiceList(property.getTextContent());
                    } else if (property.getNodeName().equals("state")) {
                        trafficType.setState(property.getTextContent());
                    }
                }
                trafficTypes.add(trafficType);
            }
        }

        response.setTrafficTypes(trafficTypes);
        return response;
    }

    /**
     * Updates traffic type of a physical network
     *
     * @param TrafficTypeId The traffic type id
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateTrafficTypeResponse updateTrafficType(String TrafficTypeId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateTrafficType", optional);
        arguments.add(new NameValuePair("id", TrafficTypeId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateTrafficTypeResponse(responseDocument);
    }

    /**
     * Converts XML document into getListTrafficTypeResponse object
     *
     * @param doc
     * @return
     */
    private UpdateTrafficTypeResponse getUpdateTrafficTypeResponse(Document doc) {
        UpdateTrafficTypeResponse response = new UpdateTrafficTypeResponse();

        // get id from XML and set id of the network provider
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get kvmnetworklabel from XML and set The network name label of 
        //the physical device dedicated to this traffic on a KVM host
        list = doc.getElementsByTagName("kvmnetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKvmNetworkLabel(node.getTextContent());
        }

        // get physicalnetworkid from XML and set the physical network this belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get traffictype from XML and set the trafficType to be added to the physical network
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTrafficType(node.getTextContent());
        }

        // get vmwarenetworklabel from XML and set The network name label of 
        //the physical device dedicated to this traffic on a VMware host
        list = doc.getElementsByTagName("vmwarenetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmwareNetworkLabel(node.getTextContent());
        }

        // get xennetworklabel from XML and set The network name label of 
        //the physical device dedicated to this traffic on a XenServer host
        list = doc.getElementsByTagName("xennetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setXenNetworkLabel(node.getTextContent());
        }

        return response;

    }

    /**
     * Lists implementors of implementor of a network traffic type or implementors of all network traffic types
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListTrafficTypeImplementorsResponse listTrafficTypeImplementors(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listTrafficTypeImplementors", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListTrafficTypeImplementorsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListTrafficTypeImplementorsResponse object
     *
     * @param doc
     * @return
     */
    private ListTrafficTypeImplementorsResponse getListTrafficTypeImplementorsResponse(Document doc) {
        ListTrafficTypeImplementorsResponse response = new ListTrafficTypeImplementorsResponse();

        // Lists implementors of implementor of a network traffic type or implementors of all network traffic types
        NodeList list = doc.getElementsByTagName("traffictypeimplementor");
        List<TrafficTypeImplementorResponse> trafficTypeImplementors = new LinkedList<TrafficTypeImplementorResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node trafficTypeImplementorNode = list.item(index);

                TrafficTypeImplementorResponse trafficTypeImplementor = new TrafficTypeImplementorResponse();

                NodeList trafficTypeImplementorProperties = trafficTypeImplementorNode.getChildNodes();
                for (int childIndex = 0; childIndex < trafficTypeImplementorProperties.getLength(); childIndex++) {
                    Node property = trafficTypeImplementorProperties.item(childIndex);

                    if (property.getNodeName().equals("traffictype")) {
                        trafficTypeImplementor.setTrafficType(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictypeimplementor")) {
                        trafficTypeImplementor.setTrafficTypeImplementor(property.getTextContent());
                    }
                }
                trafficTypeImplementors.add(trafficTypeImplementor);
            }
        }

        response.setTrafficTypeImplementors(trafficTypeImplementors);
        return response;

    }

    /**
     * Generates usage records. This will generate records only if there any records to be generated, i.e if the
     * scheduled usage job was not run or failed
     *
     * @param endDate End date range for usage record query. Use yyyy-MM-dd as the date format, e.g.
     * startDate=2009-06-03
     *
     * @param startDate Start date range for usage record query. Use yyyy-MM-dd as the date format, e.g.
     * startDate=2009-06-01.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public GenerateUsageRecordsResponse generateUsageRecords(String endDate,
            String startDate, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("generateUsageRecords", optional);
        arguments.add(new NameValuePair("enddate", endDate));
        arguments.add(new NameValuePair("startdate", startDate));

        Document responseDocument = server.makeRequest(arguments);

        return getGenerateUsageRecordsResponse(responseDocument);
    }

    /**
     * Converts XML document into GenerateUsageRecordsResponse object
     *
     * @param doc
     * @return
     */
    private GenerateUsageRecordsResponse getGenerateUsageRecordsResponse(Document doc) {
        GenerateUsageRecordsResponse response = new GenerateUsageRecordsResponse();

        // get displaytext from XML and set as any text associated with the success or generating usage records
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }
        // get success from XML and set as true if operation is executed successfully on generating usage records
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists usage records for accounts
     *
     * @param endDate End date range for usage record query. Use yyyy-MM-dd as the date format, e.g.
     * startDate=2009-06-03.
     *
     * @param startDate Start date range for usage record query. Use yyyy-MM-dd as the date format, e.g.
     * startDate=2009-06-01.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListUsageRecordsResponse listUsageRecords(String endDate,
            String startDate, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listUsageRecords", optional);
        arguments.add(new NameValuePair("enddate", endDate));
        arguments.add(new NameValuePair("startdate", startDate));

        Document responseDocument = server.makeRequest(arguments);

        return getListUsageRecordsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListUsageRecordsResponse object
     *
     * @param doc
     * @return
     */
    private ListUsageRecordsResponse getListUsageRecordsResponse(Document doc) {
        ListUsageRecordsResponse response = new ListUsageRecordsResponse();

        //list of UsageRecord
        NodeList list = doc.getElementsByTagName("usagerecord");

        List<UsageRecordResponse> usageRecords = new LinkedList<UsageRecordResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node usageRecordNode = list.item(index);

                UsageRecordResponse usageRecord = new UsageRecordResponse();
                NodeList usageRecordProperties = usageRecordNode.getChildNodes();
                for (int childIndex = 0; childIndex < usageRecordProperties.getLength(); childIndex++) {
                    Node property = usageRecordProperties.item(childIndex);

                    if (property.getNodeName().equals("account")) {
                        usageRecord.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                        usageRecord.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        usageRecord.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        usageRecord.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        usageRecord.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("enddate")) {
                        usageRecord.setEndDate(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        usageRecord.setIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("issourcenat")) {
                        usageRecord.setIsSourceNat(property.getTextContent());
                    } else if (property.getNodeName().equals("issystem")) {
                        usageRecord.setIsSystem(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        usageRecord.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        usageRecord.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("offeringid")) {
                        usageRecord.setOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        usageRecord.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        usageRecord.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("rawusage")) {
                        usageRecord.setRawUsage(property.getTextContent());
                    } else if (property.getNodeName().equals("size")) {
                        usageRecord.setSize(property.getTextContent());
                    } else if (property.getNodeName().equals("startdate")) {
                        usageRecord.setStartDate(property.getTextContent());
                    } else if (property.getNodeName().equals("templateid")) {
                        usageRecord.setTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        usageRecord.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("usage")) {
                        usageRecord.setUsage(property.getTextContent());
                    } else if (property.getNodeName().equals("usageid")) {
                        usageRecord.setUsageId(property.getTextContent());
                    } else if (property.getNodeName().equals("usagetype")) {
                        usageRecord.setUsageType(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachineid")) {
                        usageRecord.setVirtualMachineId(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualsize")) {
                        usageRecord.setVirtualSize(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        usageRecord.setZoneId(property.getTextContent());
                    }
                }
                usageRecords.add(usageRecord);
            }
        }
        response.setUsageRecords(usageRecords);
        return response;
    }

    /**
     * List Usage Types
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListUsageTypesResponse listUsageTypes() throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listUsageTypes", null);

        Document responseDocument = server.makeRequest(arguments);

        return getListUsageTypesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListUsageTypesResponse object
     *
     * @param doc
     * @return
     */
    private ListUsageTypesResponse getListUsageTypesResponse(Document doc) {
        ListUsageTypesResponse response = new ListUsageTypesResponse();

        //list of UsageRecord
        NodeList list = doc.getElementsByTagName("usagetype");

        List<UsageTypeRecordResponse> usageTypes = new LinkedList<UsageTypeRecordResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node usageTypeNode = list.item(index);

                UsageTypeRecordResponse usageType = new UsageTypeRecordResponse();

                NodeList usageTypeProperties = usageTypeNode.getChildNodes();
                for (int childIndex = 0; childIndex < usageTypeProperties.getLength(); childIndex++) {
                    Node property = usageTypeProperties.item(childIndex);

                    if (property.getNodeName().equals("usagetypeid")) {
                        usageType.setUsageTypeId(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                        usageType.setDescription(property.getTextContent());
                    }
                }
                usageTypes.add(usageType);
            }
        }

        response.setUsageTypes(usageTypes);
        return response;

    }

    /**
     * Adds Traffic Monitor Host for Direct Network Usage
     *
     * @param url URL of the traffic monitor Host
     * @param zoneId Zone in which to add the external firewall appliance.
     * @return
     * @throws Exception
     */
    public AddTrafficMonitorResponse addTrafficMonitor(String url,
            String zoneId) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addTrafficMonitor", null);
        arguments.add(new NameValuePair("url", url));
        arguments.add(new NameValuePair("zoneid", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getAddTrafficMonitorResponse(responseDocument);
    }

    /**
     * Converts XML document into AddTrafficMonitorResponse object
     *
     * @param doc
     * @return
     */
    private AddTrafficMonitorResponse getAddTrafficMonitorResponse(Document doc) {
        AddTrafficMonitorResponse response = new AddTrafficMonitorResponse();

        // get id from XML and set the ID of the external firewall
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get ipaddress from XML and set the management IP address of the external firewall
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpAddress(node.getTextContent());
        }

        // get numretries from XML and set the number of times to retry requests to the external firewall
        list = doc.getElementsByTagName("numretries");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNumRetries(node.getTextContent());
        }

        // get timeout from XML and set the timeout (in seconds) for requests to the external firewall
        list = doc.getElementsByTagName("timeout");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTimeout(node.getTextContent());
        }

        // get zoneid from XML and set the zone ID of the external firewalls
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;

    }

    /**
     * Deletes an traffic monitor host.
     *
     * @param TrafficMonitorId The Id of the Traffic Monitor Host.
     * @return
     * @throws Exception
     */
    public DeleteTrafficMonitorResponse deleteTrafficMonitor(String TrafficMonitorId
    ) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteTrafficMonitor", null);
        arguments.add(new NameValuePair("id", TrafficMonitorId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteTrafficMonitorResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteTrafficMonitorResponse object
     *
     * @param doc
     * @return
     */
    private DeleteTrafficMonitorResponse getDeleteTrafficMonitorResponse(Document doc) {
        DeleteTrafficMonitorResponse response = new DeleteTrafficMonitorResponse();

        // get displaytext from XML and set as any text associated with the success on deleting traffic monitor
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }
        // get success from XML and set as true if operation is executed successfully on deleting traffic monitor
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for usage.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public UsageJobResultResponse usageJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getUsageJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into UsageJobResultResponse object
     *
     * @param doc
     * @return
     */
    private UsageJobResultResponse getUsageJobResultResponse(Document doc) {
        UsageJobResultResponse response = new UsageJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("traffictype")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("canenableindividualservice")) {
                            response.setCanEnableIndividualService(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("destinationphysicalnetworkid")) {
                            response.setDestinationPhysicalNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("physicalnetworkid")) {
                            response.setPhysicalNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("servicelist")) {
                            response.setServiceList(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
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
     * List traffic monitor Hosts.
     *
     * @param zoneId zone Id
     * @param optional
     * @return
     * @throws Exception
     */
    public ListTrafficMonitorResponse listTrafficMonitors(String zoneId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listTrafficMonitors", optional);
        arguments.add(new NameValuePair("zoneid", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getListTrafficMonitorResponse(responseDocument);
    }

    private ListTrafficMonitorResponse getListTrafficMonitorResponse(Document doc) {
        ListTrafficMonitorResponse response = new ListTrafficMonitorResponse();

        // List traffic monitor Hosts
        NodeList list = doc.getElementsByTagName("trafficmonitor");

        List<TrafficMonitorResponse> trafficMonitors = new LinkedList<TrafficMonitorResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node trafficMonitorNode = list.item(index);

                TrafficMonitorResponse trafficMonitor = new TrafficMonitorResponse();

                NodeList trafficMonitorProperties = trafficMonitorNode.getChildNodes();
                for (int childIndex = 0; childIndex < trafficMonitorProperties.getLength(); childIndex++) {
                    Node property = trafficMonitorProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        trafficMonitor.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        trafficMonitor.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("numretries")) {
                        trafficMonitor.setNumretries(property.getTextContent());
                    } else if (property.getNodeName().equals("timeout")) {
                        trafficMonitor.setTimeout(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        trafficMonitor.setZoneId(property.getTextContent());
                    }
                }
                trafficMonitors.add(trafficMonitor);
            }
        }

        response.setTrafficMonitors(trafficMonitors);
        return response;
    }

}
