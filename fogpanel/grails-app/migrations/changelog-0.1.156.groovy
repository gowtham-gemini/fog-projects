databaseChangeLog = {

        changeSet(author: "Lakshmi (generated)", id: "CC-Avenue-Config") {
		        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 87)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "Merchant Id")
                column(name: "name", value: "payment.gateway.ccavenue.merchant")
                column(name: "value", value: "")
            }
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 88)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "Working Key")
                column(name: "name", value: "payment.gateway.ccavenue.working.key")
                column(name: "value", value: "")
            }
    
        }
}
