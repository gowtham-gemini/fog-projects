package com.assistanz.cloud.cloudstack.template;

import java.util.HashMap;
import java.util.LinkedList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.apache.commons.httpclient.NameValuePair;
import com.assistanz.cloud.cloudstack.CloudStackServer;
import java.util.List;

/**
 * 
 * @author Gowtham
 *
 */
public class CSTemplateService {
	
	private CloudStackServer server;
	
	public CSTemplateService(CloudStackServer server) {
		this.server = server;
	}
	
	/**
	 * Creates a template of a virtual machine. 
	 * The virtual machine must be in a STOPPED state. 
	 * A template created from this command is automatically designated 
	 * as a private template visible to the account that created it.
	 * 
	 * @param displayText the display text of the template. This is usually used for display purposes.
	 * @param templateName the name of the template
	 * @param osTypeId the ID of the OS Type that best represents the OS of this template.
	 * @param optional
	 * @return
	 * @throws Exception
	 */
	public CreateTemplateResponse createTemplate(String displayText, 
			String templateName, String osTypeId, HashMap<String,String> optional)
					throws Exception {
				
		LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("createTemplate", optional);
		arguments.add(new NameValuePair("displaytext", displayText));
        arguments.add(new NameValuePair("name", templateName));
        arguments.add(new NameValuePair("ostypeid", osTypeId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getCreateTemplateResponse(responseDocument);
	}
	
    /**
     * Converts XML document into CreateTemplateResponse object
     *
     * @param doc
     * @return
     */
    private CreateTemplateResponse getCreateTemplateResponse(Document doc) {
    	CreateTemplateResponse response = new CreateTemplateResponse();

        // get id from XML and set as the template ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get account from XML and set as 	the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the template belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get bootable from XML and set as true if the ISO is bootable, otherwise false 
        list = doc.getElementsByTagName("bootable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBootable(node.getTextContent());
        }

        // get checksum from XML and set as checksum of the template
        list = doc.getElementsByTagName("checksum");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setChecksum(node.getTextContent());
        }

        // get created from XML and set as the date this template was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateCreated(node.getTextContent());
        }

        // get crossZones from XML and set as true if the template is managed across all Zones, otherwise false
        list = doc.getElementsByTagName("crossZones");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCrossZones(node.getTextContent());
        }

