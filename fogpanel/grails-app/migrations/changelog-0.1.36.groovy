databaseChangeLog = {

	changeSet(author: "gowtham (generated)", id: "1377778583655-1") {
		addColumn(tableName: "invoice_item") {
			column(name: "zone_id", type: "bigint")
		}
	}
}
