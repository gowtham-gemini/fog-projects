package com.assistanz.cloud.cloudstack.asyncjob;

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
public class CSAsyncJobService {
	
	private CloudStackServer server;
	
	public CSAsyncJobService(CloudStackServer server) {
		this.server = server;
	}
	
    /**
     * Retrieves the current status of asynchronous job.
     * 
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public QueryAsyncJobResultResponse queryAsyncJobResult(String asychronousJobid) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("queryAsyncJobResult", null);
            arguments.add(new NameValuePair("jobid",  asychronousJobid));

            Document responseDocument = server.makeRequest(arguments);

            return getQueryAsyncJobResultResponse(responseDocument);
    }
	
    /**
     * Converts XML document into QueryAsyncJobResultResponse object
     * 
     * @param doc
     * @return
     */
    private QueryAsyncJobResultResponse getQueryAsyncJobResultResponse(Document doc) {
            QueryAsyncJobResultResponse response = new QueryAsyncJobResultResponse();

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
            
            list = doc.getElementsByTagName("id");
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setId(node.getTextContent());
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
                    if(nodeProperty.getNodeName().equals("errorcode")) {
                        response.setErrorCode(nodeProperty.getTextContent());
                    } else if(nodeProperty.getNodeName().equals("errortext")) {
                        response.setErrorText(nodeProperty.getTextContent());
                    }

                }
            }
            
            
            if (list.getLength() > 0) {
                Node node = list.item(0);
                response.setAsychronousJobResult(node.getTextContent());
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
	
	/**
	 * Lists all pending asynchronous jobs for the account
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListAsyncJobsResponse listAsyncJobs(HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("queryAsyncJobResult", optional);
						
		Document responseDocument = server.makeRequest(arguments);
		
		return getListAsyncJobsResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListAsyncJobsResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListAsyncJobsResponse getListAsyncJobsResponse(Document doc) {
		ListAsyncJobsResponse response = new ListAsyncJobsResponse();

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
		    Node node = list.item(0);
		    response.setAsychronousJobResult(node.getTextContent());
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
