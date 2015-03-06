/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.cloud.cloudstack.events;

/**
 *
 * @author gowtham
 */
class EventsResponse {
    
    
    	/**
	 * the ID of the event
	 */
	private String eventId;
	
	/**
	 * the account name for the account that owns the object being acted on in the event 
	 * (e.g. the owner of the virtual machine, ip address, or security group)
	 */
	private String accountName;
	
	/**
	 * the date the event was created
	 */
	private String created;
	
	/**
	 * a brief description of the event
	 */
	private String eventDescription;	

	/**
	 * the name of the account's domain
	 */
	private String domainName;
	
	/**
	 * the id of the account's domain
	 */
	private String domainId;
	
	/**
	 * the event level (INFO, WARN, ERROR)
	 */
	private String eventLevel;
	
	/**
	 * whether the event is parented
	 */
	private String eventParentId;
	
	/**
	 * the project name of the address
	 */
	private String projectName;	
	
	/**
	 * the project id of the ip address
	 */
	private String projectId;
	
	/**
	 * the state of the event
	 */
	private String eventState;
	
	/**
	 * the type of the event (see event types)
	 */
	private String EventType;
	
	/**
	 * the name of the user who performed the action (can be different from the account 
	 * if an admin is performing an action for a user, 
	 * e.g. starting/stopping a user's virtual machine)
	 */
	private String userName;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}

	public String getEventParentId() {
		return eventParentId;
	}

	public void setEventParentId(String eventParentId) {
		this.eventParentId = eventParentId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getEventState() {
		return eventState;
	}

	public void setEventState(String eventState) {
		this.eventState = eventState;
	}

	public String getEventType() {
		return EventType;
	}

	public void setEventType(String eventType) {
		EventType = eventType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}	
    
}
