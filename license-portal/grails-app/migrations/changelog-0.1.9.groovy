databaseChangeLog = {

	changeSet(author: "operations (generated)", id: "1407819321602-1") {
		addColumn(tableName: "user") {
			column(name: "ip_address", type: "varchar(255)")
		}
	}

	changeSet(author: "operations (generated)", id: "1407819321602-2") {
		addColumn(tableName: "user") {
			column(name: "last_login", type: "datetime")
		}
	}

	changeSet(author: "operations (generated)", id: "1407819321602-3") {
		addColumn(tableName: "user") {
			column(name: "registration_date", type: "datetime")
		}
	}
}
