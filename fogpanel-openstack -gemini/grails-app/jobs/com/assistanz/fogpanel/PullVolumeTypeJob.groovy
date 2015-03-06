package com.assistanz.fogpanel



class PullVolumeTypeJob {
    
    AsyncJobService asyncJobService
    VolumeTypeService volumeTypeService
     
    static triggers = {}

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_VOLUME_TYPE")
        volumeTypeService.pullVolumeTypeFromOpenstack(jobid)
        
    }
}
