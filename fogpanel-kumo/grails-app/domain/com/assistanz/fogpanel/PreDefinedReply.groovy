package com.assistanz.fogpanel

class PreDefinedReply {

    String content
    String subject 
    SupportDepartment department 
    Boolean deleted
    
    static constraints = {
        content(nullable: false, blank: false, size:0..5000)
        subject(nullable: true, blank: true, size:0..5000)
        deleted(nullable: false, blank: false)
    }
    
    static belongsTo = [department: SupportDepartment]  
}
