databaseChangeLog = {
    changeSet(author: "Nandhini (generated)", id: "date-format") {         
        insert(tableName: "config") {
            column(name: "id", valueNumeric: 92)
            column(name: "version", valueNumeric: 1)
            column(name: "description", value: "Date Format")
            column(name: "name", value: "date.formate")
            column(name: "value", value: "dd/MMM/yyyy")
        }
    }
}
