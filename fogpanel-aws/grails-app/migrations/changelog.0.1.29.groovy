databaseChangeLog = {

	changeSet(author: "az (generated)", id: "1424959062143-1") {
		addColumn(tableName: "volume") {
			column(name: "is_delete_on_termination", type: "bit")
		}
	}

	changeSet(author: "az (generated)", id: "1424959062143-2") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "volume_type_id", tableName: "volume")
	}
}
