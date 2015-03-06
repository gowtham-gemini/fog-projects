/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.fogpanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.codehaus.groovy.grails.web.json.JSONArray;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 *
 * @author Az-PHP
 */
public class UsageServer {
    
    private final String USER_AGENT = "Mozilla/5.0";
    
    private static UsageServer usageServer = new UsageServer();
   
    /* A private Constructor prevents any other 
     * class from instantiating.
     */
    private UsageServer(){ }

    /* Static 'instance' method */
    public static UsageServer getInstance( ) {
       return usageServer;
    }
    /* Other methods protected by singleton-ness */
    protected static void demoMethod( ) {
       System.out.println("demoMethod for singleton"); 
    }
    
    protected void setConfig() throws Exception {
      
    }
    
    protected UsageResponse getData(String vmName)throws Exception {
        UsageResponse usageResponse = new UsageResponse();

        Socket pingSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            pingSocket = new Socket("cloudstack.assistanz.com", 8649);
            out = new PrintWriter(pingSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(pingSocket.getInputStream()));
        } catch (IOException e) {
            
        }
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document  document = builder.parse(new InputSource(new StringReader(response.toString())));

        
        XPath xPath =  XPathFactory.newInstance().newXPath();
        String memeoryExpression = "/GANGLIA_XML/CLUSTER/HOST/METRIC[@NAME='" + vmName + ".vmem_util']";
        String cpuExpression = "/GANGLIA_XML/CLUSTER/HOST/METRIC[@NAME='" + vmName + ".vcpu_util']";
        String diskReadExpression = "/GANGLIA_XML/CLUSTER/HOST/METRIC[@NAME='" + vmName + ".vdisk_reads']";
        String diskWriteExpression = "/GANGLIA_XML/CLUSTER/HOST/METRIC[@NAME='" + vmName + ".vdisk_writes']";
        String netInExpression = "/GANGLIA_XML/CLUSTER/HOST/METRIC[@NAME='" + vmName + ".vbytes_in']";
        String netOutWeiteExpression = "/GANGLIA_XML/CLUSTER/HOST/METRIC[@NAME='" + vmName + ".vbytes_out']";

        //read an xml node using xpath
        Node memoryNode = (Node) xPath.compile(memeoryExpression).evaluate(document, XPathConstants.NODE);
        Node cpuNode = (Node) xPath.compile(cpuExpression).evaluate(document, XPathConstants.NODE);
        Node diskWriteNode = (Node) xPath.compile(diskWriteExpression).evaluate(document, XPathConstants.NODE);
        Node diskReadyNode = (Node) xPath.compile(diskReadExpression).evaluate(document, XPathConstants.NODE);
        Node netInNode = (Node) xPath.compile(netInExpression).evaluate(document, XPathConstants.NODE);
        Node netOutNode = (Node) xPath.compile(netOutWeiteExpression).evaluate(document, XPathConstants.NODE);
        
        
        usageResponse.setCpuUtil(cpuNode.getAttributes().getNamedItem("VAL").getTextContent());
        usageResponse.setDiskRead(diskReadyNode.getAttributes().getNamedItem("VAL").getTextContent());
        usageResponse.setDiskWrite(diskWriteNode.getAttributes().getNamedItem("VAL").getTextContent());
        usageResponse.setNetworkRead(netOutNode.getAttributes().getNamedItem("VAL").getTextContent());
        usageResponse.setNetworkWrite(netInNode.getAttributes().getNamedItem("VAL").getTextContent());
        usageResponse.setMemeoryUtil(memoryNode.getAttributes().getNamedItem("VAL").getTextContent());
        
        out.close();
        in.close();
        pingSocket.close();
        return usageResponse;
    }
}
