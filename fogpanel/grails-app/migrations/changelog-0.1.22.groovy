databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "STOPPEDBILLABLEITEM-89") {
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 10)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "StoppedInstance")
                column(name: "reference_item_name", value: "StoppedInstance")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }
            
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 11)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "SteupCostForInstance")
                column(name: "reference_item_name", value: "SteupCost")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
            }
	}

    }
