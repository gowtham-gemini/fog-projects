databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1393564405026-1") {
		addColumn(tableName: "snapshot") {
			column(name: "billing_type", type: "varchar(255)")
		}
	}

	changeSet(author: "gowtham (generated)", id: "1393564405026-2") {
		addColumn(tableName: "volume") {
			column(name: "billing_type", type: "varchar(255)")
		}
	}
            
        changeSet(author: "gowtham (generated)", id: "billingtype-volume-snapshot-1") {
         
            insert(tableName: "billable_item") {
                    column(name: "id", valueNumeric: 14)
                    column(name: "version", valueNumeric: 1)
                    column(name: "name", value: "Monthly Volume")
                    column(name: "reference_item_name", value: "Monthly Volume")
                    column(name: "tax_id", valueNumeric: 1)
                    column(name: "customized", valueNumeric: 0)
                    column(name: "enabled", valueNumeric: 1)
                    column(name: "discountable", valueNumeric: 0)
                    column(name: "has_zone", valueNumeric: 1)
            }

            insert(tableName: "billable_item") {
                    column(name: "id", valueNumeric: 15)
                    column(name: "version", valueNumeric: 1)
                    column(name: "name", value: "Monthly Snapshot")
                    column(name: "reference_item_name", value: "Monthly Snapshot")
                    column(name: "tax_id", valueNumeric: 1)
                    column(name: "customized", valueNumeric: 0)
                    column(name: "enabled", valueNumeric: 1)
                    column(name: "discountable", valueNumeric: 0)
                    column(name: "has_zone", valueNumeric: 1)
            }   
        
        
        }
    
	
}
