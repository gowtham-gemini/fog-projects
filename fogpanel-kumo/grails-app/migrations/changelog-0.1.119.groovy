databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1394448158457-1") {
		addColumn(tableName: "payments") {
			column(name: "manual_payment_code", type: "varchar(255)")
		}
	}
}
