databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1380786533006-1") {
		addColumn(tableName: "volume") {
			column(name: "source", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1380786533006-52") {
		dropColumn(columnName: "from", tableName: "volume")
	}

	changeSet(author: "gowtham (generated)", id: "1380786533006-53") {
		dropColumn(columnName: "size", tableName: "volume")
	}
}
