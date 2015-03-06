package com.assistanz.fogpanel

import org.apache.commons.logging.LogFactory;

class CardValidityJob {
    
    private static final log = LogFactory.getLog(this)
    PaymentService paymentService
    
    static triggers = {
      cron name: 'cardValidity', startDelay: 600000, cronExpression: "0 01 04 1,15 * ?"  // will fire every 15 days at 04:01 am 
    }

    def execute() {
        
        log.info("CardValidity Cron Started");
        paymentService.cardValidityCheck()
    }
}
