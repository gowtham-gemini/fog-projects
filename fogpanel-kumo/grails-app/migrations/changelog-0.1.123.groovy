databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1396000777904-1") {
		addColumn(tableName: "disk_offer") {
			column(name: "disk_read_ratebps", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396000777904-2") {
		addColumn(tableName: "disk_offer") {
			column(name: "disk_read_rateiops", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396000777904-3") {
		addColumn(tableName: "disk_offer") {
			column(name: "disk_write_ratebps", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396000777904-4") {
		addColumn(tableName: "disk_offer") {
			column(name: "disk_write_rateiops", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396000777904-5") {
		addColumn(tableName: "disk_offer") {
			column(name: "hypervisor_snap_reserve", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396000777904-6") {
		addColumn(tableName: "disk_offer") {
			column(name: "is_customized_iops", type: "bit")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396000777904-7") {
		addColumn(tableName: "disk_offer") {
			column(name: "maxiops", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396000777904-8") {
		addColumn(tableName: "disk_offer") {
			column(name: "miniops", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396000777904-9") {
		addColumn(tableName: "disk_offer") {
			column(name: "qostype", type: "varchar(255)")
		}
	}
}
