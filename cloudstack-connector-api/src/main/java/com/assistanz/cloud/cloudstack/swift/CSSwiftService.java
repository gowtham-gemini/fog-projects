package com.assistanz.cloud.cloudstack.swift;

import java.util.HashMap;
import java.util.LinkedList;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Gowtham
 *
 */
public class CSSwiftService {

    private CloudStackServer server;

    public CSSwiftService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds Swift.
     *
     * @param swiftUrl the URL for swift
     * @param optional
     * @return
     * @throws Exception
     */
    public AddSwiftResponse addSwift(String swiftUrl,
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addSwift", optional);
        arguments.add(new NameValuePair("url", swiftUrl));

        Document responseDocument = server.makeRequest(arguments);

        return getAddSwiftResponse(responseDocument);
    }

    /**
     * Converts XML document into AddSwiftResponse object
     *
     * @param doc
     * @return
     */
    private AddSwiftResponse getAddSwiftResponse(Document doc) {
        AddSwiftResponse response = new AddSwiftResponse();

        // get id from XML and set the ID of the image store
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get details from XML and set the details of the image store
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get name from XML and set the name of the image store
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get protocol from XML and set the protocol of the image store
        list = doc.getElementsByTagName("protocol");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProtocol(node.getTextContent());
        }

        // get providername from XML and set as the provider name of the image store
        list = doc.getElementsByTagName("providername");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProviderName(node.getTextContent());
        }

        // get scope from XML and set as the scope of the image store
        list = doc.getElementsByTagName("scope");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setScope(node.getTextContent());
        }

        // get url from XML and set the url of the image store
        list = doc.getElementsByTagName("url");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUrl(node.getTextContent());
        }

        // get zoneid from XML and set the zoneid of the image store
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        // get zonename from XML and set the zonename of the image store
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneName(node.getTextContent());
        }

        return response;

    }

    /**
     * List Swift.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListSwiftsResponse listSwifts(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listSwifts", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListSwiftsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListSwiftsResponse object
     *
     * @param doc
     * @return
     */
    private ListSwiftsResponse getListSwiftsResponse(Document doc) {
        ListSwiftsResponse response = new ListSwiftsResponse();

        NodeList list = doc.getElementsByTagName("swift");

        List<SwiftResponse> swifts = new LinkedList<SwiftResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node swiftNode = list.item(Index);
            SwiftResponse swift = new SwiftResponse();

            NodeList swiftProperties = swiftNode.getChildNodes();
            for (int childIndex = 0; childIndex < swiftProperties.getLength(); childIndex++) {
                Node property = swiftProperties.item(childIndex);

                if (property.getNodeName().equals("id")) {
                    swift.setId(property.getTextContent());
                } else if (property.getNodeName().equals("details")) {
                    swift.setDetails(property.getTextContent());
                } else if (property.getNodeName().equals("name")) {
                    swift.setName(property.getTextContent());
                } else if (property.getNodeName().equals("protocol")) {
                    swift.setProtocol(property.getTextContent());
                } else if (property.getNodeName().equals("providername")) {
                    swift.setProviderName(property.getTextContent());
                } else if (property.getNodeName().equals("scope")) {
                    swift.setScope(property.getTextContent());
                } else if (property.getNodeName().equals("url")) {
                    swift.setUrl(property.getTextContent());
                } else if (property.getNodeName().equals("zoneid")) {
                    swift.setZoneId(property.getTextContent());
                } else if (property.getNodeName().equals("zonename")) {
                    swift.setZoneName(property.getTextContent());
                }
            }
            swifts.add(swift);
        }

        response.setSwifts(swifts);
        return response;
    }
}
