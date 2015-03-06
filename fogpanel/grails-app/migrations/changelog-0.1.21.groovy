databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1375945423014-1") {
		createTable(tableName: "recurring_item") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "recurring_itePK")
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

			column(name: "billable_item_id", type: "bigint")

			column(name: "description", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "tax_amount", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "tax_percent", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "total_amount", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1375945423014-7") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "recurring_item", constraintName: "FK8F540735A6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1375945423014-8") {
		addForeignKeyConstraint(baseColumnNames: "billable_item_id", baseTableName: "recurring_item", constraintName: "FK8F5407354C5E670E", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "billable_item", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "RECURRINGITEM-33") {
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 66)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.authorizenet.apiKey")
                column(name: "description", value: "description")
                column(name: "value", value: "")
            }
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 67)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.authorizenet.apiSecret")
                column(name: "description", value: "description")
                column(name: "value", value: "")
            }

            insert(tableName: "config") {
                column(name: "id", valueNumeric: 68)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway.authorizenet.environment")
                column(name: "description", value: "description")
                column(name: "value", value: "SANDBOX")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 69)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.gateway")
                column(name: "description", value: "description")
                column(name: "value", value: "Paypal ")
            }
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 9)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "RecurringItem")
                column(name: "reference_item_name", value: "RecurringItem")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }
	}

    }
