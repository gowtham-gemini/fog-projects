databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1418894656695-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "zone_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1418894656695-11") {
		createIndex(indexName: "FK_g7m5i040mh3pxr8onlhtqpkx7", tableName: "virtual_machine") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1418894656695-10") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "virtual_machine", constraintName: "FK_g7m5i040mh3pxr8onlhtqpkx7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
