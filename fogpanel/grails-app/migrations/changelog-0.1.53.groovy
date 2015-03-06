databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1381744668653-1") {
		addColumn(tableName: "account") {
			column(name: "last_usage_run_date", type: "datetime")
		}
	}
}
