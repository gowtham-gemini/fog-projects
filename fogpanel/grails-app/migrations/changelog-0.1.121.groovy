databaseChangeLog = {
	           
        changeSet(author: "gowtham (generated)", id: "default-language") {
         
            insert(tableName: "config") {
                    column(name: "id", valueNumeric: 82)
                    column(name: "version", valueNumeric: 1)
                    column(name: "description", value: "Default language")
                    column(name: "name", value: "default.language")
                    column(name: "value", value: "en")
            }
        }
}
