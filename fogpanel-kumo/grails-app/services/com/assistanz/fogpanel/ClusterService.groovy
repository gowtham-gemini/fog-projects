package com.assistanz.fogpanel

import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.NameValuePair;
import java.net.URLEncoder;
import java.util.HashMap;
import com.assistanz.cloud.cloudstack.CloudStackServer
import grails.converters.deep.JSON
import com.assistanz.cloud.cloudstack.CapacityResponse
import com.assistanz.cloud.cloudstack.cluster.ClusterResponse
import com.assistanz.cloud.cloudstack.cluster.CSClusterService
import com.assistanz.cloud.cloudstack.systemcapacity.CSSystemCapacityService
import org.codehaus.groovy.grails.commons.ApplicationHolder

class ClusterService {
    
    def csClusterServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSClusterService csClusterService = new CSClusterService(server);
        
        return csClusterService;
    }
    
    def list(String referencePodId) {
        try {
            
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("podid", referencePodId);

            
            def response;
            def result;
            if(referencePodId == "null" || referencePodId == null) {
                response = csClusterServer().listClusters()
                result = Cluster.findAll() 
            } else {
                 response = csClusterServer().listClusters(optional)
                 result = Cluster.findAllWhere(pod: Pod.findByPodReferenceId(referencePodId))  
            }
            
           ArrayList<ArrayList<String>> clusterList = new ArrayList<ArrayList<String>>();
           for(Iterator<ClusterResponse> clusterIter = response.clusters.iterator(); clusterIter.hasNext();) {
               def clusterData = clusterIter.next();

               Cluster cluster = Cluster.findByClusterReferenceId(clusterData.clusterId);
               if (!cluster) {
                   cluster = new Cluster()
                   cluster.clusterReferenceId = clusterData.clusterId
                   cluster.name = clusterData.clusterName
                   cluster.description = clusterData.clusterName
                   cluster.zone = Zone.findByReferenceZoneId(clusterData.clusterZoneId)
                   cluster.pod = Pod.findByPodReferenceId(clusterData.clusterPodId)
                   cluster.save(flush: true)
               }
           }
           
            for(int i=0; i < result.size(); i++) {  
               def item = result[i]; 
               HashMap<String,String> cluster = new HashMap<String,String>(); 
               cluster.put("clusterReferenceId", item.clusterReferenceId);
               cluster.put("clusterName", item.name);
               cluster.put("clusterDescription", item.description);
               clusterList.add(cluster); 
           }
            return clusterList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def update(String requestBody) {
        
        // convert string to json object
        def requestData = JSON.parse(requestBody) 
        Cluster oldCluster = Cluster.findByClusterReferenceId(requestData.id);
        oldCluster.description = requestData.description;
        oldCluster.save(flush: true);
        if (oldCluster.hasErrors()) {
            throw new ValidationException(oldCluster.errors.allErrors);
        } 
        ArrayList<ArrayList<String>> clusterList = new ArrayList<ArrayList<String>>();
        HashMap<String,String> cluster = new HashMap<String,String>();    
        cluster.put("result", GeneralConstants.RESULT_SUCCESS);
        cluster.put("clusterReferenceId", oldCluster.clusterReferenceId);
        cluster.put("clusterName", oldCluster.name);
        cluster.put("clusterDescription", oldCluster.description);
        clusterList.add(cluster);   
        
        return clusterList;
    }
}
