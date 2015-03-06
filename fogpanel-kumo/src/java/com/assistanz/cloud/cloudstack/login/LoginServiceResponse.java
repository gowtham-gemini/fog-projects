package com.assistanz.cloud.cloudstack.login;

/**
 * 
 * @author Gowtham
 *
 */
public class LoginServiceResponse {
	
	/**
	 * Username
	 */
	private String userName;
	
	/**
	 * User id
	 */
	private String userId;
	
	/**
	 * Password
	 */
	private String password;
	
	/**
	 * domain ID that the user belongs to
	 */
	private String domainId;
	
	/**
	 * the time period before the session has expired
	 */
	private String timeout;
	
	/**
	 * the account name the user belongs to
	 */
	private String accountName;
	
	/**
	 * first name of the user
	 */
	private String firstName;
	
	/**
	 * last name of the user
	 */
	private String lastName;
	
	/**
	 * the account type (admin, domain-admin, read-only-admin, user)
	 */
	private String accountType;
	
	/**
	 * user time zone
	 */
	private String timeZone;
	
	/**
	 * user time zone offset from UTC 00:00
	 */
	private String timeZoneOffset;
	
	/**
	 * Session key that can be passed in subsequent Query command calls
	 */
	private String sessionKey;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZoneOffset() {
		return timeZoneOffset;
	}

	public void setTimeZoneOffset(String timeZoneOffset) {
		this.timeZoneOffset = timeZoneOffset;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}	

	
}
