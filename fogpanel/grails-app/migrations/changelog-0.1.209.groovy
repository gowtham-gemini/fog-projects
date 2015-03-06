databaseChangeLog = {
        changeSet(author: "Gowtham (generated)", id: "retail-resource-limits") {         
            insert(tableName: "config") {
                column(name: "id", valueNumeric: 103)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "retail.enableThresholdLimit")
                column(name: "name", value: "retail.enableThresholdLimit")
                column(name: "value", value: "false")
            }
            
        }
}
