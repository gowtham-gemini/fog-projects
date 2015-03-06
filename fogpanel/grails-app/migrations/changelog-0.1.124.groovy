databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1396251673469-1") {
		addColumn(tableName: "disk_offer") {
			column(name: "domain_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396251673469-2") {
		addColumn(tableName: "disk_offer") {
			column(name: "is_public", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
}
