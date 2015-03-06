/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.vpn;

/**
 *
 * @author Nandhini
 */
public class VPNJobResponse {
    /**
     * the account that executed the async command
     */
    private String accountId;
    
    /**
     * the user that executed the async command
     */
    private String userId;
    
    /**
     * 	the async command executed
     */
    private String command;
    
    /**
     * the current job status-should be 0 for PENDING
     */
    private String jobStatus;
    
    /**
     * the jobProcStatus
     */
    private String jobProcStatus;
    
    /**
     * the result code for the job
     */
    private String jobResultCode;
    
    /**
     * the result type
     */
    private String jobResultType;
    
    /**
     * the created date of the job
     */
    private String created;	        
    
    /**
     * the ID of the async job
     */
    private String jobId;                  
    
    // VPN Results
    /**
     * the current Enabled ip id
     */
    private String publicIpId;
    /**
     * the current enabled ip
     */
    private String publicIp;    
    
    /**     
     * the limitation of an ip
     */
    private String ipRange;
    /**     
    * the shared key of current ip
    */
    private String preSharedKey;
    /**     
    * the enabled ip account
    */
    private String account;
    
    /**     
    * the enabled ip domainid
    */
    private String domainId;
    /**     
    * the enabled ip domain
    */
    private String domain;
    /**     
    * the enabled ip status
    */
    private String state;
    
    /**     
    * the enabled ip id
    */
    private String vpnId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobProcStatus() {
        return jobProcStatus;
    }

    public void setJobProcStatus(String jobProcStatus) {
        this.jobProcStatus = jobProcStatus;
    }

    public String getJobResultCode() {
        return jobResultCode;
    }

    public void setJobResultCode(String jobResultCode) {
        this.jobResultCode = jobResultCode;
    }

    public String getJobResultType() {
        return jobResultType;
    }

    public void setJobResultType(String jobResultType) {
        this.jobResultType = jobResultType;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getPublicIpId() {
        return publicIpId;
    }

    public void setPublicIpId(String publicIpId) {
        this.publicIpId = publicIpId;
    }

    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }

    public String getIpRange() {
        return ipRange;
    }

    public void setIpRange(String ipRange) {
        this.ipRange = ipRange;
    }

    public String getPreSharedKey() {
        return preSharedKey;
    }

    public void setPreSharedKey(String preSharedKey) {
        this.preSharedKey = preSharedKey;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getVpnId() {
        return vpnId;
    }

    public void setVpnId(String vpnId) {
        this.vpnId = vpnId;
    }

    
}
