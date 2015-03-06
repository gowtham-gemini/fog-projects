package com.assistanz.fogpanel

import org.apache.commons.logging.LogFactory;

class LateFeeJob {
    InvoiceService invoiceService
    private static final log = LogFactory.getLog(this)
    static triggers = {
      cron name: 'lateFeeTrigger', startDelay: 7200000, cronExpression: "0 01 05 * * ?"  // will fire daily at 05:01 am
    }

    def execute() {
        
        log.info("Late Fee Cron Started");
        invoiceService.addLateFee()
    }
}
