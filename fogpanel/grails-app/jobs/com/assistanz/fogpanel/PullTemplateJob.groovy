package com.assistanz.fogpanel



class PullTemplateJob {
    
     AsyncJobService asyncJobService
     TemplateService templateService
    
    static triggers = {
    }

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_TEMPLATE")
        templateService.pullDiskOfferFromCloudStack(jobid)
        
    }
}
