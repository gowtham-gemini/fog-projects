databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1420194162990-1") {
		createTable(tableName: "floating_ip") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "floating_ipPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "external_network_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "fixed_ip_address", type: "varchar(255)")

			column(name: "floating_ip_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "port_id", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "router_id", type: "varchar(255)")

			column(name: "status", type: "varchar(255)")

			column(name: "virtual_machine_id", type: "bigint")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420194162990-14") {
		createIndex(indexName: "FK_bwx7ygndd4sydu0s4sprs7qhi", tableName: "floating_ip") {
			column(name: "account_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420194162990-15") {
		createIndex(indexName: "FK_r97a6ojfrc9ndp42nrwu63o1o", tableName: "floating_ip") {
			column(name: "external_network_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420194162990-16") {
		createIndex(indexName: "FK_tkvea5bgljqh676ni39trg3v6", tableName: "floating_ip") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420194162990-9") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "floating_ip", constraintName: "FK_bwx7ygndd4sydu0s4sprs7qhi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "Abdul (generated)", id: "1420194162990-10") {
		addForeignKeyConstraint(baseColumnNames: "external_network_id", baseTableName: "floating_ip", constraintName: "FK_r97a6ojfrc9ndp42nrwu63o1o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "Abdul (generated)", id: "1420194162990-11") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "floating_ip", constraintName: "FK_tkvea5bgljqh676ni39trg3v6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

}
