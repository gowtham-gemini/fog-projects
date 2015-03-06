databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424859710771-1") {
		addColumn(tableName: "route_table") {
			column(name: "region_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
