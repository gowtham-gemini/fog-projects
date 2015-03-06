databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "single.sign.on.url-1") {
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 74)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "single sign on url")
                column(name: "name", value: "single.sign.on.url")
                column(name: "value", value: "")
            } 
        }
}
