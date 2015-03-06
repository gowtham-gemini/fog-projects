databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "addressextension-1") {
		insert(tableName: "config") {
                    column(name: "id", valueNumeric: 73)
                    column(name: "version", valueNumeric: 1)
                    column(name: "description", value: "description")
                    column(name: "name", value: "organisation.address.extension")
                    column(name: "value", value: "organisation.address.extension")
                } 
	}
}
