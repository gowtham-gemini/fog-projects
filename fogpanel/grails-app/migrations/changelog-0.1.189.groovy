databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1410249451224-1") {
		addColumn(tableName: "network") {
			column(name: "broadcast_uri", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1410249451224-2") {
		addColumn(tableName: "network") {
			column(name: "vpc_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1410249451224-4") {
		createIndex(indexName: "FK_cxghlrl9wt67ncuyrb67ylsfy", tableName: "network") {
			column(name: "vpc_id")
		}
	}

//	changeSet(author: "gowtham (generated)", id: "1410249451224-3") {
//		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "network", constraintName: "FK_cxghlrl9wt67ncuyrb67ylsfy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
//	}
}
