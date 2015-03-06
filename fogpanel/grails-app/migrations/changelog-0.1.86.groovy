databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "trial management config") {
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 75)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "instance limit for trial account")
                column(name: "name", value: "instance.limit")
                column(name: "value", value: "2")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 76)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "storage limit for trial account")
                column(name: "name", value: "storage.limit")
                column(name: "value", value: "2")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 77)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "snapshot limit for trial account")
                column(name: "name", value: "snapshot.limit")
                column(name: "value", value: "2")
            } 
            
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 78)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "bandwidth limit for trial account")
                column(name: "name", value: "bandwidth.limit")
                column(name: "value", value: "5")
            } 
        }
}
