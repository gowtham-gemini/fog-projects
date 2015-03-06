package com.assistanz.fogpanel

import grails.transaction.Transactional

@Transactional
class RouteTableService {

    def getRouteTableList(currentRegionId, vpcId) {
        try {
            def vpc = Vpc.findById(vpcId);
            def region = Region.findById(currentRegionId);
            ArrayList routeTableList = new ArrayList()
            if(vpc != null && region != null) {
                def routeTables = RouteTable.findAllByVpcIdAndRegionId(vpc.id,region.id)
                for(def routeTable : routeTables) {
                    HashMap routeTableMap = new HashMap()
                    routeTableMap.put("id",routeTable.id)        
                    routeTableMap.put("vpcId",routeTable.vpcId)
                    routeTableMap.put("referenceId",routeTable.referenceId)
                    routeTableList.add(routeTableMap)
                }
            } else if(vpc != null) {
                def routeTables = RouteTable.findAllByVpcId(vpcId)
                for(def routeTable : routeTables) {
                    HashMap routeTableMap = new HashMap()
                    routeTableMap.put("id",routeTable.id)        
                    routeTableMap.put("vpcId",routeTable.vpcId)
                    routeTableMap.put("referenceId",routeTable.referenceId)
                    routeTableList.add(routeTableMap)
                }
            } else {
                def routeTables = RouteTable.findAllByRegionId(currentRegionId)
                for(def routeTable : routeTables) {
                    HashMap routeTableMap = new HashMap()
                    routeTableMap.put("id",routeTable.id)        
                    routeTableMap.put("vpcId",routeTable.vpcId)
                    routeTableMap.put("referenceId",routeTable.referenceId)
                    routeTableList.add(routeTableMap)
                }
            }
            return routeTableList
        
        } catch (Exception ex) {
            throw ex
        }
    }
    
    def getRouteTableView(id) {
        
        try {
            def routeTable = RouteTable.findByReferenceId(id)
            HashMap map = new HashMap()
            map.put("id",routeTable.id)
            map.put("referenceId",routeTable.referenceId)
            map.put("vpcId",routeTable.vpcId)
            return map
            
        } catch(Exception ex) {
            throw ex
        }
        
        
        
    }
}
