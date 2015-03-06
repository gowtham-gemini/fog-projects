package com.assistanz.cloud.cloudstack.baremetal;

import java.util.HashMap;
import java.util.LinkedList;

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
public class CSBaremetalService {

    private CloudStackServer server;

    public CSBaremetalService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * add a baremetal pxe server
     *
     * @param password Credentials to reach external pxe device
     * @param physicalNetworkId the Physical Network ID
     * @param pxeServerType type of pxe device
     * @param tftpDir Tftp root directory of PXE server
     * @param url URL of the external pxe device
     * @param userName Credentials to reach external pxe device
     * @param optional
     * @return
     * @throws Exception
     */
    public AddBaremetalPxeKickStartServerResponse addBaremetalPxeKickStartServer(String password,
            String physicalNetworkId, String pxeServerType, String tftpDir, String url, String userName,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addBaremetalPxeKickStartServer", optional);
        arguments.add(new NameValuePair("password", password));
        arguments.add(new NameValuePair("physicalnetworkid", physicalNetworkId));
        arguments.add(new NameValuePair("pxeservertype", pxeServerType));
        arguments.add(new NameValuePair("tftpdir", tftpDir));
        arguments.add(new NameValuePair("url", url));
        arguments.add(new NameValuePair("username", userName));

        Document responseDocument = server.makeRequest(arguments);

        return getAddBaremetalPxeKickStartServerResponse(responseDocument);
    }

    /**
     * Converts XML document into AddBaremetalPxeKickStartServerResponse object
     *
     * @param doc
     * @return
     */
    private AddBaremetalPxeKickStartServerResponse getAddBaremetalPxeKickStartServerResponse(Document doc) {
        AddBaremetalPxeKickStartServerResponse response = new AddBaremetalPxeKickStartServerResponse();

        // get tftpdir from XML and set as Tftp root directory of PXE server
        NodeList list = doc.getElementsByTagName("tftpdir");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTftpDir(node.getTextContent());
        }

