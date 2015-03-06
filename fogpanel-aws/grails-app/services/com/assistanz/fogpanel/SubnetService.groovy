package com.assistanz.fogpanel

import grails.transaction.Transactional

@Transactional
class SubnetService {

    def getSubnetListByRegionAndVPC(currentRegionId, vpcId) {
        try {
            
            def vpc = Vpc.findByReferenceId(vpcId);
            def region = Region.findById(currentRegionId);
            ArrayList subnetList = new ArrayList()
            if(vpc != null &&  region != null) {
                
                def subnets = Subnet.findAllByRegionIdAndVpc(currentRegionId, vpc)
                for(def subnet : subnets) {
                    HashMap map = new HashMap()
                    map.put("id",subnet.id)
                    map.put("referenceId",subnet.referenceId)                    
                    map.put("cidr",subnet.cidr)                    
                    map.put("state",subnet.state)                    
                    map.put("subnetId",subnet.referenceId)                      
                    map.put("availabilityZone",subnet.availabilityZone)                    
                    map.put("vpcId",Vpc.findById(subnet.vpcId)?.referenceId)                    
                    subnetList.add(map)
                }
            } else if(region == null) {
               
                def subnets = Subnet.findAllByVpc(vpc)
                for(def subnet : subnets) {
                    HashMap subnetMap = new HashMap()
                    subnetMap.put("id",subnet.id)
                    subnetMap.put("referenceId",subnet.referenceId)
                    subnetMap.put("cidr",subnet.cidr)
                    subnetMap.put("state",subnet.state)
                    subnetMap.put("subnetId",subnet.referenceId) 
                    subnetMap.put("availabilityZone",subnet.availabilityZone)     
                    subnetMap.put("vpcId",Vpc.findById(subnet.vpcId)?.referenceId)
                    subnetList.add(subnetMap)
                }
                
            } else {
                
                def subnets = Subnet.findAllByRegionId(region.id)
                for(def subnet : subnets) {
                    HashMap subnetMap = new HashMap()
                    subnetMap.put("id",subnet.id)
                    subnetMap.put("referenceId",subnet.referenceId)
                    subnetMap.put("cidr",subnet.cidr)
                    subnetMap.put("state",subnet.state)
                    subnetMap.put("subnetId",subnet.referenceId) 
                    subnetMap.put("availabilityZone",subnet.availabilityZone)    
                    subnetMap.put("vpcId",Vpc.findById(subnet.vpcId)?.referenceId)
                    subnetList.add(subnetMap)
                }
                
            }
            return subnetList
            
            
        } catch(Exception ex){
            throw ex
        }
    }
}
