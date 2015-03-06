package com.assistanz.fogpanel

class PullImageJob {
    
    ImageService imageService
    AsyncJobService asyncJobService
    
    static triggers = {
      
    }

    def execute() {
        def jobid = asyncJobService.addJob("PULL_IMAGE")
        imageService.pullImage(jobid)
    }
}
