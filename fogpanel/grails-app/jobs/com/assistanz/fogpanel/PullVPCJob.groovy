package com.assistanz.fogpanel



class PullVPCJob {
    
    AsyncJobService asyncJobService
    VpcService  vpcService
    
    static triggers = {
    }

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_VPC")
        vpcService.pullVPCFromCloudStack(jobid)
        
    }
}
