databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1418880949777-1") {
		addColumn(tableName: "billable_item") {
			column(name: "has_zone", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}
        
        changeSet(author: "gowtham (generated)", id: "Initial-BillableItem") {
            insert(tableName: "billable_item") {
                
                    column(name: "id", valueNumeric: 1)
                    column(name: "version", valueNumeric: 1)
                    column(name: "name", value: "common.billableItem.instance")
                    column(name: "reference_item_name", value: "instance")
                    column(name: "tax_id", valueNumeric: 1)
                    column(name: "customized", valueNumeric: 0)
                    column(name: "enabled", valueNumeric: 1)
                    column(name: "discountable", valueNumeric: 0)
                    column(name: "has_zone", valueNumeric: 1)
            }
            
            insert(tableName: "billable_item") {
                
                    column(name: "id", valueNumeric: 2)
                    column(name: "version", valueNumeric: 1)
                    column(name: "name", value: "common.billableItem.volume")
                    column(name: "reference_item_name", value: "volume")
                    column(name: "tax_id", valueNumeric: 1)
                    column(name: "customized", valueNumeric: 0)
                    column(name: "enabled", valueNumeric: 1)
                    column(name: "discountable", valueNumeric: 0)
                    column(name: "has_zone", valueNumeric: 1)
            }
            
            insert(tableName: "billable_item") {
                
                    column(name: "id", valueNumeric: 3)
                    column(name: "version", valueNumeric: 1)
                    column(name: "name", value: "common.billableItem.snapshot")
                    column(name: "reference_item_name", value: "snapshot")
                    column(name: "tax_id", valueNumeric: 1)
                    column(name: "customized", valueNumeric: 0)
                    column(name: "enabled", valueNumeric: 1)
                    column(name: "discountable", valueNumeric: 0)
                    column(name: "has_zone", valueNumeric: 1)
            }
            
            insert(tableName: "billable_item") {
                
                    column(name: "id", valueNumeric: 4)
                    column(name: "version", valueNumeric: 1)
                    column(name: "name", value: "common.billableItem.network")
                    column(name: "reference_item_name", value: "network")
                    column(name: "tax_id", valueNumeric: 1)
                    column(name: "customized", valueNumeric: 0)
                    column(name: "enabled", valueNumeric: 1)
                    column(name: "discountable", valueNumeric: 0)
                    column(name: "has_zone", valueNumeric: 0)
            }
            
            insert(tableName: "billable_item") {
                
                    column(name: "id", valueNumeric: 5)
                    column(name: "version", valueNumeric: 1)
                    column(name: "name", value: "common.billableItem.vpc")
                    column(name: "reference_item_name", value: "VPC")
                    column(name: "tax_id", valueNumeric: 1)
                    column(name: "customized", valueNumeric: 0)
                    column(name: "enabled", valueNumeric: 1)
                    column(name: "discountable", valueNumeric: 0)
                    column(name: "has_zone", valueNumeric: 1)
            }
        
        }
}
