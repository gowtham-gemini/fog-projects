package com.assistanz.cloud.cloudstack.usages;

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
           HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("addTrafficType", optional);
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
            response.setNetworkProviderId(node.getNodeValue());
        }
        
        // get kvmnetworklabel from XML and set as The network name label of the physical 
        //device dedicated to this traffic on a KVM host
        list = doc.getElementsByTagName("kvmnetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKvmNetworkLabel(node.getNodeValue());
        }
        
        // get physicalnetworkid from XML and set as the physical network this belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalnetworkid(node.getNodeValue());
        }
        
        // get traffictype from XML and set as 	the trafficType to be added to the physical network
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTraffictype(node.getNodeValue());
        }
        
        // get vmwarenetworklabel from XML and set as The network name label of the physical device 
        //dedicated to this traffic on a VMware host
        list = doc.getElementsByTagName("vmwarenetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmwarenetworklabel(node.getNodeValue());
        }
        
        // get xennetworklabel from XML and set as The network name label of the physical device 
        //dedicated to this traffic on a XenServer host
        list = doc.getElementsByTagName("xennetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setXennetworklabel(node.getNodeValue());
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
         
         LinkedList<NameValuePair> arguments = 
                 server.getDefaultQuery("deleteTrafficType", null);
         arguments.add(new NameValuePair("id", trafficTypeId));
                          
         Document responseDocument = server.makeRequest(arguments);

         return getDeleteTrafficTypeResponse(responseDocument);
     }
     
    /**
     *  Converts XML document into DeleteTrafficTypeResponse object
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
             response.setDisplayText(node.getNodeValue());
         }
         // get success from XML and set as true if operation is executed successfully on deleting traffic type
         list = doc.getElementsByTagName("success");
         if (list.getLength() > 0) {
             Node node = list.item(0);
             response.setSuccess(node.getNodeValue());
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
    public ListTrafficTypeResponse listTrafficType(String physicalNetworkId, 
    		HashMap<String,String> optional)throws Exception {
          
          LinkedList<NameValuePair> arguments = 
                  server.getDefaultQuery("listTrafficType", optional);
          arguments.add(new NameValuePair("physicalnetworkid", physicalNetworkId));
                           
          Document responseDocument = server.makeRequest(arguments);

          return getListTrafficTypeResponse(responseDocument);
    }
    
    /**
     * Converts XML document into ListTrafficTypeResponse object
     * 
     * @param doc
     * @return
     */
    private ListTrafficTypeResponse getListTrafficTypeResponse(Document doc) {
    	ListTrafficTypeResponse response = new ListTrafficTypeResponse();
    	
    	// get id from XML and set as id of the network provider
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkProviderId(node.getNodeValue());
        }
        
        // get canenableindividualservice from XML and set true if individual services can be enabled/disabled
        list = doc.getElementsByTagName("canenableindividualservice");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCanEnableIndividualService(node.getNodeValue());
        }
        
        // get destinationphysicalnetworkid from XML and set the destination physical network
        list = doc.getElementsByTagName("destinationphysicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestinationPhysicalNetworkId(node.getNodeValue());
        }
                        
        // get name from XML and set the provider name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProviderName(node.getNodeValue());
        }
        
        // get physicalnetworkid from XML and set the physical network this belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getNodeValue());
        }
        
        // get servicelist from XML and set services for this provider
        list = doc.getElementsByTagName("servicelist");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceList(node.getNodeValue());
        }
        
        // get state from XML and set state of the network provider
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getNodeValue());
        }
        
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
    		HashMap<String,String> optional)throws Exception {
          
          LinkedList<NameValuePair> arguments = 
                  server.getDefaultQuery("updateTrafficType", optional);
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
            response.setNetworkProviderId(node.getNodeValue());
        }
        
        // get kvmnetworklabel from XML and set The network name label of 
        //the physical device dedicated to this traffic on a KVM host
        list = doc.getElementsByTagName("kvmnetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setKvmNetworkLabel(node.getNodeValue());
        }
        
        // get physicalnetworkid from XML and set the physical network this belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalnetworkid(node.getNodeValue());
        }
        
        // get traffictype from XML and set the trafficType to be added to the physical network
        list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTraffictype(node.getNodeValue());
        }
        
        // get vmwarenetworklabel from XML and set The network name label of 
        //the physical device dedicated to this traffic on a VMware host
        list = doc.getElementsByTagName("vmwarenetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmwarenetworklabel(node.getNodeValue());
        }
        
        // get xennetworklabel from XML and set The network name label of 
    	//the physical device dedicated to this traffic on a XenServer host
        list = doc.getElementsByTagName("xennetworklabel");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setXennetworklabel(node.getNodeValue());
        }
            	
    	return response;
    	
    }	
    
    /**
     * Lists implementors of implementor of a network traffic type or 
     * implementors of all network traffic types
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public ListTrafficTypeImplementorsResponse listTrafficTypeImplementors( 
    		HashMap<String,String> optional)throws Exception {
          
          LinkedList<NameValuePair> arguments = 
                  server.getDefaultQuery("listTrafficTypeImplementors", optional);
                                    
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
    	
    	 // get traffictype from XML and set network traffic type
    	NodeList list = doc.getElementsByTagName("traffictype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTrafficTypeNetwork(node.getNodeValue());
        }
        
        // get traffictypeimplementor from XML and set implementor of network traffic type
        list = doc.getElementsByTagName("traffictypeimplementor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTrafficTypeImplementor(node.getNodeValue());
        }
        
    	return response;
    	
    }
    
    /**
     * Generates usage records. 
     * This will generate records only if there any records to be generated, 
     * i.e if the scheduled usage job was not run or failed
     * 
     * @param endDate End date range for usage record query. 
     * Use yyyy-MM-dd as the date format, e.g. startDate=2009-06-03
     * 
     * @param startDate Start date range for usage record query. 
     * Use yyyy-MM-dd as the date format, e.g. startDate=2009-06-01.
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public GenerateUsageRecordsResponse generateUsageRecords(String endDate, 
    		String startDate, HashMap<String,String> optional)throws Exception {
          
          LinkedList<NameValuePair> arguments = 
                  server.getDefaultQuery("generateUsageRecords", optional);
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
            response.setDisplayText(node.getNodeValue());
        }
        // get success from XML and set as true if operation is executed successfully on generating usage records
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getNodeValue());
        }
        
        
        return response;
   }
    
    /**
     * Lists usage records for accounts
     * 
     * @param endDate End date range for usage record query. 
     * Use yyyy-MM-dd as the date format, e.g. startDate=2009-06-03.
     *                 
     * @param startDate Start date range for usage record query. 
     * Use yyyy-MM-dd as the date format, e.g. startDate=2009-06-01.
     * 
     * @param optional
     * @return
     * @throws Exception
     */
    public ListUsageRecordsResponse listUsageRecords(String endDate, 
    		String startDate, HashMap<String,String> optional)throws Exception {
          
          LinkedList<NameValuePair> arguments = 
                  server.getDefaultQuery("listUsageRecords", optional);
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

                if (usageRecordNode == null) {
                    continue;
                }  
                
                UsageRecordResponse usageRecord = new UsageRecordResponse();
                        
                NodeList usageRecorProperties = usageRecordNode.getChildNodes();
                for (int childIndex = 0; childIndex < usageRecorProperties.getLength(); childIndex++) {
                    Node property = usageRecorProperties.item(childIndex);
                    
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                    
                    if (property.getNodeName().equals("usageid")) {
                        usageRecord.setUsageId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                    	usageRecord.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                    	usageRecord.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("description")) {
                    	usageRecord.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("enddate")) {
                    	usageRecord.setEndDate(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                    	usageRecord.setIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("issourcenat")) {
                    	usageRecord.setIsSourceNat(property.getTextContent());
                    } else if (property.getNodeName().equals("issystem")) {
                    	usageRecord.setIsSystem(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	usageRecord.setVmName(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                    	usageRecord.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                    	usageRecord.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                    	usageRecord.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("offeringid")) {
                    	usageRecord.setOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                    	usageRecord.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                    	usageRecord.setProjectName(property.getTextContent());
                    } else if (property.getNodeName().equals("rawusage")) {
                    	usageRecord.setRawUsage(property.getTextContent());
                    } else if (property.getNodeName().equals("size")) {
                    	usageRecord.setResourceSize(property.getTextContent());
                    } else if (property.getNodeName().equals("startdate")) {
                    	usageRecord.setStartDate(property.getTextContent());
                    } else if (property.getNodeName().equals("templateid")) {
                    	usageRecord.setTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	usageRecord.setResourceType(property.getTextContent());
                    } else if (property.getNodeName().equals("usage")) {
                    	usageRecord.setUsage(property.getTextContent());
                    } else if (property.getNodeName().equals("usagetype")) {
                    	usageRecord.setUsageType(property.getTextContent());
                    } else if (property.getNodeName().equals("virtualmachineid")) {
                    	usageRecord.setVmId(property.getTextContent());
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
    public ListUsageTypesResponse listUsageTypes()throws Exception {
          
          LinkedList<NameValuePair> arguments = 
                  server.getDefaultQuery("listUsageTypes", null);
                   
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

                if (usageTypeNode == null) {
                    continue;
                } 
                
                UsageTypeRecordResponse usageType = new UsageTypeRecordResponse();
                
                
                NodeList usageTypeProperties = usageTypeNode.getChildNodes();
                for (int childIndex = 0; childIndex < usageTypeProperties.getLength(); childIndex++) {
                    Node property = usageTypeProperties.item(childIndex);
                
                
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                    
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
    		String zoneId)throws Exception {
          
          LinkedList<NameValuePair> arguments = 
                  server.getDefaultQuery("addTrafficMonitor", null);
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
            response.setExternalFirewallId(node.getNodeValue());
        }
        
        // get ipaddress from XML and set the management IP address of the external firewall
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExternalFirewallIpAddress(node.getNodeValue());
        }
        
        // get numretries from XML and set the number of times to retry requests to the external firewall
        list = doc.getElementsByTagName("numretries");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNumberOfRetries(node.getNodeValue());
        }
        
        // get privateinterface from XML and set the private interface of the external firewall
        list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateInterface(node.getNodeValue());
        }
        
        // get privatezone from XML and set the private security zone of the external firewall
        list = doc.getElementsByTagName("privatezone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateZone(node.getNodeValue());
        }
        
        // get publicinterface from XML and set the public interface of the external firewall
        list = doc.getElementsByTagName("publicinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicInterface(node.getNodeValue());
        }
        
        // get publiczone from XML and set the public security zone of the external firewall
        list = doc.getElementsByTagName("publiczone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicZone(node.getNodeValue());
        }
        
        // get timeout from XML and set the timeout (in seconds) for requests to the external firewall
        list = doc.getElementsByTagName("timeout");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTimeout(node.getNodeValue());
        }
        
        // get usageinterface from XML and set the usage interface of the external firewall
        list = doc.getElementsByTagName("usageinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUsageInterface(node.getNodeValue());
        }
        
        // get username from XML and set the username that's used to log in to the external firewall
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserName(node.getNodeValue());
        }
        
        // get zoneid from XML and set the zone ID of the external firewalls
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getNodeValue());
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
    		)throws Exception {
          
          LinkedList<NameValuePair> arguments = 
                  server.getDefaultQuery("deleteTrafficMonitor", null);
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
            response.setDisplayText(node.getNodeValue());
        }
        // get success from XML and set as true if operation is executed successfully on deleting traffic monitor
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getNodeValue());
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
    		HashMap<String,String> optional)throws Exception {
          
          LinkedList<NameValuePair> arguments = 
                  server.getDefaultQuery("listTrafficMonitors", optional);
          arguments.add(new NameValuePair("zoneid", zoneId));
          
          Document responseDocument = server.makeRequest(arguments);

          return getListTrafficMonitorResponse(responseDocument);
    }
    
    private ListTrafficMonitorResponse getListTrafficMonitorResponse(Document doc) {
    	ListTrafficMonitorResponse response = new ListTrafficMonitorResponse();
    	
    	// get id from XML and set the ID of the external firewall
    	NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExternalFirewallId(node.getNodeValue());
        }
        
     	// get ipaddress from XML and set the management IP address of the external firewall
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExternalFirewallIpAddress(node.getNodeValue());
        }
        
        // get numretries from XML and set the number of times to retry requests to the external firewall
        list = doc.getElementsByTagName("numretries");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNumberOfRetries(node.getNodeValue());
        }
        
        // get privateinterface from XML and set the private interface of the external firewall
        list = doc.getElementsByTagName("privateinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateInterface(node.getNodeValue());
        }
        
        // get privatezone from XML and set the private security zone of the external firewall
        list = doc.getElementsByTagName("privatezone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPrivateZone(node.getNodeValue());
        }
        
        // get publicinterface from XML and set the public interface of the external firewall
        list = doc.getElementsByTagName("publicinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicInterface(node.getNodeValue());
        }
        
        // get publiczone from XML and set the public security zone of the external firewall
        list = doc.getElementsByTagName("publiczone");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPublicZone(node.getNodeValue());
        }
        
        // get timeout from XML and set the timeout (in seconds) for requests to the external firewall
        list = doc.getElementsByTagName("timeout");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTimeout(node.getNodeValue());
        }
        
        // get usageinterface from XML and set the usage interface of the external firewall
        list = doc.getElementsByTagName("usageinterface");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUsageInterface(node.getNodeValue());
        }
        
        // get username from XML and set the username that's used to log in to the external firewall
        list = doc.getElementsByTagName("username");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserName(node.getNodeValue());
        }
        
        // get zoneid from XML and set the zone ID of the external firewalls
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getNodeValue());
        }

        return response;
        
    }
    
}
