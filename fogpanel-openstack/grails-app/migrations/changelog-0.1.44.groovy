databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1419231237550-1") {
		dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "email", tableName: "user")
	}
}
