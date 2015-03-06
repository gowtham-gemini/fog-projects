databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1377500989821-1") {
		addColumn(tableName: "billable_item") {
			column(name: "has_zone", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
