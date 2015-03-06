package com.assistanz.fogpanel



class PullVPCOfferingJob {
    
     AsyncJobService asyncJobService
     VpcService  vpcService
    
    static triggers = {
    }

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_VPC_OFFERING")
        vpcService.pullVPCOfferingFromCloudStack(jobid)
        
    }
}
