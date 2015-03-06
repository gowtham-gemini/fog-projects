databaseChangeLog = {

	changeSet(author: "abdul (generated)", id: "1413179011940-5") {
		addColumn(tableName: "region") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
