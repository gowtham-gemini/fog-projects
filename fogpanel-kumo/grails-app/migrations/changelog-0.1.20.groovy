databaseChangeLog = {

    changeSet(author: "gowtham (generated)", id: "CREDITLIMITLEVEL-1") {
	
	insert(tableName: "config") {
            column(name: "id", valueNumeric: 63)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "credit.limit.notification.level1")
            column(name: "description", value: "description")
            column(name: "value", value: "50")
        }
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 64)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "credit.limit.notification.level2")
            column(name: "description", value: "description")
            column(name: "value", value: "80")
        }
        
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 65)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "credit.limit.notification.level3")
            column(name: "description", value: "description")
            column(name: "value", value: "90")
        }
    }
}
