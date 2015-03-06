databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1375507209569-1") {
		addColumn(tableName: "invoice") {
			column(name: "credit", type: "double precision") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "gowtham (generated)", id: "1375507209569-2") {
		addColumn(tableName: "invoice") {
			column(name: "previous_invoice_id", type: "bigint")
		}
	}
}
