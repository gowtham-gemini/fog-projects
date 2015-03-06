package com.assistanz.fogpanel



class EnableDeviceJob {
    MonitoringService monitoringService
    
    static triggers = {      
    }

    def execute() {
        
        monitoringService.checkForDeviceJob()
    }
}
