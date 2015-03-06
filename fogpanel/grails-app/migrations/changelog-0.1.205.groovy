databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1415697019805-1") {
		modifyDataType(columnName: "old_minimum_cost", newDataType: "double precision", tableName: "service_cost_change_log")
	}

	changeSet(author: "gowtham (generated)", id: "1415697019805-2") {
		dropNotNullConstraint(columnDataType: "double precision", columnName: "old_minimum_cost", tableName: "service_cost_change_log")
	}
}
