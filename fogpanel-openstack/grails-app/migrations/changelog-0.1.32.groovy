databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1418646440993-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "keypair_id", type: "bigint")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1418646440993-11") {
		createIndex(indexName: "FK_992ism1jtj460nqx2r25tkfq1", tableName: "virtual_machine") {
			column(name: "keypair_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1418646440993-12") {
		dropColumn(columnName: "keypair", tableName: "virtual_machine")
	}

	changeSet(author: "Abdul (generated)", id: "1418646440993-10") {
		addForeignKeyConstraint(baseColumnNames: "keypair_id", baseTableName: "virtual_machine", constraintName: "FK_992ism1jtj460nqx2r25tkfq1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sshkeys", referencesUniqueColumn: "false")
	}
}
