
package com.assistanz;

import java.io.ByteArrayInputStream;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XmlParse {
    
    String result;
    public String parseXml(String response) {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(response.getBytes());
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = (Document) builder.parse(stream);
            XPath xPath =  XPathFactory.newInstance().newXPath();
            
            //System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
            NodeList listOfTickets = doc.getElementsByTagName("tickets");
            int totalTickets = listOfTickets.getLength();
            //System.out.println("Total no of Tickets : " + totalTickets);
            
            for(int i=0; i<listOfTickets.getLength() ; i++) {
                Node firstTicketNode = listOfTickets.item(i);
                if(firstTicketNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element firstElement = (Element)firstTicketNode;                              
                    NodeList firstNameList = firstElement.getElementsByTagName("ticketid");
                    Element firstNameElement = (Element)firstNameList.item(0);
                    NodeList textFNList = firstNameElement.getChildNodes();
                    result = ((Node)textFNList.item(0)).getNodeValue().trim();
                    //System.out.println("Ticket Id : " + ((Node)textFNList.item(0)).getNodeValue().trim());
                }
            }
        } catch (SAXParseException err)  {
              System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
              System.out.println(" " + err.getMessage ());
        } catch (SAXException e) {
                Exception x = e.getException ();
                ((x == null) ? e : x).printStackTrace ();
        } catch (Throwable t) {
                t.printStackTrace ();
        }
        return result;
    }
}