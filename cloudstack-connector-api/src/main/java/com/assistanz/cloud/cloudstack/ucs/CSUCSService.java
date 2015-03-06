package com.assistanz.cloud.cloudstack.ucs;

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
 * @author Santhosh
 *
 */
public class CSUCSService {

    private CloudStackServer server;

    public CSUCSService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds a Ucs manager.
     *
     * @param ucsPassword
     * @param ucsUrl
     * @param ucsUserName
     * @param ucsZoneId
     * @param optional
     * @return
     * @throws Exception
     */
    public AddUcsManagerResponse addUcsManager(String ucsPassword, String ucsUrl,
            String ucsUserName, String ucsZoneId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addUcsManager", optional);
        arguments.add(new NameValuePair("password", ucsPassword));
        arguments.add(new NameValuePair("url", ucsUrl));
        arguments.add(new NameValuePair("username", ucsUserName));
        arguments.add(new NameValuePair("zoneid", ucsZoneId));

        Document responseDocument = server.makeRequest(arguments);

        return getAddUcsManagerResponse(responseDocument);
    }

    /**
     * Converts XML document into AddUcsManagerResponse object
     *
     * @param doc
     * @return
     */
    private AddUcsManagerResponse getAddUcsManagerResponse(Document doc) {
        AddUcsManagerResponse response = new AddUcsManagerResponse();

        // get id from XML and set as the ID of the ucs manager
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get name from XML and set as the name of ucs manager
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get url from XML and set as the url of ucs manager
        list = doc.getElementsByTagName("url");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUrl(node.getTextContent());
        }

