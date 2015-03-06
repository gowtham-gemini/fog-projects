databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1401966523893-1") {
		addColumn(tableName: "validation_log") {
			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1401966523893-2") {
		modifyDataType(columnName: "deployed_date", newDataType: "bigint", tableName: "validation_log")
	}
        
        changeSet(author: "gowtham (generated)", id: "long-test-checksum") {
		modifyDataType(columnName: "checksum", newDataType: "longtext", tableName: "validation_log")
	}

	changeSet(author: "gowtham (generated)", id: "1401966523893-3") {
		modifyDataType(columnName: "last_updated_date", newDataType: "bigint", tableName: "validation_log")
	}

	changeSet(author: "gowtham (generated)", id: "1401966523893-4") {
		modifyDataType(columnName: "registration_date", newDataType: "bigint", tableName: "validation_log")
	}

//	changeSet(author: "gowtham (generated)", id: "1401966523893-5") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "invoice", constraintName: "FK74D6432DB6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401966523893-6") {
//		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "invoice_item", constraintName: "FKC7D1E4E5D1EBAA89", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401966523893-7") {
//		addForeignKeyConstraint(baseColumnNames: "base_product_id", baseTableName: "product", constraintName: "FKED8DCCEFAB03CD77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401966523893-8") {
//		addForeignKeyConstraint(baseColumnNames: "product_id", baseTableName: "product_instance", constraintName: "FKBE4415E5412B3B49", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401966523893-9") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "product_instance", constraintName: "FKBE4415E5B6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401966523893-10") {
//		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A11CF906B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401966523893-11") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AB6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
}
