package com.assistanz.cloud.cloudstack.nic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

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
            response.setAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCommand(node.getTextContent());                   
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobProgressStatus(node.getTextContent());
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
            response.setJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        return response;
    }
    
    /**
     * Assigns secondary IP to NIC
     *
     * @param nicid
     * @param optional
     * @return
     * @throws Exception
     */
    public AddIpToNicResponse addIpToNic(String nicid, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addIpToNic", optional);
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
            response.setNetworkId(node.getTextContent());
        }

        // get id from XML and set as user ID
        list = doc.getElementsByTagName("nicid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNicId(node.getTextContent());
        }
        
        // get id from XML and set as user ID
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get id from XML and set as user ID
        list = doc.getElementsByTagName("virtualmachineid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVirtualMachineId(node.getTextContent());
        }

        return response;
    }

    /**
     * Remove secondary IP to NIC.
     *
     * @param secondaryIpId
     * @return
     * @throws Exception
     */
    public RemoveIpFromNicResponse removeIpFromNic(String secondaryIpId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("removeIpFromNic", null);
        arguments.add(new NameValuePair("id", secondaryIpId));

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
     * list the VM NICS IP to NIC
     *
     * @param virtualMachineId
     * @param optional
     * @return
     * @throws Exception
     */
    public ListNicsResponse listNics(String virtualMachineId, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listNics", optional);
        arguments.add(new NameValuePair("virtualmachineid", virtualMachineId));

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
            for (int index = 0; index < list.getLength(); index++) {
                Node nicNode = list.item(index);
                NicResponse nic = new NicResponse();
                List<NicSecondaryIPResponse> secIps = new LinkedList<NicSecondaryIPResponse>(); 
                NodeList nicProperties = nicNode.getChildNodes();
                for (int childIndex = 0; childIndex < nicProperties.getLength(); childIndex++) {
                    Node property = nicProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        nic.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("broadcasturi")) {
                        nic.setBroadcastUri(property.getTextContent());
                    } else if (property.getNodeName().equals("gateway")) {
                        nic.setGateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6address")) {
                        nic.setIp6Address(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6cidr")) {
                        nic.setIp6Cidr(property.getTextContent());
                    } else if (property.getNodeName().equals("ip6gateway")) {
                        nic.setIp6Gateway(property.getTextContent());
                    } else if (property.getNodeName().equals("ipaddress")) {
                        nic.setIpAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("isdefault")) {
                        nic.setIsDefault(property.getTextContent());
                    } else if (property.getNodeName().equals("isolationuri")) {
                        nic.setIsolationUri(property.getTextContent());
                    } else if (property.getNodeName().equals("macaddress")) {
                        nic.setMacAddress(property.getTextContent());
                    } else if (property.getNodeName().equals("netmask")) {
                        nic.setNetmask(property.getTextContent());
                    } else if (property.getNodeName().equals("networkid")) {
                        nic.setNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("networkname")) {
                        nic.setNetworkName(property.getTextContent());
                    } else if (property.getNodeName().equals("traffictype")) {
                        nic.setTrafficType(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        nic.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("secondaryip")) {
                        NodeList nicSecondaryIpProperties =  property.getChildNodes();
                        if (nicSecondaryIpProperties.getLength() > 0) { 
                            NicSecondaryIPResponse nicSecondaryIP = new NicSecondaryIPResponse();

                            for (int secIPIndex = 0; secIPIndex < nicSecondaryIpProperties.getLength(); secIPIndex++) {
                                Node secIpProperty = nicSecondaryIpProperties.item(secIPIndex);
                                if (secIpProperty.getNodeName().equals("id")) {
                                    nicSecondaryIP.setId(secIpProperty.getTextContent());
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
     * Retrieves the current status of asynchronous job for Add Ip.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public AddIpToNicJobResultResponse addIpToNicJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getAddIpToNicJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into AddIpToNicJobResultResponse object
     *
     * @param doc
     * @return
     */
    private AddIpToNicJobResultResponse getAddIpToNicJobResultResponse(Document doc) {
        AddIpToNicJobResultResponse response = new AddIpToNicJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("nicsecondaryip")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ipaddress")) {
                            response.setIpAddress(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("networkid")) {
                            response.setNetworkId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("nicid")) {
                            response.setNicId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("virtualmachineid")) {
                            response.setVirtualMachineId(childNodeProperty.getTextContent());
                        }
                    }
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
}
