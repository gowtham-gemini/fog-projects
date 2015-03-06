package com.assistanz.cloud.cloudstack.baremetal;

/**
 *
 * @author Santhosh
 *
 */
public class AddBaremetalPxeKickStartServerResponse {

    /**
     * Tftp root directory of PXE server
     */
    private String tftpDir;

    public String getTftpDir() {
        return tftpDir;
    }

    public void setTftpDir(String tftpDir) {
        this.tftpDir = tftpDir;
    }

}
