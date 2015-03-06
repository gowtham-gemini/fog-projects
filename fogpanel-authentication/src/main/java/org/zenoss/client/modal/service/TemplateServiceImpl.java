package org.zenoss.client.modal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.zenoss.client.api.TemplateService;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceHandler;
import org.zenoss.client.common.Router;
import org.zenoss.client.modal.entities.device.Dictionary;
import org.zenoss.client.util.Utility;

public class TemplateServiceImpl implements TemplateService {

    private RestServiceHandler handler;

    public TemplateServiceImpl(RestServiceHandler restServiceHandler) {
        this.handler = restServiceHandler;
    }
    
    @Override
    public List<String> getThresholdTypes() throws ApplicationException {

        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getTemplate(), "getThresholdTypes", new HashMap());

        JSONArray jSONArray = (JSONArray) jsonObject.get("data");
        return Utility.convertJSONArrayToList(jSONArray, "type");
    }
    
    @Override
    public String copyTemplate(String templateId, String deviceId) throws ApplicationException {
        
        HashMap map = new HashMap();
        map.put("uid", templateId);
        map.put("targetUid",deviceId);
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getTemplate(), "copyTemplate", map);
        return "success";
    }
    
    @Override
    public List<Dictionary> getThresholds(String templateUid) throws ApplicationException {
        
        HashMap hashMap = new HashMap();
        hashMap.put("uid", templateUid);
        
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getTemplate(), "getThresholds", hashMap);
        JSONArray jSONArray = (JSONArray) jsonObject.get("data");
        List<Dictionary> list = new ArrayList<Dictionary>();
        
        for (Object object : jSONArray) {
            
            JSONObject jSONObject = (JSONObject) object;
            list.add(Dictionary.convert(jSONObject));
        }
        return list;
    }

    @Override
    public String removeThreshold(String thresholdUid) throws ApplicationException {
        
        HashMap map = new HashMap();
        map.put("uid", thresholdUid);
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getTemplate(), "removeThreshold", map);
        
        return "success";
    }

    @Override
    public List<Dictionary> getObjTemplates(String uid) throws ApplicationException {
        
        HashMap hashMap = new HashMap();
        hashMap.put("uid", uid);
        
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getTemplate(), "getObjTemplates", hashMap);
        JSONArray jSONArray = (JSONArray) jsonObject.get("data");
        List<Dictionary> list = new ArrayList<Dictionary>();
        
        for (Object object : jSONArray) {
            
            JSONObject jSONObject = (JSONObject) object;
            list.add(Dictionary.convert(jSONObject));
        }
        return list;
    }

    @Override
    public String makeLocalRRDTemplate(String uid, String templateName) throws ApplicationException {
        
        HashMap map = new HashMap();
        map.put("uid", uid);
        map.put("templateName", templateName);
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getTemplate(), "makeLocalRRDTemplate", map);
        
        return "success";
    }
    
    @Override
    public String addThreshold(Map map) throws ApplicationException {
        
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getTemplate(), "addThreshold", map);
        return "success";
        
    }
    
    @Override
    public Dictionary setInfoForAddedThreshold(Map map) throws ApplicationException {
        
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getTemplate(), "setInfo", map);
        return Dictionary.convert((JSONObject) jsonObject.get("data"));
    }
    

}
