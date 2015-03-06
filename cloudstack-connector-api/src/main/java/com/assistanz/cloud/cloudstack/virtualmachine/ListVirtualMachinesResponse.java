package com.assistanz.cloud.cloudstack.virtualmachine;

import java.util.List;

/**
 *
 * @author Gowtham
 *
 */
public class ListVirtualMachinesResponse {

    /**
     * List of Virtual Machines
     */
    private List<VirtualMachineResponse> virtualMachines;

    public List<VirtualMachineResponse> getVirtualMachines() {
        return virtualMachines;
    }

    public void setVirtualMachines(List<VirtualMachineResponse> virtualMachines) {
        this.virtualMachines = virtualMachines;
    }

}
