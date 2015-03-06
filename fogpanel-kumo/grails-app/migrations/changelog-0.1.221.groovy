databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1417417001988-1") {
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

			column(name: "result_description", type: "varchar(255)")

			column(name: "started_at", type: "datetime")

			column(name: "status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint")
		}
	}
	
	changeSet(author: "gowtham (generated)", id: "1417417001988-6") {
		createIndex(indexName: "FK_dvw5fmbvgxwgu0uglef9v4jvi", tableName: "asynchronous_jobs") {
			column(name: "user_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1417417001988-7") {
		createIndex(indexName: "FK_gh68tl37tghxw6il33ih4ym3y", tableName: "asynchronous_jobs") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1417417001988-3") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "asynchronous_jobs", constraintName: "FK_gh68tl37tghxw6il33ih4ym3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1417417001988-4") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "asynchronous_jobs", constraintName: "FK_dvw5fmbvgxwgu0uglef9v4jvi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
	
}
