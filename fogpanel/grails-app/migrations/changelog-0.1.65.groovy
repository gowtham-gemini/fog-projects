databaseChangeLog = {

	changeSet(author: "nandhini (generated)", id: "1383031076546-1") {
		addColumn(tableName: "template") {
			column(name: "is_ready", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}	
    }
