databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1378187087998-1") {
		addColumn(tableName: "disk_offer") {
			column(name: "available", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
