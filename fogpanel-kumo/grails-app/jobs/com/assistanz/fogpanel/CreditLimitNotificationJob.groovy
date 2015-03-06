package com.assistanz.fogpanel

import org.apache.commons.logging.LogFactory;

class CreditLimitNotificationJob {
    
    InvoiceService invoiceService
    private static final log = LogFactory.getLog(this)
    static triggers = {
      simple name: "CreditLimitNotificationJob",  startDelay: 600000, repeatInterval: 10800000  // execute job once in a 3 hr
    }

    def execute() {
        
        log.info("CreditLimitNotification Cron Started");
        invoiceService.sendBillingMailForAllAccount()
    }
}