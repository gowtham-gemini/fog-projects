databaseChangeLog = {

	changeSet(author: "Abdul (generated)", id: "1420206971331-1") {
		addColumn(tableName: "floating_ip") {
			column(name: "created_at", type: "datetime")
		} 
	}

	changeSet(author: "Abdul (generated)", id: "1420206971331-2") {
		addColumn(tableName: "floating_ip") {
			column(name: "deleted", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "Abdul (generated)", id: "1420206971331-3") {
            addColumn(tableName: "floating_ip") {
                column(name: "deleted_at", type: "datetime")
            }
        }
}