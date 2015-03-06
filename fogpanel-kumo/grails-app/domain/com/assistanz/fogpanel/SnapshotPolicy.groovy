package com.assistanz.fogpanel

//@gorm.AuditStamp
class SnapshotPolicy {

    String snapshotPolicyReferenceId
    Boolean deleted
    User user
    Volume volume
    String snapshotName
    String intervalType
    String maxSnaps
    String scheduleTime
    String timeZone
    String dayValue
    String monthValue
    String dayName
    String timeZoneValue
    
//    Integer createdBy
//    Integer editedBy
//    Date editedDate
//    Date createdDate
    
    static constraints = {
        deleted(nullable: false, blank: false)
        dayName(nullable: true, blank: true)
        timeZoneValue(nullable: true, blank: true)
        dayValue(nullable: true, blank: true)
        monthValue(nullable: true, blank: true)
        user(nullable: false, blank: false)
        volume(nullable: false, blank: false)
        snapshotPolicyReferenceId(nullable: false, unique: true, blank: false)
        snapshotName(nullable: true, blank: true)
        intervalType(nullable: false, blank: false)
        maxSnaps(nullable: false, blank: false)
        scheduleTime(nullable: false, blank: false)
        timeZone(nullable: false, blank: false)
        
//        createdBy   nullable:true,display:false,editable:false
//        editedBy    nullable:true,display:false,editable:false
//        editedDate  nullable:true,display:false,editable:false
//        createdDate nullable:true,display:false,editable:false
    }
}
