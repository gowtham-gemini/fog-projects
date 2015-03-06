databaseChangeLog = {
	            
        changeSet(author: "Gowtham (generated)", id: "usage-billing-calc-type") {         
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 105)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "usage billing calc type")
                column(name: "name", value: "usage.billing.calc.type")
                column(name: "value", value: "HOURLY")
            }            
        }
}
