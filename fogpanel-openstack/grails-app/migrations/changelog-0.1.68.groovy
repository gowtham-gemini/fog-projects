databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1423474551906-1") {
		addColumn(tableName: "security_group") {
			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1423474551906-2") {
		dropForeignKeyConstraint(baseTableName: "security_group", constraintName: "FK_rim39616qje3nffpvt1ecnsmb")
	}
//
//	changeSet(author: "developer (generated)", id: "1423474551906-4") {
//		createIndex(indexName: "FK_sl3jnp9vr046lmfqf5yg0np84", tableName: "security_group") {
//			column(name: "account_id")
//		}
//	}

	changeSet(author: "developer (generated)", id: "1423474551906-5") {
		dropColumn(columnName: "project_id", tableName: "security_group")
	}

//	changeSet(author: "developer (generated)", id: "1423474551906-3") {
//		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "security_group", constraintName: "FK_sl3jnp9vr046lmfqf5yg0np84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
//	}
}
