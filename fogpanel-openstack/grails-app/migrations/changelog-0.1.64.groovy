databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1422434778170-1") {
		createTable(tableName: "job_properties") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "job_propertiePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "asynchronous_jobs_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "job_status", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "value", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1422434778170-3") {
		createIndex(indexName: "FK_c1l0okjaxj98xu2uqdox69f1k", tableName: "job_properties") {
			column(name: "asynchronous_jobs_id")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1422434778170-2") {
		addForeignKeyConstraint(baseColumnNames: "asynchronous_jobs_id", baseTableName: "job_properties", constraintName: "FK_c1l0okjaxj98xu2uqdox69f1k", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "asynchronous_jobs", referencesUniqueColumn: "false")
	}
}
