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
class PullZoneJob {
	
    AsyncJobService asyncJobService
    ZoneService zoneService
     
    static triggers = {}

    def execute() {
        
        def jobid = asyncJobService.addJob("PULL_ZONE")
        zoneService.pullZoneFromOpenStack(jobid)
        
    }
}

