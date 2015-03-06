databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1374057308571-1") {
		createTable(tableName: "discount") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "discountPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "end_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "start_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "type", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "value", type: "double precision") {
				constraints(nullable: "false")
			}
                        
                        column(name: "is_all", type: "bit") {
                            constraints(nullable: "false")
                        }            
            
            
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-2") {
		createTable(tableName: "discount_accounts") {
			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "discount_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-3") {
		createTable(tableName: "discount_computing_offers") {
			column(name: "computing_offer_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "discount_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	

	changeSet(author: "gowtham (generated)", id: "1374057308571-8") {
		addPrimaryKey(columnNames: "discount_id, account_id", tableName: "discount_accounts")
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-9") {
		addPrimaryKey(columnNames: "discount_id, computing_offer_id", tableName: "discount_computing_offers")
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-10") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "discount_accounts", constraintName: "FKDE13D124A6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-11") {
		addForeignKeyConstraint(baseColumnNames: "discount_id", baseTableName: "discount_accounts", constraintName: "FKDE13D12471D1B3AB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "discount", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-12") {
		addForeignKeyConstraint(baseColumnNames: "computing_offer_id", baseTableName: "discount_computing_offers", constraintName: "FKD70A704055513EE2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "computing_offer", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-13") {
		addForeignKeyConstraint(baseColumnNames: "discount_id", baseTableName: "discount_computing_offers", constraintName: "FKD70A704071D1B3AB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "discount", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-22") {
		createIndex(indexName: "FKDE13D12471D1B3AB", tableName: "discount_accounts") {
			column(name: "discount_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-23") {
		createIndex(indexName: "FKDE13D124A6E55AE9", tableName: "discount_accounts") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-24") {
		createIndex(indexName: "FKD70A704055513EE2", tableName: "discount_computing_offers") {
			column(name: "computing_offer_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374057308571-25") {
		createIndex(indexName: "FKD70A704071D1B3AB", tableName: "discount_computing_offers") {
			column(name: "discount_id")
		}
	}
}
