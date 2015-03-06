package com.assistanz.fogpanel
class CloudMaintainanceJob {
    CloudMaintenanceService cloudMaintenanceService
    static triggers = {
      simple name: "CloudMaintainanceJob", startDelay: 600000, repeatInterval: 600000 // execute job once in 10 min
    }

    def execute() {
        // execute job
        cloudMaintenanceService.sendCloudMaintainMail();
    }
}
