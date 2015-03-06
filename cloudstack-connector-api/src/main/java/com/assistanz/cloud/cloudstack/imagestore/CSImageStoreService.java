package com.assistanz.cloud.cloudstack.imagestore;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
//import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Santhosh
 */
public class CSImageStoreService {

    private CloudStackServer server;

    public CSImageStoreService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds backup image store
     *
     * @param providerName
     * @param optional
     * @return
     * @throws Exception
     */
    public AddImageStoreResponse addImageStore(String providerName, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addImageStore", optional);
        arguments.add(new NameValuePair("provider", providerName));

        Document responseDocument = server.makeRequest(arguments);

        return getAddImageStoreResponse(responseDocument);
    }

    /**
     * Converts XML document into AddImageStoreResponse object
     *
     * @param doc
     * @return
     */
    private AddImageStoreResponse getAddImageStoreResponse(Document doc) {
        AddImageStoreResponse response = new AddImageStoreResponse();

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
     * Lists image stores.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListImageStoresResponse listImageStores(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listImageStores", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListImageStoresResponse(responseDocument);
    }

    /**
     * Converts XML document into ListImageStoresResponse object
     *
     * @param doc
     * @return
     */
    private ListImageStoresResponse getListImageStoresResponse(Document doc) {
        ListImageStoresResponse response = new ListImageStoresResponse();

        NodeList list = doc.getElementsByTagName("imagestore");

        List<ImageStoreResponse> imageStores = new LinkedList<ImageStoreResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node imageStoreNode = list.item(Index);
            ImageStoreResponse imageStore = new ImageStoreResponse();

            NodeList imageStoreProperties = imageStoreNode.getChildNodes();
            for (int childIndex = 0; childIndex < imageStoreProperties.getLength(); childIndex++) {
                Node property = imageStoreProperties.item(childIndex);

                if (property.getNodeName().equals("id")) {
                    imageStore.setId(property.getTextContent());
                } else if (property.getNodeName().equals("details")) {
                    imageStore.setDetails(property.getTextContent());
                } else if (property.getNodeName().equals("name")) {
                    imageStore.setName(property.getTextContent());
                } else if (property.getNodeName().equals("protocol")) {
                    imageStore.setProtocol(property.getTextContent());
                } else if (property.getNodeName().equals("providername")) {
                    imageStore.setProviderName(property.getTextContent());
                } else if (property.getNodeName().equals("scope")) {
                    imageStore.setScope(property.getTextContent());
                } else if (property.getNodeName().equals("url")) {
                    imageStore.setUrl(property.getTextContent());
                } else if (property.getNodeName().equals("zoneid")) {
                    imageStore.setZoneId(property.getTextContent());
                } else if (property.getNodeName().equals("zonename")) {
                    imageStore.setZoneName(property.getTextContent());
                }
            }
            imageStores.add(imageStore);
        }

        response.setImageStores(imageStores);
        return response;
    }

