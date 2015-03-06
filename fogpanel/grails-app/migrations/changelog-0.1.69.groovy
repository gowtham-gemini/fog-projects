databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1383221499652-1") {
		addColumn(tableName: "cloud_maintenance") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}	
    }
