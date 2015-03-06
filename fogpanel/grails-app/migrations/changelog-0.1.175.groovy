databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1407386084234-1") {
		addColumn(tableName: "firewall_rules") {
			column(name: "zone_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407386084234-2") {
		addColumn(tableName: "load_balancer") {
			column(name: "account_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407386084234-3") {
		addColumn(tableName: "load_balancer") {
			column(name: "zone_id", type: "bigint")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407386084234-7") {
		createIndex(indexName: "FK_fc97162ts09buxi5yhikmh87i", tableName: "firewall_rules") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407386084234-8") {
		createIndex(indexName: "FK_918rays00uh39w9y6pjp3xxa3", tableName: "load_balancer") {
			column(name: "zone_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407386084234-9") {
		createIndex(indexName: "FK_fuqalnwhb0grcyg4oi5crbl3y", tableName: "load_balancer") {
			column(name: "account_id")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1407386084234-4") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "firewall_rules", constraintName: "FK_fc97162ts09buxi5yhikmh87i", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407386084234-5") {
		addForeignKeyConstraint(baseColumnNames: "account_id", baseTableName: "load_balancer", constraintName: "FK_fuqalnwhb0grcyg4oi5crbl3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "account", referencesUniqueColumn: "false")
	}

	changeSet(author: "gowtham (generated)", id: "1407386084234-6") {
		addForeignKeyConstraint(baseColumnNames: "zone_id", baseTableName: "load_balancer", constraintName: "FK_918rays00uh39w9y6pjp3xxa3", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "zone", referencesUniqueColumn: "false")
	}
        
        changeSet(author: "gowtham (generated)", id: "LB-BillableItem") {
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 18)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "common.billableItem.loadBalancer")
                column(name: "reference_item_name", value: "LoadBalancer")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
                column(name: "has_zone", valueNumeric: 1)
                
            }
                    
            insert(tableName: "miscellaneous_offer") {
               column(name: "id", valueNumeric: 5)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "LoadBalancer Cost")
                column(name: "name", value: "LoadBalancer Cost")
                column(name: "unit", value: "per Month")
            }
            
	}
    
}
