package com.assistanz.fogpanel

class PullFlavorJob {
    
    AsyncJobService asyncJobService
    FlavorService flavorService
        
    static triggers = {}

    def execute() {        
        
        def jobid = asyncJobService.addJob("PULL_FLAVOR")
        flavorService.pullFlavour(jobid)                
    }
}
