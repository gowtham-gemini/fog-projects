package cs.api.tester

import com.fogpanel.connectors.*;
import com.sun.org.apache.xerces.internal.impl.XMLDocumentFragmentScannerImpl.Element
import groovy.xml.streamingmarkupsupport.BaseMarkupBuilder.Document;
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.apache.http.*;
import org.apache.http.NameValuePair;
//import org.apache.commons.httpclient.*;
//import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.httpclient.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity
//import org.apache.commons.httpclient.message.BasicNameValuePair;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import com.assistanz.cloud.cloudstack.serviceoffering.CSServiceOfferingService;
//import java.util.HashMap; 
//import java.util.*;

class AppController {

    def connect() {
        
        try {
            
            def apiURL = params.apiURL
            def apikey = params.apikey
            def secret = params.secret
                       
            CloudStackServer cs = new CloudStackServer(apiURL,secret,apikey);
            HashMap<String,String> optional = new HashMap<String,String>()
            LinkedList<NameValuePair> nvp = cs.getDefaultQuery(params.command, optional);
            println(params.command)
            params.remove("command")
            params.remove("apikey")
   
            params.each() { key, value ->
                if (key != "action" && key != "controller" && key != "apiCommand" && key != "secret" 
                    && key != "apikey" && key != "apiURL")
                    // optional.put(key, value)
                    nvp.add(new NameValuePair(key, value));
                }
            println(nvp)
            Element root = cs.makeRequest(nvp).getDocumentElement();
            DOMSource domSource = new DOMSource(root);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
            transformer.transform(domSource, result);
            render writer.toString();
                        
        }   catch(Exception e) {
                render e.message
        }
        
    }
    
    def api() {
        
        def path = params.service + "/" + params.apiCommand
        render ( view : "api",  model : [ path : path ] )
    }
  
}
