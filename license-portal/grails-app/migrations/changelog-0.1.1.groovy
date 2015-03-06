databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1401940849244-1") {
		addColumn(tableName: "product_instance") {
			column(name: "registered_date", type: "datetime")
		}
	}

//	changeSet(author: "gowtham (generated)", id: "1401940849244-2") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "invoice", constraintName: "FK74D6432DB6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401940849244-3") {
//		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "invoice_item", constraintName: "FKC7D1E4E5D1EBAA89", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401940849244-4") {
//		addForeignKeyConstraint(baseColumnNames: "base_product_id", baseTableName: "product", constraintName: "FKED8DCCEFAB03CD77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401940849244-5") {
//		addForeignKeyConstraint(baseColumnNames: "product_id", baseTableName: "product_instance", constraintName: "FKBE4415E5412B3B49", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401940849244-6") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "product_instance", constraintName: "FKBE4415E5B6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401940849244-7") {
//		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A11CF906B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
//	}
//
//	changeSet(author: "gowtham (generated)", id: "1401940849244-8") {
//		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AB6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
//	}
}
