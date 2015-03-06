package com.assistanz.fogpanel



class PullAccountJob {
    
     AsyncJobService asyncJobService
     AccountService  accountService
    
    static triggers = {
    }

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_ACCOUNT")
        accountService.pullAccountFromCloudStack(jobid)
        
    }
}
