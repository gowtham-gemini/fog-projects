databaseChangeLog = {

    changeSet(author: "abdul (generated)", id: "Openstack Config Update") {
        
        insert(tableName: "config") {
                column(name: "id", valueNumeric: 100)
                column(name: "version", valueNumeric: 1)
                column(name: "description", value: "object.store.enabled")
                column(name: "name", value: "object.store.enabled")
                column(name: "value", value: "")
            } 
        
    }
}
