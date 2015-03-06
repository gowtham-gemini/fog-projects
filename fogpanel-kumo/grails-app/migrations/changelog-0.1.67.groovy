databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1383125915269-1") {
		createTable(tableName: "refund") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "refundPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(5000)")

			column(name: "invoice_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1383125915269-2") {
		addColumn(tableName: "invoice") {
			column(name: "refund_amount", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1383125915269-13") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "refund", constraintName: "FKC847DF78A6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1383125915269-14") {
		addForeignKeyConstraint(baseColumnNames: "invoice_id", baseTableName: "refund", constraintName: "FKC847DF783BEBAE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "invoice", referencesUniqueColumn: "false")
	}
		
	changeSet(author: "gowtham (generated)", id: "1383125915269-43") {
		createIndex(indexName: "FKC847DF783BEBAE9", tableName: "refund") {
			column(name: "invoice_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1383125915269-44") {
		createIndex(indexName: "FKC847DF78A6E55AE9", tableName: "refund") {
			column(name: "account_id")
		}
	}
}
