databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1414740276067-1") {
		createTable(tableName: "asynchronous_jobs") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "asynchronous_PK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "completed_at", type: "datetime")

			column(name: "created_at", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "job_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "started_at", type: "datetime")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414740276067-2") {
		createTable(tableName: "volume_snapshot") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "volume_snapshPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "cteated_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "reference_id", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "status", type: "varchar(255)")

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "volume_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}
	

	changeSet(author: "gowtham (generated)", id: "1414740276067-11") {
		createIndex(indexName: "FK_dvw5fmbvgxwgu0uglef9v4jvi", tableName: "asynchronous_jobs") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414740276067-12") {
		createIndex(indexName: "FK_gh68tl37tghxw6il33ih4ym3y", tableName: "asynchronous_jobs") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414740276067-13") {
		createIndex(indexName: "FK_35ns6gpxtxi9gpnd9j2x6obc9", tableName: "volume_snapshot") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414740276067-14") {
		createIndex(indexName: "FK_3a1p9x4tvjekf1s8sesboqohm", tableName: "volume_snapshot") {
			column(name: "volume_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414740276067-15") {
		createIndex(indexName: "FK_9svckp4ndy6lh2dn4tarsn0us", tableName: "volume_snapshot") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1414740276067-4") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "asynchronous_jobs", constraintName: "FK_gh68tl37tghxw6il33ih4ym3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1414740276067-5") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "asynchronous_jobs", constraintName: "FK_dvw5fmbvgxwgu0uglef9v4jvi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
	

	changeSet(author: "gowtham (generated)", id: "1414740276067-8") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "volume_snapshot", constraintName: "FK_9svckp4ndy6lh2dn4tarsn0us", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1414740276067-9") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "volume_snapshot", constraintName: "FK_35ns6gpxtxi9gpnd9j2x6obc9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1414740276067-10") {
		addForeignKeyConstraint(baseColumnNames: "volume_id", baseTableName: "volume_snapshot", constraintName: "FK_3a1p9x4tvjekf1s8sesboqohm", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "volume", referencesUniqueColumn: "false")
	}
}
