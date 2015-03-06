package com.assistanz.fogpanel

import grails.transaction.Transactional

@Transactional
class InternetGatewayService {

    def getList(id) {
        
        
        if(id == null) {
            getGatewayListBasedOnRegionId(1)
        } else {
            getGatewayListBasedOnRegionId(id.toInteger())
        }
        
    }
    
    def getGatewayListBasedOnRegionId(id){
        
        ArrayList internetGatewayList = new ArrayList()
        def internetGateways = InternetGateway.findAllByRegionId(id)
            for(def internetGateway : internetGateways) {
                HashMap internetGatewayMap = new HashMap()
                internetGatewayMap.put("id",internetGateway.id)
                internetGatewayMap.put("referenceId",internetGateway.referenceId)
                internetGatewayMap.put("vpcId",internetGateway.vpcId)
                internetGatewayMap.put("state",internetGateway.state)
                internetGatewayMap.put("name",internetGateway.name)
                internetGatewayList.add(internetGatewayMap)
            }
            return internetGatewayList
        
    }
    
    
}
