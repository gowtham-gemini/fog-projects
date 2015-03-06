databaseChangeLog = {
	   
        changeSet(author: "gowtham (generated)", id: "13742241277491-78") {
		        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 41)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "organisation.address")
                column(name: "description", value: "organisation address description")
                column(name: "value", value: "12/4 street")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 42)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "organisation.address.city")
                column(name: "description", value: "organisation city description")
                column(name: "value", value: "coimbatore")
            }   
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 43)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "organisation.address.state")
                column(name: "description", value: "organisation state description")
                column(name: "value", value: "TamilNadu")
            }   
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 44)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "organisation.address.counrty")
                column(name: "description", value: "organisation country description")
                column(name: "value", value: "India")
            }  
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 45)
                column(name: "version", valueNumeric: 1)
                column(name: "name", value: "organisation.address.zip")
                column(name: "description", value: "organisation zip description")
                column(name: "value", value: "642100")
            }  
	}
}
