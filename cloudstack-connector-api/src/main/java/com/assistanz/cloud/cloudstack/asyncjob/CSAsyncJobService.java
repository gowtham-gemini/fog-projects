package com.assistanz.cloud.cloudstack.asyncjob;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

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
            response.setAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCmd(node.getTextContent());
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
            response.setJobProcStatus(node.getTextContent());
        }

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobResult(node.getTextContent());
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
        list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }
        // get errorcode from XML and set as the ID of the async job
        list = doc.getElementsByTagName("errorcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setErrorCode(node.getTextContent());
        }
        
        // get errortext from XML and set as the ID of the async job
        list = doc.getElementsByTagName("errortext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setErrorText(node.getTextContent());
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
    public ListAsyncJobsResponse listAsyncJobs(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", optional);

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

        NodeList list = doc.getElementsByTagName("ayncjob");

        List<AsyncJobResponse> asyncJobs = new LinkedList<AsyncJobResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node ayncJobNode = list.item(index);

                AsyncJobResponse asyncJob = new AsyncJobResponse();
                NodeList ayncJobProperties = ayncJobNode.getChildNodes();
                for (int childIndex = 0; childIndex < ayncJobProperties.getLength(); childIndex++) {
                    Node property = ayncJobProperties.item(childIndex);

                    if (property.getNodeName().equals("accountid")) {
                        asyncJob.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("cmd")) {
                        asyncJob.setCmd(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                        asyncJob.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("jobinstanceid")) {
                        asyncJob.setJobInstanceId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobinstancetype")) {
                        asyncJob.setJobInstanceType(property.getTextContent());
                    } else if (property.getNodeName().equals("jobprocstatus")) {
                        asyncJob.setJobProcStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("jobresult")) {
                        asyncJob.setJobResult(property.getTextContent());
                    } else if (property.getNodeName().equals("jobresultcode")) {
                        asyncJob.setJobResultCode(property.getTextContent());
                    } else if (property.getNodeName().equals("jobresulttype")) {
                        asyncJob.setJobResultType(property.getTextContent());
                    } else if (property.getNodeName().equals("jobstatus")) {
                        asyncJob.setJobStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("userid")) {
                        asyncJob.setUserId(property.getTextContent());
                    } else if (property.getNodeName().equals("jobid")) {
                        asyncJob.setJobId(property.getTextContent());
                    }
                }
                asyncJobs.add(asyncJob);
            }
        }
        response.setAsyncJobs(asyncJobs);
        return response;
    }

}
