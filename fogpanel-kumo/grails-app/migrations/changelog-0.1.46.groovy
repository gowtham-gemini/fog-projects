databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1380080710132-1") {
		addColumn(tableName: "account") {
			column(name: "cloud_password", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
