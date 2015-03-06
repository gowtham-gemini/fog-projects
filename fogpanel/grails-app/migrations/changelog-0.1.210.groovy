databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1415960976820-1") {
		addColumn(tableName: "account") {
			column(name: "network_limit", type: "varchar(255)")
		}
	}
            
        changeSet(author: "Gowtham (generated)", id: "retail-resource-limits-network") {         
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 104)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.network.limit")
                column(name: "name", value: "retail.network.limit")
                column(name: "value", value: "-1")
            }
            
        }
}
