databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1409914616818-1") {
		addColumn(tableName: "vpc") {
			column(name: "network_domain", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1409914616818-2") {
		addColumn(tableName: "vpc") {
			column(name: "restart_required", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
