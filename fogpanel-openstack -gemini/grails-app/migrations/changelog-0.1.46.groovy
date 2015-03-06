databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1419334993164-1") {
		addColumn(tableName: "security_group_rules") {
			column(name: "direction", type: "varchar(255)")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1419334993164-2") {
		addColumn(tableName: "security_group_rules") {
			column(name: "ether_type", type: "varchar(255)")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1419334993164-3") {
		addColumn(tableName: "security_group_rules") {
			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1419334993164-4") {
		addColumn(tableName: "security_group_rules") {
			column(name: "remote_group_id", type: "varchar(255)")
		}
	}
}
