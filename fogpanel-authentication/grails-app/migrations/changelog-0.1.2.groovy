databaseChangeLog = {

	changeSet(author: "az (generated)", id: "1423724849333-1") {
		createTable(tableName: "security_group") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "security_grouPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "description", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "az (generated)", id: "1423724849333-2") {
		createTable(tableName: "security_group_rules") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "security_grouPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cidr", type: "varchar(255)")

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "direction", type: "varchar(255)")

			column(name: "ether_type", type: "varchar(255)")

			column(name: "from_port", type: "integer")

			column(name: "protocol", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "remote_group_id", type: "varchar(255)")

			column(name: "security_group_id", type: "bigint")

			column(name: "to_port", type: "integer")
		}
	}

	changeSet(author: "az (generated)", id: "1423724849333-3") {
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

			column(name: "private_key", type: "longtext")

			column(name: "public_key", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "az (generated)", id: "1423724849333-9") {
		createIndex(indexName: "FK_8cx8ylpdj6l7m6duniqxgomdh", tableName: "security_group") {
			column(name: "user_id")
		}
	}

	changeSet(author: "az (generated)", id: "1423724849333-10") {
		createIndex(indexName: "FK_sl3jnp9vr046lmfqf5yg0np84", tableName: "security_group") {
			column(name: "account_id")
		}
	}

	changeSet(author: "az (generated)", id: "1423724849333-11") {
		createIndex(indexName: "FK_j47n7qdogdyxkhp86twuwgxqo", tableName: "security_group_rules") {
			column(name: "security_group_id")
		}
	}

	changeSet(author: "az (generated)", id: "1423724849333-12") {
		createIndex(indexName: "FK_62rnb2hcgy2v38lp8mhrw1c5y", tableName: "sshkeys") {
			column(name: "user_id")
		}
	}

	changeSet(author: "az (generated)", id: "1423724849333-13") {
		createIndex(indexName: "FK_7c2bki05yhorcxv1inwqid1u6", tableName: "sshkeys") {
			column(name: "account_id")
		}
	}

	changeSet(author: "az (generated)", id: "1423724849333-14") {
		createIndex(indexName: "unique_name", tableName: "sshkeys", unique: "true") {
			column(name: "account_id")

			column(name: "name")
		}
	}

	changeSet(author: "az (generated)", id: "1423724849333-4") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "security_group", constraintName: "FK_sl3jnp9vr046lmfqf5yg0np84", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1423724849333-5") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "security_group", constraintName: "FK_8cx8ylpdj6l7m6duniqxgomdh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1423724849333-6") {
		addForeignKeyConstraint(baseColumnNames: "security_group_id", baseTableName: "security_group_rules", constraintName: "FK_j47n7qdogdyxkhp86twuwgxqo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "security_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1423724849333-7") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "sshkeys", constraintName: "FK_7c2bki05yhorcxv1inwqid1u6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "az (generated)", id: "1423724849333-8") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "sshkeys", constraintName: "FK_62rnb2hcgy2v38lp8mhrw1c5y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
