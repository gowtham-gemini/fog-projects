package com.assistanz.fogpanel

import org.apache.commons.logging.LogFactory;

class BillingJob {
    
    InvoiceService invoiceService
    private static final log = LogFactory.getLog(this)
    
    static triggers = {
      cron name: 'InvoiceGenerate', startDelay: 600000, cronExpression: "0 01 04 * * ?"  // will fire daily at 04:01 am
    }

    def execute() {
        
        log.info("InvoiceGenerate Cron Started");
        invoiceService.generateInvoice()
    }
}
