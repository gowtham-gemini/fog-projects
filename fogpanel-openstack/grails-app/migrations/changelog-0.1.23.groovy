databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1415622230883-1") {
		addColumn(tableName: "zone") {
			column(name: "available", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1415622230883-2") {
		addColumn(tableName: "zone") {
			column(name: "created_at", type: "datetime")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1415622230883-3") {
		addColumn(tableName: "zone") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1415622230883-4") {
		addColumn(tableName: "zone") {
			column(name: "deleted_at", type: "datetime")
		}
	}

}
