/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assistanz.fogpanel;

/**
 *
 * @author Gowtham
 */
class UsageResponse {
    
    String diskRead;
    String diskWrite;
    String cpuUtil;
    String memeoryUtil;
    String networkRead;
    String networkWrite;

    public String getDiskRead() {
        return diskRead;
    }

    public void setDiskRead(String diskRead) {
        this.diskRead = diskRead;
    }

    public String getDiskWrite() {
        return diskWrite;
    }

    public void setDiskWrite(String diskWrite) {
        this.diskWrite = diskWrite;
    }

    public String getCpuUtil() {
        return cpuUtil;
    }

    public void setCpuUtil(String cpuUtil) {
        this.cpuUtil = cpuUtil;
    }

    public String getMemeoryUtil() {
        return memeoryUtil;
    }

    public void setMemeoryUtil(String memeoryUtil) {
        this.memeoryUtil = memeoryUtil;
    }

    public String getNetworkRead() {
        return networkRead;
    }

    public void setNetworkRead(String networkRead) {
        this.networkRead = networkRead;
    }

    public String getNetworkWrite() {
        return networkWrite;
    }

    public void setNetworkWrite(String networkWrite) {
        this.networkWrite = networkWrite;
    }
    
}
