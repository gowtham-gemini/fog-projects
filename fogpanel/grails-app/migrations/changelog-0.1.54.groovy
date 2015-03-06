databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1382000727447-1") {
		addColumn(tableName: "account") {
			column(name: "credit_limit_level", type: "varchar(255)")
		}
	}
}
