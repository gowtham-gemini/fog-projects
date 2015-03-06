package com.assistanz.cloud.cloudstack.virtualmachine;

import java.util.List;

import com.assistanz.cloud.cloudstack.EgressRuleResponse;
import com.assistanz.cloud.cloudstack.IngressRuleResponse;
import com.assistanz.cloud.cloudstack.NetworkInterfaceCardResponse;
import com.assistanz.cloud.cloudstack.SecurityGroupResponse;

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
