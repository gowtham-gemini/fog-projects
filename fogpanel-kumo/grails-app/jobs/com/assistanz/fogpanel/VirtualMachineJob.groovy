package com.assistanz.fogpanel

class VirtualMachineJob {
    

    VirtualMachineService virtualMachineService
    static triggers = {
        simple name: "UpdateHostName", startDelay: 3000000, repeatInterval: 3000000 // execute job once in 10 min 
    }
    def execute() {
        // execute job
         virtualMachineService.UpdateHostName();
    }
}