        // get zoneid from XML and set as the zone ID of ucs manager
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setZoneId(node.getTextContent());
        }

        return response;
    }

    /**
     * List ucs manager
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListUcsManagersResponse listUcsManagers(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listUcsManagers", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListUcsManagersResponse(responseDocument);
    }

    /**
     * Converts XML document into ListUcsManagersResponse object
     *
     * @param doc
     * @return
     */
    private ListUcsManagersResponse getListUcsManagersResponse(Document doc) {
        ListUcsManagersResponse response = new ListUcsManagersResponse();

        //List ucs manager
        NodeList list = doc.getElementsByTagName("ucsmanager");

        List<UcsManagerResponse> ucsManagers = new LinkedList<UcsManagerResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node ucsManagerNode = list.item(index);
                UcsManagerResponse ucsManager = new UcsManagerResponse();

                NodeList ucsManagerProperties = ucsManagerNode.getChildNodes();
                for (int childIndex = 0; childIndex < ucsManagerProperties.getLength(); childIndex++) {
                    Node property = ucsManagerProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        ucsManager.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        ucsManager.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("url")) {
                        ucsManager.setUrl(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                        ucsManager.setZoneId(property.getTextContent());
                    }
                }
                ucsManagers.add(ucsManager);
            }
        }
        response.setUcsManagers(ucsManagers);
        return response;
    }

    /**
     * List profile in ucs manager
     *
     * @param ucsManagerId
     * @param optional
     * @return
     * @throws Exception
     */
    public ListUcsProfilesResponse listUcsProfiles(String ucsManagerId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listUcsProfiles", optional);
        arguments.add(new NameValuePair("ucsmanagerid", ucsManagerId));

        Document responseDocument = server.makeRequest(arguments);

        return getListUcsProfilesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListUcsProfilesResponse object
     *
     * @param doc
     * @return
     */
    private ListUcsProfilesResponse getListUcsProfilesResponse(Document doc) {
        ListUcsProfilesResponse response = new ListUcsProfilesResponse();

        //List ucs manager
        NodeList list = doc.getElementsByTagName("ucsprofile");

        List<UcsProfileResponse> ucsProfiles = new LinkedList<UcsProfileResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node ucsProfileNode = list.item(index);
                UcsProfileResponse ucsProfile = new UcsProfileResponse();

                NodeList ucsProfileProperties = ucsProfileNode.getChildNodes();
                for (int childIndex = 0; childIndex < ucsProfileProperties.getLength(); childIndex++) {
                    Node property = ucsProfileProperties.item(childIndex);

                    if (property.getNodeName().equals("ucsdn")) {
                        ucsProfile.setUcsDn(property.getTextContent());
                    }
                }
                ucsProfiles.add(ucsProfile);
            }
        }
        response.setUcsProfiles(ucsProfiles);
        return response;
    }

    /**
     * List ucs blades
     *
     * @param ucsManagerId
     * @param optional
     * @return
     * @throws Exception
     */
    public ListUcsBladesResponse listUcsBlades(String ucsManagerId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listUcsBlades", optional);
        arguments.add(new NameValuePair("ucsmanagerid", ucsManagerId));

        Document responseDocument = server.makeRequest(arguments);

        return getListUcsBladesResponse(responseDocument);
    }

    /**
     * Converts XML document into ListUcsBladesResponse object
     *
     * @param doc
     * @return
     */
    private ListUcsBladesResponse getListUcsBladesResponse(Document doc) {
        ListUcsBladesResponse response = new ListUcsBladesResponse();

        //List ucs blades
        NodeList list = doc.getElementsByTagName("ucsblade");

        List<UcsBladeResponse> ucsBlades = new LinkedList<UcsBladeResponse>();

        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node ucsBladeNode = list.item(index);
                UcsBladeResponse ucsBlade = new UcsBladeResponse();

                NodeList ucsBladeProperties = ucsBladeNode.getChildNodes();
                for (int childIndex = 0; childIndex < ucsBladeProperties.getLength(); childIndex++) {
                    Node property = ucsBladeProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        ucsBlade.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("bladedn")) {
                        ucsBlade.setBladeDn(property.getTextContent());
                    } else if (property.getNodeName().equals("hostid")) {
                        ucsBlade.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("profiledn")) {
                        ucsBlade.setProfileDn(property.getTextContent());
                    } else if (property.getNodeName().equals("ucsmanagerid")) {
                        ucsBlade.setUcsManagerId(property.getTextContent());
                    }
                }
                ucsBlades.add(ucsBlade);
            }
        }
        response.setUcsBlades(ucsBlades);
        return response;
    }

    /**
     * associate a profile to a blade.
     *
     * @param bladeId blade id
     * @param profileDn profile dn
     * @param userManagerId ucs manager id
     * @param optional
     * @return
     * @throws Exception
     */
    public AssociateUcsProfileToBladeResponse associateUcsProfileToBlade(String bladeId, String profileDn,
            String userManagerId, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("associateUcsProfileToBlade", optional);
        arguments.add(new NameValuePair("bladeid", bladeId));
        arguments.add(new NameValuePair("profiledn", profileDn));
        arguments.add(new NameValuePair("usermanagerid", userManagerId));

        Document responseDocument = server.makeRequest(arguments);

        return getAssociateUcsProfileToBladeResponse(responseDocument);
    }

    /**
     * Converts XML document into AssociateUcsProfileToBladeResponse object
     *
     * @param doc
     * @return
     */
    private AssociateUcsProfileToBladeResponse getAssociateUcsProfileToBladeResponse(Document doc) {
        AssociateUcsProfileToBladeResponse response = new AssociateUcsProfileToBladeResponse();

        // get id from XML and set as the ucs blade id
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get bladedn from XML and set as the ucs blade dn
        list = doc.getElementsByTagName("bladedn");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBladeDn(node.getTextContent());
        }

        // get hostid from XML and set as cloudstack host id this blade associates to
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get profiledn from XML and set as the associated ucs profile dn
        list = doc.getElementsByTagName("profiledn");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProfileDn(node.getTextContent());
        }

        // get ucsmanagerid from XML and set as ucs manager id
        list = doc.getElementsByTagName("ucsmanagerid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUcsManagerId(node.getTextContent());
        }

        return response;
    }

}
