databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1402483934599-1") {
		addColumn(tableName: "network") {
			column(name: "has_first_ip", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
