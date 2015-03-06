package com.assistanz.fogpanel

class VolumeJob {
    VolumeService volumeService
    static triggers = {      
      simple name: "UpdateStorageName", startDelay: 600000, repeatInterval: 600000 // execute job once in 10 min
    }

    def execute() {
        // execute job
        volumeService.updateStorageName();
    }
}
