package com.assistanz.fogpanel

//@gorm.AuditStamp
class Role {

	String authority
        
//        Integer createdBy
//        Integer editedBy
//        Date editedDate
//        Date createdDate

	static mapping = {
		cache true
	}
        
	static constraints = {
		authority blank: false, unique: true
                
//                createdBy   nullable:true,display:false,editable:false
//                editedBy    nullable:true,display:false,editable:false
//                editedDate  nullable:true,display:false,editable:false
//                createdDate nullable:true,display:false,editable:false
	}
}
