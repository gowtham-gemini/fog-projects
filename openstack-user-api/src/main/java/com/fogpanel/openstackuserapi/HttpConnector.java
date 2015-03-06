/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fogpanel.openstackuserapi;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.client.HttpClient;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author gowtham
 */
public class HttpConnector {
    
    /** Logger is to provide log messages. */
    private static final Logger LOGGER =
            Logger.getLogger(HttpConnector.class.getName());
    private static final String USER_AGENT = "FogPanel";
              
     /**
     * DisableSSL.
     */
    private void disableSSL() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] { 
                new X509TrustManager() {     
                    public java.security.cert.
                        X509Certificate[] getAcceptedIssuers() { 
                        return null;
                    } 
                    public void checkClientTrusted(
                            final java.security.cert.X509Certificate[] certs,
                            final String authType) {
                        } 
                    public void checkServerTrusted(
                            final java.security.cert.X509Certificate[] certs, 
                            final String authType) {
                    }
                } 
            };
            SSLContext sc = SSLContext.getInstance("SSL"); 
            sc.init(null, trustAllCerts, new java.security.SecureRandom()); 

            HttpsURLConnection.setDefaultSSLSocketFactory(
                    sc.getSocketFactory());
            
            
//            javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
//                new javax.net.ssl.HostnameVerifier(){
//
//                    public boolean verify(String hostname,
//                            javax.net.ssl.SSLSession sslSession) {
//                        if (hostname.equals("license.fogpanel.com")) {
//                            return true;
//                        }
//                        return false;
//                    }
//                });
                        
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, 
                    "Unable to disable ssl verification: {0} ", ex);
        }
    }
     
    
    /**
     * Send a Post request for given URL
     * 
     * @param url the url of the openstack server
     * @param data the data parameter
     * @param token the X-Auth-Token
     * @return empty
     */
    public String simplePost(URL url, String data, String token) throws URISyntaxException, IOException {
        
        try {
            HttpClient connection = new DefaultHttpClient();

            HttpPost request = new HttpPost(url.toURI());

            StringEntity entity = new StringEntity(data, "UTF-8");

            request.addHeader("User-Agent", USER_AGENT);
            request.addHeader("Accept-Language", "en-US,en;q=0.5");
            request.addHeader("X-Auth-Token", token);
            request.addHeader("Content-Type", "application/json");
            request.setEntity(entity);
    //            request();
            HttpResponse response = connection.execute(request);

            System.out.println("staruscoede" + response.getStatusLine().getStatusCode());

            BufferedReader inStream;
            inStream = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String inputLine;
            StringBuilder result = new StringBuilder();

            while ((inputLine = inStream.readLine()) != null) {
                result.append(inputLine);
            }
            inStream.close();
            
            if(response.getStatusLine().getStatusCode() == 200) {
                return "Success";
            }        
            
            return result.toString();
            
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, 
                    "Unable to connect to server  " + url.toString(), ex);
        } catch (Exception ex ) {
            throw ex;
        }
        return "";
    }
    
    /**
     * Send a patch request for given URL
     * 
     * @param url
     * @param data
     * @param token
     * @return 
     */
    public String simplePatch(URL url, String data, String token) throws URISyntaxException {

        try {
            
            System.out.println("url" + url.toString());
            
            HttpClient connection = new DefaultHttpClient();

            HttpPatch request = new HttpPatch(url.toURI());
            
            
            
//            if (properties != null && properties.
//                    getProperty("ssl.noverify") != null && properties.
//                            getProperty("ssl.noverify").equals("true")) {
//                LOGGER.log(Level.SEVERE, "SSL VERIFICATION OFF !!!!");
//                disableSSL();
//            } else {
//                LOGGER.log(Level.SEVERE, "SSL VERIFICATION ON !!!!");
//            }

//            if (url.getProtocol().equals("https")) {
//                connection = (HttpsURLConnection) url.openConnection();
//            } else {
//                connection = (HttpURLConnection) url.openConnection();
//            }

//            request.setRequestMethod("POST");
            
            StringEntity entity = new StringEntity(data, "UTF-8");
            
            request.addHeader("User-Agent", USER_AGENT);
            request.addHeader("Accept-Language", "en-US,en;q=0.5");
            request.addHeader("X-Auth-Token", token);
            request.addHeader("Content-Type", "application/json");
            request.setEntity(entity);
//            request();
            HttpResponse response = connection.execute(request);
            
            System.out.println("staruscoede" + response.getStatusLine().getStatusCode());
                        
            BufferedReader inStream;
            inStream = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String inputLine;
            StringBuilder result = new StringBuilder();

            while ((inputLine = inStream.readLine()) != null) {
                result.append(inputLine);
            }
            inStream.close();
            
            
//            if(response.getStatusLine().getStatusCode() == 401 || response.getStatusLine().getStatusCode() == 405 || response.getStatusLine().getStatusCode() == 403) {
//                return "You are not authorized to perform the requested action";
//            } else if(response.getStatusLine().getStatusCode() == 400 ) {
//                return "Bad request";
//            } else if(response.getStatusLine().getStatusCode() == 413) {
//                return "Request Entity Too Large";
//            } else if(response.getStatusLine().getStatusCode() == 404) {
//                return "Not Found";
//            } else if(response.getStatusLine().getStatusCode() == 404) {
//                return "Service Unavailable";
//            } else if(response.getStatusLine().getStatusCode() == 200) {
//                return "Success";
//            }      
//            Gson gson = new Gson();
//            String json = gson.toJson(result.toString());
            
            if(response.getStatusLine().getStatusCode() == 200) {
                return "Success";
            }        
            
            return result.toString();
            

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, 
                    "Unable to connect to server  " + url.toString(), ex);
        } catch (Exception ex ) {
            throw ex;
        }
        return "";
    
     }
     
    /**
     * Send a PUT request for given URL
     * 
     * @param url
     * @param data
     * @param token
     * @return 
     */ 
    public String simplePut(URL url, String data, String token) throws URISyntaxException {
        try {
            HttpClient connection = new DefaultHttpClient();

            HttpPut request = new HttpPut(url.toURI());

//            if (properties != null && properties.
//                    getProperty("ssl.noverify") != null && properties.
//                            getProperty("ssl.noverify").equals("true")) {
//                LOGGER.log(Level.SEVERE, "SSL VERIFICATION OFF !!!!");
//                disableSSL();
//            } else {
//                LOGGER.log(Level.SEVERE, "SSL VERIFICATION ON !!!!");
//            }
            
            StringEntity entity = new StringEntity(data, "UTF-8");
            
//            System.out.println("url" + url.toString());
            request.addHeader("User-Agent", USER_AGENT);
            request.addHeader("Accept-Language", "en-US,en;q=0.5");
            request.addHeader("X-Auth-Token", token);
            request.addHeader("Content-Type", "application/json");
            request.setEntity(entity);
//            request();
            HttpResponse response = connection.execute(request);
            
            System.out.println("staruscoede" + response.getStatusLine().getStatusCode());
                        
            BufferedReader inStream;
            inStream = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            String inputLine;
            StringBuilder result = new StringBuilder();

            while ((inputLine = inStream.readLine()) != null) {
                result.append(inputLine);
            }
            inStream.close();
            
            if(response.getStatusLine().getStatusCode() == 200) {
                return "Success";
            }        
            
            return result.toString();

        } catch (IOException ex) {
            
            System.out.println("EX" + ex);
            
            LOGGER.log(Level.SEVERE, 
                    "Unable to connect to server  " + url.toString(), ex);
        }
     
     return "";
     
     }
    
}
