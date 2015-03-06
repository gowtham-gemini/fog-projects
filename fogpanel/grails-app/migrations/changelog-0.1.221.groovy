databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1417759764746-2") {
		dropIndex(indexName: "user_name_unique_1383280327309", tableName: "account")
	}

	changeSet(author: "developer (generated)", id: "1417759764746-3") {
		dropIndex(indexName: "username_unique_1383280327470", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1417759764746-4") {
		createIndex(indexName: "unique_user_name", tableName: "account", unique: "true") {
			column(name: "domain_id")

			column(name: "user_name")
		}
	}

	changeSet(author: "developer (generated)", id: "1417759764746-5") {
		createIndex(indexName: "unique_username", tableName: "user", unique: "true") {
			column(name: "domain_id")

			column(name: "username")
		}
	}

//	changeSet(author: "developer (generated)", id: "1417759764746-1") {
//		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "user", constraintName: "FK_p10kanw1ubd833ui091tkhw2d", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
//	}
}
