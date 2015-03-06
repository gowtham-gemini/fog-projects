databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1419833245383-1") {
		addColumn(tableName: "network") {
			column(name: "is_external", type: "bit") {
				constraints(nullable: "true")
			}
		}
	}
}
