databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1416570543541-1") {
		dropColumn(columnName: "password", tableName: "account")
	}
}
