databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1417083634263-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "keypair", type: "varchar(255)")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1417083634263-4") {
		dropForeignKeyConstraint(baseTableName: "virtual_machine", constraintName: "FK_992ism1jtj460nqx2r25tkfq1")
	}

	changeSet(author: "Abdul (generated)", id: "1417083634263-11") {
		dropIndex(indexName: "FK_7c2bki05yhorcxv1inwqid1u6", tableName: "sshkeys")
	}

	changeSet(author: "Abdul (generated)", id: "1417083634263-12") {
		dropColumn(columnName: "keypair_id", tableName: "virtual_machine")
	}

	changeSet(author: "Abdul (generated)", id: "1417083634263-14") {
		dropTable(tableName: "sshkeys")
	}

}
