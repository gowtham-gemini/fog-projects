databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1402289349635-1") {
		createTable(tableName: "network") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "networkPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cidr", type: "varchar(255)")

			column(name: "created_date", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "gateway", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_offer_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "removed_date", type: "datetime")

			column(name: "state", type: "varchar(255)")

			column(name: "traffic_type", type: "varchar(255)")

			column(name: "type", type: "varchar(255)")

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-2") {
		addColumn(tableName: "network_offer_zone_cost") {
			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-7") {
		dropIndex(indexName: "network_reference_id_unique_1383280327417", tableName: "network_offer")
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-8") {
		createIndex(indexName: "FK_5s834cqm3gd2531x7r5uiwwvs", tableName: "network") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-9") {
		createIndex(indexName: "FK_6dos8079g5b706dhgnu2k6983", tableName: "network") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-10") {
		createIndex(indexName: "FK_l0th9u9plv70gl6ql3s5tqqfg", tableName: "network") {
			column(name: "network_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-11") {
		createIndex(indexName: "reference_id_uniq_1402289349030", tableName: "network", unique: "true") {
			column(name: "reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-12") {
		createIndex(indexName: "FK_e7kddug9fc9ga4pam2ja8acoy", tableName: "network_offer_zone_cost") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-13") {
		dropColumn(columnName: "minimum_cost", tableName: "network_offer_zone_cost")
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-14") {
		dropColumn(columnName: "setup_cost", tableName: "network_offer_zone_cost")
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-3") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "network", constraintName: "FK_6dos8079g5b706dhgnu2k6983", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-4") {
		addForeignKeyConstraint(baseColumnNames: "network_offer_id", baseTableName: "network", constraintName: "FK_l0th9u9plv70gl6ql3s5tqqfg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-5") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "network", constraintName: "FK_5s834cqm3gd2531x7r5uiwwvs", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1402289349635-6") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "network_offer_zone_cost", constraintName: "FK_e7kddug9fc9ga4pam2ja8acoy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
}
