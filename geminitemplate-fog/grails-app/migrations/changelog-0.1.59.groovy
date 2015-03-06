databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1420711539136-1") {
		createTable(tableName: "notification_topic") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "notification_PK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "created_at", type: "datetime")

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "deleted_at", type: "datetime")

			column(name: "name", type: "varchar(255)")

			column(name: "status", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1420711539136-2") {
		createTable(tableName: "topic_subscriber") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "topic_subscriPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "subscribed_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "token", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "topic_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "unsubscribed_date", type: "datetime")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1420711539136-14") {
		createIndex(indexName: "FK_by6p4a7fc0qymohyjnwk3ixhp", tableName: "notification_topic") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1420711539136-15") {
		createIndex(indexName: "FK_64takrjrph8hyxi3il62d2shq", tableName: "topic_subscriber") {
			column(name: "topic_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1420711539136-16") {
		createIndex(indexName: "unique_email", tableName: "topic_subscriber", unique: "true") {
			column(name: "topic_id")

			column(name: "email")
		}
	}	

	changeSet(author: "gowtham (generated)", id: "1420711539136-10") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "notification_topic", constraintName: "FK_by6p4a7fc0qymohyjnwk3ixhp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1420711539136-11") {
		addForeignKeyConstraint(baseColumnNames: "topic_id", baseTableName: "topic_subscriber", constraintName: "FK_64takrjrph8hyxi3il62d2shq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "notification_topic", referencesUniqueColumn: "false")
	}
        
        changeSet(author: "Gowtham (generated)", id: "billable-migration-14") {            
            sqlFile( path: "billableItemUpdate.sql")     
        }
}
