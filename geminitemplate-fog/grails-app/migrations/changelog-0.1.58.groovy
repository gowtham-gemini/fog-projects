databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1420527371135-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "monitoring_enabled", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
