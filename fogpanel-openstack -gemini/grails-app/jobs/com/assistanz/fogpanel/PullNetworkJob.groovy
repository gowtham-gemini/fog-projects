package com.assistanz.fogpanel



class PullNetworkJob {
          
    NetworkService networkService
    AsyncJobService asyncJobService
    
    static triggers = {}

    def execute() {
        def jobid = asyncJobService.addJob("PULL_NETWORK")
        println("Network jobId " + jobid)
        networkService.pullNetworkFromOpenstack(jobid);
    }
}
