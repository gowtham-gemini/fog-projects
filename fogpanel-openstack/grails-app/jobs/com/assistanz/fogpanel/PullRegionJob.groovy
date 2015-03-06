/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.fogpanel

/**
 *
 * @author Abdul
 */
class PullRegionJob {

    AsyncJobService asyncJobService
    RegionService regionService
     
    static triggers = {}

    def execute() {
        
        def jobId = asyncJobService.addJob("PULL_REGION")
        regionService.pullRegionFromOpenStack(jobId)
        
    }
}

