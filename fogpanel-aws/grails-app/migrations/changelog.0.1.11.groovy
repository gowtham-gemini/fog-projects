databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424343283341-1") {
		modifyDataType(columnName: "clock_speed", newDataType: "integer", tableName: "flavors")
	}

	changeSet(author: "santhosh (generated)", id: "1424343283341-2") {
		dropNotNullConstraint(columnDataType: "integer", columnName: "clock_speed", tableName: "flavors")
	}

	changeSet(author: "santhosh (generated)", id: "1424343283341-3") {
		modifyDataType(columnName: "memory", newDataType: "double precision", tableName: "flavors")
	}

	changeSet(author: "santhosh (generated)", id: "1424343283341-4") {
		addNotNullConstraint(columnDataType: "double precision", columnName: "memory", tableName: "flavors")
	}
}
