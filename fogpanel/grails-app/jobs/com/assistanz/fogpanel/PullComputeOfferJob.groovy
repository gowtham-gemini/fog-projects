package com.assistanz.fogpanel



class PullComputeOfferJob {
    
    AsyncJobService asyncJobService
    ComputingOfferService computingOfferService
    
    static triggers = {
    }

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_COMPUTE_OFFER")
        computingOfferService.pullComputeOfferFromCloudStack(jobid)
        
    }
}
