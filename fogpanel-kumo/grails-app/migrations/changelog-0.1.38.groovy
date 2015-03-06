databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1378281541305-1") {
		addColumn(tableName: "recurring_item") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
