databaseChangeLog = {

	changeSet(author: "lakshmi (generated)", id: "1406283115307-1") {
		addColumn(tableName: "payment_gateways") {
			column(name: "gatewayurl", type: "varchar(255)")
		}
	}
        
        
}
