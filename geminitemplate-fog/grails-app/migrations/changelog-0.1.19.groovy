databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1414750068646-1") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "account_id", tableName: "asynchronous_jobs")
	}

	changeSet(author: "gowtham (generated)", id: "1414750068646-2") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "user_id", tableName: "asynchronous_jobs")
	}	
}
