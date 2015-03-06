package com.assistanz.cloud.cloudstack.systemvm;

import java.util.List;

/**
 *
 * @author Santhosh
 *
 */
public class ListSystemVmsResponse {

    /**
     * List system virtual machines
     */
    private List<SystemVmResponse> systemVms;

    public List<SystemVmResponse> getSystemVms() {
        return systemVms;
    }

    public void setSystemVms(List<SystemVmResponse> systemVms) {
        this.systemVms = systemVms;
    }

}
