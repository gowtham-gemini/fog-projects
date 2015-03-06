
package org.zenoss.client.modal.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.zenoss.client.api.ServiceRouterService;
import org.zenoss.client.common.ApplicationException;
import org.zenoss.client.common.RestServiceHandler;
import org.zenoss.client.common.Router;
import org.zenoss.client.modal.entities.device.Dictionary;

public class ServiceRouterServiceImpl implements ServiceRouterService {
    
    private RestServiceHandler handler;

    public ServiceRouterServiceImpl(RestServiceHandler restServiceHandler) {
        this.handler = restServiceHandler;
    }

    @Override
    public List<Dictionary> getInstances(HashMap hashMap) throws ApplicationException {
        
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getService(), "query", hashMap);
        JSONArray jSONArray = (JSONArray) jsonObject.get("services");
        List<Dictionary> list = new ArrayList<Dictionary>();
        
        for (Object object : jSONArray) {
            
            JSONObject jSONObject = (JSONObject) object;
            list.add(Dictionary.convert(jSONObject));
        }
        return list;
        
    }

    @Override
    public String addClass(HashMap hashMap) throws ApplicationException {
        
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getService(), "addClass", hashMap);
        return "SUCCESS";
    }

    @Override
    public String setInfoForAddedClass(HashMap hashMap) throws ApplicationException {
        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getService(), "setInfo", hashMap);
        return "SUCCESS";
    }

    @Override
    public List<Dictionary> getClassNames(HashMap hashMap) throws ApplicationException {

        JSONObject jsonObject
                = (JSONObject) handler.execute(Router.getService(), "getClassNames", hashMap);
        JSONArray jSONArray = (JSONArray) jsonObject.get("data");
        List<Dictionary> list = new ArrayList<Dictionary>();

        for (Object object : jSONArray) {

            JSONObject jSONObject = (JSONObject) object;
            list.add(Dictionary.convertLimited(jSONObject));
        }
        return list;
    }

    
    
    
}
