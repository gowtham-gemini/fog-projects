databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1425360976074-1") {
		addColumn(tableName: "route_table") {
			column(name: "region_id", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}
}
