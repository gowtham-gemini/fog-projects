databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1374731695219-1") {
		addColumn(tableName: "invoice_item") {
			column(name: "reference_plan_id", type: "varchar(255)")
		}
	}
}
