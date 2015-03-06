package com.assistanz.cloud.cloudstack.vmgroup;

/**
 * 
 * @author Gowtham
 *
 */
public class CreateInstanceGroupResponse {
	
	/**
	 * the id of the instance group
	 */
	String instanceGroupId;
	
	/**
	 * the account owning the instance group
	 */
	String instanceGroupAccount;
	
	/**
	 * time and date the instance group was created
	 */
	String instanceGroupCreated;	
	
	/**
	 * the domain name of the instance group
	 */
	String instanceGroupDomain;	
	
	/**
	 * the domain ID of the instance group
	 */
	String instanceGroupDomainId;
	
	/**
	 * the name of the instance group
	 */
	String instanceGroupName;
	
	/**
	 * the project name of the group
	 */
	String groupProjectName;	
	
	/**
	 * the project id of the group
	 */
	String groupProjectId;

	public String getInstanceGroupId() {
		return instanceGroupId;
	}

	public void setInstanceGroupId(String instanceGroupId) {
		this.instanceGroupId = instanceGroupId;
	}

	public String getInstanceGroupAccount() {
		return instanceGroupAccount;
	}

	public void setInstanceGroupAccount(String instanceGroupAccount) {
		this.instanceGroupAccount = instanceGroupAccount;
	}

	public String getInstanceGroupCreated() {
		return instanceGroupCreated;
	}

	public void setInstanceGroupCreated(String instanceGroupCreated) {
		this.instanceGroupCreated = instanceGroupCreated;
	}

	public String getInstanceGroupDomain() {
		return instanceGroupDomain;
	}

	public void setInstanceGroupDomain(String instanceGroupDomain) {
		this.instanceGroupDomain = instanceGroupDomain;
	}

	public String getInstanceGroupDomainId() {
		return instanceGroupDomainId;
	}

	public void setInstanceGroupDomainId(String instanceGroupDomainId) {
		this.instanceGroupDomainId = instanceGroupDomainId;
	}

	public String getInstanceGroupName() {
		return instanceGroupName;
	}

	public void setInstanceGroupName(String instanceGroupName) {
		this.instanceGroupName = instanceGroupName;
	}

	public String getGroupProjectName() {
		return groupProjectName;
	}

	public void setGroupProjectName(String groupProjectName) {
		this.groupProjectName = groupProjectName;
	}

	public String getGroupProjectId() {
		return groupProjectId;
	}

	public void setGroupProjectId(String groupProjectId) {
		this.groupProjectId = groupProjectId;
	}	

}
