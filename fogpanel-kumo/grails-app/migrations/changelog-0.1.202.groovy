databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1412338689504-1") {
		addColumn(tableName: "network") {
			column(name: "tier_type", type: "varchar(255)")
		}
	}
}
