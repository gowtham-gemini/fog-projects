package com.assistanz.fogpanel

import org.apache.commons.logging.LogFactory;

/** 
 * 
 * 
 */
class AccountUsageSynchJob {

    private static final log = LogFactory.getLog(this)
    UsageService usageService
    VpnCustomerGatewayService vpnCustomerGatewayService
       
    static triggers = {
      simple name: "AccountUsageSynchJob",  startDelay: 60000, repeatInterval: 7200000  // execute job once in a 2 hr
    }

    def execute() {
        
//        volumeService.list(null, null, null)
//        snapShotService.list(null, null)
//        virtualMachineService.list(null, null)
        log.info("AccountUsageSynch Cron Started");
//        vpnCustomerGatewayService.VPCConnectionBillingCalc()
//        Console.print("Usage Job Start")
        usageService.populateUsageRecord();
//        Console.print("Usage Job Stop")
    }
}
