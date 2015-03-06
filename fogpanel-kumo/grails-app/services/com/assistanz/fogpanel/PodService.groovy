package com.assistanz.fogpanel

import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.NameValuePair;
import java.net.URLEncoder;
import java.util.HashMap;
import com.assistanz.cloud.cloudstack.CloudStackServer
import grails.converters.deep.JSON
import com.assistanz.cloud.cloudstack.pod.CSPodService
import com.assistanz.cloud.cloudstack.pod.PodResponse
import com.assistanz.cloud.cloudstack.CapacityResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder

class PodService {
    
    def csPodServer() {
            def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
            def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
            def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
            CloudStackServer server =
                    new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSPodService csPodService = new CSPodService(server);
        
        return csPodService;
    }
    

    def list(String referenceZoneId) {
        try { 
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("zoneid", referenceZoneId);
            def response;
            def result;
            if(referenceZoneId == "null" || referenceZoneId == null) {
                response = csPodServer().listPods();
                result = Pod.findAll() 
            } else {
                 response = csPodServer().listPods(optional);
                 result = Pod.findAllWhere(zone: Zone.findByReferenceZoneId(referenceZoneId)) 
            }

            ArrayList<ArrayList<String>> podList = new ArrayList<ArrayList<String>>();
            for(Iterator<PodResponse> podIter = response.pods.iterator(); podIter.hasNext();) {
                def podData = podIter.next();

                Pod pod = Pod.findByPodReferenceId(podData.podId);
                if (!pod) {
                    pod = new Pod()
                    pod.podReferenceId = podData.podId
                    pod.name = podData.podName
                    pod.description = podData.podName
                    pod.zone = Zone.findByReferenceZoneId(podData.podZoneId) 
                    pod.save(flush: true);
                }
             }
             for(int i=0; i < result.size(); i++) { 
                def item = result[i]; 
                HashMap<String,String> pod = new HashMap<String,String>();    
                pod.put("podReferenceId", item.podReferenceId);
                pod.put("podName", item.name);
                pod.put("podDescription", item.description);
                podList.add(pod);              
            }        
             return podList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def update(String requestBody) {
        
        // convert string to json object
        def requestData = JSON.parse(requestBody) 
        Pod oldPod = Pod.findByPodReferenceId(requestData.id);
        oldPod.description = requestData.description;
        oldPod.save(flush: true);
        if (oldPod.hasErrors()) {
            throw new ValidationException(oldPod.errors.allErrors);
        } 
        ArrayList<ArrayList<String>> podList = new ArrayList<ArrayList<String>>();
        HashMap<String,String> pod = new HashMap<String,String>(); 
        pod.put("result", GeneralConstants.RESULT_SUCCESS);
        pod.put("podReferenceId", oldPod.podReferenceId);
        pod.put("podName", oldPod.name);
        pod.put("podDescription", oldPod.description);
        podList.add(pod);   
        
        return podList;
    }
}
