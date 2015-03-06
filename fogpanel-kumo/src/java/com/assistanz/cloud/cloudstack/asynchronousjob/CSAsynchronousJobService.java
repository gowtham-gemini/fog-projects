package com.assistanz.cloud.cloudstack.asynchronousjob;

import java.util.HashMap;
import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import org.apache.commons.httpclient.NameValuePair;

/**
 * 
 * @author Gowtham
 *
 */
public class CSAsynchronousJobService {
	
	private CloudStackServer server;
	 
	public CSAsynchronousJobService(CloudStackServer server) {
	        this.server = server;
	}
	
	/**
	 * Retrieves the current status of asynchronous job.
	 * 
	 * @param jobId The ID of the asychronous job
	 * @return
	 * @throws Exception
	 */
	public QueryAsynchronousJobResultResponse queryAsyncJobResult(String jobId)
			 throws Exception {
	        
	        LinkedList<NameValuePair> arguments = 
	                server.getDefaultQuery("queryAsyncJobResult", null);
	        arguments.add(new NameValuePair("jobid", jobId));
	        
	        Document responseDocument = server.makeRequest(arguments);
	
	        return getQueryAsynchronousJobResultResponse(responseDocument);
	}
	
	 /**
	  * Converts XML document into QueryAsynchronousJobResultResponse object
	  * 
	  * @param doc
	  * @return
	  */
	 private QueryAsynchronousJobResultResponse getQueryAsynchronousJobResultResponse(Document doc) {
		 QueryAsynchronousJobResultResponse response = new QueryAsynchronousJobResultResponse();
					
		// get accountid from XML and set as the account that executed the async command
	    NodeList list = doc.getElementsByTagName("accountid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setAccountId(node.getNodeValue());
	    }
	    
	    // get cmd from XML and set as the async command executed
	    list = doc.getElementsByTagName("cmd");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setCmd(node.getNodeValue());
	    }
	    
	    // get created from XML and set as the created date of the job
	    list = doc.getElementsByTagName("created");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setCreated(node.getNodeValue());
	    }
	    
	    // get jobinstanceid from XML and set the unique ID of the instance/entity object related to the job
	    list = doc.getElementsByTagName("jobinstanceid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobInstanceId(node.getNodeValue());
	    }
	    
	    // get jobinstancetype from XML and set as the instance/entity object related to the job
	    list = doc.getElementsByTagName("jobinstancetype");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobInstanceType(node.getNodeValue());
	    }
	    
	    // get jobprocstatus from XML and set as the progress information of the PENDING job
	    list = doc.getElementsByTagName("jobprocstatus");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobStatus(node.getNodeValue());
	    }
	    
	    // get jobresult from XML and set as the result reason
	    list = doc.getElementsByTagName("jobresult");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobResult(node.getNodeValue());
	    }
	    
	    // get jobresultcode from XML and set as the result code for the job
	    list = doc.getElementsByTagName("jobresultcode");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobResultCode(node.getNodeValue());
	    }
	    
	    // get jobresulttype from XML and set as the result type
	    list = doc.getElementsByTagName("jobresulttype");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobResultType(node.getNodeValue());
	    }
	    
	    // get jobstatus from XML and set the current job status-should be 0 for PENDING
	    list = doc.getElementsByTagName("jobstatus");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobStatus(node.getNodeValue());
	    }
	    
	    // get userid from XML and set as the user that executed the async command
	    list = doc.getElementsByTagName("userid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setUserId(node.getNodeValue());
	    }
	    
	    // get jobid from XML and set as the ID of the async job
	    list = doc.getElementsByTagName("jobid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobId(node.getNodeValue());
	    }
	    	    	    
	    return response;
	}
	
	/**
	 * Lists all pending asynchronous jobs for the account.
	 *  
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListAsynchronousJobsResponse queryAsyncJobResult(
			HashMap<String,String> optional)throws Exception {
	        
	        LinkedList<NameValuePair> arguments = 
	                server.getDefaultQuery("queryAsyncJobResult", optional);
	        	        
	        Document responseDocument = server.makeRequest(arguments);
	
	        return getListAsynchronousJobsResponse(responseDocument);
	 }
	
	 /**
	  * Converts XML document into ListAsynchronousJobsResponse object
	  * 
	  * @param doc
	  * @return
	  */
	 private ListAsynchronousJobsResponse getListAsynchronousJobsResponse(Document doc) {
		 ListAsynchronousJobsResponse response = new ListAsynchronousJobsResponse();
					
		// get accountid from XML and set as the account that executed the async command
	    NodeList list = doc.getElementsByTagName("accountid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setAccountId(node.getNodeValue());
	    }
	    
	    // get cmd from XML and set as the async command executed
	    list = doc.getElementsByTagName("cmd");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setCmd(node.getNodeValue());
	    }
	    
	    // get created from XML and set as the created date of the job
	    list = doc.getElementsByTagName("created");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setCreated(node.getNodeValue());
	    }
	    
	    // get jobinstanceid from XML and set the unique ID of the instance/entity object related to the job
	    list = doc.getElementsByTagName("jobinstanceid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobInstanceId(node.getNodeValue());
	    }
	    
	    // get jobinstancetype from XML and set as the instance/entity object related to the job
	    list = doc.getElementsByTagName("jobinstancetype");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobInstanceType(node.getNodeValue());
	    }
	    
	    // get jobprocstatus from XML and set as the progress information of the PENDING job
	    list = doc.getElementsByTagName("jobprocstatus");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobStatus(node.getNodeValue());
	    }
	    
	    // get jobresult from XML and set as the result reason
	    list = doc.getElementsByTagName("jobresult");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobResult(node.getNodeValue());
	    }
	    
	    // get jobresultcode from XML and set as the result code for the job
	    list = doc.getElementsByTagName("jobresultcode");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobResultCode(node.getNodeValue());
	    }
	    
	    // get jobresulttype from XML and set as the result type
	    list = doc.getElementsByTagName("jobresulttype");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobResultType(node.getNodeValue());
	    }
	    
	    // get jobstatus from XML and set the current job status-should be 0 for PENDING
	    list = doc.getElementsByTagName("jobstatus");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobStatus(node.getNodeValue());
	    }
	    
	    // get userid from XML and set as the user that executed the async command
	    list = doc.getElementsByTagName("userid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setUserId(node.getNodeValue());
	    }
	    
	    // get jobid from XML and set as the ID of the async job
	    list = doc.getElementsByTagName("jobid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobId(node.getNodeValue());
	    }
	    	    	    
	    return response;
	}

}
