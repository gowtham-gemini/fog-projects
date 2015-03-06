databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1383736763075-1") {
		createTable(tableName: "daily_usage_cost") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "daily_usage_cPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}
}
