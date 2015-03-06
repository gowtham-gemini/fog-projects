databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1374144485999-1") {
		
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 38)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "payment.due.days")
                column(name: "description", value: "payment due days description")
                column(name: "value", value: "5")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 39)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "billing.cycle.fixed.enabled")
                column(name: "description", value: "billing cycle fixed enabled description")
                column(name: "value", value: "false")
            } 
            
        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 40)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "billing.period.fixed.day")
                column(name: "description", value: "fixed day description")
                column(name: "value", value: "28")
            } 
	}
}
