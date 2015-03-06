databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1382076157787-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "first_run", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
