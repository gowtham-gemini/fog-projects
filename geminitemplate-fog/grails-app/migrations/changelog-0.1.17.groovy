databaseChangeLog = {

	changeSet(author: "lakshmi (generated)", id: "1414408785790-1") {
		createTable(tableName: "virtual_machine_security_groups") {
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

			column(name: "security_group_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "virtual_machine_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}
	

	changeSet(author: "lakshmi (generated)", id: "1414408785790-12") {
		createIndex(indexName: "FK_f6v2rk4jl4ih703xidq1pp3rn", tableName: "virtual_machine_security_groups") {
			column(name: "virtual_machine_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1414408785790-13") {
		createIndex(indexName: "FK_kbo1jnetn3q0y1pbrcobp2o47", tableName: "virtual_machine_security_groups") {
			column(name: "security_group_id")
		}
	}

	changeSet(author: "lakshmi (generated)", id: "1414408785790-10") {
		addForeignKeyConstraint(baseColumnNames: "security_group_id", baseTableName: "virtual_machine_security_groups", constraintName: "FK_kbo1jnetn3q0y1pbrcobp2o47", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "security_group", referencesUniqueColumn: "false")
	}

	changeSet(author: "lakshmi (generated)", id: "1414408785790-11") {
		addForeignKeyConstraint(baseColumnNames: "virtual_machine_id", baseTableName: "virtual_machine_security_groups", constraintName: "FK_f6v2rk4jl4ih703xidq1pp3rn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "virtual_machine", referencesUniqueColumn: "false")
	}
}
