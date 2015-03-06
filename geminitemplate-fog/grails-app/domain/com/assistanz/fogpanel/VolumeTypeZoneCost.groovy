package com.assistanz.fogpanel

class VolumeTypeZoneCost {
    VolumeType volumeType    
    Zone zone
    Double cost
    static constraints = {
        volumeType(nullable: false, blank: false)
        zone(nullable: false, blank: false)
        cost(nullable: false, blank: false)
    }
    
    static belongsTo = [volumeType: VolumeType]
}
