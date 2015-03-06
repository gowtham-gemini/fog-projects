
package org.zenoss.client.modal.entities.device;

/**
 * This returns the device response once device gets added
 * 
 * @author developer
 */
public class DeviceResponse {
    
    private String jobId;
    private String jobStatus;
    private String deviceId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
    
    
}
