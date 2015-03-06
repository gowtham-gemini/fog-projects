databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1417065958114-1") {
		createTable(tableName: "asynchronous_jobs") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "asynchronous_PK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint")

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

			column(name: "user_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1417065958114-2") {
		addColumn(tableName: "domain") {
			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1417065958114-3") {
		addColumn(tableName: "user") {
			column(name: "domain_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1417065958114-7") {
		createIndex(indexName: "FK_dvw5fmbvgxwgu0uglef9v4jvi", tableName: "asynchronous_jobs") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1417065958114-8") {
		createIndex(indexName: "FK_gh68tl37tghxw6il33ih4ym3y", tableName: "asynchronous_jobs") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1417065958114-9") {
		createIndex(indexName: "FK_p10kanw1ubd833ui091tkhw2d", tableName: "user") {
			column(name: "domain_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1417065958114-4") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "asynchronous_jobs", constraintName: "FK_gh68tl37tghxw6il33ih4ym3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1417065958114-5") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "asynchronous_jobs", constraintName: "FK_dvw5fmbvgxwgu0uglef9v4jvi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

//	changeSet(author: "gowtham (generated)", id: "1417065958114-6") {
//		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "user", constraintName: "FK_p10kanw1ubd833ui091tkhw2d", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
//	}
}
