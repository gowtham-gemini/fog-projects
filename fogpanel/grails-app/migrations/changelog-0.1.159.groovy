databaseChangeLog = {

        changeSet(author: "gowtham (generated)", id: "1405746055153-3") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "account_id", tableName: "useripaddress")
	}
    
	changeSet(author: "gowtham (generated)", id: "1405746055153-5") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "user_id", tableName: "useripaddress")
	}
}
