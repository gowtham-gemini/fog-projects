databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1418899662906-1") {
		addColumn(tableName: "invoice_item") {
			column(name: "zone_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1418899662906-11") {
		createIndex(indexName: "FK_n4tkekuqyf82cfbb27fq0m2nt", tableName: "invoice_item") {
			column(name: "zone_id")
		}
	}	

	changeSet(author: "gowtham (generated)", id: "1418899662906-9") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "invoice_item", constraintName: "FK_n4tkekuqyf82cfbb27fq0m2nt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
	
}
