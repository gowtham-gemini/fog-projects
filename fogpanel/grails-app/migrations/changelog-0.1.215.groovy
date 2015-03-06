databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1416483760596-1") {
		dropColumn(columnName: "cloud_password", tableName: "account")
	}
}
