package com.assistanz.cloud.cloudstack.domain;

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
public class CSDomainService {

    private CloudStackServer server;

    public CSDomainService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Creates a domain
     *
     * @param domainName creates domain with this name
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateDomainResponse createDomain(String domainName,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createDomain", optional);
        arguments.add(new NameValuePair("name", domainName));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateDomainResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateDomainResponse object
     *
     * @param doc
     * @return
     */
    private CreateDomainResponse getCreateDomainResponse(Document doc) {
        CreateDomainResponse response = new CreateDomainResponse();

        // get id from XML and set as the ID of the domain
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get haschild from XML and set whether the domain has one or more sub-domains
        list = doc.getElementsByTagName("haschild");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasChild(node.getTextContent());
        }

        // get level from XML and set the level of the domain
        list = doc.getElementsByTagName("level");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLevel(node.getTextContent());
        }

        // get name from XML and set the name of the domain
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get parentdomainid from XML and set the domain ID of the parent domain
        list = doc.getElementsByTagName("parentdomainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setParentDomainId(node.getTextContent());
        }

        // get parentdomainname from XML and set the domain name of the parent domain
        list = doc.getElementsByTagName("parentdomainname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setParentDomainName(node.getTextContent());
        }

        // get path from XML and set the path of the domain
        list = doc.getElementsByTagName("path");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPath(node.getTextContent());
        }

        return response;

    }

    /**
     * Updates a domain with a new name
     *
     * @param domainId The ID of domain to update
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateDomainResponse updateDomain(String domainId,
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("updateDomain", optional);
        arguments.add(new NameValuePair("id", domainId));

        Document responseDocument = server.makeRequest(arguments);

        return getUpdateDomainResponse(responseDocument);
    }

    /**
     * Converts XML document into UpdateDomainResponse object
     *
     * @param doc
     * @return
     */
    private UpdateDomainResponse getUpdateDomainResponse(Document doc) {
        UpdateDomainResponse response = new UpdateDomainResponse();

        // get id from XML and set as the ID of the domain
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setId(node.getTextContent());
        }

        // get haschild from XML and set whether the domain has one or more sub-domains
        list = doc.getElementsByTagName("haschild");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHasChild(node.getTextContent());
        }

        // get level from XML and set the level of the domain
        list = doc.getElementsByTagName("level");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setLevel(node.getTextContent());
        }

        // get name from XML and set the name of the domain
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setName(node.getTextContent());
        }

        // get networkdomain from XML and set the network domain
        list = doc.getElementsByTagName("networkdomain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setNetworkDomain(node.getTextContent());
        }

        // get parentdomainid from XML and set the domain ID of the parent domain
        list = doc.getElementsByTagName("parentdomainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setParentDomainId(node.getTextContent());
        }

        // get parentdomainname from XML and set the domain name of the parent domain
        list = doc.getElementsByTagName("parentdomainname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setParentDomainName(node.getTextContent());
        }

        // get path from XML and set the path of the domain
        list = doc.getElementsByTagName("path");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPath(node.getTextContent());
        }

        return response;

    }

    /**
     * Deletes a specified domain
     *
     * @param domainId The ID of domain to delete
     * @return
     * @throws Exception
     */
    public DeleteDomainResponse deleteDomain(String domainId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteDomain", null);
        arguments.add(new NameValuePair("id", domainId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteDomainResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteDomainResponse object
     *
     * @param doc
     * @return
     */
    private DeleteDomainResponse getDeleteDomainResponse(Document doc) {
        DeleteDomainResponse response = new DeleteDomainResponse();

        // get displaytext from XML and set any text associated with the success or failure on deleting domain 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting domain 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * Lists domains and provides detailed information for listed domains
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListDomainsResponse listDomains(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listDomains", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListDomainsResponse(responseDocument);
    }

    /**
     * Converts XML document into ListDomainsResponse object
     *
     * @param doc
     * @return
     */
    private ListDomainsResponse getListDomainsResponse(Document doc) {
        ListDomainsResponse response = new ListDomainsResponse();

        NodeList list = doc.getElementsByTagName("domain");

        List<DomainResponse> domains = new LinkedList<DomainResponse>();
        if (list.getLength() > 0) {
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node domainNode = list.item(Index);

                DomainResponse domain = new DomainResponse();

                NodeList domainProperties = domainNode.getChildNodes();
                for (int childIndex = 0; childIndex < domainProperties.getLength(); childIndex++) {
                    Node property = domainProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        domain.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("haschild")) {
                        domain.setHasChild(property.getTextContent());
                    } else if (property.getNodeName().equals("level")) {
                        domain.setLevel(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        domain.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkdomain")) {
                        domain.setNetworkDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("parentdomainname")) {
                        domain.setParentDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("parentdomainid")) {
                        domain.setParentDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("path")) {
                        domain.setPath(property.getTextContent());
                    }
                }
                domains.add(domain);
            }
        }
        response.setDomains(domains);
        return response;
    }

    /**
     * Lists all children domains belonging to a specified domain
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListDomainChildrenResponse listDomainChildren(
            HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listDomainChildren", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListDomainChildrenResponse(responseDocument);
    }

    /**
     * Converts XML document into ListDomainChildrenResponse object
     *
     * @param doc
     * @return
     */
    private ListDomainChildrenResponse getListDomainChildrenResponse(Document doc) {
        ListDomainChildrenResponse response = new ListDomainChildrenResponse();

        NodeList list = doc.getElementsByTagName("domainchildren");

        List<DomainChildrenResponse> domainChildrens = new LinkedList<DomainChildrenResponse>();
        if (list.getLength() > 0) {
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node domainChildrenNode = list.item(Index);

                DomainChildrenResponse domainChildren = new DomainChildrenResponse();

                NodeList domainChildrenProperties = domainChildrenNode.getChildNodes();
                for (int childIndex = 0; childIndex < domainChildrenProperties.getLength(); childIndex++) {
                    Node property = domainChildrenProperties.item(childIndex);

                    if (property.getNodeName().equals("id")) {
                        domainChildren.setId(property.getTextContent());
                    } else if (property.getNodeName().equals("haschild")) {
                        domainChildren.setHasChild(property.getTextContent());
                    } else if (property.getNodeName().equals("level")) {
                        domainChildren.setLevel(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        domainChildren.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("networkdomain")) {
                        domainChildren.setNetworkDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("parentdomainid")) {
                        domainChildren.setParentDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("parentdomainname")) {
                        domainChildren.setParentDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("path")) {
                        domainChildren.setPath(property.getTextContent());
                    }
                }
                domainChildrens.add(domainChildren);
            }
        }
        response.setDomainChildrens(domainChildrens);
        return response;
    }

}
