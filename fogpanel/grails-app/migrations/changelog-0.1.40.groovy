databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1378793943168-1") {
		addColumn(tableName: "account") {
			column(name: "street_address1", type: "varchar(255)")
		}
	}
}