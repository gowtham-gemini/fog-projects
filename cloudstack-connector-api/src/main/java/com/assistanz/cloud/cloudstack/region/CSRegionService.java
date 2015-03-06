package com.assistanz.cloud.cloudstack.region;

import java.util.HashMap;
import java.util.LinkedList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
//import org.apache.http.NameValuePair;
//import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Santhosh
 *
 */
public class CSRegionService {

    private CloudStackServer server;

    public CSRegionService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds a Region
     *
     * @param regionId Id of the Region
     * @param regionEndPoint Region service endpoint
     * @param regionName Name of the region
     * @return
     * @throws Exception
     */
    public AddRegionResponse addRegion(String regionId, String regionEndPoint,
            String regionName)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addRegion", null);
        arguments.add(new NameValuePair("id", regionId));
        arguments.add(new NameValuePair("endpoint", regionEndPoint));
        arguments.add(new NameValuePair("name", regionName));

        Document responseDocument = server.makeRequest(arguments);

        return getAddRegionResponse(responseDocument);
    }

    /**
     * Converts XML document into AddRegionResponse object
     *
     * @param doc
     * @return
     */
    private AddRegionResponse getAddRegionResponse(Document doc) {
        AddRegionResponse response = new AddRegionResponse();

        // get id from XML and set the Id of the project 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get endpoint from XML and set the end point of the region
        list = doc.getElementsByTagName("endpoint");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndPoint(node.getTextContent());
        }

        // get gslbserviceenabled from XML and set true if GSLB service is enabled in the region, false otherwise
        list = doc.getElementsByTagName("gslbserviceenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGslbServiceEnabled(node.getTextContent());
        }

        // get name from XML and set the name of the region
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get portableipserviceenabled and set true if security groups support is enabled, false otherwise
        list = doc.getElementsByTagName("portableipserviceenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPortableIpServiceEnabled(node.getTextContent());
        }

        return response;
    }

    /**
     * Updates a region
     *
     * @param regionId Id of the Region
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateRegionResponse updateRegion(String regionId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateRegion", optional);
        arguments.add(new NameValuePair("id", regionId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateRegionResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateRegionResponse object
     *
     * @param doc
     * @return
     */
    private UpdateRegionResponse getUpdateRegionResponse(Document doc) {
        UpdateRegionResponse response = new UpdateRegionResponse();

        // get id from XML and set the Id of the project 
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get endpoint from XML and set the end point of the region
        list = doc.getElementsByTagName("endpoint");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setEndPoint(node.getTextContent());
        }

        // get gslbserviceenabled from XML and set true if GSLB service is enabled in the region, false otherwise
        list = doc.getElementsByTagName("gslbserviceenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setGslbServiceEnabled(node.getTextContent());
        }

        // get name from XML and set the name of the region
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get portableipserviceenabled and set true if security groups support is enabled, false otherwise
        list = doc.getElementsByTagName("portableipserviceenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPortableIpServiceEnabled(node.getTextContent());
        }

        return response;
    }

    /**
     * Removes specified region
     *
     * @param regionId ID of the region to delete
     * @return
     * @throws Exception
     */
    public RemoveRegionResponse removeRegion(String regionId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("removeRegion", null);
        arguments.add(new NameValuePair("id", regionId));

        Document responseDocument = server.makeRequest(arguments);

        return getRemoveRegionResponse(responseDocument);

    }

    /**
     * Converts XML document into RemoveRegionResponse object
     *
     * @param doc
     * @return
     */
    private RemoveRegionResponse getRemoveRegionResponse(Document doc) {
        RemoveRegionResponse response = new RemoveRegionResponse();

        /* get displaytext from XML and set Any text associated with the success or 
         failure  */
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }

        /* get success from XML and set Return true if it  
         is executed successfully */
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists Regions
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListRegionsResponse listRegions(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listRegions", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListRegionsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListRegionsResponse object
     *
     * @param doc
     * @return
     */
    private ListRegionsResponse getListRegionsResponse(Document doc) {
        ListRegionsResponse response = new ListRegionsResponse();

        NodeList list = doc.getElementsByTagName("region");

        List<RegionResponse> regions = new LinkedList<RegionResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node regionNode = list.item(Index);
            RegionResponse region = new RegionResponse();

            NodeList regionProperties = regionNode.getChildNodes();
            for (int childIndex = 0; childIndex < regionProperties.getLength(); childIndex++) {
                Node property = regionProperties.item(childIndex);

                if (property.getNodeName().equals("id")) {
                    region.setId(property.getTextContent());
                } else if (property.getNodeName().equals("endpoint")) {
                    region.setEndPoint(property.getTextContent());
                } else if (property.getNodeName().equals("gslbserviceenabled")) {
                    region.setGslbServiceEnabled(property.getTextContent());
                } else if (property.getNodeName().equals("name")) {
                    region.setName(property.getTextContent());
                } else if (property.getNodeName().equals("portableipserviceenabled")) {
                    region.setPortableIpServiceEnabled(property.getTextContent());
                }
            }
            regions.add(region);
        }

        response.setRegions(regions);
        return response;
    }

}
