databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1414065378335-1") {
		addColumn(tableName: "images") {
			column(name: "isvmsnapshot", type: "bit")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414065378335-2") {
		addColumn(tableName: "images") {
			column(name: "virtual_machine_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414065378335-3") {
		addColumn(tableName: "virtual_machine") {
			column(name: "keypair_id", type: "bigint")
		}
	}
	
	changeSet(author: "gowtham (generated)", id: "1414065378335-10") {
		createIndex(indexName: "FK_992ism1jtj460nqx2r25tkfq1", tableName: "virtual_machine") {
			column(name: "keypair_id")
		}
	}
	
	changeSet(author: "gowtham (generated)", id: "1414065378335-8") {
		addForeignKeyConstraint(baseColumnNames: "keypair_id", baseTableName: "virtual_machine", constraintName: "FK_992ism1jtj460nqx2r25tkfq1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "sshkeys", referencesUniqueColumn: "false")
	}
}
