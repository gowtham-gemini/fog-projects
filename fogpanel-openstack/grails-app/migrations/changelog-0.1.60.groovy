databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1420722436335-1") {
		createTable(tableName: "alarm") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "alarmPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "condition", type: "varchar(255)")

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "port_number", type: "integer")

			column(name: "sub_type", type: "varchar(255)")

			column(name: "threshold", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "topic_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "updated_at", type: "datetime")

			column(name: "virtual_machine_id", type: "bigint")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420722436335-4") {
		createIndex(indexName: "FK_2fyyqvwehea2ugnrbs9jvmvrp", tableName: "alarm") {
			column(name: "topic_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420722436335-5") {
		createIndex(indexName: "FK_ofo99rxxjfpw25ouatxrwppq", tableName: "alarm") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420722436335-6") {
		createIndex(indexName: "unique_virtual_machine_id", tableName: "alarm", unique: "true") {
			column(name: "port_number")

			column(name: "threshold")

			column(name: "type")

			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420722436335-2") {
		addForeignKeyConstraint(baseColumnNames: "topic_id", baseTableName: "alarm", constraintName: "FK_2fyyqvwehea2ugnrbs9jvmvrp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "notification_topic", referencesUniqueColumn: "false")
	}

	changeSet(author: "Abdul (generated)", id: "1420722436335-3") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "alarm", constraintName: "FK_ofo99rxxjfpw25ouatxrwppq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}
}
