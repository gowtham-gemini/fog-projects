databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1413614324093-1") {
		createTable(tableName: "virtual_machine") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "virtual_machiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit")

			column(name: "deleted_at", type: "datetime")

			column(name: "first_run", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "flavor_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "image_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413614324093-36") {
		createIndex(indexName: "FK_4jt39p05i8q4w4d3vabwrtadf", tableName: "virtual_machine") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413614324093-37") {
		createIndex(indexName: "FK_7nk7ln6js0lj710qyq6v8ahi2", tableName: "virtual_machine") {
			column(name: "image_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413614324093-38") {
		createIndex(indexName: "FK_af3lte75yu6ih16y6wqqspqj", tableName: "virtual_machine") {
			column(name: "flavor_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413614324093-39") {
                createIndex(indexName: "FK_tqtkdro59c0ovnlsyofket2sj", tableName: "virtual_machine") {
                        column(name: "user_id")
                }
	}

	changeSet(author: "gowtham (generated)", id: "1413614324093-32") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "virtual_machine", constraintName: "FK_4jt39p05i8q4w4d3vabwrtadf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413614324093-33") {
		addForeignKeyConstraint(baseColumnNames: "flavor_id", baseTableName: "virtual_machine", constraintName: "FK_af3lte75yu6ih16y6wqqspqj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "flavors", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413614324093-34") {
		addForeignKeyConstraint(baseColumnNames: "image_id", baseTableName: "virtual_machine", constraintName: "FK_7nk7ln6js0lj710qyq6v8ahi2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "images", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413614324093-35") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "virtual_machine", constraintName: "FK_tqtkdro59c0ovnlsyofket2sj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
