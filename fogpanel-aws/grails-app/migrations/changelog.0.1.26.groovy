databaseChangeLog = {

	changeSet(author: "az (generated)", id: "1424887092506-1") {
		addColumn(tableName: "sshkeys") {
			column(name: "region_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}
        
        //changeSet(author: "az (generated)", id: "1424887092506-2") {
	//	addForeignKeyConstraint(baseColumnNames: "region_id", baseTableName: "sshkeys", constraintName: "FK_cu4y8t1m5m0ye3k2hdu4n3qyt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "region", referencesUniqueColumn: "false")
	//}

	//changeSet(author: "az (generated)", id: "1424887092506-3") {
	//	createIndex(indexName: "FK_cu4y8t1m5m0ye3k2hdu4n3qyt", tableName: "sshkeys") {
	//		column(name: "region_id")
	//	}
	//}	
}
