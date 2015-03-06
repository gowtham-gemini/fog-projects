package com.assistanz.cloud.cloudstack;

import com.assistanz.fogpanel.CloudStackException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import com.assistanz.fogpanel.DebugLogger;

/**
 * The
 * 
 * @author Gowtham
 */
public class CloudStackServer {
    private String secret;
    private String apikey;
    private String apiURL;
   
    /**
     * Creates a new Server Instance that could connect to the 
     * specified URL, with the provided key and secret
     * 
     * @param apiURL
     * @param secret
     * @param apikey 
     */
    public CloudStackServer(String apiURL, String secret, String apikey) {
        this.secret = secret;
        this.apikey = apikey;
        this.apiURL = apiURL;
    }
    
    /**
     * 
     * @param queryValues
     * @return
     * @throws Exception 
     */
    public Document makeRequest(LinkedList<NameValuePair> queryValues) throws Exception { 
        HttpMethod method = null;
        
        try {
            String querySignature = signRequest(queryValues);
            queryValues.add(new NameValuePair("signature", querySignature));
            method = new GetMethod(apiURL);
            method.setFollowRedirects(true);            
            method.setQueryString(queryValues.toArray(new NameValuePair[0]));                    
        } catch (Exception e) {
            throw new CloudStackException(e);
        }
        return getResponse(method);
    }
    
    /**
     * 
     * @param method
     * @return
     * @throws HttpException
     * @throws IOException
     * @throws Exception 
     */
    public Document getResponse(HttpMethod method) throws HttpException, 
            IOException, Exception{
        HttpClient client = new HttpClient();
        
        DebugLogger.printLog(method.getQueryString());
        DebugLogger.printLog(method.getPath());
        
//        System.out.println(method.getQueryString());
//        System.out.println(method.getPath());

        Document response = null;
        client.executeMethod(method);
        
        DebugLogger.printLog(method.getResponseBodyAsString());
//        System.out.println(method.getResponseBodyAsString());
        
        response = handleResponse(method.getResponseBodyAsStream());

        //clean up the connection resources
        method.releaseConnection();

        return response;
    }
        
    /**
     * 
     * @param queryValues
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.InvalidKeyException 
     */
    private String signRequest(LinkedList<NameValuePair> queryValues) throws 
            java.security.NoSuchAlgorithmException, 
            java.security.InvalidKeyException {
        Collections.sort(queryValues, new Comparator<NameValuePair>() {
            public int compare(NameValuePair o1, NameValuePair o2) {
            return o1.getName().compareTo(o2.getName());
            }
        });

        String queryString = EncodingUtil.formUrlEncode(queryValues.toArray(new NameValuePair[queryValues.size()]), "UTF-8").replace("+", "%20");
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(),"HmacSHA1");
        mac.init(secret_key);
        byte[] digest = mac.doFinal(queryString.toLowerCase().getBytes());

        return DatatypeConverter.printBase64Binary(digest); 
    }
    
    public LinkedList<NameValuePair> getDefaultQuery(String command, HashMap<String, String> optional) {
        LinkedList<NameValuePair> queryValues = new LinkedList<NameValuePair>();
        queryValues.add(new NameValuePair("command",command));
        queryValues.add(new NameValuePair("apiKey",apikey));
        if(optional != null) {
            Iterator optional_it = optional.entrySet().iterator();
            while(optional_it.hasNext()) {
            Map.Entry pairs = (Map.Entry)optional_it.next();
            queryValues.add(new NameValuePair((String)pairs.getKey(), 
                    (String)pairs.getValue()));
            }
        }
        return queryValues;
    }
    
    /**
     * 
     */
    public class CloudStackServerException extends Exception {
        CloudStackServerException(String errorcode, String errortext) {
            super(errorcode + ": " + errortext);
        }
    }
    
    /**
     * 
     * @param ResponseBody
     * @return
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws org.xml.sax.SAXException
     * @throws java.io.IOException
     * @throws com.assistanz.cloud.cloudstack.CloudStackServer.CloudStackServerException 
     */
    private Document handleResponse(InputStream responseBody) throws 
            javax.xml.parsers.ParserConfigurationException, 
            org.xml.sax.SAXException, 
            java.io.IOException, CloudStackServerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
        DebugLogger.printLog(responseBody);
//        System.out.println(responseBody);

        Document doc = dBuilder.parse(responseBody);
       
        DebugLogger.printLog(doc);
//        System.out.println(doc);
        
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();

        try {
            XPathExpression errorCodePath = xpath.compile("/*/errorcode/text()");
            XPathExpression errorTextPath = xpath.compile("/*/errortext/text()");

            String errorCode = (String)errorCodePath.evaluate(doc, XPathConstants.STRING);
            String errorText = (String)errorTextPath.evaluate(doc, XPathConstants.STRING);
        if(errorCode.length() > 0) {
            throw new CloudStackServerException(errorCode, errorText);
            }
        } catch(XPathExpressionException e) {
        // ignore, should never happen
            }

        return doc;
    }
}
