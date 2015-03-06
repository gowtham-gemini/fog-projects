databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1385794750325-1") {
		addColumn(tableName: "cloud_maintenance") {
			column(name: "mail_send", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	
}
