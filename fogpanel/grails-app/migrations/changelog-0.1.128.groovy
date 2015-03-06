databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1397113368954-1") {
		addColumn(tableName: "snapshot") {
			column(name: "size", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}
}
