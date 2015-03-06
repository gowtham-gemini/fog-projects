package com.assistanz.fogpanel

import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.NameValuePair;
import java.net.URLEncoder;
import com.assistanz.fogpanel.GeneralConstants;
import java.util.HashMap;
import com.assistanz.cloud.cloudstack.CloudStackServer
import grails.converters.deep.JSON
import com.assistanz.cloud.cloudstack.pool.CSPoolService
import com.assistanz.cloud.cloudstack.pool.StoragePoolResponse
import org.codehaus.groovy.grails.commons.ApplicationHolder

class StoragePoolService {

    def csStoragePoolServer() {
        
        def cloudStackUrl =  ApplicationHolder.getApplication().config.cloudstack.api.url
        def cloudStackApiKey = ApplicationHolder.getApplication().config.cloudstack.api.key
        def cloudStackSecretKey = ApplicationHolder.getApplication().config.cloudstack.api.secret

        CloudStackServer server =
                new CloudStackServer(cloudStackUrl, cloudStackSecretKey, cloudStackApiKey);

        CSPoolService csPoolService = new CSPoolService(server);
        
        return csPoolService;
    }
    
    def list(String clusterReferenceId, String storageType) {
        def result;  
        try {      
            HashMap<String,String> optional = new HashMap<String,String>();
            optional.put("clusterid", clusterReferenceId);

            def response = csStoragePoolServer().listStoragePools(optional);

            ArrayList<ArrayList<String>> storagePoolList = new ArrayList<ArrayList<String>>();
            for(Iterator<StoragePoolResponse> storagePoolIter = response.storagePools.iterator(); storagePoolIter.hasNext();) {
                def storagePoolData = storagePoolIter.next();
                def size = Double.parseDouble(storagePoolData.diskSizeTotal) 
                StoragePool storagePool = StoragePool.findByStoragePoolReferenceId(storagePoolData.storagePoolId);
                if(!storagePool) {
                    storagePool = new StoragePool()
                    storagePool.storagePoolReferenceId = storagePoolData.storagePoolId
                    storagePool.name = storagePoolData.storagePoolName
                    storagePool.tag = storagePoolData.storagePoolTags
                    storagePool.storagePoolType = storagePoolData.storagePoolType
                    storagePool.totalSize = size/1073741824
                    storagePool.cluster = Cluster.findByClusterReferenceId(clusterReferenceId)
                    storagePool.save(flush: true)
                } else if(storagePool) {
                    storagePool.totalSize = size/1073741824
                    storagePool.name = storagePoolData.storagePoolName
                    storagePool.tag = storagePoolData.storagePoolTags
                    storagePool.save(flush: true)
                }
            }
            if((clusterReferenceId != "null" || clusterReferenceId != null) && (storageType == "null" || storageType == null)) { 
                result = StoragePool.findAllWhere(cluster: Cluster.findByClusterReferenceId(clusterReferenceId)); 
            } else if((clusterReferenceId != "null" || clusterReferenceId != null) && (storageType != "null" || storageType != null)) {
                if(storageType == "Local") {
                    result = StoragePool.findAllWhere(cluster: Cluster.findByClusterReferenceId(clusterReferenceId), storagePoolType: GeneralConstants.STORAGE_POOL_TYPE_LVM); 
                } else if(storageType == "Shared") {
                    result = StoragePool.findAllWhere(cluster: Cluster.findByClusterReferenceId(clusterReferenceId), storagePoolType: GeneralConstants.STORAGE_POOL_TYPE_NFS); 
                }
            }
             for(int i=0; i < result.size(); i++) { 
                def item = result[i]; 
                HashMap<String,String> storagePool = new HashMap<String,String>();    
                storagePool.put("storagePoolReferenceId", item.storagePoolReferenceId);
                storagePool.put("storagePoolName", item.name);
                storagePool.put("storageTag", item.tag);
                storagePoolList.add(storagePool);
            }        
             return storagePoolList;
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            throw ex;
        }
    }
    
    def getCapacity(String storagetReferenceId) {
        
        StoragePool storagePool = StoragePool.findByStoragePoolReferenceId(storagetReferenceId);
       
        ArrayList<ArrayList<String>> storagePoolList = new ArrayList<ArrayList<String>>();
        HashMap<String,String> item = new HashMap<String,String>();    
        item.put("storagePoolReferenceId", storagePool.storagePoolReferenceId);
        item.put("storagePoolName", storagePool.name);
        item.put("storageTag", storagePool.tag);
        item.put("totalSize", (int) storagePool.totalSize);
        storagePoolList.add(item);
        
        return storagePoolList;
    }
    
    
    
    
}
