databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1378978929862-1") {
		addColumn(tableName: "account") {
			column(name: "billing_street_address1", type: "varchar(255)")
		}
	}
    }
