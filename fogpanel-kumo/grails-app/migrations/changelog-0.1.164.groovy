databaseChangeLog = {

	changeSet(author: "lakshmi (generated)", id: "1406027960413-1") {
		addColumn(tableName: "payment_gateways") {
			column(name: "gateway_type", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

}
