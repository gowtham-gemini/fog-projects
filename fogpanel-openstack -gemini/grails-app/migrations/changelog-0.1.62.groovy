databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1421228631502-1") {
		addColumn(tableName: "alarm") {
			column(name: "max_value", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-2") {
		addColumn(tableName: "alarm") {
			column(name: "min_value", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-3") {
		addColumn(tableName: "alarm") {
			column(name: "monitoring_device_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-4") {
		addColumn(tableName: "alarm") {
			column(name: "name", type: "varchar(255)")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-5") {
		addColumn(tableName: "alarm") {
			column(name: "refrence_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-6") {
		addColumn(tableName: "alarm") {
			column(name: "severity", type: "varchar(255)")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-7") {
		addColumn(tableName: "alarm") {
			column(name: "threshold_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-9") {
		dropIndex(indexName: "unique_virtual_machine_id", tableName: "alarm")
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-10") {
		createIndex(indexName: "FK_b47w3k4gb7oct25fgmj7fn53n", tableName: "alarm") {
			column(name: "monitoring_device_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-11") {
		createIndex(indexName: "unique_virtual_machine_id", tableName: "alarm", unique: "true") {
			column(name: "port_number")

			column(name: "type")

			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-12") {
		dropColumn(columnName: "condition", tableName: "alarm")
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-13") {
		dropColumn(columnName: "threshold", tableName: "alarm")
	}

	changeSet(author: "Abdul (generated)", id: "1421228631502-8") {
		addForeignKeyConstraint(baseColumnNames: "monitoring_device_id", baseTableName: "alarm", constraintName: "FK_b47w3k4gb7oct25fgmj7fn53n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "monitoring_device", referencesUniqueColumn: "false")
	}
}
