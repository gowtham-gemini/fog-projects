/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.assistanz.fogpanel

import com.assistanz.fogpanel.MonitoringService;

/**
 *
 * @author Abdul
 */
class MonitoringDeviceJob {
    
    MonitoringService monitoringService;
    
    static triggers = {}
    
    def execute(context) {
        
        def collector = context.mergedJobDataMap.get('collector');
        def snmpPort = context.mergedJobDataMap.get('snmpPort');
        def snmpCommunity = context.mergedJobDataMap.get('snmpCommunity');
        def monitorOsType = context.mergedJobDataMap.get('monitorOsType');
        
        monitoringService.enableMonitor(collector, snmpPort, snmpCommunity, monitorOsType)
        
    }
	
}

