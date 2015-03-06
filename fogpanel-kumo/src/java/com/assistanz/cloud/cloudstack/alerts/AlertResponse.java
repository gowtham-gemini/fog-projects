package com.assistanz.cloud.cloudstack.alerts;

/**
 * 
 * @author Gowtham
 *
 */
public class AlertResponse {
	
	/**
	 * the id of the alert
	 */
	private String alertId;
	
	/**
	 * description of the alert
	 */
	private String alertDescription;	
	
	/**
	 * the date and time the alert was sent
	 */
	private String alertSent;
	
	/**
	 * the alert type
	 */
	private String alertType;

	public String getAlertId() {
		return alertId;
	}

	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}

	public String getAlertDescription() {
		return alertDescription;
	}

	public void setAlertDescription(String alertDescription) {
		this.alertDescription = alertDescription;
	}

	public String getAlertSent() {
		return alertSent;
	}

	public void setAlertSent(String alertSent) {
		this.alertSent = alertSent;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	
}
