package com.assistanz.fogpanel

import org.apache.commons.logging.LogFactory;

class DailyUsageCheckJob {
    
    UsageService usageService
    private static final log = LogFactory.getLog(this)
    
    static triggers = {
      cron name: 'DailyUsageCheckJob', cronExpression: "0 01 06 * * ?"  // will fire daily at 06:01 am
    }

    def execute() {
        
        log.info("DailyUsageCheck Cron Started");
        usageService.dailyUsageCheck();
    }
}
