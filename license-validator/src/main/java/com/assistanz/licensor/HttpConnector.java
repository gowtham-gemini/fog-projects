package com.assistanz.licensor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author sujaisd
 */
class HttpConnector {

    /** Logger is to provide log messages. */
    private static final Logger LOGGER =
            Logger.getLogger(HttpConnector.class.getName());
    private URL url;
    private static final String USER_AGENT = "FogPanel";
    private String version;
    private Properties properties;

    /**
     * 
     * @param url the HTTP URL 
     * @param version the version 
     * @throws MalformedURLException if malformed URL has occurred
     */
    public HttpConnector(final String url, final String version) 
            throws MalformedURLException {
        this.url = new URL(url);
        this.version = version;
    }
 
    /**
     * 
     * @param url the HTTP URL 
     * @param version the version 
     * @param prop the properties 
     * @throws MalformedURLException if malformed URL has occurred
     */
    public HttpConnector(final String url, final String version, 
            final Properties prop) throws MalformedURLException {
        this.url = new URL(url);
        this.version = version;
        this.properties = prop;
    }

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
     * 
     * @param data the data parameter
     * @return empty
     */
    public String simplePost(final String data) {
        try {
            HttpURLConnection connection;

            if (properties != null && properties.
                    getProperty("ssl.noverify") != null && properties.
                            getProperty("ssl.noverify").equals("true")) {
                LOGGER.log(Level.SEVERE, "SSL VERIFICATION OFF !!!!");
                disableSSL();
            } else {
                LOGGER.log(Level.SEVERE, "SSL VERIFICATION ON !!!!");
            }

            if (url.getProtocol().equals("https")) {
                connection = (HttpsURLConnection) url.openConnection();
            } else {
                connection = (HttpURLConnection) url.openConnection();
            }

            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            connection.setRequestProperty("X-FOG-VERSION", version);
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);
            DataOutputStream writer = 
                    new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(data);
            writer.flush();
            writer.close();

            int responseCode = connection.getResponseCode();
            LOGGER.log(Level.FINER, "Connecting to {0}", url.toString());
            LOGGER.log(Level.FINER, "Response Code : {0}", responseCode);

            BufferedReader inStream = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = inStream.readLine()) != null) {
                response.append(inputLine);
            }
            inStream.close();

            return response.toString();

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, 
                    "Unable to connect to server  " + url.toString(), ex);
        }

        return "";
    }

}
