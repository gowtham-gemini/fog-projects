databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1420292922975-1") {
		createTable(tableName: "virtual_machine_networks") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "virtual_machiPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "fixed_ips", type: "varchar(255)")

			column(name: "network_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "virtual_machine_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420292922975-9") {
		createIndex(indexName: "FK_4h3hh481hn4ta4kwfws1dynd6", tableName: "virtual_machine_networks") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420292922975-10") {
		createIndex(indexName: "FK_tjwm0717jmbo7xcbf7ohpbrmt", tableName: "virtual_machine_networks") {
			column(name: "network_id")
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420292922975-7") {
		addForeignKeyConstraint(baseColumnNames: "network_id", baseTableName: "virtual_machine_networks", constraintName: "FK_tjwm0717jmbo7xcbf7ohpbrmt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "network", referencesUniqueColumn: "false")
	}

	changeSet(author: "Abdul (generated)", id: "1420292922975-8") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "virtual_machine_networks", constraintName: "FK_4h3hh481hn4ta4kwfws1dynd6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}
}
