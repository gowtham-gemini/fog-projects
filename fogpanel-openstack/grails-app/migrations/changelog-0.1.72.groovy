databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1424413802405-14") {
            
            insert(tableName: "miscellaneous_offer") {
                
                column(name: "id", valueNumeric: 8)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "monitoring alarm cost")
                column(name: "name", value: "monitoring alarm")
                column(name: "unit", value: "per monitoring alarm/month")
                column(name: "cost", value: 0)            
                
            }	
        
            insert(tableName: "billable_item") {
                
                column(name: "id", valueNumeric: 16)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "common.billableItem.monitoringAlarm")
                column(name: "reference_item_name", value: "monitoringAlarm")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
                column(name: "has_zone", valueNumeric: 0)                        
                
            }
	}

	
}
