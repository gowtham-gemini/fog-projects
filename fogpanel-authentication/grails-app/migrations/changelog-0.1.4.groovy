databaseChangeLog = {

	changeSet(author: "az (generated)", id: "1424073438220-1") {
		addColumn(tableName: "zone") {
			column(name: "region_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
