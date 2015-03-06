databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1383719460986-1") {
		addColumn(tableName: "mail_template") {
			column(name: "subject", type: "varchar(5000)")
		}
	}

	
}
