databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1424505583138-1") {
		addColumn(tableName: "alarm") {
			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}
	

//	changeSet(author: "Nandhini (generated)", id: "1424505583138-17") {
//		createIndex(indexName: "FK_hovy2t32xm2y26v2g86tw4lkj", tableName: "alarm") {
//			column(name: "account_id")
//		}
//	}

	

//	changeSet(author: "Nandhini (generated)", id: "1424505583138-8") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "alarm", constraintName: "FK_hovy2t32xm2y26v2g86tw4lkj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}

	
}
