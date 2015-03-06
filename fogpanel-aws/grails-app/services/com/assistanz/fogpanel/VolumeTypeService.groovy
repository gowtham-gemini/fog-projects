package com.assistanz.fogpanel

import grails.transaction.Transactional

@Transactional
class VolumeTypeService {

    def getList() {
        try {
            
            def volumeTypeList = VolumeType.withCriteria{
                eq('deleted',false)
            }.asList()
            
            return volumeTypeList
        } catch(Exception ex){
            throw ex
        }
        
    }
}
