databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1399962545077-1") {
		addColumn(tableName: "account") {
			column(name: "vm_snap_usage_date", type: "datetime")
		}
	}
}
