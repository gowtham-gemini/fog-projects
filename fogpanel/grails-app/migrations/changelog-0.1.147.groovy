databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1402207551572-1") {
		addColumn(tableName: "network_offer") {
			column(name: "guest_ip_type", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402207551572-2") {
		dropColumn(columnName: "guest_type", tableName: "network_offer")
	}
}
