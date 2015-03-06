databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1418642274433-1") {
		modifyDataType(columnName: "created_at", newDataType: "datetime", tableName: "network_subnet")
	}

	changeSet(author: "Abdul (generated)", id: "1418642274433-2") {
		dropNotNullConstraint(columnDataType: "datetime", columnName: "created_at", tableName: "network_subnet")
	}

	changeSet(author: "Abdul (generated)", id: "1418642274433-3") {
		modifyDataType(columnName: "deleted_at", newDataType: "datetime", tableName: "network_subnet")
	}

	changeSet(author: "Abdul (generated)", id: "1418642274433-4") {
		dropNotNullConstraint(columnDataType: "datetime", columnName: "deleted_at", tableName: "network_subnet")
	}

}
