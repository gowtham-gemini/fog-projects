package com.assistanz.fogpanel



class PullVpcJob {
          
    VpcService vpcService
    AsyncJobService asyncJobService
    
    static triggers = {}

    def execute() {
         Console.print("Pull job ")
        def jobid = asyncJobService.addJob("PULL_VPC")
        vpcService.pullVpcAndSubnetFromAws(jobid);
    }
}
