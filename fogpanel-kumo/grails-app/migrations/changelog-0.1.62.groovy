databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1382426966993-1") {
		addColumn(tableName: "user") {
			column(name: "token_expiry_date", type: "datetime")
		}
	}

	
}
