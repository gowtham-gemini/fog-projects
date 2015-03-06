package com.assistanz.fogpanel



class PullUserJob {
    
     AsyncJobService asyncJobService
     UserService  userService
    
    static triggers = {
    }

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_USER")
        userService.pullUserFromOpenStack(jobid)
        
    }
}
