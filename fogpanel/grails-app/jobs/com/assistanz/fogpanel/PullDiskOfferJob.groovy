package com.assistanz.fogpanel



class PullDiskOfferJob {
    
    AsyncJobService asyncJobService
    DiskOfferService diskOfferService
    
    static triggers = {      
    }

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_DISK_OFFER")
        diskOfferService.pullDiskOfferFromCloudStack(jobid)
        
    }
}
