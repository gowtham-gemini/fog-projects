package com.assistanz.fogpanel

import com.assistanz.fogpanel.AccountService;
import org.apache.commons.logging.LogFactory;

class CancelAccountCleanJob {
   
    AccountService accountService
    private static final log = LogFactory.getLog(this)
        
    static triggers = {
      cron name: 'CancelAccountCleanTrigger', startDelay: 600000, cronExpression: "0 01 12 * * ?"  // will fire daily at 12:01 pm
    }

    def execute() {
        
        log.info("CancelAccountClean Cron Started");
        accountService.cleanCancelAccount();
    }
}
