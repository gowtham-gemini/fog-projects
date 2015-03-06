databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1386071939670-1") {
		createTable(tableName: "sshkeys") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "sshkeysPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fingerprint", type: "varchar(5000)") {
				constraints(nullable: "false")
			}

			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "privatekey", type: "varchar(5000)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1386071939670-79") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "sshkeys", constraintName: "FK8D95A7FCA6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	

	changeSet(author: "gowtham (generated)", id: "1386071939670-152") {
		createIndex(indexName: "FK8D95A7FCA6E55AE9", tableName: "sshkeys") {
			column(name: "account_id")
		}
	}

	
}
