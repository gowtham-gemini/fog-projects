databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1416292349589-1") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "cluster_id", tableName: "virtual_machine")
	}
}
