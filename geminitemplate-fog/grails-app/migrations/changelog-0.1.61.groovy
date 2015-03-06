databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1421065738285-1") {
		createTable(tableName: "monitoring_device") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "monitoring_dePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "collector", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "device_name", type: "varchar(255)")

			column(name: "job_id", type: "varchar(255)")

			column(name: "job_status", type: "varchar(255)")

			column(name: "os_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "refrence_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "snmp_community", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "snmp_port", type: "integer") {
				constraints(nullable: "false")
			}

			column(name: "updated_at", type: "datetime")

			column(name: "virtual_machine_id", type: "bigint")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421065738285-3") {
		createIndex(indexName: "FK_tjckpis95sjbv04imjf3drmv5", tableName: "monitoring_device") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1421065738285-2") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "monitoring_device", constraintName: "FK_tjckpis95sjbv04imjf3drmv5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}
}
