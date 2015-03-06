databaseChangeLog = {

	changeSet(author: "az (generated)", id: "1424875989457-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424875989457-2") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "virtual_machine_id", tableName: "volume")
	}
}
