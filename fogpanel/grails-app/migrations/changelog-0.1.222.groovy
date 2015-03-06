databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1417841707567-1") {
		addColumn(tableName: "account") {
			column(name: "account_identifier", type: "varchar(50)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1417841707567-2") {
		addColumn(tableName: "user") {
			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1417841707567-3") {
		addColumn(tableName: "user") {
			column(name: "first_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1417841707567-4") {
		addColumn(tableName: "user") {
			column(name: "last_name", type: "varchar(150)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1417841707567-6") {
		dropIndex(indexName: "unique_user_name", tableName: "account")
	}

	changeSet(author: "developer (generated)", id: "1417841707567-7") {
		createIndex(indexName: "unique_account_identifier", tableName: "account", unique: "true") {
			column(name: "domain_id")

			column(name: "account_identifier")
		}
	}

	changeSet(author: "developer (generated)", id: "1417841707567-8") {
		dropColumn(columnName: "user_name", tableName: "account")
	}

//	changeSet(author: "developer (generated)", id: "1417841707567-5") {
//		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "user", constraintName: "FK_p10kanw1ubd833ui091tkhw2d", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
//	}
}
