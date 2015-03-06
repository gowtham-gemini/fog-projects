databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1377068403846-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "cloud_previous_network_read", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1377068403846-2") {
		addColumn(tableName: "virtual_machine") {
			column(name: "cloud_previousnetwork_write", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}
        
        changeSet(author: "gowtham (generated)", id: "cloudStackServerUpDate-1") {
            
             insert(tableName: "config") {
                column(name: "id", valueNumeric: 71)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "cloud.stack.server.up.date")
                column(name: "description", value: "description")
                column(name: "value", value: "")
            }
        }
}
