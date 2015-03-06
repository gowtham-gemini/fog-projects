databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1422441910279-1") {
		addColumn(tableName: "alarm") {
			column(name: "mount_point", type: "varchar(255)")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1422441910279-2") {
		addColumn(tableName: "alarm") {
			column(name: "port_enabled", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
