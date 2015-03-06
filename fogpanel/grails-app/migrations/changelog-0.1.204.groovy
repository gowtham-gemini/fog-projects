databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1415690104345-1") {
		createTable(tableName: "service_cost_change_log") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "service_cost_PK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "changed_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "computing_offer_zone_cost_id", type: "bigint")

			column(name: "disk_offer_zone_cost_id", type: "bigint")

			column(name: "miscellaneous_offer_zone_cost_id", type: "bigint")

			column(name: "new_cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "new_minimum_cost", type: "double precision")

			column(name: "new_setup_cost", type: "double precision")

			column(name: "old_cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "old_minimum_cost", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "old_setup_cost", type: "double precision")

			column(name: "service_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1415690104345-7") {
		createIndex(indexName: "FK_ao7bxi6r2uivxngfsqu6svuuq", tableName: "service_cost_change_log") {
			column(name: "computing_offer_zone_cost_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1415690104345-8") {
		createIndex(indexName: "FK_hhr9vxd1qho81htoopxcm5vbt", tableName: "service_cost_change_log") {
			column(name: "miscellaneous_offer_zone_cost_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1415690104345-9") {
		createIndex(indexName: "FK_kih27pmuepxsw7uq70rje5qed", tableName: "service_cost_change_log") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1415690104345-10") {
		createIndex(indexName: "FK_pnilrwfuf57nyxid4680n8t23", tableName: "service_cost_change_log") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1415690104345-11") {
		createIndex(indexName: "FK_riybegubq200t7wj4i9cea1oj", tableName: "service_cost_change_log") {
			column(name: "disk_offer_zone_cost_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1415690104345-2") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "service_cost_change_log", constraintName: "FK_kih27pmuepxsw7uq70rje5qed", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1415690104345-3") {
		addForeignKeyConstraint(baseColumnNames: "computing_offer_zone_cost_id", baseTableName: "service_cost_change_log", constraintName: "FK_ao7bxi6r2uivxngfsqu6svuuq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "computing_offer_zone_cost", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1415690104345-4") {
		addForeignKeyConstraint(baseColumnNames: "disk_offer_zone_cost_id", baseTableName: "service_cost_change_log", constraintName: "FK_riybegubq200t7wj4i9cea1oj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "disk_offer_zone_cost", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1415690104345-5") {
		addForeignKeyConstraint(baseColumnNames: "miscellaneous_offer_zone_cost_id", baseTableName: "service_cost_change_log", constraintName: "FK_hhr9vxd1qho81htoopxcm5vbt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "miscellaneous_offer_zone_cost", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1415690104345-6") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "service_cost_change_log", constraintName: "FK_pnilrwfuf57nyxid4680n8t23", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
}
