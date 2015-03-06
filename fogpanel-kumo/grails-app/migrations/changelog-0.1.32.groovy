databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1377238988317-1") {
		addColumn(tableName: "recurring_item") {
			column(name: "billing_cycles", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1377238988317-2") {
		addColumn(tableName: "recurring_item") {
			column(name: "uses", type: "integer") {
				constraints(nullable: "false")
			}
		}
	}
}
