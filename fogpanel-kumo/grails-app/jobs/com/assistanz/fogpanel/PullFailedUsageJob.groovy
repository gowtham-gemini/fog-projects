package com.assistanz.fogpanel

class PullFailedUsageJob {
    
    AsyncJobService asyncJobService
    FailedUsageService failedUsageService
    
    static triggers = {}

    def execute() {
        
//        def id = context.mergedJobDataMap.get('id')
        
        
        def jobid = asyncJobService.addJob("PULL_FAILED_USAGE")
        failedUsageService.usageUpdateFailed(jobid)
    }
}
