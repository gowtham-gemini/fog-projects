databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1405770663271-1") {
		createTable(tableName: "nic_secondaryip") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "nic_secondaryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "ip_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ip_reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "job_id", type: "varchar(255)")

			column(name: "network_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "nic_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "removed", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "virtual_machine_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

        changeSet(author: "gowtham (generated)", id: "1405770663271-8") {
		createIndex(indexName: "FK_oy8akoxr8eyccbr4my3xwc8on", tableName: "nic_secondaryip") {
			column(name: "network_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405770663271-9") {
		createIndex(indexName: "FK_pthnr9y0cxleyff9k9kw2m7om", tableName: "nic_secondaryip") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405770663271-10") {
		createIndex(indexName: "FK_ra6p1ekd3guf0mh3i6mfo1bf8", tableName: "nic_secondaryip") {
			column(name: "nic_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405770663271-11") {
		createIndex(indexName: "FK_sp6rxu03qjw3e86kw2uvianmp", tableName: "nic_secondaryip") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405770663271-12") {
		createIndex(indexName: "ip_reference_id_uniq_1405770662409", tableName: "nic_secondaryip", unique: "true") {
			column(name: "ip_reference_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405770663271-4") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "nic_secondaryip", constraintName: "FK_sp6rxu03qjw3e86kw2uvianmp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405770663271-5") {
		addForeignKeyConstraint(baseColumnNames: "network_id", baseTableName: "nic_secondaryip", constraintName: "FK_oy8akoxr8eyccbr4my3xwc8on", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405770663271-6") {
		addForeignKeyConstraint(baseColumnNames: "nic_id", baseTableName: "nic_secondaryip", constraintName: "FK_ra6p1ekd3guf0mh3i6mfo1bf8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "nic", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405770663271-7") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "nic_secondaryip", constraintName: "FK_pthnr9y0cxleyff9k9kw2m7om", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}
}
