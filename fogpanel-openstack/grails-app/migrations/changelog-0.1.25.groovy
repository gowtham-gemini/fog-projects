databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1416390183540-1") {
		addColumn(tableName: "volume") {
			column(name: "virtual_machine_id", type: "bigint")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1416390183540-12") {
		createIndex(indexName: "FK_q8ha6vbmru7dxjbqqj2ra64wm", tableName: "volume") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1416390183540-11") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "volume", constraintName: "FK_q8ha6vbmru7dxjbqqj2ra64wm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}
}
