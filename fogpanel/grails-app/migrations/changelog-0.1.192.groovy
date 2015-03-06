databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1410436675902-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "vpc_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1410436675902-4") {
		createIndex(indexName: "FK_s92l1kjcrwx9k3y7s4klbo8f8", tableName: "virtual_machine") {
			column(name: "vpc_id")
		}
	}

//	changeSet(author: "gowtham (generated)", id: "1410436675902-2") {
//		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "useripaddress", constraintName: "FK_lk720evr622xs34a40uj7hctx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1410436675902-3") {
//		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "virtual_machine", constraintName: "FK_s92l1kjcrwx9k3y7s4klbo8f8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
//	}
}
