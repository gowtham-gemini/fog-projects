databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1385469916929-1") {
		addColumn(tableName: "account") {
			column(name: "bandwidth_limit", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1385469916929-2") {
		addColumn(tableName: "account") {
			column(name: "snapshot_limit", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1385469916929-3") {
		addColumn(tableName: "account") {
			column(name: "storage_limit", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1385469916929-4") {
		addColumn(tableName: "account") {
			column(name: "vm_limit", type: "varchar(255)")
		}
	}
}
