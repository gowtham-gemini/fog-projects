databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1418991066138-1") {
		addColumn(tableName: "volume_snapshot") {
			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1418991066138-11") {
		createIndex(indexName: "FK_hqxwu7wnx0i17ow51mvpsqes", tableName: "volume_snapshot") {
			column(name: "zone_id")
		}
	}
	
//	changeSet(author: "gowtham (generated)", id: "1418991066138-10") {
//		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "volume_snapshot", constraintName: "FK_hqxwu7wnx0i17ow51mvpsqes", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
//	}
}
