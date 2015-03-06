databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1418375640585-1") {
		addColumn(tableName: "network") {
			column(name: "acl_reference_id", type: "varchar(255)")
		}
	}

}
