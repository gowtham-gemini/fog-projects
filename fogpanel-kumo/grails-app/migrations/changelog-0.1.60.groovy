databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "accountuuid-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "uuid", tableName: "account")
	}
        
}
