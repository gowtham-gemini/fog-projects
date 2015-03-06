databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1391145142510-1") {
		addColumn(tableName: "volume") {
			column(name: "storage_name", type: "varchar(255)")
		}
	}                
    }
