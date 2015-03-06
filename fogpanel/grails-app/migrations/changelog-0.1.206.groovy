databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1415708663581-1") {
		addColumn(tableName: "user") {
			column(name: "currency_format", type: "varchar(255)")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1415708663581-2") {
		addColumn(tableName: "user") {
			column(name: "date_format", type: "varchar(255)")
		}
	}
}
