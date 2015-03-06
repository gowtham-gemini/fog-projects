databaseChangeLog = {

	changeSet(author: "deveops (generated)", id: "1425317724632-1") {
		modifyDataType(columnName: "contact_number", newDataType: "varchar(255)", tableName: "user")
	}

	changeSet(author: "deveops (generated)", id: "1425317724632-2") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "contact_number", tableName: "user")
	}

	changeSet(author: "deveops (generated)", id: "1425317724632-67") {
		dropTable(tableName: "group")
	}

}
