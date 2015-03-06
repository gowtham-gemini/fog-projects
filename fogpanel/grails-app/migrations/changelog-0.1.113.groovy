databaseChangeLog = {
    changeSet(author: "nandhini (generated)", id: "1391073168622-1") {
        addColumn(tableName: "virtual_machine") {
            column(name: "host_name", type: "varchar(255)")
        }
    }	
}