        return response;

    }

    /**
     * add a baremetal ping pxe server
     *
     * @param password Credentials to reach external pxe device
     * @param physicalNetworkId the Physical Network ID
     * @param pingDir Root directory on PING storage server
     * @param pingStorageServerIp PING storage server ip
     * @param pxeServerType type of pxe device
     * @param tftpDir Tftp root directory of PXE server
     * @param url URL of the external pxe device
     * @param userName Credentials to reach external pxe device
     * @param optional
     * @return
     * @throws Exception
     */
    public AddBaremetalPxePingServerResponse addBaremetalPxePingServer(String password,
            String physicalNetworkId, String pingDir, String pingStorageServerIp, String pxeServerType,
            String tftpDir, String url, String userName, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addBaremetalPxePingServer", optional);
        arguments.add(new NameValuePair("password", password));
        arguments.add(new NameValuePair("physicalnetworkid", physicalNetworkId));
        arguments.add(new NameValuePair("pingdir", pingDir));
        arguments.add(new NameValuePair("pingstorageserverip", pingStorageServerIp));
        arguments.add(new NameValuePair("pxeservertype", pxeServerType));
        arguments.add(new NameValuePair("tftpdir", tftpDir));
        arguments.add(new NameValuePair("url", url));
        arguments.add(new NameValuePair("username", userName));

        Document responseDocument = server.makeRequest(arguments);

        return getAddBaremetalPxePingServerResponse(responseDocument);
    }

    /**
     * Converts XML document into AddBaremetalPxePingServerResponse object
     *
     * @param doc
     * @return
     */
    private AddBaremetalPxePingServerResponse getAddBaremetalPxePingServerResponse(Document doc) {
        AddBaremetalPxePingServerResponse response = new AddBaremetalPxePingServerResponse();

        // get pingdir from XML and set as Root directory on PING storage server
        NodeList list = doc.getElementsByTagName("pingdir");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPingDir(node.getTextContent());
        }

        // get pingstorageserverip from XML and set PING storage server ip
        list = doc.getElementsByTagName("pingstorageserverip");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPingStorageServerIp(node.getTextContent());
        }

        // get tftpdir from XML and set as Tftp root directory of PXE server
        list = doc.getElementsByTagName("tftpdir");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTftpDir(node.getTextContent());
        }

        return response;

    }

    /**
     * adds a baremetal dhcp server
     *
     * @param dhcpServerType Type of dhcp device
     * @param password Credentials to reach external pxe device
     * @param physicalNetworkId the Physical Network ID
     * @param url URL of the external pxe device
     * @param userName Credentials to reach external pxe device
     * @param optional
     * @return
     * @throws Exception
     */
    public AddBaremetalDhcpResponse addBaremetalDhcp(String dhcpServerType, String password,
            String physicalNetworkId, String url, String userName,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addBaremetalDhcp", optional);
        arguments.add(new NameValuePair("dhcpservertype", dhcpServerType));
        arguments.add(new NameValuePair("password", password));
        arguments.add(new NameValuePair("physicalnetworkid", physicalNetworkId));
        arguments.add(new NameValuePair("url", url));
        arguments.add(new NameValuePair("username", userName));

        Document responseDocument = server.makeRequest(arguments);

        return getAddBaremetalDhcpResponse(responseDocument);
    }

    /**
     * Converts XML document into AddBaremetalDhcpResponse object
     *
     * @param doc
     * @return
     */
    private AddBaremetalDhcpResponse getAddBaremetalDhcpResponse(Document doc) {
        AddBaremetalDhcpResponse response = new AddBaremetalDhcpResponse();

        // get id from XML and set as device id of
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get dhcpservertype from XML and set name of the provider
        list = doc.getElementsByTagName("dhcpservertype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDhcpServerType(node.getTextContent());
        }

        // get physicalnetworkid from XML and set as the physical network to which this external dhcp device belongs to
        list = doc.getElementsByTagName("physicalnetworkid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPhysicalNetworkId(node.getTextContent());
        }

        // get provider from XML and set as the name of the provider
        list = doc.getElementsByTagName("provider");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setProvider(node.getTextContent());
        }

        // get url from XML and set url
        list = doc.getElementsByTagName("url");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUrl(node.getTextContent());
        }

        return response;

    }

    /**
     * list baremetal dhcp servers
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListBaremetalDhcpsResponse listBaremetalDhcp(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listBaremetalDhcp", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListBaremetalDhcpsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListBaremetalDhcpsResponse object
     *
     * @param doc
     * @return
     */
    private ListBaremetalDhcpsResponse getListBaremetalDhcpsResponse(Document doc) {
        ListBaremetalDhcpsResponse response = new ListBaremetalDhcpsResponse();

        NodeList list = doc.getElementsByTagName("baremetaldhcp");

        List<BaremetalDhcpResponse> baremetalDhcps = new LinkedList<BaremetalDhcpResponse>();
        if (list.getLength() > 0) {
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node baremetalDhcpNode = list.item(Index);

                BaremetalDhcpResponse baremetalDhcp = new BaremetalDhcpResponse();

                NodeList baremetalDhcpProperties = baremetalDhcpNode.getChildNodes();
                for (int childIndex = 0; childIndex < baremetalDhcpProperties.getLength(); childIndex++) {
                    Node property = baremetalDhcpProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        baremetalDhcp.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("dhcpservertype")) {
                        baremetalDhcp.setDhcpServerType(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        baremetalDhcp.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                        baremetalDhcp.setProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("url")) {
                        baremetalDhcp.setUrl(property.getTextContent());
                    }
                }
                baremetalDhcps.add(baremetalDhcp);
            }
        }
        response.setBaremetalDhcps(baremetalDhcps);
        return response;
    }

    /**
     * list baremetal pxe server
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListBaremetalPxeServersResponse listBaremetalPxeServers(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listBaremetalPxeServers", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListBaremetalPxeServersResponse(responseDocument);
    }

    /**
     * Converts XML document into ListBaremetalPxeServersResponse object
     *
     * @param doc
     * @return
     */
    private ListBaremetalPxeServersResponse getListBaremetalPxeServersResponse(Document doc) {
        ListBaremetalPxeServersResponse response = new ListBaremetalPxeServersResponse();

        NodeList list = doc.getElementsByTagName("baremetalpxeserver");

        List<BaremetalPxeServerResponse> baremetalPxeServers = new LinkedList<BaremetalPxeServerResponse>();
        if (list.getLength() > 0) {
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node baremetalPxeServerNode = list.item(Index);

                BaremetalPxeServerResponse baremetalPxeServer = new BaremetalPxeServerResponse();

                NodeList baremetalPxeServerProperties = baremetalPxeServerNode.getChildNodes();
                for (int childIndex = 0; childIndex < baremetalPxeServerProperties.getLength(); childIndex++) {
                    Node property = baremetalPxeServerProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        baremetalPxeServer.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("physicalnetworkid")) {
                        baremetalPxeServer.setPhysicalNetworkId(property.getTextContent());
                    } else if (property.getNodeName().equals("provider")) {
                        baremetalPxeServer.setProvider(property.getTextContent());
                    } else if (property.getNodeName().equals("url")) {
                        baremetalPxeServer.setUrl(property.getTextContent());
                    }
                }
                baremetalPxeServers.add(baremetalPxeServer);
            }
        }
        response.setBaremetalPxeServers(baremetalPxeServers);
        return response;
    }

}
