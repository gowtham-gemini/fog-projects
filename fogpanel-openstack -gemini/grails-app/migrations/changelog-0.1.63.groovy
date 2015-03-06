databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1421935968458-1") {
		addColumn(tableName: "alarm") {
			column(name: "partition", type: "varchar(255)")
		}
	}
}
