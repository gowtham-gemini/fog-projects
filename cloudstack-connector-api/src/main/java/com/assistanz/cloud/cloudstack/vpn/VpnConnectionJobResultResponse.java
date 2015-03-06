package com.assistanz.cloud.cloudstack.vpn;

/**
 *
 * @author gowtham
 */
class VpnConnectionJobResultResponse {

    /**
     * the account that executed the async command
     */
    private String asynchronousAccountId;

    /**
     * the async command executed
     */
    private String asynchronousCmd;

    /**
     * the created date of the job
     */
    private String asynchronousCreated;

    /**
     * the unique ID of the instance/entity object related to the job
     */
    private String asynchronousJobInstanceId;

    /**
     * the instance/entity object related to the job
     */
    private String asynchronousJobInstanceType;

    /**
     * the progress information of the PENDING job
     */
    private String asynchronousJobProgressStatus;

    /**
     * the result reason
     */
    private String asynchronousJobResult;

    /**
     * the result code for the job
     */
    private String asynchronousJobResultCode;

    /**
     * the result type
     */
    private String asynchronousJobResultType;

    /**
     * the current job status-should be 0 for PENDING
     */
    private String asynchronousJobStatus;

    /**
     * the user that executed the async command
     */
    private String asynchronousUserId;

    /**
     * the ID of the async job
     */
    private String asynchronousJobId;

    public String getAsynchronousAccountId() {
        return asynchronousAccountId;
    }

    public void setAsynchronousAccountId(String asynchronousAccountId) {
        this.asynchronousAccountId = asynchronousAccountId;
    }

    public String getAsynchronousCmd() {
        return asynchronousCmd;
    }

    public void setAsynchronousCmd(String asynchronousCmd) {
        this.asynchronousCmd = asynchronousCmd;
    }

    public String getAsynchronousCreated() {
        return asynchronousCreated;
    }

    public void setAsynchronousCreated(String asynchronousCreated) {
        this.asynchronousCreated = asynchronousCreated;
    }

    public String getAsynchronousJobInstanceId() {
        return asynchronousJobInstanceId;
    }

    public void setAsynchronousJobInstanceId(String asynchronousJobInstanceId) {
        this.asynchronousJobInstanceId = asynchronousJobInstanceId;
    }

    public String getAsynchronousJobInstanceType() {
        return asynchronousJobInstanceType;
    }

    public void setAsynchronousJobInstanceType(String asynchronousJobInstanceType) {
        this.asynchronousJobInstanceType = asynchronousJobInstanceType;
    }

    public String getAsynchronousJobProgressStatus() {
        return asynchronousJobProgressStatus;
    }

    public void setAsynchronousJobProgressStatus(String asynchronousJobProgressStatus) {
        this.asynchronousJobProgressStatus = asynchronousJobProgressStatus;
    }

    public String getAsynchronousJobResult() {
        return asynchronousJobResult;
    }

    public void setAsynchronousJobResult(String asynchronousJobResult) {
        this.asynchronousJobResult = asynchronousJobResult;
    }

    public String getAsynchronousJobResultCode() {
        return asynchronousJobResultCode;
    }

    public void setAsynchronousJobResultCode(String asynchronousJobResultCode) {
        this.asynchronousJobResultCode = asynchronousJobResultCode;
    }

    public String getAsynchronousJobResultType() {
        return asynchronousJobResultType;
    }

    public void setAsynchronousJobResultType(String asynchronousJobResultType) {
        this.asynchronousJobResultType = asynchronousJobResultType;
    }

    public String getAsynchronousJobStatus() {
        return asynchronousJobStatus;
    }

    public void setAsynchronousJobStatus(String asynchronousJobStatus) {
        this.asynchronousJobStatus = asynchronousJobStatus;
    }

    public String getAsynchronousUserId() {
        return asynchronousUserId;
    }

    public void setAsynchronousUserId(String asynchronousUserId) {
        this.asynchronousUserId = asynchronousUserId;
    }

    public String getAsynchronousJobId() {
        return asynchronousJobId;
    }

    public void setAsynchronousJobId(String asynchronousJobId) {
        this.asynchronousJobId = asynchronousJobId;
    }
   
    /**
     * the current error Code of the latest async job acting on this object
     */
    private String errorCode;
    /**
     * the current error Text of the latest async job acting on this object
     */
    private String errorText;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    /**
     * the vpn gateway ID
     */
    private String id;

    /**
     * the owner
     */
    private String account;

    /**
     * guest cidr list of the customer gateway
     */
    private String cidrList;
    
    /**
     * the date and time the host was created
     */
    private String created;

    /**
     * the domain name of the owner
     */
    private String domain;

    /**
     * the domain id of the owner
     */
    private String domainId;

    /**
     * if DPD is enabled for customer gateway
     */
    private String dpd;

    /**
     * Lifetime of ESP SA of customer gateway
     */
    private String espLifeTime;

    /**
     * IPsec policy of customer gateway
     */
    private String espPolicy;
    
    /**
     * public ip address id of the customer gateway
     */
    private String gateway;
    
    /**
     * Lifetime of IKE SA of customer gateway
     */
    private String ikeLifeTime;
    
    /**
     * IKE policy of customer gateway
     */
    private String ikePolicy;
    
    /**
     * IPsec preshared-key of customer gateway
     */
    private String ipsecPsk;
    
    /**
     * the project name
     */
    private String project;
    
    /**
     * the project id
     */
    private String projectId;
    
    /**
     * the public IP address
     */
    private String publicIp;
    
    /**
     * the date and time the host was removed
     */
    private String removed;
    
    /**
     * the customer gateway ID
     */
    private String s2sCustomerGatewayId;
    
    /**
     * the vpn gateway ID
     */
    private String s2sVpnGatewayId;
    
    /**
     * State of vpn connection
     */
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCidrList() {
        return cidrList;
    }

    public void setCidrList(String cidrList) {
        this.cidrList = cidrList;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getDpd() {
        return dpd;
    }

    public void setDpd(String dpd) {
        this.dpd = dpd;
    }

    public String getEspLifeTime() {
        return espLifeTime;
    }

    public void setEspLifeTime(String espLifeTime) {
        this.espLifeTime = espLifeTime;
    }

    public String getEspPolicy() {
        return espPolicy;
    }

    public void setEspPolicy(String espPolicy) {
        this.espPolicy = espPolicy;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getIkeLifeTime() {
        return ikeLifeTime;
    }

    public void setIkeLifeTime(String ikeLifeTime) {
        this.ikeLifeTime = ikeLifeTime;
    }

    public String getIkePolicy() {
        return ikePolicy;
    }

    public void setIkePolicy(String ikePolicy) {
        this.ikePolicy = ikePolicy;
    }

    public String getIpsecPsk() {
        return ipsecPsk;
    }

    public void setIpsecPsk(String ipsecPsk) {
        this.ipsecPsk = ipsecPsk;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }

    public String getS2sCustomerGatewayId() {
        return s2sCustomerGatewayId;
    }

    public void setS2sCustomerGatewayId(String s2sCustomerGatewayId) {
        this.s2sCustomerGatewayId = s2sCustomerGatewayId;
    }

    public String getS2sVpnGatewayId() {
        return s2sVpnGatewayId;
    }

    public void setS2sVpnGatewayId(String s2sVpnGatewayId) {
        this.s2sVpnGatewayId = s2sVpnGatewayId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
