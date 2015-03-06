databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1418277302392-1") {
		addColumn(tableName: "account") {
			column(name: "is_completed", type: "bit")
		}
	}
}
