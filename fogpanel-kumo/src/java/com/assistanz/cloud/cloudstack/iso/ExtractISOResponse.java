package com.assistanz.cloud.cloudstack.iso;

/**
 * 
 * @author Gowtham
 *
 */
public class ExtractISOResponse {
	
	/**
	 * the id of extracted object
	 */
	private String extractedObjectId;
	
	/**
	 * the account id to which the extracted object belongs
	 */
	private String extractedObjectaccountid;
	
	/**
	 * the time and date the object was created
	 */
	private String extractedObjectcreated;	
	
	/**
	 * the upload id of extracted object
	 */
	private String uploadExtractedObjectId;
	
	/**
	 * the mode of extraction - upload or download
	 */
	private String extractedObjectMode;	
	
	/**
	 * the name of the extracted object
	 */
	private String extractedObjectName;
	
	/**
	 * the state of the extracted object
	 */
	private String extractedObjectState;
	/**
	 * the status of the extraction
	 */
	private String extractedObjectStatus;
	
	/**
	 * type of the storage
	 */
	private String extractedObjectStorageType;	
	
	/**
	 * the percentage of the entity uploaded to the specified location
	 */
	private String extractedObjectUploadPercentage;
	/**
	 * if mode = upload then url of the uploaded entity. 
	 * if mode = download the url from which the entity can be downloaded
	 */
	private String extractedObjectUrl;	
	
	/**
	 * zone ID the object was extracted from
	 */
	private String extractedObjectZoneid;
	
	/**
	 * zone name the object was extracted from
	 */
	private String extractedObjectZonename;

	public String getExtractedObjectId() {
		return extractedObjectId;
	}

	public void setExtractedObjectId(String extractedObjectId) {
		this.extractedObjectId = extractedObjectId;
	}

	public String getExtractedObjectaccountid() {
		return extractedObjectaccountid;
	}

	public void setExtractedObjectaccountid(String extractedObjectaccountid) {
		this.extractedObjectaccountid = extractedObjectaccountid;
	}

	public String getExtractedObjectcreated() {
		return extractedObjectcreated;
	}

	public void setExtractedObjectcreated(String extractedObjectcreated) {
		this.extractedObjectcreated = extractedObjectcreated;
	}

	public String getUploadExtractedObjectId() {
		return uploadExtractedObjectId;
	}

	public void setUploadExtractedObjectId(String uploadExtractedObjectId) {
		this.uploadExtractedObjectId = uploadExtractedObjectId;
	}

	public String getExtractedObjectMode() {
		return extractedObjectMode;
	}

	public void setExtractedObjectMode(String extractedObjectMode) {
		this.extractedObjectMode = extractedObjectMode;
	}

	public String getExtractedObjectName() {
		return extractedObjectName;
	}

	public void setExtractedObjectName(String extractedObjectName) {
		this.extractedObjectName = extractedObjectName;
	}

	public String getExtractedObjectState() {
		return extractedObjectState;
	}

	public void setExtractedObjectState(String extractedObjectState) {
		this.extractedObjectState = extractedObjectState;
	}

	public String getExtractedObjectStatus() {
		return extractedObjectStatus;
	}

	public void setExtractedObjectStatus(String extractedObjectStatus) {
		this.extractedObjectStatus = extractedObjectStatus;
	}

	public String getExtractedObjectStorageType() {
		return extractedObjectStorageType;
	}

	public void setExtractedObjectStorageType(String extractedObjectStorageType) {
		this.extractedObjectStorageType = extractedObjectStorageType;
	}

	public String getExtractedObjectUploadPercentage() {
		return extractedObjectUploadPercentage;
	}

	public void setExtractedObjectUploadPercentage(
			String extractedObjectUploadPercentage) {
		this.extractedObjectUploadPercentage = extractedObjectUploadPercentage;
	}

	public String getExtractedObjectUrl() {
		return extractedObjectUrl;
	}

	public void setExtractedObjectUrl(String extractedObjectUrl) {
		this.extractedObjectUrl = extractedObjectUrl;
	}

	public String getExtractedObjectZoneid() {
		return extractedObjectZoneid;
	}

	public void setExtractedObjectZoneid(String extractedObjectZoneid) {
		this.extractedObjectZoneid = extractedObjectZoneid;
	}

	public String getExtractedObjectZonename() {
		return extractedObjectZonename;
	}

	public void setExtractedObjectZonename(String extractedObjectZonename) {
		this.extractedObjectZonename = extractedObjectZonename;
	}
}
