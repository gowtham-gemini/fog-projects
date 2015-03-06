databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1376722603893-1") {
		addColumn(tableName: "virtual_machine") {
			column(name: "created_date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1376722603893-2") {
		addColumn(tableName: "virtual_machine") {
			column(name: "upgraded_date", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

}
