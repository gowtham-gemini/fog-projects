databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1407326815555-1") {
		createTable(tableName: "firewall_rules") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "firewall_rulePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "end_port", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "netwotk_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "protocol", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "purpose", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "purpose_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "start_port", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)")

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "useripaddress_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-2") {
		createTable(tableName: "load_balancer_vm_map") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "load_balancerPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "load_balancer_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "revoked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)")

			column(name: "virtual_machine_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-3") {
		addColumn(tableName: "load_balancer") {
			column(name: "algorithm", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-4") {
		addColumn(tableName: "load_balancer") {
			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-5") {
		addColumn(tableName: "load_balancer") {
			column(name: "description", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-6") {
		addColumn(tableName: "load_balancer") {
			column(name: "firewall_rules_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-7") {
		addColumn(tableName: "load_balancer") {
			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-8") {
		addColumn(tableName: "load_balancer") {
			column(name: "private_port", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-9") {
		addColumn(tableName: "load_balancer") {
			column(name: "public_port", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-10") {
		addColumn(tableName: "load_balancer") {
			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-11") {
		addColumn(tableName: "load_balancer") {
			column(name: "scheme", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-18") {
		createIndex(indexName: "FK_2g4bj9stm1u42sj7urmnjv41q", tableName: "firewall_rules") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-19") {
		createIndex(indexName: "FK_4nxyq9i7swj4885sq1rv1kkiq", tableName: "firewall_rules") {
			column(name: "netwotk_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-20") {
		createIndex(indexName: "FK_fmmdownoyf82ctshvv0e9ysl0", tableName: "firewall_rules") {
			column(name: "useripaddress_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-21") {
		createIndex(indexName: "FK_fm273g6j92vqtt4mv3qels3i3", tableName: "load_balancer") {
			column(name: "firewall_rules_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-22") {
		createIndex(indexName: "FK_a7xg4935lvs7f6dtxestente3", tableName: "load_balancer_vm_map") {
			column(name: "load_balancer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-23") {
		createIndex(indexName: "FK_ncblyfy11l7j230r3gm5tylel", tableName: "load_balancer_vm_map") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-12") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "firewall_rules", constraintName: "FK_2g4bj9stm1u42sj7urmnjv41q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-13") {
		addForeignKeyConstraint(baseColumnNames: "netwotk_id", baseTableName: "firewall_rules", constraintName: "FK_4nxyq9i7swj4885sq1rv1kkiq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-14") {
		addForeignKeyConstraint(baseColumnNames: "useripaddress_id", baseTableName: "firewall_rules", constraintName: "FK_fmmdownoyf82ctshvv0e9ysl0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "useripaddress", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-15") {
		addForeignKeyConstraint(baseColumnNames: "firewall_rules_id", baseTableName: "load_balancer", constraintName: "FK_fm273g6j92vqtt4mv3qels3i3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "firewall_rules", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-16") {
		addForeignKeyConstraint(baseColumnNames: "load_balancer_id", baseTableName: "load_balancer_vm_map", constraintName: "FK_a7xg4935lvs7f6dtxestente3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "load_balancer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407326815555-17") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "load_balancer_vm_map", constraintName: "FK_ncblyfy11l7j230r3gm5tylel", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}
}
