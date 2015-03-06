databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1404898480603-1") {
		addColumn(tableName: "snapshot") {
			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1404898480603-5") {
		createIndex(indexName: "FK_hc33wc0x0cl9pitk68sf99fxc", tableName: "snapshot") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1404898480603-4") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "snapshot", constraintName: "FK_hc33wc0x0cl9pitk68sf99fxc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
