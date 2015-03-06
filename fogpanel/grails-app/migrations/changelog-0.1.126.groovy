databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1396431102746-1") {
		createTable(tableName: "vmsnapshot") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "vmsnapshotPK")
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

			column(name: "current", type: "bit")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "job_id", type: "varchar(255)")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)")

			column(name: "snapshot_memory", type: "bit")

			column(name: "state", type: "varchar(255)")

			column(name: "type", type: "varchar(255)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "virtual_machine_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396431102746-117") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "vmsnapshot", constraintName: "FKCBF83D7BA6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1396431102746-118") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "vmsnapshot", constraintName: "FKCBF83D7BA0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1396431102746-119") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "vmsnapshot", constraintName: "FKCBF83D7BF6AB5554", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1396431102746-120") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "vmsnapshot", constraintName: "FKCBF83D7BA2BF084B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1396431102746-184") {
		createIndex(indexName: "FKCBF83D7BA0981FEB", tableName: "vmsnapshot") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396431102746-185") {
		createIndex(indexName: "FKCBF83D7BA2BF084B", tableName: "vmsnapshot") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396431102746-186") {
		createIndex(indexName: "FKCBF83D7BA6E55AE9", tableName: "vmsnapshot") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396431102746-187") {
		createIndex(indexName: "FKCBF83D7BF6AB5554", tableName: "vmsnapshot") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1396431102746-188") {
		createIndex(indexName: "reference_id_unique_1396431102190", tableName: "vmsnapshot", unique: "true") {
			column(name: "reference_id")
		}
	}
        changeSet(author: "gowtham (generated)", id: "vm-snapshot-adminchanges") {
            insert(tableName: "miscellaneous_offer") {
                column(name: "id", valueNumeric: 4)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "VM SnapShot Cost")
                column(name: "name", value: "VM SnapShot Cost")
                column(name: "unit", value: "per Hr")
            }


            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 16)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "Monthly VM Snapshot")
                column(name: "reference_item_name", value: "Monthly VM Snapshot")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
                column(name: "has_zone", valueNumeric: 1)
            }

            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 17)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "VM Snapshot")
                column(name: "reference_item_name", value: "VM Snapshot")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
                column(name: "has_zone", valueNumeric: 1)
            }
        }
}
