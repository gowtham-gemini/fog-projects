databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1395983699422-1") {
		addColumn(tableName: "computing_offer") {
			column(name: "disk_read_ratebps", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1395983699422-2") {
		addColumn(tableName: "computing_offer") {
			column(name: "disk_read_rateiops", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1395983699422-3") {
		addColumn(tableName: "computing_offer") {
			column(name: "disk_write_ratebps", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1395983699422-4") {
		addColumn(tableName: "computing_offer") {
			column(name: "disk_write_rateiops", type: "varchar(255)")
		}
	}
}