    /**
     * Deletes an image store
     *
     * @param imageStoreId
     * @return
     * @throws Exception
     */
    public DeleteImageStoreResponse deleteImageStore(String imageStoreId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteImageStore", null);
        arguments.add(new NameValuePair("id", imageStoreId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteImageStoreResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteImageStoreResponse object
     *
     * @param doc
     * @return
     */
    private DeleteImageStoreResponse getDeleteImageStoreResponse(Document doc) {
        DeleteImageStoreResponse response = new DeleteImageStoreResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Ip Forwarding Rule 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Ip Forwarding Rule 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

    /**
     * create secondary staging store
     *
     * @param stagingStoreUrl
     * @param optional
     * @return
     * @throws Exception
     */
    public CreateSecondaryStagingStoreResponse createSecondaryStagingStore(String stagingStoreUrl, HashMap<String, String> optional) throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("createSecondaryStagingStore", optional);
        arguments.add(new NameValuePair("url", stagingStoreUrl));

        Document responseDocument = server.makeRequest(arguments);

        return getCreateSecondaryStagingStoreResponse(responseDocument);
    }

    /**
     * Converts XML document into CreateSecondaryStagingStoreResponse object
     *
     * @param doc
     * @return
     */
    private CreateSecondaryStagingStoreResponse getCreateSecondaryStagingStoreResponse(Document doc) {
        CreateSecondaryStagingStoreResponse response = new CreateSecondaryStagingStoreResponse();

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
     * Lists secondary staging stores.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListSecondaryStagingStoresResponse listSecondaryStagingStores(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listSecondaryStagingStores", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListSecondaryStagingStoresResponse(responseDocument);
    }

    /**
     * Converts XML document into ListSecondaryStagingStoresResponse object
     *
     * @param doc
     * @return
     */
    private ListSecondaryStagingStoresResponse getListSecondaryStagingStoresResponse(Document doc) {
        ListSecondaryStagingStoresResponse response = new ListSecondaryStagingStoresResponse();

        NodeList list = doc.getElementsByTagName("secondarystagingstore");

        List<SecondaryStagingStoreResponse> secondaryStagingStores = new LinkedList<SecondaryStagingStoreResponse>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node secondaryStagingStoreNode = list.item(Index);
            SecondaryStagingStoreResponse secondaryStagingStore = new SecondaryStagingStoreResponse();

            NodeList secondaryStagingStoreProperties = secondaryStagingStoreNode.getChildNodes();
            for (int childIndex = 0; childIndex < secondaryStagingStoreProperties.getLength(); childIndex++) {
                Node property = secondaryStagingStoreProperties.item(childIndex);

                if (property.getNodeName().equals("id")) {
                    secondaryStagingStore.setId(property.getTextContent());
                } else if (property.getNodeName().equals("details")) {
                    secondaryStagingStore.setDetails(property.getTextContent());
                } else if (property.getNodeName().equals("name")) {
                    secondaryStagingStore.setName(property.getTextContent());
                } else if (property.getNodeName().equals("protocol")) {
                    secondaryStagingStore.setProtocol(property.getTextContent());
                } else if (property.getNodeName().equals("providername")) {
                    secondaryStagingStore.setProviderName(property.getTextContent());
                } else if (property.getNodeName().equals("scope")) {
                    secondaryStagingStore.setScope(property.getTextContent());
                } else if (property.getNodeName().equals("url")) {
                    secondaryStagingStore.setUrl(property.getTextContent());
                } else if (property.getNodeName().equals("zoneid")) {
                    secondaryStagingStore.setZoneId(property.getTextContent());
                } else if (property.getNodeName().equals("zonename")) {
                    secondaryStagingStore.setZoneName(property.getTextContent());
                }
            }
            secondaryStagingStores.add(secondaryStagingStore);
        }

        response.setSecondaryStagingStores(secondaryStagingStores);
        return response;
    }

    /**
     * Deletes a secondary staging store
     *
     * @param stagingStoreId
     * @return
     * @throws Exception
     */
    public DeleteSecondaryStagingStoreResponse deleteSecondaryStagingStore(String stagingStoreId)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("deleteSecondaryStagingStore", null);
        arguments.add(new NameValuePair("id", stagingStoreId));

        Document responseDocument = server.makeRequest(arguments);

        return getDeleteSecondaryStagingStoreResponse(responseDocument);
    }

    /**
     * Converts XML document into DeleteSecondaryStagingStoreResponse object
     *
     * @param doc
     * @return
     */
    private DeleteSecondaryStagingStoreResponse getDeleteSecondaryStagingStoreResponse(Document doc) {
        DeleteSecondaryStagingStoreResponse response = new DeleteSecondaryStagingStoreResponse();

        // get displaytext from XML and set any text associated with the success or failure on Delete Ip Forwarding Rule 
        NodeList list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplaytext(node.getTextContent());
        }
        //get success from XML and any text associated with the success or failure on deleting Delete Ip Forwarding Rule 
        list = doc.getElementsByTagName("success");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSuccess(node.getTextContent());
        }

        return response;
    }

}
