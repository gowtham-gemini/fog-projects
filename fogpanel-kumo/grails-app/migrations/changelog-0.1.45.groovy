databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1379913796635-1") {
		addColumn(tableName: "log") {
			column(name: "viewed", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
