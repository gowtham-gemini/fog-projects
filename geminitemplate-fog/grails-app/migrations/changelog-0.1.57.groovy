databaseChangeLog = {

        changeSet(author: "gowtham (generated)", id: "Zenoss Config update") {
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 97)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "zenoss endpoint url")
                column(name: "name", value: "zenoss.endpoint.url")
                column(name: "value", value: "")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 98)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "zenoss username")
                column(name: "name", value: "zenoss.username")
                column(name: "value", value: "")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 99)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "zenoss password")
                column(name: "name", value: "zenoss.password")
                column(name: "value", value: "")
            } 
        
	}
}