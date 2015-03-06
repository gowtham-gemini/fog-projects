databaseChangeLog = {

	changeSet(author: "santhosh (generated)", id: "1424843030238-1") {
		addColumn(tableName: "security_group") {
			column(name: "vpc_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424843030238-3") {
		createIndex(indexName: "FK_icselgyg1l8ka63mlq9qqawdv", tableName: "security_group") {
			column(name: "vpc_id")
		}
	}

	changeSet(author: "santhosh (generated)", id: "1424843030238-2") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "security_group", constraintName: "FK_icselgyg1l8ka63mlq9qqawdv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}
}
