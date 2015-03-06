/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.nic;

/**
 *
 * @author Nandhini
 */
public class AddIpJobResultResponse {
    /**
     * the account that executed the async command
     */
    String accountId;

    /**
     * 	the async command executed
     */
    String command;

    /**
     * the created date of the job
     */
    String created;	

    /**
     * the unique ID of the instance/entity object related to the job
     */
    String jobInstanceId;

    /**
     * 	the instance/entity object related to the job
     */
    String jobInstanceType;

    /**
     * the progress information of the PENDING job
     */
    String jobProgressStatus;

    /**
     * the result reason
     */
    String jobResult;

    /**
     * the result code for the job
     */
    String jobResultCode;

    /**
     * the result type
     */
    String jobResultType;

    /**
     * the current job status-should be 0 for PENDING
     */
    String jobStatus;

    /**
     * the user that executed the async command
     */
    String userId;

    /**
     * the ID of the async job
     */
    String jobId;        
    
    /**
     * the secondaryIpId
     */
    String secondaryIpId;

    /**
     * 	the secondaryIp
     */
    String secondaryIp;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getJobInstanceId() {
        return jobInstanceId;
    }

    public void setJobInstanceId(String jobInstanceId) {
        this.jobInstanceId = jobInstanceId;
    }

    public String getJobInstanceType() {
        return jobInstanceType;
    }

    public void setJobInstanceType(String jobInstanceType) {
        this.jobInstanceType = jobInstanceType;
    }

    public String getJobProgressStatus() {
        return jobProgressStatus;
    }

    public void setJobProgressStatus(String jobProgressStatus) {
        this.jobProgressStatus = jobProgressStatus;
    }

    public String getJobResult() {
        return jobResult;
    }

    public void setJobResult(String jobResult) {
        this.jobResult = jobResult;
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

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getSecondaryIpId() {
        return secondaryIpId;
    }

    public void setSecondaryIpId(String secondaryIpId) {
        this.secondaryIpId = secondaryIpId;
    }

    public String getSecondaryIp() {
        return secondaryIp;
    }

    public void setSecondaryIp(String secondaryIp) {
        this.secondaryIp = secondaryIp;
    }

    
}
