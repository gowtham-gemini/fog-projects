databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1413544219886-1") {
		createTable(tableName: "network_subnet") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "network_subnePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "dhcpenable", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "dnsname_servers", type: "varchar(255)")

			column(name: "ipversion", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "allocation_pools", type: "varchar(255)")

			column(name: "cidr", type: "varchar(255)")

			column(name: "gatewayip", type: "varchar(255)")

			column(name: "host_routes", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-2") {
		addColumn(tableName: "network") {
			column(name: "account_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-3") {
		addColumn(tableName: "network") {
			column(name: "is_shared", type: "bit")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-4") {
		addColumn(tableName: "network") {
			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-5") {
		addColumn(tableName: "network") {
			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-41") {
		createIndex(indexName: "FK_37thpll3yx9nsv9qhyeborr90", tableName: "network") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-42") {
		createIndex(indexName: "FK_6dos8079g5b706dhgnu2k6983", tableName: "network") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-43") {
		createIndex(indexName: "FK_ismjee0god515qtyujx9lpfff", tableName: "network_subnet") {
			column(name: "network_id")
		}
	}


	changeSet(author: "gowtham (generated)", id: "1413544219886-15") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "network", constraintName: "FK_6dos8079g5b706dhgnu2k6983", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-16") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "network", constraintName: "FK_37thpll3yx9nsv9qhyeborr90", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-17") {
		addForeignKeyConstraint(baseColumnNames: "network_id", baseTableName: "network_subnet", constraintName: "FK_ismjee0god515qtyujx9lpfff", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-25") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "security_group", constraintName: "FK_8cx8ylpdj6l7m6duniqxgomdh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1413544219886-26") {
		addForeignKeyConstraint(baseColumnNames: "security_group_id", baseTableName: "security_group_rules", constraintName: "FK_j47n7qdogdyxkhp86twuwgxqo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "security_group", referencesUniqueColumn: "false")
	}

}
