databaseChangeLog = {

	changeSet(author: "Nandhini (generated)", id: "1417001130088-1") {
		addColumn(tableName: "user") {
			column(name: "currency_format", type: "varchar(255)")
		}
	}

	changeSet(author: "Nandhini (generated)", id: "1417001130088-2") {
		addColumn(tableName: "user") {
			column(name: "date_format", type: "varchar(255)")
		}
	}	
        
        changeSet(author: "Nandhini (generated)", id: "1417001130088-3") {         
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 95)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "Date Format")
            column(name: "name", value: "date.formate")
            column(name: "value", value: "dd/MMM/yyyy")
        }
    }
}
