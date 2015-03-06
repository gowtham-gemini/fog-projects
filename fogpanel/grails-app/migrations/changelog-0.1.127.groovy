databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1396688050886-1") {
		addColumn(tableName: "vmsnapshot") {
			column(name: "billing_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
