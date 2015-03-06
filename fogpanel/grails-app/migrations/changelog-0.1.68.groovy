databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1383212662384-1") {
		addColumn(tableName: "refund") {
			column(name: "created_by", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1383212662384-2") {
		addColumn(tableName: "refund") {
			column(name: "created_date", type: "datetime")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1383212662384-3") {
		addColumn(tableName: "refund") {
			column(name: "edited_by", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1383212662384-4") {
		addColumn(tableName: "refund") {
			column(name: "edited_date", type: "datetime")
		}
	}
}
