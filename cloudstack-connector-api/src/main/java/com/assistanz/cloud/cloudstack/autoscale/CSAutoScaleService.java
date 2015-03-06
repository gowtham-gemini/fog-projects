package com.assistanz.cloud.cloudstack.autoscale;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.TagsResponse;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Santhosh
 *
 */
public class CSAutoScaleService {

    private CloudStackServer server;

    public CSAutoScaleService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds metric counter
     *
     * @param counterName Name of the counter
     * @param counterSource Source of the counter
     * @param counterValue Values of the counter
     * @return
     * @throws Exception
     */
    public CreateCounterResponse createCounter(String counterName,
            String counterSource, String counterValue)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createCounter", null);
        arguments.add(new NameValuePair("name", counterName));
        arguments.add(new NameValuePair("source", counterSource));
        arguments.add(new NameValuePair("value", counterValue));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateCounterResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateCounterResponse object
     *
     * @param doc
     * @return
     */
    private CreateCounterResponse getCreateCounterResponse(Document doc) {
        CreateCounterResponse response = new CreateCounterResponse();

        // get Id from XML and set the id of the Counter    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get name from XML and set the Name of the counter   
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get source from XML and set the Source of the counter  
        list = doc.getElementsByTagName("source");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSource(node.getTextContent());
        }

