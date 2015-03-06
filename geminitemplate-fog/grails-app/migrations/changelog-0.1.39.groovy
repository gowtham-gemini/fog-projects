databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "tax-default") {
		
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
        }
}
