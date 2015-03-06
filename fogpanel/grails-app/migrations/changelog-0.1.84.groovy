databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1385105641128-1") {
		addColumn(tableName: "ticket_details") {
			column(name: "viewed", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

}
