package com.assistanz.cloud.cloudstack.s3;

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
public class CSS3Service {

    private CloudStackServer server;

    public CSS3Service(CloudStackServer server) {
        this.server = server;
    }

    /**
     * Adds S3.
     *
     * @param accessKeyS3 access key
     * @param templateStorageBucket name of the template storage bucket
     * @param secretKey S3 secret key
     * @param optional
     * @return
     * @throws Exception
     */
    public AddS3Response addS3(String accessKeyS3, String templateStorageBucket,
            String secretKey, HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("addS3", optional);
        arguments.add(new NameValuePair("accesskey", accessKeyS3));
        arguments.add(new NameValuePair("bucket", templateStorageBucket));
        arguments.add(new NameValuePair("secretkey", secretKey));

        Document responseDocument = server.makeRequest(arguments);

        return getAddS3Response(responseDocument);
    }

    /**
     * Converts XML document into AddS3Response object
     *
     * @param doc
     * @return
     */
    private AddS3Response getAddS3Response(Document doc) {
        AddS3Response response = new AddS3Response();

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
     * Lists S3s.
     *
     * @param optional
     * @return
     * @throws Exception
     */
    public ListS3sResponse listS3s(
            HashMap<String, String> optional)
            throws Exception {

        LinkedList<NameValuePair> arguments
                = server.getDefaultQuery("listS3s", optional);

        Document responseDocument = server.makeRequest(arguments);

        return getListS3sResponse(responseDocument);
    }

    /**
     * Converts XML document into ListS3sResponse object
     *
     * @param doc
     * @return
     */
    private ListS3sResponse getListS3sResponse(Document doc) {
        ListS3sResponse response = new ListS3sResponse();

        NodeList list = doc.getElementsByTagName("swift");

        List<S3Response> s3s = new LinkedList<S3Response>();

        for (int Index = 0; Index < list.getLength(); Index++) {
            Node s3Node = list.item(Index);
            S3Response s3 = new S3Response();

            NodeList s3Properties = s3Node.getChildNodes();
            for (int childIndex = 0; childIndex < s3Properties.getLength(); childIndex++) {
                Node property = s3Properties.item(childIndex);

                if (property.getNodeName().equals("id")) {
                    s3.setId(property.getTextContent());
                } else if (property.getNodeName().equals("details")) {
                    s3.setDetails(property.getTextContent());
                } else if (property.getNodeName().equals("name")) {
                    s3.setName(property.getTextContent());
                } else if (property.getNodeName().equals("protocol")) {
                    s3.setProtocol(property.getTextContent());
                } else if (property.getNodeName().equals("providername")) {
                    s3.setProviderName(property.getTextContent());
                } else if (property.getNodeName().equals("scope")) {
                    s3.setScope(property.getTextContent());
                } else if (property.getNodeName().equals("url")) {
                    s3.setUrl(property.getTextContent());
                } else if (property.getNodeName().equals("zoneid")) {
                    s3.setZoneId(property.getTextContent());
                } else if (property.getNodeName().equals("zonename")) {
                    s3.setZoneName(property.getTextContent());
                }
            }
            s3s.add(s3);
        }

        response.setS3s(s3s);
        return response;
    }
}
