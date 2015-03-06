package com.assistanz.cloud.cloudstack.guestos;

import java.util.HashMap;
import java.util.LinkedList;
import org.apache.commons.httpclient.NameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;


/**
 * 
 * @author Gowtham
 *
 */
public class CSGuestOSService {
	
	private CloudStackServer server;
	
	public CSGuestOSService(CloudStackServer server) {
		this.server = server;
	}
	
	/**
	 * Lists all supported OS types for this cloud.
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListOsTypesResponse listOsTypes(HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listOsTypes", optional);
				
		Document responseDocument = server.makeRequest(arguments);
		
		return getListOsTypesResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListOsTypesResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListOsTypesResponse getListOsTypesResponse(Document doc) {
		ListOsTypesResponse response = new ListOsTypesResponse();

            //list of OS types
            NodeList list = doc.getElementsByTagName("ostype");
                
            List<OsTypesResponse> osTypes = new LinkedList<OsTypesResponse>();
            
            if (list.getLength() > 0) {
                for (int index = 0; index < list.getLength(); index++) {
                    Node osTypeNode = list.item(index);

                    if (osTypeNode == null) {
                        continue;
                    }  

                    OsTypesResponse osType = new OsTypesResponse();                        
                    NodeList osTypeProperties = osTypeNode.getChildNodes();
                    for (int childIndex = 0; childIndex < osTypeProperties.getLength(); childIndex++) {
                        Node property = osTypeProperties.item(childIndex);

                        if (property == null || property.getNodeName() == null) {
                            continue;
                        }

                        if (property.getNodeName().equals("id")) {
                            osType.setOsTypeId(property.getTextContent());
                        } else if (property.getNodeName().equals("description")) {
                            osType.setOsTypeDescription(property.getTextContent());
                        }else if (property.getNodeName().equals("oscategoryid")) {
                            osType.setOsCategoryId(property.getTextContent());
                        }
                    }
                    osTypes.add(osType);
                }
            } 
            response.setOsTypes(osTypes);
            return response;
	}
	
	/**
	 * Lists all supported OS categories for this cloud.
	 * 
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public ListOsCategoriesResponse listOsCategories(HashMap<String,String> optional) 
			throws Exception {

		LinkedList<NameValuePair> arguments = 
		        server.getDefaultQuery("listOsCategories", optional);
				
		Document responseDocument = server.makeRequest(arguments);
		
		return getListOsCategoriesResponse(responseDocument);
	}
	
	/**
	 * Converts XML document into ListOsCategoriesResponse object
	 * 
	 * @param doc
	 * @return
	 */
	private ListOsCategoriesResponse getListOsCategoriesResponse(Document doc) {
		ListOsCategoriesResponse response = new ListOsCategoriesResponse();

                
                 //list of OS types
            NodeList list = doc.getElementsByTagName("oscategory");
                
            List<OsCategoryResponse> osCategories = new LinkedList<OsCategoryResponse>();
            
            if (list.getLength() > 0) {
                for (int index = 0; index < list.getLength(); index++) {
                    Node osCategoryNode = list.item(index);

                    if (osCategoryNode == null) {
                        continue;
                    }  

                    OsCategoryResponse osCategory = new OsCategoryResponse();                        
                    NodeList osCategoryProperties = osCategoryNode.getChildNodes();
                    for (int childIndex = 0; childIndex < osCategoryProperties.getLength(); childIndex++) {
                        Node property = osCategoryProperties.item(childIndex);

                        if (property == null || property.getNodeName() == null) {
                            continue;
                        }

                        if (property.getNodeName().equals("id")) {
                            osCategory.setOsCategoryId(property.getTextContent());
                        } else if (property.getNodeName().equals("name")) {
                            osCategory.setOsCategoryName(property.getTextContent());
                        }
                    }
                    osCategories.add(osCategory);
                }
            } 
            response.setOsCategories(osCategories);
            return response;
             
	}

}
