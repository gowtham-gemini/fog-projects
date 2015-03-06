databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1375176677797-1") {
		addColumn(tableName: "payments") {
			column(name: "payment_token", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1375176677797-52") {
		dropColumn(columnName: "payment_tocken", tableName: "payments")
	}
}
