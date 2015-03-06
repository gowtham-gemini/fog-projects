package com.assistanz.fogpanel

import com.assistanz.fogpanel.UsageServer;
import com.assistanz.fogpanel.UsageResponse;

import grails.converters.deep.JSON;
import java.util.Date;
import java.sql.Timestamp;

class VmUsageService { 

    
    def setData() {
        Date date = new Date();
        def time = new Timestamp(date.getTime());
        UsageServer usageServer = UsageServer.getInstance();
        def vmList = VirtualMachine.findAllWhere(deleted: false); 
        for(int i=0; i < vmList.size(); i++) { 
            def item = vmList[i]; 
            def response = usageServer.getData(item.vmInternalName);
            
            VmUsage vmUsage = new VmUsage();
            vmUsage.diskRead = Double.parseDouble(response.diskRead);    
            vmUsage.diskWrite = Double.parseDouble(response.diskWrite);    
            vmUsage.cpuUtil = Double.parseDouble(response.cpuUtil);    
            vmUsage.memeoryUtil = Double.parseDouble(response.memeoryUtil);    
            vmUsage.networkRead = Double.parseDouble(response.networkRead);    
            vmUsage.networkWrite = Double.parseDouble(response.networkWrite);    
            vmUsage.date = time;    
            vmUsage.virtualMachine = item;
            vmUsage.save(flush:true);
            
        }
    }
    
    def getData(String VmId) { 
       ArrayList<ArrayList<String>> usageList = new ArrayList<ArrayList<String>>();              
        VirtualMachine virtualMachine = VirtualMachine.findByReferenceId(VmId);
        def vmUsageList = VmUsage.findAllWhere(virtualMachine: virtualMachine); 
         for(int i=0; i < vmUsageList.size(); i++) { 
            def item = vmUsageList[i]; 
            HashMap usageItem = new HashMap();  
            usageItem.put("cpuUtil", item.cpuUtil);
            usageItem.put("memoryUtil", item.memeoryUtil);
            usageItem.put("diskRead", item.diskRead);
            usageItem.put("diskWrite", item.diskWrite);
            usageItem.put("networkRead", item.networkRead);
            usageItem.put("networkWrite", item.networkWrite);
            usageList.add(usageItem);
        }
        return usageList;
     }
    
}
