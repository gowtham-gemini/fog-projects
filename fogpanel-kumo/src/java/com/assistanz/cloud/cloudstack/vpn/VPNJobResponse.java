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
    private String asychronousAccountId;
    
    /**
     * the user that executed the async command
     */
    private String asychronousUserId;
    
    /**
     * 	the async command executed
     */
    private String asychronousCmd;
    
    /**
     * the current job status-should be 0 for PENDING
     */
    private String asychronousJobStatus;
    
    /**
     * the jobProcStatus
     */
    private String asychronousJobProcStatus;
    
    /**
     * the result code for the job
     */
    private String asychronousJobResultCode;
    
    /**
     * the result type
     */
    private String asychronousJobResultType;
    
    /**
     * the created date of the job
     */
    private String asychronousCreated;	        
    
    /**
     * the ID of the async job
     */
    private String asychronousJobId;                  
    
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

    /**
     * @return the asychronousAccountId
     */
    public String getAsychronousAccountId() {
        return asychronousAccountId;
    }

    /**
     * @param asychronousAccountId the asychronousAccountId to set
     */
    public void setAsychronousAccountId(String asychronousAccountId) {
        this.asychronousAccountId = asychronousAccountId;
    }

    /**
     * @return the asychronousUserId
     */
    public String getAsychronousUserId() {
        return asychronousUserId;
    }

    /**
     * @param asychronousUserId the asychronousUserId to set
     */
    public void setAsychronousUserId(String asychronousUserId) {
        this.asychronousUserId = asychronousUserId;
    }

    /**
     * @return the asychronousCmd
     */
    public String getAsychronousCmd() {
        return asychronousCmd;
    }

    /**
     * @param asychronousCmd the asychronousCmd to set
     */
    public void setAsychronousCmd(String asychronousCmd) {
        this.asychronousCmd = asychronousCmd;
    }

    /**
     * @return the asychronousJobStatus
     */
    public String getAsychronousJobStatus() {
        return asychronousJobStatus;
    }

    /**
     * @param asychronousJobStatus the asychronousJobStatus to set
     */
    public void setAsychronousJobStatus(String asychronousJobStatus) {
        this.asychronousJobStatus = asychronousJobStatus;
    }

    /**
     * @return the asychronousJobProcStatus
     */
    public String getAsychronousJobProcStatus() {
        return asychronousJobProcStatus;
    }

    /**
     * @param asychronousJobProcStatus the asychronousJobProcStatus to set
     */
    public void setAsychronousJobProcStatus(String asychronousJobProcStatus) {
        this.asychronousJobProcStatus = asychronousJobProcStatus;
    }

    /**
     * @return the asychronousJobResultCode
     */
    public String getAsychronousJobResultCode() {
        return asychronousJobResultCode;
    }

    /**
     * @param asychronousJobResultCode the asychronousJobResultCode to set
     */
    public void setAsychronousJobResultCode(String asychronousJobResultCode) {
        this.asychronousJobResultCode = asychronousJobResultCode;
    }

    /**
     * @return the asychronousJobResultType
     */
    public String getAsychronousJobResultType() {
        return asychronousJobResultType;
    }

    /**
     * @param asychronousJobResultType the asychronousJobResultType to set
     */
    public void setAsychronousJobResultType(String asychronousJobResultType) {
        this.asychronousJobResultType = asychronousJobResultType;
    }

    /**
     * @return the asychronousCreated
     */
    public String getAsychronousCreated() {
        return asychronousCreated;
    }

    /**
     * @param asychronousCreated the asychronousCreated to set
     */
    public void setAsychronousCreated(String asychronousCreated) {
        this.asychronousCreated = asychronousCreated;
    }

    /**
     * @return the asychronousJobId
     */
    public String getAsychronousJobId() {
        return asychronousJobId;
    }

    /**
     * @param asychronousJobId the asychronousJobId to set
     */
    public void setAsychronousJobId(String asychronousJobId) {
        this.asychronousJobId = asychronousJobId;
    }

    /**
     * @return the publicIpId
     */
    public String getPublicIpId() {
        return publicIpId;
    }

    /**
     * @param publicIpId the publicIpId to set
     */
    public void setPublicIpId(String publicIpId) {
        this.publicIpId = publicIpId;
    }

    
    public String getPublicIp() {
        return publicIp;
    }

    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }
    /**
     * @return the ipRange
     */
    public String getIpRange() {
        return ipRange;
    }

    /**
     * @param ipRange the ipRange to set
     */
    public void setIpRange(String ipRange) {
        this.ipRange = ipRange;
    }

    /**
     * @return the preSharedKey
     */
    public String getPreSharedKey() {
        return preSharedKey;
    }

    /**
     * @param preSharedKey the preSharedKey to set
     */
    public void setPreSharedKey(String preSharedKey) {
        this.preSharedKey = preSharedKey;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the domainId
     */
    public String getDomainId() {
        return domainId;
    }

    /**
     * @param domainId the domainId to set
     */
    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    /**
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * @param domain the domain to set
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the vpnId
     */
    public String getVpnId() {
        return vpnId;
    }

    /**
     * @param vpnId the vpnId to set
     */
    public void setVpnId(String vpnId) {
        this.vpnId = vpnId;
    }
    
}
