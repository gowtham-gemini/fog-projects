package com.assistanz.cloud.cloudstack.baremetal;

/**
 *
 * @author Santhosh
 *
 */
public class AddBaremetalPxePingServerResponse {

    /**
     * Root directory on PING storage server
     */
    private String pingDir;

    /**
     * PING storage server ip
     */
    private String pingStorageServerIp;

    /**
     * Tftp root directory of PXE server
     */
    private String tftpDir;

    public String getPingDir() {
        return pingDir;
    }

    public void setPingDir(String pingDir) {
        this.pingDir = pingDir;
    }

    public String getPingStorageServerIp() {
        return pingStorageServerIp;
    }

    public void setPingStorageServerIp(String pingStorageServerIp) {
        this.pingStorageServerIp = pingStorageServerIp;
    }

    public String getTftpDir() {
        return tftpDir;
    }

    public void setTftpDir(String tftpDir) {
        this.tftpDir = tftpDir;
    }

}
