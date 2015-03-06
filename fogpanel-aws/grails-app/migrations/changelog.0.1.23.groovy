databaseChangeLog = {

	changeSet(author: "az (generated)", id: "1424863351250-1") {
		addColumn(tableName: "subnet") {
			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1424863351250-2") {
		dropColumn(columnName: "referenceid", tableName: "subnet")
	}
}
