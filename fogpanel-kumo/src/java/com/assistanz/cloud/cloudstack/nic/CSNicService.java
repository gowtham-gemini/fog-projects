package com.assistanz.cloud.cloudstack.nic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer; 
/**
 *
 * @author gowtham
 */
public class CSNicService {
    
    private CloudStackServer server;
    
    
    public CSNicService(CloudStackServer server) {
        this.server = server;
    }
    
    
    /**
     * Assigns secondary IP to NIC
     * 
     * @param nicid
     * @param optional
     * @return
     * @throws Exception 
     */
    public AddIpToNicResponse addIpToNic(String nicid, HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("addIpToNic", optional);
        arguments.add(new NameValuePair("nicid", nicid));
        
        Document responseDocument = server.makeRequest(arguments);

        return getAddIpToNicResponse(responseDocument);
    }
    
    
    /**
     * Converts XML document into AddIpToNicResponse object
     * 
     * @param doc
     * @return 
     */
    private AddIpToNicResponse getAddIpToNicResponse(Document doc) {
        AddIpToNicResponse response = new AddIpToNicResponse();
        
        // get id from XML and set as user ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }
        
        // get id from XML and set as user ID
        list = doc.getElementsByTagName("ipaddress");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIpaddress(node.getTextContent());
        }
        
        // get id from XML and set as user ID
        list = doc.getElementsByTagName("networkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkid(node.getTextContent());
        }
        
        // get id from XML and set as user ID
        list = doc.getElementsByTagName("nicid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNicid(node.getTextContent());
        }
        
        // get id from XML and set as user ID
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualmachineid(node.getTextContent());
        }
        
        // get id from XML and set as user ID
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        return response;
    }
    
    /**
     * list nic
     * 
     * @param virtualmachineid
     * @param optional
     * @return
     * @throws Exception 
     */
    public ListNicsResponse listNics(String virtualmachineid, HashMap<String,String> optional) throws Exception {

        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listNics", optional);
        arguments.add(new NameValuePair("virtualmachineid", virtualmachineid));

        Document responseDocument = server.makeRequest(arguments);

        return getListNicsResponse(responseDocument);
    }
    
    /**
     * Converts XML document into ListNicsResponse object
     * 
     * @param doc
     * @return 
     */
    private ListNicsResponse getListNicsResponse(Document doc) {
        ListNicsResponse response = new ListNicsResponse();
        
        NodeList list = doc.getElementsByTagName("nic");

        List<NicResponse> nicList = new LinkedList<NicResponse>();      

        
        if (list.getLength() > 0) {
            
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node nicNode = list.item(Index);

                if (nicNode == null) {
                    continue;
                } 
                
                NicResponse nic = new NicResponse();
                List<NicSecondaryIPResponse> secIps = new LinkedList<NicSecondaryIPResponse>(); 
                NodeList nicProperties = nicNode.getChildNodes();
                for (int childIndex = 0; childIndex < nicProperties.getLength(); childIndex++) {
                                  
                    Node property = nicProperties.item(childIndex);
                    
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                                       
                    if (property.getNodeName().equals("id")) {
                        nic.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcasturi")) {
                    	nic.setBroadcasturi(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                    	nic.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6address")) {
                    	nic.setIp6address(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6cidr")) {
                    	nic.setIp6cidr(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6gateway")) {
                    	nic.setIp6gateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                    	nic.setIpaddress(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                    	nic.setIsdefault(property.getTextContent());
                    } else if (property.getNodeName().equals("isolationuri")) {
                    	nic.setIsolationuri(property.getTextContent());
                    } else if (property.getNodeName().equals("macaddress")) {
                    	nic.setMacaddress(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                    	nic.setNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                    	nic.setNetworkid(property.getTextContent());
                    } else if (property.getNodeName().equals("networkname")) {
                    	nic.setNetworkname(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                    	nic.setTraffictype(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                    	nic.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("secondaryip")) {
                        NodeList nicSecondaryIpProperties =  property.getChildNodes();
                        if (nicSecondaryIpProperties.getLength() > 0) { 
                            NicSecondaryIPResponse nicSecondaryIP = new NicSecondaryIPResponse();

                            for (int secIPIndex = 0; secIPIndex < nicSecondaryIpProperties.getLength(); secIPIndex++) {
                                Node secIpProperty = nicSecondaryIpProperties.item(secIPIndex);
                                if (secIpProperty.getNodeName().equals("id")) {
                                    nicSecondaryIP.setSecIpId(secIpProperty.getTextContent());
                                } else if (secIpProperty.getNodeName().equals("ipaddress")) {
                                    nicSecondaryIP.setIpAddress(secIpProperty.getTextContent());
                                }
                            }
                            secIps.add(nicSecondaryIP);
                        }
                    }                                                 
                }
                nic.setSecondaryIp(secIps);
                nicList.add(nic);
            }
        }   
        response.setNic(nicList);
        return response;
    }
        
    /**
     * Remove secondary IP to NIC.
     * 
     * @param diskOfferingId
     * @return
     * @throws Exception 
     */
    public RemoveIpFromNicResponse removeIpFromNic(String id) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("removeIpFromNic", null);
        arguments.add(new NameValuePair("id", id));
       
        Document responseDocument = server.makeRequest(arguments);

        return getRemoveIpFromNicResponse(responseDocument);
    }
	
    /**
     * Converts XML document into RemoveIpFromNicResponse object
     * 
     * @param doc
     * @return 
     */
    private RemoveIpFromNicResponse getRemoveIpFromNicResponse(Document doc) {
    	RemoveIpFromNicResponse response = new RemoveIpFromNicResponse();

        // get displaytext from XML and set Any text associated with the success or 
        // failure on Deleting Disk Offering
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        // get success from XML and set Return true if Deleting Disk Offering operation 
        // is executed successfully
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        // get id from XML and set as user ID
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        return response;
    }
    
    /**
     * Retrieves the current status of asynchronous job for Add Ip.
     * 
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public AddIpJobResultResponse addIpJobResult(String asychronousJobid) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("queryAsyncJobResult", null);
            arguments.add(new NameValuePair("jobid",  asychronousJobid));

            Document responseDocument = server.makeRequest(arguments);

            return getIpJobResultResponse(responseDocument);
    }
    
     /**
     * Converts XML document into AddIpJobResultResponse object
     * 
     * @param doc
     * @return 
     */
    private AddIpJobResultResponse getIpJobResultResponse(Document doc) {
    	AddIpJobResultResponse response = new AddIpJobResultResponse();
        
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
                if (nodeProperty.getNodeName().equals("nicsecondaryip")) {
                    NodeList childNodeProperties =  nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setSecondaryIpId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ipaddress")) {
                            response.setSecondaryIp(childNodeProperty.getTextContent());
                        } 
                    }
                }
            }
        
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
