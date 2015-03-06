databaseChangeLog = {

    changeSet(author: "gowtham (generated)", id: "LATEFEEAPPLICABLE-2") {
	
	insert(tableName: "config") {
            column(name: "id", valueNumeric: 62)
            column(name: "version", valueNumeric: 1)
            column(name: "name", value: "late.fee.applicable.amount")
            column(name: "description", value: "description")
            column(name: "value", value: "100")
        }
    }
}
