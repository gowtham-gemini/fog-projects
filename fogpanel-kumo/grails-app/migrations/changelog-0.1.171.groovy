databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1406803316851-1") {
		createTable(tableName: "load_balancer") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "load_balancerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1406803316851-2") {
		addColumn(tableName: "computing_offer") {
			column(name: "diskio", type: "varchar(255)")
		}
	}
}
