/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.cloud.cloudstack.snapshot;

/**
 *
 * @author gowtham
 */
class DeleteVMSnapshotResponse {
    
    /**
     * any text associated with the success or failure on deleting snapshot
     */
    String displaytext;	

    /**
     * true if operation is executed successfully on deleting snapshot
     */
    String success;

    String jobid;

    public String getDisplaytext() {
        return displaytext;
    }

    public void setDisplaytext(String displaytext) {
        this.displaytext = displaytext;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getJobid() {
        return jobid;
    }

    public void setJobid(String jobid) {
        this.jobid = jobid;
    }
        
        
}
