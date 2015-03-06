databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1418801802074-1") {
		addColumn(tableName: "account") {
			column(name: "account_identifier", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1418801802074-2") {
		addColumn(tableName: "user") {
			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1418801802074-3") {
		addColumn(tableName: "user") {
			column(name: "first_name", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1418801802074-4") {
		addColumn(tableName: "user") {
			column(name: "last_name", type: "varchar(150)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1418801802074-13") {
		dropIndex(indexName: "unique_user_name", tableName: "account")
	}

	changeSet(author: "developer (generated)", id: "1418801802074-14") {
		dropIndex(indexName: "username_uniq_1412148545265", tableName: "user")
	}

	changeSet(author: "developer (generated)", id: "1418801802074-15") {
		createIndex(indexName: "unique_account_identifier", tableName: "account", unique: "true") {
			column(name: "domain_id")

			column(name: "account_identifier")
		}
	}

	changeSet(author: "developer (generated)", id: "1418801802074-16") {
		createIndex(indexName: "unique_username", tableName: "user", unique: "true") {
			column(name: "domain_id")

			column(name: "username")
		}
	}

	changeSet(author: "developer (generated)", id: "1418801802074-17") {
		dropColumn(columnName: "cloud_password", tableName: "account")
	}

	changeSet(author: "developer (generated)", id: "1418801802074-18") {
		dropColumn(columnName: "password", tableName: "account")
	}

	changeSet(author: "developer (generated)", id: "1418801802074-19") {
		dropColumn(columnName: "user_name", tableName: "account")
	}

//	changeSet(author: "developer (generated)", id: "1418801802074-5") {
//		addForeignKeyConstraint(baseColumnNames: "billing_country_id", baseTableName: "account", constraintName: "FK_296wkqwal09diune025bhqmy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418801802074-6") {
//		addForeignKeyConstraint(baseColumnNames: "billing_state_id", baseTableName: "account", constraintName: "FK_e0e4fj7pb4j008n57dl563yg0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418801802074-7") {
//		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "account", constraintName: "FK_5ta5bijgwndhkhsv98kv5i6js", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418801802074-8") {
//		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "account", constraintName: "FK_asmmv3yhdupfaev30onvqm5lg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418801802074-9") {
//		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "account", constraintName: "FK_99vgsqatibaqbaar8onngjijq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418801802074-10") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "api_user", constraintName: "FK_6amqsmb5hmim0r67uoums68m4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418801802074-11") {
//		addForeignKeyConstraint(baseColumnNames: "tax_id", baseTableName: "billable_item", constraintName: "FK_c0mjsdvfmtqmy4sumao1nyuko", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tax", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418801802074-12") {
//		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "user", constraintName: "FK_p10kanw1ubd833ui091tkhw2d", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
//	}

        changeSet(author: "developer (generated)", id: "admin-migration-1") {
            
            sqlFile( path: "adminMigration.sql")     
	}
}
