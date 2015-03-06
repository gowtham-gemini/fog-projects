databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1418619852367-1") {
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

			column(name: "created_at", type: "datetime")

			column(name: "fingerprint", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "private_key", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "public_key", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1418619852367-5") {
		createIndex(indexName: "FK_62rnb2hcgy2v38lp8mhrw1c5y", tableName: "sshkeys") {
			column(name: "user_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1418619852367-6") {
		createIndex(indexName: "FK_7c2bki05yhorcxv1inwqid1u6", tableName: "sshkeys") {
			column(name: "account_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1418619852367-7") {
		createIndex(indexName: "name_uniq_1418619851866", tableName: "sshkeys", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1418619852367-3") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "sshkeys", constraintName: "FK_7c2bki05yhorcxv1inwqid1u6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "Abdul (generated)", id: "1418619852367-4") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "sshkeys", constraintName: "FK_62rnb2hcgy2v38lp8mhrw1c5y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
