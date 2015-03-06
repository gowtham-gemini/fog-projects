databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1405321870771-1") {
		createTable(tableName: "applications") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "applicationsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405321870771-2") {
		addColumn(tableName: "product") {
			column(name: "code", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}
	
	changeSet(author: "gowtham (generated)", id: "1405321870771-13") {
		createIndex(indexName: "code_uniq_1405321870570", tableName: "product", unique: "true") {
			column(name: "code")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1405321870771-3") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "invoice", constraintName: "FK74D6432DB6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405321870771-4") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "invoice_item", constraintName: "FKC7D1E4E5D1EBAA89", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405321870771-5") {
		addForeignKeyConstraint(baseColumnNames: "base_product_id", baseTableName: "product", constraintName: "FKED8DCCEFAB03CD77", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405321870771-6") {
		addForeignKeyConstraint(baseColumnNames: "product_id", baseTableName: "product_instance", constraintName: "FKBE4415E5412B3B49", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405321870771-7") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "product_instance", constraintName: "FKBE4415E5B6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405321870771-8") {
		addForeignKeyConstraint(baseColumnNames: "product_id", baseTableName: "product_release", constraintName: "FKD2763CB7412B3B49", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405321870771-9") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FK143BF46A11CF906B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405321870771-10") {
		addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK143BF46AB6FA544B", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1405321870771-11") {
		addForeignKeyConstraint(baseColumnNames: "product_instance_id", baseTableName: "validation_log", constraintName: "FKEF59367E68B90148", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "product_instance", referencesUniqueColumn: "false")
	}
}
