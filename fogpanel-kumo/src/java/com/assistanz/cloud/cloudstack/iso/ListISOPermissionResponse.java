package com.assistanz.cloud.cloudstack.iso;

/**
 * 
 * @author Gowtham
 *
 */
public class ListISOPermissionResponse {
	
	/**
	 * the template ID
	 */
	private String templateId;
	
	/**
	 * the list of accounts the template is available for
	 */
	private String accountList;	
	
	/**
	 * the ID of the domain to which the template belongs
	 */
	private String domainId;
	
	/**
	 * true if this template is a public template, false otherwise
	 */
	private String isPublic;
	
	/**
	 * the list of projects the template is available for
	 */
	private String projectIdList;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getAccountList() {
		return accountList;
	}

	public void setAccountList(String accountList) {
		this.accountList = accountList;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	public String getProjectIdList() {
		return projectIdList;
	}

	public void setProjectIdList(String projectIdList) {
		this.projectIdList = projectIdList;
	}	
}
