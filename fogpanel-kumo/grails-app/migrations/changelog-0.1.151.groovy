databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1402640521721-1") {
		createTable(tableName: "nic") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "nicPK")
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

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "gateway", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "ip_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "mac_address", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "net_mask", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "network_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "removed_date", type: "datetime")

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "virtual_machine_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1402640521721-5") {
		createIndex(indexName: "FK_ddai75v4seu7v1p3wnoi22mtc", tableName: "nic") {
			column(name: "network_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1402640521721-6") {
		createIndex(indexName: "FK_js73syafhma3rcwkqx10ns48y", tableName: "nic") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1402640521721-7") {
		createIndex(indexName: "FK_p32tlb7n4lc87ykebcssw3l1s", tableName: "nic") {
			column(name: "account_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1402640521721-2") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "nic", constraintName: "FK_p32tlb7n4lc87ykebcssw3l1s", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1402640521721-3") {
		addForeignKeyConstraint(baseColumnNames: "network_id", baseTableName: "nic", constraintName: "FK_ddai75v4seu7v1p3wnoi22mtc", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "Nandhini (generated)", id: "1402640521721-4") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "nic", constraintName: "FK_js73syafhma3rcwkqx10ns48y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}
}
