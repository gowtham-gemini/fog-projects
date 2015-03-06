package com.assistanz.fogpanel

import org.apache.commons.logging.LogFactory;

class DailyUsageCalcJob {
    
    UsageService usageService
    private static final log = LogFactory.getLog(this)
    
    static triggers = {
     simple name: "DailyUsageCalcJob",  startDelay: 600000, repeatInterval: 10800000  // execute job once in a 3 hr
    }

    def execute() {
        // execute job
        log.info("DailyUsageCalc Cron Started");
        usageService.dailyUsageCostUpdate();
        
    }
}
