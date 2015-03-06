package com.assistanz.cloud.cloudstack.project;

/**
 * 
 * @author Gopwtham
 *
 */
public class ListProjectsResponse {
	
	/**
	 * the id of the project
	 */
	private String projectId;
	
	/**
	 * the account name of the project's owner
	 */
	private String projectAccount;
	
	/**
	 * the displaytext of the project
	 */
	private String projectDisplayText;
	
	/**
	 * the domain name where the project belongs to
	 */
	private String projectDomain;
	
	/**
	 * the domain id the project belongs to
	 */
	private String projectDomainId;
	
	/**
	 * the name of the project
	 */
	private String projectName;
	
	/**
	 * the state of the project
	 */
	private String projectState;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectAccount() {
		return projectAccount;
	}

	public void setProjectAccount(String projectAccount) {
		this.projectAccount = projectAccount;
	}

	public String getProjectDisplayText() {
		return projectDisplayText;
	}

	public void setProjectDisplayText(String projectDisplayText) {
		this.projectDisplayText = projectDisplayText;
	}

	public String getProjectDomain() {
		return projectDomain;
	}

	public void setProjectDomain(String projectDomain) {
		this.projectDomain = projectDomain;
	}

	public String getProjectDomainId() {
		return projectDomainId;
	}

	public void setProjectDomainId(String projectDomainId) {
		this.projectDomainId = projectDomainId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectState() {
		return projectState;
	}

	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}

}
