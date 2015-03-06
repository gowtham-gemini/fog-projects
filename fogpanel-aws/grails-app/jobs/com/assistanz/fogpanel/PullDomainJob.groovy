package com.assistanz.fogpanel



class PullDomainJob {
    
    AsyncJobService asyncJobService
//    DomainService domainService
    
    static triggers = {
    }

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_DOMAIN")
//        domainService.pullFromOpenstack(jobid)
        
    }
}
