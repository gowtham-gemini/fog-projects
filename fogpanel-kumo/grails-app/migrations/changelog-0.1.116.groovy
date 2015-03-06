databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1393321915636-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "billing_type", type: "varchar(255)")
		}
	}
        
        changeSet(author: "gowtham (generated)", id: "vm-billingtype-1") {
		sqlFile( path: "vm-billingtype.sql")       
	}
        
        changeSet(author: "gowtham (generated)", id: "credit-cardsettings-1") {
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 79)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "creditcard.processing")
                column(name: "description", value: "credit card processing for panel")
                column(name: "value", value: "false")
            }   
            
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 80)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "creditcard.processing.in.createvm")
                column(name: "description", value: "credit card processing on create vm ")
                column(name: "value", value: "false")
            }  
            
        
            insert(tableName: "billable_item") {
                column(name: "id", valueNumeric: 13)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "Monthly Instance")
                column(name: "reference_item_name", value: "Monthly Instance")
                column(name: "tax_id", valueNumeric: 1)
                column(name: "customized", valueNumeric: 0)
                column(name: "enabled", valueNumeric: 1)
                column(name: "discountable", valueNumeric: 0)
                column(name: "has_zone", valueNumeric: 1)
            }
            
	}
}
