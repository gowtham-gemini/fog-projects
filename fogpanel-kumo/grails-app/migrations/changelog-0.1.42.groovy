databaseChangeLog = {
    
	changeSet(author: "gowtham (generated)", id: "volume_created_date") {
		addColumn(tableName: "volume") {
			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}
}
