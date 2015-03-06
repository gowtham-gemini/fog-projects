databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1374224120491-1") {
		addColumn(tableName: "billable_item") {
			column(name: "discountable", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374224120491-2") {
		addColumn(tableName: "invoice") {
			column(name: "current_due", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374224120491-3") {
		addColumn(tableName: "invoice") {
			column(name: "date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374224120491-4") {
		addColumn(tableName: "invoice") {
			column(name: "payment", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374224120491-5") {
		addColumn(tableName: "invoice") {
			column(name: "previous_invoice_date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374224120491-6") {
		addColumn(tableName: "invoice_item") {
			column(name: "discount_amount", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374224120491-7") {
		addColumn(tableName: "invoice_item") {
			column(name: "discount_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374224120491-8") {
		addColumn(tableName: "invoice_item") {
			column(name: "discount_percent", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1374224120491-13") {
		addForeignKeyConstraint(baseColumnNames: "discount_id", baseTableName: "invoice_item", constraintName: "FKC7D1E4E571D1B3AB", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "discount", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1374224120491-61") {
		dropColumn(columnName: "last_payment", tableName: "invoice")
	}
        
    
        changeSet(author: "gowtham (generated)", id: "1374224120491-78") {
		
            insert(tableName: "tax") {
                column(name: "id", valueNumeric: 1)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "N/A")
                column(name: "description", value: "N/A")
                column(name: "percentage", valueNumeric: 0)
                
            }
            
        
            insert(tableName: "tax") {
                column(name: "id", valueNumeric: 2)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "vat")
                column(name: "description", value: "vat")
                column(name: "percentage", valueNumeric: 2)
            }
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 1)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "ComputingOffer")
                column(name: "reference_item_name", value: "computingOffer")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 2)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "DiskOffer")
                column(name: "reference_item_name", value: "diskOffer")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 3)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "SnapShot")
                column(name: "reference_item_name", value: "snapShot")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 4)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "Template")
                column(name: "reference_item_name", value: "template")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }
            
        
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 5)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "BandWidth ")
                column(name: "reference_item_name", value: "bandWidth ")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }
	}
}
