databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1411732951232-1") {
		createTable(tableName: "static_routes") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "static_routesPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cidr", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "job_id", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "state", type: "varchar(255)")

			column(name: "vpc_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "vpc_gateways_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-2") {
		createTable(tableName: "vpcgateways") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "vpcgatewaysPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "gateway", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ip_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "is_static_nat", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "job_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "netmask", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_id", type: "bigint")

			column(name: "network_acl_rule_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_reference_id", type: "varchar(255)")

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "removed", type: "datetime")

			column(name: "state", type: "varchar(255)")

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "vlan_tag", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "vpc_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-11") {
		createIndex(indexName: "FK_2c019cl07nnb4n247xj62861p", tableName: "static_routes") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-12") {
		createIndex(indexName: "FK_ct1xhsqcv5r1880egx0cg5x8j", tableName: "static_routes") {
			column(name: "vpc_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-13") {
		createIndex(indexName: "FK_rbjhay81n3ruc06sglblsi4b9", tableName: "static_routes") {
			column(name: "vpc_gateways_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-14") {
		createIndex(indexName: "FK_sp5lysc1iiu0lhi0cvbmnj0o8", tableName: "static_routes") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-15") {
		createIndex(indexName: "reference_id_uniq_1411732950055", tableName: "static_routes", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-16") {
		createIndex(indexName: "FK_6fhjhi0eq6b6eso3kpgflgbfj", tableName: "vpcgateways") {
			column(name: "vpc_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-17") {
		createIndex(indexName: "FK_99w0m5i2fun1ayuc78pto07aj", tableName: "vpcgateways") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-18") {
		createIndex(indexName: "FK_b2b84h9414xw7s05mhjos1ku4", tableName: "vpcgateways") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-19") {
		createIndex(indexName: "FK_q9otxmkbb7v51gyw026lq1p54", tableName: "vpcgateways") {
			column(name: "network_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-20") {
		createIndex(indexName: "reference_id_uniq_1411732950076", tableName: "vpcgateways", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-3") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "static_routes", constraintName: "FK_2c019cl07nnb4n247xj62861p", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-4") {
		addForeignKeyConstraint(baseColumnNames: "vpc_gateways_id", baseTableName: "static_routes", constraintName: "FK_rbjhay81n3ruc06sglblsi4b9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpcgateways", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-5") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "static_routes", constraintName: "FK_ct1xhsqcv5r1880egx0cg5x8j", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-6") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "static_routes", constraintName: "FK_sp5lysc1iiu0lhi0cvbmnj0o8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-7") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "vpcgateways", constraintName: "FK_b2b84h9414xw7s05mhjos1ku4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-8") {
		addForeignKeyConstraint(baseColumnNames: "network_id", baseTableName: "vpcgateways", constraintName: "FK_q9otxmkbb7v51gyw026lq1p54", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-9") {
		addForeignKeyConstraint(baseColumnNames: "vpc_id", baseTableName: "vpcgateways", constraintName: "FK_6fhjhi0eq6b6eso3kpgflgbfj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "vpc", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1411732951232-10") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "vpcgateways", constraintName: "FK_99w0m5i2fun1ayuc78pto07aj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
