package com.assistanz.fogpanel



class PullNetworkOfferingJob {
    
     AsyncJobService asyncJobService
     NetworkOfferService  networkOfferService
    
    static triggers = {
    }

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_NETWORK_OFFERING")
        networkOfferService.pullNetworkOfferCloudStack(jobid)
        
    }
}