        // get value from XML and set the value in case of snmp or other specific counters
        list = doc.getElementsByTagName("value");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setValue(node.getTextContent());
        }

        // get zoneid from XML and set the zone id of counter
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }
        return response;
    }

    /**
     * Creates a condition
     *
     * @param counterId ID of the counter
     * @param relationalOperator Relational Operator to be used with threshold
     * @param thresholdValue Threshold value
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateConditionResponse createCondition(String counterId, String relationalOperator,
            String thresholdValue, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createCondition", optional);
        arguments.add(new NameValuePair("counterid", counterId));
        arguments.add(new NameValuePair("relationaloperator", relationalOperator));
        arguments.add(new NameValuePair("threshold", thresholdValue));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateConditionResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateConditionResponse object
     *
     * @param doc
     * @return
     */
    private CreateConditionResponse getCreateConditionResponse(Document doc) {
        CreateConditionResponse response = new CreateConditionResponse();

        // get Id from XML and set the id of the Condition    
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the owner of the Condition  
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get counter from XML and set the Details of the counter  
        list = doc.getElementsByTagName("counter");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCounter(node.getTextContent());
        }

        // get domain from XML and set the domain name of the owner
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domain id of the owner
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get project from XML and set the project name of the owner
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get project id from XML and set the project id of the owner
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get relational operator from XML and set Relational Operator to be used with threshold
        list = doc.getElementsByTagName("relationaloperator");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRelationalOperator(node.getTextContent());
        }

        // get threshold from XML and set Threshold Value for the counter
        list = doc.getElementsByTagName("threshold");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setThreshold(node.getTextContent());
        }

        // get zoneid from XML and set the zone id of counter
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }
        return response;
    }

    /**
     * Creates an Autoscale policy for a provision or deprovision action
     *
     * @param action the action to be executed if all the conditions evaluate to true for the specified duration
     * @param conditionIds the list of IDs of the conditions that are being evaluated on every interval
     * @param duration the duration for which the conditions have to be true before action is taken
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateAutoScalePolicyResponse createAutoScalePolicy(String action, String conditionIds,
            String duration, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createAutoScalePolicy", optional);
        arguments.add(new NameValuePair("action", action));
        arguments.add(new NameValuePair("conditionIds", conditionIds));
        arguments.add(new NameValuePair("duration", duration));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateAutoScalePolicyResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateAutoScalePolicyResponse object
     *
     * @param doc
     * @return
     */
    private CreateAutoScalePolicyResponse getCreateAutoScalePolicyResponse(Document doc) {
        CreateAutoScalePolicyResponse response = new CreateAutoScalePolicyResponse();

        // get Id from XML and set the autoscale policy ID  
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account owning the autoscale policy  
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get action from XML and set the action to be executed in the specified duration  
        list = doc.getElementsByTagName("action");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAction(node.getTextContent());
        }

        // get conditions from XML and set the list of IDs of the conditions that are being evaluated on every interval  
        list = doc.getElementsByTagName("conditions");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setConditions(node.getTextContent());
        }

        // get domain from XML and set the domain name of the autoscale policy
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domain ID of the autoscale policy
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get duration from XML and set the duration for which the conditions have to be true before action is taken
        list = doc.getElementsByTagName("duration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDuration(node.getTextContent());
        }

        // get project from XML and set the project name of the autoscale policy
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get project id from XML and set the project id of autoscale policy
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get quiettime from XML and set the cool down period for the policy 
        list = doc.getElementsByTagName("quiettime");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setQuietTime(node.getTextContent());
        }
        return response;
    }

    /**
     * Creates a profile that contains information about the virtual machine
     *
     * @param serviceOfferingId the service offering of the auto deployed virtual machine
     * @param templateId the template of the auto deployed virtual machine
     * @param zoneId availability zone for the auto deployed virtual machine
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateAutoScaleVmProfileResponse createAutoScaleVmProfile(String serviceOfferingId, String templateId,
            String zoneId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createAutoScaleVmPolicy", optional);
        arguments.add(new NameValuePair("serviceofferingid", serviceOfferingId));
        arguments.add(new NameValuePair("templatedid", templateId));
        arguments.add(new NameValuePair("zoneid", zoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateAutoScaleVmProfileResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateAutoScaleVmProfileResponse object
     *
     * @param doc
     * @return
     */
    private CreateAutoScaleVmProfileResponse getCreateAutoScaleVmProfileResponse(Document doc) {
        CreateAutoScaleVmProfileResponse response = new CreateAutoScaleVmProfileResponse();

        // get Id from XML and set the autoscale vm profile ID  
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account owning the instance group 
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get autoscaleuserid from XML and set the ID of the user used to launch and destroy the VMs  
        list = doc.getElementsByTagName("autoscaleuserid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAutoScaleUserId(node.getTextContent());
        }

        // get conditions from XML and set the list of IDs of the time to get closed before a vm is destroyed  
        list = doc.getElementsByTagName("destroyvmgraceperiod");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestroyVmGracePeriod(node.getTextContent());
        }

        // get domain from XML and set the domain name of the vm profile
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get the domain ID of the vm profile
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get otherdeployparams from XML and parameters other than zoneId/serviceOfferringId/templateId
        list = doc.getElementsByTagName("otherdeployparams");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOtherDeployParams(node.getTextContent());
        }

        // get project from XML and set the project name of the vm profile
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get project id from XML and set the project id of the vm profile
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get serviceofferingid from XML and set the service offering to be used while deploying a virtual machine
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get templateid from XML and set the template to be used while deploying a virtual machine
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get zoneid from XML and set the availability zone to be used while deploying a virtual machine
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * Creates and automatically starts a virtual machine based on a service offering, disk offering, and template
     *
     * @param lbRuleId the ID of the load balancer rule
     * @param maxMembers the maximum number of members in the vmgroup
     * @param minMembers the minimum number of members in the vmgroup
     * @param scaleDownPolicyIds list of scaledown autoscale policies
     * @param scaleUpPolicyIds list of scaleup autoscale policies
     * @param vmProfileId the autoscale profile that contains information about the vms in the vm group
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateAutoScaleVmGroupResponse createAutoScaleVmGroup(String lbRuleId, String maxMembers,
            String minMembers, String scaleDownPolicyIds, String scaleUpPolicyIds, String vmProfileId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createAutoScaleVmGroup", optional);
        arguments.add(new NameValuePair("lbruleid", lbRuleId));
        arguments.add(new NameValuePair("maxmembers", maxMembers));
        arguments.add(new NameValuePair("minmembers", minMembers));
        arguments.add(new NameValuePair("scaledownpolicyids", scaleDownPolicyIds));
        arguments.add(new NameValuePair("scaleuppolicyids", scaleUpPolicyIds));
        arguments.add(new NameValuePair("vmprofileid", vmProfileId));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateAutoScaleVmGroupResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateAutoScaleVmGroupResponse object
     *
     * @param doc
     * @return
     */
    private CreateAutoScaleVmGroupResponse getCreateAutoScaleVmGroupResponse(Document doc) {
        CreateAutoScaleVmGroupResponse response = new CreateAutoScaleVmGroupResponse();

        // get Id from XML and set the autoscale vm group ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account owning the instance group 
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get domain from XML and set the domain name of the vm profile
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domain id from XML and set the domain ID of the vm profile
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get interval from XML and set the frequency at which the conditions have to be evaluated  
        list = doc.getElementsByTagName("interval");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInterval(node.getTextContent());
        }

        // get lbruleid from XML and set the load balancer rule ID  
        list = doc.getElementsByTagName("lbruleid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLbRuleId(node.getTextContent());
        }

        // get maxmembers from XML and set the maximum number of members in the vmgroup
        list = doc.getElementsByTagName("maxmembers");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxMembers(node.getTextContent());
        }

        // get minmembers from XML and set the minimum number of members in the vmgroup
        list = doc.getElementsByTagName("minmembers");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMinMembers(node.getTextContent());
        }

        // get project from XML and set the project name of the vm profile
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get project id from XML and set the project id of the vm profile
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get scaledownpolicies from XML and set the list of scaledown autoscale policies
        list = doc.getElementsByTagName("scaledownpolicies");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScaleDownPolicies(node.getTextContent());
        }

        // get scaleuppolicies from XML and set the list of scaleup autoscale policies
        list = doc.getElementsByTagName("scaleuppolicies");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScaleUpPolicies(node.getTextContent());
        }

        // get state from XML and set the current state of the AutoScale Vm Group
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get vmprofileid from XML and set the autoscale profile that contains information about the vms in the vm group
        list = doc.getElementsByTagName("vmprofileid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmProfileId(node.getTextContent());
        }

        return response;
    }

    /**
     * Deletes a counter
     *
     * @param counterId the ID of the counter
     * @return
     * @throws Exception
     */
    public DeleteCounterResponse deleteCounter(String counterId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteCounter", null);
        arguments.add(new NameValuePair("id", counterId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteCounterResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteCounterResponse object
     *
     * @param doc
     * @return
     */
    private DeleteCounterResponse getDeleteCounterResponse(Document doc) {
        DeleteCounterResponse response = new DeleteCounterResponse();

        // get displaytext from XML and set any text associated with the success or failure 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Removes a condition
     *
     * @param conditionId the ID of the counter
     * @return
     * @throws Exception
     */
    public DeleteConditionResponse deleteCondition(String conditionId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteCondition", null);
        arguments.add(new NameValuePair("id", conditionId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteConditionResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteConditionResponse object
     *
     * @param doc
     * @return
     */
    private DeleteConditionResponse getDeleteConditionResponse(Document doc) {
        DeleteConditionResponse response = new DeleteConditionResponse();

        // get displaytext from XML and set any text associated with the success or failure
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure  
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Deletes a autoscale policy
     *
     * @param autoScalePolicyId the ID of the autoscale policy
     * @return
     * @throws Exception
     */
    public DeleteAutoScalePolicyResponse deleteAutoScalePolicy(String autoScalePolicyId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteAutoScalePolicy", null);
        arguments.add(new NameValuePair("id", autoScalePolicyId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteAutoScalePolicyResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteAutoScalePolicyResponse object
     *
     * @param doc
     * @return
     */
    private DeleteAutoScalePolicyResponse getDeleteAutoScalePolicyResponse(Document doc) {
        DeleteAutoScalePolicyResponse response = new DeleteAutoScalePolicyResponse();

        // get displaytext from XML and set any text associated with the success or failure 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure  
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Deletes a autoscale vm profile
     *
     * @param autoScaleProfileId the ID of the autoscale policy
     * @return
     * @throws Exception
     */
    public DeleteAutoScaleVmProfileResponse deleteAutoScaleVmProfile(String autoScaleProfileId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteAutoScaleVmProfile", null);
        arguments.add(new NameValuePair("id", autoScaleProfileId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteAutoScaleVmProfileResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteAutoScaleVmProfileResponse object
     *
     * @param doc
     * @return
     */
    private DeleteAutoScaleVmProfileResponse getDeleteAutoScaleVmProfileResponse(Document doc) {
        DeleteAutoScaleVmProfileResponse response = new DeleteAutoScaleVmProfileResponse();

        // get displaytext from XML and set any text associated with the success or failure 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure  
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Deletes a autoscale vm group
     *
     * @param autoScaleGroupId the ID of the autoscale group
     * @return
     * @throws Exception
     */
    public DeleteAutoScaleVmGroupResponse deleteAutoScaleVmGroup(String autoScaleGroupId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteAutoScaleVmGroup", null);
        arguments.add(new NameValuePair("id", autoScaleGroupId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteAutoScaleVmGroupResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteAutoScaleVmGroupResponse object
     *
     * @param doc
     * @return
     */
    private DeleteAutoScaleVmGroupResponse getDeleteAutoScaleVmGroupResponse(Document doc) {
        DeleteAutoScaleVmGroupResponse response = new DeleteAutoScaleVmGroupResponse();

        // get displaytext from XML and set any text associated with the success or failure 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure  
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * List the counters.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListCountersResponse listCounters(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listCounters", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListCountersResponse(responseDocument);
    }

    /**
     * Converts XML document into ListCountersResponse object
     *
     * @param doc
     * @return
     */
    private ListCountersResponse getListCountersResponse(Document doc) {
        ListCountersResponse response = new ListCountersResponse();

        // List the counters
        NodeList list = doc.getElementsByTagName("counter");

        List<CounterResponse> counters = new LinkedList<CounterResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node counterNode = list.item(index);

                CounterResponse counter = new CounterResponse();

                NodeList counterProperties = counterNode.getChildNodes();
                for (int childIndex = 0; childIndex < counterProperties.getLength(); childIndex++) {
                    Node property = counterProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        counter.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        counter.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("source")) {
                        counter.setSource(property.getTextContent());
                    } else if (property.getNodeName().equals("value")) {
                        counter.setValue(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        counter.setZoneId(property.getTextContent());
                    }
                }
                counters.add(counter);
            }
        }

        response.setCounters(counters);
        return response;
    }

    /**
     * List Conditions for the specific user.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListConditionsResponse listConditions(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listConditions", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListConditionsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListConditionsResponse object
     *
     * @param doc
     * @return
     */
    private ListConditionsResponse getListConditionsResponse(Document doc) {
        ListConditionsResponse response = new ListConditionsResponse();

        // List Conditions for the specific user
        NodeList list = doc.getElementsByTagName("condition");

        List<ConditionResponse> conditions = new LinkedList<ConditionResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node conditionNode = list.item(index);

                ConditionResponse condition = new ConditionResponse();

                NodeList conditionProperties = conditionNode.getChildNodes();
                for (int childIndex = 0; childIndex < conditionProperties.getLength(); childIndex++) {
                    Node property = conditionProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        condition.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        condition.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("counter")) {
                        condition.setCounter(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        condition.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        condition.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        condition.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        condition.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("relationaloperator")) {
                        condition.setRelationalOperator(property.getTextContent());
                    } else if (property.getNodeName().equals("threshold")) {
                        condition.setThreshold(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        condition.setZoneId(property.getTextContent());
                    }
                }
                conditions.add(condition);
            }
        }

        response.setConditions(conditions);
        return response;
    }

    /**
     * Lists autoscale policies.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListAutoScalePoliciesResponse listAutoScalePolicies(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listAutoScalePolicies", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListAutoScalePoliciesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListAutoScalePoliciesResponse object
     *
     * @param doc
     * @return
     */
    private ListAutoScalePoliciesResponse getListAutoScalePoliciesResponse(Document doc) {
        ListAutoScalePoliciesResponse response = new ListAutoScalePoliciesResponse();

        // Lists autoscale policies
        NodeList list = doc.getElementsByTagName("autoscalepolicy");

        List<AutoScalePolicyResponse> autoScalePolicies = new LinkedList<AutoScalePolicyResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node autoScalePolicyNode = list.item(index);

                AutoScalePolicyResponse autoScalePolicy = new AutoScalePolicyResponse();

                NodeList autoScalePolicyProperties = autoScalePolicyNode.getChildNodes();
                for (int childIndex = 0; childIndex < autoScalePolicyProperties.getLength(); childIndex++) {
                    Node property = autoScalePolicyProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        autoScalePolicy.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        autoScalePolicy.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("action")) {
                        autoScalePolicy.setAction(property.getTextContent());
                    } else if (property.getNodeName().equals("conditions")) {
                        autoScalePolicy.setConditions(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        autoScalePolicy.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        autoScalePolicy.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("duration")) {
                        autoScalePolicy.setDuration(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        autoScalePolicy.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        autoScalePolicy.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("quiettime")) {
                        autoScalePolicy.setQuietTime(property.getTextContent());
                    }
                }
                autoScalePolicies.add(autoScalePolicy);
            }
        }

        response.setAutoScalePolicies(autoScalePolicies);
        return response;
    }

    /**
     * Lists autoscale vm profiles.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListAutoScaleVmProfilesResponse listAutoScaleVmProfiles(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listAutoScaleVmProfiles", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListAutoScaleVmProfilesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListAutoScaleVmProfilesResponse object
     *
     * @param doc
     * @return
     */
    private ListAutoScaleVmProfilesResponse getListAutoScaleVmProfilesResponse(Document doc) {
        ListAutoScaleVmProfilesResponse response = new ListAutoScaleVmProfilesResponse();

        // Lists autoscale vm profiles
        NodeList list = doc.getElementsByTagName("autoscalevmprofile");

        List<AutoScaleVmProfileResponse> autoScaleVmProfiles = new LinkedList<AutoScaleVmProfileResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node autoScaleVmProfileNode = list.item(index);

                AutoScaleVmProfileResponse autoScaleVmProfile = new AutoScaleVmProfileResponse();

                NodeList autoScaleVmProfileProperties = autoScaleVmProfileNode.getChildNodes();
                for (int childIndex = 0; childIndex < autoScaleVmProfileProperties.getLength(); childIndex++) {
                    Node property = autoScaleVmProfileProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        autoScaleVmProfile.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        autoScaleVmProfile.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("autoscaleuserid")) {
                        autoScaleVmProfile.setAutoScaleUserId(property.getTextContent());
                    } else if (property.getNodeName().equals("destroyvmgraceperiod")) {
                        autoScaleVmProfile.setDestroyVmGracePeriod(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        autoScaleVmProfile.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        autoScaleVmProfile.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("otherdeployparams")) {
                        autoScaleVmProfile.setOtherDeployParams(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        autoScaleVmProfile.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        autoScaleVmProfile.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("serviceofferingid")) {
                        autoScaleVmProfile.setServiceOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("templateid")) {
                        autoScaleVmProfile.setTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        autoScaleVmProfile.setZoneId(property.getTextContent());
                    }
                }
                autoScaleVmProfiles.add(autoScaleVmProfile);
            }
        }

        response.setAutoScaleVmProfiles(autoScaleVmProfiles);
        return response;
    }

    /**
     * Lists autoscale vm groups.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListAutoScaleVmGroupsResponse listAutoScaleVmGroups(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listAutoScaleVmGroups", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListAutoScaleVmGroupsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListAutoScaleVmGroupsResponse object
     *
     * @param doc
     * @return
     */
    private ListAutoScaleVmGroupsResponse getListAutoScaleVmGroupsResponse(Document doc) {
        ListAutoScaleVmGroupsResponse response = new ListAutoScaleVmGroupsResponse();

        // Lists autoscale vm groups
        NodeList list = doc.getElementsByTagName("autoscalevmgroup");

        List<AutoScaleVmGroupResponse> autoScaleVmGroups = new LinkedList<AutoScaleVmGroupResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node autoScaleVmGroupNode = list.item(index);

                AutoScaleVmGroupResponse autoScaleVmGroup = new AutoScaleVmGroupResponse();

                NodeList autoScaleVmGroupProperties = autoScaleVmGroupNode.getChildNodes();
                for (int childIndex = 0; childIndex < autoScaleVmGroupProperties.getLength(); childIndex++) {
                    Node property = autoScaleVmGroupProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        autoScaleVmGroup.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                        autoScaleVmGroup.setAccount(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                        autoScaleVmGroup.setDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                        autoScaleVmGroup.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("interval")) {
                        autoScaleVmGroup.setInterval(property.getTextContent());
                    } else if (property.getNodeName().equals("lbruleid")) {
                        autoScaleVmGroup.setLbRuleId(property.getTextContent());
                    } else if (property.getNodeName().equals("maxmembers")) {
                        autoScaleVmGroup.setMaxMembers(property.getTextContent());
                    } else if (property.getNodeName().equals("minmembers")) {
                        autoScaleVmGroup.setMinMembers(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                        autoScaleVmGroup.setProject(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                        autoScaleVmGroup.setProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("scaledownpolicies")) {
                        autoScaleVmGroup.setScaleDownPolicies(property.getTextContent());
                    } else if (property.getNodeName().equals("scaleuppolicies")) {
                        autoScaleVmGroup.setScaleUpPolicies(property.getTextContent());
                    }
                }
                autoScaleVmGroups.add(autoScaleVmGroup);
            }
        }

        response.setAutoScaleVmGroups(autoScaleVmGroups);
        return response;
    }

    /**
     * Enables an AutoScale Vm Group
     *
     * @param autoScaleGroupId the ID of the autoscale group
     * @return
     * @throws Exception
     */
    public EnableAutoScaleVmGroupResponse enableAutoScaleVmGroup(String autoScaleGroupId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("enableAutoScaleVmGroup", null);
        arguments.add(new NameValuePair("id", autoScaleGroupId));

        Document responseDocument = server.makeRequest(arguments);

        return getEnableAutoScaleVmGroupResponse(responseDocument);
    }

    /**
     * Converts XML document into EnableAutoScaleVmGroupResponse object
     *
     * @param doc
     * @return
     */
    private EnableAutoScaleVmGroupResponse getEnableAutoScaleVmGroupResponse(Document doc) {
        EnableAutoScaleVmGroupResponse response = new EnableAutoScaleVmGroupResponse();

        // get Id from XML and set the autoscale vm group ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account owning the instance group 
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get domain from XML and set the domain name of the vm profile
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domain id from XML and set the domain ID of the vm profile
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get interval from XML and set the frequency at which the conditions have to be evaluated  
        list = doc.getElementsByTagName("interval");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInterval(node.getTextContent());
        }

        // get lbruleid from XML and set the load balancer rule ID  
        list = doc.getElementsByTagName("lbruleid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLbRuleId(node.getTextContent());
        }

        // get maxmembers from XML and set the maximum number of members in the vmgroup
        list = doc.getElementsByTagName("maxmembers");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxMembers(node.getTextContent());
        }

        // get minmembers from XML and set the minimum number of members in the vmgroup
        list = doc.getElementsByTagName("minmembers");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMinMembers(node.getTextContent());
        }

        // get project from XML and set the project name of the vm profile
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get project id from XML and set the project id of the vm profile
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get scaledownpolicies from XML and set the list of scaledown autoscale policies
        list = doc.getElementsByTagName("scaledownpolicies");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScaleDownPolicies(node.getTextContent());
        }

        // get scaleuppolicies from XML and set the list of scaleup autoscale policies
        list = doc.getElementsByTagName("scaleuppolicies");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScaleUpPolicies(node.getTextContent());
        }

        // get state from XML and set the current state of the AutoScale Vm Group
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get vmprofileid from XML and set the autoscale profile that contains information about the vms in the vm group
        list = doc.getElementsByTagName("vmprofileid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmProfileId(node.getTextContent());
        }

        return response;
    }

    /**
     * Disables an AutoScale Vm Group
     *
     * @param autoScaleGroupId the ID of the autoscale group
     * @return
     * @throws Exception
     */
    public DisableAutoScaleVmGroupResponse disableAutoScaleVmGroup(String autoScaleGroupId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("disableAutoScaleVmGroup", null);
        arguments.add(new NameValuePair("id", autoScaleGroupId));

        Document responseDocument = server.makeRequest(arguments);

        return getDisableAutoScaleVmGroupResponse(responseDocument);
    }

    /**
     * Converts XML document into DisableAutoScaleVmGroupResponse object
     *
     * @param doc
     * @return
     */
    private DisableAutoScaleVmGroupResponse getDisableAutoScaleVmGroupResponse(Document doc) {
        DisableAutoScaleVmGroupResponse response = new DisableAutoScaleVmGroupResponse();

        // get Id from XML and set the autoscale vm group ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account owning the instance group 
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get domain from XML and set the domain name of the vm profile
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domain id from XML and set the domain ID of the vm profile
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get interval from XML and set the frequency at which the conditions have to be evaluated  
        list = doc.getElementsByTagName("interval");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInterval(node.getTextContent());
        }

        // get lbruleid from XML and set the load balancer rule ID  
        list = doc.getElementsByTagName("lbruleid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLbRuleId(node.getTextContent());
        }

        // get maxmembers from XML and set the maximum number of members in the vmgroup
        list = doc.getElementsByTagName("maxmembers");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxMembers(node.getTextContent());
        }

        // get minmembers from XML and set the minimum number of members in the vmgroup
        list = doc.getElementsByTagName("minmembers");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMinMembers(node.getTextContent());
        }

        // get project from XML and set the project name of the vm profile
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get project id from XML and set the project id of the vm profile
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get scaledownpolicies from XML and set the list of scaledown autoscale policies
        list = doc.getElementsByTagName("scaledownpolicies");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScaleDownPolicies(node.getTextContent());
        }

        // get scaleuppolicies from XML and set the list of scaleup autoscale policies
        list = doc.getElementsByTagName("scaleuppolicies");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScaleUpPolicies(node.getTextContent());
        }

        // get state from XML and set the current state of the AutoScale Vm Group
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get vmprofileid from XML and set the autoscale profile that contains information about the vms in the vm group
        list = doc.getElementsByTagName("vmprofileid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmProfileId(node.getTextContent());
        }

        return response;
    }

    /**
     * Updates an existing autoscale policy
     *
     * @param autoScalePolicyId the ID of the autoscale policy
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateAutoScalePolicyResponse updateAutoScalePolicy(String autoScalePolicyId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateAutoScalePolicy", optional);
        arguments.add(new NameValuePair("id", autoScalePolicyId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateAutoScalePolicyResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateAutoScalePolicyResponse object
     *
     * @param doc
     * @return
     */
    private UpdateAutoScalePolicyResponse getUpdateAutoScalePolicyResponse(Document doc) {
        UpdateAutoScalePolicyResponse response = new UpdateAutoScalePolicyResponse();

        // get Id from XML and set the autoscale policy ID  
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account owning the autoscale policy  
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get action from XML and set the action to be executed in the specified duration  
        list = doc.getElementsByTagName("action");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAction(node.getTextContent());
        }

        // get conditions from XML and set the list of IDs of the conditions that are being evaluated on every interval  
        list = doc.getElementsByTagName("conditions");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setConditions(node.getTextContent());
        }

        // get domain from XML and set the domain name of the autoscale policy
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domainid from XML and set the domain ID of the autoscale policy
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get duration from XML and set the duration for which the conditions have to be true before action is taken
        list = doc.getElementsByTagName("duration");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDuration(node.getTextContent());
        }

        // get project from XML and set the project name of the autoscale policy
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get project id from XML and set the project id of autoscale policy
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get quiettime from XML and set the cool down period for the policy 
        list = doc.getElementsByTagName("quiettime");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setQuietTime(node.getTextContent());
        }
        return response;
    }

    /**
     * Updates an existing autoscale vm profile
     *
     * @param autoScaleVmProfileId the ID of the autoscale vm profile
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateAutoScaleVmProfileResponse updateAutoScaleVmProfile(String autoScaleVmProfileId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateAutoScaleVmProfile", optional);
        arguments.add(new NameValuePair("id", autoScaleVmProfileId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateAutoScaleVmProfileResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateAutoScaleVmProfileResponse object
     *
     * @param doc
     * @return
     */
    private UpdateAutoScaleVmProfileResponse getUpdateAutoScaleVmProfileResponse(Document doc) {
        UpdateAutoScaleVmProfileResponse response = new UpdateAutoScaleVmProfileResponse();

        // get Id from XML and set the autoscale vm profile ID  
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account owning the instance group 
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get autoscaleuserid from XML and set the ID of the user used to launch and destroy the VMs  
        list = doc.getElementsByTagName("autoscaleuserid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAutoScaleUserId(node.getTextContent());
        }

        // get conditions from XML and set the list of IDs of the time to get closed before a vm is destroyed  
        list = doc.getElementsByTagName("destroyvmgraceperiod");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDestroyVmGracePeriod(node.getTextContent());
        }

        // get domain from XML and set the domain name of the vm profile
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get the domain ID of the vm profile
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get otherdeployparams from XML and parameters other than zoneId/serviceOfferringId/templateId
        list = doc.getElementsByTagName("otherdeployparams");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOtherDeployParams(node.getTextContent());
        }

        // get project from XML and set the project name of the vm profile
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get project id from XML and set the project id of the vm profile
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get serviceofferingid from XML and set the service offering to be used while deploying a virtual machine
        list = doc.getElementsByTagName("serviceofferingid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setServiceOfferingId(node.getTextContent());
        }

        // get templateid from XML and set the template to be used while deploying a virtual machine
        list = doc.getElementsByTagName("templateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get zoneid from XML and set the availability zone to be used while deploying a virtual machine
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * Retrieves the current status of asynchronous job for counter.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public CounterJobResultResponse counterJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getCounterJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into CounterJobResultResponse object
     *
     * @param doc
     * @return
     */
    private CounterJobResultResponse getCounterJobResultResponse(Document doc) {
        CounterJobResultResponse response = new CounterJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("counter")) {
                    List<TagsResponse> tagss = new LinkedList<TagsResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("action")) {
                            response.setAction(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("conditions")) {
                            response.setConditions(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("duration")) {
                            response.setDuration(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("quiettime")) {
                            response.setQuietTime(childNodeProperty.getTextContent());
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
     * Retrieves the current status of asynchronous job for condition.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public ConditionJobResultResponse conditionJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getConditionJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into ConditionJobResultResponse object
     *
     * @param doc
     * @return
     */
    private ConditionJobResultResponse getConditionJobResultResponse(Document doc) {
        ConditionJobResultResponse response = new ConditionJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("condition")) {
                    List<ConditionResponse> conditions = new LinkedList<ConditionResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("counter")) {
                            response.setCounter(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("relationaloperator")) {
                            response.setRelationalOperator(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("threshold")) {
                            response.setThreshold(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
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
     * Retrieves the current status of asynchronous job for autoscalepolicy.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public AutoScalePolicyJobResultResponse autoScalePolicyJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getAutoScalePolicyJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into AutoScalePolicyJobResultResponse object
     *
     * @param doc
     * @return
     */
    private AutoScalePolicyJobResultResponse getAutoScalePolicyJobResultResponse(Document doc) {
        AutoScalePolicyJobResultResponse response = new AutoScalePolicyJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("autoscalepolicy")) {
                    List<ConditionResponse> conditions = new LinkedList<ConditionResponse>();
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("action")) {
                            response.setAction(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("conditions")) {
                            response.setConditions(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("duration")) {
                            response.setDuration(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("quiettime")) {
                            response.setQuietTime(childNodeProperty.getTextContent());
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
     * Retrieves the current status of asynchronous job for autoscalevmprofile.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public AutoScaleVmProfileJobResultResponse autoScaleVmProfileJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getAutoScaleVmProfileJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into AutoScaleVmProfileJobResultResponse object
     *
     * @param doc
     * @return
     */
    private AutoScaleVmProfileJobResultResponse getAutoScaleVmProfileJobResultResponse(Document doc) {
        AutoScaleVmProfileJobResultResponse response = new AutoScaleVmProfileJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("autoscalevmprofile")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("autoscaleuserid")) {
                            response.setAutoScaleUserId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("destroyvmgraceperiod")) {
                            response.setDestroyVmGracePeriod(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("otherdeployparams")) {
                            response.setOtherDeployParams(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("serviceofferingid")) {
                            response.setServiceOfferingId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("templateid")) {
                            response.setTemplateId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setZoneId(childNodeProperty.getTextContent());
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
     * Retrieves the current status of asynchronous job for autoscalevmgroup.
     *
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public AutoScaleVmGroupJobResultResponse autoScaleVmGroupJobResult(String asychronousJobid)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("queryAsyncJobResult", null);
        arguments.add(new NameValuePair("jobid", asychronousJobid));

        Document responseDocument = server.makeRequest(arguments);

        return getAutoScaleVmGroupJobResultResponse(responseDocument);
    }

    /**
     * Converts XML document into AutoScaleVmGroupJobResultResponse object
     *
     * @param doc
     * @return
     */
    private AutoScaleVmGroupJobResultResponse getAutoScaleVmGroupJobResultResponse(Document doc) {
        AutoScaleVmGroupJobResultResponse response = new AutoScaleVmGroupJobResultResponse();

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
                if (nodeProperty.getNodeName().equals("autoscalevmgroup")) {
                    NodeList childNodeProperties = nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccount(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomain(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("interval")) {
                            response.setInterval(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("lbruleid")) {
                            response.setLbRuleId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("maxmembers")) {
                            response.setMaxMembers(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("minmembers")) {
                            response.setMinMembers(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setProject(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("scaledownpolicies")) {
                            response.setScaleDownPolicies(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("scaleuppolicies")) {
                            response.setScaleUpPolicies(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("state")) {
                            response.setState(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("vmprofileid")) {
                            response.setVmProfileId(childNodeProperty.getTextContent());
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
     * Updates an existing autoscale vm group
     *
     * @param autoScaleGroupId the ID of the autoscale group
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateAutoScaleVmGroupResponse updateAutoScaleVmGroup(String autoScaleGroupId,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateAutoScaleVmGroup", optional);
        arguments.add(new NameValuePair("id", autoScaleGroupId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateAutoScaleVmGroupResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateAutoScaleVmGroupResponse object
     *
     * @param doc
     * @return
     */
    private UpdateAutoScaleVmGroupResponse getUpdateAutoScaleVmGroupResponse(Document doc) {
        UpdateAutoScaleVmGroupResponse response = new UpdateAutoScaleVmGroupResponse();

        // get Id from XML and set the autoscale vm group ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get account from XML and set the account owning the instance group 
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccount(node.getTextContent());
        }

        // get domain from XML and set the domain name of the vm profile
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomain(node.getTextContent());
        }

        // get domain id from XML and set the domain ID of the vm profile
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get interval from XML and set the frequency at which the conditions have to be evaluated  
        list = doc.getElementsByTagName("interval");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setInterval(node.getTextContent());
        }

        // get lbruleid from XML and set the load balancer rule ID  
        list = doc.getElementsByTagName("lbruleid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLbRuleId(node.getTextContent());
        }

        // get maxmembers from XML and set the maximum number of members in the vmgroup
        list = doc.getElementsByTagName("maxmembers");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMaxMembers(node.getTextContent());
        }

        // get minmembers from XML and set the minimum number of members in the vmgroup
        list = doc.getElementsByTagName("minmembers");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setMinMembers(node.getTextContent());
        }

        // get project from XML and set the project name of the vm profile
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProject(node.getTextContent());
        }

        // get project id from XML and set the project id of the vm profile
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProjectId(node.getTextContent());
        }

        // get scaledownpolicies from XML and set the list of scaledown autoscale policies
        list = doc.getElementsByTagName("scaledownpolicies");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScaleDownPolicies(node.getTextContent());
        }

        // get scaleuppolicies from XML and set the list of scaleup autoscale policies
        list = doc.getElementsByTagName("scaleuppolicies");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScaleUpPolicies(node.getTextContent());
        }

        // get state from XML and set the current state of the AutoScale Vm Group
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setState(node.getTextContent());
        }

        // get vmprofileid from XML and set the autoscale profile that contains information about the vms in the vm group
        list = doc.getElementsByTagName("vmprofileid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setVmProfileId(node.getTextContent());
        }

        return response;
    }

}
