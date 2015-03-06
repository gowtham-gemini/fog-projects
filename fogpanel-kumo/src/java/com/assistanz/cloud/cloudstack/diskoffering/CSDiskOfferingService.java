package com.assistanz.cloud.cloudstack.diskoffering;

import java.util.HashMap;
import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;
import org.apache.commons.httpclient.NameValuePair;

/**
 * 
 * @author Gowtham
 *
 */
public class CSDiskOfferingService {
	
	private CloudStackServer server;
	
	public CSDiskOfferingService(CloudStackServer server) {
		this.server = server;
	}
	
	/**
	 * Creates a disk offering.
	 * 
	 * @param displayText alternate display text of the disk offering
	 * @param name name of the disk offering
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public CreateDiskOfferingResponse createDiskOffering(String displayText, 
			String name,
            HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createDiskOffering", optional);
        arguments.add(new NameValuePair("displaytext", displayText));
        arguments.add(new NameValuePair("name", name));
        
        Document responseDocument = server.makeRequest(arguments);

        return getCreateDiskOfferingResponse(responseDocument);
    }
	
	/**
     * Converts XML document into CreateDiskOfferingResponse object
     * 
     * @param doc
     * @return 
     */
    private CreateDiskOfferingResponse getCreateDiskOfferingResponse(Document doc) {
    	CreateDiskOfferingResponse response = new CreateDiskOfferingResponse();
    	    	
      	 // get id from XML and set as unique ID of the disk offering
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingId(node.getTextContent());
        }
        
        // get created from XML and set the date this disk offering was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }
        
