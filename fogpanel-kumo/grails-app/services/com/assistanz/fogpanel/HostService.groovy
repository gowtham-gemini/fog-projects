package com.assistanz.fogpanel

import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.NameValuePair;
import java.net.URLEncoder;
import java.util.HashMap;
import com.assistanz.cloud.cloudstack.CloudStackServer
import grails.converters.deep.JSON
import com.assistanz.cloud.cloudstack.host.CSHostService
import com.assistanz.cloud.cloudstack.host.HostResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder

class HostService {
    
    def csHostServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret
    
        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSHostService csHostService = new CSHostService(server);
        
        return csHostService;
    }

    def list(String clusterReferenceId) {
        
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("clusterid", clusterReferenceId);
        
        def response = csHostServer().listHosts(optional);
        ArrayList<ArrayList<String>> hostList = new ArrayList<ArrayList<String>>();
        for(Iterator<HostResponse> hostIter = response.hosts.iterator(); hostIter.hasNext();) {
            def hostData = hostIter.next();
            Double memory = Double.parseDouble(hostData.hostMemoryTotal);
            Host host = Host.findByHostReferenceId(hostData.hostId);
            if (!host) {
                host = new Host()
                host.hostReferenceId = hostData.hostId
                host.name = hostData.hostName
                host.description = hostData.hostName
                host.cpuNumberTotal =  Integer.parseInt(hostData.hostCpuNumber) 
                host.coreCpuSpeedTotal =  Double.parseDouble(hostData.hostCpuSpeed) 
                host.memoryTotal = memory/1048576;
                host.hostTag = hostData.hostTags
                host.zone = Zone.findByReferenceZoneId(hostData.hostZoneId)
                host.pod = Pod.findByPodReferenceId(hostData.hostPodId)
                host.cluster = Cluster.findByClusterReferenceId(hostData.hostClusterId)
                host.save(flush: true)
            } else if(host) {
                host.name = hostData.hostName
                host.hostTag = hostData.hostTags
                host.cpuNumberTotal =  Integer.parseInt(hostData.hostCpuNumber) 
                host.coreCpuSpeedTotal =  Double.parseDouble(hostData.hostCpuSpeed) 
                host.memoryTotal = memory/1048576;
                host.save(flush: true)
            }
         }
        def result = Host.findAllWhere(cluster: Cluster.findByClusterReferenceId(clusterReferenceId));
        for(int i=0; i < result.size(); i++) { 
            def item = result[i]; 
            HashMap<String,String> host = new HashMap<String,String>();    
            host.put("hostReferenceId", item.hostReferenceId);
            host.put("hostName", item.name);
            host.put("hostTag", item.hostTag);
            host.put("hostDescription", item.description);
            host.put("cpuNumberTotal", item.cpuNumberTotal);
            host.put("coreCpuSpeedTotal", item.coreCpuSpeedTotal);
            host.put("memoryTotal",  (int) item.memoryTotal);
            hostList.add(host);
        }
        return hostList;
    }
    
    
    def getHostCapacity(String hostId) {
        
        HashMap<String,String> optional = new HashMap<String,String>();
        optional.put("id", hostId);
        
        def response = csHostServer().listHosts(optional);
        ArrayList<ArrayList<String>> hostList = new ArrayList<ArrayList<String>>();
        for(Iterator<HostResponse> hostIter = response.hosts.iterator(); hostIter.hasNext();) {
            def hostData = hostIter.next();
            Double memory = Double.parseDouble(hostData.hostMemoryTotal);
            Double cpuSpeed = Double.parseDouble(hostData.hostCpuSpeed);
            Double cpuNumber =  Integer.parseInt(hostData.hostCpuNumber);
            Double memoryUsed;
            Double memoryAllocated;
            Double networkkbsRead;
            Double networkkbsWrite;
            
            
//            Console.print("hostData.hostMemoryUsed" + hostData.hostMemoryUsed)
            if(hostData.hostMemoryUsed == null || hostData.hostMemoryUsed == "null") {
                memoryUsed =  0;
            } else {
               memoryUsed =  Double.parseDouble(hostData.hostMemoryUsed); 
            }
            if(hostData.hostMemoryAllocated == null || hostData.hostMemoryAllocated == "null") {
                memoryAllocated =  0;
            } else {
               memoryAllocated =  Double.parseDouble(hostData.hostMemoryAllocated);
            }
            if(hostData.hostNetworKbsRead == null || hostData.hostNetworKbsRead == "null") {
                networkkbsRead =  0;
            } else {
                networkkbsRead =  Double.parseDouble(hostData.hostNetworKbsRead);
            }
            if(hostData.hostNetworKbsWrite == null || hostData.hostNetworKbsWrite == "null") {
                networkkbsWrite =  0;
            } else {
                networkkbsWrite =  Double.parseDouble(hostData.hostNetworKbsWrite);
            }
            HashMap<String,String> host = new HashMap<String,String>();    
            host.put("totalCpu", cpuNumber + " X " + cpuSpeed/1000 + " Ghz");
            host.put("memory",  Math.round((memory/1073741824) *100)/100  + " GB");
            host.put("cpuAllocated", hostData.hostCpuAllocated);
            host.put("cpuUsed", hostData.hostCpuUsed);
            host.put("memoryUsed", Math.round((memoryUsed/1048576) *100)/100 + " MB");
            host.put("memoryAllocated",  Math.round((memoryAllocated/1073741824) *100)/100  + " GB");
            host.put("networkkbsRead",  Math.round((networkkbsRead/1024) *100)/100 + " MB");
            host.put("networkkbsWrite", Math.round((networkkbsWrite/1024) *100)/100 + " MB");
            hostList.add(host); 
        }    
        return hostList;
    }
    
    
    
    def update(String requestBody) {
        
        // convert string to json object
        def requestData = JSON.parse(requestBody) 
        Host oldHost = Host.findByHostReferenceId(requestData.id);
        oldHost.description = requestData.description;
        oldHost.save(flush: true);
        if (oldHost.hasErrors()) {
            throw new ValidationException(oldHost.errors.allErrors);
        } 
        ArrayList<ArrayList<String>> hostList = new ArrayList<ArrayList<String>>();
        HashMap<String,String> host = new HashMap<String,String>();    
        host.put("hostReferenceId", oldHost.hostReferenceId);
        host.put("hostName", oldHost.name);
        host.put("hostDescription", oldHost.description);
        hostList.add(host);   
        
        return hostList;
    }
    
    def getCapacity(String hostReferenceId) {                
        Host oldHost = Host.findByHostReferenceId(hostReferenceId);       
        if(oldHost) {
            ArrayList<ArrayList<String>> hostList = new ArrayList<ArrayList<String>>();
            HashMap<String,String> host = new HashMap<String,String>();    
            host.put("hostReferenceId", oldHost.hostReferenceId);
            host.put("hostName", oldHost.name);
            host.put("hostDescription", oldHost.description);
            host.put("cpuNumberTotal", oldHost.cpuNumberTotal);
            host.put("coreCpuSpeedTotal", oldHost.coreCpuSpeedTotal);
            host.put("memoryTotal", (int) oldHost.memoryTotal);
            hostList.add(host);           
            return hostList;
        }     
    }        
}
