databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424446680371-1") {
		modifyDataType(columnName: "deleted", newDataType: "bit", tableName: "subnet")
	}

	changeSet(author: "santhosh (generated)", id: "1424446680371-2") {
		dropNotNullConstraint(columnDataType: "bit", columnName: "deleted", tableName: "subnet")
	}
}
