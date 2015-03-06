databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1418216895026-1") {
		addColumn(tableName: "network_subnet") {
			column(name: "created_at", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1418216895026-2") {
		addColumn(tableName: "network_subnet") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1418216895026-3") {
		addColumn(tableName: "network_subnet") {
			column(name: "deleted_at", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}
	
}
