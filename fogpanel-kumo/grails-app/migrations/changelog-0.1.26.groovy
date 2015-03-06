databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1376736187397-1") {
		dropNotNullConstraint(columnDataType: "datetime", columnName: "due_date", tableName: "invoice")
	}
}
