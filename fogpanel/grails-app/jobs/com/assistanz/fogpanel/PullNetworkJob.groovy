package com.assistanz.fogpanel



class PullNetworkJob {
    
     AsyncJobService asyncJobService
     NetworkService  networkService
    
    static triggers = {
    }

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_NETWORK")
        networkService.pullNetworkCloudStack(jobid)
        
    }
}
