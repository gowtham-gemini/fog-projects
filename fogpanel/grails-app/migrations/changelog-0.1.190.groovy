databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1410414238646-1") {
		addColumn(tableName: "useripaddress") {
			column(name: "vpc_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1410414238646-4") {
		createIndex(indexName: "FK_lk720evr622xs34a40uj7hctx", tableName: "useripaddress") {
			column(name: "vpc_id")
		}
	}

//	changeSet(author: "gowtham (generated)", id: "1410414238646-2") {
//		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "network", constraintName: "FK_cxghlrl9wt67ncuyrb67ylsfy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1410414238646-3") {
//		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "useripaddress", constraintName: "FK_lk720evr622xs34a40uj7hctx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
//	}
}
