databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1419594656280-1") {
		addColumn(tableName: "port") {
			column(name: "fixed_ips", type: "varchar(255)")
		}
	}

}
