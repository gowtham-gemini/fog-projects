databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1374144485757-1") {
		addColumn(tableName: "discount") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
                
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 36)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "late.fee.minimum.amount")
                column(name: "description", value: "Late fee minimum amount")
                column(name: "value", value: "100")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 37)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "late.fee.percendage")
                column(name: "description", value: "Late fee Percentage")
                column(name: "value", value: "10")
            } 
	}
}
