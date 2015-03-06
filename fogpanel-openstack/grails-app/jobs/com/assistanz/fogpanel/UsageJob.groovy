package com.assistanz.fogpanel

class UsageJob {
    
    UsageService usageService
        
    static triggers = {
        
      cron name: 'DailyUsage', startDelay: 600000, cronExpression: "0 01 02 * * ?"  // will fire daily at 02:01 am
    
    }

    def execute() {
        
        Console.println("DailyUsage Cron Started");
        usageService.populateUsageRecords()
    }
}
