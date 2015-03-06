databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1375081976372-1") {
		createTable(tableName: "payments") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "paymentsPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "amount", type: "double precision")

			column(name: "payment_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "payment_tocken", type: "varchar(255)")

			column(name: "processing_fee", type: "double precision") {
				constraints(nullable: "false")
			}

			column(name: "total_amount", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}
	
	changeSet(author: "gowtham (generated)", id: "1375081976372-6") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "payments", constraintName: "FK526A0F2DA6E55AE9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1375081976372-29") {
		createIndex(indexName: "FK526A0F2DA6E55AE9", tableName: "payments") {
			column(name: "account_id")
		}
	}
        
        changeSet(author: "gowtham (generated)", id: "paymentConfig-67") {
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 59)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.processing.fee.percentage")
                column(name: "description", value: "description")
                column(name: "value", value: "2")
            }   
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 60)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.processing.fee.amount")
                column(name: "description", value: "description")
                column(name: "value", value: "10")
            }   
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 61)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.processing.fee.type")
                column(name: "description", value: "description")
                column(name: "value", value: "INCLUDE")
            }
	}
}
