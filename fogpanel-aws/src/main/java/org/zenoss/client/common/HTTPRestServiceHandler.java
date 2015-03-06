package org.zenoss.client.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import sun.applet.AppletIOException;

/**
 *
 */
public class HTTPRestServiceHandler implements RestServiceHandler {

    CloseableHttpClient httpclient;
    private ResponseHandler<String> responseHandler = new BasicResponseHandler();
    private JSONParser jsonParser = new JSONParser();
    private int reqCount = 1;

    private String ZENOSS_INSTANCE;

    
    public HTTPRestServiceHandler(String ZENOSS_INSTANCE, String ZENOSS_USERNAME, String ZENOSS_PASSWORD) throws ApplicationException {

        System.out.println("Credentials: " + ZENOSS_INSTANCE + "/" + ZENOSS_USERNAME + "/" + ZENOSS_PASSWORD);
                
        try {
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();          

            // Increase max total connection to 200
            cm.setMaxTotal(200);
            // Increase default max connection per route to 20
            cm.setDefaultMaxPerRoute(20);

            httpclient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
            
            HttpPost httpost = new HttpPost(ZENOSS_INSTANCE + "/zport/acl_users/cookieAuthHelper/login");
            this.ZENOSS_INSTANCE = ZENOSS_INSTANCE;

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("__ac_name", ZENOSS_USERNAME));
            nvps.add(new BasicNameValuePair("__ac_password", ZENOSS_PASSWORD));
            nvps.add(new BasicNameValuePair("submitted", "true"));
            nvps.add(new BasicNameValuePair("came_from", ZENOSS_INSTANCE +
                    "/zport/dmd"));

            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

            // Response from POST not needed, just the cookie
            HttpResponse response = httpclient.execute(httpost);               

            String json = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println("Execute Output: " + json);
            
            // Consume so we can reuse httpclient
            response.getEntity().consumeContent();
                        
        } 
        catch (Exception ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }

    /**
     * routerRequest is the main method used to communicate with the Zenoss instance
     */
    private JSONObject routerRequest(String router, String action, String method, Map data)
            throws Exception {
        
//        System.out.println("Router: " + router);
//        System.out.println("Action: " + action);
//        System.out.println("Method: " + method);
//        System.out.println("Data: " + data.toString());
            
        try {
            // Construct standard URL for requests
            String postValue =   ZENOSS_INSTANCE +  "/zport/dmd/" + action + "_router";

          //  System.out.println("PostValue: " + postValue);
                    
            HttpPost httpost = new HttpPost(postValue);

            // Content-type MUST be set to 'application/json'
            httpost.addHeader("Content-type", "application/json; charset=utf-8");

            ArrayList packagedData = new ArrayList();
            packagedData.add(data);

            HashMap reqData = new HashMap();
            reqData.put("action", router);
            reqData.put("method", method);
            reqData.put("data", packagedData);
            reqData.put("type", "rpc");

            // Increment the request count ('tid'). More important if sending multiple
            // calls in a single request
            reqData.put("tid", String.valueOf(this.reqCount++));
            
            //System.out.println(reqData.toString());
            
            // Set the POST content to be a JSON-serialized version of request data
            httpost.setEntity(new StringEntity(JSONValue.toJSONString(reqData)));

            // Execute the request, and return the JSON-deserialized data
            HttpResponse response = httpclient.execute(httpost);          

            if(response.getStatusLine().getStatusCode() == 302) {                    
                //System.out.println("Status Code: " + response.getStatusLine().getStatusCode() );
                
                Header[] headers = response.getHeaders("Location");
                String headerValue = headers[0].getValue();

                //System.out.println(headerValue);
                
                String[] value = headerValue.split("\\?");                   
                
                //System.out.println(value[0]);
                
                if(value[0].equals(ZENOSS_INSTANCE + "/zport/acl_users/cookieAuthHelper/login_form")) {
                    throw new ApplicationException("User credentials not correct or session expired");
                }                                                                
            }
            
            String json = EntityUtils.toString(response.getEntity(), "UTF-8");
            httpost.releaseConnection();
            
            System.out.println("Execute Output: ");
            System.out.println((JSONObject)jsonParser.parse(json));
            
            return (JSONObject)jsonParser.parse(json);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
    }


    // Close the httpclient instance, shutting down the connection
    public void close() throws Exception {
        httpclient.getConnectionManager().shutdown();
    }

    @Override
    public JSONObject execute(Router router, String method, Map data) throws ApplicationException {
        try {
            
            JSONObject obj = routerRequest(router.getAction(), router.getValue(), method, data);

            if(obj.get("result") != null && obj.get("result").toString().length() > 0) {
                JSONObject resultObj = (JSONObject) obj.get("result");
                if(resultObj.get("success").equals(Boolean.FALSE) && (resultObj.get("type").equals("exception") || 
                        resultObj.get("type").equals("error"))) {
                    throw new ApplicationException(resultObj.get("msg").toString());
                }
                    }

            return (JSONObject) obj.get("result");
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
    }
    
    
    @Override
    public InputStream getFile(String url) throws ApplicationException {        
        
        HttpGet httpGet = new HttpGet(url);
        
        try {            
            HttpResponse resp = httpclient.execute(httpGet);            
            InputStream stream = resp.getEntity().getContent();                       
            
//            httpGet.releaseConnection();
            
            return stream;
        } 
        catch (IOException ex) {
            throw new ApplicationException(ex.getMessage());
        }               
    }

    public String getHttpInstanceUrl() throws ApplicationException {
        return ZENOSS_INSTANCE;
    }  
    
    @Override
    public JSONArray executeList(Router router, String method, Map data) throws ApplicationException {
        
        try {
            JSONObject obj = routerRequest(router.getAction(), router.getValue(), method, data);
            
            if(obj.get("result") != null && obj.get("result").toString().length() > 0) {
                
                JSONArray resultObj = (JSONArray) obj.get("result");
                return resultObj;
            } else {
                
                throw new ApplicationException(obj.get("msg").toString());
            }
        }
        catch (Exception ex) {
            throw new ApplicationException(ex.getMessage());
        }
    }
    
    //Remove it once done with API wrapping
    public JSONObject test(String router, String action, String method, Map data) throws Exception {
        return routerRequest(router, action, method, data);
    }      
}
