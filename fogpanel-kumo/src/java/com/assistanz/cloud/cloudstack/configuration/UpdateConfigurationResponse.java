package com.assistanz.cloud.cloudstack.configuration;

public class UpdateConfigurationResponse {
	
	/**
	 * the category of the configuration
	 */
	private String configurationCategory;
	
	/**
	 * the description of the configuration
	 */
	private String configurationDescription;
	
	/**
	 * the name of the configuration
	 */
	private String configurationName;
	
	/**
	 * the value of the configuration
	 */
	private String configurationValue;

	public String getConfigurationCategory() {
		return configurationCategory;
	}

	public void setConfigurationCategory(String configurationCategory) {
		this.configurationCategory = configurationCategory;
	}

	public String getConfigurationDescription() {
		return configurationDescription;
	}

	public void setConfigurationDescription(String configurationDescription) {
		this.configurationDescription = configurationDescription;
	}

	public String getConfigurationName() {
		return configurationName;
	}

	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

	public String getConfigurationValue() {
		return configurationValue;
	}

	public void setConfigurationValue(String configurationValue) {
		this.configurationValue = configurationValue;
	}	
}
