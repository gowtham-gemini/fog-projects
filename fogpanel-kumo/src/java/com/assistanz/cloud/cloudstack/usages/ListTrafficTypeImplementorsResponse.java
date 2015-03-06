package com.assistanz.cloud.cloudstack.usages;

/**
 * 
 * @author Gowtham
 *
 */
public class ListTrafficTypeImplementorsResponse {
	
	/**
	 * traffic type
	 */
	private String trafficTypeNetwork;
	
	/**
	 * implementor of network traffic type
	 */
	private String trafficTypeImplementor;

	public String getTrafficTypeNetwork() {
		return trafficTypeNetwork;
	}

	public void setTrafficTypeNetwork(String trafficTypeNetwork) {
		this.trafficTypeNetwork = trafficTypeNetwork;
	}

	public String getTrafficTypeImplementor() {
		return trafficTypeImplementor;
	}

	public void setTrafficTypeImplementor(String trafficTypeImplementor) {
		this.trafficTypeImplementor = trafficTypeImplementor;
	}	
	
	
}
