databaseChangeLog = {

        changeSet(author: "Gowtham (generated)", id: "Signup-Config") {
		        
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 91)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "Sign up setting")
                column(name: "name", value: "signup.setting")
                column(name: "value", value: "PUBLIC")
            }
        }
}
