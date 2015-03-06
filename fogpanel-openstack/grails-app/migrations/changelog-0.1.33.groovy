databaseChangeLog = {

	changeSet(author: "developer (generated)", id: "1418648904397-1") {
		addColumn(tableName: "account") {
			column(name: "domain_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1418648904397-2") {
		addColumn(tableName: "domain") {
			column(name: "created_at", type: "datetime")
		}
	}

	changeSet(author: "developer (generated)", id: "1418648904397-3") {
		addColumn(tableName: "domain") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1418648904397-4") {
		addColumn(tableName: "domain") {
			column(name: "deleted_at", type: "datetime")
		}
	}

	changeSet(author: "developer (generated)", id: "1418648904397-5") {
		addColumn(tableName: "domain") {
			column(name: "description", type: "longtext")
		}
	}

	changeSet(author: "developer (generated)", id: "1418648904397-6") {
		addColumn(tableName: "domain") {
			column(name: "is_default", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1418648904397-7") {
		addColumn(tableName: "user") {
			column(name: "domain_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "developer (generated)", id: "1418648904397-8") {
		modifyDataType(columnName: "created_at", newDataType: "datetime", tableName: "network_subnet")
	}

	changeSet(author: "developer (generated)", id: "1418648904397-9") {
		dropNotNullConstraint(columnDataType: "datetime", columnName: "created_at", tableName: "network_subnet")
	}

	changeSet(author: "developer (generated)", id: "1418648904397-10") {
		modifyDataType(columnName: "deleted_at", newDataType: "datetime", tableName: "network_subnet")
	}

	changeSet(author: "developer (generated)", id: "1418648904397-11") {
		dropNotNullConstraint(columnDataType: "datetime", columnName: "deleted_at", tableName: "network_subnet")
	}

	changeSet(author: "developer (generated)", id: "1418648904397-12") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "image_id", tableName: "virtual_machine")
	}

	changeSet(author: "developer (generated)", id: "1418648904397-13") {
		dropNotNullConstraint(columnDataType: "bigint", columnName: "volume_id", tableName: "volume_snapshot")
	}

	changeSet(author: "developer (generated)", id: "1418648904397-22") {
		dropIndex(indexName: "user_name_uniq_1412148545228", tableName: "account")
	}

	changeSet(author: "developer (generated)", id: "1418648904397-23") {
		createIndex(indexName: "FK_asmmv3yhdupfaev30onvqm5lg", tableName: "account") {
			column(name: "domain_id")
		}
	}

	changeSet(author: "developer (generated)", id: "1418648904397-24") {
		createIndex(indexName: "unique_user_name", tableName: "account", unique: "true") {
			column(name: "domain_id")

			column(name: "user_name")
		}
	}

	changeSet(author: "developer (generated)", id: "1418648904397-25") {
		createIndex(indexName: "FK_p10kanw1ubd833ui091tkhw2d", tableName: "user") {
			column(name: "domain_id")
		}
	}

	changeSet(author: "developer (generated)", id: "1418648904397-26") {
		dropColumn(columnName: "extra", tableName: "domain")
	}

//	changeSet(author: "developer (generated)", id: "1418648904397-14") {
//		addForeignKeyConstraint(baseColumnNames: "billing_country_id", baseTableName: "account", constraintName: "FK_296wkqwal09diune025bhqmy3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418648904397-15") {
//		addForeignKeyConstraint(baseColumnNames: "billing_state_id", baseTableName: "account", constraintName: "FK_e0e4fj7pb4j008n57dl563yg0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418648904397-16") {
//		addForeignKeyConstraint(baseColumnNames: "country_id", baseTableName: "account", constraintName: "FK_5ta5bijgwndhkhsv98kv5i6js", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "country", referencesUniqueColumn: "false")
//	}

//	changeSet(author: "developer (generated)", id: "1418648904397-17") {
//		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "account", constraintName: "FK_asmmv3yhdupfaev30onvqm5lg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
//	}

//	changeSet(author: "developer (generated)", id: "1418648904397-18") {
//		addForeignKeyConstraint(baseColumnNames: "state_id", baseTableName: "account", constraintName: "FK_99vgsqatibaqbaar8onngjijq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "state", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418648904397-19") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "api_user", constraintName: "FK_6amqsmb5hmim0r67uoums68m4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418648904397-20") {
//		addForeignKeyConstraint(baseColumnNames: "tax_id", baseTableName: "billable_item", constraintName: "FK_c0mjsdvfmtqmy4sumao1nyuko", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tax", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "developer (generated)", id: "1418648904397-21") {
//		addForeignKeyConstraint(baseColumnNames: "domain_id", baseTableName: "user", constraintName: "FK_p10kanw1ubd833ui091tkhw2d", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "domain", referencesUniqueColumn: "false")
//	}
}
