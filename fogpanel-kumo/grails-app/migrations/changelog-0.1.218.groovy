databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1416896678353-1") {
		addColumn(tableName: "account") {
			column(name: "is_usage_running_failed", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
