databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1375684844570-1") {
		createTable(tableName: "log") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "logPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1375684844570-7") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "log", constraintName: "FK1A344A6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1375684844570-8") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "log", constraintName: "FK1A344A0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1375684844570-27") {
		createIndex(indexName: "FK1A344A0981FEB", tableName: "log") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1375684844570-28") {
		createIndex(indexName: "FK1A344A6E55AE9", tableName: "log") {
			column(name: "account_id")
		}
	}
}
