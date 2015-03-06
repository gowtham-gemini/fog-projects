package com.assistanz.cloud.cloudstack.logout;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assistanz.cloud.cloudstack.CloudStackServer;

/**
 *
 * @author Gowtham
 *
 */
public class CSLogoutService {

    private CloudStackServer server;

    public CSLogoutService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Logs out the user
     *
     * @return
     */
    public LogoutServiceResponse logout() {
        return getLogoutServiceResponse(null);
    }

    /**
     * Converts XML document into Logoutserviceresponse object
     *
     * @param doc
     * @return
     */
    private LogoutServiceResponse getLogoutServiceResponse(Document doc) {
        LogoutServiceResponse response = new LogoutServiceResponse();

        // get description from XML and set success if the logout action succeeded
        NodeList list = doc.getElementsByTagName("description");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDescription(node.getTextContent());
        }

        return response;
    }
}
