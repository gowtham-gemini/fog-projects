package com.assistanz.cloud.cloudstack.template;

public class ListTemplatePermissionsResponse {
	
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
	private String templateDomainId;
	
	/**	
	 * true if this template is a public template, false otherwise
	 */
	private String templateIsPublic;
	
	/**	
	 * the list of projects the template is available for
	 */
	private String templateProjectidList;

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

	public String getTemplateDomainId() {
		return templateDomainId;
	}

	public void setTemplateDomainId(String templateDomainId) {
		this.templateDomainId = templateDomainId;
	}

	public String getTemplateIsPublic() {
		return templateIsPublic;
	}

	public void setTemplateIsPublic(String templateIsPublic) {
		this.templateIsPublic = templateIsPublic;
	}

	public String getTemplateProjectidList() {
		return templateProjectidList;
	}

	public void setTemplateProjectidList(String templateProjectidList) {
		this.templateProjectidList = templateProjectidList;
	}	
}
