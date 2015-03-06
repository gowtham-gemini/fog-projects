databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1424875664320-1") {
		addColumn(tableName: "persistent_session") {
			column(name: "data", type: "longtext") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1424875664320-2") {
		dropColumn(columnName: "password", tableName: "persistent_session")
	}

	changeSet(author: "developer (generated)", id: "1424875664320-3") {
		dropColumn(columnName: "username", tableName: "persistent_session")
	}
}
