package com.assistanz.fogpanel

class SupportDepartment {
 
    String name
    Boolean deleted

    static constraints = {
        name (nullable: false, blank: false)
        deleted(nullable: false, blank: false)
    }
     
     static hasMany = [preDefinedReply: PreDefinedReply]
}
