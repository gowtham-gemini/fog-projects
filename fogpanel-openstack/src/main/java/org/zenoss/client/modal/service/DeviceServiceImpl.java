package org.zenoss.client.modal.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.zenoss.client.api.DeviceService;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceHandler;
import org.zenoss.client.common.Router;
import org.zenoss.client.modal.entities.device.Device;
import org.zenoss.client.modal.entities.device.Dictionary;
import org.zenoss.client.modal.entities.device.Direction;
import org.zenoss.client.modal.entities.device.GraphDef;
import org.zenoss.client.modal.entities.device.IPService;
import org.zenoss.client.modal.entities.device.LocationNode;
import org.zenoss.client.modal.entities.device.MetaType;
import org.zenoss.client.util.Utility;

public class DeviceServiceImpl implements DeviceService {

    private RestServiceHandler handler;

    public DeviceServiceImpl(RestServiceHandler restServiceHandler) {
        this.handler = restServiceHandler;
    }

    @Override
    public Dictionary addLocationNode(LocationNode locationNode) throws ApplicationException {
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getDevice(), "addLocationNode", locationNode.toHashMap());

        return Dictionary.convert((JSONObject) jsonObject.get("nodeConfig"));
    }
    
    @Override
    public List<String> getDeviceClasses() throws ApplicationException {

        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getDevice(), "getDeviceClasses", new HashMap());

        JSONArray jSONArray = (JSONArray) jsonObject.get("deviceClasses");
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < jSONArray.size(); i++) {

            JSONObject jSONObject = (JSONObject) jSONArray.get(i);
            list.add(jSONObject.get("name").toString());
        }
        return list;
    }

    @Override
    public HashMap getProductionStates() throws ApplicationException {

        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getDevice(), "getProductionStates", new HashMap());

        JSONArray jSONArray = (JSONArray) jsonObject.get("data");
        return Utility.convertJSONArrayToMap(jSONArray, "name", "value");
    }
    
    @Override
    public HashMap getPriorities() throws ApplicationException {

        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getDevice(), "getPriorities", new HashMap());

        JSONArray jSONArray = (JSONArray) jsonObject.get("data");
        return Utility.convertJSONArrayToMap(jSONArray, "name", "value");
    }

    @Override
    public HashMap getCollectors() throws ApplicationException {

        HashMap map = new HashMap();

        JSONArray jSONArray
                = (JSONArray) handler.executeList(Router.getDevice(), "getCollectors", map);
        List<Dictionary> list = new ArrayList<Dictionary>();

        for (int i = 0; i < jSONArray.size(); i++) {

            map.put(i, jSONArray.get(i));
        }
        return map;
    }

    @Override
    public String addDevice(Device device) throws ApplicationException {
        
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getDevice(), "addDevice", device.toHashMap());
        
        System.out.println("Add device"+jsonObject.toString());
        
        JSONArray newJobArray = (JSONArray)jsonObject.get("new_jobs");
        
        for (Object object : newJobArray) {
            
            JSONObject jSONObject = (JSONObject) object;
            
            return ""+jSONObject.get("uuid");
        }
        
        return "";

    }                 
    
    @Override
    public List<Dictionary> getDevices(Device device) throws ApplicationException {
        
        HashMap hashMap = new HashMap();
        
        
        if(device != null && device.getUid() != null && !device.getUid().isEmpty()) {
            hashMap.put("uid", device.getUid());
        }
        
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getDevice(), "getDevices", hashMap);
        JSONArray jSONArray = (JSONArray) jsonObject.get("devices");
        List<Dictionary> list = new ArrayList<Dictionary>();
        
        for (Object object : jSONArray) {
            
            JSONObject jSONObject = (JSONObject) object;
            Dictionary dictionary = Dictionary.convert(jSONObject);
            
            if(dictionary != null) {
                list.add(dictionary);
            }
        }
        return list;
    }
    
    @Override
    public List<GraphDef> getGraphDefs(Device device) throws ApplicationException {
        Map map = new HashMap();
        
        map.put("uid", device.getUid());
        
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getDevice(), "getGraphDefs", map);
        
        
        JSONArray jSONArray = (JSONArray) jsonObject.get("data");

        List<GraphDef> resultList = new ArrayList<GraphDef>();
        
        for (int i = 0; i < jSONArray.size(); i++) {                                      
            GraphDef def = GraphDef.convert((JSONObject)jSONArray.get(i));
            
            if(def != null) {
                resultList.add(def);
            }    
        }
           
        return resultList;
    }

    @Override
    public InputStream getGraph(GraphDef graphDef, Long width, Long dateRange,
            String start, String end) throws ApplicationException {                                                    
            
            String instanceURL = handler.getHttpInstanceUrl();
            String graphURL = graphDef.getUrl();
            graphURL = graphURL.replace("width=500", "width=" + width);    
            graphURL = graphURL.replace("drange=129600", "drange=" + dateRange);                
            graphURL = graphURL + "&end=now-0s&start=end-"+dateRange;            
            InputStream inputStream = 
                    handler.getFile(instanceURL + graphURL);
                      
        return inputStream;
    }
       
    @Override
    public List<Dictionary> getTemplates(String deviceId) throws ApplicationException {
        
        HashMap hashMap = new HashMap();
        hashMap.put("id", deviceId);
        
        JSONArray jSONArray
                = (JSONArray) handler.executeList(Router.getDevice(), "getTemplates", hashMap);
        List<Dictionary> list = new ArrayList<Dictionary>();
        
        for (Object object : jSONArray) {
            
            JSONObject jSONObject = (JSONObject) object;
            list.add(Dictionary.convert(jSONObject));
        }
        return list;
    }
    
    @Override
    public List<Dictionary> getComponents(String deviceUid) throws ApplicationException {
        
        HashMap hashMap = new HashMap();
        hashMap.put("uid", deviceUid);
       // hashMap.put("meta_type", componentType);

        
        JSONObject jSONObject
                = (JSONObject) handler.execute(Router.getDevice(), "getComponents", hashMap);
        List<Dictionary> list = new ArrayList<Dictionary>();
        
        JSONArray jSONArray = (JSONArray) jSONObject.get("data");
        
        for (Object object : jSONArray) {
            
            JSONObject jsonObject = (JSONObject) object;
            list.add(Dictionary.convert(jsonObject));
        }
        return list;
        
    }
    
    @Override
    public String removeDevice(Device device) throws ApplicationException {
        
        HashMap hashMap = new HashMap();
        
        hashMap.put("uids", device.getUid());
        hashMap.put("deleteEvents", true);
        hashMap.put("deletePerf", true);
        hashMap.put("hashcheck", 1);
        hashMap.put("action", "delete");
        
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getDevice(), "removeDevices", hashMap);
        return "SUCCESS";
    }
    
    @Override
    public List<Dictionary> getComponents(HashMap hashMap) throws ApplicationException {
        
        JSONObject jSONObject
                = (JSONObject) handler.execute(Router.getDevice(), "getComponents", hashMap);
        List<Dictionary> list = new ArrayList<Dictionary>();
        
        JSONArray jSONArray = (JSONArray) jSONObject.get("data");
        
        for (Object object : jSONArray) {
            
            JSONObject jsonObject = (JSONObject) object;
            list.add(Dictionary.convert(jsonObject));
        }
        return list;
        
    }

    @Override
    public Boolean addIpService(HashMap hashMap) throws ApplicationException {

        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getDevice(), "addIpService", hashMap);
        return true;
    }
    
    @Override
    public List<IPService> getComponents(Device device, MetaType type, List<String> keys, Long start, Long limit, 
            String sortName, Direction dir, String filter) throws ApplicationException {
        Map optional = new HashMap();        
        optional.put("uid", device.getUid());        
        System.out.println("Calling" + device.getUid());
        if(start != null) {
            optional.put("start", start);   
        }
        if(type != null) {
            optional.put("meta_type", type.toString());   
        }
        if(keys != null) {
            optional.put("keys", keys);   
        }
        
        if(start != null) {
            optional.put("start", start);   
        }
        if(limit != null) {
            optional.put("limit", limit);   
        }        
        if(sortName != null) {
            optional.put("sort", sortName);  
        }        
        if(dir != null) {
            optional.put("dir", dir);
        }        
        if(limit != null) {
            optional.put("limit", limit);     
        }                                                           
        
        //Call zenoss client and get the response
        JSONObject jSONObject
                = (JSONObject) handler.execute(Router.getDevice(), "getComponents", optional);
        
        List<IPService> list = new ArrayList<IPService>();
        
        //Get the result JSON object
        JSONArray jSONArray = (JSONArray) jSONObject.get("data");
        
        for (Object object : jSONArray) {
           
            //Convert it to List<IPService>
            JSONObject jsonObject = (JSONObject) object;
            
            list.add(this.convertJSONtoIPServiceList(jsonObject));            
        }        
        
        return list;                                        
    }
    
    /**
     * ...
     * 
     * @param obj
     * @return 
     */
    private IPService convertJSONtoIPServiceList(JSONObject obj) {
        
      
       
        //Iterate
            //
             return IPService.convert(obj);
            //
              
    }
    
    @Override
    public Boolean setComponentsMonitored(HashMap hashMap) throws ApplicationException {
        
       JSONObject jsonObject
                    = (JSONObject) handler.execute(Router.getDevice(), "setComponentsMonitored", hashMap);
       return true;
    }
    
    
}
