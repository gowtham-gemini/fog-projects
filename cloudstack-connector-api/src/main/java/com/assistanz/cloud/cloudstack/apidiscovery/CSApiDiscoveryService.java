package com.assistanz.cloud.cloudstack.apidiscovery;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
//import org.apache.http.NameValuePair;
import org.apache.commons.httpclient.NameValuePair;

/**
 *
 * @author Santhosh
 *
 */
public class CSApiDiscoveryService {

    private CloudStackServer server;

    public CSApiDiscoveryService(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Lists all network ACL items.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListApisResponse listApis(HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listApis", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListApisResponse(responseDocument);
    }

    /**
     * Converts XML document into ListApisResponse object
     *
     * @param doc
     * @return
     */
    private ListApisResponse getListApisResponse(Document doc) {
        ListApisResponse response = new ListApisResponse();

        NodeList list = doc.getElementsByTagName("listapi");

        List<ApiListResponse> apiLists = new LinkedList<ApiListResponse>();
        if (list.getLength() > 0) {
            for (int apiListIndex = 0; apiListIndex < list.getLength(); apiListIndex++) {
                Node apiListNode = list.item(apiListIndex);

                ApiListResponse apiList = new ApiListResponse();
                List<ParamsResponse> paramss = new LinkedList<ParamsResponse>();
                List<ApiResponse> apis = new LinkedList<ApiResponse>();
                NodeList apiListProperties = apiListNode.getChildNodes();
                for (int childIndex = 0; childIndex < apiListProperties.getLength(); childIndex++) {
                    Node property = apiListProperties.item(childIndex);

                    if (property.getNodeName().equals("description")) {
                        apiList.setDescription(property.getTextContent());
                    } else if (property.getNodeName().equals("isasync")) {
                        apiList.setIsAsync(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                        apiList.setName(property.getTextContent());
                    } else if (property.getNodeName().equals("related")) {
                        apiList.setRelated(property.getTextContent());
                    } else if (property.getNodeName().equals("since")) {
                        apiList.setSince(property.getTextContent());
                    } else if (property.getNodeName().equals("type")) {
                        apiList.setType(property.getTextContent());
                    } else if (property.getNodeName().equals("params")) {
                        NodeList paramsProperties = property.getChildNodes();
                        if (paramsProperties.getLength() > 0) {
                            ParamsResponse params = new ParamsResponse();
                            for (int paramsIndex = 0; paramsIndex < paramsProperties.getLength(); paramsIndex++) {
                                Node paramsProperty = paramsProperties.item(paramsIndex);
                                if (paramsProperty.getNodeName().equals("Description")) {
                                    params.setDescription(paramsProperty.getTextContent());
                                } else if (paramsProperty.getNodeName().equals("length")) {
                                    params.setLength(paramsProperty.getTextContent());
                                } else if (paramsProperty.getNodeName().equals("name")) {
                                    params.setName(paramsProperty.getTextContent());
                                } else if (paramsProperty.getNodeName().equals("related")) {
                                    params.setRelated(paramsProperty.getTextContent());
                                } else if (paramsProperty.getNodeName().equals("required")) {
                                    params.setRequired(paramsProperty.getTextContent());
                                } else if (paramsProperty.getNodeName().equals("since")) {
                                    params.setSince(paramsProperty.getTextContent());
                                } else if (paramsProperty.getNodeName().equals("type")) {
                                    params.setType(paramsProperty.getTextContent());
                                } else if (property.getNodeName().equals("response")) {
                                    NodeList apiProperties = property.getChildNodes();
                                    if (apiProperties.getLength() > 0) {
                                        ApiResponse api = new ApiResponse();
                                        for (int apiIndex = 0; apiIndex < paramsProperties.getLength(); apiIndex++) {
                                            Node apiProperty = apiProperties.item(apiIndex);
                                            if (apiProperty.getNodeName().equals("Description")) {
                                                api.setDescription(apiProperty.getTextContent());
                                            } else if (apiProperty.getNodeName().equals("name")) {
                                                api.setName(apiProperty.getTextContent());
                                            } else if (apiProperty.getNodeName().equals("response")) {
                                                api.setResponse(apiProperty.getTextContent());
                                            } else if (apiProperty.getNodeName().equals("type")) {
                                                api.setType(apiProperty.getTextContent());
                                            }
                                        }
                                        apis.add(api);
                                    }
                                }
                            }
                            paramss.add(params);
                        }
                    }
                    apiLists.add(apiList);
                }
                apiList.setApis(apis);
                apiList.setParamss(paramss);
                apiLists.add(apiList);
            }
        }
        response.setApiLists(apiLists);
        return response;
    }
}
