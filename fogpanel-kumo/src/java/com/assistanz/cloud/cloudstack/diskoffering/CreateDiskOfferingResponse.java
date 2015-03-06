package com.assistanz.cloud.cloudstack.diskoffering;

/**
 * 
 * @author Gowtham
 *
 */
public class CreateDiskOfferingResponse {
	
	/**
	 * unique ID of the disk offering
	 */
	private String diskOfferingId;
	
	/**
	 * the date this disk offering was created
	 */
	private String created;
	
	/**
	 * the size of the disk offering in GB
	 */
	private String diskSize;
	
	/**
	 * an alternate display text of the disk offering.
	 */
	private String displayText;
	
	/**
	 * the domain name this disk offering belongs to. 
	 * Ignore this information as it is not currently applicable.
	 */
	private String diskOfferingDomain;
		
	/**
	 * the domain ID this disk offering belongs to. 
	 * Ignore this information as it is not currently applicable.
	 */
	private String diskOfferingDomainId;	
	
	/**
	 * true if disk offering uses custom size, false otherwise
	 */
	private String isCustomized;
	
	/**
	 * the name of the disk offering
	 */
	private String diskOfferingName;
	
	/**
	 * the tags for the disk offering
	 */
	private String diskOfferingTags;

	public String getDiskOfferingId() {
		return diskOfferingId;
	}

	public void setDiskOfferingId(String diskOfferingId) {
		this.diskOfferingId = diskOfferingId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getDiskSize() {
		return diskSize;
	}

	public void setDiskSize(String diskSize) {
		this.diskSize = diskSize;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	
	public String getDiskOfferingDomain() {
		return diskOfferingDomain;
	}

	public void setDiskOfferingDomain(String diskOfferingDomain) {
		this.diskOfferingDomain = diskOfferingDomain;
	}

	public String getDiskOfferingDomainId() {
		return diskOfferingDomainId;
	}

	public void setDiskOfferingDomainId(String diskOfferingDomainId) {
		this.diskOfferingDomainId = diskOfferingDomainId;
	}

	public String getIsCustomized() {
		return isCustomized;
	}

	public void setIsCustomized(String isCustomized) {
		this.isCustomized = isCustomized;
	}

	public String getDiskOfferingName() {
		return diskOfferingName;
	}

	public void setDiskOfferingName(String diskOfferingName) {
		this.diskOfferingName = diskOfferingName;
	}

	public String getDiskOfferingTags() {
		return diskOfferingTags;
	}

	public void setDiskOfferingTags(String diskOfferingTags) {
		this.diskOfferingTags = diskOfferingTags;
	}	
	
	

}
