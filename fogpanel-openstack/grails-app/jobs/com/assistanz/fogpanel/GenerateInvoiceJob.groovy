package com.assistanz.fogpanel

class GenerateInvoiceJob {
    
    InvoiceService invoiceService
    
    static triggers = {
       
        cron name: 'GenerateInvoice', startDelay: 600000, cronExpression: "0 01 04 * * ?"  // will fire daily at 04:01 am
    
    }

    def execute() {
       
        invoiceService.generateInvoice()
        
    }
}
