databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1424869353436-1") {
		addColumn(tableName: "persistent_session") {
			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1424869353436-2") {
		addColumn(tableName: "persistent_session") {
			column(name: "username", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}
}
