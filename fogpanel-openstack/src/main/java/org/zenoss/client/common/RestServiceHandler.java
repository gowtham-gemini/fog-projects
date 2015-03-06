package org.zenoss.client.common;


import java.io.InputStream;
import org.json.simple.JSONObject;

import java.util.Map;
import org.json.simple.JSONArray;

public interface RestServiceHandler {

    public JSONObject execute(Router router, String method, Map data) throws ApplicationException;
    
    public JSONArray executeList(Router router, String method, Map data) throws ApplicationException;
    
    public JSONObject test(String router, String action, String method, Map data) throws Exception;
    
    public InputStream getFile(String url) throws ApplicationException;
    
    public String getHttpInstanceUrl() throws ApplicationException;
}
