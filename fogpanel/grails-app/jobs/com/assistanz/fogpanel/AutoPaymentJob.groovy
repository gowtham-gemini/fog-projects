package com.assistanz.fogpanel

import org.apache.commons.logging.LogFactory;

class AutoPaymentJob {
    
    PaymentService paymentService
    private static final log = LogFactory.getLog(this)
    
    static triggers = {
      cron name: 'autoPaymentJob', startDelay: 600000, cronExpression: "0 01 06 * * ?"  // will fire daily at 06:01 am
    }

    def execute() {
        
        log.info("AutoPayment Cron Started");
        paymentService.makeAutomatedPaymentAttempt3();
    }
}
