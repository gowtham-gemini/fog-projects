databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1385114911683-1") {
		addColumn(tableName: "account") {
			column(name: "card_expiry_month", type: "double precision")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1385114911683-2") {
		addColumn(tableName: "account") {
			column(name: "card_expiry_year", type: "double precision")
		}
	}
}
