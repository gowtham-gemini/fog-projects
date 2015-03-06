databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1374485555476-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "company_name", tableName: "account")
	}
        
        changeSet(author: "nandhini (generated)", id: "137448555547676-2") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "sub_type", tableName: "discount")
	}
}
