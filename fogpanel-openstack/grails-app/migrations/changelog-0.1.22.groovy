databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1414995676164-1") {
		addColumn(tableName: "volume") {
			column(name: "volume_snapshot_id", type: "bigint")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1414995676164-12") {
		createIndex(indexName: "FK_j1i1bpi0kwnokqc2m907n35rp", tableName: "volume") {
			column(name: "volume_snapshot_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1414995676164-11") {
		addForeignKeyConstraint(baseColumnNames: "volume_snapshot_id", baseTableName: "volume", constraintName: "FK_j1i1bpi0kwnokqc2m907n35rp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "volume_snapshot", referencesUniqueColumn: "false")
	}
}