        // get details from XML and set as additional key/value details tied with template
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get displaytext from XML and set as the template display text
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get domainid from XML and set the ID of the domain to which the template belongsl
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get format from XML and set as the format of the template.
        list = doc.getElementsByTagName("format");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateFormat(node.getTextContent());
        }
        
        // get hostid from XML and set as the ID of the secondary storage host for the template
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set as the name of the secondary storage host for the template
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the template is extractable, otherwise false
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get isfeatured from XML and set as true if this template is a featured template,otherwise false 
        list = doc.getElementsByTagName("isfeatured");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsFeatured(node.getTextContent());
        }
        
        // get isready from XML and set as true if the template is ready to be deployed from, otherwise false.
        list = doc.getElementsByTagName("isready");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsReady(node.getTextContent());
        }
        
        // get name from XML and set as the template name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateName(node.getTextContent());
        }
        
        // get ostypeid from XML and set as the ID of the OS type for this template.
        list = doc.getElementsByTagName("ostypeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeId(node.getTextContent());
        }
        
        // get ostypename from XML and set as the Name of the OS type for this template.
        list = doc.getElementsByTagName("ostypename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeName(node.getTextContent());
        }
        
        // get passwordenabled from XML and set as true if the reset password feature is enabled,otherwise false 
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the template
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectId(node.getTextContent());
        }
        
        // get removed from XML and set as the date this template was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }
        
        // get size from XML and set as the size of the template
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateSize(node.getTextContent());
        }
        
        // get sourcetemplateid from XML and set as the template ID of the parent template if present
        list = doc.getElementsByTagName("sourcetemplateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceTemplateId(node.getTextContent());
        }
        
        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateStatus(node.getTextContent());
        }
        
        // get templatetag from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetag");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTag(node.getTextContent());
        }
        
        // get templatetype from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateType(node.getTextContent());
        }
        
        // get zoneid from XML and set the ID of the zone for this template
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneId(node.getTextContent());
        }
        
        // get zonename from XML and set the name of the zone for this template
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneName(node.getTextContent());
        }
        
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;
    }
    
    /**
     * Registers an existing template into the Cloud.com cloud. 
     * 
     * @param displayText the display text of the template. This is usually used for display purposes.
     * @param format the format for the template. Possible values include QCOW2, RAW, and VHD
     * @param hypervisor the target hypervisor for the template
     * @param templateName the name of the template
     * @param osTypeId the ID of the OS Type that best represents the OS of this template.
     * @param url the URL of where the template is hosted. Possible URL include http:// and https://
     * @param zoneId the ID of the zone the template is to be hosted on
     * @param optional
     * @return
     * @throws Exception
     */
	public RegisterTemplateResponse registerTemplate(String displayText, 
			String format, String hypervisor, String templateName, String osTypeId, 
			String url, String zoneId, 
			HashMap<String,String> optional) throws Exception {
				
		LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("registerTemplate", optional);
		arguments.add(new NameValuePair("displaytext", displayText));
        arguments.add(new NameValuePair("format", format));
        arguments.add(new NameValuePair("hypervisor", hypervisor));
        arguments.add(new NameValuePair("name", templateName));
        arguments.add(new NameValuePair("ostypeid", osTypeId));
        arguments.add(new NameValuePair("url", url));
        arguments.add(new NameValuePair("zoneid", zoneId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getRegisterTemplateResponse(responseDocument);
	}
	
    /**
     * Converts XML document into CreateTemplateResponse object
     *
     * @param doc
     * @return
     */
    private RegisterTemplateResponse getRegisterTemplateResponse(Document doc) {
    	RegisterTemplateResponse response = new RegisterTemplateResponse();

        // get id from XML and set as the template ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get account from XML and set as 	the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the template belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get bootable from XML and set as true if the ISO is bootable, otherwise false 
        list = doc.getElementsByTagName("bootable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBootable(node.getTextContent());
        }

        // get checksum from XML and set as checksum of the template
        list = doc.getElementsByTagName("checksum");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setChecksum(node.getTextContent());
        }

        // get created from XML and set as the date this template was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateCreated(node.getTextContent());
        }

        // get crossZones from XML and set as true if the template is managed across all Zones, otherwise false
        list = doc.getElementsByTagName("crossZones");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCrossZones(node.getTextContent());
        }

        // get details from XML and set as additional key/value details tied with template
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get displaytext from XML and set as the template display text
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get domainid from XML and set the ID of the domain to which the template belongsl
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get format from XML and set as the format of the template.
        list = doc.getElementsByTagName("format");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateFormat(node.getTextContent());
        }
        
        // get hostid from XML and set as the ID of the secondary storage host for the template
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set as the name of the secondary storage host for the template
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the template is extractable, otherwise false
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get isfeatured from XML and set as true if this template is a featured template,otherwise false 
        list = doc.getElementsByTagName("isfeatured");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsFeatured(node.getTextContent());
        }
        
        // get isready from XML and set as true if the template is ready to be deployed from, otherwise false.
        list = doc.getElementsByTagName("isready");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsReady(node.getTextContent());
        }
        
        // get name from XML and set as the template name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateName(node.getTextContent());
        }
        
        // get ostypeid from XML and set as the ID of the OS type for this template.
        list = doc.getElementsByTagName("ostypeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeId(node.getTextContent());
        }
        
        // get ostypename from XML and set as the Name of the OS type for this template.
        list = doc.getElementsByTagName("ostypename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeName(node.getTextContent());
        }
        
        // get passwordenabled from XML and set as true if the reset password feature is enabled,otherwise false 
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the template
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectId(node.getTextContent());
        }
        
        // get removed from XML and set as the date this template was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }
        
        // get size from XML and set as the size of the template
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateSize(node.getTextContent());
        }
        
        // get sourcetemplateid from XML and set as the template ID of the parent template if present
        list = doc.getElementsByTagName("sourcetemplateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceTemplateId(node.getTextContent());
        }
        
        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateStatus(node.getTextContent());
        }
        
        // get templatetag from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetag");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTag(node.getTextContent());
        }
        
        // get templatetype from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateType(node.getTextContent());
        }
        
        // get zoneid from XML and set the ID of the zone for this template
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneId(node.getTextContent());
        }
        
        // get zonename from XML and set the name of the zone for this template
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneName(node.getTextContent());
        }
        
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;
    }
    
    /**
     * Updates attributes of a template.
     * 
     * @param imageFileId the ID of the image file
     * @param optional
     * @return
     * @throws Exception
     */
	public UpdateTemplateResponse updateTemplate(String imageFileId, 
			HashMap<String,String> optional) throws Exception {
				
		LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateTemplate", optional);
		arguments.add(new NameValuePair("id", imageFileId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateTemplateResponse(responseDocument);
	}
	
    /**
     * Converts XML document into UpdateTemplateResponse object
     *
     * @param doc
     * @return
     */
    private UpdateTemplateResponse getUpdateTemplateResponse(Document doc) {
    	UpdateTemplateResponse response = new UpdateTemplateResponse();

        // get id from XML and set as the template ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get account from XML and set as 	the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the template belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get bootable from XML and set as true if the ISO is bootable, otherwise false 
        list = doc.getElementsByTagName("bootable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBootable(node.getTextContent());
        }

        // get checksum from XML and set as checksum of the template
        list = doc.getElementsByTagName("checksum");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setChecksum(node.getTextContent());
        }

        // get created from XML and set as the date this template was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateCreated(node.getTextContent());
        }

        // get crossZones from XML and set as true if the template is managed across all Zones, otherwise false
        list = doc.getElementsByTagName("crossZones");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCrossZones(node.getTextContent());
        }

        // get details from XML and set as additional key/value details tied with template
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get displaytext from XML and set as the template display text
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get domainid from XML and set the ID of the domain to which the template belongsl
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get format from XML and set as the format of the template.
        list = doc.getElementsByTagName("format");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateFormat(node.getTextContent());
        }
        
        // get hostid from XML and set as the ID of the secondary storage host for the template
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set as the name of the secondary storage host for the template
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the template is extractable, otherwise false
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get isfeatured from XML and set as true if this template is a featured template,otherwise false 
        list = doc.getElementsByTagName("isfeatured");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsFeatured(node.getTextContent());
        }
        
        // get isready from XML and set as true if the template is ready to be deployed from, otherwise false.
        list = doc.getElementsByTagName("isready");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsReady(node.getTextContent());
        }
        
        // get name from XML and set as the template name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateName(node.getTextContent());
        }
        
        // get ostypeid from XML and set as the ID of the OS type for this template.
        list = doc.getElementsByTagName("ostypeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeId(node.getTextContent());
        }
        
        // get ostypename from XML and set as the Name of the OS type for this template.
        list = doc.getElementsByTagName("ostypename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeName(node.getTextContent());
        }
        
        // get passwordenabled from XML and set as true if the reset password feature is enabled,otherwise false 
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }
        
        // get ispublic from XML and set as true if this template is a public template,otherwise false 
        list = doc.getElementsByTagName("ispublic");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsPublic(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the template
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectId(node.getTextContent());
        }
        
        // get removed from XML and set as the date this template was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }
        
        // get size from XML and set as the size of the template
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateSize(node.getTextContent());
        }
        
        // get sourcetemplateid from XML and set as the template ID of the parent template if present
        list = doc.getElementsByTagName("sourcetemplateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceTemplateId(node.getTextContent());
        }
        
        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateStatus(node.getTextContent());
        }
        
        // get templatetag from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetag");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTag(node.getTextContent());
        }
        
        // get templatetype from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateType(node.getTextContent());
        }
        
        // get zoneid from XML and set the ID of the zone for this template
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneId(node.getTextContent());
        }
        
        // get zonename from XML and set the name of the zone for this template
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneName(node.getTextContent());
        }
        
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;
    }
    
    /**
     * Copies a template from one zone to another.
     * 
     * @param templateId Template ID.
     * @param destZoneId ID of the zone the template is being copied to.
     * @param sourceZoneId ID of the zone the template is currently hosted on.
     * @return
     * @throws Exception
     */
	public CopyTemplateResponse copyTemplate(String templateId, 
			String destZoneId, String sourceZoneId) throws Exception {
				
		LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("copyTemplate", null);
		arguments.add(new NameValuePair("id", templateId));
		arguments.add(new NameValuePair("destzoneid", destZoneId));
		arguments.add(new NameValuePair("sourcezoneid", sourceZoneId));
        
        Document responseDocument = server.makeRequest(arguments);

        return getCopyTemplateResponse(responseDocument);
	}
	
    /**
     * Converts XML document into CopyTemplateResponse object
     *
     * @param doc
     * @return
     */
    private CopyTemplateResponse getCopyTemplateResponse(Document doc) {
    	CopyTemplateResponse response = new CopyTemplateResponse();

        // get id from XML and set as the template ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get account from XML and set as 	the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the template belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get bootable from XML and set as true if the ISO is bootable, otherwise false 
        list = doc.getElementsByTagName("bootable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBootable(node.getTextContent());
        }

        // get checksum from XML and set as checksum of the template
        list = doc.getElementsByTagName("checksum");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setChecksum(node.getTextContent());
        }

        // get created from XML and set as the date this template was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateCreated(node.getTextContent());
        }

        // get crossZones from XML and set as true if the template is managed across all Zones, otherwise false
        list = doc.getElementsByTagName("crossZones");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCrossZones(node.getTextContent());
        }

        // get details from XML and set as additional key/value details tied with template
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get displaytext from XML and set as the template display text
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get domainid from XML and set the ID of the domain to which the template belongsl
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get format from XML and set as the format of the template.
        list = doc.getElementsByTagName("format");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateFormat(node.getTextContent());
        }
        
        // get hostid from XML and set as the ID of the secondary storage host for the template
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set as the name of the secondary storage host for the template
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the template is extractable, otherwise false
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get isfeatured from XML and set as true if this template is a featured template,otherwise false 
        list = doc.getElementsByTagName("isfeatured");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsFeatured(node.getTextContent());
        }
        
        // get isready from XML and set as true if the template is ready to be deployed from, otherwise false.
        list = doc.getElementsByTagName("isready");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsReady(node.getTextContent());
        }
        
        // get name from XML and set as the template name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateName(node.getTextContent());
        }
        
        // get ostypeid from XML and set as the ID of the OS type for this template.
        list = doc.getElementsByTagName("ostypeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeId(node.getTextContent());
        }
        
        // get ostypename from XML and set as the Name of the OS type for this template.
        list = doc.getElementsByTagName("ostypename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeName(node.getTextContent());
        }
        
        // get passwordenabled from XML and set as true if the reset password feature is enabled,otherwise false 
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }
        
        // get project from XML and set as the project name of the template
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectName(node.getTextContent());
        }
        
        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectId(node.getTextContent());
        }
        
        // get removed from XML and set as the date this template was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }
        
        // get size from XML and set as the size of the template
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateSize(node.getTextContent());
        }
        
        // get sourcetemplateid from XML and set as the template ID of the parent template if present
        list = doc.getElementsByTagName("sourcetemplateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceTemplateId(node.getTextContent());
        }
        
        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateStatus(node.getTextContent());
        }
        
        // get templatetag from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetag");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTag(node.getTextContent());
        }
        
        // get templatetype from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateType(node.getTextContent());
        }
        
        // get zoneid from XML and set the ID of the zone for this template
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneId(node.getTextContent());
        }
        
        // get zonename from XML and set the name of the zone for this template
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneName(node.getTextContent());
        }
        
        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }
        
        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;
    }
    
    /**
     * Deletes a template from the system. 
     * All virtual machines using the deleted template will not be affected.
     * 
     * @param templateId The ID of the template
     * @param optional
     * @return
     * @throws Exception
     */
	public DeleteTemplateResponse deleteTemplate(String templateId, 
			HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("deleteTemplate", optional);
        arguments.add(new NameValuePair("id", templateId));
       
        Document responseDocument = server.makeRequest(arguments);

        return getDeleteTemplateResponse(responseDocument);
    }
	
	/**
     * Converts XML document into DeleteTemplateResponse object
     * 
     * @param doc
     * @return 
     */
    private DeleteTemplateResponse getDeleteTemplateResponse(Document doc) {
    	DeleteTemplateResponse response = new DeleteTemplateResponse();

	    // get displaytext from XML and set Any text associated with the success or 
	    // failure on Deleting Template
	    NodeList list = doc.getElementsByTagName("displaytext");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDisplayText(node.getTextContent());
	    }
            
            
            list = doc.getElementsByTagName("jobid");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setJobId(node.getTextContent());
	    }
	    
	    // get success from XML and set Return true if Deleting template operation 
	    // is executed successfully
	    list = doc.getElementsByTagName("success");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setSuccess(node.getTextContent());
	    }
	    
	     return response;
    }
    
    /**
     * List all public, private, and privileged templates.
     * 
     * @param templateFilter possible values are "featured", "self", "self-executable", "executable", and "community".* featured-templates that are featured and are public* self-templates that have been registered/created by the owner* selfexecutable-templates that have been registered/created by the owner that can be used to deploy a new VM* executable-all templates that can be used to deploy a new VM* community-templates that are public.
     * @param optional
     * @return
     * @throws Exception
     */
	public ListTemplateResponse listTemplates(String templateFilter, 
			HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listTemplates", optional);
        arguments.add(new NameValuePair("templatefilter", templateFilter));
       
        Document responseDocument = server.makeRequest(arguments);

        return getListTemplateResponse(responseDocument);
    }
	
    /**
     * Converts XML document into ListTemplateResponse object
     *
     * @param doc
     * @return
     */
    private ListTemplateResponse getListTemplateResponse(Document doc) {
    	ListTemplateResponse response = new ListTemplateResponse();
        
        //list of template
        NodeList list = doc.getElementsByTagName("template");
        
        List<TemplateResponse> templates = new LinkedList<TemplateResponse>(); 
                 
        if (list.getLength() > 0) {
            for (int index = 0; index < list.getLength(); index++) {
                Node templateNode = list.item(index);

                if (templateNode == null) {
                    continue;
                }  
                
                TemplateResponse template = new TemplateResponse();
                        
                NodeList templateProperties = templateNode.getChildNodes();
                for (int childIndex = 0; childIndex < templateProperties.getLength(); childIndex++) {
                    Node property = templateProperties.item(childIndex);
                    
                    if (property == null || property.getNodeName() == null) {
                        continue;
                    }
                    
                    if (property.getNodeName().equals("id")) {
                        template.setTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("account")) {
                    	template.setAccountName(property.getTextContent());
                    } else if (property.getNodeName().equals("accountid")) {
                    	template.setAccountId(property.getTextContent());
                    } else if (property.getNodeName().equals("bootable")) {
                    	template.setBootable(property.getTextContent());
                    } else if (property.getNodeName().equals("checksum")) {
                    	template.setChecksum(property.getTextContent());
                    } else if (property.getNodeName().equals("created")) {
                    	template.setTemplateCreated(property.getTextContent());
                    } else if (property.getNodeName().equals("crossZones")) {
                    	template.setCrossZones(property.getTextContent());
                    } else if (property.getNodeName().equals("details")) {
                    	template.setDetails(property.getTextContent());
                    } else if (property.getNodeName().equals("displaytext")) {
                    	template.setTemplateDisplayText(property.getTextContent());
                    } else if (property.getNodeName().equals("domain")) {
                    	template.setDomainName(property.getTextContent());
                    } else if (property.getNodeName().equals("domainid")) {
                    	template.setDomainId(property.getTextContent());
                    } else if (property.getNodeName().equals("format")) {
                    	template.setTemplateFormat(property.getTextContent());
                    } else if (property.getNodeName().equals("hostid")) {
                    	template.setHostId(property.getTextContent());
                    } else if (property.getNodeName().equals("hostname")) {
                    	template.setHostName(property.getTextContent());
                    } else if (property.getNodeName().equals("hypervisor")) {
                    	template.setHypervisor(property.getTextContent());
                    } else if (property.getNodeName().equals("isextractable")) {
                    	template.setIsExtractable(property.getTextContent());
                    } else if (property.getNodeName().equals("isfeatured")) {
                    	template.setIsFeatured(property.getTextContent());
                    } else if (property.getNodeName().equals("ispublic")) {
                    	template.setIsPublic(property.getTextContent());
                    } else if (property.getNodeName().equals("isready")) {
                    	template.setIsReady(property.getTextContent());
                    } else if (property.getNodeName().equals("name")) {
                    	template.setTemplateName(property.getTextContent());
                    } else if (property.getNodeName().equals("ostypeid")) {
                    	template.setOsTypeId(property.getTextContent());
                    } else if (property.getNodeName().equals("ostypename")) {
                    	template.setOsTypeName(property.getTextContent());
                    } else if (property.getNodeName().equals("passwordenabled")) {
                    	template.setPasswordEnabled(property.getTextContent());
                    } else if (property.getNodeName().equals("project")) {
                    	template.setTemplateProjectName(property.getTextContent());
                    }else if (property.getNodeName().equals("jobstatus")) {
                    	template.setJobStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("projectid")) {
                    	template.setTemplateProjectId(property.getTextContent());
                    } else if (property.getNodeName().equals("removed")) {
                    	template.setRemoved(property.getTextContent());
                    } else if (property.getNodeName().equals("size")) {
                    	template.setTemplateSize(property.getTextContent());
                    } else if (property.getNodeName().equals("sourcetemplateid")) {
                    	template.setSourceTemplateId(property.getTextContent());
                    } else if (property.getNodeName().equals("status")) {
                    	template.setTemplateStatus(property.getTextContent());
                    } else if (property.getNodeName().equals("templatetag")) {
                    	template.setTemplateTag(property.getTextContent());
                    } else if (property.getNodeName().equals("templatetype")) {
                    	template.setTemplateType(property.getTextContent());
                    } else if (property.getNodeName().equals("zoneid")) {
                    	template.setTemplateZoneId(property.getTextContent());
                    } else if (property.getNodeName().equals("zonename")) {
                    	template.setTemplateZoneName(property.getTextContent());
                    } else if (property.getNodeName().equals("jobid")) {
                    	template.setJobId(property.getTextContent());
                    }            
                }
                templates.add(template);
            }
        } 
        response.setTemplates(templates);
        return response;
    }
    
    /**
     * Updates a template visibility permissions. 
     * A public template is visible to all accounts within the same domain. 
     * A private template is visible only to the owner of the template. A priviledged template is a private template with account permissions added. 
     * Only accounts specified under the template permissions are visible to them.
     * 
     * @param templateId The template ID
     * @param optional
     * @return
     * @throws Exception
     */
    public UpdateTemplatePermissionsResponse updateTemplatePermissions(String templateId, 
			HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("updateTemplatePermissions", optional);
        arguments.add(new NameValuePair("id", templateId));
       
        Document responseDocument = server.makeRequest(arguments);

        return getUpdateTemplatePermissionsResponse(responseDocument);
    }
	
    /**
     * Converts XML document into UpdateTemplatePermissionsResponse object
     * 
     * @param doc
     * @return 
     */
    private UpdateTemplatePermissionsResponse getUpdateTemplatePermissionsResponse(Document doc) {
    	UpdateTemplatePermissionsResponse response = new UpdateTemplatePermissionsResponse();

	    // get displaytext from XML and set Any text associated with the success or 
	    // failure on updating Template
	    NodeList list = doc.getElementsByTagName("displaytext");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setDisplayText(node.getTextContent());
	    }
	    
	    // get success from XML and set Return true if updating template operation 
	    // is executed successfully
	    list = doc.getElementsByTagName("success");
	    if (list.getLength() > 0) {
	        Node node = list.item(0);
	        response.setSuccess(node.getTextContent());
	    }
	    
	     return response;
    }
    
    /**
     * List template visibility and all accounts that have permissions to view this template.
     * 
     * @param templateId The template ID
     * @return
     * @throws Exception
     */
    public ListTemplatePermissionsResponse listTemplatePermissions(String templateId) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("listTemplatePermissions", null);
        arguments.add(new NameValuePair("id", templateId));
       
        Document responseDocument = server.makeRequest(arguments);

        return getListTemplatePermissionsResponse(responseDocument);
    }
	
    /**
     * Converts XML document into ListTemplatePermissionsResponse object
     *
     * @param doc
     * @return
     */
    private ListTemplatePermissionsResponse getListTemplatePermissionsResponse(Document doc) {
    	ListTemplatePermissionsResponse response = new ListTemplatePermissionsResponse();

        // get id from XML and set as the template ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get account from XML and set as the list of accounts the template is available for
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountList(node.getTextContent());
        }
        
        // get domainid from XML and set as the ID of the domain to which the template belongs
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDomainId(node.getTextContent());
        }
        
        // get ispublic from XML and set as true if this template is a public template, false otherwise
        list = doc.getElementsByTagName("ispublic");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateIsPublic(node.getTextContent());
        }
        
        // get projectids from XML and set the list of projects the template is available for
        list = doc.getElementsByTagName("projectids");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectidList(node.getTextContent());
        }
        
        return response;
    }
    
    /**
     * Extracts a template
     * 
     * @param templateId The ID of the template
     * @param mode The ID of the template
     * @param optional
     * @return
     * @throws Exception
     */
    public ExtractTemplateResponse extractTemplate(String templateId, 
			String mode, HashMap<String,String> optional) 
			throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("extractTemplate", optional);
        arguments.add(new NameValuePair("id", templateId));
        arguments.add(new NameValuePair("mode", mode));
       
        Document responseDocument = server.makeRequest(arguments);

        return getExtractTemplateResponse(responseDocument);
    }
	
    /**
     * Converts XML document into ExtractTemplateResponse object
     *
     * @param doc
     * @return
     */
    private ExtractTemplateResponse getExtractTemplateResponse(Document doc) {
    	ExtractTemplateResponse response = new ExtractTemplateResponse();

        // get id from XML and set as the id of extracted object
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectId(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the extracted object belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectaccountid(node.getTextContent());
        }
        
        // get created from XML and set as the time and date the object was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectcreated(node.getTextContent());
        }
        
        // get extractId from XML and set as the upload id of extracted object
        list = doc.getElementsByTagName("extractId");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setUploadExtractedObjectId(node.getTextContent());
        }
        
        // get extractMode from XML and set as the mode of extraction - upload or download
        list = doc.getElementsByTagName("extractMode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectMode(node.getTextContent());
        }
        
        // get name from XML and set as the name of the extracted object
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectName(node.getTextContent());
        }
        
        // get state from XML and set as the state of the extracted object
        list = doc.getElementsByTagName("state");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectState(node.getTextContent());
        }
        
        // get status from XML and set as the status of the extraction
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectStatus(node.getTextContent());
        }
        
        // get storagetype from XML and set as type of the storage
        list = doc.getElementsByTagName("storagetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectStorageType(node.getTextContent());
        }
        
        // get uploadpercentage from XML and set as the percentage of the entity uploaded to the specified location
        list = doc.getElementsByTagName("uploadpercentage");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectUploadPercentage(node.getTextContent());
        }
        
        // get url from XML and set as if mode = upload then url of the uploaded entity. 
        // if mode = download the url from which the entity can be downloaded
        list = doc.getElementsByTagName("url");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectUrl(node.getTextContent());
        }
        
        // get zoneid from XML and set as the zone ID the object was extracted from
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectZoneid(node.getTextContent());
        }
        
        // get zonename from XML and set as the zone name the object was extracted from
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setExtractedObjectZonename(node.getTextContent());
        }
        
        return response;
    }
    
    /**
     * load template into primary storage
     * 
     * @param templateId template ID of the template to be prepared in primary storage(s).
     * @param templateZoneId zone ID of the template to be prepared in primary storage(s).
     * @return
     * @throws Exception
     */
    public PrepareTemplateResponse prepareTemplate(String templateId, 
			String templateZoneId)throws Exception {
        
        LinkedList<NameValuePair> arguments = 
                server.getDefaultQuery("prepareTemplate", null);
        arguments.add(new NameValuePair("templateid", templateId));
        arguments.add(new NameValuePair("zoneid", templateZoneId));
       
        Document responseDocument = server.makeRequest(arguments);

        return getPrepareTemplateResponse(responseDocument);
    }
	
    /**
     * Converts XML document into PrepareTemplateResponse object
     *
     * @param doc
     * @return
     */
    private PrepareTemplateResponse getPrepareTemplateResponse(Document doc) {
    	PrepareTemplateResponse response = new PrepareTemplateResponse();

        // get id from XML and set as the template ID
        NodeList list = doc.getElementsByTagName("id");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateId(node.getTextContent());
        }

        // get account from XML and set as 	the account name to which the template belongs
        list = doc.getElementsByTagName("account");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountName(node.getTextContent());
        }

        // get accountid from XML and set as the account id to which the template belongs
        list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAccountId(node.getTextContent());
        }

        // get bootable from XML and set as true if the ISO is bootable, otherwise false 
        list = doc.getElementsByTagName("bootable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setBootable(node.getTextContent());
        }

        // get checksum from XML and set as checksum of the template
        list = doc.getElementsByTagName("checksum");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setChecksum(node.getTextContent());
        }

        // get created from XML and set as the date this template was created
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateCreated(node.getTextContent());
        }

        // get crossZones from XML and set as true if the template is managed across all Zones, otherwise false
        list = doc.getElementsByTagName("crossZones");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setCrossZones(node.getTextContent());
        }

        // get details from XML and set as additional key/value details tied with template
        list = doc.getElementsByTagName("details");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDetails(node.getTextContent());
        }

        // get displaytext from XML and set as the template display text
        list = doc.getElementsByTagName("displaytext");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateDisplayText(node.getTextContent());
        }

        // get domain from XML and set as the name of the domain to which the template belongs
        list = doc.getElementsByTagName("domain");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainName(node.getTextContent());
        }

        // get domainid from XML and set the ID of the domain to which the template belongsl
        list = doc.getElementsByTagName("domainid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setDomainId(node.getTextContent());
        }

        // get format from XML and set as the format of the template.
        list = doc.getElementsByTagName("format");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateFormat(node.getTextContent());
        }

        // get hostid from XML and set as the ID of the secondary storage host for the template
        list = doc.getElementsByTagName("hostid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostId(node.getTextContent());
        }

        // get hostname from XML and set as the name of the secondary storage host for the template
        list = doc.getElementsByTagName("hostname");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHostName(node.getTextContent());
        }

        // get hypervisor from XML and set the hypervisor on which the template runs
        list = doc.getElementsByTagName("hypervisor");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setHypervisor(node.getTextContent());
        }

        // get isextractable from XML and set as true if the template is extractable, otherwise false
        list = doc.getElementsByTagName("isextractable");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsExtractable(node.getTextContent());
        }

        // get isfeatured from XML and set as true if this template is a featured template,otherwise false 
        list = doc.getElementsByTagName("isfeatured");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsFeatured(node.getTextContent());
        }

        // get isready from XML and set as true if the template is ready to be deployed from, otherwise false.
        list = doc.getElementsByTagName("isready");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setIsReady(node.getTextContent());
        }

        // get name from XML and set as the template name
        list = doc.getElementsByTagName("name");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateName(node.getTextContent());
        }

        // get ostypeid from XML and set as the ID of the OS type for this template.
        list = doc.getElementsByTagName("ostypeid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeId(node.getTextContent());
        }

        // get ostypename from XML and set as the Name of the OS type for this template.
        list = doc.getElementsByTagName("ostypename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setOsTypeName(node.getTextContent());
        }

        // get passwordenabled from XML and set as true if the reset password feature is enabled,otherwise false 
        list = doc.getElementsByTagName("passwordenabled");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setPasswordEnabled(node.getTextContent());
        }

        // get project from XML and set as the project name of the template
        list = doc.getElementsByTagName("project");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectName(node.getTextContent());
        }

        // get projectid from XML and set as the project id of the template
        list = doc.getElementsByTagName("projectid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateProjectId(node.getTextContent());
        }

        // get removed from XML and set as the date this template was removed
        list = doc.getElementsByTagName("removed");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setRemoved(node.getTextContent());
        }

        // get size from XML and set as the size of the template
        list = doc.getElementsByTagName("size");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateSize(node.getTextContent());
        }

        // get sourcetemplateid from XML and set as the template ID of the parent template if present
        list = doc.getElementsByTagName("sourcetemplateid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setSourceTemplateId(node.getTextContent());
        }

        // get status from XML and set as the status of the template
        list = doc.getElementsByTagName("status");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateStatus(node.getTextContent());
        }

        // get templatetag from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetag");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateTag(node.getTextContent());
        }

        // get templatetype from XML and set as the tag of this template
        list = doc.getElementsByTagName("templatetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateType(node.getTextContent());
        }

        // get zoneid from XML and set the ID of the zone for this template
        list = doc.getElementsByTagName("zoneid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneId(node.getTextContent());
        }

        // get zonename from XML and set the name of the zone for this template
        list = doc.getElementsByTagName("zonename");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setTemplateZoneName(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the latest async job acting on this object
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobId(node.getTextContent());
        }

        // get jobstatus from XML and set as the current status of the latest async job acting on this object
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setJobStatus(node.getTextContent());
        }

        return response;
    }
    
    /**
     * Retrieves the current status of asynchronous job for template.
     * 
     * @param asychronousJobid the ID of the asychronous job
     * @return
     * @throws Exception
     */
    public TemplateJobResultResponse templateJobResult(String asychronousJobid) 
                    throws Exception {

            LinkedList<NameValuePair> arguments = 
                    server.getDefaultQuery("queryAsyncJobResult", null);
            arguments.add(new NameValuePair("jobid",  asychronousJobid));

            Document responseDocument = server.makeRequest(arguments);

            return getTemplateJobResultResponse(responseDocument);
    }
	
    /**
     * Converts XML document into VolumeJobResultResponse object
     * 
     * @param doc
     * @return
     */
    private TemplateJobResultResponse getTemplateJobResultResponse(Document doc) {
        TemplateJobResultResponse response = new TemplateJobResultResponse();

        // get accountid from XML and set as the account that executed the async command
        NodeList list = doc.getElementsByTagName("accountid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousAccountId(node.getTextContent());
        }

        // get cmd from XML and set as the async command executed
        list = doc.getElementsByTagName("cmd");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCmd(node.getTextContent());                   
        }

        // get created from XML and set as the created date of the job
        list = doc.getElementsByTagName("created");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousCreated(node.getTextContent());
        }

        // get jobinstanceid from XML and set as the unique ID of the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstanceid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobInstanceId(node.getTextContent());
        }

        // get jobinstancetype from XML and set as the instance/entity object related to the job
        list = doc.getElementsByTagName("jobinstancetype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobInstanceType(node.getTextContent());
        }

        // get jobprocstatus from XML and set as the progress information of the PENDING job
        list = doc.getElementsByTagName("jobprocstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobProgressStatus(node.getTextContent());
        }

        // get jobresult from XML and set as the result reason
        list = doc.getElementsByTagName("jobresult");
        if (list.getLength() > 0) {
            NodeList node = list.item(0).getChildNodes();    
            for (int index = 0; index < node.getLength(); index++) {
                Node nodeProperty = node.item(index);
                if (nodeProperty.getNodeName().equals("template")) {
                    NodeList childNodeProperties =  nodeProperty.getChildNodes();
                    for (int childIndex = 0; childIndex < childNodeProperties.getLength(); childIndex++) {
                        Node childNodeProperty = childNodeProperties.item(childIndex);
                        if (childNodeProperty.getNodeName().equals("id")) {
                            response.setTemplateId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("account")) {
                            response.setAccountName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("accountid")) {
                            response.setAccountId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("bootable")) {
                            response.setBootable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("checksum")) {
                            response.setChecksum(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("created")) {
                            response.setTemplateCreated(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("crossZones")) {
                            response.setCrossZones(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("details")) {
                            response.setDetails(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("displaytext")) {
                            response.setTemplateDisplayText(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domain")) {
                            response.setDomainName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("domainid")) {
                            response.setDomainId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("format")) {
                            response.setTemplateFormat(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hostid")) {
                            response.setHostId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hostname")) {
                            response.setHostName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("hypervisor")) {
                            response.setHypervisor(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("isextractable")) {
                            response.setIsExtractable(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("isfeatured")) {
                            response.setIsFeatured(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ispublic")) {
                            response.setIsPublic(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("isready")) {
                            response.setIsReady(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("name")) {
                            response.setTemplateName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ostypeid")) {
                            response.setOsTypeId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("ostypename")) {
                            response.setOsTypeName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("passwordenabled")) {
                            response.setPasswordEnabled(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("project")) {
                            response.setTemplateProjectName(childNodeProperty.getTextContent());
                        }else if (childNodeProperty.getNodeName().equals("jobstatus")) {
                            response.setJobStatus(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("projectid")) {
                            response.setTemplateProjectId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("removed")) {
                            response.setRemoved(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("size")) {
                            response.setTemplateSize(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("sourcetemplateid")) {
                            response.setSourceTemplateId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("status")) {
                            response.setTemplateStatus(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("templatetag")) {
                            response.setTemplateTag(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("templatetype")) {
                            response.setTemplateType(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zoneid")) {
                            response.setTemplateZoneId(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("zonename")) {
                            response.setTemplateZoneName(childNodeProperty.getTextContent());
                        } else if (childNodeProperty.getNodeName().equals("jobid")) {
                            response.setJobId(childNodeProperty.getTextContent());
                        } 
                    }
                } else if(nodeProperty.getNodeName().equals("errorcode")) {
                    response.setErrorCode(nodeProperty.getTextContent());
                } else if(nodeProperty.getNodeName().equals("errortext")) {
                    response.setErrorText(nodeProperty.getTextContent());
                }
            }
        }

        // get jobresultcode from XML and set as the result code for the job
        list = doc.getElementsByTagName("jobresultcode");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobResultCode(node.getTextContent());
        }

        // get jobresulttype from XML and set as the result type
        list = doc.getElementsByTagName("jobresulttype");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobResultType(node.getTextContent());
        }

        // get jobstatus from XML and set as the current job status-should be 0 for PENDING
        list = doc.getElementsByTagName("jobstatus");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobStatus(node.getTextContent());
        }

        // get userid from XML and set as the user that executed the async command
        list = doc.getElementsByTagName("userid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousUserId(node.getTextContent());
        }

        // get jobid from XML and set as the ID of the async job
        list = doc.getElementsByTagName("jobid");
        if (list.getLength() > 0) {
            Node node = list.item(0);
            response.setAsychronousJobId(node.getTextContent());
        }

        return response;
    }
    
    
    
    
}

