package com.assistanz.cloud.cloudstack.ssh;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListSSHKeyPairsResponse {

    /**
     * List registered keypairs
     */
    private List<SSHKeyPairResponse> sshKeyPairs;

    public List<SSHKeyPairResponse> getSshKeyPairs() {
        return sshKeyPairs;
    }

    public void setSshKeyPairs(List<SSHKeyPairResponse> sshKeyPairs) {
        this.sshKeyPairs = sshKeyPairs;
    }

}
