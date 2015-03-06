databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1419659018681-1") {
		addColumn(tableName: "router") {
			column(name: "external_network_id", type: "varchar(255)")
		}
	}

}
