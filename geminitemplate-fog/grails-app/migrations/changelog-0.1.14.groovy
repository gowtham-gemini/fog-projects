databaseChangeLog = {

	changeSet(author: "lakshmi (generated)", id: "1414048978609-1") {
		modifyDataType(columnName: "deleted_at", newDataType: "datetime", tableName: "security_group")
	}

	changeSet(author: "lakshmi (generated)", id: "1414048978609-2") {
		dropNotNullConstraint(columnDataType: "datetime", columnName: "deleted_at", tableName: "security_group")
	}

	changeSet(author: "lakshmi (generated)", id: "1414048978609-3") {
		modifyDataType(columnName: "deleted_at", newDataType: "datetime", tableName: "security_group_rules")
	}

	changeSet(author: "lakshmi (generated)", id: "1414048978609-4") {
		dropNotNullConstraint(columnDataType: "datetime", columnName: "deleted_at", tableName: "security_group_rules")
	}

	
}
