databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424760695220-1") {
		createTable(tableName: "route_table") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "route_tablePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "vpc_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424760695220-2") {
		createTable(tableName: "routes") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "routesPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "destination_cidr_block", type: "varchar(255)")

			column(name: "gateway_id", type: "varchar(255)")

			column(name: "origin", type: "varchar(255)")

			column(name: "route_table_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)")
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424760695220-4") {
		createIndex(indexName: "FK_43aioirl3l0xdf5ln9ts1cj47", tableName: "routes") {
			column(name: "route_table_id")
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424760695220-3") {
		addForeignKeyConstraint(baseColumnNames: "route_table_id", baseTableName: "routes", constraintName: "FK_43aioirl3l0xdf5ln9ts1cj47", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "route_table", referencesUniqueColumn: "false")
	}
}
