databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1384925622488-1") {
		addColumn(tableName: "pre_defined_reply") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
