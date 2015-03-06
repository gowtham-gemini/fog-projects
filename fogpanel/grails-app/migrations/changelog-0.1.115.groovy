databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1391597637868-1") {
		createTable(tableName: "api_user") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "api_userPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "api_key", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "secret", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "user_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1391597637868-16") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "api_user", constraintName: "FK39AA6AD0A0981FEB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}
	
	changeSet(author: "gowtham (generated)", id: "1391597637868-122") {
		createIndex(indexName: "FK39AA6AD0A0981FEB", tableName: "api_user") {
			column(name: "user_id")
		}
	}
}
