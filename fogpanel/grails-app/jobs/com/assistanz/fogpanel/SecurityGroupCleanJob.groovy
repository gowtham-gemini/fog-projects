package com.assistanz.fogpanel


import org.apache.commons.logging.LogFactory;

class SecurityGroupCleanJob {
    
    SecurityGroupService securityGroupService
    private static final log = LogFactory.getLog(this)
    
    static triggers = {
      simple name: "SecurityGroupCleanJob", startDelay: 600000, repeatInterval: 600000 // execute job once in 10 min
    }

    def execute() {
        log.info("SecurityGroupClean Cron Started");
        securityGroupService.clean()
    }
}
