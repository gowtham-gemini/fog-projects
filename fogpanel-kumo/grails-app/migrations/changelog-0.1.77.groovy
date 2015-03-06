databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1384150133874-1") {
		addColumn(tableName: "daily_usage_cost") {
			column(name: "refund", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}
}