        // get disksize from XML and set disk size for the disk offering
        list = doc.getElementsByTagName("disksize");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSize(node.getTextContent());
        }
        
        // get displaytext from XML and set display text for the disk offering
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }
        
        // get domain from XML and set domain name of the disk offering
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingDomain(node.getTextContent());
        }
        
        // get domainid from XML and set as domain id ID of the disk offering
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingDomainId(node.getTextContent());
        }
        
        // get name from XML and set as name of the disk offering
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingName(node.getTextContent());
        }
        
        // get tags from XML and set tags for the disk offering
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingTags(node.getTextContent());
        }
                
        return response;
    }
    
    /**
     * Updates a disk offering.
     * 
     * @param diskOfferingId The ID of the disk offering
     * @param optional
     * @return
     * @throws Exception
     */
	public UpdateDiskOfferingResponse updateDiskOffering(String diskOfferingId, 
            HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateDiskOffering", optional);
        arguments.add(new NameValuePair("id", diskOfferingId));
       
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateDiskOfferingResponse(responseDocument);
    }
	
	/**
     * Converts XML document into UpdateDiskOfferingResponse object
     * 
     * @param doc
     * @return 
     */
    private UpdateDiskOfferingResponse getUpdateDiskOfferingResponse(Document doc) {
    	UpdateDiskOfferingResponse response = new UpdateDiskOfferingResponse();
    	    	
      	 // get id from XML and set as unique ID of the disk offering
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingId(node.getTextContent());
        }
        
        // get created from XML and set the date this disk offering was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCreated(node.getTextContent());
        }
        
        // get disksize from XML and set disk size for the disk offering
        list = doc.getElementsByTagName("disksize");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskSize(node.getTextContent());
        }
        
        // get displaytext from XML and set display text for the disk offering
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDisplayText(node.getTextContent());
        }
        
        // get domain from XML and set domain name of the disk offering
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingDomain(node.getTextContent());
        }
        
        // get domainid from XML and set as domail id ID of the disk offering
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingDomainId(node.getTextContent());
        }
        
        // get name from XML and set as name of the disk offering
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingName(node.getTextContent());
        }
        
        // get tags from XML and set tags for the disk offering
        list = doc.getElementsByTagName("tags");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDiskOfferingTags(node.getTextContent());
        }
                
        return response;
    }
    
    /**
     * Deletes a disk offering.
     * 
     * @param diskOfferingId The ID of the disk offering
     * @return
     * @throws Exception
     */
	public DeleteDiskOfferingResponse deleteDiskOffering(String diskOfferingId) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteDiskOffering", null);
        arguments.add(new NameValuePair("id", diskOfferingId));
       
        Document responseDocument = server.makeRequest(arguments);

        return getDeleteDiskOfferingResponse(responseDocument);
    }
	
	/**
     * Converts XML document into DeleteDiskOfferingResponse object
     * 
     * @param doc
     * @return 
     */
    private DeleteDiskOfferingResponse getDeleteDiskOfferingResponse(Document doc) {
    	DeleteDiskOfferingResponse response = new DeleteDiskOfferingResponse();

	    // get displaytext from XML and set Any text associated with the success or 
	    // failure on Deleting Disk Offering
	    NodeList list = doc.getElementsByTagName("displaytext");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDisplayText(node.getTextContent());
	    }
	    
	    // get success from XML and set Return true if Deleting Disk Offering operation 
	    // is executed successfully
	    list = doc.getElementsByTagName("success");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setSuccess(node.getTextContent());
	    }
	    
	     return response;
    }
    
    /**
     * Lists all available disk offerings.
     * 
     * @param optional
     * @return
     * @throws Exception
     */
	public ListDiskOfferingResponse listDiskOfferings( 
            HashMap<String,String> optional) throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listDiskOfferings", optional);
       
        Document responseDocument = server.makeRequest(arguments);

        return getListDiskOfferingsResponse(responseDocument);
    }
	
	/**
     * Converts XML document into ListDiskOfferingResponse object
     * 
     * @param doc
     * @return 
     */
    private ListDiskOfferingResponse getListDiskOfferingsResponse(Document doc) {
    	ListDiskOfferingResponse response = new ListDiskOfferingResponse();
    	    	
        NodeList list = doc.getElementsByTagName("diskoffering");

        List<DiskOfferingResponse> diskOfferings = new LinkedList<DiskOfferingResponse>();            
        if (list.getLength() > 0) {
            for (int Index = 0; Index < list.getLength(); Index++) {
                Node diskOfferingNode = list.item(Index);

                if (diskOfferingNode == null) {
                    continue;
                }  
                
                DiskOfferingResponse diskOffering = new DiskOfferingResponse();
                        
                NodeList diskOfferingProperties = diskOfferingNode.getChildNodes();
                for (int childIndex = 0; childIndex < diskOfferingProperties.getLength(); childIndex++) {
                    Node property = diskOfferingProperties.item(childIndex);
                    
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                                       
                    if (property.getNodeName().equals("id")) {
                        diskOffering.setDiskOfferingId(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                    	diskOffering.setCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("disksize")) {
                    	diskOffering.setDiskSize(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                    	diskOffering.setDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                    	diskOffering.setDiskOfferingDomain(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                    	diskOffering.setDiskOfferingDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("iscustomized")) {
                    	diskOffering.setIsCustomized(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	diskOffering.setDiskOfferingName(property.getTextContent());
                    } else if (property.getNodeName().equals("storagetype")) {
                    	diskOffering.setStorageType(property.getTextContent());
                    }  else if (property.getNodeName().equals("miniops")) {
                        diskOffering.setMinIOPS(property.getTextContent());
                    }  else if (property.getNodeName().equals("maxiops")) {
                    	diskOffering.setMaxIOPS(property.getTextContent());
                    }  else if (property.getNodeName().equals("diskBytesReadRate")) {
                    	diskOffering.setDiskReadRateBPS(property.getTextContent());
                    }  else if (property.getNodeName().equals("diskBytesWriteRate")) {
                    	diskOffering.setDiskWriteRateBPS(property.getTextContent());
                    }  else if (property.getNodeName().equals("diskIopsReadRate")) {
                    	diskOffering.setDiskReadRateIOPS(property.getTextContent());
                    }  else if (property.getNodeName().equals("diskIopsWriteRate")) {
                    	diskOffering.setDiskWriteRateIOPS(property.getTextContent());
                    }  else if (property.getNodeName().equals("tags")) {
                    	diskOffering.setDiskOfferingTags(property.getTextContent());
                    } 
                                    
                }
                diskOfferings.add(diskOffering);
            }
        } 
        response.setDiskOfferings(diskOfferings);
        return response;
        
    }

}
