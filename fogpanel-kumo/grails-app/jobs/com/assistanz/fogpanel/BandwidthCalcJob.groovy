package com.assistanz.fogpanel

import org.apache.commons.logging.LogFactory;

class BandwidthCalcJob {
    
    BandwidthService bandwidthService
    private static final log = LogFactory.getLog(this)
    
    static triggers = {
        simple name: "BandwidthCalcJob", startDelay: 600000, repeatInterval: 600000 // execute job once in 10 min
    }

    def execute() {
        
        log.info("BandwidthCalc Cron Started");
        bandwidthService.calc()
    }
}
